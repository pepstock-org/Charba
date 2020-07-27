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

import org.pepstock.charba.client.defaults.IsDefaultBaseAnimation;
import org.pepstock.charba.client.enums.Easing;

/**
 * Defaults for base animation option element.
 * 
 * @author Andrea "Stock" Stocchero
 * @param <T> type of default interface of wrapped object
 */
abstract class AbstractDefaultChartAnimation<T extends IsDefaultBaseAnimation> implements IsDefaultBaseAnimation {
	
	private final T animation;

	/**
	 * Creates the object wrapping a base animation instance.
	 * 
	 * @param animation a base animation instance to wrap
	 */
	AbstractDefaultChartAnimation(T animation) {
		// checks if the argument is consistent
		if (animation == null) {
			throw new IllegalArgumentException("The animation wrapped object is null");
		}
		// stores reference
		this.animation = animation;
	}
	
	/**
	 * Returns the defaults instance to be wrapped.
	 * @return the defaults instance to be wrapped
	 */
	final T getDefaults() {
		return animation;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.animation.IsDefaultBaseAnimation#getEasing()
	 */
	@Override
	public Easing getEasing() {
		return animation.getEasing();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.animation.IsDefaultBaseAnimation#getDuration()
	 */
	@Override
	public int getDuration() {
		return animation.getDuration();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultBaseAnimation#isDebug()
	 */
	@Override
	public boolean isDebug() {
		return animation.isDebug();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultBaseAnimation#getDelay()
	 */
	@Override
	public int getDelay() {
		return animation.getDelay();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultBaseAnimation#isLoop()
	 */
	@Override
	public boolean isLoop() {
		return animation.isLoop();
	}
	
}
