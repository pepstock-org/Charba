/**
    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.
*/
package org.pepstock.charba.client.configuration;

import org.pepstock.charba.client.callbacks.BorderRadiusCallback;
import org.pepstock.charba.client.callbacks.ColorCallback;
import org.pepstock.charba.client.callbacks.FontCallback;
import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.callbacks.PointLabelCallback;
import org.pepstock.charba.client.callbacks.ScaleContext;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyIntegerCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyNativeObjectCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyStringCallback;
import org.pepstock.charba.client.callbacks.ScriptableIntegerChecker;
import org.pepstock.charba.client.callbacks.ScriptableUtil;
import org.pepstock.charba.client.callbacks.SimplePaddingCallback;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.data.BarBorderRadius;
import org.pepstock.charba.client.enums.Display;
import org.pepstock.charba.client.options.IsScriptableFontProvider;

import jsinterop.annotations.JsFunction;

/**
 * It is used to configure the point labels that are shown on the perimeter of the scale.<br>
 * Note that these options only apply if display is true.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class RadialPointLabels extends AxisContainer implements IsScriptableFontProvider<ScaleContext> {

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
		 * @param item label of the point
		 * @param index index of the label
		 * @return new label to show.
		 */
		Object call(String item, int index);
	}

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------

	// callback proxy to invoke the point labels function
	private final CallbackProxy<ProxyPointLabelCallback> pointLabelCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the font function
	private final CallbackProxy<ProxyNativeObjectCallback> fontCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the color function
	private final CallbackProxy<ProxyStringCallback> colorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the padding function
	private final CallbackProxy<ProxyIntegerCallback> paddingCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the backdrop color function
	private final CallbackProxy<ProxyStringCallback> backdropColorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the border radius function
	private final CallbackProxy<ProxyNativeObjectCallback> borderRadiusCallbackProxy = JsHelper.get().newCallbackProxy();

	// ---------------------------
	// -- USERS CALLBACKS ---
	// ---------------------------
	// user callback implementation for point labels
	private PointLabelCallback callback = null;
	// font callback instance
	private FontCallback<ScaleContext> fontCallback = null;
	// color callback instance
	private ColorCallback<ScaleContext> colorCallback = null;
	// padding callback instance
	private SimplePaddingCallback paddingCallback = null;
	// color callback instance
	private ColorCallback<ScaleContext> backdropColorCallback = null;
	// border radius callback instance
	private BorderRadiusCallback<ScaleContext> borderRadiusCallback = null;

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
		BORDER_RADIUS("borderRadius"),
		CALLBACK("callback"),
		COLOR("color"),
		FONT("font"),
		PADDING("padding");

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
		this.font = new Font(this, () -> getAxis().getConfiguration().getPointLabels().getFont());
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		this.pointLabelCallbackProxy.setCallback((item, index) -> {
			// gets callback
			PointLabelCallback callback = getCallback();
			// checks if callback is consistent
			if (callback != null) {
				// invokes callback
				Object result = callback.onCallback(getAxis(), item, index);
				// parses the result
				return ScriptableUtil.parseCallbackResult(result, item);
			}
			// returns passed item
			return item;
		});
		// sets function to proxy callback in order to invoke the java interface
		this.fontCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValueAsFont(getAxis().createContext(context), getFontCallback(), getAxis().getDefaultValues().getPointLabels().getFont()).nativeObject());
		// sets function to proxy callback in order to invoke the java interface
		this.colorCallbackProxy.setCallback(context -> onColor(getAxis().createContext(context), getColorCallback(), getAxis().getDefaultValues().getPointLabels().getColorAsString()));
		// sets function to proxy callback in order to invoke the java interface
		this.backdropColorCallbackProxy.setCallback(context -> onColor(getAxis().createContext(context), getBackdropColorCallback(), getAxis().getDefaultValues().getPointLabels().getBackdropColorAsString()));
		// sets function to proxy callback in order to invoke the java interface
		this.paddingCallbackProxy
				.setCallback(context -> ScriptableUtil.getOptionValueAsNumber(getAxis().createContext(context), getPaddingCallback(), getAxis().getDefaultValues().getPointLabels().getPadding(), ScriptableIntegerChecker.POSITIVE_OR_DEFAULT).intValue());
		// sets function to proxy callback in order to invoke the java interface
		this.borderRadiusCallbackProxy.setCallback(context -> BorderRadiusCallback.toObject(getAxis().createContext(context), getBorderRadiusCallback(), getAxis().getDefaultValues().getPointLabels().getBorderRadius()).nativeObject());
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
	 * The display option controls the visibility of labels.<br>
	 * Controls the axis global visibility (visible when true, hidden when false). When display: 'auto', the labels are visible only if there is enough space to draw them.
	 * 
	 * @param display display option controls the visibility of labels
	 */
	public void setDisplay(Display display) {
		getAxis().getScale().getPointLabels().setDisplay(display);
	}

	/**
	 * The display option controls the visibility of labels.<br>
	 * Controls the axis global visibility (visible when true, hidden when false). When display: 'auto', the labels are visible only if there is enough space to draw them.
	 * 
	 * @return display option controls the visibility of labels
	 */
	public Display getDisplay() {
		return getAxis().getScale().getPointLabels().getDisplay();
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
	 * @return the padding between chart and point labels, in pixels.
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
	 * Sets <code>true</code> if point labels are centered.
	 * 
	 * @param centered <code>true</code> if point labels are centered.
	 */
	public void setCentered(boolean centered) {
		getAxis().getScale().getPointLabels().setCentered(centered);
	}

	/**
	 * Returns if point labels are centered.
	 * 
	 * @return if point labels are centered
	 */
	public boolean isCentered() {
		return getAxis().getScale().getPointLabels().isCentered();
	}

	/**
	 * Returns the user callback to change point labels.
	 * 
	 * @return the callback
	 */
	public PointLabelCallback getCallback() {
		return callback;
	}

	/**
	 * Sets the user callback to change point labels.
	 * 
	 * @param callback the callback to set
	 */
	public void setCallback(PointLabelCallback callback) {
		// sets the callback
		this.callback = callback;
		// stores and manages callback
		getAxis().setCallback(getAxis().getConfiguration().getPointLabels(), Property.CALLBACK, callback, pointLabelCallbackProxy);
	}

	/**
	 * Sets the border radius.
	 * 
	 * @param radius the border radius.
	 */
	public void setBorderRadius(int radius) {
		// resets callback
		setBorderRadius((BorderRadiusCallback<ScaleContext>) null);
		// stores value
		getAxis().getScale().getPointLabels().setBorderRadius(radius);
	}

	/**
	 * Sets the border radius (in pixels).
	 * 
	 * @param borderRadius the border radius (in pixels).
	 */
	public void setBorderRadius(BarBorderRadius borderRadius) {
		// resets callback
		setBorderRadius((BorderRadiusCallback<ScaleContext>) null);
		// stores value
		getAxis().getScale().getPointLabels().setBorderRadius(borderRadius);
	}

	/**
	 * Returns the border radius (in pixels).
	 * 
	 * @return the border radius (in pixels).
	 */
	public int getBorderRadius() {
		return getAxis().getScale().getPointLabels().getBorderRadius();
	}

	/**
	 * Returns the border radius (in pixels).
	 * 
	 * @return the border radius (in pixels).
	 */
	public BarBorderRadius getBorderRadiusAsObject() {
		return getAxis().getScale().getPointLabels().getBorderRadiusAsObject();
	}

	// -------------------------
	// CALLBACKS
	// -------------------------

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
	 * Sets the font callback.
	 * 
	 * @param fontCallback the font callback to set
	 */
	@Override
	public void setFont(FontCallback<ScaleContext> fontCallback) {
		// sets the callback
		this.fontCallback = fontCallback;
		// stores and manages callback
		getAxis().setCallback(getAxis().getConfiguration().getPointLabels(), Property.FONT, fontCallback, fontCallbackProxy);
	}

	/**
	 * Sets the font callback.
	 * 
	 * @param fontCallback the font callback to set
	 */
	@Override
	public void setFont(NativeCallback fontCallback) {
		// resets callback
		setFont((FontCallback<ScaleContext>) null);
		// stores callback
		getAxis().setCallback(getAxis().getConfiguration().getPointLabels(), Property.FONT, fontCallback);
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
	 * Sets the padding callback.
	 * 
	 * @param paddingCallback the padding callback to set
	 */
	public void setPadding(SimplePaddingCallback paddingCallback) {
		// sets the callback
		this.paddingCallback = paddingCallback;
		// stores and manages callback
		getAxis().setCallback(getAxis().getConfiguration().getPointLabels(), Property.PADDING, paddingCallback, paddingCallbackProxy);
	}

	/**
	 * Sets the padding callback.
	 * 
	 * @param paddingCallback the padding callback to set
	 */
	public void setPadding(NativeCallback paddingCallback) {
		// resets callback
		setPadding((SimplePaddingCallback) null);
		// stores and manages callback
		getAxis().setCallback(getAxis().getConfiguration().getPointLabels(), Property.PADDING, paddingCallback);
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
	 * Sets the color callback.
	 * 
	 * @param colorCallback the color callback to set
	 */
	public void setColor(ColorCallback<ScaleContext> colorCallback) {
		// sets the callback
		this.colorCallback = colorCallback;
		// stores and manages callback
		getAxis().setCallback(getAxis().getConfiguration().getPointLabels(), Property.COLOR, colorCallback, colorCallbackProxy);
	}

	/**
	 * Sets the color callback.
	 * 
	 * @param colorCallback the color callback to set
	 */
	public void setColor(NativeCallback colorCallback) {
		// resets callback
		setColor((ColorCallback<ScaleContext>) null);
		// stores and manages callback
		getAxis().setCallback(getAxis().getConfiguration().getPointLabels(), Property.COLOR, colorCallback);
	}

	/**
	 * Returns the backdrop color callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the backdrop color callback, if set, otherwise <code>null</code>.
	 */
	public ColorCallback<ScaleContext> getBackdropColorCallback() {
		return backdropColorCallback;
	}

	/**
	 * Sets the backdrop color callback.
	 * 
	 * @param backdropColorCallback the backdrop color callback to set
	 */
	public void setBackdropColor(ColorCallback<ScaleContext> backdropColorCallback) {
		// sets the callback
		this.backdropColorCallback = backdropColorCallback;
		// stores and manages callback
		getAxis().setCallback(getAxis().getConfiguration().getPointLabels(), Property.BACKDROP_COLOR, backdropColorCallback, backdropColorCallbackProxy);
	}

	/**
	 * Sets the backdrop color callback.
	 * 
	 * @param backdropColorCallback the backdrop color callback to set
	 */
	public void setBackdropColor(NativeCallback backdropColorCallback) {
		// resets callback
		setBackdropColor((ColorCallback<ScaleContext>) null);
		// stores and manages callback
		getAxis().setCallback(getAxis().getConfiguration().getPointLabels(), Property.BACKDROP_COLOR, backdropColorCallback);
	}

	/**
	 * Returns the callback called to set the border radius.
	 * 
	 * @return the callback called to set the border radius
	 */
	public BorderRadiusCallback<ScaleContext> getBorderRadiusCallback() {
		return borderRadiusCallback;
	}

	/**
	 * Sets the callback to set the border radius.
	 * 
	 * @param borderRadiusCallback to set the border radius
	 */
	public void setBorderRadius(BorderRadiusCallback<ScaleContext> borderRadiusCallback) {
		// sets the callback
		this.borderRadiusCallback = borderRadiusCallback;
		// stores and manages callback
		getAxis().setCallback(getAxis().getConfiguration().getPointLabels(), Property.BORDER_RADIUS, borderRadiusCallback, borderRadiusCallbackProxy);
	}

	/**
	 * Sets the callback to set the border radius.
	 * 
	 * @param borderRadiusCallback to set the border radius
	 */
	public void setBorderRadius(NativeCallback borderRadiusCallback) {
		// resets callback
		setBorderRadius((BorderRadiusCallback<ScaleContext>) null);
		// stores and manages callback
		getAxis().setCallback(getAxis().getConfiguration().getPointLabels(), Property.BORDER_RADIUS, borderRadiusCallback);
	}

	// -------------------------
	// INTERNALS
	// -------------------------

	/**
	 * Returns a string as color when the callback has been activated.
	 * 
	 * @param context native object as context
	 * @param callback callback to invoke
	 * @param defaultValue default value of the color
	 * @return a string as color
	 */
	private String onColor(ScaleContext context, ColorCallback<ScaleContext> callback, String defaultValue) {
		// gets value
		Object result = ScriptableUtil.getOptionValueAsColor(context, callback, defaultValue, false);
		// checks if result is a string
		if (result instanceof String) {
			// returns result
			return (String) result;
		}
		// default result
		return defaultValue;
	}

}