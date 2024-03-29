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

import java.util.Date;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.annotation.callbacks.ControlPointCallback;
import org.pepstock.charba.client.annotation.callbacks.CurveCallback;
import org.pepstock.charba.client.annotation.callbacks.ValueCallback;
import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyBooleanCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyObjectCallback;
import org.pepstock.charba.client.callbacks.ScriptableUtil;
import org.pepstock.charba.client.commons.CallbackPropertyHandler;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.items.Undefined;
import org.pepstock.charba.client.options.ElementFactory;
import org.pepstock.charba.client.options.ScaleId;
import org.pepstock.charba.client.utils.Utilities;
import org.pepstock.charba.client.utils.Window;

/**
 * Implements a LINE annotation which draws a line in the a chart.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class LineAnnotation extends AbstractAnnotation implements IsDefaultsLineAnnotation, IsLabelContainer<LineLabel> {

	/**
	 * Default line annotation border width, <b>{@value DEFAULT_BORDER_WIDTH}</b>.
	 */
	public static final int DEFAULT_BORDER_WIDTH = 2;

	// Annotation element key
	private static final String ELEMENT_KEY_AS_STRING = "LineAnnotation";

	/**
	 * Element factory to get "{@value LineAnnotation#ELEMENT_KEY_AS_STRING}" element.
	 */
	public static final ElementFactory<LineAnnotation> FACTORY = new LineElementFactory(ELEMENT_KEY_AS_STRING);

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		ARROW_HEADS("arrowHeads"),
		CONTROL_POINT("controlPoint"),
		CURVE("curve"),
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
	// callback proxy to invoke the curve function
	private final CallbackProxy<ProxyBooleanCallback> curveCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the controlpoint function
	private final CallbackProxy<ProxyObjectCallback> controlPointCallbackProxy = JsHelper.get().newCallbackProxy();

	// callback instance to handle value options
	private static final CallbackPropertyHandler<ValueCallback> VALUE_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.VALUE);
	// callback instance to handle value options
	private static final CallbackPropertyHandler<ValueCallback> END_VALUE_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.END_VALUE);
	// callback instance to handle curve options
	private static final CallbackPropertyHandler<CurveCallback> CURVE_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.CURVE);
	// callback instance to handle control point options
	private static final CallbackPropertyHandler<ControlPointCallback> CONTROL_POINT_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.CONTROL_POINT);

	// defaults options
	private final IsDefaultsLineAnnotation defaultValues;
	// label for line instance
	private final LineLabel label;
	// arraow heads instance
	private final ArrowHeads arrowHeads;

	/**
	 * Creates a line annotation to be added to an {@link AnnotationOptions} instance.<br>
	 * The annotation id is calculated automatically.
	 * 
	 * @see AnnotationType#createId()
	 */
	public LineAnnotation() {
		this(AnnotationType.LINE.createId(), Defaults.get().getGlobal().getElements().getElement(FACTORY));
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
		this(id, Defaults.get().getGlobal().getElements().getElement(FACTORY));
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
		this(id, IsChart.checkAndGetIfConsistent(chart).getOptions().getElements().getElement(FACTORY));
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
		// creates arrow heads
		this.arrowHeads = new ArrowHeads(this, getValue(Property.ARROW_HEADS), this.defaultValues.getArrowHeads());
		// stores in the annotation
		setValue(Property.ARROW_HEADS, arrowHeads);
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
		// creates arrow heads
		this.arrowHeads = new ArrowHeads(this, getValue(Property.ARROW_HEADS), this.defaultValues.getArrowHeads());
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
		// sets function to proxy callback in order to invoke the java interface
		this.curveCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValue(new AnnotationContext(this, context), getCurveCallback(), defaultValues.isCurve()));
		// sets function to proxy callback in order to invoke the java interface
		this.controlPointCallbackProxy.setCallback(context -> onControlPoint(new AnnotationContext(this, context), getControlPointCallback()));
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
	 * Returns the arrow heads of annotation.
	 * 
	 * @return the arrow heads of annotation
	 */
	@Override
	public ArrowHeads getArrowHeads() {
		return arrowHeads;
	}

	/**
	 * Sets <code>true</code> if the line is set as a curve.
	 * 
	 * @param curve <code>true</code> if the line is set as a curve.
	 */
	public void setCurve(boolean curve) {
		// reset callback
		setCurve((CurveCallback) null);
		// stores value
		setValue(Property.CURVE, curve);
	}

	/**
	 * Returns <code>true</code> if the line is set as a curve.
	 * 
	 * @return <code>true</code> if the line is set as a curve
	 */
	@Override
	public boolean isCurve() {
		return getValue(Property.CURVE, defaultValues.isCurve());
	}

	/**
	 * Sets the control point to drawn the curve, calculated in pixels.
	 * 
	 * @param cp the control point to drawn the curve.
	 */
	public void setControlPoint(double cp) {
		// resets callback
		setControlPoint((ControlPointCallback) null);
		// stores values
		setValue(Property.CONTROL_POINT, cp);
	}

	/**
	 * Sets the control point to drawn the curve, calculated in percentage.
	 * 
	 * @param cp the control point to drawn the curve.
	 */
	public void setControlPointAsPercentage(double cp) {
		// resets callback
		setControlPoint((ControlPointCallback) null);
		// stores values
		setControlPoint(Utilities.getAsPercentage(cp, ControlPoint.MINIMUN_PERCENTAGE, ControlPoint.MINIMUN_PERCENTAGE, 0D));
	}

	/**
	 * Sets the control point to drawn the curve, calculated in percentage format 'number%' which are representing the percentage of the distance between the start and end point
	 * from the center.
	 * 
	 * @param cp the control point to drawn the curve.
	 */
	public void setControlPoint(String cp) {
		// gets percentage
		double value = Utilities.getAsPercentage(cp, Undefined.DOUBLE);
		// checks if consistent
		if (Undefined.isNot(value)) {
			// resets callback
			setControlPoint((ControlPointCallback) null);
			// stores value
			setValue(Property.CONTROL_POINT, cp);
		} else {
			// if here, the argument is not consistent
			// then removes the options
			remove(Property.CONTROL_POINT);
		}
	}

	/**
	 * Sets the control point to drawn the curve, calculated in pixels.<br>
	 * It can be set by a string in percentage format 'number%' which are representing the percentage of the distance between the start and end point from the center.
	 * 
	 * @param cp the control point to drawn the curve.
	 */
	public void setControlPoint(ControlPoint cp) {
		// resets callback
		setControlPoint((ControlPointCallback) null);
		// stores values
		setValue(Property.CONTROL_POINT, cp);
	}

	/**
	 * Returns the control point to drawn the curve, calculated in pixels.<br>
	 * It can be set by a string in percentage format 'number%' which are representing the percentage of the distance between the start and end point from the center.
	 * 
	 * @return the control point to drawn the curve
	 */
	public ControlPoint getControlPoint() {
		// checks the type of control point
		if (isType(Property.CONTROL_POINT, ObjectType.NUMBER)) {
			// gets value as number
			double value = getValue(Property.CONTROL_POINT, Undefined.DOUBLE);
			// creates and returns object
			return new ControlPoint(value, value);
		} else if (isType(Property.CONTROL_POINT, ObjectType.STRING)) {
			// gets value as number
			String value = getValue(Property.CONTROL_POINT, Undefined.STRING);
			// creates and returns object
			return new ControlPoint(value, value);
		} else if (isType(Property.CONTROL_POINT, ObjectType.OBJECT)) {
			// returns as object
			return new ControlPoint(this, Property.CONTROL_POINT, getValue(Property.CONTROL_POINT));
		}
		// if here, control point is not set
		// then returns new default
		return new ControlPoint(this, Property.CONTROL_POINT, null);
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
		setValue(Property.SCALE_ID, scaleId);
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
		setValue(Property.SCALE_ID, scaleId);
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

	/**
	 * Returns the callback called to set whether the annotation should be curve.
	 * 
	 * @return the callback called to set whether the annotation should be curve
	 */
	@Override
	public CurveCallback getCurveCallback() {
		return CURVE_PROPERTY_HANDLER.getCallback(this, defaultValues.getCurveCallback());
	}

	/**
	 * Sets the callback to set whether the annotation should be curve.
	 * 
	 * @param curveCallback to set whether the annotation should be curve
	 */
	public void setCurve(CurveCallback curveCallback) {
		CURVE_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, curveCallback, curveCallbackProxy.getProxy());
	}

	/**
	 * Sets the callback to set whether the annotation should be curve.
	 * 
	 * @param curveCallback to set whether the annotation should be curve
	 */
	public void setCurve(NativeCallback curveCallback) {
		// resets callback
		setCurve((CurveCallback) null);
		// stores values
		setValueAndAddToParent(Property.CURVE, curveCallback);
	}

	/**
	 * Returns the callback called to set the annotation control point for curve.
	 * 
	 * @return the callback called to set the annotation control point for curve.
	 */
	@Override
	public ControlPointCallback getControlPointCallback() {
		return CONTROL_POINT_PROPERTY_HANDLER.getCallback(this, defaultValues.getControlPointCallback());
	}

	/**
	 * Sets the callback to set the annotation control point for curve.
	 * 
	 * @param controlPointCallback to set the annotation control point for curve.
	 */
	public void setControlPoint(ControlPointCallback controlPointCallback) {
		CONTROL_POINT_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, controlPointCallback, controlPointCallbackProxy.getProxy());
	}

	/**
	 * Sets the callback to set the annotation control point for curve.
	 * 
	 * @param controlPointCallback to set the annotation control point for curve.
	 */
	public void setControlPoint(NativeCallback controlPointCallback) {
		// resets callback
		setControlPoint((ControlPointCallback) null);
		// stores values
		setValueAndAddToParent(Property.CONTROL_POINT, controlPointCallback);
	}

	// --------------------------
	// INTERNALS
	// --------------------------

	/**
	 * Returns an object as double, string or control point {@link ControlPoint} when the callback has been activated.
	 * 
	 * @param context annotation context instance.
	 * @param controlPointCallback value callback instance
	 * @return an object as double, string or control point {@link ControlPoint}
	 */
	final Object onControlPoint(AnnotationContext context, ControlPointCallback controlPointCallback) {
		// gets value
		Object result = ScriptableUtil.getOptionValue(context, controlPointCallback);
		// checks if consistent
		if (result instanceof Number) {
			// casts to number
			Number number = (Number) result;
			// returns the number
			return number.doubleValue();
		} else if (result instanceof String) {
			// returns the string
			return result;
		} else if (result instanceof ControlPoint) {
			// casts to control point
			ControlPoint cp = (ControlPoint) result;
			// returns the object
			return cp.nativeObject();
		}
		// default result is undefined
		return Window.undefined();
	}

	/**
	 * Specific element factory for line annotation elements.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class LineElementFactory extends AnnotationElementFactory<LineAnnotation> {

		/**
		 * Creates the factory by the key of object, as string.
		 * 
		 * @param elementKeyAsString the key of object, as string.
		 */
		private LineElementFactory(String elementKeyAsString) {
			super(elementKeyAsString);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.annotation.AnnotationElementFactory#createOptions(org.pepstock.charba.client.commons.NativeObject)
		 */
		@Override
		LineAnnotation createOptions(NativeObject nativeObject) {
			return new LineAnnotation(nativeObject, AnnotationType.LINE.getDefaultsValues());
		}

	}
}