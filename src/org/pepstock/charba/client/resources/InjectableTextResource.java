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
package org.pepstock.charba.client.resources;

import org.pepstock.charba.client.commons.Key;

import com.google.gwt.resources.client.TextResource;

/**
 * Default implementation for an injectable resource related to a text resource.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class InjectableTextResource extends AbstractInjectableResource {

	/**
	 * Creates an injectable resources using the name of text resource and the text of it.
	 * 
	 * @param resource text resource to be injected.
	 */
	public InjectableTextResource(TextResource resource) {
		this(resource != null ? resource.getName() : null, resource);
		// checks if the name is not overriding the charba resource name
		// scans resource names
		for (ResourceName name : ResourceName.values()) {
			// checks if name of resource is equals to a reserved name
			if (name.value().equalsIgnoreCase(resource.getName())) {
				// throws an exception
				throw new IllegalArgumentException("Resource name '"+resource.getName()+"' is not allowed because is reserved");
			}
		}
	}

	/**
	 * CCreates an injectable resources using the text of it and the name as key, passed as argument.
	 * 
	 * @param key name of injectable resource as key , should be a unique value in the DOM.
	 * @param content content of object to be injected
	 */
	InjectableTextResource(Key key, TextResource resource) {
		super(Key.checkAndGetIfValid(key), resource != null ? resource.getText() : null);
	}

	/**
	 * CCreates an injectable resources using the text of it and the name, passed as argument.
	 * 
	 * @param name name of injectable resource, should be a unique value in the DOM.
	 * @param content content of object to be injected
	 */
	InjectableTextResource(String name, TextResource resource) {
		super(name, resource != null ? resource.getText() : null);
	}

}
