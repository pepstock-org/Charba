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

import java.util.Date;

import org.pepstock.charba.client.annotation.callbacks.AdjustSizeCallback;
import org.pepstock.charba.client.annotation.callbacks.ValueCallback;
import org.pepstock.charba.client.callbacks.NativeCallback;
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
 * This is the {@link AnnotationPlugin#ID} plugin annotation DEFAULTS options for annotation which are configured by a point in a chart.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
abstract class AbstractPointedAnnotation extends AbstractAnnotation implements IsDefaultsAbstractPointedAnnotation, HasBackgroundColor, HasExtendedShadowOptions {

	/**
	 * Default annotation X adjust, <b>{@value DEFAULT_X_ADJUST}</b>.
	 */
	public static final double DEFAULT_X_ADJUST = 0D;

	/**
	 * Default annotation Y adjust, <b>{@value DEFAULT_Y_ADJUST}</b>.
	 */
	public static final double DEFAULT_Y_ADJUST = 0D;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		X_ADJUST("xAdjust"),
		X_VALUE("xValue"),
		Y_ADJUST("yAdjust"),
		Y_VALUE("yValue");

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
	// callback proxy to invoke the xValue function
	private final CallbackProxy<ProxyObjectCallback> xValueCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the yValue function
	private final CallbackProxy<ProxyObjectCallback> yValueCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the xAdjust function
	private final CallbackProxy<ProxyDoubleCallback> xAdjustCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the yAdjust function
	private final CallbackProxy<ProxyDoubleCallback> yAdjustCallbackProxy = JsHelper.get().newCallbackProxy();

	// callback instance to handle xValue options
	private static final CallbackPropertyHandler<ValueCallback> X_VALUE_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.X_VALUE);
	// callback instance to handle yValue options
	private static final CallbackPropertyHandler<ValueCallback> Y_VALUE_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.Y_VALUE);
	// callback instance to handle xAdjust options
	private static final CallbackPropertyHandler<AdjustSizeCallback> X_ADJUST_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.X_ADJUST);
	// callback instance to handle yAdjustg options
	private static final CallbackPropertyHandler<AdjustSizeCallback> Y_ADJUST_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.Y_ADJUST);

	// defaults options
	private final IsDefaultsAbstractPointedAnnotation defaultValues;
	// background color handler
	private final BackgroundColorHandler backgroundColorHandler;
	// extended shadow options handler
	private final ExtendedShadowOptionsHandler extendedShadowOptionsHandler;

	/**
	 * Creates the object with the type of annotation to handle.
	 * 
	 * @param type annotation type
	 * @param id annotation id
	 * @param defaultValues default options instance
	 */
	AbstractPointedAnnotation(AnnotationType type, AnnotationId id, IsDefaultsAnnotation defaultValues) {
		super(type, id, defaultValues);
		// checks if default are of the right class
		Checker.assertCheck(getDefaultsValues() instanceof IsDefaultsAbstractPointedAnnotation, Utilities.applyTemplate(INVALID_DEFAULTS_VALUES_CLASS, type.value()));
		// casts and stores it
		this.defaultValues = (IsDefaultsAbstractPointedAnnotation) getDefaultsValues();
		// creates background color handler
		this.backgroundColorHandler = new BackgroundColorHandler(this, this.defaultValues, getNativeObject());
		// creates shadow options handler
		this.extendedShadowOptionsHandler = new ExtendedShadowOptionsHandler(this, this.defaultValues, getNativeObject());
		// sets callbacks proxies
		initCallbacks();
	}

	/**
	 * Creates the object wrapping an existing native object.
	 * 
	 * @param nativeObject native object to wrap
	 * @param defaultValues default options instance
	 */
	AbstractPointedAnnotation(NativeObject nativeObject, IsDefaultsAnnotation defaultValues) {
		super(nativeObject, defaultValues);
		// checks if default are of the right class
		Checker.assertCheck(getDefaultsValues() instanceof IsDefaultsAbstractPointedAnnotation, Utilities.applyTemplate(INVALID_DEFAULTS_VALUES_CLASS, getType()));
		// casts and stores it
		this.defaultValues = (IsDefaultsAbstractPointedAnnotation) getDefaultsValues();
		// creates background color handler
		this.backgroundColorHandler = new BackgroundColorHandler(this, this.defaultValues, getNativeObject());
		// creates shadow options handler
		this.extendedShadowOptionsHandler = new ExtendedShadowOptionsHandler(this, this.defaultValues, getNativeObject());
		// sets callbacks proxies
		initCallbacks();
	}

	/**
	 * Initializes the callbacks proxies for the options which can be scriptable.
	 */
	private void initCallbacks() {
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// sets function to proxy callback in order to invoke the java interface
		this.xValueCallbackProxy.setCallback(context -> onValue(new AnnotationContext(this, context), getXValueCallback()));
		// sets function to proxy callback in order to invoke the java interface
		this.yValueCallbackProxy.setCallback(context -> onValue(new AnnotationContext(this, context), getYValueCallback()));
		// sets function to proxy callback in order to invoke the java interface
		this.xAdjustCallbackProxy.setCallback(context -> ScriptableUtils.getOptionValueAsNumber(new AnnotationContext(this, context), getXAdjustCallback(), defaultValues.getXAdjust()).doubleValue());
		// sets function to proxy callback in order to invoke the java interface
		this.yAdjustCallbackProxy.setCallback(context -> ScriptableUtils.getOptionValueAsNumber(new AnnotationContext(this, context), getYAdjustCallback(), defaultValues.getYAdjust()).doubleValue());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.annotation.HasBackgroundColor#getBackgroundColorHandler()
	 */
	@Override
	public final BackgroundColorHandler getBackgroundColorHandler() {
		return backgroundColorHandler;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.annotation.HasExtendedShadowOptions#getExtendedShadowOptionsHandler()
	 */
	@Override
	public final ExtendedShadowOptionsHandler getExtendedShadowOptionsHandler() {
		return extendedShadowOptionsHandler;
	}

	/**
	 * Sets the data X value to draw the annotation at.
	 * 
	 * @param value the data X value to draw the annotation at
	 */
	public final void setXValue(String value) {
		// resets callback
		setXValue((ValueCallback) null);
		// stores value
		setValue(Property.X_VALUE, value);
	}

	/**
	 * Sets the data X value to draw the annotation at.
	 * 
	 * @param value the data X value to draw the annotation at
	 */
	public final void setXValue(double value) {
		// resets callback
		setXValue((ValueCallback) null);
		// stores value
		setValue(Property.X_VALUE, value);
	}

	/**
	 * Sets the data X value to draw the annotation at.
	 * 
	 * @param value the data X value to draw the annotation at
	 */
	public final void setXValue(Date value) {
		// resets callback
		setXValue((ValueCallback) null);
		// stores value
		setValue(Property.X_VALUE, value);
	}

	/**
	 * Returns the data X value to draw the annotation at.
	 * 
	 * @return the data X value to draw the annotation at
	 */
	@Override
	public final String getXValueAsString() {
		return getValueForMultipleKeyTypes(Property.X_VALUE, defaultValues.getXValueAsString());
	}

	/**
	 * Returns the data X value to draw the annotation at.
	 * 
	 * @return the data X value to draw the annotation at
	 */
	@Override
	public final double getXValueAsDouble() {
		return getValueForMultipleKeyTypes(Property.X_VALUE, defaultValues.getXValueAsDouble());
	}

	/**
	 * Returns the data X value to draw the annotation at.
	 * 
	 * @return the data X value to draw the annotation at
	 */
	@Override
	public final Date getXValueAsDate() {
		return getValueForMultipleKeyTypes(Property.X_VALUE, defaultValues.getXValueAsDate());
	}

	/**
	 * Sets the data Y value to draw the annotation at.
	 * 
	 * @param value the data Y value to draw the annotation at
	 */
	public final void setYValue(String value) {
		// resets callback
		setYValue((ValueCallback) null);
		// stores value
		setValue(Property.Y_VALUE, value);
	}

	/**
	 * Sets the data Y value to draw the annotation at.
	 * 
	 * @param value the data Y value to draw the annotation at
	 */
	public final void setYValue(double value) {
		// resets callback
		setYValue((ValueCallback) null);
		// stores value
		setValue(Property.Y_VALUE, value);
	}

	/**
	 * Sets the data Y value to draw the annotation at.
	 * 
	 * @param value the data Y value to draw the annotation at
	 */
	public final void setYValue(Date value) {
		// resets callback
		setYValue((ValueCallback) null);
		// stores value
		setValue(Property.Y_VALUE, value);
	}

	/**
	 * Returns the data Y value to draw the annotation at.
	 * 
	 * @return the data Y value to draw the annotation at
	 */
	@Override
	public final String getYValueAsString() {
		return getValueForMultipleKeyTypes(Property.Y_VALUE, defaultValues.getYValueAsString());
	}

	/**
	 * Returns the data Y value to draw the annotation at.
	 * 
	 * @return the data Y value to draw the annotation at
	 */
	@Override
	public final double getYValueAsDouble() {
		return getValueForMultipleKeyTypes(Property.Y_VALUE, defaultValues.getYValueAsDouble());
	}

	/**
	 * Returns the data Y value to draw the annotation at.
	 * 
	 * @return the data Y value to draw the annotation at
	 */
	@Override
	public final Date getYValueAsDate() {
		return getValueForMultipleKeyTypes(Property.Y_VALUE, defaultValues.getYValueAsDate());
	}

	/**
	 * Sets the adjustment along y-axis (top-bottom) of annotation relative to above number (can be negative).
	 * 
	 * @param yAdjust the adjustment along y-axis (top-bottom) of annotation
	 */
	public final void setYAdjust(double yAdjust) {
		// resets callback
		setYAdjust((AdjustSizeCallback) null);
		// stores value
		setValue(Property.Y_ADJUST, yAdjust);
	}

	/**
	 * Returns the adjustment along y-axis (top-bottom) of annotation relative to above number (can be negative).
	 * 
	 * @return the adjustment along y-axis (top-bottom) of annotation
	 */
	@Override
	public final double getYAdjust() {
		return getValue(Property.Y_ADJUST, defaultValues.getYAdjust());
	}

	/**
	 * Sets the adjustment along x-axis (left-right) of annotation relative to above number (can be negative).
	 * 
	 * @param xAdjust the adjustment along x-axis (left-right) of annotation
	 */
	public final void setXAdjust(double xAdjust) {
		// resets callback
		setXAdjust((AdjustSizeCallback) null);
		// stores value
		setValue(Property.X_ADJUST, xAdjust);
	}

	/**
	 * Returns the adjustment along x-axis (left-right) of annotation relative to above number (can be negative).
	 * 
	 * @return the adjustment along x-axis (left-right) of annotation
	 */
	@Override
	public final double getXAdjust() {
		return getValue(Property.X_ADJUST, defaultValues.getXAdjust());
	}

	// ---------------------
	// CALLBACKS
	// ---------------------

	/**
	 * Returns the callback called to set the data X value to draw the annotation at.
	 * 
	 * @return the callback called to set the data X value to draw the annotation at
	 */
	@Override
	public final ValueCallback getXValueCallback() {
		return X_VALUE_PROPERTY_HANDLER.getCallback(this, defaultValues.getXValueCallback());
	}

	/**
	 * Sets the callback to set the data X value to draw the annotation at.
	 * 
	 * @param valueCallback to set the data X value to draw the annotation at
	 */
	public final void setXValue(ValueCallback valueCallback) {
		X_VALUE_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, valueCallback, xValueCallbackProxy.getProxy());
	}

	/**
	 * Sets the callback to set the data X value to draw the annotation at.
	 * 
	 * @param valueCallback to set the data X value to draw the annotation at
	 */
	public final void setXValue(NativeCallback valueCallback) {
		// resets callback
		setXValue((ValueCallback) null);
		// stores values
		setValue(Property.X_VALUE, valueCallback);
	}

	/**
	 * Returns the callback called to set the data Y value to draw the annotation at.
	 * 
	 * @return the callback called to set the data Y value to draw the annotation at
	 */
	@Override
	public final ValueCallback getYValueCallback() {
		return Y_VALUE_PROPERTY_HANDLER.getCallback(this, defaultValues.getYValueCallback());
	}

	/**
	 * Sets the callback to set the data Y value to draw the annotation at.
	 * 
	 * @param valueCallback to set the data Y value to draw the annotation at
	 */
	public final void setYValue(ValueCallback valueCallback) {
		Y_VALUE_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, valueCallback, yValueCallbackProxy.getProxy());
	}

	/**
	 * Sets the callback to set the data Y value to draw the annotation at.
	 * 
	 * @param valueCallback to set the data Y value to draw the annotation at
	 */
	public final void setYValue(NativeCallback valueCallback) {
		// resets callback
		setYValue((ValueCallback) null);
		// stores values
		setValue(Property.Y_VALUE, valueCallback);

	}

	/**
	 * Returns the callback called to set the adjustment along y-axis (top-bottom) of annotation relative to above number (can be negative).
	 * 
	 * @return the callback called to set the adjustment along y-axis (top-bottom) of annotation relative to above number (can be negative)
	 */
	@Override
	public final AdjustSizeCallback getYAdjustCallback() {
		return Y_ADJUST_PROPERTY_HANDLER.getCallback(this, defaultValues.getYAdjustCallback());
	}

	/**
	 * Sets the callback to set the adjustment along x-axis (left-right) of annotation relative to above number (can be negative).
	 * 
	 * @param adjustCallback to set the adjustment along x-axis (left-right) of annotation relative to above number (can be negative)
	 */
	public final void setYAdjust(AdjustSizeCallback adjustCallback) {
		Y_ADJUST_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, adjustCallback, yAdjustCallbackProxy.getProxy());
	}

	/**
	 * Sets the callback to set the adjustment along x-axis (left-right) of annotation relative to above number (can be negative).
	 * 
	 * @param adjustCallback to set the adjustment along x-axis (left-right) of annotation relative to above number (can be negative)
	 */
	public final void setYAdjust(NativeCallback adjustCallback) {
		// resets callback
		setYAdjust((AdjustSizeCallback) null);
		// stores values
		setValue(Property.Y_ADJUST, adjustCallback);
	}

	/**
	 * Returns the callback called to set the adjustment along x-axis (left-right) of annotation relative to above number (can be negative).
	 * 
	 * @return the callback called to set the adjustment along x-axis (left-right) of annotation relative to above number (can be negative)
	 */
	@Override
	public final AdjustSizeCallback getXAdjustCallback() {
		return X_ADJUST_PROPERTY_HANDLER.getCallback(this, defaultValues.getXAdjustCallback());
	}

	/**
	 * Sets the callback to set the adjustment along y-axis (top-bottom) of annotation relative to above number (can be negative).
	 * 
	 * @param adjustCallback to set the adjustment along y-axis (top-bottom) of annotation relative to above number (can be negative)
	 */
	public final void setXAdjust(AdjustSizeCallback adjustCallback) {
		X_ADJUST_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, adjustCallback, xAdjustCallbackProxy.getProxy());
	}

	/**
	 * Sets the callback to set the adjustment along y-axis (top-bottom) of annotation relative to above number (can be negative).
	 * 
	 * @param adjustCallback to set the adjustment along y-axis (top-bottom) of annotation relative to above number (can be negative)
	 */
	public final void setXAdjust(NativeCallback adjustCallback) {
		// resets callback
		setXAdjust((AdjustSizeCallback) null);
		// stores values
		setValue(Property.X_ADJUST, adjustCallback);
	}

}
