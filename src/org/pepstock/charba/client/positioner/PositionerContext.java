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
package org.pepstock.charba.client.positioner;

import org.pepstock.charba.client.Chart;
import org.pepstock.charba.client.commons.NativeName;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * The positioner context is used to give contextual information to the tooltip positioner function.<br>
 * The context object contains the following properties:<br>
 * <ul>
 * <li><b>Charba ID</b>(String): id (of Charba) assigned to chart
 * </ul>
 * 
 * @author Andrea "Stock" Stocchero
 */
@JsType(isNative = true, name = NativeName.OBJECT, namespace = JsPackage.GLOBAL)
public final class PositionerContext {

	/**
	 * To avoid any instantiation
	 */
	protected PositionerContext() {
	}

	/**
	 * Returns the <code>chart</code> property by native object.
	 * 
	 * @return the <code>chart</code> property by native object.
	 */
	@JsProperty(name = "_chart")
	native Chart getNativeChart();


	/**
	 * Returns the CHARBA ID, set to the chart.
	 * 
	 * @return the CHARBA ID
	 */
	@JsOverlay
	String getCharbaId() {
		return getNativeChart().getCharbaId();
	}

}