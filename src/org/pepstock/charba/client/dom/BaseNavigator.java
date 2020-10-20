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
 * Represents the state and the identity of the user agent.<br>
 * It allows scripts to query it and to register themselves to carry on some activities.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, name = NativeName.DOM_NAVIGATOR, namespace = JsPackage.GLOBAL)
public final class BaseNavigator {

	/**
	 * To avoid any instantiation
	 */
	BaseNavigator() {
		// do nothing
	}

	/**
	 * Returns a string representing the preferred language of the user, usually the language of the browser UI.
	 * 
	 * @return a string representing the preferred language of the user, usually the language of the browser UI
	 */
	@JsProperty
	public final native String getLanguage();


	/**
	 * Returns the user agent string for the current browser.<br>
	 * Browser identification based on detecting the user agent string is unreliable and is not recommended, as the user agent string is user configurable.
	 * 
	 * @return the user agent string for the current browser
	 */
	@JsProperty
	public final native String getUserAgent();
}
