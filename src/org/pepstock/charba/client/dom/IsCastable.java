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
package org.pepstock.charba.client.dom;

import org.pepstock.charba.client.commons.IsJSType;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.NativeName;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * Interface which is implemented to DOM elements or items which can be cast to other objects.<br>
 * This is the interface in order to enable the link from other DOM tree managers to Charba.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, name = NativeName.OBJECT, namespace = JsPackage.GLOBAL)
public interface IsCastable extends IsJSType {

	/**
	 * Performs checked cast to lefthand-side type.
	 * 
	 * @param <T> type of result casting
	 * @return the same object cast to the type
	 */
	@JsOverlay
	default <T> T as() {
		return JsHelper.get().cast(this);
	}

}