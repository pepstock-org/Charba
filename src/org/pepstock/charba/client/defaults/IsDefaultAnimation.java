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

/**
 * Interface to define animation object defaults, ANIMATION name space.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface IsDefaultAnimation extends IsDefaultBaseAnimation {

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
}
