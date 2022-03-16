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
package org.pepstock.charba.client.data;

import java.util.Date;

import org.pepstock.charba.client.commons.BaseEnvelop;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.Envelop;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.items.DataItem;
import org.pepstock.charba.client.items.ItemsEnvelop;
import org.pepstock.charba.client.items.Undefined;
import org.pepstock.charba.client.utils.JSON;

/**
 * Used for sparse datasets, such as those in scatter charts. Each data point is specified using an object containing x and y properties.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class DataPoint extends AbstractDataPoint {

	// default value for X. No private because it is used by time series item
	static final double DEFAULT_X = Undefined.DOUBLE;
	// default value for R. No private because it is used by time series item
	static final double DEFAULT_R = Undefined.DOUBLE;
	// default value for Y. No private because it is used by time series item
	static final double DEFAULT_Y = Undefined.DOUBLE;

	/**
	 * Name of properties of native object.<br>
	 * No private because it is used by time series item
	 */
	enum Property implements Key
	{
		X("x"),
		Y("y"),
		R("r");

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
	public DataPoint() {
		this((NativeObject) null);
	}

	/**
	 * Creates the object with the passed point values.
	 * 
	 * @param x x value of data point
	 * @param y y value of data point
	 */
	public DataPoint(double x, double y) {
		this();
		// stores values
		setX(x);
		setY(y);
	}

	/**
	 * Creates the object with the passed point values.
	 * 
	 * @param x x value of data point
	 * @param y y value of data point
	 */
	public DataPoint(Date x, double y) {
		this();
		// stores values
		setX(x);
		setY(y);
	}

	/**
	 * Creates the object with the passed point values.
	 * 
	 * @param x x value of data point
	 * @param y y value of data point
	 */
	public DataPoint(String x, double y) {
		this();
		// stores values
		setX(x);
		setY(y);
	}

	/**
	 * Creates the object with the passed point values.
	 * 
	 * @param x x value of data point
	 * @param y y value of data point
	 */
	public DataPoint(double x, FloatingData y) {
		this();
		// stores values
		setX(x);
		setY(y);
	}

	/**
	 * Creates the object with the passed point values.
	 * 
	 * @param x x value of data point
	 * @param y y value of data point
	 */
	public DataPoint(Date x, FloatingData y) {
		this();
		// stores values
		setX(x);
		setY(y);
	}

	/**
	 * Creates the object with the passed point values.
	 * 
	 * @param x x value of data point
	 * @param y y value of data point
	 */
	public DataPoint(String x, FloatingData y) {
		this();
		// stores values
		setX(x);
		setY(y);
	}

	/**
	 * Creates the object with the passed point values (for bubble).
	 * 
	 * @param x x value of data point
	 * @param y y value of data point
	 * @param r the bubble radius in pixels (not scaled).
	 */
	public DataPoint(double x, double y, double r) {
		this(x, y);
		// stores values
		setR(r);
	}

	/**
	 * Creates the object with a native object passed as argument by and {@link BaseEnvelop}.<br>
	 * This is called by the {@link DataItem}.
	 * 
	 * @param envelop envelop which contains a native object with a data point
	 */
	public DataPoint(ItemsEnvelop<NativeObject> envelop) {
		this(Envelop.checkAndGetIfValid(envelop).getContent());
	}

	/**
	 * Creates the object with a native object passed as argument.
	 * 
	 * @param nativeObject native object which maps a data point
	 */
	DataPoint(NativeObject nativeObject) {
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
	public ObjectType getXObjectType() {
		return type(Property.X);
	}

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
			return getValue(Property.X, DEFAULT_X);
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

	// -------------
	// Y
	// -------------

	/**
	 * Returns the object type of data stored as Y.
	 * 
	 * @return the object type of data stored as Y
	 */
	public ObjectType getYObjectType() {
		return type(Property.Y);
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
			return getValue(Property.Y, DEFAULT_Y);
		}
		// if here the data is missing or an array
		// then returns the default
		return DEFAULT_Y;
	}

	/**
	 * Sets Y value.
	 * 
	 * @param y Y value.
	 */
	public void setY(FloatingData y) {
		setValue(Property.Y, y);
	}

	/**
	 * Returns Y value.
	 * 
	 * @return Y value.
	 */
	public FloatingData getYAsFloatingData() {
		// checks if the stored data is a array
		if (ObjectType.ARRAY.equals(getYObjectType())) {
			return new FloatingData(getArrayValue(Property.Y));
		}
		// if here the data is missing or not a number
		// then returns an empty data
		return new FloatingData();
	}

	// -------------
	// R
	// -------------

	/**
	 * Sets the bubble radius in pixels (not scaled).<br>
	 * It is not scaled by the chart, it is the raw radius in pixels of the bubble that is drawn on the canvas.
	 * 
	 * @param r the bubble radius in pixels (not scaled).
	 */
	public void setR(double r) {
		setValue(Property.R, Checker.positiveOrZero(r));
	}

	/**
	 * Returns the bubble radius in pixels (not scaled).
	 * 
	 * @return the bubble radius in pixels (not scaled).
	 */
	public double getR() {
		return getValue(Property.R, DEFAULT_R);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return JSON.stringifyWithReplacer(getNativeObject());
	}

}