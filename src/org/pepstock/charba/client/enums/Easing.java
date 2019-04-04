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

/**
 * Easing is acceleration, a change in speed.<br>
 * For further details, see <a href="http://www.chartjs.org/docs/latest/configuration/animations.html#easing">here</a>.
 * 
 * @author Andrea "Stock" Stocchero
 */
public enum Easing implements Key
{
	LINEAR("linear"),
	EASE_IN_QUAD("easeInQuad"),
	EASE_OUT_QUAD("easeOutQuad"),
	EASE_IN_OUT_QUAD("easeInOutQuad"),
	EASE_IN_CUBIC("easeInCubic"),
	EASE_OUT_CUBIC("easeOutCubic"),
	EASE_IN_OUT_CUBIC("easeInOutCubic"),
	EASE_IN_QUART("easeInQuart"),
	EASE_OUT_QUART("easeOutQuart"),
	EASE_IN_OUT_QUART("easeInOutQuart"),
	EASE_IN_QUINT("easeInQuint"),
	EASE_OUT_QUINT("easeOutQuint"),
	EASE_IN_OUT_QUINT("easeInOutQuint"),
	EASE_IN_SINE("easeInSine"),
	EASE_OUT_SINE("easeOutSine"),
	EASE_IN_OUT_SINE("easeInOutSine"),
	EASE_IN_EXPO("easeInExpo"),
	EASE_OUT_EXPO("easeOutExpo"),
	EASE_IN_OUT_EXPO("easeInOutExpo"),
	EASE_IN_CIRC("easeInCirc"),
	EASE_OUT_CIRC("easeOutCirc"),
	EASE_IN_OUT_CIRC("easeInOutCirc"),
	EASE_IN_ELASTIC("easeInElastic"),
	EASE_OUT_ELASTIC("easeOutElastic"),
	EASE_IN_OUT_ELASTIC("easeInOutElastic"),
	EASE_IN_BACK("easeInBack"),
	EASE_OUT_BACK("easeOutBack"),
	EASE_IN_OUT_BACK("easeInOutBack"),
	EASE_IN_BOUNCE("easeInBounce"),
	EASE_OUT_BOUNCE("easeOutBounce"),
	EASE_IN_OUT_BOUNCE("easeInOutBounce");

	// name value of property
	private final String value;

	/**
	 * Creates with the property value to use into native object.
	 * 
	 * @param value value of property name
	 */
	private Easing(String value) {
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