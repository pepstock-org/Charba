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

import org.pepstock.charba.client.annotation.callbacks.ShadowBlurCallback;
import org.pepstock.charba.client.annotation.callbacks.ShadowOffsetCallback;
import org.pepstock.charba.client.callbacks.ColorCallback;
import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.callbacks.ScriptableDoubleChecker;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyDoubleCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyIntegerCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyObjectCallback;
import org.pepstock.charba.client.callbacks.ScriptableIntegerChecker;
import org.pepstock.charba.client.callbacks.ScriptableUtil;
import org.pepstock.charba.client.colors.HtmlColor;
import org.pepstock.charba.client.commons.CallbackPropertyHandler;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.PropertyHandler;

/**
 * Base object to map the shadow options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class ShadowOptionsHandler extends PropertyHandler<IsDefaultsShadowOptionsHandler> {

	// default of border shadow color
	static final String DEFAULT_BORDER_SHADOW_COLOR_AS_STRING = HtmlColor.TRANSPARENT.getHtmlColorName();
	// default of shadow blur
	static final double DEFAULT_SHADOW_BLUR = 0D;
	// default of shadow offset X
	static final int DEFAULT_SHADOW_OFFSET_X = 0;
	// default of shadow offset Y
	static final int DEFAULT_SHADOW_OFFSET_Y = 0;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		BORDER_SHADOW_COLOR("borderShadowColor"),
		SHADOW_BLUR("shadowBlur"),
		SHADOW_OFFSET_X("shadowOffsetX"),
		SHADOW_OFFSET_Y("shadowOffsetY");

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
	// callback proxy to invoke the border shadow color function
	private final CallbackProxy<ProxyObjectCallback> borderShadowColorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the shadow blur function
	private final CallbackProxy<ProxyDoubleCallback> shadowBlurCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the shadow offset X function
	private final CallbackProxy<ProxyIntegerCallback> shadowOffsetXCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the shadow offset Y function
	private final CallbackProxy<ProxyIntegerCallback> shadowOffsetYCallbackProxy = JsHelper.get().newCallbackProxy();

	// callback instance to handle border shadow color options
	private static final CallbackPropertyHandler<ColorCallback<AnnotationContext>> BORDER_SHADOW_COLOR_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.BORDER_SHADOW_COLOR);
	// callback instance to handle shadow blur options
	private static final CallbackPropertyHandler<ShadowBlurCallback> SHADOW_BLUR_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.SHADOW_BLUR);
	// callback instance to handle shadow offset x options
	private static final CallbackPropertyHandler<ShadowOffsetCallback> SHADOW_OFFSET_X_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.SHADOW_OFFSET_X);
	// callback instance to handle shadow offset y options
	private static final CallbackPropertyHandler<ShadowOffsetCallback> SHADOW_OFFSET_Y_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.SHADOW_OFFSET_Y);

	/**
	 * Creates a shadow options handler with the native object where shadow options properties must be managed and the default value to use when the property does not exist.
	 * 
	 * @param parent model which contains the shadow options handler.
	 * @param defaultValues default value of shadow options to use when the properties do not exist
	 * @param nativeObject native object where shadow options handler properties must be managed
	 */
	ShadowOptionsHandler(AbstractAnnotation parent, IsDefaultsShadowOptionsHandler defaultValues, NativeObject nativeObject) {
		super(parent, defaultValues, nativeObject);
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// sets function to proxy callback in order to invoke the java interface
		this.borderShadowColorCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValueAsColor(new AnnotationContext(parent, context), getBorderShadowColorCallback(), getDefaultValues().getBorderShadowColorAsString(), false));
		// sets function to proxy callback in order to invoke the java interface
		this.shadowBlurCallbackProxy
				.setCallback(context -> ScriptableUtil.getOptionValueAsNumber(new AnnotationContext(parent, context), getShadowBlurCallback(), getDefaultValues().getShadowBlur(), ScriptableDoubleChecker.POSITIVE_OR_DEFAULT).doubleValue());
		// sets function to proxy callback in order to invoke the java interface
		this.shadowOffsetXCallbackProxy
				.setCallback(context -> ScriptableUtil.getOptionValueAsNumber(new AnnotationContext(parent, context), getShadowOffsetXCallback(), getDefaultValues().getShadowOffsetX(), ScriptableIntegerChecker.POSITIVE_OR_DEFAULT).intValue());
		// sets function to proxy callback in order to invoke the java interface
		this.shadowOffsetYCallbackProxy
				.setCallback(context -> ScriptableUtil.getOptionValueAsNumber(new AnnotationContext(parent, context), getShadowOffsetYCallback(), getDefaultValues().getShadowOffsetY(), ScriptableIntegerChecker.POSITIVE_OR_DEFAULT).intValue());
	}

	/**
	 * Sets the color of the border shadow of annotation.
	 * 
	 * @param borderShadowColor the border shadow of the border of annotation
	 */
	void setBorderShadowColor(String borderShadowColor) {
		// resets callback
		setBorderShadowColor((ColorCallback<AnnotationContext>) null);
		// stores value
		setValueAndAddToParent(Property.BORDER_SHADOW_COLOR, borderShadowColor);
	}

	/**
	 * Returns the color of the border shadow of annotation.
	 * 
	 * @return the color of the border shadow of annotation
	 */
	String getBorderShadowColorAsString() {
		return getValue(Property.BORDER_SHADOW_COLOR, getDefaultValues().getBorderShadowColorAsString());
	}

	/**
	 * Sets the amount of blur applied to shadows.
	 * 
	 * @param shadowBlur the amount of blur applied to shadows
	 */
	void setShadowBlur(double shadowBlur) {
		// resets callback
		setShadowBlur((ShadowBlurCallback) null);
		// stores value
		setValueAndAddToParent(Property.SHADOW_BLUR, Checker.positiveOrZero(shadowBlur));
	}

	/**
	 * Returns the amount of blur applied to shadows.
	 * 
	 * @return the amount of blur applied to shadows
	 */
	double getShadowBlur() {
		return getValue(Property.SHADOW_BLUR, getDefaultValues().getShadowBlur());
	}

	/**
	 * Sets the distance that shadows will be offset horizontally.
	 * 
	 * @param shadowOffset the distance that shadows will be offset horizontally.
	 */
	void setShadowOffsetX(int shadowOffset) {
		// resets callback
		setShadowOffsetX((ShadowOffsetCallback) null);
		// stores value
		setValueAndAddToParent(Property.SHADOW_OFFSET_X, shadowOffset);
	}

	/**
	 * Returns the distance that shadows will be offset horizontally.
	 * 
	 * @return the distance that shadows will be offset horizontally.
	 */
	int getShadowOffsetX() {
		return getValue(Property.SHADOW_OFFSET_X, getDefaultValues().getShadowOffsetX());
	}

	/**
	 * Sets the distance that shadows will be offset vertically.
	 * 
	 * @param shadowOffset the distance that shadows will be offset vertically.
	 */
	void setShadowOffsetY(int shadowOffset) {
		// resets callback
		setShadowOffsetY((ShadowOffsetCallback) null);
		// stores value
		setValueAndAddToParent(Property.SHADOW_OFFSET_Y, shadowOffset);
	}

	/**
	 * Returns the distance that shadows will be offset vertically.
	 * 
	 * @return the distance that shadows will be offset vertically.
	 */
	int getShadowOffsetY() {
		return getValue(Property.SHADOW_OFFSET_Y, getDefaultValues().getShadowOffsetY());
	}

	// ---------------------
	// CALLBACKS
	// ---------------------

	/**
	 * Returns the callback called to set the color of the border shadow of annotation.
	 * 
	 * @return the callback called to set the color of the border shadow of annotation
	 */
	ColorCallback<AnnotationContext> getBorderShadowColorCallback() {
		return BORDER_SHADOW_COLOR_PROPERTY_HANDLER.getCallback(this, getDefaultValues().getBorderShadowColorCallback());
	}

	/**
	 * Sets the callback to set the color of the border shadow of annotation.
	 * 
	 * @param borderShadowColorCallback to set the color of the border shadow of annotation
	 */
	void setBorderShadowColor(ColorCallback<AnnotationContext> borderShadowColorCallback) {
		BORDER_SHADOW_COLOR_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, borderShadowColorCallback, borderShadowColorCallbackProxy.getProxy());
	}

	/**
	 * Sets the callback to set the color of the border shadow of annotation.
	 * 
	 * @param borderShadowColorCallback to set the color of the border shadow of annotation
	 */
	void setBorderShadowColor(NativeCallback borderShadowColorCallback) {
		// resets callback
		setBorderShadowColor((ColorCallback<AnnotationContext>) null);
		// stores values
		setValueAndAddToParent(Property.BORDER_SHADOW_COLOR, borderShadowColorCallback);
	}

	/**
	 * Returns the callback called to set the amount of blur applied to shadows.
	 * 
	 * @return the callback called to set the amount of blur applied to shadows.
	 */
	ShadowBlurCallback getShadowBlurCallback() {
		return SHADOW_BLUR_PROPERTY_HANDLER.getCallback(this, getDefaultValues().getShadowBlurCallback());
	}

	/**
	 * Sets the callback to set the amount of blur applied to shadows.
	 * 
	 * @param shadowBlurCallback to set the amount of blur applied to shadows.
	 */
	void setShadowBlur(ShadowBlurCallback shadowBlurCallback) {
		SHADOW_BLUR_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, shadowBlurCallback, shadowBlurCallbackProxy.getProxy());
	}

	/**
	 * Sets the callback to set the amount of blur applied to shadows.
	 * 
	 * @param shadowBlurCallback to set the amount of blur applied to shadows.
	 */
	void setShadowBlur(NativeCallback shadowBlurCallback) {
		// resets callback
		setShadowBlur((ShadowBlurCallback) null);
		// stores values
		setValueAndAddToParent(Property.SHADOW_BLUR, shadowBlurCallback);
	}

	/**
	 * Returns the callback called to set the distance that shadows will be offset horizontally.
	 * 
	 * @return the callback called to set the distance that shadows will be offset horizontally.
	 */
	ShadowOffsetCallback getShadowOffsetXCallback() {
		return SHADOW_OFFSET_X_PROPERTY_HANDLER.getCallback(this, getDefaultValues().getShadowOffsetXCallback());
	}

	/**
	 * Sets the callback to set the distance that shadows will be offset horizontally.
	 * 
	 * @param shadowOffsetCallback to set the distance that shadows will be offset horizontally.
	 */
	void setShadowOffsetX(ShadowOffsetCallback shadowOffsetCallback) {
		SHADOW_OFFSET_X_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, shadowOffsetCallback, shadowOffsetXCallbackProxy.getProxy());
	}

	/**
	 * Sets the callback to set the distance that shadows will be offset horizontally.
	 * 
	 * @param shadowOffsetCallback to set the distance that shadows will be offset horizontally.
	 */
	void setShadowOffsetX(NativeCallback shadowOffsetCallback) {
		// resets callback
		setShadowOffsetX((ShadowOffsetCallback) null);
		// stores values
		setValueAndAddToParent(Property.SHADOW_OFFSET_X, shadowOffsetCallback);
	}

	/**
	 * Returns the callback called to set the distance that shadows will be offset vertically.
	 * 
	 * @return the callback called to set the distance that shadows will be offset vertically.
	 */
	ShadowOffsetCallback getShadowOffsetYCallback() {
		return SHADOW_OFFSET_Y_PROPERTY_HANDLER.getCallback(this, getDefaultValues().getShadowOffsetYCallback());
	}

	/**
	 * Sets the callback to set the distance that shadows will be offset vertically.
	 * 
	 * @param shadowOffsetCallback to set the distance that shadows will be offset vertically.
	 */
	void setShadowOffsetY(ShadowOffsetCallback shadowOffsetCallback) {
		SHADOW_OFFSET_Y_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, shadowOffsetCallback, shadowOffsetYCallbackProxy.getProxy());
	}

	/**
	 * Sets the callback to set the distance that shadows will be offset vertically.
	 * 
	 * @param shadowOffsetCallback to set the distance that shadows will be offset vertically.
	 */
	void setShadowOffsetY(NativeCallback shadowOffsetCallback) {
		// resets callback
		setShadowOffsetY((ShadowOffsetCallback) null);
		// stores values
		setValueAndAddToParent(Property.SHADOW_OFFSET_Y, shadowOffsetCallback);
	}

}