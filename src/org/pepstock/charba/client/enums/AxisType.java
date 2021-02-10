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
package org.pepstock.charba.client.enums;

import org.pepstock.charba.client.commons.Key;

/**
 * Axes are an integral part of a chart. They are used to determine how data maps to a pixel value on the chart.<br>
 * These are the possible types of an axis.
 * 
 * @author Andrea "Stock" Stocchero
 */
public enum AxisType implements Key
{
	/**
	 * The linear scale is use to chart numerical data. It can be placed on either the x or y axis.<br>
	 * The linear interpolation is used to determine where a value lies on the axis.
	 */
	LINEAR("linear", DefaultScaleId.Y, ScaleDataType.NUMBER),
	/**
	 * The logarithmic scale is use to chart numerical data. It can be placed on either the x or y axis. <br>
	 * The logarithmic interpolation is used to determine where a value lies on the axis.
	 */
	LOGARITHMIC("logarithmic", DefaultScaleId.Y, ScaleDataType.NUMBER),
	/**
	 * The labels are drawn from one of the label arrays included in the chart data.
	 */
	CATEGORY("category", DefaultScaleId.X, ScaleDataType.STRING),
	/**
	 * The time scale is used to display times and dates. When building its ticks, it will automatically calculate the most comfortable unit base on the size of the scale.
	 */
	TIME("time", DefaultScaleId.X, ScaleDataType.DATE),
	/**
	 * The time series scale is used to display times and dates. When building its ticks, it will automatically calculate the most comfortable unit base on the size of the scale.
	 */
	TIMESERIES("timeseries", DefaultScaleId.X, ScaleDataType.DATE),
	/**
	 * Radial axes are used specifically for the radar and polar area chart types.<br>
	 * These axes overlay the chart area, rather than being positioned on one of the edges.<br>
	 * The linear scale is use to chart numerical data.<br>
	 * The linear interpolation is used to determine where a value lies in relation the center of the axis.
	 */
	RADIAL_LINEAR("radialLinear", DefaultScaleId.R, ScaleDataType.NUMBER);

	// name value of property
	private final String value;

	// default scale id
	private final DefaultScaleId defaultScaleId;

	// data type of scale
	private final ScaleDataType dataType;

	/**
	 * Creates with the property value to use into native object.
	 * 
	 * @param value value of property name
	 * @param defaultScaleId default scale id for this axis type
	 * @param dataType type of data which the scale can manage
	 */
	private AxisType(String value, DefaultScaleId defaultScaleId, ScaleDataType dataType) {
		this.value = value;
		this.defaultScaleId = defaultScaleId;
		this.dataType = dataType;
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

	/**
	 * Returns the default scale id for this axis type.
	 * 
	 * @return the default scale id for this axis type
	 */
	public DefaultScaleId getDefaultScaleId() {
		return defaultScaleId;
	}

	/**
	 * Returns the type of managed data for this axis type.
	 * 
	 * @return the type of managed data for this axis type
	 */
	public ScaleDataType getDataType() {
		return dataType;
	}

}