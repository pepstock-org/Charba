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
package org.pepstock.charba.client.items;

import org.pepstock.charba.client.ChartType;
import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.data.BarBorderRadius;
import org.pepstock.charba.client.data.BarBorderWidth;
import org.pepstock.charba.client.defaults.globals.DefaultBar;
import org.pepstock.charba.client.enums.BorderSkipped;

/**
 * Maps the out-of-the-box CHART.JS element options used to represents bars on the charts.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class BarElementOptions extends CommonElementOptions {

	// min value of percentage
	private static final double MINIMUM_PERCENTAGE = 0D;
	// max value of percentage
	private static final double MAXIMUM_PERCENTAGE = 1D;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		BAR_PERCENTAGE("barPercentage"),
		BASE("base"),
		BORDER_SKIPPED("borderSkipped"),
		BORDER_RADIUS("borderRadius"),
		BORDER_WIDTH("borderWidth"),
		CATEGORY_PERCENTAGE("categoryPercentage"),
		INFLATE_AMOUNT("inflateAmount");

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
	 * Creates the item using a native java script object which contains all properties.
	 * 
	 * @param nativeObject native java script object which contains all properties.
	 */
	protected BarElementOptions(NativeObject nativeObject) {
		super(nativeObject);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.items.ChartElementOptions#getDefaultBorderWidth()
	 */
	@Override
	protected int getDefaultBorderWidth() {
		return Defaults.get().getGlobal().getElements().getBar().getBorderWidth();
	}

	/**
	 * Returns <code>true</code> if the border width is defined as {@link BarBorderWidth}.
	 * 
	 * @return <code>true</code> if the border width is defined as {@link BarBorderWidth}
	 */
	public boolean isBorderWidthAsObject() {
		return isType(Property.BORDER_WIDTH, ObjectType.OBJECT);
	}

	/**
	 * Returns the border width of the dataset item in pixels.
	 *
	 * @return the border width of the dataset item in pixels.
	 */
	@Override
	public int getBorderWidth() {
		// checks if border width is an object
		if (isBorderWidthAsObject()) {
			// gets the border width object
			// then returns the average
			return BarBorderWidth.FACTORY.create(getValue(Property.BORDER_WIDTH)).average();
		}
		// if here, the border width is a number or missing
		return super.getBorderWidth();
	}

	/**
	 * Returns the border width of the dataset item in pixels as {@link BarBorderWidth}.
	 *
	 * @return the border width of the dataset item in pixels as {@link BarBorderWidth}.
	 */
	public BarBorderWidth getBorderWidthAsObject() {
		// checks if border width is an object
		if (isBorderWidthAsObject()) {
			// gets the border width object
			return BarBorderWidth.FACTORY.create(getValue(Property.BORDER_WIDTH));
		}
		// if here, the border width is a number or missing
		// then returns a new object with same value
		return new BarBorderWidth(super.getBorderWidth());
	}

	/**
	 * Sets the border width.
	 * 
	 * @param borderWidth the border width.
	 */
	public final void setBorderWidth(BarBorderWidth borderWidth) {
		setValue(Property.BORDER_WIDTH, borderWidth);
	}

	/**
	 * Returns the percent (0-1) of the available width each bar should be within the category width. 1.0 will take the whole category width and put the bars right next to each
	 * other.
	 * 
	 * @return percent (0-1) of the available width each bar should be within the category width. 1.0 will take the whole category width and put the bars right next to each other.
	 */
	public double getBarPercentage() {
		return getValue(Property.BAR_PERCENTAGE, Defaults.get().getGlobal().getDatasets().get(ChartType.BAR).getBarPercentage());
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
	 * Returns the percent (0-1) of the available width each category should be within the sample width.
	 * 
	 * @return the percent (0-1) of the available width each category should be within the sample width.
	 */
	public double getCategoryPercentage() {
		return getValue(Property.CATEGORY_PERCENTAGE, Defaults.get().getGlobal().getDatasets().get(ChartType.BAR).getCategoryPercentage());
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
	 * Returns the base value for the bar in data units along the value axis.<br>
	 * If not set, defaults to the value axis base value.
	 * 
	 * @return base value for the bar in data units along the value axis.<br>
	 *         If not set, defaults to the value axis base value
	 */
	public double getBase() {
		return getValue(Property.BASE, Defaults.get().getGlobal().getElements().getBar().getBase());
	}

	/**
	 * Sets the base value for the bar in data units along the value axis.<br>
	 * If not set, defaults to the value axis base value.
	 * 
	 * @param base base value for the bar in data units along the value axis.<br>
	 *            If not set, defaults to the value axis base value
	 */
	public void setBase(double base) {
		setValue(Property.BASE, base);
	}

	/**
	 * Returns the edge to skip drawing the border for.
	 * 
	 * @return the edge to skip drawing the border for.
	 */
	public BorderSkipped getBorderSkipped() {
		// checks if 'false' has been set
		if (isType(Property.BORDER_SKIPPED, ObjectType.BOOLEAN)) {
			// returns is false
			return BorderSkipped.FALSE;
		}
		// otherwise returns the enum value as string
		return getValue(Property.BORDER_SKIPPED, BorderSkipped.values(), Defaults.get().getGlobal().getElements().getBar().getBorderSkipped());
	}

	/**
	 * Sets the edge to skip drawing the border for.
	 * 
	 * @param position the edge to skip drawing the border for.
	 */
	public void setBorderSkipped(BorderSkipped position) {
		// checks if setting a false value
		if (BorderSkipped.FALSE.equals(position)) {
			// stores boolean value
			setValue(Property.BORDER_SKIPPED, false);
		} else if (BorderSkipped.TRUE.equals(position)) {
			// stores boolean value
			setValue(Property.BORDER_SKIPPED, true);
		} else {
			// otherwise stores the key value
			setValue(Property.BORDER_SKIPPED, position);
		}
	}

	/**
	 * Sets the edge to skip drawing the border for.
	 * 
	 * @param borderskip to set <code>false</code> as border skipped.
	 */
	public void setBorderSkipped(boolean borderskip) {
		setValue(Property.BORDER_SKIPPED, borderskip);
	}

	/**
	 * Returns <code>true</code> if the border width is defined as {@link BarBorderRadius}.
	 * 
	 * @return <code>true</code> if the border width is defined as {@link BarBorderRadius}
	 */
	public boolean isBorderRadiusAsObject() {
		return isType(Property.BORDER_RADIUS, ObjectType.OBJECT);
	}

	/**
	 * Returns the bar border radius (in pixels).
	 * 
	 * @return the bar border radius (in pixels).
	 */
	public int getBorderRadius() {
		// checks if border radius is an object
		if (isBorderRadiusAsObject()) {
			// gets the border radius object
			// then returns the average
			return BarBorderRadius.FACTORY.create(getValue(Property.BORDER_RADIUS)).average();
		}
		// if here, the border radius is a number or missing
		return getValue(Property.BORDER_RADIUS, Defaults.get().getGlobal().getElements().getBar().getBorderRadius());
	}

	/**
	 * Sets the bar border radius (in pixels).
	 * 
	 * @param borderRadius the bar border radius (in pixels).
	 */
	public void setBorderRadius(int borderRadius) {
		setValue(Property.BORDER_RADIUS, Checker.positiveOrZero(borderRadius));
	}

	/**
	 * Returns the border radius of the dataset item in pixels as {@link BarBorderRadius}.
	 *
	 * @return the border radius of the dataset item in pixels as {@link BarBorderRadius}.
	 */
	public BarBorderRadius getBarBorderRadius() {
		// checks if border radius is an object
		if (isBorderRadiusAsObject()) {
			// gets the border radius object
			return BarBorderRadius.FACTORY.create(getValue(Property.BORDER_RADIUS));
		}
		// if here, the border radius is a number or missing
		// then returns a new object with same value
		return new BarBorderRadius(getValue(Property.BORDER_RADIUS, Defaults.get().getGlobal().getElements().getBar().getBorderRadius()));
	}

	/**
	 * Sets the bar border radius (in pixels).
	 * 
	 * @param borderRadius the bar border radius (in pixels).
	 */
	public void setBorderRadius(BarBorderRadius borderRadius) {
		setValue(Property.BORDER_RADIUS, borderRadius);
	}

	/**
	 * Returns <code>true</code> if the amount of pixels to inflate the bar rectangles, when drawing, is automatically calculated.
	 * 
	 * @return <code>true</code> if the amount of pixels to inflate the bar rectangles, when drawing, is automatically calculated
	 */
	public boolean isAutoInflateAmount() {
		// checks if the property is NOT set as number
		if (!isType(Property.INFLATE_AMOUNT, ObjectType.NUMBER)) {
			// gets value
			String value = getValue(Property.INFLATE_AMOUNT, Undefined.STRING);
			// checks if the value is consistent
			if (DefaultBar.AUTO_INFLATE_AMOUNT.equalsIgnoreCase(value) || value == null) {
				// if here, the value is consistent or not exists
				return true;
			}
			// if here, the stored value is not consistent
			// then returns default
			return Defaults.get().getGlobal().getElements().getBar().isAutoInflateAmount();
		}
		// if here, the inflate is a number
		// then returns false
		return false;
	}

	/**
	 * Sets <code>true</code> if the amount of pixels to inflate the bar rectangles, when drawing, is automatically calculated.
	 * 
	 * @param autoInflateAmount <code>true</code> if the amount of pixels to inflate the bar rectangles, when drawing, is automatically calculated
	 */
	public void setAutoInflateAmount(boolean autoInflateAmount) {
		// checks if setting
		if (autoInflateAmount) {
			setValue(Property.INFLATE_AMOUNT, DefaultBar.AUTO_INFLATE_AMOUNT);
		} else {
			// removes key
			remove(Property.INFLATE_AMOUNT);
		}
	}

	/**
	 * Returns the amount of pixels to inflate the bar rectangles, when drawing.
	 * 
	 * @return the amount of pixels to inflate the bar rectangles, when drawing
	 */
	public int getInflateAmount() {
		// checks if the property is NOT set as number
		if (isType(Property.INFLATE_AMOUNT, ObjectType.NUMBER)) {
			return getValue(Property.INFLATE_AMOUNT, Defaults.get().getGlobal().getElements().getBar().getInflateAmount());
		}
		// if here, the inflate is a number
		// then returns false
		return Undefined.INTEGER;
	}

	/**
	 * Sets the amount of pixels to inflate the bar rectangles, when drawing.
	 * 
	 * @param inflateAmount the amount of pixels to inflate the bar rectangles, when drawing
	 */
	public void setInflateAmount(int inflateAmount) {
		setValue(Property.INFLATE_AMOUNT, Checker.positiveOrZero(inflateAmount));
	}

	/**
	 * Any double between 0d and 1d (inclusive) is valid.
	 * 
	 * @param percentage value between 0 and 1 for a percentage value
	 * @param property property to be checked
	 * @return the value passed as argument
	 */
	private double checkAndGetPercentage(double percentage, Property property) {
		Checker.checkIfBetween(percentage, MINIMUM_PERCENTAGE, MAXIMUM_PERCENTAGE, property.value + " argument");
		// if here the value is correct
		return percentage;
	}
}