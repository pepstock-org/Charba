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
package org.pepstock.charba.client.annotation;

import java.util.Date;
import java.util.List;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.annotation.enums.AnnotationType;
import org.pepstock.charba.client.annotation.enums.LineMode;
import org.pepstock.charba.client.colors.Color;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.ArrayInteger;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.Key;

/**
 * Implements a LINE annotation which draws a line into a chart.<br>
 * Vertical or horizontal lines are supported.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class LineAnnotation extends AbstractAnnotation implements IsDefaultsLineAnnotation {

	/**
	 * Default line annotation ID, <b>{@value DEFAULT_ID}</b>.
	 */
	public static final String DEFAULT_ID = "a-line-1";

	/**
	 * Default line annotation border color, <b>rgb(54, 162, 235)</b>.
	 */
	public static final IsColor DEFAULT_BORDER_COLOR = new Color(54, 162, 235);

	/**
	 * Default line annotation border color as string, <b>rgb(54, 162, 235)</b>.
	 */
	public static final String DEFAULT_BORDER_COLOR_AS_STRING = DEFAULT_BORDER_COLOR.toRGB();

	/**
	 * Default line annotation border width, <b>{@value DEFAULT_BORDER_WIDTH}</b>.
	 */
	public static final int DEFAULT_BORDER_WIDTH = 2;

	/**
	 * Default line annotation mode, <b>{@link LineMode#VERTICAL}</b>.
	 */
	public static final LineMode DEFAULT_MODE = LineMode.VERTICAL;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		MODE("mode"),
		VALUE("value"),
		END_VALUE("endValue"),
		SCALE_ID("scaleID"),
		BORDER_DASH("borderDash"),
		BORDER_DASH_OFFSET("borderDashOffset"),
		LABEL("label");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use into native object.
		 * 
		 * @param value value of property name
		 */
		private Property(String value) {
			this.value = value;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.Key#value()
		 */
		@Override
		public String value() {
			return value;
		}

	}

	// lable for line instance
	private final LineLabel label;

	/**
	 * Creates a line annotation to be added to an {@link AnnotationOptions} instance.
	 */
	public LineAnnotation() {
		this((DefaultsOptions) null);
	}

	/**
	 * Creates a line annotation to be added to an {@link AnnotationOptions} instance, relating to chart instance for default.
	 * 
	 * @param chart chart instance related to the plugin options
	 */
	public LineAnnotation(IsChart chart) {
		this(IsChart.isConsistent(chart) ? chart.getDefaultChartOptions().getPlugins().getOptions(AnnotationPlugin.ID, AnnotationPlugin.DEFAULTS_FACTORY) : null);
	}

	/**
	 * Creates a line annotation to be added to an {@link AnnotationOptions} instance.
	 * 
	 * @param defaultsOptions default options stored into defaults global
	 */
	private LineAnnotation(DefaultsOptions defaultsOptions) {
		super(AnnotationType.LINE, defaultsOptions);
		// creates a line label
		label = new LineLabel();
		// stores into annotation
		setValue(Property.LABEL, label);
	}

	/**
	 * Returns the label on the line.
	 * 
	 * @return the label on the line
	 */
	public LineLabel getLabel() {
		return label;
	}

	/**
	 * Returns the color of the border of annotation.
	 * 
	 * @return the color of the border of annotation
	 */
	@Override
	public String getBorderColorAsString() {
		// returns color as string
		return getValue(AbstractAnnotation.Property.BORDER_COLOR, IsDefaultsLineAnnotation.super.getBorderColorAsString());
	}

	/**
	 * Returns the width of the border in pixels.
	 * 
	 * @return the width of the border in pixels.
	 */
	@Override
	public int getBorderWidth() {
		return getValue(AbstractAnnotation.Property.BORDER_WIDTH, IsDefaultsLineAnnotation.super.getBorderWidth());
	}

	/**
	 * Sets the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 * 
	 * @param borderDash the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 */
	public void setBorderDash(int... borderDash) {
		// stores value
		setArrayValue(Property.BORDER_DASH, ArrayInteger.fromOrNull(borderDash));
	}

	/**
	 * Returns the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 * 
	 * @return the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 */
	@Override
	public List<Integer> getBorderDash() {
		// checks if there is the property
		if (has(Property.BORDER_DASH)) {
			ArrayInteger array = getArrayValue(Property.BORDER_DASH);
			return ArrayListHelper.list(array);
		}
		// if here, the property i smissing
		return IsDefaultsLineAnnotation.super.getBorderDash();
	}

	/**
	 * Sets the line dash pattern offset or "phase".
	 * 
	 * @param borderDashOffset the line dash pattern offset or "phase".
	 */
	public void setBorderDashOffset(int borderDashOffset) {
		// stores value
		setValue(Property.BORDER_DASH_OFFSET, borderDashOffset);
	}

	/**
	 * Returns the line dash pattern offset or "phase".
	 * 
	 * @return the line dash pattern offset or "phase".
	 */
	@Override
	public int getBorderDashOffset() {
		return getValue(Property.BORDER_DASH_OFFSET, IsDefaultsLineAnnotation.super.getBorderDashOffset());
	}

	/**
	 * Sets the orientation (horizontal or vertical) of the line.
	 * 
	 * @param mode the orientation (horizontal or vertical) of the line
	 */
	public void setMode(LineMode mode) {
		setValue(Property.MODE, mode);
	}

	/**
	 * Returns the orientation (horizontal or vertical) of the line.
	 * 
	 * @return the orientation (horizontal or vertical) of the line
	 */
	@Override
	public LineMode getMode() {
		return getValue(Property.MODE, LineMode.values(), IsDefaultsLineAnnotation.super.getMode());
	}

	/**
	 * Sets the ID of the scale to bind onto.
	 * 
	 * @param scaleId the ID of the scale to bind onto
	 */
	public void setScaleID(String scaleId) {
		setValue(LineAnnotation.Property.SCALE_ID, scaleId);
	}

	/**
	 * Returns the ID of the scale to bind onto.
	 * 
	 * @return the ID of the scale to bind onto
	 */
	@Override
	public String getScaleID() {
		return getValue(Property.SCALE_ID, IsDefaultsLineAnnotation.super.getScaleID());
	}

	/**
	 * Sets the data value to draw the line at.
	 * 
	 * @param value the data value to draw the line at
	 */
	public void setValue(String value) {
		setValue(Property.VALUE, value);
	}

	/**
	 * Sets the data value to draw the line at.
	 * 
	 * @param value the data value to draw the line at
	 */
	public void setValue(double value) {
		setValue(Property.VALUE, value);
	}

	/**
	 * Sets the data value to draw the line at.
	 * 
	 * @param value the data value to draw the line at
	 */
	public void setValue(Date value) {
		setValue(Property.VALUE, value);
	}

	/**
	 * Returns the data value to draw the line at.
	 * 
	 * @return the data value to draw the line at
	 */
	@Override
	public String getValueAsString() {
		return getValueForMultipleKeyTypes(Property.VALUE, IsDefaultsLineAnnotation.super.getValueAsString());
	}

	/**
	 * Returns the data value to draw the line at.
	 * 
	 * @return the data value to draw the line at
	 */
	@Override
	public double getValueAsDouble() {
		return getValueForMultipleKeyTypes(Property.VALUE, IsDefaultsLineAnnotation.super.getValueAsDouble());
	}

	/**
	 * Returns the data value to draw the line at.
	 * 
	 * @return the data value to draw the line at
	 */
	@Override
	public Date getValueAsDate() {
		return getValueForMultipleKeyTypes(Property.VALUE, IsDefaultsLineAnnotation.super.getValueAsDate());
	}

	/**
	 * Sets the data value at which the line draw should end.
	 * 
	 * @param endValue the data value at which the line draw should end
	 */
	public void setEndValue(String endValue) {
		setValue(Property.END_VALUE, endValue);
	}

	/**
	 * Sets the data value at which the line draw should end.
	 * 
	 * @param endValue the data value at which the line draw should end
	 */
	public void setEndValue(double endValue) {
		setValue(Property.END_VALUE, endValue);
	}

	/**
	 * Sets the data value at which the line draw should end.
	 * 
	 * @param endValue the data value at which the line draw should end
	 */
	public void setEndValue(Date endValue) {
		setValue(Property.END_VALUE, endValue);
	}

	/**
	 * Returns the data value at which the line draw should end.
	 * 
	 * @return the data value at which the line draw should end
	 */
	@Override
	public String getEndValueAsString() {
		return getValueForMultipleKeyTypes(Property.END_VALUE, IsDefaultsLineAnnotation.super.getEndValueAsString());
	}

	/**
	 * Returns the data value at which the line draw should end.
	 * 
	 * @return the data value at which the line draw should end
	 */
	@Override
	public double getEndValueAsDouble() {
		return getValueForMultipleKeyTypes(Property.END_VALUE, IsDefaultsLineAnnotation.super.getEndValueAsDouble());
	}

	/**
	 * Returns the data value at which the line draw should end.
	 * 
	 * @return the data value at which the line draw should end
	 */
	@Override
	public Date getEndValueAsDate() {
		return getValueForMultipleKeyTypes(Property.END_VALUE, IsDefaultsLineAnnotation.super.getEndValueAsDate());
	}

}
