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
import org.pepstock.charba.client.utils.Window;

/**
 * FIXME
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class LineAnnotationElement extends AbstractAnnotationElement<LineAnnotation> {

	private final LineFunction lineFunction;

	private final LineLabelAnnotationElement linelabel;

	private final Point startingPoint = new Point();

	private final Point endingPoint = new Point();

	/**
	 * Creates an annotation element by an annotation configuration.
	 * 
	 * @param chart chart instance
	 * @param configuration annotation configuration element
	 */
	LineAnnotationElement(IsChart chart, LineAnnotation annotation) {
		super(chart, annotation);
		// creates line function instance
		linelabel = new LineLabelAnnotationElement(this);
		// creates line function instance
		lineFunction = new LineFunction(this);
	}

	/**
	 * FIXME
	 * 
	 * @return the startingPoint
	 */
	Point getStartingPoint() {
		return startingPoint;
	}

	/**
	 * FIXME
	 * 
	 * @return the endingPoint
	 */
	Point getEndingPoint() {
		return endingPoint;
	}

	/**
	 * FIXME 
	 * 
	 * @return the lineFunction
	 */
	LineFunction getLineFunction() {
		return lineFunction;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.myannotation.AbstractAnnotationElement#configureElement(org.pepstock.charba.client.items.ChartAreaNode, java.util.Map)
	 */
	@Override
	void configureElement(ChartAreaNode area, Map<String, ScaleItem> scalesMap) {
		// checks if scale nodes contains the scale id for X
		if (scalesMap.containsKey(getConfiguration().getScaleID().value())) {
			ScaleItem scale = scalesMap.get(getConfiguration().getScaleID().value());
			configureScale(area, scale);
			// configures line function
			lineFunction.configure();
			// configures line label element
			linelabel.configure();
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
		ctx.setLineWidth(getConfiguration().getBorderWidth());
		ctx.setStrokeColor(getConfiguration().getBorderColorAsString());

		List<Integer> borderDash = getConfiguration().getBorderDash();
		if (!borderDash.isEmpty()) {
			ctx.setLineDash(borderDash);
			ctx.setLineDashOffset(getConfiguration().getBorderDashOffset());
		}

		// Draw
		ctx.beginPath();
		ctx.moveTo(startingPoint.getX(), startingPoint.getY());
		ctx.lineTo(endingPoint.getX(), endingPoint.getY());
		ctx.stroke();

		// ---------------
		// LABEL
		// ---------------
		linelabel.draw();
	}

	/**
	 * FIXME
	 * 
	 * @param area
	 * @param scale
	 * @param scaleLimit
	 * @param isXScale
	 */
	private void configureScale(ChartAreaNode area, ScaleItem scale) {
		double start = UndefinedValues.DOUBLE;
		double end = UndefinedValues.DOUBLE;
		if (ScaleDataType.STRING.equals(scale.getType().getDataType())) {
			final String startString = getConfiguration().getValueAsString();
			final String endString = getConfiguration().getEndValueAsString() != null ? getConfiguration().getEndValueAsString() : startString;
			start = getValuePositionFromString(startString, scale, Double.NaN);
			end = getValuePositionFromString(endString, scale, start);
		} else if (ScaleDataType.DATE.equals(scale.getType().getDataType())) {
			final Date startDate = getConfiguration().getValueAsDate();
			final Date endDate = getConfiguration().getEndValueAsDate() != null ? getConfiguration().getEndValueAsDate() : startDate;
			start = getValuePositionFromDate(startDate, scale, Double.NaN);
			end = getValuePositionFromDate(endDate, scale, start);
		} else if (ScaleDataType.NUMBER.equals(scale.getType().getDataType())) {
			final double startDouble = getConfiguration().getValueAsDouble();
			final double endDouble = !Double.isNaN(getConfiguration().getEndValueAsDouble()) ? getConfiguration().getEndValueAsDouble() : startDouble;
			start = getValuePosition(startDouble, scale, Double.NaN);
			end = getValuePosition(endDouble, scale, start);
			
			// FIXME log
			Window.getConsole().log("start", startDouble,"end",endDouble);
			Window.getConsole().log("start", start,"end",end);
		}
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
	 * FIXME
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	static final class LineFunction {

		private final LineAnnotationElement annotationElement;

		private double m = UndefinedValues.DOUBLE;

		private double b = UndefinedValues.DOUBLE;

		/**
		 * FIXME
		 * 
		 * @param element
		 */
		private LineFunction(LineAnnotationElement annotationElement) {
			this.annotationElement = annotationElement;
		}

		private void configure() {
			final Point lineStartingPoint = annotationElement.getStartingPoint();
			final Point lineEndingPoint = annotationElement.getEndingPoint();
			// Describe the line in slope-intercept form (y = mx + b).
			// Note that the axes are rotated 90° CCW, which causes the
			// x- and y-axes to be swapped.
			m = (lineEndingPoint.getX() - lineStartingPoint.getX()) / (lineEndingPoint.getY() - lineStartingPoint.getY());
			b = Double.isNaN(lineStartingPoint.getX()) ? 0 : lineStartingPoint.getX();
		}

		/**
		 * @return the m
		 */
		double getM() {
			return m;
		}

		/**
		 * @return the b
		 */
		double getB() {
			return b;
		}

		double getX(double y) {
			final Point lineStartingPoint = annotationElement.getStartingPoint();
			// Coordinates are relative to the origin of the canvas
			return m * (y - lineStartingPoint.getY()) + b;
		}

		double getY(double x) {
			final Point lineStartingPoint = annotationElement.getStartingPoint();
			return ((x - b) / m) + lineStartingPoint.getY();
		}

		private boolean intersects(double x, double y, double epsilon) {
			epsilon = Double.isNaN(epsilon) ? 0.001 : epsilon;
			double dy = this.getY(x);
			double dx = this.getX(y);
			return (!Double.isFinite(dy) || Math.abs(y - dy) < epsilon) && (!Double.isFinite(dx) || Math.abs(x - dx) < epsilon);
		}
	}

}
