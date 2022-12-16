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
package org.pepstock.charba.client.data;

import java.util.List;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.callbacks.ColorCallback;
import org.pepstock.charba.client.callbacks.DatasetContext;
import org.pepstock.charba.client.colors.CanvasObject;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.data.Dataset.CanvasObjectKey;
import org.pepstock.charba.client.items.IsArea;

/**
 * Callback to set a {@link CanvasObject} as background color.<br>
 * This is used and set in the data set when a canvas object is set for a background color.
 * 
 * @author Andrea "Stock" Stocchero
 * @param <T> type of canvas object
 *
 */
abstract class AbstractCanvasObjectCallback<T extends CanvasObject> implements ColorCallback<DatasetContext> {

	// key of the gradient
	private final Key property;
	// canvas object container instance
	private final AbstractContainer<T> container;
	// cache values of canvas objects
	private List<T> objects = null;

	/**
	 * Creates the callback using the container of canvas object and the property related to the canvas object to set in the dataset.
	 * 
	 * @param container container of canvas object instance.
	 * @param property the property related to the canvas object to set in the data set
	 */
	AbstractCanvasObjectCallback(AbstractContainer<T> container, CanvasObjectKey property) {
		this.property = Key.checkAndGetIfValid(property);
		this.container = container;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.callbacks.Scriptable#invoke(org.pepstock.charba.client.callbacks.ChartContext)
	 */
	@Override
	public T invoke(DatasetContext context) {
		// gets chart
		IsChart chart = context.getChart();
		// checks if chart is consistent
		if (IsChart.isValid(chart) && context.getDatasetIndex() >= 0 && IsArea.isConsistent(chart.getNode().getChartArea())) {
			// checks if chart area is consistent
			// retrieves data set
			Dataset dataset = chart.getData().retrieveDataset(context);
			// checks if data set and the canvas object container are consistent
			if (dataset != null && !container.isEmpty() && container.hasObjects(property)) {
				// checks and gets all objects for the key
				// checks if container is changed
				if (objects == null || container.isChanged()) {
					// reloads the objects list
					objects = container.getObjects(property);
				}
				// checks if data index is consistent
				if (context.getDataIndex() >= 0) {
					// get the module for object index
					int gradientIndex = context.getDataIndex() % objects.size();
					// returns the object at modulo.
					return objects.get(gradientIndex);
				}
				// if here, data set index not consistent
				// returns the first object
				return objects.get(0);
			}
		}
		// if here, arguments are not consistent
		// data sets without any canvas objects
		return null;
	}

}