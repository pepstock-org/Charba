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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.pepstock.charba.client.commons.Key;

/**
 * This utility checks if the animation element names are consistent or if they are using reserved names.
 * 
 * @author Andrea "Stock" Stocchero
 */
final class AnimationElementChecker {

	private static final AnimationElementChecker INSTANCE = new AnimationElementChecker();

	private final List<Key> reservedKeys = new ArrayList<>();

	/**
	 * To avoid any instantiation
	 */
	private AnimationElementChecker() {
		// loads all teh keys used for animation options
		reservedKeys.addAll(Arrays.asList(AbstractAnimation.Property.values()));
		reservedKeys.addAll(Arrays.asList(AbstractAnimationElement.Property.values()));
		reservedKeys.addAll(Arrays.asList(Animation.Property.values()));
	}

	/**
	 * Singleton method to get static instance.
	 * 
	 * @return animation checker instance
	 */
	static AnimationElementChecker get() {
		return INSTANCE;
	}
	
	/**
	 * Returns <code>true</code> if the mode name is not overriding the name of other properties of animation options.
	 * 
	 * @param value value of the property instance to be checked.
	 * @param defaultProperties1 list of default animation elements
	 * @param defaultProperties2 list of default animation elements
	 * @return <code>true</code> if the mode name is not overriding the name of other properties of animation options
	 */
	boolean isValid(String value, Key[] defaultProperties1, Key[] defaultProperties2) {
		// checks if key is valid and
		// checks if the key matches with options and default
		return !Key.hasKeyByValue(reservedKeys.toArray(new Key[0]), value) && !Key.hasKeyByValue(defaultProperties1, value) && !Key.hasKeyByValue(defaultProperties2, value);
	}

}
