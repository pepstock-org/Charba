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
package org.pepstock.charba.client.geo;

import java.util.List;

import org.pepstock.charba.client.commons.ArrayDouble;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;

/**
 * FIXME
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Graticule extends NativeObjectContainer{
	
	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		STEP_MAJOR("stepMajor"),
		STEP_MINOR("stepMinor");

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
	 * Creates a graticule object.
	 */
	public Graticule() {
		this(null);
	}

	/**
	 * Creates the object with native object instance to be wrapped.
	 * 
	 * @param nativeObject native object instance to be wrapped.
	 */
	Graticule(NativeObject nativeObject) {
		super(nativeObject);
	}
	
	/**
	 * 
	 * @param steps
	 */
	public void setStepMajor(double... steps) {
		setValues(Property.STEP_MAJOR, steps);
	}

	/**
	 * 
	 * @param steps
	 */
	public List<Double> getStepMajor() {
		return getValues(Property.STEP_MAJOR);
	}
	
	/**
	 * 
	 * @param steps
	 */
	public void setStepMinor(double... steps) {
		setValues(Property.STEP_MINOR, steps);
	}

	/**
	 * 
	 * @param steps
	 */
	public List<Double> getStepMinor() {
		return getValues(Property.STEP_MINOR);
	}
	
	/**
	 * Returns the native object instance.
	 * 
	 * @return the native object instance.
	 */
	NativeObject nativeObject() {
		return getNativeObject();
	}

	/**
	 * 
	 * @param property
	 * @param steps
	 */
	private void setValues(Property property, double... steps) {
		// checks if argument is consistent
		if (steps != null && steps.length == 2) {
			// stores
			setValueOrArray(property, steps);
		} else {
			// if here the argument is not consistent
			// then removes the property
			remove(property);
		}
	}

	/**
	 * 
	 * @param property
	 * @return
	 */
	private List<Double> getValues(Property property) {
		ArrayDouble array = getArrayValue(property);
		return ArrayListHelper.list(array);
	}
	
}
