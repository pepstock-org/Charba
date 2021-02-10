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

import org.pepstock.charba.client.options.IsAnimationModeKey;

/**
 * Interface to define animation object defaults.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface IsDefaultAnimation extends IsDefaultAnimationMode {

	/**
	 * If <code>true</code>, the chart will animate in with a rotation animation.
	 * 
	 * @return If <code>true</code>, the chart will animate in with a rotation animation.
	 */
	boolean isAnimateRotate();

	/**
	 * If <code>true</code>, will animate scaling the chart from the center outwards.
	 * 
	 * @return If <code>true</code>, will animate scaling the chart from the center outwards.
	 */
	boolean isAnimateScale();

	/**
	 * Returns <code>true</code> if an animation mode instance is stored into the animation options.
	 * 
	 * @param mode mode instance used to check into animation options
	 * @return <code>true</code> if an animation mode instance is stored into the animation options
	 */
	boolean hasMode(IsAnimationModeKey mode);

	/**
	 * Returns the animation options set for a specific mode.
	 * 
	 * @param mode mode instance used to search for animation options
	 * @return the animation options set for a specific mode.
	 */
	IsDefaultAnimationMode getMode(IsAnimationModeKey mode);

}
