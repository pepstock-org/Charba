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
 * Interface to be implemented to load elements from an array of native array.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface NativeArrayContainerFactory<A extends Array, T extends NativeArrayContainer<A>> {

	/**
	 * Creates a native array container instance by an empty native array.
	 * 
	 * @return native array container element instance
	 */
	default T create() {
		return create(null);
	}

	/**
	 * Creates a native array container instance by a native array.
	 * 
	 * @param nativeArray native object
	 * @return native array container element instance
	 */
	T create(final A nativeArray);

}