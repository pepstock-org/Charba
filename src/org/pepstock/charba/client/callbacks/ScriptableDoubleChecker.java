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

/**
 * Checker out of the box for {@link Scriptable}s which return a {@link Double}.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum ScriptableDoubleChecker implements ScriptableResultChecker<Double>
{
	VALID_OR_ZERO((value, defaultValue) -> Checker.validOrZero(value)),
	VALID_OR_DEFAULT((value, defaultValue) -> Checker.validOrDefault(value, defaultValue)),
	POSITIVE_OR_ZERO((value, defaultValue) -> Checker.positiveOrZero(value)),
	POSITIVE_OR_DEFAULT((value, defaultValue) -> Checker.positiveOrDefault(value, defaultValue)),
	NEGATIVE_OR_ZERO((value, defaultValue) -> Checker.negativeOrZero(value)),
	NEGATIVE_OR_DEFAULT((value, defaultValue) -> Checker.negativeOrDefault(value, defaultValue)),
	GREATER_THAN_OR_ZERO((value, defaultValue) -> Checker.greaterThanOrZero(value, defaultValue)),
	LESS_THAN_OR_ZERO((value, defaultValue) -> Checker.lessThanOrZero(value, defaultValue));

	private final ScriptableResultChecker<Double> checker;

	/**
	 * Creates the object with a specific checker and its logic.
	 * 
	 * @param checker checker instance to check the result
	 */
	private ScriptableDoubleChecker(ScriptableResultChecker<Double> checker) {
		this.checker = checker;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.callbacks.ScriptableResultChecker#checkAndGet(java.lang.Number, java.lang.Number)
	 */
	@Override
	public Double checkAndGet(Double value, Double defaultValue) {
		return checker.checkAndGet(value, defaultValue);
	}

}
