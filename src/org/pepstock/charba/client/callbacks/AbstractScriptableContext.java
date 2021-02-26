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
package org.pepstock.charba.client.callbacks;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;
import org.pepstock.charba.client.enums.ContextType;

/**
 * Abstract implementation to map the context used as argument on options, datasets and scales callback.<br>
 * All context implementation have go a property which is a reference to the chart instance.
 * 
 * @author Andrea "Stock" Stocchero
 */
public abstract class AbstractScriptableContext extends NativeObjectContainer{

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		CHART("chart"),
		TYPE("type"),
		OPTIONS("options");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use into native object.
		 * 
		 * @param value value of property name
		 */
		private Property(String value) {
			this.value = value;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.Key#value()
		 */
		@Override
		public String value() {
			return value;
		}
	}

	// instance of native context
	//private final NativeContext context;

	/**
	 * Creates the object with native object instance to be wrapped.
	 * 
	 * @param nativeObject native object instance to be wrapped.
	 */
	protected AbstractScriptableContext(NativeObject nativeObject) {
		super(nativeObject);
		// checks if object is consistent
		if (nativeObject == null) {
			throw new IllegalArgumentException("Native context argument is null");
		}
		// casts the native object to the context
		// this is because the context passed by CHART.JS
		// is using inheritance on prototypes
		// this.context = JsCallbacksHelper.get().wrap(nativeObject);
		// the chart must be there
		if (!has(Property.CHART)) {
			// if not, exception
			throw new IllegalArgumentException("Unable to retrieve the chart instance and the context does not seem to be consistent");
		}
	}

//	/**
//	 * Returns the native context of the scriptable option.
//	 * 
//	 * @return the native context of the scriptable option
//	 */
//	final NativeContext getContext() {
//		return context;
//	}

	/**
	 * Returns the CHARBA chart instance.
	 * 
	 * @return the CHARBA chart instance
	 */
	public final IsChart getChart() {
		return super.getNativeChart(Property.CHART).getChart();
	}

	/**
	 * Returns the type of the context.
	 * 
	 * @return the type of the context
	 */
	public final ContextType getType() {
		return getValue(Property.TYPE, ContextType.values(), ContextType.UNKNOWN);
	}

	/**
	 * Sets the additional options.
	 * 
	 * @param options additional options instance.
	 * @param <T> type of public object container to store
	 */
	public final <T extends NativeObjectContainer> void setOptions(T options) {
		setValue(Property.OPTIONS, options);
	}

	/**
	 * Checks if there is any options.
	 * 
	 * @return <code>true</code> if there is an options, otherwise <code>false</code>.
	 */
	public final boolean hasOptions() {
		return has(Property.OPTIONS);
	}

	/**
	 * Returns the options, if exist. It uses a factory instance to create a public object container.
	 * 
	 * @param factory factory instance to create a public object container.
	 * @param <T> type of public object container to return
	 * @return java script object used to map the options or an empty object if not exist.
	 */
	public final <T extends NativeObjectContainer> T getOptions(NativeObjectContainerFactory<T> factory) {
		// checks if factory is consistent
		if (factory != null) {
			// checks if there is a options
			if (hasOptions()) {
				// creates and returns the object
				return factory.create(getValue(Property.OPTIONS));
			}
			// if here, returns an empty object
			return factory.create();
		}
		// if here, argument is not consistent
		return null;
	}

}