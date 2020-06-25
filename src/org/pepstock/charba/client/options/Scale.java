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

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.IsDefaultScale;
import org.pepstock.charba.client.enums.AxisKind;
import org.pepstock.charba.client.enums.AxisType;
import org.pepstock.charba.client.enums.DefaultScaleId;

/**
 * Scales are an integral part of a chart.<br>
 * They are used to determine how data maps to a pixel value on the chart.<br>
 * <ul>
 * <li>linear
 * <li>logarithmic
 * <li>category
 * <li>time
 * <li>radial linear
 * </ul>
 * <br>
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class Scale extends AbstractScale {

	/**
	 * Name of properties of native object.
	 */
	enum Property implements Key
	{
		TYPE("type"),
		ID("id"),
		AXIS("axis");

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
	 * Creates the object only with default provider and type.<br>
	 * This is used when the scale is the root element.<br>
	 * New native java script object is created and it's empty.
	 * 
	 * @param type scale type
	 * @param defaultValues default provider instance.
	 */
	Scale(AxisType type, IsDefaultScale defaultValues) {
		// no parent, child key and native object
		this(defaultValues, null);
		// checks axis type
		Key.checkIfValid(type);
		// sets the type
		setType(type);
	}

	/**
	 * Creates the object only with default provider and native object.<br>
	 * This is used when the scale is read by {@link Scales}.
	 * 
	 * @param defaultValues default provider instance.
	 * @param nativeObject native object to store properties.
	 */
	protected Scale(IsDefaultScale defaultValues, NativeObject nativeObject) {
		super(defaultValues, nativeObject);
	}

	/**
	 * Creates the object only with aixs type, default provider and native object.<br>
	 * This is used when the default scale is read by {@link Defaults#getScale(AxisType)}.
	 * 
	 * @param type scale type
	 * @param defaultValues default provider instance.
	 * @param nativeObject native object to store properties.
	 */
	protected Scale(AxisType type, IsDefaultScale defaultValues, NativeObject nativeObject) {
		super(defaultValues, nativeObject);
		// checks axis type
		Key.checkIfValid(type);
		// sets the type
		setType(type);
	}

	/**
	 * Sets the id of scale.<br>
	 * It is usually used to link datasets and scale axes together.<br>
	 * This is especially needed if multi-axes charts are used.
	 * 
	 * @param id The ID is used to link datasets and scale axes together
	 */
	final void setId(IsScaleId id) {
		// checks if key is consistent
		ScaleIdChecker.check(id);
		// if the scale id is UNKNWON (set by Charba)
		// does not store
		// this is for radial axis where id must miss
		if (!DefaultScaleId.UNKNOWN.is(id.value())) {
			// stores id
			setValue(Property.ID, id);
			// checks if all parents are attached
			checkAndAddToParent();
		}
	}

	/**
	 * Returns the id of scale.<br>
	 * It is usually used to link datasets and scale axes together.<br>
	 * This is especially needed if multi-axes charts are used.
	 * 
	 * @return The ID is used to link datasets and scale axes together or {@link DefaultScaleId#UNKNOWN} if not set
	 */
	public final IsScaleId getId() {
		return getValue(Property.ID, DefaultScaleId.UNKNOWN);
	}

	/**
	 * Type of scale being employed.
	 * 
	 * @param type type of axis
	 */
	private void setType(AxisType type) {
		setValue(Property.TYPE, type);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the type of axis.
	 * 
	 * @return the type of axis.
	 */
	public final AxisType getType() {
		// checks if there is the type
		if (!has(Property.TYPE)) {
			// exception
			throw new IllegalArgumentException("The scale does not contain the type");
		}
		// gets axis type
		// uses linear as default even if it is useless
		return getValue(Property.TYPE, AxisType.values(), AxisType.LINEAR);
	}

	/**
	 * Which kind of axis this is.<br>
	 * Possible values are: 'x', 'y' or 'r'.
	 * 
	 * @param kind kind of axis
	 */
	final void setAxis(AxisKind kind) {
		// checks if axis type is radial
		// gets axis type
		AxisType type = getType();
		// creates the reference of kind to store
		AxisKind typeToStore = null;
		// checks if axis type is radial
		if (AxisType.RADIAL_LINEAR.equals(type)) {
			// always R
			typeToStore = AxisKind.R;
		} else if (!AxisKind.R.equals(kind)) {
			// sets to the argument
			typeToStore = kind;
		}
		setValue(Property.AXIS, typeToStore);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Which kind of axis this is.<br>
	 * Possible values are: ''x', 'y' or 'r'.
	 * 
	 * @return the kind of axis.
	 */
	public final AxisKind getAxis() {
		// checks if there is the property
		if (has(Property.AXIS)) {
			// returns the property
			return getValue(Property.AXIS, AxisKind.values(), getType().getDefaultScaleId().getAxisKind());
		}
		// if here, there is not axis
		// then checks and gets from id and axis type
		return checkAndGetDefaultAxis();
	}

	/**
	 * Returns the axis kind checking the scale id and the axis type defaults.
	 * 
	 * @return the axis kind checking the scale and the axis type defaults
	 */
	private AxisKind checkAndGetDefaultAxis() {
		// gets axis type
		AxisType type = getType();
		// checks if axis type is radial
		if (AxisType.RADIAL_LINEAR.equals(type)) {
			// always R
			return AxisKind.R;
		}
		// gets scale id
		IsScaleId id = getId();
		// checks if the id is consistent
		if (DefaultScaleId.UNKNOWN.equals(id)) {
			// if not, returns the default axis kind for axis type
			return type.getDefaultScaleId().getAxisKind();
		}
		// then returns base on scale id
		return DefaultScaleId.getAxisKindByScaleId(getId(), getType().getDefaultScaleId().getAxisKind());
	}

}