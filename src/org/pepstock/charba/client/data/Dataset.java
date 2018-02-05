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
package org.pepstock.charba.client.data;

import java.util.List;

import org.pepstock.charba.client.Type;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.JavaScriptObjectContainer;
import org.pepstock.charba.client.commons.JsDoubleArrayList;
import org.pepstock.charba.client.commons.Key;

/**
 * The chart allows a number of properties to be specified for each dataset. These are used to set display properties for a specific dataset.<br>
 * This is the base implementation for all datasets with common fields.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public abstract class Dataset extends JavaScriptObjectContainer{
	
	/**
	 * Name of fields of JavaScript object. 
	 */
	private enum Property implements Key {
		label,
		data,
		type
	}
	
	/**
	 * Sets the label for the dataset which appears in the legend and tooltips.
	 * @param label the label for the dataset which appears in the legend and tooltips.
	 */
	public void setLabel(String label){
		  setValue(Property.label, label);
	}

	/**
	 * Returns the label for the dataset which appears in the legend and tooltips.
	 * @return the label for the dataset which appears in the legend and tooltips.
	 */
	public String getLabel(){
		  return getValue(Property.label, (String)null);
	}

	/**
	 * Sets the data property of a dataset for a chart is specified as an array of numbers. Each point in the data array corresponds to the label at the same index on the x axis.
	 * @param values an array of numbers
	 */
	public void setData(double... values){
		setInternalData(ArrayListHelper.build(values));
	}
	
	/**
	 * Sets the data property of a dataset for a chart is specified as an array of numbers. Each point in the data array corresponds to the label at the same index on the x axis.
	 * @param values list of numbers. 
	 * @see org.pepstock.charba.client.commons.JsDoubleArrayList
	 */
	public void setData(List<Double> values){
		// checks if is a internal list, already with JavaScript object
		if (values instanceof JsDoubleArrayList){
			// sets directly
			setInternalData((JsDoubleArrayList)values);
		} else {
			// creates a new JavaScript arraylist
			JsDoubleArrayList list = new JsDoubleArrayList();
			// adds all values
			list.addAll(values);
			// sets array
			setInternalData(list);
		}
	}

	/**
	 * Sets the data property of a dataset for a chart is specified as an array of numbers. Each point in the data array corresponds to the label at the same index on the x axis.
	 * @param list list of numbers.
	 * @see org.pepstock.charba.client.commons.JsDoubleArrayList
	 */
	private void setInternalData(JsDoubleArrayList list){
		setDoubleArray(Property.data, list);
	}

	/**
	 * Returns the data property of a dataset for a chart is specified as an array of numbers. Each point in the data array corresponds to the label at the same index on the x axis.
	 * @return list of numbers.
	 * @see org.pepstock.charba.client.commons.JsDoubleArrayList
	 */
	public List<Double> getData(){
		return getDoubleArray(Property.data);
	}

	/**
	 * Sets the type of dataset based on type of chart.
	 * @param type type of dataset.
	 */
	public void setType(Type type){
		  setValue(Property.type, type);
	}

	/**
	 * Returns the type of dataset, based on type of chart.
	 * @return type of dataset or null if not set.
	 */
	public Type getType(){
		  return getValue(Property.type, Type.class, (Type)null);
	}

}