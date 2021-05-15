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
package org.pepstock.charba.client.configuration;

import org.pepstock.charba.client.commons.Checker;

/**
 * Defines a configuration element which is managing the ANIMATION options.<br>
 * It has being used in the options and datasets instances where ANIMATION is required.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public interface HasAnimation {

	/**
	 * Returns an animation container instance to use in the default methods of this interface.
	 * 
	 * @return an animation container instance
	 */
	AnimationContainer getAnimationContainer();

	/**
	 * Returns the animation element.
	 * 
	 * @return the animation
	 */
	default Animation getAnimation() {
		// checks if container is consistent
		return Checker.checkAndGetIfValid(getAnimationContainer(), "Animation container").getAnimation();
	}

	/**
	 * Returns the animations collection element.
	 * 
	 * @return the animations collection
	 */
	default Animations getAnimations() {
		// checks if container is consistent
		return Checker.checkAndGetIfValid(getAnimationContainer(), "Animation container").getAnimations();
	}

	/**
	 * Returns the animation transition element.
	 * 
	 * @return the animation transition
	 */
	default Transitions getTransitions() {
		// checks if container is consistent
		return Checker.checkAndGetIfValid(getAnimationContainer(), "Animation container").getTransitions();
	}

	/**
	 * Enables or disables the animation.
	 * 
	 * @param enabled if <code>true</code> the animation is enabled otherwise <code>false</code> to disable it.
	 */
	default void setAnimationEnabled(boolean enabled) {
		// checks if container is consistent
		if (getAnimationContainer() != null) {
			getAnimationContainer().setAnimationEnabled(enabled);
		}
	}

	/**
	 * Returns <code>true</code> if animation is enabled, otherwise <code>false</code>.
	 * 
	 * @return <code>true</code> if animation is enabled, otherwise <code>false</code>
	 */
	default boolean isAnimationEnabled() {
		// checks if container is consistent
		if (getAnimationContainer() != null) {
			return getAnimationContainer().isAnimationEnabled();
		}
		// if here the animation container is not consistent
		// then the animation is enabled y default
		return true;
	}

}
