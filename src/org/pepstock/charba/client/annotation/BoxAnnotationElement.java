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
import org.pepstock.charba.client.callbacks.AnnotationValueCallback;
import org.pepstock.charba.client.colors.Area;
import org.pepstock.charba.client.colors.CanvasObjectFactory;
import org.pepstock.charba.client.colors.Center;
import org.pepstock.charba.client.colors.Gradient;
import org.pepstock.charba.client.colors.Pattern;
import org.pepstock.charba.client.colors.Radius;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.dom.BaseNativeEvent;
import org.pepstock.charba.client.dom.elements.Context2dItem;
import org.pepstock.charba.client.enums.ColorType;
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

	private final MinMaxContainer minMaxContainer = new MinMaxContainer();

	private final BoxAnnotationCanvasObjectFactory canvasObjectFactory;

	// area limits
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
	BoxAnnotationElement(IsChart chart, BoxAnnotation configuration) {
		super(chart, configuration);
		// creates the canvas object factory
		this.canvasObjectFactory = new BoxAnnotationCanvasObjectFactory(this);
	}

	/**
	 * Returns the left position of the annotation.
	 * 
	 * @return the left position of the annotation
	 */
	double getLeft() {
		return left;
	}

	/**
	 * Returns the top position of the annotation.
	 * 
	 * @return the top position of the annotation
	 */
	double getTop() {
		return top;
	}

	/**
	 * Returns the right position of the annotation.
	 * 
	 * @return the right position of the annotation
	 */
	double getRight() {
		return right;
	}

	/**
	 * Returns the bottom position of the annotation.
	 * 
	 * @return the bottom position of the annotation
	 */
	double getBottom() {
		return bottom;
	}

	/**
	 * Returns the width of the annotation.
	 * 
	 * @return the width of the annotation
	 */
	double getWidth() {
		return right - left;
	}

	/**
	 * Returns the height of the annotation.
	 * 
	 * @return the height of the annotation
	 */
	double getHeight() {
		return bottom - top;
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
			configureScale(area, xScale, true, getConfiguration().getXMinCallback(), getConfiguration().getXMaxCallback());
		}
		// --------------
		// SCALE Y
		// --------------
		// checks if scale nodes contains the scale id for Y
		if (scalesMap.containsKey(getConfiguration().getYScaleID().value())) {
			// gets scale Y
			ScaleItem yScale = scalesMap.get(getConfiguration().getYScaleID().value());
			// configures the annotation element by the scale instance
			configureScale(area, yScale, false, getConfiguration().getYMinCallback(), getConfiguration().getYMaxCallback());
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
		// gets color type of background
		ColorType backgroundColorType = getConfiguration().getBackgroundColorType();
		// check type of background color
		if (ColorType.GRADIENT.equals(backgroundColorType)) {
			// GRADIENT
			// gets gradient
			Gradient gradient = getConfiguration().getBackgroundColorAsGradient();
			// sets the gradient of the rectangle
			ctx.setFillGradient(canvasObjectFactory.createGradient(getChart(), gradient, UndefinedValues.INTEGER, UndefinedValues.INTEGER));
		} else if (ColorType.PATTERN.equals(backgroundColorType)) {
			// PATTERN
			// gets pattern
			Pattern pattern = getConfiguration().getBackgroundColorAsPattern();
			// sets the pattern of the rectangle
			ctx.setFillPattern(canvasObjectFactory.createPattern(getChart(), pattern));
		} else {
			// COLOR
			// sets the color of the rectangle
			ctx.setFillColor(getConfiguration().getBackgroundColorAsString());
		}
		// Draws the rectangle
		double width = getWidth();
		double height = getHeight();
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.annotation.AbstractAnnotationElement#destroyElement()
	 */
	@Override
	void destroyElement() {
		// clean the canvas objects
		// previously loaded
		canvasObjectFactory.clear(getChart());
	}

	/**
	 * Configures the box annotation element calculating left, top, right and bottom points of the rectangle to draw.<br>
	 * It uses the scale in order to get the positions of the values passed by box annotation options.
	 * 
	 * @param area chart area instance
	 * @param scale scale instance to use to configure
	 * @param isXScale if <code>true</code>, the configuration of element must take care that is for scale X
	 * @param minCallback the value callback to calculate the minimum value 
	 * @param maxCallback the value callback to calculate the maximum value 
	 */
	private void configureScale(ChartAreaNode area, ScaleItem scale, boolean isXScale, AnnotationValueCallback minCallback, AnnotationValueCallback maxCallback) {
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
			retrieveMinMaxFromScaleForString(scale, isXScale, minLimit, maxLimit, minCallback, maxCallback);
		} else if (ScaleDataType.DATE.equals(scale.getType().getDataType())) {
			// ------------------------
			// TIME or TIMESERIES scales
			// manage Date
			// ------------------------
			// gets the minimum and maximum value configured for annotation
			retrieveMinMaxFromScaleForDate(scale, isXScale, minLimit, maxLimit, minCallback, maxCallback);
		} else if (ScaleDataType.NUMBER.equals(scale.getType().getDataType())) {
			// ----------------------------
			// LINEAR or LOGARITHMIC scales
			// manage Double
			// ----------------------------
			retrieveMinMaxFromScaleForDouble(scale, isXScale, minLimit, maxLimit, minCallback, maxCallback);
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
	 * @param minCallback the value callback to calculate the minimum value 
	 * @param maxCallback the value callback to calculate the maximum value 
	 */
	private void retrieveMinMaxFromScaleForString(ScaleItem scale, boolean isXScale, double minLimit, double maxLimit, AnnotationValueCallback minCallback, AnnotationValueCallback maxCallback) {
		// --------------
		// CATEGORY scale
		// manages String
		// --------------
		// -------
		// MIN
		// -------
		final String minString;
		// checks if there is any value callback
		if (minCallback != null) {
			// invokes the callback
			Object result = minCallback.compute(getChart(), getConfiguration());
			// checks if the result of callback is consistent
			if (result != null) {
				// stores the result as string
				minString = result.toString();
			} else {
				// if here, the result is null
				// then undefined
				minString = UndefinedValues.STRING;
			}
		} else {
			// if here, there is not callback
			// then reads the value from configuration
			minString = isXScale ? getConfiguration().getXMinAsString() : getConfiguration().getYMinAsString();
		}
		// -------
		// MAX
		// -------
		final String maxString;
		// checks if there is any value callback
		if (maxCallback != null) {
			// invokes the callback
			Object result = maxCallback.compute(getChart(), getConfiguration());
			// checks if the result of callback is consistent
			if (result != null) {
				// stores the result as string
				maxString = result.toString();
			} else {
				// if here, the result is null
				// then undefined
				maxString = UndefinedValues.STRING;
			}
		} else {
			// if here, there is not callback
			// then reads the value from configuration
			maxString = isXScale ? getConfiguration().getXMaxAsString() : getConfiguration().getYMaxAsString();
		}
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
	 * @param minCallback the value callback to calculate the minimum value 
	 * @param maxCallback the value callback to calculate the maximum value 
	 */
	private void retrieveMinMaxFromScaleForDate(ScaleItem scale, boolean isXScale, double minLimit, double maxLimit, AnnotationValueCallback minCallback, AnnotationValueCallback maxCallback) {
		// ------------------------
		// TIME or TIMESERIES scales
		// manage Date
		// ------------------------
		// -------
		// MIN
		// -------
		final Date minDate;
		// checks if there is any value callback
		if (minCallback != null) {
			// invokes the callback
			Object result = minCallback.compute(getChart(), getConfiguration());
			// checks if the result of callback is consistent
			if (result instanceof Date) {
				// stores the result as date
				minDate = (Date) result;
			} else {
				// if here, the result is null
				// then null
				minDate = null;
			}
		} else {
			// if here, there is not callback
			// then reads the value from configuration
			minDate = isXScale ? getConfiguration().getXMinAsDate() : getConfiguration().getYMinAsDate();
		}
		// -------
		// MAX
		// -------
		// gets the end value configured for annotation
		final Date maxDate;
		// checks if there is any value callback
		if (maxCallback != null) {
			// invokes the callback
			Object result = maxCallback.compute(getChart(), getConfiguration());
			// checks if the result of callback is consistent
			if (result instanceof Date) {
				// stores the result as date
				maxDate = (Date) result;
			} else {
				// if here, the result is null
				// then undefined
				maxDate = null;
			}
		} else {
			// if here, there is not callback
			// then reads the value from configuration
			maxDate = isXScale ? getConfiguration().getXMaxAsDate() : getConfiguration().getYMaxAsDate();
		}
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
	 * @param minCallback value callback set to calculate the minimum value
	 * @param maxCallback value callback set to calculate the maximum value
	 */
	private void retrieveMinMaxFromScaleForDouble(ScaleItem scale, boolean isXScale, double minLimit, double maxLimit, AnnotationValueCallback minCallback, AnnotationValueCallback maxCallback) {
		// ----------------------------
		// LINEAR or LOGARITHMIC scales
		// manage Double
		// ----------------------------
		// -------
		// MIN
		// -------
		final double minDouble;
		// checks if there is any value callback
		if (minCallback != null) {
			// invokes the callback
			Object result = minCallback.compute(getChart(), getConfiguration());
			// checks if the result of callback is consistent
			if (result instanceof Number) {
				// casts to a number
				Number tempResult = (Number)result;
				// stores the result as number
				minDouble = tempResult.doubleValue();
			} else {
				// if here, the result is null
				// then null
				minDouble = UndefinedValues.DOUBLE;
			}
		} else {
			// if here, there is not callback
			// then reads the value from configuration
			minDouble = isXScale ? getConfiguration().getXMinAsDouble() : getConfiguration().getYMinAsDouble();
		}
		// -------
		// MAX
		// -------
		final double maxDouble;
		// checks if there is any value callback
		if (maxCallback != null) {
			// invokes the callback
			Object result = maxCallback.compute(getChart(), getConfiguration());
			// checks if the result of callback is consistent
			if (result instanceof Number) {
				// stores the result as double
				// casts to a number
				Number tempResult = (Number)result;
				// stores the result as number
				maxDouble = tempResult.doubleValue();
			} else {
				// if here, the result is null
				// then undefined
				maxDouble = UndefinedValues.DOUBLE;
			}
		} else {
			// if here, there is not callback
			// then reads the value from configuration
			maxDouble = isXScale ? getConfiguration().getXMaxAsDouble() : getConfiguration().getYMaxAsDouble();
		}
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
	private static final class MinMaxContainer {

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

	/**
	 * Internal canvas objects factory to create gradient and pattern fo box annotation.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class BoxAnnotationCanvasObjectFactory extends CanvasObjectFactory {
		
		// scope of the factory
		private static final Key SCOPE = Key.create(Annotation.ID);

		private final BoxAnnotationElement annotationElement;

		/**
		 * Creates the canvas objects factory by the box annotation element.
		 * 
		 * @param annotationElement box annotation element instance
		 */
		private BoxAnnotationCanvasObjectFactory(BoxAnnotationElement annotationElement) {
			super(SCOPE);
			this.annotationElement = annotationElement;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.colors.CanvasObjectFactory#getArea(org.pepstock.charba.client.IsChart, org.pepstock.charba.client.colors.Gradient)
		 */
		@Override
		protected Area getArea(IsChart chart, Gradient gradient) {
			// IGNORE THE SCOPE of gradient
			final Area area = new Area();
			// stores the coordinates
			area.setTop(annotationElement.getTop());
			area.setLeft(annotationElement.getLeft());
			area.setRight(annotationElement.getRight());
			area.setBottom(annotationElement.getBottom());
			// returns area
			return area;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.colors.CanvasObjectFactory#getCenter(org.pepstock.charba.client.IsChart, org.pepstock.charba.client.colors.Gradient, int, int)
		 */
		@Override
		protected Center getCenter(IsChart chart, Gradient gradient, int datasetIndex, int index) {
			// calculates X
			final double x = annotationElement.getLeft() + (annotationElement.getWidth() / 2D);
			final double y = annotationElement.getTop() + (annotationElement.getHeight() / 2D);
			// IGNORE THE SCOPE of gradient
			final Center center = new Center();
			// the center of box has the following coordinates:
			// X - the right minus left, divided by 2
			// Y - the bottom minus top, divided by 2
			center.setX(x);
			center.setY(y);
			// returns center
			return center;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.colors.CanvasObjectFactory#getRadius(org.pepstock.charba.client.IsChart, org.pepstock.charba.client.colors.Gradient, int, int)
		 */
		@Override
		protected Radius getRadius(IsChart chart, Gradient gradient, int datasetIndex, int index) {
			// by default is the center of chart box
			final Radius radius = new Radius();
			radius.setInner(0);
			// the outer is max between width and height, divided by 2
			radius.setOuter(Math.max(annotationElement.getWidth(), annotationElement.getHeight()) / 2D);
			// returns radius
			return radius;
		}

	}

}
