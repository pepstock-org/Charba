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
import org.pepstock.charba.client.callbacks.RadiusCallback;
import org.pepstock.charba.client.callbacks.ScriptableDoubleChecker;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyDoubleCallback;
import org.pepstock.charba.client.callbacks.ScriptableUtil;
import org.pepstock.charba.client.commons.CallbackPropertyHandler;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.utils.Utilities;

/**
 * This is the {@link AnnotationPlugin#ID} plugin annotation DEFAULTS options for annotation which are configured by a point and with a radius in a chart.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
abstract class AbstractCircleBasedAnnotation extends AbstractPointedAnnotation implements IsDefaultsAbstractCircleBasedAnnotation, HasRotation {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		RADIUS("radius");

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
	// callback proxy to invoke the radius function
	private final CallbackProxy<ProxyDoubleCallback> radiusCallbackProxy = JsHelper.get().newCallbackProxy();

	// callback instance to handle radius options
	private static final CallbackPropertyHandler<RadiusCallback<AnnotationContext>> RADIUS_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.RADIUS);

	// defaults options
	private final IsDefaultsAbstractCircleBasedAnnotation defaultValues;
	// rotation handler
	private final RotationHandler rotationHandler;

	/**
	 * Creates the object with the type of annotation to handle.
	 * 
	 * @param type annotation type
	 * @param id annotation id
	 * @param defaultValues default options instance
	 */
	AbstractCircleBasedAnnotation(AnnotationType type, AnnotationId id, IsDefaultsAnnotation defaultValues) {
		super(type, id, defaultValues);
		// checks if default are of the right class
		Checker.assertCheck(getDefaultsValues() instanceof IsDefaultsAbstractCircleBasedAnnotation, Utilities.applyTemplate(INVALID_DEFAULTS_VALUES_CLASS, type.value()));
		// casts and stores it
		this.defaultValues = (IsDefaultsAbstractCircleBasedAnnotation) getDefaultsValues();
		// creates rotation handler
		this.rotationHandler = new RotationHandler(this, this.defaultValues, getNativeObject());
		// sets callbacks proxies
		initCircleBasedCallbacks();
	}

	/**
	 * Creates the object wrapping an existing native object.
	 * 
	 * @param nativeObject native object to wrap
	 * @param defaultValues default options instance
	 */
	AbstractCircleBasedAnnotation(NativeObject nativeObject, IsDefaultsAnnotation defaultValues) {
		super(nativeObject, defaultValues);
		// checks if default are of the right class
		Checker.assertCheck(getDefaultsValues() instanceof IsDefaultsAbstractCircleBasedAnnotation, Utilities.applyTemplate(INVALID_DEFAULTS_VALUES_CLASS, getType()));
		// casts and stores it
		this.defaultValues = (IsDefaultsAbstractCircleBasedAnnotation) getDefaultsValues();
		// creates rotation handler
		this.rotationHandler = new RotationHandler(this, this.defaultValues, getNativeObject());
		// sets callbacks proxies
		initCircleBasedCallbacks();
	}

	/**
	 * Initializes the callbacks proxies for the options which can be scriptable.
	 */
	private void initCircleBasedCallbacks() {
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// sets function to proxy callback in order to invoke the java interface
		this.radiusCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValueAsNumber(new AnnotationContext(this, context), getRadiusCallback(), defaultValues.getRadius(), ScriptableDoubleChecker.POSITIVE_OR_ZERO).doubleValue());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.annotation.HasRotation#getRotationHandler()
	 */
	@Override
	public final RotationHandler getRotationHandler() {
		return rotationHandler;
	}

	/**
	 * Sets the radius of the annotation shape.<br>
	 * If set to 0, the annotation is not rendered.
	 * 
	 * @param radius array of the radius of the point shape.
	 */
	public final void setRadius(double radius) {
		// resets callback
		setRadius((RadiusCallback<AnnotationContext>) null);
		// stores value
		setValue(Property.RADIUS, Checker.positiveOrZero(radius));
	}

	/**
	 * Returns the radius of the annotation.
	 * 
	 * @return the radius of the annotation.
	 */
	@Override
	public final double getRadius() {
		return getValue(Property.RADIUS, defaultValues.getRadius());
	}

	// ---------------------
	// CALLBACKS
	// ---------------------

	/**
	 * Returns the callback called to set the radius.
	 * 
	 * @return the callback called to set the radius
	 */
	@Override
	public final RadiusCallback<AnnotationContext> getRadiusCallback() {
		return RADIUS_PROPERTY_HANDLER.getCallback(this, defaultValues.getRadiusCallback());
	}

	/**
	 * Sets the callback to set the radius.
	 * 
	 * @param radiusCallback to set the radius
	 */
	public final void setRadius(RadiusCallback<AnnotationContext> radiusCallback) {
		RADIUS_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, radiusCallback, radiusCallbackProxy.getProxy());
	}

	/**
	 * Sets the callback to set the radius.
	 * 
	 * @param radiusCallback to set the radius
	 */
	public final void setRadius(NativeCallback radiusCallback) {
		// resets callback
		setRadius((RadiusCallback<AnnotationContext>) null);
		// stores values
		setValue(Property.RADIUS, radiusCallback);
	}

}