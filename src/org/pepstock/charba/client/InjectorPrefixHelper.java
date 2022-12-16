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

import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.resources.AbstractInjectableResource;
import org.pepstock.charba.client.resources.ResourceName;

/**
 * Singleton which contains the prefix to apply to CHARBA injectable resources DOM element id when they will injected.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class InjectorPrefixHelper {

	// suffix to use when the injected resource is not a Charba one
	private static final String CUSTOM_SUFFIX = "_custom";
	// package name to use to calculate the key of injected resources
	private static final String PACKAGE_NAME_PREFIX = "org.pepstock.charba.client";
	// package name to use to calculate the key of injected resources
	private static final String CUSTOM_PACKAGE_NAME_PREFIX = PACKAGE_NAME_PREFIX + CUSTOM_SUFFIX;
	// singleton instance created
	// in order to manage a field with the package name of this class
	// needed to improve the key calculation
	private static final InjectorPrefixHelper INSTANCE = new InjectorPrefixHelper();

	/**
	 * To avoid any instantiation.
	 */
	private InjectorPrefixHelper() {
		// nothing
	}

	/**
	 * Provides the singleton instance of helper.
	 * 
	 * @return the singleton instance of helper
	 */
	static InjectorPrefixHelper get() {
		return INSTANCE;
	}

	/**
	 * Returns the prefix to apply to injectable resources DOM element id when they will injected.
	 * 
	 * @param resource injectable resource to inject
	 * @return the prefix to apply to injectable resources DOM element id when they will injected
	 */
	String getPrefixPackageName(AbstractInjectableResource resource) {
		// checks if resource is consistent
		AbstractInjectableResource checkedResource = Checker.checkAndGetIfValid(resource, "Injectable resource");
		// checks if the name of resource is a CHARBA one
		Key key = Key.getKeyByValue(ResourceName.values(), checkedResource.getName());
		// if not null, is CHARBA
		if (key != null) {
			// then returns CHARBA prefix
			return PACKAGE_NAME_PREFIX;
		}
		// if here, returns CUSTOM prefix
		return CUSTOM_PACKAGE_NAME_PREFIX;
	}

}