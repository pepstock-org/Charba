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
package org.pepstock.charba.client.options;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.defaults.IsDefaultAnimationContainer;

/**
 * Defines a configuration element which is managing the animation options items, ANIMATION, ANIMATIONS and TRANSITIONS.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public interface HasAnimationOptions extends IsDefaultAnimationContainer {

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
	@Override
	default Animation getAnimation() {
		// checks if container is consistent
		if (getAnimationContainer() != null) {
			return getAnimationContainer().getAnimation();
		}
		// if here, the container is not consistent
		return Defaults.get().getGlobal().getAnimation();
	}

	/**
	 * Returns the animations collection element.
	 * 
	 * @return the animations collection
	 */
	@Override
	default Animations getAnimations() {
		// checks if container is consistent
		if (getAnimationContainer() != null) {
			return getAnimationContainer().getAnimations();
		}
		// if here, the container is not consistent
		return Defaults.get().getGlobal().getAnimations();
	}

	/**
	 * Returns the animation transition element.
	 * 
	 * @return the animation transition
	 */
	@Override
	default Transitions getTransitions() {
		// checks if container is consistent
		if (getAnimationContainer() != null) {
			return getAnimationContainer().getTransitions();
		}
		// if here, the container is not consistent
		return Defaults.get().getGlobal().getTransitions();
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
