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

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.DefaultAnimationCollectionKey;
import org.pepstock.charba.client.enums.DefaultAnimationModeKey;
import org.pepstock.charba.client.enums.DefaultAnimationPropertyKey;

/**
 * Represents the mode to set to configure animation.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface IsAnimationModeKey extends Key {

	/**
	 * Returns a animation mode instance by its string value.
	 * 
	 * @param mode string value to use
	 * @return new mode instance
	 */
	static IsAnimationModeKey create(String mode) {
		// checks if mode as argument is a default one
		for (DefaultAnimationModeKey defMode : DefaultAnimationModeKey.values()) {
			// checks if mode is equals to default
			if (defMode.value().equals(mode)) {
				// if equals, returns the default mode
				return defMode;
			}
		}
		// if here, is not a default one
		// then creates new animation mode
		return new StandardAnimationMode(mode);
	}

	/**
	 * Returns <code>true</code> if type passed as argument is not <code>null</code>.
	 * 
	 * @param mode animation mode to be checked
	 * @return <code>true</code> if type passed as argument is not <code>null</code>
	 */
	static boolean isValid(IsAnimationModeKey mode) {
		return Key.isValid(mode) && AnimationElementChecker.get().isValid(mode.value(), DefaultAnimationCollectionKey.values(), DefaultAnimationPropertyKey.values());
	}

	/**
	 * Checks if type passed as argument is not <code>null</code>.<br>
	 * If not, throw a {@link IllegalArgumentException}.
	 * 
	 * @param mode animation mode to be checked
	 */
	static void checkIfValid(IsAnimationModeKey mode) {
		if (!isValid(mode)) {
			// sets checking value
			boolean hasWrongName = mode != null && !AnimationElementChecker.get().isValid(mode.value(), DefaultAnimationCollectionKey.values(), DefaultAnimationPropertyKey.values());
			// gets the exception message
			// additional check to throw the right exception message
			String exceptionMessage = hasWrongName ? "Invalid animation mode name, '" + mode.value() + "' because is reserved" : "Animation mode is null or not consistent";
			// throws exception
			throw new IllegalArgumentException(exceptionMessage);
		}
	}

	/**
	 * Checks if mode passed as argument is not <code>null</code>.<br>
	 * If not, throw a {@link IllegalArgumentException}, otherwise it returns the argument.
	 * 
	 * @param mode mode to be checked
	 * @return the same mode passed as argument
	 */
	static IsAnimationModeKey checkAndGetIfValid(IsAnimationModeKey mode) {
		// checks if collection is consistent
		checkIfValid(mode);
		// if here, is consistent
		// then returns the argument
		return mode;
	}
}