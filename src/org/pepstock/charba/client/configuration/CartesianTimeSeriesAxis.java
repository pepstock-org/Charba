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
package org.pepstock.charba.client.configuration;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.AxisKind;
import org.pepstock.charba.client.enums.AxisType;
import org.pepstock.charba.client.enums.DefaultScaleId;
import org.pepstock.charba.client.options.IsScaleId;

/**
 * This object is used to map defined axis as time series. This is used to have time series charts.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class CartesianTimeSeriesAxis extends CartesianTimeAxis {

	/**
	 * Builds the object storing the chart instance. Axis type is X by default.
	 * 
	 * @param chart chart instance
	 */
	public CartesianTimeSeriesAxis(IsChart chart) {
		// uses X as axis id
		this(chart, AxisType.TIMESERIES.getDefaultScaleId());
	}

	/**
	 * Builds the object storing the chart instance. Axis type is X by default.
	 * 
	 * @param chart chart instance
	 * @param id axis id
	 */
	public CartesianTimeSeriesAxis(IsChart chart, String id) {
		this(chart, IsScaleId.create(id));
	}

	/**
	 * Builds the object storing the chart instance. Axis type is X by default.
	 * 
	 * @param chart chart instance
	 * @param id axis id
	 */
	public CartesianTimeSeriesAxis(IsChart chart, IsScaleId id) {
		this(chart, id, null);
	}

	/**
	 * Builds the object storing the chart instance and axis type.
	 * 
	 * @param chart chart instance
	 * @param kind axis kind
	 */
	public CartesianTimeSeriesAxis(IsChart chart, AxisKind kind) {
		this(chart, DefaultScaleId.getByAxisKind(kind, AxisType.TIMESERIES.getDefaultScaleId()), kind);
	}

	/**
	 * Builds the object storing the chart instance and axis type.
	 * 
	 * @param chart chart instance
	 * @param id axis id
	 * @param kind axis kind
	 */
	public CartesianTimeSeriesAxis(IsChart chart, String id, AxisKind kind) {
		this(chart, IsScaleId.create(id), kind);
	}

	/**
	 * Builds the object storing the chart instance and axis type.
	 * 
	 * @param chart chart instance
	 * @param id axis id
	 * @param kind axis kind
	 */
	public CartesianTimeSeriesAxis(IsChart chart, IsScaleId id, AxisKind kind) {
		super(chart, id, AxisType.TIMESERIES, Key.isValid(kind) ? kind : DefaultScaleId.getAxisKindByScaleId(id, AxisKind.X));
	}
}