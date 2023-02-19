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
package org.pepstock.charba.client.callbacks;

import org.pepstock.charba.client.ScaleType;
import org.pepstock.charba.client.commons.IsPoint;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.items.Undefined;

/**
 * Represents the parsed data values for the given data index and dataset index.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ParsedData extends NativeObjectContainer implements IsPoint {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		X("x"),
		Y("y"),
		R("r"),
		VALUE("v");

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
	 * Creates the object with native object instance to be wrapped.
	 * 
	 * @param nativeObject native object instance to be wrapped.
	 */
	ParsedData(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Creates the object with value (this is for charts without scales).
	 * 
	 * @param value value to set in the object.
	 */
	ParsedData(double value) {
		super();
		// stores value
		setValue(Property.VALUE, value);
	}

	/**
	 * Returns X value as double (for {@link ScaleType#MULTI}).
	 * 
	 * @return X value as double (for {@link ScaleType#MULTI})
	 */
	@Override
	public double getX() {
		return getValue(Property.X, Undefined.DOUBLE);
	}

	/**
	 * Returns Y value as double (for {@link ScaleType#MULTI}).
	 * 
	 * @return Y value as double (for {@link ScaleType#MULTI})
	 */
	@Override
	public double getY() {
		return getValue(Property.Y, Undefined.DOUBLE);
	}

	/**
	 * Returns value as double (for {@link ScaleType#SINGLE}).
	 * 
	 * @return value as double (for {@link ScaleType#SINGLE})
	 */
	public double getR() {
		return getValue(Property.R, Undefined.DOUBLE);
	}

	/**
	 * Returns value as double (for {@link ScaleType#NONE}).
	 * 
	 * @return value as double (for {@link ScaleType#NONE})
	 */
	public double getValue() {
		return getValue(Property.VALUE, Undefined.DOUBLE);
	}

	/**
	 * Returns the type of data are stored in the parsed data.
	 * 
	 * @return the type of data are stored in the parsed data
	 */
	public ScaleType getType() {
		// checks if value is set
		if (isType(Property.VALUE, ObjectType.NUMBER)) {
			// no scale, single value
			return ScaleType.NONE;
		} else if (isType(Property.R, ObjectType.NUMBER)) {
			// single scale, single value at R position
			return ScaleType.SINGLE;
		} else if (isType(Property.X, ObjectType.NUMBER) && isType(Property.Y, ObjectType.NUMBER)) {
			// multi scales, X and Y are set
			return ScaleType.MULTI;
		}
		// if here, parsed object is not readable
		// it could be customized by the controller
		// therefor returns null
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// gets type
		ScaleType type = getType();
		// checks the type of scale
		if (ScaleType.MULTI.equals(type)) {
			// MULTI SCALE
			return "ParsedData [x=" + getX() + ", y=" + getY() + "]";
		} else if (ScaleType.MULTI.equals(type)) {
			// SINGLE SCALE
			return "ParsedData [r=" + getR() + "]";
		} else if (ScaleType.MULTI.equals(type)) {
			// NONE SCALE
			return "ParsedData [" + getValue() + " ]";
		}
		return "ParsedData []";
	}

}
