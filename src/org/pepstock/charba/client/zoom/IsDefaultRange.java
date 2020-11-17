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
package org.pepstock.charba.client.zoom;

import java.util.Date;

import org.pepstock.charba.client.items.UndefinedValues;

/**
 * {@link ZoomPlugin#ID} plugin default options interface for RANGE (min and max) elements.<br>
 * It contains all default values for RANGE.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
interface IsDefaultRange {

	/**
	 * Returns the X value of range as string.
	 * 
	 * @return the X value of range as string
	 */
	default String getX() {
		return UndefinedValues.STRING;
	}

	/**
	 * Returns the X value of range as double.
	 * 
	 * @return the X value of range as double
	 */
	default double getXAsDouble() {
		return UndefinedValues.DOUBLE;
	}

	/**
	 * Returns the X value of range as date.
	 * 
	 * @return the X value of range as date
	 */
	default Date getXAsDate() {
		return null;
	}

	/**
	 * Returns the Y value of range as double.
	 * 
	 * @return the Y value of range as double
	 */
	default double getY() {
		return UndefinedValues.DOUBLE;
	}

	/**
	 * Returns the Y value of range as string.
	 * 
	 * @return the Y value of range as string
	 */
	default String getYAsString() {
		return UndefinedValues.STRING;
	}

	/**
	 * Returns the Y value of range as date.
	 * 
	 * @return the Y value of range as date
	 */
	default Date getYAsDate() {
		return null;
	}
}