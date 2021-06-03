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
package org.pepstock.charba.client.zoom;

import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.enums.ModifierKey;

/**
 * Base object to map wheel options for {@link ZoomPlugin#ID} plugin configuration.<br>
 * It represents the container for WHEEL options.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class Wheel extends AbstractNode implements IsDefaultWheel {

	/**
	 * Default enabled, <b>{@value DEFAULT_ENABLED}</b>.
	 */
	public static final boolean DEFAULT_ENABLED = false;

	/**
	 * Default speed, <b>{@value DEFAULT_SPEED}</b>.
	 */
	public static final double DEFAULT_SPEED = 0.1D;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		ENABLED("enabled"),
		SPEED("speed"),
		MODIFIER_KEY("modifierKey");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use in the native object.
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

	// default values instance
	private final IsDefaultWheel defaultOptions;

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param parent parent node to use to add this element where changed
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultOptions default wheel options to returns the default when required.
	 * @param nativeObject native object to map java script properties
	 */
	Wheel(AbstractNode parent, Key childKey, IsDefaultWheel defaultOptions, NativeObject nativeObject) {
		super(parent, childKey, nativeObject);
		// checks if defaults options is consistent
		// stores defaults options
		this.defaultOptions = checkDefaultValuesArgument(defaultOptions);
	}

	/**
	 * Sets <code>true</code> to enable element for wheel zooming.
	 * 
	 * @param enabled <code>true</code> to enable element for wheel zooming
	 */
	public void setEnabled(boolean enabled) {
		setValueAndAddToParent(Property.ENABLED, enabled);
	}

	/**
	 * Returns <code>true</code> to enable element for wheel zooming.
	 * 
	 * @return <code>true</code> to enable element for wheel zooming
	 */
	@Override
	public boolean isEnabled() {
		return getValue(Property.ENABLED, defaultOptions.isEnabled());
	}

	/**
	 * Sets the speed of element via mouse wheel (percentage of element on a wheel event).<br>
	 * Must be a value from 0 and 1.
	 * 
	 * @param speed the speed of element via mouse wheel
	 */
	public void setSpeed(double speed) {
		setValueAndAddToParent(Property.SPEED, Checker.checkAndGetIfBetween(speed, 0D, 1D, "Speed value"));
	}

	/**
	 * Returns the speed of element via mouse wheel (percentage of element on a wheel event).
	 * 
	 * @return the speed of element via mouse wheel
	 */
	@Override
	public double getSpeed() {
		return getValue(Property.SPEED, defaultOptions.getSpeed());
	}

	/**
	 * Sets the modifier key to activate zooming by wheeling.
	 * 
	 * @param modifierKey the modifier key to activate zooming by wheeling
	 */
	public void setModifierKey(ModifierKey modifierKey) {
		setValueAndAddToParent(Property.MODIFIER_KEY, modifierKey);
	}

	/**
	 * Returns the modifier key to activate zooming by wheeling.
	 * 
	 * @return the modifier key to activate zooming by wheeling
	 */
	@Override
	public ModifierKey getModifierKey() {
		return getValue(Property.MODIFIER_KEY, ModifierKey.values(), defaultOptions.getModifierKey());
	}

}