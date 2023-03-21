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
package org.pepstock.charba.client.matrix;

import java.util.Date;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;
import org.pepstock.charba.client.data.AbstractXYDataPoint;
import org.pepstock.charba.client.enums.DataPointType;
import org.pepstock.charba.client.items.DataItem;
import org.pepstock.charba.client.items.Undefined;

/**
 * Maps the data passed to a matrix dataset.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class MatrixDataPoint extends AbstractXYDataPoint {

	/**
	 * Factory instance to create data points. To use by {@link DataItem} to get the data point for matrix chart.
	 */
	public static final MatrixDataPointFactory FACTORY = new MatrixDataPointFactory();

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		V("v");

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
	 * Creates the object with an empty native object.
	 */
	public MatrixDataPoint() {
		this((NativeObject) null);
	}

	/**
	 * Creates the object with the passed point values.
	 * 
	 * @param x x value of data point
	 * @param y y value of data point
	 * @param value value of the data point
	 */
	public MatrixDataPoint(double x, double y, double value) {
		this(value);
		// stores values
		setX(x);
		setY(y);
	}

	/**
	 * Creates the object with the passed point values.
	 * 
	 * @param x x value of data point
	 * @param y y value of data point
	 * @param value value of the data point
	 */
	public MatrixDataPoint(Date x, double y, double value) {
		this(value);
		// stores values
		setX(x);
		setY(y);
	}

	/**
	 * Creates the object with the passed point values.
	 * 
	 * @param x x value of data point
	 * @param y y value of data point
	 * @param value value of the data point
	 */
	public MatrixDataPoint(String x, double y, double value) {
		this(value);
		// stores values
		setX(x);
		setY(y);
	}

	/**
	 * Creates the object with the passed point values.
	 * 
	 * @param x x value of data point
	 * @param y y value of data point
	 * @param value value of the data point
	 */
	public MatrixDataPoint(double x, Date y, double value) {
		this(value);
		// stores values
		setX(x);
		setY(y);
	}

	/**
	 * Creates the object with the passed point values.
	 * 
	 * @param x x value of data point
	 * @param y y value of data point
	 * @param value value of the data point
	 */
	public MatrixDataPoint(Date x, Date y, double value) {
		this(value);
		// stores values
		setX(x);
		setY(y);
	}

	/**
	 * Creates the object with the passed point values.
	 * 
	 * @param x x value of data point
	 * @param y y value of data point
	 * @param value value of the data point
	 */
	public MatrixDataPoint(String x, Date y, double value) {
		this(value);
		// stores values
		setX(x);
		setY(y);
	}

	/**
	 * Creates the object with the passed point values.
	 * 
	 * @param x x value of data point
	 * @param y y value of data point
	 * @param value value of the data point
	 */
	public MatrixDataPoint(double x, String y, double value) {
		this(value);
		// stores values
		setX(x);
		setY(y);
	}

	/**
	 * Creates the object with the passed point values.
	 * 
	 * @param x x value of data point
	 * @param y y value of data point
	 * @param value value of the data point
	 */
	public MatrixDataPoint(Date x, String y, double value) {
		this(value);
		// stores values
		setX(x);
		setY(y);
	}

	/**
	 * Creates the object with the passed point values.
	 * 
	 * @param x x value of data point
	 * @param y y value of data point
	 * @param value value of the data point
	 */
	public MatrixDataPoint(String x, String y, double value) {
		this(value);
		// stores values
		setX(x);
		setY(y);
	}

	/**
	 * Creates the object with the passed point values.
	 * 
	 * @param value value of data point
	 */
	private MatrixDataPoint(double value) {
		this();
		setValue(value);
	}

	/**
	 * Creates the object with a native object passed as argument.
	 * 
	 * @param nativeObject native object which maps a data point
	 */
	MatrixDataPoint(NativeObject nativeObject) {
		super(nativeObject);
	}

	// -------------
	// Y
	// -------------

	/**
	 * Sets Y value as date for time series.
	 * 
	 * @param y Y value as date for time series
	 */
	public void setY(Date y) {
		setValue(XYProperty.Y, y);
		// sets type
		checkAndSetType(XYProperty.Y, CharbaProperty.CHARBA_Y_TYPE, DataPointType.DATE);
	}

	/**
	 * Returns Y value as date for time series.
	 * 
	 * @return Y value as date for time series
	 */
	public Date getYAsDate() {
		// checks if the stored data is a number
		if (DataPointType.DATE.equals(getYType())) {
			return getValue(XYProperty.Y, (Date) null);
		}
		// if here the data is missing or a string
		// then returns the default
		return null;
	}

	/**
	 * Sets Y value as string.
	 * 
	 * @param y Y value as string
	 */
	public void setY(String y) {
		setValue(XYProperty.Y, y);
		// sets type
		checkAndSetType(XYProperty.Y, CharbaProperty.CHARBA_Y_TYPE, DataPointType.STRING);
	}

	/**
	 * Returns Y value as string.
	 * 
	 * @return Y value as string
	 */
	public String getYAsString() {
		// checks if the stored data is a string
		if (DataPointType.STRING.equals(getYType())) {
			return getValue(XYProperty.Y, Undefined.STRING);
		}
		// if here the data is missing or a number
		// then returns the default
		return Undefined.STRING;
	}

	// -------------
	// VALUE
	// -------------

	/**
	 * Sets the value of matrix data point.
	 * 
	 * @param value the value of matrix data point
	 */
	public void setValue(double value) {
		setValue(Property.V, value);
	}

	/**
	 * Returns the value of matrix data point.
	 * 
	 * @return the value of matrix data point.
	 */
	public double getValue() {
		return getValue(Property.V, Undefined.DOUBLE);
	}

	/**
	 * Creates {@link MatrixDataPoint} form a {@link NativeObject}. This can be used by {@link DataItem}.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	public static final class MatrixDataPointFactory implements NativeObjectContainerFactory<MatrixDataPoint> {

		/**
		 * To avoid any instantiation
		 */
		private MatrixDataPointFactory() {
			// do nothing
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.commons.NativeObject)
		 */
		@Override
		public MatrixDataPoint create(NativeObject nativeObject) {
			return new MatrixDataPoint(nativeObject);
		}

	}

}