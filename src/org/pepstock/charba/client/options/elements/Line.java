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
package org.pepstock.charba.client.options.elements;

import java.util.List;

import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.JsIntegerArrayList;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.CapStyle;
import org.pepstock.charba.client.enums.Fill;
import org.pepstock.charba.client.enums.JoinStyle;

/**
 * Line elements are used to represent the line in a line chart.<br>
 * While chart types provide settings to configure the styling of each dataset, you sometimes want to style all datasets the same way.<br>
 * Options can be configured for four different types of elements: arc, lines, points, and rectangles.<br>
 * When set, these options apply to all objects of that type unless specifically overridden by the configuration attached to a dataset.
 * @author Andrea "Stock" Stocchero
 * @see org.pepstock.charba.client.LineChart
 */
public class Line extends Arc{

	// default line tension
	private static final float DEFAULT_TENSION = 0.4F;
	// default background color
	private static final String DEFAULT_BACKGROUND_COLOR = "rgba(0,0,0,0.1)";
	// default border width
	private static final int DEFAULT_BORDER_WIDTH = 3;
	// default line tension
	private static final String DEFAULT_BORDER_COLOR = "rgba(0,0,0,0.1)";

	private static final int DEFAULT_BORDER_DASH_OFFSET = 0;

	private static final boolean DEFAULT_CAP_BEZIER_POINTS = true;

	private static final boolean DEFAULT_STEPPED = false;
	
	/**
	 * Name of fields of JavaScript object. 
	 */
	enum Property implements Key {
		tension,
		borderCapStyle,
		borderDash,
		borderDashOffset,
		borderJoinStyle,
		capBezierPoints,
		fill,
		stepped
	}
	
	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.options.elements.Arc#getDefaultBackgroundColor()
	 */
	@Override
	protected String getDefaultBackgroundColor() {
		return DEFAULT_BACKGROUND_COLOR;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.options.elements.Arc#getDefaultBorderWidth()
	 */
	@Override
	protected int getDefaultBorderWidth() {
		return DEFAULT_BORDER_WIDTH;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.options.elements.Arc#getDefaultBorderColor()
	 */
	@Override
	protected String getDefaultBorderColor() {
		return DEFAULT_BORDER_COLOR;
	}

	/**
	 * Returns the Bezier curve tension (0 for no Bezier curves).
	 * @param tension the Bezier curve tension (0 for no Bezier curves).
	 */
	public void setTension(double tension) {
		setValue(Property.tension, tension);
	}

	/**
	 * Returns the Bezier curve tension (0 for no Bezier curves).
	 * @return the Bezier curve tension (0 for no Bezier curves).
	 */
	public double getTension() {
		return getValue(Property.tension, DEFAULT_TENSION);
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
	public void setBorderDashOffset(int borderDashOffset) {
		setValue(Property.borderDashOffset, borderDashOffset);
	}

	/**
	 * Returns the line dash pattern offset or "phase". 
	 * @return the line dash pattern offset or "phase". 
	 */
	public int getBorderDashOffset() {
		return getValue(Property.borderDashOffset, DEFAULT_BORDER_DASH_OFFSET);
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
	 * Sets <code>true</code> to keep Bezier control inside the chart, <code>false</code> for no restriction.
	 * @param capBezierPoints <code>true</code> to keep Bezier control inside the chart, <code>false</code> for no restriction.
	 */
	public void setCapBezierPoints(boolean capBezierPoints) {
		setValue(Property.capBezierPoints, capBezierPoints);
	}

	/**
	 * Returns <code>true</code> to keep Bezier control inside the chart, <code>false</code> for no restriction.
	 * @return <code>true</code> to keep Bezier control inside the chart, <code>false</code> for no restriction.
	 */
	public boolean isCapBezierPoints() {
		return getValue(Property.capBezierPoints, DEFAULT_CAP_BEZIER_POINTS);
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
	 * Sets <code>true</code> to show the line as a stepped line (tension will be ignored).
	 * @param stepped <code>true</code> to show the line as a stepped line (tension will be ignored).
	 */
	public void setStepped(boolean stepped) {
		setValue(Property.stepped, stepped);
	}

	/**
	 * Returns <code>true</code> to show the line as a stepped line (tension will be ignored).
	 * @return <code>true</code> to show the line as a stepped line (tension will be ignored).
	 */
	public boolean isStepped() {
		return getValue(Property.stepped, DEFAULT_STEPPED);
	}

}