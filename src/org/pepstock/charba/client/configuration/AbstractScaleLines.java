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

import org.pepstock.charba.client.callbacks.ScaleBorderDashOffsetCallback;
import org.pepstock.charba.client.callbacks.ScaleColorCallback;
import org.pepstock.charba.client.callbacks.ScaleLineWidthCallback;
import org.pepstock.charba.client.callbacks.ScaleScriptableContext;
import org.pepstock.charba.client.callbacks.ScriptableFunctions;
import org.pepstock.charba.client.callbacks.ScriptableUtils;
import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.defaults.IsDefaultScaleLines;

/**
 * The scale lines configuration ({@link GridLines} and {@link RadialAngleLines}) defines options for the lines that run a the axis.
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
	private final CallbackProxy<ScriptableFunctions.ProxyIntegerCallback> borderDashOffsetCallbackProxy = JsHelper.get().newCallbackProxy();

	// color callback instance
	private ScaleColorCallback colorCallback = null;
	// hover line width callback instance
	private ScaleLineWidthCallback lineWidthCallback = null;
	// border dashoffset callback instance
	private ScaleBorderDashOffsetCallback borderDashOffsetCallback = null;
	
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
	
	// instance of abstract node where to store the callbacks
	private final AbstractNode node;
	
	/**
	 * Builds the object storing the axis which this scale lines belongs to.
	 * 
	 * @param axis axis which scale grid lines belongs to.
	 * @param node instance of abstract node where to store the callbacks
	 * @param defaultValues default value to use
	 */
	AbstractScaleLines(Axis axis, AbstractNode node, IsDefaultScaleLines defaultValues) {
		super(axis);
		// checks node instance
		if (node == null) {
			throw new IllegalArgumentException("Node argument is null");
		}
		this.node = node;
		// checks default value instance
		if (defaultValues == null) {
			throw new IllegalArgumentException("Default value argument is null");
		}
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// gets value calling callback
		colorCallbackProxy.setCallback(
				(contextFunction, context) -> ScriptableUtils.getOptionValueAsColor(getAxis(), new ScaleScriptableContext(new ConfigurationEnvelop<>(context)), colorCallback, defaultValues.getColorAsString(), false));
		// gets value calling callback
		lineWidthCallbackProxy.setCallback(
				(contextFunction, context) -> ScriptableUtils.getOptionValue(getAxis(), new ScaleScriptableContext(new ConfigurationEnvelop<>(context)), lineWidthCallback, defaultValues.getLineWidth()).intValue());
		// gets value calling callback
		borderDashOffsetCallbackProxy.setCallback(
				(contextFunction, context) -> ScriptableUtils.getOptionValue(getAxis(), new ScaleScriptableContext(new ConfigurationEnvelop<>(context)), borderDashOffsetCallback, defaultValues.getBorderDashOffset()).intValue());
	}

	/**
	 * Returns the color callback instance.
	 * 
	 * @return the color callback instance
	 */
	public ScaleColorCallback getColorCallback() {
		return colorCallback;
	}

	/**
	 * Sets the color callback instance.
	 * 
	 * @param colorCallback the color callback instance
	 */
	public void setColor(ScaleColorCallback colorCallback) {
		// stores callback
		this.colorCallback = colorCallback;
		// checks if consistent
		if (colorCallback != null) {
			// adds the callback proxy function to java script object
			getAxis().getConfiguration().setCallback(node, Property.COLOR, colorCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			getAxis().getConfiguration().setCallback(node, Property.COLOR, null);
		}
	}

	/**
	 * Returns the line width callback instance.
	 * 
	 * @return the line width callback instance
	 */
	public ScaleLineWidthCallback getLineWidthCallback() {
		return lineWidthCallback;
	}

	/**
	 * Sets the line width callback instance.
	 * 
	 * @param lineWidthCallback the line width callback instance.
	 */
	public void setLineWidth(ScaleLineWidthCallback lineWidthCallback) {
		// stores callback
		this.lineWidthCallback = lineWidthCallback;
		// checks if consistent
		if (lineWidthCallback != null) {
			// adds the callback proxy function to java script object
			getAxis().getConfiguration().setCallback(node, Property.LINE_WIDTH, lineWidthCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			getAxis().getConfiguration().setCallback(node, Property.LINE_WIDTH, null);
		}
	}
	
	/**
	 * Returns the border dash offset callback instance.
	 * 
	 * @return the border dash offset callback instance
	 */
	public ScaleBorderDashOffsetCallback getBorderDashOffsetCallback() {
		return borderDashOffsetCallback;
	}

	/**
	 * Sets the border dash offset callback instance.
	 * 
	 * @param borderDashOffsetCallback the border dash offset callback instance
	 */
	public void setBorderDashOffset(ScaleBorderDashOffsetCallback borderDashOffsetCallback) {
		// stores callback
		this.borderDashOffsetCallback = borderDashOffsetCallback;
		// checks if consistent
		if (borderDashOffsetCallback != null) {
			// adds the callback proxy function to java script object
			getAxis().getConfiguration().setCallback(node, Property.BORDER_DASH_OFFSET, borderDashOffsetCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			getAxis().getConfiguration().setCallback(node, Property.BORDER_DASH_OFFSET, null);
		}
	}

}