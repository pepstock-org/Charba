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

import org.pepstock.charba.client.annotation.AnnotationPlugin;
import org.pepstock.charba.client.commons.NativeName;
import org.pepstock.charba.client.commons.NativeObject;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * Internal utility of {@link AnnotationPlugin} to interact with {@link AnnotationElement}.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, name = NativeName.JS_ANNOTATION_HELPER, namespace = JsPackage.GLOBAL)
final class NativeJsAnnotationHelper {

	/**
	 * To avoid any instantiation
	 */
	private NativeJsAnnotationHelper() {
		// do nothing
	}

	/**
	 * Returns the center point of the element.
	 * 
	 * @param element element which should provide the point
	 * @param useFinalPosition if the position must be calculated with final dimensions or also during the animation.
	 * @return the center point of the element.
	 */
	static native NativeObject getCenterPoint(NativeObject element, boolean useFinalPosition);

	/**
	 * Returns whether the coordinates, passed as arguments, are inside the element or not.
	 * 
	 * @param element element which should be invoked
	 * @param x coordinate x of the point to check.
	 * @param y coordinate y of the point to check.
	 * @param useFinalPosition if the position must be calculated with final dimensions or also during the animation.
	 * @return <code>true</code> if point is inside the element
	 */
	static native boolean inRange(NativeObject element, double x, double y, boolean useFinalPosition);

}
