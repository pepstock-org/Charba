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
import org.pepstock.charba.client.callbacks.ColorCallback;
import org.pepstock.charba.client.callbacks.RadiusCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyDoubleCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyObjectCallback;
import org.pepstock.charba.client.callbacks.ScriptableUtils;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.CallbackPropertyHandler;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.options.IsScaleId;
import org.pepstock.charba.client.utils.Utilities;

/**
 * Implements a POINT annotation which draws a point in the a chart.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class PointAnnotation extends AbstractAnnotation implements IsDefaultsPointAnnotation {

	/**
	 * Default point annotation border width, <b>{@value DEFAULT_BORDER_WIDTH}</b>.
	 */
	public static final int DEFAULT_BORDER_WIDTH = 2;

	/**
	 * Default point annotation radius, <b>{@value DEFAULT_RADIUS}</b>.
	 */
	public static final double DEFAULT_RADIUS = 10D;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		X_SCALE_ID("xScaleID"),
		Y_SCALE_ID("yScaleID"),
		X_VALUE("xValue"),
		Y_VALUE("yValue"),
		RADIUS("radius"),
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
	// callback proxy to invoke the radius function
	private final CallbackProxy<ProxyDoubleCallback> radiusCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the xValue function
	private final CallbackProxy<ProxyObjectCallback> xValueCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the yValue function
	private final CallbackProxy<ProxyObjectCallback> yValueCallbackProxy = JsHelper.get().newCallbackProxy();


	// callback instance to handle background color options
	private static final CallbackPropertyHandler<ColorCallback<AnnotationContext>> BACKGROUND_COLOR_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.BACKGROUND_COLOR);
	// callback instance to handle radius options
	private static final CallbackPropertyHandler<RadiusCallback<AnnotationContext>> RADIUS_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.RADIUS);
	// callback instance to handle xValue options
	private static final CallbackPropertyHandler<ValueCallback> X_VALUE_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.X_VALUE);
	// callback instance to handle yValue options
	private static final CallbackPropertyHandler<ValueCallback> Y_VALUE_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.Y_VALUE);


	// defaults options
	private final IsDefaultsPointAnnotation defaultValues;

	/**
	 * Creates a line annotation to be added to an {@link AnnotationOptions} instance.<br>
	 * The annotation id is calculated automatically.
	 * 
	 * @see AnnotationType#createId()
	 */
	public PointAnnotation() {
		this(AnnotationType.POINT.createId(), AnnotationType.POINT.getDefaultsValues());
	}

	/**
	 * Creates a line annotation to be added to an {@link AnnotationOptions} instance, using the ID passed as argument.
	 * 
	 * @param id annotation id to apply to the object, as string
	 */
	public PointAnnotation(String id) {
		this(IsAnnotationId.create(id));
	}

	/**
	 * Creates a line annotation to be added to an {@link AnnotationOptions} instance, using the ID passed as argument.
	 * 
	 * @param id annotation id to apply to the object
	 */
	public PointAnnotation(IsAnnotationId id) {
		this(id, AnnotationHelper.get().getDefaultsAnnotationOptionsByGlobal(AnnotationType.POINT, id));
	}

	/**
	 * Creates a line annotation to be added to an {@link AnnotationOptions} instance, using the ID passed as argument.<br>
	 * The chart instance, passed as argument, must be the chart where the annotations will be applied and is used to get the whole default options in order to get the default for
	 * this object.
	 * 
	 * @param id annotation id to apply to the object, as string
	 * @param chart chart instance related to the plugin options
	 */
	public PointAnnotation(String id, IsChart chart) {
		this(IsAnnotationId.create(id), chart);
	}

	/**
	 * Creates a line annotation to be added to an {@link AnnotationOptions} instance, using the ID passed as argument.<br>
	 * The chart instance, passed as argument, must be the chart where the annotations will be applied and is used to get the whole default options in order to get the default for
	 * this object.
	 * 
	 * @param id annotation id to apply to the object
	 * @param chart chart instance related to the plugin options
	 */
	public PointAnnotation(IsAnnotationId id, IsChart chart) {
		this(id, AnnotationHelper.get().getDefaultsAnnotationOptionsByChart(AnnotationType.POINT, id, chart));
	}

	/**
	 * Creates a line annotation to be added to an {@link AnnotationOptions} instance, using the native object and defaults passed as argument.
	 * 
	 * @param id annotation id to apply to the object
	 * @param defaultValues default options instance
	 */
	private PointAnnotation(IsAnnotationId id, IsDefaultsAnnotation defaultValues) {
		// if id is not consistent, new one is created
		// if defaults is not consistent, the defaults defined for this annotation type is used
		super(AnnotationType.POINT, id == null ? AnnotationType.POINT.createId() : id, defaultValues == null ? AnnotationType.POINT.getDefaultsValues() : defaultValues);
		// checks if default are of the right class
		if (getDefaultsValues() instanceof IsDefaultsPointAnnotation) {
			// casts and stores it
			this.defaultValues = (IsDefaultsPointAnnotation) getDefaultsValues();
		} else {
			// wrong class, exception!
			throw new IllegalArgumentException(Utilities.applyTemplate(INVALID_DEFAULTS_VALUES_CLASS, AnnotationType.POINT.value()));
		}
		// sets callbacks proxies
		initCallbacks();
	}

	/**
	 * Creates the object wrapping an existing native object.
	 * 
	 * @param nativeObject native object to wrap
	 * @param defaultValues default options instance
	 */
	PointAnnotation(NativeObject nativeObject, IsDefaultsAnnotation defaultValues) {
		super(nativeObject, defaultValues);
		// checks if default are of the right class
		if (getDefaultsValues() instanceof IsDefaultsPointAnnotation) {
			// casts and stores it
			this.defaultValues = (IsDefaultsPointAnnotation) getDefaultsValues();
		} else {
			// wrong class, exception!
			throw new IllegalArgumentException(Utilities.applyTemplate(INVALID_DEFAULTS_VALUES_CLASS, AnnotationType.POINT.value()));
		}
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
		this.backgroundColorCallbackProxy.setCallback((contextFunction, context) -> ScriptableUtils.getOptionValueAsColor(new AnnotationContext(this, context), getBackgroundColorCallback(), defaultValues.getBackgroundColorAsString()));
		// sets function to proxy callback in order to invoke the java interface
		this.radiusCallbackProxy.setCallback((contextFunction, context) -> ScriptableUtils.getOptionValue(new AnnotationContext(this, context), getRadiusCallback(), defaultValues.getRadius()).doubleValue());
		// sets function to proxy callback in order to invoke the java interface
		this.xValueCallbackProxy.setCallback((contextFunction, context) -> onValue(new AnnotationContext(this, context), getXValueCallback()));
		// sets function to proxy callback in order to invoke the java interface
		this.yValueCallbackProxy.setCallback((contextFunction, context) -> onValue(new AnnotationContext(this, context), getYValueCallback()));
	}


	/**
	 * Sets the radius of the point shape.<br>
	 * If set to 0, the point is not rendered.
	 * 
	 * @param radius array of the radius of the point shape.
	 */
	public void setRadius(double radius) {
		// resets callback
		setRadius(null);
		// stores value
		setValue(Property.RADIUS, radius);
	}

	/**
	 * Returns the radius of the point.
	 * 
	 * @return the radius of the point.
	 */
	@Override
	public double getRadius() {
		return getValue(Property.RADIUS, defaultValues.getRadius());
	}

	/**
	 * Sets the color of the background of annotation.
	 * 
	 * @param backgroundColor the color of the background of annotation
	 */
	public void setBackgroundColor(IsColor backgroundColor) {
		setBackgroundColor(IsColor.checkAndGetValue(backgroundColor));
	}

	/**
	 * Sets the color of the background of annotation.
	 * 
	 * @param backgroundColor the color of the background of annotation
	 */
	public void setBackgroundColor(String backgroundColor) {
		// resets callback
		setBackgroundColor((ColorCallback<AnnotationContext>)null);
		// stores value
		setValue(Property.BACKGROUND_COLOR, backgroundColor);
	}

	/**
	 * Returns the color of the background of annotation.
	 * 
	 * @return the color of the background of annotation
	 */
	@Override
	public String getBackgroundColorAsString() {
		return getValue(Property.BACKGROUND_COLOR, defaultValues.getBackgroundColorAsString());
	}

	/**
	 * Returns the color of the background of annotation.
	 * 
	 * @return the color of the background of annotation
	 */
	public IsColor getBackgroundColor() {
		return ColorBuilder.parse(getBackgroundColorAsString());
	}

	/**
	 * Sets the ID of the Y scale to bind onto.
	 * 
	 * @param scaleId the ID of the Y scale to bind onto
	 */
	public void setYScaleID(String scaleId) {
		// checks if scale id is valid
		IsScaleId.checkIfValid(scaleId);
		// stores it
		setValue(PointAnnotation.Property.Y_SCALE_ID, scaleId);
	}

	/**
	 * Sets the ID of the Y scale to bind onto.
	 * 
	 * @param scaleId the ID of the Y scale to bind onto
	 */
	public void setYScaleID(IsScaleId scaleId) {
		// checks if scale id is valid
		IsScaleId.checkIfValid(scaleId);
		// stores it
		setValue(PointAnnotation.Property.Y_SCALE_ID, scaleId);
	}

	/**
	 * Returns the ID of the Y scale to bind onto.
	 * 
	 * @return the ID of the Y scale to bind onto
	 */
	@Override
	public IsScaleId getYScaleID() {
		return getValue(Property.Y_SCALE_ID, defaultValues.getYScaleID());
	}

	/**
	 * Sets the ID of the X scale to bind onto.
	 * 
	 * @param scaleId the ID of the X scale to bind onto
	 */
	public void setXScaleID(String scaleId) {
		// checks if scale id is valid
		IsScaleId.checkIfValid(scaleId);
		// stores it
		setValue(PointAnnotation.Property.X_SCALE_ID, scaleId);
	}

	/**
	 * Sets the ID of the X scale to bind onto.
	 * 
	 * @param scaleId the ID of the X scale to bind onto
	 */
	public void setXScaleID(IsScaleId scaleId) {
		// checks if scale id is valid
		IsScaleId.checkIfValid(scaleId);
		// stores it
		setValue(PointAnnotation.Property.X_SCALE_ID, scaleId);
	}

	/**
	 * Returns the ID of the X scale to bind onto.
	 * 
	 * @return the ID of the X scale to bind onto
	 */
	@Override
	public IsScaleId getXScaleID() {
		return getValue(Property.X_SCALE_ID, defaultValues.getXScaleID());
	}

	/**
	 * Sets the data X value to draw the line at.
	 * 
	 * @param value the data X value to draw the line at
	 */
	public void setXValue(String value) {
		// resets callback
		setXValue((ValueCallback)null);
		// stores value
		setValue(Property.X_VALUE, value);
	}

	/**
	 * Sets the data X value to draw the line at.
	 * 
	 * @param value the data X value to draw the line at
	 */
	public void setXValue(double value) {
		// resets callback
		setXValue((ValueCallback)null);
		// stores value		
		setValue(Property.X_VALUE, value);
	}

	/**
	 * Sets the data X value to draw the line at.
	 * 
	 * @param value the data X value to draw the line at
	 */
	public void setXValue(Date value) {
		// resets callback
		setXValue((ValueCallback)null);
		// stores value
		setValue(Property.X_VALUE, value);
	}

	/**
	 * Returns the data X value to draw the line at.
	 * 
	 * @return the data X value to draw the line at
	 */
	@Override
	public String getXValueAsString() {
		return getValueForMultipleKeyTypes(Property.X_VALUE, defaultValues.getXValueAsString());
	}

	/**
	 * Returns the data X value to draw the line at.
	 * 
	 * @return the data X value to draw the line at
	 */
	@Override
	public double getXValueAsDouble() {
		return getValueForMultipleKeyTypes(Property.X_VALUE, defaultValues.getXValueAsDouble());
	}

	/**
	 * Returns the data X value to draw the line at.
	 * 
	 * @return the data X value to draw the line at
	 */
	@Override
	public Date getXValueAsDate() {
		return getValueForMultipleKeyTypes(Property.X_VALUE, defaultValues.getXValueAsDate());
	}

	/**
	 * Sets the data Y value to draw the line at.
	 * 
	 * @param value the data Y value to draw the line at
	 */
	public void setYValue(String value) {
		// resets callback
		setYValue((ValueCallback)null);
		// stores value
		setValue(Property.Y_VALUE, value);
	}

	/**
	 * Sets the data Y value to draw the line at.
	 * 
	 * @param value the data Y value to draw the line at
	 */
	public void setYValue(double value) {
		// resets callback
		setYValue((ValueCallback)null);
		// stores value
		setValue(Property.Y_VALUE, value);
	}

	/**
	 * Sets the data Y value to draw the line at.
	 * 
	 * @param value the data Y value to draw the line at
	 */
	public void setYValue(Date value) {
		// resets callback
		setYValue((ValueCallback)null);
		// stores value
		setValue(Property.Y_VALUE, value);
	}

	/**
	 * Returns the data Y value to draw the line at.
	 * 
	 * @return the data Y value to draw the line at
	 */
	@Override
	public String getYValueAsString() {
		return getValueForMultipleKeyTypes(Property.Y_VALUE, defaultValues.getYValueAsString());
	}

	/**
	 * Returns the data Y value to draw the line at.
	 * 
	 * @return the data Y value to draw the line at
	 */
	@Override
	public double getYValueAsDouble() {
		return getValueForMultipleKeyTypes(Property.Y_VALUE, defaultValues.getYValueAsDouble());
	}

	/**
	 * Returns the data Y value to draw the line at.
	 * 
	 * @return the data Y value to draw the line at
	 */
	@Override
	public Date getYValueAsDate() {
		return getValueForMultipleKeyTypes(Property.Y_VALUE, defaultValues.getYValueAsDate());
	}
	
	// ---------------------
	// CALLBACKS
	// ---------------------

	/**
	 * Returns the callback called to set the color of the background of annotation.
	 * 
	 * @return the callback called to set the color of the background of annotation
	 */
	@Override
	public ColorCallback<AnnotationContext> getBackgroundColorCallback() {
		return BACKGROUND_COLOR_PROPERTY_HANDLER.getCallback(this, defaultValues.getBackgroundColorCallback());
	}

	/**
	 * Sets the callback to set the color of the background of annotation.
	 * 
	 * @param backgroundColorCallback to set the color of the background of annotation
	 */
	public void setBackgroundColor(ColorCallback<AnnotationContext> backgroundColorCallback) {
		BACKGROUND_COLOR_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, backgroundColorCallback, backgroundColorCallbackProxy.getProxy());
	}
	
	/**
	 * Returns the callback called to set the radius.
	 * 
	 * @return the callback called to set the radius
	 */
	@Override
	public RadiusCallback<AnnotationContext> getRadiusCallback() {
		return RADIUS_PROPERTY_HANDLER.getCallback(this, defaultValues.getRadiusCallback());
	}

	/**
	 * Sets the callback to set the radius.
	 * 
	 * @param radiusCallback to set the radius
	 */
	public void setRadius(RadiusCallback<AnnotationContext> radiusCallback) {
		RADIUS_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, radiusCallback, radiusCallbackProxy.getProxy());
	}
	
	/**
	 * Returns the callback called to set the data X value to draw the line at.
	 * 
	 * @return the callback called to set the data X value to draw the line at
	 */
	@Override
	public ValueCallback getXValueCallback() {
		return X_VALUE_PROPERTY_HANDLER.getCallback(this, defaultValues.getXValueCallback());
	}

	/**
	 * Sets the callback to set the data X value to draw the line at.
	 * 
	 * @param valueCallback to set the data X value to draw the line at
	 */
	public void setXValue(ValueCallback valueCallback) {
		X_VALUE_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, valueCallback, xValueCallbackProxy.getProxy());
	}
	
	/**
	 * Returns the callback called to set the data Y value to draw the line at.
	 * 
	 * @return the callback called to set the data Y value to draw the line at
	 */
	@Override
	public ValueCallback getYValueCallback() {
		return Y_VALUE_PROPERTY_HANDLER.getCallback(this, defaultValues.getYValueCallback());
	}

	/**
	 * Sets the callback to set the data Y value to draw the line at.
	 * 
	 * @param valueCallback to set the data Y value to draw the line at
	 */
	public void setYValue(ValueCallback valueCallback) {
		Y_VALUE_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, valueCallback, yValueCallbackProxy.getProxy());
	}

}
