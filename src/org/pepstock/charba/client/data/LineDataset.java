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

import java.util.ArrayList;
import java.util.List;

import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.defaults.IsDefaultOptions;
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
public class LineDataset extends LiningDataset {

	// default value for cubic interpolation mode
	private static final String DEFAULT_CUBIC_INTERPOLATION_MODE = "default";
	// factory to create data points
	private final DataPointListFactory factory = new DataPointListFactory();

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		xAxisID,
		yAxisID,
		cubicInterpolationMode,
		showLines,
		spanGaps,
		steppedLine
	}

	/**
	 * Creates a dataset.<br>
	 * It uses the global options has default.
	 */
	public LineDataset() {
		super();
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
		setValue(Property.xAxisID, xAxisID);
	}

	/**
	 * Returns the ID of the x axis to plot this dataset on.
	 * 
	 * @return the ID of the x axis to plot this dataset on. Default is
	 *         {@link org.pepstock.charba.client.options.Scales#DEFAULT_X_AXIS_ID}
	 */
	public String getXAxisID() {
		return getValue(Property.xAxisID, Scales.DEFAULT_X_AXIS_ID);
	}

	/**
	 * Sets the ID of the y axis to plot this dataset on.
	 * 
	 * @param yAxisID the ID of the y axis to plot this dataset on.
	 */
	public void setYAxisID(String yAxisID) {
		setValue(Property.yAxisID, yAxisID);
	}

	/**
	 * Returns the ID of the y axis to plot this dataset on.
	 * 
	 * @return the ID of the y axis to plot this dataset on. Default is
	 *         {@link org.pepstock.charba.client.options.Scales#DEFAULT_Y_AXIS_ID}
	 */
	public String getYAxisID() {
		return getValue(Property.yAxisID, Scales.DEFAULT_Y_AXIS_ID);
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
	public void setCubicInterpolationMode(String mode) {
		setValue(Property.cubicInterpolationMode, mode);
	}

	/**
	 * Returns algorithm used to interpolate a smooth curve from the discrete data points.
	 * 
	 * @return algorithm used to interpolate a smooth curve from the discrete data points. Default is <code>'default'</code>.
	 */
	public String getCubicInterpolationMode() {
		return getValue(Property.cubicInterpolationMode, DEFAULT_CUBIC_INTERPOLATION_MODE);
	}

	/**
	 * Sets if the line is not drawn for this dataset.
	 * 
	 * @param showLine <code>false</code> if the line is not drawn for this dataset.
	 */
	public void setShowLines(boolean showLine) {
		setValue(Property.showLines, showLine);
	}

	/**
	 * Returns if the line is not drawn for this dataset.
	 * 
	 * @return <code>false</code> if the line is not drawn for this dataset.
	 */
	public boolean isShowLines() {
		return getValue(Property.showLines, getDefaultValues().isShowLines());
	}

	/**
	 * Sets if lines will be drawn between points with no or null data. If false, points with NaN data will create a break in
	 * the line
	 * 
	 * @param spanGaps <code>true</code> if lines will be drawn between points with no or null data. If false, points with NaN
	 *            data will create a break in the line
	 */
	public void setSpanGaps(boolean spanGaps) {
		setValue(Property.spanGaps, spanGaps);
	}

	/**
	 * Returns if lines will be drawn between points with no or null data. If false, points with NaN data will create a break in
	 * the line.
	 * 
	 * @return <code>true</code> if lines will be drawn between points with no or null data. If false, points with NaN data will
	 *         create a break in the line.
	 */
	public boolean isSpanGaps() {
		return getValue(Property.spanGaps, getDefaultValues().isSpanGaps());
	}

	/**
	 * Sets If the line is shown as a stepped line.<br>
	 * If the steppedLine value is set to anything other than false, lineTension will be ignored.
	 * 
	 * @param line If the line is shown as a stepped line.
	 */
	public void setSteppedLine(SteppedLine line) {
		// checks if no stepped line
		if (SteppedLine.nosteppedline.equals(line)) {
			// sets boolean value instead of string one
			setValue(Property.steppedLine, false);
		} else {
			// sets value
			setValue(Property.steppedLine, line);
		}
	}

	/**
	 * Returns If the line is shown as a stepped line.
	 * 
	 * @return If the line is shown as a stepped line.
	 */
	public SteppedLine getSteppedLine() {
		// checks if value of stepped line is a boolean
		if (ObjectType.Boolean.equals(type(Property.steppedLine))) {
			return SteppedLine.nosteppedline;
		} else {
			// otherwise returns the steppedline
			return getValue(Property.steppedLine, SteppedLine.class, SteppedLine.nosteppedline);
		}
	}

	/**
	 * Sets the data property of a dataset for a chart is specified as an array of strings. Each point in the data array
	 * corresponds to the label at the same index on the x axis.
	 * 
	 * @param data an array of strings
	 */
	public void setDataString(String... data) {
		setArrayValue(Dataset.Property.data, ArrayString.of(data));
		// sets data type
		setValue(Dataset.Property._charbaDataType, DataType.strings);
	}

	/**
	 * Sets the data property of a dataset for a chart is specified as an array of strings. Each point in the data array
	 * corresponds to the label at the same index on the x axis.
	 * 
	 * @param data a list of strings
	 */
	public void setDataString(List<String> data) {
		setArrayValue(Dataset.Property.data, ArrayString.of(data));
		// sets data type
		setValue(Dataset.Property._charbaDataType, DataType.strings);
	}

	/**
	 * Returns the data property of a dataset for a chart is specified as an array of strings. Each point in the data array
	 * corresponds to the label at the same index on the x axis.
	 * 
	 * @return a list of strings or an empty list of strings if the data type is not {@link DataType#strings}.
	 */
	public List<String> getDataString() {
		// checks if is a string data type
		if (DataType.strings.equals(getDataType())) {
			// returns strings
			ArrayString array = getArrayValue(Dataset.Property.data);
			return ArrayListHelper.list(array);
		} else {
			// otherwise an empty list
			return new ArrayList<String>();
		}
	}

	/**
	 * Sets the data property of a dataset for a chart is specified as an array of data points.
	 * 
	 * @param datapoints an array of data points
	 */
	public void setDataPoints(DataPoint... datapoints) {
		setArrayValue(Dataset.Property.data, ArrayObject.of(datapoints));
		// sets data type
		setValue(Dataset.Property._charbaDataType, DataType.points);
	}

	/**
	 * Sets the data property of a dataset for a chart is specified as an array of data points.
	 * 
	 * @param datapoints a list of data points
	 */
	public void setDataPoints(List<DataPoint> datapoints) {
		setArrayValue(Dataset.Property.data, ArrayObject.of(datapoints));
		// sets data type
		setValue(Dataset.Property._charbaDataType, DataType.points);
	}

	/**
	 * Returns the data property of a dataset for a chart is specified as an array of data points
	 * 
	 * @return a list of data points or an empty list of data points if the data type is not {@link DataType#points}.
	 */
	public List<DataPoint> getDataPoints() {
		// checks if is a points data type
		if (DataType.points.equals(getDataType())) {
			// returns points
			ArrayObject array = getArrayValue(Dataset.Property.data);
			return ArrayListHelper.list(array, factory);
		} else {
			// otherwise an empty list
			return new ArrayList<DataPoint>();
		}
	}

}