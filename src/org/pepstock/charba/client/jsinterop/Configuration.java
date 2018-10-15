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
package org.pepstock.charba.client.jsinterop;

import org.pepstock.charba.client.Type;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;
import org.pepstock.charba.client.jsinterop.configuration.ConfigurationOptions;
import org.pepstock.charba.client.jsinterop.data.Data;
import org.pepstock.charba.client.jsinterop.data.NativeData;
import org.pepstock.charba.client.jsinterop.options.NativeOptions;
import org.pepstock.charba.client.jsinterop.plugins.ArrayPlugin;
import org.pepstock.charba.client.jsinterop.plugins.Plugins;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * This is the configuration object of a chart.<br>
 * It contains always teh type, options, plugins and data.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name="Object")
public final class Configuration extends NativeObject {
	
	@JsProperty(name = "type")
	native void setNativeType(String type);

	@JsProperty
	public native void setOptions(NativeOptions options);

	@JsProperty
	public native void setData(NativeData data);

	@JsProperty 
	public native void setPlugins(ArrayPlugin plugins);

	/**
	 * Sets the type of chart.
	 * 
	 * @param type type of chart.
	 * @see Type
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