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
package org.pepstock.charba.client.dom.safehtml;

import org.pepstock.charba.client.commons.Constants;
import org.pepstock.charba.client.dom.elements.LineBreak;

/**
 * Utility to create and manage safe html objects setting strings, chars, numbers.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class SafeHtmlBuilder {

	/**
	 * Default empty {@link SafeHtml}.
	 */
	public static final SafeHtml EMPTY_SAFE_HTML = new SafeHtmlString(Constants.EMPTY_STRING);
	// string builder instance
	private final StringBuilder builder = new StringBuilder();

	/**
	 * To avoid any instantiation
	 */
	private SafeHtmlBuilder() {
		// do nothing
	}

	/**
	 * Creates and returns a safe html builder to consume.
	 * 
	 * @return a safe html builder to consume
	 */
	public static SafeHtmlBuilder create() {
		return new SafeHtmlBuilder();
	}

	/**
	 * Appends a boolean to the builder.
	 *
	 * @param value the boolean to add
	 * @return the safe html builder instance
	 */
	public SafeHtmlBuilder append(boolean value) {
		builder.append(value);
		return this;
	}

	/**
	 * Appends a byte to the builder.
	 *
	 * @param value the byte to add
	 * @return the safe html builder instance
	 */
	public SafeHtmlBuilder append(byte value) {
		builder.append(value);
		return this;
	}

	/**
	 * Appends a char to the builder.
	 *
	 * @param value the char to add
	 * @return the safe html builder instance
	 */
	public SafeHtmlBuilder append(char value) {
		builder.append(SafeHtmlUtil.htmlEscape(value));
		return this;
	}

	/**
	 * Appends a double to the builder.
	 *
	 * @param value the double to add
	 * @return the safe html builder instance
	 */
	public SafeHtmlBuilder append(double value) {
		builder.append(value);
		return this;
	}

	/**
	 * Appends a integer to the builder.
	 *
	 * @param value the integer to add
	 * @return the safe html builder instance
	 */
	public SafeHtmlBuilder append(int value) {
		builder.append(value);
		return this;
	}

	/**
	 * Appends the contents of another safe html object.
	 *
	 * @param value the safe html instance to add
	 * @return the safe html builder instance
	 */
	public SafeHtmlBuilder append(SafeHtml value) {
		builder.append(value.asString());
		return this;
	}

	/**
	 * Appends a string to the builder.
	 *
	 * @param value the string to add
	 * @return the safe html builder instance
	 */
	public SafeHtmlBuilder appendEscaped(String value) {
		builder.append(SafeHtmlUtil.htmlEscape(value));
		return this;
	}

	/**
	 * Appends a string to the builder, splitting the string if contains line separators.
	 *
	 * @param value the string to append
	 * @return the safe html builder instance
	 */
	public SafeHtmlBuilder appendEscapedLines(String value) {
		builder.append(SafeHtmlUtil.htmlEscape(value).replace(Constants.LINE_SEPARATOR, Constants.LT + LineBreak.TAG + Constants.GT));
		return this;
	}

	/**
	 * Appends a string without any escaping.
	 *
	 * @param value the HTML to be appended
	 * @return the safe html builder instance
	 */
	public SafeHtmlBuilder appendHtmlConstant(String value) {
		builder.append(value);
		return this;
	}

	/**
	 * Returns the safe html instance.
	 *
	 * @return the safe html instance
	 */
	public SafeHtml toSafeHtml() {
		return new SafeHtmlString(builder.toString());
	}

}
