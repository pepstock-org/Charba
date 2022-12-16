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
package org.pepstock.charba.client.utils.toast;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.pepstock.charba.client.Injector;
import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.CallbackProxy;
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

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the close function
	private final CallbackProxy<ProxyHandlerCallback> closeCallbackProxy = JsHelper.get().newCallbackProxy();

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
	// stores the current toast items
	private final Map<Integer, ToastItem> currentOpenItems = new HashMap<>();
	// stores the current toast contexts
	private final Map<Integer, Map<String, Object>> currentContexts = new HashMap<>();
	// historical cache of toast items
	private final List<ToastItem> historyItems = new LinkedList<>();
	// max amount of history items
	private int maxHistoryItems = 0;
	// list of items in queue
	private final List<ToastItem> queueItems = new LinkedList<>();
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
		this.closeCallbackProxy.setCallback(this::closeActiveToast);
		// stores handler
		NativeToasting.setInternalCloseHandler(closeCallbackProxy.getProxy());
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

	// ---------------------------------
	// SHOW toast without CONTEXT
	// ---------------------------------

	/**
	 * Creates and shows a toast configured by the passed options.
	 * 
	 * @param label label of the toast
	 * @return the status if the toast has been shown
	 */
	public Status show(String... label) {
		return show((ToastOptions) null, label);
	}

	/**
	 * Creates and shows a toast configured by the passed options.
	 * 
	 * @param label label of the toast
	 * @return the status if the toast has been shown
	 */
	public Status show(List<String> label) {
		return show((ToastOptions) null, label);
	}

	/**
	 * Creates and shows a toast configured by the passed options.
	 * 
	 * @param type type of the toast
	 * @param label label of the toast
	 * @return the status if the toast has been shown
	 */
	public Status show(IsToastType type, String... label) {
		return show(ToastOptionsBuilder.create(type).build(), label);
	}

	/**
	 * Creates and shows a toast configured by the passed options.
	 * 
	 * @param type type of the toast
	 * @param label label of the toast
	 * @return the status if the toast has been shown
	 */
	public Status show(IsToastType type, List<String> label) {
		return show(type, null, label);
	}

	/**
	 * Creates and shows a toast configured by the passed options.
	 * 
	 * @param options configuration of the toast to show
	 * @param label label of the toast
	 * @return the status if the toast has been shown
	 */
	public Status show(ToastOptions options, String... label) {
		return show(options, null, ArrayString.fromOrNull(label), null, null);
	}

	/**
	 * Creates and shows a toast configured by the passed options.
	 * 
	 * @param options configuration of the toast to show
	 * @param label label of the toast
	 * @return the status if the toast has been shown
	 */
	public Status show(ToastOptions options, List<String> label) {
		return show(options, null, label);
	}

	/**
	 * Creates and shows a toast with title and label.
	 * 
	 * @param title title of the toast
	 * @param label label of the toast
	 * @return the status if the toast has been shown
	 */
	public Status show(String title, String label) {
		return show((ToastOptions) null, title, label);
	}

	/**
	 * Creates and shows a toast with title and label.
	 * 
	 * @param title title of the toast
	 * @param label label of the toast
	 * @return the status if the toast has been shown
	 */
	public Status show(String title, List<String> label) {
		return show((ToastOptions) null, title, label);
	}

	/**
	 * Creates and shows a toast with title and label.
	 * 
	 * @param type type of the toast
	 * @param title title of the toast
	 * @param label label of the toast
	 * @return the status if the toast has been shown
	 */
	public Status show(IsToastType type, String title, String label) {
		return show(ToastOptionsBuilder.create(type).build(), title, label);
	}

	/**
	 * Creates and shows a toast with title and label.
	 * 
	 * @param type type of the toast
	 * @param title title of the toast
	 * @param label label of the toast
	 * @return the status if the toast has been shown
	 */
	public Status show(IsToastType type, String title, List<String> label) {
		return show(ToastOptionsBuilder.create(type).build(), title, label);
	}

	/**
	 * Creates and shows a toast configured by the passed options.
	 * 
	 * @param options configuration of the toast to show
	 * @param title title of the toast
	 * @param label label of the toast
	 * @return the status if the toast has been shown
	 */
	public Status show(ToastOptions options, String title, String label) {
		return show(options, title, ArrayString.fromOrNull(label), null, null);
	}

	/**
	 * Creates and shows a toast configured by the passed options.
	 * 
	 * @param options configuration of the toast to show
	 * @param title title of the toast
	 * @param label label of the toast
	 * @return the status if the toast has been shown
	 */
	public Status show(ToastOptions options, String title, List<String> label) {
		return show(options, title, ArrayString.fromOrNull(label), null, null);
	}

	// ---------------------------------
	// SHOW toast with CONTEXT
	// ---------------------------------

	/**
	 * Creates and shows a toast configured by the passed options.
	 * 
	 * @param context context of toast execution
	 * @param label label of the toast
	 * @return the status if the toast has been shown
	 */
	public Status show(Map<String, Object> context, String... label) {
		return show(context, (ToastOptions) null, label);
	}

	/**
	 * Creates and shows a toast configured by the passed options.
	 * 
	 * @param context context of toast execution
	 * @param label label of the toast
	 * @return the status if the toast has been shown
	 */
	public Status show(Map<String, Object> context, List<String> label) {
		return show(context, (ToastOptions) null, label);
	}

	/**
	 * Creates and shows a toast configured by the passed options.
	 * 
	 * @param context context of toast execution
	 * @param type type of the toast
	 * @param label label of the toast
	 * @return the status if the toast has been shown
	 */
	public Status show(Map<String, Object> context, IsToastType type, String... label) {
		return show(context, ToastOptionsBuilder.create(type).build(), label);
	}

	/**
	 * Creates and shows a toast configured by the passed options.
	 * 
	 * @param context context of toast execution
	 * @param type type of the toast
	 * @param label label of the toast
	 * @return the status if the toast has been shown
	 */
	public Status show(Map<String, Object> context, IsToastType type, List<String> label) {
		return show(context, type, null, label);
	}

	/**
	 * Creates and shows a toast configured by the passed options.
	 * 
	 * @param context context of toast execution
	 * @param options configuration of the toast to show
	 * @param label label of the toast
	 * @return the status if the toast has been shown
	 */
	public Status show(Map<String, Object> context, ToastOptions options, String... label) {
		return show(options, null, ArrayString.fromOrNull(label), null, context);
	}

	/**
	 * Creates and shows a toast configured by the passed options.
	 * 
	 * @param context context of toast execution
	 * @param options configuration of the toast to show
	 * @param label label of the toast
	 * @return the status if the toast has been shown
	 */
	public Status show(Map<String, Object> context, ToastOptions options, List<String> label) {
		return show(context, options, null, label);
	}

	/**
	 * Creates and shows a toast with title and label.
	 * 
	 * @param context context of toast execution
	 * @param title title of the toast
	 * @param label label of the toast
	 * @return the status if the toast has been shown
	 */
	public Status show(Map<String, Object> context, String title, String label) {
		return show(context, (ToastOptions) null, title, label);
	}

	/**
	 * Creates and shows a toast with title and label.
	 * 
	 * @param context context of toast execution
	 * @param title title of the toast
	 * @param label label of the toast
	 * @return the status if the toast has been shown
	 */
	public Status show(Map<String, Object> context, String title, List<String> label) {
		return show(context, (ToastOptions) null, title, label);
	}

	/**
	 * Creates and shows a toast with title and label.
	 * 
	 * @param context context of toast execution
	 * @param type type of the toast
	 * @param title title of the toast
	 * @param label label of the toast
	 * @return the status if the toast has been shown
	 */
	public Status show(Map<String, Object> context, IsToastType type, String title, String label) {
		return show(context, ToastOptionsBuilder.create(type).build(), title, label);
	}

	/**
	 * Creates and shows a toast with title and label.
	 * 
	 * @param context context of toast execution
	 * @param type type of the toast
	 * @param title title of the toast
	 * @param label label of the toast
	 * @return the status if the toast has been shown
	 */
	public Status show(Map<String, Object> context, IsToastType type, String title, List<String> label) {
		return show(context, ToastOptionsBuilder.create(type).build(), title, label);
	}

	/**
	 * Creates and shows a toast configured by the passed options.
	 * 
	 * @param context context of toast execution
	 * @param options configuration of the toast to show
	 * @param title title of the toast
	 * @param label label of the toast
	 * @return the status if the toast has been shown
	 */
	public Status show(Map<String, Object> context, ToastOptions options, String title, String label) {
		return show(options, title, ArrayString.fromOrNull(label), null, context);
	}

	/**
	 * Creates and shows a toast configured by the passed options.
	 * 
	 * @param context context of toast execution
	 * @param options configuration of the toast to show
	 * @param title title of the toast
	 * @param label label of the toast
	 * @return the status if the toast has been shown
	 */
	public Status show(Map<String, Object> context, ToastOptions options, String title, List<String> label) {
		return show(options, title, ArrayString.fromOrNull(label), null, context);
	}

	// ---------------------------------
	// INTERNAL SHOW toast
	// ---------------------------------

	/**
	 * Creates and shows a toast configured by the passed options.
	 * 
	 * @param options configuration of the toast to show
	 * @param title title of the toast
	 * @param label label of the toast
	 * @param dateTime date time object to maintain when a toast item is showing from the queue
	 * @param context context of toast execution
	 * @return the status if the toast has been shown
	 */
	private Status show(ToastOptions options, String title, ArrayString label, NativeObject dateTime, Map<String, Object> context) {
		// checks and normalized the context
		final Map<String, Object> normalizedContext = context == null ? new HashMap<>() : context;
		// checks if the toast can be show
		// because a maximum amount of toast is not reached
		if (NativeToasting.getCurrentOpenItems() < maxOpenItems) {
			// gets id
			int id = counter.getAndIncrement();
			// stores context
			// it's storing here because it's needed for open handler
			// where toast item is not available
			currentContexts.put(id, normalizedContext);
			// shows the toast
			NativeObject result = NativeToasting.create(id, title, label, options != null ? options.nativeObject() : null, dateTime);
			// creates toast item
			ToastItem item = new ToastItem(result, options, normalizedContext);
			// stores in the current item
			currentOpenItems.put(item.getId(), item);
			// stores context
			// stores history
			storeHistory(item);
			// returns the status
			return item.getStatus();
		}
		// if here
		// maximum amount of toast items was reacher
		return manageQueue(options, title, label, normalizedContext);
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
	 * @param options configuration of the toast to show
	 * @param title title of the toast
	 * @param label label of the toast
	 * @param context context of toast execution
	 * @return the status if the toast has been shown
	 */
	private Status manageQueue(ToastOptions options, String title, ArrayString label, Map<String, Object> context) {
		// creates toast item
		ToastItem item = new ToastItem(options, context);
		// stores title and label
		item.setTitle(title);
		item.setLabel(label);
		// checks if it must queue or discard
		if (MaximumOpenItemsPolicy.QUEUE.equals(getMaxOpenItemsPolicy())) {
			item.setStatus(Status.QUEUED);
			queueItems.add(item);
		} else {
			// here is discarded
			// sets status
			item.setStatus(Status.DISCARDED);
			storeHistory(item);
		}
		return item.getStatus();
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
			// checks the queue
			checkAndCleanQueue();
		} else {
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
			ToastItem queuedItem = queueItems.remove(0);
			// shows item
			// PAY attention: it must pass also the date time object
			// to maintain the previous time
			show(queuedItem.getOptions().getDelegated(), queuedItem.getTitle(), ArrayString.fromOrNull(queuedItem.getLabel()), queuedItem.getDateTime(), queuedItem.getContext());
		}
	}

	/**
	 * Stores in the history the toast.
	 * 
	 * @param item the toast item to store
	 */
	private void storeHistory(ToastItem item) {
		// checks if consistent and
		// the user wants to have an history
		if (item != null && maxHistoryItems > 0) {
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

	/**
	 * Invoked when every toast has been closed, removing the item from the list of current open items, and checking the queue.
	 * 
	 * @param object toast item instance as native object
	 */
	private void closeActiveToast(NativeObject object) {
		// gets the id from toast item
		int id = JsHelper.get().getIntegerProperty(ToastItem.CommonProperty.ID, object);
		// removes from open items
		currentOpenItems.remove(id);
		// removes from context
		currentContexts.remove(id);
		// checks the queue
		checkAndCleanQueue();
	}

	/**
	 * Returns the {@link ToastItem}, currently open, by its id, or <code>null</code> if not in the map of open item.
	 * 
	 * @param id the id of toast item
	 * @return the {@link ToastItem}, currently open, by its id, or <code>null</code> if not in the map of open item
	 */
	ToastItem getCurrentOpenItem(int id) {
		return currentOpenItems.get(id);
	}

	/**
	 * Returns the toast context, currently open, by its id, or <code>null</code> if not in the map of contexts.
	 * 
	 * @param id the id of toast item
	 * @return the toast context, currently open, by its id, or <code>null</code> if not in the map of contexts
	 */
	Map<String, Object> getCurrentContext(int id) {
		return currentContexts.get(id);
	}

	/**
	 * Closes all items which are opened and not closed yet.<br>
	 * All toast items in queued will be removed.
	 */
	public void hideOpenItems() {
		hideOpenItems(true);
	}

	/**
	 * Closes all items which are opened and not closed yet.<br>
	 * If the passed argument is <code>true</code>, it clears also the queue, without showing the items.
	 * 
	 * @param clearQueue if <code>true</code>, it clears also the queue, without showing the items.
	 */
	public void hideOpenItems(boolean clearQueue) {
		// checks if the queue must be clear
		if (clearQueue) {
			// creates iterator
			Iterator<ToastItem> iterator = queueItems.iterator();
			// scans all open items
			while (iterator.hasNext()) {
				// gets item
				ToastItem item = iterator.next();
				// sets closed status
				item.setStatus(Status.NOT_SHOWED);
				// stores history
				storeHistory(item);
				// removes item
				iterator.remove();
			}
		}
		// scans all open items
		for (ToastItem item : currentOpenItems.values()) {
			// closes item
			item.hide();
		}
	}
}