/**
    Copyright 2017 Andrea "Stock" Stocchero

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

	    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUString WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/
package org.pepstock.charba.client.jsinterop.commons;

/**
 * Utility class to check values setting the right values between the provided value and default. 
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 */
public final class Checker {

	/**
	 * To avoid any instantiation
	 */
	private Checker() {
		// do nothing
	}

	/**
	 * Checks 2 java objects and returns the no-null one.
	 * @param value original value
	 * @param defaultValue default value
	 * @return returns the no-null one.
	 */
	public static <T> T check(T value, T defaultValue) {
		return value == null ? defaultValue : value;
	}

	/**
	 * Checks 2 strings and returns the no-null one.
	 * @param value original value
	 * @param defaultValue default value
	 * @return returns the no-null one.
	 */
	public static String check(String value, String defaultValue) {
		return value == null ? defaultValue : value;
	}
	
	/**
	 * Checks 2 booleans and returns the no-null one.
	 * @param value original value
	 * @param defaultValue default value
	 * @return returns the no-null one.
	 */
	public static boolean check(boolean value, boolean defaultValue) {
		// transforms the value into string because a boolean can not be null
		String stringValue = String.valueOf(value);
		// by java script, if value is null, to string you have "undefined"
		return ObjectType.Undefined.name().equalsIgnoreCase(stringValue) ? defaultValue : value;
	}

	/**
	 * Checks 2 integers and returns the no-null one.
	 * @param value original value
	 * @param defaultValue default value
	 * @return returns the no-null one.
	 */
	public static int check(int value, int defaultValue) {
		return Double.isNaN(value) ? defaultValue : value;
	}

	/**
	 * Checks 2 doubles and returns the no-null one.
	 * @param value original value
	 * @param defaultValue default value
	 * @return returns the no-null one.
	 */
	public static double check(double value, double defaultValue) {
		return Double.isNaN(value) ? defaultValue : value;
	}

}