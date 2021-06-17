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

import org.pepstock.charba.client.callbacks.BorderRadiusCallback;
import org.pepstock.charba.client.callbacks.ColorCallback;
import org.pepstock.charba.client.callbacks.DatasetContext;
import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.callbacks.PointStyleCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyIntegerCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyObjectCallback;
import org.pepstock.charba.client.callbacks.ScriptableUtils;
import org.pepstock.charba.client.callbacks.WidthCallback;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.IsDefaultOptionsElement;
import org.pepstock.charba.client.dom.elements.Img;
import org.pepstock.charba.client.enums.PointStyle;
import org.pepstock.charba.client.options.AbstractElement;

/**
 * While chart types provide settings to configure the styling of each dataset, you sometimes want to style all datasets the same way.<br>
 * Options can be configured for four different types of elements: arc, lines, points, and bars.<br>
 * When set, these options apply to all objects of that type unless specifically overridden by the configuration attached to a dataset.
 * 
 * @author Andrea "Stock" Stocchero
 */
abstract class AbstractConfigurationElement<D extends IsDefaultOptionsElement> extends ConfigurationOptionsContainer {
	
	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		BACKGROUND_COLOR("backgroundColor"),
		BORDER_WIDTH("borderWidth"),
		BORDER_COLOR("borderColor"),
		HOVER_BACKGROUND_COLOR("hoverBackgroundColor"),
		HOVER_BORDER_WIDTH("hoverBorderWidth"),
		HOVER_BORDER_COLOR("hoverBorderColor");
		
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
	
	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the hover background color function
	private final CallbackProxy<ProxyObjectCallback> hoverBackgroundColorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the hover border color function
	private final CallbackProxy<ProxyObjectCallback> hoverBorderColorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the hover border width function
	private final CallbackProxy<ProxyIntegerCallback> hoverBorderWidthCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the background color function
	private final CallbackProxy<ProxyObjectCallback> backgroundColorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the border color function
	private final CallbackProxy<ProxyObjectCallback> borderColorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the border width function
	private final CallbackProxy<ProxyIntegerCallback> borderWidthCallbackProxy = JsHelper.get().newCallbackProxy();

	// background color callback instance
	private ColorCallback<DatasetContext> backgroundColorCallback = null;
	// border color callback instance
	private ColorCallback<DatasetContext> borderColorCallback = null;
	// borderWidth callback instance
	private WidthCallback<DatasetContext> borderWidthCallback = null;
	// hover background color callback instance
	private ColorCallback<DatasetContext> hoverBackgroundColorCallback = null;
	// hover border color callback instance
	private ColorCallback<DatasetContext> hoverBorderColorCallback = null;
	// hover borderWidth callback instance
	private WidthCallback<DatasetContext> hoverBorderWidthCallback = null;

	/***
	 * Builds the object with options.
	 * 
	 * @param options options instance
	 */
	AbstractConfigurationElement(ConfigurationOptions options) {
		super(options);
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// sets function to proxy callback in order to invoke the java interface
		this.backgroundColorCallbackProxy.setCallback(context -> ScriptableUtils.getOptionValueAsColor(createContext(context), getBackgroundColorCallback(), getDefaultElement().getBackgroundColorAsString()));
		// sets function to proxy callback in order to invoke the java interface
		this.borderColorCallbackProxy.setCallback(context -> ScriptableUtils.getOptionValueAsColor(createContext(context), getBorderColorCallback(), getDefaultElement().getBorderColorAsString(), false));
		// sets function to proxy callback in order to invoke the java interface
		this.borderWidthCallbackProxy.setCallback(context -> ScriptableUtils.getOptionValue(createContext(context), getBorderWidthCallback(), getDefaultElement().getBorderWidth()).intValue());
		// sets function to proxy callback in order to invoke the java interface
		this.hoverBackgroundColorCallbackProxy.setCallback(context -> ScriptableUtils.getOptionValueAsColor(createContext(context), getHoverBackgroundColorCallback(), getDefaultElement().getHoverBackgroundColorAsString()));
		// sets function to proxy callback in order to invoke the java interface
		this.hoverBorderColorCallbackProxy.setCallback(context -> ScriptableUtils.getOptionValueAsColor(createContext(context), getHoverBorderColorCallback(), getDefaultElement().getHoverBorderColorAsString(), false));
		// sets function to proxy callback in order to invoke the java interface
		this.hoverBorderWidthCallbackProxy.setCallback(context -> ScriptableUtils.getOptionValue(createContext(context), getHoverBorderWidthCallback(), getDefaultElement().getHoverBorderWidth()).intValue());
	}

	/**
	 * Returns the element instance to be managed.
	 * 
	 * @return the element instance to be managed
	 */
	protected abstract AbstractElement<D> getElement();

	/**
	 * Returns the element instance to be managed.
	 * 
	 * @return the element instance to be managed
	 */
	protected abstract D getDefaultElement();
	
	/**
	 * Creates a data set context for callback.
	 * 
	 * @param context native context, passed by CHART.JS
	 * @return a data set context for callback
	 */
	final DatasetContext createContext(NativeObject context) {
		return new DatasetContext(context);
	}

	/**
	 * Sets the background color.
	 * 
	 * @param backgroundColor the background color.
	 */
	public void setBackgroundColor(IsColor backgroundColor) {
		// resets callback
		setBackgroundColor((ColorCallback<DatasetContext>)null);
		// stores new value
		getElement().setBackgroundColor(backgroundColor);
	}

	/**
	 * Sets the background color.
	 * 
	 * @param backgroundColor the background color.
	 */
	public void setBackgroundColor(String backgroundColor) {
		// resets callback
		setBackgroundColor((ColorCallback<DatasetContext>)null);
		// stores new value
		getElement().setBackgroundColor(backgroundColor);
	}

	/**
	 * Returns the background color.
	 * 
	 * @return the background color.
	 */
	public String getBackgroundColorAsString() {
		return getElement().getBackgroundColorAsString();
	}

	/**
	 * Returns the background color.
	 * 
	 * @return the background color.
	 */
	public IsColor getBackgroundColor() {
		return getElement().getBackgroundColor();
	}

	/**
	 * Sets the border width.
	 * 
	 * @param borderWidth the border width.
	 */
	public void setBorderWidth(int borderWidth) {
		// resets callback
		setBorderWidth((WidthCallback<DatasetContext>)null);
		// stores new value
		getElement().setBorderWidth(borderWidth);
	}

	/**
	 * Returns the border width.
	 * 
	 * @return the border width.
	 */
	public int getBorderWidth() {
		return getElement().getBorderWidth();
	}

	/**
	 * Sets the border color.
	 * 
	 * @param borderColor the border color.
	 */
	public void setBorderColor(IsColor borderColor) {
		// resets callback
		setBorderColor((ColorCallback<DatasetContext>)null);
		// stores new value
		getElement().setBorderColor(borderColor);
	}

	/**
	 * Sets the border color.
	 * 
	 * @param borderColor the border color.
	 */
	public void setBorderColor(String borderColor) {
		// resets callback
		setBorderColor((ColorCallback<DatasetContext>)null);
		// stores new value
		getElement().setBorderColor(borderColor);
	}

	/**
	 * Returns the border color.
	 * 
	 * @return the border color.
	 */
	public String getBorderColorAsString() {
		return getElement().getBorderColorAsString();
	}

	/**
	 * Returns the border color.
	 * 
	 * @return the border color.
	 */
	public IsColor getBorderColor() {
		return getElement().getBorderColor();
	}

	// ------------------------------------------------------
	// HOVER
	// ------------------------------------------------------

	/**
	 * Sets the background color when hovered.
	 * 
	 * @param backgroundColor the background color when hovered.
	 */
	public void setHoverBackgroundColor(IsColor backgroundColor) {
		getElement().setHoverBackgroundColor(backgroundColor);
	}

	/**
	 * Sets the background color when hovered.
	 * 
	 * @param backgroundColor the background color when hovered.
	 */
	public void setHoverBackgroundColor(String backgroundColor) {
		getElement().setHoverBackgroundColor(backgroundColor);
	}

	/**
	 * Returns the background color when hovered.
	 * 
	 * @return the background color when hovered.
	 */
	public String getHoverBackgroundColorAsString() {
		return getElement().getHoverBackgroundColorAsString();
	}

	/**
	 * Returns the background color when hovered.
	 * 
	 * @return the background color when hovered.
	 */
	public IsColor getHoverBackgroundColor() {
		return getElement().getHoverBackgroundColor();
	}

	/**
	 * Sets the border width when hovered.
	 * 
	 * @param borderWidth the border width when hovered.
	 */
	public void setHoverBorderWidth(int borderWidth) {
		getElement().setHoverBorderWidth(borderWidth);
	}

	/**
	 * Returns the border width when hovered.
	 * 
	 * @return the border width when hovered.
	 */
	public int getHoverBorderWidth() {
		return getElement().getHoverBorderWidth();
	}

	/**
	 * Sets the border color when hovered.
	 * 
	 * @param borderColor the border color when hovered.
	 */
	public void setHoverBorderColor(IsColor borderColor) {
		getElement().setHoverBorderColor(borderColor);
	}

	/**
	 * Sets the border color when hovered.
	 * 
	 * @param borderColor the border color when hovered.
	 */
	public void setHoverBorderColor(String borderColor) {
		getElement().setHoverBorderColor(borderColor);
	}

	/**
	 * Returns the border color when hovered.
	 * 
	 * @return the border color when hovered.
	 */
	public String getHoverBorderColorAsString() {
		return getElement().getHoverBorderColorAsString();
	}

	/**
	 * Returns the border color when hovered.
	 * 
	 * @return the border color when hovered.
	 */
	public IsColor getHoverBorderColor() {
		return getElement().getHoverBorderColor();
	}
	
	// -----------------
	// CALLBACK
	// -----------------

	/**
	 * Returns the background color callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the background color callback, if set, otherwise <code>null</code>.
	 */
	public ColorCallback<DatasetContext> getBackgroundColorCallback() {
		return backgroundColorCallback;
	}

	/**
	 * Sets the background color callback.
	 * 
	 * @param backgroundColorCallback the background color callback.
	 */
	public void setBackgroundColor(ColorCallback<DatasetContext> backgroundColorCallback) {
		// sets the callback
		this.backgroundColorCallback = backgroundColorCallback;
		// stores and manages callback
		getChart().getOptions().setCallback(getElement(), Property.BACKGROUND_COLOR, backgroundColorCallback, backgroundColorCallbackProxy);
	}

	/**
	 * Sets the background color callback.
	 * 
	 * @param backgroundColorCallback the background color callback.
	 */
	public void setBackgroundColor(NativeCallback backgroundColorCallback) {
		// resets callback
		setBackgroundColor((ColorCallback<DatasetContext>) null);
		// stores and manages callback
		getChart().getOptions().setCallback(getElement(), Property.BACKGROUND_COLOR, backgroundColorCallback);
	}

	/**
	 * Returns the border color callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the border color callback, if set, otherwise <code>null</code>.
	 */
	public ColorCallback<DatasetContext> getBorderColorCallback() {
		return borderColorCallback;
	}

	/**
	 * Sets the border color callback.
	 * 
	 * @param borderColorCallback the border color callback.
	 */
	public void setBorderColor(ColorCallback<DatasetContext> borderColorCallback) {
		// sets the callback
		this.borderColorCallback = borderColorCallback;
		// stores and manages callback
		getChart().getOptions().setCallback(getElement(), Property.BORDER_COLOR, borderColorCallback, borderColorCallbackProxy);
	}

	/**
	 * Sets the border color callback.
	 * 
	 * @param borderColorCallback the border color callback.
	 */
	public void setBorderColor(NativeCallback borderColorCallback) {
		// resets callback
		setBorderColor((ColorCallback<DatasetContext>) null);
		// stores and manages callback
		getChart().getOptions().setCallback(getElement(), Property.BORDER_COLOR, borderColorCallback);
	}

	/**
	 * Returns the border width callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the border width callback, if set, otherwise <code>null</code>.
	 */
	public WidthCallback<DatasetContext> getBorderWidthCallback() {
		return borderWidthCallback;
	}

	/**
	 * Sets the border width callback.
	 * 
	 * @param borderWidthCallback the border width callback.
	 */
	public void setBorderWidth(WidthCallback<DatasetContext> borderWidthCallback) {
		// sets the callback
		this.borderWidthCallback = borderWidthCallback;
		// stores and manages callback
		getChart().getOptions().setCallback(getElement(), Property.BORDER_WIDTH, borderWidthCallback, borderWidthCallbackProxy);
	}

	/**
	 * Sets the border width callback.
	 * 
	 * @param borderWidthCallback the border width callback.
	 */
	public void setBorderWidth(NativeCallback borderWidthCallback) {
		// resets callback
		setBorderWidth((WidthCallback<DatasetContext>) null);
		// stores and manages callback
		getChart().getOptions().setCallback(getElement(), Property.BORDER_WIDTH, borderWidthCallback);
	}
	
	// -----------------
	// CALLBACK
	// -----------------
	
	/**
	 * Returns the background color callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the background color callback, if set, otherwise <code>null</code>.
	 */
	public ColorCallback<DatasetContext> getHoverBackgroundColorCallback() {
		return hoverBackgroundColorCallback;
	}

	/**
	 * Sets the background color callback, as hovered.
	 * 
	 * @param hoverBackgroundColorCallback the background color callback, as hovered
	 */
	public void setHoverBackgroundColor(ColorCallback<DatasetContext> hoverBackgroundColorCallback) {
		// sets the callback
		this.hoverBackgroundColorCallback = hoverBackgroundColorCallback;
		// stores and manages callback
		getChart().getOptions().setCallback(getElement(), Property.HOVER_BACKGROUND_COLOR, hoverBackgroundColorCallback, hoverBackgroundColorCallbackProxy);
	}

	/**
	 * Sets the background color callback, as hovered.
	 * 
	 * @param hoverBackgroundColorCallback the background color callback, as hovered
	 */
	public void setHoverBackgroundColor(NativeCallback hoverBackgroundColorCallback) {
		// resets callback
		setHoverBackgroundColor((ColorCallback<DatasetContext>) null);
		// stores and manages callback
		getChart().getOptions().setCallback(getElement(), Property.HOVER_BACKGROUND_COLOR, hoverBackgroundColorCallback);
	}

	/**
	 * Returns the border color callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the border color callback, if set, otherwise <code>null</code>.
	 */
	public ColorCallback<DatasetContext> getHoverBorderColorCallback() {
		return hoverBorderColorCallback;
	}

	/**
	 * Sets the border color callback, as hovered.
	 * 
	 * @param hoverBorderColorCallback the border color callback, as hovered
	 */
	public void setHoverBorderColor(ColorCallback<DatasetContext> hoverBorderColorCallback) {
		// sets the callback
		this.hoverBorderColorCallback = hoverBorderColorCallback;
		// stores and manages callback
		getChart().getOptions().setCallback(getElement(), Property.HOVER_BORDER_COLOR, hoverBorderColorCallback, hoverBorderColorCallbackProxy);
	}

	/**
	 * Sets the border color callback, as hovered.
	 * 
	 * @param hoverBorderColorCallback the border color callback, as hovered
	 */
	public void setHoverBorderColor(NativeCallback hoverBorderColorCallback) {
		// resets callback
		setHoverBorderColor((ColorCallback<DatasetContext>) null);
		// stores and manages callback
		getChart().getOptions().setCallback(getElement(), Property.HOVER_BORDER_COLOR, hoverBorderColorCallback);
	}

	/**
	 * Returns the border width callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the border width callback, if set, otherwise <code>null</code>.
	 */
	public WidthCallback<DatasetContext> getHoverBorderWidthCallback() {
		return hoverBorderWidthCallback;
	}

	/**
	 * Sets the border width callback, as hovered.
	 * 
	 * @param hoverBorderWidthCallback the border width callback, as hovered
	 */
	public void setHoverBorderWidth(WidthCallback<DatasetContext> hoverBorderWidthCallback) {
		// sets the callback
		this.hoverBorderWidthCallback = hoverBorderWidthCallback;
		// stores and manages callback
		getChart().getOptions().setCallback(getElement(), Property.HOVER_BORDER_WIDTH, hoverBorderWidthCallback, hoverBorderWidthCallbackProxy);
	}

	/**
	 * Sets the border width callback, as hovered.
	 * 
	 * @param hoverBorderWidthCallback the border width callback, as hovered
	 */
	public void setHoverBorderWidth(NativeCallback hoverBorderWidthCallback) {
		// resets callback
		setHoverBorderWidth((WidthCallback<DatasetContext>) null);
		// stores and manages callback
		getChart().getOptions().setCallback(getElement(), Property.HOVER_BORDER_WIDTH, hoverBorderWidthCallback);
	}
	
	// ------------------------
	// INTERNALS for CALLBACKS
	// ------------------------

	/**
	 * Returns an integer when the callback has been activated.
	 * 
	 * @param context native object as context.
	 * @param callback {@link BorderRadiusCallback} instance to be invoked
	 * @param defaultValue default value for this border radius.
	 * @return a object property value, as integer
	 */
	final int onBorderRadius(DatasetContext context, BorderRadiusCallback callback, int defaultValue) {
		// gets value
		Object value = ScriptableUtils.getOptionValue(context, callback);
		// checks if is an integer
		if (value instanceof Number) {
			// casts to number
			Number number = (Number) value;
			// returns the integer
			return number.intValue();
		}
		// if here, the value of callback is not consistent
		// returns the default value
		return defaultValue;
	}
	
	/**
	 * Returns a {@link PointStyle} or {@link Img} when the callback has been activated.
	 * 
	 * @param context native object as context.
	 * @param callback callback instance to be invoked
	 * @param defaultValue default point style value
	 * @return a object property value, as {@link PointStyle} or {@link Img}
	 */
	final Object onPointStyle(DatasetContext context, PointStyleCallback callback, PointStyle defaultValue) {
		// gets value
		Object result = ScriptableUtils.getOptionValue(context, callback);
		// checks result
		if (result instanceof PointStyle) {
			// is point style instance
			PointStyle style = (PointStyle) result;
			return style.value();
		} else if (result instanceof Img) {
			// is image element instance
			return result;
		}
		// checks defaults
		Checker.checkIfValid(defaultValue, "Default point style argument");
		// default result
		return defaultValue.value();
	}


}