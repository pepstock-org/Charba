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
package org.pepstock.charba.client.options.scales;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.ChartContainer;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.FontStyle;

/**
 * When creating a chart, you want to tell the viewer what data they are viewing. To do this, you need to label the axis.<br>
 * The scale label configuration defines options for the scale title. Note that this only applies to cartesian axes.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class CartesianScaleLabel extends ChartContainer {

	private final Padding padding;
	
	private final Axis axis;
	
	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
		display,
		labelString,
		lineHeight,
		padding,
		fontSize,
		fontStyle,
		fontColor,
		fontFamily,
	}

	/**
	 * Builds the object storing the chart instance and the axis which this scale label belongs to.
	 * 
	 * @param chart chart instance.
	 * @param axis axis which this scale label belongs to.
	 */
	CartesianScaleLabel(AbstractChart<?, ?> chart, Axis axis) {
		super(chart);
		this.axis = axis;
		padding = new Padding(chart, axis);
		setValue(Property.padding, padding);
	}
	
	/**
	 * @return the padding
	 */
	public Padding getPadding() {
		return padding;
	}

	/**
	 * f true, display the axis title.
	 * 
	 * @param display f true, display the axis title.
	 */
	public void setDisplay(boolean display) {
		setValue(Property.display, display);
	}

	/**
	 * f true, display the axis title.
	 * 
	 * @return f true, display the axis title.
	 */
	public boolean isDisplay() {
		return getValue(Property.display, axis.getScale().getScaleLabel().isDisplay());
	}

	/**
	 * Sets the text for the title.
	 * 
	 * @param labelString The text for the title.
	 */
	public void setLabelString(String labelString) {
		setValue(Property.labelString, labelString);
	}

	/**
	 * Returns the text for the title.
	 * 
	 * @return The text for the title. 
	 */
	public String getLabelString() {
		return getValue(Property.labelString, axis.getScale().getScaleLabel().getLabelString());
	}

	/**
	 * Sets the height of an individual line of text.
	 * 
	 * @param lineHeight Height of an individual line of text.
	 */
	public void setLineHeight(double lineHeight) {
		setValue(Property.lineHeight, lineHeight);
	}

	/**
	 * Returns the height of an individual line of text.
	 * 
	 * @return the height of an individual line of text.
	 */
	public double getLineHeight() {
		return getValue(Property.lineHeight, axis.getScale().getScaleLabel().getLineHeight());
	}

	/**
	 * Sets the font size for scale title.
	 * 
	 * @param fontSize Font size for scale title.
	 */
	public void setFontSize(int fontSize) {
		setValue(Property.fontSize, fontSize);
	}

	/**
	 * Returns the font size for scale title.
	 * 
	 * @return Font size for scale title.
	 */
	public int getFontSize() {
		return getValue(Property.fontSize, axis.getScale().getScaleLabel().getFontSize());
	}

	/**
	 * Sets the font style for the scale title, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @param fontStyle Font style for the scale title, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * @see org.pepstock.charba.client.enums.FontStyle
	 */
	public void setFontStyle(FontStyle fontStyle) {
		setValue(Property.fontStyle, fontStyle);
	}

	/**
	 * Returns the font style for the scale title, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @return the font style for the scale title, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit). 
	 * @see org.pepstock.charba.client.enums.FontStyle
	 */
	public FontStyle getFontStyle() {
		return getValue(Property.fontStyle, FontStyle.class, axis.getScale().getScaleLabel().getFontStyle());
	}

	/**
	 * Sets the font color for scale title
	 * 
	 * @param fontColor Font color for scale title
	 */
	public void setFontColor(IsColor fontColor) {
		setFontColor(fontColor.toRGBA());
	}

	/**
	 * Sets the font color for scale title
	 * 
	 * @param fontColor Font color for scale title
	 */
	public void setFontColor(String fontColor) {
		setValue(Property.fontColor, fontColor);
	}

	/**
	 * Returns the font color for scale title
	 * 
	 * @return Font color for scale title. 
	 */
	public String getFontColorAsString() {
		return getValue(Property.fontColor, axis.getScale().getScaleLabel().getFontColorAsString());
	}

	/**
	 * Returns the font color for scale title
	 * 
	 * @return Font color for scale title. 
	 */
	public IsColor getFontColor() {
		return ColorBuilder.parse(getFontColorAsString());
	}

	/**
	 * Sets the font family for the scale title, follows CSS font-family options.
	 * 
	 * @param fontFamily Font family for the scale title, follows CSS font-family options.
	 */
	public void setFontFamily(String fontFamily) {
		setValue(Property.fontFamily, fontFamily);
	}

	/**
	 * Returns the font family for the scale title, follows CSS font-family options.
	 * 
	 * @return Font family for the scale title, follows CSS font-family options. 
	 */
	public String getFontFamily() {
		return getValue(Property.fontFamily, axis.getScale().getScaleLabel().getFontFamily());
	}

}