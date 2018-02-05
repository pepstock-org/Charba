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

import java.util.List;

import org.pepstock.charba.client.commons.AbstractList;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.JsObjectContainerArrayList;
import org.pepstock.charba.client.commons.JsStringArrayList;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.SteppedLine;

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
	
	// list of data points
	private final AbstractList<DataPoint> dataPoints = new JsObjectContainerArrayList<DataPoint>();

	/**
	 * Name of fields of JavaScript object. 
	 */
	private enum Property implements Key {
		xAxisID,
		yAxisID,
		cubicInterpolationMode,
		showLines,
		spanGaps,
		steppedLine,
		data
	}
	
	/**
	 * Sets the ID of the x axis to plot this dataset on. If not specified, this defaults to the ID of the first found x axis.
	 * @param xAxisID the ID of the x axis to plot this dataset on. If not specified, this defaults to the ID of the first found x axis.
	 */
	public void setXAxisID(String xAxisID){
		  setValue(Property.xAxisID, xAxisID);
	}

	/**
	 * Returns the ID of the x axis to plot this dataset on. If not specified, this defaults to the ID of the first found x axis.
	 * @return the ID of the x axis to plot this dataset on. If not specified, this defaults to the ID of the first found x axis.
	 */
	public String getXAxisID(){
		  return getValue(Property.xAxisID, (String)null);
	}

	/**
	 * Sets the ID of the y axis to plot this dataset on. If not specified, this defaults to the ID of the first found y axis.
	 * @param yAxisID the ID of the y axis to plot this dataset on. If not specified, this defaults to the ID of the first found y axis.
	 */
	public void setYAxisID(String yAxisID){
		  setValue(Property.yAxisID, yAxisID);
	}

	/**
	 * Returns the ID of the y axis to plot this dataset on. If not specified, this defaults to the ID of the first found y axis.
	 * @return the ID of the y axis to plot this dataset on. If not specified, this defaults to the ID of the first found y axis.
	 */
	public String getYAxisID(){
		  return getValue(Property.yAxisID, (String)null);
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
		  setValue(Property.cubicInterpolationMode, mode);
	}
	
	/**
	 * Returns algorithm used to interpolate a smooth curve from the discrete data points.
	 * @return algorithm used to interpolate a smooth curve from the discrete data points. Default is <code>'default'</code>.
	 */
	public String getCubicInterpolationMode(){
		  return getValue(Property.cubicInterpolationMode, DEFAULT_CUBIC_INTERPOLATION_MODE);
	}

	/**
	 * Sets  if the line is not drawn for this dataset.
	 * @param showLine <code>false</code> if the line is not drawn for this dataset.
	 */
	public void setShowLines(boolean showLine){
		  setValue(Property.showLines, showLine);
	}

	/**
	 * Returns if the line is not drawn for this dataset.
	 * @return <code>false</code> if the line is not drawn for this dataset. Default is <code>true</code>
	 */
	public boolean isShowLines(){
		  return getValue(Property.showLines, DEFAULT_SHOW_LINES);
	}

	/**
	 * Sets if lines will be drawn between points with no or null data. If false, points with NaN data will create a break in the line
	 * @param spanGaps <code>true</code> if lines will be drawn between points with no or null data. If false, points with NaN data will create a break in the line
	 */
	public void setSpanGaps(boolean spanGaps){
		  setValue(Property.spanGaps, spanGaps);
	}

	/**
	 * Returns if lines will be drawn between points with no or null data. If false, points with NaN data will create a break in the line.
	 * @return code>true</code> if lines will be drawn between points with no or null data. If false, points with NaN data will create a break in the line. Default is <code>false</code>
	 */
	public boolean isSpanGaps(){
		  return getValue(Property.spanGaps, DEFAULT_SPAN_GAPS);
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
			setValue(Property.steppedLine, false);
		} else {
			// sets value
			setValue(Property.steppedLine, line.name());
		}
	}
	
	/**
	 * Returns If the line is shown as a stepped line. 
	 * @return If the line is shown as a stepped line. 
	 * @see org.pepstock.charba.client.enums.SteppedLine
	 */
	public SteppedLine getSteppedLine(){
		// gets value
		String value = getValue(Property.steppedLine, SteppedLine.nosteppedline.name());
		// if is a boolean FALSE value
		if (value.equalsIgnoreCase(Boolean.FALSE.toString())){
			// returns no stepped line
			return SteppedLine.nosteppedline;
		}
		// scans all enums
		for (SteppedLine enumValue : SteppedLine.values()){
			// if enum is equals to value
			if (enumValue.name().equalsIgnoreCase(value)){
				// returns enum
				return enumValue;
			}
		}
		// returns this as default
		return SteppedLine.nosteppedline;
	}

	/**
	 * Sets the data property of a dataset for a chart is specified as an array of strings. Each point in the data array corresponds to the label at the same index on the x axis.
	 * @param data an array of strings
	 */
	public void setDataString(String... data){
		setDataString(ArrayListHelper.build(data));
	}

	/**
	 * Sets the data property of a dataset for a chart is specified as an array of strings. Each point in the data array corresponds to the label at the same index on the x axis.
	 * @param values a list of strings
	 */
	public void setDataString(List<String> values){
		// checks if is a internal list, already with JavaScript object 
		if (values instanceof JsStringArrayList){
			// sets directly
			setInternalDataString((JsStringArrayList)values);
		} else {
			// creates a new JavaScript arraylist
			JsStringArrayList list = new JsStringArrayList();
			// adds all values
			list.addAll(values);
			// sets array
			setInternalDataString(list);
		}
	}

	/**
	 * Sets the data property of a dataset for a chart is specified as an array of strings. Each point in the data array corresponds to the label at the same index on the x axis.
	 * @param list a list of strings
	 */
	private void setInternalDataString(JsStringArrayList list){
		setStringArray(Property.data, list);
	}

	/**
	 * Returns the data property of a dataset for a chart is specified as an array of strings. Each point in the data array corresponds to the label at the same index on the x axis.
	 * @return a list of strings
	 */
	public JsStringArrayList getDataString(){
		return getStringArray(Property.data);
	}
	
	/**
	 * Sets the data property of a dataset for a chart is specified as an array of data points.
	 * @param datapoints an array of data points
	 * @see org.pepstock.charba.client.data.DataPoint
	 */
	public void setDataPoints(DataPoint... datapoints){
		setValue(Property.data, ArrayListHelper.load(this.dataPoints, datapoints));
	}
	
	/**
	 * Returns the data property of a dataset for a chart is specified as an array of data points
	 * @return a list of data points
	 * @see org.pepstock.charba.client.data.DataPoint
	 */
	public List<DataPoint> getDataPoints(){
		return dataPoints;
	}
}