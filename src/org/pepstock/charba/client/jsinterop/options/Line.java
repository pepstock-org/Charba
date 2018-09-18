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
package org.pepstock.charba.client.jsinterop.options;

import java.util.List;

import org.pepstock.charba.client.enums.CapStyle;
import org.pepstock.charba.client.enums.Fill;
import org.pepstock.charba.client.enums.JoinStyle;
import org.pepstock.charba.client.jsinterop.commons.ArrayInteger;
import org.pepstock.charba.client.jsinterop.commons.ArrayListHelper;
import org.pepstock.charba.client.jsinterop.commons.AssignHelper;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultLine;

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
public class Line extends AbstractElement<NativeLine, Elements, IsDefaultLine>{

	public Line(Elements elements,IsDefaultLine defaultValues) {
		this(new NativeLine(), elements, defaultValues);
	}

	Line(NativeLine delegated, Elements elements, IsDefaultLine defaultValues) {
		super(delegated, elements, defaultValues);
	}
	
	/**
	 * Returns the Bezier curve tension (0 for no Bezier curves).
	 * 
	 * @param tension the Bezier curve tension (0 for no Bezier curves).
	 */
	public void setTension(double tension) {
		getDelegated().setTension(tension);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the Bezier curve tension (0 for no Bezier curves).
	 * 
	 * @return the Bezier curve tension (0 for no Bezier curves). Default is 0.4F.
	 */
	public double getTension() {
		return AssignHelper.check(getDelegated().getTension(), getDefaultValues().getTension());
	}

	/**
	 * Sets how the end points of every line are drawn. There are three possible values for this property and those are: butt,
	 * round and square.
	 * 
	 * @param borderCapStyle how the end points of every line are drawn.
	 * @see org.pepstock.charba.client.enums.CapStyle
	 */
	public void setBorderCapStyle(CapStyle borderCapStyle) {
		getDelegated().setBorderCapStyle(borderCapStyle.name());
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns how the end points of every line are drawn. There are three possible values for this property and those are:
	 * butt, round and square. By default this property is set to butt.
	 * 
	 * @return how the end points of every line are drawn. Default is {@link org.pepstock.charba.client.enums.CapStyle#butt}.
	 * @see org.pepstock.charba.client.enums.CapStyle
	 */
	public CapStyle getBorderCapStyle() {
		return AssignHelper.deserialize(AssignHelper.check(getDelegated().getBorderCapStyle(), getDefaultValues().getBorderCapStyle()), CapStyle.class, CapStyle.butt);
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
		getDelegated().setBorderDash(borderDash);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of
	 * lines and gaps which describe the pattern.
	 * 
	 * @return the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of
	 *         lines and gaps which describe the pattern.
	 */
	public List<Integer> getBorderDash() {
		return ArrayListHelper.build(AssignHelper.check(getDelegated().getBorderDash(), getDefaultValues().getBorderDash()));
	}

	/**
	 * Sets the line dash pattern offset or "phase".
	 * 
	 * @param borderDashOffset the line dash pattern offset or "phase".
	 */
	public void setBorderDashOffset(int borderDashOffset) {
		getDelegated().setBorderDashOffset(borderDashOffset);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the line dash pattern offset or "phase".
	 * 
	 * @return the line dash pattern offset or "phase". Default is 0.
	 */
	public int getBorderDashOffset() {
		return AssignHelper.check(getDelegated().getBorderDashOffset(), getDefaultValues().getBorderDashOffset());
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
		getDelegated().setBorderJoinStyle(borderJoinStyle.name());
		// checks if the node is already added to parent
		checkAndAddToParent();
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
		return AssignHelper.deserialize(AssignHelper.check(getDelegated().getBorderJoinStyle(), getDefaultValues().getBorderJoinStyle()), JoinStyle.class, JoinStyle.miter);
	}

	/**
	 * Sets <code>true</code> to keep Bezier control inside the chart, <code>false</code> for no restriction.
	 * 
	 * @param capBezierPoints <code>true</code> to keep Bezier control inside the chart, <code>false</code> for no restriction.
	 */
	public void setCapBezierPoints(boolean capBezierPoints) {
		getDelegated().setCapBezierPoints(capBezierPoints);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns <code>true</code> to keep Bezier control inside the chart, <code>false</code> for no restriction.
	 * 
	 * @return <code>true</code> to keep Bezier control inside the chart, <code>false</code> for no restriction. Default is <code>true</code>.
	 */
	public boolean isCapBezierPoints() {
		return AssignHelper.check(getDelegated().isCapBezierPoints(), getDefaultValues().isCapBezierPoints());
	}

	/**
	 * Sets how to fill the area under the line.
	 * 
	 * @param fill how to fill the area under the line.
	 */
	public void setFill(boolean fill) {
		getDelegated().setFill(fill);
		// checks if the node is already added to parent
		checkAndAddToParent();
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
			getDelegated().setFill(false);
			// checks if the node is already added to parent
			checkAndAddToParent();
		} else {
			// sets value
			getDelegated().setFill(fill.name());
			// checks if the node is already added to parent
			checkAndAddToParent();
		}
	}

	/**
	 * Returns how to fill the area under the line.
	 * 
	 * @return how to fill the area under the line. Default is {@link org.pepstock.charba.client.enums.Fill#origin}.
	 * @see org.pepstock.charba.client.enums.Fill
	 */
	public Fill getFill() {
		String value = getDelegated().getFill() != null ? getDelegated().getFill().toString() : getDefaultValues().getFill();
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
		getDelegated().setStepped(stepped);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns <code>true</code> to show the line as a stepped line (tension will be ignored).
	 * 
	 * @return <code>true</code> to show the line as a stepped line (tension will be ignored). Default is <code>false</code>.
	 */
	public boolean isStepped() {
		return AssignHelper.check(getDelegated().isStepped(), getDefaultValues().isStepped());
	}
	
	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.BaseModel#addToParent()
	 */
	@Override
	protected void addToParent() {
		if (getParent().getDelegated().getArc() == null) {
			getParent().getDelegated().setArc(getDelegated());
		}
	}

}