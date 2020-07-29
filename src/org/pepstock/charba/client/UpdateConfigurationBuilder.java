/**
    Copyright 2017 Andrea "Stock" Stocchero

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

	    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/
package org.pepstock.charba.client;

import org.pepstock.charba.client.enums.Easing;
import org.pepstock.charba.client.options.AnimationCollection;
import org.pepstock.charba.client.options.AnimationProperty;

/**
 * Comfortable object to create {@link UpdateConfiguration} item.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class UpdateConfigurationBuilder {

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
		return this;
	}

	/**
	 * Sets the time for the animation of the redraw in milliseconds.
	 * 
	 * @param milliseconds time for the animation of the redraw in milliseconds.
	 * @return builder instance
	 */
	public UpdateConfigurationBuilder setDuration(int milliseconds) {
		configuration.setDuration(milliseconds);
		return this;
	}

	/**
	 * Sets <code>true</code> if running animation count plus FPS display in upper left corner of the chart.
	 * 
	 * @param debug <code>true</code> if running animation count plus FPS display in upper left corner of the chart
	 * @return builder instance
	 */
	public UpdateConfigurationBuilder setDebug(boolean debug) {
		configuration.setDebug(debug);
		return this;
	}

	/**
	 * Sets the delay before starting the animations.
	 * 
	 * @param delay the delay before starting the animations
	 * @return builder instance
	 */
	public UpdateConfigurationBuilder setDelay(int delay) {
		configuration.setDelay(delay);
		return this;
	}

	/**
	 * If set to <code>true</code>, loops the animations endlessly.
	 * 
	 * @param loop <code>true</code> if loops the animations endlessly.
	 * @return builder instance
	 */
	public UpdateConfigurationBuilder setLoop(boolean loop) {
		configuration.setLoop(loop);
		return this;
	}

	/**
	 * Sets an animation property instance to animation options.
	 * 
	 * @param animationElement animation property instance to add
	 * @return builder instance
	 */
	public UpdateConfigurationBuilder setProperty(AnimationProperty animationElement) {
		configuration.setProperty(animationElement);
		return this;
	}

	/**
	 * Sets an animation collection instance to animation options.
	 * 
	 * @param animationElement animation collection instance to add
	 * @return builder instance
	 */
	public UpdateConfigurationBuilder setCollection(AnimationCollection animationElement) {
		configuration.setCollection(animationElement);
		return this;
	}

	/**
	 * Returns a update configuration item.
	 * 
	 * @return a update configuration item
	 */
	public UpdateConfiguration build() {
		// returns configuration
		return configuration;
	}

}