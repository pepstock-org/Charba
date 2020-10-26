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

import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.resources.DateAdapterInjectionComplete;

/**
 * Every date adapter needs to have a module with some attributes of date adapter itself.<br>
 * It provides a single point of glass on date adapter.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class DateAdapterModule {

	/**
	 * Constants of date adapter identifier, <b>{@value}</b>.
	 */
	public static final String ID = "luxon";
	// singleton instance
	private static final DateAdapterModule INSTANCE = new DateAdapterModule();
	// internal instance to know if the module has been injected
	private boolean injected = false;

	/**
	 * To avoid any instantiation
	 */
	private DateAdapterModule() {
		// do nothing
	}

	/**
	 * Returns the singleton instance of module.
	 * 
	 * @return the singleton instance of module
	 */
	public static DateAdapterModule get() {
		return INSTANCE;
	}

	/**
	 * Returns if the date adapter has been injected.
	 * 
	 * @return <code>false</code>, the date adapter has not been injected
	 */
	public boolean isInjected() {
		return injected;
	}

	/**
	 * Is invoked when the date adapter has been injected.<br>
	 * It can be invoked only by the resources type of the module.
	 * 
	 * @param injectionComplete empty object which can be created only by resources type
	 */
	public void injectionComplete(DateAdapterInjectionComplete injectionComplete) {
		// checks if injection complete is consistent
		if (injectionComplete != null) {
			// sets that it has been injected
			this.injected = true;
			// creates an empty options
			DateAdapterOptions options = new DateAdapterOptions();
			// creates a native date adapter
			NativeDateAdapter nativeAdapter = JsDateAdapterHelper.get().create(options);
			// gets formats
			NativeObject nativeObject = nativeAdapter.formats();
			// checks if formats are consistent
			if (nativeObject != null) {
				// creates an overrider object
				DefaultsFormatsOverrider overrider = new DefaultsFormatsOverrider(nativeObject);
				// overrides
				overrider.override();
			}
		}
	}
}
