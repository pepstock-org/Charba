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
import org.pepstock.charba.client.callbacks.ScaleScriptableContext;
import org.pepstock.charba.client.callbacks.ScriptableFunctions;
import org.pepstock.charba.client.callbacks.ScriptableUtils;
import org.pepstock.charba.client.callbacks.WidthCallback;
import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.CallbackProxy;
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
	private final CallbackProxy<ScriptableFunctions.ProxyObjectCallback> colorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the line width function
	private final CallbackProxy<ScriptableFunctions.ProxyIntegerCallback> lineWidthCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the border dash offset function
	private final CallbackProxy<ScriptableFunctions.ProxyDoubleCallback> borderDashOffsetCallbackProxy = JsHelper.get().newCallbackProxy();

	// color callback instance
	private ColorCallback<ScaleScriptableContext> colorCallback = null;
	// line width callback instance
	private WidthCallback<ScaleScriptableContext> lineWidthCallback = null;
	// border dashoffset callback instance
	private BorderDashOffsetCallback<ScaleScriptableContext> borderDashOffsetCallback = null;

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
		if (defaultValues == null) {
			// exception!
			throw new IllegalArgumentException("Default value argument is null");
		}
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// gets value calling callback
		colorCallbackProxy.setCallback((contextFunction, context) -> ScriptableUtils.getOptionValueAsColor(new ScaleScriptableContext(getAxis(), new ConfigurationEnvelop<>(context)), colorCallback, defaultValues.getColorAsString(), false));
		// gets value calling callback
		lineWidthCallbackProxy.setCallback((contextFunction, context) -> ScriptableUtils.getOptionValue(new ScaleScriptableContext(getAxis(), new ConfigurationEnvelop<>(context)), lineWidthCallback, defaultValues.getLineWidth()).intValue());
		// gets value calling callback
		borderDashOffsetCallbackProxy
				.setCallback((contextFunction, context) -> ScriptableUtils.getOptionValue(new ScaleScriptableContext(getAxis(), new ConfigurationEnvelop<>(context)), borderDashOffsetCallback, defaultValues.getBorderDashOffset()).doubleValue());
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
	public ColorCallback<ScaleScriptableContext> getColorCallback() {
		return colorCallback;
	}

	/**
	 * Sets the color callback instance.
	 * 
	 * @param colorCallback the color callback instance
	 */
	public void setColor(ColorCallback<ScaleScriptableContext> colorCallback) {
		// stores callback
		this.colorCallback = colorCallback;
		// checks if consistent
		if (colorCallback != null) {
			// adds the callback proxy function to java script object
			getAxis().getConfiguration().setCallback(checkAndGet(), Property.COLOR, colorCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			getAxis().getConfiguration().setCallback(checkAndGet(), Property.COLOR, null);
		}
	}

	/**
	 * Returns the line width callback instance.
	 * 
	 * @return the line width callback instance
	 */
	public WidthCallback<ScaleScriptableContext> getLineWidthCallback() {
		return lineWidthCallback;
	}

	/**
	 * Sets the line width callback instance.
	 * 
	 * @param lineWidthCallback the line width callback instance.
	 */
	public void setLineWidth(WidthCallback<ScaleScriptableContext> lineWidthCallback) {
		// stores callback
		this.lineWidthCallback = lineWidthCallback;
		// checks if consistent
		if (lineWidthCallback != null) {
			// adds the callback proxy function to java script object
			getAxis().getConfiguration().setCallback(checkAndGet(), Property.LINE_WIDTH, lineWidthCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			getAxis().getConfiguration().setCallback(checkAndGet(), Property.LINE_WIDTH, null);
		}
	}

	/**
	 * Returns the border dash offset callback instance.
	 * 
	 * @return the border dash offset callback instance
	 */
	public BorderDashOffsetCallback<ScaleScriptableContext> getBorderDashOffsetCallback() {
		return borderDashOffsetCallback;
	}

	/**
	 * Sets the border dash offset callback instance.
	 * 
	 * @param borderDashOffsetCallback the border dash offset callback instance
	 */
	public void setBorderDashOffset(BorderDashOffsetCallback<ScaleScriptableContext> borderDashOffsetCallback) {
		// stores callback
		this.borderDashOffsetCallback = borderDashOffsetCallback;
		// checks if consistent
		if (borderDashOffsetCallback != null) {
			// adds the callback proxy function to java script object
			getAxis().getConfiguration().setCallback(checkAndGet(), Property.BORDER_DASH_OFFSET, borderDashOffsetCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			getAxis().getConfiguration().setCallback(checkAndGet(), Property.BORDER_DASH_OFFSET, null);
		}
	}

	/**
	 * Gets the {@link AbstractNode} instance checking if is consistent.
	 * 
	 * @return the {@link AbstractNode} instance
	 */
	private AbstractNode checkAndGet() {
		AbstractNode node = getElement();
		// checks if consistent
		if (node == null) {
			// exception!
			throw new IllegalArgumentException("Node element is null");
		}
		return node;
	}

}