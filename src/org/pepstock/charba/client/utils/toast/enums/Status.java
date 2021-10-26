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
package org.pepstock.charba.client.utils.toast.enums;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.utils.toast.ToastItem;

/**
 * Enumerates the status of {@link ToastItem}.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum Status implements Key
{
	/**
	 * The toast is opened and showing.
	 */
	OPENED("opened"),
	/**
	 * The toast is shown and closed.
	 */
	CLOSED("closed"),
	/**
	 * The toast is in the queue.
	 */
	QUEUED("queued"),
	/**
	 * The toast is invalid, doesn't have any label or title context to show.
	 */
	INVALID("invalid"),
	/**
	 * The toast has been discarded because the maximum amount of open items is reached.
	 */
	DISCARDED("discarded"),
	/**
	 * The toast has been removed from queue because the user decided to hide all toast items and cleared the queue.
	 */
	NOT_SHOWED("notShowed"),
	/**
	 * The status is unknown, default.
	 */
	UNKNOWN("unknown");

	// name value of property
	private final String value;

	/**
	 * Creates the progress bar type with its property name to use in the options.
	 * 
	 * @param value value to use inside the native object as name of property
	 */
	private Status(String value) {
		this.value = value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.commons.Key#value()
	 */
	@Override
	public String value() {
		return value;
	}
}
