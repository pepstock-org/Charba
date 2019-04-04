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
	TOP_DOWN("topDown", GradientType.LINEAR),
	/**
	 * From bottom to to (vertical)
	 */
	BOTTOM_UP("bottomUp", GradientType.LINEAR),
	/**
	 * From left to right (horizontal)
	 */
	LEFT_RIGHT("leftRight", GradientType.LINEAR),
	/**
	 * From right to left (horizontal)
	 */
	RIGHT_LEFT("rightLeft", GradientType.LINEAR),
	/**
	 * From top(left) to right(bottom) (diagonal)
	 */
	TOP_RIGHT("topRight", GradientType.LINEAR),
	/**
	 * From bottom(right) to left(top) (diagonal)
	 */
	BOTTOM_LEFT("bottomLeft", GradientType.LINEAR),
	/**
	 * From top(right) to left(bottom) (diagonal)
	 */
	TOP_LEFT("topLeft", GradientType.LINEAR),
	/**
	 * From bottom(left) to right(top) (diagonal)
	 */
	BOTTOM_RIGHT("bottomRight", GradientType.LINEAR),
	/**
	 * From center to the borders (ONLY radial)
	 */
	IN_OUT("inOut", GradientType.RADIAL),
	/**
	 * From borders to the center (ONLY radial)
	 */
	OUT_IN("outIn", GradientType.RADIAL);

	// name value of property
	private final String value;

	// supported gradient type
	private GradientType type;

	/**
	 * Creates the orientation with supported gradient type
	 * 
	 * @param value value of property name
	 * @param type supported gradient type
	 */
	private GradientOrientation(String value, GradientType type) {
		this.value = value;
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
	 * Returns the default orientation based on gradient type.
	 * 
	 * @param type gradient type
	 * @return the default orientation based on gradient type.
	 */
	public static final GradientOrientation getDefaultByType(GradientType type) {
		// checks if is linear
		if (type.equals(GradientType.LINEAR)) {
			return TOP_DOWN;
		} else {
			// if here, is radial
			return IN_OUT;
		}
	}

}