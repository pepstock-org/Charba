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
package org.pepstock.charba.client.patterns;

import org.pepstock.charba.client.commons.NativeName;

import com.google.gwt.canvas.dom.client.CanvasPattern;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * Native object which import the PATTERNOMALY java script library.<br>
 * The <code>pattern</code> the entry point of PATTERNOMALY.
 * 
 * @author Andrea "Stock" Stocchero
 */
@JsType(isNative = true, name = NativeName.PATTERNOMALY, namespace = JsPackage.GLOBAL)
final class NativePatternomaly {

	/**
	 * To avoid any instantiation
	 */
	NativePatternomaly() {
		// do nothing
	}

	/**
	 * Returns a canvas pattern, using the shape, back ground color, pattern color and size as arguments.
	 * 
	 * @param shape shape to apply to canvas pattern
	 * @param backgroundColor background color of canvas pattern
	 * @param patternColor pattern color 
	 * @param size size of canvas pattern
	 * @return a canvas pattern
	 */
	static native CanvasPattern draw(String shape, String backgroundColor, String patternColor, int size);

}
