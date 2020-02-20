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
import org.pepstock.charba.client.defaults.IsDefaultHover;
import org.pepstock.charba.client.enums.InteractionAxis;
import org.pepstock.charba.client.enums.InteractionMode;

/**
 * Definitions about how elements appear in the tooltip, hovering the chart.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Hover extends AbstractHover<IsDefaultHover> implements IsDefaultHover {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		AXIS("axis"),
		ANIMATION_DURATION("animationDuration");

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
	Hover(Options options, Key childKey, IsDefaultHover defaultValues, NativeObject nativeObject) {
		super(options, childKey, defaultValues, nativeObject);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.AbstractHover#getDefaultMode()
	 */
	@Override
	InteractionMode getDefaultMode() {
		return getDefaultValues().getMode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.AbstractHover#isDefaultIntersect()
	 */
	@Override
	boolean isDefaultIntersect() {
		return getDefaultValues().isIntersect();
	}

	/**
	 * Sets the duration in milliseconds it takes to animate hover style changes.
	 * 
	 * @param milliseconds duration in milliseconds it takes to animate hover style changes.
	 */
	public void setAnimationDuration(int milliseconds) {
		setValue(Property.ANIMATION_DURATION, milliseconds);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the duration in milliseconds it takes to animate hover style changes.
	 * 
	 * @return duration in milliseconds it takes to animate hover style changes.
	 */
	public int getAnimationDuration() {
		return getValue(Property.ANIMATION_DURATION, getDefaultValues().getAnimationDuration());
	}

	/**
	 * Sets to 'x', 'y', or 'xy' to define which directions are used in calculating distances.<br>
	 * Defaults to 'x' for index mode and 'xy' in dataset and nearest modes.
	 * 
	 * @param axis define which directions are used in calculating distances.
	 */
	public void setAxis(InteractionAxis axis) {
		setValue(Property.AXIS, axis);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns to 'x', 'y', or 'xy' to define which directions are used in calculating distances.
	 * 
	 * @return define which directions are used in calculating distances.
	 */
	public InteractionAxis getAxis() {
		return getValue(Property.AXIS, InteractionAxis.values(), getDefaultValues().getAxis());
	}

}