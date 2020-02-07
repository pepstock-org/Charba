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
package org.pepstock.charba.client.dom;

import jsinterop.annotations.JsFunction;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * Provides the ability to watch for changes being made to the DOM tree.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL)
public final class MutationObserver {

	// ---------------------------
	// -- JAVASCRIPT FUNCTIONS ---
	// ---------------------------

	/**
	 * A function which will be called on each DOM change that qualifies given the targeted node or subtree and options.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	@JsFunction
	public interface MutationObserverCallback {
		
		/**
		 * The callback function takes as input two parameters:<br>
		 * <ul>
		 * <li> an array of {@link MutationRecord} objects describing each change that occurred
		 * <li> the {@link MutationObserver} which invoked the callback.
		 * </ul>
		 * 
		 * @param mutationRecords an array of {@link MutationRecord} objects describing each change that occurred
		 * @param observer the {@link MutationObserver} which invoked the callback
		 * @return a new {@link MutationObserver} object configured to call the specified callback when DOM mutations occur.
		 */
		Object onInvoke(MutationRecord[] mutationRecords, MutationObserver observer);
	}

	/**
	 * Creates and returns a new mutation observer which will invoke a specified callback function when DOM changes occur.
	 * 
	 * @param callback a function which will be called on each DOM change that qualifies given the targeted node or subtree and
	 *            options
	 */
	public MutationObserver(MutationObserver.MutationObserverCallback callback) {
		// do nothing
	}

	/**
	 * Stops the mutation observer instance from receiving further notifications until and unless
	 * {@link MutationObserver#observe(BaseNode)} is called again.
	 */
	public native void disconnect();

	/**
	 * Configures the mutation observer to begin receiving notifications through its callback function when DOM changes matching
	 * the given options occur.
	 * 
	 * @param target a DOM node within the DOM tree to watch for changes, or to be the root of a subtree of nodes to be watched
	 * @param options initialization object providing options that describe what DOM mutations should be reported to the
	 *            observer's callback
	 */
	public native void observe(BaseNode target, MutationObserverInit options);

	/**
	 * Configures the mutation observer to begin receiving notifications through its callback function when DOM changes matching
	 * the given options occur.
	 * 
	 * @param target a DOM node within the DOM tree to watch for changes, or to be the root of a subtree of nodes to be watched
	 */
	public native void observe(BaseNode target);

	/**
	 * Removes all pending notifications from the mutation observer's notification queue and returns them in a new array of
	 * mutation record objects.
	 * 
	 * @return all pending notifications from the mutation observer's notification queue
	 */
	public native MutationRecord[] takeRecords();
}
