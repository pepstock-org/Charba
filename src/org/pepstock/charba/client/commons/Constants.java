/**
    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.
*/
package org.pepstock.charba.client.commons;

import org.pepstock.charba.client.utils.RegExp;

/**
 * Contains all constants where values can be used in different packages.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Constants {

	// regexp pattern to have only letters and number
	private static final String REGEXP_ID_PATTERN = "^[a-zA-Z0-9_]+$";
	/**
	 * Regular expression to check if a string can be used as controller type or plugin ID, <b>"^[a-zA-Z0-9_]+$"</b>
	 */
	public static final RegExp REGEXP_ID = new RegExp(REGEXP_ID_PATTERN);

	/**
	 * Constant for EMPTY string, <b>{@value}</b>.
	 */
	public static final String EMPTY_STRING = "";

	/**
	 * Constant for APOSTROPHE string, <b>{@value}</b>.
	 */
	public static final String APOSTROPHE = "'";

	/**
	 * Constant for SLASH string, <b>{@value}</b>.
	 */
	public static final String SLASH = "/";

	/**
	 * Constant for NULL string, <b>"&lt;null&gt;"</b>.
	 */
	public static final String NULL_STRING = "<null>";

	/**
	 * Constant for LINE SEPARATOR, <b>{@value}</b>.
	 */
	public static final String LINE_SEPARATOR = "\n";

	/**
	 * Constant for BLANK, <b>{@value}</b>.
	 */
	public static final String BLANK = " ";

	/**
	 * Constant for COMMA, <b>{@value}</b>.
	 */
	public static final String COMMA = ",";

	/**
	 * Constant for COLON, <b>{@value}</b>.
	 */
	public static final String COLON = ":";

	/**
	 * Constant for PERCENT, <b>{@value}</b>.
	 */
	public static final String PERCENT = "%";

	/**
	 * Constant for OPEN round bracket, <b>{@value}</b>.
	 */
	public static final String OPEN_ROUND_BRACKET = "(";

	/**
	 * Constant for CLOSE round bracket, <b>{@value}</b>.
	 */
	public static final String CLOSE_ROUND_BRACKET = ")";

	/**
	 * Constant for OPEN square bracket, <b>{@value}</b>.
	 */
	public static final String OPEN_SQUARE_BRACKET = "[";

	/**
	 * Constant for CLOSE square bracket, <b>{@value}</b>.
	 */
	public static final String CLOSE_SQUARE_BRACKET = "]";

	/**
	 * Constant for OPEN brace, <b>{@value}</b>.
	 */
	public static final String OPEN_BRACE = "{";

	/**
	 * Constant for CLOSE brace, <b>{@value}</b>.
	 */
	public static final String CLOSE_BRACE = "}";

	/**
	 * Constants for DOT, <b>{@value}</b>.
	 */
	public static final String DOT = ".";

	/**
	 * Constants for UNDERSCORE, <b>{@value}</b>.
	 */
	public static final String UNDERSCORE = "_";

	/**
	 * Constants for MINUS, <b>{@value}</b>.
	 */
	public static final String MINUS = "-";

	/**
	 * Constants for EQUALS, <b>{@value}</b>.
	 */
	public static final String EQ = "=";

	/**
	 * Constants for LESS THAN, <b>{@value}</b>.
	 */
	public static final String LT = "<";

	/**
	 * Constants for GREATER THAN, <b>{@value}</b>.
	 */
	public static final String GT = ">";

	/**
	 * Constants for AMPERSAND, <b>{@value}</b>.
	 */
	public static final String AMPERSAND = "&";

	/**
	 * To avoid any instantiation
	 */
	private Constants() {
		// do nothing
	}

}