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
 * FIXME
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class BoxAnnotationElement extends AbstractAnnotationElement<BoxAnnotation> {

	private double left = UndefinedValues.DOUBLE;

	private double top = UndefinedValues.DOUBLE;

	private double right = UndefinedValues.DOUBLE;

	private double bottom = UndefinedValues.DOUBLE;

	/**
	 * Creates an annotation element by an annotation configuration.
	 * 
	 * @param chart chart instance
	 * @param configuration annotation configuration element
	 */
	BoxAnnotationElement(IsChart chart, BoxAnnotation annotation) {
		super(chart, annotation);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.myannotation.AbstractAnnotationElement#configureElement(org.pepstock.charba.client.items.ChartAreaNode, java.util.Map)
	 */
	@Override
	void configureElement(ChartAreaNode area, Map<String, ScaleItem> scalesMap) {
		// stores the model info
		left = area.getLeft();
		right = area.getRight();
		top = area.getTop();
		bottom = area.getBottom();
		// checks if scale nodes contains the scale id for X
		if (scalesMap.containsKey(getConfiguration().getXScaleID().value())) {
			ScaleItem xScale = scalesMap.get(getConfiguration().getXScaleID().value());
			configureScale(area, xScale, true);
		}
		// checks if scale nodes contains the scale id for Y
		if (scalesMap.containsKey(getConfiguration().getYScaleID().value())) {
			ScaleItem yScale = scalesMap.get(getConfiguration().getYScaleID().value());
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
		ctx.setLineWidth(getConfiguration().getBorderWidth());
		ctx.setStrokeColor(getConfiguration().getBorderColorAsString());
		ctx.setFillColor(getConfiguration().getBackgroundColorAsString());
		// Draw
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
	 * 
	 * @param area
	 * @param scale
	 * @param scaleLimit
	 * @param isXScale
	 */
	private void configureScale(ChartAreaNode area, ScaleItem scale, boolean isXScale) {
		double min = UndefinedValues.DOUBLE;
		double max = UndefinedValues.DOUBLE;
		final double minLimit = isXScale ? area.getLeft() : area.getBottom();
		final double maxLimit = isXScale ? area.getRight() : area.getTop();
		if (ScaleDataType.STRING.equals(scale.getType().getDataType())) {
			final String minString = isXScale ? getConfiguration().getXMinAsString() : getConfiguration().getYMinAsString();
			final String maxString = isXScale ? getConfiguration().getXMaxAsString() : getConfiguration().getYMaxAsString();
			min = getValuePositionFromString(minString, scale, minLimit);
			max = getValuePositionFromString(maxString, scale, maxLimit);
		} else if (ScaleDataType.DATE.equals(scale.getType().getDataType())) {
			final Date minDate = isXScale ? getConfiguration().getXMinAsDate() : getConfiguration().getYMinAsDate();
			final Date maxDate = isXScale ? getConfiguration().getXMaxAsDate() : getConfiguration().getYMaxAsDate();
			min = getValuePositionFromDate(minDate, scale, minLimit);
			max = getValuePositionFromDate(maxDate, scale, maxLimit);
		} else if (ScaleDataType.NUMBER.equals(scale.getType().getDataType())) {
			final double minDouble = isXScale ? getConfiguration().getXMinAsDouble() : getConfiguration().getYMinAsDouble();
			final double maxDouble = isXScale ? getConfiguration().getXMaxAsDouble() : getConfiguration().getYMaxAsDouble();
			min = getValuePosition(minDouble, scale, minLimit);
			max = getValuePosition(maxDouble, scale, maxLimit);
		}
		if (isXScale) {
			left = Math.min(min, max);
			right = Math.max(min, max);
		} else {
			top = Math.min(min, max);
			bottom = Math.max(min, max);
		}
	}

}
