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
package org.pepstock.charba.client.options;

import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.configuration.CartesianLinearAxis;
import org.pepstock.charba.client.configuration.CartesianTimeAxis;
import org.pepstock.charba.client.configuration.CartesianTimeSeriesAxis;
import org.pepstock.charba.client.data.LineDataset;
import org.pepstock.charba.client.defaults.IsDefaultDecimation;
import org.pepstock.charba.client.enums.DecimationAlgorithm;
import org.pepstock.charba.client.enums.IndexAxis;

/**
 * The decimation plugin can be used with line charts to automatically decimate data at the start of the chart life cycle.<br>
 * <br>
 * To use the decimation plugin, the following requirements must be met:<br>
 * <ul>
 * <li>the data set must have an {@link IndexAxis#X}
 * <li>the data set must be a {@link LineDataset}
 * <li>the X axis for the data set must be either a {@link CartesianLinearAxis} or {@link CartesianTimeAxis} or {@link CartesianTimeSeriesAxis}
 * </ul>
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Decimation extends AbstractModel<Plugins, IsDefaultDecimation> implements IsDefaultDecimation {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		ENABLED("enabled"),
		ALGORITHM("algorithm"),
		SAMPLES("samples");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use in the native object.
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

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param options options of the chart.
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	Decimation(Plugins options, Key childKey, IsDefaultDecimation defaultValues, NativeObject nativeObject) {
		super(options, childKey, defaultValues, nativeObject);
	}

	/**
	 * Sets <code>true</code> if decimation are enabled.
	 * 
	 * @param enabled <code>true</code> if decimation are enabled.
	 */
	public void setEnabled(boolean enabled) {
		setValueAndAddToParent(Property.ENABLED, enabled);
	}

	/**
	 * Returns <code>true</code> if decimation are enabled.
	 * 
	 * @return <code>true</code> if decimation are enabled.
	 */
	@Override
	public boolean isEnabled() {
		return getValue(Property.ENABLED, getDefaultValues().isEnabled());
	}

	/**
	 * Sets the algorithm used by the plugin.
	 * 
	 * @param algorithm the algorithm used by the plugin
	 */
	public void setAlgorithm(DecimationAlgorithm algorithm) {
		setValueAndAddToParent(Property.ALGORITHM, algorithm);
	}

	/**
	 * Returns the algorithm used by the plugin.
	 * 
	 * @return the algorithm used by the plugin
	 */
	@Override
	public DecimationAlgorithm getAlgorithm() {
		return getValue(Property.ALGORITHM, DecimationAlgorithm.values(), getDefaultValues().getAlgorithm());
	}

	/**
	 * If the {@link DecimationAlgorithm#LTTB} algorithm is used, this is the number of samples in the output data set.<br>
	 * Defaults to the canvas width to pick 1 sample per pixel.
	 * 
	 * @param samples the number of samples in the output data set
	 */
	public void setSamples(double samples) {
		setValueAndAddToParent(Property.SAMPLES, Checker.positiveOrZero(samples));
	}

	/**
	 * If the {@link DecimationAlgorithm#LTTB} algorithm is used, this is the number of samples in the output data set.<br>
	 * Defaults to the canvas width to pick 1 sample per pixel.
	 * 
	 * @return the number of samples in the output data set
	 */
	@Override
	public double getSamples() {
		return getValue(Property.SAMPLES, getDefaultValues().getSamples());
	}
}