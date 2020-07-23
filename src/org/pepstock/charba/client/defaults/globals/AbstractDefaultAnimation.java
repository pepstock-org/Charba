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

import org.pepstock.charba.client.defaults.IsDefaultBaseAnimation;
import org.pepstock.charba.client.enums.AnimationType;
import org.pepstock.charba.client.enums.Easing;
import org.pepstock.charba.client.items.UndefinedValues;

/**
 * CHART.JS default values for ANIMATION element.
 * 
 * @author Andrea "Stock" Stocchero
 */
abstract class AbstractDefaultAnimation implements IsDefaultBaseAnimation {

	private static final int DEFAULT_DURATION = 1000;

	private static final boolean DEFAULT_DEBUG = false;

	private static final int DEFAULT_DELAY = UndefinedValues.INTEGER;

	private static final boolean DEFAULT_LOOP = false;

	private static final double DEFAULT_FROM = UndefinedValues.DOUBLE;

	private static final boolean DEFAULT_FROM_AS_BOOLEAN = UndefinedValues.BOOLEAN;

	private static final String DEFAULT_FROM_AS_STRING = UndefinedValues.STRING;

	/**
	 * To avoid any instantiation
	 */

	AbstractDefaultAnimation() {
		// do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.animation.IsDefaultBaseAnimation#getEasing()
	 */
	@Override
	public Easing getEasing() {
		return Easing.EASE_OUT_QUART;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.animation.IsDefaultBaseAnimation#getDuration()
	 */
	@Override
	public int getDuration() {
		return DEFAULT_DURATION;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultBaseAnimation#isDebug()
	 */
	@Override
	public boolean isDebug() {
		return DEFAULT_DEBUG;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultBaseAnimation#getDelay()
	 */
	@Override
	public int getDelay() {
		return DEFAULT_DELAY;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultBaseAnimation#isLoop()
	 */
	@Override
	public boolean isLoop() {
		return DEFAULT_LOOP;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultBaseAnimation#getType()
	 */
	@Override
	public AnimationType getType() {
		return AnimationType.UNKNOWN;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultBaseAnimation#getFrom()
	 */
	@Override
	public double getFrom() {
		return DEFAULT_FROM;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultBaseAnimation#getFromAsBoolean()
	 */
	@Override
	public boolean getFromAsBoolean() {
		return DEFAULT_FROM_AS_BOOLEAN;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultBaseAnimation#getFromAsString()
	 */
	@Override
	public String getFromAsString() {
		return DEFAULT_FROM_AS_STRING;
	}


}
