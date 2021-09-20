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
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.pepstock.charba.client.Injector;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.utils.toast.ToastOptions.ProxyHandlerCallback;

/**
 * Utility for displaying toast notifications with progress bars on the page.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Toaster {

	// static instance for singleton
	private static final Toaster INSTANCE = new Toaster();
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
	// current open item
	private int currentOpenItems = 0;
	// max amount of open items
	private int maxOpenItems = Integer.MAX_VALUE;

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
		this.closeCallbackProxy.setCallback(item -> {
			// decrements the counter
			currentOpenItems--;
			// checks if open items are in wait
			if (!queueItems.isEmpty()) {
				// gets first
				AbstractReadOnlyToastOptions queuedItem = queueItems.remove(0);
				// shows item
				showToast(queuedItem);
			}
		});
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

	/**
	 * Creates and shows a toast configured by the passed options.
	 * 
	 * @param options configuration of the toast to show
	 * @return <code>true</code> if the toast has been shown
	 */
	public boolean show(ToastOptions options) {
		return showToast(options != null ? options : new ToastOptions());
	}

	/**
	 * Creates and shows a toast configured by the passed options.
	 * 
	 * @param options configuration of the toast to show
	 * @return <code>true</code> if the toast has been shown
	 */
	boolean showToast(AbstractReadOnlyToastOptions options) {
		// checks if argument is consistent
		if (options != null) {
			// checks if the toast can be show
			// because a maximum amount of toast is not reached
			if (currentOpenItems < maxOpenItems) {
				// shows the toast
				NativeObject result = NativeToasting.create(counter.getAndIncrement(), options.nativeObject());
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
				// sets if object is consistent
				boolean shown = result != null;
				// if shown, increment the counter of open items
				if (shown) {
					currentOpenItems++;
				}
				// returns if the toast is showing
				return shown;
			}
			// if here
			// maximum amount of toast items was reacher
			// then puts the toast options in a queue
			queueItems.add(options);
		}
		// if here, argument is not consistent
		// then return false
		return false;
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
	 * Returns the maximum amount of open toast items you want to maintain on DOM.
	 * 
	 * @return the maximum amount of open toast items you want to maintain on DOM
	 */
	public int getMaxOpenItems() {
		return maxOpenItems;
	}

	/**
	 * Sets the maximum amount of open toast items you want to maintain on DOM.
	 * 
	 * @param maxOpenItems the maximum amount of open toast items you want to maintain on DOM
	 */
	public void setMaxOpenItems(int maxOpenItems) {
		this.maxOpenItems = Checker.positiveOrZero(maxOpenItems);
	}

}