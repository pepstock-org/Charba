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
package org.pepstock.charba.client.zoom;

import java.util.Date;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.items.UndefinedValues;

/**
 * Entity of {@link ZoomPlugin#ID} configuration in order to set minimum and maximum values of X and Y scales.<br>
 * The values to set to the properties depends on the type of scales are used.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Range extends NativeObjectContainer implements IsDefaultRange {

	/**
	 * Name of properties of native object.
	 */
	enum Property implements Key
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

	// defaults global options instance
	private IsDefaultRange defaultOptions;

	/**
	 * Creates new range element, using the default values options.
	 * 
	 * @param defaultOptions default RANGE options to returns the default when required.
	 */
	Range(IsDefaultRange defaultOptions) {
		this(defaultOptions, null);
	}

	/**
	 * Creates new range element, using stored native object instance and the default values options.
	 * 
	 * @param defaultOptions default RANGE options to returns the default when required.
	 * @param nativeObject stored range values in the native object to read.
	 */
	Range(IsDefaultRange defaultOptions, NativeObject nativeObject) {
		super(nativeObject);
		this.defaultOptions = defaultOptions;
	}

	/**
	 * Returns the {@link ObjectType} of X property.
	 * 
	 * @return the {@link ObjectType} of X property
	 */
	public ObjectType getTypeForX() {
		return type(Property.X);
	}

	/**
	 * Returns the {@link ObjectType} of Y property.
	 * 
	 * @return the {@link ObjectType} of Y property
	 */
	public ObjectType getTypeForY() {
		return type(Property.Y);
	}

	/**
	 * Sets the X value of range as string.
	 * 
	 * @param x the X value of range as string
	 */
	public void setX(String x) {
		setValue(Property.X, x);
	}

	/**
	 * Sets the X value of range as double.
	 * 
	 * @param x the X value of range as double
	 */
	public void setX(Double x) {
		setValue(Property.X, x);
	}

	/**
	 * Sets the X value of range as date.
	 * 
	 * @param x the X value of range as date
	 */
	public void setX(Date x) {
		setValue(Property.X, x);
	}

	/**
	 * Returns the X value of range as string.
	 * 
	 * @return the X value of range as string
	 */
	@Override
	public String getX() {
		// checks if the value has been set as string
		if (ObjectType.STRING.equals(getTypeForX())) {
			return getValue(Range.Property.X, defaultOptions.getX());
		}
		// if here, the value is not a string then returns undefined
		return UndefinedValues.STRING;
	}

	/**
	 * Returns the X value of range as double.
	 * 
	 * @return the X value of range as double
	 */
	@Override
	public double getXAsDouble() {
		// checks if the value has been set as double
		if (ObjectType.NUMBER.equals(getTypeForX())) {
			return getValue(Range.Property.X, defaultOptions.getXAsDouble());
		}
		// if here, the value is not a double then returns undefined
		return UndefinedValues.DOUBLE;
	}

	/**
	 * Returns the X value of range as date.
	 * 
	 * @return the X value of range as date
	 */
	@Override
	public Date getXAsDate() {
		// checks if the value has been set as date
		if (ObjectType.NUMBER.equals(getTypeForX())) {
			return getValue(Range.Property.X, defaultOptions.getXAsDate());
		}
		// if here, the value is not a date then returns null
		return null;
	}

	/**
	 * Sets the Y value of range as string.
	 * 
	 * @param y the Y value of range as string
	 */
	public void setY(String y) {
		setValue(Property.Y, y);
	}

	/**
	 * Sets the Y value of range as double.
	 * 
	 * @param y the Y value of range as double
	 */
	public void setY(Double y) {
		setValue(Property.Y, y);
	}

	/**
	 * Sets the Y value of range as date.
	 * 
	 * @param y the Y value of range as date
	 */
	public void setY(Date y) {
		setValue(Property.Y, y);
	}

	/**
	 * Returns the Y value of range as double.
	 * 
	 * @return the Y value of range as double
	 */
	@Override
	public double getY() {
		// checks if the value has been set as double
		if (ObjectType.NUMBER.equals(getTypeForY())) {
			return getValue(Range.Property.Y, defaultOptions.getY());
		}
		// if here, the value is not a double then returns undefined
		return UndefinedValues.DOUBLE;
	}

	/**
	 * Returns the Y value of range as string.
	 * 
	 * @return the Y value of range as string
	 */
	@Override
	public String getYAsString() {
		// checks if the value has been set as string
		if (ObjectType.STRING.equals(getTypeForY())) {
			return getValue(Range.Property.Y, defaultOptions.getYAsString());
		}
		// if here, the value is not a stirng then returns undefined
		return UndefinedValues.STRING;
	}

	/**
	 * Returns the Y value of range as date.
	 * 
	 * @return the Y value of range as date
	 */
	@Override
	public Date getYAsDate() {
		// checks if the value has been set as date
		if (ObjectType.NUMBER.equals(getTypeForY())) {
			return getValue(Range.Property.Y, defaultOptions.getYAsDate());
		}
		// if here, the value is not a date then returns null
		return null;
	}
}
