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
package org.pepstock.charba.client.options;

import org.pepstock.charba.client.defaults.IsDefaultAnimations;

/**
 * Interface to map a animations element where animation element properties can be set, ANIMATIONS name space.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface IsAnimations extends IsDefaultAnimations {

	/**
	 * Enables or disables an animation collection instance in the animation options.
	 * 
	 * @param collection collection instance used to check in the animation options
	 * @param enabled if <code>true</code> it enables an animation collection
	 */
	void setEnabled(AnimationCollectionKey collection, boolean enabled);

	/**
	 * Returns <code>true</code> if the animation collection is enabled, otherwise <code>false</code>.
	 * 
	 * @param collection collection instance used to check in the animation options
	 * @return <code>true</code> if the animation collection is enabled, otherwise <code>false</code>
	 */
	boolean isEnabled(AnimationCollectionKey collection);

	/**
	 * Returns an animation collection instance if stored in the animation options.
	 * 
	 * @param collection collection instance used to get for animation options
	 * @return an animation collection instance or <code>null</code> if does not exists
	 */
	@Override
	AnimationCollection get(AnimationCollectionKey collection);

	/**
	 * Sets an animation collection instance to store in the animation options.
	 * 
	 * @param collection collection instance used to get for animation options
	 * @param animationCollection an animation collection instance to set
	 */
	void set(AnimationCollectionKey collection, AnimationCollection animationCollection);

	/**
	 * Creates an animation collection instance and stores in the animation options.
	 * 
	 * @param collection collection key used to create the animation collections
	 * @return a collection animation options
	 */
	AnimationCollection create(AnimationCollectionKey collection);

	/**
	 * Removes an animation collection previously added.
	 * 
	 * @param collection collection instance used to remove from animation options
	 */
	void remove(AnimationCollectionKey collection);

}