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
import org.pepstock.charba.client.commons.CallbackPropertyHandler;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.enums.InteractionAxis;
import org.pepstock.charba.client.zoom.callbacks.CompletedCallback;
import org.pepstock.charba.client.zoom.callbacks.ModeCallback;
import org.pepstock.charba.client.zoom.callbacks.ProgressCallback;
import org.pepstock.charba.client.zoom.callbacks.RejectedCallback;

import jsinterop.annotations.JsFunction;

/**
 * Abstract element used by pan and zoom object in order to enable to provide the configuration of {@link ZoomPlugin#ID}.
 * 
 * @author Andrea "Stock" Stocchero
 * @param <T> type of default
 */
public abstract class AbstractConfigurationItem<T extends IsDefaultConfigurationItem> extends NativeObjectContainer implements IsDefaultConfigurationItem {

	/**
	 * Java script FUNCTION callback called to provide the mode (direction) of element.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyModeCallback {

		/**
		 * Method of function to be called to provide a string mode (direction) property.
		 * 
		 * @param contextFunction context value of <code>this</code> to the execution context of function
		 * @param context wrapper of native chart instance
		 * @return a string mode (direction) value
		 */
		String call(NativeObject contextFunction, NativeObject context);
	}

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
		void call(NativeObject contextFunction, NativeObject context);
	}

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the MODE function
	private final CallbackProxy<ProxyModeCallback> modeCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the MODE function
	private final CallbackProxy<ProxyModeCallback> overScaleModeCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the PROGRESS function
	private final CallbackProxy<ProxyHandlerCallback> progressCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the COMPLETED function
	private final CallbackProxy<ProxyHandlerCallback> completeCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the REJECTED function
	private final CallbackProxy<ProxyHandlerCallback> rejectCallbackProxy = JsHelper.get().newCallbackProxy();

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
		RANGE_MIN("rangeMin"),
		RANGE_MAX("rangeMax");

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

	// parent instance
	private final ZoomOptions parent;
	// default options
	private final T defaultOptions;
	// minimum range
	private final Range rangeMin;
	// maximum range
	private final Range rangeMax;

	/**
	 * Creates the object with native object instance to be wrapped.
	 * 
	 * @param parent zoom options, parent of this node
	 * @param defaultOptions default options of element
	 * @param nativeObject native object instance to be wrapped.
	 */
	AbstractConfigurationItem(ZoomOptions parent, T defaultOptions, NativeObject nativeObject) {
		super(nativeObject);
		// stores parent
		this.parent = parent;
		// checks if defaults options is consistent
		// stores defaults options
		this.defaultOptions = checkDefaultValuesArgument(defaultOptions);
		// checks if range min are already present
		if (has(Property.RANGE_MIN)) {
			// gets range min
			this.rangeMin = new Range(defaultOptions.getRangeMin(), getValue(Property.RANGE_MIN));
		} else {
			// gets range min
			this.rangeMin = new Range(defaultOptions.getRangeMin());
			// stores new range
			setValue(Property.RANGE_MIN, rangeMin);
		}
		// checks if range max are already present
		if (has(Property.RANGE_MAX)) {
			// gets range max
			this.rangeMax = new Range(defaultOptions.getRangeMax(), getValue(Property.RANGE_MAX));
		} else {
			// gets range max
			this.rangeMax = new Range(defaultOptions.getRangeMax());
			// stores new range
			setValue(Property.RANGE_MAX, rangeMax);
		}
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		modeCallbackProxy.setCallback((contextFunction, context) -> onMode(new ZoomContext(context), MODE_PROPERTY_HANDLER.getCallback(this)));
		overScaleModeCallbackProxy.setCallback((contextFunction, context) -> onMode(new ZoomContext(context), OVER_SCALE_MODE_PROPERTY_HANDLER.getCallback(this)));
		progressCallbackProxy.setCallback((contextFunction, context) -> onProgress(new ZoomContext(context)));
		completeCallbackProxy.setCallback((contextFunction, context) -> onCompleted(new ZoomContext(context)));
		rejectCallbackProxy.setCallback((contextFunction, context) -> onRejected(new ZoomContext(context)));
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
		setMode((ModeCallback) null);
		// sets values
		setValue(Property.MODE, mode);
	}

	/**
	 * Returns which of the enabled zooming directions should only be available when the mouse cursor is over one of scale.
	 * 
	 * @return which of the enabled zooming directions should only be available when the mouse cursor is over one of scale
	 */
	@Override
	public final InteractionAxis getOverScaleMode() {
		// no callback
		return getValue(Property.MODE, InteractionAxis.values(), defaultOptions.getOverScaleMode());
	}

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
		MODE_PROPERTY_HANDLER.setCallback(this, parent.getId(), modeCallback, modeCallbackProxy.getProxy());
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
		OVER_SCALE_MODE_PROPERTY_HANDLER.setCallback(this, parent.getId(), modeCallback, overScaleModeCallbackProxy.getProxy());
	}

	/**
	 * Returns the minimum element (panning or zooming) range depending on scale type.
	 * 
	 * @return the minimum element (panning or zooming) range depending on scale type
	 */
	@Override
	public final Range getRangeMin() {
		return rangeMin;
	}

	/**
	 * Returns the maximum element (panning or zooming) range depending on scale type.
	 * 
	 * @return the maximum element (panning or zooming) range depending on scale type
	 */
	@Override
	public final Range getRangeMax() {
		return rangeMax;
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
		getProgessPropertyHandler().setCallback(this, parent.getId(), progressCallback, progressCallbackProxy.getProxy());
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
		getCompletedPropertyHandler().setCallback(this, parent.getId(), completeCallback, completeCallbackProxy.getProxy());
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
		getRejectedPropertyHandler().setCallback(this, parent.getId(), rejectCallback, rejectCallbackProxy.getProxy());
	}

	/**
	 * Method of function to be called to provide a string mode (direction) property.
	 * 
	 * @param context wrapper of native chart instance.
	 * @param modeCallback callback to be invoked
	 * @return a string mode (direction) value.
	 */
	private String onMode(ZoomContext context, ModeCallback modeCallback) {
		// checks if the callback must be invoked
		if (isFunctionInvocationConsistent(modeCallback, context)) {
			// invokes callback
			InteractionAxis result = modeCallback.mode(context.getChart());
			// checks if result is consistent
			if (Key.isValid(result)) {
				// returns the value of result of callback
				return result.value();
			}
		}
		// defaults mode if here
		// because some entities is not consistent
		return defaultOptions.getMode().value();
	}

	/**
	 * Method of function to be called to manage onPan or onZoom callbacks.
	 * 
	 * @param context wrapper of native plugin context instance.
	 */
	private void onProgress(ZoomContext context) {
		// gets callback
		ProgressCallback progressCallback = getProgessPropertyHandler().getCallback(this);
		// checks if the callback must be invoked
		if (isFunctionInvocationConsistent(progressCallback, context)) {
			// invokes callback
			progressCallback.onProgress(context.getChart(), this);
		}
	}

	/**
	 * Method of function to be called to manage onPanComplete or onZoomComplete callbacks.
	 * 
	 * @param context wrapper of native plugin context instance.
	 */
	private void onCompleted(ZoomContext context) {
		// gets callback
		CompletedCallback completeCallback = getCompletedPropertyHandler().getCallback(this);
		// checks if the callback must be invoked
		if (isFunctionInvocationConsistent(completeCallback, context)) {
			// invokes callback
			completeCallback.onCompleted(context.getChart(), this);
		}
	}

	/**
	 * Method of function to be called to manage onPanRejected or onZoomRejected callbacks.
	 * 
	 * @param context wrapper of native plugin context instance.
	 */
	private void onRejected(ZoomContext context) {
		// gets callback
		RejectedCallback rejectCallback = getRejectedPropertyHandler().getCallback(this);
		// checks if the callback must be invoked
		if (isFunctionInvocationConsistent(rejectCallback, context)) {
			// invokes callback
			rejectCallback.onRejected(context.getChart(), this);
		}
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
