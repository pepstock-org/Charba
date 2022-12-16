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

import org.pepstock.charba.client.callbacks.DelayCallback;
import org.pepstock.charba.client.callbacks.DurationCallback;
import org.pepstock.charba.client.callbacks.EasingCallback;
import org.pepstock.charba.client.callbacks.LoopCallback;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.defaults.IsDefaultBaseAnimation;
import org.pepstock.charba.client.enums.Easing;

/**
 * Defaults for base animation option element.
 * 
 * @author Andrea "Stock" Stocchero
 * @param <T> type of default interface of wrapped object
 */
abstract class AbstractDefaultChartAnimation<T extends IsDefaultBaseAnimation> implements IsDefaultBaseAnimation {

	private final T animation;

	/**
	 * Creates the object wrapping a base animation instance.
	 * 
	 * @param animation a base animation instance to wrap
	 */
	AbstractDefaultChartAnimation(T animation) {
		// checks if the argument is consistent
		// stores reference
		this.animation = Checker.checkAndGetIfValid(animation, "Animation wrapped object");
	}

	/**
	 * Returns the defaults instance to be wrapped.
	 * 
	 * @return the defaults instance to be wrapped
	 */
	final T getDefaults() {
		return animation;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.animation.IsDefaultBaseAnimation#getEasing()
	 */
	@Override
	public Easing getEasing() {
		return animation.getEasing();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.animation.IsDefaultBaseAnimation#getDuration()
	 */
	@Override
	public int getDuration() {
		return animation.getDuration();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultBaseAnimation#getDelay()
	 */
	@Override
	public int getDelay() {
		return animation.getDelay();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultBaseAnimation#isLoop()
	 */
	@Override
	public boolean isLoop() {
		return animation.isLoop();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultBaseAnimation#getDurationCallback()
	 */
	@Override
	public DurationCallback getDurationCallback() {
		return animation.getDurationCallback();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultBaseAnimation#getDelayCallback()
	 */
	@Override
	public DelayCallback getDelayCallback() {
		return animation.getDelayCallback();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultBaseAnimation#getLoopCallback()
	 */
	@Override
	public LoopCallback getLoopCallback() {
		return animation.getLoopCallback();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultBaseAnimation#getEasingCallback()
	 */
	@Override
	public EasingCallback getEasingCallback() {
		return animation.getEasingCallback();
	}

}