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
package org.pepstock.charba.client;

import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.defaults.globals.DefaultTransitions;
import org.pepstock.charba.client.enums.Easing;
import org.pepstock.charba.client.options.AnimationCollection;
import org.pepstock.charba.client.options.AnimationTransition;
import org.pepstock.charba.client.options.IsAnimation;
import org.pepstock.charba.client.options.IsAnimationCollectionKey;
import org.pepstock.charba.client.options.IsAnimations;
import org.pepstock.charba.client.options.IsTransitionKey;

/**
 * Object can be provided with additional configuration for the update process.<br>
 * This is useful when update is manually called inside an event handler and some different animation is desired.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class UpdateConfiguration extends NativeObjectContainer implements IsAnimation, IsAnimations {

	// default key, usually used for update
	private static final String UPDATE_MODE_KEY = "charbaupdate";
	/**
	 * Default animation mode key, used for chart updating.
	 */
	public static final IsTransitionKey UPDATE = IsTransitionKey.create(UPDATE_MODE_KEY);
	// delegated animation mode
	private final AnimationTransition transition;

	/**
	 * Creates an empty animation mode to use for chart updating.
	 */
	public UpdateConfiguration() {
		super();
		// creates a mode to wrap
		this.transition = new AnimationTransition(UPDATE, DefaultTransitions.DEFAULT_ANIMATION_TRANSITION, new ChartEnvelop<>(getNativeObject()));
	}

	/**
	 * Returns the mode which is wrapped.
	 * 
	 * @return the mode which is wrapped
	 */
	AnimationTransition getTransition() {
		return transition;
	}

	/**
	 * Sets the animation easing.
	 * 
	 * @param easing animation easing.
	 */
	@Override
	public void setEasing(Easing easing) {
		transition.getAnimation().setEasing(easing);
	}

	/**
	 * Returns the animation easing.
	 * 
	 * @return animation easing.
	 */
	@Override
	public Easing getEasing() {
		return transition.getAnimation().getEasing();
	}

	/**
	 * Sets the number of milliseconds an animation takes.
	 * 
	 * @param milliseconds the number of milliseconds an animation takes.
	 */
	@Override
	public void setDuration(int milliseconds) {
		transition.getAnimation().setDuration(milliseconds);
	}

	/**
	 * Returns the number of milliseconds an animation takes.
	 * 
	 * @return the number of milliseconds an animation takes.
	 */
	@Override
	public int getDuration() {
		return transition.getAnimation().getDuration();
	}

	/**
	 * Sets the delay before starting the animations.
	 * 
	 * @param delay the delay before starting the animations
	 */
	@Override
	public void setDelay(int delay) {
		transition.getAnimation().setDelay(delay);
	}

	/**
	 * Returns the delay before starting the animations.
	 * 
	 * @return the delay before starting the animations
	 */
	@Override
	public int getDelay() {
		return transition.getAnimation().getDelay();
	}

	/**
	 * If set to <code>true</code>, loops the animations endlessly.
	 * 
	 * @param loop <code>true</code> if loops the animations endlessly.
	 */
	@Override
	public void setLoop(boolean loop) {
		transition.getAnimation().setLoop(loop);
	}

	/**
	 * If set to <code>true</code>, loops the animations endlessly.
	 * 
	 * @return <code>true</code> if loops the animations endlessly.
	 */
	@Override
	public boolean isLoop() {
		return transition.getAnimation().isLoop();
	}

	/**
	 * If <code>true</code>, the chart will animate in with a rotation animation.
	 * 
	 * @param animateRotate If <code>true</code>, the chart will animate in with a rotation animation.
	 */
	@Override
	public void setAnimateRotate(boolean animateRotate) {
		transition.getAnimation().setAnimateRotate(animateRotate);
	}

	/**
	 * If <code>true</code>, the chart will animate in with a rotation animation.
	 * 
	 * @return if <code>true</code>, the chart will animate in with a rotation animation.
	 */
	@Override
	public boolean isAnimateRotate() {
		return transition.getAnimation().isAnimateRotate();
	}

	/**
	 * If <code>true</code>, will animate scaling the chart from the center outwards.
	 * 
	 * @param animateScale If <code>true</code>, will animate scaling the chart from the center outwards.
	 */
	@Override
	public void setAnimateScale(boolean animateScale) {
		transition.getAnimation().setAnimateScale(animateScale);
	}

	/**
	 * If <code>true</code>, will animate scaling the chart from the center outwards.
	 * 
	 * @return If <code>true</code>, will animate scaling the chart from the center outwards.
	 */
	@Override
	public boolean isAnimateScale() {
		return transition.getAnimation().isAnimateScale();
	}

	/**
	 * Enables or disables an animation collection instance into animation options.
	 * 
	 * @param collection collection instance used to check into animation options
	 * @param enabled if <code>true</code> it enables an animation collection
	 */
	@Override
	public void setEnabled(IsAnimationCollectionKey collection, boolean enabled) {
		transition.getAnimations().setEnabled(collection, enabled);
	}

	/**
	 * Returns <code>true</code> if the animation collection is enabled, otherwise <code>false</code>.
	 * 
	 * @param collection collection instance used to check into animation options
	 * @return <code>true</code> if the animation collection is enabled, otherwise <code>false</code>
	 */
	@Override
	public boolean isEnabled(IsAnimationCollectionKey collection) {
		return transition.getAnimations().isEnabled(collection);
	}

	/**
	 * Returns <code>true</code> if an animation collection instance is stored into the animation options.
	 * 
	 * @param collection collection instance used to check into animation options
	 * @return <code>true</code> if an animation collection instance is stored into the animation options
	 */
	@Override
	public boolean has(IsAnimationCollectionKey collection) {
		return transition.getAnimations().has(collection);
	}

	/**
	 * Returns an animation collection instance if stored into the animation options.
	 * 
	 * @param collection collection instance used to get for animation options
	 * @return an animation collection instance or <code>null</code> if does not exists
	 */
	@Override
	public AnimationCollection get(IsAnimationCollectionKey collection) {
		return transition.getAnimations().get(collection);
	}
	
	/**
	 * Sets an animation collection instance to store in the animation options.
	 * 
	 * @param collection collection instance used to get for animation options
	 * @param animationCollection an animation collection instance to set
	 */
	@Override
	public void set(IsAnimationCollectionKey collection, AnimationCollection animationCollection) {
		transition.getAnimations().set(collection, animationCollection);
	}

	/**
	 * Creates an animation collection instance and stores into the animation options.
	 * 
	 * @param collection collection key used to create the animation collections
	 * @return a collection animation options
	 */
	@Override
	public AnimationCollection create(IsAnimationCollectionKey collection) {
		// returns the animation options
		return transition.getAnimations().create(collection);
	}

	/**
	 * Removes an animation collection previously added.
	 * 
	 * @param collection collection instance used to remove from animation options
	 */
	@Override
	public void remove(IsAnimationCollectionKey collection) {
		transition.getAnimations().remove(collection);
	}

	/**
	 * Wraps the protected method to get the java script object in order to consume it to configure the chart update or render.
	 * 
	 * @return the java script object in order to consume it to configure the chart update or render.
	 */
	NativeObject nativeObject() {
		return getNativeObject();
	}

}