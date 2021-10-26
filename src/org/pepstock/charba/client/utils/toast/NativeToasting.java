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

import org.pepstock.charba.client.commons.ArrayString;
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
	 * Returns the amount of current and shown toasts.
	 * 
	 * @return the amount of current and shown toasts
	 */
	@JsProperty
	static native int getCurrentOpenItems();

	/**
	 * Creates and shows a toast configured by the passed options.
	 * 
	 * @param id unique id to use as element id.
	 * @param title title of the toast
	 * @param label label of the toast
	 * @param options configuration of the toast to show
	 * @param dateTime date time object of the toast item (when queued or re-show)
	 * @return the toast item created and showed
	 */
	@JsMethod
	static native NativeObject create(int id, String title, ArrayString label, NativeObject options, NativeObject dateTime);

	/**
	 * Clones the argument.
	 * 
	 * @param source native object to be cloned
	 * @return clone of the argument
	 */
	@JsMethod
	static native NativeObject clone(NativeObject source);

	/**
	 * Closes the toast item.
	 * 
	 * @param item native object toast item to be closed
	 */
	@JsMethod
	static native void close(NativeObject item);

}
