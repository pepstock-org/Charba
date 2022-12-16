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

/**
 * Object used to invoke a mutation handler when is attached or detached an element.<br>
 * This object can not be instantiated out of this package to avoid that anyone (apart the observer) will invoke the methods of a mutation handler.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class MutationItem {
	// singleton instance
	private static final MutationItem INSTANCE = new MutationItem();

	/**
	 * To avoid any instantiation
	 */
	private MutationItem() {
		// do nothing
	}

	/**
	 * Returns the singleton instance of object.
	 * 
	 * @return the singleton instance of object
	 */
	static MutationItem get() {
		return INSTANCE;
	}
}