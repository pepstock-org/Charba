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

import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.HtmlColor;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.geo.callbacks.InterpolateCallback;
import org.pepstock.charba.client.geo.enums.Interpolate;

import jsinterop.annotations.JsFunction;

/**
 * The coloring of the nodes will be done with a special color scale.<br>
 * Provides the elements, as colored legend, which can provide the how the values are distributed on map.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ColorScale extends LegendScale {

	/**
	 * Default missing color options, {@link HtmlColor#TRANSPARENT}.
	 */
	public static final String DEFAULT_MISSING_COLOR = HtmlColor.TRANSPARENT.toRGBA();

	/**
	 * Default quantize options, <b>{@value DEFAULT_QUANTIZE}</b>.
	 */
	public static final int DEFAULT_QUANTIZE = 0;

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

	// ---------------------------
	// -- USERS CALLBACKS ---
	// ---------------------------
	// user callback implementation for filtering legend labels
	private InterpolateCallback interpolateCallback = null;

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param parent parent node to use to add this element where changed
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param nativeObject native object to map java script properties
	 */
	ColorScale(AbstractNode parent, Key childKey, NativeObject nativeObject) {
		super(parent, childKey, nativeObject);
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		this.interpolateCallbackProxy.setCallback(this::onColor);
	}

	/**
	 * Sets the missing color.
	 * 
	 * @param missingColor the missing color.
	 */
	public void setMissingColor(IsColor missingColor) {
		setMissingColor(IsColor.checkAndGetValue(missingColor));
	}

	/**
	 * Sets the missing color.
	 * 
	 * @param missingColor the missing color.
	 */
	public void setMissingColor(String missingColor) {
		setValueAndAddToParent(Property.MISSING, missingColor);
	}

	/**
	 * Returns the missing color.
	 * 
	 * @return the missing color.
	 */
	public String getMissingColorAsString() {
		return getValue(Property.MISSING, DEFAULT_MISSING_COLOR);
	}

	/**
	 * Returns the missing color.
	 * 
	 * @return the missing color.
	 */
	public IsColor getMissingColor() {
		return ColorBuilder.parse(getMissingColorAsString());
	}

	/**
	 * Sets the amount of pieces to allow to split the color scale in N quantized equal bins.
	 * 
	 * @param quantize the amount of pieces to allow to split the color scale in N quantized equal bins
	 */
	public void setQuantize(int quantize) {
		setValueAndAddToParent(Property.QUANTIZE, quantize);
	}

	/**
	 * Returns the amount of pieces to allow to split the color scale in N quantized equal bins.
	 * 
	 * @return the amount of pieces to allow to split the color scale in N quantized equal bins
	 */
	public int getQuantize() {
		return getValue(Property.QUANTIZE, DEFAULT_QUANTIZE);
	}

	/**
	 * Sets the color interpolation of the scale.
	 * 
	 * @param interpolate the color interpolation of the scale
	 */
	public void setInterpolate(Interpolate interpolate) {
		// resets callback
		setInterpolate((InterpolateCallback) null);
		// stores value
		setValueAndAddToParent(Property.INTERPOLATE, interpolate);
	}

	/**
	 * Returns the color interpolation of the scale.
	 * 
	 * @return the color interpolation of the scale
	 */
	public Interpolate getInterpolate() {
		return getValue(Property.INTERPOLATE, Interpolate.values(), Interpolate.BLUES);
	}

	/**
	 * Sets the color interpolation callback of the scale.
	 * 
	 * @param interpolateCallback the color interpolation callback of the scale
	 */
	public void setInterpolate(InterpolateCallback interpolateCallback) {
		// sets the callback
		this.interpolateCallback = interpolateCallback;
		// checks if consistent
		if (interpolateCallback != null) {
			// adds the callback proxy function to java script object
			setValueAndAddToParent(Property.INTERPOLATE, interpolateCallbackProxy.getProxy());
		} else {
			// otherwise removes the properties from java script object
			remove(Property.INTERPOLATE);
		}
	}

	/**
	 * Returns the color interpolation callback of the scale.
	 * 
	 * @return the color interpolation callback of the scale
	 */
	public InterpolateCallback getInterpolateCallback() {
		return interpolateCallback;
	}

	/**
	 * Returns a string as color when the callback has been activated.
	 * 
	 * @param normalizedValue normalized value of the dataset
	 * @return a string as color
	 */
	private String onColor(double normalizedValue) {
		// checks if callback is consistent
		if (getInterpolateCallback() != null) {
			// invokes callback
			Object result = getInterpolateCallback().interpolate(normalizedValue);
			// checks result
			if (result instanceof IsColor) {
				// is color instance
				IsColor color = (IsColor) result;
				// checks if the color is consistent
				if (IsColor.isConsistent(color)) {
					// then returns RGBA representation
					return color.toRGBA();
				}
			} else if (result instanceof String) {
				// returns result
				return (String) result;
			}
		}
		// default result
		return DEFAULT_MISSING_COLOR;
	}
}
