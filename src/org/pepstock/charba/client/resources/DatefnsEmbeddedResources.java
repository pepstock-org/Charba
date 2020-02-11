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
package org.pepstock.charba.client.resources;

import org.pepstock.charba.client.adapters.DatefnsModule;

/**
 * Client bundle to reference DATE-FNS as date time library (synchronous mode).
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DatefnsEmbeddedResources extends AbstractEmbeddedResources {

	/**
	 * Static reference to DATE-FNS resources.
	 */
	public static final DatefnsEmbeddedResources INSTANCE = new DatefnsEmbeddedResources();

	private final DatefnsAdapterResource dateAdapter = new DatefnsAdapterResource();

	private final DatefnsLibraryResource dateLibrary = new DatefnsLibraryResource();

	/**
	 * To avoid any instantiation
	 */
	private DatefnsEmbeddedResources() {
		super(DatefnsModule.get());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.resources.DateAdpaterResources#datetimeLibrary()
	 */
	@Override
	public InjectableResource datetimeLibrary() {
		return dateLibrary;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.resources.DateAdpaterResources#datetimeAdapter()
	 */
	@Override
	public InjectableResource datetimeAdapter() {
		return dateAdapter;
	}

}