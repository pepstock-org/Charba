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

import org.pepstock.charba.client.ChartType;
import org.pepstock.charba.client.Type;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.controllers.ControllerType;
import org.pepstock.charba.client.jsinterop.commons.ArrayListHelper;
import org.pepstock.charba.client.jsinterop.commons.ArrayObject;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;
import org.pepstock.charba.client.jsinterop.commons.NativeObjectContainer;
import org.pepstock.charba.client.jsinterop.items.DatasetItem.DatasetItemFactory;
import org.pepstock.charba.client.jsinterop.options.Scales;

/**
 * This object is just a proxy object, created from JavaScript side, to wrap an JavaScript array.<br>
 * Created and passed by CHART.JS.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class DatasetMetaItem extends NativeObjectContainer {
	
	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
		hidden,
		type,
		data,
		yAxisID,
		xAxisID
	}
	
	private final DatasetItemFactory datasetItemFactory = new DatasetItemFactory();
	
	/**
	 * 
	 */
	DatasetMetaItem() {
	}

	/**
	 * @param nativeObject
	 */
	public DatasetMetaItem(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns the type of dataset. If not set, the default is <code>bar</code>.
	 * 
	 * @return the type of dataset
	 * @see org.pepstock.charba.client.Type
	 */
	public Type getType() {
		// gets string value from java script object
		String value = getValue(Property.type, ChartType.bar.name());
		// checks if consistent with out of the box chart types
		Type type = ChartType.get(value);
		// if not, creates new type being a controller.
		return type == null ? new ControllerType(value) : type;
	}

	/**
	 * Returns if the dataset is hidden.
	 * 
	 * @return <code>true</code> if the dataset is hidden, otherwise is {@link org.pepstock.charba.client.items.UndefinedValues#BOOLEAN}.
	 */
	public boolean isHidden() {
		return getValue(Property.hidden, UndefinedValues.BOOLEAN);
	}

	/**
	 * Sets if the dataset must be hidden.
	 * 
	 * @param hidden <code>true</code> if the dataset must be hidden, otherwise is {@link org.pepstock.charba.client.items.UndefinedValues#BOOLEAN}.
	 */
	public void setHidden(boolean hidden) {
		setValue(Property.hidden, hidden);
	}
	
	/**
	 * Returns the Y axis ID. 
	 * 
	 * @return the Y axis ID. Default is {@link org.pepstock.charba.client.items.UndefinedValues#STRING}.
	 */
	public String getYAxisID() {
		return getValue(Property.yAxisID, Scales.DEFAULT_Y_AXIS_ID);
	}
	
	/**
	 * Returns the X axis ID. 
	 * 
	 * @return the X axis ID. Default is {@link org.pepstock.charba.client.items.UndefinedValues#STRING}.
	 */
	public String getXAxisID() {
		return getValue(Property.xAxisID, Scales.DEFAULT_X_AXIS_ID);
	}

	/**
	 * Returns a list of dataset metadata items.
	 * 
	 * @return a list of dataset metadata items.
	 * @see org.pepstock.charba.client.items.DatasetItem
	 */
	public List<DatasetItem> getDatasets() {
		ArrayObject array = getArrayValue(Property.data);
		return ArrayListHelper.unmodifiableList(array, datasetItemFactory);
	}
	
}