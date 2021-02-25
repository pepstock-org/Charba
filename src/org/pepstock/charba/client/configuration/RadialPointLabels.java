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

import org.pepstock.charba.client.callbacks.CallbackFunctionContext;
import org.pepstock.charba.client.callbacks.ColorCallback;
import org.pepstock.charba.client.callbacks.RadialPointLabelCallback;
import org.pepstock.charba.client.callbacks.ScaleFontCallback;
import org.pepstock.charba.client.callbacks.ScaleScriptableContext;
import org.pepstock.charba.client.callbacks.ScriptableFunctions;
import org.pepstock.charba.client.callbacks.ScriptableUtils;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;

import jsinterop.annotations.JsFunction;

/**
 * It is used to configure the point labels that are shown on the perimeter of the scale.<br>
 * Note that these options only apply if display is true.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class RadialPointLabels extends AxisContainer {

	// ---------------------------
	// -- JAVASCRIPT FUNCTIONS ---
	// ---------------------------

	/**
	 * Java script FUNCTION callback called to transform data labels to point labels. The default implementation simply returns the current string.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyPointLabelCallback {

		/**
		 * Method of function to be called to transform data labels to point labels. The default implementation simply returns the current string.
		 * 
		 * @param context context context value of <code>this</code> to the execution context of function.
		 * @param item label of the point
		 * @return new label to show.
		 */
		String call(CallbackFunctionContext context, String item);
	}

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------

	// callback proxy to invoke the point labels function
	private final CallbackProxy<ProxyPointLabelCallback> pointLabelCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the font function
	private final CallbackProxy<ScriptableFunctions.ProxyNativeObjectCallback> fontCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the color function
	private final CallbackProxy<ScriptableFunctions.ProxyStringCallback> colorCallbackProxy = JsHelper.get().newCallbackProxy();

	// ---------------------------
	// -- USERS CALLBACKS ---
	// ---------------------------
	// user callbacks implementation for point labels
	private RadialPointLabelCallback callback = null;
	// font callback instance
	private ScaleFontCallback fontCallback = null;
	// color callback instance
	private ColorCallback colorCallback = null;

	// font instance
	private final Font font;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		COLOR("color"),
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
	 * Builds the object storing the axis which this point labels belongs to.
	 * 
	 * @param axis axis which this point labels belongs to.
	 */
	RadialPointLabels(Axis axis) {
		super(axis);
		// gets embedded elements
		font = new Font(() -> getAxis().getConfiguration().getPointLabels().getFont());
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		pointLabelCallbackProxy.setCallback((context, item) -> {
			// checks if callback is consistent
			if (callback != null) {
				// invokes callback
				return callback.onCallback(getAxis(), item);
			}
			// returns passed item
			return item;
		});
		// gets value calling callback
		fontCallbackProxy.setCallback((contextFunction, context) -> onFont(new ScaleScriptableContext(new ConfigurationEnvelop<>(context)), fontCallback));
		// gets value calling callback
		colorCallbackProxy.setCallback((contextFunction, context) -> onColor(new ScaleScriptableContext(new ConfigurationEnvelop<>(context)), colorCallback));
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
		getAxis().getScale().getPointLabels().setColor(color);
	}

	/**
	 * Returns the font color as string.
	 * 
	 * @return font color as string
	 */
	public String getColorAsString() {
		return getAxis().getScale().getPointLabels().getColorAsString();
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
	 * If true, labels are shown
	 * 
	 * @param display if true, labels are shown
	 */
	public void setDisplay(boolean display) {
		getAxis().getScale().getPointLabels().setDisplay(display);
	}

	/**
	 * If true, labels are shown
	 * 
	 * @return if true, labels are shown.
	 */
	public boolean isDisplay() {
		return getAxis().getScale().getPointLabels().isDisplay();
	}

	/**
	 * Returns the user callback to change point labels.
	 * 
	 * @return the callback
	 */
	public RadialPointLabelCallback getCallback() {
		return callback;
	}

	/**
	 * Sets the user callback to change point labels.
	 * 
	 * @param callback the callback to set
	 */
	public void setCallback(RadialPointLabelCallback callback) {
		// sets the callback
		this.callback = callback;
		// checks if callback is consistent
		if (callback != null) {
			// adds the callback proxy function to java script object
			getAxis().getConfiguration().setCallback(getAxis().getConfiguration().getPointLabels(), Property.CALLBACK, pointLabelCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			getAxis().getConfiguration().setCallback(getAxis().getConfiguration().getPointLabels(), Property.CALLBACK, null);
		}
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
			getAxis().getConfiguration().setCallback(getAxis().getConfiguration().getPointLabels(), Property.FONT, fontCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			getAxis().getConfiguration().setCallback(getAxis().getConfiguration().getPointLabels(), Property.FONT, null);
		}
	}

	/**
	 * Returns the color callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the color callback, if set, otherwise <code>null</code>.
	 */
	public ColorCallback getColorCallback() {
		return colorCallback;
	}

	/**
	 * Sets the the color callback.
	 * 
	 * @param colorCallback the color callback to set
	 */
	public void setColor(ColorCallback colorCallback) {
		// sets the callback
		this.colorCallback = colorCallback;
		// checks if callback is consistent
		if (colorCallback != null) {
			// adds the callback proxy function to java script object
			getAxis().getConfiguration().setCallback(getAxis().getConfiguration().getPointLabels(), Property.COLOR, colorCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			getAxis().getConfiguration().setCallback(getAxis().getConfiguration().getPointLabels(), Property.COLOR, null);
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
		return getAxis().getScale().getPointLabels().getFont().createOptions().nativeObject();
	}

	/**
	 * Returns a string as color when the callback has been activated.
	 * 
	 * @param context native object as context
	 * @param callback callback to invoke
	 * @return a string as color
	 */
	private String onColor(ScaleScriptableContext context, ColorCallback callback) {
		// gets default color
		String defaultColor = getAxis().getDefaultValues().getPointLabels().getColorAsString();
		// gets value
		Object result = ScriptableUtils.getOptionValueAsColor(getAxis(), context, callback, defaultColor, false);
		// checks if result is a string
		if (result instanceof String) {
			// returns result
			return (String) result;
		}
		// default result
		return defaultColor;
	}

}