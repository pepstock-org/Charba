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

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * JavaScript object which contains the margins sizes of an axis.<br>
 * This object reflects the object created by CHART.JS and is provided to Axis callbacks.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
public final class AxisMarginsItem extends MarginsItem {

	@JsProperty(name = "top")
	native void setNativeTop(int top);
	
	@JsProperty(name = "right")
	native void setNativeRight(int right);

	@JsProperty(name = "bottom")
	native void setNativeBottom(int bottom);

	@JsProperty(name = "left")
	native void setNativeLeft(int left);
	
	/**
	 * Sets the top margin in pixel
	 * 
	 * @param top the top margin in pixel
	 */
	@JsOverlay
	public final void setTop(int top) {
		setNativeTop(top);
	}

	/**
	 * Sets the bottom margin in pixel
	 * 
	 * @param bottom the bottom margin in pixel
	 */
	@JsOverlay
	public final void setBottom(int bottom) {
		setNativeBottom(bottom);
	}

	/**
	 * Sets the left margin in pixel
	 * 
	 * @param left the left margin in pixel
	 */
	@JsOverlay
	public final void setLeft(int left) {
		setNativeLeft(left);
	}

	/**
	 * Sets the right margin in pixel
	 * 
	 * @param right the right margin in pixel
	 */
	@JsOverlay
	public final void setRight(int right) {
		setNativeRight(right);
	}
}