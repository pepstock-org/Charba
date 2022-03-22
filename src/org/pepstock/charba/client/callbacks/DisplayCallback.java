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
package org.pepstock.charba.client.callbacks;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.Display;

/**
 * Callback interface to set <code>display</code> property where it can be set by {@link Display} or a boolean.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface DisplayCallback<C extends ChartContext> extends Scriptable<Object, C> {

	/**
	 * Checks the result of this callback returning the right value to set in the options.
	 * 
	 * @param result result of callback invocation
	 * @param defaultValue default value to use if the result is not consistent
	 * @return the right value to set in the options
	 */
	static Object checkAndGet(Object result, Display defaultValue) {
		// gets return value using the default argument
		Display toReturn = Key.checkAndGetIfValid(defaultValue);
		// checks callback result
		if (result instanceof Boolean) {
			// cats and returns the boolean value
			return ((Boolean) result).booleanValue();
		} else if (result instanceof Display) {
			// casts
			toReturn = (Display) result;
		}
		// if here, returns the default
		// because the callback is not consistent
		// checks if is auto
		if (Display.AUTO.equals(toReturn)) {
			return Display.AUTO.value();
		}
		// returns the boolean
		return Display.TRUE.equals(toReturn);
	}

}
