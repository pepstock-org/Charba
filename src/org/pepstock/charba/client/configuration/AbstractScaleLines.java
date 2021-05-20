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

import org.pepstock.charba.client.callbacks.BorderDashOffsetCallback;
import org.pepstock.charba.client.callbacks.ColorCallback;
import org.pepstock.charba.client.callbacks.ScaleContext;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyDoubleCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyIntegerCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyObjectCallback;
import org.pepstock.charba.client.callbacks.ScriptableUtils;
import org.pepstock.charba.client.callbacks.WidthCallback;
import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.defaults.IsDefaultScaleLines;

/**
 * The scale lines configuration ({@link Grid} and {@link RadialAngleLines}) defines options for the lines that run a the axis.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
abstract class AbstractScaleLines extends AxisContainer {

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the color function
	private final CallbackProxy<ProxyObjectCallback> colorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the line width function
	private final CallbackProxy<ProxyIntegerCallback> lineWidthCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the border dash offset function
	private final CallbackProxy<ProxyDoubleCallback> borderDashOffsetCallbackProxy = JsHelper.get().newCallbackProxy();

	// color callback instance
	private ColorCallback<ScaleContext> colorCallback = null;
	// line width callback instance
	private WidthCallback<ScaleContext> lineWidthCallback = null;
	// border dashoffset callback instance
	private BorderDashOffsetCallback<ScaleContext> borderDashOffsetCallback = null;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		COLOR("color"),
		LINE_WIDTH("lineWidth"),
		BORDER_DASH_OFFSET("borderDashOffset");

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
	 * Builds the object storing the axis which this scale lines belongs to.
	 * 
	 * @param axis axis which scale grid belongs to.
	 * @param defaultValues default value to use
	 */
	AbstractScaleLines(Axis axis, IsDefaultScaleLines defaultValues) {
		super(axis);
		// checks default value instance
		Checker.checkAndGetIfValid(defaultValues, "Default value argument");
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// sets function to proxy callback in order to invoke the java interface
		this.colorCallbackProxy.setCallback((context) -> ScriptableUtils.getOptionValueAsColor(getAxis().createContext(context), getColorCallback(), defaultValues.getColorAsString(), false));
		// sets function to proxy callback in order to invoke the java interface
		this.lineWidthCallbackProxy.setCallback((context) -> ScriptableUtils.getOptionValue(getAxis().createContext(context), getLineWidthCallback(), defaultValues.getLineWidth()).intValue());
		// sets function to proxy callback in order to invoke the java interface
		this.borderDashOffsetCallbackProxy.setCallback((context) -> ScriptableUtils.getOptionValue(getAxis().createContext(context), getBorderDashOffsetCallback(), defaultValues.getBorderDashOffset()).doubleValue());
	}

	/**
	 * Returns the options element to use to store the properties.
	 * 
	 * @return the options element to use to store the properties
	 */
	abstract AbstractNode getElement();

	/**
	 * Returns the color callback instance.
	 * 
	 * @return the color callback instance
	 */
	public ColorCallback<ScaleContext> getColorCallback() {
		return colorCallback;
	}

	/**
	 * Sets the color callback instance.
	 * 
	 * @param colorCallback the color callback instance
	 */
	public void setColor(ColorCallback<ScaleContext> colorCallback) {
		// stores callback
		this.colorCallback = colorCallback;
		// stores and manages callback
		getAxis().setCallback(checkAndGet(), Property.COLOR, colorCallback, colorCallbackProxy);
	}

	/**
	 * Returns the line width callback instance.
	 * 
	 * @return the line width callback instance
	 */
	public WidthCallback<ScaleContext> getLineWidthCallback() {
		return lineWidthCallback;
	}

	/**
	 * Sets the line width callback instance.
	 * 
	 * @param lineWidthCallback the line width callback instance.
	 */
	public void setLineWidth(WidthCallback<ScaleContext> lineWidthCallback) {
		// stores callback
		this.lineWidthCallback = lineWidthCallback;
		// stores and manages callback
		getAxis().setCallback(checkAndGet(), Property.LINE_WIDTH, lineWidthCallback, lineWidthCallbackProxy);
	}

	/**
	 * Returns the border dash offset callback instance.
	 * 
	 * @return the border dash offset callback instance
	 */
	public BorderDashOffsetCallback<ScaleContext> getBorderDashOffsetCallback() {
		return borderDashOffsetCallback;
	}

	/**
	 * Sets the border dash offset callback instance.
	 * 
	 * @param borderDashOffsetCallback the border dash offset callback instance
	 */
	public void setBorderDashOffset(BorderDashOffsetCallback<ScaleContext> borderDashOffsetCallback) {
		// stores callback
		this.borderDashOffsetCallback = borderDashOffsetCallback;
		// stores and manages callback
		getAxis().setCallback(checkAndGet(), Property.BORDER_DASH_OFFSET, borderDashOffsetCallback, borderDashOffsetCallbackProxy);
	}

	/**
	 * Gets the {@link AbstractNode} instance checking if is consistent.
	 * 
	 * @return the {@link AbstractNode} instance
	 */
	private AbstractNode checkAndGet() {
		return Checker.checkAndGetIfValid(getElement(), "Node element");
	}

}