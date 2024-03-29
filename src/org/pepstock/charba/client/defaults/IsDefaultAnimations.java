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
package org.pepstock.charba.client.defaults;

import org.pepstock.charba.client.commons.Key;

/**
 * Interface to define animations for animation element properties defaults, ANIMATIONS name space.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface IsDefaultAnimations {

	/**
	 * Returns <code>true</code> if an animation collection instance is stored in the animation options.
	 * 
	 * @param collection collection instance used to check in the animation options
	 * @return <code>true</code> if an animation collection instance is stored in the animation options
	 */
	boolean contains(Key collection);

	/**
	 * Returns <code>true</code> if an animation collection instance is stored in the animation options.
	 * 
	 * @param collection collection instance used to check in the animation options
	 * @return <code>true</code> if an animation collection instance is stored in the animation options
	 */
	default boolean contains(String collection) {
		return contains(Key.create(collection));
	}

	/**
	 * Returns an animation collection instance if stored in the animation options.
	 * 
	 * @param collection collection instance used to get for animation options
	 * @return an animation collection instance or <code>null</code> if does not exists
	 */
	IsDefaultAnimationCollection get(Key collection);

	/**
	 * Returns an animation collection instance if stored in the animation options.
	 * 
	 * @param collection collection instance used to get for animation options
	 * @return an animation collection instance or <code>null</code> if does not exists
	 */
	default IsDefaultAnimationCollection get(String collection) {
		return get(Key.create(collection));
	}
}