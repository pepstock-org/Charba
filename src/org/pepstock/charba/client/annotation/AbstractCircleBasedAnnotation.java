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

import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.callbacks.RadiusCallback;
import org.pepstock.charba.client.callbacks.RotationCallback;
import org.pepstock.charba.client.callbacks.ScriptableDoubleChecker;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyDoubleCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyObjectCallback;
import org.pepstock.charba.client.callbacks.ScriptableUtils;
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
abstract class AbstractCircleBasedAnnotation extends AbstractPointedAnnotation implements IsDefaultsAbstractCircleBasedAnnotation {

	/**
	 * Default annotation rotation, <b>{@value DEFAULT_ROTATION}</b>.
	 */
	public static final double DEFAULT_ROTATION = 0D;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		RADIUS("radius"),
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
	// callback proxy to invoke the radius function
	private final CallbackProxy<ProxyDoubleCallback> radiusCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the rotation function
	private final CallbackProxy<ProxyObjectCallback> rotationCallbackProxy = JsHelper.get().newCallbackProxy();

	// callback instance to handle radius options
	private static final CallbackPropertyHandler<RadiusCallback<AnnotationContext>> RADIUS_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.RADIUS);
	// callback instance to handle rotation options
	private static final CallbackPropertyHandler<RotationCallback<AnnotationContext>> ROTATION_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.ROTATION);

	// defaults options
	private final IsDefaultsAbstractCircleBasedAnnotation defaultValues;

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
		this.radiusCallbackProxy.setCallback(context -> ScriptableUtils.getOptionValueAsNumber(new AnnotationContext(this, context), getRadiusCallback(), defaultValues.getRadius(), ScriptableDoubleChecker.POSITIVE_OR_ZERO).doubleValue());
		// sets function to proxy callback in order to invoke the java interface
		this.rotationCallbackProxy.setCallback(context -> ScriptableUtils.getOptionValueAsNumber(new AnnotationContext(this, context), getRotationCallback(), defaultValues.getRotation()).doubleValue());
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

	/**
	 * Sets the rotation of annotation in degrees.
	 * 
	 * @param rotation the rotation of annotation in degrees
	 */
	public final void setRotation(double rotation) {
		// resets callback
		setRotation((RotationCallback<AnnotationContext>) null);
		// stores value
		setValue(Property.ROTATION, rotation);
	}

	/**
	 * Returns the rotation of annotation in degrees.
	 * 
	 * @return the rotation of annotation in degrees
	 */
	@Override
	public final double getRotation() {
		return getValue(Property.ROTATION, defaultValues.getRotation());
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

	/**
	 * Returns the callback called to set the rotation of label in degrees.
	 * 
	 * @return the callback called to set the rotation of label in degrees
	 */
	@Override
	public final RotationCallback<AnnotationContext> getRotationCallback() {
		return ROTATION_PROPERTY_HANDLER.getCallback(this, defaultValues.getRotationCallback());
	}

	/**
	 * Sets the callback to set the rotation of label in degrees.
	 * 
	 * @param rotationCallback to set the rotation of label in degrees
	 */
	public final void setRotation(RotationCallback<AnnotationContext> rotationCallback) {
		ROTATION_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, rotationCallback, rotationCallbackProxy.getProxy());
	}

	/**
	 * Sets the callback to set the rotation of label in degrees.
	 * 
	 * @param rotationCallback to set the rotation of label in degrees
	 */
	public final void setRotation(NativeCallback rotationCallback) {
		// resets callback
		setRotation((RotationCallback<AnnotationContext>) null);
		// stores values
		setValue(Property.ROTATION, rotationCallback);
	}

}
