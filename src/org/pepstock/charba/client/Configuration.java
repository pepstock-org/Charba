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

import org.pepstock.charba.client.commons.NativeName;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.configuration.ConfigurationOptions;
import org.pepstock.charba.client.data.Data;
import org.pepstock.charba.client.plugins.ArrayPlugin;
import org.pepstock.charba.client.plugins.Plugins;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * This is the configuration object of a chart.<br>
 * It contains always the type, options, plugins and data.<br>
 * THIS IS AN EXPORTED OBJECT.
 * 
 * @author Andrea "Stock" Stocchero
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = NativeName.OBJECT)
public final class Configuration {

	/**
	 * To avoid any instantiation
	 */
	Configuration() {
		// do nothing
	}

	/**
	 * Sets the type of chart by the <code>type</code> property into native object.
	 * 
	 * @param type type of chart as string.
	 */
	@JsProperty(name = "type")
	native void setNativeType(String type);

	/**
	 * Sets the options of chart by the <code>options</code> property into native object.
	 * 
	 * @param options options of chart as native object.
	 */
	@JsProperty
	public native void setOptions(NativeObject options);

	/**
	 * Sets the data of chart by the <code>data</code> property into native object.
	 * 
	 * @param data data of chart as native object.
	 */
	@JsProperty
	public native void setData(NativeObject data);

	/**
	 * Sets the inline plugins of chart by the <code>plugins</code> property into native object.
	 * 
	 * @param plugins inline plugins of chart as array of plugin native object.
	 */
	@JsProperty
	public native void setPlugins(ArrayPlugin plugins);

	/**
	 * Sets the type of chart.
	 * 
	 * @param type type of chart.
	 */
	@JsOverlay
	void setType(Type type) {
		setNativeType(type.name());
	}

	/**
	 * Sets the options of the chart.
	 * 
	 * @param options the options of the chart.
	 */
	@JsOverlay
	final <T extends ConfigurationOptions> void setOptions(T options) {
		options.load(this);
	}

	/**
	 * Sets the data of chart.
	 * 
	 * @param data the data of chart.
	 */
	@JsOverlay
	void setData(Data data) {
		data.load(this);
	}

	/**
	 * Sets the plugins of chart.
	 * 
	 * @param plugins the plugins of chart.
	 */
	@JsOverlay
	void setPlugins(Plugins plugins) {
		plugins.load(this);
	}

}