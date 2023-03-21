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
package org.pepstock.charba.client.geo;

import java.util.List;

import org.pepstock.charba.client.commons.ArrayDouble;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.items.Undefined;

/**
 * The network of intersecting lines of latitude and longitude is called the graticule.<br>
 * It is imaginary on the earth, of course, but is drawn on globes and maps for reference.<br>
 * The graticule of latitude and longitude lines is an angular measurement system.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Graticule extends NativeObjectContainer {

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
	 * Sets the graticule lines distances in degrees.
	 * 
	 * @param start the start graticule line distance in degrees
	 * @param end the end graticule line distance in degrees
	 */
	public void setStepMajor(double start, double end) {
		setValues(Property.STEP_MAJOR, start, end);
	}

	/**
	 * Returns the graticule lines distances in degrees. Only 2 values are allowed.
	 * 
	 * @return the graticule lines distances in degrees
	 */
	public List<Double> getStepMajor() {
		return getValues(Property.STEP_MAJOR);
	}

	/**
	 * Sets the graticule lines distances in degrees. Only 2 values are allowed.
	 * 
	 * @param start the start graticule line distance in degrees
	 * @param end the end graticule line distance in degrees
	 */
	public void setStepMinor(double start, double end) {
		setValues(Property.STEP_MINOR, start, end);
	}

	/**
	 * Returns the graticule lines distances in degrees. Only 2 values are allowed.
	 * 
	 * @return the graticule lines distances in degrees
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
	 * Checks the argument and stores the values.
	 * 
	 * @param property property of java script object to use for storing values
	 * @param start the start graticule line distance in degrees
	 * @param end the end graticule line distance in degrees
	 */
	private void setValues(Property property, double start, double end) {
		// checks if argument is consistent
		if (Undefined.isNot(start) && Undefined.isNot(end)) {
			// stores
			setValueOrArray(property, start, end);
		} else {
			// if here the argument is not consistent
			// then removes the property
			remove(property);
		}
	}

	/**
	 * Returns the stored values as a list.
	 * 
	 * @param property property of java script object to use for getting values
	 * @return a list of stored values
	 */
	private List<Double> getValues(Property property) {
		ArrayDouble array = getArrayValue(property);
		return ArrayListHelper.list(array);
	}

}