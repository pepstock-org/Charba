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
package org.pepstock.charba.client.configuration;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.options.AnimationCollection;
import org.pepstock.charba.client.options.IsAnimations;

/**
 * It animates charts out of the box.<br>
 * A number of options are provided to configure how the animation looks and how long it takes.<br>
 * This configuration item is configuring the common animation properties for a specific sets of element(like bar, point, arc and line) properties.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class Animations extends AbstractDynamicConfiguration<IsAnimations> implements IsAnimations {

	/**
	 * Builds the object by a animations provider used to get the animations element for storing properties.
	 * 
	 * @param provider animations provider used to get the animations element for storing properties.
	 */
	Animations(IsProvider<IsAnimations> provider) {
		super(provider);
	}

	/**
	 * Enables or disables an animation collection instance in the animation options.
	 * 
	 * @param collection collection instance used to check in the animation options
	 * @param enabled if <code>true</code> it enables an animation collection
	 */
	@Override
	public void setEnabled(Key collection, boolean enabled) {
		checkAndGet().setEnabled(collection, enabled);
	}

	/**
	 * Returns <code>true</code> if the animation collection is enabled, otherwise <code>false</code>.
	 * 
	 * @param collection collection instance used to check in the animation options
	 * @return <code>true</code> if the animation collection is enabled, otherwise <code>false</code>
	 */
	@Override
	public boolean isEnabled(Key collection) {
		return checkAndGet().isEnabled(collection);
	}

	/**
	 * Returns <code>true</code> if an animation collection instance is stored in the animation options.
	 * 
	 * @param collection collection instance used to check in the animation options
	 * @return <code>true</code> if an animation collection instance is stored in the animation options
	 */
	@Override
	public boolean contains(Key collection) {
		return checkAndGet().contains(collection);
	}

	/**
	 * Returns an animation collection instance if stored in the animation options.
	 * 
	 * @param collection collection instance used to get for animation options
	 * @return an animation collection instance or <code>null</code> if does not exists
	 */
	@Override
	public AnimationCollection get(Key collection) {
		return checkAndGet().get(collection);
	}

	/**
	 * Sets an animation collection instance to store in the animation options.
	 * 
	 * @param collection collection instance used to get for animation options
	 * @param animationCollection an animation collection instance to set
	 */
	@Override
	public void set(Key collection, AnimationCollection animationCollection) {
		checkAndGet().set(collection, animationCollection);
	}

	/**
	 * Creates an animation collection instance and stores in the animation options.
	 * 
	 * @param collection collection key used to create the animation collections
	 * @return a collection animation options
	 */
	@Override
	public AnimationCollection create(Key collection) {
		return checkAndGet().create(collection);
	}

	/**
	 * Removes an animation collection previously added.
	 * 
	 * @param collection collection instance used to remove from animation options
	 */
	@Override
	public void delete(Key collection) {
		checkAndGet().delete(collection);
	}

}