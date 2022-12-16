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
package org.pepstock.charba.client;

import org.pepstock.charba.client.resources.DeferredResources;
import org.pepstock.charba.client.resources.EntryPointStarter;
import org.pepstock.charba.client.resources.ResourcesType;

/**
 * Entry point to initialize and inject all Charba modules, leveraging on deferred resources mode.<br>
 * This helps when the GWT application is leveraging on code splitting.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class DeferredCharba {

	/**
	 * To avoid any instantiation
	 */
	private DeferredCharba() {
		// do nothing
	}

	/**
	 * Enables Charba in the application, injecting also the date time library.<br>
	 * Start an entry point as a runnable.<br>
	 * This runnable instance must contains all calls to chart.<br>
	 * This helps when the GWT application is leveraging on code splitting.
	 * 
	 * @param runnable the entry point instance as runnable
	 */
	public static void enable(Runnable runnable) {
		enable(runnable, true);
	}

	/**
	 * Enables Charba in the application.<br>
	 * If the argument is set to <code>false</code>, the date time library is not injected.<br>
	 * This runnable instance must contains all calls to chart.<br>
	 * This helps when the GWT application is leveraging on code splitting.
	 * 
	 * @param runnable the entry point instance as runnable
	 * @param loadDateTimeLibrary if <code>false</code>, the date time library is not injected
	 */
	public static void enable(Runnable runnable, boolean loadDateTimeLibrary) {
		// checks if resources is not already injected
		if (!ResourcesType.isInjected()) {
			// creates an envelop
			ChartEnvelop<DeferredResources> envelop = new ChartEnvelop<>(loadDateTimeLibrary ? DeferredResources.INSTANCE : DeferredResources.INSTANCE_WITHOUT_DATE_LIBRARY);
			// invokes the async injection
			EntryPointStarter.run(runnable, envelop);
		}
	}

}