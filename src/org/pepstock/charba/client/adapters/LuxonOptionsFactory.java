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
 * Date adapter options factory for LUXON option.<br>
 * It should be use to get stored LUXON options from global, chart options and configurations.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class LuxonOptionsFactory implements DateAdaptersOptionsFactory<LuxonOptions> {
	// singleton instance of factory
	private static final LuxonOptionsFactory INSTANCE = new LuxonOptionsFactory();

	/**
	 * To avoid any instantiation
	 */
	private LuxonOptionsFactory() {
		// do nothing
	}

	/**
	 * Singleton method to get static instance.
	 * 
	 * @return object instance
	 */
	public static LuxonOptionsFactory get() {
		return INSTANCE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.commons.NativeObject)
	 */
	@Override
	public LuxonOptions create(NativeObject nativeObject) {
		return new LuxonOptions(nativeObject);
	}

}
