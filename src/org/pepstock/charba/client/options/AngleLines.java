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

import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.IsDefaultAngleLines;

/**
 * It is used to configure angled lines that radiate from the center of the chart to the point labels.<br>
 * Note that these options only apply if display is <code>true</code>.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class AngleLines extends AbstractScaleLine<IsDefaultAngleLines> implements IsDefaultAngleLines {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		DISPLAY("display"),
		COLOR("color"),
		LINE_WIDTH("lineWidth");

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

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script
	 * properties.
	 * 
	 * @param scale scale/axis of this angle lines object.
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	AngleLines(Scale scale, Key childKey, IsDefaultAngleLines defaultValues, NativeObject nativeObject) {
		super(scale, childKey, defaultValues, nativeObject);
	}

	/**
	 * If <code>true</code>, angle lines are shown
	 * 
	 * @param display if <code>true</code>, angle lines are shown
	 */
	public void setDisplay(boolean display) {
		setValue(Property.DISPLAY, display);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * If <code>true</code>, angle lines are shown
	 * 
	 * @return if <code>true</code>, angle lines are shown.
	 */
	public boolean isDisplay() {
		return getValue(Property.DISPLAY, getDefaultValues().isDisplay());
	}

	/**
	 * Sets the color of angled lines.
	 * 
	 * @param color color of angled lines.
	 */
	public void setColor(IsColor color) {
		setColor(color.toRGBA());
	}

	/**
	 * Sets the color of angled lines.
	 * 
	 * @param color color of angled lines.
	 */
	public void setColor(String color) {
		setValue(Property.COLOR, color);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the color of angled lines.
	 * 
	 * @return color of angled lines.
	 */
	public String getColorAsString() {
		return getValue(Property.COLOR, getDefaultValues().getColorAsString());
	}

	/**
	 * Returns the color of angled lines.
	 * 
	 * @return color of angled lines.
	 */
	public IsColor getColor() {
		return ColorBuilder.parse(getColorAsString());
	}

	/**
	 * Sets the width of angled lines.
	 * 
	 * @param lineWidth width of angled lines.
	 */
	public void setLineWidth(int lineWidth) {
		setValue(Property.LINE_WIDTH, lineWidth);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the width of angled lines.
	 * 
	 * @return width of angled lines.
	 */
	public int getLineWidth() {
		return getValue(Property.LINE_WIDTH, getDefaultValues().getLineWidth());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.AbstractScaleLine#getDefaultBorderDashOffset()
	 */
	@Override
	int getDefaultBorderDashOffset() {
		return getDefaultValues().getBorderDashOffset();
	}
}