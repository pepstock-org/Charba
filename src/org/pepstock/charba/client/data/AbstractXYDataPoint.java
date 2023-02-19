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
package org.pepstock.charba.client.data;

import java.util.Date;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.enums.DataPointType;
import org.pepstock.charba.client.items.Undefined;

/**
 * Maps the common methods for a data point with X and Y properties.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public abstract class AbstractXYDataPoint extends AbstractDataPoint {

	// default value for X. No private because it is used by time series item
	static final double DEFAULT_X = Undefined.DOUBLE;
	// default value for Y. No private because it is used by time series item
	static final double DEFAULT_Y = Undefined.DOUBLE;

	/**
	 * Name of properties of native object.<br>
	 * No private because it is used by time series item
	 */
	protected enum XYProperty implements Key
	{
		X("x"),
		Y("y");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use in the native object.
		 * 
		 * @param value value of property name
		 */
		private XYProperty(String value) {
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
	 * Creates the object with a native object passed as argument.
	 * 
	 * @param nativeObject native object which maps a data point
	 */
	protected AbstractXYDataPoint(NativeObject nativeObject) {
		super(nativeObject);
	}

	// -------------
	// X
	// -------------

	/**
	 * Returns the object type of data stored as X.
	 * 
	 * @return the object type of data stored as X
	 */
	public final DataPointType getXType() {
		return getValue(CharbaProperty.CHARBA_X_TYPE, DataPointType.values(), DataPointType.UNKNOWN);
	}

	/**
	 * Sets X value as double (for scatter and bubble datasets).
	 * 
	 * @param x X value as double
	 */
	public final void setX(double x) {
		setValue(XYProperty.X, x);
		// sets type
		checkAndSetType(XYProperty.X, CharbaProperty.CHARBA_X_TYPE, DataPointType.NUMBER);
	}

	/**
	 * Returns X value as double (for scatter and bubble datasets).
	 * 
	 * @return X value as double
	 */
	public final double getX() {
		// gets data point type
		DataPointType type = getXType();
		// checks if the stored data is a number
		if (DataPointType.NUMBER.equals(type) || DataPointType.DATE.equals(type)) {
			return getValue(XYProperty.X, DEFAULT_X);
		}
		// if here the data is missing or a string
		// then returns the default
		return DEFAULT_X;
	}

	/**
	 * Sets X value as date for time series.
	 * 
	 * @param x X value as date for time series
	 */
	public final void setX(Date x) {
		setValue(XYProperty.X, x);
		// sets type
		checkAndSetType(XYProperty.X, CharbaProperty.CHARBA_X_TYPE, DataPointType.DATE);
	}

	/**
	 * Returns X value as date for time series.
	 * 
	 * @return X value as date for time series or <code>null</code> if is not set
	 */
	public final Date getXAsDate() {
		// checks if the stored data is a number
		if (DataPointType.DATE.equals(getXType())) {
			return getValue(XYProperty.X, (Date) null);
		}
		// if here the data is missing or a string
		// then returns the default
		return null;
	}

	/**
	 * Sets X value as string.
	 * 
	 * @param x X value as string
	 */
	public final void setX(String x) {
		setValue(XYProperty.X, x);
		// sets type
		checkAndSetType(XYProperty.X, CharbaProperty.CHARBA_X_TYPE, DataPointType.STRING);
	}

	/**
	 * Returns X value as string.
	 * 
	 * @return X value as string or {@link Undefined#STRING} if is not set
	 */
	public final String getXAsString() {
		// checks if the stored data is a string
		if (DataPointType.STRING.equals(getXType())) {
			return getValue(XYProperty.X, Undefined.STRING);
		}
		// if here the data is missing or a number
		// then returns the default
		return Undefined.STRING;
	}

	// -------------
	// Y
	// -------------

	/**
	 * Returns the object type of data stored as Y.
	 * 
	 * @return the object type of data stored as Y
	 */
	public final DataPointType getYType() {
		return getValue(CharbaProperty.CHARBA_Y_TYPE, DataPointType.values(), DataPointType.UNKNOWN);
	}

	/**
	 * Sets Y value.
	 * 
	 * @param y Y value.
	 */
	public final void setY(double y) {
		setValue(XYProperty.Y, y);
		// sets type
		checkAndSetType(XYProperty.Y, CharbaProperty.CHARBA_Y_TYPE, DataPointType.NUMBER);
	}

	/**
	 * Returns Y value.
	 * 
	 * @return Y value.
	 */
	public final double getY() {
		// checks if the stored data is a number
		if (DataPointType.NUMBER.equals(getYType())) {
			return getValue(XYProperty.Y, DEFAULT_Y);
		}
		// if here the data is missing or an array
		// then returns the default
		return DEFAULT_Y;
	}

}