/**
    Copyright 2017 Andrea "Stock" Stocchero

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

	    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/
package org.pepstock.charba.client.data;

import java.util.HashMap;
import java.util.Map;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.ChartNode;
import org.pepstock.charba.client.colors.Gradient;
import org.pepstock.charba.client.colors.GradientColor;
import org.pepstock.charba.client.colors.GradientOrientation;
import org.pepstock.charba.client.colors.GradientScope;
import org.pepstock.charba.client.colors.GradientType;
import org.pepstock.charba.client.colors.Pattern;
import org.pepstock.charba.client.items.ChartAreaNode;
import org.pepstock.charba.client.items.DatasetItem;
import org.pepstock.charba.client.items.DatasetMetaItem;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.CanvasGradient;
import com.google.gwt.canvas.dom.client.CanvasPattern;
import com.google.gwt.canvas.dom.client.Context2d;

/**
 * Utility class which creates a canvas gradient and pattern java script objects using a Charba gradient or pattern.<br>
 * A Charba gradient or pattern describes how a GWT canvas gradient or pattern must be created.
 * 
 * @author Andrea "Stock" Stocchero
 * 
 * @see Gradient
 * @see Pattern
 * @see com.google.gwt.canvas.dom.client.CanvasGradient
 * @see com.google.gwt.canvas.dom.client.CanvasPattern
 */
public final class CanvasObjectFactory {

	// cache for canvas gradients already created
	// K = chart id, K = gradient id, V = canvas gradient
	private static final Map<String, Map<Integer, CanvasGradient>> GRADIENTS = new HashMap<String, Map<Integer, CanvasGradient>>();

	// cache for canvas patterns already created
	// K = chart id, K = pattern id, V = canvas pattern
	private static final Map<String, Map<Integer, CanvasPattern>> PATTERNS = new HashMap<String, Map<Integer, CanvasPattern>>();

	/**
	 * To avoid any instantiation
	 */
	private CanvasObjectFactory() {
		// do nothing
	}

	/**
	 * Creates a GWT canvas pattern java script object using a Charba pattern and a chart instance which must provide a canvas
	 * instance and its context.
	 * 
	 * @param chart chart instance which must provide a canvas instance and its context
	 * @param pattern pattern instance created at configuration level
	 * @return a GWT canvas pattern
	 */
	public static CanvasPattern createPattern(AbstractChart<?, ?> chart, Pattern pattern) {
		final Map<Integer, CanvasPattern> patternsMap;
		// checks if the pattern is already created
		if (PATTERNS.containsKey(chart.getId())) {
			patternsMap = PATTERNS.get(chart.getId());
			if (patternsMap.containsKey(pattern.getId())) {
				// returns the existing canvas pattern
				return patternsMap.get(pattern.getId());
			}
		} else {
			// new chart!
			// creates the cache for the chart
			patternsMap = new HashMap<>();
			PATTERNS.put(chart.getId(), patternsMap);
		}
		// checks if canvas pattern already loaded
		if (pattern.getCanvasPattern() != null) {
			return pattern.getCanvasPattern();
		}
		// gets canvas and context 2d
		Canvas canvas = chart.getCanvas();
		Context2d context = canvas.getContext2d();
		// creates the pattern
		CanvasPattern result = context.createPattern(pattern.getImage(), pattern.getRepetition());
		// stores canvas pattern into cache
		patternsMap.put(pattern.getId(), result);
		// returns result
		return result;
	}

	/**
	 * Removes from cache all loaded gradients, needed during the resize of chart.
	 * 
	 * @param chart chart instance on which removes all loaded gradients
	 */
	static void resetGradients(AbstractChart<?, ?> chart) {
		GRADIENTS.remove(chart.getId());
	}

	/**
	 * Clears the cache of loaded patterns and gradients, needed when a chart is destroy.
	 * 
	 * @param chart chart instance on which removes all loaded objects.
	 */
	static void clear(AbstractChart<?, ?> chart) {
		PATTERNS.remove(chart.getId());
		GRADIENTS.remove(chart.getId());
	}

	/**
	 * Creates a GWT canvas gradient java script object using a Charba gradient and a chart instance which must provide a canvas
	 * instance and its context.
	 * 
	 * @param chart chart instance which must provide a canvas instance and its context
	 * @param gradient gradient instance created at configuration level
	 * @param datasetIndex dataset index
	 * @param index index of gradient related to index of dataset item of whole dataset
	 * @return a GWT canvas gradient
	 */
	public static CanvasGradient createGradient(AbstractChart<?, ?> chart, Gradient gradient, int datasetIndex, int index) {
		// checks if the gradient is already created
		final Map<Integer, CanvasGradient> gradientsMap;
		// checks if the gradient is already created
		if (GRADIENTS.containsKey(chart.getId())) {
			gradientsMap = GRADIENTS.get(chart.getId());
			if (gradientsMap.containsKey(gradient.getId())) {
				// returns the existing canvas gradient
				return gradientsMap.get(gradient.getId());
			}
		} else {
			// new chart!
			// creates the cache for the chart
			gradientsMap = new HashMap<>();
			GRADIENTS.put(chart.getId(), gradientsMap);
		}
		// checks if chart is initialized
		if (chart.isInitialized()) {
			// creates the result instance
			CanvasGradient result = null;
			// checks if the gradient must be linear oe radial
			if (GradientType.linear.equals(gradient.getType())) {
				// creates a linear
				result = createLinearGradient(chart, gradient);
			} else {
				// creates a radial
				result = createRadialGradient(chart, gradient, datasetIndex, index);
			}
			// checks if result is consistent
			if (result != null) {
				// scans all colors to add to gradient
				for (GradientColor color : gradient.getColors()) {
					// adds colors using offset and color
					result.addColorStop(color.getOffset(), color.getColorAsString());
				}
			}
			// stores canvas gradient into cache
			gradientsMap.put(gradient.getId(), result);
			// returns result
			return result;
		} else {
			// if here,
			// chart is not initialized
			// then throws an exception
			throw new IllegalArgumentException("Chart is not initialized");
		}
	}

	/**
	 * Creates a linear gradient, an image consisting of a progressive transition between two or more colors along a straight
	 * line.
	 * 
	 * @param chart chart instance which must provide a canvas instance and its context
	 * @param gradient gradient instance created at configuration level
	 * @return a GWT linear canvas gradient
	 */
	private static CanvasGradient createLinearGradient(AbstractChart<?, ?> chart, Gradient gradient) {
		// gets canvas and context 2d
		Canvas canvas = chart.getCanvas();
		Context2d context = canvas.getContext2d();
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
		final double top;
		final double bottom;
		final double left;
		final double right;
		// depending of scope (canvas or chart area)
		if (GradientScope.canvas.equals(gradient.getScope())) {
			// sets the coordinates of scope
			// CANVAS
			top = 0;
			left = 0;
			right = canvas.getOffsetWidth();
			bottom = canvas.getOffsetHeight();
		} else {
			// sets the coordinates of scope
			// CHART AREA
			ChartAreaNode chartArea = chart.getNode().getChartArea();
			top = chartArea.getTop();
			left = chartArea.getLeft();
			bottom = chartArea.getBottom();
			right = chartArea.getRight();
		}
		// checks the orientation requires by gradient
		// and then calculates the coordinates instances of gradient
		if (GradientOrientation.topDown.equals(gradient.getOrientation())) {
			// TOP-DOWN: top --> bottom
			x0 = left;
			y0 = top;
			x1 = left;
			y1 = bottom;
		} else if (GradientOrientation.bottomUp.equals(gradient.getOrientation())) {
			// BOTTOM-UP: bottom --> up
			x0 = left;
			y0 = bottom;
			x1 = left;
			y1 = top;
		} else if (GradientOrientation.leftRight.equals(gradient.getOrientation())) {
			// LEFT-RIGHT: left --> right
			x0 = left;
			y0 = top;
			x1 = right;
			y1 = top;
		} else if (GradientOrientation.rightLeft.equals(gradient.getOrientation())) {
			// RIGHT-LEFT: right --> left
			x0 = right;
			y0 = top;
			x1 = left;
			y1 = top;
		} else if (GradientOrientation.topRight.equals(gradient.getOrientation())) {
			// DIAGONAL TOP-RIGHT: top(left) --> bottom(right)
			x0 = left;
			y0 = top;
			x1 = right;
			y1 = bottom;
		} else if (GradientOrientation.bottomLeft.equals(gradient.getOrientation())) {
			// DIAGONAL BOTTOM-LEFT: bottom(right) --> top(left)
			x0 = right;
			y0 = bottom;
			x1 = left;
			y1 = top;
		} else if (GradientOrientation.topLeft.equals(gradient.getOrientation())) {
			// DIAGONAL TOP-LEFT: top(right) --> bottom(left)
			x0 = right;
			y0 = top;
			x1 = left;
			y1 = bottom;
		} else if (GradientOrientation.bottomRight.equals(gradient.getOrientation())) {
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
		// returns GWT canvas gradient
		// by GWT context 2d method
		return context.createLinearGradient(x0, y0, x1, y1);
	}

	/**
	 * Creates a radial gradient, an image consisting of a progressive transition between two or more colors that radiate from
	 * an origin. Its shape may be a circle or an ellipse.
	 * 
	 * @param chart chart instance which must provide a canvas instance and its context
	 * @param gradient gradient instance created at configuration level
	 * @param datasetIndex dataset index
	 * @param index index of gradient related to index of dataset item of whole dataset
	 * @return a GWT radial canvas gradient
	 */
	private static CanvasGradient createRadialGradient(AbstractChart<?, ?> chart, Gradient gradient, int datasetIndex, int index) {
		// gets canvas and context 2d
		Canvas canvas = chart.getCanvas();
		Context2d context = canvas.getContext2d();
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
		final double centerX;
		final double centerY;
		final double radius0;
		final double radius1;
		// depending of scope (canvas or chart area)
		if (GradientScope.canvas.equals(gradient.getScope())) {
			// gets chart node
			ChartNode node = chart.getNode();
			// CANVAS
			// the center of canvas has the following coordinates:
			// X - the width divided by 2
			// Y - the height divided by 2
			centerX = (canvas.getOffsetWidth() / 2D);
			centerY = (canvas.getOffsetHeight() / 2D);
			// checks if the radius is already calculated by CHART.JS
			// depending on chart type
			if (!Double.isNaN(node.getInnerRadius()) && !Double.isNaN(node.getOuterRadius())) {
				// gets meta data
				DatasetMetaItem metaItem = chart.getDatasetMeta(datasetIndex);
				// checks if datasetIndex is consistent
				if (metaItem != null && index < metaItem.getDatasets().size() && index >= 0) {
					DatasetItem item = metaItem.getDatasets().get(index);
					// checks if chart is circular or not
					if (!Double.isNaN(item.getView().getInnerRadius()) && !Double.isNaN(item.getView().getOuterRadius())) {
						// uses the inner radius
						radius0 = item.getView().getInnerRadius();
						// uses the outer radius
						radius1 = item.getView().getOuterRadius();
					} else {
						// uses the inner radius
						radius0 = node.getInnerRadius();
						// uses the outer radius
						radius1 = node.getOuterRadius();
					}
				} else {
					// uses the inner radius
					radius0 = node.getInnerRadius();
					// uses the outer radius
					radius1 = node.getOuterRadius();
				}
			} else {
				// by default is the center of chart area
				radius0 = 0;
				// radius - if max value between width and height, divided by 2
				radius1 = (Math.max(canvas.getOffsetWidth(), canvas.getOffsetHeight()) / 2D);
			}
		} else {
			// gets chart node
			ChartNode node = chart.getNode();
			// gets chart area
			ChartAreaNode chartArea = node.getChartArea();
			// CHART
			// the center of canvas has the following coordinates:
			// X - the difference between right and left, divided by 2 plus left
			// Y - the difference between bottom and top, divided by 2 plus top
			centerX = ((chartArea.getRight() - chartArea.getLeft()) / 2D) + chartArea.getLeft();
			centerY = ((chartArea.getBottom() - chartArea.getTop()) / 2D) + chartArea.getTop();
			// checks if the radius is already calculated by CHART.JS
			// depending on chart type
			if (!Double.isNaN(node.getInnerRadius()) && !Double.isNaN(node.getOuterRadius())) {
				// gets meta data
				DatasetMetaItem metaItem = chart.getDatasetMeta(datasetIndex);
				// checks if datasetIndex is consistent
				if (metaItem != null && index < metaItem.getDatasets().size() && index >= 0) {
					DatasetItem item = metaItem.getDatasets().get(index);
					// checks if chart is circular or not
					if (!Double.isNaN(item.getView().getInnerRadius()) && !Double.isNaN(item.getView().getOuterRadius())) {
						// uses the inner radius
						radius0 = item.getView().getInnerRadius();
						// uses the outer radius
						radius1 = item.getView().getOuterRadius();
					} else {
						// uses the inner radius
						radius0 = node.getInnerRadius();
						// uses the outer radius
						radius1 = node.getOuterRadius();
					}
				} else {
					// uses the inner radius
					radius0 = node.getInnerRadius();
					// uses the outer radius
					radius1 = node.getOuterRadius();
				}
			} else {
				// by default is the center of chart area
				radius0 = 0;
				// radius - if max value between the difference between right and left and the difference between bottom and
				// top,
				// divided by 2
				radius1 = (Math.max((chartArea.getRight() - chartArea.getLeft()), (chartArea.getBottom() - chartArea.getTop())) / 2D);
			}
		}
		// checks the orientation requires by gradient
		// and then calculates the coordinates instances of gradient
		if (GradientOrientation.inOut.equals(gradient.getOrientation())) {
			// from center to border of scope (canvas or chart area) O-->
			x0 = centerX;
			y0 = centerY;
			r0 = radius0;
			x1 = centerX;
			y1 = centerY;
			r1 = radius1;
		} else if (GradientOrientation.outIn.equals(gradient.getOrientation())) {
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
		// returns GWT canvas gradient
		// by GWT context 2d method
		return context.createRadialGradient(x0, y0, r0, x1, y1, r1);
	}

}
