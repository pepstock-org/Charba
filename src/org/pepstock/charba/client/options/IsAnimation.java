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
package org.pepstock.charba.client.options;

import org.pepstock.charba.client.callbacks.DelayCallback;
import org.pepstock.charba.client.callbacks.DurationCallback;
import org.pepstock.charba.client.callbacks.EasingCallback;
import org.pepstock.charba.client.callbacks.LoopCallback;
import org.pepstock.charba.client.defaults.IsDefaultAnimation;
import org.pepstock.charba.client.enums.Easing;

/**
 * Interface to map a animations element, ANIMATIONS name space.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface IsAnimation extends IsDefaultAnimation {

	/**
	 * If <code>true</code>, the chart will animate in with a rotation animation.
	 * 
	 * @param animateRotate If <code>true</code>, the chart will animate in with a rotation animation.
	 */
	void setAnimateRotate(boolean animateRotate);

	/**
	 * If <code>true</code>, will animate scaling the chart from the center outwards.
	 * 
	 * @param animateScale If <code>true</code>, will animate scaling the chart from the center outwards.
	 */
	void setAnimateScale(boolean animateScale);

	/**
	 * Sets the animation easing.
	 * 
	 * @param easing animation easing.
	 */
	void setEasing(Easing easing);

	/**
	 * Sets the number of milliseconds an animation takes.
	 * 
	 * @param milliseconds the number of milliseconds an animation takes.
	 */
	void setDuration(int milliseconds);

	/**
	 * Sets the delay before starting the animations.
	 * 
	 * @param delay the delay before starting the animations
	 */
	void setDelay(int delay);

	/**
	 * If set to <code>true</code>, loops the animations endlessly.
	 * 
	 * @param loop <code>true</code> if loops the animations endlessly.
	 */
	void setLoop(boolean loop);

	/**
	 * Sets the number of milliseconds an animation takes by a callback.
	 * 
	 * @param durationCallback the callback instance to use
	 */
	void setDuration(DurationCallback durationCallback);

	/**
	 * Sets the delay before starting the animations by a callback.
	 * 
	 * @param delayCallback the callback instance to use
	 */
	void setDelay(DelayCallback delayCallback);

	/**
	 * Sets to loop the animations endlessly by a callback.
	 * 
	 * @param loopCallback the callback instance to use
	 */
	void setLoop(LoopCallback loopCallback);

	/**
	 * Sets the animation easing by a callback.
	 * 
	 * @param easingCallback the callback instance to use
	 */
	void setEasing(EasingCallback easingCallback);

}
