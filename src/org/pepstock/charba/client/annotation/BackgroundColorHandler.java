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

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.callbacks.ColorCallback;
import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyObjectCallback;
import org.pepstock.charba.client.callbacks.ScriptableUtils;
import org.pepstock.charba.client.commons.CallbackPropertyHandler;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.PropertyHandler;

/**
 * Base object to map the background color options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class BackgroundColorHandler extends PropertyHandler<IsDefaultsBackgroundColorHandler> {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		BACKGROUND_COLOR("backgroundColor");

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
	// callback proxy to invoke the background color function
	private final CallbackProxy<ProxyObjectCallback> backgroundColorCallbackProxy = JsHelper.get().newCallbackProxy();

	// callback instance to handle background color options
	private static final CallbackPropertyHandler<ColorCallback<AnnotationContext>> BACKGROUND_COLOR_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.BACKGROUND_COLOR);

	private final String defaultBackgroundColor;

	/**
	 * Creates a background color handler with the native object where background color properties must be managed and the default value to use when the property does not exist.
	 * 
	 * @param parent model which contains the background color handler.
	 * @param defaultValues default value of background color to use when the properties do not exist
	 * @param nativeObject native object where background color handler properties must be managed
	 */
	BackgroundColorHandler(AbstractAnnotation parent, IsDefaultsBackgroundColorHandler defaultValues, NativeObject nativeObject) {
		this(parent, defaultValues, nativeObject, Defaults.get().getGlobal().getColorAsString());
	}

	/**
	 * Creates a background color handler with the native object where background color properties must be managed and the default value to use when the property does not exist.
	 * 
	 * @param parent model which contains the background color handler.
	 * @param defaultValues default value of background color to use when the properties do not exist
	 * @param nativeObject native object where background color handler properties must be managed
	 * @param defaultBackgroundColor default background color because not possible to use the default of interface
	 */
	BackgroundColorHandler(AbstractAnnotation parent, IsDefaultsBackgroundColorHandler defaultValues, NativeObject nativeObject, String defaultBackgroundColor) {
		super(parent, defaultValues, nativeObject);
		// stores background color
		this.defaultBackgroundColor = defaultBackgroundColor;
		// sets function to proxy callback in order to invoke the java interface
		this.backgroundColorCallbackProxy.setCallback(context -> ScriptableUtils.getOptionValueAsColor(new AnnotationContext(parent, context), getBackgroundColorCallback(), defaultBackgroundColor));
	}

	/**
	 * Sets the color of the background of annotation.
	 * 
	 * @param backgroundColor the color of the background of annotation
	 */
	void setBackgroundColor(String backgroundColor) {
		// resets callback
		setBackgroundColor((ColorCallback<AnnotationContext>) null);
		// stores value
		setValue(Property.BACKGROUND_COLOR, backgroundColor);
	}

	/**
	 * Returns the color of the background of annotation.
	 * 
	 * @return the color of the background of annotation
	 */
	String getBackgroundColorAsString() {
		return getValue(Property.BACKGROUND_COLOR, defaultBackgroundColor);
	}

	/**
	 * Returns the callback called to set the color of the background of annotation.
	 * 
	 * @return the callback called to set the color of the background of annotation
	 */
	ColorCallback<AnnotationContext> getBackgroundColorCallback() {
		return BACKGROUND_COLOR_PROPERTY_HANDLER.getCallback(this, getDefaultValues().getBackgroundColorCallback());
	}

	/**
	 * Sets the callback to set the color of the background of annotation.
	 * 
	 * @param backgroundColorCallback to set the color of the background of annotation
	 */
	void setBackgroundColor(ColorCallback<AnnotationContext> backgroundColorCallback) {
		BACKGROUND_COLOR_PROPERTY_HANDLER.setCallback(getParent(), AnnotationPlugin.ID, backgroundColorCallback, backgroundColorCallbackProxy.getProxy());
	}
	
	/**
	 * Sets the callback to set the color of the background of annotation.
	 * 
	 * @param backgroundColorCallback to set the color of the background of annotation
	 */
	void setBackgroundColor(NativeCallback backgroundColorCallback) {
		// resets callback
		setBackgroundColor((ColorCallback<AnnotationContext>)null);
		// stores values
		setValueAndAddToParent(Property.BACKGROUND_COLOR, backgroundColorCallback);
	}

}