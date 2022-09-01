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

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.enums.IsTooltipPosition;
import org.pepstock.charba.client.items.DatasetReference;

import jsinterop.annotations.JsFunction;

/**
 * Manages the custom tooltip positioner. With a custom positioner you can decide where the tooltip can appear returning the point in the canvas.
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
		 * @param context context value of <code>this</code> to the execution context of function.
		 * @param datasetItems dataset elements of tooltips.
		 * @param eventPoint point on the canvas where the event occurred.
		 * @return point to show the tooltip or <code>false</code> if result of positioner is <code>null</code>.
		 */
		Object call(NativeObject context, ArrayObject datasetItems, NativeObject eventPoint);
	}

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the render function
	private final CallbackProxy<ProxyPositionerCallback> positionerCallbackProxy = JsHelper.get().newCallbackProxy();

	// all custom positioners
	private final Map<String, TooltipPositioner> positioners = new HashMap<>();

	/**
	 * To avoid any instantiation
	 */
	private Positioner() {
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		positionerCallbackProxy.setIgnoreFunctionContext(false);
		positionerCallbackProxy.setCallback(this::onToolipPosition);
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
	 * @param name the name of custom tooltip position to use in the tooltip
	 * @return <code>true</code> if the custom positioner has been registered, otherwise <code>false</code>
	 */
	public boolean hasTooltipPosition(String name) {
		// checks if name argument is consistent
		if (name != null) {
			return positioners.containsKey(name);
		}
		// if here the name is not consistent
		return false;
	}

	/**
	 * Returns the tooltip positioner implementation by tooltip position name. If not exists, returns <code>null</code>.
	 * 
	 * @param name the name of custom tooltip position to use in the tooltip
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
		if (TooltipPositioner.isValid(positioner)) {
			CustomTooltipPosition position = positioner.getName();
			// if consistent
			// checks if is already defined
			if (!positioners.containsKey(position.value())) {
				// if not
				// adds the function to invoke the custom positioner
				JsPositionerHelper.get().register(position, positionerCallbackProxy.getProxy());
			}
			// stores in the positioners map
			positioners.put(position.value(), positioner);
		}
	}

	/**
	 * Unregister the tooltips positioner to CHART.JS.<br>
	 * Pay attention that when a tooltip positioner is unregistered, if any charts, which still have the tooltip position of the positioner, will fail.
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

	/**
	 * Invokes an existing positioner to get the point.
	 * 
	 * @param position position of tooltips to be invoked
	 * @param chart chart instance
	 * @param items list of dataset reference items
	 * @param eventPoint the point of event when the method has been invoked
	 * @return the point calculated by positioner or <code>null</code> if positioner does not exist or the result of invoked positioner is <code>null</code>
	 */
	public Point invokePositioner(IsTooltipPosition position, IsChart chart, List<DatasetReference> items, Point eventPoint) {
		// checks if the chart is consistent
		if (IsChart.isValid(chart) && position != null && eventPoint != null && eventPoint.isConsistent()) {
			// creates context
			PositionerContext context = new PositionerContext();
			// stores chart
			context.setChart(chart);
			// gets array of objects
			ArrayObject datasetItems = ArrayObject.fromOrEmpty(items);
			// invokes actively the helper
			NativeObject pointAsObject = NativeJsPositionerHelper.invoke(position.value(), context.nativeObject(), datasetItems, eventPoint.nativeObject());
			// checks if consistent
			if (pointAsObject != null) {
				return new Point(pointAsObject);
			}
		}
		// if here, the object is not consistent
		// then return null
		return null;
	}

	/**
	 * Returns the point where the tooltip will appear.
	 * 
	 * @param context context value of <code>this</code> to the execution context of function.
	 * @param datasetItems dataset elements of tooltips.
	 * @param eventPoint point on the canvas where the event occurred.
	 * @return point to show the tooltip or <code>false</code> if result of positioner is <code>null</code>.
	 */
	private Object onToolipPosition(NativeObject context, ArrayObject datasetItems, NativeObject eventPoint) {
		// creates the context
		PositionerContext internalContext = new PositionerContext(context);
		// creates the point
		Point internalPoint = new Point(eventPoint);
		// gets chart instance
		IsChart chart = internalContext.getChart();
		// checks if the chart is consistent
		if (IsChart.isValid(chart)) {
			// gets the tooltip position requested
			// because this callback will be set for all custom positioners
			IsTooltipPosition position = chart.getOptions().getTooltips().getPosition();
			// checks if the custom positioner has been registered
			if (positioners.containsKey(position.value())) {
				// gets the custom implementation
				TooltipPositioner positioner = positioners.get(position.value());
				// list of dataset items
				List<DatasetReference> items = ArrayListHelper.unmodifiableList(datasetItems, chart.getDatasetReferenceFactory());
				// and invokes it
				Point result = positioner.computePosition(chart, items, internalPoint);
				// checks if the result is consistent
				if (result != null) {
					return result.nativeObject();
				}
			}
		}
		return false;
	}
}
