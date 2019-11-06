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

import org.pepstock.charba.client.ChartType;
import org.pepstock.charba.client.Type;
import org.pepstock.charba.client.defaults.IsDefaultOptions;

/**
 * The horizontal bar chart allows a number of properties to be specified for each dataset. These are used to set display
 * properties for a specific dataset.<br>
 * Some properties can be specified as an array. If these are set to an array value, the first value applies to the first bar,
 * the second value to the second bar, and so on.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class HorizontalBarDataset extends BarDataset {

	/**
	 * Creates a dataset.<br>
	 * It uses the global options has default.
	 */
	public HorizontalBarDataset() {
		this(ChartType.HORIZONTAL_BAR);
	}

	/**
	 * Creates the dataset using a default.
	 * 
	 * @param defaultValues default options
	 */
	public HorizontalBarDataset(IsDefaultOptions defaultValues) {
		this(ChartType.HORIZONTAL_BAR, defaultValues);
	}

	/**
	 * Creates the dataset using chart type related to the dataset.
	 * 
	 * @param type chart type related to the dataset
	 */
	protected HorizontalBarDataset(Type type) {
		this(type, null);
	}

	/**
	 * Creates the dataset using a default and chart type related to the dataset.
	 * 
	 * @param type chart type related to the dataset
	 * @param defaultValues default options
	 */
	protected HorizontalBarDataset(Type type, IsDefaultOptions defaultValues) {
		super(type, defaultValues);
	}

}