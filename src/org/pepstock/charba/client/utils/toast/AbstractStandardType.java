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
package org.pepstock.charba.client.utils.toast;

import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.Key;

/**
 * This is an abstract standard implementation of a custom toast type.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
abstract class AbstractStandardType implements Key {

	// name of type
	private final Key name;
	// type color instance
	private final IsColor backgroundColor;
	// flag is injected
	private boolean injected = false;

	/**
	 * Builds the object with the custom key value
	 * 
	 * @param name value to use inside the native object as name of property
	 * @param backgroundColor background color of toast
	 */
	AbstractStandardType(Key name, IsColor backgroundColor) {
		// stores name
		this.name = name;
		this.backgroundColor = backgroundColor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.commons.Key#value()
	 */
	@Override
	public final String value() {
		return name.value();
	}

	/**
	 * Returns the background color of the toast.
	 * 
	 * @return the background color of the toast
	 */
	public final IsColor getBackgroundColor() {
		return backgroundColor;
	}

	/**
	 * Returns <code>true</code> if the new CSS type has been injected.
	 * 
	 * @return <code>true</code> if the new CSS type has been injected
	 */
	final boolean isInjected() {
		return injected;
	}

	/**
	 * Sets <code>true</code> if the new CSS type has been injected.
	 * 
	 * @param injected <code>true</code> if the new CSS type has been injected
	 */
	final void setInjected(boolean injected) {
		this.injected = injected;
	}

}
