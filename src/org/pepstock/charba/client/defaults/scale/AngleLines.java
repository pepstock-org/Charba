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
package org.pepstock.charba.client.defaults.scale;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.defaults.AbstractItem;

/**
 * It is used to configure angled lines that radiate from the center of the chart to the point labels.<br>
 * Note that these options only apply if display is true.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class AngleLines extends AbstractItem {

	private static final boolean DEFAULT_DISPLAY = true;

	private static final int DEFAULT_LINE_WIDTH = 1;

	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
		display,
		color,
		lineWidth
	}

	/**
	 * Builds the object with parent item and child.
	 * 
	 * @param parent parent item.
	 * @param childKey key of child.
	 */
	AngleLines(AbstractItem parent, Key childKey) {
		super(parent, childKey);
	}

	/**
	 * If true, angle lines are shown
	 * 
	 * @param display if true, angle lines are shown
	 */
	public void setDisplay(boolean display) {
		setValue(Property.display, display);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * If true, angle lines are shown
	 * 
	 * @return if true, angle lines are shown. Default is true.
	 */
	public boolean isDisplay() {
		return getValue(Property.display, DEFAULT_DISPLAY);
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
		setValue(Property.color, color);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the color of angled lines.
	 * 
	 * @return color of angled lines. Default is {@link org.pepstock.charba.client.defaults.global.Options#getDefaultColor()}.
	 */
	public String getColorAsString() {
		return getValue(Property.color, Defaults.getGlobal().getDefaultColorAsString());
	}

	/**
	 * Returns the color of angled lines.
	 * 
	 * @return color of angled lines. Default is {@link org.pepstock.charba.client.defaults.global.Options#getDefaultColor()}.
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
		setValue(Property.lineWidth, lineWidth);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the width of angled lines.
	 * 
	 * @return width of angled lines. Default is 1.
	 */
	public int getLineWidth() {
		return getValue(Property.lineWidth, DEFAULT_LINE_WIDTH);
	}

}