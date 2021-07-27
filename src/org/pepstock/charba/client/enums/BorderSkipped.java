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
import org.pepstock.charba.client.data.StackedBarDataset;

/**
 * Property to set the border position on chart datasets.<br>
 * This setting is used to avoid drawing the bar stroke at the base of the fill. In general, this does not need to be changed except when creating chart types that derive from a
 * bar chart.<br>
 * For negative bars in vertical chart, top and bottom are flipped. Same goes for left and right in horizontal chart.
 * 
 * @author Andrea "Stock" Stocchero
 */
public enum BorderSkipped implements Key
{
	/**
	 * The start property sets the start edge of an element to a unit above/below its normal position.
	 */
	START("start"),
	/**
	 * The end property sets the end edge of an element to a unit above/below its normal position.
	 */
	END("end"),
	/**
	 * The top property sets the top edge of an element to a unit above/below its normal position.
	 */
	TOP("top"),
	/**
	 * The left property sets the left edge of an element to a unit to the left/right to its normal position.
	 */
	LEFT("left"),
	/**
	 * The bottom property sets the bottom edge of an element to a unit above/below its normal position.
	 */
	BOTTOM("bottom"),
	/**
	 * The right property sets the right edge of an element to a unit to the left/right to its normal position.
	 */
	RIGHT("right"),
	/**
	 * The middle property sets the borders between bars are skipped. It's ONLY valid on stacked bars {@link StackedBarDataset}.
	 */
	MIDDLE("middle"),
	/**
	 * The property sets <code>false</code> to avoid drawing the bar stroke at the base of the fill.
	 */
	FALSE("false");

	// name value of property
	private final String value;

	/**
	 * Creates with the property value to use in the native object.
	 * 
	 * @param value value of property name
	 */
	private BorderSkipped(String value) {
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