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
package org.pepstock.charba.client.defaults.chart;

import org.pepstock.charba.client.defaults.IsDefaultAnimationCollection;
import org.pepstock.charba.client.defaults.IsDefaultAnimations;
import org.pepstock.charba.client.options.AnimationCollectionKey;

/**
 * Defaults for animations option element, based on chart type.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultChartAnimations implements IsDefaultAnimations {

	private final IsDefaultAnimations animations;

	/**
	 * Creates the object by animations option element instance.
	 * 
	 * @param animations animations option element instance.
	 */
	public DefaultChartAnimations(IsDefaultAnimations animations) {
		this.animations = animations;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimations#has(org.pepstock.charba.client.options.AnimationCollectionKey)
	 */
	@Override
	public boolean has(AnimationCollectionKey collection) {
		return animations.has(collection);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimations#get(org.pepstock.charba.client.options.AnimationCollectionKey)
	 */
	@Override
	public IsDefaultAnimationCollection get(AnimationCollectionKey collection) {
		return animations.get(collection);
	}

}