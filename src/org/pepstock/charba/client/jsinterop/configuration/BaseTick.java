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

import org.pepstock.charba.client.callbacks.TickCallback;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.GenericJavaScriptObject;
import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.jsinterop.options.FontItem;

/**
 * Base object to map an axis tick.<br>
 * It is also common to want to change the tick marks to include information about the data type.<br>
 * To do this, you need to add a callback in the axis configuration. <br>
 * If the callback returns null or undefined the associated grid line will be hidden.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class BaseTick<T extends FontItem<?,?,?>> extends AxisContainer {

	// the axis instance, owner of this tick
	private T configuration;

	private TickCallback callback = null;

	/**
	 * Builds the object storing the chart instance.
	 * 
	 * @param chart chart instance
	 */
	BaseTick(Axis axis, T configuration) {
		super(axis);
		this.configuration = configuration;
//		super(chart);
//		// registers callback
//		registerNativeTickCallbacktHandler(getJavaScriptObject());
	}

	/**
	 * @return the configuration
	 */
	final T getConfiguration() {
		return configuration;
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
	 * @see org.pepstock.charba.client.enums.FontStyle
	 */
	public void setFontStyle(FontStyle fontStyle) {
		configuration.setFontStyle(fontStyle);
	}

	/**
	 * Returns the font style for the tick, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @return the font style for the tick, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit). 
	 * @see org.pepstock.charba.client.enums.FontStyle
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
	 * @return the callback
	 */
	public TickCallback getCallback() {
		return callback;
	}

	/**
	 * @param callback the callback to set
	 */
	public void setCallback(TickCallback callback) {
		// FIXME
//		// checks if callback has been already set
//		if (hasToBeRegistered(callback, Property.callback)) {
//			// registers the callback
//			registerNativeTickCallbacktHandler(getJavaScriptObject());
//		}
//		this.callback = callback;
	}

	/**
	 * Called to change the tick marks to include information about the data type.
	 * 
	 * @param object java script object element of a single tick.
	 * @return if the callback returns null or undefined the associated grid line will be hidden.
	 * @see org.pepstock.charba.client.items.TickItem
	 */
	protected String onCallback(GenericJavaScriptObject object) {
		// FIXME
//		TickItem item = new TickItem(object);
//		AbstractChart<?, ?> chart = getChart();
//		if (callback != null && chart != null) {
//			return callback.onCallback(chart, item);
//		}
//		return String.valueOf(item.getValue());
		return null;
	}

	/**
	 * Sets the java script code to activate the call back, adding functions.
	 * 
	 * @param options
	 *            java script object where adding new functions definition.
	 */
	private native void registerNativeTickCallbacktHandler(GenericJavaScriptObject options)/*-{
		var self = this;
		options.callback = function(value, index, values){
			var myItem = new Object();
			myItem.value = value;
			myItem.index = index;
			myItem.values = values;
			return self.@org.pepstock.charba.client.options.scales.BaseTick::onCallback(Lorg/pepstock/charba/client/commons/GenericJavaScriptObject;)(myItem);
		}
	}-*/;

}