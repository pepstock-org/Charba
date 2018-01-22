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

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.defaults.AbstractItem;
import org.pepstock.charba.client.defaults.FontItem;

/**
 * When creating a chart, you want to tell the viewer what data they are viewing. To do this, you need to label the axis.<br>
 * The scale label configuration defines options for the scale title. Note that this only applies to cartesian axes.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ScaleLabel extends FontItem {

	private static final boolean DEFAULT_DISPLAY = false;

	private static final String DEFAULT_LABEL_STRING = "";

	private static final double DEFAULT_LINE_HEIGHT = 1.2D;

	private final Padding padding;
	
	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
		display,
		labelString,
		lineHeight,
		padding
	}

	/**
	 * Builds the object with parent item and child.
	 * 
	 * @param parent parent item.
	 * @param childKey key of child.
	 */
	ScaleLabel(AbstractItem parent, Key childKey) {
		super(parent, childKey);
		padding = new Padding(this, Property.padding);
	}
	
	/**
	 * @return the padding
	 */
	public Padding getPadding() {
		return padding;
	}

	/**
	 * If true, display the axis title.
	 * 
	 * @param display if true, display the axis title.
	 */
	public void setDisplay(boolean display) {
		setValue(Property.display, display);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * If true, display the axis title.
	 * 
	 * @return f true, display the axis title. Default is false
	 */
	public boolean isDisplay() {
		return getValue(Property.display, DEFAULT_DISPLAY);
	}

	/**
	 * Sets the text for the scale string.
	 * 
	 * @param labelString The text for the scale string.
	 */
	public void setLabelString(String labelString) {
		setValue(Property.labelString, labelString);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the text for the scale string.
	 * 
	 * @return The text for the scale string. Default is "".
	 */
	public String getLabelString() {
		return getValue(Property.labelString, DEFAULT_LABEL_STRING);
	}

	/**
	 * Sets the height of an individual line of text.
	 * 
	 * @param lineHeight Height of an individual line of text.
	 */
	public void setLineHeight(double lineHeight) {
		setValue(Property.lineHeight, lineHeight);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the height of an individual line of text.
	 * 
	 * @return the height of an individual line of text. Default is 1.2
	 */
	public double getLineHeight() {
		return getValue(Property.lineHeight, DEFAULT_LINE_HEIGHT);
	}
}