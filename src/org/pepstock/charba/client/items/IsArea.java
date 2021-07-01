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
package org.pepstock.charba.client.items;

/**
 * Defines an area dimensions.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface IsArea {

	/**
	 * Returns <code>true</code> if the are is consistent, otherwise <code>false</code>.
	 * 
	 * @param area area instance to be checked
	 * @return <code>true</code> if the are is consistent, otherwise <code>false</code>
	 */
	static boolean isConsistent(IsArea area) {
		// checks if argument is consistent
		if (area != null) {
			// checks horizontal dimensions
			final boolean x = Undefined.isNot(area.getLeft()) && Undefined.isNot(area.getRight()) && area.getWidth() >= 0;
			// checks vertical dimensions
			final boolean y = Undefined.isNot(area.getTop()) && Undefined.isNot(area.getBottom()) && area.getHeight() >= 0;
			return x && y;
		}
		// if here, area is not consistent
		// then returns false
		return false;
	}

	/**
	 * Returns the top of area.
	 * 
	 * @return the top of area.
	 */
	double getTop();

	/**
	 * Returns the right of area.
	 * 
	 * @return the right of area.
	 */
	double getRight();

	/**
	 * Returns the bottom of area.
	 * 
	 * @return the bottom of area.
	 */
	double getBottom();

	/**
	 * Returns the left of area.
	 * 
	 * @return the left of area.
	 */
	double getLeft();

	/**
	 * Returns the width in pixel.
	 * 
	 * @return the width in pixel.
	 */
	default double getWidth() {
		return getRight() - getLeft();
	}

	/**
	 * Returns the height in pixel.
	 * 
	 * @return the height in pixel.
	 */
	default double getHeight() {
		return getBottom() - getTop();
	}

}