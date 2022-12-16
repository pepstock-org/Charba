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
package org.pepstock.charba.client.configuration;

import java.util.Date;
import java.util.List;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.adapters.DateAdapter;
import org.pepstock.charba.client.callbacks.ScriptableUtil;
import org.pepstock.charba.client.callbacks.TimeTickCallback;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.CallbackProxy.Proxy;
import org.pepstock.charba.client.commons.Constants;
import org.pepstock.charba.client.commons.ImmutableDate;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.enums.TimeUnit;
import org.pepstock.charba.client.items.ScaleItem;
import org.pepstock.charba.client.items.ScaleTickItem;

import jsinterop.annotations.JsFunction;

/**
 * Base object to map an axis tick for TIME axes, {@link CartesianTimeAxis} or {@link CartesianTimeSeriesAxis}.<br>
 * It is also common to want to change the tick marks to include information about the data type.<br>
 * To do this, you need to add a callback in the axis configuration. <br>
 * If the callback returns null or undefined the associated grid line will be hidden.
 * 
 * @author Andrea "Stock" Stocchero
 */
final class TimeTickHandler extends AbstractTickHandler<CartesianTimeTick, TimeTickCallback> {

	// ---------------------------
	// -- JAVASCRIPT FUNCTIONS ---
	// ---------------------------

	/**
	 * Java script FUNCTION callback when tick is created.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyTickCallback {

		/**
		 * Method of function to be called when tick is created.
		 * 
		 * @param value the timestamp of the thick
		 * @param index index of tick
		 * @param values array with all values of ticks
		 * @return string or array of strings representation of tick
		 */
		Object call(double value, int index, ArrayObject values);
	}

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the tick function
	private final CallbackProxy<ProxyTickCallback> tickCallbackProxy = JsHelper.get().newCallbackProxy();

	// gets date adapter
	private final CartesianTimeAxis timeAxis;

	/**
	 * Builds the object storing the axis instance and options element, based on different kind of axis.
	 * 
	 * @param axis axis instance
	 * @param configuration options element, based on different kind of axis.
	 */
	TimeTickHandler(Axis axis, CartesianTimeTick configuration) {
		super(axis, configuration);
		// checks if axis is a time or time series axis
		if (axis instanceof CartesianTimeAxis) {
			// stores time axis
			this.timeAxis = (CartesianTimeAxis) axis;
		} else {
			// oif here is an error
			throw new IllegalArgumentException("Axis is not a time or time series instance");
		}
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		tickCallbackProxy.setCallback((value, index, values) -> {
			// get default label
			String label = Constants.EMPTY_STRING;
			// gets chart
			IsChart chart = getAxis().getChart();
			// gets scale item
			ScaleItem scale = chart.getNode().getScales().getItems().get(getAxis().getId().value());
			// checks if scale is consistent
			if (scale != null) {
				// creates adapter
				DateAdapter adapter = scale.getDateAdapter();
				// get time unit
				TimeUnit unit = timeAxis.getTime().getUnit();
				// gets format
				String format = timeAxis.getTime().getDisplayFormats().getDisplayFormat(unit);
				// stores default label
				label = adapter.format((long) value, format);
			}
			// checks if callback is consistent
			if (getCallback() != null) {
				// gets as list the tick items
				List<ScaleTickItem> tickItems = getTickItems(values);
				// retrieves the current value
				Date dtValue = new ImmutableDate((long) value);
				// then calls user callback
				Object result = getCallback().onCallback(getAxis(), dtValue, label, index, tickItems);
				// parses and returns the result
				return ScriptableUtil.parseCallbackResult(result, label);
			}
			// scale is inconsistent
			// then returns an empty string
			return label;
		});

	}

	/**
	 * Returns a list of time tick items from an array of native objects passed by CHART.JS.
	 * 
	 * @param values an array of native objects passed by CHART.JS
	 * @return a list of time tick items
	 */
	final List<ScaleTickItem> getTickItems(ArrayObject values) {
		return ArrayListHelper.unmodifiableList(values, ScaleTickItem.FACTORY);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.AbstractTickHandler#getProxy()
	 */
	@Override
	Proxy getProxy() {
		return tickCallbackProxy.getProxy();
	}

}