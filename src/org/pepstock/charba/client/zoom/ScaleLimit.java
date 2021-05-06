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
import org.pepstock.charba.client.items.Undefined;

/**
 * Entity of {@link ZoomPlugin#ID} configuration in order to set minimum and maximum values of the scales for pan or zoom.<br>
 * The values to set to the properties depends on the type of scales are used.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ScaleLimit extends NativeObjectContainer implements IsDefaultScaleLimit {

	/**
	 * Name of properties of native object.
	 */
	enum Property implements Key
	{
		MIN("min"),
		MAX("max"),
		MIN_RANGE("minRange");

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
	private IsDefaultScaleLimit defaultOptions;

	/**
	 * Creates new scale limit element, using stored native object instance and the default values options.
	 * 
	 * @param defaultOptions default scale limit options to returns the default when required.
	 * @param nativeObject stored scale limit values in the native object to read.
	 */
	ScaleLimit(IsDefaultScaleLimit defaultOptions, NativeObject nativeObject) {
		super(nativeObject);
		// checks if defaults options is consistent
		// stores defaults options
		this.defaultOptions = checkDefaultValuesArgument(defaultOptions);
	}

	/**
	 * Returns the {@link ObjectType} of MIN property.
	 * 
	 * @return the {@link ObjectType} of MIN property
	 */
	public ObjectType getTypeForMin() {
		return type(Property.MIN);
	}

	/**
	 * Returns the {@link ObjectType} of Y property.
	 * 
	 * @return the {@link ObjectType} of Y property
	 */
	public ObjectType getTypeForMax() {
		return type(Property.MAX);
	}

	/**
	 * Sets the minimum value of limit as string.
	 * 
	 * @param min the minimum value of limit as string
	 */
	public void setMin(String min) {
		setValue(Property.MIN, min);
	}

	/**
	 * Sets the minimum value of limit as double.
	 * 
	 * @param min the minimum value of limit as double
	 */
	public void setMin(double min) {
		setValue(Property.MIN, min);
	}

	/**
	 * Sets the minimum value of limit as date.
	 * 
	 * @param min the minimum value of limit as date
	 */
	public void setMin(Date min) {
		setValue(Property.MIN, min);
	}

	/**
	 * Returns the minimum value of limit as string.
	 * 
	 * @return the minimum value of limit as string
	 */
	@Override
	public String getMin() {
		// checks if the value has been set as string
		if (ObjectType.STRING.equals(getTypeForMin())) {
			return getValue(ScaleLimit.Property.MIN, defaultOptions.getMin());
		}
		// if here, the value is not a string then returns undefined
		return Undefined.STRING;
	}

	/**
	 * Returns the minimum value of limit as double.
	 * 
	 * @return the minimum value of limit as double
	 */
	@Override
	public double getMinAsDouble() {
		// checks if the value has been set as double
		if (ObjectType.NUMBER.equals(getTypeForMin())) {
			return getValue(ScaleLimit.Property.MIN, defaultOptions.getMinAsDouble());
		}
		// if here, the value is not a double then returns undefined
		return Undefined.DOUBLE;
	}

	/**
	 * Returns the minimum value of limit as date.
	 * 
	 * @return the minimum value of limit as date
	 */
	@Override
	public Date getMinAsDate() {
		// checks if the value has been set as date
		if (ObjectType.NUMBER.equals(getTypeForMin())) {
			return getValue(ScaleLimit.Property.MIN, defaultOptions.getMinAsDate());
		}
		// if here, the value is not a date then returns null
		return null;
	}

	/**
	 * Sets the maximum value of limit as string.
	 * 
	 * @param y the maximum value of limit as string
	 */
	public void setMax(String y) {
		setValue(Property.MAX, y);
	}

	/**
	 * Sets the maximum value of limit as double.
	 * 
	 * @param y the maximum value of limit as double
	 */
	public void setMax(double y) {
		setValue(Property.MAX, y);
	}

	/**
	 * Sets the maximum value of limit as date.
	 * 
	 * @param y the maximum value of limit as date
	 */
	public void setMax(Date y) {
		setValue(Property.MAX, y);
	}

	/**
	 * Returns the maximum value of limit as double.
	 * 
	 * @return the maximum value of limit as double
	 */
	@Override
	public double getMax() {
		// checks if the value has been set as double
		if (ObjectType.NUMBER.equals(getTypeForMax())) {
			return getValue(ScaleLimit.Property.MAX, defaultOptions.getMax());
		}
		// if here, the value is not a double then returns undefined
		return Undefined.DOUBLE;
	}

	/**
	 * Returns the maximum value of limit as string.
	 * 
	 * @return the maximum value of limit as string
	 */
	@Override
	public String getMaxAsString() {
		// checks if the value has been set as string
		if (ObjectType.STRING.equals(getTypeForMax())) {
			return getValue(ScaleLimit.Property.MAX, defaultOptions.getMaxAsString());
		}
		// if here, the value is not a stirng then returns undefined
		return Undefined.STRING;
	}

	/**
	 * Returns the maximum value of limit as date.
	 * 
	 * @return the maximum value of limit as date
	 */
	@Override
	public Date getMaxAsDate() {
		// checks if the value has been set as date
		if (ObjectType.NUMBER.equals(getTypeForMax())) {
			return getValue(ScaleLimit.Property.MAX, defaultOptions.getMaxAsDate());
		}
		// if here, the value is not a date then returns null
		return null;
	}
}
