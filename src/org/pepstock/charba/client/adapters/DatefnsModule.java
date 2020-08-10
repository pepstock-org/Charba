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
import org.pepstock.charba.client.enums.TimeUnit;

/**
 * FIXME https://github.com/stockiNail/chartjs-adapter-date-fns#locale-support-via-scale-options
 * Date adapter module for DATE-FNS.<br>
 * <b><a href="https://date-fns.org/">date-fns</a></b> provides the most comprehensive, yet simple and consistent toolset for manipulating JavaScript dates.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DatefnsModule extends AbstractModule {
	// singleton instance of module
	private static final DatefnsModule INSTANCE = new DatefnsModule();
	// WEEK time unit formats
	private static final String WEEK_FORMAT = "I yyyy";

	/**
	 * To avoid any instantiation
	 */
	private DatefnsModule() {
		super(DefaultDateAdapter.DATE_FNS);
	}

	/**
	 * Returns the singleton instance of module.
	 * 
	 * @return the singleton instance of module
	 */
	public static DatefnsModule get() {
		return INSTANCE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.adapters.AbstractModule#overrideDefaultFormats(org.pepstock.charba.client.adapters.AbstractModule.DefaultsFormatsOverrider)
	 */
	@Override
	public void overrideDefaultFormats(DefaultsFormatsOverrider overrider) {
		// checks if argument is consistent
		if (overrider != null) {
			// overrides the defaults
			overrider.setFormat(TimeUnit.WEEK, WEEK_FORMAT);
		}
	}

}
