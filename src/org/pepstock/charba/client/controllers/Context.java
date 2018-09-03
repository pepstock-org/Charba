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
 * 
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Context extends JavaScriptObjectContainer {
	
	private final ChartNode chartNode;
	
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
		if (has(Property.chart)) {
			chartNode = new ChartNode((GenericJavaScriptObject) getValue(Property.chart));
		} else {
			chartNode = null;
		}
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
	 * Returns all view information about the dataset.
	 * 
	 * @return all view information about the dataset.
	 * @see org.pepstock.charba.client.items.DatasetViewItem
	 */
	public ChartNode getChartNode() {
		return chartNode;
	}
	
	public List<Integer> getData(){
		return getIntegerArray(Property._data);
	}
	
	GenericJavaScriptObject getObject() {
		return super.getJavaScriptObject();
	}
}