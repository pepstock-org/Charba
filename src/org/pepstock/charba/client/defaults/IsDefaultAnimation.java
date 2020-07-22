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

import org.pepstock.charba.client.options.IsAnimationCollection;
import org.pepstock.charba.client.options.IsAnimationMode;
import org.pepstock.charba.client.options.IsAnimationProperty;

/**
 * Interface to define animation object defaults.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface IsDefaultAnimation extends IsDefaultBaseAnimation {

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
	 * Returns the animation options set for a specific mode.
	 * 
	 * @param mode mode instance used to search for animation options
	 * @return the animation options set for a specific mode.
	 */
	IsDefaultAnimationModeElement getMode(IsAnimationMode mode);

	/**
	 * Returns the animation options set for a specific property.
	 * 
	 * @param property property instance used to search for animation options
	 * @return the animation options set for a specific property.
	 */
	IsDefaultAnimationElement getProperty(IsAnimationProperty property);
	
	/**
	 * Returns the animation options set for a specific collection.
	 * 
	 * @param collection collection instance used to search for animation options
	 * @return the animation options set for a specific collection.
	 */
	IsDefaultAnimationElement getCollection(IsAnimationCollection collection);
}
