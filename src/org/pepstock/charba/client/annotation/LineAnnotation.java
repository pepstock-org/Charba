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

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.annotation.callbacks.ValueCallback;
import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyObjectCallback;
import org.pepstock.charba.client.commons.CallbackPropertyHandler;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.options.ScaleId;
import org.pepstock.charba.client.utils.Utilities;

/**
 * Implements a LINE annotation which draws a line in the a chart.<br>
 * Vertical or horizontal lines are supported.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class LineAnnotation extends AbstractAnnotation implements IsDefaultsLineAnnotation, IsLabelContainer<LineLabel> {

	/**
	 * Default line annotation border width, <b>{@value DEFAULT_BORDER_WIDTH}</b>.
	 */
	public static final int DEFAULT_BORDER_WIDTH = 2;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		VALUE("value"),
		END_VALUE("endValue"),
		SCALE_ID("scaleID"),
		LABEL("label");

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
	// callback proxy to invoke the value function
	private final CallbackProxy<ProxyObjectCallback> valueCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the end value function
	private final CallbackProxy<ProxyObjectCallback> endValueCallbackProxy = JsHelper.get().newCallbackProxy();

	// callback instance to handle value options
	private static final CallbackPropertyHandler<ValueCallback> VALUE_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.VALUE);
	// callback instance to handle value options
	private static final CallbackPropertyHandler<ValueCallback> END_VALUE_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.END_VALUE);

	// defaults options
	private final IsDefaultsLineAnnotation defaultValues;
	// label for line instance
	private final LineLabel label;

	/**
	 * Creates a line annotation to be added to an {@link AnnotationOptions} instance.<br>
	 * The annotation id is calculated automatically.
	 * 
	 * @see AnnotationType#createId()
	 */
	public LineAnnotation() {
		this(AnnotationType.LINE.createId(), AnnotationType.LINE.getDefaultsValues());
	}

	/**
	 * Creates a line annotation to be added to an {@link AnnotationOptions} instance, using the ID passed as argument.
	 * 
	 * @param id annotation id to apply to the object, as string
	 */
	public LineAnnotation(String id) {
		this(AnnotationId.create(id));
	}

	/**
	 * Creates a line annotation to be added to an {@link AnnotationOptions} instance, using the ID passed as argument.
	 * 
	 * @param id annotation id to apply to the object
	 */
	public LineAnnotation(AnnotationId id) {
		this(id, AnnotationHelper.get().getDefaultsAnnotationOptionsByGlobal(AnnotationType.LINE, id));
	}

	/**
	 * Creates a line annotation to be added to an {@link AnnotationOptions} instance, using the ID passed as argument.<br>
	 * The chart instance, passed as argument, must be the chart where the annotations will be applied and is used to get the whole default options in order to get the default for
	 * this object.
	 * 
	 * @param id annotation id to apply to the object, as string
	 * @param chart chart instance related to the plugin options
	 */
	public LineAnnotation(String id, IsChart chart) {
		this(AnnotationId.create(id), chart);
	}

	/**
	 * Creates a line annotation to be added to an {@link AnnotationOptions} instance, using the ID passed as argument.<br>
	 * The chart instance, passed as argument, must be the chart where the annotations will be applied and is used to get the whole default options in order to get the default for
	 * this object.
	 * 
	 * @param id annotation id to apply to the object
	 * @param chart chart instance related to the plugin options
	 */
	public LineAnnotation(AnnotationId id, IsChart chart) {
		this(id, AnnotationHelper.get().getDefaultsAnnotationOptionsByChart(AnnotationType.LINE, id, chart));
	}

	/**
	 * Creates a line annotation to be added to an {@link AnnotationOptions} instance, using the native object and defaults passed as argument.
	 * 
	 * @param id annotation id to apply to the object
	 * @param defaultValues default options instance
	 */
	private LineAnnotation(AnnotationId id, IsDefaultsAnnotation defaultValues) {
		// if id is not consistent, new one is created
		// if defaults is not consistent, the defaults defined for this annotation type is used
		super(AnnotationType.LINE, id == null ? AnnotationType.LINE.createId() : id, defaultValues == null ? AnnotationType.LINE.getDefaultsValues() : defaultValues);
		// checks if default are of the right class
		Checker.assertCheck(getDefaultsValues() instanceof IsDefaultsLineAnnotation, Utilities.applyTemplate(INVALID_DEFAULTS_VALUES_CLASS, AnnotationType.LINE.value()));
		// casts and stores it
		this.defaultValues = (IsDefaultsLineAnnotation) getDefaultsValues();
		// creates a line label
		this.label = new LineLabel(this, this.defaultValues.getLabel());
		// stores in the annotation
		setValue(Property.LABEL, label);
		// sets callbacks proxies
		initCallbacks();
	}

	/**
	 * Creates the object wrapping an existing native object.
	 * 
	 * @param nativeObject native object to wrap
	 * @param defaultValues default options instance
	 */
	LineAnnotation(NativeObject nativeObject, IsDefaultsAnnotation defaultValues) {
		super(nativeObject, defaultValues);
		// checks if default are of the right class
		Checker.assertCheck(getDefaultsValues() instanceof IsDefaultsLineAnnotation, Utilities.applyTemplate(INVALID_DEFAULTS_VALUES_CLASS, AnnotationType.LINE.value()));
		// casts and stores it
		this.defaultValues = (IsDefaultsLineAnnotation) getDefaultsValues();
		// creates a line label
		this.label = new LineLabel(this, getValue(Property.LABEL), this.defaultValues.getLabel());
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
		this.valueCallbackProxy.setCallback(context -> onValue(new AnnotationContext(this, context), getValueCallback()));
		// sets function to proxy callback in order to invoke the java interface
		this.endValueCallbackProxy.setCallback(context -> onValue(new AnnotationContext(this, context), getEndValueCallback()));
	}

	/**
	 * Returns the label on the line.
	 * 
	 * @return the label on the line
	 */
	@Override
	public LineLabel getLabel() {
		return label;
	}

	/**
	 * Sets the ID of the scale to bind onto.
	 * 
	 * @param scaleId the ID of the scale to bind onto
	 */
	public void setScaleID(String scaleId) {
		// checks if scale id is valid
		ScaleId.checkIfValid(scaleId);
		// stores it
		setValue(LineAnnotation.Property.SCALE_ID, scaleId);
	}

	/**
	 * Sets the ID of the scale to bind onto.
	 * 
	 * @param scaleId the ID of the scale to bind onto
	 */
	public void setScaleID(ScaleId scaleId) {
		// checks if scale id is valid
		ScaleId.checkIfValid(scaleId);
		// stores it
		setValue(LineAnnotation.Property.SCALE_ID, scaleId);
	}

	/**
	 * Returns the ID of the scale to bind onto.
	 * 
	 * @return the ID of the scale to bind onto
	 */
	@Override
	public ScaleId getScaleID() {
		return getValue(Property.SCALE_ID, defaultValues.getScaleID());
	}

	/**
	 * Sets the data value to draw the line at.
	 * 
	 * @param value the data value to draw the line at
	 */
	public void setValue(String value) {
		// resets callback
		setValue((ValueCallback) null);
		// stores value
		setValue(Property.VALUE, value);
	}

	/**
	 * Sets the data value to draw the line at.
	 * 
	 * @param value the data value to draw the line at
	 */
	public void setValue(double value) {
		// resets callback
		setValue((ValueCallback) null);
		// stores value
		setValue(Property.VALUE, value);
	}

	/**
	 * Sets the data value to draw the line at.
	 * 
	 * @param value the data value to draw the line at
	 */
	public void setValue(Date value) {
		// resets callback
		setValue((ValueCallback) null);
		// stores value
		setValue(Property.VALUE, value);
	}

	/**
	 * Returns the data value to draw the line at.
	 * 
	 * @return the data value to draw the line at
	 */
	@Override
	public String getValueAsString() {
		return getValueForMultipleKeyTypes(Property.VALUE, defaultValues.getValueAsString());
	}

	/**
	 * Returns the data value to draw the line at.
	 * 
	 * @return the data value to draw the line at
	 */
	@Override
	public double getValueAsDouble() {
		return getValueForMultipleKeyTypes(Property.VALUE, defaultValues.getValueAsDouble());
	}

	/**
	 * Returns the data value to draw the line at.
	 * 
	 * @return the data value to draw the line at
	 */
	@Override
	public Date getValueAsDate() {
		return getValueForMultipleKeyTypes(Property.VALUE, defaultValues.getValueAsDate());
	}

	/**
	 * Sets the data value at which the line draw should end.
	 * 
	 * @param endValue the data value at which the line draw should end
	 */
	public void setEndValue(String endValue) {
		// resets callback
		setEndValue((ValueCallback) null);
		// stores value
		setValue(Property.END_VALUE, endValue);
	}

	/**
	 * Sets the data value at which the line draw should end.
	 * 
	 * @param endValue the data value at which the line draw should end
	 */
	public void setEndValue(double endValue) {
		// resets callback
		setEndValue((ValueCallback) null);
		// stores value
		setValue(Property.END_VALUE, endValue);
	}

	/**
	 * Sets the data value at which the line draw should end.
	 * 
	 * @param endValue the data value at which the line draw should end
	 */
	public void setEndValue(Date endValue) {
		// resets callback
		setEndValue((ValueCallback) null);
		// stores value
		setValue(Property.END_VALUE, endValue);
	}

	/**
	 * Returns the data value at which the line draw should end.
	 * 
	 * @return the data value at which the line draw should end
	 */
	@Override
	public String getEndValueAsString() {
		return getValueForMultipleKeyTypes(Property.END_VALUE, defaultValues.getEndValueAsString());
	}

	/**
	 * Returns the data value at which the line draw should end.
	 * 
	 * @return the data value at which the line draw should end
	 */
	@Override
	public double getEndValueAsDouble() {
		return getValueForMultipleKeyTypes(Property.END_VALUE, defaultValues.getEndValueAsDouble());
	}

	/**
	 * Returns the data value at which the line draw should end.
	 * 
	 * @return the data value at which the line draw should end
	 */
	@Override
	public Date getEndValueAsDate() {
		return getValueForMultipleKeyTypes(Property.END_VALUE, defaultValues.getEndValueAsDate());
	}

	// ---------------------
	// CALLBACKS
	// ---------------------

	/**
	 * Returns the callback called to set the left edge of the box, in units along the x axis.
	 * 
	 * @return the callback called to set the left edge of the box, in units along the x axis
	 */
	@Override
	public ValueCallback getValueCallback() {
		return VALUE_PROPERTY_HANDLER.getCallback(this, defaultValues.getValueCallback());
	}

	/**
	 * Sets the callback to set the left edge of the box, in units along the x axis.
	 * 
	 * @param valueCallback to set the left edge of the box, in units along the x axis
	 */
	public void setValue(ValueCallback valueCallback) {
		VALUE_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, valueCallback, valueCallbackProxy.getProxy());
	}

	/**
	 * Sets the callback to set the left edge of the box, in units along the x axis.
	 * 
	 * @param valueCallback to set the left edge of the box, in units along the x axis
	 */
	public void setValue(NativeCallback valueCallback) {
		// resets callback
		setValue((ValueCallback) null);
		// stores values
		setValueAndAddToParent(Property.VALUE, valueCallback);
	}

	/**
	 * Returns the callback called to set the data value at which the line draw should end.
	 * 
	 * @return the callback called to set the data value at which the line draw should end
	 */
	@Override
	public ValueCallback getEndValueCallback() {
		return END_VALUE_PROPERTY_HANDLER.getCallback(this, defaultValues.getEndValueCallback());
	}

	/**
	 * Sets the callback to set the data value at which the line draw should end.
	 * 
	 * @param valueCallback to set the data value at which the line draw should end
	 */
	public void setEndValue(ValueCallback valueCallback) {
		END_VALUE_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, valueCallback, endValueCallbackProxy.getProxy());
	}

	/**
	 * Sets the callback to set the data value at which the line draw should end.
	 * 
	 * @param valueCallback to set the data value at which the line draw should end
	 */
	public void setEndValue(NativeCallback valueCallback) {
		// resets callback
		setEndValue((ValueCallback) null);
		// stores values
		setValueAndAddToParent(Property.END_VALUE, valueCallback);
	}

}
