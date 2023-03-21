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
package org.pepstock.charba.client.controllers;

import org.pepstock.charba.client.commons.NativeName;

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * Every javaScript function is actually a <code>Function</code> object and this class can map it.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, name = NativeName.FUNCTION, namespace = JsPackage.GLOBAL)
final class NativeFunction {

	/**
	 * Creates a new <code>Function</code> object.<br>
	 * Calling the constructor directly can create functions dynamically but suffers from security and similar (but far less significant) performance issues to
	 * <code>eval</code>.<br>
	 * However, unlike <code>eval</code>, the <code>Function</code> constructor creates functions that execute in the global scope only.
	 * 
	 * @param evalString javascript code with is the body of new function.
	 */
	// Ignores SonarCloud issue, java:S1172 - Unused method parameters should be removed, because this is the way of JSINTEROP to invoke a JavaScript constructor.
	@SuppressWarnings("java:S1172")
	NativeFunction(String evalString) {
		// do nothing
	}

	/**
	 * Calls a function and sets its <code>this</code> to the provided value.<br>
	 * Arguments can be passed as they are but not implemented here.
	 */
	native void call();

	/**
	 * Returns a string representing the source code of the function.
	 * 
	 * @return a string representing the source code of the function
	 */
	@JsMethod(name = "toString")
	native String toSource();
}