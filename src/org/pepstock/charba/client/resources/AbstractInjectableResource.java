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
package org.pepstock.charba.client.resources;

import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.utils.Hasher;

/**
 * Defines an object which must inject script or CSS style objects in the DOM document, at runtime.<br>
 * It must have a name and should be a unique value because it will be used to set the element (script or style) id.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public abstract class AbstractInjectableResource {

	// name of resources
	private final String name;
	// context to inject
	private final StringBuilder builder = new StringBuilder();

	/**
	 * Creates the resource with a mandatory name as key and the content of injectable resource.
	 * 
	 * @param key name of resource as key
	 * @param content content of resource to be injected
	 */
	protected AbstractInjectableResource(Key key, String... content) {
		this(Key.checkAndGetIfValid(key).value(), content);
	}

	/**
	 * Creates the resource with a mandatory name as string and the content of injectable resource.
	 * 
	 * @param name name of resource as string
	 * @param content content of resource to be injected
	 */
	protected AbstractInjectableResource(String name, String... content) {
		// checks if name is consistent
		// stores name
		this.name = Checker.checkAndGetIfValid(name, "Name");
		// checks length
		Checker.checkIfNotEqualTo(this.name.trim().length(), 0, "Name length");
		// checks if content is consistent
		Checker.checkIfValid(content, "Content");
		Checker.checkIfNotEqualTo(content.length, 0, "Content size");
		// checks if not an internal CHARBA class for deferred resources and
		// the internals do not need any check because managed by CHARBA and correct by definition
		if (!(this instanceof IsInternalInjectableTextResource)) {
			// checks if the name of resource is a CHARBA one
			ResourceName resourceName = Key.getKeyByValue(ResourceName.values(), name);
			// checks if it is a CHARBA resource and if it can be override
			if (resourceName != null && !resourceName.isOverride() && ResourceHash.hash(resourceName) != Hasher.hash(content)) {
				// if here the injectable resource is not a CHARBA class
				// but try to override with a custom
				throw new IllegalArgumentException("Unable to override '" + resourceName.value() + "' with a custom implementation. ");
			}
		}
		// scans the array
		for (String line : content) {
			// appending the strings in the a builder
			builder.append(line);
		}
	}

	/**
	 * Returns the content of the item to inject.
	 * 
	 * @return the content of the item to inject
	 */
	public final String getContent() {
		return builder.toString();
	}

	/**
	 * Returns the name of the object.
	 * 
	 * @return the name of object
	 */
	public final String getName() {
		return name;
	}

}