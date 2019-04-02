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
package org.pepstock.charba.client.labels.enums;

import org.pepstock.charba.client.labels.LabelsPlugin;

/**
 * Enumeration of available positions to use to configure {@link LabelsPlugin#ID} plugin.
 * 
 * @author Andrea "Stock" Stocchero
 */
public enum Position
{
	/**
	 * In labels plugin is named 'default', inside the chart.
	 */
	defaults("default"),
	/**
	 * The labels are located on border.
	 */
	border("border"),
	/**
	 * The labels are located outisde of the chart.
	 */
	outside("outside");

	// value of enum
	// this is the real value to set to configure
	// correctly the LABELS plugin
	private final String value;

	/**
	 * Creates the enumeration item by its value.
	 * 
	 * @param value this is the real value to set to configure LABELS plugin
	 */
	private Position(String value) {
		this.value = value;
	}

	/**
	 * Returns the real value to set to configure LABELS plugin.
	 * 
	 * @return the value the real value to set to configure LABELS plugin
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Returns the position enumeration item by its value.
	 * 
	 * @param value the real value to set to configure LABELS plugin
	 * @return the position enumeration item or {@link Position#defaults} is the value is wrong.
	 */
	public static Position getPositionByValue(String value) {
		// if the value is consistent
		if (value != null) {
			// scans all position
			for (Position position : values()) {
				// if the position matches with the value
				if (position.getValue().equalsIgnoreCase(value)) {
					// returns the position
					return position;
				}
			}
		}
		// returns the default
		return Position.defaults;
	}

}
