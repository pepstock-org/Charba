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

import java.util.LinkedList;
import java.util.List;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.defaults.IsDefaultScale;
import org.pepstock.charba.client.defaults.IsDefaultScales;
import org.pepstock.charba.client.enums.AxisType;
import org.pepstock.charba.client.enums.CartesianAxisType;
import org.pepstock.charba.client.enums.DefaultScaleId;

/**
 * The configuration element which contains all axes definitions.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Scales extends AbstractModel<Options, IsDefaultScales> implements IsDefaultScales {
	
	/**
	 * Creates a scales object by defaults values. This method is creating the root element of scales.
	 * 
	 * @param defaultValues default values of scales
	 */
	public Scales(IsDefaultScales defaultValues) {
		this(null, null, defaultValues, null);
	}

	/**
	 * Creates the scales object as child of the option object.
	 * 
	 * @param options options of the chart.
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	Scales(Options options, Key childKey, IsDefaultScales defaultValues, NativeObject nativeObject) {
		super(options, childKey, defaultValues, nativeObject);
		// redefines hashcode in order do not have 
		// the property $H for hashcode
		super.redefineHashcode();
	}

	/**
	 * Sets all axes of chart.
	 * 
	 * @param scales array of axes.
	 */
	public void setAxes(Scale... scales) {
		// clears the object from previous scales
		if (!empty()) {
			// removes all keys
			for (Key key : keys()) {
				// checks if the property is related to an object
				// otherwise is not a scale
				if (ObjectType.OBJECT.equals(type(key))) {
					// remove key
					remove(key);
				}
			}
		}
		// checks if the arguments are consistent
		if (scales != null && scales.length > 0) {
			// scans passed scales
			for (Scale scale : scales) {
				// checks if not null
				if (scale != null) {
					// gets id as key
					IsScaleId id = scale.getId();
					// checks if scale id of scale is consistent
					// used for cartesian, it must not be set to unknown
					if (DefaultScaleId.UNKNOWN.is(id)) {
						// get the axis type and default id
						AxisType type = scale.getType();
						// checks if type is consistent 
						// it MUST
						Key.checkIfValid(type);
						// stores default id as id
						id = type.getDefaultScaleId();
						// override the id
						scale.setId(id);
					}
					// checks if the id is already into object
					// that means there are 2 or more scales to add
					// with the same id
					if (has(id)) {
						throw new IllegalArgumentException("A scale with id " + id.value() + " has been already added");
					}
					// stores scale
					setValue(id, scale);
				}
			}
			// checks if all parents are attached
			checkAndAddToParent();
		}
	}

	/**
	 * Returns <code>true</code> if the scale with the id passed as argument exists.
	 * 
	 * @param scaleId scale id to check
	 * @return <code>true</code> if the scale with the id passed as argument exists
	 */
	public boolean hasAxis(String scaleId) {
		return hasAxis(IsScaleId.create(scaleId));
	}

	/**
	 * Returns <code>true</code> if the scale with the id passed as argument exists.
	 * 
	 * @param scaleId scale id to check
	 * @return <code>true</code> if the scale with the id passed as argument exists
	 */
	public boolean hasAxis(IsScaleId scaleId) {
		// checks if the scale id is consistent
		ScaleIdChecker.check(scaleId);
		// checks if the scale id exist
		return has(scaleId);
	}

	/**
	 * Returns the scale with the id passed as argument or <code>null</code> if not exist.
	 * 
	 * @param scaleId scale id to check
	 * @return the scale with the id passed as argument or <code>null</code> if not exist
	 */
	public Scale getAxis(String scaleId) {
		return getAxis(IsScaleId.create(scaleId));
	}

	/**
	 * Returns the scale with the id passed as argument or <code>null</code> if not exist.
	 * 
	 * @param scaleId scale id to check
	 * @return the scale with the id passed as argument or <code>null</code> if not exist
	 */
	public Scale getAxis(IsScaleId scaleId) {
		// checks if the scale id is consistent
		ScaleIdChecker.check(scaleId);
		// checks if the scale id exist
		if (has(scaleId)) {
			// gets and creates the scale
			return getAndCreate(scaleId);
		}
		// if here, the scale id does not exist
		// then returns null
		return null;
	}

	/**
	 * Returns a list of X axes.
	 * 
	 * @return a list of X axes.
	 */
	public List<Scale> getAxes() {
		// creates the result
		List<Scale> result = new LinkedList<>();
		// gets all keys
		for (Key key : keys()) {
			// gets and creates the scale
			result.add(getAndCreate(key));
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScales#getXAxis()
	 */
	@Override
	public IsDefaultScale getXAxis() {
		return getDefaultValues().getXAxis();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScales#getYAxis()
	 */
	@Override
	public IsDefaultScale getYAxis() {
		return getDefaultValues().getYAxis();
	}

	/**
	 * Gets from the native object the scale object by its id and creates a scale instance.
	 * 
	 * @param scaleId scale id
	 * @return a scale instance
	 */
	private Scale getAndCreate(Key scaleId) {
		// gets object 
		NativeObject object = getValue(scaleId);
		// gets temporary scale wrapper
		InternalTemporaryScaleWrapper internalScale = new InternalTemporaryScaleWrapper(object);
		// create default scale reference
		IsDefaultScale defaultValue = null;
		// checks if is a cartesian axis
		if (!AxisType.RADIAL_LINEAR.equals(internalScale.getType())){
			// gets cartesian type by scale id
			CartesianAxisType type = CartesianAxisType.getByScaleId(scaleId);
			// gets default value based on cartesian type of the object
			defaultValue = CartesianAxisType.X.equals(type) ? getXAxis() : getYAxis();
		} else {
			// gets default value from global axis
			defaultValue = Defaults.get().getScale(AxisType.RADIAL_LINEAR);
		}
		// creates the scale
		Scale scale = new Scale(defaultValue, object);
		// checks if scale has got the id
		if (DefaultScaleId.UNKNOWN.is(scale.getId())) {
			// sets id
			scale.setId(IsScaleId.create(scaleId.value()));
		}
		// returns scale
		return scale;
	}
	
	/**
	 * Temporary wrapper of a scale object, read as native object.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class InternalTemporaryScaleWrapper extends NativeObjectContainer{
		
		/**
		 * Creates the object with native object instance to be wrapped.
		 * 
		 * @param nativeObject native object instance to be wrapped.
		 */
		private InternalTemporaryScaleWrapper(NativeObject nativeObject) {
			super(nativeObject);
		}
		
		/**
		 * Returns the type of axis.
		 * 
		 * @return the type of axis.
		 */
		private AxisType getType() {
			// checks if has got the axis type
			if (has(Scale.Property.TYPE)) {
				return getValue(Scale.Property.TYPE, AxisType.values(), AxisType.LINEAR);
			}
			// if here, there is not axis type
			// then exception
			throw new IllegalArgumentException("The axis type is not stored into scale native object.");
		}

	}
}