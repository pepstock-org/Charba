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
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.enums.InteractionAxis;
import org.pepstock.charba.client.zoom.callbacks.CompleteCallback;
import org.pepstock.charba.client.zoom.callbacks.ModeCallback;
import org.pepstock.charba.client.zoom.callbacks.ProgressCallback;

import jsinterop.annotations.JsFunction;

/**
 * Abstract element used by pan and zoom object in order to enable to provide the configuration of {@link ZoomPlugin#ID}.
 * 
 * @author Andrea "Stock" Stocchero
 * 
 */
public abstract class AbstractConfigurationItem extends NativeObjectContainer {

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
		String call(NativeObject contextFunction, Context context);
	}

	/**
	 * Java script FUNCTION callback called to provide onPan, onPanComplete, onZoom and onZoomComplete handlers.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyHandlerCallback {

		/**
		 * Method of function to be called to provide onPan, onPanComplete, onZoom and onZoomComplete handlers.
		 * 
		 * @param contextFunction context value of <code>this</code> to the execution context of function
		 * @param context native chart
		 */
		void call(NativeObject contextFunction, Context context);
	}

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the MODE function
	private final CallbackProxy<ProxyModeCallback> modeCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the PROGRESS function
	private final CallbackProxy<ProxyHandlerCallback> progressCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the COMPLETE function
	private final CallbackProxy<ProxyHandlerCallback> completeCallbackProxy = JsHelper.get().newCallbackProxy();

	/**
	 * Default enabled, <b>{@value DEFAULT_ENABLED}</b>.
	 */
	public static final boolean DEFAULT_ENABLED = false;

	/**
	 * Default mode directions, <b>{@link InteractionAxis#XY}</b>.
	 */
	public static final InteractionAxis DEFAULT_MODE = InteractionAxis.XY;

	/**
	 * Name of properties of native object.
	 */
	enum Property implements Key
	{
		ENABLED("enabled"),
		MODE("mode"),
		RANGE_MIN("rangeMin"),
		RANGE_MAX("rangeMax");

		// name value of property
		private final String value;

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

	// default options
	private final AbstractDefaultsConfigurationItem defaultsOptions;
	// minimum range
	private final Range rangeMin;
	// maximum range
	private final Range rangeMax;
	// mode callback
	private ModeCallback modeCallback;
	// progress callback
	private ProgressCallback progressCallback;
	// complete callback
	private CompleteCallback completeCallback;

	/**
	 * Creates the object with native object instance to be wrapped.
	 * 
	 * @param nativeObject native object instance to be wrapped.
	 * @param defaultsOptions default options of element
	 */
	AbstractConfigurationItem(NativeObject nativeObject, AbstractDefaultsConfigurationItem defaultsOptions) {
		super(nativeObject);
		// checks if defaults options is consistent
		if (defaultsOptions == null) {
			// if not, exception
			throw new IllegalArgumentException("Defaults options argument is null");
		}
		// stores defaults options
		this.defaultsOptions = defaultsOptions;
		// checks if range min are already present
		if (has(Property.RANGE_MIN)) {
			// gets range min
			this.rangeMin = new Range(getValue(Property.RANGE_MIN), defaultsOptions.getRangeMin());
		} else {
			// gets range min
			this.rangeMin = new Range(defaultsOptions.getRangeMin());
			// stores new range
			setValue(Property.RANGE_MIN, rangeMin);
		}
		// checks if range max are already present
		if (has(Property.RANGE_MAX)) {
			// gets range max
			this.rangeMax = new Range(getValue(Property.RANGE_MAX), defaultsOptions.getRangeMax());
		} else {
			// gets range max
			this.rangeMax = new Range(defaultsOptions.getRangeMax());
			// stores new range
			setValue(Property.RANGE_MAX, rangeMax);
		}
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		modeCallbackProxy.setCallback((contextFunction, context) -> onMode(context));
		progressCallbackProxy.setCallback((contextFunction, context) -> onProgress(context));
		completeCallbackProxy.setCallback((contextFunction, context) -> onComplete(context));
	}

	/**
	 * Returns the property name to use to set the progress handler.
	 * 
	 * @return the property name to use to set the progress handler
	 */
	abstract Key getProgressKey();

	/**
	 * Returns the property name to use to set the complete handler.
	 * 
	 * @return the property name to use to set the complete handler
	 */
	abstract Key getCompleteKey();

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
	public final boolean isEnabled() {
		return getValue(Property.ENABLED, defaultsOptions.isEnabled());
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
	public final InteractionAxis getMode() {
		// checks if callback has been activated
		if (getModeCallback() == null) {
			// no callback
			return getValue(Property.MODE, InteractionAxis.class, defaultsOptions.getMode());
		}
		// if here, mode callback has been activated
		// then returns the default
		return defaultsOptions.getMode();
	}

	/**
	 * Returns the element (panning or zooming) directions callback, to set the mode at runtime.
	 * 
	 * @return the element (panning or zooming) directions callback
	 */
	public final ModeCallback getModeCallback() {
		return modeCallback;
	}

	/**
	 * Sets the element (panning or zooming) directions callback, to set the mode at runtime.
	 * 
	 * @param modeCallback the element (panning or zooming) directions callback
	 */
	public final void setMode(ModeCallback modeCallback) {
		// sets the callback
		this.modeCallback = modeCallback;
		// checks if callback is consistent
		if (modeCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.MODE, modeCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.MODE);
		}
	}

	/**
	 * Returns the minimum element (panning or zooming) range depending on scale type.
	 * 
	 * @return the minimum element (panning or zooming) range depending on scale type
	 */
	public final Range getRangeMin() {
		return rangeMin;
	}

	/**
	 * Returns the maximum element (panning or zooming) range depending on scale type.
	 * 
	 * @return the maximum element (panning or zooming) range depending on scale type
	 */
	public final Range getRangeMax() {
		return rangeMax;
	}

	/**
	 * Returns the callback called while the user is zooming or panning.
	 * 
	 * @return the callback called while the user is zooming or panning
	 */
	public final ProgressCallback getProgressCallback() {
		return progressCallback;
	}

	/**
	 * Sets the callback called while the user is zooming or panning.
	 * 
	 * @param progressCallback the callback called while the user is zooming or panning
	 */
	public final void setProgressCallback(ProgressCallback progressCallback) {
		// gets the key to use for progress callback
		Key key = getProgressKey();
		// checks if key is consistent
		Key.checkIfValid(key);
		// sets the callback
		this.progressCallback = progressCallback;
		// checks if callback is consistent
		if (progressCallback != null) {
			// adds the callback proxy function to java script object
			setValue(key, progressCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(key);
		}
	}

	/**
	 * Returns the callback called once zooming or panning is completed.
	 * 
	 * @return the callback called once zooming or panning is completed
	 */
	public final CompleteCallback getCompleteCallback() {
		return completeCallback;
	}

	/**
	 * Sets the callback called once zooming or panning is completed.
	 * 
	 * @param completeCallback the callback called once zooming or panning is completed
	 */
	public final void setCompleteCallback(CompleteCallback completeCallback) {
		// gets the key to use for complete callback
		Key key = getCompleteKey();
		// checks if key is consistent
		Key.checkIfValid(key);
		// sets the callback
		this.completeCallback = completeCallback;
		// checks if callback is consistent
		if (completeCallback != null) {
			// adds the callback proxy function to java script object
			setValue(key, completeCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(key);
		}
	}

	/**
	 * Method of function to be called to provide a string mode (direction) property.
	 * 
	 * @param context wrapper of native chart instance.
	 * @return a string mode (direction) value.
	 */
	private String onMode(Context context) {
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
		return defaultsOptions.getMode().value();
	}

	/**
	 * Method of function to be called to manage onPan or onZoom callbacks.
	 * 
	 * @param context wrapper of native chart instance.
	 */
	private void onProgress(Context context) {
		// checks if the callback must be invoked
		if (isFunctionInvocationConsistent(progressCallback, context)) {
			// invokes callback
			progressCallback.onProgress(context.getChart(), this);
		}
	}

	/**
	 * Method of function to be called to manage onPanComplete or onZoomComplete callbacks.
	 * 
	 * @param context wrapper of native chart instance.
	 */
	private void onComplete(Context context) {
		// checks if the callback must be invoked
		if (isFunctionInvocationConsistent(completeCallback, context)) {
			// invokes callback
			completeCallback.onComplete(context.getChart(), this);
		}
	}

	/**
	 * Returns <code>true</code> if the callback must be invoked, checking callaback and chart consistency.
	 * 
	 * @param function callback to check
	 * @param context context of callback passed by plugin
	 * @return <code>true</code> if the callback must be invoked, checking callback and chart consistency
	 */
	private boolean isFunctionInvocationConsistent(Object function, Context context) {
		return function != null && context.getNativeChart() != null && IsChart.isValid(context.getChart());
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
