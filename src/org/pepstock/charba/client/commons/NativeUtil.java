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
package org.pepstock.charba.client.commons;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * Reference to static methods of a java script object, used to manage {@link NativeObject}.<br>
 * It wraps some native methods.
 * 
 * @author Andrea "Stock" Stocchero
 */
@JsType(isNative = true, name = NativeName.OBJECT, namespace = JsPackage.GLOBAL)
final class NativeUtil {

	/**
	 * To avoid any instantiation
	 */
	private NativeUtil() {
		// do nothing
	}

	/**
	 * Defines a new property directly on an object, or modifies an existing property on an object.
	 * 
	 * @param source the object on which to define the property.
	 * @param key the name of the property to be defined or modified.
	 * @param descriptor the descriptor for the property being defined or modified.
	 * @param <T> type of descriptor set value
	 */
	static native <T extends NativeAbstractDescriptor> void defineProperty(NativeObject source, String key, T descriptor);

	/**
	 * Returns an array of a given object's own property names, in the same order as we get with a normal loop.
	 * 
	 * @param source the object of which the enumerable's own properties are to be returned.
	 * @return an array of strings that represent all the enumerable properties of the given object.
	 */
	static native ArrayString keys(NativeObject source);

}