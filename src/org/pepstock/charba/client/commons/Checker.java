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

import org.pepstock.charba.client.items.UndefinedValues;
import org.pepstock.charba.client.utils.Utilities;

/**
 * Utility which provides a set o methods to check the values of numbers.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Checker {

	// exception message template when the value is undefined
	private static final String ASSERT_EXCEPTION_MESSAGE = "The value is not true";
	// exception message template when the value is undefined
	private static final String UNDEFINED_EXCEPTION_MESSAGE_TEMPLATE = "{0} is not valid: {1}";
	// exception message template when the value is not positive
	private static final String POSITIVE_EXCEPTION_MESSAGE_TEMPLATE = "{0} is not positive: {1}";
	// exception message template when the value is not negative
	private static final String NEGATIVE_EXCEPTION_MESSAGE_TEMPLATE = "{0} is not negative: {1}";
	// exception message template when the value is not equals
	private static final String NOT_EQUALS_EXCEPTION_MESSAGE_TEMPLATE = "{0} is equals to {1}";
	// exception message template when the value is equals
	private static final String EQUALS_EXCEPTION_MESSAGE_TEMPLATE = "{0} is not equals to {2} but is {1}";
	// exception message template when the value is not greater
	private static final String GREATER_EXCEPTION_MESSAGE_TEMPLATE = "{0} is not greater than {2} but is {1}";
	// exception message template when the value is not less
	private static final String LESS_EXCEPTION_MESSAGE_TEMPLATE = "{0} is not less than {2} but is {1}";
	// exception message template when the value is not between
	private static final String BETWEEN_EXCEPTION_MESSAGE_TEMPLATE = "{0} is not between {2} and {3} but is {1}";

	/**
	 * To avoid any instantiation
	 */
	private Checker() {
		// do nothing
	}

	// -----------------
	// BOOLEAR
	// -----------------

	/**
	 * Checks if the value passed as argument is <code>true</code>.<br>
	 * If not, throw a {@link IllegalArgumentException}.
	 * 
	 * @param value value to be checked
	 */
	public static void assertCheck(boolean value) {
		assertCheck(value, ASSERT_EXCEPTION_MESSAGE);
	}

	/**
	 * Checks if the value passed as argument is <code>true</code>.<br>
	 * If not, throw a {@link IllegalArgumentException}.
	 * 
	 * @param value value to be checked
	 * @param what name of the value to put in the exception message
	 */
	public static void assertCheck(boolean value, String what) {
		if (!value) {
			throw new IllegalArgumentException(what == null ? ASSERT_EXCEPTION_MESSAGE : what);
		}
	}

	// -----------------
	// INTEGER
	// -----------------

	/**
	 * Returns <code>true</code> if the value is not undefined.
	 * 
	 * @param value value to be checked
	 * @return <code>true</code> if the value is not undefined
	 */
	public static boolean isValid(int value) {
		return value != UndefinedValues.INTEGER;
	}

	/**
	 * Checks if value passed as argument is valid.<br>
	 * If not, throw a {@link IllegalArgumentException}.
	 * 
	 * @param value value to be checked
	 * @param what name of the value to put in the exception message
	 */
	public static void checkIfValid(int value, String what) {
		if (!isValid(value)) {
			throwIllegalArgumentException(UNDEFINED_EXCEPTION_MESSAGE_TEMPLATE, what, value);
		}
	}

	/**
	 * Checks if value passed as argument is valid and, if valid, returns the argument.<br>
	 * If not, throw a {@link IllegalArgumentException}.
	 * 
	 * @param value value to be checked
	 * @param what name of the value to put in the exception message
	 * @return the value passed as argument
	 */
	public static int checkAndGetIfValid(int value, String what) {
		checkIfValid(value, what);
		return value;
	}

	/**
	 * Checks if value passed as argument is a positive number (zero is included).
	 *  
	 * @param value value to be checked
	 * @return <code>true</code> if the value is positive number (zero is included)
	 */
	public static boolean isPositive(int value) {
		return value >= 0;
	}

	/**
	 * Checks if value passed as argument is a positive number (zero is included).<br>
	 * If not, throw a {@link IllegalArgumentException}.
	 * 
	 * @param value value to be checked
	 * @param what name of the value to put in the exception message
	 */
	public static void checkIfPositive(int value, String what) {
		if (!isPositive(value)) {
			throwIllegalArgumentException(POSITIVE_EXCEPTION_MESSAGE_TEMPLATE, what, value);
		}
	}
	
	/**
	 * Checks if value passed as argument is a negative number (zero is excluded).
	 *  
	 * @param value value to be checked
	 * @return <code>true</code> if the value is positive number (zero is excluded)
	 */
	public static boolean isNegative(int value) {
		return value < 0;
	}

	/**
	 * Checks if value passed as argument is a negative number (zero is excluded).<br>
	 * If not, throw a {@link IllegalArgumentException}.
	 * 
	 * @param value value to be checked
	 * @param what name of the value to put in the exception message
	 */
	public static void checkIfNegative(int value, String what) {
		if (!isNegative(value)) {
			throwIllegalArgumentException(NEGATIVE_EXCEPTION_MESSAGE_TEMPLATE, what, value);
		}
	}

	/**
	 * Checks if both values passed as argument are not equals.
	 *  
	 * @param value value to be checked
	 * @param target value to be compared to the value
	 * @return <code>true</code> if the arguments are not equals
	 */
	public static boolean isNotEqualTo(int value, int target) {
		return value != target;
	}

	/**
	 * Checks if both values passed as argument are not equals.<br>
	 * If not, throw a {@link IllegalArgumentException}.
	 * 
	 * @param value value to be checked
	 * @param target value to be compared to the value
	 * @param what name of the value to put in the exception message
	 */
	public static void checkIfNotEqualTo(int value, int target, String what) {
		if (isEqualTo(value, target)) {
			throwIllegalArgumentException(NOT_EQUALS_EXCEPTION_MESSAGE_TEMPLATE, what, value);
		}
	}

	/**
	 * Checks if both values passed as argument are equals.
	 *  
	 * @param value value to be checked
	 * @param target value to be compared to the value
	 * @return <code>true</code> if the arguments are equals
	 */
	public static boolean isEqualTo(int value, int target) {
		return value == target;
	}

	/**
	 * Checks if both values passed as argument are equals.<br>
	 * If not, throw a {@link IllegalArgumentException}.
	 * 
	 * @param value value to be checked
	 * @param target value to be compared to the value
	 * @param what name of the value to put in the exception message
	 */
	public static void checkIfEqualTo(int value, int target, String what) {
		if (isNotEqualTo(value, target)) {
			throwIllegalArgumentException(EQUALS_EXCEPTION_MESSAGE_TEMPLATE, what, value, target);
		}
	}

	/**
	 * Checks if the value passed as argument is greater than the threshold passed as argument (inclusive).
	 *  
	 * @param value value to be checked
	 * @param threshold value to be compared to the value
	 * @return <code>true</code> if the value is greater than the threshold (inclusive)
	 */
	public static boolean isGreaterThan(int value, int threshold) {
		return value >= threshold;
	}

	/**
	 * Checks if the value passed as argument is greater than the threshold passed as argument (inclusive).<br>
	 * If not, throw a {@link IllegalArgumentException}.
	 * 
	 * @param value value to be checked
	 * @param threshold value to be compared to the value
	 * @param what name of the value to put in the exception message
	 */
	public static void checkIfGreaterThan(int value, int threshold, String what) {
		if (!isGreaterThan(value, threshold)) {
			throwIllegalArgumentException(GREATER_EXCEPTION_MESSAGE_TEMPLATE, what, value, threshold);
		}
	}

	/**
	 * Checks if the value passed as argument is less than the threshold passed as argument (inclusive).
	 *  
	 * @param value value to be checked
	 * @param threshold value to be compared to the value
	 * @return <code>true</code> if the value is less than the threshold (inclusive)
	 */
	public static boolean isLessThan(int value, int threshold) {
		return value <= threshold;
	}

	/**
	 * Checks if the value passed as argument is less than the threshold passed as argument (inclusive).<br>
	 * If not, throw a {@link IllegalArgumentException}.
	 * 
	 * @param value value to be checked
	 * @param threshold value to be compared to the value
	 * @param what name of the value to put in the exception message
	 */
	public static void checkIfLessThan(int value, int threshold, String what) {
		if (!isLessThan(value, threshold)) {
			throwIllegalArgumentException(LESS_EXCEPTION_MESSAGE_TEMPLATE, what, value, threshold);
		}
	}

	/**
	 * Checks if the value passed as argument is between minimum and maximum values, passed as argument (inclusive).
	 *  
	 * @param value value to be checked
	 * @param minimum minimum limit to compare to the value
	 * @param maximum maximum limit to compare to the value
	 * @return <code>true</code> if the value passed as argument is between minimum and maximum values, passed as argument (inclusive
	 */
	public static boolean isBetween(int value, int minimum, int maximum) {
		return value >= minimum && value <= maximum;
	}

	/**
	 * Checks if the value passed as argument is between minimum and maximum values, passed as argument (inclusive).<br>
	 * If not, throw a {@link IllegalArgumentException}.
	 * 
	 * @param value value to be checked
	 * @param minimum minimum limit to compare to the value
	 * @param maximum maximum limit to compare to the value
	 * @param what name of the value to put in the exception message
	 */
	public static void checkIfBetween(int value, int minimum, int maximum, String what) {
		if (!isBetween(value, minimum, maximum)) {
			throwIllegalArgumentException(BETWEEN_EXCEPTION_MESSAGE_TEMPLATE, what, value, minimum, maximum);
		}
	}

	// -----------------
	// DOUBLE 
	// -----------------

	/**
	 * Returns <code>true</code> if the value is not undefined.
	 * 
	 * @param value value to be checked
	 * @return <code>true</code> if the value is not undefined
	 */
	public static boolean isValid(double value) {
		return !Double.isNaN(value) && !Double.isInfinite(value);
	}

	/**
	 * Checks if value passed as argument is valid.<br>
	 * If not, throw a {@link IllegalArgumentException}.
	 * 
	 * @param value value to be checked
	 * @param what name of the value to put in the exception message
	 */
	public static void checkIfValid(double value, String what) {
		if (!isValid(value)) {
			throwIllegalArgumentException(UNDEFINED_EXCEPTION_MESSAGE_TEMPLATE, what, value);
		}
	}

	/**
	 * Checks if value passed as argument is valid and, if valid, returns the argument.<br>
	 * If not, throw a {@link IllegalArgumentException}.
	 * 
	 * @param value value to be checked
	 * @param what name of the value to put in the exception message
	 * @return the value passed as argument
	 */
	public static double checkAndGetIfValid(double value, String what) {
		checkIfValid(value, what);
		return value;
	}

	/**
	 * Checks if value passed as argument is a positive number (zero is included).
	 *  
	 * @param value value to be checked
	 * @return <code>true</code> if the value is positive number (zero is included)
	 */
	public static boolean isPositive(double value) {
		return isValid(value) && value >= 0;
	}

	/**
	 * Checks if value passed as argument is a positive number (zero is included).<br>
	 * If not, throw a {@link IllegalArgumentException}.
	 * 
	 * @param value value to be checked
	 * @param what name of the value to put in the exception message
	 */
	public static void checkIfPositive(double value, String what) {
		if (!isPositive(value)) {
			throwIllegalArgumentException(POSITIVE_EXCEPTION_MESSAGE_TEMPLATE, what, value);
		}
	}

	/**
	 * Checks if value passed as argument is a negative number (zero is excluded).
	 *  
	 * @param value value to be checked
	 * @return <code>true</code> if the value is positive number (zero is excluded)
	 */
	public static boolean isNegative(double value) {
		return isValid(value) && value < 0;
	}

	/**
	 * Checks if value passed as argument is a negative number (zero is excluded).<br>
	 * If not, throw a {@link IllegalArgumentException}.
	 * 
	 * @param value value to be checked
	 * @param what name of the value to put in the exception message
	 */
	public static void checkIfNegative(double value, String what) {
		if (!isNegative(value)) {
			throwIllegalArgumentException(NEGATIVE_EXCEPTION_MESSAGE_TEMPLATE, what, value);
		}
	}

	/**
	 * Checks if both values passed as argument are not equals.
	 *  
	 * @param value value to be checked
	 * @param target value to be compared to the value
	 * @return <code>true</code> if the arguments are not equals
	 */
	public static boolean isNotEqualTo(double value, double target) {
		return isValid(value) && isValid(target) && Double.compare(value, target) != 0;
	}

	/**
	 * Checks if both values passed as argument are not equals.<br>
	 * If not, throw a {@link IllegalArgumentException}.
	 * 
	 * @param value value to be checked
	 * @param target value to be compared to the value
	 * @param what name of the value to put in the exception message
	 */
	public static void checkIfNotEqualTo(double value, double target, String what) {
		if (isEqualTo(value, target)) {
			throwIllegalArgumentException(NOT_EQUALS_EXCEPTION_MESSAGE_TEMPLATE, what, value);
		}
	}

	/**
	 * Checks if both values passed as argument are equals.
	 *  
	 * @param value value to be checked
	 * @param target value to be compared to the value
	 * @return <code>true</code> if the arguments are equals
	 */
	public static boolean isEqualTo(double value, double target) {
		return isValid(value) && isValid(target) && Double.compare(value, target) == 0;
	}

	/**
	 * Checks if both values passed as argument are equals.<br>
	 * If not, throw a {@link IllegalArgumentException}.
	 * 
	 * @param value value to be checked
	 * @param target value to be compared to the value
	 * @param what name of the value to put in the exception message
	 */
	public static void checkIfEqualTo(double value, double target, String what) {
		if (isNotEqualTo(value, target)) {
			throwIllegalArgumentException(EQUALS_EXCEPTION_MESSAGE_TEMPLATE, what, value, target);
		}
	}

	/**
	 * Checks if the value passed as argument is greater than the threshold passed as argument (inclusive).
	 *  
	 * @param value value to be checked
	 * @param threshold value to be compared to the value
	 * @return <code>true</code> if the value is greater than the threshold (inclusive)
	 */
	public static boolean isGreaterThan(double value, double threshold) {
		return isValid(value) && isValid(threshold) && value >= threshold;
	}

	/**
	 * Checks if the value passed as argument is greater than the threshold passed as argument (inclusive).<br>
	 * If not, throw a {@link IllegalArgumentException}.
	 * 
	 * @param value value to be checked
	 * @param threshold value to be compared to the value
	 * @param what name of the value to put in the exception message
	 */
	public static void checkIfGreaterThan(double value, double threshold, String what) {
		if (!isGreaterThan(value, threshold)) {
			throwIllegalArgumentException(GREATER_EXCEPTION_MESSAGE_TEMPLATE, what, value, threshold);
		}
	}

	/**
	 * Checks if the value passed as argument is less than the threshold passed as argument (inclusive).
	 *  
	 * @param value value to be checked
	 * @param threshold value to be compared to the value
	 * @return <code>true</code> if the value is less than the threshold (inclusive)
	 */
	public static boolean isLessThan(double value, double threshold) {
		return isValid(value) && isValid(threshold) && value <= threshold;
	}

	/**
	 * Checks if the value passed as argument is less than the threshold passed as argument (inclusive).<br>
	 * If not, throw a {@link IllegalArgumentException}.
	 * 
	 * @param value value to be checked
	 * @param threshold value to be compared to the value
	 * @param what name of the value to put in the exception message
	 */
	public static void checkIfLessThan(double value, double threshold, String what) {
		if (!isLessThan(value, threshold)) {
			throwIllegalArgumentException(LESS_EXCEPTION_MESSAGE_TEMPLATE, what, value, threshold);
		}
	}

	/**
	 * Checks if the value passed as argument is between minimum and maximum values, passed as argument (inclusive).
	 *  
	 * @param value value to be checked
	 * @param minimum minimum limit to compare to the value
	 * @param maximum maximum limit to compare to the value
	 * @return <code>true</code> if the value passed as argument is between minimum and maximum values, passed as argument (inclusive
	 */
	public static boolean isBetween(double value, double minimum, double maximum) {
		return isValid(value) && isValid(minimum) && isValid(maximum) && value >= minimum && value <= maximum;
	}

	/**
	 * Checks if the value passed as argument is between minimum and maximum values, passed as argument (inclusive).<br>
	 * If not, throw a {@link IllegalArgumentException}.
	 * 
	 * @param value value to be checked
	 * @param minimum minimum limit to compare to the value
	 * @param maximum maximum limit to compare to the value
	 * @param what name of the value to put in the exception message
	 */
	public static void checkIfBetween(double value, double minimum, double maximum, String what) {
		if (!isBetween(value, minimum, maximum)) {
			throwIllegalArgumentException(BETWEEN_EXCEPTION_MESSAGE_TEMPLATE, what, value, minimum, maximum);
		}
	}

	// -----------------
	// OBJECT 
	// -----------------

	/**
	 * Returns <code>true</code> if the value is not undefined.
	 * 
	 * @param value value to be checked
	 * @return <code>true</code> if the value is not undefined
	 */
	public static boolean isValid(Object value) {
		return value != null;
	}

	/**
	 * Checks if value passed as argument is valid.<br>
	 * If not, throw a {@link IllegalArgumentException}.
	 * 
	 * @param value value to be checked
	 * @param what name of the value to put in the exception message
	 */
	public static void checkIfValid(Object value, String what) {
		if (!isValid(value)) {
			throwIllegalArgumentException(UNDEFINED_EXCEPTION_MESSAGE_TEMPLATE, what, value);
		}
	}

	/**
	 * Checks if value passed as argument is valid and, if valid, returns the argument.<br>
	 * If not, throw a {@link IllegalArgumentException}.
	 * 
	 * @param value value to be checked
	 * @param what name of the value to put in the exception message
	 * @param <T> type of the value to be checked
	 * @return the value passed as argument
	 */
	public static <T> T checkAndGetIfValid(T value, String what) {
		checkIfValid(value, what);
		// returns the value
		return value;
	}

	/**
	 * Creates the exception message and throw a {@link IllegalArgumentException}.
	 * 
	 * @param template template of exception message
	 * @param objects values to apply in the template
	 */
	private static void throwIllegalArgumentException(String template, Object... objects) {
		throw new IllegalArgumentException(Utilities.applyTemplate(template, objects));
	}
}
