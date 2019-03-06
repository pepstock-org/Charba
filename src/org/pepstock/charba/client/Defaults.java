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

import java.util.HashMap;
import java.util.Map;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.Merger;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.controllers.Controllers;
import org.pepstock.charba.client.plugins.GlobalPlugins;
import org.pepstock.charba.client.resources.DefaultsResources;

/**
 * This singleton is a wrapper to <code>defaults</code> object that CHART.JS (by CHART object) provides to get defaults values.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class Defaults {

	// singleton instance
	private static final Defaults INSTANCE = new Defaults();
	// native defaults java script object
	private final WrapperDefaults wrapperDefaults;
	// global options
	private final GlobalOptions options;
	// global scale
	private final GlobalScale scale;
	// global plugins
	private final GlobalPlugins plugins;
	// cache for chart options already implemented to improve performance
	private final Map<String, ChartOptions> chartOptions = new HashMap<>();
	// controllers
	private final Controllers controllers;

	/**
	 * To avoid any instantiation.<br>
	 * This gets from other objects (by static references) the defaults of CHART.JS.
	 */
	private Defaults() {
		// to be sure that chart.js has been injected
//		Injector.ensureInjected(Resources.INSTANCE.chartJs());
		Injector.ensureInjected(DefaultsResources.getResources().chartJs());
		// gets defaults from CHART.JS
		wrapperDefaults = new WrapperDefaults(Chart.getDefaults());
		// creates global options wrapping the global property of CHART
		this.options = new GlobalOptions(wrapperDefaults.getGlobal());
		// creates global scale wrapping the scale property of CHART
		this.scale = new GlobalScale(wrapperDefaults.getScale());
		// creates global plugins wrapping the plugins property of CHART
		this.plugins = new GlobalPlugins(Chart.getPlugins());
		// creates the controller object
		this.controllers = Controllers.get();
	}

	/**
	 * Singleton method to get static instance.
	 * 
	 * @return defaults instance
	 */
	public static Defaults get() {
		return INSTANCE;
	}

	/**
	 * Returns the global options.
	 * 
	 * @return the global options.
	 */
	public GlobalOptions getGlobal() {
		return options;
	}

	/**
	 * Returns the global scale.
	 * 
	 * @return the global scale.
	 */
	public GlobalScale getScale() {
		return scale;
	}

	/**
	 * Returns the global plugins manager.
	 * 
	 * @return the global plugins manager.
	 */
	public GlobalPlugins getPlugins() {
		return plugins;
	}

	/**
	 * Returns the controllers.
	 * 
	 * @return the controllers.
	 */
	public Controllers getControllers() {
		return controllers;
	}

	/**
	 * Returns the default options by a chart type, for a existing chart instance
	 * 
	 * @param type chart type.
	 * @return the default options
	 */
	ChartOptions getChartOptions(Type type) {
		return new ChartOptions(type, Merger.get().get(type));
	}

	/**
	 * Returns the default options by a chart type, by defaults of CHART.JS
	 * 
	 * @param type chart type.
	 * @return the default options
	 */
	public ChartOptions getOptions(Type type) {
		// checks if the options have already stored
		if (!chartOptions.containsKey(type.name())) {
			// if not, creates and stores new options by chart type
			chartOptions.put(type.name(), wrapperDefaults.chart(type));
		}
		// returns the existing options
		return chartOptions.get(type.name());
	}

	/**
	 * It wraps the defaults object of CHART.JS chart instance.<br>
	 * It returns the global, scale and chart options.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static final class WrapperDefaults extends NativeObjectContainer {

		/**
		 * Name of properties of native object.
		 */
		private enum Property implements Key
		{
			global,
			scale
		}

		/**
		 * Creates the wrapper by the default object of chart instance.
		 * 
		 * @param nativeObject the default object of chart instance
		 */
		WrapperDefaults(NativeObject nativeObject) {
			super(nativeObject);
		}

		/**
		 * Returns the GLOBAL options of chart as native object.
		 * 
		 * @return the GLOBAL options
		 */
		NativeObject getGlobal() {
			return getValue(Property.global);
		}

		/**
		 * Returns the SCALE global options of chart as native object.
		 * 
		 * @return the SCALE global options
		 */
		NativeObject getScale() {
			return getValue(Property.scale);

		}

		/**
		 * Returns an options instance, to use as default options, based of type of chart.
		 * 
		 * @param type chart type.
		 * @return default options.
		 */
		ChartOptions chart(Type type) {
			// checks if the property is present
			if (ObjectType.Object.equals(type(type))) {
				return new ChartOptions(type, getValue(type));
			} else {
				// if here, the chart type is not defined (could be a controller)
				// therefore returns an empty options
				return new ChartOptions(type);
			}
		}

	}

}
