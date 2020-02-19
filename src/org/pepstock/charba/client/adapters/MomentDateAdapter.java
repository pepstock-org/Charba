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

import org.pepstock.charba.client.enums.TimeUnit;

/**
 * MOMENT date adapter implementation in order to return an override formats (needed for week format).
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class MomentDateAdapter extends DateAdapter {

	// WEEK time unit formats
	private static final String WEEK_FORMAT = "w YYYY";

	/**
	 * Creates a MOMENT date adapter without any options.
	 */
	protected MomentDateAdapter() {
		super(null);
	}

	/**
	 * Creates a MOMENT date adapter using the options passed as argument.
	 * 
	 * @param options MOMENT date adapter options
	 */
	protected MomentDateAdapter(DateAdapterOptions options) {
		super(options);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.adapters.DateAdapter#getFormats()
	 */
	@Override
	public DateAdapterFormats getFormats() {
		// gets the default values
		DateAdapterFormats formats = super.getFormats();
		// overrides the defaults
		formats.setFormat(TimeUnit.WEEK, WEEK_FORMAT);
		// returns the constants formats
		return formats;
	}

}
