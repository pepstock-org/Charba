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

/**
 * Defines an object which must inject script or CSS style objects into DOM document, at runtime.<br>
 * It must have a name and should be a unique value because it will be used to set the element (script or style) id.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public abstract class InjectableResource {

	// name of resources
	private final String name;
	// context to inject
	private final StringBuilder builder = new StringBuilder();

	/**
	 * Creates the object with a mandatory name as string and the content of injectable resource.
	 * 
	 * @param name name of object as string
	 * @param content content of object to be injected
	 */
	protected InjectableResource(String name, String... content) {
		// checks if name is consistent
		if (name == null) {
			// if not exception
			throw new IllegalArgumentException("Name is null");
		}
		// checks if name is consistent
		if (content == null || content.length == 0) {
			// if not exception
			throw new IllegalArgumentException("Content is not consistent");
		}
		// stores the name
		this.name = name;
		// scans the array
		for (String line : content) {
			// appending the strings into a builder
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
