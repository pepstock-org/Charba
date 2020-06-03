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
package org.pepstock.charba.client.configuration;

import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.options.Ticks;

/**
 * Specific tick with minimum and maximum sub ticks.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
abstract class Tick extends AxisContainer {
	
	// the axis instance, owner of this tick
	private final Ticks configuration;

	private final Major major;
	
	/**
	 * Name of properties of native object.
	 */
	enum Property implements Key
	{
		CALLBACK("callback");

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
	 * Builds the object storing the axis which this tick belongs to.
	 * 
	 * @param axis axis which this tick belongs to.
	 */
	Tick(Axis axis) {
		super(axis);
		// stores the options element
		this.configuration = axis.getScale().getTicks();
		// creates sub element, min and max
		major = new Major(axis, axis.getScale().getTicks());
	}
	
	/**
	 * Returns the options element for tick.
	 * 
	 * @return the configuration
	 */
	final Ticks getConfiguration() {
		return configuration;
	}

	/**
	 * Returns major tick element.
	 * 
	 * @return the major
	 */
	public Major getMajor() {
		return major;
	}

	/**
	 * If true, show tick marks.
	 * 
	 * @param display if true, show tick marks
	 */
	public void setDisplay(boolean display) {
		getConfiguration().setDisplay(display);
	}

	/**
	 * If true, show tick marks
	 * 
	 * @return if true, show tick marks.
	 */
	public boolean isDisplay() {
		return getConfiguration().isDisplay();
	}

	/**
	 * Sets z-index of tick layer. Useful when ticks are drawn on chart area. Values less than or equals to 0 are drawn under datasets, greater than 0 on top.
	 * 
	 * @param z z-index of tick layer. Useful when ticks are drawn on chart area. Values less than or equals to 0 are drawn under datasets, greater than 0 on top.
	 */
	public void setZ(int z) {
		getConfiguration().setZ(z);
	}

	/**
	 * Returns z-index of tick layer. Useful when ticks are drawn on chart area. Values less than or equals to 0 are drawn under datasets, greater than 0 on top.
	 * 
	 * @return z-index of tick layer. Useful when ticks are drawn on chart area. Values less than or equals to 0 are drawn under datasets, greater than 0 on top.
	 */
	public int getZ() {
		return getConfiguration().getZ();
	}
	
	/**
	 * Sets the font size for tick.
	 * 
	 * @param fontSize the font size for tick.
	 */
	public void setFontSize(int fontSize) {
		configuration.setFontSize(fontSize);
	}

	/**
	 * Returns the font size for tick.
	 * 
	 * @return the font size for tick.
	 */
	public int getFontSize() {
		return configuration.getFontSize();
	}

	/**
	 * Sets the font style for the tick, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @param fontStyle Font style for the tick, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 */
	public void setFontStyle(FontStyle fontStyle) {
		configuration.setFontStyle(fontStyle);
	}

	/**
	 * Returns the font style for the tick, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @return the font style for the tick, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 */
	public FontStyle getFontStyle() {
		return configuration.getFontStyle();
	}

	/**
	 * Sets the font color for tick
	 * 
	 * @param fontColor Font color for tick
	 */
	public void setFontColor(IsColor fontColor) {
		configuration.setFontColor(fontColor);
	}

	/**
	 * Sets the font color for tick
	 * 
	 * @param fontColor Font color for tick
	 */
	public void setFontColor(String fontColor) {
		configuration.setFontColor(fontColor);
	}

	/**
	 * Returns the font color for tick
	 * 
	 * @return Font color for tick.
	 */
	public String getFontColorAsString() {
		return configuration.getFontColorAsString();
	}

	/**
	 * Returns the font color for tick
	 * 
	 * @return Font color for tick.
	 */
	public IsColor getFontColor() {
		return configuration.getFontColor();
	}

	/**
	 * Sets the font family for the tick, follows CSS font-family options.
	 * 
	 * @param fontFamily Font family for the tick, follows CSS font-family options.
	 */
	public void setFontFamily(String fontFamily) {
		configuration.setFontFamily(fontFamily);
	}

	/**
	 * Returns the font family for the tick, follows CSS font-family options.
	 * 
	 * @return Font family for the tick, follows CSS font-family options.
	 */
	public String getFontFamily() {
		return configuration.getFontFamily();
	}

	/**
	 * Sets the height of an individual line of text.
	 * 
	 * @param lineHeight height of an individual line of text.
	 */
	public void setLineHeight(double lineHeight) {
		getConfiguration().setLineHeight(lineHeight);
	}

	/**
	 * Sets the height of an individual line of text.
	 * 
	 * @param lineHeight height of an individual line of text.
	 */
	public void setLineHeight(String lineHeight) {
		getConfiguration().setLineHeight(lineHeight);
	}

	/**
	 * Returns the height of an individual line of text.
	 * 
	 * @return the height of an individual line of text.
	 */
	public double getLineHeight() {
		return getConfiguration().getLineHeight();
	}

	/**
	 * Returns the height of an individual line of text.
	 * 
	 * @return the height of an individual line of text.
	 */
	public String getLineHeightAsString() {
		return getConfiguration().getLineHeightAsString();
	}
	
	/**
	 * Sets the padding between the tick label and the axis. When set on a vertical axis, this applies in the horizontal (X) direction. When set on a horizontal axis, this applies
	 * in the vertical (Y) direction.
	 * 
	 * @param padding padding between the tick label and the axis. When set on a vertical axis, this applies in the horizontal (X) direction. When set on a horizontal axis, this
	 *            applies in the vertical (Y) direction.
	 */
	public void setPadding(int padding) {
		getConfiguration().setPadding(padding);
	}

	/**
	 * Returns the padding between the tick label and the axis. When set on a vertical axis, this applies in the horizontal (X) direction. When set on a horizontal axis, this
	 * applies in the vertical (Y) direction.
	 * 
	 * @return padding between the tick label and the axis. When set on a vertical axis, this applies in the horizontal (X) direction. When set on a horizontal axis, this applies
	 *         in the vertical (Y) direction.
	 */
	public int getPadding() {
		return getConfiguration().getPadding();
	}

}