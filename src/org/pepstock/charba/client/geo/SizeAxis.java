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

import java.util.List;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.configuration.AxisType;
import org.pepstock.charba.client.controllers.ControllerMapperFactory;
import org.pepstock.charba.client.enums.ScaleDataType;
import org.pepstock.charba.client.geo.enums.Mode;
import org.pepstock.charba.client.options.ScaleId;

/**
 * The scale is used to map the values to symbol radius size.<br>
 * Provides the elements, as colored legend, which can provide the how the values are distributed on map.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class SizeAxis extends LegendAxis {

	/**
	 * Default missing radius options, {@value DEFAULT_MISSING_RADIUS}.
	 */
	public static final double DEFAULT_MISSING_RADIUS = 1D;

	/**
	 * Default minimum range options, <b>{@value DEFAULT_MINIMUM_RANGE}</b>.
	 */
	public static final int DEFAULT_MINIMUM_RANGE = 2;

	/**
	 * Default maximum range options, <b>{@value DEFAULT_MAXIMUM_RANGE}</b>.
	 */
	public static final int DEFAULT_MAXIMUM_RANGE = 20;
	
	/**
	 * Size axis id.
	 */
	public static final ScaleId ID = ScaleId.create("r");
	
	/**
	 * Size axis type.
	 */
	public static final AxisType TYPE = AxisType.create("size", null, ID, ScaleDataType.NUMBER);
	// projection mapper factory
	private final SizeAxisRemappedOptionsFactory factory;
	// projection mapper
	private SizeAxisMapper mapper;

	/**
	 * Builds the object storing the chart instance and axis type.
	 * 
	 * @param chart chart instance
	 */
	public SizeAxis(IsChart chart) {
		super(chart, ID, TYPE);
		// chart must be only bubble map
		Checker.assertCheck(BubbleMapChart.CONTROLLER_TYPE.equals(chart.getType()), "Size axis must be used ONLY by bubble map chart");
		// creates the factory
		this.factory = new SizeAxisRemappedOptionsFactory();
		// initializes the mapper
		afterAxisConfigurationUpdate();
	}
	
	/**
	 * Reloads the extended scale
	 */
	void afterAxisConfigurationUpdate() {
		// creates mapper
		this.mapper = getConfiguration().getRemappedOptions(factory);
		// resets legend
		resetLegend();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.geo.LegendAxis#getMapper()
	 */
	@Override
	SizeAxisMapper getMapper() {
		return mapper;
	}

	/**
	 * Sets the radius to render for missing values.
	 * 
	 * @param missingRadius the radius to render for missing values
	 */
	public void setMissingRadius(double missingRadius) {
		mapper.setMissingRadius(missingRadius);
	}

	/**
	 * Returns the radius to render for missing values.
	 * 
	 * @return the radius to render for missing values
	 */
	public double getMissingRadius() {
		return mapper.getMissingRadius();
	}

	/**
	 * Sets the operation modes for the scale, area means that the area is linearly increasing whereas radius the radius is.
	 * 
	 * @param mode the operation modes for the scale, area means that the area is linearly increasing whereas radius the radius is
	 */
	public void setMode(Mode mode) {
		mapper.setMode(mode);
	}

	/**
	 * Returns the operation modes for the scale, area means that the area is linearly increasing whereas radius the radius is.
	 * 
	 * @return the operation modes for the scale, area means that the area is linearly increasing whereas radius the radius is
	 */
	public Mode getMode() {
		return mapper.getMode();
	}

	/**
	 * Sets the radius range in pixel, the minimal data value will be mapped to the first entry, the maximal one to the second and a linear interpolation for all values in between.
	 * 
	 * @param min minimum range in pixel
	 * @param max maximum range in pixel
	 */
	public void setRange(int min, int max) {
		mapper.setRange(min, max);
	}

	/**
	 * Returns the radius range in pixel, the minimal data value will be mapped to the first entry, the maximal one to the second and a linear interpolation for all values in
	 * between.
	 * 
	 * @return the radius range in pixel, the minimal data value will be mapped to the first entry, the maximal one to the second and a linear interpolation for all values in
	 *         between
	 */
	public List<Integer> getRange() {
		return mapper.getRange();
	}

	/**
	 * Can create a options mapper in order to re-map the CHART.JS options where needed in order to add additional properties and nodes for GEO charts.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class SizeAxisRemappedOptionsFactory extends ControllerMapperFactory<SizeAxisMapper> {

		/**
		 * Creates the factory of the mapper
		 */
		private SizeAxisRemappedOptionsFactory() {
			super(BubbleMapChart.CONTROLLER_TYPE);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.commons.NativeObject)
		 */
		@Override
		public SizeAxisMapper create(NativeObject nativeObject) {
			return new SizeAxisMapper(nativeObject);
		}

	}

}
