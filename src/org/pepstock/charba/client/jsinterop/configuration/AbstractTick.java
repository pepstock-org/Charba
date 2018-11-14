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
import org.pepstock.charba.client.jsinterop.callbacks.TickCallback;
import org.pepstock.charba.client.jsinterop.callbacks.handlers.TickHandler;
import org.pepstock.charba.client.jsinterop.commons.ArrayDouble;
import org.pepstock.charba.client.jsinterop.commons.ArrayListHelper;
import org.pepstock.charba.client.jsinterop.options.BaseTick;

/**
 * Base object to map an axis tick.<br>
 * It is also common to want to change the tick marks to include information about the data type.<br>
 * To do this, you need to add a callback in the axis configuration. <br>
 * If the callback returns null or undefined the associated grid line will be hidden.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
abstract class AbstractTick<T extends BaseTick<?,?,?>> extends AxisContainer implements TickHandler {

	// the axis instance, owner of this tick
	private T configuration;

	private TickCallback callback = null;

	/**
	 * Builds the object storing the chart instance.
	 * 
	 * @param chart chart instance
	 */
	AbstractTick(Axis axis, T configuration) {
		super(axis);
		this.configuration = configuration;
		if (hasGlobalCallback()) {
			axis.getChart().getOptions().getConfiguration().setTickCallbackHandler(getDefaultTick(), this);
		}
	}
	
	abstract T getDefaultTick();
	
	private boolean hasGlobalCallback() {
		return getDefaultTick().getCallback() != null;
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
		getConfiguration().setCallback(callback);
		if (!hasGlobalCallback()) {
			if (callback == null) {
				getAxis().getChart().getOptions().getConfiguration().setTickCallbackHandler(getDefaultTick(), null);
			} else {
				getAxis().getChart().getOptions().getConfiguration().setTickCallbackHandler(getDefaultTick(), this);
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.callbacks.handlers.TickHandler#onCallback(double, int, org.pepstock.charba.client.jsinterop.commons.ArrayDouble)
	 */
	@Override
	public String onCallback(double value, int index, ArrayDouble values) {
		if (getConfiguration().getCallback() != null) {
			return getConfiguration().getCallback().onCallback(getAxis(), value, index, ArrayListHelper.unmodifiableList(values));
		} else if (hasGlobalCallback()) {
			return getDefaultTick().getCallback().onCallback(getAxis(), value, index, ArrayListHelper.unmodifiableList(values));
		}
		return String.valueOf(value);
	}

}