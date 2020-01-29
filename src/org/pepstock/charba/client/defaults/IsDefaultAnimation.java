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

import org.pepstock.charba.client.enums.Easing;
import org.pepstock.charba.client.enums.InterpolatorType;

/**
 * Interface to define animation object defaults.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface IsDefaultAnimation {

	/**
	 * Returns the animation element to get the duration in milliseconds it takes to animate hover style changes.
	 * 
	 * @return the animation element to get the duration in milliseconds it takes to animate hover style changes.
	 */
	IsDefaultAnimationElement getActive();

	/**
	 * Returns the animation element to get the duration in milliseconds it takes to animate to new size after a resize event.
	 * 
	 * @return the animation element to get the duration in milliseconds it takes to animate to new size after a resize event.
	 */
	IsDefaultAnimationElement getResize();

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
	 * If true, the chart will animate in with a rotation animation.
	 * 
	 * @return If true, the chart will animate in with a rotation animation.
	 */
	boolean isAnimateRotate();

	/**
	 * If true, will animate scaling the chart from the center outwards.
	 * 
	 * @return If true, will animate scaling the chart from the center outwards.
	 */
	boolean isAnimateScale();

	/**
	 * Returns <code>true</code> if running animation count plus FPS display in upper left corner of the chart.
	 * 
	 * @return <code>true</code> if running animation count plus FPS display in upper left corner of the chart
	 */
	boolean isDebug();

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
	 * Returns the type of <code>from</code> property and determines the interpolator used.
	 * 
	 * @return the type of <code>from</code> property and determines the interpolator used.
	 */
	InterpolatorType getType();

	/**
	 * Returns the start value for the animation as number.
	 * 
	 * @return the start value for the animation as number.
	 */
	double getFrom();

	/**
	 * Returns the start value for the animation as color string.
	 * 
	 * @return the start value for the animation as color string.
	 */
	String getFromAsString();
}
