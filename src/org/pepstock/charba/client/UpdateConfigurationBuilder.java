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
	 * If <code>true</code>, the animation can be interrupted by other animations.
	 * 
	 * @param intersect if <code>true</code>, the animation can be interrupted by other animations.
	 * @return builder instance
	 */
	public UpdateConfigurationBuilder setLazy(boolean intersect) {
		configuration.setLazy(intersect);
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