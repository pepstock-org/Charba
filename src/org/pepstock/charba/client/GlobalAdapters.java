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
import org.pepstock.charba.client.adapters.LuxonOptions;
import org.pepstock.charba.client.adapters.LuxonOptionsFactory;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.defaults.IsDefaultAdapters;
import org.pepstock.charba.client.defaults.IsDefaultScale;
import org.pepstock.charba.client.enums.AxisType;
import org.pepstock.charba.client.enums.DefaultDateAdapter;
import org.pepstock.charba.client.items.UndefinedValues;
import org.pepstock.charba.client.options.Scale;
import org.pepstock.charba.client.resources.ResourcesType;

/**
 * This singleton is a wrapper to <code>_adapters</code> object that CHART.JS (by CHART object) provides to get adapters.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class GlobalAdapters {

	// singleton instance
	private static final GlobalAdapters INSTANCE = new GlobalAdapters();

	/**
	 * Property name to get the adapter ID.
	 */
	public static final Key ID = Key.create("_id");
	// native adapters java script object
	private final NativeAdapters nativeAdapters;
	// date adapter wrapper
	private final DateAdpaterWrapper dateAdapterWrapper;

	/**
	 * To avoid any instantiation.
	 */
	private GlobalAdapters() {
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
		// creates date adapter wrapper
		dateAdapterWrapper = new DateAdpaterWrapper(nativeAdapters.getDate());
	}

	/**
	 * Singleton method to get static instance.
	 * 
	 * @return adapter instance
	 */
	public static GlobalAdapters get() {
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
	 * Returns the id of date adapter or {@link UndefinedValues#STRING} if not exist.
	 * 
	 * @return the id of date adapter or {@link UndefinedValues#STRING} if not exist.
	 */
	public DefaultDateAdapter getDateId() {
		return dateAdapterWrapper.getId();
	}

	/**
	 * Returns the date adapter of CHART.JS.<br>
	 * It uses the global scale default date adapter, if exist.
	 * 
	 * @return the date adapter of CHART.JS
	 */
	public DateAdapter getDate() {
		return createDateAdapter(Defaults.get().getScale(AxisType.TIME).getAdapters());
	}

	/**
	 * Returns the date adapter of CHART.JS.<br>
	 * It uses the scale, passed as argument, to retrieve the stored options, if exist.
	 * 
	 * @param scale the scale to use to get the adapter options.
	 * @return the date adapter of CHART.JS
	 */
	public DateAdapter getDate(Scale scale) {
		// checks if scale is consistent and is a time axis
		if (scale != null && isCategoryTimeAxis(scale)) {
			return createDateAdapter(scale.getAdapters());
		}
		// if here, the scale is not consistent
		// then means you can not use any time axis
		// and returns an adapter without options
		return new DateAdapter();
	}

	/**
	 * Returns <code>true</code> if the passed scale is a cartesian time axis.
	 * 
	 * @param scale scael instance to check
	 * @return <code>true</code> if the passed scale is a cartesian time axis
	 */
	private boolean isCategoryTimeAxis(IsDefaultScale scale) {
		return scale != null && AxisType.TIME.equals(scale.getType());
	}

	/**
	 * Creates a date adapter retrieving the date adapter options if exist.
	 * 
	 * @param adapters options adapters instance
	 * @return a date adapter
	 */
	private DateAdapter createDateAdapter(IsDefaultAdapters adapters) {
		// checks if the date adapter is LUXON one
		// LUXON is the only one which can use the options (at the moment)
		if (adapters != null && DefaultDateAdapter.LUXON.equals(getDateId())) {
			LuxonOptions options = adapters.getDate(LuxonOptionsFactory.get());
			// creates and returns the adapter
			return new DateAdapter(options);
		}
		// if here, there is not options to read
		return new DateAdapter();
	}

	/**
	 * Inner class to wrap the date adapter object in order to get the id.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class DateAdpaterWrapper extends NativeObjectContainer {

		/**
		 * Creates the object with native object instance to be wrapped.
		 * 
		 * @param nativeObject native object instance to be wrapped.
		 */
		DateAdpaterWrapper(NativeObject nativeObject) {
			super(nativeObject);
		}

		/**
		 * Returns the id of date adapter or {@link UndefinedValues#STRING} if not exist.
		 * 
		 * @return the id of date adapter or {@link UndefinedValues#STRING} if not exist.
		 */
		DefaultDateAdapter getId() {
			return Key.getKeyByValue(DefaultDateAdapter.class, getValue(ID, UndefinedValues.STRING), DefaultDateAdapter.UNKNOWN);
		}

	}

}
