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
package org.pepstock.charba.client.annotation;

import org.pepstock.charba.client.callbacks.ColorCallback;
import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyIntegerCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyObjectCallback;
import org.pepstock.charba.client.callbacks.ScriptableIntegerChecker;
import org.pepstock.charba.client.callbacks.ScriptableUtil;
import org.pepstock.charba.client.callbacks.WidthCallback;
import org.pepstock.charba.client.commons.CallbackPropertyHandler;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.PropertyHandler;

/**
 * Base object to map the text stroke options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class TextStrokeOptionsHandler extends PropertyHandler<IsDefaultsTextStrokeOptionsHandler> {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		TEXT_STROKE_COLOR("textStrokeColor"),
		TEXT_STROKE_WIDTH("textStrokeWidth");

		// name value of property
		private String value;

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
	// callback proxy to invoke the text stroke color function
	private final CallbackProxy<ProxyObjectCallback> textStrokeColorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the text stroke width function
	private final CallbackProxy<ProxyIntegerCallback> textStrokeWidthCallbackProxy = JsHelper.get().newCallbackProxy();

	// callback instance to handle text stroke color options
	private static final CallbackPropertyHandler<ColorCallback<AnnotationContext>> TEXT_STROKE_COLOR_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.TEXT_STROKE_COLOR);
	// callback instance to handle text stroke width options
	private static final CallbackPropertyHandler<WidthCallback<AnnotationContext>> TEXT_STROKE_WIDTH_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.TEXT_STROKE_WIDTH);

	/**
	 * Creates a text stroke options handler with the native object where text stroke options properties must be managed and the default value to use when the property does not
	 * exist.
	 * 
	 * @param parent model which contains the text stroke options handler.
	 * @param defaultValues default value of text stroke options to use when the properties do not exist
	 * @param nativeObject native object where text stroke options handler properties must be managed
	 */
	TextStrokeOptionsHandler(AbstractAnnotation parent, IsDefaultsTextStrokeOptionsHandler defaultValues, NativeObject nativeObject) {
		super(parent, defaultValues, nativeObject);
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// sets function to proxy callback in order to invoke the java interface
		this.textStrokeColorCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValueAsColor(new AnnotationContext(parent, context), getTextStrokeColorCallback(), getDefaultValues().getTextStrokeColorAsString(), false));
		// sets function to proxy callback in order to invoke the java interface
		this.textStrokeWidthCallbackProxy
				.setCallback(context -> ScriptableUtil.getOptionValueAsNumber(new AnnotationContext(parent, context), getTextStrokeWidthCallback(), getDefaultValues().getTextStrokeWidth(), ScriptableIntegerChecker.POSITIVE_OR_DEFAULT).intValue());
	}

	/**
	 * Sets the color of the text stroke.
	 * 
	 * @param textStrokeColor the color of the text stroke
	 */
	void setTextStrokeColor(String textStrokeColor) {
		// resets callback
		setTextStrokeColor((ColorCallback<AnnotationContext>) null);
		// stores value
		setValueAndAddToParent(Property.TEXT_STROKE_COLOR, textStrokeColor);
	}

	/**
	 * Returns the color of the text stroke.
	 * 
	 * @return the color of the text stroke
	 */
	String getTextStrokeColorAsString() {
		return getValue(Property.TEXT_STROKE_COLOR, getDefaultValues().getTextStrokeColorAsString());
	}

	/**
	 * Sets the width of the text stroke in pixels.
	 * 
	 * @param textStrokeWidth the width of the text stroke in pixels.
	 */
	void setTextStrokeWidth(int textStrokeWidth) {
		// resets callback
		setTextStrokeWidth((WidthCallback<AnnotationContext>) null);
		// stores value
		setValueAndAddToParent(Property.TEXT_STROKE_WIDTH, Checker.positiveOrZero(textStrokeWidth));
	}

	/**
	 * Returns the width of the text stroke in pixels.
	 * 
	 * @return the width of the text stroke in pixels.
	 */
	int getTextStrokeWidth() {
		return getValue(Property.TEXT_STROKE_WIDTH, getDefaultValues().getTextStrokeWidth());
	}

	// ---------------------
	// CALLBACKS
	// ---------------------

	/**
	 * Returns the callback called to set the color of the text stroke.
	 * 
	 * @return the callback called to set the color of the text stroke
	 */
	ColorCallback<AnnotationContext> getTextStrokeColorCallback() {
		return TEXT_STROKE_COLOR_PROPERTY_HANDLER.getCallback(this, getDefaultValues().getTextStrokeColorCallback());
	}

	/**
	 * Sets the callback to set the color of the text stroke.
	 * 
	 * @param textStrokeColorCallback to set the color of the text stroke
	 */
	void setTextStrokeColor(ColorCallback<AnnotationContext> textStrokeColorCallback) {
		TEXT_STROKE_COLOR_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, textStrokeColorCallback, textStrokeColorCallbackProxy.getProxy());
	}

	/**
	 * Sets the callback to set the color of the text stroke.
	 * 
	 * @param textStrokeColorCallback to set the color of the text stroke
	 */
	void setTextStrokeColor(NativeCallback textStrokeColorCallback) {
		// resets callback
		setTextStrokeColor((ColorCallback<AnnotationContext>) null);
		// stores values
		setValueAndAddToParent(Property.TEXT_STROKE_COLOR, textStrokeColorCallback);
	}

	/**
	 * Returns the callback called to set the width of the text stroke in pixels.
	 * 
	 * @return the callback called to set the width of the text stroke in pixels
	 */
	WidthCallback<AnnotationContext> getTextStrokeWidthCallback() {
		return TEXT_STROKE_WIDTH_PROPERTY_HANDLER.getCallback(this, getDefaultValues().getTextStrokeWidthCallback());
	}

	/**
	 * Sets the callback to set the color of the width of the text stroke in pixels.
	 * 
	 * @param textStrokeWidthCallback to set the width of the text stroke in pixels
	 */
	void setTextStrokeWidth(WidthCallback<AnnotationContext> textStrokeWidthCallback) {
		TEXT_STROKE_WIDTH_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, textStrokeWidthCallback, textStrokeWidthCallbackProxy.getProxy());
	}

	/**
	 * Sets the callback to set the color of the width of the text stroke in pixels.
	 * 
	 * @param textStrokeWidthCallback to set the width of the text stroke in pixels
	 */
	void setTextStrokeWidth(NativeCallback textStrokeWidthCallback) {
		// resets callback
		setTextStrokeWidth((WidthCallback<AnnotationContext>) null);
		// stores values
		setValueAndAddToParent(Property.TEXT_STROKE_WIDTH, textStrokeWidthCallback);
	}

}