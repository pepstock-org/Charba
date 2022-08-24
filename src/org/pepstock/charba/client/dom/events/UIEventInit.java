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
package org.pepstock.charba.client.dom.events;

import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.Key;

/**
 * Initialization object for a {@link NativeUIEvent}.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
class UIEventInit extends EventInit {

	// default detail value
	private static final int DEFAULT_DETAIL = 0;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		DETAIL("detail");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use in the native object.
		 * 
		 * @param value value of property name
		 */
		private Property(String value) {
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

	/**
	 * Creates an empty object
	 */
	UIEventInit() {
		super();
	}

	/**
	 * When non-zero, provides the current (or next, depending on the event) click count.<br>
	 * For click events, is the current click count.<br>
	 * For mousedown or mouseup events, is 1 plus the current click count.<br>
	 * For all other, is always zero.
	 * 
	 * @return When non-zero, provides the current (or next, depending on the event) click count.<br>
	 *         For click events, is the current click count.<br>
	 *         For mousedown or mouseup events, is 1 plus the current click count.<br>
	 *         For all other, is always zero
	 */
	public final int getDetail() {
		return getValue(Property.DETAIL, DEFAULT_DETAIL);
	}

	/**
	 * Sets non-zero, provides the current (or next, depending on the event) click count.<br>
	 * For click events, is the current click count.<br>
	 * For mousedown or mouseup events, is 1 plus the current click count.<br>
	 * For all other, is always zero
	 * 
	 * @param detail non-zero, provides the current (or next, depending on the event) click count.<br>
	 *            For click events, is the current click count.<br>
	 *            For mousedown or mouseup events, is 1 plus the current click count.<br>
	 *            For all other, is always zero
	 */
	public final void setDetail(int detail) {
		setValue(Property.DETAIL, Checker.greaterThanOrZero(detail, 0));
	}

}
