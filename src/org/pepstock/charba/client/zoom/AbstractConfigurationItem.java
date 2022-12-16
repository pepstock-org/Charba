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
package org.pepstock.charba.client.zoom;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyBooleanCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyHandlerCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyStringCallback;
import org.pepstock.charba.client.callbacks.ScriptableUtil;
import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.CallbackPropertyHandler;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.zoom.callbacks.CompletedCallback;
import org.pepstock.charba.client.zoom.callbacks.ModeCallback;
import org.pepstock.charba.client.zoom.callbacks.ProgressCallback;
import org.pepstock.charba.client.zoom.callbacks.RejectedCallback;
import org.pepstock.charba.client.zoom.callbacks.StartCallback;
import org.pepstock.charba.client.zoom.enums.Mode;

/**
 * Abstract element used by pan and zoom object in order to enable to provide the configuration of {@link ZoomPlugin#ID}.
 * 
 * @author Andrea "Stock" Stocchero
 */
public abstract class AbstractConfigurationItem extends AbstractNode implements IsDefaultConfigurationItem {

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the MODE function
	private final CallbackProxy<ProxyStringCallback> modeCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the MODE function
	private final CallbackProxy<ProxyStringCallback> scaleModeCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the MODE function
	private final CallbackProxy<ProxyStringCallback> overScaleModeCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the PROGRESS function
	private final CallbackProxy<ProxyHandlerCallback> progressCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the COMPLETED function
	private final CallbackProxy<ProxyHandlerCallback> completeCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the REJECTED function
	private final CallbackProxy<ProxyHandlerCallback> rejectCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the START function
	private final CallbackProxy<ProxyBooleanCallback> startCallbackProxy = JsHelper.get().newCallbackProxy();

	// mode callback
	private static final CallbackPropertyHandler<ModeCallback> MODE_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.MODE);
	// scale mode callback
	private static final CallbackPropertyHandler<ModeCallback> SCALE_MODE_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.SCALE_MODE);
	// over scale mode callback
	private static final CallbackPropertyHandler<ModeCallback> OVER_SCALE_MODE_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.OVER_SCALE_MODE);

	/**
	 * Default mode directions, <b>{@link Mode#XY}</b>.
	 */
	public static final Mode DEFAULT_MODE = Mode.XY;

	/**
	 * Default mode directions for the scale, <b>{@link Mode#XY}</b>.
	 */
	public static final Mode DEFAULT_SCALE_MODE = Mode.XY;

	/**
	 * Default mode directions, when over the scale, <b>{@link Mode#XY}</b>.
	 */
	public static final Mode DEFAULT_OVER_SCALE_MODE = Mode.XY;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		MODE("mode"),
		SCALE_MODE("scaleMode"),
		OVER_SCALE_MODE("overScaleMode");

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

	/**
	 * Creates the object with native object instance to be wrapped.
	 * 
	 * @param nativeObject native object instance to be wrapped.
	 */
	AbstractConfigurationItem(NativeObject nativeObject) {
		super(nativeObject);
		// stores new incremental id
		setNewIncrementalId();
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		this.modeCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValue(createContext(context), getModeCallback(), getDefaultsOptions().getMode()).value());
		this.scaleModeCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValue(createContext(context), getScaleModeCallback(), getDefaultsOptions().getScaleMode()).value());
		this.overScaleModeCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValue(createContext(context), getOverScaleModeCallback(), getDefaultsOptions().getOverScaleMode()).value());
		this.progressCallbackProxy.setCallback(context -> onProgress(createContext(context)));
		this.completeCallbackProxy.setCallback(context -> onCompleted(createContext(context)));
		this.rejectCallbackProxy.setCallback(context -> onRejected(createContext(context)));
		this.startCallbackProxy.setCallback(context -> onStart(createContext(context)));
	}

	/**
	 * Returns the default options instance.
	 * 
	 * @return the default options instance
	 */
	abstract IsDefaultConfigurationItem getDefaultsOptions();

	/**
	 * Returns the callback property handler for progress event.
	 * 
	 * @return the callback property handler for progress event
	 */
	abstract CallbackPropertyHandler<ProgressCallback> getProgessPropertyHandler();

	/**
	 * Returns the callback property handler for completed event.
	 * 
	 * @return the callback property handler for completed event
	 */
	abstract CallbackPropertyHandler<CompletedCallback> getCompletedPropertyHandler();

	/**
	 * Returns the callback property handler for rejected event.
	 * 
	 * @return the callback property handler for rejected event
	 */
	abstract CallbackPropertyHandler<RejectedCallback> getRejectedPropertyHandler();

	/**
	 * Returns the callback property handler for start event.
	 * 
	 * @return the callback property handler for start event
	 */
	abstract CallbackPropertyHandler<StartCallback> getStartPropertyHandler();

	/**
	 * Sets the element (panning or zooming) directions.
	 * 
	 * @param mode the element (panning or zooming) directions
	 */
	public final void setMode(Mode mode) {
		// reset callback if there is
		setMode((ModeCallback) null);
		// sets values
		setValue(Property.MODE, mode);
	}

	/**
	 * Returns the element (panning or zooming) directions.
	 * 
	 * @return the element (panning or zooming) directions
	 */
	@Override
	public final Mode getMode() {
		return getValue(Property.MODE, Mode.values(), getDefaultsOptions().getMode());
	}

	/**
	 * Enables panning over a scale for that axis (regardless of mode).
	 * 
	 * @param mode panning over a scale for that axis (regardless of mode).
	 */
	public final void setScaleMode(Mode mode) {
		// reset callback if there is
		setScaleMode((ModeCallback) null);
		// sets values
		setValue(Property.SCALE_MODE, mode);
	}

	/**
	 * Returns panning over a scale for that axis (regardless of mode).
	 * 
	 * @return panning over a scale for that axis (regardless of mode).
	 */
	@Override
	public final Mode getScaleMode() {
		return getValue(Property.SCALE_MODE, Mode.values(), getDefaultsOptions().getScaleMode());
	}

	/**
	 * Sets which of the enabled zooming directions should only be available when the mouse cursor is over one of scale.<br>
	 * If under mouse hasn't scale, then return all other scales which 'mode' is different with overScaleMode.<br>
	 * So 'overScaleMode' works as a limiter to scale the user-selected scale (in 'mode') only when the cursor is under the scale, and other directions in 'mode' works as before.
	 * 
	 * @param mode which of the enabled zooming directions should only be available when the mouse cursor is over one of scale
	 */
	public final void setOverScaleMode(Mode mode) {
		// reset callback if there is
		setOverScaleMode((ModeCallback) null);
		// sets values
		setValue(Property.OVER_SCALE_MODE, mode);
	}

	/**
	 * Returns which of the enabled zooming directions should only be available when the mouse cursor is over one of scale.
	 * 
	 * @return which of the enabled zooming directions should only be available when the mouse cursor is over one of scale
	 */
	@Override
	public final Mode getOverScaleMode() {
		return getValue(Property.OVER_SCALE_MODE, Mode.values(), getDefaultsOptions().getOverScaleMode());
	}

	// -----------------------
	// CALLBACKS
	// -----------------------

	/**
	 * Returns the element (panning or zooming) directions callback, to set the mode at runtime.
	 * 
	 * @return the element (panning or zooming) directions callback
	 */
	@Override
	public final ModeCallback getModeCallback() {
		return MODE_PROPERTY_HANDLER.getCallback(this, getDefaultsOptions().getModeCallback());
	}

	/**
	 * Sets the element (panning or zooming) directions callback, to set the mode at runtime.
	 * 
	 * @param modeCallback the element (panning or zooming) directions callback
	 */
	public final void setMode(ModeCallback modeCallback) {
		MODE_PROPERTY_HANDLER.setCallback(this, ZoomPlugin.ID, modeCallback, modeCallbackProxy.getProxy());
	}

	/**
	 * Sets the element (panning or zooming) directions callback, to set the mode at runtime.
	 * 
	 * @param modeCallback the element (panning or zooming) directions callback
	 */
	public final void setMode(NativeCallback modeCallback) {
		// resets callback
		setMode((ModeCallback) null);
		// stores value
		setValue(Property.MODE, modeCallback);
	}

	/**
	 * Returns the element directions callback, to enable panning over a scale for that axis (regardless of mode).
	 * 
	 * @return the element directions callback
	 */
	@Override
	public final ModeCallback getScaleModeCallback() {
		return SCALE_MODE_PROPERTY_HANDLER.getCallback(this, getDefaultsOptions().getScaleModeCallback());
	}

	/**
	 * Sets the element directions callback, to enable panning over a scale for that axis (regardless of mode).
	 * 
	 * @param modeCallback the element directions callback
	 */
	public final void setScaleMode(ModeCallback modeCallback) {
		SCALE_MODE_PROPERTY_HANDLER.setCallback(this, ZoomPlugin.ID, modeCallback, scaleModeCallbackProxy.getProxy());
	}

	/**
	 * Sets the element directions callback, to enable panning over a scale for that axis (regardless of mode).
	 * 
	 * @param modeCallback the element directions callback
	 */
	public final void setScaleMode(NativeCallback modeCallback) {
		// resets callback
		setScaleMode((ModeCallback) null);
		// stores value
		setValue(Property.SCALE_MODE, modeCallback);
	}

	/**
	 * Returns the element (panning or zooming) directions callback, to set the mode at runtime, which of the enabled zooming directions should only be available when the mouse
	 * cursor is over one of scale
	 * 
	 * @return the element (panning or zooming) directions callback
	 */
	@Override
	public final ModeCallback getOverScaleModeCallback() {
		return OVER_SCALE_MODE_PROPERTY_HANDLER.getCallback(this, getDefaultsOptions().getOverScaleModeCallback());
	}

	/**
	 * Sets the element (panning or zooming) directions callback, to set the mode at runtime, which of the enabled zooming directions should only be available when the mouse cursor
	 * is over one of scale
	 * 
	 * @param modeCallback the element (panning or zooming) directions callback
	 */
	public final void setOverScaleMode(ModeCallback modeCallback) {
		OVER_SCALE_MODE_PROPERTY_HANDLER.setCallback(this, ZoomPlugin.ID, modeCallback, overScaleModeCallbackProxy.getProxy());
	}

	/**
	 * Sets the element (panning or zooming) directions callback, to set the mode at runtime, which of the enabled zooming directions should only be available when the mouse cursor
	 * is over one of scale
	 * 
	 * @param modeCallback the element (panning or zooming) directions callback
	 */
	public final void setOverScaleMode(NativeCallback modeCallback) {
		// resets callback
		setOverScaleMode((ModeCallback) null);
		// stores value
		setValue(Property.OVER_SCALE_MODE, modeCallback);
	}

	/**
	 * Returns the callback called while the user is zooming or panning.
	 * 
	 * @return the callback called while the user is zooming or panning
	 */
	@Override
	public final ProgressCallback getProgressCallback() {
		return getProgessPropertyHandler().getCallback(this, getDefaultsOptions().getProgressCallback());
	}

	/**
	 * Sets the callback called while the user is zooming or panning.
	 * 
	 * @param progressCallback the callback called while the user is zooming or panning
	 */
	public final void setProgressCallback(ProgressCallback progressCallback) {
		getProgessPropertyHandler().setCallback(this, ZoomPlugin.ID, progressCallback, progressCallbackProxy.getProxy());
	}

	/**
	 * Returns the callback called once zooming or panning is completed.
	 * 
	 * @return the callback called once zooming or panning is completed
	 */
	@Override
	public final CompletedCallback getCompletedCallback() {
		return getCompletedPropertyHandler().getCallback(this, getDefaultsOptions().getCompletedCallback());
	}

	/**
	 * Sets the callback called once zooming or panning is completed.
	 * 
	 * @param completeCallback the callback called once zooming or panning is completed
	 */
	public final void setCompletedCallback(CompletedCallback completeCallback) {
		getCompletedPropertyHandler().setCallback(this, ZoomPlugin.ID, completeCallback, completeCallbackProxy.getProxy());
	}

	/**
	 * Returns the callback called once zooming or panning is completed.
	 * 
	 * @return the callback called once zooming or panning is completed
	 */
	@Override
	public final RejectedCallback getRejectedCallback() {
		return getRejectedPropertyHandler().getCallback(this, getDefaultsOptions().getRejectedCallback());
	}

	/**
	 * Sets the callback called once zooming or panning is rejected.
	 * 
	 * @param rejectCallback the callback called once zooming or panning is rejected
	 */
	public final void setRejectedCallback(RejectedCallback rejectCallback) {
		getRejectedPropertyHandler().setCallback(this, ZoomPlugin.ID, rejectCallback, rejectCallbackProxy.getProxy());
	}

	/**
	 * Returns the callback called once zooming or panning is started.
	 * 
	 * @return the callback called once zooming or panning is started
	 */
	@Override
	public final StartCallback getStartCallback() {
		return getStartPropertyHandler().getCallback(this, getDefaultsOptions().getStartCallback());
	}

	/**
	 * Sets the callback called once zooming or panning is started.
	 * 
	 * @param startCallback the callback called once zooming or panning is started
	 */
	public final void setStartCallback(StartCallback startCallback) {
		getStartPropertyHandler().setCallback(this, ZoomPlugin.ID, startCallback, startCallbackProxy.getProxy());
	}

	// ------------------------------
	// internal methods for callback
	// ------------------------------

	/**
	 * Creates and returns the {@link ZoomPlugin} context.
	 * 
	 * @param context native context provided by zomm plugin
	 * @return the {@link ZoomPlugin} context
	 */
	private ZoomContext createContext(NativeObject context) {
		return new ZoomContext(this, context);
	}

	/**
	 * Method of function to be called to manage onPan or onZoom callbacks.
	 * 
	 * @param context wrapper of native plugin context instance.
	 */
	private void onProgress(ZoomContext context) {
		// gets callback
		ProgressCallback progressCallback = getProgressCallback();
		// checks if the callback must be invoked
		if (isFunctionInvocationConsistent(progressCallback, context)) {
			// invokes callback
			progressCallback.onProgress(context);
		}
	}

	/**
	 * Method of function to be called to manage onPanComplete or onZoomComplete callbacks.
	 * 
	 * @param context wrapper of native plugin context instance.
	 */
	private void onCompleted(ZoomContext context) {
		// gets callback
		CompletedCallback completeCallback = getCompletedCallback();
		// checks if the callback must be invoked
		if (isFunctionInvocationConsistent(completeCallback, context)) {
			// invokes callback
			completeCallback.onCompleted(context);
		}
	}

	/**
	 * Method of function to be called to manage onPanRejected or onZoomRejected callbacks.
	 * 
	 * @param context wrapper of native plugin context instance.
	 */
	private void onRejected(ZoomContext context) {
		// gets callback
		RejectedCallback rejectCallback = getRejectedCallback();
		// checks if the callback must be invoked
		if (isFunctionInvocationConsistent(rejectCallback, context)) {
			// invokes callback
			rejectCallback.onRejected(context);
		}
	}

	/**
	 * Method of function to be called to manage onPanStart or onZoomStart callbacks.
	 * 
	 * @param context wrapper of native plugin context instance.
	 * @return if this callback returns <code>false</code>, panning or zooming is aborted and {@link RejectedCallback} is invoked.
	 */
	private boolean onStart(ZoomContext context) {
		// gets callback
		StartCallback startCallback = getStartCallback();
		// checks if the callback must be invoked
		if (isFunctionInvocationConsistent(startCallback, context)) {
			// invokes callback
			return startCallback.onStart(context);
		}
		// if here, the context is not consistent
		// then returns true to go on
		return true;
	}

	/**
	 * Returns <code>true</code> if the callback must be invoked, checking callaback and chart consistency.
	 * 
	 * @param function callback to check
	 * @param context context of callback passed by plugin
	 * @return <code>true</code> if the callback must be invoked, checking callback and chart consistency
	 */
	private boolean isFunctionInvocationConsistent(Object function, ZoomContext context) {
		return function != null && context != null && IsChart.isValid(context.getChart());
	}

	/**
	 * Exposes the native object.
	 * 
	 * @return the native object.
	 */
	final NativeObject nativeObject() {
		return getNativeObject();
	}

}