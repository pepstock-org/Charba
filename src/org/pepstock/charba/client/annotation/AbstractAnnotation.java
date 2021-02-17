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

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.annotation.callbacks.DisplayCallback;
import org.pepstock.charba.client.annotation.enums.DrawTime;
import org.pepstock.charba.client.annotation.listeners.ClickCallback;
import org.pepstock.charba.client.annotation.listeners.DoubleClickCallback;
import org.pepstock.charba.client.annotation.listeners.EnterCallback;
import org.pepstock.charba.client.annotation.listeners.LeaveCallback;
import org.pepstock.charba.client.callbacks.CallbackFunctionContext;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.ArrayInteger;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.CallbackPropertyHandler;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.items.UndefinedValues;

import jsinterop.annotations.JsFunction;

/**
 * Base class to define an annotation into {@link AnnotationPlugin#ID} plugin.<br>
 * It contains all commons properties to define an annotation.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public abstract class AbstractAnnotation extends NativeObjectContainer implements IsDefaultsAnnotation {

	/**
	 * Default annotation display, <b>{@value DEFAULT_DISPLAY}</b>.
	 */
	public static final boolean DEFAULT_DISPLAY = true;
	// internal count
	private static final AtomicInteger COUNTER = new AtomicInteger(0);
	// constants for the scope of callback management
	static final String PLUGIN_SCOPE = "charbaAnnotationDefaultScope";
	// exception pattern when the scale or scales methods is invoked and the scale type is not correct
	static final String INVALID_DEFAULTS_VALUES_CLASS = "Defaults options are not invalid because is not a {0} annotation defaults";

	/**
	 * Java script FUNCTION callback called to provide events generated by annotations.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyHandlerEventsCallback {

		/**
		 * Method of function to be called to provide events generated by annotations.
		 * 
		 * @param functionContext context value of <code>this</code> to the execution context of function
		 * @param context plugin context which contains the element
		 */
		void call(CallbackFunctionContext functionContext, NativeObject context);
	}

	/**
	 * Java script FUNCTION callback called to provide the display options for the annotation.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyHandlerDisplayCallback {

		/**
		 * Method of function to be called to provide the display options for the annotation.
		 * 
		 * @param functionContext context value of <code>this</code> to the execution context of function
		 * @param context plugin context which contains the options
		 * @return <code>true</code> if the annotation must be shown, otherwise <code>false</code>
		 */
		boolean call(CallbackFunctionContext functionContext, NativeObject context);
	}

	/**
	 * Name of properties of native object.
	 */
	enum Property implements Key
	{
		TYPE("type"),
		ID("id"),
		DISPLAY("display"),
		BORDER_COLOR("borderColor"),
		BORDER_WIDTH("borderWidth"),
		BORDER_DASH("borderDash"),
		BORDER_DASH_OFFSET("borderDashOffset"),
		// events properties
		ENTER("enter"),
		LEAVE("leave"),
		CLICK("click"),
		DOUBLE_CLICK("dblclick"),
		// internal property to set an unique id for caching
		CHARBA_ANNOTATION_ID("_charbaAnnotationId");

		// name value of property
		private final String value;
		//

		/**
		 * Creates with the property value to use into native object.
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
	// callback proxy to invoke the ENTER function
	private final CallbackProxy<ProxyHandlerEventsCallback> enterCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the LEAVE function
	private final CallbackProxy<ProxyHandlerEventsCallback> leaveCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the CLICK function
	private final CallbackProxy<ProxyHandlerEventsCallback> clickCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the CLICK function
	private final CallbackProxy<ProxyHandlerEventsCallback> dblclickCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the display function
	private final CallbackProxy<ProxyHandlerDisplayCallback> displayCallbackProxy = JsHelper.get().newCallbackProxy();

	// callback instance to handle click event
	private static final CallbackPropertyHandler<ClickCallback> CLICK_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.CLICK);
	// callback instance to handle enter event
	private static final CallbackPropertyHandler<EnterCallback> ENTER_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.ENTER);
	// callback instance to handle leave event
	private static final CallbackPropertyHandler<LeaveCallback> LEAVE_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.LEAVE);
	// callback instance to handle dblclick event
	private static final CallbackPropertyHandler<DoubleClickCallback> DOUBLE_CLICK_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.DOUBLE_CLICK);
	// callback instance to handle display event
	private static final CallbackPropertyHandler<DisplayCallback> DISPLAY_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.DISPLAY);

	private final IsDefaultsAnnotation defaultValues;
	// draw time instance set at plugin startup
	private DrawTime defaultDrawTime = null;

	/**
	 * Creates the object with the type of annotation to handle.
	 * 
	 * @param type annotation type
	 * @param id annotation id
	 * @param defaultValues default options instance
	 */
	AbstractAnnotation(AnnotationType type, IsAnnotationId id, IsDefaultsAnnotation defaultValues) {
		this(null, defaultValues);
		// checks if is is consistent
		IsAnnotationId.checkIfValid(id);
		// checks if type is consistent
		Key.checkIfValid(type);
		// stores id
		setValue(Property.ID, id);
		// stores type
		setValue(Property.TYPE, type);
		// stores the internal id for caching
		setValue(Property.CHARBA_ANNOTATION_ID, COUNTER.getAndIncrement());
		// cached it
		AnnotationHelper.get().addAnnotation(this);
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// sets proxy handler to callback proxy to invoke the ENTER function
		enterCallbackProxy.setCallback(this::onEnter);
		// sets proxy handler to callback proxy to invoke the LEAVE function
		leaveCallbackProxy.setCallback(this::onLeave);
		// sets proxy handler to callback proxy to invoke the CLICK function
		clickCallbackProxy.setCallback(this::onClick);
		// sets proxy handler to callback proxy to invoke the DBLCLICK function
		dblclickCallbackProxy.setCallback(this::onDblclick);
		// sets proxy handler to callback proxy to invoke the display function
		displayCallbackProxy.setCallback(this::onDisplay);
	}

	/**
	 * Creates the object wrapping an existing native object.
	 * 
	 * @param nativeObject native object to wrap
	 * @param defaultValues default options instance
	 */
	AbstractAnnotation(NativeObject nativeObject, IsDefaultsAnnotation defaultValues) {
		super(nativeObject);
		// redefine hash code
		super.redefineHashcode();
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
		return getValue(Property.CHARBA_ANNOTATION_ID, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the id of annotation.
	 * 
	 * @return the id of annotation
	 */
	public final IsAnnotationId getId() {
		return IsAnnotationId.create(getValue(Property.ID, UndefinedValues.STRING));
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
	 * @param enabled <code>true</code> whether the annotation should be displayed
	 */
	public final void setDisplay(boolean enabled) {
		setValue(Property.DISPLAY, enabled);
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
	 * Sets the draw time defined as default into options as top level.
	 * 
	 * @param drawTime the draw time defined as default into options as top level
	 */
	final void setDefaultDrawTime(DrawTime drawTime) {
		this.defaultDrawTime = drawTime;
	}

	/**
	 * Sets the draw time which defines when the annotations are drawn.
	 * 
	 * @param drawTime the draw time which defines when the annotations are drawn
	 */
	public final void setDrawTime(DrawTime drawTime) {
		setValue(AnnotationOptions.Property.DRAW_TIME, drawTime);
	}

	/**
	 * Returns the draw time which defines when the annotations are drawn.
	 * 
	 * @return the draw time which defines when the annotations are drawn
	 */
	@Override
	public final DrawTime getDrawTime() {
		return getValue(AnnotationOptions.Property.DRAW_TIME, DrawTime.values(), defaultDrawTime != null ? defaultDrawTime : defaultValues.getDrawTime());
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
		setValue(Property.BORDER_WIDTH, borderWidth);
	}

	/**
	 * Sets the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 * 
	 * @param borderDash the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 */
	public void setBorderDash(int... borderDash) {
		setArrayValue(Property.BORDER_DASH, ArrayInteger.fromOrNull(borderDash));
	}

	/**
	 * Returns the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 * 
	 * @return the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 */
	@Override
	public List<Integer> getBorderDash() {
		// checks if there is the property
		if (has(Property.BORDER_DASH)) {
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
	public void setBorderDashOffset(double borderDashOffset) {
		setValue(Property.BORDER_DASH_OFFSET, borderDashOffset);
	}

	/**
	 * Returns the line dash pattern offset.
	 * 
	 * @return the line dash pattern offset.
	 */
	@Override
	public double getBorderDashOffset() {
		return getValue(Property.BORDER_DASH_OFFSET, defaultValues.getBorderDashOffset());
	}

	/**
	 * Returns the callback called to set the display option at runtime.
	 * 
	 * @return the callback called to set the display option at runtime
	 */
	@Override
	public final DisplayCallback getDisplayCallback() {
		return DISPLAY_PROPERTY_HANDLER.getCallback(this, defaultValues.getDisplayCallback());
	}

	/**
	 * Sets to set the display option at runtime.
	 * 
	 * @param displayCallback to set the display option at runtime
	 */
	public final void setDisplayCallback(DisplayCallback displayCallback) {
		DISPLAY_PROPERTY_HANDLER.setCallback(this, PLUGIN_SCOPE, displayCallback, displayCallbackProxy.getProxy());
	}

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
		ENTER_PROPERTY_HANDLER.setCallback(this, PLUGIN_SCOPE, enterCallback, enterCallbackProxy.getProxy());
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
		LEAVE_PROPERTY_HANDLER.setCallback(this, PLUGIN_SCOPE, leaveCallback, leaveCallbackProxy.getProxy());
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
		CLICK_PROPERTY_HANDLER.setCallback(this, PLUGIN_SCOPE, clickCallback, clickCallbackProxy.getProxy());
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
		DOUBLE_CLICK_PROPERTY_HANDLER.setCallback(this, PLUGIN_SCOPE, dblclickCallback, dblclickCallbackProxy.getProxy());
	}

	/**
	 * Manages the ENTER event invoking the callback is exists.
	 * 
	 * @param functionContext context value of <code>this</code> to the execution context of function
	 * @param context plugin context which contains teh element
	 */
	private void onEnter(CallbackFunctionContext functionContext, NativeObject context) {
		// gets callback
		EnterCallback enterCallback = ENTER_PROPERTY_HANDLER.getCallback(this);
		// creates a context wrapper
		Context internalContext = new Context(context);
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
	 * @param functionContext context value of <code>this</code> to the execution context of function
	 * @param context plugin context which contains teh element
	 */
	private void onLeave(CallbackFunctionContext functionContext, NativeObject context) {
		// gets callback
		LeaveCallback leaveCallback = LEAVE_PROPERTY_HANDLER.getCallback(this);
		// creates a context wrapper
		Context internalContext = new Context(context);
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
	 * @param functionContext context value of <code>this</code> to the execution context of function
	 * @param context plugin context which contains the element
	 */
	private void onClick(CallbackFunctionContext functionContext, NativeObject context) {
		// gets callback
		ClickCallback clickCallback = CLICK_PROPERTY_HANDLER.getCallback(this);
		// creates a context wrapper
		Context internalContext = new Context(context);
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
	 * @param functionContext context value of <code>this</code> to the execution context of function
	 * @param context plugin context which contains the element
	 */
	private void onDblclick(CallbackFunctionContext functionContext, NativeObject context) {
		// gets callback
		DoubleClickCallback dblclickCallback = DOUBLE_CLICK_PROPERTY_HANDLER.getCallback(this);
		// creates a context wrapper
		Context internalContext = new Context(context);
		// gets chart instance from function context
		IsChart chart = internalContext.getChart();
		// checks if chart is consistent
		if (IsChart.isValid(chart) && dblclickCallback != null) {
			// invokes callback
			dblclickCallback.onDoubleClick(chart, this);
		}
	}

	/**
	 * Manages the DISPLAY option in an annotation.
	 * 
	 * @param functionContext context value of <code>this</code> to the execution context of function
	 * @param context plugin context which contains the options
	 * @return <code>true</code> if the annotation must be shown, otherwise <code>false</code>
	 */
	private boolean onDisplay(CallbackFunctionContext functionContext, NativeObject context) {
		// gets callback
		DisplayCallback displayCallback = DISPLAY_PROPERTY_HANDLER.getCallback(this);
		// creates a context wrapper
		Context internalContext = new Context(context);
		// gets chart instance from function context
		IsChart chart = internalContext.getChart();
		// checks if chart is consistent
		if (IsChart.isValid(chart) && displayCallback != null) {
			// invokes callback
			return displayCallback.invoke(chart, this);
		}
		// if here, returns the default
		return defaultValues.isDisplay();
	}

}
