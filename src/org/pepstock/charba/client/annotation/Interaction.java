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
package org.pepstock.charba.client.annotation;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.enums.InteractionAxis;
import org.pepstock.charba.client.enums.InteractionMode;

/**
 * Definitions about how the user can interact with chart elements.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Interaction extends NativeObjectContainer implements IsDefaultsAnnotationInteraction {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		AXIS("axis"),
		MODE("mode"),
		INTERSECT("intersect");

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

	// defaults instance
	private final IsDefaultsAnnotationInteraction defaultValues;

	/**
	 * Creates the object with empty values.
	 * 
	 * @param defaultValues default provider
	 */
	Interaction(IsDefaultsAnnotationInteraction defaultValues) {
		this(defaultValues, null);
	}

	/**
	 * Creates the object with native object instance to be wrapped.
	 * 
	 * @param defaultValues default provider
	 * @param nativeObject native object instance to be wrapped.
	 */
	Interaction(IsDefaultsAnnotationInteraction defaultValues, NativeObject nativeObject) {
		super(nativeObject);
		// stores defaults
		this.defaultValues = checkDefaultValuesArgument(defaultValues);
	}

	/**
	 * Sets which the mode to engaged annotations on events.
	 * 
	 * @param mode which the mode to engaged annotations on events.
	 */
	public void setMode(InteractionMode mode) {
		setValue(Property.MODE, mode);
	}

	/**
	 * Returns which the mode to engaged annotations on events.
	 * 
	 * @return which the mode to engaged annotations on events.
	 */
	@Override
	public InteractionMode getMode() {
		return getValue(Property.MODE, InteractionMode.values(), defaultValues.getMode());
	}

	/**
	 * Sets which directions are used in calculating distances.
	 * 
	 * @param axis define which directions are used in calculating distances.
	 */
	public void setAxis(InteractionAxis axis) {
		setValue(Property.AXIS, axis);
	}

	/**
	 * Returns which directions are used in calculating distances.
	 * 
	 * @return define which directions are used in calculating distances.
	 */
	@Override
	public InteractionAxis getAxis() {
		return getValue(Property.AXIS, InteractionAxis.values(), defaultValues.getAxis());
	}

	/**
	 * if <code>true</code>, the only applies when the mouse position intersects an annotation.
	 * 
	 * @param intersect if <code>true</code>, the mode only applies when the mouse position intersects an annotation.
	 */
	public void setIntersect(boolean intersect) {
		setValue(Property.INTERSECT, intersect);
	}

	/**
	 * if <code>true</code>, the mode only applies when the mouse position intersects an item on the chart.
	 * 
	 * @return if <code>true</code>, the mode only applies when the mouse position intersects an item on the chart.
	 */
	@Override
	public boolean isIntersect() {
		return getValue(Property.INTERSECT, defaultValues.isIntersect());
	}

}