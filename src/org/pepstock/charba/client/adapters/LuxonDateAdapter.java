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

/**
 * LUXON date adapter implementation in order to return an override formats.<br>
 * The LUXON CHART.JS adapter implements the default formats by <b>Intl.DateTimeFormat</b> instead of strings.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class LuxonDateAdapter extends DateAdapter {

	/**
	 * Creates a LUXON date adapter without any options.
	 */
	protected LuxonDateAdapter() {
		super(null);
	}

	/**
	 * Creates a LUXON date adapter using the options passed as argument.<br>
	 * At the moment ONLY LUXON is enabled to using options to act on dates actions.
	 * 
	 * @param options LUXON date adapter options
	 */
	protected LuxonDateAdapter(LuxonOptions options) {
		super(options);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.adapters.DateAdapter#getFormats()
	 */
	@Override
	public DateAdapterFormats getFormats() {
		// returns the constants formats
		return LuxonFormats.get();
	}

}
