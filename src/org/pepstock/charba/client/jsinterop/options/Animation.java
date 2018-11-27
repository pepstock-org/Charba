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
package org.pepstock.charba.client.jsinterop.options;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.Easing;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultAnimation;

/**
 * It animates charts out of the box. A number of options are provided to configure how the animation looks and how long it
 * takes.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class Animation extends AbstractModel<Options, IsDefaultAnimation> implements IsDefaultAnimation {
	
	/**
	 * Name of fields of JavaScript object.
	 */
	enum Property implements Key
	{
		animateRotate,
		animateScale,
		duration,
		easing
	}
	
	Animation(Options options, Key childKey, IsDefaultAnimation defaultValues, NativeObject nativeObject) {
		super(options, childKey, defaultValues, nativeObject);
	}
	
	/**
	 * Sets the animation easing.
	 * 
	 * @param easing animation easing.
	 * @see org.pepstock.charba.client.enums.Easing
	 */
	public void setEasing(Easing easing) {
		setValue(Property.easing, easing);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the animation easing.
	 * 
	 * @return animation easing. Default value is {@link org.pepstock.charba.client.enums.Easing#easeOutQuart}.
	 * @see org.pepstock.charba.client.enums.Easing
	 */
	public Easing getEasing() {
		return getValue(Property.easing, Easing.class, getDefaultValues().getEasing());
	}

	/**
	 * Sets the number of milliseconds an animation takes.
	 * 
	 * @param milliseconds the number of milliseconds an animation takes.
	 */
	public void setDuration(int milliseconds) {
		setValue(Property.duration, milliseconds);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the number of milliseconds an animation takes.
	 * 
	 * @return the number of milliseconds an animation takes. Default is 1000 (1 second).
	 */
	public int getDuration() {
		return getValue(Property.duration, getDefaultValues().getDuration());
	}
	
	/**
	 * If true, the chart will animate in with a rotation animation.
	 * 
	 * @param animateRotate If true, the chart will animate in with a rotation animation.
	 */
	public void setAnimateRotate(boolean animateRotate) {
		setValue(Property.animateRotate, animateRotate);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * If true, the chart will animate in with a rotation animation.
	 * 
	 * @return If true, the chart will animate in with a rotation animation. Default is true.
	 */
	public boolean isAnimateRotate() {
		return getValue(Property.animateRotate, getDefaultValues().isAnimateRotate());
	}

	/**
	 * If true, will animate scaling the chart from the center outwards.
	 * 
	 * @param animateScale If true, will animate scaling the chart from the center outwards.
	 */
	public void setAnimateScale(boolean animateScale) {
		setValue(Property.animateScale, animateScale);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * If true, will animate scaling the chart from the center outwards.
	 * 
	 * @return If true, will animate scaling the chart from the center outwards. Default is false.
	 */
	public boolean isAnimateScale() {
		return getValue(Property.animateScale, getDefaultValues().isAnimateScale());
	}
	
}