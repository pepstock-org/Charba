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
package org.pepstock.charba.client.gwt;

import org.pepstock.charba.client.resources.InjectableResource;

import com.google.gwt.resources.client.TextResource;

/**
 * Default implementation for an injectable resource related to a text resource.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class InjectableTextResource extends InjectableResource {

	/**
	 * Creates an injectable resources using the name of text resource and the text of it.
	 * 
	 * @param resource text resource to be injected.
	 */
	public InjectableTextResource(TextResource resource) {
		this(resource.getName(), resource);
	}

	/**
	 * Creates an injectable resources using the text of it and the name, passed as argument.
	 * 
	 * @param name name of injectable resource, should be a unique value in the DOM.
	 * @param resource text resource to be injected.
	 */
	InjectableTextResource(String name, TextResource resource) {
		super(name, resource.getText());
	}

}
