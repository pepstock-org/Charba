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
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.annotation.callbacks.AdjustScaleRangeCallback;
import org.pepstock.charba.client.annotation.callbacks.DrawTimeCallback;
import org.pepstock.charba.client.annotation.callbacks.ValueCallback;
import org.pepstock.charba.client.annotation.enums.DrawTime;
import org.pepstock.charba.client.annotation.listeners.ClickCallback;
import org.pepstock.charba.client.annotation.listeners.DoubleClickCallback;
import org.pepstock.charba.client.annotation.listeners.EnterCallback;
import org.pepstock.charba.client.annotation.listeners.LeaveCallback;
import org.pepstock.charba.client.callbacks.BorderDashCallback;
import org.pepstock.charba.client.callbacks.BorderDashOffsetCallback;
import org.pepstock.charba.client.callbacks.ColorCallback;
import org.pepstock.charba.client.callbacks.DisplayCallback;
import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyArrayCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyBooleanCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyDoubleCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyHandlerCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyIntegerCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyObjectCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyStringCallback;
import org.pepstock.charba.client.callbacks.ScriptableUtils;
import org.pepstock.charba.client.callbacks.WidthCallback;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.Array;
import org.pepstock.charba.client.commons.ArrayInteger;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.CallbackPropertyHandler;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.items.Undefined;
import org.pepstock.charba.client.utils.Window;

/**
 * Base class to define an annotation in the {@link AnnotationPlugin#ID} plugin.<br>
 * It contains all commons properties to define an annotation.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public abstract class AbstractAnnotation extends AbstractNode implements IsDefaultsAnnotation {

	/**
	 * Default annotation display, <b>{@value DEFAULT_DISPLAY}</b>.
	 */
	public static final boolean DEFAULT_DISPLAY = true;

	/**
	 * Default annotation adjust scale range, <b>{@value DEFAULT_ADJUST_SCALE_RANGE}</b>.
	 */
	public static final boolean DEFAULT_ADJUST_SCALE_RANGE = true;

	// internal count
	private static final AtomicInteger COUNTER = new AtomicInteger(0);
	// exception pattern when the scale or scales methods is invoked and the scale type is not correct
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
		BORDER_COLOR("borderColor"),
		BORDER_WIDTH("borderWidth"),
		BORDER_DASH("borderDash"),
		BORDER_DASH_OFFSET("borderDashOffset"),
		DISPLAY("display"),
		// events properties
		ENTER("enter"),
		LEAVE("leave"),
		CLICK("click"),
		DOUBLE_CLICK("dblclick"),
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
	// callback proxy to invoke the border color function
	private final CallbackProxy<ProxyObjectCallback> borderColorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the border width function
	private final CallbackProxy<ProxyIntegerCallback> borderWidthCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the border dash function
	private final CallbackProxy<ProxyArrayCallback> borderDashCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the border dash offset function
	private final CallbackProxy<ProxyDoubleCallback> borderDashOffsetCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the draw time function
	private final CallbackProxy<ProxyStringCallback> drawTimeCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the adjust scale range function
	private final CallbackProxy<ProxyBooleanCallback> adjustScaleRangeCallbackProxy = JsHelper.get().newCallbackProxy();

	// callback instance to handle display options
	private static final CallbackPropertyHandler<DisplayCallback<AnnotationContext>> DISPLAY_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.DISPLAY);
	// callback instance to handle border color options
	private static final CallbackPropertyHandler<ColorCallback<AnnotationContext>> BORDER_COLOR_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.BORDER_COLOR);
	// callback instance to handle border width options
	private static final CallbackPropertyHandler<WidthCallback<AnnotationContext>> BORDER_WIDTH_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.BORDER_WIDTH);
	// callback instance to handle border dash options
	private static final CallbackPropertyHandler<BorderDashCallback<AnnotationContext>> BORDER_DASH_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.BORDER_DASH);
	// callback instance to handle border dash offset options
	private static final CallbackPropertyHandler<BorderDashOffsetCallback<AnnotationContext>> BORDER_DASH_OFFSET_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.BORDER_DASH_OFFSET);
	// callback instance to handle draw time options
	private static final CallbackPropertyHandler<DrawTimeCallback> DRAW_TIME_PROPERTY_HANDLER = new CallbackPropertyHandler<>(AnnotationOptions.Property.DRAW_TIME);
	// callback instance to handle adjust scale range options
	private static final CallbackPropertyHandler<AdjustScaleRangeCallback> ADJUST_SCALE_RANGE_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.ADJUST_SCALE_RANGE);

	// ---------------------------
	// -- CALLBACKS PROXIES EVENTS
	// ---------------------------
	// callback proxy to invoke the ENTER function
	private final CallbackProxy<ProxyHandlerCallback> enterCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the LEAVE function
	private final CallbackProxy<ProxyHandlerCallback> leaveCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the CLICK function
	private final CallbackProxy<ProxyHandlerCallback> clickCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the DBLCLICK function
	private final CallbackProxy<ProxyHandlerCallback> dblclickCallbackProxy = JsHelper.get().newCallbackProxy();

	// callback instance to handle click event
	private static final CallbackPropertyHandler<ClickCallback> CLICK_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.CLICK);
	// callback instance to handle enter event
	private static final CallbackPropertyHandler<EnterCallback> ENTER_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.ENTER);
	// callback instance to handle leave event
	private static final CallbackPropertyHandler<LeaveCallback> LEAVE_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.LEAVE);
	// callback instance to handle dblclick event
	private static final CallbackPropertyHandler<DoubleClickCallback> DOUBLE_CLICK_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.DOUBLE_CLICK);

	private final IsDefaultsAnnotation defaultValues;
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
		this.displayCallbackProxy.setCallback(context -> ScriptableUtils.getOptionValue(new AnnotationContext(this, context), getDisplayCallback(), defaultValues.isDisplay()));
		// sets function to proxy callback in order to invoke the java interface
		this.borderColorCallbackProxy.setCallback(context -> ScriptableUtils.getOptionValueAsColor(new AnnotationContext(this, context), getBorderColorCallback(), defaultValues.getBorderColorAsString(), false));
		// sets function to proxy callback in order to invoke the java interface
		this.borderWidthCallbackProxy.setCallback(context -> ScriptableUtils.getOptionValue(new AnnotationContext(this, context), getBorderWidthCallback(), defaultValues.getBorderWidth()).intValue());
		// sets function to proxy callback in order to invoke the java interface
		this.borderDashCallbackProxy.setCallback(context -> onBorderDash(new AnnotationContext(this, context), getBorderDashCallback(), defaultValues.getBorderDash()));
		// sets function to proxy callback in order to invoke the java interface
		this.borderDashOffsetCallbackProxy.setCallback(context -> ScriptableUtils.getOptionValue(new AnnotationContext(this, context), getBorderDashOffsetCallback(), defaultValues.getBorderDashOffset()).doubleValue());
		// sets function to proxy callback in order to invoke the java interface
		this.drawTimeCallbackProxy.setCallback(context -> ScriptableUtils.getOptionValue(new AnnotationContext(this, context), getDrawTimeCallback(), defaultValues.getDrawTime()).value());
		// sets function to proxy callback in order to invoke the java interface
		this.adjustScaleRangeCallbackProxy.setCallback(context -> ScriptableUtils.getOptionValue(new AnnotationContext(this, context), getAdjustScaleRangeCallback(), defaultValues.isAdjustScaleRange()));
		// ------------------------------------------
		// -- SET CALLBACKS to PROXIES for EVENTs ---
		// ------------------------------------------
		// sets proxy handler to callback proxy to invoke the ENTER function
		this.enterCallbackProxy.setCallback(this::onEnter);
		// sets proxy handler to callback proxy to invoke the LEAVE function
		this.leaveCallbackProxy.setCallback(this::onLeave);
		// sets proxy handler to callback proxy to invoke the CLICK function
		this.clickCallbackProxy.setCallback(this::onClick);
		// sets proxy handler to callback proxy to invoke the DBLCLICK function
		this.dblclickCallbackProxy.setCallback(this::onDblclick);
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
		setDisplay((DisplayCallback<AnnotationContext>) null);
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
	 * Sets the draw time defined as default in the options from the parent.
	 * 
	 * @param parentDrawTime the draw time defined as default in the options from the parent
	 */
	final void setParentDrawTime(DrawTime parentDrawTime) {
		this.parentDrawTime = parentDrawTime;
		// checks if is line annotations to propagate to the label
		// and the draw current draw mustn't be set
		if (this instanceof LineAnnotation && isType(AnnotationOptions.Property.DRAW_TIME, ObjectType.UNDEFINED)) {
			// casts
			LineAnnotation line = (LineAnnotation) this;
			// stores the parent draw time
			line.getLabel().setParentDrawTime(parentDrawTime);
		}
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
		// checks if is line annotations to propagate to the label
		if (this instanceof LineAnnotation) {
			// casts
			LineAnnotation line = (LineAnnotation) this;
			// stores the parent draw time
			line.getLabel().setParentDrawTime(drawTime);
		}
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
	 * Sets the color of the border of annotation.
	 * 
	 * @param borderColor the color of the border of annotation
	 */
	public final void setBorderColor(IsColor borderColor) {
		setBorderColor(IsColor.checkAndGetValue(borderColor));
	}

	/**
	 * Sets the color of the border of annotation.
	 * 
	 * @param borderColor the color of the border of annotation
	 */
	public final void setBorderColor(String borderColor) {
		// resets callback
		setBorderColor((ColorCallback<AnnotationContext>) null);
		// stores value
		setValue(Property.BORDER_COLOR, borderColor);
	}

	/**
	 * Returns the color of the border of annotation.
	 * 
	 * @return the color of the border of annotation
	 */
	public final IsColor getBorderColor() {
		return ColorBuilder.parse(getBorderColorAsString());
	}

	/**
	 * Sets the width of the border in pixels.
	 * 
	 * @param borderWidth the width of the border in pixels.
	 */
	public final void setBorderWidth(int borderWidth) {
		// resets callback
		setBorderWidth((WidthCallback<AnnotationContext>) null);
		// stores value
		setValue(Property.BORDER_WIDTH, Checker.positiveOrZero(borderWidth));
	}

	/**
	 * Sets the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 * 
	 * @param borderDash the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 */
	public final void setBorderDash(int... borderDash) {
		// resets callback
		setBorderDash((BorderDashCallback<AnnotationContext>) null);
		// stores value
		setArrayValue(Property.BORDER_DASH, ArrayInteger.fromOrNull(borderDash));
	}

	/**
	 * Returns the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 * 
	 * @return the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 */
	@Override
	public final List<Integer> getBorderDash() {
		// checks if there is the property
		if (isType(Property.BORDER_DASH, ObjectType.ARRAY)) {
			// gets the array
			ArrayInteger array = getArrayValue(Property.BORDER_DASH);
			// and transforms to a list
			return ArrayListHelper.list(array);
		}
		// if here, the property is missing
		return defaultValues.getBorderDash();
	}

	/**
	 * Sets the line dash pattern offset.
	 * 
	 * @param borderDashOffset the line dash pattern offset.
	 */
	public final void setBorderDashOffset(double borderDashOffset) {
		// resets callback
		setBorderDashOffset((BorderDashOffsetCallback<AnnotationContext>) null);
		// stores value
		setValue(Property.BORDER_DASH_OFFSET, borderDashOffset);
	}

	/**
	 * Returns the line dash pattern offset.
	 * 
	 * @return the line dash pattern offset.
	 */
	@Override
	public final double getBorderDashOffset() {
		return getValue(Property.BORDER_DASH_OFFSET, defaultValues.getBorderDashOffset());
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
	public final DisplayCallback<AnnotationContext> getDisplayCallback() {
		return DISPLAY_PROPERTY_HANDLER.getCallback(this, defaultValues.getDisplayCallback());
	}

	/**
	 * Sets the callback to set whether the annotation should be displayed.
	 * 
	 * @param displayCallback to set whether the annotation should be displayed
	 */
	public final void setDisplay(DisplayCallback<AnnotationContext> displayCallback) {
		DISPLAY_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, displayCallback, displayCallbackProxy.getProxy());
	}

	/**
	 * Sets the callback to set whether the annotation should be displayed.
	 * 
	 * @param displayCallback to set whether the annotation should be displayed
	 */
	public final void setDisplay(NativeCallback displayCallback) {
		// resets callback
		setDisplay((DisplayCallback<AnnotationContext>) null);
		// stores values
		setValueAndAddToParent(Property.DISPLAY, displayCallback);
	}

	/**
	 * Returns the callback called to set the color of the border of annotation.
	 * 
	 * @return the callback called to set the color of the border of annotation
	 */
	@Override
	public final ColorCallback<AnnotationContext> getBorderColorCallback() {
		return BORDER_COLOR_PROPERTY_HANDLER.getCallback(this, defaultValues.getBorderColorCallback());
	}

	/**
	 * Sets the callback to set the color of the border of annotation.
	 * 
	 * @param borderColorCallback to set the color of the border of annotation
	 */
	public final void setBorderColor(ColorCallback<AnnotationContext> borderColorCallback) {
		BORDER_COLOR_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, borderColorCallback, borderColorCallbackProxy.getProxy());
	}

	/**
	 * Sets the callback to set the color of the border of annotation.
	 * 
	 * @param borderColorCallback to set the color of the border of annotation
	 */
	public final void setBorderColor(NativeCallback borderColorCallback) {
		// resets callback
		setBorderColor((ColorCallback<AnnotationContext>) null);
		// stores values
		setValueAndAddToParent(Property.BORDER_COLOR, borderColorCallback);
	}

	/**
	 * Returns the callback called to set the width of the border in pixels.
	 * 
	 * @return the callback called to set the width of the border in pixels
	 */
	@Override
	public final WidthCallback<AnnotationContext> getBorderWidthCallback() {
		return BORDER_WIDTH_PROPERTY_HANDLER.getCallback(this, defaultValues.getBorderWidthCallback());
	}

	/**
	 * Sets the callback to set the color of the width of the border in pixels.
	 * 
	 * @param borderWidthCallback to set the width of the border in pixels
	 */
	public final void setBorderWidth(WidthCallback<AnnotationContext> borderWidthCallback) {
		BORDER_WIDTH_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, borderWidthCallback, borderWidthCallbackProxy.getProxy());
	}

	/**
	 * Sets the callback to set the color of the width of the border in pixels.
	 * 
	 * @param borderWidthCallback to set the width of the border in pixels
	 */
	public final void setBorderWidth(NativeCallback borderWidthCallback) {
		// resets callback
		setBorderWidth((WidthCallback<AnnotationContext>) null);
		// stores values
		setValueAndAddToParent(Property.BORDER_WIDTH, borderWidthCallback);
	}

	/**
	 * Returns the callback called to set the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which
	 * describe the pattern.
	 * 
	 * @return the callback called to set the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which
	 *         describe the pattern
	 */
	@Override
	public final BorderDashCallback<AnnotationContext> getBorderDashCallback() {
		return BORDER_DASH_PROPERTY_HANDLER.getCallback(this, defaultValues.getBorderDashCallback());
	}

	/**
	 * Sets the callback to set the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the
	 * pattern.
	 * 
	 * @param borderDashCallback to set the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe
	 *            the pattern
	 */
	public final void setBorderDash(BorderDashCallback<AnnotationContext> borderDashCallback) {
		BORDER_DASH_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, borderDashCallback, borderDashCallbackProxy.getProxy());
	}

	/**
	 * Sets the callback to set the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the
	 * pattern.
	 * 
	 * @param borderDashCallback to set the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe
	 *            the pattern
	 */
	public final void setBorderDash(NativeCallback borderDashCallback) {
		// resets callback
		setBorderDash((BorderDashCallback<AnnotationContext>) null);
		// stores values
		setValueAndAddToParent(Property.BORDER_DASH, borderDashCallback);
	}

	/**
	 * Returns the callback called to set the line dash pattern offset.
	 * 
	 * @return the callback called to set the line dash pattern offset
	 */
	@Override
	public final BorderDashOffsetCallback<AnnotationContext> getBorderDashOffsetCallback() {
		return BORDER_DASH_OFFSET_PROPERTY_HANDLER.getCallback(this, defaultValues.getBorderDashOffsetCallback());
	}

	/**
	 * Sets the callback to set the line dash pattern offset.
	 * 
	 * @param borderDashOffsetCallback to set the line dash pattern offset
	 */
	public final void setBorderDashOffset(BorderDashOffsetCallback<AnnotationContext> borderDashOffsetCallback) {
		BORDER_DASH_OFFSET_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, borderDashOffsetCallback, borderDashOffsetCallbackProxy.getProxy());
	}

	/**
	 * Sets the callback to set the line dash pattern offset.
	 * 
	 * @param borderDashOffsetCallback to set the line dash pattern offset
	 */
	public final void setBorderDashOffset(NativeCallback borderDashOffsetCallback) {
		// resets callback
		setBorderDashOffset((BorderDashOffsetCallback<AnnotationContext>) null);
		// stores values
		setValueAndAddToParent(Property.BORDER_DASH_OFFSET, borderDashOffsetCallback);
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

	// ---------------------
	// EVENTS
	// ---------------------

	/**
	 * Returns the callback called when a "enter" event is occurring.
	 * 
	 * @return the callback called when a "enter" event is occurring
	 */
	@Override
	public final EnterCallback getEnterCallback() {
		return ENTER_PROPERTY_HANDLER.getCallback(this, defaultValues.getEnterCallback());
	}

	/**
	 * Sets the callback called when a "enter" event is occurring.
	 * 
	 * @param enterCallback the callback called when a "enter" event is occurring
	 */
	public final void setEnterCallback(EnterCallback enterCallback) {
		ENTER_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, enterCallback, enterCallbackProxy.getProxy());
	}

	/**
	 * Returns the callback called when a "leave" event is occurring.
	 * 
	 * @return the callback called when a "leave" event is occurring
	 */
	@Override
	public final LeaveCallback getLeaveCallback() {
		return LEAVE_PROPERTY_HANDLER.getCallback(this, defaultValues.getLeaveCallback());
	}

	/**
	 * Sets the callback called when a "leave" event is occurring.
	 * 
	 * @param leaveCallback the callback called when a "leave" event is occurring
	 */
	public final void setLeaveCallback(LeaveCallback leaveCallback) {
		LEAVE_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, leaveCallback, leaveCallbackProxy.getProxy());
	}

	/**
	 * Returns the callback called when a "click" event is occurring.
	 * 
	 * @return the callback called when a "click" event is occurring
	 */
	@Override
	public final ClickCallback getClickCallback() {
		return CLICK_PROPERTY_HANDLER.getCallback(this, defaultValues.getClickCallback());
	}

	/**
	 * Sets the callback called when a "click" event is occurring.
	 * 
	 * @param clickCallback the callback called when a "click" event is occurring
	 */
	public final void setClickCallback(ClickCallback clickCallback) {
		CLICK_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, clickCallback, clickCallbackProxy.getProxy());
	}

	/**
	 * Returns the callback called when a "dblclick" event is occurring.
	 * 
	 * @return the callback called when a "dblclick" event is occurring
	 */
	@Override
	public final DoubleClickCallback getDoubleClickCallback() {
		return DOUBLE_CLICK_PROPERTY_HANDLER.getCallback(this, defaultValues.getDoubleClickCallback());
	}

	/**
	 * Sets the callback called when a "dblclick" event is occurring.
	 * 
	 * @param dblclickCallback the callback called when a "dblclick" event is occurring
	 */
	public final void setDoubleClickCallback(DoubleClickCallback dblclickCallback) {
		DOUBLE_CLICK_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, dblclickCallback, dblclickCallbackProxy.getProxy());
	}

	/**
	 * Manages the ENTER event invoking the callback is exists.
	 * 
	 * @param context plugin context which contains the element
	 */
	private void onEnter(NativeObject context) {
		// gets callback
		EnterCallback enterCallback = getEnterCallback();
		// creates a context wrapper
		AnnotationContext internalContext = new AnnotationContext(this, context);
		// gets chart instance from function context
		IsChart chart = internalContext.getChart();
		// checks if chart, event and callback are consistent
		if (IsChart.isValid(chart) && enterCallback != null) {
			// invokes callback
			enterCallback.onEnter(chart, this);
		}
	}

	/**
	 * Manages the LEAVE event firing an annotation event.
	 * 
	 * @param context plugin context which contains the element
	 */
	private void onLeave(NativeObject context) {
		// gets callback
		LeaveCallback leaveCallback = getLeaveCallback();
		// creates a context wrapper
		AnnotationContext internalContext = new AnnotationContext(this, context);
		// gets chart instance from function context
		IsChart chart = internalContext.getChart();
		// checks if chart is consistent
		if (IsChart.isValid(chart) && leaveCallback != null) {
			// invokes callback
			leaveCallback.onLeave(chart, this);
		}
	}

	/**
	 * Manages the CLICK event firing an annotation event.
	 * 
	 * @param context plugin context which contains the element
	 */
	private void onClick(NativeObject context) {
		// gets callback
		ClickCallback clickCallback = getClickCallback();
		// creates a context wrapper
		AnnotationContext internalContext = new AnnotationContext(this, context);
		// gets chart instance from function context
		IsChart chart = internalContext.getChart();
		// checks if chart is consistent
		if (IsChart.isValid(chart) && clickCallback != null) {
			// invokes callback
			clickCallback.onClick(chart, this);
		}
	}

	/**
	 * Manages the DBLCLICK event firing an annotation event.
	 * 
	 * @param context plugin context which contains the element
	 */
	private void onDblclick(NativeObject context) {
		// gets callback
		DoubleClickCallback dblclickCallback = getDoubleClickCallback();
		// creates a context wrapper
		AnnotationContext internalContext = new AnnotationContext(this, context);
		// gets chart instance from function context
		IsChart chart = internalContext.getChart();
		// checks if chart is consistent
		if (IsChart.isValid(chart) && dblclickCallback != null) {
			// invokes callback
			dblclickCallback.onDoubleClick(chart, this);
		}
	}

	/**
	 * Returns an array of integer when the callback has been activated.
	 * 
	 * @param context native object as context.
	 * @param borderDashCallback border dash callback instance
	 * @param defaultValue default value of options
	 * @return an array of integer
	 */
	private Array onBorderDash(AnnotationContext context, BorderDashCallback<AnnotationContext> borderDashCallback, List<Integer> defaultValue) {
		// gets value
		List<Integer> result = ScriptableUtils.getOptionValue(context, borderDashCallback);
		// checks if consistent
		if (result != null) {
			// returns result of callback
			return ArrayInteger.fromOrEmpty(result);
		}
		// default result
		return ArrayInteger.fromOrEmpty(defaultValue);
	}

	/**
	 * Returns an object as double, string or date (as time) when the callback has been activated.
	 * 
	 * @param context native object as context.
	 * @param valueCallback border dash callback instance
	 * @return an object as double, string or date
	 */
	final Object onValue(AnnotationContext context, ValueCallback valueCallback) {
		// gets value
		Object result = ScriptableUtils.getOptionValue(context, valueCallback);
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

}
