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

import java.util.HashMap;
import java.util.Map;

import org.pepstock.charba.client.ChartType;
import org.pepstock.charba.client.Injector;
import org.pepstock.charba.client.Type;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.jsinterop.commons.Merger;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;
import org.pepstock.charba.client.jsinterop.commons.NativeObjectContainer;
import org.pepstock.charba.client.jsinterop.commons.ObjectType;
import org.pepstock.charba.client.jsinterop.controllers.ControllerType;
import org.pepstock.charba.client.jsinterop.controllers.Controllers;
import org.pepstock.charba.client.jsinterop.plugins.GlobalPlugins;

/**
 * This singleton is a wrapper to <code>defaults</code> object that CHART.JS (by CHART object) provides to get defaults values.
 * 
 * @author Andrea "Stock" Stocchero
 * @version 2.0
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
	 * This gets from other objects 8by static references) the defaults of CHART.JS.
	 */
	Defaults() {
		// to be sure that chart.js has been injected
		Injector.ensureInjected();
		// gets defaults from CHART.JS
		wrapperDefaults = new WrapperDefaults(Chart.getDefaults());
		// creates global options wrapping the global property of CHART
		this.options = new GlobalOptions(wrapperDefaults.getGlobal());
		// creates global scale wrapping the scale property of CHART
		this.scale = new GlobalScale(wrapperDefaults.getScale());
		// creates global plugins wrapping the plugins property of CHART
		this.plugins = new GlobalPlugins(Chart.getPlugins());
		// creates the controller object
		this.controllers = new Controllers(Chart.getControllers(), Chart.getDatasetController());
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
	
	public void addControllerOptions(ControllerType type, ChartType parent) {
		wrapperDefaults.addControllerOptions(type, parent);
	}

	/**
	 * Returns the default options by a chart type, for a existing chart instance
	 * 
	 * @param type chart type.
	 * @return the default options
	 */
	public ChartOptions options(Type type) {
		return new ChartOptions(type, Merger.get().get(type));
	}

	/**
	 * Returns the default options by a chart type, by defaults of CHART.JS
	 * 
	 * @param type chart type.
	 * @return the default options
	 */
	public ChartOptions chart(Type type) {
		// checks if the options have already stored
		if (!chartOptions.containsKey(type.name())) {
			// if not, creates and stores new options by chart type
			chartOptions.put(type.name(), wrapperDefaults.chart(type));
		}
		// returns the existing options
		return chartOptions.get(type.name());
	}
	
	private static final class WrapperDefaults extends NativeObjectContainer{
		
		/**
		 * Name of properties of native object.
		 */
		private enum Property implements Key
		{
			global,
			scale
		}

		/**
		 * @param nativeObject
		 */
		WrapperDefaults(NativeObject nativeObject) {
			super(nativeObject);
		}
		
		NativeObject getGlobal() {
			return getValue(Property.global);
		}
		
		NativeObject getScale() {
			return getValue(Property.scale);
			
		}
		
		void addControllerOptions(ControllerType type, ChartType parent) {
			if (has(parent)) {
				setValue(type, getValue(parent));
			}
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
