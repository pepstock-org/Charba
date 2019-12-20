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
package org.pepstock.charba.client.zoom;

import java.util.Date;

import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.items.UndefinedValues;

/**
 * {@link ZoomPlugin#ID} plugin default options for RANGE (min and max) elements.<br>
 * It contains all default values for RANGE.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class DefaultsRange extends NativeObjectContainer {

	/**
	 * Creates the object with an empty native object instance.
	 */
	DefaultsRange() {
		super();
	}

	/**
	 * Creates the object with native object instance to be wrapped.
	 * 
	 * @param nativeObject native object instance to be wrapped
	 */
	DefaultsRange(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns the X value of range as string.
	 * 
	 * @return the X value of range as string
	 */
	String getX() {
		return getValue(Range.Property.X, UndefinedValues.STRING);
	}

	/**
	 * Returns the X value of range as double.
	 * 
	 * @return the X value of range as double
	 */
	double getXAsDouble() {
		return getValue(Range.Property.X, UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the X value of range as date.
	 * 
	 * @return the X value of range as date
	 */
	Date getXAsDate() {
		return getValue(Range.Property.X, (Date) null);
	}

	/**
	 * Returns the Y value of range as double.
	 * 
	 * @return the Y value of range as double
	 */
	double getY() {
		return getValue(Range.Property.Y, UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the Y value of range as string.
	 * 
	 * @return the Y value of range as string
	 */
	String getYAsString() {
		return getValue(Range.Property.Y, UndefinedValues.STRING);
	}

	/**
	 * Returns the Y value of range as date.
	 * 
	 * @return the Y value of range as date
	 */
	Date getYAsDate() {
		return getValue(Range.Property.Y, (Date) null);
	}
}