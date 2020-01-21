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
package org.pepstock.charba.client;

import org.pepstock.charba.client.adapters.DateAdapter;
import org.pepstock.charba.client.resources.ResourcesType;

/**
 * This singleton is a wrapper to <code>_adapters</code> object that CHART.JS (by CHART object) provides to get adapters.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class Adapters {

	// singleton instance
	private static final Adapters INSTANCE = new Adapters();
	// native adapters java script object
	private final NativeAdapters nativeAdapters;

	/**
	 * To avoid any instantiation.
	 */
	private Adapters() {
		// to be sure that chart.js has been injected
		Injector.ensureInjected(ResourcesType.getClientBundle().chartJs());
		// to be sure that date time library has been injected
		Injector.ensureInjected(ResourcesType.getClientBundle().datetimeLibrary());
		// to be sure that date time chart.js adapter has been injected
		Injector.ensureInjected(ResourcesType.getClientBundle().datetimeAdapter());
		// gets adapters from CHART.JS
		this.nativeAdapters = Chart.getAdapters();
		// checks if adapters is consistent
		if (this.nativeAdapters == null) {
			// if not, exception
			throw new IllegalArgumentException("CHART.JS adapters is null");
		}
	}

	/**
	 * Singleton method to get static instance.
	 * 
	 * @return adapter instance
	 */
	public static Adapters get() {
		return INSTANCE;
	}

	/**
	 * Returns <code>true</code> if the date time adapters is consistent.
	 * 
	 * @return <code>true</code> if the date time adapters is consistent.
	 */
	public boolean hasDate() {
		// checks if the date adapter instance is consistent
		return nativeAdapters.getDate() != null;
	}

	/**
	 * Checks if the date time adapters is consistent otherwise will throw {@link IllegalArgumentException}.
	 */
	public void checkIfHasDate() {
		// checks if has got date adapter
		if (!hasDate()) {
			// if not, exception
			throw new IllegalArgumentException("CHART.JS date adapters is null");
		}
	}

	/**
	 * Returns the date adapter of CHART.JS.
	 * 
	 * @return the date adapter of CHART.JS
	 */
	public DateAdapter getDate() {
		// returns the date adapter with global options
		// FIXME
		return new DateAdapter();
	}

}
