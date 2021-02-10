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
package org.pepstock.charba.client.data;

import org.pepstock.charba.client.items.UndefinedValues;

/**
 * Manages the stack properties for datasets which this property is required.
 * 
 * @author Andrea "Stock" Stocchero
 */
interface HasBarStackGroup {

	/**
	 * Returns an stack option handler instance.
	 * 
	 * @return an stack option handler instance
	 */
	BarStackHandler getBarStackHandler();

	/**
	 * Sets the name of stack group.
	 * 
	 * @param stackGroup name of stack group.
	 */
	default void setStackGroup(String stackGroup) {
		// checks if bar stack handler is consistent
		if (getBarStackHandler() != null) {
			getBarStackHandler().setStackGroup(stackGroup);
		}
	}

	/**
	 * Returns the name of stack group.
	 * 
	 * @return the name of stack group.
	 */
	default String getStackGroup() {
		// checks if bar stack handler is consistent
		if (getBarStackHandler() != null) {
			return getBarStackHandler().getStackGroup();
		}
		// if here, bar stack handler is not consistent
		// then returns the default
		return UndefinedValues.STRING;
	}

}