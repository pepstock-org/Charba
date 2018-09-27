/**
    Copyright 2017 Andrea "Stock" Stocchero

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

	    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/
package org.pepstock.charba.client.jsinterop.items;

import org.pepstock.charba.client.jsinterop.commons.Checker;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * This item contains the new size of the chart after it has been resized.<br>
 * This object has been created ONLY when a resize event occurs.
 * 
 * @author Andrea "Stock" Stocchero
 * @see org.pepstock.charba.client.events.ChartResizeEvent
 * @see org.pepstock.charba.client.events.ChartResizeEventHandler
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name="Object")
public class SizeItem extends NativeObject {

	/**
	 * Returns the current Animation frame number.
	 * 
	 * @return the current Animation frame number. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	@JsProperty (name = "width")
	native int getNativeWidth();

	/**
	 * Returns the total number of animation frames.
	 * 
	 * @return the total number of animation frames. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	@JsProperty (name = "height")
	native int getNativeHeight();
	
	
	/**
	 * Returns the width of the chart item in pixel.
	 * 
	 * @return the width of the chart item in pixel. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	@JsOverlay
	public final int getWidth() {
		return Checker.check(getNativeWidth(), UndefinedValues.INTEGER);
	}

	/**
	 * Returns the height of the chart item in pixel.
	 * 
	 * @return the height of the chart item in pixel. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	@JsOverlay
	public final int getHeight() {
		return Checker.check(getNativeHeight(), UndefinedValues.INTEGER);
	}

}