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
import org.pepstock.charba.client.callbacks.PaddingCallback;
import org.pepstock.charba.client.callbacks.ScaleContext;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyBooleanCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyIntegerCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyNativeObjectCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyObjectCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyStringCallback;
import org.pepstock.charba.client.callbacks.ScriptableUtils;
import org.pepstock.charba.client.callbacks.ShowLabelBackdropCallback;
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
	// callback proxy to invoke the backdrop padding function
	private final CallbackProxy<ProxyNativeObjectCallback> backdropPaddingCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the backdrop color function
	private final CallbackProxy<ProxyObjectCallback> backdropColorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the show label backdrop function
	private final CallbackProxy<ProxyBooleanCallback> showLabelBackdropCallbackProxy = JsHelper.get().newCallbackProxy();

	// font callback instance
	private FontCallback<ScaleContext> fontCallback = null;
	// color callback instance
	private ColorCallback<ScaleContext> colorCallback = null;
	// text color callback instance
	private ColorCallback<ScaleContext> textStrokeColorCallback = null;
	// text width callback instance
	private WidthCallback<ScaleContext> textStrokeWidthCallback = null;
	// backdrop color callback instance
	private PaddingCallback<ScaleContext> backdropPaddingCallback = null;
	// backdrop color callback instance
	private ColorCallback<ScaleContext> backdropColorCallback = null;
	// show label backdrop callback instance
	private ShowLabelBackdropCallback showLabelBackdropCallback = null;

	// major tick instance
	private final Major major;
	// font instance
	private final Font font;
	// padding instance
	private final Padding backdropPadding;

	/**
	 * Name of properties of native object.
	 */
	enum Property implements Key
	{
		CALLBACK("callback"),
		COLOR("color"),
		FONT("font"),
		TEXT_STROKE_COLOR("textStrokeColor"),
		TEXT_STROKE_WIDTH("textStrokeWidth"),
		BACKDROP_PADDING("backdropPadding"),
		BACKDROP_COLOR("backdropColor"),
		SHOW_LABEL_BACKDROP("showLabelBackdrop");

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
		this.backdropPadding = new Padding(() -> getAxis().getScale().getTicks().getBackdropPadding());
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// sets function to proxy callback in order to invoke the java interface
		this.fontCallbackProxy.setCallback((contextFunction, context) -> getAxis().onFont(new ScaleContext(getAxis(), new ConfigurationEnvelop<>(context)), fontCallback, getAxis().getScale().getTicks().getFont()));
		// sets function to proxy callback in order to invoke the java interface
		this.backdropPaddingCallbackProxy.setCallback((contextFunction, context) -> getAxis().onPadding(new ScaleContext(getAxis(), new ConfigurationEnvelop<>(context)), backdropPaddingCallback, getAxis().getScale().getTicks().getBackdropPadding()));
		// sets function to proxy callback in order to invoke the java interface
		this.colorCallbackProxy.setCallback((contextFunction, context) -> onColor(new ScaleContext(getAxis(), new ConfigurationEnvelop<>(context)), colorCallback));
		// sets function to proxy callback in order to invoke the java interface
		this.textStrokeColorCallbackProxy.setCallback((contextFunction, context) -> onColor(new ScaleContext(getAxis(), new ConfigurationEnvelop<>(context)), textStrokeColorCallback));
		// sets function to proxy callback in order to invoke the java interface
		this.textStrokeWidthCallbackProxy
				.setCallback((contextFunction, context) -> ScriptableUtils.getOptionValue(new ScaleContext(getAxis(), new ConfigurationEnvelop<>(context)), textStrokeWidthCallback, getAxis().getDefaultValues().getTicks().getTextStrokeWidth()).intValue());
		// sets function to proxy callback in order to invoke the java interface
		this.backdropColorCallbackProxy
				.setCallback((contextFunction, context) -> ScriptableUtils.getOptionValueAsColor(new ScaleContext(getAxis(), new ConfigurationEnvelop<>(context)), backdropColorCallback, getConfiguration().getBackdropColorAsString(), false));
		// sets function to proxy callback in order to invoke the java interface
		this.showLabelBackdropCallbackProxy.setCallback((contextFunction, context) -> ScriptableUtils.getOptionValue(new ScaleContext(getAxis(), new ConfigurationEnvelop<>(context)), showLabelBackdropCallback, getConfiguration().isShowLabelBackdrop()));
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
	 * Returns the padding of label backdrop.
	 * 
	 * @return padding of label backdrop.
	 */
	public Padding getBackdropPadding() {
		return backdropPadding;
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
	 * Sets the color of label backdrops.
	 * 
	 * @param backdropColor color of label backdrops.
	 */
	public void setBackdropColor(IsColor backdropColor) {
		// reset callback
		setBackdropColor((ColorCallback<ScaleContext>) null);
		// stores values
		getConfiguration().setBackdropColor(backdropColor);
	}

	/**
	 * Sets the color of label backdrops.
	 * 
	 * @param backdropColor color of label backdrops.
	 */
	public void setBackdropColor(String backdropColor) {
		// reset callback
		setBackdropColor((ColorCallback<ScaleContext>) null);
		// stores values
		getConfiguration().setBackdropColor(backdropColor);
	}

	/**
	 * Returns the color of label backdrops.
	 * 
	 * @return color of label backdrops.
	 */
	public String getBackdropColorAsString() {
		return getConfiguration().getBackdropColorAsString();
	}

	/**
	 * Returns the color of label backdrops.
	 * 
	 * @return color of label backdrops.
	 */
	public IsColor getBackdropColor() {
		return getConfiguration().getBackdropColor();
	}
	
	/**
	 * If true, draw a background behind the tick labels.
	 * 
	 * @param showLabelBackdrop if true, draw a background behind the tick labels.
	 */
	public void setShowLabelBackdrop(boolean showLabelBackdrop) {
		// reset callback
		setShowLabelBackdrop((ShowLabelBackdropCallback) null);
		// stores values
		getConfiguration().setShowLabelBackdrop(showLabelBackdrop);
	}

	/**
	 * If true, draw a background behind the tick labels.
	 * 
	 * @return if true, draw a background behind the tick labels.
	 */
	public boolean isShowLabelBackdrop() {
		return getConfiguration().isShowLabelBackdrop();
	}
	
	// ---------------------
	// CALLBACKS
	// ---------------------

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
	 * Returns the backdrop padding callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the backdrop padding callback, if set, otherwise <code>null</code>.
	 */
	public PaddingCallback<ScaleContext> getBackdropPaddingCallback() {
		return backdropPaddingCallback;
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
		// stores and manages callback
		getAxis().setCallback(getConfiguration(), Property.COLOR, colorCallback, colorCallbackProxy);
	}

	/**
	 * Sets the text stroke color callback.
	 * 
	 * @param textStrokeColorCallback the text stroke color callback to set
	 */
	public void setTextStrokeColor(ColorCallback<ScaleContext> textStrokeColorCallback) {
		// sets the callback
		this.textStrokeColorCallback = textStrokeColorCallback;
		// stores and manages callback
		getAxis().setCallback(getConfiguration(), Property.TEXT_STROKE_COLOR, textStrokeColorCallback, textStrokeColorCallbackProxy);
	}

	/**
	 * Sets the text stroke width callback.
	 * 
	 * @param textStrokeWidthCallback the text stroke width callback to set
	 */
	public void setTextStrokeWidth(WidthCallback<ScaleContext> textStrokeWidthCallback) {
		// sets the callback
		this.textStrokeWidthCallback = textStrokeWidthCallback;
		// stores and manages callback
		getAxis().setCallback(getConfiguration(), Property.TEXT_STROKE_WIDTH, textStrokeWidthCallback, textStrokeWidthCallbackProxy);
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
		// stores and manages callback
		getAxis().setCallback(getConfiguration(), Property.FONT, fontCallback, fontCallbackProxy);
	}
	
	/**
	 * Sets the backdrop padding callback.
	 * 
	 * @param backdropPaddingCallback the backdrop padding callback to set
	 */
	public void setBackdropPadding(PaddingCallback<ScaleContext> backdropPaddingCallback) {
		// sets the callback
		this.backdropPaddingCallback = backdropPaddingCallback;
		// stores and manages callback
		getAxis().setCallback(getConfiguration(), Property.BACKDROP_PADDING, backdropPaddingCallback, backdropPaddingCallbackProxy);
	}
	
	/**
	 * Returns the backdrop color callback instance.
	 * 
	 * @return the backdrop color callback instance
	 */
	public ColorCallback<ScaleContext> getBackdropColorCallback() {
		return backdropColorCallback;
	}

	/**
	 * Sets the backdrop color callback instance.
	 * 
	 * @param backdropColorCallback the backdrop color callback instance
	 */
	public void setBackdropColor(ColorCallback<ScaleContext> backdropColorCallback) {
		// stores callback
		this.backdropColorCallback = backdropColorCallback;
		// stores and manages callback
		getAxis().setCallback(getAxis().getConfiguration().getTicks(), Property.BACKDROP_COLOR, backdropColorCallback, backdropColorCallbackProxy);
	}
	
	/**
	 * Returns the show label backdrop callback instance.
	 * 
	 * @return the show label backdrop callback instance
	 */
	public ShowLabelBackdropCallback getShowLabelBackdrop() {
		return showLabelBackdropCallback;
	}

	/**
	 * Sets the show label backdrop callback instance.
	 * 
	 * @param showLabelBackdropCallback the show label backdrop callback instance
	 */
	public void setShowLabelBackdrop(ShowLabelBackdropCallback showLabelBackdropCallback) {
		// stores callback
		this.showLabelBackdropCallback = showLabelBackdropCallback;
		// stores and manages callback
		getAxis().setCallback(getAxis().getConfiguration().getTicks(), Property.SHOW_LABEL_BACKDROP, showLabelBackdropCallback, showLabelBackdropCallbackProxy);
	}

	// ------------------------------
	// internal methods for callback
	// ------------------------------
	
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