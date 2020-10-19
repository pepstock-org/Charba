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
package org.pepstock.charba.client.utils;

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Regular expressions are patterns used to match character combinations in strings.<br>
 * In JavaScript, regular expressions are also objects.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL)
public final class RegExp {

	/**
	 * Creates a regular expression.<br>
	 * See <a href="https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/RegExp">MDN RegExp</a>.
	 * 
	 * @param pattern the text of the regular expression.<br>
	 *            Patterns can include special characters so they can match a wider range of values than would a literal string.
	 */
	public RegExp(String pattern) {
		// do nothing
	}

	/**
	 * Creates a regular expression, using flags passed as string.<br>
	 * If specified, flags is a string that contains the flags to add.<br>
	 * Alternatively, if an object is supplied for the pattern, the flags string will replace any of that object's flags (and lastIndex will be reset to 0).<br>
	 * Flags may contain any combination of the following characters:<br>
	 * <ul>
	 * <li><b>g</b> (global match), finds all matches rather than stopping after the first match.
	 * <li><b>i</b> (ignore case), if <code>u</code> flag is also enabled, uses Unicode case folding.
	 * <li><b>m</b> (multiline) treats beginning and end characters (^ and $) as working over multiple lines.<br>
	 * In other words, match the beginning or end of each line (delimited by \n or \r), not only the very beginning or end of the whole input string.
	 * <li><b>s</b> ("dotAll") allows <code>.</code> to match newlines.
	 * <li><b>u</b> (unicode) treats pattern as a sequence of Unicode code points.
	 * <li><b>y</b> (sticky) matches only from the index indicated by the lastIndex property of this regular expression in the target string.<br>
	 * Does not attempt to match from any later indexes.
	 * </ul>
	 * <br>
	 * See <a href="https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/RegExp">MDN RegExp</a>.
	 * 
	 * <br>
	 * <b style="font-size: 16px">PAY ATTENTION</b><br>
	 * The following method is not supported on Internet Explorer.<br>
	 * <ul>
	 * <li>{@link RegExp#isSticky()}
	 * </ul>
	 * 
	 * @param pattern the text of the regular expression.<br>
	 *            Patterns can include special characters so they can match a wider range of values than would a literal string.
	 * @param flags If specified, flags is a string that contains the flags to add.<br>
	 *            Alternatively, if an object is supplied for the pattern, the flags string will replace any of that object's flags (and lastIndex will be reset to 0).<br>
	 *            Flags may contain any combination of the following characters:<br>
	 *            <ul>
	 *            <li><b>g</b> (global match), finds all matches rather than stopping after the first match.
	 *            <li><b>i</b> (ignore case), if <code>u</code> flag is also enabled, uses Unicode case folding.
	 *            <li><b>m</b> (multiline) treats beginning and end characters (^ and $) as working over multiple lines.<br>
	 *            In other words, match the beginning or end of each line (delimited by \n or \r), not only the very beginning or end of the whole input string.
	 *            <li><b>s</b> ("dotAll") allows <code>.</code> to match newlines.
	 *            <li><b>u</b> (unicode) treats pattern as a sequence of Unicode code points.
	 *            <li><b>y</b> (sticky) matches only from the index indicated by the lastIndex property of this regular expression in the target string.<br>
	 *            Does not attempt to match from any later indexes.
	 *            </ul>
	 */
	public RegExp(String pattern, String flags) {
		// do nothing
	}

	/**
	 * Returns the index at which to start the next match.<br>
	 * This property is set only if the regular expression instance used the g flag to indicate a global search, or the y flag to indicate a sticky search.<br>
	 * The following rules apply:<br>
	 * <ul>
	 * <li>If lastIndex is greater than the length of the string, {@link RegExp#test(String)} and {@link RegExp#exec(String)} fail, then lastIndex is set to 0
	 * <li>If lastIndex is equal to or less than the length of the string and if the regular expression matches the empty string, then the regular expression matches input starting
	 * from lastIndex.
	 * <li>If lastIndex is equal to the length of the string and if the regular expression does not match the empty string, then the regular expression mismatches input, and
	 * lastIndex is reset to 0.
	 * <li>Otherwise, lastIndex is set to the next position following the most recent match.
	 * </ul>
	 * 
	 * @return the index at which to start the next match
	 */
	@JsProperty
	public static native int getLastIndex();

	/**
	 * Returns <code>true</code> whether or not the "s" flag is used with the regular expression.<br>
	 * It is a read-only property of an individual regular expression instance.
	 * 
	 * @return <code>true</code> whether or not the "s" flag is used with the regular expression
	 */
	@JsProperty
	public native boolean isDotAll();

	/**
	 * Returns <code>true</code> whether or not the "g" flag is used with the regular expression.<br>
	 * It is a read-only property of an individual regular expression instance.
	 * 
	 * @return <code>true</code> whether or not the "g" flag is used with the regular expression
	 */
	@JsProperty
	public native boolean isGlobal();

	/**
	 * Returns <code>true</code> if the "i" flag was used; otherwise, <code>false</code>.<br>
	 * The "i" flag indicates that case should be ignored while attempting a match in a string.<br>
	 * It is a read-only property of an individual regular expression instance.
	 * 
	 * @return <code>true</code> if the "i" flag was used; otherwise, <code>false</code>
	 */
	@JsProperty
	public native boolean isIgnoreCase();

	/**
	 * Returns <code>true</code> whether or not the "m" flag is used with the regular expression.<br>
	 * It is a read-only property of an individual regular expression instance.
	 * 
	 * @return <code>true</code> whether or not the "m" flag is used with the regular expression
	 */
	@JsProperty
	public native boolean isMultiline();

	/**
	 * Returns <code>true</code> whether or not the "u" flag is used with a regular expression.<br>
	 * It is a read-only property of an individual regular expression instance.
	 * 
	 * @return <code>true</code> whether or not the "u" flag is used with a regular expression
	 */
	@JsProperty
	public native boolean isUnicode();

	/**
	 * Returns <code>true</code> whether or not the search is sticky (searches in strings only from the index indicated by the last index property of this regular expression).<br>
	 * It is a read-only property of an individual regular expression instance.
	 * <br>
	 * <b style="font-size: 16px">PAY ATTENTION</b><br>
	 * This method is not supported on Internet Explorer.
	 * <br>
	 * 
	 * @return <code>true</code> whether or not the search is sticky (searches in strings only from the index indicated by the last index property of this regular expression)
	 */
	@JsProperty
	public native boolean isSticky();

	/**
	 * Returns a string containing the source text of the {@link RegExp} object, and it doesn't contain the two forward slashes on both sides and any flags.
	 * 
	 * @return a string containing the source text of the {@link RegExp} object, and it doesn't contain the two forward slashes on both sides and any flags
	 */
	@JsProperty
	public native String getSource();

	/**
	 * Executes a search for a match in a string.<br>
	 * It returns an array of information or null on a mismatch.
	 * 
	 * @param s string to use for matching
	 * @return an array of information or null on a mismatch.
	 */
	@JsMethod
	public native RegExpResult exec(String s);

	/**
	 * Executes a search for a match between a regular expression and a specified string.
	 * 
	 * @param value the string against which to match the regular expression.
	 * @return <code>true</code> if there is a match between the regular expression and the string. Otherwise, <code>false</code>.
	 */
	@JsMethod
	public native boolean test(String value);

}