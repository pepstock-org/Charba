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
package org.pepstock.charba.client.adapters;

import org.pepstock.charba.client.enums.DefaultDateAdapter;
import org.pepstock.charba.client.resources.ResourcesType;

/**
 * Date adapter module for LUXON.<br>
 * <b><a href="https://moment.github.io/luxon/">luxon</a></b> provides a powerful, modern, and friendly wrapper for javascript dates and times.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class LuxonModule extends AbstractModule {
	// singleton instance
	private static final LuxonModule INSTANCE = new LuxonModule();

	/**
	 * To avoid any instantiation
	 */
	private LuxonModule() {
		super(DefaultDateAdapter.LUXON);
	}

	/**
	 * Returns the singleton instance of module.
	 * 
	 * @return the singleton instance of module
	 */
	public static LuxonModule get() {
		return INSTANCE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.adapters.AbstractModule#createDateAdapter(org.pepstock.charba.client.adapters. DateAdapterOptions)
	 */
	@Override
	public DateAdapter createDateAdapter(DateAdapterOptions options) {
		// checks if the module is injected and LUXON one
		if (isInjected() && ResourcesType.equalsTo(INSTANCE)) {
			// checks if the options has been LUXON ones
			if (options instanceof LuxonOptions) {
				// casts to LUXON options
				LuxonOptions luxonOptions = (LuxonOptions) options;
				// creates and returns new LUXON date adapter
				return new LuxonDateAdapter(luxonOptions);
			}
			// creates and returns new LUXON date adapter
			// without options
			return new LuxonDateAdapter();
		}
		// if here, the module injected is not LUXON
		// then exception
		throw new IllegalArgumentException("LUXON module is not injected or different with the current injected: " + ResourcesType.getModuleId());
	}

}
