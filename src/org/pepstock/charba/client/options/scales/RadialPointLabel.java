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
import org.pepstock.charba.client.callbacks.RadialPointLabelCallback;
import org.pepstock.charba.client.commons.GenericJavaScriptObject;
import org.pepstock.charba.client.commons.JavaScriptObjectContainer;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.FontStyle;

/**
 * It is used to configure the point labels that are shown on the perimeter of the scale.<br>
 * Note that these options only apply if display is true.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class RadialPointLabel extends JavaScriptObjectContainer {

	private static final boolean DEFAULT_DISPLAY = true;

	private static final int DEFAULT_FONT_SIZE = 10;

	private static final String DEFAULT_FONT_COLOR = "#666";

	private static final String DEFAULT_FONT_FAMILY = "'Helvetica Neue', 'Helvetica', 'Arial', sans-serif";

	private final Axis axis;

	private RadialPointLabelCallback callback = null;

	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
		display,
		callback,
		fontSize,
		fontStyle,
		fontColor,
		fontFamily,
	}

	/**
	 * Builds the object with own axis.
	 * 
	 * @param axis own axis.
	 */
	RadialPointLabel(Axis axis) {
		this.axis = axis;
		registerNativePointLabelCallbacktHandler(getJavaScriptObject());
	}

	/**
	 * If true, labels are shown
	 * 
	 * @param display if true, labels are shown
	 */
	public void setDisplay(boolean display) {
		setValue(Property.display, display);
	}

	/**
	 * If true, labels are shown
	 * 
	 * @return if true, labels are shown. Default is true.
	 */
	public boolean isDisplay() {
		return getValue(Property.display, DEFAULT_DISPLAY);
	}

	/**
	 * Sets the font size for the tick labels.
	 * 
	 * @param fontSize font size for the tick labels.
	 */
	public void setFontSize(int fontSize) {
		setValue(Property.fontSize, fontSize);
	}

	/**
	 * Returns the font size for the tick labels.
	 * 
	 * @return font size for the tick labels. Default is 10.
	 */
	public int getFontSize() {
		return getValue(Property.fontSize, DEFAULT_FONT_SIZE);
	}

	/**
	 * Sets the font style for the tick labels, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @param fontStyle font style for the tick labels, follows CSS font-style options (i.e. normal, italic, oblique, initial,
	 *            inherit).
	 * @see org.pepstock.charba.client.enums.FontStyle
	 */
	public void setFontStyle(FontStyle fontStyle) {
		setValue(Property.fontStyle, fontStyle);
	}

	/**
	 * Returns the font style for the tick labels, follows CSS font-style options (i.e. normal, italic, oblique, initial,
	 * inherit).
	 * 
	 * @return font style for the tick labels, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 *         Default is normal.
	 * @see org.pepstock.charba.client.enums.FontStyle
	 */
	public FontStyle getFontStyle() {
		return getValue(Property.fontStyle, FontStyle.class, FontStyle.normal);
	}

	/**
	 * Sets the font color for tick labels.
	 * 
	 * @param fontColor font color for tick labels.
	 */
	public void setFontColor(String fontColor) {
		setValue(Property.fontColor, fontColor);
	}

	/**
	 * Returns the font color for tick labels.
	 * 
	 * @return font color for tick labels. Default is '#666'.
	 */
	public String getFontColor() {
		return getValue(Property.fontColor, DEFAULT_FONT_COLOR);
	}

	/**
	 * Sets the font family for the tick labels, follows CSS font-family options.
	 * 
	 * @param fontFamily font family for the tick labels, follows CSS font-family options.
	 */
	public void setFontFamily(String fontFamily) {
		setValue(Property.fontFamily, fontFamily);
	}

	/**
	 * Returns the font family for the tick labels, follows CSS font-family options.
	 * 
	 * @return font family for the tick labels, follows CSS font-family options. Default is "'Helvetica Neue', 'Helvetica',
	 *         'Arial', sans-serif".
	 */
	public String getFontFamily() {
		return getValue(Property.fontFamily, DEFAULT_FONT_FAMILY);
	}

	/**
	 * @return the callback
	 */
	public RadialPointLabelCallback getCallback() {
		return callback;
	}

	/**
	 * @param callback the callback to set
	 */
	public void setCallback(RadialPointLabelCallback callback) {
		// checks if property is already set
		if (hasToBeRegistered(callback, Property.callback)) {
			// registers the property
			registerNativePointLabelCallbacktHandler(getJavaScriptObject());
		}
		this.callback = callback;
	}

	/**
	 * Returns the string representation of the tick value as it should be displayed on the chart.
	 * 
	 * @param item tick item to be shown
	 * @return string representation of the tick value
	 */
	protected String onCallback(String item) {
		// gets the chart instance
		AbstractChart<?, ?> chart = axis.getChart();
		// checks if callback and chart are consistent
		if (callback != null && chart != null) {
			// invokes callback and returns new value
			return callback.onCallback(chart, item);
		}
		// returns the item passed because no changed
		return item;
	}

	/**
	 * Sets the java script code to activate the call back, adding functions.
	 * 
	 * @param options
	 *            java script object where adding new functions definition.
	 */
    private native void registerNativePointLabelCallbacktHandler(GenericJavaScriptObject options)/*-{
		var self = this;
	    options.callback = function(item){
	    	return self.@org.pepstock.charba.client.options.scales.RadialPointLabel::onCallback(Ljava/lang/String;)(item);
	    }
	}-*/;
}