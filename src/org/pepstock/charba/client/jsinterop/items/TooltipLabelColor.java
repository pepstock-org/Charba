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

import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.jsinterop.Defaults;
import org.pepstock.charba.client.jsinterop.commons.Checker;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * This object contains the color info when a label into tooltip.<br>
 * It must be used into label tooltip callback.
 * 
 * @author Andrea "Stock" Stocchero
 * @see org.pepstock.charba.client.callbacks.TooltipLabelCallback
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name="Object")
public final class TooltipLabelColor extends NativeObject {
	
	@JsProperty(name = "backgroundColor")
	native String getNativeBackgroundColor();

	@JsProperty(name = "borderColor")
	native String getNativeBorderColor();

	/**
	 * Returns the background color of the label.
	 * 
	 * @return the background color of the label.
	 */
	@JsOverlay
	public String getBackgroundColorAsString() {
		return Checker.check(getNativeBackgroundColor(), Defaults.getGlobal().getTooltips().getBackgroundColorAsString());
	}

	/**
	 * Returns the background color of the label.
	 * 
	 * @return the background color of the label.
	 */
	@JsOverlay
	public IsColor getBackgroundColor() {
		return ColorBuilder.parse(getBackgroundColorAsString());
	}

	/**
	 * Returns the border color of the label.
	 * 
	 * @return the border color of the label.
	 */
	@JsOverlay
	public String getBorderColorAsString() {
		return Checker.check(getNativeBorderColor(),  Defaults.getGlobal().getTooltips().getBorderColorAsString());
	}
	
	/**
	 * Returns the border color of the label.
	 * 
	 * @return the border color of the label.
	 */
	@JsOverlay
	public IsColor getBorderColor() {
		return ColorBuilder.parse(getBorderColorAsString());
	}

}