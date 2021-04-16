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
package org.pepstock.charba.client.enums;

import org.pepstock.charba.client.commons.Key;

/**
 * Property to set the text alignment.
 * 
 * @author Andrea "Stock" Stocchero
 */
public enum TextAlign implements Key
{
	/**
	 * The inline contents are centered within the line box.
	 */
	CENTER("center"),
	/**
	 * The same as right if direction is left-to-right and left if direction is right-to-left.
	 */
	END("end", "right", null),
	/**
	 * The inline contents are aligned to the left edge of the line box.
	 */
	LEFT("left", null, "start"),
	/**
	 * The inline contents are aligned to the right edge of the line box.
	 */
	RIGHT("right", null, "end"),
	/**
	 * The same as left if direction is left-to-right and right if direction is right-to-left.
	 */
	START("start", null, "start");
	
	// name value of property
	private final String value;
	// value to use when only left and right is used
	private final String leftRightValue;
	// value to use when only start and end is used
	private final String startEndValue;
	
	/**
	 * Creates with the property value to use in the native object.<br>
	 * Used only for CENTER.
	 * 
	 * @param value value of property name
	 */
	private TextAlign(String value) {
		this(value, null, null);
	}

	/**
	 * Creates with the property value to use in the native object.
	 * 
	 * @param value value of property name
	 * @param leftRightValue value to use when only left and right is used
	 * @param startEndValue value to use when only start and end is used
	 */
	private TextAlign(String value, String leftRightValue, String startEndValue) {
		this.value = value;
		this.leftRightValue = leftRightValue == null ? value : leftRightValue;
		this.startEndValue = startEndValue == null ? value : startEndValue;
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

	/**
	 * Returns the value to use when only left and right is used.
	 * 
	 * @return the leftRightValue value to use when only left and right is used
	 */
	public String getLeftRightValue() {
		return leftRightValue;
	}

	/**
	 * Returns the value to use when only start and end is used.
	 * 
	 * @return the value to use when only start and end is used
	 */
	public String getStartEndValue() {
		return startEndValue;
	}

}