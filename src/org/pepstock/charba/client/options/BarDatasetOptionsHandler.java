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

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.defaults.IsDefaultDatasets;
import org.pepstock.charba.client.defaults.globals.DefaultDatasets;

/**
 * Manages the BAR dataset properties of options in order to use the same logic between datasets and options/configuration.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class BarDatasetOptionsHandler extends NativeObjectContainer {

	// min value of percentage
	private static final double MINIMUM_PERCENTAGE = 0D;
	// max value of percentage
	private static final double MAXIMUM_PERCENTAGE = 1D;
	
	// default value
	private final IsDefaultDatasets defaultValues;

	/**
	 * Name of properties of native object.
	 */
	protected enum Property implements Key
	{
		BAR_PERCENTAGE("barPercentage"),
		CATEGORY_PERCENTAGE("categoryPercentage"),
		BAR_THICKNESS("barThickness"),
		MAX_BAR_THICKNESS("maxBarThickness"),
		MIN_BAR_LENGTH("minBarLength");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use into native object.
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
	 * Creates an bar options handler with the native object where properties must be managed and the default value to use when the property does not exist.
	 * 
	 * @param nativeObject native object where bar options properties must be managed
	 * @param defaultValues default value of options properties to use when the properties do not exist
	 */
	public BarDatasetOptionsHandler(NativeObject nativeObject, IsDefaultDatasets defaultValues) {
		super(nativeObject);
		// checks default value instance
		if (defaultValues == null) {
			throw new IllegalArgumentException("Default value argument is null");
		}
		this.defaultValues = defaultValues;
	}

	/**
	 * Returns the default value of options to use when the property does not exist.
	 * 
	 * @return the default value of options to use when the property does not exist
	 */
	protected final IsDefaultDatasets getDefaultValues() {
		return defaultValues;
	}

	/**
	 * Sets the percent (0-1) of the available width each bar should be within the category width. 1.0 will take the whole category width and put the bars right next to each other.
	 * 
	 * @param barPercentage percent (0-1) of the available width each bar should be within the category width. 1.0 will take the whole category width and put the bars right next to
	 *            each other.
	 */
	public void setBarPercentage(double barPercentage) {
		setValue(Property.BAR_PERCENTAGE, checkAndGetPercentage(barPercentage, Property.BAR_PERCENTAGE));
	}

	/**
	 * Returns the percent (0-1) of the available width each bar should be within the category width. 1.0 will take the whole category width and put the bars right next to each
	 * other.
	 * 
	 * @return percent (0-1) of the available width each bar should be within the category width. 1.0 will take the whole category width and put the bars right next to each other.
	 */
	public double getBarPercentage() {
		return getValue(Property.BAR_PERCENTAGE, defaultValues.getBarPercentage());
	}

	/**
	 * Sets the percent (0-1) of the available width each category should be within the sample width.
	 * 
	 * @param categoryPercentage percent (0-1) of the available width each category should be within the sample width.
	 */
	public void setCategoryPercentage(double categoryPercentage) {
		setValue(Property.CATEGORY_PERCENTAGE, checkAndGetPercentage(categoryPercentage, Property.CATEGORY_PERCENTAGE));
	}

	/**
	 * Returns the percent (0-1) of the available width each category should be within the sample width.
	 * 
	 * @return the percent (0-1) of the available width each category should be within the sample width.
	 */
	public double getCategoryPercentage() {
		return getValue(Property.CATEGORY_PERCENTAGE, defaultValues.getCategoryPercentage());
	}

	/**
	 * Sets the width of each bar in pixels. If set to 'flex', it computes "optimal" sample widths that globally arrange bars side by side. If not set, the base sample widths are
	 * calculated automatically so that they take the full available widths without overlap. Then, the bars are sized using barPercentage and categoryPercentage.
	 * 
	 * @param barThickness width of each bar in pixels. If not set, the base sample widths are calculated automatically so that they take the full available widths without overlap.
	 *            Then, the bars are sized using barPercentage and categoryPercentage.
	 */
	public void setBarThickness(int barThickness) {
		// checks if FLEX value has been set
		if (DefaultDatasets.FLEX_BAR_THICKNESS == barThickness) {
			// flex must be set
			setValue(Property.BAR_THICKNESS, DefaultDatasets.FLEX_BAR_THICKNESS_VALUE);
		} else {
			setValue(Property.BAR_THICKNESS, barThickness);
		}
	}

	/**
	 * Returns the width of each bar in pixels. If set to 'flex', it computes "optimal" sample widths that globally arrange bars side by side. If not set, the base sample widths
	 * are calculated automatically so that they take the full available widths without overlap. Then, the bars are sized using barPercentage and categoryPercentage.
	 * 
	 * @return width of each bar in pixels. If not set, the base sample widths are calculated automatically so that they take the full available widths without overlap. Then, the
	 *         bars are sized using barPercentage and categoryPercentage.
	 */
	public int getBarThickness() {
		// checks if flex has been set
		if (ObjectType.STRING.equals(type(Property.BAR_THICKNESS))) {
			return DefaultDatasets.FLEX_BAR_THICKNESS;
		}
		// if here, is not flex
		return getValue(Property.BAR_THICKNESS, defaultValues.getBarThickness());
	}

	/**
	 * Sets the maximum bar thickness, to ensure that bars are not sized thicker than this
	 * 
	 * @param maxBarThickness the maximum bar thickness.
	 */
	public void setMaxBarThickness(int maxBarThickness) {
		setValue(Property.MAX_BAR_THICKNESS, maxBarThickness);
	}

	/**
	 * Returns the maximum bar thickness.
	 * 
	 * @return the maximum bar thickness.
	 */
	public int getMaxBarThickness() {
		return getValue(Property.MAX_BAR_THICKNESS, defaultValues.getMaxBarThickness());
	}

	/**
	 * Set this to ensure that bars have a minimum length in pixels.
	 * 
	 * @param minBarLength a minimum length in pixels.
	 */
	public void setMinBarLength(int minBarLength) {
		setValue(Property.MIN_BAR_LENGTH, minBarLength);
	}

	/**
	 * Returns a minimum length in pixels.
	 * 
	 * @return a minimum length in pixels.
	 */
	public int getMinBarLength() {
		return getValue(Property.MIN_BAR_LENGTH, defaultValues.getMinBarLength());
	}
	
	/**
	 * Any double between 0.0d and 1.0d (inclusive) is valid.
	 * 
	 * @param percentage value between 0 and 1 for a percentage value
	 * @param property property to be checked
	 * @return the value passed as argument
	 */
	private double checkAndGetPercentage(double percentage, Property property) {
		if (percentage < MINIMUM_PERCENTAGE || percentage > MAXIMUM_PERCENTAGE) {
			throw new IllegalArgumentException(property.value+" argument (" + percentage + ") is not within bounds (0D-1D)");
		}
		// if here the value is correct
		return percentage;
	}
}
