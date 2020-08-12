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

import org.pepstock.charba.client.callbacks.ScaleFontCallback;
import org.pepstock.charba.client.callbacks.ScaleScriptableContext;
import org.pepstock.charba.client.callbacks.ScriptableFunctions;
import org.pepstock.charba.client.callbacks.ScriptableUtils;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.options.Ticks;

/**
 * Specific tick with minimum and maximum sub ticks.
 * 
 * @author Andrea "Stock" Stocchero
 */
abstract class Tick extends AxisContainer {

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the font function
	private final CallbackProxy<ScriptableFunctions.ProxyNativeObjectCallback> fontCallbackProxy = JsHelper.get().newCallbackProxy();

	// the axis instance, owner of this tick
	private final Ticks configuration;

	private final Major major;

	// font instance
	private final Font font;
	// font callback instance
	private ScaleFontCallback fontCallback = null;

	/**
	 * Name of properties of native object.
	 */
	enum Property implements Key
	{
		FONT("font"),
		CALLBACK("callback");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use into native object.
		 * 
		 * @param value value of property name
		 */
		private Property(String value) {
			this.value = value;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.Key#value()
		 */
		@Override
		public String value() {
			return value;
		}

	}

	/**
	 * Builds the object storing the axis which this tick belongs to.
	 * 
	 * @param axis axis which this tick belongs to.
	 */
	Tick(Axis axis) {
		super(axis);
		// stores the options element
		this.configuration = axis.getScale().getTicks();
		// creates sub element, min and max
		major = new Major(axis, axis.getScale().getTicks());
		font = new Font(axis.getConfiguration().getTicks().getFont());
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// gets value calling callback
		fontCallbackProxy.setCallback((contextFunction, context) -> onFont(new ScaleScriptableContext(new ConfigurationEnvelop<>(context)), fontCallback));
	}

	/**
	 * Returns the options element for tick.
	 * 
	 * @return the configuration
	 */
	final Ticks getConfiguration() {
		return configuration;
	}

	/**
	 * Returns major tick element.
	 * 
	 * @return the major
	 */
	public Major getMajor() {
		return major;
	}

	/**
	 * Returns the font element.<br>
	 * <b>Pay attention</b> that if the font callback has been set previously, the method returns <code>null</code>.
	 * 
	 * @return the font element.<br>
	 *         <b>Pay attention</b> that if the font callback has been set previously, the method returns <code>null</code>
	 */
	public Font getFont() {
		// checks if font is scriptable
		// otherwise returns null
		return getFontCallback() == null ? font : null;
	}

	/**
	 * If true, show tick marks.
	 * 
	 * @param display if true, show tick marks
	 */
	public void setDisplay(boolean display) {
		getConfiguration().setDisplay(display);
	}

	/**
	 * If true, show tick marks
	 * 
	 * @return if true, show tick marks.
	 */
	public boolean isDisplay() {
		return getConfiguration().isDisplay();
	}

	/**
	 * Sets z-index of tick layer. Useful when ticks are drawn on chart area. Values less than or equals to 0 are drawn under datasets, greater than 0 on top.
	 * 
	 * @param z z-index of tick layer. Useful when ticks are drawn on chart area. Values less than or equals to 0 are drawn under datasets, greater than 0 on top.
	 */
	public void setZ(int z) {
		getConfiguration().setZ(z);
	}

	/**
	 * Returns z-index of tick layer. Useful when ticks are drawn on chart area. Values less than or equals to 0 are drawn under datasets, greater than 0 on top.
	 * 
	 * @return z-index of tick layer. Useful when ticks are drawn on chart area. Values less than or equals to 0 are drawn under datasets, greater than 0 on top.
	 */
	public int getZ() {
		return getConfiguration().getZ();
	}

	/**
	 * Sets the padding between the tick label and the axis. When set on a vertical axis, this applies in the horizontal (X) direction. When set on a horizontal axis, this applies
	 * in the vertical (Y) direction.
	 * 
	 * @param padding padding between the tick label and the axis. When set on a vertical axis, this applies in the horizontal (X) direction. When set on a horizontal axis, this
	 *            applies in the vertical (Y) direction.
	 */
	public void setPadding(int padding) {
		getConfiguration().setPadding(padding);
	}

	/**
	 * Returns the padding between the tick label and the axis. When set on a vertical axis, this applies in the horizontal (X) direction. When set on a horizontal axis, this
	 * applies in the vertical (Y) direction.
	 * 
	 * @return padding between the tick label and the axis. When set on a vertical axis, this applies in the horizontal (X) direction. When set on a horizontal axis, this applies
	 *         in the vertical (Y) direction.
	 */
	public int getPadding() {
		return getConfiguration().getPadding();
	}

	/**
	 * Returns the font callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the font callback, if set, otherwise <code>null</code>.
	 */
	public ScaleFontCallback getFontCallback() {
		return fontCallback;
	}

	/**
	 * Sets the the font callback.
	 * 
	 * @param fontCallback the font callback to set
	 */
	public void setFont(ScaleFontCallback fontCallback) {
		// sets the callback
		this.fontCallback = fontCallback;
		// checks if callback is consistent
		if (fontCallback != null) {
			// adds the callback proxy function to java script object
			getAxis().getConfiguration().setCallback(getConfiguration(), Property.FONT, fontCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			getAxis().getConfiguration().setCallback(getConfiguration(), Property.FONT, null);
		}
	}

	/**
	 * Returns a native object as font when the callback has been activated.
	 * 
	 * @param context native object as context
	 * @param callback callback to invoke
	 * @return a native object as font
	 */
	private NativeObject onFont(ScaleScriptableContext context, ScaleFontCallback callback) {
		// gets value
		FontOptions result = ScriptableUtils.getOptionValue(getAxis(), context, callback);
		// checks if result is consistent
		if (result != null) {
			// returns result
			return result.nativeObject();
		}
		// default result
		return getConfiguration().getFont().createOptions().nativeObject();
	}

}