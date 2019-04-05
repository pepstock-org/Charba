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
package org.pepstock.charba.client.data;

import java.util.LinkedList;
import java.util.List;

import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.ArrayStringList;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.defaults.IsDefaultOptions;
import org.pepstock.charba.client.enums.CubicInterpolationMode;
import org.pepstock.charba.client.enums.DataType;
import org.pepstock.charba.client.enums.SteppedLine;
import org.pepstock.charba.client.options.Scales;

/**
 * The line chart allows a number of properties to be specified for each dataset. These are used to set display properties for a
 * specific dataset.<br>
 * All point* properties can be specified as an array. If these are set to an array value, the first value applies to the first
 * point, the second value to the second point, and so on.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class LineDataset extends LiningDataset implements HasDataPoints {

	// factory to create data points
	private final DataPointListFactory factory = new DataPointListFactory();

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		X_AXIS_ID("xAxisID"),
		Y_AXIS_ID("yAxisID"),
		CUBIC_INTERPOLATION_MODE("cubicInterpolationMode"),
		SHOW_LINE("showLine"),
		SPAN_GAPS("spanGaps"),
		STEPPED_LINE("steppedLine");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use into native object.
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
	 * Creates a dataset.<br>
	 * It uses the global options has default.
	 */
	public LineDataset() {
		this(null);
	}

	/**
	 * Creates the dataset using a default.
	 * 
	 * @param defaultValues default options
	 */
	public LineDataset(IsDefaultOptions defaultValues) {
		super(defaultValues);
	}

	/**
	 * Sets the ID of the x axis to plot this dataset on.
	 * 
	 * @param xAxisID the ID of the x axis to plot this dataset on.
	 */
	public void setXAxisID(String xAxisID) {
		setValue(Property.X_AXIS_ID, xAxisID);
	}

	/**
	 * Returns the ID of the x axis to plot this dataset on.
	 * 
	 * @return the ID of the x axis to plot this dataset on. Default is
	 *         {@link org.pepstock.charba.client.options.Scales#DEFAULT_X_AXIS_ID}
	 */
	public String getXAxisID() {
		return getValue(Property.X_AXIS_ID, Scales.DEFAULT_X_AXIS_ID);
	}

	/**
	 * Sets the ID of the y axis to plot this dataset on.
	 * 
	 * @param yAxisID the ID of the y axis to plot this dataset on.
	 */
	public void setYAxisID(String yAxisID) {
		setValue(Property.Y_AXIS_ID, yAxisID);
	}

	/**
	 * Returns the ID of the y axis to plot this dataset on.
	 * 
	 * @return the ID of the y axis to plot this dataset on. Default is
	 *         {@link org.pepstock.charba.client.options.Scales#DEFAULT_Y_AXIS_ID}
	 */
	public String getYAxisID() {
		return getValue(Property.Y_AXIS_ID, Scales.DEFAULT_Y_AXIS_ID);
	}

	/**
	 * Sets algorithm used to interpolate a smooth curve from the discrete data points.<br>
	 * The following interpolation modes are supported:<br>
	 * <br>
	 * 
	 * <pre>
	 * 'default'
	 * 'monotone'
	 * </pre>
	 * 
	 * <br>
	 * The 'default' algorithm uses a custom weighted cubic interpolation, which produces pleasant curves for all types of
	 * datasets.<br>
	 * The 'monotone' algorithm is more suited to y = f(x) datasets : it preserves monotonicity (or piecewise monotonicity) of
	 * the dataset being interpolated, and ensures local extremums (if any) stay at input data points.
	 * 
	 * @param mode algorithm used to interpolate a smooth curve from the discrete data points
	 */
	public void setCubicInterpolationMode(CubicInterpolationMode mode) {
		setValue(Property.CUBIC_INTERPOLATION_MODE, mode);
	}

	/**
	 * Returns algorithm used to interpolate a smooth curve from the discrete data points.
	 * 
	 * @return algorithm used to interpolate a smooth curve from the discrete data points.
	 */
	public CubicInterpolationMode getCubicInterpolationMode() {
		return getValue(Property.CUBIC_INTERPOLATION_MODE, CubicInterpolationMode.class, CubicInterpolationMode.DEFAULT);
	}

	/**
	 * Sets if the line is not drawn for this dataset.
	 * 
	 * @param showLine <code>false</code> if the line is not drawn for this dataset.
	 */
	public void setShowLine(boolean showLine) {
		setValue(Property.SHOW_LINE, showLine);
	}

	/**
	 * Returns if the line is not drawn for this dataset.
	 * 
	 * @return <code>false</code> if the line is not drawn for this dataset.
	 */
	public boolean isShowLine() {
		return getValue(Property.SHOW_LINE, getDefaultValues().isShowLines());
	}

	/**
	 * Sets if lines will be drawn between points with no or null data. If false, points with NaN data will create a break in
	 * the line
	 * 
	 * @param spanGaps <code>true</code> if lines will be drawn between points with no or null data. If false, points with NaN
	 *            data will create a break in the line
	 */
	public void setSpanGaps(boolean spanGaps) {
		setValue(Property.SPAN_GAPS, spanGaps);
	}

	/**
	 * Returns if lines will be drawn between points with no or null data. If false, points with NaN data will create a break in
	 * the line.
	 * 
	 * @return <code>true</code> if lines will be drawn between points with no or null data. If false, points with NaN data will
	 *         create a break in the line.
	 */
	public boolean isSpanGaps() {
		return getValue(Property.SPAN_GAPS, getDefaultValues().isSpanGaps());
	}

	/**
	 * Sets If the line is shown as a stepped line.<br>
	 * If the steppedLine value is set to anything other than false, lineTension will be ignored.
	 * 
	 * @param line If the line is shown as a stepped line. <code>false</code> is no step interpolation
	 */
	public void setSteppedLine(boolean line) {
		// checks if no stepped line
		if (!line) {
			// sets boolean value instead of string one
			setValue(Property.STEPPED_LINE, false);
		} else {
			// sets value before, equals to true
			setValue(Property.STEPPED_LINE, SteppedLine.BEFORE);
		}
	}

	/**
	 * Sets If the line is shown as a stepped line.<br>
	 * If the steppedLine value is set to anything other than false, lineTension will be ignored.
	 * 
	 * @param line If the line is shown as a stepped line.
	 */
	public void setSteppedLine(SteppedLine line) {
		// checks if no stepped line
		if (SteppedLine.FALSE.equals(line)) {
			// sets boolean value instead of string one
			setValue(Property.STEPPED_LINE, false);
		} else {
			// sets value
			setValue(Property.STEPPED_LINE, line);
		}
	}

	/**
	 * Returns If the line is shown as a stepped line.
	 * 
	 * @return If the line is shown as a stepped line.
	 */
	public SteppedLine getSteppedLine() {
		// checks if value of stepped line is a boolean
		if (ObjectType.BOOLEAN.equals(type(Property.STEPPED_LINE))) {
			return SteppedLine.FALSE;
		} else {
			// otherwise returns the steppedline
			return getValue(Property.STEPPED_LINE, SteppedLine.class, SteppedLine.FALSE);
		}
	}

	/**
	 * Sets the data property of a dataset for a chart is specified as an array of strings. Each point in the data array
	 * corresponds to the label at the same index on the x axis.
	 * 
	 * @param data an array of strings
	 */
	public void setDataString(String... data) {
		setArrayValue(Dataset.Property.DATA, ArrayString.fromOrNull(data));
		// sets data type checking if the key exists
		setValue(Dataset.Property.CHARBA_DATA_TYPE, has(Dataset.Property.DATA) ? DataType.STRINGS : DataType.UNKNOWN);
	}

	/**
	 * Sets the data property of a dataset for a chart is specified as an array of strings. Each point in the data array
	 * corresponds to the label at the same index on the x axis.
	 * 
	 * @param data a list of strings
	 */
	public void setDataString(List<String> data) {
		setArrayValue(Dataset.Property.DATA, ArrayString.fromOrNull(data));
		// sets data type checking if the key exists
		setValue(Dataset.Property.CHARBA_DATA_TYPE, has(Dataset.Property.DATA) ? DataType.STRINGS : DataType.UNKNOWN);
	}

	/**
	 * Returns the data property of a dataset for a chart is specified as an array of strings. Each point in the data array
	 * corresponds to the label at the same index on the x axis.
	 * 
	 * @return a list of strings or an empty list of strings if the data type is not {@link DataType#STRINGS}.
	 */
	public List<String> getDataString() {
		return getDataString(false);
	}

	/**
	 * Returns the data property of a dataset for a chart is specified as an array of strings. Each point in the data array
	 * corresponds to the label at the same index on the x axis.
	 * 
	 * @param binding if <code>true</code> binds the new array list into container
	 * @return a list of strings or an empty list of strings if the data type is not {@link DataType#STRINGS}.
	 */
	public List<String> getDataString(boolean binding) {
		// checks if is a string data type
		if (has(Dataset.Property.DATA) && DataType.STRINGS.equals(getDataType())) {
			/// returns strings
			ArrayString array = getArrayValue(Dataset.Property.DATA);
			return ArrayListHelper.list(array);
		}
		// checks if wants to bind the array
		if (binding) {
			ArrayStringList result = new ArrayStringList();
			// set value
			setArrayValue(Dataset.Property.DATA, ArrayString.from(result));
			// sets data type
			setValue(Dataset.Property.CHARBA_DATA_TYPE, DataType.STRINGS);
			// returns list
			return result;
		}
		// returns an empty list
		return new LinkedList<>();
	}

	/**
	 * Sets the data property of a dataset for a chart is specified as an array of data points.
	 * 
	 * @param datapoints an array of data points
	 */
	@Override
	public void setDataPoints(DataPoint... datapoints) {
		setArrayValue(Dataset.Property.DATA, ArrayObject.fromOrNull(datapoints));
		// sets data type checking if the key exists
		setValue(Dataset.Property.CHARBA_DATA_TYPE, has(Dataset.Property.DATA) ? DataType.POINTS : DataType.UNKNOWN);
	}

	/**
	 * Sets the data property of a dataset for a chart is specified as an array of data points.
	 * 
	 * @param datapoints a list of data points
	 */
	@Override
	public void setDataPoints(List<DataPoint> datapoints) {
		setArrayValue(Dataset.Property.DATA, ArrayObject.fromOrNull(datapoints));
		// sets data type checking if the key exists
		setValue(Dataset.Property.CHARBA_DATA_TYPE, has(Dataset.Property.DATA) ? DataType.POINTS : DataType.UNKNOWN);
	}

	/**
	 * Returns the data property of a dataset for a chart is specified as an array of data points
	 * 
	 * @return a list of data points or an empty list of data points if the data type is not {@link DataType#POINTS}.
	 */
	@Override
	public List<DataPoint> getDataPoints() {
		return getDataPoints(false);
	}

	/**
	 * Returns the data property of a dataset for a chart is specified as an array of data points
	 * 
	 * @param binding if <code>true</code> binds the new array list into container
	 * @return a list of data points or an empty list of data points if the data type is not {@link DataType#POINTS}.
	 */
	@Override
	public List<DataPoint> getDataPoints(boolean binding) {
		return getDataPoints(factory, binding);
	}

}