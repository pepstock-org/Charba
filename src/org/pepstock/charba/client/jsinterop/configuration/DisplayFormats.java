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
package org.pepstock.charba.client.jsinterop.configuration;

import org.pepstock.charba.client.enums.TimeUnit;

/**
 * The following display formats are used to configure how different time units are formed into strings for the axis tick marks.
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 *
 */
public class DisplayFormats extends AxisContainer {

	/**
	 * Builds the object by axis instance.
	 * 
	 * @param axis axis instance
	 */
	DisplayFormats(Axis axis) {
		super(axis);
	}

	/**
	 * Sets the display formats are used to configure how different time units are formed into strings for the axis tick marks.
	 * 
	 * @param unit time unit.
	 * @param format display format
	 */
	public void setDisplayFormat(TimeUnit unit, String format) {
		getAxis().getScale().getTime().getDisplayFormats().setDisplayFormat(unit, format);
	}

	/**
	 * Returns the display formats are used to configure how different time units are formed into strings for the axis tick
	 * marks.
	 * 
	 * @param unit time unit.
	 * @return display format
	 */
	public String getDisplayFormat(TimeUnit unit) {
		return getAxis().getScale().getTime().getDisplayFormats().getDisplayFormat(unit);
	}

}