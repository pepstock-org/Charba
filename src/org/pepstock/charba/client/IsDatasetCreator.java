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

import org.pepstock.charba.client.data.Dataset;

/**
 * Defines the methods for creating datasets pof a specific type.<br>
 * This is used by chart instances to create datasets relatedo to the chart type.
 * 
 * @author Andrea "Stock" Stocchero
 *
 * @param <D> type of dataset to create
 */
public interface IsDatasetCreator<D extends Dataset> {

	/**
	 * Creates a new dataset related to chart type.
	 * 
	 * @return a new dataset related to chart type.
	 */
	default D newDataset() {
		return newDataset(false);
	}

	/**
	 * Creates a new dataset related to chart type.
	 * 
	 * @param hidden if <code>true</code>, it will be initially hidden.
	 * @return a new dataset related to chart type.
	 */
	D newDataset(boolean hidden);

}
