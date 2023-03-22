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
import java.util.concurrent.atomic.AtomicInteger;

import org.pepstock.charba.client.Chart;
import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.annotation.callbacks.AdjustScaleRangeCallback;
import org.pepstock.charba.client.annotation.callbacks.DrawTimeCallback;
import org.pepstock.charba.client.annotation.callbacks.InitCallback;
import org.pepstock.charba.client.annotation.callbacks.LabelAlignPositionCallback;
import org.pepstock.charba.client.annotation.callbacks.ValueCallback;
import org.pepstock.charba.client.annotation.callbacks.ZCallback;
import org.pepstock.charba.client.annotation.elements.AnnotationElement;
import org.pepstock.charba.client.annotation.elements.AnnotationProperties;
import org.pepstock.charba.client.annotation.enums.DrawTime;
import org.pepstock.charba.client.annotation.enums.LabelPosition;
import org.pepstock.charba.client.annotation.listeners.ElementHookCallback;
import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyBooleanCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyHandlerCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyIntegerCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyObjectCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyStringCallback;
import org.pepstock.charba.client.callbacks.ScriptableIntegerChecker;
import org.pepstock.charba.client.callbacks.ScriptableUtil;
import org.pepstock.charba.client.callbacks.SimpleDisplayCallback;
import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.CallbackPropertyHandler;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.commons.ObjectType;
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
	 * Default annotation animation initialization, <b>{@value DEFAULT_INIT}</b>.
	 */
	public static final boolean DEFAULT_INIT = false;

	/**
	 * Default annotation adjust scale range, <b>{@value DEFAULT_ADJUST_SCALE_RANGE}</b>.
	 */
	public static final boolean DEFAULT_ADJUST_SCALE_RANGE = true;

	/**
	 * Default annotation rotation, <b>{@value DEFAULT_ROTATION}</b>.
	 */
	public static final double DEFAULT_ROTATION = 0D;

	/**
	 * Default annotation Z, <b>{@value DEFAULT_Z}</b>.
	 */
	public static final int DEFAULT_Z = 0;

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
		INIT("init"),
		// scales definitions
		X_SCALE_ID("xScaleID"),
		X_MIN("xMin"),
		X_MAX("xMax"),
		Y_SCALE_ID("yScaleID"),
		Y_MIN("yMin"),
		Y_MAX("yMax"),
		Z("z"),
		// hooks
		BEFORE_DRAW("beforeDraw"),
		AFTER_DRAW("afterDraw"),
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
	// callback proxy to invoke the z function
	private final CallbackProxy<ProxyIntegerCallback> zCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the before draw function
	private final CallbackProxy<ProxyHandlerCallback> beforeDrawCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the after draw function
	private final CallbackProxy<ProxyHandlerCallback> afterDrawCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the init function
	private final CallbackProxy<ProxyObjectCallback> initCallbackProxy = JsHelper.get().newCallbackProxy();

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
	// callback instance to handle z options
	private static final CallbackPropertyHandler<ZCallback> Z_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.Z);
	// callback instance to handle before draw options
	private static final CallbackPropertyHandler<ElementHookCallback> BEFORE_DRAW_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.BEFORE_DRAW);
	// callback instance to handle after draw options
	private static final CallbackPropertyHandler<ElementHookCallback> AFTER_DRAW_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.AFTER_DRAW);
	// callback instance to handle init options
	private static final CallbackPropertyHandler<InitCallback> INIT_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.INIT);

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
		// sets function to proxy callback in order to invoke the java interface
		this.zCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValueAsNumber(new AnnotationContext(this, context), getZCallback(), defaultValues.getZ(), ScriptableIntegerChecker.VALID_OR_DEFAULT).intValue());
		// sets function to proxy callback in order to invoke the java interface
		this.beforeDrawCallbackProxy.setCallback(context -> onDraw(new AnnotationContext(this, context), getBeforeDrawCallback()));
		// sets function to proxy callback in order to invoke the java interface
		this.afterDrawCallbackProxy.setCallback(context -> onDraw(new AnnotationContext(this, context), getAfterDrawCallback()));
		// sets function to proxy callback in order to invoke the java interface
		this.initCallbackProxy.setCallback(this::onInit);
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
	 * Sets <code>true</code> to enable the animation to the annotations when they are drawing at chart initialization.
	 * 
	 * @param init <code>true</code> to enable the animation to the annotations when they are drawing at chart initialization
	 */
	public final void setInit(boolean init) {
		// resets callback
		setInit((InitCallback) null);
		// stores value
		setValue(Property.INIT, init);
	}

	/**
	 * Returns <code>true</code> to enable the animation to the annotations when they are drawing at chart initialization.
	 * 
	 * @return <code>true</code> to enable the animation to the annotations when they are drawing at chart initialization.
	 */
	@Override
	public final boolean isInit() {
		return isType(Property.INIT, ObjectType.FUNCTION) || getValue(Property.INIT, defaultValues.isInit());
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
	 * Sets the property determines the drawing stack level of the box annotation element.<br>
	 * All visible elements will be drawn in ascending order of `z` option, with the same "drawTime" option.
	 * 
	 * @param z the property determines the drawing stack level of the box annotation element.<br>
	 *            All visible elements will be drawn in ascending order of `z` option, with the same "drawTime" option.
	 */
	public final void setZ(int z) {
		// resets callback
		setZ((ZCallback) null);
		// stores value
		setValue(Property.Z, z);
	}

	/**
	 * Returns the property determines the drawing stack level of the box annotation element.<br>
	 * All visible elements will be drawn in ascending order of `z` option, with the same "drawTime" option.
	 * 
	 * @return the property determines the drawing stack level of the box annotation element.<br>
	 *         All visible elements will be drawn in ascending order of `z` option, with the same "drawTime" option.
	 */
	@Override
	public final int getZ() {
		return getValue(Property.Z, defaultValues.getZ());
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
	 * Returns the callback called to set the property determines the drawing stack level of the box annotation element.
	 * 
	 * @return the callback called to set the property determines the drawing stack level of the box annotation element
	 */
	@Override
	public final ZCallback getZCallback() {
		return Z_PROPERTY_HANDLER.getCallback(this, defaultValues.getZCallback());
	}

	/**
	 * Sets the callback called to set the property determines the drawing stack level of the box annotation element.
	 * 
	 * @param zCallback to set the property determines the drawing stack level of the box annotation element
	 */
	public final void setZ(ZCallback zCallback) {
		Z_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, zCallback, zCallbackProxy.getProxy());
	}

	/**
	 * Sets the callback called to set the property determines the drawing stack level of the box annotation element.
	 * 
	 * @param zCallback to set the property determines the drawing stack level of the box annotation element
	 */
	public final void setZ(NativeCallback zCallback) {
		// resets callback
		setZ((ZCallback) null);
		// stores values
		setValueAndAddToParent(Property.Z, zCallback);
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
	// HOOKS
	// --------------------------

	/**
	 * Returns the callback called to get the control before the annotation element drawing.
	 * 
	 * @return the callback called to get the control before the annotation element drawing
	 */
	@Override
	public final ElementHookCallback getBeforeDrawCallback() {
		return BEFORE_DRAW_PROPERTY_HANDLER.getCallback(this, defaultValues.getBeforeDrawCallback());
	}

	/**
	 * Sets the callback to get the control before the annotation element drawing.
	 * 
	 * @param beforeDrawCallback to get the control before the annotation element drawing
	 */
	public final void setBeforeDraw(ElementHookCallback beforeDrawCallback) {
		BEFORE_DRAW_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, beforeDrawCallback, beforeDrawCallbackProxy.getProxy());
	}

	/**
	 * Sets the callback to get the control before the annotation element drawing.
	 * 
	 * @param beforeDrawCallback to get the control before the annotation element drawing
	 */
	public final void setBeforeDraw(NativeCallback beforeDrawCallback) {
		// resets callback
		setBeforeDraw((ElementHookCallback) null);
		// stores beforeDraws
		setValueAndAddToParent(Property.BEFORE_DRAW, beforeDrawCallback);
	}

	/**
	 * Returns the callback called to get the control after the annotation element drawing.
	 * 
	 * @return the callback called to get the control after the annotation element drawing
	 */
	@Override
	public final ElementHookCallback getAfterDrawCallback() {
		return AFTER_DRAW_PROPERTY_HANDLER.getCallback(this, defaultValues.getAfterDrawCallback());
	}

	/**
	 * Sets the callback to get the control after the annotation element drawing.
	 * 
	 * @param afterDrawCallback to get the control after the annotation element drawing
	 */
	public final void setAfterDraw(ElementHookCallback afterDrawCallback) {
		AFTER_DRAW_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, afterDrawCallback, afterDrawCallbackProxy.getProxy());
	}

	/**
	 * Sets the callback to get the control after the annotation element drawing.
	 * 
	 * @param afterDrawCallback to get the control after the annotation element drawing
	 */
	public final void setAfterDraw(NativeCallback afterDrawCallback) {
		// resets callback
		setAfterDraw((ElementHookCallback) null);
		// stores afterDraws
		setValueAndAddToParent(Property.AFTER_DRAW, afterDrawCallback);
	}

	/**
	 * Returns the callback called to get the control after the annotation element drawing.
	 * 
	 * @return the callback called to get the control after the annotation element drawing
	 */
	@Override
	public final InitCallback getInitCallback() {
		return INIT_PROPERTY_HANDLER.getCallback(this, defaultValues.getInitCallback());
	}

	/**
	 * Sets the callback to get the control after the annotation element drawing.
	 * 
	 * @param initCallback to get the control after the annotation element drawing
	 */
	public final void setInit(InitCallback initCallback) {
		INIT_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, initCallback, initCallbackProxy.getProxy());
	}

	/**
	 * Sets the callback to get the control after the annotation element drawing.
	 * 
	 * @param initCallback to get the control after the annotation element drawing
	 */
	public final void setInit(NativeCallback initCallback) {
		// resets callback
		setInit((InitCallback) null);
		// stores inits
		setValueAndAddToParent(Property.INIT, initCallback);
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

	/**
	 * Invoked before and after drawing of annotation element.
	 * 
	 * @param context annotation context instance
	 * @param hookCallback value callback instance
	 */
	private void onDraw(AnnotationContext context, ElementHookCallback hookCallback) {
		// checks if callback is consistent
		if (hookCallback != null) {
			// invokes callback
			hookCallback.invoke(context);
		}
	}

	/**
	 * Returns an object as boolean or {@link AnnotationElement} when the callback has been activated.
	 * 
	 * @param context context with chart, properties and options
	 * @return an object as boolean or {@link AnnotationElement} when the callback has been activated
	 */
	final Object onInit(NativeObject context) {
		// gets init callback
		InitCallback callback = getInitCallback();
		// checks if callback is consistent
		if (callback != null) {
			// wraps context
			InitContextWrapper wrapper = new InitContextWrapper(context);
			// gets chart
			IsChart chart = wrapper.getChart();
			// checks if chart is consistent
			if (IsChart.isValid(chart)) {
				// gets element
				AnnotationProperties properties = wrapper.getProperties();
				if (properties != null) {
					// invokes callback
					Object result = callback.invoke(chart, properties, this);
					// checks result of callback
					if (result instanceof Boolean) {
						// cats to boolean
						Boolean value = (Boolean) result;
						// returns value
						return value.booleanValue();
					} else if (result instanceof AnnotationProperties) {
						// cats to annotation element
						AnnotationProperties value = (AnnotationProperties) result;
						// returns value
						return value.nativeObject();
					}
				}
			}
		}
		// if here, the callback or the context are not consistent
		// then returns default
		return DEFAULT_INIT;
	}

	/**
	 * Wrapper of init callback context passed by plugin.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class InitContextWrapper extends NativeObjectContainer {

		/**
		 * Properties names of the context
		 */
		private enum InnerProperty implements Key
		{
			CHART("chart"),
			PROPERTIES("properties");

			// name value of property
			private final String value;
			//

			/**
			 * Creates with the property value to use in the native object.
			 * 
			 * @param value value of property name
			 */
			private InnerProperty(String value) {
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

		/**
		 * Creates the object with native object instance to be wrapped.
		 * 
		 * @param nativeObject native object instance to be wrapped.
		 */
		private InitContextWrapper(NativeObject nativeObject) {
			super(nativeObject);
		}

		/**
		 * Returns a value (chart) in the embedded JavaScript object at specific property.
		 * 
		 * @param key key of the property of JavaScript object.
		 * @return value of the property
		 */
		private IsChart getChart() {
			// gets native chart
			Chart nativeChart = getNativeChart(InnerProperty.CHART);
			// checks chart consistency
			if (nativeChart != null) {
				// returns chart instance
				return nativeChart.getChart();
			}
			// if chart is not consistent
			// the returns null
			return null;
		}

		/**
		 * Returns a value (annotation properties) in the embedded JavaScript object at specific property.
		 * 
		 * @param key key of the property of JavaScript object.
		 * @return value of the property
		 */
		private AnnotationProperties getProperties() {
			// gets properties
			NativeObject properties = getValue(InnerProperty.PROPERTIES);
			// checks properties consistency
			if (properties != null) {
				// returns annotation element
				return new AnnotationProperties(new AnnotationEnvelop<>(properties, true));
			}
			// if properties are not consistent
			// the returns null
			return null;
		}

	}

}