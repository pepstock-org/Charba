/**
    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.
*/
package org.pepstock.charba.client.utils;

import org.pepstock.charba.client.commons.NativeName;
import org.pepstock.charba.client.utils.WindowHelper.OnBeforePrintCallback;

import jsinterop.annotations.JsFunction;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * The window object represents an open window in a browser.
 * 
 * @author Andrea "Stock" Stocchero
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = NativeName.WINDOW)
public final class Window {

	// ---------------------------
	// -- JAVASCRIPT FUNCTIONS ---
	// ---------------------------

	/**
	 * Java script FUNCTION callback called for <code>setTimeout</code> and <code>setInterval</code> methods.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface AsyncCallback {

		/**
		 * Method of function to be called for <code>setTimeout</code> and <code>setInterval</code> methods.
		 */
		void call();
	}

	/**
	 * To avoid any instantiation
	 */
	private Window() {
		// do nothing
	}

	/**
	 * Returns an <code>undefined</code> instance.
	 * 
	 * @param <T> type of result object
	 * @return the <code>undefined</code> instance
	 */
	@JsProperty(name = "undefined")
	public static native <T> T undefined();

	/**
	 * Sets the <code>onbeforeprint</code> callback.
	 * 
	 * @param callback the callback to be set
	 */
	@JsProperty(name = "onbeforeprint")
	static native void onBeforePrint(OnBeforePrintCallback callback);

	/**
	 * Sets a timer which executes a function or specified piece of code once the timer expires.
	 * 
	 * @param function a function to be executed after the timer expires.
	 * @param delay the time, in milliseconds (thousandths of a second), the timer should wait before the specified function or code is executed.<br>
	 *            If this parameter is omitted, a value of 0 is used, meaning execute "immediately", or more accurately, the next event cycle.
	 * @return the returned timer identifier is a positive integer value which identifies the timer created by the call.<br>
	 *         This value can be passed to {@link Window#clearTimeout(int)} to cancel the timeout.
	 */
	static native int setTimeout(AsyncCallback function, int delay);

	/**
	 * Cancels a timeout previously established by calling {@link Window#setTimeout(AsyncCallback, int)} method.
	 * 
	 * @param timeoutID the identifier of the timeout you want to cancel.<br>
	 *            This ID was returned by the corresponding call to {@link Window#setTimeout(AsyncCallback, int)} method.
	 */
	static native void clearTimeout(int timeoutID);

	/**
	 * Sets a timer which executes repeatedly calls a function with a fixed time delay between each call.<br>
	 * It returns an interval ID which uniquely identifies the interval.
	 * 
	 * @param function a function to be executed repeatedly
	 * @param interval the time, in milliseconds (thousands of a second), the timer should delay in between executions of the specified function
	 * @return an interval ID which uniquely identifies the interval
	 */
	static native int setInterval(AsyncCallback function, int interval);

	/**
	 * cancels a timed, repeating action which was previously established by a call to {@link Window#setInterval(AsyncCallback, int)} method.
	 * 
	 * @param internalID the identifier of the repeated action you want to cancel.<br>
	 *            This ID was returned by the corresponding call to {@link Window#setInterval(AsyncCallback, int)} method.
	 */
	static native void clearInterval(int internalID);

	/**
	 * Returns the Console object
	 *
	 * @return The console object
	 */
	@JsProperty
	static native NativeConsole getConsole();

	/**
	 * Returns the device pixel ratio
	 *
	 * @return The device pixel ratio
	 */
	@JsProperty
	public static native double getDevicePixelRatio();

	/**
	 * The innerWidth property returns the inner width of a window's content area.
	 *
	 * @return The inner width of a window's content area.
	 */
	@JsProperty
	public static native int getInnerWidth();

	/**
	 * The innerHeight property returns the inner height of a window's content area.
	 *
	 * @return The inner height of a window's content area.
	 */
	@JsProperty
	public static native int getInnerHeight();

	/**
	 * The outerWidth property returns the outer width of a window, including all interface elements (like toolbars/scrollbars).
	 *
	 * @return The outer width of a window, including all interface elements (like toolbars/scrollbars).
	 */
	@JsProperty
	public static native int getOuterWidth();

	/**
	 * The outerHeight property returns the outer height of a window, including all interface elements (like toolbars/scrollbars).
	 *
	 * @return The outer height of a window, including all interface elements (like toolbars/scrollbars).
	 */
	@JsProperty
	public static native int getOuterHeight();

	/**
	 * Displays an alert box with a specified message and an OK button.
	 *
	 * @param message The text to display in the alert box
	 */
	public static native void alert(String message);

	/**
	 * Decode a base-64 encoded string
	 *
	 * @param encoded Required. The string which has been encoded by the btoa() method
	 * @return Decoded a base-64 encoded string
	 */
	public static native String atob(String encoded);

	/**
	 * Encodes a string in base-64.
	 *
	 * This method uses the "A-Z", "a-z", "0-9", "+", "/" and "=" characters to encode the string.
	 *
	 * @param str Required. The string to be encoded
	 * @return A String, representing the base-64 encoded string
	 */
	public static native String btoa(String str);

	/**
	 * CSS media queries allow changing styles when printing a page. The CSS applied from these media queries may cause charts to need to resize. However, the resize won't happen
	 * automatically. To support resizing charts when printing, one needs to hook the <code>onbeforeprint</code> event and manually trigger resizing of each chart.
	 * 
	 */
	@JsOverlay
	public static void enableResizeOnBeforePrint() {
		WindowHelper.get().enableResizeOnBeforePrint();
	}

}