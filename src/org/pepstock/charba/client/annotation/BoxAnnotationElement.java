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
package org.pepstock.charba.client.annotation;

import java.util.Date;
import java.util.Map;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.dom.BaseNativeEvent;
import org.pepstock.charba.client.dom.elements.Context2dItem;
import org.pepstock.charba.client.enums.ScaleDataType;
import org.pepstock.charba.client.items.ChartAreaNode;
import org.pepstock.charba.client.items.ScaleItem;
import org.pepstock.charba.client.items.UndefinedValues;

/**
 * Internal class created for each box annotation options configured from the plugin.<br>
 * It uses the box annotation option to calculate the area to be drawn.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class BoxAnnotationElement extends AbstractAnnotationElement<BoxAnnotation> {

	// area limits
	private double left = UndefinedValues.DOUBLE;

	private double top = UndefinedValues.DOUBLE;

	private double right = UndefinedValues.DOUBLE;

	private double bottom = UndefinedValues.DOUBLE;

	private final MinMaxCointainer minMaxContainer = new MinMaxCointainer();

	/**
	 * Creates an annotation element by an annotation configuration.
	 * 
	 * @param chart chart instance
	 * @param configuration annotation configuration element
	 */
	BoxAnnotationElement(IsChart chart, BoxAnnotation configuration) {
		super(chart, configuration);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.myannotation.AbstractAnnotationElement#configureElement(org.pepstock.charba.client.items.ChartAreaNode, java.util.Map)
	 */
	@Override
	void configureElement(ChartAreaNode area, Map<String, ScaleItem> scalesMap) {
		// stores the chart area size as default
		left = area.getLeft();
		right = area.getRight();
		top = area.getTop();
		bottom = area.getBottom();
		// --------------
		// SCALE X
		// --------------
		// checks if scale nodes contains the scale id for X
		if (scalesMap.containsKey(getConfiguration().getXScaleID().value())) {
			// gets scale X
			ScaleItem xScale = scalesMap.get(getConfiguration().getXScaleID().value());
			// configures the annotation element by the scale instance
			configureScale(area, xScale, true);
		}
		// --------------
		// SCALE Y
		// --------------
		// checks if scale nodes contains the scale id for Y
		if (scalesMap.containsKey(getConfiguration().getYScaleID().value())) {
			// gets scale Y
			ScaleItem yScale = scalesMap.get(getConfiguration().getYScaleID().value());
			// configures the annotation element by the scale instance
			configureScale(area, yScale, false);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.myannotation.AbstractAnnotationElement#isConsistent()
	 */
	@Override
	boolean isConsistent() {
		return !Double.isNaN(left) && !Double.isNaN(right) && !Double.isNaN(top) && !Double.isNaN(bottom);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.myannotation.AbstractAnnotationElement#drawElement(org.pepstock.charba.client.items.ChartAreaNode,
	 * org.pepstock.charba.client.dom.elements.Context2dItem)
	 */
	@Override
	void drawElement(ChartAreaNode area, Context2dItem ctx) {
		// sets the line width and color of the rectangle border
		ctx.setLineWidth(getConfiguration().getBorderWidth());
		ctx.setStrokeColor(getConfiguration().getBorderColorAsString());
		// sets the color of the rectangle
		ctx.setFillColor(getConfiguration().getBackgroundColorAsString());
		// Draws the rectangle
		double width = right - left;
		double height = bottom - top;
		ctx.fillRect(left, top, width, height);
		ctx.strokeRect(left, top, width, height);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.myannotation.AbstractAnnotationElement#isInside(org.pepstock.charba.client.dom.BaseNativeEvent)
	 */
	@Override
	boolean isInside(BaseNativeEvent event) {
		// checks if consistent
		if (isConsistent()) {
			// checks X
			boolean isX = event.getLayerX() >= left && event.getLayerX() <= right;
			// checks Y
			boolean isY = event.getLayerY() >= top && event.getLayerY() <= bottom;
			// merges the results
			return isX && isY;
		}
		// if here is not consistent
		// then always false
		return false;
	}

	/**
	 * Configures the box annotation element calculating left, top, right and bottom points of the rectangle to draw.<br>
	 * It uses the scale in order to get the positions of the values passed by box annotation options.
	 * 
	 * @param area chart area instance
	 * @param scale scale instance to use to configure
	 * @param isXScale if <code>true</code>, the configuration of element must take care that is for scale X
	 */
	private void configureScale(ChartAreaNode area, ScaleItem scale, boolean isXScale) {
		// resets minimum and maximum references
		minMaxContainer.setMinimum(UndefinedValues.DOUBLE);
		minMaxContainer.setMaximum(UndefinedValues.DOUBLE);
		// gets the minimum and maximum limit of chart area
		// based on if is configuring a X scale
		final double minLimit = isXScale ? area.getLeft() : area.getBottom();
		final double maxLimit = isXScale ? area.getRight() : area.getTop();
		// checks the data type managed by the selected scale
		if (ScaleDataType.STRING.equals(scale.getType().getDataType())) {
			// --------------
			// CATEGORY scale
			// manages String
			// --------------
			// gets the minimum and maximum pixel value
			retrieveMinMaxFromScaleForString(scale, isXScale, minLimit, maxLimit);
		} else if (ScaleDataType.DATE.equals(scale.getType().getDataType())) {
			// ------------------------
			// TIME or TIMESERIES scales
			// manage Date
			// ------------------------
			// gets the minimum and maximum value configured for annotation
			retrieveMinMaxFromScaleForDate(scale, isXScale, minLimit, maxLimit);
		} else if (ScaleDataType.NUMBER.equals(scale.getType().getDataType())) {
			// ----------------------------
			// LINEAR or LOGARITHMIC scales
			// manage Double
			// ----------------------------
			retrieveMinMaxFromScaleForDouble(scale, isXScale, minLimit, maxLimit);
		}
		// checks if is asking for configuration against a scale X
		if (isXScale) {
			// if X scale
			// sets left and right
			left = Math.min(minMaxContainer.getMinimum(), minMaxContainer.getMaximum());
			right = Math.max(minMaxContainer.getMinimum(), minMaxContainer.getMaximum());
		} else {
			// if Y scale
			// sets top and bottom
			top = Math.min(minMaxContainer.getMinimum(), minMaxContainer.getMaximum());
			bottom = Math.max(minMaxContainer.getMinimum(), minMaxContainer.getMaximum());
		}
	}

	/**
	 * Retrieves the minimum and maximum values from the scale, category one, in pixel.
	 * 
	 * @param scale scale instance to use to configure
	 * @param isXScale if <code>true</code>, the configuration of element must take care that is for scale X
	 * @param minLimit limit value to set in case the minimum value calculation is not consistent
	 * @param maxLimit limit value to set in case the maximum value calculation is not consistent
	 */
	private void retrieveMinMaxFromScaleForString(ScaleItem scale, boolean isXScale, double minLimit, double maxLimit) {
		// --------------
		// CATEGORY scale
		// manages String
		// --------------
		// gets the minimum and maximum value configured for annotation
		final String minString = isXScale ? getConfiguration().getXMinAsString() : getConfiguration().getYMinAsString();
		final String maxString = isXScale ? getConfiguration().getXMaxAsString() : getConfiguration().getYMaxAsString();
		// gets the position in pixel on chart area for the minimum value
		minMaxContainer.setMinimum(getValuePositionFromString(minString, scale, minLimit));
		// gets the position in pixel on chart area for the maximum value
		minMaxContainer.setMaximum(getValuePositionFromString(maxString, scale, maxLimit));
	}

	/**
	 * Retrieves the minimum and maximum values from the scale, time and time-series ones, in pixel.
	 * 
	 * @param scale scale instance to use to configure
	 * @param isXScale if <code>true</code>, the configuration of element must take care that is for scale X
	 * @param minLimit limit value to set in case the minimum value calculation is not consistent
	 * @param maxLimit limit value to set in case the maximum value calculation is not consistent
	 */
	private void retrieveMinMaxFromScaleForDate(ScaleItem scale, boolean isXScale, double minLimit, double maxLimit) {
		// ------------------------
		// TIME or TIMESERIES scales
		// manage Date
		// ------------------------
		// gets the minimum and maximum value configured for annotation
		final Date minDate = isXScale ? getConfiguration().getXMinAsDate() : getConfiguration().getYMinAsDate();
		final Date maxDate = isXScale ? getConfiguration().getXMaxAsDate() : getConfiguration().getYMaxAsDate();
		// gets the position in pixel on chart area for the minimum value
		minMaxContainer.setMinimum(getValuePositionFromDate(minDate, scale, minLimit));
		// gets the position in pixel on chart area for the maximum value
		minMaxContainer.setMaximum(getValuePositionFromDate(maxDate, scale, maxLimit));
	}

	/**
	 * Retrieves the minimum and maximum values from the scale, linear and logarithmic ones, in pixel.
	 * 
	 * @param scale scale instance to use to configure
	 * @param isXScale if <code>true</code>, the configuration of element must take care that is for scale X
	 * @param minLimit limit value to set in case the minimum value calculation is not consistent
	 * @param maxLimit limit value to set in case the maximum value calculation is not consistent
	 */
	private void retrieveMinMaxFromScaleForDouble(ScaleItem scale, boolean isXScale, double minLimit, double maxLimit) {
		// ----------------------------
		// LINEAR or LOGARITHMIC scales
		// manage Double
		// ----------------------------
		// gets the minimum and maximum value configured for annotation
		final double minDouble = isXScale ? getConfiguration().getXMinAsDouble() : getConfiguration().getYMinAsDouble();
		final double maxDouble = isXScale ? getConfiguration().getXMaxAsDouble() : getConfiguration().getYMaxAsDouble();
		// gets the position in pixel on chart area for the minimum value
		minMaxContainer.setMinimum(getValuePosition(minDouble, scale, minLimit));
		// gets the position in pixel on chart area for the maximum value
		minMaxContainer.setMaximum(getValuePosition(maxDouble, scale, maxLimit));
	}

	/**
	 * Internal class to wrap minimum and maximum pixel values.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static final class MinMaxCointainer {

		private double minimum = Double.NaN;

		private double maximum = Double.NaN;

		/**
		 * Returns the minimum value.
		 * 
		 * @return the minimum value
		 */
		double getMinimum() {
			return minimum;
		}

		/**
		 * Sets the minimum value.
		 * 
		 * @param minimum the minimum value
		 */
		void setMinimum(double minimum) {
			this.minimum = minimum;
		}

		/**
		 * Returns the maximum value.
		 * 
		 * @return the maximum value
		 */
		double getMaximum() {
			return maximum;
		}

		/**
		 * Sets the maximum value.
		 * 
		 * @param maximum the maximum value
		 */
		void setMaximum(double maximum) {
			this.maximum = maximum;
		}

	}

}