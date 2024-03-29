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

import org.pepstock.charba.client.Charba;
import org.pepstock.charba.client.ChartEnvelop;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.Envelop;
import org.pepstock.charba.client.commons.JsHelper;

/**
 * Utility to set which kind of resources type must be use to load injectable resources.<br>
 * This utility MUST be called as first statement before using Charba.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ResourcesType {

	// static instance of resources to be loaded
	private static AbstractResources resources = null;
	// flag to know if the resource has been inject
	private static boolean injected = false;

	/**
	 * To avoid any instantiation
	 */
	private ResourcesType() {
		// do nothing
	}

	/**
	 * Sets the resources type to use to inject java script code.<br>
	 * If the resources type was already set or if is <code>null</code> an exception will be throw.<br>
	 * This is deprecated, use {@link Charba}.
	 * 
	 * @param envelop envelop which contains the resources type to use to inject java script code
	 * @param <T> type of resources to inject
	 */
	public static <T extends AbstractResources> void setResources(ChartEnvelop<T> envelop) {
		// checks envelop
		Envelop.checkIfValid(envelop);
		// gets resources
		// checks if resource is consistent
		AbstractResources resources = Checker.checkAndGetIfValid(envelop.getContent(), "Resources type argument");
		// checks if is extending a correct abstract resource
		Checker.assertCheck(resources instanceof IsResourceType, "Resources type is not correct. Must be an EmbeddedResources or DeferredResources instance");
		// checks if the resources type is already loaded
		Checker.assertCheck(!isInjected(), "Resources type is already loaded and can not be changed");
		// stores the instance
		ResourcesType.resources = resources;
		// to be sure that CHARBA java script object is injected
		// invoking the JsHelper
		// PAY ATTENTION: MUST be called before injecting CHART.JS
		JsHelper.get();
	}

	/**
	 * Returns the resources type to use to inject java script code.<br>
	 * If the resources type was not already set, an exception will be throw.
	 * 
	 * @return the resources type to use to inject java script code
	 */
	public static AbstractResources getResources() {
		// checks if a type was already stored
		if (ResourcesType.resources == null) {
			// if not, exception
			throw new IllegalArgumentException("Resources type is invalid (not set). Must be set before using CHARBA by Charba.enable or DeferredCharba.enable");
		}
		// returns the instance
		return ResourcesType.resources;
	}

	/**
	 * Returns <code>true</code> if the resource has been injected.
	 * 
	 * @return <code>true</code> if the resource has been injected
	 */
	public static boolean isInjected() {
		return injected;
	}

	/**
	 * Sets <code>true</code> if the resource has been injected.
	 * 
	 * @param injected <code>true</code> if the resource has been injected
	 */
	static void setInjected(boolean injected) {
		ResourcesType.injected = injected;
	}

}