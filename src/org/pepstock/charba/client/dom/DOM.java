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
 * The DOM object provide a static reference to the document attached into the window.
 * 
 * @author Andrea "Stock" Stocchero
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = NativeName.WINDOW)
public final class DOM {

	/**
	 * To avoid any instantiation
	 */
	private DOM() {
		// do nothing
	}

	/**
	 * Returns a reference to the document that the window contains.
	 * 
	 * @return a reference to the document that the window contains
	 */
	@JsProperty
	public static native BaseHtmlDocument getDocument();

	/**
	 * Returns a {@link BaseLocation} object, which contains information about the URL of the document and provides methods for changing that URL and loading another URL.
	 * 
	 * @return  a {@link BaseLocation} object, which contains information about the URL of the document and provides methods for changing that URL and loading another URL
	 */
	@JsProperty
	public static native BaseLocation getLocation();

	/**
	 * Returns a {@link BaseNavigator} object, which represents the state and the identity of the user agent.
	 * 
	 * @return  a {@link BaseNavigator} object, which  represents the state and the identity of the user agent
	 */
	@JsProperty
	public static native BaseNavigator getNavigator();
}