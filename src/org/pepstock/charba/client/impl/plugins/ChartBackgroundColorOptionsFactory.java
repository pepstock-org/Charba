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
package org.pepstock.charba.client.impl.plugins;

import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;

/**
 * Factory to get the options (ONLY for chart and not global options) related to background plugin.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class ChartBackgroundColorOptionsFactory implements NativeObjectContainerFactory<ChartBackgroundColorOptions> {

	/**
	 * To avoid any instantiation. Use the statis reference into {@link ChartBackgroundColor#FACTORY}.
	 */
	ChartBackgroundColorOptionsFactory() {
		// do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.commons.NativeObject)
	 */
	@Override
	public ChartBackgroundColorOptions create(NativeObject nativeObject) {
		return new ChartBackgroundColorOptions(nativeObject);
	}

}
