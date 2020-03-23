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

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Represents the dimensions of a piece of text in the canvas, as created by the {@link Context2dItem#measureText(String)} method.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, name = NativeName.DOM_TEXT_METRICS, namespace = JsPackage.GLOBAL)
public final class TextMetricsItem {

	/**
	 * To avoid any instantiation
	 */
	private TextMetricsItem() {
		// do nothing
	}

	/**
	 * Return the value giving the calculated width of a segment of inline text in CSS pixels.<br>
	 * It takes into account the current font of the context..
	 * 
	 * @return the value giving the calculated width of a segment of inline text in CSS pixels
	 */
	@JsProperty
	public native double getWidth();

}
