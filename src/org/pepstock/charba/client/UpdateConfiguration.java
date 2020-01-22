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

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.enums.Easing;

/**
 * Object can be provided with additional configuration for the update/render process.<br>
 * This is useful when update is manually called inside an event handler and some different animation is desired.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class UpdateConfiguration extends NativeObjectContainer {

	/**
	 * Default to enable the animation can be interrupted by other animations, <b>{@value DEFAULT_LAZY}</b>.
	 */
	public static final boolean DEFAULT_LAZY = false;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		DURATION("duration"),
		LAZY("lazy"),
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
	 * Sets the animation easing function.
	 * 
	 * @param easing animation easing function.
	 */
	public void setEasing(Easing easing) {
		setValue(Property.EASING, easing);
	}

	/**
	 * Returns the animation easing function.
	 * 
	 * @return the animation easing function.
	 */
	public Easing getEasing() {
		return getValue(Property.EASING, Easing.class, Defaults.get().getGlobal().getAnimation().getEasing());
	}

	/**
	 * Sets the time for the animation of the redraw in milliseconds.
	 * 
	 * @param milliseconds time for the animation of the redraw in milliseconds.
	 */
	public void setDuration(int milliseconds) {
		setValue(Property.DURATION, milliseconds);
	}

	/**
	 * Returns the time for the animation of the redraw in milliseconds.
	 * 
	 * @return time for the animation of the redraw in milliseconds.
	 */
	public int getDuration() {
		return getValue(Property.DURATION, Defaults.get().getGlobal().getAnimation().getDuration());
	}

	/**
	 * If <code>true</code>, the animation can be interrupted by other animations.
	 * 
	 * @param intersect if <code>true</code>, the animation can be interrupted by other animations.
	 */
	public void setLazy(boolean intersect) {
		setValue(Property.LAZY, intersect);
	}

	/**
	 * If <code>true</code>, the animation can be interrupted by other animations.
	 * 
	 * @return if <code>true</code>, the animation can be interrupted by other animations.
	 */
	public boolean isLazy() {
		return getValue(Property.LAZY, DEFAULT_LAZY);
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