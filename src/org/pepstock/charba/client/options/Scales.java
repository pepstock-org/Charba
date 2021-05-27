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

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.IsDefaultScale;
import org.pepstock.charba.client.defaults.IsDefaultScales;
import org.pepstock.charba.client.defaults.globals.DefaultsBuilder;
import org.pepstock.charba.client.enums.AxisKind;
import org.pepstock.charba.client.enums.DefaultScaleId;

/**
 * The configuration element which contains all axes definitions.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class Scales extends AbstractModel<Options, IsDefaultScales> implements IsDefaultScales {

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
	}

	/**
	 * Returns <code>true</code> if the scale with the id passed as argument exists.
	 * 
	 * @param scaleId scale id to check
	 * @return <code>true</code> if the scale with the id passed as argument exists
	 */
	public boolean hasAxis(String scaleId) {
		return hasAxis(ScaleId.create(scaleId));
	}

	/**
	 * Returns <code>true</code> if the scale with the id passed as argument exists.
	 * 
	 * @param scaleId scale id to check
	 * @return <code>true</code> if the scale with the id passed as argument exists
	 */
	public boolean hasAxis(ScaleId scaleId) {
		// checks if the scale id is consistent
		ScaleId.checkIfValid(scaleId);
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
		return getAxis(ScaleId.create(scaleId));
	}

	/**
	 * Returns the scale with the id passed as argument or <code>null</code> if not exist.
	 * 
	 * @param scaleId scale id to check
	 * @return the scale with the id passed as argument or <code>null</code> if not exist
	 */
	public Scale getAxis(ScaleId scaleId) {
		// checks if the scale id is consistent
		ScaleId.checkIfValid(scaleId);
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
	 * @see org.pepstock.charba.client.defaults.IsDefaultScales#getAxis(org.pepstock.charba.client.options.ScaleId, org.pepstock.charba.client.enums.AxisKind)
	 */
	@Override
	public IsDefaultScale getAxis(ScaleId scaleId, AxisKind kind) {
		return getDefaultValues().getAxis(scaleId, kind);
	}

	/**
	 * Gets from the native object the scale object by its id and creates a scale instance.
	 * 
	 * @param propertyKey property key of the object as scale id
	 * @return a scale instance
	 */
	private Scale getAndCreate(Key propertyKey) {
		// creates scale id
		ScaleId scaleId = ScaleId.create(propertyKey.value());
		// gets native object
		NativeObject nativeObject = getValue(propertyKey);
		// gets temporary scale
		Scale internalScale = new Scale(DefaultsBuilder.get().getScale(), nativeObject);
		// create default scale reference
		IsDefaultScale defaultValue = getAxis(scaleId, internalScale.getAxis());
		// creates the scale
		Scale scale = new Scale(defaultValue, nativeObject);
		// checks if scale has got the id
		if (DefaultScaleId.UNKNOWN.is(scale.getId())) {
			// sets id
			scale.setId(ScaleId.create(propertyKey.value()));
		}
		// returns scale
		return scale;
	}

}