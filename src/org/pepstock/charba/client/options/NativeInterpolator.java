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
package org.pepstock.charba.client.options;

import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.IsJSType;
import org.pepstock.charba.client.commons.NativeName;

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * Enables the capability to set a custom animation interpolator.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, name = NativeName.FUNCTION, namespace = JsPackage.GLOBAL)
public final class NativeInterpolator implements IsJSType {

	// fixed arguments of the callback
	// from - the initial value of element property
	// to - the target value of element property
	// factor - a value between 0 and 1 (inclusive) with the factor of animation
	@JsOverlay
	private static final String FROM_ARGUMENT = "from";
	@JsOverlay
	private static final String TO_ARGUMENT = "to";
	@JsOverlay
	private static final String FACTOR_ARGUMENT = "factor";

	/**
	 * Creates a new <code>Function</code> object.<br>
	 * Calling the constructor directly can create functions dynamically but suffers from security and similar (but far less significant) performance issues to
	 * <code>eval</code>.<br>
	 * However, unlike <code>eval</code>, the <code>Function</code> constructor creates functions that execute in the global scope only.
	 * 
	 * @param from the initial value of element property
	 * @param to the target value of element property
	 * @param factor a value between 0 and 1 (inclusive) with the factor of animation
	 * @param evalString java script code with is the body of new function.
	 */
	// Ignores SonarCloud issue, java:S1172 - Unused method parameters should be removed, because this is the way of JSINTEROP to invoke a JavaScript constructor.
	@SuppressWarnings("java:S1172")
	private NativeInterpolator(String from, String to, String factor, String evalString) {
		// do nothing
	}

	/**
	 * Creates a callback in java script.<br>
	 * All options in the configuration which can be set like scriptable, can be set with a native callback.<br>
	 * The callback can receive only 3 arguments:<br>
	 * <ul>
	 * <li><b>from</b>: the initial value of element property
	 * <li><b>to</b>:the target value of element property
	 * <li><b>factor</b>: a value between 0 and 1 (inclusive) with the factor of animation
	 * </ul>
	 * 
	 * @param code the code of the callback to execute
	 * @return a callback in java script
	 */
	@JsOverlay
	public static NativeInterpolator create(String code) {
		// checks if code is consistent
		Checker.checkIfValid(code, "Code argument");
		Checker.checkAndGetIfGreaterThan(code.trim().length(), 0, "Code argument length ");
		// creates and returns the callback
		return new NativeInterpolator(FROM_ARGUMENT, TO_ARGUMENT, FACTOR_ARGUMENT, code);
	}

	/**
	 * Returns a string representing the source code of the function.
	 * 
	 * @return a string representing the source code of the function
	 */
	@JsMethod(name = "toString")
	public native String toSource();
}