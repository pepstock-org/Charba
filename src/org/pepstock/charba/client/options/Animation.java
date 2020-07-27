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

/**
 * It animates charts out of the box. A number of options are provided to configure how the animation looks and how long it takes. <br>
 * This is the <code>animation</code> options structure:<br>
 * <br>
 * 
 * <pre>
 * animation
 *   !
 *   +--- animation property
 *   !
 *   +--- animation collection
 *   !
 *   +--- animation mode
 *          !
 *          +--- animation property
 *          !
 *          +--- animation collection
 * </pre>
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Animation extends AbstractAnimationMode<Key, IsDefaultAnimation> implements IsDefaultAnimation {

	/**
	 * Name of properties of native object.
	 */
	enum Property implements Key
	{
		ANIMATE_ROTATE("animateRotate"),
		ANIMATE_SCALE("animateScale");

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

	// default values
	private final IsDefaultAnimation defaultValues;

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param parent parent node of the chart options.
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	Animation(AbstractNode parent, Key childKey, IsDefaultAnimation defaultValues, NativeObject nativeObject) {
		super(parent, childKey, defaultValues, nativeObject);
		// stores defaults which has been already checked on super class
		this.defaultValues = defaultValues;
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
	 * @return if <code>true</code>, the chart will animate in with a rotation animation.
	 */
	@Override
	public boolean isAnimateRotate() {
		return getValue(Property.ANIMATE_ROTATE, defaultValues.isAnimateRotate());
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
	@Override
	public boolean isAnimateScale() {
		return getValue(Property.ANIMATE_SCALE, defaultValues.isAnimateScale());
	}

	/**
	 * Sets the animation options set for a specific mode.
	 * 
	 * @param animationElement the animation options set for a specific mode
	 */
	public void setMode(AnimationMode animationElement) {
		// adds and checks if added
		if (setSubElement(animationElement)) {
			// checks if the node is already added to parent
			checkAndAddToParent();
		}
	}

	/**
	 * Returns the animation options set for a specific mode.
	 * 
	 * @param mode mode instance used to get for animation options
	 * @return the animation options set for a specific mode.
	 */
	@Override
	public AnimationMode getMode(IsAnimationModeKey mode) {
		// checks if mode is consistent
		if (IsAnimationModeKey.isValid(mode)) {
			return new AnimationMode(this, mode, defaultValues.getMode(mode), getValue(mode));
		}
		// if here, the mode is not valid
		// then returns null
		return null;
	}

}