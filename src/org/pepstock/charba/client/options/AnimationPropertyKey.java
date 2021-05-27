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
import org.pepstock.charba.client.enums.AnimationType;
import org.pepstock.charba.client.enums.DefaultAnimationPropertyKey;

/**
 * Represents the property id to set to the configure animation collection items.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface AnimationPropertyKey extends AnimationCollectionKey {

	/**
	 * Returns a animation property instance by its string value.
	 * 
	 * @param property string value to use
	 * @param type type related to the typeof property
	 * @return new property instance
	 */
	static AnimationPropertyKey create(String property, AnimationType type) {
		// checks if property as argument is a default one
		if (DefaultAnimationPropertyKey.is(property)) {
			// returns the value
			return Key.getKeyByValue(DefaultAnimationPropertyKey.values(), property);
		}
		// if here, is not a default one
		// then creates new animation property
		return new StandardAnimationProperty(property, type);

	}

	/**
	 * Returns <code>true</code> if type passed as argument is not <code>null</code> and its type is not <code>null</code> as well.
	 * 
	 * @param property animation property to be checked
	 * @return <code>true</code> if type passed as argument is not <code>null</code> and its type is not <code>null</code> as well.
	 */
	static boolean isValid(AnimationPropertyKey property) {
		return Key.isValid(property) && Key.isValid(property.type());
	}

	/**
	 * Checks if type passed as argument is not <code>null</code> and its type is not <code>null</code> as well.<br>
	 * If not, throw a {@link IllegalArgumentException}.
	 * 
	 * @param property animation property to be checked
	 */
	static void checkIfValid(AnimationPropertyKey property) {
		if (!isValid(property)) {
			// gets the exception message
			// additional check to throw the right exception message
			String exceptionMessage = property != null ? "Invalid animation property name, '" + property.value() + "' because is reserved" : "Animation property is null or not consistent";
			// throws exception
			throw new IllegalArgumentException(exceptionMessage);
		}
	}

	/**
	 * Checks if property passed as argument is not <code>null</code> and its value is not <code>null</code> as well.<br>
	 * If not, throw a {@link IllegalArgumentException}, otherwise it returns the key.
	 * 
	 * @param property property to be checked
	 * @return the same property passed as argument
	 */
	static AnimationPropertyKey checkAndGetIfValid(AnimationPropertyKey property) {
		// checks if property is consistent
		checkIfValid(property);
		// if here, is consistent
		// then returns the argument
		return property;
	}

}