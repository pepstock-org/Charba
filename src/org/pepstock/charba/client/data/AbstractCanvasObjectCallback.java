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
package org.pepstock.charba.client.data;

import java.util.Collections;
import java.util.List;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.callbacks.BackgroundColorCallback;
import org.pepstock.charba.client.callbacks.ScriptableContext;
import org.pepstock.charba.client.colors.CanvasObject;
import org.pepstock.charba.client.commons.Key;

/**
 * FIXME
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
abstract class AbstractCanvasObjectCallback<T extends CanvasObject> implements BackgroundColorCallback {

	// key of the gradient
	private final Key property;
	// canvas object container instance
	private final AbstractContainer<T> container;
	// cache values of objects
	private List<T> objects = Collections.emptyList();
	// flag for first execution
	private boolean firstExecution = true;

	/**
	 * FIXME
	 * @param property
	 */
	AbstractCanvasObjectCallback(AbstractContainer<T> container, Key property) {
		this.property = Key.checkAndGetIfValid(property);
		this.container = container;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.callbacks.Scriptable#invoke(org.pepstock.charba.client.IsChart, org.pepstock.charba.client.callbacks.ScriptableContext)
	 */
	@Override
	public final T invoke(IsChart chart, ScriptableContext context) {
		// checks if chart is consistent
		if (IsChart.isValid(chart) && context.getDatasetIndex() >= 0 && chart.getNode().getChartArea().isConsistent()) {
			// checks if chart area is consistent
			// retrieves dataset
			Dataset dataset = chart.getData().retrieveDataset(context);
			// checks if dataset and the canvas object container are consistent
			if (dataset != null && !container.isEmpty() && container.hasObjects(property)) {
				// checks and gets all objects for the key
				checkAndSetObjects();
				// checks if data index is consistent
				if (context.getDataIndex() >= 0) {
					// get the module for object index
					int gradientIndex = context.getDataIndex() % objects.size();
					// returns the object at modulo.
					return objects.get(gradientIndex);
				}
				// if here, dataset index not consistent
				// returns the first object
				return objects.get(0);
			}
		}
		return null;
	}
	
	private void checkAndSetObjects() {
		//checks if container is changed
		if (container.isChanged() || firstExecution) {
			objects = container.getObjects(property);
			// sets first execution flag
			firstExecution = false;
		}
	}

}
