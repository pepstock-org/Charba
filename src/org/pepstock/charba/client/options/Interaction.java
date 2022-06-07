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
import org.pepstock.charba.client.defaults.IsDefaultInteraction;
import org.pepstock.charba.client.enums.InteractionAxis;
import org.pepstock.charba.client.enums.InteractionMode;

/**
 * Definitions about how the user can interact with chart elements.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class Interaction extends AbstractInteraction<Options, IsDefaultInteraction> implements IsDefaultInteraction {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		AXIS("axis"),
		INCLUDE_INVISIBLE("includeInvisible");

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

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param options options of the chart.
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	Interaction(Options options, Key childKey, IsDefaultInteraction defaultValues, NativeObject nativeObject) {
		super(options, childKey, defaultValues, nativeObject);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.AbstractHover#getDefaultMode()
	 */
	@Override
	final InteractionMode getDefaultMode() {
		return getDefaultValues().getMode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.AbstractHover#isDefaultIntersect()
	 */
	@Override
	final boolean isDefaultIntersect() {
		return getDefaultValues().isIntersect();
	}

	/**
	 * Sets which directions are used in calculating distances.
	 * 
	 * @param axis define which directions are used in calculating distances.
	 */
	public final void setAxis(InteractionAxis axis) {
		setValueAndAddToParent(Property.AXIS, axis);
	}

	/**
	 * Returns which directions are used in calculating distances.
	 * 
	 * @return define which directions are used in calculating distances.
	 */
	@Override
	public final InteractionAxis getAxis() {
		return getValue(Property.AXIS, InteractionAxis.values(), getDefaultValues().getAxis());
	}

	/**
	 * If true, the invisible points that are outside of the chart area will also be included when evaluating interactions.
	 * 
	 * @param includeInvisible if true, the invisible points that are outside of the chart area will also be included when evaluating interactions.
	 */
	public final void setIncludeInvisible(boolean includeInvisible) {
		setValueAndAddToParent(Property.INCLUDE_INVISIBLE, includeInvisible);
	}

	/**
	 * If true, the invisible points that are outside of the chart area will also be included when evaluating interactions.
	 * 
	 * @return if true, the invisible points that are outside of the chart area will also be included when evaluating interactions.
	 */
	@Override
	public final boolean isIncludeInvisible() {
		return getValue(Property.INCLUDE_INVISIBLE, getDefaultValues().isIncludeInvisible());
	}

}