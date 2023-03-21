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

import java.util.List;

import org.pepstock.charba.client.Chart;
import org.pepstock.charba.client.Charts;
import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.callbacks.RadiusCallback;
import org.pepstock.charba.client.callbacks.ScaleContext;
import org.pepstock.charba.client.geo.callbacks.ModeCallback;
import org.pepstock.charba.client.geo.callbacks.RangeCallback;
import org.pepstock.charba.client.geo.enums.Mode;
import org.pepstock.charba.client.items.Undefined;

/**
 * The scale is used to map the values to symbol radius size.<br>
 * Provides the elements, as colored legend, which can provide the how the values are distributed on map.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
interface IsSizeAxis extends IsLegendAxis {

	/**
	 * Returns the size axis mapper.
	 * 
	 * @return the lize axis mapper
	 */
	@Override
	SizeAxisMapper getMapper();

	/**
	 * Sets the radius to render for missing values.
	 * 
	 * @param missingRadius the radius to render for missing values
	 */
	default void setMissingRadius(double missingRadius) {
		// checks if mapper is consistent
		if (getMapper() != null) {
			// returns value
			getMapper().setMissingRadius(missingRadius);
		}
	}

	/**
	 * Returns the radius to render for missing values.
	 * 
	 * @return the radius to render for missing values
	 */
	default double getMissingRadius() {
		// checks if mapper is consistent
		if (getMapper() != null) {
			// returns value
			return getMapper().getMissingRadius();
		}
		// if here, mapper is not consistent
		// then returns default
		return SizeAxis.DEFAULT_MISSING_RADIUS;
	}

	/**
	 * Sets the operation modes for the scale, area means that the area is linearly increasing whereas radius the radius is.
	 * 
	 * @param mode the operation modes for the scale, area means that the area is linearly increasing whereas radius the radius is
	 */
	default void setMode(Mode mode) {
		// checks if mapper is consistent
		if (getMapper() != null) {
			// returns value
			getMapper().setMode(mode);
		}
	}

	/**
	 * Returns the operation modes for the scale, area means that the area is linearly increasing whereas radius the radius is.
	 * 
	 * @return the operation modes for the scale, area means that the area is linearly increasing whereas radius the radius is
	 */
	default Mode getMode() {
		// checks if mapper is consistent
		if (getMapper() != null) {
			// returns value
			return getMapper().getMode();
		}
		// if here, mapper is not consistent
		// then returns default
		return Mode.AREA;
	}

	/**
	 * Sets the radius range in pixel, the minimal data value will be mapped to the first entry, the maximal one to the second and a linear interpolation for all values in between.
	 * 
	 * @param min minimum range in pixel
	 * @param max maximum range in pixel
	 */
	default void setRange(int min, int max) {
		// checks if mapper is consistent
		if (getMapper() != null) {
			// returns value
			getMapper().setRange(min, max);
		}
	}

	/**
	 * Returns the radius range in pixel, the minimal data value will be mapped to the first entry, the maximal one to the second and a linear interpolation for all values in
	 * between.
	 * 
	 * @return the radius range in pixel, the minimal data value will be mapped to the first entry, the maximal one to the second and a linear interpolation for all values in
	 *         between
	 */
	default List<Integer> getRange() {
		// checks if mapper is consistent
		if (getMapper() != null) {
			// returns value
			return getMapper().getRange();
		}
		// if here, mapper is not consistent
		// then returns default
		return SizeAxis.DEFAULT_RANGE;
	}

	/**
	 * Sets the radius to render for missing values.
	 * 
	 * @param missingRadiusCallback the radius to render for missing values
	 */
	default void setMissingRadius(RadiusCallback<ScaleContext> missingRadiusCallback) {
		// checks if mapper is consistent
		if (getMapper() != null) {
			// returns value
			getMapper().setMissingRadius(missingRadiusCallback);
		}
	}

	/**
	 * Returns the radius callback to render for missing values.
	 * 
	 * @return the radius callback to render for missing values
	 */
	default RadiusCallback<ScaleContext> getMissingRadiusCallback() {
		// checks if mapper is consistent
		if (getMapper() != null) {
			// returns value
			return getMapper().getMissingRadiusCallback();
		}
		// if here, mapper is not consistent
		// then returns null
		return null;
	}

	/**
	 * Sets the operation modes for the scale, area means that the area is linearly increasing whereas radius the radius is.
	 * 
	 * @param modeCallback the operation modes callback for the scale, area means that the area is linearly increasing whereas radius the radius is
	 */
	default void setMode(ModeCallback modeCallback) {
		// checks if mapper is consistent
		if (getMapper() != null) {
			// returns value
			getMapper().setMode(modeCallback);
		}
	}

	/**
	 * Returns the operation modes callback for the scale, area means that the area is linearly increasing whereas radius the radius is.
	 * 
	 * @return the operation modes callback for the scale, area means that the area is linearly increasing whereas radius the radius is
	 */
	default ModeCallback getModeCallback() {
		// checks if mapper is consistent
		if (getMapper() != null) {
			// returns value
			return getMapper().getModeCallback();
		}
		// if here, mapper is not consistent
		// then returns null
		return null;
	}

	/**
	 * Sets the radius range callback, the minimal data value will be mapped to the first entry, the maximal one to the second and a linear interpolation for all values in between.
	 * 
	 * @param rangeCallback the radius range callback, the minimal data value will be mapped to the first entry, the maximal one to the second and a linear interpolation for all
	 *            values in between.
	 */
	default void setRange(RangeCallback rangeCallback) {
		// checks if mapper is consistent
		if (getMapper() != null) {
			// returns value
			getMapper().setRange(rangeCallback);
		}
	}

	/**
	 * Returns the radius range callback, the minimal data value will be mapped to the first entry, the maximal one to the second and a linear interpolation for all values in
	 * between.
	 * 
	 * @return the radius range callback, the minimal data value will be mapped to the first entry, the maximal one to the second and a linear interpolation for all values in
	 *         between
	 */
	default RangeCallback getRangeCallback() {
		// checks if mapper is consistent
		if (getMapper() != null) {
			// returns value
			return getMapper().getRangeCallback();
		}
		// if here, mapper is not consistent
		// then returns null
		return null;
	}

	/**
	 * Returns the size for a specific data value.
	 * 
	 * @param value to use for searching
	 * @return the size of the value
	 */
	default double getSizeForValue(double value) {
		// checks if mapper is consistent
		if (getMapper() != null && Undefined.isNot(value)) {
			// gets chart
			IsChart chart = getMapper().getAxis().getChart();
			// gets if native object is consistent
			if (Charts.hasNative(chart)) {
				// gets native chart
				Chart nativeChart = Charts.getNative(chart);
				// gets color
				return NativeJsGeoHelper.getSizeForValue(nativeChart, value);
			}
		}
		// if here, chart is not consistent
		return Undefined.DOUBLE;
	}
}