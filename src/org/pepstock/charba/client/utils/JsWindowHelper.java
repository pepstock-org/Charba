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
package org.pepstock.charba.client.utils;

import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.resources.ResourcesType;

/**
 * This is a singleton wrapper for Java native object which is wrapping a CHARBA java script object implementation with some utilities to act on <code>window</code> java script
 * object.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class JsWindowHelper {
	// static instance for singleton
	private static final JsWindowHelper INSTANCE = new JsWindowHelper();

	private boolean enableResizeOnBeforePrint = false;

	/**
	 * To avoid any instantiation
	 */
	private JsWindowHelper() {
		// to be sure that CHART.JS java script object is injected
		// some methods are calling CHART.JS for this reason is mandatory
		// to include also chart.js
		// inject Chart.js and date library if not already loaded
		ResourcesType.getClientBundle().inject();
		// to be sure that CHARBA java script object is injected
		// invoking the JsHelper
		JsHelper.get();
	}

	/**
	 * Singleton object to get the helper instance
	 * 
	 * @return helper instance.
	 */
	public static JsWindowHelper get() {
		return INSTANCE;
	}

	/**
	 * CSS media queries allow changing styles when printing a page. The CSS applied from these media queries may cause charts to need to resize. However, the resize won't happen
	 * automatically. To support resizing charts when printing, one needs to hook the <code>onbeforeprint</code> event and manually trigger resizing of each chart.
	 * 
	 */
	public void enableResizeOnBeforePrint() {
		// checks if already set
		if (!enableResizeOnBeforePrint) {
			// if not, set the resizing function
			NativeJsWindowHelper.enableResizeOnBeforePrint();
			// sets the flag
			enableResizeOnBeforePrint = true;
		}
	}

}
