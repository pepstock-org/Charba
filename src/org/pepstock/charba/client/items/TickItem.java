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
package org.pepstock.charba.client.items;

import java.util.List;

import org.pepstock.charba.client.commons.GenericJavaScriptObject;
import org.pepstock.charba.client.commons.JavaScriptObjectContainer;
import org.pepstock.charba.client.commons.Key;

/**
 * This item contains the tick info item.<br>
 * It has been created to change the tick marks.
 * 
 * @author Andrea "Stock" Stocchero
 * @see org.pepstock.charba.client.callbacks.TickCallback
 */
public final class TickItem extends JavaScriptObjectContainer {

	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
		value,
		index,
		values
	}

	/**
	 * Wraps the CHART.JS java script object.
	 * 
	 * @param javaScriptObject CHART.JS java script object
	 */
	public TickItem(GenericJavaScriptObject javaScriptObject) {
		super(javaScriptObject);
	}

	/**
	 * Returns the value of the tick.
	 * 
	 * @return the value of the tick. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	public double getValue() {
		return getValue(Property.value, UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the index of the tick.
	 * 
	 * @return the index of the tick. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	public int getIndex() {
		return getValue(Property.index, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the complete list of ticks.
	 * 
	 * @return the complete list of ticks.
	 */
	public List<Double> getValues() {
		return getDoubleArray(Property.values);
	}

}