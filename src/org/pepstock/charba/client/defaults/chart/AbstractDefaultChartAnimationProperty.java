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
package org.pepstock.charba.client.defaults.chart;

import org.pepstock.charba.client.defaults.IsDefaultAnimationProperty;
import org.pepstock.charba.client.enums.AnimationType;

/**
 * CHART.JS default values for animation property.
 * 
 * @author Andrea "Stock" Stocchero
 * @param <T> type of default interface of wrapped object
 * 
 */
abstract class AbstractDefaultChartAnimationProperty<T extends IsDefaultAnimationProperty> extends AbstractDefaultChartAnimation<T> implements IsDefaultAnimationProperty {

	/**
	 * Creates a default animation property.
	 * 
	 * @param property a default animation property to wrap
	 */
	AbstractDefaultChartAnimationProperty(T property) {
		super(property);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimationProperty#getType()
	 */
	@Override
	public AnimationType getType() {
		return getDefaults().getType();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimationProperty#getFrom()
	 */
	@Override
	public double getFrom() {
		return getDefaults().getFrom();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimationProperty#getFromAsBoolean()
	 */
	@Override
	public boolean getFromAsBoolean() {
		return getDefaults().getFromAsBoolean();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimationProperty#getFromAsString()
	 */
	@Override
	public String getFromAsString() {
		return getDefaults().getFromAsString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimationProperty#getTo()
	 */
	@Override
	public double getTo() {
		return getDefaults().getTo();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimationProperty#getToAsBoolean()
	 */
	@Override
	public boolean getToAsBoolean() {
		return getDefaults().getToAsBoolean();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimationProperty#getToAsString()
	 */
	@Override
	public String getToAsString() {
		return getDefaults().getToAsString();
	}

}
