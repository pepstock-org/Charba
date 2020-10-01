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
package org.pepstock.charba.client.commons;

/**
 * Represents the key of property of a java-script object.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface PropertyKey extends Key {

	/**
	 * Returns <code>true</code> if id passed as argument is not <code>null</code> and its value could be a valid property key.
	 * 
	 * @param id id to be checked
	 * @return <code>true</code> if id passed as argument is not <code>null</code> and its value could be a valid property key.
	 */
	static boolean isValid(String id) {
		return PropertyKeyChecker.isValid(id);
	}

	/**
	 * Returns <code>true</code> if id passed as argument is not <code>null</code> and its value is not <code>null</code> as well and could be a valid property key.
	 * 
	 * @param id id to be checked
	 * @return <code>true</code> if id passed as argument is not <code>null</code> and its value is not <code>null</code> as well and could be a valid property key.
	 */
	static boolean isValid(PropertyKey id) {
		return Key.isValid(id) && isValid(id.value());
	}

	/**
	 * Checks if id passed as argument is not <code>null</code> and its value is not <code>null</code> as well and could be a valid property key.<br>
	 * If not, throw a {@link IllegalArgumentException}.
	 * 
	 * @param id id to be checked
	 */
	static void checkIfValid(PropertyKey id) {
		PropertyKeyChecker.check(id);
	}

	/**
	 * Checks if id passed as argument is not <code>null</code> and its value is not <code>null</code> as well and could be a valid property key.<br>
	 * If not, throw a {@link IllegalArgumentException}.
	 * 
	 * @param id id as string to be checked
	 */
	static void checkIfValid(String id) {
		PropertyKeyChecker.check(id);
	}

}