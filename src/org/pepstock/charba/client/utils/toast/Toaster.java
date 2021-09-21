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
package org.pepstock.charba.client.utils.toast;

import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.pepstock.charba.client.Injector;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.CallbackProxy.Proxy;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.utils.toast.ToastOptions.ProxyHandlerCallback;
import org.pepstock.charba.client.utils.toast.enums.MaximumOpenItemsPolicy;
import org.pepstock.charba.client.utils.toast.enums.Status;

/**
 * Utility for displaying toast notifications with progress bars on the page.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Toaster {

	// static instance for singleton
	private static final Toaster INSTANCE = new Toaster();
	/**
	 * Maximum amount of open and shown toasts.
	 */
	public static final int MAXIMUM_OPEN_ITEMS = 100;
	// injectable JS resource for toasting
	private final ToastJsUtilResource jsResource = new ToastJsUtilResource();
	// injectable CSS resource for toasting
	private final ToastCssUtilResource cssResource = new ToastCssUtilResource();
	// internal counter for element id
	private final AtomicInteger counter = new AtomicInteger();
	// instance of defaults
	private final ImmutableToastOptions defaults;
	// instance of overrides
	private final DefaultToastOptions overrides;
	// historical cache of toast items
	private final List<ToastItem> historyItems = new LinkedList<>();
	// max amount of history items
	private int maxHistoryItems = 0;
	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the close function
	private final CallbackProxy<ProxyHandlerCallback> closeCallbackProxy = JsHelper.get().newCallbackProxy();
	// list of items in queue
	private final List<AbstractReadOnlyToastOptions> queueItems = new LinkedList<>();
	// max amount of open items
	private int maxOpenItems = MAXIMUM_OPEN_ITEMS;
	// maximum items policy
	private MaximumOpenItemsPolicy policy = MaximumOpenItemsPolicy.QUEUE;

	/**
	 * To avoid any instantiation
	 */
	private Toaster() {
		// to be sure that CHARBA java script object is injected
		JsHelper.get();
		// injects CSS of toasting
		Injector.ensureCssInjected(cssResource);
		// injects JS of toasting
		Injector.ensureInjected(jsResource);
		// loads defaults
		this.defaults = new ImmutableToastOptions(NativeToasting.getDefaults());
		// loads overrides
		this.overrides = new DefaultToastOptions(NativeToasting.getOverrides(), defaults);
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// sets function to proxy callback in order to invoke the java interface
		this.closeCallbackProxy.setCallback(item -> checkAndCleanQueue());
	}

	/**
	 * Singleton object to get the toaster instance
	 * 
	 * @return toaster instance.
	 */
	public static Toaster get() {
		return INSTANCE;
	}

	/**
	 * Returns the read-only defaults of toast.
	 * 
	 * @return the read-only defaults of toast
	 */
	ImmutableToastOptions getReadOnlyDefaults() {
		return defaults;
	}

	/**
	 * Returns the defaults of toast.
	 * 
	 * @return the defaults of toast
	 */
	public DefaultToastOptions getDefaults() {
		return overrides;
	}

	/**
	 * Creates and shows a toast configured by the passed options.
	 * 
	 * @param options configuration of the toast to show
	 * @return the status if the toast has been shown
	 */
	public Status show(ToastOptions options) {
		return showToast(options);
	}

	/**
	 * Creates and shows a toast configured by the passed options.
	 * 
	 * @param options configuration of the toast to show
	 * @return the status if the toast has been shown
	 */
	Status showToast(AbstractReadOnlyToastOptions options) {
		// checks if argument is consistent
		if (options != null) {
			// checks if the toast can be show
			// because a maximum amount of toast is not reached
			if (NativeToasting.getCurrentOpenItems() < maxOpenItems) {
				// shows the toast
				NativeObject result = NativeToasting.create(counter.getAndIncrement(), options.nativeObject());
				// checks if result is consistent
				if (result == null) {
					// sets invalid status
					options.setStatus(Status.INVALID);
					// stores history
					storeHistory(options.nativeObject());
					// returns invalid
					return Status.INVALID;
				} else {
					// stores history
					storeHistory(result);
					// returns the status
					return Status.SHOWING;
				}
			}
			// if here
			// maximum amount of toast items was reacher
			return manageQueue(options);
		}
		// if here, argument is not consistent
		// then return false
		return Status.INVALID;
	}

	/**
	 * Returns the maximum amount of toast items you want to maintain in the history.
	 * 
	 * @return the maximum amount of toast items you want to maintain in the history
	 */
	public int getMaxHistoryItems() {
		return maxHistoryItems;
	}

	/**
	 * Sets the maximum amount of toast items you want to maintain in the history.
	 * 
	 * @param maxHistoryItems the maximum amount of toast items you want to maintain in the history
	 */
	public void setMaxHistoryItems(int maxHistoryItems) {
		// stores the value
		this.maxHistoryItems = Checker.positiveOrZero(maxHistoryItems);
		// cleans the history accordingly
		checkAndCleanHistory();
	}

	/**
	 * Returns the list of toast items in the history.
	 * 
	 * @return the list of toast items in the history
	 */
	public List<ToastItem> getHistoryItems() {
		return Collections.unmodifiableList(historyItems);
	}

	/**
	 * Returns the policy to apply when the maximum amount of open items is reached.
	 * 
	 * @return the policy to apply when the maximum amount of open items is reached
	 */
	public MaximumOpenItemsPolicy getMaxOpenItemsPolicy() {
		return policy;
	}

	/**
	 * Sets the policy to apply when the maximum amount of open items is reached.
	 * 
	 * @param policy the policy to apply when the maximum amount of open items is reached
	 */
	public void setMaxOpenItemsPolicy(MaximumOpenItemsPolicy policy) {
		this.policy = policy == null ? MaximumOpenItemsPolicy.QUEUE : policy;
		// setup queue configuration
		setupQueueConfiguration();
	}

	/**
	 * Returns the maximum amount of open toast items you want to maintain on DOM.
	 * 
	 * @return the maximum amount of open toast items you want to maintain on DOM
	 */
	public int getMaxOpenItems() {
		return maxOpenItems;
	}

	/**
	 * Sets the maximum amount of open toast items you want to maintain on DOM.<br>
	 * Maximum value is {@value Toaster#MAXIMUM_OPEN_ITEMS}.
	 * 
	 * @param maxOpenItems the maximum amount of open toast items you want to maintain on DOM
	 */
	public void setMaxOpenItems(int maxOpenItems) {
		this.maxOpenItems = Checker.betweenOrMaximum(maxOpenItems, 1, MAXIMUM_OPEN_ITEMS);
		// setup queue configuration
		setupQueueConfiguration();
	}

	// ---------------------------------
	// internal methods
	// ---------------------------------

	/**
	 * Manages the options if it must put in the queue or discard.
	 * 
	 * @param options configuration of the toast to queue or discard
	 * @return the status if the toast has been shown
	 */
	private Status manageQueue(AbstractReadOnlyToastOptions options) {
		// checks if it must queue or discard
		if (MaximumOpenItemsPolicy.QUEUE.equals(getMaxOpenItemsPolicy())) {
			// then puts the toast options in a queue
			// checks if the instance is a normal options
			// if yes, it clones the instance
			// because it can be changed in the meantime
			if (options instanceof ToastOptions) {
				// casts to normal options
				ToastOptions normalOptions = (ToastOptions) options;
				// clone object
				ToastOptions clonedOptions = new ToastOptions(normalOptions);
				// stores queue time
				clonedOptions.setQueueDateTime(new Date());
				queueItems.add(clonedOptions);
			} else {
				// stores queue time
				options.setQueueDateTime(new Date());
				queueItems.add(options);
			}
			// returns queued
			return Status.QUEUED;
		} else {
			// here is discarded
			// sets status
			options.setStatus(Status.DISCARDED);
			// but store to history
			storeHistory(options.nativeObject());
			// returns queued
			return Status.DISCARDED;
		}
	}

	/**
	 * Checks and cleans the history when the max amount of items is changed.
	 */
	private void checkAndCleanHistory() {
		// cycles to remove useless items
		while (historyItems.size() > maxHistoryItems) {
			// removes the last
			historyItems.remove(historyItems.size() - 1);
		}
	}

	/**
	 * Setup the queue configuration.
	 */
	private void setupQueueConfiguration() {
		// checks policy type
		if (MaximumOpenItemsPolicy.QUEUE.equals(getMaxOpenItemsPolicy())) {
			// checks if the handler must be set
			Proxy proxy = this.maxHistoryItems < MAXIMUM_OPEN_ITEMS ? closeCallbackProxy.getProxy() : null;
			// stores handler
			NativeToasting.setInternalCloseHandler(proxy);
			// checks the queue
			checkAndCleanQueue();
		} else {
			// resets handler
			NativeToasting.setInternalCloseHandler(null);
			// clear the queue
			queueItems.clear();
		}
	}

	/**
	 * Checks and cleans the queue when the max open items is changed.
	 */
	private void checkAndCleanQueue() {
		// cycles to remove useless items
		while (NativeToasting.getCurrentOpenItems() < maxOpenItems && !queueItems.isEmpty()) {
			// gets first
			AbstractReadOnlyToastOptions queuedItem = queueItems.remove(0);
			// shows item
			showToast(queuedItem);
		}
	}

	/**
	 * Stores in the history the toast.
	 * 
	 * @param result the native object of the toast to store
	 */
	private void storeHistory(NativeObject result) {
		// checks if consistent and
		// the user wants to have an history
		if (result != null && maxHistoryItems > 0) {
			// gets the toast item
			ToastItem item = new ToastItem(result);
			// checks if it must be removed the older item
			if (!historyItems.isEmpty() && historyItems.size() >= maxHistoryItems) {
				// removes the last
				historyItems.remove(historyItems.size() - 1);
			}
			// stores in the history
			// always at the beginning of list
			historyItems.add(0, item);
		}
	}

}