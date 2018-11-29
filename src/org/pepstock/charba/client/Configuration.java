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

import org.pepstock.charba.client.commons.GenericJavaScriptObject;
import org.pepstock.charba.client.commons.JavaScriptObjectContainer;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.data.Data;
import org.pepstock.charba.client.options.BaseOptions;
import org.pepstock.charba.client.plugins.Plugins;

/**
 * This is the configuration object of a chart.<br>
 * It contains always teh type, options, plugins and data.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
class Configuration extends JavaScriptObjectContainer {

	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
		type,
		options,
		plugins,
		data
	}

	/**
	 * Sets the type of chart.
	 * 
	 * @param type type of chart.
	 * @see Type
	 */
	void setType(Type type) {
		setValue(Property.type, type.name());
	}

	/**
	 * Sets the options of the chart.
	 * 
	 * @param options the options of the chart.
	 */
	void setOptions(BaseOptions options) {
		setValue(Property.options, options);
	}

	/**
	 * Sets the data of chart.
	 * 
	 * @param data the data of chart.
	 */
	void setData(Data data) {
		setValue(Property.data, data);
	}

	/**
	 * Sets the plugins of chart.
	 * 
	 * @param plugins the plugins of chart.
	 */
	void setPlugins(Plugins plugins) {
		setObjectArray(Property.plugins, plugins.getArrayList());
	}

	/**
	 * Wraps the protected method to get the java script object in order to consume it to configure the chart.
	 * 
	 * @return the java script object in order to consume it to configure the chart.
	 */
	GenericJavaScriptObject getObject() {
		return getJavaScriptObject();
	}

}