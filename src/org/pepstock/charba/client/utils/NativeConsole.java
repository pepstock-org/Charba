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

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * This object is the wrapper to console java script object.<br>
 * The console object provides access to the browser's debugging console
 * 
 * @author Andrea "Stock" Stocchero
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = NativeName.CONSOLE)
interface NativeConsole {

	/**
	 * Outputs a message to the Web Console.
	 *
	 * @param objects a list of JavaScript objects to output.<br>
	 *            The string representations of each of these objects are appended together in the order listed and output.
	 */
	void log(Object... objects);

	/**
	 * Displays an interactive list of the properties of the specified JavaScript object.<br>
	 * The output is presented as a hierarchical listing with disclosure triangles that let you see the contents of child objects.<br>
	 * In other words, it is the way to see all the properties of a specified JavaScript object in console by which the developer can easily get the properties of the object.
	 *
	 * @param object a JavaScript object whose properties should be output.
	 */
	void dir(Object object);

	/**
	 * Displays an interactive tree of the descendant elements of the specified XML/HTML element.<br>
	 * If it is not possible to display as an element the JavaScript Object view is shown instead.<br>
	 * The output is presented as a hierarchical listing of expandable nodes that let you see the contents of child nodes.
	 *
	 * @param object a JavaScript object whose properties should be output.
	 */
	void dirxml(Object object);
}