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
package org.pepstock.charba.client.annotation;

import org.pepstock.charba.client.commons.PropertyKey;

/**
 * Represents the annotation id of a annotation configuration object into {@link AnnotationHelper#ID} plugin.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface IsAnnotationId extends PropertyKey {

	/**
	 * Returns a key instance by its string value.
	 * 
	 * @param id string value to use
	 * @return new annotation configuration object id instance
	 */
	static IsAnnotationId create(String id) {
		// checks if passed id is consistent
		PropertyKey.checkIfValid(id);
		// creates new annotation id
		return new StandardAnnotationId(id);
	}

	/**
	 * Returns <code>true</code> if id passed as argument is not <code>null</code> and its value could be a valid annotation configuration object id.
	 * 
	 * @param id annotation configuration object id to be checked
	 * @return <code>true</code> if id passed as argument is not <code>null</code> and its value could be a valid annotation configuration object id.
	 */
	static boolean isValid(String id) {
		return PropertyKey.isValid(id);
	}

	/**
	 * Returns <code>true</code> if id passed as argument is not <code>null</code> and its value is not <code>null</code> as well and could be a valid annotation configuration
	 * object id.
	 * 
	 * @param id annotation configuration object id to be checked
	 * @return <code>true</code> if id passed as argument is not <code>null</code> and its value is not <code>null</code> as well and could be a valid annotation configuration
	 *         object id.
	 */
	static boolean isValid(IsAnnotationId id) {
		return PropertyKey.isValid(id);
	}

	/**
	 * Checks if id passed as argument is not <code>null</code> and its value is not <code>null</code> as well and could be a valid annotation configuration object id.<br>
	 * If not, throw a {@link IllegalArgumentException}.
	 * 
	 * @param id annotation configuration object id to be checked
	 */
	static void checkIfValid(IsAnnotationId id) {
		PropertyKey.checkIfValid(id);
	}

	/**
	 * Checks if id passed as argument is not <code>null</code> and its value is not <code>null</code> as well and could be a valid annotation configuration object id.<br>
	 * If not, throw a {@link IllegalArgumentException}.
	 * 
	 * @param id annotation configuration object id as string to be checked
	 */
	static void checkIfValid(String id) {
		PropertyKey.checkIfValid(id);
	}

	/**
	 * Returns the ID of the annotation checking is consistent.
	 * 
	 * @param id id value, as string, to create new annotation id object
	 * @param defaultValue the default value if the id parameter is not a valid, it can not be a annotation id due to its value.
	 * @return the ID of the annotation
	 */
	static IsAnnotationId checkAndGetAnnotationID(String id, IsAnnotationId defaultValue) {
		// checks if default value is consistent
		if (IsAnnotationId.isValid(id)) {
			// creates new annotation configuration object id with id stored into the object
			return IsAnnotationId.create(id);
		}
		// if here not, default value is not consistent
		// then returns the default
		return defaultValue;
	}

}