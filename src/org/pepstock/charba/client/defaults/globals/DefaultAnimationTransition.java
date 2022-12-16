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
package org.pepstock.charba.client.defaults.globals;

import org.pepstock.charba.client.defaults.IsDefaultAnimation;
import org.pepstock.charba.client.defaults.IsDefaultAnimationTransition;
import org.pepstock.charba.client.defaults.IsDefaultAnimations;

/**
 * CHART.JS default values for animation collection.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class DefaultAnimationTransition implements IsDefaultAnimationTransition {

	private static final DefaultAnimation DEFAULT_ANIMATION = new DefaultAnimation();

	private static final DefaultAnimations DEFAULT_ANIMATIONS = new DefaultAnimations();

	/**
	 * To avoid any instantiation
	 */
	DefaultAnimationTransition() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimationTransition#getAnimation()
	 */
	@Override
	public IsDefaultAnimation getAnimation() {
		return DEFAULT_ANIMATION;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimationTransition#getAnimations()
	 */
	@Override
	public IsDefaultAnimations getAnimations() {
		return DEFAULT_ANIMATIONS;
	}

}