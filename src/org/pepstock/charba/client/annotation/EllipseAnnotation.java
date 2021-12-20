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

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.callbacks.RotationCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyObjectCallback;
import org.pepstock.charba.client.commons.CallbackPropertyHandler;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.utils.Utilities;

/**
 * Implements a ELLIPSE annotation which draws a ellipse in the a chart.<br>
 * If one of the axes is not specified, the ellipse will take the entire chart dimension.<br>
 * The 4 coordinates, xMin, xMax, yMin, yMax are optional. If not specified, the ellipse is expanded out to the edges.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class EllipseAnnotation extends AbstractAnnotation implements IsDefaultsEllipseAnnotation, HasBackgroundColor {

	/**
	 * Default ellipse annotation border width, <b>{@value DEFAULT_BORDER_WIDTH}</b>.
	 */
	public static final int DEFAULT_BORDER_WIDTH = 1;

	/**
	 * Default ellipse annotation rotation, <b>{@value DEFAULT_ROTATION}</b>.
	 */
	public static final int DEFAULT_ROTATION = 0;

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the rotation function
	private final CallbackProxy<ProxyObjectCallback> rotationCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback instance to handle rotation options
	private static final CallbackPropertyHandler<RotationCallback<AnnotationContext>> ROTATION_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.ROTATION);

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

	// defaults options
	private final IsDefaultsEllipseAnnotation defaultValues;
	// background color handler
	private final BackgroundColorHandler backgroundColorHandler;

	/**
	 * Creates a ellipse annotation to be added to an {@link AnnotationOptions} instance.<br>
	 * The annotation id is calculated automatically.
	 * 
	 * @see AnnotationType#createId()
	 */
	public EllipseAnnotation() {
		this(AnnotationType.ELLIPSE.createId(), AnnotationType.ELLIPSE.getDefaultsValues());
	}

	/**
	 * Creates a ellipse annotation to be added to an {@link AnnotationOptions} instance, using the ID passed as argument.
	 * 
	 * @param id annotation id to apply to the object, as string
	 */
	public EllipseAnnotation(String id) {
		this(AnnotationId.create(id));
	}

	/**
	 * Creates a ellipse annotation to be added to an {@link AnnotationOptions} instance, using the ID passed as argument.
	 * 
	 * @param id annotation id to apply to the object
	 */
	public EllipseAnnotation(AnnotationId id) {
		this(id, AnnotationHelper.get().getDefaultsAnnotationOptionsByGlobal(AnnotationType.ELLIPSE, id));
	}

	/**
	 * Creates a ellipse annotation to be added to an {@link AnnotationOptions} instance, using the ID passed as argument.<br>
	 * The chart instance, passed as argument, must be the chart where the annotations will be applied and is used to get the whole default options in order to get the default for
	 * this object.
	 * 
	 * @param id annotation id to apply to the object, as string
	 * @param chart chart instance related to the plugin options
	 */
	public EllipseAnnotation(String id, IsChart chart) {
		this(AnnotationId.create(id), chart);
	}

	/**
	 * Creates a ellipse annotation to be added to an {@link AnnotationOptions} instance, using the ID passed as argument.<br>
	 * The chart instance, passed as argument, must be the chart where the annotations will be applied and is used to get the whole default options in order to get the default for
	 * this object.
	 * 
	 * @param id annotation id to apply to the object
	 * @param chart chart instance related to the plugin options
	 */
	public EllipseAnnotation(AnnotationId id, IsChart chart) {
		this(id, AnnotationHelper.get().getDefaultsAnnotationOptionsByChart(AnnotationType.ELLIPSE, id, chart));
	}

	/**
	 * Creates a ellipse annotation to be added to an {@link AnnotationOptions} instance, using the native object and defaults passed as argument.
	 * 
	 * @param id annotation id to apply to the object
	 * @param defaultValues default options instance
	 */
	private EllipseAnnotation(AnnotationId id, IsDefaultsAnnotation defaultValues) {
		super(AnnotationType.ELLIPSE, id, defaultValues);
		// checks if default are of the right class
		Checker.assertCheck(getDefaultsValues() instanceof IsDefaultsEllipseAnnotation, Utilities.applyTemplate(INVALID_DEFAULTS_VALUES_CLASS, AnnotationType.ELLIPSE.value()));
		// casts and stores it
		this.defaultValues = (IsDefaultsEllipseAnnotation) getDefaultsValues();
		// creates background color handler
		this.backgroundColorHandler = new BackgroundColorHandler(this, this.defaultValues, getNativeObject());
	}

	/**
	 * Creates the object wrapping an existing native object.
	 * 
	 * @param nativeObject native object to wrap
	 * @param defaultValues default options instance
	 */
	EllipseAnnotation(NativeObject nativeObject, IsDefaultsAnnotation defaultValues) {
		super(nativeObject, defaultValues);
		// checks if default are of the right class
		Checker.assertCheck(getDefaultsValues() instanceof IsDefaultsEllipseAnnotation, Utilities.applyTemplate(INVALID_DEFAULTS_VALUES_CLASS, AnnotationType.ELLIPSE.value()));
		// casts and stores it
		this.defaultValues = (IsDefaultsEllipseAnnotation) getDefaultsValues();
		// creates background color handler
		this.backgroundColorHandler = new BackgroundColorHandler(this, this.defaultValues, getNativeObject());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.annotation.HasBackgroundColor#getBackgroundColorHandler()
	 */
	@Override
	public BackgroundColorHandler getBackgroundColorHandler() {
		return backgroundColorHandler;
	}

	/**
	 * Sets the rotation of label in degrees.
	 * 
	 * @param rotation the rotation of label in degrees
	 */
	public void setRotation(double rotation) {
		// resets callback
		setRotation((RotationCallback<AnnotationContext>) null);
		// stores value
		setValue(Property.ROTATION, rotation);
	}

	/**
	 * Returns the rotation of label in degrees.
	 * 
	 * @return the rotation of label in degrees
	 */
	@Override
	public double getRotation() {
		return getValue(Property.ROTATION, defaultValues.getRotation());
	}

	// ---------------------
	// CALLBACKS
	// ---------------------

	/**
	 * Returns the callback called to set the rotation of label in degrees.
	 * 
	 * @return the callback called to set the rotation of label in degrees
	 */
	@Override
	public RotationCallback<AnnotationContext> getRotationCallback() {
		return ROTATION_PROPERTY_HANDLER.getCallback(this, defaultValues.getRotationCallback());
	}

	/**
	 * Sets the callback to set the rotation of label in degrees.
	 * 
	 * @param rotationCallback to set the rotation of label in degrees
	 */
	public void setRotation(RotationCallback<AnnotationContext> rotationCallback) {
		ROTATION_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, rotationCallback, rotationCallbackProxy.getProxy());
	}

	/**
	 * Sets the callback to set the rotation of label in degrees.
	 * 
	 * @param rotationCallback to set the rotation of label in degrees
	 */
	public void setRotation(NativeCallback rotationCallback) {
		// resets callback
		setRotation((RotationCallback<AnnotationContext>) null);
		// stores values
		setValueAndAddToParent(Property.ROTATION, rotationCallback);
	}

}
