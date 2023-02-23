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
package org.pepstock.charba.client.items;

import org.pepstock.charba.client.commons.Envelop;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.defaults.IsDefaultInteraction;
import org.pepstock.charba.client.enums.DefaultInteractionMode;
import org.pepstock.charba.client.enums.InteractionAxis;
import org.pepstock.charba.client.enums.IsInteractionMode;
import org.pepstock.charba.client.interaction.InteractionEnvelop;
import org.pepstock.charba.client.interaction.Interactions;

/**
 * Definitions about how the interaction with events will be applied on chart elements.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class InteractionOptions extends NativeObjectContainer implements IsDefaultInteraction {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		AXIS("axis"),
		MODE("mode"),
		INCLUDE_INVISIBLE("includeInvisible"),
		INTERSECT("intersect");

		// name value of property
		private String value;

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
	 * Creates an interaction item with defaults.
	 */
	public InteractionOptions() {
		this(DefaultInteractionMode.NEAREST);
	}

	/**
	 * Creates an interaction item with passed mode and all other configuration as defaults.
	 * 
	 * @param mode how the event will be apply on elements
	 */
	public InteractionOptions(IsInteractionMode mode) {
		this(mode, true);
	}

	/**
	 * Creates an interaction item with passed mode and intersect and all other configuration as defaults.
	 * 
	 * @param mode how the event will be apply on elements
	 * @param intersect if <code>true</code>, the mode only applies when the mouse position intersects an element on the chart.
	 */
	public InteractionOptions(IsInteractionMode mode, boolean intersect) {
		this(mode, intersect, InteractionAxis.XY);
	}

	/**
	 * Creates an interaction item with all passed arguments.
	 * 
	 * @param mode how to interact with the elements on charts.
	 * @param intersect if <code>true</code>, the mode only applies when the mouse position intersects an element on the chart.
	 * @param axis which directions are used in calculating distances.
	 */
	public InteractionOptions(IsInteractionMode mode, boolean intersect, InteractionAxis axis) {
		super();
		setMode(mode);
		setIntersect(intersect);
		setAxis(axis);
	}

	/**
	 * Creates the object with envelop envelop with the native object instance to be wrapped.
	 * 
	 * @param envelop envelop with the native object instance to be wrapped.
	 */
	public InteractionOptions(InteractionEnvelop<NativeObject> envelop) {
		super(Envelop.checkAndGetIfValid(envelop).getContent());
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
		return getValue(Property.AXIS, InteractionAxis.values(), InteractionAxis.XY);
	}

	/**
	 * If true, the invisible points that are outside of the chart area will also be included when evaluating interactions.
	 * 
	 * @param includeInvisible if true, the invisible points that are outside of the chart area will also be included when evaluating interactions.
	 */
	public void setIncludeInvisible(boolean includeInvisible) {
		setValue(Property.INCLUDE_INVISIBLE, includeInvisible);
	}

	/**
	 * If true, the invisible points that are outside of the chart area will also be included when evaluating interactions.
	 * 
	 * @return if true, the invisible points that are outside of the chart area will also be included when evaluating interactions.
	 */
	@Override
	public boolean isIncludeInvisible() {
		return getValue(Property.INCLUDE_INVISIBLE, false);
	}

	/**
	 * Sets which elements appear in the interaction.
	 * 
	 * @param mode which elements appear in the interaction.
	 */
	public void setMode(IsInteractionMode mode) {
		setValue(Property.MODE, mode);
	}

	/**
	 * Sets which elements appear in the interaction.
	 * 
	 * @param name which elements appear in the interaction.
	 */
	public void setMode(String name) {
		// checks if exist
		if (Interactions.get().hasInteractionMode(name)) {
			// gets the interaction mode by name
			setMode(Interactions.get().getInteractionMode(name));
		}
	}

	/**
	 * Returns which elements appear in the interaction.
	 * 
	 * @return which elements appear in the interaction.
	 */
	@Override
	public IsInteractionMode getMode() {
		// gets mode
		String value = getValue(Property.MODE, DefaultInteractionMode.NEAREST.value());
		// checks if mode exists
		IsInteractionMode mode = Interactions.get().getInteractionMode(value);
		// checks if exists and then returns the mode
		return mode != null ? mode : DefaultInteractionMode.NEAREST;
	}

	/**
	 * if <code>true</code>, the mode only applies when the mouse position intersects an item on the chart.
	 * 
	 * @param intersect if <code>true</code>, the mode only applies when the mouse position intersects an item on the chart.
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
		return getValue(Property.INTERSECT, true);
	}

	/**
	 * Returns the native object instance.
	 * 
	 * @return the native object instance.
	 */
	public NativeObject nativeObject() {
		return super.getNativeObject();
	}
}