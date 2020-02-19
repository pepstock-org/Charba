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
 * Date adapter module for MOMENT.<br>
 * <b><a href="https://momentjs.com/">moment</a></b> to parse, validate, manipulate, and display dates and times in JavaScript.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class MomentModule extends AbstractModule {
	// singleton instance
	private static final MomentModule INSTANCE = new MomentModule();

	/**
	 * To avoid any instantiation
	 */
	private MomentModule() {
		super(DefaultDateAdapter.MOMENT);
	}

	/**
	 * Returns the singleton instance of module.
	 * 
	 * @return the singleton instance of module
	 */
	public static MomentModule get() {
		return INSTANCE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.adapters.AbstractModule#createDateAdapter(org.pepstock.charba.client.adapters. DateAdapterOptions)
	 */
	@Override
	public DateAdapter createDateAdapter(DateAdapterOptions options) {
		// checks if the module which has been set is MOMENT
		if (ResourcesType.equalsTo(INSTANCE)) {
			// invokes the standard method to create a date adapter
			return super.createDateAdapter(options);
		}
		// if here, the module injected is not MOMENT
		// then exception
		throw new IllegalArgumentException("MOMENT module is not injected. The current injected adapter is " + ResourcesType.getModuleId());
	}

}
