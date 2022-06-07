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
package org.pepstock.charba.client.plugins;

import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.IsJSType;
import org.pepstock.charba.client.commons.NativeName;

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * Enables the capability to create a plugin hook directly in java script language.<br>
 * This could be helpful when for performance reason, you don't want to wraps all objects.<br>
 * The hook will receive only 3 arguments, to use in the java script code, <code>{@value NativeHook#DEFAULT_CHART_ARGUMENT}</code>,
 * <code>{@value NativeHook#DEFAULT_ARGS_ARGUMENT}</code>, <code>{@value NativeHook#DEFAULT_OPTIONS_ARGUMENT}</code>.<br>
 * See <a href="https://www.chartjs.org/docs/latest/api/interfaces/Plugin.html">CHART.JS doc</a> about the arguments.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, name = NativeName.FUNCTION, namespace = JsPackage.GLOBAL)
public final class NativeHook implements IsJSType {

	// fixed first argument of the hook, is the "chart"
	@JsOverlay
	private static final String DEFAULT_CHART_ARGUMENT = "chart";
	// fixed second argument of the hook, is the "args"
	@JsOverlay
	private static final String DEFAULT_ARGS_ARGUMENT = "args";
	// fixed third argument of the hook, is the "options"
	@JsOverlay
	private static final String DEFAULT_OPTIONS_ARGUMENT = "options";

	/**
	 * Creates a new <code>Function</code> object.<br>
	 * Calling the constructor directly can create functions dynamically but suffers from security and similar (but far less significant) performance issues to
	 * <code>eval</code>.<br>
	 * However, unlike <code>eval</code>, the <code>Function</code> constructor creates functions that execute in the global scope only.
	 * 
	 * @param chart the chart instance name, always {@value NativeHook#DEFAULT_CHART_ARGUMENT}.
	 * @param args the arguments instance name, always {@value NativeHook#DEFAULT_ARGS_ARGUMENT}.
	 * @param options the options instance name, always {@value NativeHook#DEFAULT_OPTIONS_ARGUMENT}.
	 * @param evalString java script code with is the body of new function.
	 */
	// Ignores SonarCloud issue, java:S1172 - Unused method parameters should be removed, because this is the way of JSINTEROP to invoke a JavaScript constructor.
	@SuppressWarnings("java:S1172")
	private NativeHook(String chart, String args, String options, String evalString) {
		// do nothing
	}

	/**
	 * Creates a plugin hook in java script.<br>
	 * The hook will receive only 3 arguments, to use in the java script code, <code>{@value NativeHook#DEFAULT_CHART_ARGUMENT}</code>,
	 * <code>{@value NativeHook#DEFAULT_ARGS_ARGUMENT}</code>, <code>{@value NativeHook#DEFAULT_OPTIONS_ARGUMENT}</code>.
	 * 
	 * @param code the code of the hook to execute
	 * @return a plugin hook in java script
	 */
	@JsOverlay
	public static NativeHook create(String code) {
		// checks if code is consistent
		Checker.checkIfValid(code, "Code argument");
		Checker.checkAndGetIfGreaterThan(code.trim().length(), 0, "Code argument length");
		// creates and returns the callback
		return new NativeHook(DEFAULT_CHART_ARGUMENT, DEFAULT_ARGS_ARGUMENT, DEFAULT_OPTIONS_ARGUMENT, code);
	}

	/**
	 * Returns a string representing the source code of the function.
	 * 
	 * @return a string representing the source code of the function
	 */
	@JsMethod(name = "toString")
	public native String toSource();
}
