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

import org.pepstock.charba.client.commons.GenericJavaScriptObject;
import org.pepstock.charba.client.defaults.global.Options;
import org.pepstock.charba.client.defaults.scale.Scale;
import org.pepstock.charba.client.plugins.GlobalPlugins;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * Is a singleton which is mapping the default of CHART.JS.<br>
 * It maps the CHART.JS object of default, <code>chart.defaults</code>.<br>
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Defaults {

	// private instance
	private static final Defaults INSTANCE = new Defaults();
	
	private final Options global;
	
	private final Scale scale;
	
	// cache for chart options
	private final Map<Type, Options> chartOptions = new HashMap<Type, Options>();
	
	// global plugins
	private final GlobalPlugins plugins = new GlobalPlugins();
	
	/**
	 * To avoid any instantiation
	 */
	private Defaults() {
		// injects CHART.JS script is not done
		Injector.ensureInjected();
		// loads global options
		global = new Options((GenericJavaScriptObject)getGlobalOptions());
		// loads scale options
		scale = new Scale((GenericJavaScriptObject)getGlobalScale());
		// loads all charts options
		for (Type type : Type.values()){
			chartOptions.put(type, new Options((GenericJavaScriptObject)getChart(type.name())));
		}
	}
	
	/**
	 * Returns the global options.<br>
	 * It maps the CHART.JS object of default, <code>chart.defaults.global</code>.
	 * 
	 * @return the global options.
	 * @see org.pepstock.charba.client.defaults.global.Options
	 */
	public static Options getGlobal(){
		return INSTANCE.global;
	}
	
	/**
	 * Returns the global scale options.<br>
	 * It maps the CHART.JS object of default, <code>chart.defaults.scale</code>.
	 * 
	 * @return the global scale options.
	 * @see org.pepstock.charba.client.defaults.scale.Scale
	 */
	public static Scale getScale(){
		return INSTANCE.scale;
	}
	
	/**
	 * Returns the global plugins wrapper.
	 *  
	 * @return the global plugins wrapper.
	 * @see org.pepstock.charba.client.plugins.GlobalPlugins
	 */
	public static GlobalPlugins getPlugins(){
		return INSTANCE.plugins;
	}
	
	/**
	 * Returns the global options by chart type.
	 * 
	 * @param type chart type.
	 * @return the global options by chart type. if type not exist, returns the options of BAR chart.
	 * @see org.pepstock.charba.client.defaults.global.Options
	 * @see Type
	 */
	public static Options getChartGlobal(Type type){
		if (INSTANCE.chartOptions.containsKey(type)){
			return INSTANCE.chartOptions.get(type);
		}
		// if type not exist
		// returns the options for BAR chart.
		return INSTANCE.chartOptions.get(Type.bar);
	}
	
	/**
	 * Returns the GLOBAL options of CHART.JS.
	 * @return java script object with GLOBAl configuration.
	 */
	private native JavaScriptObject getGlobalOptions()/*-{
	    return $wnd.Chart.defaults.global;
	}-*/;

	/**
	 * Returns the GLOBAL SCALE options of CHART.JS.
	 * @return java script object with GLOBAl SCLAE configuration.
	 */
	private native JavaScriptObject getGlobalScale()/*-{
    	return $wnd.Chart.defaults.scale;
	}-*/;

	/**
	 * Returns the GLOBAL options of CHART.JS by chart type.
	 * @param type chart type
	 * @return ava script object with GLOBAL configuration for chart type.
	 */
	private native JavaScriptObject getChart(String type)/*-{
		return $wnd.Chart.defaults[type];
	}-*/;
	
}
