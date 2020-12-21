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

import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.enums.ScaleLabelAlign;

/**
 * When creating a chart, you want to tell the viewer what data they are viewing. To do this, you need to label the getAxis().<br>
 * The scale label configuration defines options for the scale label. Note that this only applies to cartesian axes.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class CartesianScaleLabel extends AxisContainer {

	private final CartesianPadding padding;
	// font instance
	private final Font font;

	/**
	 * Builds the object storing the axis which this scale label belongs to.
	 * 
	 * @param axis axis which this scale label belongs to.
	 */
	CartesianScaleLabel(Axis axis) {
		super(axis);
		// gets embedded elements
		padding = new CartesianPadding(axis);
		font = new Font(axis.getConfiguration().getScaleLabel().getFont());
	}

	/**
	 * Returns the font element.
	 * 
	 * @return the font
	 */
	public Font getFont() {
		return font;
	}
	
	/**
	 * Sets the font color.
	 * 
	 * @param color font color.
	 */
	public void setColor(IsColor color) {
		setColor(IsColor.checkAndGetValue(color));
	}

	/**
	 * Sets the font color.
	 * 
	 * @param color font color.
	 */
	public void setColor(String color) {
		getAxis().getScale().getScaleLabel().setColor(color);
	}

	/**
	 * Returns the font color as string.
	 * 
	 * @return font color as string
	 */
	public String getColorAsString() {
		return getAxis().getScale().getScaleLabel().getColorAsString();
	}

	/**
	 * Returns the font color.
	 * 
	 * @return font color
	 */
	public IsColor getColor() {
		return ColorBuilder.parse(getColorAsString());
	}

	/**
	 * Returns the padding element.
	 * 
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
	 * Sets the alignment of the axis title.
	 * 
	 * @param align the alignment of the axis title
	 */
	public void setAlign(ScaleLabelAlign align) {
		getAxis().getScale().getScaleLabel().setAlign(align);
	}

	/**
	 * Returns the alignment of the axis title.
	 * 
	 * @return the alignment of the axis title
	 */
	public ScaleLabelAlign getAlign() {
		return getAxis().getScale().getScaleLabel().getAlign();
	}

}