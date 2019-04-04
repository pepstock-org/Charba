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

/**
 * These are the different modes for positioning of the tooltip.
 * 
 * @author Andrea "Stock" Stocchero
 */
public enum TooltipPosition implements IsTooltipPosition
{

	/**
	 * Will place the tooltip at the average position of the items displayed in the tooltip.
	 */
	AVERAGE("average"),
	/**
	 * Will place the tooltip at the position of the element closest to the event position.
	 */
	NEAREST("nearest");

	// name value of property
	private final String value;

	/**
	 * Creates with the property value to use into native object.
	 * 
	 * @param value value of property name
	 */
	private TooltipPosition(String value) {
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

	/**
	 * Returns <code>true</code> if the name passed as argument is out of the box position, otherwise <code>false</code>.
	 * 
	 * @param name the name of tooltip position
	 * @return <code>true</code> if the name passed as argument is out of the box position, otherwise <code>false</code>
	 */
	public static boolean hasTooltipPosition(String name) {
		// checks the exist
		return getTooltipPosition(name) != null;
	}

	/**
	 * Returns the tooltip position by its name passed as argument. If not exists, returns <code>null</code>.
	 * 
	 * @param name the name of tooltip position
	 * @return the tooltip position. If not exists, returns <code>null</code>.
	 */
	public static TooltipPosition getTooltipPosition(String name) {
		// checks if name is consistent
		if (name != null) {
			// scans all tooltip positions
			for (TooltipPosition position : values()) {
				// if has got the same name
				if (position.value().equalsIgnoreCase(name)) {
					return position;
				}
			}
		}
		// if here, the name has not been found
		return null;
	}
}