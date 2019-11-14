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
	private static final Map<String, IsChart> CHARTS_INSTANCES = new HashMap<>();
	// buffer with all CHART.JS charts instances
	// K = CHART id (CHARBA ID)
	// V = CHART.JS chart instance
	private static final Map<String, Chart> NATIVE_CHARTS_INSTANCES = new HashMap<>();
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
		// checks if listener is consistent
		if (listener != null) {
			LISTENERS.add(listener);
		} else {
			// if here, listener is not consistent
			// then exception
			throw new IllegalArgumentException("Listener is null");
		}
	}

	/**
	 * Removes a charts life cycle listener instance from collection.
	 * 
	 * @param listener chart life cycle listener instance
	 */
	public static void removeLifecycleListener(ChartsLifecycleListener listener) {
		// checks if listener is consistent
		// no exception because it is removing it
		if (listener != null) {
			LISTENERS.remove(listener);
		}
	}

	/**
	 * Adds new charts instance into collection.
	 * 
	 * @param chart chart instance
	 */
	static void add(IsChart chart) {
		// checks if chart is consistent
		if (chart != null) {
			// putting getting the chart
			IsChart prevChart = CHARTS_INSTANCES.put(chart.getId(), chart);
			// if previous chart instance is not consistent
			// means that chart is new and then...
			if (prevChart == null) {
				// ...scans all listener to send notification
				for (ChartsLifecycleListener listener : LISTENERS) {
					listener.onBeforeInit(chart);
				}
			}
		}
	}

	/**
	 * Adds new CHART.JS charts instance into collection.
	 * 
	 * @param chart chart instance
	 */
	static void addNative(Chart chart) {
		// checks if chart and its id are consistent
		if (chart != null && chart.getCharbaId() != null) {
			// stores the chart
			NATIVE_CHARTS_INSTANCES.put(chart.getCharbaId(), chart);
		}
	}

	/**
	 * Fires the notification to all listeners after chart init.
	 * 
	 * @param chart chart instance
	 */
	static void fireAfterInit(IsChart chart) {
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
	static void fireBeforeDestory(IsChart chart) {
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
	public static IsChart get(String chartId) {
		// checks if argument is consistent
		if (chartId != null) {
			return CHARTS_INSTANCES.get(chartId);
		}
		// if here chart id is not consistent
		return null;
	}
	
	/**
	 * Returns <code>true</code> if there is a CHART.JS chart instance by id.
	 * 
	 * @param chartId chart id
	 * @return <code>true</code> if there is a CHART.JS chart instance by id
	 */
	public static boolean hasNative(String chartId) {
		// checks if argument is consistent
		if (chartId != null) {
			return NATIVE_CHARTS_INSTANCES.containsKey(chartId);
		}
		// if here chart id is not consistent
		return false;
	}

	/**
	 * Returns the CHART.JS chart instance by id.
	 * 
	 * @param chartId chart id
	 * @return CHART.JS chart instance or <code>null</code> if not exist.
	 */
	public static Chart getNative(String chartId) {
		// checks if argument is consistent
		if (chartId != null) {
			return NATIVE_CHARTS_INSTANCES.get(chartId);
		}
		// if here chart id is not consistent
		return null;
	}

	/**
	 * Removes the chart instance by its id.
	 * 
	 * @param chartId chart id
	 */
	static void remove(String chartId) {
		// removes getting the chart
		IsChart chart = CHARTS_INSTANCES.remove(chartId);
		// if chart instance is consistent
		if (chart != null) {
			// scans all listener to send notification
			for (ChartsLifecycleListener listener : LISTENERS) {
				listener.onAfterDestroy(chart);
			}
		}
		// removes also the native chart
		NATIVE_CHARTS_INSTANCES.remove(chartId);
	}

}
