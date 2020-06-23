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
package org.pepstock.charba.client.defaults.globals;

import org.pepstock.charba.client.adapters.DateAdapterOptions;
import org.pepstock.charba.client.adapters.DateAdaptersOptionsFactory;
import org.pepstock.charba.client.defaults.IsDefaultAdapters;

/**
 * CHART.JS default values for ADPATERS element.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class DefaultAdapters implements IsDefaultAdapters {

	/**
	 * To avoid any instantiation
	 */
	DefaultAdapters() {
		// do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAdapters#getDate(org.pepstock.charba.client.adapters. DateAdaptersOptionsFactory)
	 */
	@Override
	public <T extends DateAdapterOptions> T getDate(DateAdaptersOptionsFactory<T> factory) {
		// checks if factory is consistent
		if (factory != null) {
			// creates a empty options
			return factory.create();
		}
		// if here factory is not consistent
		return null;
	}

}
