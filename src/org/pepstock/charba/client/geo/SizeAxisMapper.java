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
package org.pepstock.charba.client.geo;

import java.util.List;

import org.pepstock.charba.client.callbacks.RadiusCallback;
import org.pepstock.charba.client.callbacks.ScaleContext;
import org.pepstock.charba.client.callbacks.ScriptableDoubleChecker;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyArrayCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyDoubleCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyStringCallback;
import org.pepstock.charba.client.callbacks.ScriptableUtil;
import org.pepstock.charba.client.commons.ArrayInteger;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.CallbackPropertyHandler;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.configuration.Axis;
import org.pepstock.charba.client.geo.callbacks.ModeCallback;
import org.pepstock.charba.client.geo.callbacks.RangeCallback;
import org.pepstock.charba.client.geo.enums.Mode;

/**
 * The scale is used to map the values to symbol radius size.<br>
 * Provides the elements, as colored legend, which can provide the how the values are distributed on map.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class SizeAxisMapper extends LegendAxisMapper {

	/**
	 * Name of properties of native object for projection scale.
	 */
	private enum Property implements Key
	{
		MISSING("missing"),
		RANGE("range"),
		MODE("mode");

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
	// callback proxy to invoke the missing radius function
	private final CallbackProxy<ProxyDoubleCallback> missingRadiusCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the mode function
	private final CallbackProxy<ProxyStringCallback> modeCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the range function
	private final CallbackProxy<ProxyArrayCallback> rangeCallbackProxy = JsHelper.get().newCallbackProxy();

	// ---------------------------
	// -- USERS CALLBACKS ---
	// ---------------------------
	// user callback implementation for missing radius colors
	private static final CallbackPropertyHandler<RadiusCallback<ScaleContext>> MISSING_RADIUS_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.MISSING);
	// user callback implementation for mode
	private static final CallbackPropertyHandler<ModeCallback> MODE_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.MODE);
	// user callback implementation for range
	private static final CallbackPropertyHandler<RangeCallback> RANGE_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.RANGE);

	/**
	 * Creates the object with native object instance to be wrapped.
	 * 
	 * @param axis parent axis of the mapper
	 * @param nativeObject native object instance to be wrapped.
	 */
	SizeAxisMapper(Axis axis, NativeObject nativeObject) {
		super(axis, nativeObject);
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// sets function to proxy callback in order to invoke the java interface
		this.missingRadiusCallbackProxy
				.setCallback(context -> ScriptableUtil.getOptionValueAsNumber(new ScaleContext(getAxis(), context), getMissingRadiusCallback(), SizeAxis.DEFAULT_MISSING_RADIUS, ScriptableDoubleChecker.POSITIVE_OR_DEFAULT).doubleValue());
		// sets function to proxy callback in order to invoke the java interface
		this.modeCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValue(new ScaleContext(getAxis(), context), getModeCallback(), Mode.AREA).value());
		// sets function to proxy callback in order to invoke the java interface
		this.rangeCallbackProxy.setCallback(context -> ArrayInteger.fromOrEmpty(ScriptableUtil.getOptionValue(new ScaleContext(getAxis(), context), getRangeCallback(), SizeAxis.DEFAULT_RANGE)));
	}

	/**
	 * Sets the radius to render for missing values.
	 * 
	 * @param missingRadius the radius to render for missing values
	 */
	void setMissingRadius(double missingRadius) {
		// resets callback
		setMissingRadius(null);
		// stores value
		setValue(Property.MISSING, missingRadius);
	}

	/**
	 * Returns the radius to render for missing values.
	 * 
	 * @return the radius to render for missing values
	 */
	double getMissingRadius() {
		return getValue(Property.MISSING, SizeAxis.DEFAULT_MISSING_RADIUS);
	}

	/**
	 * Sets the operation modes for the scale, area means that the area is linearly increasing whereas radius the radius is.
	 * 
	 * @param mode the operation modes for the scale, area means that the area is linearly increasing whereas radius the radius is
	 */
	void setMode(Mode mode) {
		// resets callback
		setMode((ModeCallback) null);
		// stores value
		setValue(Property.MODE, mode);
	}

	/**
	 * Returns the operation modes for the scale, area means that the area is linearly increasing whereas radius the radius is.
	 * 
	 * @return the operation modes for the scale, area means that the area is linearly increasing whereas radius the radius is
	 */
	Mode getMode() {
		return getValue(Property.MODE, Mode.values(), Mode.AREA);
	}

	/**
	 * Sets the radius range in pixel, the minimal data value will be mapped to the first entry, the maximal one to the second and a linear interpolation for all values in between.
	 * 
	 * @param min minimum range in pixel
	 * @param max maximum range in pixel
	 */
	void setRange(int min, int max) {
		// resets callback
		setRange(null);
		// stores value
		setArrayValue(Property.RANGE, ArrayInteger.fromOrEmpty(min, max));
	}

	/**
	 * Returns the radius range in pixel, the minimal data value will be mapped to the first entry, the maximal one to the second and a linear interpolation for all values in
	 * between.
	 * 
	 * @return the radius range in pixel, the minimal data value will be mapped to the first entry, the maximal one to the second and a linear interpolation for all values in
	 *         between
	 */
	List<Integer> getRange() {
		// checks if the range is an array
		if (isType(Property.RANGE, ObjectType.ARRAY)) {
			ArrayInteger array = getArrayValue(Property.RANGE);
			return ArrayListHelper.list(array);
		}
		// if here, the value is not an array,
		// then returns the default
		return SizeAxis.DEFAULT_RANGE;
	}

	// ------------------
	// CALLBACKS
	// ------------------

	/**
	 * Sets the radius to render for missing values.
	 * 
	 * @param missingRadiusCallback the radius to render for missing values
	 */
	void setMissingRadius(RadiusCallback<ScaleContext> missingRadiusCallback) {
		MISSING_RADIUS_PROPERTY_HANDLER.setCallback(this, getAxis().getChart().getId(), missingRadiusCallback, missingRadiusCallbackProxy.getProxy());
	}

	/**
	 * Returns the radius callback to render for missing values.
	 * 
	 * @return the radius callback to render for missing values
	 */
	RadiusCallback<ScaleContext> getMissingRadiusCallback() {
		return MISSING_RADIUS_PROPERTY_HANDLER.getCallback(this, null);
	}

	/**
	 * Sets the operation modes for the scale, area means that the area is linearly increasing whereas radius the radius is.
	 * 
	 * @param modeCallback the operation modes callback for the scale, area means that the area is linearly increasing whereas radius the radius is
	 */
	void setMode(ModeCallback modeCallback) {
		MODE_PROPERTY_HANDLER.setCallback(this, getAxis().getChart().getId(), modeCallback, modeCallbackProxy.getProxy());
	}

	/**
	 * Returns the operation modes callback for the scale, area means that the area is linearly increasing whereas radius the radius is.
	 * 
	 * @return the operation modes callback for the scale, area means that the area is linearly increasing whereas radius the radius is
	 */
	ModeCallback getModeCallback() {
		return MODE_PROPERTY_HANDLER.getCallback(this, null);
	}

	/**
	 * Sets the radius range callback, the minimal data value will be mapped to the first entry, the maximal one to the second and a linear interpolation for all values in between.
	 * 
	 * @param rangeCallback the radius range callback, the minimal data value will be mapped to the first entry, the maximal one to the second and a linear interpolation for all
	 *            values in between.
	 */
	void setRange(RangeCallback rangeCallback) {
		RANGE_PROPERTY_HANDLER.setCallback(this, getAxis().getChart().getId(), rangeCallback, rangeCallbackProxy.getProxy());
	}

	/**
	 * Returns the radius range callback, the minimal data value will be mapped to the first entry, the maximal one to the second and a linear interpolation for all values in
	 * between.
	 * 
	 * @return the radius range callback, the minimal data value will be mapped to the first entry, the maximal one to the second and a linear interpolation for all values in
	 *         between
	 */
	RangeCallback getRangeCallback() {
		return RANGE_PROPERTY_HANDLER.getCallback(this, null);
	}
}