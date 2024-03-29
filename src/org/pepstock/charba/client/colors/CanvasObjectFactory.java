/**
    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.
*/
package org.pepstock.charba.client.colors;

import java.util.HashMap;
import java.util.Map;

import org.pepstock.charba.client.Charts;
import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.Constants;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.dom.elements.Canvas;
import org.pepstock.charba.client.dom.elements.CanvasGradientItem;
import org.pepstock.charba.client.dom.elements.CanvasPatternItem;
import org.pepstock.charba.client.dom.elements.Context2dItem;
import org.pepstock.charba.client.items.IsArea;

/**
 * Abstract utility class which creates a canvas gradient and pattern java script objects using a Charba gradient or pattern.<br>
 * A Charba gradient or pattern describes how a canvas gradient or pattern must be created.
 * 
 * @author Andrea "Stock" Stocchero
 * 
 * @see Gradient
 * @see Pattern
 * @see CanvasGradientItem
 * @see CanvasPatternItem
 */
public abstract class CanvasObjectFactory {

	// cache for canvas gradients already created
	// K = chart id, K = gradient id, V = canvas gradient
	private final Map<String, Map<String, CanvasGradientItem>> gradientsCache = new HashMap<>();
	// cache for canvas patterns already created
	// K = chart id, K = pattern id, V = canvas pattern
	private final Map<String, Map<String, CanvasPatternItem>> patternCache = new HashMap<>();

	// scope instance
	private final Key scope;

	/**
	 * Creates the object using the scope of this factory.
	 * 
	 * @param scope scope of this factory
	 */
	protected CanvasObjectFactory(Key scope) {
		this.scope = Key.checkAndGetIfValid(scope);
	}

	/**
	 * Returns the scope of this factory.
	 * 
	 * @return the scope of this factory
	 */
	public final Key getScope() {
		return scope;
	}

	/**
	 * Creates a canvas pattern java script object using a Charba pattern and a chart instance which must provide a canvas instance and its context.
	 * 
	 * @param chart chart instance which must provide a canvas instance and its context
	 * @param pattern pattern instance created at configuration level
	 * @return a canvas pattern
	 */
	public final CanvasPatternItem createPattern(IsChart chart, Pattern pattern) {
		// checks if chart is consistent
		IsChart.checkIfValid(chart);
		// checks if pattern is consistent
		Checker.checkIfValid(pattern, "Pattern argument");
		// map instance
		final Map<String, CanvasPatternItem> patternsMap;
		// checks if the pattern is already created
		if (patternCache.containsKey(chart.getId())) {
			patternsMap = patternCache.get(chart.getId());
			if (patternsMap.containsKey(pattern.getId())) {
				// returns the existing canvas pattern
				return patternsMap.get(pattern.getId());
			}
		} else {
			// new chart!
			// creates the cache for the chart
			patternsMap = new HashMap<>();
			patternCache.put(chart.getId(), patternsMap);
		}
		// checks if canvas pattern already loaded
		if (pattern.getCanvasPattern() != null) {
			CanvasPatternItem result = pattern.getCanvasPattern();
			// stores canvas pattern in the cache
			patternsMap.put(pattern.getId(), result);
			return result;
		}
		// gets canvas and context 2d
		Canvas canvas = chart.getCanvas();
		Context2dItem context = canvas.getContext2d();
		// creates the pattern
		CanvasPatternItem result = context.createPattern(pattern.getImage(), pattern.getRepetition());
		// checks if result is consistent
		// to avoid to add a pattern not consistent
		if (result != null) {
			// stores the canvas id
			pattern.store(result);
			// stores canvas pattern in the cache
			patternsMap.put(pattern.getId(), result);
		}
		// returns result
		return result;
	}

	/**
	 * Removes from cache all loaded gradients, needed during the resize of chart.
	 * 
	 * @param chart chart instance on which removes all loaded gradients
	 */
	public final void resetGradients(IsChart chart) {
		// checks if chart is consistent
		if (IsChart.isValid(chart)) {
			gradientsCache.remove(chart.getId());
		}
	}

	/**
	 * Clears the cache of loaded patterns and gradients, needed when a chart is destroy.
	 * 
	 * @param chart chart instance on which removes all loaded objects.
	 */
	public final void clear(IsChart chart) {
		// checks if chart is consistent
		if (IsChart.isValid(chart)) {
			patternCache.remove(chart.getId());
			gradientsCache.remove(chart.getId());
		}
	}

	/**
	 * Creates a canvas gradient java script object using a Charba gradient and a chart instance which must provide a canvas instance and its context.
	 * 
	 * @param chart chart instance which must provide a canvas instance and its context
	 * @param gradient gradient instance created at configuration level
	 * @param datasetIndex data set index
	 * @return a canvas gradient
	 */
	public final CanvasGradientItem createGradient(IsChart chart, Gradient gradient, int datasetIndex) {
		// checks if chart is consistent
		IsChart.checkIfValid(chart);
		// checks if gradient is consistent
		Checker.checkIfValid(gradient, "Gradient argument");
		// checks if the gradient is already created
		final Map<String, CanvasGradientItem> gradientsMap;
		// creates the unique id
		String uniqueId = createGradientUniqueId(gradient);
		// checks if the gradient is already created
		if (gradientsCache.containsKey(chart.getId())) {
			gradientsMap = gradientsCache.get(chart.getId());
			if (gradientsMap.containsKey(uniqueId)) {
				// returns the existing canvas gradient
				return gradientsMap.get(uniqueId);
			}
		} else {
			// new chart!
			// creates the cache for the chart
			gradientsMap = new HashMap<>();
			gradientsCache.put(chart.getId(), gradientsMap);
		}
		// checks if chart is initialized
		// if chart is not initialized
		// then throws an exception
		Checker.assertCheck(chart.isInitialized() || Charts.hasNative(chart.getId()), "Chart is not initialized");
		// creates the result instance
		CanvasGradientItem result = null;
		// checks if the gradient must be linear oe radial
		if (GradientType.LINEAR.equals(gradient.getType())) {
			// creates a linear
			result = createLinearGradient(chart, gradient);
		} else {
			// creates a radial
			result = createRadialGradient(chart, gradient, datasetIndex);
		}
		// checks if result is consistent
		if (result != null) {
			// stores id
			gradient.store(result);
			// scans all colors to add to gradient
			for (GradientColor color : gradient.getColors()) {
				// adds colors using offset and color
				result.addColorStop(color.getOffset(), color.getColorAsString());
			}
			// stores canvas gradient in the cache
			gradientsMap.put(uniqueId, result);
		}
		// returns result
		return result;
	}

	/**
	 * Creates a linear gradient, an image consisting of a progressive transition between two or more colors along a straight line.
	 * 
	 * @param chart chart instance which must provide a canvas instance and its context
	 * @param gradient gradient instance created at configuration level
	 * @return a linear canvas gradient
	 */
	private CanvasGradientItem createLinearGradient(IsChart chart, Gradient gradient) {
		// gets canvas and context 2d
		Canvas canvas = chart.getCanvas();
		Context2dItem context = canvas.getContext2d();
		// these are the coordinates instances of gradient
		// x0 - the x coordinate of the starting point of the gradient
		// y0 - the y coordinate of the starting point of the gradient
		// x1 - the x coordinate of the ending point of the gradient
		// y1 - the y coordinate of the ending point of the gradient
		double x0 = 0;
		double y0 = 0;
		double x1 = 0;
		double y1 = 0;
		// these are the coordinates instances of scope
		// left - the x coordinate of the starting point of the scope
		// top - the y coordinate of the starting point of the scope
		// right - the x coordinate of the ending point of the scope
		// bottom - the y coordinate of the ending point of the scope
		final Area area = getArea(chart, gradient);
		// checks if area is consistent
		Checker.checkIfValid(area, "Area for linear gradient");
		Checker.assertCheck(IsArea.isConsistent(area), "Area for linear gradient is not consistent");
		final double top = area.getTop();
		final double bottom = area.getBottom();
		final double left = area.getLeft();
		final double right = area.getRight();
		// checks the orientation requires by gradient
		// and then calculates the coordinates instances of gradient
		if (GradientOrientation.TOP_DOWN.equals(gradient.getOrientation())) {
			// TOP-DOWN: top --> bottom
			x0 = left;
			y0 = top;
			x1 = left;
			y1 = bottom;
		} else if (GradientOrientation.BOTTOM_UP.equals(gradient.getOrientation())) {
			// BOTTOM-UP: bottom --> up
			x0 = left;
			y0 = bottom;
			x1 = left;
			y1 = top;
		} else if (GradientOrientation.LEFT_RIGHT.equals(gradient.getOrientation())) {
			// LEFT-RIGHT: left --> right
			x0 = left;
			y0 = top;
			x1 = right;
			y1 = top;
		} else if (GradientOrientation.RIGHT_LEFT.equals(gradient.getOrientation())) {
			// RIGHT-LEFT: right --> left
			x0 = right;
			y0 = top;
			x1 = left;
			y1 = top;
		} else if (GradientOrientation.TOP_RIGHT.equals(gradient.getOrientation())) {
			// DIAGONAL TOP-RIGHT: top(left) --> bottom(right)
			x0 = left;
			y0 = top;
			x1 = right;
			y1 = bottom;
		} else if (GradientOrientation.BOTTOM_LEFT.equals(gradient.getOrientation())) {
			// DIAGONAL BOTTOM-LEFT: bottom(right) --> top(left)
			x0 = right;
			y0 = bottom;
			x1 = left;
			y1 = top;
		} else if (GradientOrientation.TOP_LEFT.equals(gradient.getOrientation())) {
			// DIAGONAL TOP-LEFT: top(right) --> bottom(left)
			x0 = right;
			y0 = top;
			x1 = left;
			y1 = bottom;
		} else if (GradientOrientation.BOTTOM_RIGHT.equals(gradient.getOrientation())) {
			// DIAGONAL BOTTOM-RIGHT: bottom(left) --> top(right)
			x0 = left;
			y0 = bottom;
			x1 = right;
			y1 = top;
		} else {
			// if here, the scope is invalid for a linear gradient
			// then exception
			throw new IllegalArgumentException("Gradient orientation is wrong [" + gradient.getOrientation() + "]");
		}
		// returns canvas gradient
		// by context 2d method
		return context.createLinearGradient(x0, y0, x1, y1);
	}

	/**
	 * Creates a radial gradient, an image consisting of a progressive transition between two or more colors that radiate from an origin. Its shape may be a circle or an ellipse.
	 * 
	 * @param chart chart instance which must provide a canvas instance and its context
	 * @param gradient gradient instance created at configuration level
	 * @param datasetIndex data set index
	 * @return a radial canvas gradient
	 */
	private CanvasGradientItem createRadialGradient(IsChart chart, Gradient gradient, int datasetIndex) {
		// gets canvas and context 2d
		Canvas canvas = chart.getCanvas();
		Context2dItem context = canvas.getContext2d();
		// these are the coordinates and radius instances of gradient
		// x0 - the x coordinate of the center of the start circle of the gradient
		// y0 - the y coordinate of the center of the start circle of the gradient
		// r0 - the radius of the start circle of the gradient
		// x1 - the x coordinate of the center of the end circle of the gradient
		// y1 - the y coordinate of the center of the end circle of the gradient
		// r1 - the radius of the end circle of the gradient
		double x0 = 0;
		double y0 = 0;
		double r0 = 0;
		double x1 = 0;
		double y1 = 0;
		double r1 = 0;
		// these are the coordinates of center and radius of scope
		final Center center = getCenter(chart, gradient, datasetIndex);
		// checks if center is consistent
		Checker.checkIfValid(center, "Center for radial gradient");
		final double centerX = center.getX();
		final double centerY = center.getY();
		final Radius radius = getRadius(chart, gradient, datasetIndex);
		// checks if radius is consistent
		Checker.checkIfValid(radius, "Radius for radial gradient");
		final double radius0 = radius.getInner();
		final double radius1 = radius.getOuter();
		// checks the orientation requires by gradient
		// and then calculates the coordinates instances of gradient
		if (GradientOrientation.IN_OUT.equals(gradient.getOrientation())) {
			// from center to border of scope (canvas or chart area) O-->
			x0 = centerX;
			y0 = centerY;
			r0 = radius0;
			x1 = centerX;
			y1 = centerY;
			r1 = radius1;
		} else if (GradientOrientation.OUT_IN.equals(gradient.getOrientation())) {
			// from border of scope to center (canvas or chart area) -->O
			x0 = centerX;
			y0 = centerY;
			r0 = radius1;
			x1 = centerX;
			y1 = centerY;
			r1 = radius0;
		} else {
			// if here, the scope is invalid for a linear gradient
			// then exception
			throw new IllegalArgumentException("Gradient orientation is wrong [" + gradient.getOrientation() + "]");
		}
		// returns canvas gradient
		// by context 2d method
		return context.createRadialGradient(x0, y0, r0, x1, y1, r1);
	}

	/**
	 * Creates the unique id for gradient using the scope.<br>
	 * This is important in order to use the dame gradient from different scope having different {@link CanvasGradientItem}.
	 * 
	 * @param gradient gradient instance to use
	 * @return the unique id for gradient using the scope
	 */
	private String createGradientUniqueId(Gradient gradient) {
		StringBuilder sb = new StringBuilder(gradient.getId());
		// adds the scope and returns it
		return sb.append(Constants.MINUS).append(getScope().value()).toString();
	}

	/**
	 * Returns an area object used to define the area of LINEAR gradient.
	 * 
	 * @param chart chart instance
	 * @param gradient gradient instance
	 * @return an area object used to define the area of LINEAR gradient
	 */
	protected abstract Area getArea(IsChart chart, Gradient gradient);

	/**
	 * Returns the coordinates of a center of arc for RADIAL gradient.
	 * 
	 * @param chart chart instance
	 * @param gradient gradient instance
	 * @param datasetIndex data set index
	 * @return the coordinates of a center of arc
	 */
	protected abstract Center getCenter(IsChart chart, Gradient gradient, int datasetIndex);

	/**
	 * Returns the inner and outer radius of arc for RADIAL gradient.
	 * 
	 * @param chart chart instance
	 * @param gradient gradient instance
	 * @param datasetIndex data set index
	 * @return the inner and outer radius of arc
	 */
	protected abstract Radius getRadius(IsChart chart, Gradient gradient, int datasetIndex);

}