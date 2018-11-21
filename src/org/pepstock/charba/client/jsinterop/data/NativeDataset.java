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

import org.pepstock.charba.client.jsinterop.commons.ArrayInteger;
import org.pepstock.charba.client.jsinterop.commons.NativeName;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * The chart allows a number of properties to be specified for each dataset. These are used to set display properties for a specific dataset.<br>
 * This is the base implementation for all datasets with common fields.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, name = NativeName.OBJECT, namespace = JsPackage.GLOBAL)
final class NativeDataset extends NativeObject{
	
	/**
	 * Sets if the dataset will appear or not.
	 * @param hidden if the dataset will appear or not.
	 */
	@JsProperty
	native void setHidden(boolean hidden);

	/**
	 * Returns if the dataset will appear or not.
	 * @return if the dataset will appear or not. Default is <code>false</code>
	 */
	@JsProperty
	native boolean isHidden();

	/**
	 * Sets the label for the dataset which appears in the legend and tooltips.
	 * @param label the label for the dataset which appears in the legend and tooltips.
	 */
	@JsProperty
	native void setLabel(String label);

	/**
	 * Returns the label for the dataset which appears in the legend and tooltips.
	 * @return the label for the dataset which appears in the legend and tooltips.
	 */
	@JsProperty
	native String getLabel();

	/**
	 * Sets the data property of a dataset for a chart is specified as an array of numbers. Each point in the data array corresponds to the label at the same index on the x axis.
	 * @param values an array of numbers
	 */
	@JsProperty
	native void setData(Object values);
	
	/**
	 * Returns the data property of a dataset for a chart is specified as an array of numbers. Each point in the data array corresponds to the label at the same index on the x axis.
	 * @return list of numbers.
	 * @see org.pepstock.charba.client.commons.JsDoubleArrayList
	 */
	@JsProperty
	native Object getData();

	/**
	 * Sets the type of dataset based on type of chart.
	 * @param type type of dataset.
	 */
	@JsProperty
	native void setType(String type);

	/**
	 * Returns the type of dataset, based on type of chart.
	 * @return type of dataset or null if not set.
	 */
	@JsProperty
	native String getType();
	
	/**
	 * Sets the name of stack group.
	 * @param stackGroup name of stack group.
	 */
	@JsProperty
	native void setStack(String label);

	/**
	 * Returns the name of stack group. 
	 * @return the name of stack group.
	 */
	@JsProperty
	native String getStack();

	/**
	 * Sets the ID of the x axis to plot this dataset on. If not specified, this defaults to the ID of the first found x axis.
	 * @param xAxisID the ID of the x axis to plot this dataset on. If not specified, this defaults to the ID of the first found x axis.
	 */
	@JsProperty(name = "xAxisID")
	native void setXAxisID(String xAxisID);

	/**
	 * Returns the ID of the x axis to plot this dataset on. If not specified, this defaults to the ID of the first found x axis.
	 * @return the ID of the x axis to plot this dataset on. If not specified, this defaults to the ID of the first found x axis.
	 */
	@JsProperty(name = "xAxisID")
	native String getXAxisID();

	/**
	 * Sets the ID of the y axis to plot this dataset on. If not specified, this defaults to the ID of the first found y axis.
	 * @param yAxisID the ID of the y axis to plot this dataset on. If not specified, this defaults to the ID of the first found y axis.
	 */
	@JsProperty(name = "yAxisID")
	native void setYAxisID(String yAxisID);

	/**
	 * Returns the ID of the y axis to plot this dataset on. If not specified, this defaults to the ID of the first found y axis.
	 * @return the ID of the y axis to plot this dataset on. If not specified, this defaults to the ID of the first found y axis.
	 */
	@JsProperty(name = "yAxisID")
	native String getYAxisID();
	

	/**
	 * Sets the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines
	 * and gaps which describe the pattern.
	 * 
	 * @param borderDash the line dash pattern used when stroking lines, using an array of values which specify alternating
	 *            lengths of lines and gaps which describe the pattern.
	 */
	@JsProperty
	native void setBorderDash(ArrayInteger borderWidth);

	/**
	 * Returns the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of
	 * lines and gaps which describe the pattern.
	 * 
	 * @return the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of
	 *         lines and gaps which describe the pattern.
	 */
	@JsProperty
	native ArrayInteger getBorderDash();

	/**
	 * Sets the line dash pattern offset or "phase".
	 * 
	 * @param borderDashOffset the line dash pattern offset or "phase".
	 */
	@JsProperty
	native void setBorderDashOffset(int borderDashOffset);

	/**
	 * Returns the line dash pattern offset or "phase".
	 * 
	 * @return the line dash pattern offset or "phase". Default is <code>0</code>
	 */
	@JsProperty
	native int getBorderDashOffset();

	/**
	 * Sets how the end points of every line are drawn. There are three possible values for this property and those are: butt,
	 * round and square.
	 * 
	 * @param borderString how the end points of every line are drawn.
	 * @see org.pepstock.charba.client.enums.CapStyle
	 */
	@JsProperty
	native void setBorderCapStyle(String borderCapStyle);

	/**
	 * Returns how the end points of every line are drawn. There are three possible values for this property and those are:
	 * butt, round and square. By default this property is set to butt.
	 * 
	 * @return how the end points of every line are drawn.
	 * @see org.pepstock.charba.client.enums.CapStyle
	 */
	@JsProperty
	native String getBorderCapStyle();

	/**
	 * Sets how two connecting segments (of lines, arcs or curves) with non-zero lengths in a shape are joined together
	 * (degenerate segments with zero lengths, whose specified endpoints and control points are exactly at the same position,
	 * are skipped).<br>
	 * There are three possible values for this property: round, bevel and miter. By default this property is set to miter.
	 * 
	 * @param borderString There are three possible values for this property: round, bevel and miter. By default this property
	 *            is set to miter.
	 * @see org.pepstock.charba.client.enums.JoinStyle
	 */
	@JsProperty
	native void setBorderJoinStyle(String borderJoinStyle);

	/**
	 * Returns how two connecting segments (of lines, arcs or curves) with non-zero lengths in a shape are joined together
	 * (degenerate segments with zero lengths, whose specified endpoints and control points are exactly at the same position,
	 * are skipped).<br>
	 * There are three possible values for this property: round, bevel and miter. By default this property is set to miter.
	 * 
	 * @return There are three possible values for this property: round, bevel and miter. By default this property is set to
	 *         miter.
	 * @see org.pepstock.charba.client.enums.JoinStyle
	 */
	@JsProperty
	native String getBorderJoinStyle();

	/**
	 * Sets how to fill the area under the line.
	 * 
	 * @param fill how to fill the area under the line.
	 * @see org.pepstock.charba.client.enums.Fill
	 */
	@JsProperty
	native void setFill(Object fill);

	/**
	 * Returns how to fill the area under the line.
	 * 
	 * @return how to fill the area under the line.
	 * @see org.pepstock.charba.client.enums.Fill
	 */
	@JsProperty
	native Object getFill();

	/**
	 * Sets curve tension of the line. Set to 0 to draw straight lines. This option is ignored if monotone cubic interpolation
	 * is used.
	 * 
	 * @param lineTension curve tension of the line
	 */
	@JsProperty
	native void setLineTension(double lineTension);

	/**
	 * Returns curve tension of the line. Set to 0 to draw straight lines. This option is ignored if monotone cubic
	 * interpolation is used.
	 * 
	 * @return curve tension of the line. Default is <code>0.4</code>
	 */
	@JsProperty
	native double getLineTension();

	/**
	 * Sets the fill color for points.
	 * 
	 * @param pointBackgroundColor array of the fill color for points.
	 */
	@JsProperty
	native void setPointBackgroundColor(Object pointBackgroundColor);

	/**
	 * Returns the fill color for points.
	 * 
	 * @return list of the fill color for points.
	 */
	@JsProperty
	native Object getPointBackgroundColor();

	/**
	 * Sets the border color for points.
	 * 
	 * @param pointBorderColor array of the border color for points.
	 */
	@JsProperty
	native void setPointBorderColor(Object pointBorderColor);

	/**
	 * Returns the border color for points.
	 * 
	 * @return list of the border color for points.
	 */
	@JsProperty
	native Object getPointBorderColor();

	/**
	 * Sets the width of the point border in pixels.
	 * 
	 * @param pointBorderWidth array of the width of the point border in pixels.
	 */
	@JsProperty
	native void setPointBorderWidth(Object pointBorderWidth);

	/**
	 * Returns the width of the point border in pixels.
	 * 
	 * @return list of the width of the point border in pixels.
	 */
	@JsProperty
	native Object getPointBorderWidth();

	/**
	 * Sets the pixel size of the non-displayed point that reacts to mouse events.
	 * 
	 * @param pointHitRadius array of the pixel size of the non-displayed point.
	 */
	@JsProperty
	native void setPointHitRadius(Object pointHitRadius);

	/**
	 * Returns the pixel size of the non-displayed point that reacts to mouse events.
	 * 
	 * @return list of the pixel size of the non-displayed point.
	 */
	@JsProperty
	native Object getPointHitRadius();

	/**
	 * Sets the point background color when hovered.
	 * 
	 * @param pointHoverBackgroundColor array of the point background color when hovered.
	 */
	@JsProperty
	native void setPointHoverBackgroundColor(Object pointHoverBackgroundColor);

	/**
	 * Returns the point background color when hovered.
	 * 
	 * @return list of the point background color when hovered.
	 */
	@JsProperty
	native Object getPointHoverBackgroundColor();

	/**
	 * Sets the point border color when hovered.
	 * 
	 * @param pointHoverBorderColor array of the point border color when hovered.
	 */
	@JsProperty
	native void setPointHoverBorderColor(Object pointHoverBorderColor);

	/**
	 * Returns the point border color when hovered.
	 * 
	 * @return list of the point border color when hovered.
	 */
	@JsProperty
	native Object getPointHoverBorderColor();

	/**
	 * Sets the border width of point when hovered.
	 * 
	 * @param pointHoverBorderWidth array of the border width of point when hovered.
	 */
	@JsProperty
	native void setPointHoverBorderWidth(Object pointHoverBorderWidth);

	/**
	 * Returns the border width of point when hovered.
	 * 
	 * @return list of the border width of point when hovered.
	 */
	@JsProperty
	native Object getPointHoverBorderWidth();

	/**
	 * Sets the radius of the point when hovered.
	 * 
	 * @param pointHoverRadius array of the radius of the point when hovered.
	 */
	@JsProperty
	native void setPointHoverRadius(Object pointHoverRadius);

	/**
	 * Returns the radius of the point when hovered.
	 * 
	 * @return list of the radius of the point when hovered.
	 */
	@JsProperty
	native Object getPointHoverRadius();

	/**
	 * Sets the radius of the point shape. If set to 0, the point is not rendered.
	 * 
	 * @param pointRadius array of the radius of the point shape.
	 */
	@JsProperty
	native void setPointRadius(Object pointRadius);

	/**
	 * Returns the radius of the point shape.
	 * 
	 * @return list of the radius of the point shape.
	 */
	@JsProperty
	native Object getPointRadius();

	/**
	 * Sets the style of the point.
	 * 
	 * @param pointStyle array of the style of the point.
	 * @see org.pepstock.charba.client.enums.PointStyle
	 */
	@JsProperty
	native void setPointStyle(Object pointStyle);

	/**
	 * Returns the style of the point.
	 * 
	 * @return list of the style of the point. Default is <code>PointStyle.circle</code>
	 * @see org.pepstock.charba.client.enums.PointStyle
	 */
	@JsProperty
	native Object getPointStyle();
	
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
	@JsProperty
	native void setCubicInterpolationMode(String mode);
	
	/**
	 * Returns algorithm used to interpolate a smooth curve from the discrete data points.
	 * @return algorithm used to interpolate a smooth curve from the discrete data points. Default is <code>'default'</code>.
	 */
	@JsProperty
	native String getCubicInterpolationMode();

	/**
	 * Sets  if the line is not drawn for this dataset.
	 * @param showLine <code>false</code> if the line is not drawn for this dataset.
	 */
	@JsProperty
	native void setShowLines(boolean showLine);
	
	/**
	 * Returns if the line is not drawn for this dataset.
	 * @return <code>false</code> if the line is not drawn for this dataset. Default is <code>true</code>
	 */
	@JsProperty
	native boolean isShowLines();

	/**
	 * Sets if lines will be drawn between points with no or null data. If false, points with NaN data will create a break in the line
	 * @param spanGaps <code>true</code> if lines will be drawn between points with no or null data. If false, points with NaN data will create a break in the line
	 */
	@JsProperty
	native void setSpanGaps(boolean spanGaps);

	/**
	 * Returns if lines will be drawn between points with no or null data. If false, points with NaN data will create a break in the line.
	 * @return <code>true</code> if lines will be drawn between points with no or null data. If false, points with NaN data will create a break in the line. Default is <code>false</code>
	 */
	@JsProperty
	native boolean isSpanGaps();

	/**
	 * Sets If the line is shown as a stepped line.<br>
	 * If the steppedLine value is set to anything other than false, lineTension will be ignored. 
	 * @param line If the line is shown as a stepped line. 
	 * @see org.pepstock.charba.client.enums.SteppedLine
	 */
	@JsProperty
	native void setSteppedLine(Object line);
	
	/**
	 * Returns If the line is shown as a stepped line. 
	 * @return If the line is shown as a stepped line. 
	 * @see org.pepstock.charba.client.enums.SteppedLine
	 */
	@JsProperty
	native Object getSteppedLine();
	
	/**
	 * Sets the edge to skip drawing the border for.
	 * @param position the edge to skip drawing the border for.
	 */
	@JsProperty
	native void setBorderSkipped(String position);
	
	/**
	 * Returns the edge to skip drawing the border for.
	 * @return the edge to skip drawing the border for.
	 */
	@JsProperty
	native String getBorderSkipped();
	
	/**
	 * Sets the pixel size of the non-displayed point that reacts to mouse events.
	 * @param hitRadius array of the pixel size of the non-displayed point.
	 */
	@JsProperty
	native void setHitRadius(Object hitRadius);

	/**
	 * Returns the pixel size of the non-displayed point that reacts to mouse events.
	 * @return list of the pixel size of the non-displayed point.
	 */
	@JsProperty
	native Object getHitRadius();
	
	/**
	 * Sets the radius of the point when hovered.
	 * @param hoverRadius array of the radius of the point when hovered.
	 */
	@JsProperty
	native void setHoverRadius(Object hoverRadius);

	/**
	 * Returns the radius of the point when hovered.
	 * @return list of the radius of the point when hovered.
	 */
	@JsProperty
	native Object getHoverRadius();

	/**
	 * Sets the radius of the point shape. If set to 0, the point is not rendered.
	 * @param radius array of the radius of the point shape.
	 */
	@JsProperty
	native void setRadius(Object radius);

	/**
	 * Returns the radius of the point shape.
	 * @return list of the radius of the point shape.
	 */
	@JsProperty
	native Object getRadius();
	
	/**
	 * Sets the fill color of the arcs in the dataset.
	 * @param backgroundColor the fill color of the arcs in the dataset.
	 */
	@JsProperty
	native void setBackgroundColor(Object backgroundColor);

	/**
	 * Returns the fill color of the arcs in the dataset as string.
	 * @return list of the fill color of the arcs in the dataset as string.
	 */
	@JsProperty
	native Object getBackgroundColor();

	/**
	 * Sets the border color of the arcs in the dataset as string. 
	 * @param borderColor the border color of the arcs in the dataset as string. 
	 */
	@JsProperty
	native void setBorderColor(Object borderColor);

	/**
	 * Returns the border color of the arcs in the dataset as string. 
	 * @return list of the border color of the arcs in the dataset as string. 
	 */
	@JsProperty
	native Object getBorderColor();

	/**
	 * Sets the border width of the arcs in the dataset.
	 * @param borderWidth the border width of the arcs in the dataset.
	 */
	@JsProperty
	native void setBorderWidth(Object borderWidth);

	/**
	 * Returns the border width of the arcs in the dataset.
	 * @return list of the border width of the arcs in the dataset.
	 */
	@JsProperty
	native Object getBorderWidth();
	
	/**
	 * Sets the fill color of the arcs when hovered as string
	 * @param colors the fill color of the arcs when hovered as string
	 */
	@JsProperty
	native void setHoverBackgroundColor(Object colors);
	
	/**
	 * Returns the fill color of the arcs when hovered
	 * @return list of the fill color of the arcs when hovered
	 */
	@JsProperty
	native Object getHoverBackgroundColor();

	/**
	 * Sets the stroke color of the arcs when hovered as string.
	 * @param colors the stroke color of the arcs when hovered as string.
	 */
	@JsProperty
	native void setHoverBorderColor(Object colors);

	/**
	 * Returns the stroke color of the arcs when hovered.
	 * @return list of the stroke color of the arcs when hovered.
	 */
	@JsProperty
	native Object getHoverBorderColor();

	/**
	 * Sets the stroke width of the arcs when hovered.
	 * @param widths the stroke width of the arcs when hovered.
	 */
	@JsProperty
	native void setHoverBorderWidth(Object widths);
	
	/**
	 * Returns the stroke width of the arcs when hovered.
	 * @return list of the stroke width of the arcs when hovered.
	 */
	@JsProperty
	native Object getHoverBorderWidth();

}