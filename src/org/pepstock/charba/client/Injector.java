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

import org.pepstock.charba.client.commons.Constants;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.ScriptElement;
import com.google.gwt.dom.client.StyleElement;
import com.google.gwt.resources.client.ResourcePrototype;
import com.google.gwt.resources.client.TextResource;

/**
 * This utility injects ChartJS java script and CHARBA custom java script implementation (for some utilities) into the web page
 * of GWT, into the HEAD.<br>
 * It enables also to inject other script into web page, necessary when you want to use some Chart.JS extensions.<br>
 * It tracks the resources which have been injected using as key their name and class name to avoid that however will inject own
 * resources will use the same name of already injected resources.
 * 
 * @author Andrea "Stock" Stocchero
 * 
 */
public final class Injector {

	// singleton instance created even if is not a singleton
	// in order to manage a field with the package name of this class
	// needed to improve the key calculation
	private static final Injector INSTANCE = new Injector(); 
	// Prefix ID of injected script elements
	private static final String CHARBA_PREFIX_SCRIPT_ELEMENT_ID = "_charba_";
	// contains all script object injected
	private static final Set<String> ELEMENTS_INJECTED = new HashSet<>();
	// package name to use to calculate the key of injected resources
	private final String charbaPrefixPackageName;

	/**
	 * To avoid any instantiation
	 */
	private Injector() {
		// gets full class name
		String fullClassName = getClass().getName();
		// gets short class name
		String shortClassname = getClass().getSimpleName();
		// stores the prefix package name
		charbaPrefixPackageName = fullClassName.substring(0, fullClassName.indexOf(shortClassname));
	}

	/**
	 * Injects a script resource if not injected yet.
	 * 
	 * @param resource script resource
	 */
	public static void ensureInjected(ResourcePrototype resource) {
		// checks if resource is consistent
		if (resource != null) {
			// creates a script element
			ScriptElement scriptElement = Document.get().createScriptElement();
			// injects it into SCRIPT element
			ensureInjected(resource, scriptElement);
		}
	}

	/**
	 * Injects a CSS style resource if not injected yet.
	 * 
	 * @param resource CSS style resource
	 */
	public static void ensureCssInjected(ResourcePrototype resource) {
		// checks if resource is consistent
		if (resource != null) {
			// creates a style element
			StyleElement styleElement = Document.get().createStyleElement();
			// injects it into STYLE element
			ensureInjected(resource, styleElement);
		}
	}

	/**
	 * Injects a script resource if not injected yet.
	 * 
	 * @param resource script resource
	 */
	private static void ensureInjected(ResourcePrototype resource, Element container) {
		// creates a unique key for the resource
		// to use to understand if is already injected
		String resourceKey = createKey(resource);
		// checks if already injected
		if (!ELEMENTS_INJECTED.contains(resourceKey)) {
			if (resource instanceof TextResource) {
				TextResource textResource = (TextResource) resource;
				// sets ID
				container.setId(CHARBA_PREFIX_SCRIPT_ELEMENT_ID + resourceKey);
				// sets the script content source
				container.setInnerText(textResource.getText());
				// appends to the head
				Document.get().getHead().appendChild(container);
			}
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
	private static final String createKey(ResourcePrototype resource) {
		// gets the full class name of resource
		String fullResourceClassName = resource.getClass().getName();
		// checks if the resource name is a CHARBA one
		// using the prefix package name
		if (fullResourceClassName.startsWith(INSTANCE.charbaPrefixPackageName)) {
			// CHARBA resource
			return INSTANCE.charbaPrefixPackageName + resource.getName();	
		} else {
			// not CHARBA resource
			return fullResourceClassName + Constants.UNDERSCORE + resource.getName();	
		}
		
	}
}