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

import java.util.List;

import org.pepstock.charba.client.commons.JavaScriptObjectContainer;
import org.pepstock.charba.client.jsinterop.commons.ArrayDouble;
import org.pepstock.charba.client.jsinterop.commons.ArrayListHelper;
import org.pepstock.charba.client.jsinterop.commons.Checker;
import org.pepstock.charba.client.jsinterop.commons.NativeName;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * This item contains the tick info item.<br>
 * It has been created to change the tick marks.
 * 
 * @author Andrea "Stock" Stocchero
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = NativeName.OBJECT)
public final class TickItem extends JavaScriptObjectContainer {
	
	@JsProperty(name = "value")
	native double getNativeValue();

	@JsProperty(name = "index")
	native int getNativeIndex();

	@JsProperty(name = "values")
	native ArrayDouble getNativeValues();

	/**
	 * Returns the value of the tick.
	 * 
	 * @return the value of the tick. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	@JsOverlay
	public double getValue() {
		return Checker.check(getNativeValue(), UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the index of the tick.
	 * 
	 * @return the index of the tick. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	@JsOverlay
	public int getIndex() {
		return Checker.check(getNativeIndex(), UndefinedValues.INTEGER);
	}

	/**
	 * Returns the complete list of ticks.
	 * 
	 * @return the complete list of ticks.
	 */
	@JsOverlay
	public List<Double> getValues() {
		return ArrayListHelper.unmodifiableList(getNativeValues());
	}

}