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

import org.pepstock.charba.client.commons.AbstractBaseBuilder;
import org.pepstock.charba.client.commons.IsBuilder;
import org.pepstock.charba.client.enums.Easing;

/**
 * Comfortable object to create {@link UpdateConfiguration} item.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class UpdateConfigurationBuilder extends AbstractBaseBuilder {

	// configuration instance
	private final UpdateConfiguration configuration = new UpdateConfiguration();

	/**
	 * To avoid any instantiation
	 */
	private UpdateConfigurationBuilder() {
		// do nothing
	}

	/**
	 * Returns new builder instance.
	 * 
	 * @return new builder instance
	 */
	public static UpdateConfigurationBuilder create() {
		return new UpdateConfigurationBuilder();
	}

	/**
	 * Sets the animation easing function.
	 * 
	 * @param easing animation easing function.
	 * @return builder instance
	 */
	public UpdateConfigurationBuilder setEasing(Easing easing) {
		configuration.setEasing(easing);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the time for the animation of the redraw in milliseconds.
	 * 
	 * @param milliseconds time for the animation of the redraw in milliseconds.
	 * @return builder instance
	 */
	public UpdateConfigurationBuilder setDuration(int milliseconds) {
		configuration.setDuration(milliseconds);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the delay before starting the animations.
	 * 
	 * @param delay the delay before starting the animations
	 * @return builder instance
	 */
	public UpdateConfigurationBuilder setDelay(int delay) {
		configuration.setDelay(delay);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * If set to <code>true</code>, loops the animations endlessly.
	 * 
	 * @param loop <code>true</code> if loops the animations endlessly.
	 * @return builder instance
	 */
	public UpdateConfigurationBuilder setLoop(boolean loop) {
		configuration.setLoop(loop);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * If <code>true</code>, the chart will animate in with a rotation animation.
	 * 
	 * @param animateRotate If <code>true</code>, the chart will animate in with a rotation animation.
	 * @return builder instance
	 */
	public UpdateConfigurationBuilder setAnimateRotate(boolean animateRotate) {
		configuration.setAnimateRotate(animateRotate);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * If <code>true</code>, will animate scaling the chart from the center outwards.
	 * 
	 * @param animateScale If <code>true</code>, will animate scaling the chart from the center outwards.
	 * @return builder instance
	 */
	public UpdateConfigurationBuilder setAnimateScale(boolean animateScale) {
		configuration.setAnimateScale(animateScale);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Returns a update configuration item.
	 * 
	 * @return a update configuration item
	 */
	public UpdateConfiguration build() {
		// sets built status
		setBuilt(true);
		// returns configuration
		return configuration;
	}

}