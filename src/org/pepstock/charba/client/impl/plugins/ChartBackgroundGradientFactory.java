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
package org.pepstock.charba.client.impl.plugins;

import java.util.HashMap;
import java.util.Map;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.colors.Gradient;
import org.pepstock.charba.client.colors.GradientColor;
import org.pepstock.charba.client.colors.GradientOrientation;
import org.pepstock.charba.client.colors.GradientType;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.CanvasGradient;
import com.google.gwt.canvas.dom.client.Context2d;

/**
 * Utility used by CHART {@link ChartBackgroundColor#ID} PLUGIN.<br>
 * Utility class which creates a canvas gradient java script object using a Charba gradient.<br>
 * A Charba gradient describes how a GWT canvas gradient must be created.
 * 
 * @author Andrea "Stock" Stocchero
 * 
 * @see Gradient
 * @see com.google.gwt.canvas.dom.client.CanvasGradient
 */
final class ChartBackgroundGradientFactory {

	// cache for canvas gradients already created
	// K = chart id, K = gradient id, V = canvas gradient
	private static final Map<String, Map<Integer, CanvasGradient>> GRADIENTS = new HashMap<String, Map<Integer, CanvasGradient>>();

	/**
	 * To avoid any instantiation
	 */
	private ChartBackgroundGradientFactory() {
		// do nothing
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
		GRADIENTS.remove(chart.getId());
	}

	/**
	 * Creates a GWT canvas gradient java script object using a Charba gradient and a chart instance which must provide a canvas
	 * instance and its context.
	 * 
	 * @param chart chart instance which must provide a canvas instance and its context
	 * @param gradient gradient instance created at configuration level
	 * @return a GWT canvas gradient
	 */
	static CanvasGradient createGradient(AbstractChart<?, ?> chart, Gradient gradient) {
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
			if (GradientType.LINEAR.equals(gradient.getType())) {
				// creates a linear
				result = createLinearGradient(chart, gradient);
			} else {
				// creates a radial
				result = createRadialGradient(chart, gradient);
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
		// IGNORE THE SCOPE of gradient
		// ALWAYS CANVAS
		// these are the coordinates instances of canvas
		// left - the x coordinate of the starting point of the canvas
		// top - the y coordinate of the starting point of the canvas
		// right - the x coordinate of the ending point of the canvas
		// bottom - the y coordinate of the ending point of the canvas
		final double top = 0;
		final double bottom = canvas.getOffsetHeight();
		final double left = 0;
		final double right = canvas.getOffsetWidth();
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
	 * @return a GWT radial canvas gradient
	 */
	private static CanvasGradient createRadialGradient(AbstractChart<?, ?> chart, Gradient gradient) {
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
		// IGNORE THE SCOPE of gradient
		// ALWAYS CANVAS
		// the center of canvas has the following coordinates:
		// X - the width divided by 2
		// Y - the height divided by 2
		// these are the coordinates of center and radius of canvas
		final double centerX = (canvas.getOffsetWidth() / 2D);
		final double centerY = (canvas.getOffsetHeight() / 2D);
		// by default is the center of chart area
		// radius - if max value between width and height, divided by 2
		final double radius0 = 0;
		final double radius1 = (Math.max(canvas.getOffsetWidth(), canvas.getOffsetHeight()) / 2D);
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
		// returns GWT canvas gradient
		// by GWT context 2d method
		return context.createRadialGradient(x0, y0, r0, x1, y1, r1);
	}

}
