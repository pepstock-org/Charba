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

import org.pepstock.charba.client.commons.GenericJavaScriptObject;
import org.pepstock.charba.client.commons.Key;
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
	 * Empty constructor to reduce visibility
	 */
	public ScaleLabel(GenericJavaScriptObject javaScriptObject) {
		super(javaScriptObject);
		padding = new Padding(load(Property.padding));
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
	 * @return f true, display the axis title. Default is false
	 */
	public boolean isDisplay() {
		return getValue(Property.display, DEFAULT_DISPLAY);
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
		return getValue(Property.labelString, DEFAULT_LABEL_STRING);
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
	 * @return the height of an individual line of text. Default is 1.2
	 */
	public double getLineHeight() {
		return getValue(Property.lineHeight, DEFAULT_LINE_HEIGHT);
	}


}