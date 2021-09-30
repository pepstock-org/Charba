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
package org.pepstock.charba.client.utils.toast;

import java.util.List;

import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.dom.enums.BorderStyle;

/**
 * Configures style of the toast actions.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class Action extends AbstractContentElement implements IsDefaultAction {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		BACKGROUND_COLOR("backgroundColor"),
		BORDER_COLOR("borderColor"),
		BORDER_RADIUS("borderRadius"),
		BORDER_STYLE("borderStyle"),
		BORDER_WIDTH("borderWidth");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use in the native object.
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

	// default values instance
	private final IsDefaultAction defaultValues;

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param options plugins options of the chart.
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	Action(AbstractReadOnlyToastOptions options, Key childKey, IsDefaultAction defaultValues, NativeObject nativeObject) {
		super(options, childKey, defaultValues, nativeObject);
		// checks and gets default
		this.defaultValues = checkDefaultValuesArgument(defaultValues);
	}

	/**
	 * Sets the background color.
	 * 
	 * @param backgroundColor the background color.
	 */
	public final void setBackgroundColor(IsColor backgroundColor) {
		setBackgroundColor(IsColor.checkAndGetValue(backgroundColor));
	}

	/**
	 * Sets the background color.
	 * 
	 * @param backgroundColor the background color.
	 */
	public final void setBackgroundColor(String backgroundColor) {
		setValueAndAddToParent(Property.BACKGROUND_COLOR, backgroundColor);
	}

	/**
	 * Returns the background color.
	 * 
	 * @return the background color.
	 */
	@Override
	public final String getBackgroundColorAsString() {
		return getValue(Property.BACKGROUND_COLOR, defaultValues.getBackgroundColorAsString());
	}

	/**
	 * Returns the background color.
	 * 
	 * @return the background color.
	 */
	public final IsColor getBackgroundColor() {
		return ColorBuilder.parse(getBackgroundColorAsString());
	}

	/**
	 * Sets the border width.
	 * 
	 * @param borderWidth the border width.
	 */
	public final void setBorderWidth(int borderWidth) {
		setValueAndAddToParent(Property.BORDER_WIDTH, Checker.positiveOrZero(borderWidth));
	}

	/**
	 * Returns the border width.
	 * 
	 * @return the border width.
	 */
	@Override
	public final int getBorderWidth() {
		return getValue(Property.BORDER_WIDTH, defaultValues.getBorderWidth());
	}

	/**
	 * Sets the border color.
	 * 
	 * @param borderColor the border color.
	 */
	public final void setBorderColor(IsColor borderColor) {
		setBorderColor(IsColor.checkAndGetValue(borderColor));
	}

	/**
	 * Sets the border color.
	 * 
	 * @param borderColor the border color.
	 */
	public final void setBorderColor(String borderColor) {
		setValueAndAddToParent(Property.BORDER_COLOR, borderColor);
	}

	/**
	 * Returns the border color.
	 * 
	 * @return the border color.
	 */
	@Override
	public final String getBorderColorAsString() {
		return getValue(Property.BORDER_COLOR, defaultValues.getBorderColorAsString());
	}

	/**
	 * Returns the border color.
	 * 
	 * @return the border color.
	 */
	public final IsColor getBorderColor() {
		return ColorBuilder.parse(getBorderColorAsString());
	}

	/**
	 * Sets the border radius (in pixels) of toast action.
	 * 
	 * @param borderRadius the border radius (in pixels) of toast action
	 */
	public final void setBorderRadius(int borderRadius) {
		setValue(Property.BORDER_RADIUS, Checker.positiveOrZero(borderRadius));
	}

	/**
	 * Returns the border radius (in pixels).
	 * 
	 * @return the border radius (in pixels).
	 */
	@Override
	public final int getBorderRadius() {
		return getValue(Property.BORDER_RADIUS, defaultValues.getBorderRadius());
	}

	/**
	 * Sets the border styles set for the action element.<br>
	 * This property can have from one to four values.<br>
	 * Examples:<br>
	 * <ul>
	 * <li><b>setBorderStyle(BorderStyle.DOTTED, BorderStyle.SOLID, BorderStyle.DOUBLE, BorderStyle.DASHED);</b><br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<code>top border is dotted</code><br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<code>right border is solid</code><br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<code>bottom border is double</code><br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<code>left border is dashed</code><br>
	 * <li><b>setBorderStyle(BorderStyle.DOTTED, BorderStyle.SOLID, BorderStyle.DOUBLE);</b><br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<code>top border is dotted</code><br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<code>right and left borders are solid</code><br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<code>bottom border is double</code><br>
	 * <li><b>setBorderStyle(BorderStyle.DOTTED, BorderStyle.SOLID);</b><br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<code>top and bottom borders are dotted</code><br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<code>right and left borders are solid</code><br>
	 * <li><b>setBorderStyle(BorderStyle.DOTTED);</b><br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<code>all four borders are dotted</code><br>
	 * </ul>
	 * 
	 * @param style the border styles set for the action element.<br>
	 *            This property can have from one to four values.
	 */
	public final void setBorderStyle(BorderStyle... style) {
		setArrayValue(Property.BORDER_STYLE, ArrayString.fromOrEmpty(style));
	}

	/**
	 * Sets the border styles set for the action element.<br>
	 * This property can have from one to four values.<br>
	 * Examples:<br>
	 * <ul>
	 * <li><b>setBorderStyle(Arrays.asList(BorderStyle.DOTTED, BorderStyle.SOLID, BorderStyle.DOUBLE, BorderStyle.DASHED));</b><br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<code>top border is dotted</code><br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<code>right border is solid</code><br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<code>bottom border is double</code><br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<code>left border is dashed</code><br>
	 * <li><b>setBorderStyle(Arrays.asList(BorderStyle.DOTTED, BorderStyle.SOLID, BorderStyle.DOUBLE));</b><br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<code>top border is dotted</code><br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<code>right and left borders are solid</code><br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<code>bottom border is double</code><br>
	 * <li><b>setBorderStyle(Arrays.asList(BorderStyle.DOTTED, BorderStyle.SOLID));</b><br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<code>top and bottom borders are dotted</code><br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<code>right and left borders are solid</code><br>
	 * <li><b>setBorderStyle(Arrays.asList(BorderStyle.DOTTED));</b><br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<code>all four borders are dotted</code><br>
	 * </ul>
	 * 
	 * @param style the border styles set for the action element.<br>
	 *            This property can have from one to four values.
	 */
	public final void setBorderStyle(List<BorderStyle> style) {
		// checks if argument is consistent
		if (style != null) {
			// stores the array
			setBorderStyle(style.toArray(new BorderStyle[0]));
		} else {
			// if here, the argument is null
			// then removes the property
			remove(Property.BORDER_STYLE);
		}
	}

	/**
	 * Returns the border styles set for the action element.
	 * 
	 * @return the border styles set for the action element
	 */
	@Override
	public final List<BorderStyle> getBorderStyle() {
		ArrayString array = getValueOrArray(Property.BORDER_STYLE, BorderStyle.NONE);
		return ArrayListHelper.list(BorderStyle.values(), array);
	}
}