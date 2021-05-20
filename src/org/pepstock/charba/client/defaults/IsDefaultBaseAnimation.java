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
package org.pepstock.charba.client.defaults;

import org.pepstock.charba.client.callbacks.DelayCallback;
import org.pepstock.charba.client.callbacks.DurationCallback;
import org.pepstock.charba.client.callbacks.EasingCallback;
import org.pepstock.charba.client.callbacks.LoopCallback;
import org.pepstock.charba.client.enums.Easing;

/**
 * Interface to define the base animation properties defaults.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface IsDefaultBaseAnimation {

	/**
	 * Returns the animation easing.
	 * 
	 * @return animation easing.
	 */
	Easing getEasing();

	/**
	 * Returns the number of milliseconds an animation takes.
	 * 
	 * @return the number of milliseconds an animation takes.
	 */
	int getDuration();

	/**
	 * Returns the delay before starting the animations.
	 * 
	 * @return the delay before starting the animations
	 */
	int getDelay();

	/**
	 * If set to <code>true</code>, loops the animations endlessly.
	 * 
	 * @return <code>true</code> if loops the animations endlessly.
	 */
	boolean isLoop();
	
	/**
	 * Returns the callback to set the number of milliseconds an animation takes.
	 * 
	 * @return the callback instance to use
	 */
	DurationCallback getDurationCallback();

	/**
	 * Returns the callback to set the delay before starting the animations.
	 * 
	 * @return the callback instance to use
	 */
	DelayCallback getDelayCallback();

	/**
	 * Returns the callback to loop the animations endlessly.
	 * 
	 * @return the callback instance to use
	 */
	LoopCallback getLoopCallback();

	/**
	 * Returns the callback to set the animation easing.
	 * 
	 * @return the callback instance to use
	 */
	EasingCallback getEasingCallback();

}
