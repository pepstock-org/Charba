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

import org.pepstock.charba.client.commons.NativeName;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * Java native object which is wrapping a CHARBA java script object implementation with some utilities to act on java script
 * objects.
 * 
 * @author Andrea "Stock" Stocchero
 */
@JsType(isNative = true, name = NativeName.JSWINDOWHELPER, namespace = JsPackage.GLOBAL)
final class NativeJsWindowHelper {

	/**
	 * To avoid any instantiation
	 */
	NativeJsWindowHelper() {
		// do nothing
	}

	/**
	 * CSS media queries allow changing styles when printing a page. The CSS applied from these media queries may cause charts
	 * to need to resize. However, the resize won't happen automatically. To support resizing charts when printing, one needs to
	 * hook the <code>onbeforeprint</code> event and manually trigger resizing of each chart.
	 */
	static native void enableResizeOnBeforePrint();

}
