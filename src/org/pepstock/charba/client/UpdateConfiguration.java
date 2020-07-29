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
import org.pepstock.charba.client.defaults.globals.DefaultAnimation;
import org.pepstock.charba.client.enums.Easing;
import org.pepstock.charba.client.options.AnimationCollection;
import org.pepstock.charba.client.options.AnimationMode;
import org.pepstock.charba.client.options.AnimationProperty;
import org.pepstock.charba.client.options.IsAnimationCollectionKey;
import org.pepstock.charba.client.options.IsAnimationModeKey;
import org.pepstock.charba.client.options.IsAnimationPropertyKey;

/**
 * Object can be provided with additional configuration for the update process.<br>
 * This is useful when update is manually called inside an event handler and some different animation is desired.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class UpdateConfiguration extends NativeObjectContainer {

	// default key, usually used for update
	private static final String UPDATE_MODE_KEY = "_charbaupdate";
	/**
	 * Default animation mode key, used for chart updating.
	 */
	public static final IsAnimationModeKey UPDATE = IsAnimationModeKey.create(UPDATE_MODE_KEY);
	// delegated animation mode
	private final AnimationMode mode;

	/**
	 * Creates an empty animation mode to use for chart updating.
	 */
	public UpdateConfiguration() {
		super();
		// creates a mode to wrap
		this.mode = new AnimationMode(UPDATE, DefaultAnimation.DEFAULT_ANIMATION_MODE, new ChartEnvelop<>(getNativeObject()));
	}

	/**
	 * Returns the mode which is wrapped.
	 * 
	 * @return the mode which is wrapped
	 */
	AnimationMode getMode() {
		return mode;
	}

	/**
	 * Sets the animation easing.
	 * 
	 * @param easing animation easing.
	 */
	public void setEasing(Easing easing) {
		mode.setEasing(easing);
	}

	/**
	 * Returns the animation easing.
	 * 
	 * @return animation easing.
	 */
	public Easing getEasing() {
		return mode.getEasing();
	}

	/**
	 * Sets the number of milliseconds an animation takes.
	 * 
	 * @param milliseconds the number of milliseconds an animation takes.
	 */
	public void setDuration(int milliseconds) {
		mode.setDuration(milliseconds);
	}

	/**
	 * Returns the number of milliseconds an animation takes.
	 * 
	 * @return the number of milliseconds an animation takes.
	 */
	public int getDuration() {
		return mode.getDuration();
	}

	/**
	 * Sets <code>true</code> if running animation count plus FPS display in upper left corner of the chart.
	 * 
	 * @param debug <code>true</code> if running animation count plus FPS display in upper left corner of the chart
	 */
	public void setDebug(boolean debug) {
		mode.setDebug(debug);
	}

	/**
	 * Returns <code>true</code> if running animation count plus FPS display in upper left corner of the chart.
	 * 
	 * @return <code>true</code> if running animation count plus FPS display in upper left corner of the chart
	 */
	public boolean isDebug() {
		return mode.isDebug();
	}

	/**
	 * Sets the delay before starting the animations.
	 * 
	 * @param delay the delay before starting the animations
	 */
	public void setDelay(int delay) {
		mode.setDelay(delay);
	}

	/**
	 * Returns the delay before starting the animations.
	 * 
	 * @return the delay before starting the animations
	 */
	public int getDelay() {
		return mode.getDelay();
	}

	/**
	 * If set to <code>true</code>, loops the animations endlessly.
	 * 
	 * @param loop <code>true</code> if loops the animations endlessly.
	 */
	public void setLoop(boolean loop) {
		mode.setLoop(loop);
	}

	/**
	 * If set to <code>true</code>, loops the animations endlessly.
	 * 
	 * @return <code>true</code> if loops the animations endlessly.
	 */
	public boolean isLoop() {
		return mode.isLoop();
	}

	/**
	 * Sets an animation property instance to animation options.
	 * 
	 * @param animationElement animation property instance to add
	 */
	public void setProperty(AnimationProperty animationElement) {
		mode.setProperty(animationElement);
	}

	/**
	 * Enables or disables an animation property instance into animation options.
	 * 
	 * @param property property instance used to check into animation options
	 * @param enabled if <code>true</code> it enables an animation property
	 */
	public void setPropertyEnabled(IsAnimationPropertyKey property, boolean enabled) {
		mode.setPropertyEnabled(property, enabled);
	}

	/**
	 * Returns <code>true</code> if the animation property is enabled, otherwise <code>false</code>.
	 * 
	 * @param property property instance used to check into animation options
	 * @return <code>true</code> if the animation property is enabled, otherwise <code>false</code>
	 */
	public boolean isPropertyEnabled(IsAnimationPropertyKey property) {
		return mode.isPropertyEnabled(property);
	}

	/**
	 * Returns <code>true</code> if an animation property instance is stored into the animation options.
	 * 
	 * @param property property instance used to check into animation options
	 * @return <code>true</code> if an animation property instance is stored into the animation options
	 */
	public boolean hasProperty(IsAnimationPropertyKey property) {
		return mode.hasProperty(property);
	}

	/**
	 * Returns an animation property instance if stored into the animation options.
	 * 
	 * @param property property instance used to get for animation options
	 * @return an animation property instance or <code>null</code> if does not exists
	 */
	public AnimationProperty getProperty(IsAnimationPropertyKey property) {
		return mode.getProperty(property);
	}

	/**
	 * Removes an animation property previously added.
	 * 
	 * @param property property instance used to remove from animation options
	 */
	public void removeProperty(IsAnimationPropertyKey property) {
		mode.removeProperty(property);
	}

	/**
	 * Sets an animation collection instance to animation options.
	 * 
	 * @param animationElement animation collection instance to add
	 */
	public void setCollection(AnimationCollection animationElement) {
		mode.setCollection(animationElement);
	}

	/**
	 * Enables or disables an animation collection instance into animation options.
	 * 
	 * @param collection collection instance used to check into animation options
	 * @param enabled if <code>true</code> it enables an animation collection
	 */
	public void setCollectionEnabled(IsAnimationCollectionKey collection, boolean enabled) {
		mode.setCollectionEnabled(collection, enabled);
	}

	/**
	 * Returns <code>true</code> if the animation collection is enabled, otherwise <code>false</code>.
	 * 
	 * @param collection collection instance used to check into animation options
	 * @return <code>true</code> if the animation collection is enabled, otherwise <code>false</code>
	 */
	public boolean isCollectionEnabled(IsAnimationCollectionKey collection) {
		return mode.isCollectionEnabled(collection);
	}

	/**
	 * Returns <code>true</code> if an animation collection instance is stored into the animation options.
	 * 
	 * @param collection collection instance used to check into animation options
	 * @return <code>true</code> if an animation collection instance is stored into the animation options
	 */
	public boolean hasCollection(IsAnimationCollectionKey collection) {
		return mode.hasCollection(collection);
	}

	/**
	 * Returns an animation collection instance if stored into the animation options.
	 * 
	 * @param collection collection instance used to get for animation options
	 * @return an animation collection instance or <code>null</code> if does not exists
	 */
	public AnimationCollection getCollection(IsAnimationCollectionKey collection) {
		return mode.getCollection(collection);
	}

	/**
	 * Removes an animation collection previously added.
	 * 
	 * @param collection collection instance used to remove from animation options
	 */
	public void removeCollection(IsAnimationCollectionKey collection) {
		mode.removeCollection(collection);
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