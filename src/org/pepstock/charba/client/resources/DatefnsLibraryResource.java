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
 * Contains the content of <code>charba.empty.js</code> to inject.<br>
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class DatefnsLibraryResource extends InjectableResource {

	// encoded javascript content of charba.empty.js
	private static final String[] CONTENT = {
		"/* EMPTY */"
	};
	
	/**
	 * Creates the injectable resource with <code>charba.empty.js</code> content.
	 */
	DatefnsLibraryResource() {
		super(ResourceNames.DATE_TIME_LIBRARY, CONTENT);
	}

}
