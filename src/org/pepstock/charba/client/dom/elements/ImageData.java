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
package org.pepstock.charba.client.dom.elements;

import java.util.List;

import org.pepstock.charba.client.commons.ArrayInteger;
import org.pepstock.charba.client.commons.NativeName;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Represents the underlying pixel data of an area of a &lt;canvas&gt; element.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, name = NativeName.DOM_IMAGE_DATA, namespace = JsPackage.GLOBAL)
public final class ImageData {

	/**
	 * To avoid any instantiation
	 */
	private ImageData() {
		// do nothing
	}

	/**
	 * Returns the actual height, in pixels.
	 *
	 * @return the actual height, in pixels
	 */
	@JsProperty
	public native int getHeight();

	/**
	 * Returns the actual width, in pixels.
	 *
	 * @return the actual width, in pixels
	 */
	@JsProperty
	public native int getWidth();

	/**
	 * Returns the array which is representing a one-dimensional array containing the data in the RGBA order, with integer values between 0 and 255 (inclusive).
	 * 
	 * @return the array which is representing a one-dimensional array containing the data in the RGBA order, with integer values between 0 and 255 (inclusive)
	 */
	@JsProperty(name = "data")
	private native Uint8ClampedArray nativeGetData();

	/**
	 * Returns the array which is representing a one-dimensional array containing the data in the RGBA order, with integer values between 0 and 255 (inclusive).
	 * 
	 * @return the array which is representing a one-dimensional array containing the data in the RGBA order, with integer values between 0 and 255 (inclusive)
	 */
	@JsOverlay
	public ArrayInteger getData() {
		// checks if data are consistent, otherwise an empty array
		return nativeGetData() != null ? nativeGetData().toArray() : ArrayInteger.fromOrEmpty((List<Integer>) null);
	}

}