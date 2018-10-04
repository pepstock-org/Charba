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
import org.pepstock.charba.client.jsinterop.configuration.StackedOptions;
import org.pepstock.charba.client.jsinterop.data.StackedBarDataset;
import org.pepstock.charba.client.jsinterop.defaults.DefaultOptions;

/**
 * STACKED BAR chart implementation.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class StackedBarChart extends AbstractChart<StackedOptions, StackedBarDataset> {

	private final StackedOptions options;

	/**
	 * Builds the object.
	 */
	public StackedBarChart() {
		options = new StackedOptions(this, DefaultOptions.get());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Chart#getType()
	 */
	@Override
	public Type getType() {
		return ChartType.bar;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Chart#getOptions()
	 */
	@Override
	public StackedOptions getOptions() {
		return options;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Chart#newDataset()
	 */
	@Override
	public StackedBarDataset newDataset() {
		return new StackedBarDataset();
	}

}