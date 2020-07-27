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
import org.pepstock.charba.client.defaults.IsDefaultBaseAnimation;
import org.pepstock.charba.client.enums.Easing;

/**
 * Is the base animation options with common properties for every animation configuration.
 * 
 * @author Andrea "Stock" Stocchero
 * @param <T> type of key
 * @param <D> type of default values
 * 
 */
abstract class AbstractAnimation<T extends Key, D extends IsDefaultBaseAnimation> extends AbstractNode implements IsDefaultBaseAnimation {

	/**
	 * Name of properties of native object.
	 */
	enum Property implements Key
	{
		DURATION("duration"),
		EASING("easing"),
		DEBUG("debug"),
		DELAY("delay"),
		LOOP("loop");

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

	// key value
	private final T key;
	// default values
	private final D defaultValues;

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param parent parent of the animation options.
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	AbstractAnimation(AbstractNode parent, T childKey, D defaultValues, NativeObject nativeObject) {
		super(parent, childKey, nativeObject);
		// redefines hashcode in order do not have
		// the property $H for hashcode
		super.redefineHashcode();
		// stores the key locally to maintain the type
		this.key = Key.checkAndGetIfValid(childKey);
		// checks if default value is consistent
		if (defaultValues == null) {
			// if not, exception
			throw new IllegalArgumentException("Default values argument is null");
		}
		// stores defaults values
		this.defaultValues = defaultValues;
	}

	/**
	 * Returns the key used for animation element.
	 * 
	 * @return the key used for animation element
	 */
	public final T getKey() {
		return key;
	}

	/**
	 * Returns the default values.
	 * 
	 * @return the default values
	 */
	protected final D getDefaultValues() {
		return defaultValues;
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
	@Override
	public Easing getEasing() {
		return getValue(Property.EASING, Easing.values(), defaultValues.getEasing());
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
	@Override
	public int getDuration() {
		return getValue(Property.DURATION, defaultValues.getDuration());
	}

	/**
	 * Sets <code>true</code> if running animation count plus FPS display in upper left corner of the chart.
	 * 
	 * @param debug <code>true</code> if running animation count plus FPS display in upper left corner of the chart
	 */
	public void setDebug(boolean debug) {
		setValue(Property.DEBUG, debug);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns <code>true</code> if running animation count plus FPS display in upper left corner of the chart.
	 * 
	 * @return <code>true</code> if running animation count plus FPS display in upper left corner of the chart
	 */
	@Override
	public boolean isDebug() {
		return getValue(Property.DEBUG, defaultValues.isDebug());
	}

	/**
	 * Sets the delay before starting the animations.
	 * 
	 * @param delay the delay before starting the animations
	 */
	public void setDelay(int delay) {
		setValue(Property.DELAY, delay);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the delay before starting the animations.
	 * 
	 * @return the delay before starting the animations
	 */
	@Override
	public int getDelay() {
		return getValue(Property.DELAY, defaultValues.getDelay());
	}

	/**
	 * If set to <code>true</code>, loops the animations endlessly.
	 * 
	 * @param loop <code>true</code> if loops the animations endlessly.
	 */
	public void setLoop(boolean loop) {
		setValue(Property.LOOP, loop);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * If set to <code>true</code>, loops the animations endlessly.
	 * 
	 * @return <code>true</code> if loops the animations endlessly.
	 */
	@Override
	public boolean isLoop() {
		return getValue(Property.LOOP, defaultValues.isLoop());
	}

	/**
	 * Returns the native object instance.
	 * 
	 * @return the native object instance.
	 */
	final NativeObject nativeObject() {
		return super.getNativeObject();
	}

}