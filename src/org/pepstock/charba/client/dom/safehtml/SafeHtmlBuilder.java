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
import org.pepstock.charba.client.dom.elements.BreakElement;

/**
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class SafeHtmlBuilder {

	/**
	 * An empty safe HTML.
	 */
	public static final SafeHtml EMPTY_SAFE_HTML = new SafeHtmlString(Constants.EMPTY_STRING);

	private final StringBuilder builder = new StringBuilder();

	/**
	 * To avoid any instantiation
	 */
	private SafeHtmlBuilder() {
		// do nothing
	}

	public static SafeHtmlBuilder create() {
		return new SafeHtmlBuilder();
	}

	/**
	 * Appends the string representation of a boolean.
	 *
	 * @param bool the boolean whose string representation to append
	 * @return a reference to safe html builder
	 */
	public SafeHtmlBuilder append(boolean bool) {
		builder.append(bool);
		return this;
	}

	/**
	 * Appends the string representation of a number.
	 *
	 * @param b the number whose string representation to append
	 * @return a reference to safe html builder
	 */
	public SafeHtmlBuilder append(byte b) {
		builder.append(b);
		return this;
	}

	/**
	 * Appends the string representation of a char.
	 *
	 * @param c the character whose string representation to append
	 * @return a reference to safe html builder
	 */
	public SafeHtmlBuilder append(char c) {
		builder.append(SafeHtmlUtils.htmlEscape(c));
		return this;
	}

	/**
	 * Appends the string representation of a number.
	 *
	 * @param dbl the number whose string representation to append
	 * @return a reference to safe html builder
	 */
	public SafeHtmlBuilder append(double dbl) {
		builder.append(dbl);
		return this;
	}

	/**
	 * Appends the string representation of a number.
	 *
	 * @param flt the number whose string representation to append
	 * @return a reference to safe html builder
	 */
	public SafeHtmlBuilder append(float flt) {
		builder.append(flt);
		return this;
	}

	/**
	 * Appends the string representation of a number.
	 *
	 * @param num the number whose string representation to append
	 * @return a reference to safe html builder
	 */
	public SafeHtmlBuilder append(int num) {
		builder.append(num);
		return this;
	}

	/**
	 * Appends the string representation of a number.
	 *
	 * @param num the number whose string representation to append
	 * @return a reference to safe html builder
	 */
	public SafeHtmlBuilder append(long num) {
		builder.append(num);
		return this;
	}

	/**
	 * Appends the contents of another safe html object, without applying HTML-escaping to it.
	 *
	 * @param html the safe html to append
	 * @return a reference to safe html builder
	 */
	public SafeHtmlBuilder append(SafeHtml html) {
		builder.append(html.asString());
		return this;
	}

	/**
	 * Appends a string after HTML-escaping it.
	 *
	 * @param text the string to append
	 * @return a reference to safe html builder
	 */
	public SafeHtmlBuilder appendEscaped(String text) {
		builder.append(SafeHtmlUtils.htmlEscape(text));
		return this;
	}

	/**
	 * Appends a string consisting of several newline-separated lines after HTML-escaping it.
	 *
	 * @param text the string to append
	 * @return a reference to safe html builder
	 */
	public SafeHtmlBuilder appendEscapedLines(String text) {
		builder.append(SafeHtmlUtils.htmlEscape(text).replaceAll(Constants.LINE_SEPARATOR, "<"+BreakElement.TAG+">"));
		return this;
	}

	/**
	 * Appends a compile-time-constant string, which will not be escaped.
	 *
	 * @param html the HTML to be appended
	 * @return a reference to safe html builder
	 */
	public SafeHtmlBuilder appendHtmlConstant(String html) {
		builder.append(html);
		return this;
	}

	/**
	 * Returns the safe HTML accumulated in the builder.
	 *
	 * @return a safe html instance
	 */
	public SafeHtml toSafeHtml() {
		return new SafeHtmlString(builder.toString());
	}

}
