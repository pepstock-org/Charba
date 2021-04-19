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

import org.pepstock.charba.client.callbacks.ColorCallback;
import org.pepstock.charba.client.callbacks.FontCallback;
import org.pepstock.charba.client.callbacks.ScaleContext;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyIntegerCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyNativeObjectCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyObjectCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyStringCallback;
import org.pepstock.charba.client.callbacks.ScriptableUtils;
import org.pepstock.charba.client.callbacks.WidthCallback;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.options.IsScriptableFontProvider;
import org.pepstock.charba.client.options.Ticks;

/**
 * Specific tick with minimum and maximum sub ticks.
 * 
 * @author Andrea "Stock" Stocchero
 */
abstract class Tick extends AxisContainer implements IsScriptableFontProvider<ScaleContext>{

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the font function
	private final CallbackProxy<ProxyNativeObjectCallback> fontCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the color function
	private final CallbackProxy<ProxyStringCallback> colorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the text stroke color function
	private final CallbackProxy<ProxyObjectCallback> textStrokeColorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the text stroke width function
	private final CallbackProxy<ProxyIntegerCallback> textStrokeWidthCallbackProxy = JsHelper.get().newCallbackProxy();

	// font callback instance
	private FontCallback<ScaleContext> fontCallback = null;
	// color callback instance
	private ColorCallback<ScaleContext> colorCallback = null;
	// text color callback instance
	private ColorCallback<ScaleContext> textStrokeColorCallback = null;
	// text width callback instance
	private WidthCallback<ScaleContext> textStrokeWidthCallback = null;

	// major tick instance
	private final Major major;

	// font instance
	private final Font font;

	/**
	 * Name of properties of native object.
	 */
	enum Property implements Key
	{
		CALLBACK("callback"),
		COLOR("color"),
		FONT("font"),
		TEXT_STROKE_COLOR("textStrokeColor"),
		TEXT_STROKE_WIDTH("textStrokeWidth");

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
	 * Builds the object storing the axis which this tick belongs to.
	 * 
	 * @param axis axis which this tick belongs to.
	 */
	Tick(Axis axis) {
		super(axis);
		// creates sub element
		this.major = new Major(axis, this);
		this.font = new Font(() -> getConfiguration().getFont());
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// sets function to proxy callback in order to invoke the java interface
		this.fontCallbackProxy.setCallback((contextFunction, context) -> getAxis().onFont(new ScaleContext(getAxis(), new ConfigurationEnvelop<>(context)), fontCallback, getAxis().getScale().getPointLabels().getFont()));
		// sets function to proxy callback in order to invoke the java interface
		this.colorCallbackProxy.setCallback((contextFunction, context) -> onColor(new ScaleContext(getAxis(), new ConfigurationEnvelop<>(context)), colorCallback));
		// sets function to proxy callback in order to invoke the java interface
		this.textStrokeColorCallbackProxy.setCallback((contextFunction, context) -> onColor(new ScaleContext(getAxis(), new ConfigurationEnvelop<>(context)), textStrokeColorCallback));
		// sets function to proxy callback in order to invoke the java interface
		this.textStrokeWidthCallbackProxy
				.setCallback((contextFunction, context) -> ScriptableUtils.getOptionValue(new ScaleContext(getAxis(), new ConfigurationEnvelop<>(context)), textStrokeWidthCallback, getAxis().getDefaultValues().getTicks().getTextStrokeWidth()).intValue());
	}

	/**
	 * Returns the options element for tick.
	 * 
	 * @return the configuration
	 */
	final Ticks getConfiguration() {
		return getAxis().getScale().getTicks();
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
	 * Returns the font element.
	 * 
	 * @return the font element
	 */
	public Font getFont() {
		return font;
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
		// resets callback
		setColor((ColorCallback<ScaleContext>)null);
		// stores the value
		getConfiguration().setColor(color);
	}

	/**
	 * Returns the font color as string.
	 * 
	 * @return font color as string
	 */
	public String getColorAsString() {
		return getConfiguration().getColorAsString();
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
	 * Sets z-index of tick layer. Useful when ticks are drawn on chart area.<br>
	 * Values less than or equals to 0 are drawn under datasets, greater than 0 on top.
	 * 
	 * @param z z-index of tick layer. Useful when ticks are drawn on chart area.<br>
	 *            Values less than or equals to 0 are drawn under datasets, greater than 0 on top.
	 */
	public void setZ(int z) {
		getConfiguration().setZ(z);
	}

	/**
	 * Returns z-index of tick layer. Useful when ticks are drawn on chart area.<br>
	 * Values less than or equals to 0 are drawn under datasets, greater than 0 on top.
	 * 
	 * @return z-index of tick layer. Useful when ticks are drawn on chart area.<br>
	 *         Values less than or equals to 0 are drawn under datasets, greater than 0 on top.
	 */
	public int getZ() {
		return getConfiguration().getZ();
	}

	/**
	 * Sets the text stroke color.
	 * 
	 * @param color the text stroke color
	 */
	public void setTextStrokeColor(IsColor color) {
		setTextStrokeColor(IsColor.checkAndGetValue(color));
	}

	/**
	 * Sets the text stroke color.
	 * 
	 * @param color the text stroke color.
	 */
	public void setTextStrokeColor(String color) {
		// resets callback
		setTextStrokeColor((ColorCallback<ScaleContext>)null);
		// stores the value
		getConfiguration().setTextStrokeColor(color);
	}

	/**
	 * Returns the text stroke color as string.
	 * 
	 * @return the text stroke color as string.
	 */
	public String getTextStrokeColorAsString() {
		return getConfiguration().getTextStrokeColorAsString();
	}

	/**
	 * Returns the text stroke color.
	 * 
	 * @return the text stroke color.
	 */
	public IsColor getTextStrokeColor() {
		return getConfiguration().getTextStrokeColor();
	}

	/**
	 * Sets the text stroke width.
	 * 
	 * @param textStrokeWidth the text stroke width.
	 */
	public void setTextStrokeWidth(int textStrokeWidth) {
		// resets callback
		setTextStrokeWidth((WidthCallback<ScaleContext>)null);
		// stores the value

		getConfiguration().setTextStrokeWidth(textStrokeWidth);
	}

	/**
	 * Returns the text stroke width.
	 * 
	 * @return the text stroke width.
	 */
	public int getTextStrokeWidth() {
		return getConfiguration().getTextStrokeWidth();
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
	@Override
	public FontCallback<ScaleContext> getFontCallback() {
		return fontCallback;
	}

	/**
	 * Returns the color callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the color callback, if set, otherwise <code>null</code>.
	 */
	public ColorCallback<ScaleContext> getColorCallback() {
		return colorCallback;
	}

	/**
	 * Returns the text stroke color callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the text stroke color callback, if set, otherwise <code>null</code>.
	 */
	public ColorCallback<ScaleContext> getTextStrokeColorCallback() {
		return textStrokeColorCallback;
	}

	/**
	 * Returns the text stroke width callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the text stroke width callback, if set, otherwise <code>null</code>.
	 */
	public WidthCallback<ScaleContext> getTextStrokeWidthCallback() {
		return textStrokeWidthCallback;
	}

	/**
	 * Sets the color callback.
	 * 
	 * @param colorCallback the color callback to set
	 */
	public void setColor(ColorCallback<ScaleContext> colorCallback) {
		// sets the callback
		this.colorCallback = colorCallback;
		// checks if callback is consistent
		if (colorCallback != null) {
			// adds the callback proxy function to java script object
			getAxis().getConfiguration().setCallback(getConfiguration(), Property.COLOR, new ConfigurationEnvelop<>(colorCallbackProxy.getProxy()));
		} else {
			// otherwise sets null which removes the properties from java script object
			getAxis().getConfiguration().setCallback(getConfiguration(), Property.COLOR, ConfigurationOptions.RESET_CALLBACK_ENVELOP);
		}
	}

	/**
	 * Sets the text stroke color callback.
	 * 
	 * @param textStrokeColorCallback the text stroke color callback to set
	 */
	public void setTextStrokeColor(ColorCallback<ScaleContext> textStrokeColorCallback) {
		// sets the callback
		this.textStrokeColorCallback = textStrokeColorCallback;
		// checks if callback is consistent
		if (textStrokeColorCallback != null) {
			// adds the callback proxy function to java script object
			getAxis().getConfiguration().setCallback(getConfiguration(), Property.TEXT_STROKE_COLOR, new ConfigurationEnvelop<>(textStrokeColorCallbackProxy.getProxy()));
		} else {
			// otherwise sets null which removes the properties from java script object
			getAxis().getConfiguration().setCallback(getConfiguration(), Property.TEXT_STROKE_COLOR, ConfigurationOptions.RESET_CALLBACK_ENVELOP);
		}
	}

	/**
	 * Sets the text stroke width callback.
	 * 
	 * @param textStrokeWidthCallback the text stroke width callback to set
	 */
	public void setTextStrokeWidth(WidthCallback<ScaleContext> textStrokeWidthCallback) {
		// sets the callback
		this.textStrokeWidthCallback = textStrokeWidthCallback;
		// checks if callback is consistent
		if (textStrokeWidthCallback != null) {
			// adds the callback proxy function to java script object
			getAxis().getConfiguration().setCallback(getConfiguration(), Property.TEXT_STROKE_WIDTH, new ConfigurationEnvelop<>(textStrokeWidthCallbackProxy.getProxy()));
		} else {
			// otherwise sets null which removes the properties from java script object
			getAxis().getConfiguration().setCallback(getConfiguration(), Property.TEXT_STROKE_WIDTH, ConfigurationOptions.RESET_CALLBACK_ENVELOP);
		}
	}

	/**
	 * Sets the font callback.
	 * 
	 * @param fontCallback the font callback to set
	 */
	@Override
	public void setFont(FontCallback<ScaleContext> fontCallback) {
		// sets the callback
		this.fontCallback = fontCallback;
		// checks if callback is consistent
		if (fontCallback != null) {
			// adds the callback proxy function to java script object
			getAxis().getConfiguration().setCallback(getConfiguration(), Property.FONT, new ConfigurationEnvelop<>(fontCallbackProxy.getProxy()));
		} else {
			// otherwise sets null which removes the properties from java script object
			getAxis().getConfiguration().setCallback(getConfiguration(), Property.FONT, ConfigurationOptions.RESET_CALLBACK_ENVELOP);
		}
	}

	/**
	 * Returns a string as color when the callback has been activated.
	 * 
	 * @param context native object as context
	 * @param callback callback to invoke
	 * @return a string as color
	 */
	private String onColor(ScaleContext context, ColorCallback<ScaleContext> callback) {
		// gets default color
		String defaultColor = getAxis().getDefaultValues().getTicks().getColorAsString();
		// gets value
		Object result = ScriptableUtils.getOptionValueAsColor(context, callback, defaultColor, false);
		// checks if result is a string
		if (result instanceof String) {
			// returns result
			return (String) result;
		}
		// default result
		return defaultColor;
	}

}