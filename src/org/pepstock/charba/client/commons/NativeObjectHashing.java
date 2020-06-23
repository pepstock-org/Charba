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
package org.pepstock.charba.client.commons;

/**
 * Utility class to redefine hash code for {@link NativeObject}, setting the hashCode property as not enumerable and not configurable.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class NativeObjectHashing {

	// property name used by GWT to store hashcode
	private static final String HASH_CODE_PROPERTY = "$H";

	/**
	 * To avoid any instantiation
	 */
	private NativeObjectHashing() {
		// do nothing
	}

	/**
	 * Sets the hashCode value to native object, defining the property as not enumerable and not configurable.
	 * 
	 * @param nativeObject native object instance to use to set the hasCode property
	 */
	static void handleHashCode(NativeObject nativeObject) {
		// checks if argument is consistent
		if (nativeObject != null) {
			// creates a descriptor
			NativeIntegerDescriptor descriptor = NativeObject.getOwnPropertyDescriptor(nativeObject, HASH_CODE_PROPERTY);
			// checks if descriptor is consistent and already configured
			if (descriptor != null && !descriptor.isConfigurable() && !descriptor.isEnumerable()) {
				// already not enumerable and not configurable
				return;
			}
			// creates new descriptor to write the hasCode
			NativeIntegerDescriptor newDescriptor = new NativeIntegerDescriptor();
			newDescriptor.setWritable(true);
			// sets to be NOT configurable and NOT enumerable
			newDescriptor.setConfigurable(false);
			newDescriptor.setEnumerable(false);
			// sets value
			newDescriptor.setValue(nativeObject.hashCode());
			// defines the property
			NativeObject.defineProperty(nativeObject, HASH_CODE_PROPERTY, newDescriptor);
		}
	}
}
