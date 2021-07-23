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
import org.pepstock.charba.client.callbacks.BorderSkippedCallback;
import org.pepstock.charba.client.callbacks.DatasetContext;
import org.pepstock.charba.client.callbacks.EnableBorderRadiusCallback;
import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.callbacks.PointStyleCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyBooleanCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyIntegerCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyObjectCallback;
import org.pepstock.charba.client.callbacks.ScriptableUtils;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.defaults.IsDefaultBar;
import org.pepstock.charba.client.dom.elements.Canvas;
import org.pepstock.charba.client.dom.elements.Img;
import org.pepstock.charba.client.enums.BorderSkipped;
import org.pepstock.charba.client.enums.PointStyle;
import org.pepstock.charba.client.enums.PointStyleType;
import org.pepstock.charba.client.options.AbstractElement;

/**
 * Bar elements are used to represent the bars in a bar chart.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class Bar extends AbstractConfigurationElement<IsDefaultBar> {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		BORDER_SKIPPED("borderSkipped"),
		BORDER_RADIUS("borderRadius"),
		ENABLE_BORDER_RADIUS("enableBorderRadius"),
		HOVER_BORDER_RADIUS("hoverBorderRadius"),
		POINT_STYLE("pointStyle");

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
	// callback proxy to invoke the border skipped function
	private final CallbackProxy<ProxyObjectCallback> borderSkippedCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the border radius function
	private final CallbackProxy<ProxyIntegerCallback> borderRadiusCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the hover border radius function
	private final CallbackProxy<ProxyIntegerCallback> hoverBorderRadiusCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the enable border radius function
	private final CallbackProxy<ProxyBooleanCallback> enableBorderRadiusCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the point style function
	private final CallbackProxy<ProxyObjectCallback> pointStyleCallbackProxy = JsHelper.get().newCallbackProxy();

	// border skipped callback instance
	private BorderSkippedCallback borderSkippedCallback = null;
	// border skipped callback instance
	private BorderRadiusCallback borderRadiusCallback = null;
	// hover borderWidth callback instance
	private BorderRadiusCallback hoverBorderRadiusCallback = null;
	// borderWidth callback instance
	private EnableBorderRadiusCallback enableBorderRadiusCallback = null;
	// point style callback instance
	private PointStyleCallback pointStyleCallback = null;

	/**
	 * Builds the object with options, root and setting the bar element.
	 * 
	 * @param options options instance
	 */
	Bar(ConfigurationOptions options) {
		super(options);
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// sets function to proxy callback in order to invoke the java interface
		this.borderRadiusCallbackProxy.setCallback(context -> onBorderRadius(createContext(context), getBorderRadiusCallback(), getDefaultElement().getBorderRadius()));
		// sets function to proxy callback in order to invoke the java interface
		this.hoverBorderRadiusCallbackProxy.setCallback(context -> onBorderRadius(createContext(context), getHoverBorderRadiusCallback(), getDefaultElement().getHoverBorderRadius()));
		// sets function to proxy callback in order to invoke the java interface
		this.borderSkippedCallbackProxy.setCallback(context -> onBorderSkipped(createContext(context), getDefaultElement().getBorderSkipped()));
		// sets function to proxy callback in order to invoke the java interface
		this.enableBorderRadiusCallbackProxy.setCallback(context -> ScriptableUtils.getOptionValue(createContext(context), getEnableBorderRadiusCallback(), getDefaultElement().isEnableBorderRadius()));
		// sets function to proxy callback in order to invoke the java interface
		this.pointStyleCallbackProxy.setCallback(context -> onPointStyle(createContext(context), getPointStyleCallback(), getDefaultElement().getPointStyle()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.AbstractConfigurationElement#getElement()
	 */
	@Override
	protected AbstractElement<IsDefaultBar> getElement() {
		return getConfiguration().getElements().getBar();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.AbstractConfigurationElement#getDefaultElement()
	 */
	@Override
	protected IsDefaultBar getDefaultElement() {
		return getOptions().getDefaultValues().getElements().getBar();
	}

	/**
	 * Sets the edge to skip drawing the border for.
	 * 
	 * @param borderSkipped to set <code>false</code> as border skipped. If set <code>true</code>, is ignored
	 */
	public void setBorderSkipped(boolean borderSkipped) {
		// resets callback
		setBorderSkipped((BorderSkippedCallback) null);
		// stores value
		getConfiguration().getElements().getBar().setBorderSkipped(borderSkipped);
	}

	/**
	 * Sets the edge to skip drawing the border for.
	 * 
	 * @param borderSkipped the edge to skip drawing the border for.
	 */
	public void setBorderSkipped(BorderSkipped borderSkipped) {
		// resets callback
		setBorderSkipped((BorderSkippedCallback) null);
		// stores value
		getConfiguration().getElements().getBar().setBorderSkipped(borderSkipped);
	}

	/**
	 * Returns the edge to skip drawing the border for.
	 * 
	 * @return the edge to skip drawing the border for.
	 */
	public BorderSkipped getBorderSkipped() {
		return getConfiguration().getElements().getBar().getBorderSkipped();
	}

	/**
	 * Sets the bar border radius (in pixels).
	 * 
	 * @param borderRadius the bar border radius (in pixels).
	 */
	public void setBorderRadius(int borderRadius) {
		// resets callback
		setBorderRadius((BorderRadiusCallback) null);
		// stores value
		getConfiguration().getElements().getBar().setBorderRadius(borderRadius);
	}

	/**
	 * Returns the bar border radius (in pixels).
	 * 
	 * @return the bar border radius (in pixels).
	 */
	public int getBorderRadius() {
		return getConfiguration().getElements().getBar().getBorderRadius();
	}

	/**
	 * Sets the bar border radius (in pixels) when hovered.
	 * 
	 * @param borderRadius the bar border radius (in pixels) when hovered.
	 */
	public void setHoverBorderRadius(int borderRadius) {
		// resets callback
		setHoverBorderRadius((BorderRadiusCallback) null);
		// stores value
		getConfiguration().getElements().getBar().setHoverBorderRadius(borderRadius);
	}

	/**
	 * Returns the bar border radius (in pixels) when hovered.
	 * 
	 * @return the bar border radius (in pixels) when hovered.
	 */
	public int getHoverBorderRadius() {
		return getConfiguration().getElements().getBar().getHoverBorderRadius();
	}

	/**
	 * Sets the style of the point.
	 * 
	 * @param pointStyle the style of the point.
	 */
	public void setPointStyle(PointStyle pointStyle) {
		// resets callback
		setPointStyle((PointStyleCallback) null);
		// stores value
		getConfiguration().getElements().getBar().setPointStyle(pointStyle);
	}

	/**
	 * Sets the style of the point as image.
	 * 
	 * @param pointStyle the style of the point.
	 */
	public void setPointStyle(Img pointStyle) {
		// resets callback
		setPointStyle((PointStyleCallback) null);
		// stores value
		getConfiguration().getElements().getBar().setPointStyle(pointStyle);
	}

	/**
	 * Sets the style of the point as canvas.
	 * 
	 * @param pointStyle the style of the point.
	 */
	public void setPointStyle(Canvas pointStyle) {
		// resets callback
		setPointStyle((PointStyleCallback) null);
		// stores value
		getConfiguration().getElements().getBar().setPointStyle(pointStyle);
	}

	/**
	 * Returns the type of point style.
	 * 
	 * @return the type of point style
	 */
	public PointStyleType getPointStyleType() {
		return getConfiguration().getElements().getBar().getPointStyleType();
	}

	/**
	 * Returns the style of the point.
	 * 
	 * @return the style of the point.
	 */
	public PointStyle getPointStyle() {
		return getConfiguration().getElements().getBar().getPointStyle();
	}

	/**
	 * Returns the style of the point as image.
	 * 
	 * @return the style of the point as image.
	 */
	public Img getPointStyleAsImage() {
		return getConfiguration().getElements().getBar().getPointStyleAsImage();
	}

	/**
	 * Returns the style of the point as canvas.
	 * 
	 * @return the style of the point as canvas.
	 */
	public Canvas getPointStyleAsCanvas() {
		return getConfiguration().getElements().getBar().getPointStyleAsCanvas();
	}

	/**
	 * If <code>true</code>, it only shows the borderRadius of a bar when the bar is at the end of the stack.
	 * 
	 * @param enableBorderRadius if <code>true</code>, it only shows the borderRadius of a bar when the bar is at the end of the stack
	 */
	public void setEnableBorderRadius(boolean enableBorderRadius) {
		// resets callback
		setEnableBorderRadius((EnableBorderRadiusCallback) null);
		// stores value
		getConfiguration().getElements().getBar().setEnableBorderRadius(enableBorderRadius);
	}

	/**
	 * If <code>true</code>, it only shows the borderRadius of a bar when the bar is at the end of the stack.
	 * 
	 * @return if <code>true</code>, it only shows the borderRadius of a bar when the bar is at the end of the stack
	 */
	public boolean isEnableBorderRadius() {
		return getConfiguration().getElements().getBar().isEnableBorderRadius();
	}

	// ----------------------
	// CALLBACKS
	// ----------------------

	/**
	 * Returns the border radius callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the border radius callback, if set, otherwise <code>null</code>.
	 */
	public BorderRadiusCallback getBorderRadiusCallback() {
		return borderRadiusCallback;
	}

	/**
	 * Sets the border radius callback.
	 * 
	 * @param borderRadiusCallback the border radius callback.
	 */
	public void setBorderRadius(BorderRadiusCallback borderRadiusCallback) {
		// sets the callback
		this.borderRadiusCallback = borderRadiusCallback;
		// stores and manages callback
		getChart().getOptions().setCallback(getElement(), Property.BORDER_RADIUS, borderRadiusCallback, borderRadiusCallbackProxy);
	}

	/**
	 * Sets the border radius callback.
	 * 
	 * @param borderRadiusCallback the border radius callback.
	 */
	public void setBorderRadius(NativeCallback borderRadiusCallback) {
		// resets callback
		setBorderRadius((BorderRadiusCallback) null);
		// stores and manages callback
		getChart().getOptions().setCallback(getElement(), Property.BORDER_RADIUS, borderRadiusCallback);
	}

	/**
	 * Returns the border radius callback, when hovered, if set, otherwise <code>null</code>.
	 * 
	 * @return the border radius callback, when hovered, if set, otherwise <code>null</code>.
	 */
	public BorderRadiusCallback getHoverBorderRadiusCallback() {
		return hoverBorderRadiusCallback;
	}

	/**
	 * Sets the border radius callback, when hovered.
	 * 
	 * @param hoverBorderRadiusCallback the border radius callback, when hovered.
	 */
	public void setHoverBorderRadius(BorderRadiusCallback hoverBorderRadiusCallback) {
		// sets the callback
		this.hoverBorderRadiusCallback = hoverBorderRadiusCallback;
		// stores and manages callback
		getChart().getOptions().setCallback(getElement(), Property.HOVER_BORDER_RADIUS, hoverBorderRadiusCallback, hoverBorderRadiusCallbackProxy);
	}

	/**
	 * Sets the border radius callback, when hovered.
	 * 
	 * @param hoverBorderRadiusCallback the border radius callback, when hovered.
	 */
	public void setHoverBorderRadius(NativeCallback hoverBorderRadiusCallback) {
		// resets callback
		setHoverBorderRadius((BorderRadiusCallback) null);
		// stores and manages callback
		getChart().getOptions().setCallback(getElement(), Property.HOVER_BORDER_RADIUS, hoverBorderRadiusCallback);
	}

	/**
	 * Returns the border skipped callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the border skipped callback, if set, otherwise <code>null</code>.
	 */
	public BorderSkippedCallback getBorderSkippedCallback() {
		return borderSkippedCallback;
	}

	/**
	 * Sets the border skipped callback.
	 * 
	 * @param borderSkippedCallback the border skipped callback.
	 */
	public void setBorderSkipped(BorderSkippedCallback borderSkippedCallback) {
		// sets the callback
		this.borderSkippedCallback = borderSkippedCallback;
		// stores and manages callback
		getChart().getOptions().setCallback(getElement(), Property.BORDER_SKIPPED, borderSkippedCallback, borderSkippedCallbackProxy);
	}

	/**
	 * Sets the border skipped callback.
	 * 
	 * @param borderSkippedCallback the border skipped callback.
	 */
	public void setBorderSkipped(NativeCallback borderSkippedCallback) {
		// resets callback
		setBorderSkipped((BorderSkippedCallback) null);
		// stores and manages callback
		getChart().getOptions().setCallback(getElement(), Property.BORDER_SKIPPED, borderSkippedCallback);
	}

	/**
	 * Returns the enable border radius callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the enable border radius callback, if set, otherwise <code>null</code>.
	 */
	public EnableBorderRadiusCallback getEnableBorderRadiusCallback() {
		return enableBorderRadiusCallback;
	}

	/**
	 * Sets the enable border radius callback.
	 * 
	 * @param enableBorderRadiusCallback the enable border radius callback.
	 */
	public void setEnableBorderRadius(EnableBorderRadiusCallback enableBorderRadiusCallback) {
		// sets the callback
		this.enableBorderRadiusCallback = enableBorderRadiusCallback;
		// stores and manages callback
		getChart().getOptions().setCallback(getElement(), Property.ENABLE_BORDER_RADIUS, enableBorderRadiusCallback, enableBorderRadiusCallbackProxy);
	}

	/**
	 * Sets the enable border radius callback.
	 * 
	 * @param enableBorderRadiusCallback the enable border radius callback.
	 */
	public void setEnableBorderRadius(NativeCallback enableBorderRadiusCallback) {
		// resets callback
		setEnableBorderRadius((EnableBorderRadiusCallback) null);
		// stores and manages callback
		getChart().getOptions().setCallback(getElement(), Property.ENABLE_BORDER_RADIUS, enableBorderRadiusCallback);
	}

	/**
	 * Returns the point style callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the point style callback, if set, otherwise <code>null</code>.
	 */
	public PointStyleCallback getPointStyleCallback() {
		return pointStyleCallback;
	}

	/**
	 * Sets the point style callback.
	 * 
	 * @param pointStyleCallback the point style callback.
	 */
	public void setPointStyle(PointStyleCallback pointStyleCallback) {
		// sets the callback
		this.pointStyleCallback = pointStyleCallback;
		// stores and manages callback
		getChart().getOptions().setCallback(getElement(), Property.POINT_STYLE, pointStyleCallback, pointStyleCallbackProxy);
	}

	/**
	 * Sets the point style callback.
	 * 
	 * @param pointStyleCallback the point style callback.
	 */
	public void setPointStyle(NativeCallback pointStyleCallback) {
		// resets callback
		setPointStyle((PointStyleCallback) null);
		// stores and manages callback
		getChart().getOptions().setCallback(getElement(), Property.POINT_STYLE, pointStyleCallback);
	}

	// ----------------------
	// METHODS for CALLBACKS
	// ----------------------

	/**
	 * Returns an object (boolean or {@link BorderSkipped}) when the callback has been activated.
	 * 
	 * @param context native object as context.
	 * @param defaultValue default value to use if the callback returns an inconsistent value
	 * @return a object property value, as boolean or {@link BorderSkipped}.
	 */
	private Object onBorderSkipped(DatasetContext context, BorderSkipped defaultValue) {
		// gets value
		BorderSkipped value = ScriptableUtils.getOptionValueAsString(context, getBorderSkippedCallback());
		// checks against the default
		BorderSkipped result = value == null ? defaultValue : value;
		// checks if is boolean
		if (BorderSkipped.FALSE.equals(result)) {
			return false;
		} else {
			// returns the string value
			return result.value();
		}
	}

}