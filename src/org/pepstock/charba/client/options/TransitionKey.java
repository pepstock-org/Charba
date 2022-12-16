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
package org.pepstock.charba.client.options;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.DefaultTransitionKey;

/**
 * Represents the update mode (transition) to set to configure animation.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface TransitionKey extends Key {

	/**
	 * Returns a animation update mode (transition) instance by its string value.
	 * 
	 * @param transition string value to use
	 * @return new update mode (transition) instance
	 */
	static TransitionKey create(String transition) {
		// checks if mode as argument is a default one
		for (DefaultTransitionKey defMode : DefaultTransitionKey.values()) {
			// checks if mode is equals to default
			if (defMode.value().equals(transition)) {
				// if equals, returns the default mode
				return defMode;
			}
		}
		// if here, is not a default one
		// then creates new animation mode
		return new StandardAnimationTransition(transition);
	}

	/**
	 * Returns <code>true</code> if type passed as argument is not <code>null</code>.
	 * 
	 * @param transition animation update mode (transition) to be checked
	 * @return <code>true</code> if type passed as argument is not <code>null</code>
	 */
	static boolean isValid(TransitionKey transition) {
		return Key.isValid(transition);
	}

	/**
	 * Checks if type passed as argument is not <code>null</code>.<br>
	 * If not, throw a {@link IllegalArgumentException}.
	 * 
	 * @param transition animation update mode (transition) to be checked
	 */
	static void checkIfValid(TransitionKey transition) {
		if (!isValid(transition)) {
			// gets the exception message
			// additional check to throw the right exception message
			String exceptionMessage = transition != null ? "Invalid animation mode name, '" + transition.value() + "' because is reserved" : "Animation mode is null or not consistent";
			// throws exception
			throw new IllegalArgumentException(exceptionMessage);
		}
	}

	/**
	 * Checks if mode passed as argument is not <code>null</code>.<br>
	 * If not, throw a {@link IllegalArgumentException}, otherwise it returns the argument.
	 * 
	 * @param transition update mode (transition) to be checked
	 * @return the same update mode (transition) passed as argument
	 */
	static TransitionKey checkAndGetIfValid(TransitionKey transition) {
		// checks if collection is consistent
		checkIfValid(transition);
		// if here, is consistent
		// then returns the argument
		return transition;
	}
}