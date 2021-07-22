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

import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.JsHelper;

/**
 * Utility to cast elements created in the other DOM tree engines in CHARBA elements.
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
	 * Returns a {@link Canvas} object casting the argument.
	 * 
	 * @param object object to cast to a <b>canvas</b>
	 * @return a {@link Canvas} object
	 */
	public static Canvas toCanvas(Object object) {
		return JsHelper.get().cast(checkAndGetObject(object));
	}

	/**
	 * Returns a {@link Div} object casting the argument.
	 * 
	 * @param object object to cast to a <b>div</b>
	 * @return a {@link Div} object
	 */
	public static Div toDiv(Object object) {
		return JsHelper.get().cast(checkAndGetObject(object));
	}

	/**
	 * Returns a {@link Img} object casting the argument.
	 * 
	 * @param object object to cast to an image
	 * @return a {@link Img} object
	 */
	public static Img toImg(Object object) {
		return JsHelper.get().cast(checkAndGetObject(object));
	}

	/**
	 * Returns a {@link CanvasPatternItem} object casting the argument.
	 * 
	 * @param object object to cast to a canvas pattern
	 * @return a {@link CanvasPatternItem} object
	 */
	public static CanvasPatternItem toPattern(Object object) {
		return JsHelper.get().cast(checkAndGetObject(object));
	}

	/**
	 * Returns a {@link CanvasGradientItem} object casting the argument.
	 * 
	 * @param object object to cast to a canvas gradient
	 * @return a {@link CanvasGradientItem} object
	 */
	public static CanvasGradientItem toGradient(Object object) {
		return JsHelper.get().cast(checkAndGetObject(object));
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
		// returns the argument
		return Checker.checkAndGetIfValid(object, "Object to cast");
	}

}
