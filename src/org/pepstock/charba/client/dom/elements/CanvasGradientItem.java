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

import org.pepstock.charba.client.commons.NativeName;
import org.pepstock.charba.client.dom.IsCastable;

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * Represents an opaque object describing a gradient.
 * 
 * @author Andrea "Stock" Stocchero
 */
@JsType(isNative = true, name = NativeName.OBJECT, namespace = JsPackage.GLOBAL)
public final class CanvasGradientItem implements IsCastable{

	/**
	 * To avoid any instantiation
	 */
	private CanvasGradientItem() {
		// do nothing
	}

	/**
	 * Adds a new stop, defined by an offset and a color, to the gradient.<br>
	 * If the offset is not between 0 and 1, inclusive, an INDEX_SIZE_ERR is raised.<br>
	 * If the color can't be parsed as a CSS color, a SYNTAX_ERR is raised.
	 * 
	 * @param offset 0 and 1 value to define the offset
	 * @param color the CSS color
	 */
	@JsMethod
	public native void addColorStop(double offset, String color);
}
