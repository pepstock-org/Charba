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
package org.pepstock.charba.client.dom.events;

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Represents a list of contact points on a touch surface.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL)
public final class TouchList {

	/**
	 * To avoid any instantiation
	 */
	private TouchList() {
		// do nothing
	}

	/**
	 * Returns the number of touch objects in the touch list.
	 * 
	 * @return the number of touch objects in the touch list
	 */
	@JsProperty(name = "length")
	public native int length();

	/**
	 * Returns the touch object at the specified index in the list.
	 * 
	 * @param index the index to get the touch object
	 * @return the touch object at the specified index
	 */
	@JsMethod
	public native Touch item(int index);
}