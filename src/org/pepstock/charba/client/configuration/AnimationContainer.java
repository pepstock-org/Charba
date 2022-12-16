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

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.options.HasAnimationOptions;

/**
 * Manages the ANIMATION, ANIMATIONS and TRANSITIONS options in order to use the same logic among all options/configuration and datasets.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class AnimationContainer extends AbstractDynamicConfiguration<HasAnimationOptions> {

	private final Animation animation;

	private final Animations animations;

	private final Transitions transitions;

	/**
	 * Builds the object by a animation options provider used to get the animation options for storing properties.
	 * 
	 * @param chart chart instance of animation container.
	 * @param provider animation options provider used to get the animation options for storing properties.
	 */
	AnimationContainer(IsChart chart, IsProvider<HasAnimationOptions> provider) {
		super(provider);
		// creates all sub elements
		this.animation = new Animation(IsChart.checkAndGetIfValid(chart), checkAndGet(), () -> checkAndGet().getAnimation());
		this.animations = new Animations(() -> checkAndGet().getAnimations());
		this.transitions = new Transitions(() -> checkAndGet().getTransitions());
	}

	/**
	 * Returns the animation element.
	 * 
	 * @return the animation
	 */
	Animation getAnimation() {
		return animation;
	}

	/**
	 * Returns the animations element.
	 * 
	 * @return the animations
	 */
	Animations getAnimations() {
		return animations;
	}

	/**
	 * Returns the transitions element.
	 * 
	 * @return the transitions
	 */
	Transitions getTransitions() {
		return transitions;
	}

	/**
	 * Enables or disables the animation.
	 * 
	 * @param enabled if <code>true</code> the animation is enabled otherwise <code>false</code> to disable it.
	 */
	void setAnimationEnabled(boolean enabled) {
		checkAndGet().setAnimationEnabled(enabled);
	}

	/**
	 * Returns <code>true</code> if animation is enabled, otherwise <code>false</code>.
	 * 
	 * @return <code>true</code> if animation is enabled, otherwise <code>false</code>
	 */
	boolean isAnimationEnabled() {
		return checkAndGet().isAnimationEnabled();
	}

}