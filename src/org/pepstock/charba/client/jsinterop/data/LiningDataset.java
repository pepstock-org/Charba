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

import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.enums.CapStyle;
import org.pepstock.charba.client.enums.Fill;
import org.pepstock.charba.client.enums.JoinStyle;
import org.pepstock.charba.client.enums.PointStyle;
import org.pepstock.charba.client.jsinterop.commons.ArrayInteger;
import org.pepstock.charba.client.jsinterop.commons.ArrayListHelper;
import org.pepstock.charba.client.jsinterop.commons.Checker;
import org.pepstock.charba.client.jsinterop.commons.Enumer;
import org.pepstock.charba.client.jsinterop.commons.FlexibleProperty;

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

	/**
	 * Sets the fill color under the line. 
	 * @param backgroundColor the fill color under the line.
	 */
	public void setBackgroundColor(IsColor backgroundColor) {
		setBackgroundColor(backgroundColor.toRGBA());
	}

	/**
	 * Sets the fill color under the line. 
	 * @param backgroundColor the fill color under the line.
	 */
	public void setBackgroundColor(String backgroundColor) {
		getNativeObject().setBackgroundColor(backgroundColor);
	}

	/**
	 * Returns the fill color under the line.
	 * @return the fill color under the line. Default is <code>rgba(0,0,0,0.1)</code>
	 */
	public String getBackgroundColorAsString() {
		return Checker.check(getNativeObject().getBackgroundColor(), DEFAULT_BACKGROUND_COLOR);
	}

	/**
	 * Returns the fill color under the line.
	 * @return the fill color under the line. Default is <code>rgba(0,0,0,0.1)</code>
	 */
	public IsColor getBackgroundColor() {
		return ColorBuilder.parse(getBackgroundColorAsString());
	}

	/**
	 * Sets the color of the line. 
	 * @param borderColor the color of the line.
	 */
	public void setBorderColor(IsColor borderColor) {
		setBorderColor(borderColor.toRGBA());
	}

	/**
	 * Sets the color of the line. 
	 * @param borderColor the color of the line.
	 */
	public void setBorderColor(String borderColor) {
		getNativeObject().setBorderColor(borderColor);
	}

	/**
	 * Returns the color of the line.
	 * @return the color of the line. Default is <code>rgba(0,0,0,0.1)</code>
	 */
	public String getBorderColorAsString() {
		return Checker.check(getNativeObject().getBorderColor(), DEFAULT_BORDER_COLOR);
	}

	/**
	 * Returns the color of the line.
	 * @return the color of the line. Default is <code>rgba(0,0,0,0.1)</code>
	 */
	public IsColor getBorderColor() {
		return ColorBuilder.parse(getBorderColorAsString());
	}
	/**
	 * Sets the width of the line in pixels.
	 * @param borderWidth the width of the line in pixels.
	 */
	public void setBorderWidth(int borderWidth) {
		getNativeObject().setBorderWidth(borderWidth);
	}

	/**
	 * Returns the width of the line in pixels.
	 * @return the width of the line in pixels. Default is <code>3</code>
	 */
	public int getBorderWidth() {
		return Checker.check(getNativeObject().getBorderWidth(), DEFAULT_BORDER_WIDTH);
	}

	/**
	 * Sets the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 * @param borderDash the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 */
	public void setBorderDash(int... borderDash) {
		getNativeObject().setBorderDash(ArrayInteger.of(borderDash));
	}

	/**
	 * Returns the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 * @return the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 */
	public List<Integer> getBorderDash() {
		// FIXME che valga la pena non restituire NULL!
		return ArrayListHelper.list(getNativeObject().getBorderDash());
	}

	/**
	 * Sets the line dash pattern offset or "phase". 
	 * @param borderDashOffset the line dash pattern offset or "phase". 
	 */
	public void setBorderDashOffset(int borderDashOffset){
		 getNativeObject().setBorderDashOffset(borderDashOffset);
	}

	/**
	 * Returns the line dash pattern offset or "phase". 
	 * @return the line dash pattern offset or "phase". Default is <code>0</code>
	 */
	public int getBorderDashOffset(){
		  return Checker.check(getNativeObject().getBorderDashOffset(), DEFAULT_BORDER_DASH_OFFSET);
	}

	/**
	 * Sets how the end points of every line are drawn. There are three possible values for this property and those are: butt, round and square. 
	 * @param borderCapStyle how the end points of every line are drawn.
	 * @see org.pepstock.charba.client.enums.CapStyle
	 */
	public void setBorderCapStyle(CapStyle borderCapStyle) {
		getNativeObject().setBorderCapStyle(borderCapStyle.name());
	}

	/**
	 * Returns how the end points of every line are drawn. There are three possible values for this property and those are: butt, round and square. By default this property is set to butt.
	 * @return how the end points of every line are drawn.
	 * @see org.pepstock.charba.client.enums.CapStyle
	 */
	public CapStyle getBorderCapStyle(){
		return Enumer.deserialize(getNativeObject().getBorderCapStyle(), CapStyle.class, CapStyle.butt);
	}

	/**
	 * Sets how two connecting segments (of lines, arcs or curves) with non-zero lengths in a shape are joined together (degenerate segments with zero lengths, whose specified endpoints and control points are exactly at the same position, are skipped).<br>
	 * There are three possible values for this property: round, bevel and miter. By default this property is set to miter.
	 * @param borderJoinStyle There are three possible values for this property: round, bevel and miter. By default this property is set to miter.
	 * @see org.pepstock.charba.client.enums.JoinStyle
	 */
	public void setBorderJoinStyle(JoinStyle borderJoinStyle) {
		getNativeObject().setBorderJoinStyle(borderJoinStyle.name());
	}

	/**
	 * Returns how two connecting segments (of lines, arcs or curves) with non-zero lengths in a shape are joined together (degenerate segments with zero lengths, whose specified endpoints and control points are exactly at the same position, are skipped).<br>
	 * There are three possible values for this property: round, bevel and miter. By default this property is set to miter.
	 * @return There are three possible values for this property: round, bevel and miter. By default this property is set to miter.
	 * @see org.pepstock.charba.client.enums.JoinStyle
	 */
	public JoinStyle getBorderJoinStyle() {
		return Enumer.deserialize(getNativeObject().getBorderJoinStyle(),JoinStyle.class, JoinStyle.miter);
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
			getNativeObject().setFill(false);
		} else {
			// sets value
			getNativeObject().setFill(fill.name());
		}
	}
	
	/**
	 * Returns how to fill the area under the line.
	 * @return how to fill the area under the line.
	 * @see org.pepstock.charba.client.enums.Fill
	 */
	public Fill getFill(){
		// gets value
		Object value = getNativeObject().getFill();
		if (value != null) {
			String valueAsString = (String) value;
			// if is a boolean FALSE value
			if (valueAsString.equalsIgnoreCase(Boolean.FALSE.toString())){
				// returns no fill
				return Fill.nofill;
			}
			return Enumer.deserialize(valueAsString, Fill.class, Fill.origin);
		}
		// returns this as default
		return Fill.origin;
	}

	/**
	 * Sets curve tension of the line. Set to 0 to draw straight lines. This option is ignored if monotone cubic interpolation is used.
	 * @param lineTension curve tension of the line
	 */
	public void setLineTension(double lineTension){
		getNativeObject().setLineTension(lineTension);
	}

	/**
	 * Returns curve tension of the line. Set to 0 to draw straight lines. This option is ignored if monotone cubic interpolation is used.
	 * @return curve tension of the line. Default is <code>0.4</code>
	 */
	public double getLineTension(){
		return Checker.check(getNativeObject().getLineTension(), DEFAULT_LINE_TENSION);
	}

	/**
	 * Sets the fill color for points.
	 * @param pointBackgroundColor array of the fill color for points.
	 */
	public void setPointBackgroundColor(IsColor... pointBackgroundColor) {
		getNativeObject().setPointBackgroundColor(FlexibleProperty.fromColors(pointBackgroundColor));
	}

	/**
	 * Sets the fill color for points.
	 * @param pointBackgroundColor array of the fill color for points.
	 */
	public void setPointBackgroundColor(String... pointBackgroundColor) {
		getNativeObject().setPointBackgroundColor(FlexibleProperty.fromStrings(pointBackgroundColor));
	}

	/**
	 * Returns the fill color for points.
	 * @return list of the fill color for points.
	 */
	public List<String> getPointBackgroundColorAsString() {
	    return FlexibleProperty.toStrings(getNativeObject().getPointBackgroundColor());
	}

	/**
	 * Returns the fill color for points.
	 * @return list of the fill color for points.
	 */
	public List<IsColor> getPointBackgroundColor() {
	    return ColorBuilder.parse(getPointBackgroundColorAsString());
	}

	/**
	 * Sets the border color for points.
	 * @param pointBorderColor array of the border color for points.
	 */
	public void setPointBorderColor(IsColor... pointBorderColor) {
		getNativeObject().setPointBorderColor(FlexibleProperty.fromColors(pointBorderColor));
	}

	/**
	 * Sets the border color for points.
	 * @param pointBorderColor array of the border color for points.
	 */
	public void setPointBorderColor(String... pointBorderColor) {
		getNativeObject().setPointBorderColor(FlexibleProperty.fromStrings(pointBorderColor));
	}

	/**
	 * Returns the border color for points.
	 * @return list of the border color for points.
	 */
	public List<String> getPointBorderColorAsString() {
	    return FlexibleProperty.toStrings(getNativeObject().getPointBorderColor());
	}

	/**
	 * Returns the border color for points.
	 * @return list of the border color for points.
	 */
	public List<IsColor> getPointBorderColor() {
	    return ColorBuilder.parse(getPointBorderColorAsString());
	}

	/**
	 * Sets the width of the point border in pixels.
	 * @param pointBorderWidth array of the width of the point border in pixels.
	 */
	public void setPointBorderWidth(int... pointBorderWidth) {
		getNativeObject().setPointBorderWidth(FlexibleProperty.fromIntegers(pointBorderWidth));
	}

	/**
	 * Returns the width of the point border in pixels.
	 * @return list of the width of the point border in pixels.
	 */
	public List<Integer> getPointBorderWidth() {
	    return FlexibleProperty.toIntegers(getNativeObject().getPointBorderWidth());
	}

	/**
	 * Sets the pixel size of the non-displayed point that reacts to mouse events.
	 * @param pointHitRadius array of the pixel size of the non-displayed point.
	 */
	public void setPointHitRadius(double... pointHitRadius) {
		getNativeObject().setPointHitRadius(FlexibleProperty.fromDoubles(pointHitRadius));
	}

	/**
	 * Returns the pixel size of the non-displayed point that reacts to mouse events.
	 * @return list of the pixel size of the non-displayed point.
	 */
	public List<Double> getPointHitRadius() {
	    return FlexibleProperty.toDoubles(getNativeObject().getPointHitRadius());
	}

	/**
	 * Sets the point background color when hovered.
	 * @param pointHoverBackgroundColor array of the point background color when hovered.
	 */
	public void setPointHoverBackgroundColor(IsColor... pointHoverBackgroundColor) {
		getNativeObject().setPointHoverBackgroundColor(FlexibleProperty.fromColors(pointHoverBackgroundColor));
	}
	
	/**
	 * Sets the point background color when hovered.
	 * @param pointHoverBackgroundColor array of the point background color when hovered.
	 */
	public void setPointHoverBackgroundColor(String... pointHoverBackgroundColor) {
		getNativeObject().setPointHoverBackgroundColor(FlexibleProperty.fromStrings(pointHoverBackgroundColor));
	}

	/**
	 * Returns the point background color when hovered.
	 * @return list of the point background color when hovered.
	 */
	public List<String> getPointHoverBackgroundColorAsString() {
	    return FlexibleProperty.toStrings(getNativeObject().getPointHoverBackgroundColor());
	}

	/**
	 * Returns the point background color when hovered.
	 * @return list of the point background color when hovered.
	 */
	public List<IsColor> getPointHoverBackgroundColor() {
	    return ColorBuilder.parse(getPointHoverBackgroundColorAsString());
	}

	/**
	 * Sets the point border color when hovered.
	 * @param pointHoverBorderColor array of the point border color when hovered.
	 */
	public void setPointHoverBorderColor(IsColor... pointHoverBorderColor) {
		getNativeObject().setPointHoverBorderColor(FlexibleProperty.fromColors(pointHoverBorderColor));
	}

	/**
	 * Sets the point border color when hovered.
	 * @param pointHoverBorderColor array of the point border color when hovered.
	 */
	public void setPointHoverBorderColor(String... pointHoverBorderColor) {
		getNativeObject().setPointHoverBorderColor(FlexibleProperty.fromStrings(pointHoverBorderColor));
	}

	/**
	 * Returns the point border color when hovered.
	 * @return list of the point border color when hovered.
	 */
	public List<String> getPointHoverBorderColorAsString() {
	    return FlexibleProperty.toStrings(getNativeObject().getPointHoverBorderColor());
	}

	/**
	 * Returns the point border color when hovered.
	 * @return list of the point border color when hovered.
	 */
	public List<IsColor> getPointHoverBorderColor() {
	    return ColorBuilder.parse(getPointHoverBorderColorAsString());
	}

	/**
	 * Sets the border width of point when hovered.
	 * @param pointHoverBorderWidth array of the border width of point when hovered.
	 */
	public void setPointHoverBorderWidth(int... pointHoverBorderWidth) {
		getNativeObject().setPointHoverBorderWidth(FlexibleProperty.fromIntegers(pointHoverBorderWidth));
	}

	/**
	 * Returns the border width of point when hovered.
	 * @return list of the border width of point when hovered.
	 */
	public List<Integer> getPointHoverBorderWidth() {
	    return FlexibleProperty.toIntegers(getNativeObject().getPointHoverBorderWidth());
	}

	/**
	 * Sets the radius of the point when hovered.
	 * @param pointHoverRadius array of the radius of the point when hovered.
	 */
	public void setPointHoverRadius(double... pointHoverRadius) {
		getNativeObject().setPointHoverRadius(FlexibleProperty.fromDoubles(pointHoverRadius));
	}

	/**
	 * Returns the radius of the point when hovered.
	 * @return list of the radius of the point when hovered.
	 */
	public List<Double> getPointHoverRadius() {
	    return FlexibleProperty.toDoubles(getNativeObject().getPointHoverRadius());
	}

	/**
	 * Sets the radius of the point shape. If set to 0, the point is not rendered.
	 * @param pointRadius array of the radius of the point shape.
	 */
	public void setPointRadius(double...  pointRadius) {
		getNativeObject().setPointRadius(FlexibleProperty.fromDoubles(pointRadius));
	}

	/**
	 * Returns the radius of the point shape.
	 * @return list of the radius of the point shape.
	 */
	public List<Double> getPointRadius() {
	    return FlexibleProperty.toDoubles(getNativeObject().getPointRadius());
	}

	/**
	 * Sets the style of the point.
	 * @param pointStyle array of the style of the point.
	 * @see org.pepstock.charba.client.enums.PointStyle
	 */
	public void setPointStyle(PointStyle... pointStyle) {
		getNativeObject().setPointStyle(FlexibleProperty.fromKeys(pointStyle));
	}

	/**
	 * Returns the style of the point.
	 * @return list of the style of the point. Default is <code>PointStyle.circle</code>
	 * @see org.pepstock.charba.client.enums.PointStyle
	 */
	public List<PointStyle> getPointStyle() {
		List<PointStyle> result = FlexibleProperty.toKeys(getNativeObject().getPointStyle(), PointStyle.class);
		if (result.isEmpty()) {
			//FIXME e corretto?
			result.add(PointStyle.circle);
		}
		return result;
	}

}