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
 * FIXME
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class ChartObserver {

	private static final ChartObserver INSTANCE = new ChartObserver();

	private final Map<String, MutationHandler> handlers = new HashMap<>();

	private ChartObserver() {
		MutationObserver mutationObserver = new MutationObserver((mutationRecords, observer) -> {
			for (int i = 0; i < mutationRecords.length; i++) {
				// PAY ATTENTION that is mandatory to check BEFORE the detached nodes
				// and then the attached ones
				scanAndCheckElements(mutationRecords[i].getRemovedElements(), false);
				scanAndCheckElements(mutationRecords[i].getAddedElements(), true);
			}
			return null;
		});
		MutationObserverInit mutationObserverInit = MutationObserverInit.create();
		mutationObserverInit.setChildList(true);
		mutationObserverInit.setAttributes(false);
		mutationObserverInit.setCharacterData(false);
		mutationObserverInit.setSubtree(true);
		mutationObserver.observe(DOM.getDocument().getBody(), mutationObserverInit);
	}

	static ChartObserver get() {
		return INSTANCE;
	}

	/**
	 * Checks if the observer has already been started, if not it will start it. Then register and callback when the element is
	 * attached to the DOM.
	 * 
	 * @param handler
	 */
	void addHandler(MutationHandler handler) {
		// checks if handler is consistent
		if (handler != null) {
			handlers.put(handler.getId(), handler);
		}
	}

	/**
	 * Checks if the observer has already been started, if not it will start it. Then register and callback when the element is
	 * attached to the DOM.
	 */
	void removeHandler(MutationHandler handler) {
		// checks if handler is consistent
		if (handler != null) {
			handlers.remove(handler.getId());
		}
	}

	private void scanAndCheckElements(List<BaseHtmlElement> elements, boolean attach) {
		for (BaseHtmlElement element : elements) {
			scanAndCheckElements(element, attach);
		}
	}

	private void scanAndCheckElements(BaseElement parent, boolean attach) {
		if (parent.getChildElementCount() > 0) {
			NodeList<BaseNode> children = parent.getChildNodes();
			for (int i = 0; i < children.length(); i++) {
				BaseNode childNode = children.item(i);
				if (childNode instanceof BaseElement) {
					BaseElement child = (BaseElement) childNode;
					if (handlers.containsKey(child.getId())) {
						MutationHandler handler = handlers.get(child.getId());
						if (attach && isChartElementAttached(child.getId())) {
							handler.onAttach();
						} else if (!attach && isChartElementDetached(child.getId())) {
							handler.onDetach();
						}
					}
					scanAndCheckElements(child, attach);
				}
			}
		}
	}

	private boolean isChartElementAttached(String elementId) {
		return !Charts.hasNative(elementId);
	}

	private boolean isChartElementDetached(String elementId) {
		return Charts.hasNative(elementId) && DOM.getDocument().getElementById(elementId) == null;
	}

}
