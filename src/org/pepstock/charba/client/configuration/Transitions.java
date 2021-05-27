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

import org.pepstock.charba.client.options.AnimationTransition;
import org.pepstock.charba.client.options.TransitionKey;
import org.pepstock.charba.client.options.IsTransitions;

/**
 * It animates charts out of the box.<br>
 * A number of options are provided to configure how the animation looks and how long it takes.<br>
 * This configuration item is configuring the common animation properties for a specific update mode (transition).
 * 
 * @author Andrea "Stock" Stocchero
 */
public class Transitions extends AbstractDynamicConfiguration<IsTransitions> implements IsTransitions {

	/**
	 * Builds the object by a animations provider used to get the transitions element for storing properties.
	 * 
	 * @param provider transitions provider used to get the transitions element for storing properties.
	 */
	Transitions(IsProvider<IsTransitions> provider) {
		super(provider);
	}

	/**
	 * Returns <code>true</code> if an animation transition instance is stored in the the animation options.
	 * 
	 * @param transition transition instance used to check in the animation options
	 * @return <code>true</code> if an animation transition instance is stored in the the animation options
	 */
	@Override
	public boolean has(TransitionKey transition) {
		return checkAndGet().has(transition);
	}

	/**
	 * Returns an animation transition instance if stored in the the animation options.
	 * 
	 * @param transition transition instance used to get for animation options
	 * @return an animation transition instance or <code>null</code> if does not exists
	 */
	@Override
	public AnimationTransition get(TransitionKey transition) {
		return checkAndGet().get(transition);
	}

	/**
	 * Sets an animation transition instance to store in the animation options.
	 * 
	 * @param transition transition instance used to get for animation options
	 * @param animationTransition an animation transition instance to set
	 */
	@Override
	public void set(TransitionKey transition, AnimationTransition animationTransition) {
		checkAndGet().set(transition, animationTransition);
	}

	/**
	 * Creates an animation transition instance and stores in the the animation options.
	 * 
	 * @param transition transition key used to create the animation transitions
	 * @return a transition animation options
	 */
	@Override
	public AnimationTransition create(TransitionKey transition) {
		return checkAndGet().create(transition);
	}

	/**
	 * Removes an animation transition previously added.
	 * 
	 * @param transition transition instance used to remove from animation options
	 */
	@Override
	public void remove(TransitionKey transition) {
		checkAndGet().remove(transition);
	}
}