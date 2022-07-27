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
package org.pepstock.charba.client.annotation.elements;

import org.pepstock.charba.client.annotation.AnnotationEnvelop;
import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.AbstractReadOnlyPoint;
import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.Envelop;
import org.pepstock.charba.client.commons.IsPoint;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.items.Undefined;

/**
 * Maps all properties of the annotation element, the implementation of the annotation options in the plugin.<br>
 * It provides all dimensions of the element and sub elements.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class AnnotationElement extends AbstractNode implements IsPoint {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		// for element activation
		ACTIVE("active"),
		SKIP("skip"),
		// for element location
		X("x"),
		Y("y"),
		X2("x2"),
		Y2("y2"),
		CENTER_X("centerX"),
		CENTER_Y("centerY"),
		HEIGHT("height"),
		WIDTH("width"),
		POINT_X("pointX"),
		POINT_Y("pointY"),
		// label element
		LABEL("label"),
		// options
		OPTIONS("options");

		// name value of property
		private final String value;

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

	// element options instance
	private final OptionsElement options;
	// label instance
	private final AnnotationElement label;

	/**
	 * Creates the item using an envelop of the native java script object which contains all properties.
	 * 
	 * @param envelop envelop of the nativeObject native java script object which contains all properties.
	 */
	public AnnotationElement(AnnotationEnvelop<NativeObject> envelop) {
		this(Envelop.checkAndGetIfValid(envelop).getContent());
	}

	/**
	 * Creates the object with native object to map java script properties.
	 * 
	 * @param nativeObject native object to map java script properties
	 */
	AnnotationElement(NativeObject nativeObject) {
		this(null, null, nativeObject);
	}

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param parent parent node to use to add this element where changed
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param nativeObject native object to map java script properties
	 */
	AnnotationElement(AbstractNode parent, Key childKey, NativeObject nativeObject) {
		super(parent, childKey, nativeObject);
		// loads inner elements
		if (has(Property.LABEL)) {
			// sets the element label
			this.label = new AnnotationElement(this, Property.LABEL, getValue(Property.LABEL));
		} else {
			// if not there, is null
			this.label = null;
		}
		// loads element options only if already stored in the element
		// there could be in the callbacks invocation, options are not loaded,
		// instead on event there should be always
		if (has(Property.OPTIONS)) {
			// sets the element options
			this.options = new OptionsElement(this, Property.OPTIONS, getValue(Property.OPTIONS));
		} else {
			// if not there, is null
			this.options = null;
		}
	}

	/**
	 * Returns the inner label element of the element.
	 * 
	 * @return the inner label element of the element
	 */
	public AnnotationElement getLabel() {
		return label;
	}

	/**
	 * Sets the center point of the element.
	 * 
	 * @param point the center point of the element.
	 */
	public void setCenterPoint(IsPoint point) {
		// checks consistent
		if (point != null) {
			setCenterPoint(point.getX(), point.getY());
		}
	}

	/**
	 * Sets the center point of the element.
	 * 
	 * @param x the X value of center point of the element.
	 * @param y the Y value of center point of the element.
	 */
	public void setCenterPoint(double x, double y) {
		setValue(Property.CENTER_X, x);
		setValue(Property.CENTER_Y, y);
	}

	/**
	 * Returns the center point of the element.
	 * 
	 * @return the center point of the element.
	 */
	public IsPoint getCenterPoint() {
		return getCenterPoint(true);
	}

	/**
	 * Returns the center point of the element.
	 * 
	 * @param useFinalPosition if the position must be calculated with final dimensions or also during the animation.
	 * @return the center point of the element.
	 */
	public IsPoint getCenterPoint(boolean useFinalPosition) {
		return new InternalCenterPoint(NativeJsAnnotationHelper.getCenterPoint(getNativeObject(), useFinalPosition));
	}

	/**
	 * Returns whether the coordinates, passed as arguments, are inside the element or not.
	 * 
	 * @param point the point instance to check.
	 * @return <code>true</code> if point is inside the element
	 */
	public boolean inRange(IsPoint point) {
		return inRange(point, true);
	}

	/**
	 * Returns whether the coordinates, passed as arguments, are inside the element or not.
	 * 
	 * @param point the point instance to check.
	 * @param useFinalPosition if the position must be calculated with final dimensions or also during the animation.
	 * @return <code>true</code> if point is inside the element
	 */
	public boolean inRange(IsPoint point, boolean useFinalPosition) {
		return point != null && point.isConsistent() && inRange(point.getX(), point.getY(), useFinalPosition);
	}

	/**
	 * Returns whether the coordinates, passed as arguments, are inside the element or not.
	 * 
	 * @param x coordinate x of the point to check.
	 * @param y coordinate y of the point to check.
	 * @return <code>true</code> if point is inside the element
	 */
	public boolean inRange(double x, double y) {
		return inRange(x, y, true);
	}

	/**
	 * Returns whether the coordinates, passed as arguments, are inside the element or not.
	 * 
	 * @param x coordinate x of the point to check.
	 * @param y coordinate y of the point to check.
	 * @param useFinalPosition if the position must be calculated with final dimensions or also during the animation.
	 * @return <code>true</code> if point is inside the element
	 */
	public boolean inRange(double x, double y, boolean useFinalPosition) {
		return Undefined.isNot(x) && Undefined.isNot(y) && NativeJsAnnotationHelper.inRange(getNativeObject(), x, y, useFinalPosition);
	}

	/**
	 * Returns the list of properties of the element, using the final position.
	 * 
	 * @return an annotation element instance.
	 */
	public AnnotationElement getFinalPositionProps() {
		return new AnnotationElement(NativeJsAnnotationHelper.getProps(getNativeObject(), ArrayString.fromOrEmpty(Property.values()), true));
	}

	/**
	 * Returns the element options or <code>null</code> if options are not stored in the element.
	 *
	 * @return the element options or <code>null</code> if options are not stored in the element.
	 */
	public OptionsElement getOptions() {
		return options;
	}

	/**
	 * Returns if element is active.
	 * 
	 * @return <code>true</code> if the element is active.
	 */
	public boolean isActive() {
		return getValue(Property.ACTIVE, Undefined.BOOLEAN);
	}

	/**
	 * Returns <code>true</code> if skipped.
	 * 
	 * @return <code>true</code> if skipped.
	 */
	public boolean isSkipped() {
		return getValue(Property.SKIP, Undefined.BOOLEAN);
	}

	/**
	 * Returns the X location of element in pixel.
	 * 
	 * @return the X location of element in pixel.
	 */
	@Override
	public double getX() {
		return getValue(Property.X, Undefined.DOUBLE);
	}

	/**
	 * Sets the X location of element in pixel.
	 * 
	 * @param x the X location of element in pixel.
	 */
	public void setX(double x) {
		setValue(Property.X, x);
	}

	/**
	 * Returns the Y location of element in pixel.
	 * 
	 * @return the Y location of element in pixel.
	 */
	@Override
	public double getY() {
		return getValue(Property.Y, Undefined.DOUBLE);
	}

	/**
	 * Sets the Y location of element in pixel.
	 * 
	 * @param y the Y location of element in pixel.
	 */
	public void setY(double y) {
		setValue(Property.Y, y);
	}

	/**
	 * Returns the X2 location of element in pixel.
	 * 
	 * @return the X2 location of element in pixel.
	 */
	public double getX2() {
		return getValue(Property.X2, Undefined.DOUBLE);
	}

	/**
	 * Sets the X2 location of element in pixel.
	 * 
	 * @param x2 the X2 location of element in pixel.
	 */
	public void setX2(double x2) {
		setValue(Property.X2, x2);
	}

	/**
	 * Returns the Y location of element in pixel.
	 * 
	 * @return the Y location of element in pixel.
	 */
	public double getY2() {
		return getValue(Property.Y2, Undefined.DOUBLE);
	}

	/**
	 * Sets the Y2 location of element in pixel.
	 * 
	 * @param y2 the Y2 location of element in pixel.
	 */
	public void setY2(double y2) {
		setValue(Property.Y2, y2);
	}

	/**
	 * Returns the width of element in pixel.
	 * 
	 * @return the width of element in pixel.
	 */
	public double getWidth() {
		return getValue(Property.WIDTH, Undefined.DOUBLE);
	}

	/**
	 * Sets the width of element in pixel.
	 * 
	 * @param width the width of element in pixel.
	 */
	public void setWidth(double width) {
		setValue(Property.WIDTH, width);
	}

	/**
	 * Returns the height of element in pixel.
	 * 
	 * @return the height of element in pixel.
	 */
	public double getHeight() {
		return getValue(Property.HEIGHT, Undefined.DOUBLE);
	}

	/**
	 * Sets the height of element in pixel.
	 * 
	 * @param height the height of element in pixel.
	 */
	public void setHeight(double height) {
		setValue(Property.HEIGHT, height);
	}

	/**
	 * Returns the X location of element point in pixel.
	 * 
	 * @return the X location of element point in pixel.
	 */
	public double getPointX() {
		return getValue(Property.POINT_X, Undefined.DOUBLE);
	}

	/**
	 * Returns the Y location of element point in pixel.
	 * 
	 * @return the Y location of element point in pixel.
	 */
	public double getPointY() {
		return getValue(Property.POINT_Y, Undefined.DOUBLE);
	}

	/**
	 * Maps the center point of the element.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class InternalCenterPoint extends AbstractReadOnlyPoint {

		InternalCenterPoint(NativeObject nativeObject) {
			super(nativeObject);
		}

	}

}