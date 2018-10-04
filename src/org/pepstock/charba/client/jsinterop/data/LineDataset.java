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
package org.pepstock.charba.client.jsinterop.data;

import java.util.List;

import org.pepstock.charba.client.enums.SteppedLine;
import org.pepstock.charba.client.jsinterop.commons.ArrayListHelper;
import org.pepstock.charba.client.jsinterop.commons.ArrayObject;
import org.pepstock.charba.client.jsinterop.commons.ArrayString;
import org.pepstock.charba.client.jsinterop.commons.Checker;
import org.pepstock.charba.client.jsinterop.commons.Enumer;
import org.pepstock.charba.client.jsinterop.items.UndefinedValues;

/**
 * The line chart allows a number of properties to be specified for each dataset. These are used to set display properties for a specific dataset.<br>
 * All point* properties can be specified as an array. If these are set to an array value, the first value applies to the first point, the second value to the second point, and so on.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class LineDataset extends LiningDataset{

	// default value for cubic interpolation mode
	private static final String DEFAULT_CUBIC_INTERPOLATION_MODE = "default";
	// default value for show line
	private static final boolean DEFAULT_SHOW_LINES = true;
	// default value for span gaps
	private static final boolean DEFAULT_SPAN_GAPS = false;
	
	private final DataPointListFactory factory = new DataPointListFactory();

	/**
	 * Sets the ID of the x axis to plot this dataset on. If not specified, this defaults to the ID of the first found x axis.
	 * @param xAxisID the ID of the x axis to plot this dataset on. If not specified, this defaults to the ID of the first found x axis.
	 */
	public void setXAxisID(String xAxisID){
		getNativeObject().setXAxisID(xAxisID);
	}

	/**
	 * Returns the ID of the x axis to plot this dataset on. If not specified, this defaults to the ID of the first found x axis.
	 * @return the ID of the x axis to plot this dataset on. If not specified, this defaults to the ID of the first found x axis.
	 */
	public String getXAxisID(){
		 return Checker.check(getNativeObject().getXAxisID(), UndefinedValues.STRING);
	}

	/**
	 * Sets the ID of the y axis to plot this dataset on. If not specified, this defaults to the ID of the first found y axis.
	 * @param yAxisID the ID of the y axis to plot this dataset on. If not specified, this defaults to the ID of the first found y axis.
	 */
	public void setYAxisID(String yAxisID){
		getNativeObject().setYAxisID(yAxisID);
	}

	/**
	 * Returns the ID of the y axis to plot this dataset on. If not specified, this defaults to the ID of the first found y axis.
	 * @return the ID of the y axis to plot this dataset on. If not specified, this defaults to the ID of the first found y axis.
	 */
	public String getYAxisID(){
		return Checker.check(getNativeObject().getYAxisID(), UndefinedValues.STRING);
	}

	/**
	 * Sets algorithm used to interpolate a smooth curve from the discrete data points.<br>
	 * The following interpolation modes are supported:<br>
	 * <br>
	 * <pre>
	 * 'default'
	 * 'monotone'
	 * </pre>
	 * <br>
	 * The 'default' algorithm uses a custom weighted cubic interpolation, which produces pleasant curves for all types of datasets.<br>
	 * The 'monotone' algorithm is more suited to y = f(x) datasets : it preserves monotonicity (or piecewise monotonicity) of the dataset being interpolated, and ensures local extremums (if any) stay at input data points.
	 * 
	 * @param mode algorithm used to interpolate a smooth curve from the discrete data points
	 */
	public void setCubicInterpolationMode(String mode){
		getNativeObject().setCubicInterpolationMode(mode);
	}
	
	/**
	 * Returns algorithm used to interpolate a smooth curve from the discrete data points.
	 * @return algorithm used to interpolate a smooth curve from the discrete data points. Default is <code>'default'</code>.
	 */
	public String getCubicInterpolationMode(){
		 return Checker.check(getNativeObject().getCubicInterpolationMode(), DEFAULT_CUBIC_INTERPOLATION_MODE);
	}

	/**
	 * Sets  if the line is not drawn for this dataset.
	 * @param showLine <code>false</code> if the line is not drawn for this dataset.
	 */
	public void setShowLines(boolean showLine){
		getNativeObject().setShowLines(showLine);
	}

	/**
	 * Returns if the line is not drawn for this dataset.
	 * @return <code>false</code> if the line is not drawn for this dataset. Default is <code>true</code>
	 */
	public boolean isShowLines(){
		  return Checker.check(getNativeObject().isShowLines(), DEFAULT_SHOW_LINES);
	}

	/**
	 * Sets if lines will be drawn between points with no or null data. If false, points with NaN data will create a break in the line
	 * @param spanGaps <code>true</code> if lines will be drawn between points with no or null data. If false, points with NaN data will create a break in the line
	 */
	public void setSpanGaps(boolean spanGaps){
		getNativeObject().setSpanGaps(spanGaps);
	}

	/**
	 * Returns if lines will be drawn between points with no or null data. If false, points with NaN data will create a break in the line.
	 * @return <code>true</code> if lines will be drawn between points with no or null data. If false, points with NaN data will create a break in the line. Default is <code>false</code>
	 */
	public boolean isSpanGaps(){
		  return Checker.check(getNativeObject().isSpanGaps(), DEFAULT_SPAN_GAPS);
	}

	/**
	 * Sets If the line is shown as a stepped line.<br>
	 * If the steppedLine value is set to anything other than false, lineTension will be ignored. 
	 * @param line If the line is shown as a stepped line. 
	 * @see org.pepstock.charba.client.enums.SteppedLine
	 */
	public void setSteppedLine(SteppedLine line){
		// checks if no stepped line
		if (SteppedLine.nosteppedline.equals(line)){
			// sets boolean value instead of string one
			getNativeObject().setSteppedLine(false);
		} else {
			// sets value
			getNativeObject().setSteppedLine(line.name());
		}
	}
	
	/**
	 * Returns If the line is shown as a stepped line. 
	 * @return If the line is shown as a stepped line. 
	 * @see org.pepstock.charba.client.enums.SteppedLine
	 */
	public SteppedLine getSteppedLine(){
		// gets value
		Object value = getNativeObject().getSteppedLine();
		if (value != null) {
			String valueAsString = (String) value;
			// if is a boolean FALSE value
			if (valueAsString.equalsIgnoreCase(Boolean.FALSE.toString())){
				// returns no stepped line
				return SteppedLine.nosteppedline;
			}
			return Enumer.deserialize(valueAsString, SteppedLine.class, SteppedLine.nosteppedline);
		}
		// returns this as default
		return SteppedLine.nosteppedline;
	}

	/**
	 * Sets the data property of a dataset for a chart is specified as an array of strings. Each point in the data array corresponds to the label at the same index on the x axis.
	 * @param data an array of strings
	 */
	public void setDataString(String... data){
		getNativeObject().setData(ArrayString.of(data));
	}

	/**
	 * Sets the data property of a dataset for a chart is specified as an array of strings. Each point in the data array corresponds to the label at the same index on the x axis.
	 * @param data a list of strings
	 */
	public void setDataString(List<String> data){
		getNativeObject().setData(ArrayString.of(data));
	}

	/**
	 * Returns the data property of a dataset for a chart is specified as an array of strings. Each point in the data array corresponds to the label at the same index on the x axis.
	 * @return a list of strings
	 */
	public List<String> getDataString(){
		return ArrayListHelper.list((ArrayString)getNativeObject().getData());
	}
	
	/**
	 * Sets the data property of a dataset for a chart is specified as an array of data points.
	 * @param datapoints an array of data points
	 * @see org.pepstock.charba.client.data.DataPoint
	 */
	public void setDataPoints(DataPoint... datapoints){
		getNativeObject().setData(ArrayObject.of(datapoints));
	}
	
	/**
	 * Returns the data property of a dataset for a chart is specified as an array of data points
	 * @return a list of data points
	 * @see org.pepstock.charba.client.data.DataPoint
	 */
	@SuppressWarnings("unchecked")
	public List<DataPoint> getDataPoints(){
		return ArrayListHelper.list((ArrayObject<NativeDataPoint>)getNativeObject().getData(), factory);
	}
}