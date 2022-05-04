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
package org.pepstock.charba.client.annotation;

import org.pepstock.charba.client.callbacks.ColorCallback;
import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyObjectCallback;
import org.pepstock.charba.client.callbacks.ScriptableUtil;
import org.pepstock.charba.client.colors.HtmlColor;
import org.pepstock.charba.client.commons.CallbackPropertyHandler;
import org.pepstock.charba.client.commons.CallbackProxy;
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
final class ExtendedShadowOptionsHandler extends PropertyHandler<IsDefaultsExtendedShadowOptionsHandler> {

	// default of background shadow color
	static final String DEFAULT_BACKGROUND_SHADOW_COLOR_AS_STRING = HtmlColor.TRANSPARENT.getHtmlColorName();

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		BACKGROUND_SHADOW_COLOR("backgroundShadowColor");

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
	// callback proxy to invoke the background shadow color function
	private final CallbackProxy<ProxyObjectCallback> backgroundShadowColorCallbackProxy = JsHelper.get().newCallbackProxy();

	// callback instance to handle background shadow color options
	private static final CallbackPropertyHandler<ColorCallback<AnnotationContext>> BACKGROUND_SHADOW_COLOR_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.BACKGROUND_SHADOW_COLOR);

	/**
	 * Creates a shadow options handler with the native object where shadow options properties must be managed and the default value to use when the property does not exist.
	 * 
	 * @param parent model which contains the shadow options handler.
	 * @param defaultValues default value of shadow options to use when the properties do not exist
	 * @param nativeObject native object where shadow options handler properties must be managed
	 */
	ExtendedShadowOptionsHandler(AbstractAnnotation parent, IsDefaultsExtendedShadowOptionsHandler defaultValues, NativeObject nativeObject) {
		super(parent, defaultValues, nativeObject);
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// sets function to proxy callback in order to invoke the java interface
		this.backgroundShadowColorCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValueAsColor(new AnnotationContext(parent, context), getBackgroundShadowColorCallback(), getDefaultValues().getBackgroundShadowColorAsString(), false));
	}

	/**
	 * Sets the color of the shadow of annotation.
	 * 
	 * @param backgroundShadowColor the shadow of the border of annotation
	 */
	void setBackgroundShadowColor(String backgroundShadowColor) {
		// resets callback
		setBackgroundShadowColor((ColorCallback<AnnotationContext>) null);
		// stores value
		setValueAndAddToParent(Property.BACKGROUND_SHADOW_COLOR, backgroundShadowColor);
	}

	/**
	 * Returns the color of the shadow of annotation.
	 * 
	 * @return the color of the shadow of annotation
	 */
	String getBackgroundShadowColorAsString() {
		return getValue(Property.BACKGROUND_SHADOW_COLOR, getDefaultValues().getBackgroundShadowColorAsString());
	}

	// ---------------------
	// CALLBACKS
	// ---------------------

	/**
	 * Returns the callback called to set the color of the shadow of annotation.
	 * 
	 * @return the callback called to set the color of the shadow of annotation
	 */
	ColorCallback<AnnotationContext> getBackgroundShadowColorCallback() {
		return BACKGROUND_SHADOW_COLOR_PROPERTY_HANDLER.getCallback(this, getDefaultValues().getBackgroundShadowColorCallback());
	}

	/**
	 * Sets the callback to set the color of the shadow of annotation.
	 * 
	 * @param borderShadowColorCallback to set the color of the shadow of annotation
	 */
	void setBackgroundShadowColor(ColorCallback<AnnotationContext> borderShadowColorCallback) {
		BACKGROUND_SHADOW_COLOR_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, borderShadowColorCallback, backgroundShadowColorCallbackProxy.getProxy());
	}

	/**
	 * Sets the callback to set the color of the shadow of annotation.
	 * 
	 * @param borderShadowColorCallback to set the color of the shadow of annotation
	 */
	void setBackgroundShadowColor(NativeCallback borderShadowColorCallback) {
		// resets callback
		setBackgroundShadowColor((ColorCallback<AnnotationContext>) null);
		// stores values
		setValueAndAddToParent(Property.BACKGROUND_SHADOW_COLOR, borderShadowColorCallback);
	}

}