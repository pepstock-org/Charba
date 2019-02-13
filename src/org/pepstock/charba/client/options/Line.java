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
package org.pepstock.charba.client.options;

import java.util.List;

import org.pepstock.charba.client.commons.ArrayInteger;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.IsDefaultLine;
import org.pepstock.charba.client.enums.AbsoluteDatasetIndexFill;
import org.pepstock.charba.client.enums.CapStyle;
import org.pepstock.charba.client.enums.Fill;
import org.pepstock.charba.client.enums.FillingMode;
import org.pepstock.charba.client.enums.IsFill;
import org.pepstock.charba.client.enums.JoinStyle;
import org.pepstock.charba.client.enums.RelativeDatasetIndexFill;

/**
 * Line elements are used to represent the line in a line chart.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class Line extends AbstractElement<IsDefaultLine> implements IsDefaultLine {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		tension,
		borderCapStyle,
		borderDash,
		borderDashOffset,
		borderJoinStyle,
		capBezierPoints,
		fill,
		stepped,
		// internal property key to map the type of FILL property
		_charbaFillingMode
	}

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script
	 * properties.
	 * 
	 * @param elements parent node to use to add this element where changed
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	Line(Elements elements, Key childKey, IsDefaultLine defaultValues, NativeObject nativeObject) {
		super(elements, childKey, defaultValues, nativeObject);
	}

	/**
	 * Returns the Bezier curve tension (0 for no Bezier curves).
	 * 
	 * @param tension the Bezier curve tension (0 for no Bezier curves).
	 */
	public void setTension(double tension) {
		setValue(Property.tension, tension);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the Bezier curve tension (0 for no Bezier curves).
	 * 
	 * @return the Bezier curve tension (0 for no Bezier curves).
	 */
	public double getTension() {
		return getValue(Property.tension, getDefaultValues().getTension());
	}

	/**
	 * Sets how the end points of every line are drawn. There are three possible values for this property and those are: butt,
	 * round and square.
	 * 
	 * @param borderCapStyle how the end points of every line are drawn.
	 */
	public void setBorderCapStyle(CapStyle borderCapStyle) {
		setValue(Property.borderCapStyle, borderCapStyle);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns how the end points of every line are drawn. There are three possible values for this property and those are:
	 * butt, round and square. By default this property is set to butt.
	 * 
	 * @return how the end points of every line are drawn.
	 */
	public CapStyle getBorderCapStyle() {
		return getValue(Property.borderCapStyle, CapStyle.class, getDefaultValues().getBorderCapStyle());
	}

	/**
	 * Sets the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines
	 * and gaps which describe the pattern.
	 * 
	 * @param borderDash the line dash pattern used when stroking lines, using an array of values which specify alternating
	 *            lengths of lines and gaps which describe the pattern.
	 */
	public void setBorderDash(int... borderDash) {
		// checks if argument is consistent
		if (borderDash != null && borderDash.length > 0) {
			setArrayValue(Property.borderDash, ArrayInteger.of(borderDash));
		} else {
			// if here, argument is null
			// then removes property
			remove(Property.borderDash);
		}
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
		ArrayInteger array = getArrayValue(Property.borderDash);
		return ArrayListHelper.list(array);
	}

	/**
	 * Sets the line dash pattern offset or "phase".
	 * 
	 * @param borderDashOffset the line dash pattern offset or "phase".
	 */
	public void setBorderDashOffset(int borderDashOffset) {
		setValue(Property.borderDashOffset, borderDashOffset);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the line dash pattern offset or "phase".
	 * 
	 * @return the line dash pattern offset or "phase".
	 */
	public int getBorderDashOffset() {
		return getValue(Property.borderDashOffset, getDefaultValues().getBorderDashOffset());
	}

	/**
	 * Sets how two connecting segments (of lines, arcs or curves) with non-zero lengths in a shape are joined together
	 * (degenerate segments with zero lengths, whose specified end points and control points are exactly at the same position,
	 * are skipped).<br>
	 * There are three possible values for this property: round, bevel and miter. By default this property is set to miter.
	 * 
	 * @param borderJoinStyle there are three possible values for this property: round, bevel and miter.
	 */
	public void setBorderJoinStyle(JoinStyle borderJoinStyle) {
		setValue(Property.borderJoinStyle, borderJoinStyle);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns how two connecting segments (of lines, arcs or curves) with non-zero lengths in a shape are joined together
	 * (degenerate segments with zero lengths, whose specified end points and control points are exactly at the same position,
	 * are skipped).<br>
	 * There are three possible values for this property: round, bevel and miter. By default this property is set to miter.
	 * 
	 * @return there are three possible values for this property: round, bevel and miter.
	 */
	public JoinStyle getBorderJoinStyle() {
		return getValue(Property.borderJoinStyle, JoinStyle.class, getDefaultValues().getBorderJoinStyle());
	}

	/**
	 * Sets <code>true</code> to keep Bezier control inside the chart, <code>false</code> for no restriction.
	 * 
	 * @param capBezierPoints <code>true</code> to keep Bezier control inside the chart, <code>false</code> for no restriction.
	 */
	public void setCapBezierPoints(boolean capBezierPoints) {
		setValue(Property.capBezierPoints, capBezierPoints);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns <code>true</code> to keep Bezier control inside the chart, <code>false</code> for no restriction.
	 * 
	 * @return <code>true</code> to keep Bezier control inside the chart, <code>false</code> for no restriction.
	 */
	public boolean isCapBezierPoints() {
		return getValue(Property.capBezierPoints, getDefaultValues().isCapBezierPoints());
	}

	/**
	 * Sets how to fill the area under the line.
	 * 
	 * @param fill <code>true</code> to fill, otherwise <code>false</code>.
	 */
	public void setFill(boolean fill) {
		setValue(Property.fill, fill);
		// stores the filling mode
		setValue(Property._charbaFillingMode, FillingMode.predefinedBoolean);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Sets how to fill the area under the line, by absolute dataset index.
	 * 
	 * @param index absolute dataset index of the chart.
	 */
	public void setFill(int index) {
		setFill(Fill.getFill(index));
	}

	/**
	 * Sets how to fill the area under the line, by relative dataset index.
	 * 
	 * @param index relative dataset index of the chart.
	 */
	public void setFill(String index) {
		setFill(Fill.getFill(index));
	}

	/**
	 * Sets how to fill the area under the line.
	 * 
	 * @param fill how to fill the area under the line.
	 */
	public void setFill(IsFill fill) {
		// checks if is no fill
		if (Fill.nofill.equals(fill)) {
			// sets the boolean value instead of string one
			setValue(Property.fill, false);
			// stores the filling mode
			setValue(Property._charbaFillingMode, FillingMode.predefinedBoolean);
		} else if (Fill.isPredefined(fill)) {
			// sets value
			setValue(Property.fill, fill);
			// stores the filling mode
			setValue(Property._charbaFillingMode, fill.getMode());
		} else if (FillingMode.absoluteDatasetIndex.equals(fill.getMode())) {
			// sets value
			setValue(Property.fill, fill.getValueAsInt());
			// stores the filling mode
			setValue(Property._charbaFillingMode, FillingMode.absoluteDatasetIndex);
		} else if (FillingMode.relativeDatasetIndex.equals(fill.getMode())) {
			// sets value
			setValue(Property.fill, fill.getValue());
			// stores the filling mode
			setValue(Property._charbaFillingMode, FillingMode.relativeDatasetIndex);
		}
		// checks if the node is already added to parent
		checkAndAddToParent();

	}

	/**
	 * Returns how to fill the area under the line.
	 * 
	 * @return how to fill the area under the line.
	 */
	public IsFill getFill() {
		// checks if there is the property
		if (has(Property._charbaFillingMode)) {
			// gets the filling mode
			FillingMode mode = getValue(Property._charbaFillingMode, FillingMode.class, FillingMode.predefined);
			// checks all possible types of filling mode
			// to return the right value
			if (FillingMode.predefinedBoolean.equals(mode)) {
				// returns no fill
				return getValue(Property.fill, false) ? Fill.origin : Fill.nofill;
			} else if (FillingMode.predefined.equals(mode)) {
				// gets the fill value, using null as default
				IsFill fill = getValue(Property.fill, Fill.class, null);
				// if null, returns the default one
				return fill == null ? getDefaultValues().getFill() : fill;
			} else if (FillingMode.absoluteDatasetIndex.equals(mode)) {
				// the default here is 0 because the property must be set
				// setting 0, an exception will be thrown
				return Fill.getFill(getValue(Property.fill, AbsoluteDatasetIndexFill.DEFAULT_VALUE_AS_INT));
			} else if (FillingMode.relativeDatasetIndex.equals(mode)) {
				// the default here is 0 because the property must be set
				// setting 0, an exception will be thrown
				return Fill.getFill(getValue(Property.fill, RelativeDatasetIndexFill.DEFAULT_VALUE));
			}
		}
		// returns the default
		return getDefaultValues().getFill();
	}

	/**
	 * Sets <code>true</code> to show the line as a stepped line (tension will be ignored).
	 * 
	 * @param stepped <code>true</code> to show the line as a stepped line (tension will be ignored).
	 */
	public void setStepped(boolean stepped) {
		setValue(Property.stepped, stepped);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns <code>true</code> to show the line as a stepped line (tension will be ignored).
	 * 
	 * @return <code>true</code> to show the line as a stepped line (tension will be ignored).
	 */
	public boolean isStepped() {
		return getValue(Property.stepped, getDefaultValues().isStepped());
	}
}