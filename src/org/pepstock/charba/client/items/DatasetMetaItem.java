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

import org.pepstock.charba.client.ChartType;
import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.Type;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.enums.DefaultScaleId;
import org.pepstock.charba.client.items.DatasetItem.DatasetItemFactory;
import org.pepstock.charba.client.options.IsScaleId;

/**
 * Calling some methods on your chart instance passing an argument of an event, will return the elements at the event position.<br>
 * Created and passed by CHART.JS and provide dataset metadata information.<br>
 * Contains all data set items.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DatasetMetaItem extends NativeObjectContainer {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		CONTROLLER("controller"),
		DATA("data"),
		HIDDEN("hidden"),
		INDEX("index"),
		LABEL("label"),
		ORDER("order"),
		TYPE("type"),
		VISIBLE("visible"),
		Y_AXIS_ID("yAxisID"),
		X_AXIS_ID("xAxisID"),
		// doughnut, pie
		TOTAL("total");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use into native object.
		 * 
		 * @param value value of property name
		 */
		private Property(String value) {
			this.value = value;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.Key#value()
		 */
		@Override
		public String value() {
			return value;
		}

	}

	// instance of dataset items factory.
	private final DatasetItemFactory datasetItemFactory = new DatasetItemFactory();
	// instance of controoler
	private final DatasetItemController datasetItemController;

	/**
	 * To avoid any user creation but provides an empty object
	 */
	DatasetMetaItem() {
		this(null);
	}

	/**
	 * Creates the item using a native java script object which contains all properties.
	 * 
	 * @param nativeObject native java script object which contains all properties.
	 */
	public DatasetMetaItem(NativeObject nativeObject) {
		super(nativeObject);
		// stores controller
		datasetItemController = new DatasetItemController(getValue(Property.CONTROLLER));
	}

	/**
	 * Returns the dataset controller.
	 * 
	 * @return the dataset controller
	 */
	public DatasetItemController getController() {
		return datasetItemController;
	}

	/**
	 * Returns the type of dataset.
	 * 
	 * @return the type of dataset. If not set or invalid, the default is {@link ChartType#BAR}.
	 */
	public Type getType() {
		// gets string value from java script object
		String value = getValue(Property.TYPE, ChartType.BAR.value());
		// checks if consistent with out of the box chart types
		Type type = Key.getKeyByValue(ChartType.values(), value);
		// if not, creates new type being a controller.
		if (type == null) {
			// gets type from controllers
			type = Defaults.get().getControllers().getTypeByString(value);
		}
		return type == null ? ChartType.BAR : type;
	}

	/**
	 * Returns if the dataset is visible.
	 * 
	 * @return <code>true</code> if the dataset is visible, otherwise is {@link UndefinedValues#BOOLEAN}.
	 */
	public boolean isVisible() {
		return getValue(Property.VISIBLE, UndefinedValues.BOOLEAN);
	}

	/**
	 * Returns if the dataset is hidden.
	 * 
	 * @return <code>true</code> if the dataset is hidden, otherwise is {@link UndefinedValues#BOOLEAN}.
	 */
	public boolean isHidden() {
		return getValue(Property.HIDDEN, UndefinedValues.BOOLEAN);
	}

	/**
	 * FIXME Sets if the dataset must be hidden.
	 * 
	 * @param hidden <code>true</code> if the dataset must be hidden, otherwise is {@link UndefinedValues#BOOLEAN}.
	 */
	// public void setHidden(boolean hidden) {
	// setValue(Property.HIDDEN, hidden);
	// }

	/**
	 * Returns the dataset index.
	 * 
	 * @return the dataset index. Default is {@link UndefinedValues#INTEGER}.
	 */
	public int getIndex() {
		return getValue(Property.INDEX, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the dataset order.
	 * 
	 * @return the dataset order. Default is {@link UndefinedValues#INTEGER}.
	 */
	public int getOrder() {
		return getValue(Property.ORDER, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the dataset label.
	 * 
	 * @return the dataset label. Default is {@link UndefinedValues#STRING}.
	 */
	public String getLabel() {
		return getValue(Property.LABEL, UndefinedValues.STRING);
	}

	/**
	 * Returns the Y axis ID.
	 * 
	 * @return the Y axis ID. Default is {@link DefaultScaleId#Y}.
	 */
	public IsScaleId getYAxisID() {
		return getValue(Property.Y_AXIS_ID, DefaultScaleId.Y);
	}

	/**
	 * Returns the X axis ID.
	 * 
	 * @return the X axis ID. Default is {@link DefaultScaleId#X}.
	 */
	public IsScaleId getXAxisID() {
		return getValue(Property.X_AXIS_ID, DefaultScaleId.X);
	}

	/**
	 * Returns the dataset total value of data.
	 * 
	 * @return the dataset total value of data. Default is {@link UndefinedValues#DOUBLE}.
	 */
	public double getTotal() {
		return getValue(Property.TOTAL, UndefinedValues.DOUBLE);
	}

	/**
	 * Returns a list of dataset metadata items.
	 * 
	 * @return a list of dataset metadata items.
	 */
	public List<DatasetItem> getDatasets() {
		ArrayObject array = getArrayValue(Property.DATA);
		return ArrayListHelper.unmodifiableList(array, datasetItemFactory);
	}

}