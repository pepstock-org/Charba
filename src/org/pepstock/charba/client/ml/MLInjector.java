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
package org.pepstock.charba.client.ml;

import org.pepstock.charba.client.Injector;
import org.pepstock.charba.client.commons.JsHelper;

/**
 * Injects the ML java script in the application.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class MLInjector {

	// static instance for singleton
	private static final MLInjector INSTANCE = new MLInjector();
	// injectable JS resource for ML
	private final MlPluginResource jsResource = new MlPluginResource();

	/**
	 * To avoid any instantiation
	 */
	private MLInjector() {
		// to be sure that CHARBA java script object is injected
		JsHelper.get();
		// injects JS of ML
		Injector.ensureInjected(jsResource);
	}

	/**
	 * Singleton object to get the ML helper instance
	 * 
	 * @return ML helper instance.
	 */
	static MLInjector get() {
		return INSTANCE;
	}
}