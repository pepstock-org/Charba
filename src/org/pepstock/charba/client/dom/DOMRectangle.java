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

import org.pepstock.charba.client.commons.NativeName;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Describes the size and position of a rectangle of a {@link BaseElement}.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, name = NativeName.DOM_RECTANGLE, namespace = JsPackage.GLOBAL)
public final class DOMRectangle {

	/**
	 * To avoid any instantiation
	 */
	private DOMRectangle() {
		// do nothing
	}

	/**
	 * Returns the bottom coordinate value of the rectangle (has the same value as y + height, or y if height is negative).
	 * 
	 * @return the bottom coordinate value of the rectangle
	 */
	@JsProperty
	public native double getBottom();

	/**
	 * Returns the height of the rectangle.
	 * 
	 * @return the height of the rectangle
	 */
	@JsProperty
	public native double getHeight();

	/**
	 * Returns the left coordinate value of the rectangle (has the same value as x, or x + width if width is negative).
	 * 
	 * @return the left coordinate value of the rectangle
	 */
	@JsProperty
	public native double getLeft();

	/**
	 * Returns the right coordinate value of the rectangle (has the same value as x + width, or x if width is negative).
	 * 
	 * @return the right coordinate value of the rectangle
	 */
	@JsProperty
	public native double getRight();

	/**
	 * Returns the top coordinate value of the rectangle (has the same value as y, or y + height if height is negative).
	 * 
	 * @return the top coordinate value of the rectangle
	 */
	@JsProperty
	public native double getTop();

	/**
	 * Returns the width of the rectangle.
	 * 
	 * @return the width of the rectangle
	 */
	@JsProperty
	public native double getWidth();

	/**
	 * Returns the x coordinate of the origin (typically the top-left corner of the rectangle).
	 * 
	 * @return the x coordinate of the origin (typically the top-left corner of the rectangle)
	 */
	@JsProperty
	public native double getX();

	/**
	 * Returns the y coordinate of the origin (typically the top-left corner of the rectangle).
	 * 
	 * @return the y coordinate of the origin (typically the top-left corner of the rectangle).
	 */
	@JsProperty
	public native double getY();
}