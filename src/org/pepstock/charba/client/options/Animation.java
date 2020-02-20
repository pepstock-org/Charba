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

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.IsDefaultAnimation;
import org.pepstock.charba.client.enums.Easing;

/**
 * It animates charts out of the box. A number of options are provided to configure how the animation looks and how long it takes.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class Animation extends AbstractModel<Options, IsDefaultAnimation> implements IsDefaultAnimation {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		ANIMATE_ROTATE("animateRotate"),
		ANIMATE_SCALE("animateScale"),
		DURATION("duration"),
		EASING("easing");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use into native object.
		 * 
		 * @param value value of property name
		 */
		private Property(String value) {
			this.value = value;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.Key#value()
		 */
		@Override
		public String value() {
			return value;
		}

	}

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param options options of the chart.
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	Animation(Options options, Key childKey, IsDefaultAnimation defaultValues, NativeObject nativeObject) {
		super(options, childKey, defaultValues, nativeObject);
	}

	/**
	 * Sets the animation easing.
	 * 
	 * @param easing animation easing.
	 */
	public void setEasing(Easing easing) {
		setValue(Property.EASING, easing);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the animation easing.
	 * 
	 * @return animation easing.
	 */
	public Easing getEasing() {
		return getValue(Property.EASING, Easing.values(), getDefaultValues().getEasing());
	}

	/**
	 * Sets the number of milliseconds an animation takes.
	 * 
	 * @param milliseconds the number of milliseconds an animation takes.
	 */
	public void setDuration(int milliseconds) {
		setValue(Property.DURATION, milliseconds);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the number of milliseconds an animation takes.
	 * 
	 * @return the number of milliseconds an animation takes.
	 */
	public int getDuration() {
		return getValue(Property.DURATION, getDefaultValues().getDuration());
	}

	/**
	 * If <code>true</code>, the chart will animate in with a rotation animation.
	 * 
	 * @param animateRotate If <code>true</code>, the chart will animate in with a rotation animation.
	 */
	public void setAnimateRotate(boolean animateRotate) {
		setValue(Property.ANIMATE_ROTATE, animateRotate);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * If <code>true</code>, the chart will animate in with a rotation animation.
	 * 
	 * @return If <code>true</code>, the chart will animate in with a rotation animation.
	 */
	public boolean isAnimateRotate() {
		return getValue(Property.ANIMATE_ROTATE, getDefaultValues().isAnimateRotate());
	}

	/**
	 * If <code>true</code>, will animate scaling the chart from the center outwards.
	 * 
	 * @param animateScale If <code>true</code>, will animate scaling the chart from the center outwards.
	 */
	public void setAnimateScale(boolean animateScale) {
		setValue(Property.ANIMATE_SCALE, animateScale);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * If <code>true</code>, will animate scaling the chart from the center outwards.
	 * 
	 * @return If <code>true</code>, will animate scaling the chart from the center outwards.
	 */
	public boolean isAnimateScale() {
		return getValue(Property.ANIMATE_SCALE, getDefaultValues().isAnimateScale());
	}

}