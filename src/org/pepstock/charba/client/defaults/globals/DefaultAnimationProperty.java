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

import org.pepstock.charba.client.defaults.IsDefaultAnimationProperty;
import org.pepstock.charba.client.enums.AnimationType;
import org.pepstock.charba.client.items.UndefinedValues;

/**
 * CHART.JS default values for animation property.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class DefaultAnimationProperty extends AbstractDefaultAnimation implements IsDefaultAnimationProperty {

	private static final double DEFAULT_FROM = UndefinedValues.DOUBLE;

	private static final boolean DEFAULT_FROM_AS_BOOLEAN = UndefinedValues.BOOLEAN;

	private static final String DEFAULT_FROM_AS_STRING = UndefinedValues.STRING;

	private static final double DEFAULT_TO = UndefinedValues.DOUBLE;

	private static final boolean DEFAULT_TO_AS_BOOLEAN = UndefinedValues.BOOLEAN;

	private static final String DEFAULT_TO_AS_STRING = UndefinedValues.STRING;

	/**
	 * To avoid any instantiation
	 */
	DefaultAnimationProperty() {
		// do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimationProperty#getType()
	 */
	@Override
	public AnimationType getType() {
		return AnimationType.NUMBER;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimationProperty#getFrom()
	 */
	@Override
	public double getFrom() {
		return DEFAULT_FROM;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimationProperty#getFromAsBoolean()
	 */
	@Override
	public boolean getFromAsBoolean() {
		return DEFAULT_FROM_AS_BOOLEAN;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimationProperty#getFromAsString()
	 */
	@Override
	public String getFromAsString() {
		return DEFAULT_FROM_AS_STRING;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimationProperty#getTo()
	 */
	@Override
	public double getTo() {
		return DEFAULT_TO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimationProperty#getToAsBoolean()
	 */
	@Override
	public boolean getToAsBoolean() {
		return DEFAULT_TO_AS_BOOLEAN;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimationProperty#getToAsString()
	 */
	@Override
	public String getToAsString() {
		return DEFAULT_TO_AS_STRING;
	}

}
