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
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Is a static reference which collects all chart instances to be able to enable global plugins.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Charts {

	// buffer with all charts instances
	// K = CHART id (CHARBA ID)
	// V = chart instance
	private static final Map<String, AbstractChart<?, ?>> CHARTS = new HashMap<String, AbstractChart<?, ?>>();
	// list with all charts life cycle listeners
	private static final List<ChartsLifecycleListener> LISTENERS = new LinkedList<>();

	/**
	 * To avoid any instantiation
	 */
	private Charts() {
	}
	
	/**
	 * Adds new charts life cycle listener instance into collection.
	 * 
	 * @param listener chart life cycle listener instance
	 */
	public static void addLifecycleListener(ChartsLifecycleListener listener) {
		LISTENERS.add(listener);
	}

	/**
	 * Removes a charts life cycle listener instance from collection.
	 * 
	 * @param listener chart life cycle listener instance
	 */
	public static void removeLifecycleListener(ChartsLifecycleListener listener) {
		LISTENERS.remove(listener);
	}

	/**
	 * Adds new charts instance into collection.
	 * 
	 * @param chart chart instance
	 */
	static void add(AbstractChart<?, ?> chart) {
		// putting getting the chart
		AbstractChart<?, ?> prevChart = CHARTS.put(chart.getId(), chart);
		// if previous chart instance is not consistent
		// means that chart is new and then...
		if (prevChart == null) {
			// ...scans all listener to send notification
			for (ChartsLifecycleListener listener : LISTENERS) {
				listener.onBeforeInit(chart);
			}
		}
	}
	
	/**
	 * Fires the notification to all listeners after chart init.
	 * 
	 * @param chart chart instance
	 */
	static void fireAfterInit(AbstractChart<?, ?> chart) {
		// ...scans all listener to send notification
		for (ChartsLifecycleListener listener : LISTENERS) {
			listener.onAfterInit(chart);
		}
	}

	/**
	 * Fires the notification to all listeners before chart destroy.
	 * 
	 * @param chart chart instance
	 */
	static void fireBeforeDestory(AbstractChart<?, ?> chart) {
		// ...scans all listener to send notification
		for (ChartsLifecycleListener listener : LISTENERS) {
			listener.onBeforeDestroy(chart);
		}
	}

	/**
	 * Returns the chart instance by its id.
	 * 
	 * @param chartId chart id
	 * @return chart instance or <code>null</code> if not exist.
	 */
	public static AbstractChart<?, ?> get(String chartId) {
		return CHARTS.get(chartId);
	}

	/**
	 * Removes a chart instance by its id.
	 * 
	 * @param chartId chart id
	 */
	static void remove(String chartId) {
		// removes getting the chart
		AbstractChart<?, ?> chart = CHARTS.remove(chartId);
		// if chart instance is consistent
		if (chart != null) {
			// scans all listener to send notification
			for (ChartsLifecycleListener listener : LISTENERS) {
				listener.onAfterDestroy(chart);
			}
		}
	}

}
