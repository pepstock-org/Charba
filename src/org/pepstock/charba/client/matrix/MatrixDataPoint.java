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
package org.pepstock.charba.client.matrix;

import java.util.Date;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.data.AbstractDataPoint;
import org.pepstock.charba.client.items.Undefined;

/**
 * Maps the data passed to a matrix dataset.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class MatrixDataPoint extends AbstractDataPoint {

	/**
	 * Name of properties of native object.<br>
	 * No private because it is used by time series item
	 */
	private enum Property implements Key
	{
		X("x"),
		Y("y"),
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
	// TYPE CHECKER
	// -------------

	/**
	 * Returns the object type of data stored as X.
	 * 
	 * @return the object type of data stored as X
	 */
	public ObjectType getXObjectType() {
		return type(Property.X);
	}

	/**
	 * Returns the object type of data stored as Y.
	 * 
	 * @return the object type of data stored as Y
	 */
	public ObjectType getYObjectType() {
		return type(Property.Y);
	}

	// -------------
	// DOUBLE
	// -------------

	/**
	 * Sets X value as double (for scatter and bubble datasets).
	 * 
	 * @param x X value as double
	 */
	public void setX(double x) {
		setValue(Property.X, x);
	}

	/**
	 * Returns X value as double (for scatter and bubble datasets).
	 * 
	 * @return X value as double
	 */
	public double getX() {
		// checks if the stored data is a number
		if (ObjectType.NUMBER.equals(getXObjectType())) {
			return getValue(Property.X, Undefined.DOUBLE);
		}
		// if here the data is missing or a string
		// then returns the default
		return Undefined.DOUBLE;
	}

	/**
	 * Sets Y value.
	 * 
	 * @param y Y value.
	 */
	public void setY(double y) {
		setValue(Property.Y, y);
	}

	/**
	 * Returns Y value.
	 * 
	 * @return Y value.
	 */
	public double getY() {
		// checks if the stored data is a number
		if (ObjectType.NUMBER.equals(getYObjectType())) {
			return getValue(Property.Y, Undefined.DOUBLE);
		}
		// if here the data is missing or an array
		// then returns the default
		return Undefined.DOUBLE;
	}

	// -------------
	// DATE
	// -------------

	/**
	 * Sets X value as date for time series.
	 * 
	 * @param x X value as date for time series
	 */
	public void setX(Date x) {
		setValue(Property.X, x);
	}

	/**
	 * Returns X value as date for time series.
	 * 
	 * @return X value as date for time series or <code>null</code> if is not set
	 */
	public Date getXAsDate() {
		// checks if the stored data is a number
		if (ObjectType.NUMBER.equals(getXObjectType())) {
			return getValue(Property.X, (Date) null);
		}
		// if here the data is missing or a string
		// then returns the default
		return null;
	}

	/**
	 * Sets Y value as date for time series.
	 * 
	 * @param y Y value as date for time series
	 */
	public void setY(Date y) {
		setValue(Property.Y, y);
	}

	/**
	 * Returns Y value as date for time series.
	 * 
	 * @return Y value as date for time series
	 */
	public Date getYAsDate() {
		// checks if the stored data is a number
		if (ObjectType.NUMBER.equals(getYObjectType())) {
			return getValue(Property.Y, (Date) null);
		}
		// if here the data is missing or a string
		// then returns the default
		return null;
	}

	// -------------
	// STRING
	// -------------

	/**
	 * Sets X value as string.
	 * 
	 * @param x X value as string
	 */
	public void setX(String x) {
		setValue(Property.X, x);
	}

	/**
	 * Returns X value as string.
	 * 
	 * @return X value as string or {@link Undefined#STRING} if is not set
	 */
	public String getXAsString() {
		// checks if the stored data is a string
		if (ObjectType.STRING.equals(getXObjectType())) {
			return getValue(Property.X, Undefined.STRING);
		}
		// if here the data is missing or a number
		// then returns the default
		return Undefined.STRING;
	}

	/**
	 * Sets Y value as string.
	 * 
	 * @param y Y value as string
	 */
	public void setY(String y) {
		setValue(Property.Y, y);
	}

	/**
	 * Returns Y value as string.
	 * 
	 * @return Y value as string
	 */
	public String getYAsString() {
		// checks if the stored data is a string
		if (ObjectType.STRING.equals(getYObjectType())) {
			return getValue(Property.Y, Undefined.STRING);
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

}