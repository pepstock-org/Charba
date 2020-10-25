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
package org.pepstock.charba.client.defaults.chart;

import org.pepstock.charba.client.defaults.IsDefaultAdapters;
import org.pepstock.charba.client.defaults.IsDefaultDateAdapterOptions;
import org.pepstock.charba.client.options.Adapters;

/**
 * Defaults for adapters option element, based on chart type.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultChartAdapters implements IsDefaultAdapters {

	private final DefaultChartDateAdapterOptions dateAdapterOptions;

	/**
	 * Creates the object by adapter option element instance.
	 * 
	 * @param adapters adapters option element instance.
	 */
	DefaultChartAdapters(Adapters adapters) {
		// gets and stores the date adapter options
		this.dateAdapterOptions = new DefaultChartDateAdapterOptions(adapters.getDate());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAdapters#getDate()
	 */
	@Override
	public IsDefaultDateAdapterOptions getDate() {
		return dateAdapterOptions;
	}

}
