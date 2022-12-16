/**
    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.
*/
package org.pepstock.charba.client.annotation;

import java.util.HashMap;
import java.util.Map;

import org.pepstock.charba.client.Charts;
import org.pepstock.charba.client.ChartsLifecycleListener;
import org.pepstock.charba.client.IsChart;

/**
 * Stores and maintains shared object that user can use by {@link AnnotationContext}.
 * 
 * @author Andrea "Stock" Stocchero
 */
final class AnnotationSharedObjects implements ChartsLifecycleListener {

	// singleton instance
	private static final AnnotationSharedObjects INSTANCE = new AnnotationSharedObjects();
	// map with common objects
	// K = chart id, V = map of objects
	private final Map<String, Map<String, Object>> sharedMaps = new HashMap<>();

	/**
	 * To avoid any instantiation
	 */
	private AnnotationSharedObjects() {
		// registers itself as chart life cycle listener
		Charts.addLifecycleListener(this);
	}

	/**
	 * Singleton method to get static instance.
	 * 
	 * @return common instance
	 */
	static AnnotationSharedObjects get() {
		return INSTANCE;
	}

	/**
	 * Returns the shared object instance by chart.<br>
	 * If not exist, it creates new instance.
	 * 
	 * @param chart chart instance
	 * @return the shared object instance by chart.<br>
	 *         If not exist, it creates new instance.
	 */
	Map<String, Object> getSharedObjects(IsChart chart) {
		// checks if chart is consistent
		IsChart.checkIfValid(chart);
		// gets or creates shared object
		return sharedMaps.computeIfAbsent(chart.getId(), mapKey -> new HashMap<>());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.ChartsLifecycleListener#onAfterDestroy(org.pepstock.charba.client.IsChart)
	 */
	@Override
	public void onAfterDestroy(IsChart chart) {
		// checks if chart is consistent
		if (IsChart.isValid(chart) && sharedMaps.containsKey(chart.getId())) {
			// removes the stored objects
			sharedMaps.remove(chart.getId());
		}
	}

}