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
 * Represents the gradient type.<br>
 * Used for enum.
 * 
 * @author Andrea "Stock" Stocchero
 */
public enum GradientOrientation implements Key{

	topDown(GradientType.linear),
	bottomUp(GradientType.linear),
	leftRight(GradientType.linear),
	rightLeft(GradientType.linear),
	topRight(GradientType.linear),
	bottomLeft(GradientType.linear),
	topLeft(GradientType.linear),
	bottomRight(GradientType.linear),
	inOut(GradientType.radial),
	outIn(GradientType.radial);
	
	private GradientType type;

	private GradientOrientation(GradientType type) {
		this.type = type;
	}

	/**
	 * @return the type
	 */
	public GradientType getType() {
		return type;
	}
	
	public static final GradientOrientation getDefaultByType(GradientType type) {
		if (type.equals(GradientType.linear)) {
			return topDown;
		} else {
			return inOut;
		}
	}

}