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
package org.pepstock.charba.client.impl.plugins;

import org.pepstock.charba.client.colors.IsColor;

/**
 * Utility provided to the user in order to set the same values to all labels, X and Y.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class CrosshairLabels {

	// options parent
	private final CrosshairOptions parent;
	// instance of font labels
	private final CrosshairLabelsFont font;

	/**
	 * Creates new crosshair labels.
	 * 
	 * @param parent plugin options.
	 */
	CrosshairLabels(CrosshairOptions parent) {
		this.parent = parent;
		// stores font
		this.font = new CrosshairLabelsFont(parent);
	}

	/**
	 * Returns the font object.
	 * 
	 * @return the font object.
	 */
	public CrosshairLabelsFont getFont() {
		return font;
	}

	/**
	 * Sets <code>true</code> if label will be applied in the chart, otherwise <code>false</code>.
	 * 
	 * @param display <code>true</code> if label will be applied in the chart, otherwise <code>false</code>
	 */
	public void setDisplay(boolean display) {
		// stores value on x and y labels
		parent.getXLabel().setDisplay(display);
		parent.getYLabel().setDisplay(display);
	}

	/**
	 * Sets the crosshair label font color.
	 * 
	 * @param color the crosshair label font color.
	 */
	public void setColor(String color) {
		// stores value on x and y labels
		parent.getXLabel().setColor(color);
		parent.getYLabel().setColor(color);
	}

	/**
	 * Set the crosshair label font color.
	 * 
	 * @param color the crosshair label font color.
	 */
	public void setColor(IsColor color) {
		setColor(IsColor.checkAndGetValue(color));
	}

	/**
	 * Sets the padding of crosshair label.
	 * 
	 * @param padding padding of crosshair label
	 */
	public void setPadding(int padding) {
		// stores value on x and y labels
		parent.getXLabel().setPadding(padding);
		parent.getYLabel().setPadding(padding);
	}

	/**
	 * Sets the background color.
	 * 
	 * @param color the background color.
	 */
	public void setBackgroundColor(String color) {
		// stores value on x and y labels
		parent.getXLabel().setBackgroundColor(color);
		parent.getYLabel().setBackgroundColor(color);
	}

	/**
	 * Sets the background color.
	 * 
	 * @param color the background color.
	 */
	public void setBackgroundColor(IsColor color) {
		setBackgroundColor(IsColor.checkAndGetValue(color));
	}

	/**
	 * Sets the label border radius (in pixels).
	 * 
	 * @param borderRadius the label border radius (in pixels).
	 */
	public void setBorderRadius(int borderRadius) {
		// stores value on x and y labels
		parent.getXLabel().setBorderRadius(borderRadius);
		parent.getYLabel().setBorderRadius(borderRadius);
	}
}
