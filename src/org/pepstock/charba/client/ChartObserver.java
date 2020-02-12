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
package org.pepstock.charba.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.pepstock.charba.client.dom.BaseElement;
import org.pepstock.charba.client.dom.BaseHtmlElement;
import org.pepstock.charba.client.dom.BaseNode;
import org.pepstock.charba.client.dom.DOM;
import org.pepstock.charba.client.dom.MutationObserver;
import org.pepstock.charba.client.dom.MutationObserverInit;
import org.pepstock.charba.client.dom.NodeList;

/**
 * Internal singleton utility which provides the ability to watch for attach and detach changes being made to the DOM tree for a
 * chart instance.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class ChartObserver {
	// singleton instance
	private static final ChartObserver INSTANCE = new ChartObserver();
	// maps of all mutation handlers
	// K = chart id, V = chart instance
	private final Map<String, MutationHandler> handlers = new HashMap<>();

	/**
	 * TO avoid any instantiation
	 */
	private ChartObserver() {
		// creates a mutation observer
		MutationObserver mutationObserver = new MutationObserver((mutationRecords, observer) -> {
			for (int i = 0; i < mutationRecords.length; i++) {
				// PAY ATTENTION that is mandatory to check BEFORE the detached nodes
				// and then the attached ones
				scanAndCheckElements(mutationRecords[i].getRemovedElements(), false);
				// scans all added elements
				scanAndCheckElements(mutationRecords[i].getAddedElements(), true);
			}
			// returns null because
			// don't create any new observer
			return null;
		});
		// creates initialization config for observer
		MutationObserverInit mutationObserverInit = MutationObserverInit.create();
		// sets child list mutation and subtree
		mutationObserverInit.setChildList(true);
		mutationObserverInit.setSubtree(true);
		// does not care about attributes of char data content updates
		mutationObserverInit.setAttributes(false);
		mutationObserverInit.setCharacterData(false);
		// starts observing on DOM document body
		mutationObserver.observe(DOM.getDocument().getBody(), mutationObserverInit);
	}

	/**
	 * Returns the singleton instance of observer.
	 * 
	 * @return the singleton instance of observer
	 */
	static ChartObserver get() {
		return INSTANCE;
	}

	/**
	 * Adds a mutation handler to notify when a chart will be attached or detached.
	 * 
	 * @param handler handler instance to store
	 */
	void addHandler(MutationHandler handler) {
		// checks if handler is consistent
		if (handler != null) {
			// stores the handler by its id
			handlers.put(handler.getId(), handler);
		}
	}

	/**
	 * Removes a mutation handler from notification list.
	 * 
	 * @param handler handler instance to store
	 */
	void removeHandler(MutationHandler handler) {
		// checks if handler is consistent
		if (handler != null) {
			// removes the handler by its id
			handlers.remove(handler.getId());
		}
	}

	/**
	 * Scans all elements to get which elements are attached or detached.
	 * 
	 * @param elements list of elements affected by attach or detach
	 * @param attach if <code>true</code>, checks for attached elements otherwise for detached ones
	 */
	private void scanAndCheckElements(List<BaseHtmlElement> elements, boolean attach) {
		// scans all elements
		for (BaseHtmlElement element : elements) {
			// scans recursively the elements tree
			scanAndCheckElements(element, attach);
		}
	}

	/**
	 * Scans recursively the DOM tree to get which elements are attached or detached.
	 * 
	 * @param parent parent element to scan
	 * @param attach if <code>true</code>, checks for attached elements otherwise for detached ones
	 */
	private void scanAndCheckElements(BaseElement parent, boolean attach) {
		// checks if parent has got children
		if (parent.getChildElementCount() > 0) {
			// gets all children nodes
			NodeList<BaseNode> children = parent.getChildNodes();
			// scans all children nodes
			for (int i = 0; i < children.length(); i++) {
				// gets node at index
				BaseNode childNode = children.item(i);
				// checks if is a element
				// charts are only element
				if (childNode instanceof BaseElement) {
					// casts to element
					BaseElement child = (BaseElement) childNode;
					// checks and perform attachment for child
					checkAndPerformAttachement(child, attach);
					// recursively scan all children
					scanAndCheckElements(child, attach);
				}
			}
		}
	}

	/**
	 * Checks if the element is related to a chart instance and if <code>true</code>, notify the chart of the attach or detach.
	 * 
	 * @param element element to check if is related to a chart
	 * @param attach if <code>true</code>, checks for attached elements otherwise for detached ones
	 */
	private void checkAndPerformAttachement(BaseElement element, boolean attach) {
		// checks if there is any chart with element id of child
		if (handlers.containsKey(element.getId())) {
			// gets mutation handler by element id
			MutationHandler handler = handlers.get(element.getId());
			// checks if is looking for attach
			// and the element is referring to a chart not attached yet
			if (attach && isChartElementAttached(element.getId())) {
				// invokes the attach method
				handler.onAttach();
			} else if (!attach && isChartElementDetached(element.getId())) {
				// checks if is looking for detach
				// and the element is referring to a chart is already attached
				// invokes the detach method
				handler.onDetach();
			}
		}
	}

	/**
	 * Returns <code>true</code> if the elements related to a chart has been attached.
	 * 
	 * @param elementId element id which should be the same of chart id
	 * @return <code>true</code> if the elements related to a chart has been attached
	 */
	private boolean isChartElementAttached(String elementId) {
		return !Charts.hasNative(elementId);
	}

	/**
	 * Returns <code>true</code> if the elements related to a chart has been detached.
	 * 
	 * @param elementId element id which should be the same of chart id
	 * @return <code>true</code> if the elements related to a chart has been detached
	 */
	private boolean isChartElementDetached(String elementId) {
		return Charts.hasNative(elementId) && DOM.getDocument().getElementById(elementId) == null;
	}

}
