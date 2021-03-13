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
package org.pepstock.charba.client;

import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.configuration.ConfigurationOptions;
import org.pepstock.charba.client.data.Data;
import org.pepstock.charba.client.plugins.Plugins;

/**
 * This is the configuration object of a chart.<br>
 * It contains always the type, options, plugins and data.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class Configuration extends NativeObjectContainer {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		TYPE("type"),
		OPTIONS("options"),
		DATA("data"),
		PLUGINS("plugins");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use in the native object.
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
	 * To avoid any instantiation
	 */
	Configuration() {
		// do nothing
	}

	/**
	 * Sets the options of chart by the <code>options</code> property in the native object.
	 * 
	 * @param options options of chart as native object.
	 */
	public void setOptions(NativeObject options) {
		setValue(Property.OPTIONS, options);
	}

	/**
	 * Returns the options of chart by the <code>options</code> property in the native object.
	 * 
	 * @return options of chart as native object.
	 */
	NativeObject getOptions() {
		return getValue(Property.OPTIONS);
	}

	/**
	 * Sets the data of chart by the <code>data</code> property in the native object.
	 * 
	 * @param data data of chart as native object.
	 */
	public void setData(NativeObject data) {
		setValue(Property.DATA, data);
	}

	/**
	 * Sets the inline plugins of chart by the <code>plugins</code> property in the native object.
	 * 
	 * @param plugins inline plugins of chart as array of plugin native object.
	 */
	public void setPlugins(ArrayObject plugins) {
		setArrayValue(Property.PLUGINS, plugins);
	}

	/**
	 * Sets the type of chart.
	 * 
	 * @param type type of chart.
	 */
	void setType(Type type) {
		setValue(Property.TYPE, type);
	}

	/**
	 * Sets the options of the chart.
	 * 
	 * @param chart chart instance
	 * @param options the options of the chart.
	 * @param <T> type of options
	 */
	<T extends ConfigurationOptions> void setOptions(IsChart chart, T options) {
		options.load(chart, this);
	}

	/**
	 * Sets the data of chart.
	 * 
	 * @param chart chart instance
	 * @param data the data of chart.
	 */
	void setData(IsChart chart, Data data) {
		data.load(chart, this);
	}

	/**
	 * Sets the plugins of chart.
	 * 
	 * @param chart chart instance
	 * @param plugins the plugins of chart.
	 */
	void setPlugins(IsChart chart, Plugins plugins) {
		plugins.load(chart, this);
	}

	/**
	 * Returns the native object instance.
	 * 
	 * @return the native object instance.
	 */
	NativeObject nativeObject() {
		return getNativeObject();
	}

}