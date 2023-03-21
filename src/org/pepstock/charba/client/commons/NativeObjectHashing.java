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
package org.pepstock.charba.client.commons;

/**
 * Utility class to redefine hash code for {@link NativeObject}, setting the hashCode property as not enumerable and not configurable.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class NativeObjectHashing {

	// property name used by GWT to store hashcode
	static final String HASH_CODE_PROPERTY = "$H";

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
	 * @return the same oject instance passed as argument
	 */
	static NativeObject handleHashCode(NativeObject nativeObject) {
		// creates new descriptor to write the hasCode
		NativeIntegerDescriptor newDescriptor = new NativeIntegerDescriptor();
		newDescriptor.setWritable(true);
		// sets to be NOT configurable and NOT enumerable
		newDescriptor.setConfigurable(false);
		newDescriptor.setEnumerable(false);
		// sets value
		newDescriptor.setValue(nativeObject.hashCode());
		// defines the property
		NativeUtil.defineProperty(nativeObject, HASH_CODE_PROPERTY, newDescriptor);
		// returns the argument
		return nativeObject;
	}
}