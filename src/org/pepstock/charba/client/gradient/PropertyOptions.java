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
package org.pepstock.charba.client.gradient;

import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.data.Dataset;
import org.pepstock.charba.client.enums.AxisKind;

/**
 * Maps all the options which can be set for each configurable property (for instance backgroundColor, borderColor and so on).
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class PropertyOptions extends AbstractNode {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		AXIS("axis"),
		COLORS("colors");

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

	// colors instance
	private final Colors colors;

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param parent parent node to use to add this element where changed
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param nativeObject native object to map java script properties
	 */
	PropertyOptions(AbstractNode parent, Key childKey, NativeObject nativeObject) {
		super(parent, childKey, nativeObject);
		// loads inner element
		this.colors = new Colors(this, Property.COLORS, getValue(Property.COLORS));
	}

	/**
	 * Returns the property name of this configuration.
	 * 
	 * @return the property name of this configuration
	 */
	public Key getName() {
		return super.getChildKey();
	}

	/**
	 * Which kind of axis this is, related to the {@link Dataset}.
	 * 
	 * @param axis kind of axis
	 */
	public void setAxis(AxisKind axis) {
		setValueAndAddToParent(Property.AXIS, axis);
	}

	/**
	 * Which kind of axis this is, related to the {@link Dataset}.
	 * 
	 * @return the kind of axis.
	 */
	public AxisKind getAxis() {
		return getValue(Property.AXIS, AxisKind.values(), null);
	}

	/**
	 * Returns the colors defined for the property, to create the gradient.
	 *
	 * @return the colors defined for the property, to create the gradient
	 */
	public Colors getColors() {
		return colors;
	}

}
