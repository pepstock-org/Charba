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
package org.pepstock.charba.client.positioner;

import org.pepstock.charba.client.commons.NativeName;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * This object is wrapping the native java script object provided by tooltip positioner to know the position of the event in
 * canvas coordinates.
 * 
 * @author Andrea "Stock" Stocchero
 */
@JsType(isNative = true, name = NativeName.OBJECT, namespace = JsPackage.GLOBAL)
public final class Point {

	/**
	 * Creates an empty object
	 */
	public Point() {
		// must be empty
	}

	/**
	 * Sets the X coordinate of the point.
	 * 
	 * @param x the X coordinate of the point.
	 */
	@JsProperty(name = "x")
	public native void setX(int x);

	/**
	 * Returns the X coordinate of the point.
	 * 
	 * @return the X coordinate of the point.
	 */
	@JsProperty(name = "x")
	public native int getX();

	/**
	 * Sets the Y coordinate of the point.
	 * 
	 * @param y the Y coordinate of the point.
	 */
	@JsProperty(name = "y")
	public native void setY(int y);

	/**
	 * Returns the Y coordinate of the point.
	 * 
	 * @return the Y coordinate of the point.
	 */
	@JsProperty(name = "y")
	public native int getY();

}