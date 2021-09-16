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

import org.pepstock.charba.client.dom.BaseHtmlElement;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * A java script property setter and getter of {@link BaseHtmlElement}.
 * 
 * @author Andrea "Stock" Stocchero
 */
@JsType(isNative = true, name = NativeName.JS_OBJECT_ELEMENT_HELPER, namespace = JsPackage.GLOBAL)
final class NativeJsObjectElement {

	/**
	 * To avoid any instantiation
	 */
	private NativeJsObjectElement() {
		// do nothing
	}

	/**
	 * Allows you to get a property on an object.
	 * 
	 * @param target the target object on which to get the property
	 * @param key the name of the property to get
	 * @return the value of the property
	 */
	static native BaseHtmlElement get(NativeObject target, String key);

	/**
	 * Allows you to set a property on an object.
	 * 
	 * @param target the target object on which to set the property
	 * @param key the name of the property to set
	 * @param value the value to set
	 */
	static native void set(NativeObject target, String key, BaseHtmlElement value);

}
