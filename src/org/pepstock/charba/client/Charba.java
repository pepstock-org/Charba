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

import org.pepstock.charba.client.resources.EmbeddedResources;
import org.pepstock.charba.client.resources.ResourcesType;

/**
 * Entry point to initialize and inject all Charba modules.<br>
 * This utility MUST be called as first statement before using Charba.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Charba {

	/**
	 * To avoid any instantiation
	 */
	private Charba() {
		// do nothing
	}

	/**
	 * Enables Charba in the application, injecting also the date time library.
	 */
	public static void enable() {
		enable(true);
	}

	/**
	 * Enables Charba in the application.<br>
	 * If the argument is set to <code>false</code>, the date time library is not injected.
	 * 
	 * @param loadDateTimeLibrary if <code>false</code>, the date time library is not injected
	 */
	public static void enable(boolean loadDateTimeLibrary) {
		// checks if resources is not already injected
		if (!ResourcesType.isInjected()) {
			// creates an envelop
			ChartEnvelop<EmbeddedResources> envelop = new ChartEnvelop<>(loadDateTimeLibrary ? EmbeddedResources.INSTANCE : EmbeddedResources.INSTANCE_WITHOUT_DATE_LIBRARY);
			// then sets resource
			ResourcesType.setResources(envelop);
		}
	}

}
