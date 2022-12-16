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

import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.callbacks.RotationCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyObjectCallback;
import org.pepstock.charba.client.callbacks.ScriptableUtil;
import org.pepstock.charba.client.commons.CallbackPropertyHandler;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.PropertyHandler;

/**
 * Base object to map the rotation options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class RotationHandler extends PropertyHandler<IsDefaultsRotationHandler> {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		ROTATION("rotation");

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
	// callback proxy to invoke the rotation function
	private final CallbackProxy<ProxyObjectCallback> rotationCallbackProxy = JsHelper.get().newCallbackProxy();

	// callback instance to handle rotation options
	private static final CallbackPropertyHandler<RotationCallback<AnnotationContext>> ROTATION_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.ROTATION);

	/**
	 * Creates a rotation handler with the native object where rotation properties must be managed and the default value to use when the property does not exist.
	 * 
	 * @param parent model which contains the rotation handler.
	 * @param defaultValues default value of rotation to use when the properties do not exist
	 * @param nativeObject native object where rotation handler properties must be managed
	 */
	RotationHandler(AbstractAnnotation parent, IsDefaultsRotationHandler defaultValues, NativeObject nativeObject) {
		super(parent, defaultValues, nativeObject);
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// sets function to proxy callback in order to invoke the java interface
		this.rotationCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValueAsNumber(new AnnotationContext(parent, context), getRotationCallback(), getDefaultValues().getRotation()).doubleValue());
	}

	/**
	 * Sets the rotation in degrees.
	 * 
	 * @param rotation the rotation in degrees
	 */
	void setRotation(double rotation) {
		// resets callback
		setRotation((RotationCallback<AnnotationContext>) null);
		// stores value
		setValueAndAddToParent(Property.ROTATION, rotation);
	}

	/**
	 * Returns the rotation in degrees.
	 * 
	 * @return the rotation in degrees
	 */
	double getRotation() {
		return getValue(Property.ROTATION, getDefaultValues().getRotation());
	}

	// ---------------------
	// CALLBACKS
	// ---------------------

	/**
	 * Returns the callback called to set the rotation in degrees.
	 * 
	 * @return the callback called to set the rotation in degrees
	 */
	RotationCallback<AnnotationContext> getRotationCallback() {
		return ROTATION_PROPERTY_HANDLER.getCallback(this, getDefaultValues().getRotationCallback());
	}

	/**
	 * Sets the callback to set the rotation in degrees.
	 * 
	 * @param rotationCallback to set the rotation in degrees
	 */
	void setRotation(RotationCallback<AnnotationContext> rotationCallback) {
		ROTATION_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, rotationCallback, rotationCallbackProxy.getProxy());
	}

	/**
	 * Sets the callback to set the rotation in degrees.
	 * 
	 * @param rotationCallback to set the rotation in degrees
	 */
	void setRotation(NativeCallback rotationCallback) {
		// resets callback
		setRotation((RotationCallback<AnnotationContext>) null);
		// stores values
		setValueAndAddToParent(Property.ROTATION, rotationCallback);
	}

}