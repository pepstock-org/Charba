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
import org.pepstock.charba.client.jsinterop.configuration.ConfigurationOptions;
import org.pepstock.charba.client.jsinterop.data.Dataset;

/**
 * Interface which defines a chart.
 * 
 * @author Andrea "Stock" Stocchero
 *
 * @param <O> Options type for the specific chart
 * @param <D> Dataset type for the specific chart
 */
public interface IsChart<O extends ConfigurationOptions, D extends Dataset> {

	/**
	 * Returns the type of chart.
	 * 
	 * @return the type of chart.
	 * @see Type
	 */
	Type getType();

	/**
	 * Returns the options of chart.
	 * 
	 * @return the options of chart.
	 */
	O getOptions();

	/**
	 * Creates a new dataset related to chart type.
	 * 
	 * @return a new dataset related to chart type.
	 */
	D newDataset();

}