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
package org.pepstock.charba.client.controllers;

import java.util.List;

import org.pepstock.charba.client.commons.GenericJavaScriptObject;
import org.pepstock.charba.client.commons.JavaScriptObjectContainer;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.items.ChartNode;
import org.pepstock.charba.client.items.UndefinedValues;

/**
 * This object stores the <code>this</code> instance of java script because is necessary to invoke the 
 * default methods of controller when it's extending an existing chart.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Context extends JavaScriptObjectContainer {
	
	/**
	 * Needed for GWt injection
	 */
	private enum Property implements Key
	{
		chart,
		index,
		_data
	}
	
	/**
	 * Wraps the CHART.JS java script object.
	 * 
	 * @param javaScriptObject CHART.JS java script object
	 */
	Context(GenericJavaScriptObject javaScriptObject) {
		super(javaScriptObject);
	}

	/**
	 * Returns the index of the data inside the dataset.
	 * 
	 * @return the index of the data inside the dataset.
	 * @see org.pepstock.charba.client.data.Dataset#getData()
	 * @see org.pepstock.charba.client.data.Data#getLabels()
	 */
	public int getIndex() {
		return getValue(Property.index, UndefinedValues.INTEGER);
	}

	/**
	 * Returns all view information about the chart instance.
	 * 
	 * @return all view information about the chart instance, otherwise <code>null</code>.
	 */
	public ChartNode getChartNode() {
		// checks if chart instance exists
		if (has(Property.chart)) {
			// returns the chart node
			return new ChartNode((GenericJavaScriptObject) getValue(Property.chart));
		}
		return null;
	}
	
	/**
	 * Returns an array of integer of data 
	 * @return an array of integer of data 
	 */
	public List<Integer> getData(){
		return getIntegerArray(Property._data);
	}
	
	/**
	 * Overrides the protected method of the container to export the java script object.
	 * @return export the java script object.
	 */
	GenericJavaScriptObject getObject() {
		return super.getJavaScriptObject();
	}
}