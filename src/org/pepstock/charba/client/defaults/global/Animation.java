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
package org.pepstock.charba.client.defaults.global;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.defaults.AbstractItem;
import org.pepstock.charba.client.enums.Easing;

/**
 * It animates charts out of the box. A number of options are provided to configure how the animation looks and how long it
 * takes.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Animation extends AbstractItem {

	private static final int DEFAULT_DURATION = 1000;

	private static final boolean DEFAULT_ANIMATE_ROTATE = true;

	private static final boolean DEFAULT_ANIMATE_SCALE = false;

	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
		animateRotate,
		animateScale,
		duration,
		easing
	}

	/**
	 * Builds the object with parent item and child.
	 * 
	 * @param parent parent item
	 * @param childKey key of child
	 */
	Animation(AbstractItem parent, Key childKey) {
		super(parent, childKey);
	}

	/**
	 * Sets the animation easing.
	 * 
	 * @param easing animation easing.
	 * @see org.pepstock.charba.client.enums.Easing
	 */
	public void setEasing(Easing easing) {
		setValue(Property.easing, easing);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the animation easing.
	 * 
	 * @return animation easing. Default value is {@link org.pepstock.charba.client.enums.Easing#EASE_OUT_QUART}.
	 * @see org.pepstock.charba.client.enums.Easing
	 */
	public Easing getEasing() {
		return getValue(Property.easing, Easing.class, Easing.easeOutQuart);
	}

	/**
	 * Sets the number of milliseconds an animation takes.
	 * 
	 * @param milliseconds the number of milliseconds an animation takes.
	 */
	public void setDuration(int milliseconds) {
		setValue(Property.duration, milliseconds);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the number of milliseconds an animation takes.
	 * 
	 * @return the number of milliseconds an animation takes. Default is 1000 (1 second).
	 */
	public int getDuration() {
		return getValue(Property.duration, DEFAULT_DURATION);
	}
	
	/**
	 * If true, the chart will animate in with a rotation animation.
	 * 
	 * @param animateRotate If true, the chart will animate in with a rotation animation.
	 */
	public void setAnimateRotate(boolean animateRotate) {
		setValue(Property.animateRotate, animateRotate);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * If true, the chart will animate in with a rotation animation.
	 * 
	 * @return If true, the chart will animate in with a rotation animation. Default is true.
	 */
	public boolean isAnimateRotate() {
		return getValue(Property.animateRotate, DEFAULT_ANIMATE_ROTATE);
	}

	/**
	 * If true, will animate scaling the chart from the center outwards.
	 * 
	 * @param animateScale If true, will animate scaling the chart from the center outwards.
	 */
	public void setAnimateScale(boolean animateScale) {
		setValue(Property.animateScale, animateScale);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * If true, will animate scaling the chart from the center outwards.
	 * 
	 * @return If true, will animate scaling the chart from the center outwards. Default is false.
	 */
	public boolean isAnimateScale() {
		return getValue(Property.animateScale, DEFAULT_ANIMATE_SCALE);
	}

}