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

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;

/**
 * Contains all info for every item of tooltip.<br>
 * Created and passed by CHART.JS.<br>
 * It uses into the tooltips callbacks.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class TooltipItem extends NativeObjectContainer {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		LABEL("label"),
		VALUE("value"),
		DATASET_INDEX("datasetIndex"),
		INDEX("index"),
		X("x"),
		Y("y");

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

	/**
	 * Creates the item using a native java script object which contains all properties.
	 * 
	 * @param nativeObject native java script object which contains all properties.
	 */
	public TooltipItem(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns the label for the tooltip.
	 * 
	 * @return the label for the tooltip. Default is {@link UndefinedValues#STRING}.
	 */
	public String getLabel() {
		return getValue(Property.LABEL, UndefinedValues.STRING);
	}

	/**
	 * Returns the value for the tooltip.
	 * 
	 * @return the value for the tooltip. Default is {@link UndefinedValues#STRING}.
	 */
	public String getValue() {
		return getValue(Property.VALUE, UndefinedValues.STRING);
	}

	/**
	 * Returns the dataset index of the chart
	 * 
	 * @return the dataset index of the chart. Default is {@link UndefinedValues#INTEGER}.
	 */
	public int getDatasetIndex() {
		return getValue(Property.DATASET_INDEX, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the index of the data inside the dataset.
	 * 
	 * @return the index of the data inside the dataset. Default is {@link UndefinedValues#INTEGER}.
	 */
	public int getIndex() {
		return getValue(Property.INDEX, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the X location of tooltip item.
	 * 
	 * @return the X location of tooltip item. Default is {@link UndefinedValues#INTEGER}.
	 */
	public int getX() {
		return getValue(Property.X, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the Y location of tooltip item.
	 * 
	 * @return the Y location of tooltip item. Default is {@link UndefinedValues#INTEGER}.
	 */
	public int getY() {
		return getValue(Property.Y, UndefinedValues.INTEGER);
	}

	/**
	 * Inner class to create tooltip item by a native object.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	public static final class TooltipItemFactory implements NativeObjectContainerFactory<TooltipItem> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.commons. NativeObject)
		 */
		@Override
		public TooltipItem create(NativeObject nativeObject) {
			return new TooltipItem(nativeObject);
		}
	}
}