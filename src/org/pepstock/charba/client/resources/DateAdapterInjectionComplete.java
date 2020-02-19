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
 * Utility object in order to notify a date adapter module that has been inject.<br>
 * This object can not be instantiated out of this package to avoid that anyone will change the module status without a real injection.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class DateAdapterInjectionComplete {
	// singleton instance
	private static final DateAdapterInjectionComplete INSTANCE = new DateAdapterInjectionComplete();

	/**
	 * To avoid any instantiation
	 */
	private DateAdapterInjectionComplete() {
		// do nothing
	}

	/**
	 * Returns the singleton instance of object.
	 * 
	 * @return the singleton instance of object
	 */
	static DateAdapterInjectionComplete get() {
		return INSTANCE;
	}
}
