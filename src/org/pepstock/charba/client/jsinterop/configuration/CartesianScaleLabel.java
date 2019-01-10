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
package org.pepstock.charba.client.jsinterop.configuration;

import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.enums.FontStyle;

/**
 * When creating a chart, you want to tell the viewer what data they are viewing. To do this, you need to label the
 * getAxis().<br>
 * The scale label configuration defines options for the scale label. Note that this only applies to cartesian axes.
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 *
 */
public class CartesianScaleLabel extends AxisContainer {

	private final CartesianPadding padding;

	/**
	 * Builds the object storing the axis which this scale label belongs to.
	 * 
	 * @param axis axis which this scale label belongs to.
	 */
	CartesianScaleLabel(Axis axis) {
		super(axis);
		padding = new CartesianPadding(axis);
	}

	/**
	 * @return the padding
	 */
	public CartesianPadding getPadding() {
		return padding;
	}

	/**
	 * If true, display the axis title.
	 * 
	 * @param display If true, display the axis title.
	 */
	public void setDisplay(boolean display) {
		getAxis().getScale().getScaleLabel().setDisplay(display);
	}

	/**
	 * If true, display the axis title.
	 * 
	 * @return If true, display the axis title.
	 */
	public boolean isDisplay() {
		return getAxis().getScale().getScaleLabel().isDisplay();
	}

	/**
	 * Sets the text for the label.
	 * 
	 * @param labelString The text for the label.
	 */
	public void setLabelString(String labelString) {
		getAxis().getScale().getScaleLabel().setLabelString(labelString);
	}

	/**
	 * Returns the text for the label.
	 * 
	 * @return The text for the label.
	 */
	public String getLabelString() {
		return getAxis().getScale().getScaleLabel().getLabelString();
	}

	/**
	 * Sets the height of an individual line of text.
	 * 
	 * @param lineHeight Height of an individual line of text.
	 */
	public void setLineHeight(double lineHeight) {
		getAxis().getScale().getScaleLabel().setLineHeight(lineHeight);
	}

	/**
	 * Returns the height of an individual line of text.
	 * 
	 * @return the height of an individual line of text.
	 */
	public double getLineHeight() {
		return getAxis().getScale().getScaleLabel().getLineHeight();
	}

	/**
	 * Sets the font size for scale label.
	 * 
	 * @param fontSize Font size for scale label.
	 */
	public void setFontSize(int fontSize) {
		getAxis().getScale().getScaleLabel().setFontSize(fontSize);
	}

	/**
	 * Returns the font size for scale label.
	 * 
	 * @return Font size for scale label.
	 */
	public int getFontSize() {
		return getAxis().getScale().getScaleLabel().getFontSize();
	}

	/**
	 * Sets the font style for the scale label, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @param fontStyle Font style for the scale label, follows CSS font-style options (i.e. normal, italic, oblique, initial,
	 *            inherit).
	 */
	public void setFontStyle(FontStyle fontStyle) {
		getAxis().getScale().getScaleLabel().setFontStyle(fontStyle);
	}

	/**
	 * Returns the font style for the scale label, follows CSS font-style options (i.e. normal, italic, oblique, initial,
	 * inherit).
	 * 
	 * @return the font style for the scale label, follows CSS font-style options (i.e. normal, italic, oblique, initial,
	 *         inherit).
	 */
	public FontStyle getFontStyle() {
		return getAxis().getScale().getScaleLabel().getFontStyle();
	}

	/**
	 * Sets the font color for scale label
	 * 
	 * @param fontColor Font color for scale label
	 */
	public void setFontColor(IsColor fontColor) {
		getAxis().getScale().getScaleLabel().setFontColor(fontColor);
	}

	/**
	 * Sets the font color for scale label
	 * 
	 * @param fontColor Font color for scale label
	 */
	public void setFontColor(String fontColor) {
		getAxis().getScale().getScaleLabel().setFontColor(fontColor);
	}

	/**
	 * Returns the font color for scale label
	 * 
	 * @return Font color for scale label.
	 */
	public String getFontColorAsString() {
		return getAxis().getScale().getScaleLabel().getFontColorAsString();
	}

	/**
	 * Returns the font color for scale label
	 * 
	 * @return Font color for scale label.
	 */
	public IsColor getFontColor() {
		return getAxis().getScale().getScaleLabel().getFontColor();
	}

	/**
	 * Sets the font family for the scale label, follows CSS font-family options.
	 * 
	 * @param fontFamily Font family for the scale label, follows CSS font-family options.
	 */
	public void setFontFamily(String fontFamily) {
		getAxis().getScale().getScaleLabel().setFontFamily(fontFamily);
	}

	/**
	 * Returns the font family for the scale label, follows CSS font-family options.
	 * 
	 * @return Font family for the scale label, follows CSS font-family options.
	 */
	public String getFontFamily() {
		return getAxis().getScale().getScaleLabel().getFontFamily();
	}

}