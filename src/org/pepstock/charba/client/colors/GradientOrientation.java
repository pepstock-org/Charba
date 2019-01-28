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
package org.pepstock.charba.client.colors;

import org.pepstock.charba.client.commons.Key;

/**
 * Represents the gradient orientation for gradient.<br>
 * Every orientation contains also on which gradient type can be applied.
 * 
 * @author Andrea "Stock" Stocchero
 */
public enum GradientOrientation implements Key
{
	/**
	 * From top to bottom (vertical)
	 */
	topDown(GradientType.linear),
	/**
	 * From bottom to to (vertical)
	 */
	bottomUp(GradientType.linear),
	/**
	 * From left to right (horizontal)
	 */
	leftRight(GradientType.linear),
	/**
	 * From right to left (horizontal)
	 */
	rightLeft(GradientType.linear),
	/**
	 * From top(left) to right(bottom) (diagonal)
	 */
	topRight(GradientType.linear),
	/**
	 * From bottom(right) to left(top) (diagonal)
	 */
	bottomLeft(GradientType.linear),
	/**
	 * From top(right) to left(bottom) (diagonal)
	 */
	topLeft(GradientType.linear),
	/**
	 * From bottom(left) to right(top) (diagonal)
	 */
	bottomRight(GradientType.linear),
	/**
	 * From center to the borders (ONLY radial)
	 */
	inOut(GradientType.radial),
	/**
	 * From borders to the center (ONLY radial)
	 */
	outIn(GradientType.radial);

	// supported gradient type
	private GradientType type;

	/**
	 * Creates the orientation with supported gradient type
	 * 
	 * @param type supported gradient type
	 */
	private GradientOrientation(GradientType type) {
		this.type = type;
	}

	/**
	 * Returns the supported gradient type.
	 * 
	 * @return the supported gradient type
	 */
	public GradientType getType() {
		return type;
	}

	/**
	 * Returns the default orientation based on gradient type.
	 * 
	 * @param type gradient type
	 * @return the default orientation based on gradient type.
	 */
	public static final GradientOrientation getDefaultByType(GradientType type) {
		// checks if is linear
		if (type.equals(GradientType.linear)) {
			return topDown;
		} else {
			// if here, is radial
			return inOut;
		}
	}

}