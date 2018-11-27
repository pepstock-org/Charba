/**
    Copyright 2017 Andrea "Stock" Stocchero

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

	    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUString WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/
package org.pepstock.charba.client.jsinterop.commons;

/**
 * Constants with java script object name to use into {@link jsinterop.annotations.JsType} native objects.
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 */
public final class NativeName {
	
	/**
	 * Constant to bind java script object as object
	 */
	public static final String OBJECT = "Object";

	/**
	 * Constant to bind java script array
	 */
	public static final String ARRAY = "Array";

	/**
	 * Constant to bind java script object as JsHelper (internal of Charba)
	 */
	public static final String JSHELPER = "JsHelper";

	/**
	 * Constant to bind java script object as Chart (internal of CHART.JS)
	 */
	public static final String CHART = "Chart";

	/**
	 * Constant to bind java script object as window
	 */
	public static final String WINDOW = "window";

	/**
	 * To avoid any instantiation
	 */
	private NativeName() {
		// do nothing
	}
	
	

}
