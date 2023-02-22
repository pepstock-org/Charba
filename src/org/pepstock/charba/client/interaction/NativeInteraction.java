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
package org.pepstock.charba.client.interaction;

import org.pepstock.charba.client.Chart;
import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.IsJSType;
import org.pepstock.charba.client.commons.NativeName;
import org.pepstock.charba.client.commons.NativeObject;

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * Enables the capability to create custom interaction directly in java script language.<br>
 * This could be helpful when for performance reason, you don't want to wraps all objects.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, name = NativeName.FUNCTION, namespace = JsPackage.GLOBAL)
public final class NativeInteraction implements IsJSType {

	// fixed arguments of the callback
	// chart - the chart we are returning items from
	// event - the event we are find things at
	// options - interaction options to use
	// useFinalPosition - use final element position (animation target)
	@JsOverlay
	private static final String CHART_ARGUMENT = "chart";
	@JsOverlay
	private static final String EVENT_ARGUMENT = "event";
	@JsOverlay
	private static final String OPTIONS_ARGUMENT = "options";
	@JsOverlay
	private static final String USE_FINAL_POSITION_ARGUMENT = "useFinalPosition";

	/**
	 * Creates a new <code>Function</code> object.<br>
	 * Calling the constructor directly can create functions dynamically but suffers from security and similar (but far less significant) performance issues to
	 * <code>eval</code>.<br>
	 * However, unlike <code>eval</code>, the <code>Function</code> constructor creates functions that execute in the global scope only.
	 * 
	 * @param chart the chart we are returning items from
	 * @param event the event we are find things at
	 * @param options options to use
	 * @param useFinalPosition use final element position (animation target)
	 * @param evalString java script code with is the body of new function.
	 */
	// Ignores SonarCloud issue, java:S1172 - Unused method parameters should be removed, because this is the way of JSINTEROP to invoke a JavaScript constructor.
	@SuppressWarnings("java:S1172")
	private NativeInteraction(String chart, String event, String options, String useFinalPosition, String evalString) {
		// do nothing
	}

	/**
	 * Method of function to be called to invoke a custom interactioner.
	 * 
	 * @param thisArg the chart is the "this" of this call
	 * @param chart the chart we are returning items from
	 * @param event the event we are find things at
	 * @param options options to use
	 * @param useFinalPosition use final element position (animation target)
	 * @return items that are found
	 */
	@JsMethod(name = "call")
	private native ArrayObject invoke(Object thisArg, Chart chart, NativeObject event, NativeObject options, boolean useFinalPosition);

	/**
	 * Creates a custom interaction in java script.
	 * 
	 * @param code the code of the custom interaction to execute
	 * @return a custom interaction in java script
	 */
	@JsOverlay
	public static NativeInteraction create(String code) {
		// checks if code is consistent
		Checker.checkIfValid(code, "Code argument");
		Checker.checkAndGetIfGreaterThan(code.trim().length(), 0, "Code argument length ");
		// creates and returns the callback
		return new NativeInteraction(CHART_ARGUMENT, EVENT_ARGUMENT, OPTIONS_ARGUMENT, USE_FINAL_POSITION_ARGUMENT, code);
	}

	/**
	 * Method of function to be called to invoke a custom interactioner.
	 * 
	 * @param chart the chart we are returning items from
	 * @param event the event we are find things at
	 * @param options options to use
	 * @param useFinalPosition use final element position (animation target)
	 * @return items that are found
	 */
	@JsOverlay
	public ArrayObject call(Chart chart, NativeObject event, NativeObject options, boolean useFinalPosition) {
		// checks arguments
		checkArguments(chart, event, options);
		// invokes call of function
		return invoke(options, chart, event, options, useFinalPosition);
	}

	/**
	 * Returns a string representing the source code of the function.
	 * 
	 * @return a string representing the source code of the function
	 */
	@JsMethod(name = "toString")
	public native String toSource();

	/**
	 * Checks the consistency of all arguments before invoking the function.
	 * 
	 * @param arguments arguments of the function to be check
	 */
	@JsOverlay
	private void checkArguments(Object... arguments) {
		// checks arguments
		for (Object arg : arguments) {
			Checker.assertCheck(arg != null, "Arguments of call method are not consistent");
		}
	}
}