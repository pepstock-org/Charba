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

/**
 * To implement to check the consistency of the result, as a number, of a {@link Scriptable}.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public interface ScriptableResultChecker<T extends Number> {

	/**
	 * Checks and gets a value, managing the vaue of the {@link Scriptable} and the default.
	 * 
	 * @param value result of a {@link Scriptable}
	 * @param defaultValue default value for the specific option
	 * @return a checked value, as number
	 */
	T checkAndGet(T value, T defaultValue);
}
