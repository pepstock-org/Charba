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

import java.util.Date;

import com.google.gwt.core.client.JsDate;

/**
 * Utility class to check values setting the right values between the provided value and default. 
 * 
 * @author Andrea "Stock" Stocchero
 * @version 2.0
 */
public final class Checker {
	
	// internal label to know if java script instance is undefined
	private static final String UNDEFINED = "undefined";
	
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
		return UNDEFINED.equalsIgnoreCase(stringValue) ? defaultValue : value;
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

	/**
	 * Checks an object which could represents a integer and returns the no-null one.<br>
	 * This kind of check is mandatory because in a java script a property could have different types.
	 * @param value original value
	 * @param defaultValue default value
	 * @return returns the no-null one.
	 */
	public static int check(Object value, int defaultValue) {
		return value == null ? defaultValue : (int)value;
	}

	/**
	 * Checks an object which could represents a double and returns the no-null one.<br>
	 * This kind of check is mandatory because in a java script a property could have different types.
	 * @param value original value
	 * @param defaultValue default value
	 * @return returns the no-null one.
	 */
	public static double check(Object value, double defaultValue) {
		return value == null ? defaultValue : (double)value;
	}

	/**
	 * Checks an object which could represents a string and returns the no-null one.<br>
	 * This kind of check is mandatory because in a java script a property could have different types.
	 * @param value original value
	 * @param defaultValue default value
	 * @return returns the no-null one.
	 */
	public static String check(Object value, String defaultValue) {
		return value == null ? defaultValue : value.toString();
	}
	
	/**
	 * Transforms a JAVA date into a java script date.
	 * @param date date to be transformed
	 * @return java script date or <code>null</code> if passed date is <code>null</code>.
	 */
	public static JsDate fromDate(Date date) {
		// creates a java script date casting long to double
		return date != null ? JsDate.create((double) date.getTime()) : null;
	}
	
	/**
	 * Transforms a java script date into a JAVA date.
	 * @param date date to be transformed
	 * @return java date or <code>null</code> if passed date is <code>null</code>.
	 */
	public static Date toDate(JsDate date) {
		// creates a java date casting double to long
		return date != null ? new Date((long)date.getTime()) : null;
	}
}