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
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.HtmlColor;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.configuration.AxisType;
import org.pepstock.charba.client.controllers.ControllerMapperFactory;
import org.pepstock.charba.client.enums.ScaleDataType;
import org.pepstock.charba.client.geo.callbacks.InterpolateCallback;
import org.pepstock.charba.client.geo.enums.Interpolate;
import org.pepstock.charba.client.options.ScaleId;

/**
 * The coloring of the nodes will be done with a special color scale.<br>
 * Provides the elements, as colored legend, which can provide the how the values are distributed on map.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ColorAxis extends LegendAxis {

	/**
	 * Default missing color options, {@link HtmlColor#TRANSPARENT}.
	 */
	public static final String DEFAULT_MISSING_COLOR = HtmlColor.TRANSPARENT.toRGBA();

	/**
	 * Default quantize options, <b>{@value DEFAULT_QUANTIZE}</b>.
	 */
	public static final int DEFAULT_QUANTIZE = 0;

	/**
	 * Projection axis id.
	 */
	public static final ScaleId ID = ScaleId.create("color");
	/**
	 * Projection axis type.
	 */
	public static final AxisType TYPE = AxisType.create("color", null, ID, ScaleDataType.NUMBER);
	// projection mapper factory
	private final ColorAxisRemappedOptionsFactory factory;
	// projection mapper
	private ColorAxisMapper mapper;

	/**
	 * Builds the object storing the chart instance and axis type.
	 * 
	 * @param chart chart instance
	 */
	public ColorAxis(IsChart chart) {
		super(chart, ID, TYPE);
		// chart must be only choropleth
		Checker.assertCheck(ChoroplethChart.CONTROLLER_TYPE.equals(chart.getType()), "Color axis must be used ONLY by choropleth chart");
		// creates the factory
		this.factory = new ColorAxisRemappedOptionsFactory();
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
	ColorAxisMapper getMapper() {
		return mapper;
	}

	/**
	 * Sets the missing color.
	 * 
	 * @param missingColor the missing color.
	 */
	public void setMissingColor(IsColor missingColor) {
		setMissingColor(IsColor.checkAndGetValue(missingColor));
	}

	/**
	 * Sets the missing color.
	 * 
	 * @param missingColor the missing color.
	 */
	public void setMissingColor(String missingColor) {
		mapper.setMissingColor(missingColor);
	}

	/**
	 * Returns the missing color.
	 * 
	 * @return the missing color.
	 */
	public String getMissingColorAsString() {
		return mapper.getMissingColorAsString();
	}

	/**
	 * Returns the missing color.
	 * 
	 * @return the missing color.
	 */
	public IsColor getMissingColor() {
		return ColorBuilder.parse(getMissingColorAsString());
	}

	/**
	 * Sets the amount of pieces to allow to split the color scale in N quantized equal bins.
	 * 
	 * @param quantize the amount of pieces to allow to split the color scale in N quantized equal bins
	 */
	public void setQuantize(int quantize) {
		mapper.setQuantize(quantize);
	}

	/**
	 * Returns the amount of pieces to allow to split the color scale in N quantized equal bins.
	 * 
	 * @return the amount of pieces to allow to split the color scale in N quantized equal bins
	 */
	public int getQuantize() {
		return mapper.getQuantize();
	}

	/**
	 * Sets the color interpolation of the scale.
	 * 
	 * @param interpolate the color interpolation of the scale
	 */
	public void setInterpolate(Interpolate interpolate) {
		mapper.setInterpolate(interpolate);
	}

	/**
	 * Returns the color interpolation of the scale.
	 * 
	 * @return the color interpolation of the scale
	 */
	public Interpolate getInterpolate() {
		return mapper.getInterpolate();
	}

	/**
	 * Sets the color interpolation callback of the scale.
	 * 
	 * @param interpolateCallback the color interpolation callback of the scale
	 */
	public void setInterpolate(InterpolateCallback interpolateCallback) {
		mapper.setInterpolate(interpolateCallback);
	}

	/**
	 * Returns the color interpolation callback of the scale.
	 * 
	 * @return the color interpolation callback of the scale
	 */
	public InterpolateCallback getInterpolateCallback() {
		return mapper.getInterpolateCallback();
	}

	/**
	 * Can create a options mapper in order to re-map the CHART.JS options where needed in order to add additional properties and nodes for GEO charts.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class ColorAxisRemappedOptionsFactory extends ControllerMapperFactory<ColorAxisMapper> {

		/**
		 * Creates the factory of the mapper
		 * 
		 * @param chartType type of GEO chart
		 */
		private ColorAxisRemappedOptionsFactory() {
			super(ChoroplethChart.CONTROLLER_TYPE);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.commons.NativeObject)
		 */
		@Override
		public ColorAxisMapper create(NativeObject nativeObject) {
			return new ColorAxisMapper(nativeObject);
		}

	}
}
