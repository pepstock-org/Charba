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
import org.pepstock.charba.client.jsinterop.commons.NativeName;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Base object which maps the CHART.JS chart items which represents a box.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = NativeName.OBJECT)
public abstract class BaseBoxItem extends NativeObject {

	@JsProperty(name = "top")
	native int getNativeTop();
	
	@JsProperty(name = "right")
	native int getNativeRight();

	@JsProperty(name = "bottom")
	native int getNativeBottom();

	@JsProperty(name = "left")
	native int getNativeLeft();
	
	
	/**
	 * Returns the top of chart area.
	 * 
	 * @return the top of chart area.
	 */
	@JsOverlay
	public final int getTop() {
		return Checker.check(getNativeTop(), UndefinedValues.INTEGER);
	}

	/**
	 * Returns the right of chart area.
	 * 
	 * @return the right of chart area.
	 */
	@JsOverlay
	public final int getRight() {
		return Checker.check(getNativeRight(), UndefinedValues.INTEGER);
	}

	/**
	 * Returns the bottom of chart area.
	 * 
	 * @return the bottom of chart area.
	 */
	@JsOverlay
	public final int getBottom() {
		return Checker.check(getNativeBottom(), UndefinedValues.INTEGER);
	}

	/**
	 * Returns the left of chart area.
	 * 
	 * @return the left of chart area.
	 */
	@JsOverlay
	public final int getLeft() {
		return Checker.check(getNativeLeft(), UndefinedValues.INTEGER);
	}

}