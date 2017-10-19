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

import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.JsDoubleArrayList;
import org.pepstock.charba.client.commons.JsEnumValueArrayList;
import org.pepstock.charba.client.commons.JsIntegerArrayList;
import org.pepstock.charba.client.commons.JsStringArrayList;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.CapStyle;
import org.pepstock.charba.client.enums.Fill;
import org.pepstock.charba.client.enums.JoinStyle;
import org.pepstock.charba.client.enums.PointStyle;

/**
 * The chart allows a number of properties to be specified for each dataset. These are used to set display properties for a specific dataset.<br>
 * This class collects a set of common field for Line and Radar charts.
 * @author Andrea "Stock" Stocchero
 *
 */
abstract class LiningDataset extends Dataset{
	
	// default value of border color
	private static final String DEFAULT_BORDER_COLOR = "rgba(0,0,0,0.1)";
	// default value of border width
	private static final int DEFAULT_BORDER_WIDTH = 3;
	// default value of background color
	private static final String DEFAULT_BACKGROUND_COLOR = "rgba(0,0,0,0.1)";
	// default value of line tension
	private static final double DEFAULT_LINE_TENSION = 0.4F;
	// default value of border dash offset
	private static final int DEFAULT_BORDER_DASH_OFFSET = 0;
	
	// set of boolean flags to know if the value has been set as single value or as array
	private boolean isPointBackgroundColorArray = false;
	
	private boolean isPointBorderColorArray = false;
	
	private boolean isPointBorderWidthArray = false;
	
	private boolean isPointHitRadiusArray = false;
	
	private boolean isPointHoverBackgroundColorArray = false;
	
	private boolean isPointHoverBorderColorArray = false;
	
	private boolean isPointHoverBorderWidthArray = false;
	
	private boolean isPointHoverRadiusArray = false;
	
	private boolean isPointRadiusArray = false;
	
	private boolean isPointStyleArray = false;

	/**
	 * Name of fields of JavaScript object. 
	 */
	private enum Property implements Key {
		backgroundColor,
		borderColor,
		borderDash,
		borderDashOffset,
		borderCapStyle,
		borderJoinStyle,
		borderWidth,
		fill,
		lineTension,
		pointBackgroundColor,
		pointBorderColor,
		pointBorderWidth,
		pointRadius,
		pointStyle,
		pointHitRadius,
		pointHoverBackgroundColor,
		pointHoverBorderColor,
		pointHoverBorderWidth,
		pointHoverRadius
	}

	/**
	 * Sets the fill color under the line. 
	 * @param backgroundColor the fill color under the line.
	 */
	public void setBackgroundColor(String backgroundColor) {
		setValue(Property.backgroundColor, backgroundColor);
	}

	/**
	 * Returns the fill color under the line.
	 * @return the fill color under the line. Default is <code>rgba(0,0,0,0.1)</code>
	 */
	public String getBackgroundColor() {
		return getValue(Property.backgroundColor, DEFAULT_BACKGROUND_COLOR);
	}

	/**
	 * Sets the color of the line. 
	 * @param borderColor the color of the line.
	 */
	public void setBorderColor(String borderColor) {
		setValue(Property.borderColor, borderColor);
	}

	/**
	 * Returns the color of the line.
	 * @return the color of the line. Default is <code>rgba(0,0,0,0.1)</code>
	 */
	public String getBorderColor() {
		return getValue(Property.borderColor, DEFAULT_BORDER_COLOR);
	}

	/**
	 * Sets the width of the line in pixels.
	 * @param borderWidth the width of the line in pixels.
	 */
	public void setBorderWidth(int borderWidth) {
		setValue(Property.borderWidth, borderWidth);
	}

	/**
	 * Returns the width of the line in pixels.
	 * @return the width of the line in pixels. Default is <code>3</code>
	 */
	public int getBorderWidth() {
		return getValue(Property.borderWidth, DEFAULT_BORDER_WIDTH);
	}

	/**
	 * Sets the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 * @param borderDash the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 */
	public void setBorderDash(int... borderDash) {
		setBorderDash(ArrayListHelper.build(borderDash));
	}

	/**
	 * Sets the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 * @param borderDash the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 */
	private void setBorderDash(JsIntegerArrayList borderDash) {
		setIntegerArray(Property.borderDash, borderDash);
	}

	/**
	 * Returns the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 * @return the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 */
	public List<Integer> getBorderDash() {
		return getIntegerArray(Property.borderDashOffset);
	}

	/**
	 * Sets the line dash pattern offset or "phase". 
	 * @param borderDashOffset the line dash pattern offset or "phase". 
	 */
	public void setBorderDashOffset(int borderDashOffset){
		  setValue(Property.borderDashOffset, borderDashOffset);
	}

	/**
	 * Returns the line dash pattern offset or "phase". 
	 * @return the line dash pattern offset or "phase". Default is <code>0</code>
	 */
	public int getBorderDashOffset(){
		  return getValue(Property.borderDashOffset, DEFAULT_BORDER_DASH_OFFSET);
	}

	/**
	 * Sets how the end points of every line are drawn. There are three possible values for this property and those are: butt, round and square. 
	 * @param borderCapStyle how the end points of every line are drawn.
	 * @see org.pepstock.charba.client.enums.CapStyle
	 */
	public void setBorderCapStyle(CapStyle borderCapStyle) {
		setValue(Property.borderCapStyle, borderCapStyle);
	}

	/**
	 * Returns how the end points of every line are drawn. There are three possible values for this property and those are: butt, round and square. By default this property is set to butt.
	 * @return how the end points of every line are drawn.
	 * @see org.pepstock.charba.client.enums.CapStyle
	 */
	public CapStyle getBorderCapStyle(){
		  return getValue(Property.borderCapStyle, CapStyle.class, CapStyle.butt);
	}

	/**
	 * Sets how two connecting segments (of lines, arcs or curves) with non-zero lengths in a shape are joined together (degenerate segments with zero lengths, whose specified endpoints and control points are exactly at the same position, are skipped).<br>
	 * There are three possible values for this property: round, bevel and miter. By default this property is set to miter.
	 * @param borderJoinStyle There are three possible values for this property: round, bevel and miter. By default this property is set to miter.
	 * @see org.pepstock.charba.client.enums.JoinStyle
	 */
	public void setBorderJoinStyle(JoinStyle borderJoinStyle) {
		setValue(Property.borderJoinStyle, borderJoinStyle);
	}

	/**
	 * Returns how two connecting segments (of lines, arcs or curves) with non-zero lengths in a shape are joined together (degenerate segments with zero lengths, whose specified endpoints and control points are exactly at the same position, are skipped).<br>
	 * There are three possible values for this property: round, bevel and miter. By default this property is set to miter.
	 * @return There are three possible values for this property: round, bevel and miter. By default this property is set to miter.
	 * @see org.pepstock.charba.client.enums.JoinStyle
	 */
	public JoinStyle getBorderJoinStyle() {
		return getValue(Property.borderJoinStyle, JoinStyle.class, JoinStyle.miter);
	}

	/**
	 * Sets how to fill the area under the line.
	 * @param fill how to fill the area under the line.
	 * @see org.pepstock.charba.client.enums.Fill
	 */
	public void setFill(Fill fill){
		// checks if is no fill
		if (Fill.nofill.equals(fill)){
			// sets the boolean value instead of string one
			setValue(Property.fill, false);
		} else {
			// sets value
			setValue(Property.fill, fill.name());
		}
	}
	
	/**
	 * Returns how to fill the area under the line.
	 * @return how to fill the area under the line.
	 * @see org.pepstock.charba.client.enums.Fill
	 */
	public Fill getFill(){
		// gets value
		String value = getValue(Property.fill, Fill.origin.name());
		// if is a boolean FALSE value
		if (value.equalsIgnoreCase(Boolean.FALSE.toString())){
			// returns no fill
			return Fill.nofill;
		}
		// scans all enums
		for (Fill enumValue : Fill.values()){
			// if enum is equals to value
			if (enumValue.name().equalsIgnoreCase(value)){
				// returns enum
				return enumValue;
			}
		}
		// returns this as default
		return Fill.origin;
	}

	/**
	 * Sets curve tension of the line. Set to 0 to draw straight lines. This option is ignored if monotone cubic interpolation is used.
	 * @param lineTension curve tension of the line
	 */
	public void setLineTension(double lineTension){
		  setValue(Property.lineTension, lineTension);
	}

	/**
	 * Returns curve tension of the line. Set to 0 to draw straight lines. This option is ignored if monotone cubic interpolation is used.
	 * @return curve tension of the line. Default is <code>0.4</code>
	 */
	public double getLineTension(){
		  return getValue(Property.lineTension, DEFAULT_LINE_TENSION);
	}

	/**
	 * Sets the fill color for points.
	 * @param pointBackgroundColor array of the fill color for points.
	 */
	public void setPointBackgroundColor(String... pointBackgroundColor) {
	    setPointBackgroundColor(ArrayListHelper.build(pointBackgroundColor));
	}

	/**
	 * Sets the fill color for points.
	 * @param pointBackgroundColor the fill color for points.
	 */
	private void setPointBackgroundColor(JsStringArrayList pointBackgroundColor) {
	    isPointBackgroundColorArray = checkAndSetStringValues(Property.pointBackgroundColor, pointBackgroundColor);
	}

	/**
	 * Returns the fill color for points.
	 * @return list of the fill color for points.
	 */
	public List<String> getPointBackgroundColor() {
	    return checkAndGetStringValues(Property.pointBackgroundColor, isPointBackgroundColorArray);
	}

	/**
	 * Sets the border color for points.
	 * @param pointBorderColor array of the border color for points.
	 */
	public void setPointBorderColor(String... pointBorderColor) {
		setPointBorderColor(ArrayListHelper.build(pointBorderColor));
	}

	/**
	 * Sets the border color for points.
	 * @param pointBorderColor the border color for points.
	 */
	private void setPointBorderColor(JsStringArrayList pointBorderColor) {
	    isPointBorderColorArray = checkAndSetStringValues(Property.pointBorderColor, pointBorderColor);
	}

	/**
	 * Returns the border color for points.
	 * @return list of the border color for points.
	 */
	public List<String> getPointBorderColor() {
	    return checkAndGetStringValues(Property.pointBorderColor, isPointBorderColorArray);
	}

	/**
	 * Sets the width of the point border in pixels.
	 * @param pointBorderWidth array of the width of the point border in pixels.
	 */
	public void setPointBorderWidth(int... pointBorderWidth) {
		setPointBorderWidth(ArrayListHelper.build(pointBorderWidth));
	}

	/**
	 * Sets the width of the point border in pixels.
	 * @param pointBorderWidth the width of the point border in pixels.
	 */
	private void setPointBorderWidth(JsIntegerArrayList pointBorderWidth) {
	    isPointBorderWidthArray = checkAndSetIntegerValues(Property.pointBorderWidth, pointBorderWidth);
	}

	/**
	 * Returns the width of the point border in pixels.
	 * @return list of the width of the point border in pixels.
	 */
	public List<Integer> getPointBorderWidth() {
	    return checkAndGetIntegerValues(Property.pointBorderWidth, isPointBorderWidthArray);
	}

	/**
	 * Sets the pixel size of the non-displayed point that reacts to mouse events.
	 * @param pointHitRadius array of the pixel size of the non-displayed point.
	 */
	public void setPointHitRadius(double... pointHitRadius) {
		setPointHitRadius(ArrayListHelper.build(pointHitRadius));
	}
	
	/**
	 * Sets the pixel size of the non-displayed point that reacts to mouse events.
	 * @param pointHitRadius the pixel size of the non-displayed point.
	 */
	private void setPointHitRadius(JsDoubleArrayList pointHitRadius) {
	    isPointHitRadiusArray = checkAndSetDoubleValues(Property.pointHitRadius, pointHitRadius);
	}

	/**
	 * Returns the pixel size of the non-displayed point that reacts to mouse events.
	 * @return list of the pixel size of the non-displayed point.
	 */
	public List<Double> getPointHitRadius() {
	    return checkAndGetDoubleValues(Property.pointHitRadius, isPointHitRadiusArray);
	}

	/**
	 * Sets the point background color when hovered.
	 * @param pointHoverBackgroundColor array of the point background color when hovered.
	 */
	public void setPointHoverBackgroundColor(String... pointHoverBackgroundColor) {
		setPointHoverBackgroundColor(ArrayListHelper.build(pointHoverBackgroundColor));
	}

	/**
	 * Sets the point background color when hovered.
	 * @param pointHoverBackgroundColor the point background color when hovered.
	 */
	private void setPointHoverBackgroundColor(JsStringArrayList pointHoverBackgroundColor) {
	    isPointHoverBackgroundColorArray = checkAndSetStringValues(Property.pointHoverBackgroundColor, pointHoverBackgroundColor);
	}

	/**
	 * Returns the point background color when hovered.
	 * @return list of the point background color when hovered.
	 */
	public List<String> getPointHoverBackgroundColor() {
	    return checkAndGetStringValues(Property.pointHoverBackgroundColor, isPointHoverBackgroundColorArray);
	}

	/**
	 * Sets the point border color when hovered.
	 * @param pointHoverBorderColor array of the point border color when hovered.
	 */
	public void setPointHoverBorderColor(String... pointHoverBorderColor) {
		setPointHoverBorderColor(ArrayListHelper.build(pointHoverBorderColor));
	}

	/**
	 * Sets the point border color when hovered.
	 * @param pointHoverBorderColor the point border color when hovered.
	 */
	private void setPointHoverBorderColor(JsStringArrayList pointHoverBorderColor) {
	    isPointHoverBorderColorArray = checkAndSetStringValues(Property.pointHoverBorderColor, pointHoverBorderColor);
	}

	/**
	 * Returns the point border color when hovered.
	 * @return list of the point border color when hovered.
	 */
	public List<String> getPointHoverBorderColor() {
	    return checkAndGetStringValues(Property.pointHoverBorderColor, isPointHoverBorderColorArray);
	}

	/**
	 * Sets the border width of point when hovered.
	 * @param pointHoverBorderWidth array of the border width of point when hovered.
	 */
	public void setPointHoverBorderWidth(int... pointHoverBorderWidth) {
		setPointHoverBorderWidth(ArrayListHelper.build(pointHoverBorderWidth));
	}

	/**
	 * Sets the border width of point when hovered.
	 * @param pointHoverBorderWidth  the border width of point when hovered.
	 */
	private void setPointHoverBorderWidth(JsIntegerArrayList pointHoverBorderWidth) {
	    isPointHoverBorderWidthArray = checkAndSetIntegerValues(Property.pointHoverBorderWidth, pointHoverBorderWidth);
	}

	/**
	 * Returns the border width of point when hovered.
	 * @return list of the border width of point when hovered.
	 */
	public List<Integer> getPointHoverBorderWidth() {
	    return checkAndGetIntegerValues(Property.pointHoverBorderWidth, isPointHoverBorderWidthArray);
	}

	/**
	 * Sets the radius of the point when hovered.
	 * @param pointHoverRadius array of the radius of the point when hovered.
	 */
	public void setPointHoverRadius(double... pointHoverRadius) {
		setPointHoverRadius(ArrayListHelper.build(pointHoverRadius));
	}

	/**
	 * Sets the radius of the point when hovered.
	 * @param pointHoverRadius the radius of the point when hovered.
	 */
	private void setPointHoverRadius(JsDoubleArrayList pointHoverRadius) {
	    isPointHoverRadiusArray = checkAndSetDoubleValues(Property.pointHoverRadius, pointHoverRadius);
	}

	/**
	 * Returns the radius of the point when hovered.
	 * @return list of the radius of the point when hovered.
	 */
	public List<Double> getPointHoverRadius() {
	    return checkAndGetDoubleValues(Property.pointHoverRadius, isPointHoverRadiusArray);
	}

	/**
	 * Sets the radius of the point shape. If set to 0, the point is not rendered.
	 * @param pointRadius array of the radius of the point shape.
	 */
	public void setPointRadius(double...  pointRadius) {
		setPointRadius(ArrayListHelper.build(pointRadius));
	}

	/**
	 * Sets the radius of the point shape. If set to 0, the point is not rendered.
	 * @param pointRadius the radius of the point shape.
	 */
	private void setPointRadius(JsDoubleArrayList pointRadius) {
	    isPointRadiusArray = checkAndSetDoubleValues(Property.pointRadius, pointRadius);
	}

	/**
	 * Returns the radius of the point shape.
	 * @return list of the radius of the point shape.
	 */
	public List<Double> getPointRadius() {
	    return checkAndGetDoubleValues(Property.pointRadius, isPointRadiusArray);
	}

	// FIXME mettere immagini
	/**
	 * Sets the style of the point.
	 * @param pointStyle array of the style of the point.
	 * @see org.pepstock.charba.client.enums.PointStyle
	 */
	public void setPointStyle(PointStyle... pointStyle) {
		setPointStyle(ArrayListHelper.build(PointStyle.class, pointStyle));
	}

	/**
	 * Sets the style of the point.
	 * @param pointStyle the style of the point.
	 * @see org.pepstock.charba.client.enums.PointStyle
	 */
	private void setPointStyle(JsEnumValueArrayList<PointStyle> pointStyle) {
		isPointStyleArray = checkAndSetEnumValues(Property.pointStyle, pointStyle);
	}

	/**
	 * Returns the style of the point.
	 * @return list of the style of the point. Default is <code>PointStyle.circle</code>
	 * @see org.pepstock.charba.client.enums.PointStyle
	 */
	public JsEnumValueArrayList<PointStyle> getPointStyle() {
		// gets the list of values in string format
		JsStringArrayList sValues = checkAndGetStringValues(Property.pointStyle, isPointStyleArray);
		// if empty, returns the default
		if (sValues.isEmpty()){
			return ArrayListHelper.build(PointStyle.class, new PointStyle[]{PointStyle.circle});
		} else {
			return ArrayListHelper.build(PointStyle.class, sValues);
		}
	}

}