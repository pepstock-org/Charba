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
package org.pepstock.charba.client.annotation.elements;

import java.util.Date;

import org.pepstock.charba.client.annotation.AbstractAnnotation;
import org.pepstock.charba.client.annotation.ControlPoint;
import org.pepstock.charba.client.annotation.LineLabel;
import org.pepstock.charba.client.annotation.PolygonAnnotation;
import org.pepstock.charba.client.annotation.enums.LabelPosition;
import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.dom.elements.Canvas;
import org.pepstock.charba.client.dom.elements.Img;
import org.pepstock.charba.client.enums.PointStyle;
import org.pepstock.charba.client.enums.PointStyleType;
import org.pepstock.charba.client.items.Undefined;
import org.pepstock.charba.client.options.ScaleId;
import org.pepstock.charba.client.utils.Utilities;

/**
 * Maps the options of a {@link AnnotationElement} at runtime.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class OptionsElement extends BaseElement {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		ARROW_HEADS("arrowHeads"),
		CALLOUT("callout"),
		// options
		ADJUST_SCALE_RANGE("adjustScaleRange"),
		DISPLAY("display"),
		CONTROL_POINT("controlPoint"),
		CURVE("curve"),
		// styles
		RADIUS("radius"),
		POINT_STYLE("pointStyle"),
		POSITION("position"),
		ROTATION("rotation"),
		SIDES("sides"),
		// location definitions
		VALUE("value"),
		END_VALUE("endValue"),
		SCALE_ID("scaleID"),
		X_SCALE_ID("xScaleID"),
		X_MIN("xMin"),
		X_MAX("xMax"),
		X_VALUE("xValue"),
		Y_SCALE_ID("yScaleID"),
		Y_MIN("yMin"),
		Y_MAX("yMax"),
		Y_VALUE("yValue");

		// name value of property
		private String value;
		//

		/**
		 * Creates with the property value to use in the native object.
		 * 
		 * @param value value of property name
		 */
		private Property(String value) {
			this.value = value;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.Key#value()
		 */
		@Override
		public String value() {
			return value;
		}

	}

	// arrow heads options
	private final ArrowHeadsElement arrowHeads;
	// callout options
	private final CalloutElement callout;

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param parent parent node to use to add this element where changed
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param nativeObject native object to map java script properties
	 */
	OptionsElement(AbstractNode parent, Key childKey, NativeObject nativeObject) {
		super(parent, childKey, nativeObject);
		// loads inner elements
		this.arrowHeads = new ArrowHeadsElement(this, Property.ARROW_HEADS, getValue(Property.ARROW_HEADS));
		this.callout = new CalloutElement(this, Property.CALLOUT, getValue(Property.CALLOUT));
	}

	/**
	 * Returns the callout options of the element.
	 * 
	 * @return the callout options of the element
	 */
	public CalloutElement getCallout() {
		return callout;
	}

	/**
	 * Returns the arrow heads options of the element.
	 * 
	 * @return the arrow heads options of the element
	 */
	public ArrowHeadsElement getArrowHeads() {
		return arrowHeads;
	}

	/**
	 * Sets <code>true</code> whether the annotation should be displayed.
	 * 
	 * @param display <code>true</code> whether the annotation should be displayed
	 */
	public void setDisplay(boolean display) {
		setValueAndAddToParent(Property.DISPLAY, display);
	}

	/**
	 * Returns <code>true</code> whether the annotation should be displayed.
	 * 
	 * @return <code>true</code> whether the annotation should be displayed
	 */
	public boolean isDisplay() {
		return getValue(Property.DISPLAY, AbstractAnnotation.DEFAULT_DISPLAY);
	}

	/**
	 * Returns <code>true</code> whether the scale range should be adjusted if this annotation is out of range.
	 * 
	 * @return <code>true</code> whether the scale range should be adjusted if this annotation is out of range
	 */
	public boolean isAdjustScaleRange() {
		return getValue(Property.ADJUST_SCALE_RANGE, AbstractAnnotation.DEFAULT_ADJUST_SCALE_RANGE);
	}

	// ---------------
	// LOCATIONS
	// ---------------

	/**
	 * Returns the ID of the X scale to bind onto.
	 * 
	 * @return the ID of the X scale to bind onto
	 */
	public ScaleId getXScaleID() {
		return getValue(Property.X_SCALE_ID, (ScaleId) null);
	}

	/**
	 * Returns the right edge of the annotation.
	 * 
	 * @return the right edge of the annotation
	 */
	public String getXMaxAsString() {
		return getValueForMultipleKeyTypes(Property.X_MAX, Undefined.STRING);
	}

	/**
	 * Returns the right edge of the annotation.
	 * 
	 * @return the right edge of the annotation
	 */
	public double getXMaxAsDouble() {
		return getValueForMultipleKeyTypes(Property.X_MAX, Undefined.DOUBLE);
	}

	/**
	 * Returns the right edge of the annotation.
	 * 
	 * @return the right edge of the annotation
	 */
	public Date getXMaxAsDate() {
		return getValueForMultipleKeyTypes(Property.X_MAX, (Date) null);
	}

	/**
	 * Returns the left edge of the annotation, in units along the x axis.
	 * 
	 * @return the left edge of the annotation
	 */
	public String getXMinAsString() {
		return getValueForMultipleKeyTypes(Property.X_MIN, Undefined.STRING);
	}

	/**
	 * Returns the left edge of the annotation, in units along the x axis.
	 * 
	 * @return the left edge of the annotation
	 */
	public double getXMinAsDouble() {
		return getValueForMultipleKeyTypes(Property.X_MIN, Undefined.DOUBLE);
	}

	/**
	 * Returns the left edge of the annotation, in units along the x axis.
	 * 
	 * @return the left edge of the annotation
	 */
	public Date getXMinAsDate() {
		return getValueForMultipleKeyTypes(Property.X_MIN, (Date) null);
	}

	/**
	 * Returns the ID of the Y scale to bind onto.
	 * 
	 * @return the ID of the Y scale to bind onto
	 */
	public ScaleId getYScaleID() {
		return getValue(Property.Y_SCALE_ID, (ScaleId) null);
	}

	/**
	 * Returns the top edge of the annotation in units along the y axis.
	 * 
	 * @return the top edge of the annotation in units along the y axis
	 */
	public String getYMaxAsString() {
		return getValueForMultipleKeyTypes(Property.Y_MAX, Undefined.STRING);
	}

	/**
	 * Returns the top edge of the annotation in units along the y axis.
	 * 
	 * @return the top edge of the annotation in units along the y axis
	 */
	public double getYMaxAsDouble() {
		return getValueForMultipleKeyTypes(Property.Y_MAX, Undefined.DOUBLE);
	}

	/**
	 * Returns the top edge of the annotation in units along the y axis.
	 * 
	 * @return the top edge of the annotation in units along the y axis
	 */
	public Date getYMaxAsDate() {
		return getValueForMultipleKeyTypes(Property.Y_MAX, (Date) null);
	}

	/**
	 * Returns the bottom edge of the annotation.
	 * 
	 * @return the bottom edge of the annotation
	 */
	public String getYMinAsString() {
		return getValueForMultipleKeyTypes(Property.Y_MIN, Undefined.STRING);
	}

	/**
	 * Returns the bottom edge of the annotation.
	 * 
	 * @return the bottom edge of the annotation
	 */
	public double getYMinAsDouble() {
		return getValueForMultipleKeyTypes(Property.Y_MIN, Undefined.DOUBLE);
	}

	/**
	 * Returns the bottom edge of the annotation.
	 * 
	 * @return the bottom edge of the annotation
	 */
	public Date getYMinAsDate() {
		return getValueForMultipleKeyTypes(Property.Y_MIN, (Date) null);
	}

	/**
	 * Returns the data X value to draw the annotation at.
	 * 
	 * @return the data X value to draw the annotation at
	 */
	public String getXValueAsString() {
		return getValueForMultipleKeyTypes(Property.X_VALUE, Undefined.STRING);
	}

	/**
	 * Returns the data X value to draw the annotation at.
	 * 
	 * @return the data X value to draw the annotation at
	 */
	public double getXValueAsDouble() {
		return getValueForMultipleKeyTypes(Property.X_VALUE, Undefined.DOUBLE);
	}

	/**
	 * Returns the data X value to draw the annotation at.
	 * 
	 * @return the data X value to draw the annotation at
	 */
	public Date getXValueAsDate() {
		return getValueForMultipleKeyTypes(Property.X_VALUE, (Date) null);
	}

	/**
	 * Returns the data Y value to draw the annotation at.
	 * 
	 * @return the data Y value to draw the annotation at
	 */
	public String getYValueAsString() {
		return getValueForMultipleKeyTypes(Property.Y_VALUE, Undefined.STRING);
	}

	/**
	 * Returns the data Y value to draw the annotation at.
	 * 
	 * @return the data Y value to draw the annotation at
	 */
	public double getYValueAsDouble() {
		return getValueForMultipleKeyTypes(Property.Y_VALUE, Undefined.DOUBLE);
	}

	/**
	 * Returns the data Y value to draw the annotation at.
	 * 
	 * @return the data Y value to draw the annotation at
	 */
	public Date getYValueAsDate() {
		return getValueForMultipleKeyTypes(Property.Y_VALUE, (Date) null);
	}

	/**
	 * Returns the ID of the scale to bind onto.
	 * 
	 * @return the ID of the scale to bind onto
	 */
	public ScaleId getScaleID() {
		return getValue(Property.SCALE_ID, (ScaleId) null);
	}

	/**
	 * Returns the data value to draw the line at.
	 * 
	 * @return the data value to draw the line at
	 */
	public String getValueAsString() {
		return getValueForMultipleKeyTypes(Property.VALUE, Undefined.STRING);
	}

	/**
	 * Returns the data value to draw the line at.
	 * 
	 * @return the data value to draw the line at
	 */
	public double getValueAsDouble() {
		return getValueForMultipleKeyTypes(Property.VALUE, Undefined.DOUBLE);
	}

	/**
	 * Returns the data value to draw the line at.
	 * 
	 * @return the data value to draw the line at
	 */
	public Date getValueAsDate() {
		return getValueForMultipleKeyTypes(Property.VALUE, (Date) null);
	}

	/**
	 * Returns the data value at which the line draw should end.
	 * 
	 * @return the data value at which the line draw should end
	 */
	public String getEndValueAsString() {
		return getValueForMultipleKeyTypes(Property.END_VALUE, Undefined.STRING);
	}

	/**
	 * Returns the data value at which the line draw should end.
	 * 
	 * @return the data value at which the line draw should end
	 */
	public double getEndValueAsDouble() {
		return getValueForMultipleKeyTypes(Property.END_VALUE, Undefined.DOUBLE);
	}

	/**
	 * Returns the data value at which the line draw should end.
	 * 
	 * @return the data value at which the line draw should end
	 */
	public Date getEndValueAsDate() {
		return getValueForMultipleKeyTypes(Property.END_VALUE, (Date) null);
	}

	// -----------------
	// STYLES
	// -----------------

	/**
	 * Sets the radius of the annotation shape.<br>
	 * If set to 0, the annotation is not rendered.
	 * 
	 * @param radius array of the radius of the point shape.
	 */
	public void setRadius(double radius) {
		setValueAndAddToParent(Property.RADIUS, Checker.positiveOrZero(radius));
	}

	/**
	 * Returns the radius of the annotation.
	 * 
	 * @return the radius of the annotation.
	 */
	public double getRadius() {
		return getValue(Property.RADIUS, Undefined.DOUBLE);
	}

	/**
	 * Sets the style of the point.
	 * 
	 * @param pointStyle array of the style of the point.
	 */
	public void setPointStyle(PointStyle pointStyle) {
		setValueAndAddToParent(Property.POINT_STYLE, pointStyle);
	}

	/**
	 * Sets the style of the point as canvas.
	 * 
	 * @param pointStyle canvas element of the style of the point as canvas.
	 */
	public void setPointStyle(Canvas pointStyle) {
		setValueAndAddToParent(Property.POINT_STYLE, pointStyle);
	}

	/**
	 * Sets the style of the point as image.
	 * 
	 * @param pointStyle image element of the style of the point as image.
	 */
	public void setPointStyle(Img pointStyle) {
		setValueAndAddToParent(Property.POINT_STYLE, pointStyle);
	}

	/**
	 * Returns the type of point style.
	 * 
	 * @return the type of point style
	 */
	public PointStyleType getPointStyleType() {
		return PointStyleType.getType(this, Property.POINT_STYLE);
	}

	/**
	 * Returns the style of the point.
	 * 
	 * @return the style of the point or <code>null</code> if point style is set as image
	 */
	public PointStyle getPointStyle() {
		// checks if string as point style has been used
		if (PointStyleType.STRING.equals(getPointStyleType())) {
			return getValue(Property.POINT_STYLE, PointStyle.values(), PointStyle.CIRCLE);
		}
		// if here, means the point style as stored as image or canvas
		// then returns the default
		return PointStyle.CIRCLE;
	}

	/**
	 * Returns the style of the point as canvas.<br>
	 * If property is missing or not an canvas, returns <code>null</code>.
	 * 
	 * @return canvas of the style of the point as canvas.<br>
	 *         If property is missing or not a canvas, returns <code>null</code>.
	 */
	public Canvas getPointStyleAsCanvas() {
		// checks if canvas as point style has been used
		if (PointStyleType.CANVAS.equals(getPointStyleType())) {
			return getValue(Property.POINT_STYLE, Undefined.CANVAS_ELEMENT);
		}
		// if here, means the point style as stored as string or image
		// returns undefined
		return Undefined.CANVAS_ELEMENT;
	}

	/**
	 * Returns the style of the point as image.<br>
	 * If property is missing or not an image, returns <code>null</code>.
	 * 
	 * @return image of the style of the point as image.<br>
	 *         If property is missing or not a image, returns <code>null</code>.
	 */
	public Img getPointStyleAsImage() {
		// checks if image as point style has been used
		if (PointStyleType.IMAGE.equals(getPointStyleType())) {
			return getValue(Property.POINT_STYLE, Undefined.IMAGE_ELEMENT);
		}
		// if here, means the point style as stored as string or canvas
		// returns undefined
		return Undefined.IMAGE_ELEMENT;
	}

	/**
	 * Sets the sides of the polygon shape.
	 * 
	 * @param sides the sides of the polygon shape
	 */
	public void setSides(int sides) {
		setValueAndAddToParent(Property.SIDES, Checker.greaterThanOrDefault(sides, PolygonAnnotation.MINIMUM_SIDES, PolygonAnnotation.DEFAULT_SIDES));
	}

	/**
	 * Returns the sides of the polygon shape.
	 * 
	 * @return the sides of the polygon shape.
	 */
	public int getSides() {
		return getValue(Property.SIDES, PolygonAnnotation.DEFAULT_SIDES);
	}

	/**
	 * Sets <code>true</code> whether the rotation of label must calculates automatically.
	 * 
	 * @param autoRotation <code>true</code> whether the rotation of label must calculates automatically
	 */
	public void setAutoRotation(boolean autoRotation) {
		// checks is enabling
		if (autoRotation) {
			// stores value
			setValueAndAddToParent(Property.ROTATION, LineLabel.AUTO_ROTATION_AS_STRING);
		} else {
			// otherwise removes the key
			remove(Property.ROTATION);
		}
	}

	/**
	 * Returns <code>true</code> whether the rotation of label must calculates automatically.
	 * 
	 * @return <code>true</code> whether the rotation of label must calculates automatically
	 */
	public boolean isAutoRotation() {
		return isType(Property.ROTATION, ObjectType.STRING);
	}

	/**
	 * Sets the anchor position of label on line.
	 * 
	 * @param position the anchor position of label on line
	 */
	public void setPosition(LabelPosition position) {
		setValueAndAddToParent(Property.POSITION, position);
	}

	/**
	 * Returns the anchor position of label on line.
	 * 
	 * @return the anchor position of label on line
	 */
	public LabelPosition getPosition() {
		return getValue(Property.POSITION, LabelPosition.values(), LabelPosition.CENTER);
	}

	/**
	 * Sets the position of label on line by the percentage (value between 0 and 1) of the line dimension.
	 * 
	 * @param percentage the position of label on line by the percentage (value between 0 and 1) of the line dimension
	 */
	public void setPositionAsPercentage(double percentage) {
		setValueAndAddToParent(Property.POSITION, Utilities.getAsPercentage(percentage, 0.5D));
	}

	/**
	 * Returns the position of label on line by the percentage (value between 0 and 1) of the line dimension.
	 * 
	 * @return the position of label on line by the percentage (value between 0 and 1) of the line dimension
	 */
	public double getPositionAsPercentage() {
		return Utilities.getAsPercentage(getValue(Property.POSITION, Undefined.STRING), 0.5D);
	}

	/**
	 * Sets <code>true</code> if the line is set as a curve.
	 * 
	 * @param curve <code>true</code> if the line is set as a curve.
	 */
	public void setCurve(boolean curve) {
		setValue(Property.CURVE, curve);
	}

	/**
	 * Returns <code>true</code> if the line is set as a curve.
	 * 
	 * @return <code>true</code> if the line is set as a curve
	 */
	public boolean isCurve() {
		return getValue(Property.CURVE, Undefined.BOOLEAN);
	}

	/**
	 * Sets the control point to drawn the curve, calculated in pixels.
	 * 
	 * @param cp the control point to drawn the curve.
	 */
	public void setControlPoint(double cp) {
		setValue(Property.CONTROL_POINT, cp);
	}

	/**
	 * Sets the control point to drawn the curve, calculated in percentage.
	 * 
	 * @param cp the control point to drawn the curve.
	 */
	public void setControlPointAsPercentage(double cp) {
		setControlPoint(Utilities.getAsPercentage(cp, ControlPoint.MINIMUN_PERCENTAGE, ControlPoint.MAXIMUN_PERCENTAGE, 0D));
	}

	/**
	 * Sets the control point to drawn the curve, calculated in percentage format 'number%' which are representing the percentage of the distance between the start and end point
	 * from the center.
	 * 
	 * @param cp the control point to drawn the curve.
	 */
	public void setControlPoint(String cp) {
		// gets percentage
		double value = Utilities.getAsPercentage(cp, Undefined.DOUBLE);
		// checks if consistent
		if (Undefined.isNot(value)) {
			// stores value
			setValue(Property.CONTROL_POINT, cp);
		} else {
			// if here, the argument is not consistent
			// then removes the options
			remove(Property.CONTROL_POINT);
		}
	}

	/**
	 * Sets the control point to drawn the curve, calculated in pixels.<br>
	 * It can be set by a string in percentage format 'number%' which are representing the percentage of the distance between the start and end point from the center.
	 * 
	 * @param cp the control point to drawn the curve.
	 */
	public void setControlPoint(ControlPoint cp) {
		setValue(Property.CONTROL_POINT, cp);
	}

	/**
	 * Returns the control point to drawn the curve, calculated in pixels.<br>
	 * It can be set by a string in percentage format 'number%' which are representing the percentage of the distance between the start and end point from the center.
	 * 
	 * @return the control point to drawn the curve
	 */
	public ControlPointElement getControlPoint() {
		// checks the type of control point
		if (isType(Property.CONTROL_POINT, ObjectType.NUMBER)) {
			// gets value as number
			double value = getValue(Property.CONTROL_POINT, Undefined.DOUBLE);
			// creates and returns object
			return new ControlPointElement(this, Property.CONTROL_POINT, value);
		} else if (isType(Property.CONTROL_POINT, ObjectType.STRING)) {
			// gets value as number
			String value = getValue(Property.CONTROL_POINT, Undefined.STRING);
			// creates and returns object
			return new ControlPointElement(this, Property.CONTROL_POINT, value);
		} else if (isType(Property.CONTROL_POINT, ObjectType.OBJECT)) {
			// returns as object
			return new ControlPointElement(this, Property.CONTROL_POINT, getValue(Property.CONTROL_POINT));
		}
		// if here, control point is not set
		// then returns null
		return null;
	}
}