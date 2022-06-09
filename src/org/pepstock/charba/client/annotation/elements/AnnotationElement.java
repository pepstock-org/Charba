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
		HEIGHT("height"),
		WIDTH("width"),
		POINT_X("pointX"),
		POINT_Y("pointY"),
		LABEL_X("labelX"),
		LABEL_Y("labelY"),
		LABEL_WIDTH("labelWidth"),
		LABEL_HEIGHT("labelHeight"),
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
	private AnnotationElement(NativeObject nativeObject) {
		super(nativeObject);
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
	public final OptionsElement getOptions() {
		return options;
	}

	/**
	 * Returns if element is active.
	 * 
	 * @return <code>true</code> if the element is active.
	 */
	public final boolean isActive() {
		return getValue(Property.ACTIVE, Undefined.BOOLEAN);
	}

	/**
	 * Returns <code>true</code> if skipped.
	 * 
	 * @return <code>true</code> if skipped.
	 */
	public final boolean isSkipped() {
		return getValue(Property.SKIP, Undefined.BOOLEAN);
	}

	/**
	 * Returns the X location of element in pixel.
	 * 
	 * @return the X location of element in pixel.
	 */
	@Override
	public final double getX() {
		return getValue(Property.X, Undefined.DOUBLE);
	}

	/**
	 * Returns the Y location of element in pixel.
	 * 
	 * @return the Y location of element in pixel.
	 */
	@Override
	public final double getY() {
		return getValue(Property.Y, Undefined.DOUBLE);
	}

	/**
	 * Returns the X location of element in pixel.
	 * 
	 * @return the X location of element in pixel.
	 */
	public final double getX2() {
		return getValue(Property.X2, Undefined.DOUBLE);
	}

	/**
	 * Returns the Y location of element in pixel.
	 * 
	 * @return the Y location of element in pixel.
	 */
	public final double getY2() {
		return getValue(Property.Y2, Undefined.DOUBLE);
	}

	/**
	 * Returns the width of element in pixel.
	 * 
	 * @return the width of element in pixel.
	 */
	public final double getWidth() {
		return getValue(Property.WIDTH, Undefined.DOUBLE);
	}

	/**
	 * Returns the height of element in pixel.
	 * 
	 * @return the height of element in pixel.
	 */
	public final double getHeight() {
		return getValue(Property.HEIGHT, Undefined.DOUBLE);
	}

	/**
	 * Returns the X location of label element in pixel.
	 * 
	 * @return the X location of label element in pixel.
	 */
	public final double getLabelX() {
		return getValue(Property.LABEL_X, Undefined.DOUBLE);
	}

	/**
	 * Returns the Y location of element in pixel.
	 * 
	 * @return the Y location of element in pixel.
	 */
	public final double getLabelY() {
		return getValue(Property.LABEL_Y, Undefined.DOUBLE);
	}

	/**
	 * Returns the width of label element in pixel.
	 * 
	 * @return the width of label element in pixel.
	 */
	public final double getLabelWidth() {
		return getValue(Property.LABEL_WIDTH, Undefined.DOUBLE);
	}

	/**
	 * Returns the height of label element in pixel.
	 * 
	 * @return the height of label element in pixel.
	 */
	public final double getLabelHeight() {
		return getValue(Property.LABEL_HEIGHT, Undefined.DOUBLE);
	}

	/**
	 * Returns the X location of element point in pixel.
	 * 
	 * @return the X location of element point in pixel.
	 */
	public final double getPointX() {
		return getValue(Property.POINT_X, Undefined.DOUBLE);
	}

	/**
	 * Returns the Y location of element point in pixel.
	 * 
	 * @return the Y location of element point in pixel.
	 */
	public final double getPointY() {
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