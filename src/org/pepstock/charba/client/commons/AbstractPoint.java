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
package org.pepstock.charba.client.commons;

import org.pepstock.charba.client.items.Undefined;

/**
 * This object is wrapping the native java script object to map a point.
 * 
 * @author Andrea "Stock" Stocchero
 */
public abstract class AbstractPoint extends AbstractReadOnlyPoint {

	/**
	 * Creates an empty object.
	 */
	protected AbstractPoint() {
		this(Undefined.DOUBLE);
	}

	/**
	 * Creates an object with X coordinate of the point
	 * 
	 * @param x the X coordinate of the point
	 */
	protected AbstractPoint(double x) {
		this(x, Undefined.DOUBLE);
	}

	/**
	 * Creates an object with X and Y coordinates of the point
	 * 
	 * @param x the X coordinate of the point
	 * @param y the Y coordinate of the point
	 */
	protected AbstractPoint(double x, double y) {
		this(null);
		// checks if arguments are consistent
		if (Undefined.isNot(x)) {
			// stores the value
			setX(x);
		}
		if (Undefined.isNot(y)) {
			// stores the value
			setY(y);
		}
	}

	/**
	 * Creates the object with native object instance to be wrapped.
	 * 
	 * @param nativeObject native object instance to be wrapped.
	 */
	protected AbstractPoint(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Sets the X coordinate of the point.
	 * 
	 * @param x the X coordinate of the point.
	 */
	public final void setX(double x) {
		setValue(Property.X, x);
	}

	/**
	 * Sets the Y coordinate of the point.
	 * 
	 * @param y the Y coordinate of the point.
	 */
	public final void setY(double y) {
		setValue(Property.Y, y);
	}

}