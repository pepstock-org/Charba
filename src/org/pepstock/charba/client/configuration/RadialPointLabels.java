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
import org.pepstock.charba.client.callbacks.FontCallback;
import org.pepstock.charba.client.callbacks.RadialPointLabelCallback;
import org.pepstock.charba.client.callbacks.ScaleScriptableContext;
import org.pepstock.charba.client.callbacks.ScriptableFunctions;
import org.pepstock.charba.client.callbacks.ScriptableUtils;
import org.pepstock.charba.client.callbacks.SimplePaddingCallback;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;

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
	// callback proxy to invoke the padding function
	private final CallbackProxy<ScriptableFunctions.ProxyIntegerCallback> paddingCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the backdrop color function
	private final CallbackProxy<ScriptableFunctions.ProxyStringCallback> backdropColorCallbackProxy = JsHelper.get().newCallbackProxy();

	// ---------------------------
	// -- USERS CALLBACKS ---
	// ---------------------------
	// user callbacks implementation for point labels
	private RadialPointLabelCallback callback = null;
	// font callback instance
	private FontCallback<ScaleScriptableContext> fontCallback = null;
	// color callback instance
	private ColorCallback<ScaleScriptableContext> colorCallback = null;
	// padding callback instance
	private SimplePaddingCallback paddingCallback = null;
	// color callback instance
	private ColorCallback<ScaleScriptableContext> backdropColorCallback = null;

	// font instance
	private final Font font;
	// padding instance
	private final Padding backdropPadding;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		BACKDROP_COLOR("backdropColor"),
		COLOR("color"),
		FONT("font"),
		PADDING("padding"),
		CALLBACK("callback");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use in the native object.
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
		this.backdropPadding = new Padding(() -> getAxis().getScale().getPointLabels().getBackdropPadding());
		this.font = new Font(() -> getAxis().getConfiguration().getPointLabels().getFont());
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		this.pointLabelCallbackProxy.setCallback((context, item) -> {
			// checks if callback is consistent
			if (callback != null) {
				// invokes callback
				return callback.onCallback(getAxis(), item);
			}
			// returns passed item
			return item;
		});
		// gets value calling callback
		this.fontCallbackProxy.setCallback((contextFunction, context) -> getAxis().onFont(new ScaleScriptableContext(getAxis(), new ConfigurationEnvelop<>(context)), fontCallback, getAxis().getScale().getPointLabels().getFont()));
		// gets value calling callback
		this.colorCallbackProxy.setCallback((contextFunction, context) -> onColor(new ScaleScriptableContext(getAxis(), new ConfigurationEnvelop<>(context)), colorCallback, getAxis().getDefaultValues().getPointLabels().getColorAsString()));
		// gets value calling callback
		this.backdropColorCallbackProxy
				.setCallback((contextFunction, context) -> onColor(new ScaleScriptableContext(getAxis(), new ConfigurationEnvelop<>(context)), backdropColorCallback, getAxis().getDefaultValues().getPointLabels().getBackdropColorAsString()));
		// gets value calling callback
		this.paddingCallbackProxy
				.setCallback((contextFunction, context) -> ScriptableUtils.getOptionValue(new ScaleScriptableContext(getAxis(), new ConfigurationEnvelop<>(context)), paddingCallback, getAxis().getDefaultValues().getPointLabels().getPadding()).intValue());
	}

	/**
	 * Returns the padding of label backdrop.
	 * 
	 * @return padding of label backdrop.
	 */
	public Padding getBackdropPadding() {
		return backdropPadding;
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
	 * Sets the padding between chart and point labels, in pixels.
	 * 
	 * @param padding the padding between chart and point labels, in pixels.
	 */
	public void setPadding(int padding) {
		getAxis().getScale().getPointLabels().setPadding(padding);
	}

	/**
	 * Returns the padding between chart and point labels, in pixels.
	 * 
	 * @return padding the padding between chart and point labels, in pixels.
	 */
	public int getPadding() {
		return getAxis().getScale().getPointLabels().getPadding();
	}

	/**
	 * Sets the background color of the point label.
	 * 
	 * @param backdropColor the background color of the point label
	 */
	public void setBackdropColor(IsColor backdropColor) {
		getAxis().getScale().getPointLabels().setBackdropColor(backdropColor);
	}

	/**
	 * Sets the background color of the point label.
	 * 
	 * @param backdropColor the background color of the point label
	 */
	public void setBackdropColor(String backdropColor) {
		getAxis().getScale().getPointLabels().setBackdropColor(backdropColor);
	}

	/**
	 * Returns the background color of the point label.
	 * 
	 * @return the background color of the point label
	 */
	public String getBackdropColorAsString() {
		return getAxis().getScale().getPointLabels().getBackdropColorAsString();
	}

	/**
	 * Returns the background color of the point label.
	 * 
	 * @return the background color of the point label
	 */
	public IsColor getBackdropColor() {
		return getAxis().getScale().getPointLabels().getBackdropColor();
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
	public FontCallback<ScaleScriptableContext> getFontCallback() {
		return fontCallback;
	}

	/**
	 * Sets the the font callback.
	 * 
	 * @param fontCallback the font callback to set
	 */
	public void setFont(FontCallback<ScaleScriptableContext> fontCallback) {
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
	 * Returns the padding callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the padding callback, if set, otherwise <code>null</code>.
	 */
	public SimplePaddingCallback getPaddingCallback() {
		return paddingCallback;
	}

	/**
	 * Sets the the padding callback.
	 * 
	 * @param paddingCallback the padding callback to set
	 */
	public void setPadding(SimplePaddingCallback paddingCallback) {
		// sets the callback
		this.paddingCallback = paddingCallback;
		// checks if callback is consistent
		if (paddingCallback != null) {
			// adds the callback proxy function to java script object
			getAxis().getConfiguration().setCallback(getAxis().getConfiguration().getPointLabels(), Property.PADDING, paddingCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			getAxis().getConfiguration().setCallback(getAxis().getConfiguration().getPointLabels(), Property.PADDING, null);
		}
	}

	/**
	 * Returns the color callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the color callback, if set, otherwise <code>null</code>.
	 */
	public ColorCallback<ScaleScriptableContext> getColorCallback() {
		return colorCallback;
	}

	/**
	 * Sets the the color callback.
	 * 
	 * @param colorCallback the color callback to set
	 */
	public void setColor(ColorCallback<ScaleScriptableContext> colorCallback) {
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
	 * Returns the backdrop color callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the backdrop color callback, if set, otherwise <code>null</code>.
	 */
	public ColorCallback<ScaleScriptableContext> getBackdropColorCallback() {
		return backdropColorCallback;
	}

	/**
	 * Sets the the backdrop color callback.
	 * 
	 * @param backdropColorCallback the backdrop color callback to set
	 */
	public void setBackdropColor(ColorCallback<ScaleScriptableContext> backdropColorCallback) {
		// sets the callback
		this.backdropColorCallback = backdropColorCallback;
		// checks if callback is consistent
		if (backdropColorCallback != null) {
			// adds the callback proxy function to java script object
			getAxis().getConfiguration().setCallback(getAxis().getConfiguration().getPointLabels(), Property.BACKDROP_COLOR, backdropColorCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			getAxis().getConfiguration().setCallback(getAxis().getConfiguration().getPointLabels(), Property.BACKDROP_COLOR, null);
		}
	}

	/**
	 * Returns a string as color when the callback has been activated.
	 * 
	 * @param context native object as context
	 * @param callback callback to invoke
	 * @param defaultValue default value of the color
	 * @return a string as color
	 */
	private String onColor(ScaleScriptableContext context, ColorCallback<ScaleScriptableContext> callback, String defaultValue) {
		// gets value
		Object result = ScriptableUtils.getOptionValueAsColor(context, callback, defaultValue, false);
		// checks if result is a string
		if (result instanceof String) {
			// returns result
			return (String) result;
		}
		// default result
		return defaultValue;
	}

}