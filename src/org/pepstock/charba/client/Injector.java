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

import java.util.HashSet;
import java.util.Set;

import org.pepstock.charba.client.dom.BaseHtmlElement;
import org.pepstock.charba.client.dom.DOM;
import org.pepstock.charba.client.dom.DOMBuilder;
import org.pepstock.charba.client.dom.elements.Script;
import org.pepstock.charba.client.dom.elements.Style;
import org.pepstock.charba.client.dom.elements.TextNode;
import org.pepstock.charba.client.resources.AbstractInjectableResource;

/**
 * This utility injects ChartJS java script and CHARBA custom java script implementation (for some utilities) into the web page of application, into the HEAD.<br>
 * It enables also to inject other script into web page, necessary when you want to use some Chart.JS extensions.<br>
 * It tracks the resources which have been injected using as key their name and class name to avoid that however will inject own resources will use the same name of already
 * injected resources.
 * 
 * @author Andrea "Stock" Stocchero
 * 
 */
public final class Injector {

	// Prefix ID of injected script elements
	private static final String CHARBA_PREFIX_SCRIPT_ELEMENT_ID = "_charba_";
	// contains all script object injected
	private static final Set<String> ELEMENTS_INJECTED = new HashSet<>();

	/**
	 * To avoid any instantiation
	 */
	private Injector() {
	}

	/**
	 * Injects a script resource if not injected yet.
	 * 
	 * @param resource script resource
	 */
	public static void ensureInjected(AbstractInjectableResource resource) {
		// checks if resource is consistent
		if (resource != null) {
			// creates a script element
			Script scriptElement = DOMBuilder.get().createScriptElement();
			// injects it into SCRIPT element
			ensureInjected(resource, scriptElement);
		}
	}

	/**
	 * Injects a CSS style resource if not injected yet.
	 * 
	 * @param resource CSS style resource
	 */
	public static void ensureCssInjected(AbstractInjectableResource resource) {
		// checks if resource is consistent
		if (resource != null) {
			// creates a style element
			Style styleElement = DOMBuilder.get().createStyleElement();
			// injects it into STYLE element
			ensureInjected(resource, styleElement);
		}
	}

	/**
	 * Returns <code>true</code> if the script or style resource has been already injected.
	 * 
	 * @param resource script or CSS style resource
	 * @return <code>true</code> if the script or style resource has been already injected
	 */
	public static boolean isInjected(AbstractInjectableResource resource) {
		// checks if resource is consistent
		if (resource != null) {
			// creates a unique key for the resource
			// to use to understand if is already injected
			String resourceKey = createKey(resource);
			// returns if already injected
			return ELEMENTS_INJECTED.contains(resourceKey);
		}
		// if here the resource is not consistent
		// then returns false
		return false;
	}

	/**
	 * Injects a script resource if not injected yet.
	 * 
	 * @param resource script resource
	 * @param container HTML element which will contains the text resource
	 */
	private static void ensureInjected(AbstractInjectableResource resource, BaseHtmlElement container) {
		// creates a unique key for the resource
		// to use to understand if is already injected
		String resourceKey = createKey(resource);
		// checks if already injected
		if (!ELEMENTS_INJECTED.contains(resourceKey)) {
			// sets ID
			container.setId(CHARBA_PREFIX_SCRIPT_ELEMENT_ID + resourceKey);
			// creates a node to add the content of resource
			TextNode node = DOMBuilder.get().createTextNode(resource.getContent());
			// sets the script content source
			container.appendChild(node);
			// appends to the head
			DOM.getDocument().getHead().appendChild(container);
			ELEMENTS_INJECTED.add(resourceKey);
		}
	}

	/**
	 * Creates a unique key for every single resource type to be injected.<br>
	 * The key is [CHARBA_prefix_package_name].[resource name] if the resource is a CHARBA one.<br>
	 * The key is [resource_class_name]_[resource name] if the resource is not provided by CHARBA.<br>
	 * 
	 * @param resource resource instance to to create the key
	 * @return a unique key for every single resource type
	 */
	private static final String createKey(AbstractInjectableResource resource) {
		// CHARBA resource
		return InjectorPrefixHelper.get().getPrefixPackageName(resource) + resource.getName();
	}
}