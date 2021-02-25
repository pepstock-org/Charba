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
package org.pepstock.charba.client.configuration;

import org.pepstock.charba.client.enums.Easing;
import org.pepstock.charba.client.options.AnimationCollection;
import org.pepstock.charba.client.options.AnimationMode;
import org.pepstock.charba.client.options.AnimationProperty;
import org.pepstock.charba.client.options.IsAnimationCollectionKey;
import org.pepstock.charba.client.options.IsAnimationModeKey;
import org.pepstock.charba.client.options.IsAnimationPropertyKey;

/**
 * It animates charts out of the box. A number of options are provided to configure how the animation looks and how long it takes.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class Animation extends ConfigurationOptionsContainer {

	/**
	 * Builds the object storing the chart instance and root options.
	 * 
	 * @param options root options of chart.
	 */
	Animation(ConfigurationOptions options) {
		super(options);
	}

	/**
	 * Sets the animation easing.
	 * 
	 * @param easing animation easing.
	 */
	public void setEasing(Easing easing) {
		getConfiguration().getAnimation().setEasing(easing);
	}

	/**
	 * Returns the animation easing.
	 * 
	 * @return animation easing.
	 */
	public Easing getEasing() {
		return getConfiguration().getAnimation().getEasing();
	}

	/**
	 * Sets the number of milliseconds an animation takes.
	 * 
	 * @param milliseconds the number of milliseconds an animation takes.
	 */
	public void setDuration(int milliseconds) {
		getConfiguration().getAnimation().setDuration(milliseconds);
	}

	/**
	 * Returns the number of milliseconds an animation takes.
	 * 
	 * @return the number of milliseconds an animation takes.
	 */
	public int getDuration() {
		return getConfiguration().getAnimation().getDuration();
	}

	/**
	 * If true, the chart will animate in with a rotation animation.
	 * 
	 * @param animateRotate If true, the chart will animate in with a rotation animation.
	 */
	public void setAnimateRotate(boolean animateRotate) {
		getConfiguration().getAnimation().setAnimateRotate(animateRotate);
	}

	/**
	 * If true, the chart will animate in with a rotation animation.
	 * 
	 * @return If true, the chart will animate in with a rotation animation.
	 */
	public boolean isAnimateRotate() {
		return getConfiguration().getAnimation().isAnimateRotate();
	}

	/**
	 * If true, will animate scaling the chart from the center outwards.
	 * 
	 * @param animateScale If true, will animate scaling the chart from the center outwards.
	 */
	public void setAnimateScale(boolean animateScale) {
		getConfiguration().getAnimation().setAnimateScale(animateScale);
	}

	/**
	 * If true, will animate scaling the chart from the center outwards.
	 * 
	 * @return If true, will animate scaling the chart from the center outwards.
	 */
	public boolean isAnimateScale() {
		return getConfiguration().getAnimation().isAnimateScale();
	}

	/**
	 * Sets <code>true</code> if running animation count plus FPS display in upper left corner of the chart.
	 * 
	 * @param debug <code>true</code> if running animation count plus FPS display in upper left corner of the chart
	 */
	public void setDebug(boolean debug) {
		getConfiguration().getAnimation().setDebug(debug);
	}

	/**
	 * Returns <code>true</code> if running animation count plus FPS display in upper left corner of the chart.
	 * 
	 * @return <code>true</code> if running animation count plus FPS display in upper left corner of the chart
	 */
	public boolean isDebug() {
		return getConfiguration().getAnimation().isDebug();
	}

	/**
	 * Sets the delay before starting the animations.
	 * 
	 * @param delay the delay before starting the animations
	 */
	public void setDelay(int delay) {
		getConfiguration().getAnimation().setDelay(delay);
	}

	/**
	 * Returns the delay before starting the animations.
	 * 
	 * @return the delay before starting the animations
	 */
	public int getDelay() {
		return getConfiguration().getAnimation().getDelay();
	}

	/**
	 * If set to <code>true</code>, loops the animations endlessly.
	 * 
	 * @param loop <code>true</code> if loops the animations endlessly.
	 */
	public void setLoop(boolean loop) {
		getConfiguration().getAnimation().setLoop(loop);
	}

	/**
	 * If set to <code>true</code>, loops the animations endlessly.
	 * 
	 * @return <code>true</code> if loops the animations endlessly.
	 */
	public boolean isLoop() {
		return getConfiguration().getAnimation().isLoop();
	}

	/**
	 * Sets an animation property instance to animation options.
	 * 
	 * @param animationElement animation property instance to add
	 */
	public void setProperty(AnimationProperty animationElement) {
		getConfiguration().getAnimation().setProperty(animationElement);
	}

	/**
	 * Enables or disables an animation property instance into animation options.
	 * 
	 * @param property property instance used to check into animation options
	 * @param enabled if <code>true</code> it enables an animation property
	 */
	public void setPropertyEnabled(IsAnimationPropertyKey property, boolean enabled) {
		getConfiguration().getAnimation().setPropertyEnabled(property, enabled);
	}

	/**
	 * Returns <code>true</code> if the animation property is enabled, otherwise <code>false</code>.
	 * 
	 * @param property property instance used to check into animation options
	 * @return <code>true</code> if the animation property is enabled, otherwise <code>false</code>
	 */
	public boolean isPropertyEnabled(IsAnimationPropertyKey property) {
		return getConfiguration().getAnimation().isPropertyEnabled(property);
	}

	/**
	 * Returns <code>true</code> if an animation property instance is stored into the animation options.
	 * 
	 * @param property property instance used to check into animation options
	 * @return <code>true</code> if an animation property instance is stored into the animation options
	 */
	public boolean hasProperty(IsAnimationPropertyKey property) {
		return getConfiguration().getAnimation().hasProperty(property);
	}

	/**
	 * Returns an animation property instance if stored into the animation options.
	 * 
	 * @param property property instance used to get for animation options
	 * @return an animation property instance or <code>null</code> if does not exists
	 */
	public AnimationProperty getProperty(IsAnimationPropertyKey property) {
		return getConfiguration().getAnimation().getProperty(property);
	}

	/**
	 * Removes an animation property previously added.
	 * 
	 * @param property property instance used to remove from animation options
	 */
	public void removeProperty(IsAnimationPropertyKey property) {
		getConfiguration().getAnimation().removeProperty(property);
	}

	/**
	 * Sets an animation collection instance to animation options.
	 * 
	 * @param animationElement animation collection instance to add
	 */
	public void setCollection(AnimationCollection animationElement) {
		getConfiguration().getAnimation().setCollection(animationElement);
	}

	/**
	 * Enables or disables an animation collection instance into animation options.
	 * 
	 * @param collection collection instance used to check into animation options
	 * @param enabled if <code>true</code> it enables an animation collection
	 */
	public void setCollectionEnabled(IsAnimationCollectionKey collection, boolean enabled) {
		getConfiguration().getAnimation().setCollectionEnabled(collection, enabled);
	}

	/**
	 * Returns <code>true</code> if the animation collection is enabled, otherwise <code>false</code>.
	 * 
	 * @param collection collection instance used to check into animation options
	 * @return <code>true</code> if the animation collection is enabled, otherwise <code>false</code>
	 */
	public boolean isCollectionEnabled(IsAnimationCollectionKey collection) {
		return getConfiguration().getAnimation().isCollectionEnabled(collection);
	}

	/**
	 * Returns <code>true</code> if an animation collection instance is stored into the animation options.
	 * 
	 * @param collection collection instance used to check into animation options
	 * @return <code>true</code> if an animation collection instance is stored into the animation options
	 */
	public boolean hasCollection(IsAnimationCollectionKey collection) {
		return getConfiguration().getAnimation().hasCollection(collection);
	}

	/**
	 * Returns an animation collection instance if stored into the animation options.
	 * 
	 * @param collection collection instance used to get for animation options
	 * @return an animation collection instance or <code>null</code> if does not exists
	 */
	public AnimationCollection getCollection(IsAnimationCollectionKey collection) {
		return getConfiguration().getAnimation().getCollection(collection);
	}

	/**
	 * Removes an animation collection previously added.
	 * 
	 * @param collection collection instance used to remove from animation options
	 */
	public void removeCollection(IsAnimationCollectionKey collection) {
		getConfiguration().getAnimation().removeCollection(collection);
	}

	/**
	 * Sets the animation options set for a specific mode.
	 * 
	 * @param animationElement the animation options set for a specific mode
	 */
	public void setMode(AnimationMode animationElement) {
		getConfiguration().getAnimation().setMode(animationElement);
	}

	/**
	 * Returns <code>true</code> if the animation mode is enabled, otherwise <code>false</code>.
	 * 
	 * @param mode mode instance used to check into animation options
	 * @return <code>true</code> if the animation mode is enabled, otherwise <code>false</code>
	 */
	public final boolean isModeEnabled(IsAnimationModeKey mode) {
		return getConfiguration().getAnimation().isModeEnabled(mode);
	}

	/**
	 * Returns <code>true</code> if an animation mode instance is stored into the animation options.
	 * 
	 * @param mode mode instance used to check into animation options
	 * @return <code>true</code> if an animation mode instance is stored into the animation options
	 */
	public final boolean hasMode(IsAnimationModeKey mode) {
		return getConfiguration().getAnimation().hasMode(mode);
	}

	/**
	 * Returns an animation mode instance if stored into the animation options.
	 * 
	 * @param mode mode instance used to get for animation options
	 * @return an animation mode instance or <code>null</code> if does not exists
	 */
	public AnimationMode getMode(IsAnimationModeKey mode) {
		return getConfiguration().getAnimation().getMode(mode);
	}

	/**
	 * Removes an animation mode previously added.
	 * 
	 * @param mode mode instance used to remove from animation options
	 */
	public final void removeMode(IsAnimationModeKey mode) {
		getConfiguration().getAnimation().removeMode(mode);
	}

}