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
 * Defines the position of the tooltip caret.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum TooltipAlign implements Key
{
	/**
	 * The caret is centered.
	 */
	CENTER("center", true, true),

	/**
	 * The caret is positioned to the right.
	 */
	RIGHT("right", true, false),
	/**
	 * The caret is positioned to the bottom.
	 */
	BOTTOM("bottom", false, true),
	/**
	 * The caret is positioned to the left.
	 */
	LEFT("left", true, false),
	/**
	 * The caret is positioned to the top.
	 */
	TOP("top", false, true),
	/**
	 * Default value and never stored in chart configuration.
	 */
	AUTO("auto", false, false);

	// name value of property
	private final String value;
	// value to use when only left and right is used
	private final boolean xValue;
	// value to use when only start and end is used
	private final boolean yValue;

	/**
	 * Creates with the property value to use in the native object.
	 * 
	 * @param value value of property name
	 * @param xValue <code>true</code> if it can be used for horizontal alignment
	 * @param yValue <code>true</code> if it can be used for vertical alignment
	 */
	private TooltipAlign(String value, boolean xValue, boolean yValue) {
		this.value = value;
		this.xValue = xValue;
		this.yValue = yValue;
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
	 * Returns <code>true</code> if it can be used for horizontal alignment.
	 * 
	 * @return <code>true</code> if it can be used for horizontal alignment
	 */
	public boolean isHorizontal() {
		return xValue;
	}

	/**
	 * Returns <code>true</code> if it can be used for vertical alignment.
	 * 
	 * @return <code>true</code> if it can be used for vertical alignment
	 */
	public boolean isVertical() {
		return yValue;
	}

}
