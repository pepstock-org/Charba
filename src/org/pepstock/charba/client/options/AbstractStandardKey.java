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

/**
 * This is an abstract standard implementation of a custom key.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
abstract class AbstractStandardKey implements Key {

	private final String value;

	/**
	 * Builds the object with the custom key value as string
	 * 
	 * @param value value of key as String
	 */
	AbstractStandardKey(String value) {
		// stores value
		this.value = value;
		// pay attention
		// it does not perform any check
		// because it must be done on original constructor of the object
		// which extends this class
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.commons.Key#value()
	 */
	@Override
	public final String value() {
		return value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		// checks if is the same object
		if (this == obj) {
			return true;
		}
		// checks if argument is null
		if (obj == null) {
			return false;
		}
		// checks if the class is the same
		if (getClass() != obj.getClass()) {
			return false;
		}
		// casts to a standard key
		AbstractStandardKey other = (AbstractStandardKey) obj;
		// checks if keys are equals
		return Key.equals(this, other);
	}

}
