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
package org.pepstock.charba.client.positioner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.defaults.globals.DefaultsBuilder;
import org.pepstock.charba.client.enums.IsTooltipPosition;
import org.pepstock.charba.client.items.DatasetItem;

import jsinterop.annotations.JsFunction;

/**
 * Manages the custom tooltip positioner. With a custom positioner you can decide where the tooltip can appear returning the
 * point in the canvas.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Positioner {

	// static instance for singleton
	private static final Positioner INSTANCE = new Positioner();

	// ---------------------------
	// -- JAVASCRIPT FUNCTIONS ---
	// ---------------------------

	/**
	 * Java script FUNCTION callback called to invoke a custom positioner for tooltips.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyPositionerCallback {

		/**
		 * Method of function to be called to invoke a custom positioner for tooltips.
		 * 
		 * @param context context Value of <code>this</code> to the execution context of function.
		 * @param datasetItems dataset elements of tooltips.
		 * @param eventPosition point on the canvas where the event occurred.
		 * @return point to show the tooltip
		 */
		Point call(PositionerContext context, ArrayObject datasetItems, Point eventPoint);
	}

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the render function
	private final CallbackProxy<ProxyPositionerCallback> positionerCallbackProxy = JsHelper.get().newCallbackProxy();

	// all custom positioners
	private final Map<String, TooltipPositioner> positioners = new HashMap<>();
	// dataset items factory
	private final DatasetItem.DatasetItemFactory factory = new DatasetItem.DatasetItemFactory();

	/**
	 * To avoid any instantiation
	 */
	private Positioner() {
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		positionerCallbackProxy.setCallback(new ProxyPositionerCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.positioner.Positioner.ProxyPositionerCallback#call(org.pepstock.charba.client.
			 * positioner.PositionerContext, org.pepstock.charba.client.commons.ArrayObject,
			 * org.pepstock.charba.client.positioner.Point)
			 */
			@Override
			public Point call(PositionerContext context, ArrayObject datasetItems, Point eventPoint) {
				// gets chart instance
				AbstractChart<?, ?> chart = context.getChart();
				// checks if the chart is consistent
				if (chart != null) {
					// gets the tooltip position requested
					// because this callback will be set for all custom positioners
					IsTooltipPosition position = chart.getOptions().getTooltips().getPosition();
					// checks if the custom positioner has been registered
					if (positioners.containsKey(position.value())) {
						// gets the custom implementation
						TooltipPositioner positioner = positioners.get(position.value());
						// list of dataset items
						List<DatasetItem> items = ArrayListHelper.unmodifiableList(datasetItems, factory);
						// and invokes it
						Point result = positioner.computePosition(chart, items, eventPoint);
						// checks if the result is consistent
						if (result != null) {
							return result;
						}
					}
				}
				// if here, is not able to get the chart or the positioner is not defined
				// or the result of custom positioner is not consistent
				// then gets the default tooltip position
				IsTooltipPosition defaultValue = DefaultsBuilder.get().getOptions().getTooltips().getPosition();
				// invokes the positioner of the default one getting the point
				Point defaultPoint = JsPositionerHelper.get().invoke(defaultValue, context, datasetItems, eventPoint);
				// if the result is ok, return it otherwise returns the point of event
				return defaultPoint != null ? defaultPoint : eventPoint;
			}
		});
	}

	/**
	 * Singleton object to get the tooltip positioner instance
	 * 
	 * @return tooltip positioner instance.
	 */
	public static Positioner get() {
		return INSTANCE;
	}

	/**
	 * Returns <code>true</code> if the custom positioner has been registered, otherwise <code>false</code>.
	 * 
	 * @param name the name of custom tooltip position to use into tooltip
	 * @return <code>true</code> if the custom positioner has been registered, otherwise <code>false</code>
	 */
	public boolean hasTooltipPosition(String name) {
		return positioners.containsKey(name);
	}

	/**
	 * Returns the tooltip positioner implementation by tooltip position name. If not exists, returns <code>null</code>.
	 * 
	 * @param name the name of custom tooltip position to use into tooltip
	 * @return the tooltip positioner implementation. If not exists, returns <code>null</code>.
	 */
	public IsTooltipPosition getTooltipPosition(String name) {
		// checks if exists
		if (hasTooltipPosition(name)) {
			// returns tooltip position name
			return positioners.get(name).getName();
		}
		return null;
	}

	/**
	 * Register the tooltips positioner to CHART.JS.
	 * 
	 * @param positioner tooltip positioner instance to invoke
	 */
	public void register(TooltipPositioner positioner) {
		// checks if tooltip positioner is consistent
		if (positioner != null) {
			CustomTooltipPosition position = positioner.getName();
			if (position != null) {
				// if consistent
				// checks if is already defined
				if (!positioners.containsKey(position.value())) {
					// if not
					// adds the function to invoke the custom positioner
					JsPositionerHelper.get().register(position, positionerCallbackProxy.getProxy());
				}
				// stores into positioners map
				positioners.put(position.value(), positioner);
			} else {
				throw new IllegalArgumentException("Custom tooltip position name is null");
			}
		}
	}

	/**
	 * Unregister the tooltips positioner to CHART.JS.<br>
	 * Pay attention that when a tooltip positioner is unregistered, if any charts, which still have the tooltip position of the
	 * positioner, will fail.
	 * 
	 * @param position custom tooltip position instance
	 */
	public void unregister(CustomTooltipPosition position) {
		// checks if tooltip position is consistent
		if (position != null) {
			// removes the custom positioner from chart.js
			JsPositionerHelper.get().unregister(position);
			// removes the entry from maps
			positioners.remove(position.value());
		}
	}
}
