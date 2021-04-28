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
package org.pepstock.charba.client.configuration;

import org.pepstock.charba.client.commons.Checker;

/**
 * Base object to map configuration, by a provider, which can be reuse in all configuration items where this configuration can be set.
 * 
 * @author Andrea "Stock" Stocchero
 * @param <I> type of object of the provider
 */
abstract class AbstractDynamicConfiguration<I> {

	// font provider instance
	private final IsProvider<I> provider;

	/**
	 * Builds the object by a provider used to get the options element for storing properties.
	 * 
	 * @param provider provider used to get the options element for storing properties.
	 */
	AbstractDynamicConfiguration(IsProvider<I> provider) {
		// checks if consistent
		// stores provider
		this.provider = Checker.checkAndGetIfValid(provider, "Options provider");
	}

	/**
	 * Gets the options element instance from provider checking if is consistent.
	 * 
	 * @return the options element instance from provider
	 */
	final I checkAndGet() {
		// checks if consistent
		return Checker.checkAndGetIfValid(provider.getElement(), "Options element");
	}

	/**
	 * Interface to implement to provide the options element to manage configuration properties.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	interface IsProvider<T> {

		/**
		 * Provides the options provider to manage properties.
		 * 
		 * @return the options provider to manage properties.
		 */
		T getElement();

	}
}
