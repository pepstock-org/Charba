/**
    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.
*/
package org.pepstock.charba.client.geo;

import org.pepstock.charba.client.Chart;
import org.pepstock.charba.client.Charts;
import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.callbacks.ColorCallback;
import org.pepstock.charba.client.callbacks.ScaleContext;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.geo.callbacks.InterpolateCallback;
import org.pepstock.charba.client.geo.callbacks.QuantizeCallback;
import org.pepstock.charba.client.geo.enums.Interpolate;
import org.pepstock.charba.client.items.Undefined;

/**
 * The coloring of the nodes will be done with a special color scale.<br>
 * Provides the elements, as colored legend, which can provide the how the values are distributed on map.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
interface IsColorAxis extends IsLegendAxis {

	/**
	 * Returns the color axis mapper.
	 * 
	 * @return the color axis mapper
	 */
	@Override
	ColorAxisMapper getMapper();

	/**
	 * Sets the missing color.
	 * 
	 * @param missingColor the missing color.
	 */
	default void setMissingColor(IsColor missingColor) {
		setMissingColor(IsColor.checkAndGetValue(missingColor));
	}

	/**
	 * Sets the missing color.
	 * 
	 * @param missingColor the missing color.
	 */
	default void setMissingColor(String missingColor) {
		// checks if mapper is consistent
		if (getMapper() != null) {
			// returns value
			getMapper().setMissingColor(missingColor);
		}
	}

	/**
	 * Returns the missing color.
	 * 
	 * @return the missing color.
	 */
	default String getMissingColorAsString() {
		// checks if mapper is consistent
		if (getMapper() != null) {
			// returns value
			return getMapper().getMissingColorAsString();
		}
		// if here, mapper is not consistent
		// then returns default
		return ColorAxis.DEFAULT_MISSING_COLOR;
	}

	/**
	 * Returns the missing color.
	 * 
	 * @return the missing color.
	 */
	default IsColor getMissingColor() {
		return ColorBuilder.parse(getMissingColorAsString());
	}

	/**
	 * Sets the amount of pieces to allow to split the color scale in N quantized equal bins.
	 * 
	 * @param quantize the amount of pieces to allow to split the color scale in N quantized equal bins
	 */
	default void setQuantize(int quantize) {
		// checks if mapper is consistent
		if (getMapper() != null) {
			// returns value
			getMapper().setQuantize(quantize);
		}
	}

	/**
	 * Returns the amount of pieces to allow to split the color scale in N quantized equal bins.
	 * 
	 * @return the amount of pieces to allow to split the color scale in N quantized equal bins
	 */
	default int getQuantize() {
		// checks if mapper is consistent
		if (getMapper() != null) {
			// returns value
			return getMapper().getQuantize();
		}
		// if here, mapper is not consistent
		// then returns default
		return ColorAxis.DEFAULT_QUANTIZE;
	}

	/**
	 * Sets the color interpolation of the scale.
	 * 
	 * @param interpolate the color interpolation of the scale
	 */
	default void setInterpolate(Interpolate interpolate) {
		// checks if mapper is consistent
		if (getMapper() != null) {
			// returns value
			getMapper().setInterpolate(interpolate);
		}
	}

	/**
	 * Returns the color interpolation of the scale.
	 * 
	 * @return the color interpolation of the scale
	 */
	default Interpolate getInterpolate() {
		// checks if mapper is consistent
		if (getMapper() != null) {
			// returns value
			return getMapper().getInterpolate();
		}
		// if here, mapper is not consistent
		// then returns default
		return Interpolate.BLUES;
	}

	/**
	 * Sets the color interpolation callback of the scale.
	 * 
	 * @param interpolateCallback the color interpolation callback of the scale
	 */
	default void setInterpolate(InterpolateCallback interpolateCallback) {
		// checks if mapper is consistent
		if (getMapper() != null) {
			// returns value
			getMapper().setInterpolate(interpolateCallback);
		}
	}

	/**
	 * Returns the color interpolation callback of the scale.
	 * 
	 * @return the color interpolation callback of the scale
	 */
	default InterpolateCallback getInterpolateCallback() {
		// checks if mapper is consistent
		if (getMapper() != null) {
			// returns value
			return getMapper().getInterpolateCallback();
		}
		// if here, mapper is not consistent
		// then returns default
		return null;
	}

	/**
	 * Sets the callback to get the amount of pieces to allow to split the color scale in N quantized equal bins.
	 * 
	 * @param quantizeCallback the callback to get the amount of pieces to allow to split the color scale in N quantized equal bins
	 */
	default void setQuantize(QuantizeCallback quantizeCallback) {
		// checks if mapper is consistent
		if (getMapper() != null) {
			// returns value
			getMapper().setQuantize(quantizeCallback);
		}
	}

	/**
	 * Returns the callback to get the amount of pieces to allow to split the color scale in N quantized equal bins.
	 * 
	 * @return the callback to get the amount of pieces to allow to split the color scale in N quantized equal bins
	 */
	default QuantizeCallback getQuantizeCallback() {
		// checks if mapper is consistent
		if (getMapper() != null) {
			// returns value
			return getMapper().getQuantizeCallback();
		}
		// if here, mapper is not consistent
		// then returns null
		return null;

	}

	/**
	 * Sets the missing color callback.
	 * 
	 * @param missingColorCallback the missing color callback
	 */
	default void setMissingColor(ColorCallback<ScaleContext> missingColorCallback) {
		// checks if mapper is consistent
		if (getMapper() != null) {
			// returns value
			getMapper().setMissingColor(missingColorCallback);
		}
	}

	/**
	 * Returns the missing color callback.
	 * 
	 * @return the missing color callback
	 */
	default ColorCallback<ScaleContext> getMissingColorCallback() {
		// checks if mapper is consistent
		if (getMapper() != null) {
			// returns value
			return getMapper().getMissingColorCallback();
		}
		// if here, mapper is not consistent
		// then returns default
		return null;

	}

	/**
	 * Returns the color for a specific data value.
	 * 
	 * @param value to use for searching
	 * @return a color a string or {@link Undefined#STRING} if not recognized the value by the chart.
	 */
	default String getColorForValueAsString(double value) {
		// checks if mapper is consistent
		if (getMapper() != null && Undefined.isNot(value)) {
			// gets chart
			IsChart chart = getMapper().getAxis().getChart();
			// gets if native object is consistent
			if (Charts.hasNative(chart)) {
				// gets native chart
				Chart nativeChart = Charts.getNative(chart);
				// gets color
				return NativeJsGeoHelper.getColorForValue(nativeChart, value);
			}
		}
		// if here, chart is not consistent
		return Undefined.STRING;
	}

	/**
	 * Returns the color for a specific data value.
	 * 
	 * @param value to use for searching
	 * @return a color or <code>null</code> if not recognized the value by the chart.
	 */
	default IsColor getColorForValue(double value) {
		// gets color as string
		String color = getColorForValueAsString(value);
		// checks if mapper is consistent
		if (color != null) {
			// returns color
			return ColorBuilder.parse(color);
		}
		// if here, chart is not consistent
		return null;
	}

}