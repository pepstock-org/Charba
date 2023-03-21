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
package org.pepstock.charba.client.plugins;

import org.pepstock.charba.client.commons.NativeName;
import org.pepstock.charba.client.commons.NativeObject;

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * This is a Java native object which is wrapping a CHARBA java script object implementation with some utilities to manage CHART.JS plugins.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, name = NativeName.JS_PLUGIN_HELPER, namespace = JsPackage.GLOBAL)
final class NativeJsPluginHelper {

	/**
	 * To avoid any instantiation
	 */
	private NativeJsPluginHelper() {
		// do nothing
	}

	/**
	 * Register new plugin.
	 * 
	 * @param object plugin java script instance
	 */
	@JsMethod
	static native void register(NativeObject object);

	/**
	 * Unregister an existing plugin.
	 * 
	 * @param object plugin java script instance
	 */
	@JsMethod
	static native void unregister(NativeObject object);

	/**
	 * Returns all registered plugins as object.
	 * 
	 * @return all registered plugins as object
	 */
	@JsMethod
	static native NativeObject getAll();

}