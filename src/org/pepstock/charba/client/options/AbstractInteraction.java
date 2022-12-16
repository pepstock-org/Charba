/**
    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.
*/
package org.pepstock.charba.client.options;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.enums.InteractionMode;

/**
 * Definitions about how the user can interact with chart elements.
 * 
 * @author Andrea "Stock" Stocchero
 * 
 * @param <P> parent model class
 * @param <D> defaults provider class
 */
abstract class AbstractInteraction<P extends AbstractModel<?, ?>, D> extends AbstractModel<P, D> {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
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

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param options parent options of the chart.
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	AbstractInteraction(P options, Key childKey, D defaultValues, NativeObject nativeObject) {
		super(options, childKey, defaultValues, nativeObject);
	}

	/**
	 * Returns which elements appear in the interaction.
	 * 
	 * @return which elements appear in the interaction.
	 */
	abstract InteractionMode getDefaultMode();

	/**
	 * if true, the mode only applies when the mouse position intersects an item on the chart.
	 * 
	 * @return if true, the mode only applies when the mouse position intersects an item on the chart.
	 */
	abstract boolean isDefaultIntersect();

	/**
	 * Sets which elements appear in the interaction.
	 * 
	 * @param mode which elements appear in the interaction.
	 */
	public final void setMode(InteractionMode mode) {
		setValueAndAddToParent(Property.MODE, mode);
	}

	/**
	 * Returns which elements appear in the interaction.
	 * 
	 * @return which elements appear in the interaction.
	 */
	public final InteractionMode getMode() {
		return getValue(Property.MODE, InteractionMode.values(), getDefaultMode());
	}

	/**
	 * if <code>true</code>, the only applies when the mouse position intersects an item on the chart.
	 * 
	 * @param intersect if <code>true</code>, the mode only applies when the mouse position intersects an item on the chart.
	 */
	public final void setIntersect(boolean intersect) {
		setValueAndAddToParent(Property.INTERSECT, intersect);
	}

	/**
	 * if <code>true</code>, the mode only applies when the mouse position intersects an item on the chart.
	 * 
	 * @return if <code>true</code>, the mode only applies when the mouse position intersects an item on the chart.
	 */
	public final boolean isIntersect() {
		return getValue(Property.INTERSECT, isDefaultIntersect());
	}

}