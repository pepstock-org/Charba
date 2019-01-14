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
import org.pepstock.charba.client.jsinterop.commons.Key;
import org.pepstock.charba.client.jsinterop.enums.FontStyle;
import org.pepstock.charba.client.jsinterop.callbacks.TickCallback;
import org.pepstock.charba.client.jsinterop.commons.ArrayDouble;
import org.pepstock.charba.client.jsinterop.commons.ArrayListHelper;
import org.pepstock.charba.client.jsinterop.commons.CallbackProxy;
import org.pepstock.charba.client.jsinterop.commons.JsHelper;
import org.pepstock.charba.client.jsinterop.options.AbstractTick;

import jsinterop.annotations.JsFunction;

/**
 * Base object to map an axis tick.<br>
 * It is also common to want to change the tick marks to include information about the data type.<br>
 * To do this, you need to add a callback in the axis configuration. <br>
 * If the callback returns null or undefined the associated grid line will be hidden.
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 */
abstract class BaseTick<T extends AbstractTick<?, ?>> extends AxisContainer {

	// ---------------------------
	// -- JAVASCRIPT FUNCTIONS ---
	// ---------------------------

	/**
	 * Java script FUNCTION callback when tick is created.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 * @since 2.0
	 */
	@JsFunction
	interface ProxyTickCallback {

		/**
		 * Method of function to be called when tick is created.
		 * 
		 * @param context value of <code>this</code> to the execution context of function.
		 * @param value value of the tick
		 * @param index index of tick
		 * @param values array with all values of ticks
		 * @return string representation of tick
		 */
		String call(Object context, double value, int index, ArrayDouble values);
	}

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------

	// callback proxy to invoke the tick function
	private final CallbackProxy<ProxyTickCallback> tickCallbackProxy = JsHelper.get().newCallbackProxy();
	// user callback instance
	private TickCallback callback = null;
	// the axis instance, owner of this tick
	private final T configuration;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		callback
	}

	/**
	 * Builds the object storing the axis instance and options element, based on different kind of axis.
	 * 
	 * @param axis axis instance
	 * @param configuration options element, based on different kind of axis.
	 */
	BaseTick(Axis axis, T configuration) {
		super(axis);
		// stores the options element
		this.configuration = configuration;

		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		tickCallbackProxy.setCallback(new ProxyTickCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.jsinterop.options.BaseTick.ProxyTickCallback#call(java.lang.Object, double, int,
			 * org.pepstock.charba.client.jsinterop.commons.ArrayDouble)
			 */
			@Override
			public String call(Object context, double value, int index, ArrayDouble values) {
				// checks if user callback is consistent
				if (callback != null) {
					// then calls user callback
					return callback.onCallback(getAxis(), value, index, ArrayListHelper.unmodifiableList(values));
				}
				// default tick is the string representation of the tick value
				return String.valueOf(value);
			}
		});
	}

	/**
	 * Returns the options element for tick.
	 * 
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
	 * @param fontStyle Font style for the tick, follows CSS font-style options (i.e. normal, italic, oblique, initial,
	 *            inherit).
	 */
	public void setFontStyle(FontStyle fontStyle) {
		configuration.setFontStyle(fontStyle);
	}

	/**
	 * Returns the font style for the tick, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @return the font style for the tick, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
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
	 * Returns the user callback instance.
	 * 
	 * @return the callback
	 */
	public TickCallback getCallback() {
		return callback;
	}

	/**
	 * Sets the user callback instance.
	 * 
	 * @param callback the callback to set
	 */
	public void setCallback(TickCallback callback) {
		// sets the callback
		this.callback = callback;
		// checks if callback is consistent
		if (callback != null) {
			// adds the callback proxy function to java script object
			getAxis().getConfiguration().setCallback(configuration, Property.callback, tickCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			getAxis().getConfiguration().setCallback(configuration, Property.callback, null);
		}
	}
}