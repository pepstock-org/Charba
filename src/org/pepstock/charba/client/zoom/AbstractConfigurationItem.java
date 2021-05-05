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
package org.pepstock.charba.client.zoom;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.callbacks.CallbackFunctionContext;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyBooleanCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyStringCallback;
import org.pepstock.charba.client.callbacks.ScriptableUtils;
import org.pepstock.charba.client.commons.CallbackPropertyHandler;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.enums.InteractionAxis;
import org.pepstock.charba.client.zoom.callbacks.CompletedCallback;
import org.pepstock.charba.client.zoom.callbacks.ModeCallback;
import org.pepstock.charba.client.zoom.callbacks.ProgressCallback;
import org.pepstock.charba.client.zoom.callbacks.RejectedCallback;
import org.pepstock.charba.client.zoom.callbacks.StartCallback;

import jsinterop.annotations.JsFunction;

/**
 * Abstract element used by pan and zoom object in order to enable to provide the configuration of {@link ZoomPlugin#ID}.
 * 
 * @author Andrea "Stock" Stocchero
 * @param <T> type of default
 */
public abstract class AbstractConfigurationItem<T extends IsDefaultConfigurationItem> extends NativeObjectContainer implements IsDefaultConfigurationItem {

	/**
	 * Java script FUNCTION callback called to provide onPan, onPanComplete, onPanRejected, onZoom, onZoomComplete and onZoomRejected handlers.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyHandlerCallback {

		/**
		 * Method of function to be called to provide onPan, onPanComplete, onPanRejected, onZoom, onZoomComplete and onZoomRejected handlers.
		 * 
		 * @param contextFunction context value of <code>this</code> to the execution context of function
		 * @param context native chart
		 */
		void call(CallbackFunctionContext contextFunction, NativeObject context);
	}

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the MODE function
	private final CallbackProxy<ProxyStringCallback> modeCallbackProxy = JsHelper.get().newCallbackProxy();
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
	// over scale mode callback
	private static final CallbackPropertyHandler<ModeCallback> OVER_SCALE_MODE_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.OVER_SCALE_MODE);

	/**
	 * Default enabled, <b>{@value DEFAULT_ENABLED}</b>.
	 */
	public static final boolean DEFAULT_ENABLED = false;

	/**
	 * Default mode directions, <b>{@link InteractionAxis#XY}</b>.
	 */
	public static final InteractionAxis DEFAULT_MODE = InteractionAxis.XY;

	/**
	 * Default mode directions, when over the scale, <b>{@link InteractionAxis#XY}</b>.
	 */
	public static final InteractionAxis DEFAULT_OVER_SCALE_MODE = InteractionAxis.XY;

	/**
	 * Name of properties of native object.
	 */
	enum Property implements Key
	{
		ENABLED("enabled"),
		MODE("mode"),
		OVER_SCALE_MODE("overScaleMode"),
		THRESHOLD("threshold");

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

	// default options
	private final T defaultOptions;

	/**
	 * Creates the object with native object instance to be wrapped.
	 * 
	 * @param defaultOptions default options of element
	 * @param nativeObject native object instance to be wrapped.
	 */
	AbstractConfigurationItem(T defaultOptions, NativeObject nativeObject) {
		super(nativeObject);
		// checks if defaults options is consistent
		// stores defaults options
		this.defaultOptions = checkDefaultValuesArgument(defaultOptions);
		// stores new incremental id
		setNewIncrementalId();
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		this.modeCallbackProxy.setCallback((contextFunction, context) -> ScriptableUtils.getOptionValue(createContext(context), getModeCallback(), this.defaultOptions.getMode()).value());
		this.overScaleModeCallbackProxy.setCallback((contextFunction, context) -> ScriptableUtils.getOptionValue(createContext(context), getOverScaleModeCallback(), this.defaultOptions.getOverScaleMode()).value());
		this.progressCallbackProxy.setCallback((contextFunction, context) -> onProgress(createContext(context)));
		this.completeCallbackProxy.setCallback((contextFunction, context) -> onCompleted(createContext(context)));
		this.rejectCallbackProxy.setCallback((contextFunction, context) -> onRejected(createContext(context)));
		this.startCallbackProxy.setCallback((contextFunction, context) -> onStart(createContext(context)));
	}

	/**
	 * Returns the default options instance.
	 * 
	 * @return the default options instance
	 */
	final T getDefaultsOptions() {
		return defaultOptions;
	}

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
	 * Sets <code>true</code> to enable element (panning or zooming).
	 * 
	 * @param enabled <code>true</code> to enable element (panning or zooming)
	 */
	public final void setEnabled(boolean enabled) {
		setValue(Property.ENABLED, enabled);
	}

	/**
	 * Returns <code>true</code> to enable element (panning or zooming).
	 * 
	 * @return <code>true</code> to enable element (panning or zooming)
	 */
	@Override
	public final boolean isEnabled() {
		return getValue(Property.ENABLED, defaultOptions.isEnabled());
	}

	/**
	 * Sets the element (panning or zooming) directions.
	 * 
	 * @param mode the element (panning or zooming) directions
	 */
	public final void setMode(InteractionAxis mode) {
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
	public final InteractionAxis getMode() {
		// checks if callback has been activated
		if (getModeCallback() == null) {
			// no callback
			return getValue(Property.MODE, InteractionAxis.values(), defaultOptions.getMode());
		}
		// if here, mode callback has been activated
		// then returns the default
		return defaultOptions.getMode();
	}

	/**
	 * Sets which of the enabled zooming directions should only be available when the mouse cursor is over one of scale.<br>
	 * If under mouse hasn't scale, then return all other scales which 'mode' is different with overScaleMode.<br>
	 * So 'overScaleMode' works as a limiter to scale the user-selected scale (in 'mode') only when the cursor is under the scale, and other directions in 'mode' works as before.
	 * 
	 * @param mode which of the enabled zooming directions should only be available when the mouse cursor is over one of scale
	 */
	public final void setOverScaleMode(InteractionAxis mode) {
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
	public final InteractionAxis getOverScaleMode() {
		return getValue(Property.OVER_SCALE_MODE, InteractionAxis.values(), defaultOptions.getOverScaleMode());
	}

	/**
	 * Sets the minimal pan distance required before actually applying pan.
	 * 
	 * @param threshold the minimal pan distance required before actually applying pan
	 */
	public void setThreshold(double threshold) {
		setValue(Property.THRESHOLD, Checker.positiveOrZero(threshold));
	}

	/**
	 * Returns the minimal pan distance required before actually applying pan.
	 * 
	 * @return the minimal pan distance required before actually applying pan
	 */
	@Override
	public double getThreshold() {
		return getValue(Property.THRESHOLD, getDefaultsOptions().getThreshold());
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
		return MODE_PROPERTY_HANDLER.getCallback(this, defaultOptions.getModeCallback());
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
	 * Returns the element (panning or zooming) directions callback, to set the mode at runtime, which of the enabled zooming directions should only be available when the mouse
	 * cursor is over one of scale
	 * 
	 * @return the element (panning or zooming) directions callback
	 */
	@Override
	public final ModeCallback getOverScaleModeCallback() {
		return OVER_SCALE_MODE_PROPERTY_HANDLER.getCallback(this, defaultOptions.getOverScaleModeCallback());
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
	 * Returns the callback called while the user is zooming or panning.
	 * 
	 * @return the callback called while the user is zooming or panning
	 */
	@Override
	public final ProgressCallback getProgressCallback() {
		return getProgessPropertyHandler().getCallback(this, defaultOptions.getProgressCallback());
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
		return getCompletedPropertyHandler().getCallback(this, defaultOptions.getCompletedCallback());
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
		return getRejectedPropertyHandler().getCallback(this, defaultOptions.getRejectedCallback());
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
		return getStartPropertyHandler().getCallback(this, defaultOptions.getStartCallback());
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
