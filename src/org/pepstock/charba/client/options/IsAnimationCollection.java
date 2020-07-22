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

import java.util.List;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.AnimationType;
import org.pepstock.charba.client.enums.DefaultAnimationCollection;
import org.pepstock.charba.client.enums.DefaultAnimationMode;
import org.pepstock.charba.client.enums.DefaultAnimationProperty;

/**
 * Represents the collection to set to configure animation.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface IsAnimationCollection extends Key {

	/**
	 * Returns a animation collection instance by its string value.
	 * 
	 * @param collection string value to use
	 * @param type type of the properties
	 * @return new collection instance
	 */
	static IsAnimationCollection create(String collection, AnimationType type) {
		// checks if mode as argument is a default one
		for (DefaultAnimationCollection defCollection : DefaultAnimationCollection.values()) {
			// checks if mode is equals to default
			if (defCollection.value().equals(collection)) {
				// if equals, returns the default mode
				return defCollection;
			}
		}
		// if here, is not a default one
		// then creates new animation collection
		return new StandardAnimationCollection(collection, type);
	}

	/**
	 * Returns a animation collection instance by its string value.
	 * 
	 * @param collection string value to use
	 * @param properties initial collection of properties
	 * @return new collection instance
	 */
	static IsAnimationCollection create(String collection, IsAnimationProperty... properties) {
		// checks if properties are consistent
		if (properties != null && properties.length > 0) {
			// get result reference
			IsAnimationCollection result = null;
			// scans all properties
			for (IsAnimationProperty property : properties) {
				// checks the consistency of property
				IsAnimationProperty.checkIfValid(property);
				// checks if is the first property
				if (result == null) {
					// creates the collection to return
					result = create(collection, property.type());
				} else if (!result.type().equals(property.type())) {
					// checks if they have got the same type
					throw new IllegalArgumentException("Animation collection properties do not have the same type");
				}
				// adds property
				result.properties().add(property);
			}
			return result;
		}
		// if here, the properties are not consistent
		// then throw an exception
		throw new IllegalArgumentException("Animation collection properties are not consistent");
	}

	/**
	 * Returns <code>true</code> if type passed as argument is not <code>null</code> and its type is not <code>null</code> as well.
	 * 
	 * @param collection animation collection to be checked
	 * @return <code>true</code> if type passed as argument is not <code>null</code> and its type is not <code>null</code> as well.
	 */
	static boolean isValid(IsAnimationCollection collection) {
		return Key.isValid(collection) && Key.isValid(collection.type()) && AnimationElementChecker.get().isValid(collection, DefaultAnimationMode.values(), DefaultAnimationProperty.values());
	}

	/**
	 * Checks if type passed as argument is not <code>null</code> and its type is not <code>null</code> as well.<br>
	 * If not, throw a {@link IllegalArgumentException}.
	 * 
	 * @param collection animation collection to be checked
	 */
	static void checkIfValid(IsAnimationCollection collection) {
		if (!isValid(collection)) {
			throw new IllegalArgumentException("Animation collection is null or not consistent");
		}
	}

	/**
	 * Checks if collection passed as argument is not <code>null</code> and its value is not <code>null</code> as well.<br>
	 * If not, throw a {@link IllegalArgumentException}, otherwise it returns the argument.
	 * 
	 * @param collection collection to be checked
	 * @return the same collection passed as argument
	 */
	static IsAnimationCollection checkAndGetIfValid(IsAnimationCollection collection) {
		// checks if collection is consistent
		checkIfValid(collection);
		// if here, is consistent
		// then returns the argument
		return collection;
	}

	/**
	 * Returns the animation type related to the collection.
	 * 
	 * @return the animation type related to the collection
	 */
	AnimationType type();

	/**
	 * Returns the animation properties related to the collection.
	 * 
	 * @return the animation properties related to the collection
	 */
	List<IsAnimationProperty> properties();

}