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
package org.pepstock.charba.client.commons;

import org.pepstock.charba.client.items.Undefined;

/**
 * This interface is defining an object which represents a point, with x and y coordinates.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface IsPoint {

	/**
	 * Returns the X coordinate of the point.
	 * 
	 * @return the X coordinate of the point.
	 */
	double getX();

	/**
	 * Returns the Y coordinate of the point.
	 * 
	 * @return the Y coordinate of the point.
	 */
	double getY();

	/**
	 * Returns <code>true</code> if the coordinates are consistent and not <code>NaN</code>.
	 * 
	 * @return <code>true</code> if the coordinates are consistent and not <code>NaN</code>
	 */
	default boolean isConsistent() {
		return Undefined.isNot(getX()) && Undefined.isNot(getY());
	}

}