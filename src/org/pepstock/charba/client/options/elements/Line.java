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

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.JsIntegerArrayList;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.CapStyle;
import org.pepstock.charba.client.enums.Fill;
import org.pepstock.charba.client.enums.JoinStyle;

/**
 * Line elements are used to represent the line in a line chart.
 * 
 * @author Andrea "Stock" Stocchero
 * @see org.pepstock.charba.client.LineChart
 */
public final class Line extends AbstractElement {

	/**
	 * Name of fields of JavaScript object.
	 */
	enum Property implements Key
	{
		tension,
		borderCapStyle,
		borderDash,
		borderDashOffset,
		borderJoinStyle,
		capBezierPoints,
		fill,
		stepped
	}
	
	/**
	 * Builds the object storing the chart instance.
	 * 
	 * @param chart chart instance
	 */
	public Line(AbstractChart<?, ?> chart) {
		super(chart);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.elements.Arc#getDefaultBackgroundColor()
	 */
	@Override
	protected String getDefaultBackgroundColor() {
		return getChart().getGlobal().getElements().getLine().getBackgroundColor();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.elements.Arc#getDefaultBorderWidth()
	 */
	@Override
	protected int getDefaultBorderWidth() {
		return getChart().getGlobal().getElements().getLine().getBorderWidth();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.elements.Arc#getDefaultBorderColor()
	 */
	@Override
	protected String getDefaultBorderColor() {
		return getChart().getGlobal().getElements().getLine().getBorderColor();
	}

	/**
	 * Returns the Bezier curve tension (0 for no Bezier curves).
	 * 
	 * @param tension the Bezier curve tension (0 for no Bezier curves).
	 */
	public void setTension(double tension) {
		setValue(Property.tension, tension);
	}

	/**
	 * Returns the Bezier curve tension (0 for no Bezier curves).
	 * 
	 * @return the Bezier curve tension (0 for no Bezier curves).
	 */
	public double getTension() {
		return getValue(Property.tension, getChart().getGlobal().getElements().getLine().getTension());
	}

	/**
	 * Sets how the end points of every line are drawn. There are three possible values for this property and those are: butt, round and square.
	 * 
	 * @param borderCapStyle how the end points of every line are drawn.
	 * @see org.pepstock.charba.client.enums.CapStyle
	 */
	public void setBorderCapStyle(CapStyle borderCapStyle) {
		setValue(Property.borderCapStyle, borderCapStyle);
	}

	/**
	 * Returns how the end points of every line are drawn. There are three possible values for this property and those are: butt, round and square. By default this property is set to butt.
	 * 
	 * @return how the end points of every line are drawn.
	 * @see org.pepstock.charba.client.enums.CapStyle
	 */
	public CapStyle getBorderCapStyle() {
		return getValue(Property.borderCapStyle, CapStyle.class, getChart().getGlobal().getElements().getLine().getBorderCapStyle());
	}

	/**
	 * Sets the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 * 
	 * @param borderDash the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 */
	public void setBorderDash(int... borderDash) {
		setBorderDash(ArrayListHelper.build(borderDash));
	}

	/**
	 * Sets the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 * 
	 * @param borderDash the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 */
	private void setBorderDash(JsIntegerArrayList borderDash) {
		setIntegerArray(Property.borderDash, borderDash);
	}

	/**
	 * Returns the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 * 
	 * @return the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 */
	public List<Integer> getBorderDash() {
		List<Integer> values = getIntegerArray(Property.borderDash);
		if (values.isEmpty()){
			return getChart().getGlobal().getElements().getLine().getBorderDash();
		}
		return values;
	}

	/**
	 * Sets the line dash pattern offset or "phase".
	 * 
	 * @param borderDashOffset the line dash pattern offset or "phase".
	 */
	public void setBorderDashOffset(int borderDashOffset) {
		setValue(Property.borderDashOffset, borderDashOffset);
	}

	/**
	 * Returns the line dash pattern offset or "phase".
	 * 
	 * @return the line dash pattern offset or "phase". 
	 */
	public int getBorderDashOffset() {
		return getValue(Property.borderDashOffset, getChart().getGlobal().getElements().getLine().getBorderDashOffset());
	}

	/**
	 * Sets how two connecting segments (of lines, arcs or curves) with non-zero lengths in a shape are joined together (degenerate segments with zero lengths, whose specified endpoints and control points are exactly at the same position, are
	 * skipped).<br>
	 * There are three possible values for this property: round, bevel and miter. By default this property is set to miter.
	 * 
	 * @param borderJoinStyle There are three possible values for this property: round, bevel and miter. 
	 * @see org.pepstock.charba.client.enums.JoinStyle
	 */
	public void setBorderJoinStyle(JoinStyle borderJoinStyle) {
		setValue(Property.borderJoinStyle, borderJoinStyle);
	}

	/**
	 * Returns how two connecting segments (of lines, arcs or curves) with non-zero lengths in a shape are joined together (degenerate segments with zero lengths, whose specified endpoints and control points are exactly at the same position, are
	 * skipped).<br>
	 * There are three possible values for this property: round, bevel and miter. By default this property is set to miter.
	 * 
	 * @return There are three possible values for this property: round, bevel and miter. 
	 * @see org.pepstock.charba.client.enums.JoinStyle
	 */
	public JoinStyle getBorderJoinStyle() {
		return getValue(Property.borderJoinStyle, JoinStyle.class, getChart().getGlobal().getElements().getLine().getBorderJoinStyle());
	}

	/**
	 * Sets <code>true</code> to keep Bezier control inside the chart, <code>false</code> for no restriction.
	 * 
	 * @param capBezierPoints <code>true</code> to keep Bezier control inside the chart, <code>false</code> for no restriction.
	 */
	public void setCapBezierPoints(boolean capBezierPoints) {
		setValue(Property.capBezierPoints, capBezierPoints);
	}

	/**
	 * Returns <code>true</code> to keep Bezier control inside the chart, <code>false</code> for no restriction.
	 * 
	 * @return <code>true</code> to keep Bezier control inside the chart, <code>false</code> for no restriction.
	 */
	public boolean isCapBezierPoints() {
		return getValue(Property.capBezierPoints, getChart().getGlobal().getElements().getLine().isCapBezierPoints());
	}

	/**
	 * Sets how to fill the area under the line.
	 * 
	 * @param fill how to fill the area under the line.
	 * @see org.pepstock.charba.client.enums.Fill
	 */
	public void setFill(Fill fill) {
		// checks if is no fill
		if (Fill.nofill.equals(fill)) {
			// sets the boolean value instead of string one
			setValue(Property.fill, false);
		} else {
			// sets value
			setValue(Property.fill, fill.name());
		}
	}

	/**
	 * Returns how to fill the area under the line.
	 * 
	 * @return how to fill the area under the line.
	 * @see org.pepstock.charba.client.enums.Fill
	 */
	public Fill getFill() {
		// gets value
		String value = getValue(Property.fill, (String)null);
		if (value == null){
			return getChart().getGlobal().getElements().getLine().getFill();
		} else if (value.equalsIgnoreCase(Boolean.FALSE.toString())) {
			// if is a boolean FALSE value
			// returns no fill
			return Fill.nofill;
		}
		// scans all enums
		for (Fill enumValue : Fill.values()) {
			// if enum is equals to value
			if (enumValue.name().equalsIgnoreCase(value)) {
				// returns enum
				return enumValue;
			}
		}
		// returns this as default
		return getChart().getGlobal().getElements().getLine().getFill();
	}

	/**
	 * Sets <code>true</code> to show the line as a stepped line (tension will be ignored).
	 * 
	 * @param stepped <code>true</code> to show the line as a stepped line (tension will be ignored).
	 */
	public void setStepped(boolean stepped) {
		setValue(Property.stepped, stepped);
	}

	/**
	 * Returns <code>true</code> to show the line as a stepped line (tension will be ignored).
	 * 
	 * @return <code>true</code> to show the line as a stepped line (tension will be ignored).
	 */
	public boolean isStepped() {
		return getValue(Property.stepped, getChart().getGlobal().getElements().getLine().isStepped());
	}

}