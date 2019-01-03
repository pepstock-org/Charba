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
package org.pepstock.charba.client.jsinterop.data;

import java.util.List;

import org.pepstock.charba.client.ChartType;
import org.pepstock.charba.client.Type;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.jsinterop.Defaults;
import org.pepstock.charba.client.jsinterop.commons.ArrayDouble;
import org.pepstock.charba.client.jsinterop.commons.ArrayListHelper;
import org.pepstock.charba.client.jsinterop.commons.NativeObjectContainer;
import org.pepstock.charba.client.jsinterop.items.UndefinedValues;
import org.pepstock.charba.client.jsinterop.utils.JSON;

/**
 * The chart allows a number of properties to be specified for each dataset. These are used to set display properties for a
 * specific dataset.<br>
 * This is the base implementation for all datasets with common fields.
 * 
 * @author Andrea "Stock" Stocchero
 * @vresion 2.0
 */
public abstract class Dataset extends NativeObjectContainer {
	// default for hidden property
	private static final boolean DEFAULT_HIDDEN = false;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		label,
		data,
		type,
		hidden
	}

	/**
	 * Sets if the dataset will appear or not.
	 * 
	 * @param hidden if the dataset will appear or not.
	 */
	public void setHidden(boolean hidden) {
		// checks if is hidden
		if (hidden) {
			// then sets it
			setValue(Property.hidden, hidden);
		} else {
			// if is not hidden
			// remove the property
			remove(Property.hidden);
		}
	}

	/**
	 * Returns if the dataset will appear or not.
	 * 
	 * @return if the dataset will appear or not. Default is <code>false</code>
	 */
	public boolean isHidden() {
		return getValue(Property.hidden, DEFAULT_HIDDEN);
	}

	/**
	 * Sets the label for the dataset which appears in the legend and tooltips.
	 * 
	 * @param label the label for the dataset which appears in the legend and tooltips.
	 */
	public void setLabel(String label) {
		setValue(Property.label, label);
	}

	/**
	 * Returns the label for the dataset which appears in the legend and tooltips.
	 * 
	 * @return the label for the dataset which appears in the legend and tooltips.
	 */
	public String getLabel() {
		return getValue(Property.label, UndefinedValues.STRING);
	}

	/**
	 * Sets the data property of a dataset for a chart is specified as an array of numbers. Each point in the data array
	 * corresponds to the label at the same index on the x axis.
	 * 
	 * @param values an array of numbers
	 */
	public void setData(double... values) {
		setArrayValue(Property.data, ArrayDouble.of(values));
	}

	/**
	 * Sets the data property of a dataset for a chart is specified as an array of numbers. Each point in the data array
	 * corresponds to the label at the same index on the x axis.
	 * 
	 * @param values list of numbers.
	 */
	public void setData(List<Double> values) {
		setArrayValue(Property.data, ArrayDouble.of(values));
	}

	/**
	 * Returns the data property of a dataset for a chart is specified as an array of numbers. Each point in the data array
	 * corresponds to the label at the same index on the x axis.
	 * 
	 * @return list of numbers.
	 */
	public List<Double> getData() {
		ArrayDouble array = getArrayValue(Property.data);
		return ArrayListHelper.list(array);
	}

	/**
	 * Sets the type of dataset based on type of chart.
	 * 
	 * @param type type of dataset.
	 */
	public void setType(Type type) {
		setValue(Property.type, type);
	}

	/**
	 * Returns the type of dataset, based on type of chart.
	 * 
	 * @return type of dataset or null if not set. If not set or invalid, the default is {@link org.pepstock.charba.client.ChartType#bar}.
	 */
	public Type getType() {
		// gets string value from java script object
		String value = getValue(Property.type, ChartType.bar.name());
		// checks if consistent with out of the box chart types
		Type type = ChartType.get(value);
		// if not, creates new type being a controller.
		if (type == null) {
			// gets type from controllers
			type = Defaults.get().getControllers().getTypeByString(value);
		}
		return type == null ? ChartType.bar : type;
	}
	
	/**
	 * Returns the data property in JSON format.
	 * 
	 * @return the data property in JSON format.
	 */
	final String getDataAsString() {
		return JSON.stringify(getValue(Property.data));
	}

}