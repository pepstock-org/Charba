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
import org.pepstock.charba.client.annotation.listeners.ClickCallback;
import org.pepstock.charba.client.annotation.listeners.DoubleClickCallback;
import org.pepstock.charba.client.annotation.listeners.EnterCallback;
import org.pepstock.charba.client.annotation.listeners.LeaveCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyHandlerEvent;
import org.pepstock.charba.client.commons.CallbackPropertyHandler;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.PropertyHandler;
import org.pepstock.charba.client.events.ChartEventContext;

/**
 * Base object to map the events callbacks options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class EventsHandler extends PropertyHandler<IsDefaultsEventsHandler> {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		ENTER("enter"),
		LEAVE("leave"),
		CLICK("click"),
		DOUBLE_CLICK("dblclick");

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
	// callback proxy to invoke the ENTER function
	private final CallbackProxy<ProxyHandlerEvent> enterCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the LEAVE function
	private final CallbackProxy<ProxyHandlerEvent> leaveCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the CLICK function
	private final CallbackProxy<ProxyHandlerEvent> clickCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the DBLCLICK function
	private final CallbackProxy<ProxyHandlerEvent> dblclickCallbackProxy = JsHelper.get().newCallbackProxy();

	// callback instance to handle click event
	private static final CallbackPropertyHandler<ClickCallback> CLICK_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.CLICK);
	// callback instance to handle enter event
	private static final CallbackPropertyHandler<EnterCallback> ENTER_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.ENTER);
	// callback instance to handle leave event
	private static final CallbackPropertyHandler<LeaveCallback> LEAVE_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.LEAVE);
	// callback instance to handle dblclick event
	private static final CallbackPropertyHandler<DoubleClickCallback> DOUBLE_CLICK_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.DOUBLE_CLICK);

	// the annotation options instance if the handlers is set at annotation plugin level
	private final AnnotationOptions commonParent;
	// the annotation options instance if the handlers is set at annotation element level
	private final AbstractAnnotation parent;

	/**
	 * Creates a events callbacks handler with the native object where events callbacks properties must be managed and the default value to use when the property does not exist.
	 * 
	 * @param parent model which contains the events callbacks handler.
	 * @param defaultValues default value of events callbacks to use when the properties do not exist
	 * @param nativeObject native object where events callbacks handler properties must be managed
	 */
	EventsHandler(AbstractAnnotation parent, IsDefaultsEventsHandler defaultValues, NativeObject nativeObject) {
		this(null, parent, defaultValues, nativeObject);
	}

	/**
	 * Creates a events callbacks handler with the native object where events callbacks properties must be managed and the default value to use when the property does not exist.
	 * 
	 * @param commonParent model which contains the events callbacks handler.
	 * @param defaultValues default value of events callbacks to use when the properties do not exist
	 * @param nativeObject native object where events callbacks handler properties must be managed
	 */
	EventsHandler(AnnotationOptions commonParent, IsDefaultsEventsHandler defaultValues, NativeObject nativeObject) {
		this(commonParent, null, defaultValues, nativeObject);
	}

	/**
	 * Creates a events callbacks handler with the native object where events callbacks properties must be managed and the default value to use when the property does not exist.
	 * 
	 * @param commonParent model which contains the events callbacks handler.
	 * @param parent model which contains the events callbacks handler.
	 * @param defaultValues default value of events callbacks to use when the properties do not exist
	 * @param nativeObject native object where events callbacks handler properties must be managed
	 */
	private EventsHandler(AnnotationOptions commonParent, AbstractAnnotation parent, IsDefaultsEventsHandler defaultValues, NativeObject nativeObject) {
		super(parent, defaultValues, nativeObject);
		// stores attribute
		this.parent = parent;
		this.commonParent = commonParent;
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
	 * Returns the callback called when a "enter" event is occurring.
	 * 
	 * @return the callback called when a "enter" event is occurring
	 */
	EnterCallback getEnterCallback() {
		return ENTER_PROPERTY_HANDLER.getCallback(this, getDefaultValues().getEnterCallback());
	}

	/**
	 * Sets the callback called when a "enter" event is occurring.
	 * 
	 * @param enterCallback the callback called when a "enter" event is occurring
	 */
	void setEnterCallback(EnterCallback enterCallback) {
		ENTER_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, enterCallback, enterCallbackProxy.getProxy());
	}

	/**
	 * Returns the callback called when a "leave" event is occurring.
	 * 
	 * @return the callback called when a "leave" event is occurring
	 */
	LeaveCallback getLeaveCallback() {
		return LEAVE_PROPERTY_HANDLER.getCallback(this, getDefaultValues().getLeaveCallback());
	}

	/**
	 * Sets the callback called when a "leave" event is occurring.
	 * 
	 * @param leaveCallback the callback called when a "leave" event is occurring
	 */
	void setLeaveCallback(LeaveCallback leaveCallback) {
		LEAVE_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, leaveCallback, leaveCallbackProxy.getProxy());
	}

	/**
	 * Returns the callback called when a "click" event is occurring.
	 * 
	 * @return the callback called when a "click" event is occurring
	 */
	ClickCallback getClickCallback() {
		return CLICK_PROPERTY_HANDLER.getCallback(this, getDefaultValues().getClickCallback());
	}

	/**
	 * Sets the callback called when a "click" event is occurring.
	 * 
	 * @param clickCallback the callback called when a "click" event is occurring
	 */
	void setClickCallback(ClickCallback clickCallback) {
		CLICK_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, clickCallback, clickCallbackProxy.getProxy());
	}

	/**
	 * Returns the callback called when a "dblclick" event is occurring.
	 * 
	 * @return the callback called when a "dblclick" event is occurring
	 */
	DoubleClickCallback getDoubleClickCallback() {
		return DOUBLE_CLICK_PROPERTY_HANDLER.getCallback(this, getDefaultValues().getDoubleClickCallback());
	}

	/**
	 * Sets the callback called when a "dblclick" event is occurring.
	 * 
	 * @param dblclickCallback the callback called when a "dblclick" event is occurring
	 */
	void setDoubleClickCallback(DoubleClickCallback dblclickCallback) {
		DOUBLE_CLICK_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, dblclickCallback, dblclickCallbackProxy.getProxy());
	}

	/**
	 * Returns the annotation context wrapper by the native context.
	 * 
	 * @param context native context, coming from JS annotation plugin
	 * @return the annotation context wrapper by the native context or <code>null</code> if the annotation id is not set
	 */
	private AnnotationContext getAnnotationContext(NativeObject context) {
		// checks if the handler has been set by an annotation element
		if (this.parent != null) {
			return new AnnotationContext(this.parent, context);
		}
		// if here, the handler has been set to the annotation common options
		// then gets the annotation ID from native object
		String annotationId = JsHelper.get().getStringProperty(AbstractAnnotation.Property.ID, context);
		// checks if ID is consistent
		if (annotationId != null && this.commonParent != null && this.commonParent.hasAnnotation(annotationId)) {
			// retrieves the annotation by id
			AbstractAnnotation annotation = this.commonParent.getAnnotation(annotationId);
			// creates context
			return new AnnotationContext(annotation, context);
		}
		// if here, the annotation element is not retrieved
		// the returns null
		return null;
	}

	/**
	 * Returns <code>true</code> if the context, inner chart and callbacks instance are consistent.
	 * 
	 * @param context callback annotation context instance
	 * @param callback user callback instance
	 * @return <code>true</code> if the context, inner chart and callbacks instance are consistent
	 */
	private boolean mustBeTriggered(AnnotationContext context, Object callback) {
		// checks if context is consistent
		if (context != null) {
			// gets chart instance from function context
			IsChart chart = context.getChart();
			// checks if chart and callback are consistent
			return IsChart.isValid(chart) && callback != null;
		}
		// if here, the context is not consistent
		return false;
	}

	/**
	 * Manages the ENTER event invoking the callback is exists.
	 * 
	 * @param context context instance
	 * @param event event instance
	 */
	private void onEnter(NativeObject context, NativeObject event) {
		// gets callback
		EnterCallback enterCallback = getEnterCallback();
		// creates a context wrapper
		AnnotationContext internalContext = getAnnotationContext(context);
		// checks if event must be triggered
		if (mustBeTriggered(internalContext, enterCallback)) {
			// creates a chart event context
			ChartEventContext eventContext = new ChartEventContext(new AnnotationEnvelop<>(event));
			// invokes callback
			enterCallback.onEnter(internalContext, eventContext);
		}
	}

	/**
	 * Manages the LEAVE event firing an annotation event.
	 * 
	 * @param context context instance
	 * @param event event instance
	 */
	private void onLeave(NativeObject context, NativeObject event) {
		// gets callback
		LeaveCallback leaveCallback = getLeaveCallback();
		// creates a context wrapper
		AnnotationContext internalContext = getAnnotationContext(context);
		// checks if event must be triggered
		if (mustBeTriggered(internalContext, leaveCallback)) {
			// creates a chart event context
			ChartEventContext eventContext = new ChartEventContext(new AnnotationEnvelop<>(event));
			// invokes callback
			leaveCallback.onLeave(internalContext, eventContext);
		}
	}

	/**
	 * Manages the CLICK event firing an annotation event.
	 * 
	 * @param context context instance
	 * @param event event instance
	 */
	private void onClick(NativeObject context, NativeObject event) {
		// gets callback
		ClickCallback clickCallback = getClickCallback();
		// creates a context wrapper
		AnnotationContext internalContext = getAnnotationContext(context);
		// checks if event must be triggered
		if (mustBeTriggered(internalContext, clickCallback)) {
			// creates a chart event context
			ChartEventContext eventContext = new ChartEventContext(new AnnotationEnvelop<>(event));
			// invokes callback
			clickCallback.onClick(internalContext, eventContext);
		}
	}

	/**
	 * Manages the DBLCLICK event firing an annotation event.
	 * 
	 * @param context context instance
	 * @param event event instance
	 */
	private void onDblclick(NativeObject context, NativeObject event) {
		// gets callback
		DoubleClickCallback dblclickCallback = getDoubleClickCallback();
		// creates a context wrapper
		AnnotationContext internalContext = getAnnotationContext(context);
		// checks if event must be triggered
		if (mustBeTriggered(internalContext, dblclickCallback)) {
			// creates a chart event context
			ChartEventContext eventContext = new ChartEventContext(new AnnotationEnvelop<>(event));
			// invokes callback
			dblclickCallback.onDoubleClick(internalContext, eventContext);
		}
	}

}