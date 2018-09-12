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
package org.pepstock.charba.client.jsinterop.options.elements.line;

import java.util.List;

import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.enums.CapStyle;
import org.pepstock.charba.client.enums.Fill;
import org.pepstock.charba.client.enums.JoinStyle;
import org.pepstock.charba.client.jsinterop.commons.ArrayInteger;
import org.pepstock.charba.client.jsinterop.commons.ArrayListHelper;
import org.pepstock.charba.client.jsinterop.commons.AssignHelper;
import org.pepstock.charba.client.jsinterop.commons.IsDelegated;

/**
 * Arcs are used in the polar area, doughnut and pie charts.<br>
 * While chart types provide settings to configure the styling of each dataset, you sometimes want to style all datasets the
 * same way.<br>
 * Options can be configured for four different types of elements: arc, lines, points, and rectangles.<br>
 * When set, these options apply to all objects of that type unless specifically overridden by the configuration attached to a
 * dataset.
 * 
 * @author Andrea "Stock" Stocchero
 * 
 */
public class Line implements IsDelegated<NativeLine>{

	private final NativeLine delegated;
	
	private final IsDefaultLine defaultValues;
	
	public Line(IsDefaultLine defaultValues) {
		this(new NativeLine(), defaultValues);
	}

	public Line(NativeLine delegated, IsDefaultLine defaultValues) {
		this.delegated = delegated;
		this.defaultValues = defaultValues;
	}

	/**
	 * Sets the background color.
	 * 
	 * @param backgroundColor the background color.
	 */
	public void setBackgroundColor(IsColor backgroundColor) {
		setBackgroundColor(backgroundColor.toRGBA());
	}
	
	/**
	 * Sets the background color.
	 * 
	 * @param backgroundColor the background color.
	 */
	public void setBackgroundColor(String backgroundColor) {
		delegated.setBackgroundColor(backgroundColor);
	}

	/**
	 * Returns the background color.
	 * 
	 * @return the background color. Default is rgba(0,0,0,0.1).
	 */
	public String getBackgroundColorAsString() {
		return AssignHelper.check(delegated.getBackgroundColor(), defaultValues.getBackgroundColor());
	}


	/**
	 * Returns the background color.
	 * 
	 * @return the background color. Default is rgba(0,0,0,0.1).
	 */
	public IsColor getBackgroundColor() {
		return ColorBuilder.parse(getBackgroundColorAsString());
	}

	/**
	 * Sets the border width.
	 * 
	 * @param borderWidth the border width.
	 */
	public void setBorderWidth(int borderWidth) {
		delegated.setBorderWidth(borderWidth);
	}

	/**
	 * Returns the border width.
	 * 
	 * @return the border width. Default is 2.
	 */
	public int getBorderWidth() {
		return AssignHelper.check(delegated.getBorderWidth(), defaultValues.getBorderWidth());
	}

	/**
	 * Sets the border color.
	 * 
	 * @param borderColor the border color.
	 */
	public void setBorderColor(IsColor borderColor) {
		setBorderColor(borderColor.toRGBA());
	}

	/**
	 * Sets the border color.
	 * 
	 * @param borderColor the border color.
	 */
	public void setBorderColor(String borderColor) {
		delegated.setBorderColor(borderColor);
	}

	/**
	 * Returns the border color.
	 * 
	 * @return the border color. Default is #fff.
	 */
	public String getBorderColorAsString() {
		return AssignHelper.check(delegated.getBorderColor(), defaultValues.getBorderColor());
	}

	/**
	 * Returns the border color.
	 * 
	 * @return the border color. Default is #fff.
	 */
	public IsColor getBorderColor() {
		return ColorBuilder.parse(getBorderColorAsString());
	}
	
	/**
	 * Returns the Bezier curve tension (0 for no Bezier curves).
	 * 
	 * @param tension the Bezier curve tension (0 for no Bezier curves).
	 */
	public void setTension(double tension) {
		delegated.setTension(tension);
	}

	/**
	 * Returns the Bezier curve tension (0 for no Bezier curves).
	 * 
	 * @return the Bezier curve tension (0 for no Bezier curves). Default is 0.4F.
	 */
	public double getTension() {
		return AssignHelper.check(delegated.getTension(), defaultValues.getTension());
	}

	/**
	 * Sets how the end points of every line are drawn. There are three possible values for this property and those are: butt,
	 * round and square.
	 * 
	 * @param borderCapStyle how the end points of every line are drawn.
	 * @see org.pepstock.charba.client.enums.CapStyle
	 */
	public void setBorderCapStyle(CapStyle borderCapStyle) {
		delegated.setBorderCapStyle(borderCapStyle.name());
	}

	/**
	 * Returns how the end points of every line are drawn. There are three possible values for this property and those are:
	 * butt, round and square. By default this property is set to butt.
	 * 
	 * @return how the end points of every line are drawn. Default is {@link org.pepstock.charba.client.enums.CapStyle#butt}.
	 * @see org.pepstock.charba.client.enums.CapStyle
	 */
	public CapStyle getBorderCapStyle() {
		return AssignHelper.deserialize(AssignHelper.check(delegated.getBorderCapStyle(), defaultValues.getBorderCapStyle()), CapStyle.class, CapStyle.butt);
	}

	/**
	 * Sets the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines
	 * and gaps which describe the pattern.
	 * 
	 * @param borderDash the line dash pattern used when stroking lines, using an array of values which specify alternating
	 *            lengths of lines and gaps which describe the pattern.
	 */
	public void setBorderDash(int... borderDash) {
		setBorderDash(ArrayInteger.of(borderDash));
	}

	/**
	 * Sets the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines
	 * and gaps which describe the pattern.
	 * 
	 * @param borderDash the line dash pattern used when stroking lines, using an array of values which specify alternating
	 *            lengths of lines and gaps which describe the pattern.
	 */
	private void setBorderDash(ArrayInteger borderDash) {
		delegated.setBorderDash(borderDash);
	}

	/**
	 * Returns the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of
	 * lines and gaps which describe the pattern.
	 * 
	 * @return the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of
	 *         lines and gaps which describe the pattern.
	 */
	public List<Integer> getBorderDash() {
		return ArrayListHelper.build(AssignHelper.check(delegated.getBorderDash(), defaultValues.getBorderDash()));
	}

	/**
	 * Sets the line dash pattern offset or "phase".
	 * 
	 * @param borderDashOffset the line dash pattern offset or "phase".
	 */
	public void setBorderDashOffset(int borderDashOffset) {
		delegated.setBorderDashOffset(borderDashOffset);
	}

	/**
	 * Returns the line dash pattern offset or "phase".
	 * 
	 * @return the line dash pattern offset or "phase". Default is 0.
	 */
	public int getBorderDashOffset() {
		return AssignHelper.check(delegated.getBorderDashOffset(), defaultValues.getBorderDashOffset());
	}

	/**
	 * Sets how two connecting segments (of lines, arcs or curves) with non-zero lengths in a shape are joined together
	 * (degenerate segments with zero lengths, whose specified endpoints and control points are exactly at the same position,
	 * are skipped).<br>
	 * There are three possible values for this property: round, bevel and miter. By default this property is set to miter.
	 * 
	 * @param borderJoinStyle There are three possible values for this property: round, bevel and miter. By default this
	 *            property is set to miter.
	 * @see org.pepstock.charba.client.enums.JoinStyle
	 */
	public void setBorderJoinStyle(JoinStyle borderJoinStyle) {
		delegated.setBorderJoinStyle(borderJoinStyle.name());
	}

	/**
	 * Returns how two connecting segments (of lines, arcs or curves) with non-zero lengths in a shape are joined together
	 * (degenerate segments with zero lengths, whose specified endpoints and control points are exactly at the same position,
	 * are skipped).<br>
	 * There are three possible values for this property: round, bevel and miter. By default this property is set to miter.
	 * 
	 * @return There are three possible values for this property: round, bevel and miter. By default this property is set to
	 *         miter. Default is {@link org.pepstock.charba.client.enums.JoinStyle#miter}.
	 * @see org.pepstock.charba.client.enums.JoinStyle
	 */
	public JoinStyle getBorderJoinStyle() {
		return AssignHelper.deserialize(AssignHelper.check(delegated.getBorderJoinStyle(), defaultValues.getBorderJoinStyle()), JoinStyle.class, JoinStyle.miter);
	}

	/**
	 * Sets <code>true</code> to keep Bezier control inside the chart, <code>false</code> for no restriction.
	 * 
	 * @param capBezierPoints <code>true</code> to keep Bezier control inside the chart, <code>false</code> for no restriction.
	 */
	public void setCapBezierPoints(boolean capBezierPoints) {
		delegated.setCapBezierPoints(capBezierPoints);
	}

	/**
	 * Returns <code>true</code> to keep Bezier control inside the chart, <code>false</code> for no restriction.
	 * 
	 * @return <code>true</code> to keep Bezier control inside the chart, <code>false</code> for no restriction. Default is <code>true</code>.
	 */
	public boolean isCapBezierPoints() {
		return AssignHelper.check(delegated.isCapBezierPoints(), defaultValues.isCapBezierPoints());
	}

	/**
	 * Sets how to fill the area under the line.
	 * 
	 * @param fill how to fill the area under the line.
	 */
	public void setFill(boolean fill) {
		delegated.setFill(fill);
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
			delegated.setFill(false);
		} else {
			// sets value
			delegated.setFill(fill.name());
		}
	}

	/**
	 * Returns how to fill the area under the line.
	 * 
	 * @return how to fill the area under the line. Default is {@link org.pepstock.charba.client.enums.Fill#origin}.
	 * @see org.pepstock.charba.client.enums.Fill
	 */
	public Fill getFill() {
		String value = delegated.getFill() != null ? delegated.getFill().toString() : defaultValues.getFill();
		// if is a boolean FALSE value
		if (value.equalsIgnoreCase(Boolean.FALSE.toString())) {
			// returns no fill
			return Fill.nofill;
		} else if (value.equalsIgnoreCase(Boolean.TRUE.toString())) {
			return Fill.origin;
		}
		return AssignHelper.deserialize(value, Fill.class, Fill.origin);
	}
	/**
	 * Sets <code>true</code> to show the line as a stepped line (tension will be ignored).
	 * 
	 * @param stepped <code>true</code> to show the line as a stepped line (tension will be ignored).
	 */
	public void setStepped(boolean stepped) {
		delegated.setStepped(stepped);
	}

	/**
	 * Returns <code>true</code> to show the line as a stepped line (tension will be ignored).
	 * 
	 * @return <code>true</code> to show the line as a stepped line (tension will be ignored). Default is <code>false</code>.
	 */
	public boolean isStepped() {
		return AssignHelper.check(delegated.isStepped(), defaultValues.isStepped());
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.commons.IsDelegated#getDelegated()
	 */
	@Override
	public NativeLine getDelegated() {
		return delegated;
	}

}