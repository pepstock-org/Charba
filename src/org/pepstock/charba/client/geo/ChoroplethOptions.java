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
import org.pepstock.charba.client.controllers.ControllerMapperFactory;
import org.pepstock.charba.client.defaults.IsDefaultScaledOptions;

/**
 * Specific options for choropleth chart.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ChoroplethOptions extends GeoOptions {

	// mapper options factory instance
	private final ChoroplethRemappedOptionsFactory factory;
	// common options handler
	private CommonOptionsHandler optionsHandler;
	// mapper options instance
	private ChoroplethOptionsMapper mapper;
	// elements instance
	private ChoroplethElements elements;
	// scales instance
	private ChoroplethScales scales;

	/**
	 * Builds the object storing the chart instance and defaults.
	 * 
	 * @param chart chart instance
	 * @param defaultValues defaults of chart
	 */
	ChoroplethOptions(IsChart chart, IsDefaultScaledOptions defaultValues) {
		super(chart, defaultValues);
		this.factory = new ChoroplethRemappedOptionsFactory(this);
		// creates and stores mapper
		this.mapper = getConfiguration().getRemappedOptions(factory);
		// creates and stores elements
		this.elements = new ChoroplethElements(this);
		// before it requested do not create the scales
		// because it must be wrapped
		this.scales = new ChoroplethScales(this);
		// creates and stores options handler
		this.optionsHandler = new CommonOptionsHandler(mapper);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.geo.HasCommonOptions#getCommonOptionsHandler()
	 */
	@Override
	public CommonOptionsHandler getCommonOptionsHandler() {
		return optionsHandler;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.geo.GeoOptions#getMapper()
	 */
	@Override
	ChoroplethOptionsMapper getMapper() {
		return mapper;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.ConfigurationOptions#getElements()
	 */
	@Override
	public ChoroplethElements getElements() {
		return elements;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.ScalesOptions#getScales()
	 */
	@Override
	public ChoroplethScales getScales() {
		return scales;
	}

	/**
	 * Can create a options mapper in order to re-map the CHART.JS options where needed in order to add additional properties and nodes for GEO charts.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class ChoroplethRemappedOptionsFactory extends ControllerMapperFactory<ChoroplethOptionsMapper> {

		// instance of options where the mapper belongs to
		private final ChoroplethOptions options;

		/**
		 * Creates the factory of the mapper
		 * 
		 * @param options instance of options where the mapper belongs to
		 */
		ChoroplethRemappedOptionsFactory(ChoroplethOptions options) {
			super(ChoroplethChart.CONTROLLER_TYPE);
			// stores the options
			this.options = options;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.commons.NativeObject)
		 */
		@Override
		public ChoroplethOptionsMapper create(NativeObject nativeObject) {
			return new ChoroplethOptionsMapper(options.getConfiguration(), nativeObject);
		}

	}

}