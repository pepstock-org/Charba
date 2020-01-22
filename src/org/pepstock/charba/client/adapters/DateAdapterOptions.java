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
package org.pepstock.charba.client.adapters;

import org.pepstock.charba.client.commons.NativeObject;

/**
 * Default class to use to map the date adapter options, if needed.<br>
 * It must be extended with specific properties depending on date adapter.
 * 
 * @author Andrea "Stock" Stocchero
 * @see DateAdaptersOptionsFactory
 */
public class DateAdapterOptions extends AbstractDateAdapterContainer {

	/**
	 * Creates the object with an empty native object instance.
	 */
	public DateAdapterOptions() {
		super();
	}

	/**
	 * Creates the object with native object instance to be wrapped.
	 * 
	 * @param nativeObject native object instance to be wrapped.
	 */
	protected DateAdapterOptions(NativeObject nativeObject) {
		super(nativeObject);
	}

}
