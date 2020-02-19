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

import org.pepstock.charba.client.adapters.MomentModule;

/**
 * Java script resources container to reference MOMENT as date time library (synchronous mode).
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class EmbeddedResources extends AbstractEmbeddedResources {

	/**
	 * Static reference to MOMENT resources.
	 */
	public static final EmbeddedResources INSTANCE = new EmbeddedResources();
	// date adapter java script wrapper
	private final MomentAdapterResource dateAdapter = new MomentAdapterResource();
	// date library java script wrapper
	private final MomentLibraryResource dateLibrary = new MomentLibraryResource();

	/**
	 * To avoid any instantiation
	 */
	private EmbeddedResources() {
		super(MomentModule.get());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.resources.DateAdpaterResources#datetimeLibrary()
	 */
	@Override
	public AbstractInjectableResource datetimeLibrary() {
		return dateLibrary;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.resources.DateAdpaterResources#datetimeAdapter()
	 */
	@Override
	public AbstractInjectableResource datetimeAdapter() {
		return dateAdapter;
	}

}