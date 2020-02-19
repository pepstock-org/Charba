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
package org.pepstock.charba.client.dom.elements;

import jsinterop.base.Js;

/**
 * Utility to cast elements created into other DOM tree engines into a Charba one.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class CastHelper {

	/**
	 * To avoid any instantiation
	 */
	private CastHelper() {
		// do nothing
	}

	/**
	 * Returns a {@link Div} object casting the argument.
	 * 
	 * @param object object to cast to a <b>div</b>
	 * @return a {@link Div} object
	 */
	public static Div toDiv(Object object) {
		return Js.cast(checkAndGetObject(object));
	}

	/**
	 * Returns a {@link Img} object casting the argument.
	 * 
	 * @param object object to cast to an image
	 * @return a {@link Img} object
	 */
	public static Img toImg(Object object) {
		return Js.cast(checkAndGetObject(object));
	}

	/**
	 * Returns a {@link CanvasPatternItem} object casting the argument.
	 * 
	 * @param object object to cast to a canvas pattern
	 * @return a {@link CanvasPatternItem} object
	 */
	public static CanvasPatternItem toPattern(Object object) {
		return Js.cast(checkAndGetObject(object));
	}

	/**
	 * Returns a {@link CanvasGradientItem} object casting the argument.
	 * 
	 * @param object object to cast to a canvas gradient
	 * @return a {@link CanvasGradientItem} object
	 */
	public static CanvasGradientItem toGradient(Object object) {
		return Js.cast(checkAndGetObject(object));
	}

	/**
	 * Checks if the object passed as argument is consistent and returns it is not null.<br>
	 * If argument is not consistent, throws an {@link IllegalArgumentException}.
	 * 
	 * @param object object instance to check and return
	 * @return the object passed as argument
	 */
	private static Object checkAndGetObject(Object object) {
		// checks if object is consistent
		if (object == null) {
			// if here, exception
			throw new IllegalArgumentException("Object to cast is null");
		}
		// returns the argument
		return object;
	}

}
