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

import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.data.BarBorderRadius;

/**
 * Callback interface to set whatever <code>borderRadius</code> property at runtime.
 * 
 * @author Andrea "Stock" Stocchero
 *
 * @param <C> type of context to pass to the callback.
 */
public interface BorderRadiusCallback<C extends ChartContext> extends Scriptable<Object, C> {

	/**
	 * Returns an {@link BarBorderRadius} instance when the callback has been activated.
	 * 
	 * @param context annotation context instance.
	 * @param callback {@link BorderRadiusCallback} instance to be invoked
	 * @param defaultValue default value for this border radius.
	 * @param <C> type of chart context
	 * @return a object property value, as {@link BarBorderRadius}
	 */
	static <C extends ChartContext> BarBorderRadius toObject(C context, BorderRadiusCallback<C> callback, int defaultValue) {
		int valueToReturn = defaultValue;
		// gets value
		Object value = ScriptableUtil.getOptionValue(context, callback);
		// checks if is an object
		if (value instanceof BarBorderRadius) {
			// casts to border radius object
			return (BarBorderRadius) value;
		} else if (value instanceof Number) {
			// checks if is an number
			// casts to number
			Number number = (Number) value;
			// stores to result
			valueToReturn = Checker.positiveOrZero(number.intValue());
		}
		// cats to a object
		return new BarBorderRadius(valueToReturn);
	}

}
