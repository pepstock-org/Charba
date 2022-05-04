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
import java.util.concurrent.atomic.AtomicInteger;

import org.pepstock.charba.client.annotation.callbacks.AdjustScaleRangeCallback;
import org.pepstock.charba.client.annotation.callbacks.DrawTimeCallback;
import org.pepstock.charba.client.annotation.callbacks.LabelAlignPositionCallback;
import org.pepstock.charba.client.annotation.callbacks.ValueCallback;
import org.pepstock.charba.client.annotation.enums.DrawTime;
import org.pepstock.charba.client.annotation.enums.LabelPosition;
import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyBooleanCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyObjectCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyStringCallback;
import org.pepstock.charba.client.callbacks.ScriptableUtil;
import org.pepstock.charba.client.callbacks.SimpleDisplayCallback;
import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.CallbackPropertyHandler;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.items.Undefined;
import org.pepstock.charba.client.options.ScaleId;
import org.pepstock.charba.client.utils.Window;

/**
 * Base class to define an annotation in the {@link AnnotationPlugin#ID} plugin.<br>
 * It contains all commons properties to define an annotation.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public abstract class AbstractAnnotation extends AbstractNode implements IsDefaultsAnnotation, HasBorderOptions, HasShadowOptions, HasEventsHandler {

	/**
	 * Default annotation display, <b>{@value DEFAULT_DISPLAY}</b>.
	 */
	public static final boolean DEFAULT_DISPLAY = true;

	/**
	 * Default annotation adjust scale range, <b>{@value DEFAULT_ADJUST_SCALE_RANGE}</b>.
	 */
	public static final boolean DEFAULT_ADJUST_SCALE_RANGE = true;

	/**
	 * Default annotation rotation, <b>{@value DEFAULT_ROTATION}</b>.
	 */
	public static final double DEFAULT_ROTATION = 0D;

	// internal count
	private static final AtomicInteger COUNTER = new AtomicInteger(0);
	// exception pattern when the annotation default is wrong type
	static final String INVALID_DEFAULTS_VALUES_CLASS = "Defaults options are not invalid because is not a {0} annotation defaults";

	/**
	 * Name of properties of native object.
	 */
	enum Property implements Key
	{
		// read only
		TYPE("type"),
		ID("id"),
		// options
		ADJUST_SCALE_RANGE("adjustScaleRange"),
		DISPLAY("display"),
		// scales definitions
		X_SCALE_ID("xScaleID"),
		X_MIN("xMin"),
		X_MAX("xMax"),
		Y_SCALE_ID("yScaleID"),
		Y_MIN("yMin"),
		Y_MAX("yMax"),
		// internal property to set an unique id for caching
		CHARBA_ANNOTATION_ID("charbaAnnotationId");

		// name value of property
		private final String value;
		//

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
	// callback proxy to invoke the display function
	private final CallbackProxy<ProxyBooleanCallback> displayCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the draw time function
	private final CallbackProxy<ProxyStringCallback> drawTimeCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the adjust scale range function
	private final CallbackProxy<ProxyBooleanCallback> adjustScaleRangeCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the xMin function
	private final CallbackProxy<ProxyObjectCallback> xMinCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the xMax function
	private final CallbackProxy<ProxyObjectCallback> xMaxCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the yMin function
	private final CallbackProxy<ProxyObjectCallback> yMinCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the yMax function
	private final CallbackProxy<ProxyObjectCallback> yMaxCallbackProxy = JsHelper.get().newCallbackProxy();

	// callback instance to handle display options
	private static final CallbackPropertyHandler<SimpleDisplayCallback<AnnotationContext>> DISPLAY_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.DISPLAY);
	// callback instance to handle draw time options
	private static final CallbackPropertyHandler<DrawTimeCallback> DRAW_TIME_PROPERTY_HANDLER = new CallbackPropertyHandler<>(AnnotationOptions.Property.DRAW_TIME);
	// callback instance to handle adjust scale range options
	private static final CallbackPropertyHandler<AdjustScaleRangeCallback> ADJUST_SCALE_RANGE_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.ADJUST_SCALE_RANGE);
	// callback instance to handle xMin options
	private static final CallbackPropertyHandler<ValueCallback> X_MIN_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.X_MIN);
	// callback instance to handle xMax options
	private static final CallbackPropertyHandler<ValueCallback> X_MAX_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.X_MAX);
	// callback instance to handle yMin options
	private static final CallbackPropertyHandler<ValueCallback> Y_MIN_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.Y_MIN);
	// callback instance to handle yMax options
	private static final CallbackPropertyHandler<ValueCallback> Y_MAX_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.Y_MAX);

	// default values instance
	private final IsDefaultsAnnotation defaultValues;
	// border options handler
	private final BorderOptionsHandler borderOptionsHandler;
	// shadow options handler
	private final ShadowOptionsHandler shadowOptionsHandler;
	// event callbacks options handler
	private final EventsHandler eventsHandler;
	// draw time instance set at plugin startup
	private DrawTime parentDrawTime = null;

	/**
	 * Creates the object with the type of annotation to handle.
	 * 
	 * @param type annotation type
	 * @param id annotation id
	 * @param defaultValues default options instance
	 */
	AbstractAnnotation(AnnotationType type, AnnotationId id, IsDefaultsAnnotation defaultValues) {
		this(null, defaultValues);
		// checks if is is consistent
		AnnotationId.checkIfValid(id);
		// checks if type is consistent
		Key.checkIfValid(type);
		// stores id
		setValue(Property.ID, id);
		// stores type
		setValue(Property.TYPE, type);
		// stores the internal id for caching
		setValue(Property.CHARBA_ANNOTATION_ID, COUNTER.getAndIncrement());
		// stores incremental ID
		setNewIncrementalId();
		// cached it
		AnnotationHelper.get().addAnnotation(this);
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// sets function to proxy callback in order to invoke the java interface
		this.displayCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValue(new AnnotationContext(this, context), getDisplayCallback(), defaultValues.isDisplay()));
		// sets function to proxy callback in order to invoke the java interface
		this.drawTimeCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValue(new AnnotationContext(this, context), getDrawTimeCallback(), defaultValues.getDrawTime()).value());
		// sets function to proxy callback in order to invoke the java interface
		this.adjustScaleRangeCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValue(new AnnotationContext(this, context), getAdjustScaleRangeCallback(), defaultValues.isAdjustScaleRange()));
		// sets function to proxy callback in order to invoke the java interface
		this.xMinCallbackProxy.setCallback(context -> onValue(new AnnotationContext(this, context), getXMinCallback()));
		// sets function to proxy callback in order to invoke the java interface
		this.xMaxCallbackProxy.setCallback(context -> onValue(new AnnotationContext(this, context), getXMaxCallback()));
		// sets function to proxy callback in order to invoke the java interface
		this.yMinCallbackProxy.setCallback(context -> onValue(new AnnotationContext(this, context), getYMinCallback()));
		// sets function to proxy callback in order to invoke the java interface
		this.yMaxCallbackProxy.setCallback(context -> onValue(new AnnotationContext(this, context), getYMaxCallback()));
	}

	/**
	 * Creates the object wrapping an existing native object.
	 * 
	 * @param nativeObject native object to wrap
	 * @param defaultValues default options instance
	 */
	AbstractAnnotation(NativeObject nativeObject, IsDefaultsAnnotation defaultValues) {
		super(nativeObject);
		// checks if default value is consistent
		// stores default options
		this.defaultValues = checkDefaultValuesArgument(defaultValues);
		// loads border handler
		this.borderOptionsHandler = new BorderOptionsHandler(this, this.defaultValues, getNativeObject());
		// loads shadow handler
		this.shadowOptionsHandler = new ShadowOptionsHandler(this, this.defaultValues, getNativeObject());
		// loads events handler
		this.eventsHandler = new EventsHandler(this, this.defaultValues, getNativeObject());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.annotation.HasBorderOptions#getBorderOptionsHandler()
	 */
	@Override
	public final BorderOptionsHandler getBorderOptionsHandler() {
		return borderOptionsHandler;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.annotation.HasShadowOptions#getShadowOptionsHandler()
	 */
	@Override
	public final ShadowOptionsHandler getShadowOptionsHandler() {
		return shadowOptionsHandler;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.annotation.HasEventsHandler#getEventsHandler()
	 */
	@Override
	public final EventsHandler getEventsHandler() {
		return eventsHandler;
	}

	/**
	 * Returns the defaults values for this object.
	 * 
	 * @return the defaults values for this object
	 */
	final IsDefaultsAnnotation getDefaultsValues() {
		return defaultValues;
	}

	/**
	 * Returns the id of annotation for caching (internal).
	 * 
	 * @return the id of annotation for caching (internal)
	 */
	final int getAnnotationId() {
		return getValue(Property.CHARBA_ANNOTATION_ID, Undefined.INTEGER);
	}

	/**
	 * Returns the id of annotation.
	 * 
	 * @return the id of annotation
	 */
	public final AnnotationId getId() {
		return AnnotationId.create(getValue(Property.ID, Undefined.STRING));
	}

	/**
	 * Returns the type of annotation.
	 * 
	 * @return the type of annotation
	 */
	@Override
	public final AnnotationType getType() {
		return getValue(Property.TYPE, AnnotationType.values(), defaultValues.getType());
	}

	/**
	 * Sets <code>true</code> whether the annotation should be displayed.
	 * 
	 * @param display <code>true</code> whether the annotation should be displayed
	 */
	public final void setDisplay(boolean display) {
		// resets callback
		setDisplay((SimpleDisplayCallback<AnnotationContext>) null);
		// stores value
		setValue(Property.DISPLAY, display);
	}

	/**
	 * Returns <code>true</code> whether the annotation should be displayed.
	 * 
	 * @return <code>true</code> whether the annotation should be displayed
	 */
	@Override
	public final boolean isDisplay() {
		return getValue(Property.DISPLAY, defaultValues.isDisplay());
	}

	/**
	 * Sets <code>true</code> whether the scale range should be adjusted if this annotation is out of range.
	 * 
	 * @param display <code>true</code> whether the scale range should be adjusted if this annotation is out of range
	 */
	public final void setAdjustScaleRange(boolean display) {
		// resets callback
		setAdjustScaleRange((AdjustScaleRangeCallback) null);
		// stores value
		setValue(Property.ADJUST_SCALE_RANGE, display);
	}

	/**
	 * Returns <code>true</code> whether the scale range should be adjusted if this annotation is out of range.
	 * 
	 * @return <code>true</code> whether the scale range should be adjusted if this annotation is out of range
	 */
	@Override
	public final boolean isAdjustScaleRange() {
		return getValue(Property.ADJUST_SCALE_RANGE, defaultValues.isAdjustScaleRange());
	}

	/**
	 * Sets the draw time which defines when the annotations are drawn.
	 * 
	 * @param drawTime the draw time which defines when the annotations are drawn
	 */
	public final void setDrawTime(DrawTime drawTime) {
		// resets callback
		setDrawTime((DrawTimeCallback) null);
		// stores value
		setValue(AnnotationOptions.Property.DRAW_TIME, drawTime);
	}

	/**
	 * Returns the draw time which defines when the annotations are drawn.
	 * 
	 * @return the draw time which defines when the annotations are drawn
	 */
	@Override
	public final DrawTime getDrawTime() {
		return getValue(AnnotationOptions.Property.DRAW_TIME, DrawTime.values(), parentDrawTime != null ? parentDrawTime : defaultValues.getDrawTime());
	}

	/**
	 * Sets the ID of the X scale to bind onto.
	 * 
	 * @param scaleId the ID of the X scale to bind onto
	 */
	public final void setXScaleID(String scaleId) {
		// checks if scale id is valid
		ScaleId.checkIfValid(scaleId);
		// stores it
		setValue(Property.X_SCALE_ID, scaleId);
	}

	/**
	 * Sets the ID of the X scale to bind onto.
	 * 
	 * @param scaleId the ID of the X scale to bind onto
	 */
	public final void setXScaleID(ScaleId scaleId) {
		// checks if scale id is valid
		ScaleId.checkIfValid(scaleId);
		// stores it
		setValue(Property.X_SCALE_ID, scaleId);
	}

	/**
	 * Returns the ID of the X scale to bind onto.
	 * 
	 * @return the ID of the X scale to bind onto
	 */
	@Override
	public final ScaleId getXScaleID() {
		return getValue(Property.X_SCALE_ID, defaultValues.getXScaleID());
	}

	/**
	 * Sets the right edge of the annotation.
	 * 
	 * @param max the right edge of the annotation
	 */
	public final void setXMax(String max) {
		// resets callback
		setXMax((ValueCallback) null);
		// stores value
		setValue(Property.X_MAX, max);
	}

	/**
	 * Sets the right edge of the annotation.
	 * 
	 * @param max the right edge of the annotation
	 */
	public final void setXMax(double max) {
		// resets callback
		setXMax((ValueCallback) null);
		// stores value
		setValue(Property.X_MAX, max);
	}

	/**
	 * Sets the right edge of the annotation.
	 * 
	 * @param max the right edge of the annotation
	 */
	public final void setXMax(Date max) {
		// resets callback
		setXMax((ValueCallback) null);
		// stores value
		setValue(Property.X_MAX, max);
	}

	/**
	 * Returns the right edge of the annotation.
	 * 
	 * @return the right edge of the annotation
	 */
	@Override
	public final String getXMaxAsString() {
		return getValueForMultipleKeyTypes(Property.X_MAX, defaultValues.getXMaxAsString());
	}

	/**
	 * Returns the right edge of the annotation.
	 * 
	 * @return the right edge of the annotation
	 */
	@Override
	public final double getXMaxAsDouble() {
		return getValueForMultipleKeyTypes(Property.X_MAX, defaultValues.getXMaxAsDouble());
	}

	/**
	 * Returns the right edge of the annotation.
	 * 
	 * @return the right edge of the annotation
	 */
	@Override
	public final Date getXMaxAsDate() {
		return getValueForMultipleKeyTypes(Property.X_MAX, defaultValues.getXMaxAsDate());
	}

	/**
	 * Sets the left edge of the annotation, in units along the x axis.
	 * 
	 * @param min the left edge of the annotation
	 */
	public final void setXMin(String min) {
		// resets callback
		setXMin((ValueCallback) null);
		// stores value
		setValue(Property.X_MIN, min);
	}

	/**
	 * Sets the left edge of the annotation, in units along the x axis.
	 * 
	 * @param min the left edge of the annotation
	 */
	public final void setXMin(double min) {
		// resets callback
		setXMin((ValueCallback) null);
		// stores value
		setValue(Property.X_MIN, min);
	}

	/**
	 * Sets the left edge of the annotation, in units along the x axis.
	 * 
	 * @param min the left edge of the annotation
	 */
	public final void setXMin(Date min) {
		// resets callback
		setXMin((ValueCallback) null);
		// stores value
		setValue(Property.X_MIN, min);
	}

	/**
	 * Returns the left edge of the annotation, in units along the x axis.
	 * 
	 * @return the left edge of the annotation
	 */
	@Override
	public final String getXMinAsString() {
		return getValueForMultipleKeyTypes(Property.X_MIN, defaultValues.getXMinAsString());
	}

	/**
	 * Returns the left edge of the annotation, in units along the x axis.
	 * 
	 * @return the left edge of the annotation
	 */
	@Override
	public final double getXMinAsDouble() {
		return getValueForMultipleKeyTypes(Property.X_MIN, defaultValues.getXMinAsDouble());
	}

	/**
	 * Returns the left edge of the annotation, in units along the x axis.
	 * 
	 * @return the left edge of the annotation
	 */
	@Override
	public final Date getXMinAsDate() {
		return getValueForMultipleKeyTypes(Property.X_MIN, defaultValues.getXMinAsDate());
	}

	/**
	 * Sets the ID of the Y scale to bind onto.
	 * 
	 * @param scaleId the ID of the Y scale to bind onto
	 */
	public final void setYScaleID(String scaleId) {
		// checks if scale id is valid
		ScaleId.checkIfValid(scaleId);
		// stores it
		setValue(Property.Y_SCALE_ID, scaleId);
	}

	/**
	 * Sets the ID of the Y scale to bind onto.
	 * 
	 * @param scaleId the ID of the Y scale to bind onto
	 */
	public final void setYScaleID(ScaleId scaleId) {
		// checks if scale id is valid
		ScaleId.checkIfValid(scaleId);
		// stores it
		setValue(Property.Y_SCALE_ID, scaleId);
	}

	/**
	 * Returns the ID of the Y scale to bind onto.
	 * 
	 * @return the ID of the Y scale to bind onto
	 */
	@Override
	public final ScaleId getYScaleID() {
		return getValue(Property.Y_SCALE_ID, defaultValues.getYScaleID());
	}

	/**
	 * Sets the top edge of the annotation in units along the y axis.
	 * 
	 * @param max the top edge of the annotation in units along the y axis
	 */
	public final void setYMax(String max) {
		// resets callback
		setYMax((ValueCallback) null);
		// stores value
		setValue(Property.Y_MAX, max);
	}

	/**
	 * Sets the top edge of the annotation in units along the y axis.
	 * 
	 * @param max the top edge of the annotation in units along the y axis
	 */
	public final void setYMax(double max) {
		// resets callback
		setYMax((ValueCallback) null);
		// stores value
		setValue(Property.Y_MAX, max);
	}

	/**
	 * Sets the top edge of the annotation in units along the y axis.
	 * 
	 * @param max the top edge of the annotation in units along the y axis
	 */
	public final void setYMax(Date max) {
		// resets callback
		setYMax((ValueCallback) null);
		// stores value
		setValue(Property.Y_MAX, max);
	}

	/**
	 * Returns the top edge of the annotation in units along the y axis.
	 * 
	 * @return the top edge of the annotation in units along the y axis
	 */
	@Override
	public final String getYMaxAsString() {
		return getValueForMultipleKeyTypes(Property.Y_MAX, defaultValues.getYMaxAsString());
	}

	/**
	 * Returns the top edge of the annotation in units along the y axis.
	 * 
	 * @return the top edge of the annotation in units along the y axis
	 */
	@Override
	public final double getYMaxAsDouble() {
		return getValueForMultipleKeyTypes(Property.Y_MAX, defaultValues.getYMaxAsDouble());
	}

	/**
	 * Returns the top edge of the annotation in units along the y axis.
	 * 
	 * @return the top edge of the annotation in units along the y axis
	 */
	@Override
	public final Date getYMaxAsDate() {
		return getValueForMultipleKeyTypes(Property.Y_MAX, defaultValues.getYMaxAsDate());
	}

	/**
	 * Sets the bottom edge of the annotation.
	 * 
	 * @param min the bottom edge of the annotation
	 */
	public final void setYMin(String min) {
		// resets callback
		setYMin((ValueCallback) null);
		// stores value
		setValue(Property.Y_MIN, min);
	}

	/**
	 * Sets the bottom edge of the annotation.
	 * 
	 * @param min the bottom edge of the annotation
	 */
	public final void setYMin(double min) {
		// resets callback
		setYMin((ValueCallback) null);
		// stores value
		setValue(Property.Y_MIN, min);
	}

	/**
	 * Sets the bottom edge of the annotation.
	 * 
	 * @param min the bottom edge of the annotation
	 */
	public final void setYMin(Date min) {
		// resets callback
		setYMin((ValueCallback) null);
		// stores value
		setValue(Property.Y_MIN, min);
	}

	/**
	 * Returns the bottom edge of the annotation.
	 * 
	 * @return the bottom edge of the annotation
	 */
	@Override
	public final String getYMinAsString() {
		return getValueForMultipleKeyTypes(Property.Y_MIN, defaultValues.getYMinAsString());
	}

	/**
	 * Returns the bottom edge of the annotation.
	 * 
	 * @return the bottom edge of the annotation
	 */
	@Override
	public final double getYMinAsDouble() {
		return getValueForMultipleKeyTypes(Property.Y_MIN, defaultValues.getYMinAsDouble());
	}

	/**
	 * Returns the bottom edge of the annotation.
	 * 
	 * @return the bottom edge of the annotation
	 */
	@Override
	public final Date getYMinAsDate() {
		return getValueForMultipleKeyTypes(Property.Y_MIN, defaultValues.getYMinAsDate());
	}

	// ---------------------
	// CALLBACKS
	// ---------------------

	/**
	 * Returns the callback called to set the draw time which defines when the annotations are drawn.
	 * 
	 * @return the callback called to set the draw time which defines when the annotations are drawn
	 */
	@Override
	public final DrawTimeCallback getDrawTimeCallback() {
		return DRAW_TIME_PROPERTY_HANDLER.getCallback(this, defaultValues.getDrawTimeCallback());
	}

	/**
	 * Sets the callback to set the draw time which defines when the annotations are drawn.
	 * 
	 * @param drawTimeCallback to set the draw time which defines when the annotations are drawn
	 */
	public final void setDrawTime(DrawTimeCallback drawTimeCallback) {
		DRAW_TIME_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, drawTimeCallback, drawTimeCallbackProxy.getProxy());
	}

	/**
	 * Sets the callback to set the draw time which defines when the annotations are drawn.
	 * 
	 * @param drawTimeCallback to set the draw time which defines when the annotations are drawn
	 */
	public final void setDrawTime(NativeCallback drawTimeCallback) {
		// resets callback
		setDrawTime((DrawTimeCallback) null);
		// stores values
		setValueAndAddToParent(AnnotationOptions.Property.DRAW_TIME, drawTimeCallback);
	}

	/**
	 * Returns the callback called to set whether the annotation should be displayed.
	 * 
	 * @return the callback called to set whether the annotation should be displayed
	 */
	@Override
	public final SimpleDisplayCallback<AnnotationContext> getDisplayCallback() {
		return DISPLAY_PROPERTY_HANDLER.getCallback(this, defaultValues.getDisplayCallback());
	}

	/**
	 * Sets the callback to set whether the annotation should be displayed.
	 * 
	 * @param displayCallback to set whether the annotation should be displayed
	 */
	public final void setDisplay(SimpleDisplayCallback<AnnotationContext> displayCallback) {
		DISPLAY_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, displayCallback, displayCallbackProxy.getProxy());
	}

	/**
	 * Sets the callback to set whether the annotation should be displayed.
	 * 
	 * @param displayCallback to set whether the annotation should be displayed
	 */
	public final void setDisplay(NativeCallback displayCallback) {
		// resets callback
		setDisplay((SimpleDisplayCallback<AnnotationContext>) null);
		// stores values
		setValueAndAddToParent(Property.DISPLAY, displayCallback);
	}

	/**
	 * Returns the callback called to set whether the scale range should be adjusted if this annotation is out of range.
	 * 
	 * @return the callback called to set whether the scale range should be adjusted if this annotation is out of range
	 */
	@Override
	public final AdjustScaleRangeCallback getAdjustScaleRangeCallback() {
		return ADJUST_SCALE_RANGE_PROPERTY_HANDLER.getCallback(this, defaultValues.getAdjustScaleRangeCallback());
	}

	/**
	 * Sets the callback to set whether the scale range should be adjusted if this annotation is out of range.
	 * 
	 * @param adjustScaleRangeCallback to set whether the scale range should be adjusted if this annotation is out of range
	 */
	public final void setAdjustScaleRange(AdjustScaleRangeCallback adjustScaleRangeCallback) {
		ADJUST_SCALE_RANGE_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, adjustScaleRangeCallback, adjustScaleRangeCallbackProxy.getProxy());
	}

	/**
	 * Sets the callback to set whether the scale range should be adjusted if this annotation is out of range.
	 * 
	 * @param adjustScaleRangeCallback to set whether the scale range should be adjusted if this annotation is out of range
	 */
	public final void setAdjustScaleRange(NativeCallback adjustScaleRangeCallback) {
		// resets callback
		setAdjustScaleRange((AdjustScaleRangeCallback) null);
		// stores values
		setValueAndAddToParent(Property.ADJUST_SCALE_RANGE, adjustScaleRangeCallback);
	}

	/**
	 * Returns the callback called to set the left edge of the annotation, in units along the x axis.
	 * 
	 * @return the callback called to set the left edge of the annotation, in units along the x axis
	 */
	@Override
	public final ValueCallback getXMinCallback() {
		return X_MIN_PROPERTY_HANDLER.getCallback(this, defaultValues.getXMinCallback());
	}

	/**
	 * Sets the callback to set the left edge of the annotation, in units along the x axis.
	 * 
	 * @param valueCallback to set the left edge of the annotation, in units along the x axis
	 */
	public final void setXMin(ValueCallback valueCallback) {
		X_MIN_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, valueCallback, xMinCallbackProxy.getProxy());
	}

	/**
	 * Sets the callback to set the left edge of the annotation, in units along the x axis.
	 * 
	 * @param valueCallback to set the left edge of the annotation, in units along the x axis
	 */
	public final void setXMin(NativeCallback valueCallback) {
		// resets callback
		setXMin((ValueCallback) null);
		// stores values
		setValueAndAddToParent(Property.X_MIN, valueCallback);
	}

	/**
	 * Returns the callback called to set the right edge of the annotation.
	 * 
	 * @return the callback called to set the right edge of the annotation
	 */
	@Override
	public final ValueCallback getXMaxCallback() {
		return X_MAX_PROPERTY_HANDLER.getCallback(this, defaultValues.getXMaxCallback());
	}

	/**
	 * Sets the callback to set the right edge of the annotation.
	 * 
	 * @param valueCallback to set the right edge of the annotation
	 */
	public final void setXMax(ValueCallback valueCallback) {
		X_MAX_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, valueCallback, xMaxCallbackProxy.getProxy());
	}

	/**
	 * Sets the callback to set the right edge of the annotation.
	 * 
	 * @param valueCallback to set the right edge of the annotation
	 */
	public final void setXMax(NativeCallback valueCallback) {
		// resets callback
		setXMax((ValueCallback) null);
		// stores values
		setValueAndAddToParent(Property.X_MAX, valueCallback);
	}

	/**
	 * Returns the callback called to set the bottom edge of the annotation.
	 * 
	 * @return the callback called to set the bottom edge of the annotation
	 */
	@Override
	public final ValueCallback getYMinCallback() {
		return Y_MIN_PROPERTY_HANDLER.getCallback(this, defaultValues.getYMinCallback());
	}

	/**
	 * Sets the callback to set the bottom edge of the annotation.
	 * 
	 * @param valueCallback to set the bottom edge of the annotation
	 */
	public final void setYMin(ValueCallback valueCallback) {
		Y_MIN_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, valueCallback, yMinCallbackProxy.getProxy());
	}

	/**
	 * Sets the callback to set the bottom edge of the annotation.
	 * 
	 * @param valueCallback to set the bottom edge of the annotation
	 */
	public final void setYMin(NativeCallback valueCallback) {
		// resets callback
		setYMin((ValueCallback) null);
		// stores values
		setValueAndAddToParent(Property.Y_MIN, valueCallback);
	}

	/**
	 * Returns the callback called to set the top edge of the annotation in units along the y axis.
	 * 
	 * @return the callback called to set the top edge of the annotation in units along the y axis
	 */
	@Override
	public final ValueCallback getYMaxCallback() {
		return Y_MAX_PROPERTY_HANDLER.getCallback(this, defaultValues.getYMaxCallback());
	}

	/**
	 * Sets the callback to set the top edge of the annotation in units along the y axis.
	 * 
	 * @param valueCallback to set the top edge of the annotation in units along the y axis
	 */
	public final void setYMax(ValueCallback valueCallback) {
		Y_MAX_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, valueCallback, yMaxCallbackProxy.getProxy());
	}

	/**
	 * Sets the callback to set the top edge of the annotation in units along the y axis.
	 * 
	 * @param valueCallback to set the top edge of the annotation in units along the y axis
	 */
	public final void setYMax(NativeCallback valueCallback) {
		// resets callback
		setYMax((ValueCallback) null);
		// stores values
		setValueAndAddToParent(Property.Y_MAX, valueCallback);
	}

	// --------------------------
	// INTERNALS
	// --------------------------

	/**
	 * Returns an object as double, string or date (as time) when the callback has been activated.
	 * 
	 * @param context annotation context instance.
	 * @param valueCallback value callback instance
	 * @return an object as double, string or date
	 */
	final Object onValue(AnnotationContext context, ValueCallback valueCallback) {
		// gets value
		Object result = ScriptableUtil.getOptionValue(context, valueCallback);
		// checks if consistent
		if (result instanceof Number) {
			// casts to number
			Number number = (Number) result;
			// returns the number
			return number.doubleValue();
		} else if (result instanceof String) {
			// returns the string
			return result;
		} else if (result instanceof Date) {
			// casts to date
			Date date = (Date) result;
			// returns the number
			return (double) date.getTime();
		}
		// default result is undefined
		return Window.undefined();
	}

	/**
	 * Returns a native object of a {@link AlignPosition} when the callback has been activated.
	 * 
	 * @param context annotation context instance.
	 * @param callback callback instance
	 * @return a native object of a {@link AlignPosition}
	 */
	final NativeObject onPosition(AnnotationContext context, LabelAlignPositionCallback callback) {
		// prepares the result
		AlignPosition resultPosition = null;
		// gets value
		Object result = ScriptableUtil.getOptionValue(context, callback);
		// checks if consistent
		if (result instanceof AlignPosition) {
			// casts
			resultPosition = (AlignPosition) result;
		} else if (result instanceof LabelPosition) {
			// casts
			LabelPosition labelPosition = (LabelPosition) result;
			// create align position
			resultPosition = new AlignPosition(labelPosition);
		} else if (result instanceof Number) {
			// is a percentage
			// casts
			Number number = (Number) result;
			// create align position
			resultPosition = new AlignPosition(number.doubleValue());
		} else {
			// if here the result is not consistent
			// then returns the default
			resultPosition = new AlignPosition();
		}
		// returns the object
		return resultPosition.nativeObject();
	}

}
