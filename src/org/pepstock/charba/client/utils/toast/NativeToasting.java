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
package org.pepstock.charba.client.utils.toast;

import org.pepstock.charba.client.commons.CallbackProxy.Proxy;
import org.pepstock.charba.client.commons.NativeName;
import org.pepstock.charba.client.commons.NativeObject;

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Wraps toast JavaScript library for displaying toast notifications with progress bars on the page.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, name = NativeName.JS_TOAST, namespace = JsPackage.GLOBAL)
final class NativeToasting {

	/**
	 * To avoid any instantiation
	 */
	private NativeToasting() {
		// nothing
	}

	/**
	 * Returns the read-only defaults of toast.
	 * 
	 * @return the read-only defaults of toast
	 */
	@JsProperty
	static native NativeObject getDefaults();

	/**
	 * Returns the defaults of toast.
	 * 
	 * @return the defaults of toast
	 */
	@JsProperty
	static native NativeObject getOverrides();

	/**
	 * Sets the internal close handler.
	 * 
	 * @param proxy the internal close handler
	 */
	@JsProperty(name = "onClose")
	static native void setInternalCloseHandler(Proxy proxy);

	/**
	 * Creates and shows a toast configured by the passed options.
	 * 
	 * @param id unique id to use as element id.
	 * @param options configuration of the toast to show
	 * @return the toast item created and showed
	 */
	@JsMethod
	static native NativeObject create(int id, NativeObject options);

}
