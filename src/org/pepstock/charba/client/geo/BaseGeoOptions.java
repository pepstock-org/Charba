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
package org.pepstock.charba.client.geo;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.Type;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.configuration.ScalesOptions;
import org.pepstock.charba.client.controllers.ControllerMapperFactory;
import org.pepstock.charba.client.controllers.ControllerType;
import org.pepstock.charba.client.defaults.IsDefaultScaledOptions;
import org.pepstock.charba.client.options.ExtendedOptions;

/**
 * Base options for GEO charts.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
abstract class BaseGeoOptions extends ScalesOptions implements HasCommonOptions {

	// common options handler
	private CommonOptionsHandler optionsHandler;
	// options remapped factory
	private final RemappedOptionsFactory factory;

	/**
	 * Builds the object storing the chart instance and defaults.
	 * 
	 * @param chart chart instance
	 * @param defaultValues defaults of chart
	 */
	BaseGeoOptions(IsChart chart, IsDefaultScaledOptions defaultValues) {
		super(chart, defaultValues);
		// gets chart type
		Type type = getChart().getType();
		// checks if is controller
		if (type instanceof ControllerType) {
			// casts to controller type
			ControllerType controller = (ControllerType) type;
			// creates factory
			this.factory = new RemappedOptionsFactory(controller);
		} else {
			// if here, the type is not a controller
			throw new IllegalArgumentException("Chart passed as a rgument is not a GEO controller");
		}
		// gets options
		// initialized objects
		this.afterConfigurationUpdate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.ConfigurationOptions#afterConfigurationUpdate()
	 */
	@Override
	protected final void afterConfigurationUpdate() {
		// gets configuration
		ExtendedOptions options = getConfiguration();
		// gets internal options
		InternalOptionsWrapper wrapper = options.getRemappedOptions(factory);
		// creates and stores options handler
		this.optionsHandler = new CommonOptionsHandler(wrapper.nativeObject());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.geo.HasCommonOptions#getHandler()
	 */
	@Override
	public final CommonOptionsHandler getHandler() {
		return optionsHandler;
	}

	/**
	 * Internal object to map the options of GEO chart and get the {@link NativeObject}.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class InternalOptionsWrapper extends NativeObjectContainer {

		/**
		 * Creates the object with native object instance to be wrapped.
		 * 
		 * @param nativeObject native object instance to be wrapped.
		 */
		protected InternalOptionsWrapper(NativeObject nativeObject) {
			super(nativeObject);
		}

		/**
		 * Returns the native object instance.
		 * 
		 * @return the native object instance.
		 */
		private NativeObject nativeObject() {
			return getNativeObject();
		}

	}

	/**
	 * Can create a options mapper in order to re-map the CHART.JS options where needed in order to add additional properties and nodes for GEO charts.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class RemappedOptionsFactory extends ControllerMapperFactory<InternalOptionsWrapper> {

		/**
		 * Creates the factory of the mapper
		 * 
		 * @param type instance of the controller type where the mapper belongs to
		 */
		RemappedOptionsFactory(ControllerType type) {
			super(type);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.commons.NativeObject)
		 */
		@Override
		public InternalOptionsWrapper create(NativeObject nativeObject) {
			return new InternalOptionsWrapper(nativeObject);
		}

	}

}