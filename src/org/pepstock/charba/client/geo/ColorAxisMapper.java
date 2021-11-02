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
package org.pepstock.charba.client.geo;

import org.pepstock.charba.client.callbacks.ColorCallback;
import org.pepstock.charba.client.callbacks.ScaleContext;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyIntegerCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyStringCallback;
import org.pepstock.charba.client.callbacks.ScriptableIntegerChecker;
import org.pepstock.charba.client.callbacks.ScriptableUtils;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.CallbackPropertyHandler;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.configuration.Axis;
import org.pepstock.charba.client.geo.callbacks.InterpolateCallback;
import org.pepstock.charba.client.geo.callbacks.QuantizeCallback;
import org.pepstock.charba.client.geo.enums.Interpolate;

import jsinterop.annotations.JsFunction;

/**
 * The coloring of the nodes will be done with a special color scale.<br>
 * Provides the elements, as colored legend, which can provide the how the values are distributed on map.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class ColorAxisMapper extends LegendAxisMapper {

	// ---------------------------
	// -- JAVASCRIPT FUNCTIONS ---
	// ---------------------------

	/**
	 * Java script FUNCTION callback called to interpolate the color on GEO chart.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyInterpolateCallback {

		/**
		 * Method of function to be called to interpolate the color on GEO chart.
		 * 
		 * @param normalizedValue normalized value of the data set.
		 * @return a color as a string
		 */
		String call(double normalizedValue);
	}

	/**
	 * Name of properties of native object for projection scale.
	 */
	private enum Property implements Key
	{
		MISSING("missing"),
		QUANTIZE("quantize"),
		INTERPOLATE("interpolate");

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
	// callback proxy to invoke the interpolate function
	private final CallbackProxy<ProxyInterpolateCallback> interpolateCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the quantize function
	private final CallbackProxy<ProxyIntegerCallback> quantizeCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the missing color function
	private final CallbackProxy<ProxyStringCallback> missingColorCallbackProxy = JsHelper.get().newCallbackProxy();

	// ---------------------------
	// -- USERS CALLBACKS ---
	// ---------------------------
	// user callback implementation for interpolates colors
	private static final CallbackPropertyHandler<InterpolateCallback> INTERPOLATE_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.INTERPOLATE);
	// user callback implementation for quantize
	private static final CallbackPropertyHandler<QuantizeCallback> QUANTIZE_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.QUANTIZE);
	// user callback implementation for missing colors
	private static final CallbackPropertyHandler<ColorCallback<ScaleContext>> MISSING_COLOR_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.MISSING);

	/**
	 * Creates the object with native object instance to be wrapped.
	 * 
	 * @param axis parent axis of the mapper
	 * @param nativeObject native object instance to be wrapped.
	 */
	ColorAxisMapper(Axis axis, NativeObject nativeObject) {
		super(axis, nativeObject);
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// sets function to proxy callback in order to invoke the java interface
		this.interpolateCallbackProxy.setCallback(this::onInterpolateColor);
		// sets function to proxy callback in order to invoke the java interface
		this.missingColorCallbackProxy.setCallback(this::onMissingColor);
		// sets function to proxy callback in order to invoke the java interface
		this.quantizeCallbackProxy.setCallback(context -> ScriptableUtils.getOptionValueAsNumber(new ScaleContext(getAxis(), context), getQuantizeCallback(), ColorAxis.DEFAULT_QUANTIZE, ScriptableIntegerChecker.POSITIVE_OR_DEFAULT).intValue());
	}

	/**
	 * Sets the missing color.
	 * 
	 * @param missingColor the missing color.
	 */
	void setMissingColor(String missingColor) {
		// resets callback
		setMissingColor((ColorCallback<ScaleContext>) null);
		// stores values
		setValue(Property.MISSING, missingColor);
	}

	/**
	 * Returns the missing color.
	 * 
	 * @return the missing color.
	 */
	String getMissingColorAsString() {
		return getValue(Property.MISSING, ColorAxis.DEFAULT_MISSING_COLOR);
	}

	/**
	 * Sets the amount of pieces to allow to split the color scale in N quantized equal bins.
	 * 
	 * @param quantize the amount of pieces to allow to split the color scale in N quantized equal bins
	 */
	void setQuantize(int quantize) {
		// resets callback
		setQuantize(null);
		// stores values
		setValue(Property.QUANTIZE, Checker.positiveOrDefault(quantize, ColorAxis.DEFAULT_QUANTIZE));
	}

	/**
	 * Returns the amount of pieces to allow to split the color scale in N quantized equal bins.
	 * 
	 * @return the amount of pieces to allow to split the color scale in N quantized equal bins
	 */
	int getQuantize() {
		return getValue(Property.QUANTIZE, ColorAxis.DEFAULT_QUANTIZE);
	}

	/**
	 * Sets the color interpolation of the scale.
	 * 
	 * @param interpolate the color interpolation of the scale
	 */
	void setInterpolate(Interpolate interpolate) {
		// resets callback
		setInterpolate((InterpolateCallback) null);
		// stores value
		setValue(Property.INTERPOLATE, interpolate);
	}

	/**
	 * Returns the color interpolation of the scale.
	 * 
	 * @return the color interpolation of the scale
	 */
	Interpolate getInterpolate() {
		return getValue(Property.INTERPOLATE, Interpolate.values(), Interpolate.BLUES);
	}

	// ---------------------
	// CALLBACKS
	// ---------------------

	/**
	 * Sets the color interpolation callback of the scale.
	 * 
	 * @param interpolateCallback the color interpolation callback of the scale
	 */
	void setInterpolate(InterpolateCallback interpolateCallback) {
		INTERPOLATE_PROPERTY_HANDLER.setCallback(this, getAxis().getChart().getId(), interpolateCallback, interpolateCallbackProxy.getProxy());
	}

	/**
	 * Returns the color interpolation callback of the scale.
	 * 
	 * @return the color interpolation callback of the scale
	 */
	InterpolateCallback getInterpolateCallback() {
		return INTERPOLATE_PROPERTY_HANDLER.getCallback(this, null);
	}

	/**
	 * Sets the callback to get the amount of pieces to allow to split the color scale in N quantized equal bins.
	 * 
	 * @param quantizeCallback the callback to get the amount of pieces to allow to split the color scale in N quantized equal bins
	 */
	void setQuantize(QuantizeCallback quantizeCallback) {
		QUANTIZE_PROPERTY_HANDLER.setCallback(this, getAxis().getChart().getId(), quantizeCallback, quantizeCallbackProxy.getProxy());
	}

	/**
	 * Returns the callback to get the amount of pieces to allow to split the color scale in N quantized equal bins.
	 * 
	 * @return the callback to get the amount of pieces to allow to split the color scale in N quantized equal bins
	 */
	QuantizeCallback getQuantizeCallback() {
		return QUANTIZE_PROPERTY_HANDLER.getCallback(this, null);
	}

	/**
	 * Sets the missing color callback.
	 * 
	 * @param missingColorCallback the missing color callback
	 */
	void setMissingColor(ColorCallback<ScaleContext> missingColorCallback) {
		MISSING_COLOR_PROPERTY_HANDLER.setCallback(this, getAxis().getChart().getId(), missingColorCallback, missingColorCallbackProxy.getProxy());
	}

	/**
	 * Returns the missing color callback.
	 * 
	 * @return the missing color callback
	 */
	ColorCallback<ScaleContext> getMissingColorCallback() {
		return MISSING_COLOR_PROPERTY_HANDLER.getCallback(this, null);
	}

	// ---------------------
	// INTERNAL METHODS
	// ---------------------

	/**
	 * Returns a string as color when the callback has been activated.
	 * 
	 * @param normalizedValue normalized value of the dataset
	 * @return a string as color
	 */
	private String onInterpolateColor(double normalizedValue) {
		// checks if callback is consistent
		if (getInterpolateCallback() != null) {
			// invokes callback
			Object result = getInterpolateCallback().interpolate(normalizedValue);
			// checks and gets result
			return checkAndGetColor(result, ColorAxis.DEFAULT_MISSING_COLOR);
		}
		// default result
		return ColorAxis.DEFAULT_MISSING_COLOR;
	}

	/**
	 * Returns a string as color when the callback has been activated.
	 * 
	 * @param context scriptable context
	 * @return a string as color
	 */
	private String onMissingColor(NativeObject context) {
		// checks if callback is consistent
		if (getMissingColorCallback() != null) {
			// invokes callback
			Object result = getMissingColorCallback().invoke(new ScaleContext(getAxis(), context));
			// checks and gets result
			return checkAndGetColor(result, ColorAxis.DEFAULT_MISSING_COLOR);
		}
		// default result
		return ColorAxis.DEFAULT_MISSING_COLOR;
	}

	/**
	 * Checke and returns a normalized value from a color callback invocation.
	 * 
	 * @param callbackResult result of color callback
	 * @param defaultValue default color as string
	 * @return a string as color
	 */
	private String checkAndGetColor(Object callbackResult, String defaultValue) {
		// checks result
		if (callbackResult instanceof IsColor) {
			// is color instance
			IsColor color = (IsColor) callbackResult;
			// checks if the color is consistent
			if (IsColor.isConsistent(color)) {
				// then returns RGBA representation
				return color.toRGBA();
			}
		} else if (callbackResult instanceof String) {
			// returns result
			return (String) callbackResult;
		}
		// default result
		return defaultValue;
	}
}
