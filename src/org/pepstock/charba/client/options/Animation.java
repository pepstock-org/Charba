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

import java.util.HashMap;
import java.util.Map;

import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.defaults.IsDefaultAnimation;
import org.pepstock.charba.client.enums.DefaultAnimationModeKey;

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
public class Animation extends AbstractAnimationMode<Key, IsDefaultAnimation> implements IsDefaultAnimation {

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
	// map to store the custom of animation modes
	// K = mode name, V = animation mode instance
	private final Map<String, AnimationMode> animationModes = new HashMap<>();

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
	public final void setAnimateRotate(boolean animateRotate) {
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
	public final boolean isAnimateRotate() {
		return getValue(Property.ANIMATE_ROTATE, defaultValues.isAnimateRotate());
	}

	/**
	 * If <code>true</code>, will animate scaling the chart from the center outwards.
	 * 
	 * @param animateScale If <code>true</code>, will animate scaling the chart from the center outwards.
	 */
	public final void setAnimateScale(boolean animateScale) {
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
	public final boolean isAnimateScale() {
		return getValue(Property.ANIMATE_SCALE, defaultValues.isAnimateScale());
	}

	/**
	 * Sets an animation mode instance to animation options.
	 * 
	 * @param animationElement the animation mode instance to add
	 */
	public final void setMode(AnimationMode animationElement) {
		// adds and checks if added
		if (setSubElement(animationElement)) {
			// checks if is using the default "none"
			// the checking has been done after adding
			// in order to be sure that argument is consistent
			if (Key.equals(animationElement.getKey(), DefaultAnimationModeKey.NONE)) {
				// removes the property previously added
				remove(DefaultAnimationModeKey.NONE);
			} else {
				// if here the mode is not "none"
				// stores the object into the cache
				animationModes.put(animationElement.getKey().value(), animationElement);
				// checks if the node is already added to parent
				checkAndAddToParent();
			}
		}
	}

	/**
	 * Returns <code>true</code> if the animation mode is enabled, otherwise <code>false</code>.
	 * 
	 * @param mode mode instance used to check into animation options
	 * @return <code>true</code> if the animation mode is enabled, otherwise <code>false</code>
	 */
	public final boolean isModeEnabled(IsAnimationModeKey mode) {
		// checks if mode is consistent, not equals to none and has got the cached element
		if (IsAnimationModeKey.isValid(mode) && !Key.equals(mode, DefaultAnimationModeKey.NONE) && hasMode(mode)) {
			// gets the mode element
			AnimationMode modeElement = getMode(mode);
			// checks if the duration is not equals to 0
			return modeElement.getDuration() > 0;
		}
		// if here, the mode is not valid or not stored
		// then returns false
		return false;
	}

	/**
	 * Returns <code>true</code> if an animation mode instance is stored into the animation options.
	 * 
	 * @param mode mode instance used to check into animation options
	 * @return <code>true</code> if an animation mode instance is stored into the animation options
	 */
	public final boolean hasMode(IsAnimationModeKey mode) {
		// checks if mode is consistent
		if (IsAnimationModeKey.isValid(mode) && !Key.equals(mode, DefaultAnimationModeKey.NONE)) {
			// checks if is cached
			if (animationModes.containsKey(mode.value())) {
				// returns because it is in the cached
				return true;
			}
			// checks on the native object
			return ObjectType.OBJECT.equals(type(mode));
		}
		// if here, the mode is not valid
		// then returns false
		return false;
	}

	/**
	 * Returns an animation mode instance if stored into the animation options.
	 * 
	 * @param mode mode instance used to get for animation options
	 * @return an animation mode instance or <code>null</code> if does not exists
	 */
	@Override
	public final AnimationMode getMode(IsAnimationModeKey mode) {
		// checks if mode is consistent
		if (IsAnimationModeKey.isValid(mode) && !Key.equals(mode, DefaultAnimationModeKey.NONE)) {
			// checks if is cached
			if (animationModes.containsKey(mode.value())) {
				// returns because it is in the cached
				return animationModes.get(mode.value());
			} else if (has(mode)) {
				// if here, the mode is stored into object
				// gets from the native object
				return new AnimationMode(this, mode, defaultValues.getMode(mode), getValue(mode));
			}
		}
		// if here, the mode is not valid
		// then returns null
		return null;
	}

	/**
	 * Removes an animation mode previously added.
	 * 
	 * @param mode mode instance used to remove from animation options
	 */
	public final void removeMode(IsAnimationModeKey mode) {
		// checks if mode is consistent and if the mode has been previously added
		if (IsAnimationModeKey.isValid(mode) && !Key.equals(mode, DefaultAnimationModeKey.NONE) && animationModes.containsKey(mode.value())) {
			// remove from object
			remove(mode);
			// removes from cache
			animationModes.remove(mode.value());
		}
	}

}