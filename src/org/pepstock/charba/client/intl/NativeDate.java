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
package org.pepstock.charba.client.intl;

import java.util.Date;

import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.NativeName;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * JavaScript Date objects represent a single moment in time in a platform-independent format.<br>
 * Date objects contain a Number that represents milliseconds since 1 January 1970 UTC.<br>
 * See <a href="https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Date">MDN</a> for more details.
 * 
 * @author Andrea "Stock" Stocchero
 */
@JsType(isNative = true, name = NativeName.DATE, namespace = JsPackage.GLOBAL)
final class NativeDate {

	/**
	 * Creates a JavaScript Date instance that represents a single moment in time in a platform-independent format.Date objects contain a Number that represents milliseconds since
	 * 1 January 1970 UTC.
	 * 
	 * @param value a Number that represents milliseconds since 1 January 1970 UTC
	 */
	private NativeDate(double value) {
		// must be empty
	}

	/**
	 * Creates a JavaScript Date object from a {@link Date} java instance.
	 * 
	 * @param date java date instance to transform in the a JavaScript one.
	 * @return a JavaScript Date object
	 */
	@JsOverlay
	static NativeDate create(Date date) {
		// checks if date is consistent
		Checker.checkIfValid(date, "Date argument");
		// creates new date
		return new NativeDate(date.getTime());
	}
}
