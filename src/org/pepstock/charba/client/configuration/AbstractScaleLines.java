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

import org.pepstock.charba.client.callbacks.ColorCallback;
import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.callbacks.ScaleContext;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyIntegerCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyObjectCallback;
import org.pepstock.charba.client.callbacks.ScriptableIntegerChecker;
import org.pepstock.charba.client.callbacks.ScriptableUtil;
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

	// color callback instance
	private ColorCallback<ScaleContext> colorCallback = null;
	// line width callback instance
	private WidthCallback<ScaleContext> lineWidthCallback = null;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		COLOR("color"),
		LINE_WIDTH("lineWidth");

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
		this.colorCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValueAsColor(getAxis().createContext(context), getColorCallback(), defaultValues.getColorAsString(), false));
		// sets function to proxy callback in order to invoke the java interface
		this.lineWidthCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValueAsNumber(getAxis().createContext(context), getLineWidthCallback(), defaultValues.getLineWidth(), ScriptableIntegerChecker.POSITIVE_OR_DEFAULT).intValue());
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
	 * Sets the color callback instance.
	 * 
	 * @param colorCallback the color callback instance
	 */
	public void setColor(NativeCallback colorCallback) {
		// resets callback
		setColor((ColorCallback<ScaleContext>) null);
		// stores and manages callback
		getAxis().setCallback(checkAndGet(), Property.COLOR, colorCallback);
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
	 * Sets the line width callback instance.
	 * 
	 * @param lineWidthCallback the line width callback instance.
	 */
	public void setLineWidth(NativeCallback lineWidthCallback) {
		// resets callback
		setLineWidth((WidthCallback<ScaleContext>) null);
		// stores and manages callback
		getAxis().setCallback(checkAndGet(), Property.LINE_WIDTH, lineWidthCallback);
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