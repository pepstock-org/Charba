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

import org.pepstock.charba.client.ChartEnvelop;
import org.pepstock.charba.client.ChartType;
import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.Type;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.IsEnvelop;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.enums.DefaultScaleId;
import org.pepstock.charba.client.enums.IndexAxis;
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
		INDEX_AXIS("indexAxis"),
		LABEL("label"),
		ORDER("order"),
		TYPE("type"),
		VISIBLE("visible"),
		Y_AXIS_ID("yAxisID"),
		X_AXIS_ID("xAxisID"),
		R_AXIS_ID("rAxisID"),
		V_AXIS_ID("vAxisID"),
		I_AXIS_ID("iAxisID"),
		// FIXME
		Y_SCALE("yScale"),
		X_SCALE("xScale"),
		R_SCALE("rScale"),
		V_SCALE("vScale"),
		I_SCALE("iScale"),
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

	// instance of controller
	private final DatasetItemController datasetItemController;

	/**
	 * Creates the item using an envelop with the native java script object which contains all properties.
	 * 
	 * @param envelop envelop with the native java script object which contains all properties.
	 */
	public DatasetMetaItem(ChartEnvelop<NativeObject> envelop) {
		this(IsEnvelop.checkAndGetIfValid(envelop).getContent());
	}

	/**
	 * To avoid any user creation but provides an empty object
	 */
	DatasetMetaItem() {
		this((NativeObject) null);
	}

	/**
	 * Creates the item using a native java script object which contains all properties.
	 * 
	 * @param nativeObject native java script object which contains all properties.
	 */
	DatasetMetaItem(NativeObject nativeObject) {
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
	 * Returns the dataset index.
	 * 
	 * @return the dataset index. Default is {@link UndefinedValues#INTEGER}.
	 */
	public int getIndex() {
		return getValue(Property.INDEX, UndefinedValues.INTEGER);
	}
	
	/**
	 * Returns the dataset index axis.
	 * 
	 * @return the dataset index axis or <code>null</code> if not found.
	 */
	public IndexAxis getIndexAxis() {
		return getValue(Property.INDEX_AXIS, IndexAxis.values(), null);
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
	 * Returns the Y scale item or <code>null</code> if not exists.
	 * 
	 * @return the Y scale item or <code>null</code> if not exists
	 */
	public ScaleItem getYScale() {
		return retrieveScale(Property.Y_SCALE);
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
	 * Returns the X scale item or <code>null</code> if not exists.
	 * 
	 * @return the X scale item or <code>null</code> if not exists
	 */
	public ScaleItem getXScale() {
		return retrieveScale(Property.X_SCALE);
	}

	/**
	 * Returns the R axis ID.
	 * 
	 * @return the R axis ID. Default is {@link DefaultScaleId#R}.
	 */
	public IsScaleId getRAxisID() {
		return getValue(Property.R_AXIS_ID, DefaultScaleId.R);
	}
	
	/**
	 * Returns the R scale item or <code>null</code> if not exists.
	 * 
	 * @return the R scale item or <code>null</code> if not exists
	 */
	public ScaleItem getRScale() {
		return retrieveScale(Property.R_SCALE);
	}
	
	/**
	 * Returns the V axis ID.
	 * 
	 * @return the V axis ID. Default is {@link DefaultScaleId#Y}.
	 */
	public IsScaleId getVAxisID() {
		return getValue(Property.V_AXIS_ID, DefaultScaleId.Y);
	}
	
	/**
	 * Returns the V scale item or <code>null</code> if not exists.
	 * 
	 * @return the V scale item or <code>null</code> if not exists
	 */
	public ScaleItem getVScale() {
		return retrieveScale(Property.V_SCALE);
	}
	
	/**
	 * Returns the I axis ID.
	 * 
	 * @return the I axis ID. Default is {@link DefaultScaleId#X}.
	 */
	public IsScaleId getIAxisID() {
		return getValue(Property.I_AXIS_ID, DefaultScaleId.X);
	}
	
	/**
	 * Returns the I scale item or <code>null</code> if not exists.
	 * 
	 * @return the I scale item or <code>null</code> if not exists
	 */
	public ScaleItem getIScale() {
		return retrieveScale(Property.I_SCALE);
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
		return ArrayListHelper.unmodifiableList(array, DatasetItem.FACTORY);
	}
	
	/**
	 * Returns the scale item from a specific property.
	 * 
	 * @param key object property to use
	 * @return a scael item or <code>null</code> if not found
	 */
	private ScaleItem retrieveScale(Property key) {
		// checks if the property is consistent
		if (Key.isValid(key) && isType(key, ObjectType.OBJECT)) {
			// creates the scale item
			return new ScaleItem(getValue(key));
		}
		// if here, the key or teh value is not consistent
		return null;
	}

}