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
package org.pepstock.charba.client.dom;

import org.pepstock.charba.client.commons.NativeName;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Represents one of a DOM element's attributes as an object.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, name = NativeName.DOM_ELEMENT_ATTR, namespace = JsPackage.GLOBAL)
public final class BaseAttribute extends BaseNode {

	/**
	 * Returns the attribute's name.
	 * 
	 * @return the attribute's name
	 */
	@JsProperty
	public native String getName();

	/**
	 * Returns the attribute's value.
	 * 
	 * @return the attribute's value
	 */
	@JsProperty
	public native String getValue();

}
