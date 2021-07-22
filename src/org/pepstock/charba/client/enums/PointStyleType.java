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
package org.pepstock.charba.client.enums;

import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.dom.elements.Canvas;
import org.pepstock.charba.client.dom.elements.Img;

/**
 * Enumerates the type of the point style.
 * 
 * @author Andrea "Stock" Stocchero
 */
public enum PointStyleType implements Key
{
	/**
	 * The point style is stored as string, see {@link PointStyle}, the default.
	 */
	STRING("string"),
	/**
	 * The point style is stored as image, see {@link Img}.
	 */
	IMAGE("image"),
	/**
	 * The point style is stored as canvas, see {@link Canvas}.
	 */
	CANVAS("canvas");

	// name value of property
	private final String value;

	/**
	 * Creates with the property value to use in the native object.
	 * 
	 * @param value value of property name
	 */
	private PointStyleType(String value) {
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

	/**
	 * Returns the point style type in the native object container.
	 * 
	 * @param object the object instance on which to check
	 * @param key the string name of the property to test
	 * @return the point style type
	 */
	public static PointStyleType getType(NativeObjectContainer object, Key key) {
		// checks consistency of arguments
		if (object != null && Key.isValid(key)) {
			// checks if is an image
			if (JsHelper.get().isImage(object, key)) {
				return IMAGE;
			} else if (JsHelper.get().isCanvas(object, key)) {
				// checks if is a canvas
				return CANVAS;
			}
		}
		// if here, arguments are not consistent
		return STRING;
	}
}