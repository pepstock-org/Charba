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
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.configuration.Axis;
import org.pepstock.charba.client.controllers.ControllerMapperFactory;
import org.pepstock.charba.client.defaults.IsDefaultScaledOptions;

/**
 * Specific options for bubble map chart.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class BubbleMapOptions extends BaseGeoOptions {

	// mapper options factory instance
	private final BubbleMapRemappedOptionsFactory factory;
	// elements instance
	private final GeoElements elements;
	// common options handler
	private CommonOptionsHandler optionsHandler;
	// mapper options instance
	private BubbleMapOptionsMapper mapper;

	/**
	 * Builds the object storing the chart instance and defaults.
	 * 
	 * @param chart chart instance
	 * @param defaultValues defaults of chart
	 */
	BubbleMapOptions(IsChart chart, IsDefaultScaledOptions defaultValues) {
		super(chart, defaultValues);
		// creates mapper factory
		this.factory = new BubbleMapRemappedOptionsFactory(this);
		// initialized objects
		this.afterConfigurationUpdate();
		// creates and stores elements
		this.elements = new GeoElements(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.ConfigurationOptions#afterConfigurationUpdate()
	 */
	@Override
	protected final void afterConfigurationUpdate() {
		// creates and stores mapper
		this.mapper = getConfiguration().getRemappedOptions(factory);
		// creates and stores options handler
		this.optionsHandler = new CommonOptionsHandler(mapper);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.ConfigurationOptions#afterAxisConfigurationUpdate(org.pepstock.charba.client.configuration.Axis)
	 */
	@Override
	protected void afterAxisConfigurationUpdate(Axis axis) {
		// checks the type of axis
		// checks if projection
		if (axis instanceof ProjectionAxis) {
			// casts to projection axis
			ProjectionAxis pAxis = (ProjectionAxis) axis;
			// resets axis
			pAxis.afterAxisConfigurationUpdate();
		} else if (axis instanceof SizeAxis) {
			// casts to size axis
			SizeAxis sAxis = (SizeAxis) axis;
			// resets axis
			sAxis.afterAxisConfigurationUpdate();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.geo.HasCommonOptions#getCommonOptionsHandler()
	 */
	@Override
	public CommonOptionsHandler getHandler() {
		return optionsHandler;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.geo.GeoOptions#getMapper()
	 */
	@Override
	BubbleMapOptionsMapper getMapper() {
		return mapper;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.ConfigurationOptions#getElements()
	 */
	@Override
	public GeoElements getElements() {
		return elements;
	}

	/**
	 * Can create a options mapper in order to re-map the CHART.JS options where needed in order to add additional properties and nodes for GEO charts.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class BubbleMapRemappedOptionsFactory extends ControllerMapperFactory<BubbleMapOptionsMapper> {

		// instance of options where the mapper belongs to
		private final BubbleMapOptions options;

		/**
		 * Creates the factory of the mapper
		 * 
		 * @param options instance of options where the mapper belongs to
		 */
		BubbleMapRemappedOptionsFactory(BubbleMapOptions options) {
			super(BubbleMapChart.CONTROLLER_TYPE);
			// stores the options
			this.options = options;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.commons.NativeObject)
		 */
		@Override
		public BubbleMapOptionsMapper create(NativeObject nativeObject) {
			return new BubbleMapOptionsMapper(options.getConfiguration(), nativeObject);
		}

	}

}