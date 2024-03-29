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

import org.pepstock.charba.client.callbacks.DelayCallback;
import org.pepstock.charba.client.callbacks.DurationCallback;
import org.pepstock.charba.client.callbacks.EasingCallback;
import org.pepstock.charba.client.callbacks.LoopCallback;
import org.pepstock.charba.client.defaults.IsDefaultBaseAnimation;
import org.pepstock.charba.client.enums.Easing;
import org.pepstock.charba.client.items.Undefined;

/**
 * CHART.JS default values for ANIMATION element.
 * 
 * @author Andrea "Stock" Stocchero
 */
abstract class AbstractDefaultAnimation implements IsDefaultBaseAnimation {

	private static final int DEFAULT_DURATION = 1000;

	private static final int DEFAULT_DELAY = Undefined.INTEGER;

	private static final boolean DEFAULT_LOOP = false;

	/**
	 * To avoid any instantiation
	 */

	AbstractDefaultAnimation() {
		// do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultBaseAnimation#getEasing()
	 */
	@Override
	public Easing getEasing() {
		return Easing.EASE_OUT_QUART;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultBaseAnimation#getDuration()
	 */
	@Override
	public int getDuration() {
		return DEFAULT_DURATION;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultBaseAnimation#getDelay()
	 */
	@Override
	public int getDelay() {
		return DEFAULT_DELAY;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultBaseAnimation#isLoop()
	 */
	@Override
	public boolean isLoop() {
		return DEFAULT_LOOP;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultBaseAnimation#getDurationCallback()
	 */
	@Override
	public DurationCallback getDurationCallback() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultBaseAnimation#getDelayCallback()
	 */
	@Override
	public DelayCallback getDelayCallback() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultBaseAnimation#getLoopCallback()
	 */
	@Override
	public LoopCallback getLoopCallback() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultBaseAnimation#getEasingCallback()
	 */
	@Override
	public EasingCallback getEasingCallback() {
		return null;
	}

}