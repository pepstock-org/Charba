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

import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.IsEnvelop;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.commons.PropertyHandler;
import org.pepstock.charba.client.data.DataEnvelop;
import org.pepstock.charba.client.defaults.IsDefaultTypedDataset;
import org.pepstock.charba.client.defaults.globals.DefaultTypedDataset;

/**
 * Manages the BAR dataset properties of options in order to use the same logic between datasets and options/configuration.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class BarDatasetOptionsHandler extends PropertyHandler<IsDefaultTypedDataset> {

	// min value of percentage
	private static final double MINIMUM_PERCENTAGE = 0D;
	// max value of percentage
	private static final double MAXIMUM_PERCENTAGE = 1D;

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
	 * Creates an bar options handler with an envelop of the native object where properties must be managed and the default value to use when the property does not exist.
	 * 
	 * @param parent model which contains the options handler.
	 * @param defaultValues default value of options properties to use when the properties do not exist
	 * @param envelop envelop of native object where bar options properties must be managed
	 */
	public BarDatasetOptionsHandler(AbstractNode parent, IsDefaultTypedDataset defaultValues, DataEnvelop<NativeObject> envelop) {
		this(parent, defaultValues, IsEnvelop.checkAndGetIfValid(envelop).getContent());
	}

	/**
	 * Creates an bar options handler with the native object where properties must be managed and the default value to use when the property does not exist.
	 * 
	 * @param parent model which contains the options handler.
	 * @param defaultValues default value of options properties to use when the properties do not exist
	 * @param nativeObject native object where bar options properties must be managed
	 */
	BarDatasetOptionsHandler(AbstractNode parent, IsDefaultTypedDataset defaultValues, NativeObject nativeObject) {
		super(parent, defaultValues, nativeObject);
	}

	/**
	 * Sets the percent (0-1) of the available width each bar should be within the category width. 1.0 will take the whole category width and put the bars right next to each other.
	 * 
	 * @param barPercentage percent (0-1) of the available width each bar should be within the category width. 1.0 will take the whole category width and put the bars right next to
	 *            each other.
	 */
	void setBarPercentage(double barPercentage) {
		setValueAndAddToParent(Property.BAR_PERCENTAGE, checkAndGetPercentage(barPercentage, Property.BAR_PERCENTAGE));
	}

	/**
	 * Returns the percent (0-1) of the available width each bar should be within the category width. 1.0 will take the whole category width and put the bars right next to each
	 * other.
	 * 
	 * @return percent (0-1) of the available width each bar should be within the category width. 1.0 will take the whole category width and put the bars right next to each other.
	 */
	double getBarPercentage() {
		return getValue(Property.BAR_PERCENTAGE, getDefaultValues().getBarPercentage());
	}

	/**
	 * Sets the percent (0-1) of the available width each category should be within the sample width.
	 * 
	 * @param categoryPercentage percent (0-1) of the available width each category should be within the sample width.
	 */
	void setCategoryPercentage(double categoryPercentage) {
		setValueAndAddToParent(Property.CATEGORY_PERCENTAGE, checkAndGetPercentage(categoryPercentage, Property.CATEGORY_PERCENTAGE));
	}

	/**
	 * Returns the percent (0-1) of the available width each category should be within the sample width.
	 * 
	 * @return the percent (0-1) of the available width each category should be within the sample width.
	 */
	double getCategoryPercentage() {
		return getValue(Property.CATEGORY_PERCENTAGE, getDefaultValues().getCategoryPercentage());
	}

	/**
	 * Sets the width of each bar in pixels. If set to 'flex', it computes "optimal" sample widths that globally arrange bars side by side. If not set, the base sample widths are
	 * calculated automatically so that they take the full available widths without overlap. Then, the bars are sized using barPercentage and categoryPercentage.
	 * 
	 * @param barThickness width of each bar in pixels. If not set, the base sample widths are calculated automatically so that they take the full available widths without overlap.
	 *            Then, the bars are sized using barPercentage and categoryPercentage.
	 */
	void setBarThickness(int barThickness) {
		// checks if FLEX value has been set
		if (DefaultTypedDataset.FLEX_BAR_THICKNESS == barThickness) {
			// flex must be set
			setValueAndAddToParent(Property.BAR_THICKNESS, DefaultTypedDataset.FLEX_BAR_THICKNESS_VALUE);
		} else {
			setValueAndAddToParent(Property.BAR_THICKNESS, barThickness);
		}
	}

	/**
	 * Returns the width of each bar in pixels. If set to 'flex', it computes "optimal" sample widths that globally arrange bars side by side. If not set, the base sample widths
	 * are calculated automatically so that they take the full available widths without overlap. Then, the bars are sized using barPercentage and categoryPercentage.
	 * 
	 * @return width of each bar in pixels. If not set, the base sample widths are calculated automatically so that they take the full available widths without overlap. Then, the
	 *         bars are sized using barPercentage and categoryPercentage.
	 */
	int getBarThickness() {
		// checks if flex has been set
		if (isType(Property.BAR_THICKNESS, ObjectType.STRING)) {
			return DefaultTypedDataset.FLEX_BAR_THICKNESS;
		}
		// if here, is not flex
		return getValue(Property.BAR_THICKNESS, getDefaultValues().getBarThickness());
	}

	/**
	 * Sets the maximum bar thickness, to ensure that bars are not sized thicker than this
	 * 
	 * @param maxBarThickness the maximum bar thickness.
	 */
	void setMaxBarThickness(int maxBarThickness) {
		setValueAndAddToParent(Property.MAX_BAR_THICKNESS, maxBarThickness);
	}

	/**
	 * Returns the maximum bar thickness.
	 * 
	 * @return the maximum bar thickness.
	 */
	int getMaxBarThickness() {
		return getValue(Property.MAX_BAR_THICKNESS, getDefaultValues().getMaxBarThickness());
	}

	/**
	 * Set this to ensure that bars have a minimum length in pixels.
	 * 
	 * @param minBarLength a minimum length in pixels.
	 */
	void setMinBarLength(int minBarLength) {
		setValueAndAddToParent(Property.MIN_BAR_LENGTH, minBarLength);
	}

	/**
	 * Returns a minimum length in pixels.
	 * 
	 * @return a minimum length in pixels.
	 */
	int getMinBarLength() {
		return getValue(Property.MIN_BAR_LENGTH, getDefaultValues().getMinBarLength());
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
			throw new IllegalArgumentException(property.value + " argument (" + percentage + ") is not within bounds (0D-1D)");
		}
		// if here the value is correct
		return percentage;
	}
}
