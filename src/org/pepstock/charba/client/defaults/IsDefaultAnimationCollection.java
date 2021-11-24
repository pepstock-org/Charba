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

import java.util.List;

import org.pepstock.charba.client.callbacks.FromCallback;
import org.pepstock.charba.client.callbacks.ToCallback;
import org.pepstock.charba.client.enums.AnimationType;
import org.pepstock.charba.client.options.AnimationPropertyKey;

/**
 * Interface to define animation for element properties defaults.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface IsDefaultAnimationCollection extends IsDefaultBaseAnimation {

	/**
	 * Returns the type of <code>from</code> property and determines the interpolator used.
	 * 
	 * @return the type of <code>from</code> property and determines the interpolator used.
	 */
	AnimationType getType();

	/**
	 * Returns the start value for the animation as number.
	 * 
	 * @return the start value for the animation as number.
	 */
	double getFrom();

	/**
	 * Returns the start value for the animation as boolean.
	 * 
	 * @return the start value for the animation as boolean.
	 */
	boolean getFromAsBoolean();

	/**
	 * Returns the start value for the animation as color string.
	 * 
	 * @return the start value for the animation as color string.
	 */
	String getFromAsString();

	/**
	 * Returns the end value for the animation as number.
	 * 
	 * @return the end value for the animation as number.
	 */
	double getTo();

	/**
	 * Returns the end value for the animation as boolean.
	 * 
	 * @return the end value for the animation as boolean.
	 */
	boolean getToAsBoolean();

	/**
	 * Returns the end value for the animation as color string.
	 * 
	 * @return the end value for the animation as color string.
	 */
	String getToAsString();

	/**
	 * Returns the properties defined in the animation collection.
	 * 
	 * @return the properties defined in the animation collection
	 */
	List<AnimationPropertyKey> getProperties();

	/**
	 * Returns the callback to set the start value for the animation.
	 * 
	 * @return the callback instance to use to set the start value for the animation
	 */
	FromCallback getFromCallback();

	/**
	 * Returns the callback to set the end value for the animation.
	 * 
	 * @return the callback instance to use to set the end value for the animation
	 */
	ToCallback getToCallback();
}
