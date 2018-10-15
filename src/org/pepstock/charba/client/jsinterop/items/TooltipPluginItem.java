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
 * This object is just a proxy object, created from JavaScript side, to wrap an JavaScript array.<br>
 * Created and passed by CHART.JS.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name="Object")
public final class TooltipPluginItem extends NativeObject {

	@JsProperty(name = "easing")
	native double getNativeEasing();
	
	@JsProperty(name = "tooltip")
	native TooltipNode getNativeTooltip();

	@JsOverlay
	public final double getEasing() {
		return Checker.check(getNativeEasing(), UndefinedValues.DOUBLE);
	}

	@JsOverlay
	public final TooltipNode getTooltip() {
		return getNativeTooltip();
	}
}