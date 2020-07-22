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
package org.pepstock.charba.client.defaults.globals;

import org.pepstock.charba.client.defaults.IsDefaultAnimationElement;
import org.pepstock.charba.client.enums.AnimationType;
import org.pepstock.charba.client.items.UndefinedValues;

/**
 * CHART.JS default values for ANIMATION element.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class DefaultAnimationElement extends AbstractDefaultAnimation implements IsDefaultAnimationElement {

	private static final double DEFAULT_FROM = UndefinedValues.DOUBLE;

	private static final boolean DEFAULT_FROM_AS_BOOLEAN = UndefinedValues.BOOLEAN;

	private static final String DEFAULT_FROM_AS_STRING = UndefinedValues.STRING;

	/**
	 * To avoid any instantiation
	 */

	DefaultAnimationElement() {
		// do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimation#getType()
	 */
	@Override
	public final AnimationType getType() {
		return AnimationType.UNKNOWN;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimation#getFrom()
	 */
	@Override
	public final double getFrom() {
		return DEFAULT_FROM;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimation#getFromAsString()
	 */
	@Override
	public final String getFromAsString() {
		return DEFAULT_FROM_AS_STRING;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimationElement#getFromAsBoolean()
	 */
	@Override
	public final boolean getFromAsBoolean() {
		return DEFAULT_FROM_AS_BOOLEAN;
	}

}
