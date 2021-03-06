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
package org.pepstock.charba.client.options;

import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.commons.PropertyHandler;
import org.pepstock.charba.client.defaults.IsDefaultPointStyleHandler;
import org.pepstock.charba.client.dom.elements.Img;
import org.pepstock.charba.client.enums.PointStyle;
import org.pepstock.charba.client.items.Undefined;

/**
 * Base object to to manage point style property in the the options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class PointStyleHandler extends PropertyHandler<IsDefaultPointStyleHandler> {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		POINT_STYLE("pointStyle");

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

	/**
	 * Creates a point style handler with the native object where POINTSTYLE property must be managed and the default value to use when the property does not exist.
	 * 
	 * @param parent model which contains the point style handler.
	 * @param defaultValues default value of point style to use when the properties do not exist
	 * @param nativeObject native object where point style handler properties must be managed
	 */
	PointStyleHandler(AbstractNode parent, IsDefaultPointStyleHandler defaultValues, NativeObject nativeObject) {
		super(parent, defaultValues, nativeObject);
	}

	/**
	 * Sets the style of the point.
	 * 
	 * @param pointStyle array of the style of the point.
	 */
	void setPointStyle(PointStyle pointStyle) {
		setValueAndAddToParent(Property.POINT_STYLE, pointStyle);
	}

	/**
	 * Returns the style of the point.
	 * 
	 * @return the style of the point or <code>null</code> if point style is set as image
	 */

	PointStyle getPointStyle() {
		// checks if image as point style has been used
		if (isType(Property.POINT_STYLE, ObjectType.STRING)) {
			return getValue(Property.POINT_STYLE, PointStyle.values(), getDefaultValues().getPointStyle());
		}
		// if here, means the point style as stored as images
		// then returns the default
		return getDefaultValues().getPointStyle();
	}

	/**
	 * Sets the style of the point as image.
	 * 
	 * @param pointStyle image element of the style of the point as image.
	 */
	void setPointStyle(Img pointStyle) {
		setValueAndAddToParent(Property.POINT_STYLE, pointStyle);
	}

	/**
	 * Returns <code>true</code> if the point style is set by an {@link Img}.
	 * 
	 * @return <code>true</code> if the point style is set by an {@link Img}
	 */
	boolean isPointStyleAsImage() {
		return isType(Property.POINT_STYLE, ObjectType.OBJECT);
	}

	/**
	 * Returns the style of the point as image.<br>
	 * If property is missing or not an image, returns <code>null</code>.
	 * 
	 * @return image of the style of the point as image.<br>
	 *         If property is missing or not a image, returns <code>null</code>.
	 */
	Img getPointStyleAsImage() {
		// checks if image as point style has been used
		if (isType(Property.POINT_STYLE, ObjectType.OBJECT)) {
			return getValue(Property.POINT_STYLE, Undefined.IMAGE_ELEMENT);
		}
		// if here, means the point style as stored as strings
		// returns undefined
		return Undefined.IMAGE_ELEMENT;
	}
}