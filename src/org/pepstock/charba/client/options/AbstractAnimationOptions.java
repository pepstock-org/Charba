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
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.defaults.IsDefaultAnimation;
import org.pepstock.charba.client.enums.Easing;

/**
 * Object can be provided with additional configuration by callbacks to define animation options at runtime.
 * 
 * @author Andrea "Stock" Stocchero
 */
public abstract class AbstractAnimationOptions extends NativeObjectContainer {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		ANIMATION("animation");

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

	// delegated animation 
	private final Animation animation;

	/**
	 * Creates an empty animation to use for chart configuration when the animation is created by a callback.
	 * 
	 * @param defaultValues default provider
	 */
	protected AbstractAnimationOptions(IsDefaultAnimation defaultValues) {
		this(defaultValues, null);
	}

	/**
	 * Creates an animation to use for chart configuration when the animation is created by a callback, using a clone of another animation object.
	 * 
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	protected AbstractAnimationOptions(IsDefaultAnimation defaultValues, NativeObject nativeObject) {
		super(nativeObject);
		// creates a animation to wrap
		this.animation = new Animation(null, Property.ANIMATION, defaultValues, getNativeObject());
	}

	/**
	 * Sets the animation easing.
	 * 
	 * @param easing animation easing.
	 */
	public final void setEasing(Easing easing) {
		animation.setEasing(easing);
	}

	/**
	 * Returns the animation easing.
	 * 
	 * @return animation easing.
	 */
	public final Easing getEasing() {
		return animation.getEasing();
	}

	/**
	 * Sets the number of milliseconds an animation takes.
	 * 
	 * @param milliseconds the number of milliseconds an animation takes.
	 */
	public final void setDuration(int milliseconds) {
		animation.setDuration(milliseconds);
	}

	/**
	 * Returns the number of milliseconds an animation takes.
	 * 
	 * @return the number of milliseconds an animation takes.
	 */
	public final int getDuration() {
		return animation.getDuration();
	}

	/**
	 * Sets <code>true</code> if running animation count plus FPS display in upper left corner of the chart.
	 * 
	 * @param debug <code>true</code> if running animation count plus FPS display in upper left corner of the chart
	 */
	public final void setDebug(boolean debug) {
		animation.setDebug(debug);
	}

	/**
	 * Returns <code>true</code> if running animation count plus FPS display in upper left corner of the chart.
	 * 
	 * @return <code>true</code> if running animation count plus FPS display in upper left corner of the chart
	 */
	public final boolean isDebug() {
		return animation.isDebug();
	}

	/**
	 * Sets the delay before starting the animations.
	 * 
	 * @param delay the delay before starting the animations
	 */
	public final void setDelay(int delay) {
		animation.setDelay(delay);
	}

	/**
	 * Returns the delay before starting the animations.
	 * 
	 * @return the delay before starting the animations
	 */
	public final int getDelay() {
		return animation.getDelay();
	}

	/**
	 * If set to <code>true</code>, loops the animations endlessly.
	 * 
	 * @param loop <code>true</code> if loops the animations endlessly.
	 */
	public final void setLoop(boolean loop) {
		animation.setLoop(loop);
	}

	/**
	 * If set to <code>true</code>, loops the animations endlessly.
	 * 
	 * @return <code>true</code> if loops the animations endlessly.
	 */
	public final boolean isLoop() {
		return animation.isLoop();
	}

	/**
	 * If <code>true</code>, the chart will animate in with a rotation animation.
	 * 
	 * @param animateRotate If <code>true</code>, the chart will animate in with a rotation animation.
	 */
	public final void setAnimateRotate(boolean animateRotate) {
		animation.setAnimateRotate(animateRotate);
	}

	/**
	 * If <code>true</code>, the chart will animate in with a rotation animation.
	 * 
	 * @return if <code>true</code>, the chart will animate in with a rotation animation.
	 */
	public final boolean isAnimateRotate() {
		return animation.isAnimateRotate();
	}

	/**
	 * If <code>true</code>, will animate scaling the chart from the center outwards.
	 * 
	 * @param animateScale If <code>true</code>, will animate scaling the chart from the center outwards.
	 */
	public final void setAnimateScale(boolean animateScale) {
		animation.setAnimateScale(animateScale);
	}

	/**
	 * If <code>true</code>, will animate scaling the chart from the center outwards.
	 * 
	 * @return If <code>true</code>, will animate scaling the chart from the center outwards.
	 */
	public final boolean isAnimateScale() {
		return animation.isAnimateScale();
	}

	/**
	 * Sets an animation mode instance to animation options.
	 * 
	 * @param animationElement the animation mode instance to add
	 */
	public final void setMode(AnimationMode animationElement) {
		animation.setMode(animationElement);
	}
	
	/**
	 * Enables or disables an animation mode instance into animation options.
	 * 
	 * @param mode mode instance used to check into animation options
	 * @param enabled if <code>true</code> it enables an animation collection
	 */
	public final void setModeEnabled(IsAnimationModeKey mode, boolean enabled) {
		animation.setModeEnabled(mode, enabled);
	}

	/**
	 * Returns <code>true</code> if the animation mode is enabled, otherwise <code>false</code>.
	 * 
	 * @param mode mode instance used to check into animation options
	 * @return <code>true</code> if the animation mode is enabled, otherwise <code>false</code>
	 */
	public final boolean isModeEnabled(IsAnimationModeKey mode) {
		return animation.isModeEnabled(mode);
	}

	/**
	 * Returns <code>true</code> if an animation mode instance is stored into the animation options.
	 * 
	 * @param mode mode instance used to check into animation options
	 * @return <code>true</code> if an animation mode instance is stored into the animation options
	 */
	public final boolean hasMode(IsAnimationModeKey mode) {
		return animation.hasMode(mode);
	}

	/**
	 * Returns an animation mode instance if stored into the animation options.
	 * 
	 * @param mode mode instance used to get for animation options
	 * @return an animation mode instance or <code>null</code> if does not exists
	 */
	public final AnimationMode getMode(IsAnimationModeKey mode) {
		return animation.getMode(mode);
	}

	/**
	 * Removes an animation mode previously added.
	 * 
	 * @param mode mode instance used to remove from animation options
	 */
	public final void removeMode(IsAnimationModeKey mode) {
		animation.removeMode(mode);
	}

	/**
	 * Sets an animation property instance to animation options.
	 * 
	 * @param animationElement animation property instance to add
	 */
	public final void setProperty(AnimationProperty animationElement) {
		animation.setProperty(animationElement);
	}

	/**
	 * Enables or disables an animation property instance into animation options.
	 * 
	 * @param property property instance used to check into animation options
	 * @param enabled if <code>true</code> it enables an animation property
	 */
	public final void setPropertyEnabled(IsAnimationPropertyKey property, boolean enabled) {
		animation.setPropertyEnabled(property, enabled);
	}

	/**
	 * Returns <code>true</code> if the animation property is enabled, otherwise <code>false</code>.
	 * 
	 * @param property property instance used to check into animation options
	 * @return <code>true</code> if the animation property is enabled, otherwise <code>false</code>
	 */
	public final boolean isPropertyEnabled(IsAnimationPropertyKey property) {
		return animation.isPropertyEnabled(property);
	}

	/**
	 * Returns <code>true</code> if an animation property instance is stored into the animation options.
	 * 
	 * @param property property instance used to check into animation options
	 * @return <code>true</code> if an animation property instance is stored into the animation options
	 */
	public final boolean hasProperty(IsAnimationPropertyKey property) {
		return animation.hasProperty(property);
	}

	/**
	 * Returns an animation property instance if stored into the animation options.
	 * 
	 * @param property property instance used to get for animation options
	 * @return an animation property instance or <code>null</code> if does not exists
	 */
	public final AnimationProperty getProperty(IsAnimationPropertyKey property) {
		return animation.getProperty(property);
	}

	/**
	 * Removes an animation property previously added.
	 * 
	 * @param property property instance used to remove from animation options
	 */
	public final void removeProperty(IsAnimationPropertyKey property) {
		animation.removeProperty(property);
	}

	/**
	 * Sets an animation collection instance to animation options.
	 * 
	 * @param animationElement animation collection instance to add
	 */
	public final void setCollection(AnimationCollection animationElement) {
		animation.setCollection(animationElement);
	}

	/**
	 * Enables or disables an animation collection instance into animation options.
	 * 
	 * @param collection collection instance used to check into animation options
	 * @param enabled if <code>true</code> it enables an animation collection
	 */
	public final void setCollectionEnabled(IsAnimationCollectionKey collection, boolean enabled) {
		animation.setCollectionEnabled(collection, enabled);
	}

	/**
	 * Returns <code>true</code> if the animation collection is enabled, otherwise <code>false</code>.
	 * 
	 * @param collection collection instance used to check into animation options
	 * @return <code>true</code> if the animation collection is enabled, otherwise <code>false</code>
	 */
	public final boolean isCollectionEnabled(IsAnimationCollectionKey collection) {
		return animation.isCollectionEnabled(collection);
	}

	/**
	 * Returns <code>true</code> if an animation collection instance is stored into the animation options.
	 * 
	 * @param collection collection instance used to check into animation options
	 * @return <code>true</code> if an animation collection instance is stored into the animation options
	 */
	public final boolean hasCollection(IsAnimationCollectionKey collection) {
		return animation.hasCollection(collection);
	}

	/**
	 * Returns an animation collection instance if stored into the animation options.
	 * 
	 * @param collection collection instance used to get for animation options
	 * @return an animation collection instance or <code>null</code> if does not exists
	 */
	public final AnimationCollection getCollection(IsAnimationCollectionKey collection) {
		return animation.getCollection(collection);
	}

	/**
	 * Removes an animation collection previously added.
	 * 
	 * @param collection collection instance used to remove from animation options
	 */
	public final void removeCollection(IsAnimationCollectionKey collection) {
		animation.removeCollection(collection);
	}

}