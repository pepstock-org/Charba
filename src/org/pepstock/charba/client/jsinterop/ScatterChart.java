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

import org.pepstock.charba.client.ChartType;
import org.pepstock.charba.client.Type;
import org.pepstock.charba.client.jsinterop.configuration.ScatterOptions;
import org.pepstock.charba.client.jsinterop.data.ScatterDataset;

/**
 * SCATTER chart implementation.<br>
 * Scatter charts are based on basic line charts with the x axis changed to a linear axis.<br>
 * To use a scatter chart, data must be passed as objects containing X and Y properties.
 * 
 * @author Andrea "Stock" Stocchero
 * @version 2.0
 *
 */
public class ScatterChart extends AbstractChart<ScatterOptions, ScatterDataset> {

	private final ScatterOptions options;

	/**
	 * Builds the object.
	 */
	public ScatterChart() {
		options = new ScatterOptions(this, Defaults.get().options(getType()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.IsChart#getType()
	 */
	@Override
	public Type getType() {
		return ChartType.scatter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.IsChart#getOptions()
	 */
	@Override
	public ScatterOptions getOptions() {
		return options;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.IsChart#newDataset()
	 */
	@Override
	public ScatterDataset newDataset() {
		return new ScatterDataset();
	}

}