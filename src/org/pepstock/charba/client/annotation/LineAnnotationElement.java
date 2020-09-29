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
import java.util.List;
import java.util.Map;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.dom.BaseNativeEvent;
import org.pepstock.charba.client.dom.elements.Context2dItem;
import org.pepstock.charba.client.enums.AxisKind;
import org.pepstock.charba.client.enums.ScaleDataType;
import org.pepstock.charba.client.items.ChartAreaNode;
import org.pepstock.charba.client.items.ScaleItem;
import org.pepstock.charba.client.items.UndefinedValues;
import org.pepstock.charba.client.positioner.Point;

/**
 * Internal class created for each line annotation options configured from the plugin.<br>
 * It uses the line annotation option to calculate the area to be drawn.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class LineAnnotationElement extends AbstractAnnotationElement<LineAnnotation> {

	// linear equation of the line
	private final LineFunction lineFunction;

	private final LineLabelAnnotationElement linelabel;

	private final Point startingPoint = new Point();

	private final Point endingPoint = new Point();

	private boolean isEndValueMissing = false;

	/**
	 * Creates an annotation element by an annotation configuration.
	 * 
	 * @param chart chart instance
	 * @param configuration annotation configuration element
	 */
	LineAnnotationElement(IsChart chart, LineAnnotation configuration) {
		super(chart, configuration);
		// creates line label annotation element
		linelabel = new LineLabelAnnotationElement(this);
		// creates linear equation of the line
		lineFunction = new LineFunction(this);
	}

	/**
	 * Returns the starting point of the line.
	 * 
	 * @return the starting point of the line
	 */
	Point getStartingPoint() {
		return startingPoint;
	}

	/**
	 * Returns the ending point of the line.
	 * 
	 * @return the ending point of the line
	 */
	Point getEndingPoint() {
		return endingPoint;
	}

	/**
	 * Returns the linear equation which represents the line.
	 * 
	 * @return the linear equation which represents the line
	 */
	LineFunction getLineFunction() {
		return lineFunction;
	}

	/**
	 * Returns <code>true</code> if end value has been set.<br>
	 * This is used when the auto rotation of label has been performed.
	 * 
	 * @return <code>true</code> if end value has been set
	 */
	boolean isEndValueMissing() {
		return isEndValueMissing;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.myannotation.AbstractAnnotationElement#configureElement(org.pepstock.charba.client.items.ChartAreaNode, java.util.Map)
	 */
	@Override
	void configureElement(ChartAreaNode area, Map<String, ScaleItem> scalesMap) {
		// checks if scale nodes contains the configured scale id
		if (scalesMap.containsKey(getConfiguration().getScaleID().value())) {
			// gets the scale
			ScaleItem scale = scalesMap.get(getConfiguration().getScaleID().value());
			// configures teh line annotation element
			configureScale(area, scale);
			// configures linear equation for the line
			// must be run after the element configuration
			lineFunction.configure();
			// configures line label element
			linelabel.configure(scale);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.myannotation.AbstractAnnotationElement#isConsistent()
	 */
	@Override
	boolean isConsistent() {
		return startingPoint.isConsistent() && endingPoint.isConsistent();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.myannotation.AbstractAnnotationElement#drawElement(org.pepstock.charba.client.items.ChartAreaNode,
	 * org.pepstock.charba.client.dom.elements.Context2dItem)
	 */
	@Override
	void drawElement(ChartAreaNode area, Context2dItem ctx) {
		// sets the width and color of the line
		ctx.setLineWidth(getConfiguration().getBorderWidth());
		ctx.setStrokeColor(getConfiguration().getBorderColorAsString());
		// gets the line dash
		List<Integer> borderDash = getConfiguration().getBorderDash();
		// if border dash is set (not empty)
		if (!borderDash.isEmpty()) {
			// sets the line dash for line
			ctx.setLineDash(borderDash);
			// sets the line dash offset
			ctx.setLineDashOffset(getConfiguration().getBorderDashOffset());
		}
		// starts drawing the line
		ctx.beginPath();
		ctx.moveTo(startingPoint.getX(), startingPoint.getY());
		ctx.lineTo(endingPoint.getX(), endingPoint.getY());
		ctx.stroke();
		// draws the line label
		linelabel.draw(area, ctx);
	}

	/**
	 * Configures the line annotation element calculating starting and ending points of the line to draw.<br>
	 * It uses the scale in order to get the positions of the values passed by line annotation options.
	 * 
	 * @param area chart area instance
	 * @param scale scale instance to use to configure
	 */
	private void configureScale(ChartAreaNode area, ScaleItem scale) {
		// creates start and end references
		double start = UndefinedValues.DOUBLE;
		double end = UndefinedValues.DOUBLE;
		// checks the data type managed by the selected scale
		if (ScaleDataType.STRING.equals(scale.getType().getDataType())) {
			// --------------
			// CATEGORY scale
			// manages String
			// --------------
			// gets the start value configured for annotation
			final String startString = getConfiguration().getValueAsString();
			// checks if start value is consistent
			if (startString == null) {
				// does nothing and this annotation is not showed
				return;
			}
			// gets the end value configured for annotation
			// if not exists, uses the starting value as end one
			final String endString = getConfiguration().getEndValueAsString() != null ? getConfiguration().getEndValueAsString() : startString;
			// stores if has end value
			isEndValueMissing = endString.equals(startString);
			// gets the position in pixel on chart area for the start value
			start = getValuePositionFromString(startString, scale, Double.NaN);
			// gets the position in pixel on chart area for the end value
			end = getValuePositionFromString(endString, scale, start);
		} else if (ScaleDataType.DATE.equals(scale.getType().getDataType())) {
			// ------------------------
			// TIME or TIMESERIES scales
			// manage Date
			// ------------------------
			// gets the start value configured for annotation
			final Date startDate = getConfiguration().getValueAsDate();
			// checks if start value is consistent
			if (startDate == null) {
				// does nothing and this annotation is not showed
				return;
			}
			// gets the end value configured for annotation
			// if not exists, uses the starting value as end one
			final Date endDate = getConfiguration().getEndValueAsDate() != null ? getConfiguration().getEndValueAsDate() : startDate;
			// stores if has end value
			isEndValueMissing = endDate.equals(startDate);
			// gets the position in pixel on chart area for the start value
			start = getValuePositionFromDate(startDate, scale, Double.NaN);
			// gets the position in pixel on chart area for the end value
			end = getValuePositionFromDate(endDate, scale, start);
		} else if (ScaleDataType.NUMBER.equals(scale.getType().getDataType())) {
			// ----------------------------
			// LINEAR or LOGARITHMIC scales
			// manage Date
			// ----------------------------
			// gets the start value configured for annotation
			final double startDouble = getConfiguration().getValueAsDouble();
			// checks if start value is consistent
			if (Double.isNaN(startDouble)) {
				// does nothing and this annotation is not showed
				return;
			}
			// gets the end value configured for annotation
			// if not exists, uses the starting value as end one
			final double endDouble = !Double.isNaN(getConfiguration().getEndValueAsDouble()) ? getConfiguration().getEndValueAsDouble() : startDouble;
			// stores if has end value
			isEndValueMissing = endDouble == startDouble;
			// gets the position in pixel on chart area for the start value
			start = getValuePosition(startDouble, scale, Double.NaN);
			// gets the position in pixel on chart area for the end value
			end = getValuePosition(endDouble, scale, start);
		}
		// calculates the start and end
		// based on the axis kind of scale.
		if (AxisKind.Y.equals(scale.getAxis())) {
			// always horizontal annotation line
			// if scale is vertical
			// starting point
			startingPoint.setX(area.getLeft());
			startingPoint.setY(start);
			// end point
			endingPoint.setX(area.getRight());
			endingPoint.setY(end);
		} else if (AxisKind.X.equals(scale.getAxis())) {
			// always vertical annotation line
			// if scale is horizontal
			// starting point
			startingPoint.setX(start);
			startingPoint.setY(area.getTop());
			// end point
			endingPoint.setX(end);
			endingPoint.setY(area.getBottom());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.myannotation.AbstractAnnotationElement#isInside(org.pepstock.charba.client.dom.BaseNativeEvent)
	 */
	@Override
	boolean isInside(BaseNativeEvent event) {
		return lineFunction.intersects(event.getLayerX(), event.getLayerY(), getConfiguration().getBorderWidth()) || linelabel.isInside(event);
	}

	/**
	 * Defines the linear equation of the line.<br>
	 * The equation of any straight line, called a linear equation, can be written as:<br>
	 * <br>
	 * <b>y = mx + b</b><br>
	 * <br>
	 * where <b>m</b> is the slope of the line and <b>b</b> is the y-intercept.<br>
	 * The y-intercept of this line is the value of y at the point where the line crosses the y axis.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	static final class LineFunction {

		// annotation line element
		private final LineAnnotationElement annotationElement;
		// the slope of the line
		private double m = UndefinedValues.DOUBLE;
		// y-intercept
		private double b = UndefinedValues.DOUBLE;

		/**
		 * Creates the object storing the line annotation element which belongs to.
		 * 
		 * @param annotationElement line annotation element which wraps the function.
		 */
		private LineFunction(LineAnnotationElement annotationElement) {
			this.annotationElement = annotationElement;
		}

		/**
		 * Calculates the slope of the line and y-intercept using the line annotation element configuration.
		 */
		private void configure() {
			// gets the starting points of the line annotation element
			final Point lineStartingPoint = annotationElement.getStartingPoint();
			final Point lineEndingPoint = annotationElement.getEndingPoint();
			// Describe the line in slope-intercept form (y = mx + b).
			// Note that the axes are rotated 90° CCW, which causes the
			// x- and y-axes to be swapped.
			m = (lineEndingPoint.getX() - lineStartingPoint.getX()) / (lineEndingPoint.getY() - lineStartingPoint.getY());
			b = Double.isNaN(lineStartingPoint.getX()) ? 0 : lineStartingPoint.getX();
		}

		/**
		 * Returns the slope of the line of linear equation.
		 * 
		 * @return the slope of the line of linear equation
		 */
		double getM() {
			return m;
		}

		/**
		 * Returns the y-intercept of linear equation.
		 * 
		 * @return the y-intercept of linear equation
		 */
		double getB() {
			return b;
		}

		/**
		 * Returns a X value applying the linear equation.
		 * 
		 * @param y Y value to use to get the X value
		 * @return position related to Y value
		 */
		double getX(double y) {
			final Point lineStartingPoint = annotationElement.getStartingPoint();
			return m * (y - lineStartingPoint.getY()) + b;
		}

		/**
		 * Returns a T value applying the linear equation.
		 * 
		 * @param x X value to use to get the Y value
		 * @return position related to X value
		 */
		double getY(double x) {
			final Point lineStartingPoint = annotationElement.getStartingPoint();
			return ((x - b) / m) + lineStartingPoint.getY();
		}

		/**
		 * Returns <code>true</code> if the coordinates (x and y) are over the straight line.
		 * 
		 * @param x coordinate X instance
		 * @param y coordinate Y instance
		 * @param epsilon width of straight line
		 * @return <code>true</code> if the coordinates (x and y) are over the straight line
		 */
		private boolean intersects(double x, double y, double epsilon) {
			// checks arguments
			epsilon = Double.isNaN(epsilon) ? 0.001 : epsilon;
			// gets x and y value of equation using the passed coordinates.
			double dy = this.getY(x);
			double dx = this.getX(y);
			// checks if the coordinates are over the line
			return (!Double.isFinite(dy) || Math.abs(y - dy) < epsilon) && (!Double.isFinite(dx) || Math.abs(x - dx) < epsilon);
		}
	}

}
