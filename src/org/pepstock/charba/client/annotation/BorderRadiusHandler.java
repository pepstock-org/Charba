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

import org.pepstock.charba.client.callbacks.BorderRadiusCallback;
import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyNativeObjectCallback;
import org.pepstock.charba.client.callbacks.ScriptableUtils;
import org.pepstock.charba.client.commons.CallbackPropertyHandler;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.data.BarBorderRadius;

/**
 * Base object to map the border radius options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class BorderRadiusHandler extends AbstractAnnotationPropertyHandler<IsDefaultsBorderRadiusHandler> {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		BORDER_RADIUS("borderRadius");

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
	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the border radius function
	private final CallbackProxy<ProxyNativeObjectCallback> borderRadiusCallbackProxy = JsHelper.get().newCallbackProxy();

	// callback instance to handle border radius options
	private static final CallbackPropertyHandler<BorderRadiusCallback<AnnotationContext>> BORDER_RADIUS_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.BORDER_RADIUS);

	/**
	 * Creates a border radius handler with the native object where border radius properties must be managed and the default value to use when the property does not exist.
	 * 
	 * @param parent model which contains the border radius handler.
	 * @param defaultValues default value of border radius to use when the properties do not exist
	 * @param nativeObject native object where border radius handler properties must be managed
	 */
	BorderRadiusHandler(AbstractAnnotation parent, IsDefaultsBorderRadiusHandler defaultValues, NativeObject nativeObject) {
		super(parent, defaultValues, nativeObject);
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// sets function to proxy callback in order to invoke the java interface
		this.borderRadiusCallbackProxy.setCallback(context -> onBorderRadius(new AnnotationContext(parent, context), getBorderRadiusCallback(), getDefaultValues().getBorderRadius()));
	}

	/**
	 * Sets the border radius.
	 * 
	 * @param radius the border radius.
	 */
	void setBorderRadius(int radius) {
		// resets callback
		setBorderRadius((BorderRadiusCallback<AnnotationContext>) null);
		// stores value
		setValue(Property.BORDER_RADIUS, Checker.positiveOrZero(radius));
	}

	/**
	 * Sets the border radius (in pixels).
	 * 
	 * @param borderRadius the border radius (in pixels).
	 */
	void setBorderRadius(BarBorderRadius borderRadius) {
		// resets callback
		setBorderRadius((BorderRadiusCallback<AnnotationContext>) null);
		// stores the value
		setValue(Property.BORDER_RADIUS, borderRadius);
	}

	/**
	 * Returns the border radius (in pixels).
	 * 
	 * @return the border radius (in pixels).
	 */
	int getBorderRadius() {
		// checks if was stored as number
		if (isType(Property.BORDER_RADIUS, ObjectType.NUMBER)) {
			return getValue(Property.BORDER_RADIUS, getDefaultValues().getBorderRadius());
		} else if (isType(Property.BORDER_RADIUS, ObjectType.OBJECT)) {
			// if here, the property is a object
			BarBorderRadius object = getBorderRadiusAsObject();
			// checks if there is the same value
			if (object != null && object.areValuesEquals()) {
				// the returns the same value
				// in whatever property
				return object.getTopLeft();
			}
		}
		// if here, the property is missing
		// then returns default
		return getDefaultValues().getBorderRadius();
	}

	/**
	 * Returns the border radius (in pixels).
	 * 
	 * @return the border radius (in pixels).
	 */
	BarBorderRadius getBorderRadiusAsObject() {
		// checks if was stored as object
		if (isType(Property.BORDER_RADIUS, ObjectType.OBJECT)) {
			return BarBorderRadius.FACTORY.create(getValue(Property.BORDER_RADIUS));
		} else if (isType(Property.BORDER_RADIUS, ObjectType.NUMBER)) {
			// if here, the property is a number
			// then returns new border radius object
			return new BarBorderRadius(getBorderRadius());
		}
		// if here, the property is missing
		// then returns null
		return null;
	}

	// ---------------------
	// CALLBACKS
	// ---------------------

	/**
	 * Returns the callback called to set the border radius.
	 * 
	 * @return the callback called to set the border radius
	 */
	BorderRadiusCallback<AnnotationContext> getBorderRadiusCallback() {
		return BORDER_RADIUS_PROPERTY_HANDLER.getCallback(this, getDefaultValues().getBorderRadiusCallback());
	}

	/**
	 * Sets the callback to set the border radius.
	 * 
	 * @param borderRadiusCallback to set the border radius
	 */
	void setBorderRadius(BorderRadiusCallback<AnnotationContext> borderRadiusCallback) {
		BORDER_RADIUS_PROPERTY_HANDLER.setCallback(getNodeWrapper(), AnnotationPlugin.ID, borderRadiusCallback, borderRadiusCallbackProxy.getProxy());
	}

	/**
	 * Sets the callback to set the border radius.
	 * 
	 * @param borderRadiusCallback to set the border radius
	 */
	void setBorderRadius(NativeCallback borderRadiusCallback) {
		// resets callback
		setBorderRadius((BorderRadiusCallback<AnnotationContext>) null);
		// stores values
		setValue(Property.BORDER_RADIUS, borderRadiusCallback);
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
	NativeObject onBorderRadius(AnnotationContext context, BorderRadiusCallback<AnnotationContext> callback, int defaultValue) {
		int valueToReturn = defaultValue;
		// gets value
		Object value = ScriptableUtils.getOptionValue(context, callback);
		// checks if is an object
		if (value instanceof BarBorderRadius) {
			// casts to border radius object
			BarBorderRadius object = (BarBorderRadius) value;
			// returns the native object
			return object.nativeObject();
		} else if (value instanceof Number) {
			// checks if is an number
			// casts to number
			Number number = (Number) value;
			// stores to result
			valueToReturn = Checker.positiveOrZero(number.intValue());
		}
		// cats to a object
		BarBorderRadius object = new BarBorderRadius(valueToReturn);
		// returns the native object
		return object.nativeObject();
	}

}