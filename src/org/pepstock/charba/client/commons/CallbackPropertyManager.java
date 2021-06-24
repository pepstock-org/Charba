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
package org.pepstock.charba.client.commons;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.pepstock.charba.client.Charts;
import org.pepstock.charba.client.ChartsLifecycleListener;
import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.utils.CScheduler;

/**
 * It is managing the notification to {@link CallbackPropertyHandler} when some callbacks are defined at chart level.<br>
 * This is implemented in order to remove unused callbacks from cache.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class CallbackPropertyManager {

	// singleton instance
	private static final CallbackPropertyManager INSTANCE = new CallbackPropertyManager();

	// caches with the callback handlers instances
	private final Set<IsCallbackPropertyHandler> handlers = new HashSet<>();
	// notifier instance
	private final CallbackPropertyNotifier notifier = new CallbackPropertyNotifier();

	/**
	 * To avoid any instantiation
	 */
	CallbackPropertyManager() {
		// adds the chart listener
		Charts.addLifecycleListener(notifier);
	}

	/**
	 * Returns the singleton instance.
	 * 
	 * @return the singleton instance
	 */
	static CallbackPropertyManager get() {
		return INSTANCE;
	}

	/**
	 * Returns the set of registered handlers.
	 * 
	 * @return the set of registered handlers
	 */
	Set<IsCallbackPropertyHandler> getHandlers() {
		return handlers;
	}

	/**
	 * Adds new callback handler instance in the collection.
	 * 
	 * @param handler new callback handler instance
	 */
	void addHandler(IsCallbackPropertyHandler handler) {
		// checks if handler is consistent
		// and adds to list
		handlers.add(Checker.checkAndGetIfValid(handler, "Callback property handler"));
	}

	/**
	 * Removes callback handler instance in the collection.
	 * 
	 * @param handler callback handler instance
	 */
	void removeHandler(IsCallbackPropertyHandler handler) {
		// checks if handler is consistent
		// no exception because it is removing it
		if (handler != null) {
			handlers.remove(handler);
		}
	}

	/**
	 * Internal class to listen when a chart instance will be destroy, in order to notify all {@link CallbackPropertyHandler}s to remove the scope from callbacks.
	 * 
	 * @author Andrea "Stock" Stocchero
	 * 
	 */
	private static final class CallbackPropertyNotifier implements ChartsLifecycleListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.ChartsLifecycleListener#onBeforeDestroy(org.pepstock.charba.client.IsChart)
		 */
		@Override
		public void onBeforeDestroy(IsChart chart) {
			// checks if chart is valid
			if (IsChart.isValid(chart) && !CallbackPropertyManager.get().getHandlers().isEmpty()) {
				// removes the scope from callback handler
				// in async in order do not block the destroy of the chart
				CScheduler.get().submit(() -> {
					// gets iterator
					Iterator<IsCallbackPropertyHandler> iterator = CallbackPropertyManager.get().getHandlers().iterator();
					// scans all handlers
					while (iterator.hasNext()) {
						// gets the handler
						IsCallbackPropertyHandler handler = iterator.next();
						// invokes the removing of the scope
						// and checks if it must be removed
						// because the handler does not have any relation with charts
						if (!handler.removeChartScope(chart.getId())) {
							// removes the deferred action
							iterator.remove();
						}
					}
				});
			}
		}

	}

}
