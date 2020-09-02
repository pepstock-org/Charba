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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.enums.BorderSkipped;
import org.pepstock.charba.client.enums.PointStyle;

/**
 * This item provides all information about the view where a dataset has been displayed.<br>
 * This object has been created and passed to event handler or callbacks to apply own logic.<br>
 * This is a wrapper of the CHART.JS item with all needed info.<br>
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class DatasetItemOptions extends NativeObjectContainer {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		// all charts
		BACKGROUND_COLOR("backgroundColor"),
		BORDER_SKIPPED("borderSkipped"),
		BORDER_COLOR("borderColor"),
		BORDER_WIDTH("borderWidth"),
		// bar, horizontal bar
		BAR_PERCENTAGE("barPercentage"),
		CATEGORY_PERCENTAGE("categoryPercentage"),
		// bubble
		RADIUS("radius"),
		POINT_STYLE("pointStyle"),
		HIT_RADIUS("hitRadius"),
		// line, radar
		HOVER_BORDER_WIDTH("hoverBorderWidth"),
		HOVER_RADIUS("hoverRadius");

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
	 * Creates the item using a native java script object which contains all properties.
	 * 
	 * @param nativeObject native java script object which contains all properties.
	 */
	DatasetItemOptions(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns the edge to skip drawing the border for.
	 *
	 * @return the edge to skip drawing the border for. Default is {@link BorderSkipped#FALSE}.
	 */
	public BorderSkipped getBorderSkipped() {
		// checks if 'false' has been set
		if (ObjectType.BOOLEAN.equals(type(Property.BORDER_SKIPPED))) {
			// returns is false
			return BorderSkipped.FALSE;
		}
		// otherwise returns the enum value as string
		return getValue(Property.BORDER_SKIPPED, BorderSkipped.values(), BorderSkipped.FALSE);
	}

	/**
	 * Returns the fill color of the dataset item.
	 *
	 * @return list of the fill color of the dataset item.
	 */
	public String getBackgroundColorAsString() {
		return getValue(Property.BACKGROUND_COLOR, Defaults.get().getGlobal().getColorAsString());
	}

	/**
	 * Returns the fill color of the dataset item.
	 *
	 * @return list of the fill color of the dataset item.
	 */
	public IsColor getBackgroundColor() {
		return ColorBuilder.parse(getBackgroundColorAsString());
	}

	/**
	 * Returns the color of the dataset item border
	 *
	 * @return list of the color of the dataset item border.
	 */
	public String getBorderColorAsString() {
		return getValue(Property.BORDER_COLOR, Defaults.get().getGlobal().getColorAsString());
	}

	/**
	 * Returns the color of the dataset item border
	 *
	 * @return list of the color of the dataset item border
	 */
	public IsColor getBorderColor() {
		return ColorBuilder.parse(getBorderColorAsString());
	}

	/**
	 * Returns the stroke width of the dataset item in pixels.
	 *
	 * @return list of the stroke width of the dataset item in pixels. Default is {@link UndefinedValues#INTEGER}.
	 */
	public int getBorderWidth() {
		return getValue(Property.BORDER_WIDTH, Defaults.get().getGlobal().getElements().getPoint().getBorderWidth());
	}

	/**
	 * Returns the percent (0-1) of the available width each bar should be within the category width. 1.0 will take the whole category width and put the bars right next to each
	 * other.
	 * 
	 * @return percent (0-1) of the available width each bar should be within the category width. 1.0 will take the whole category width and put the bars right next to each other.
	 */
	public double getBarPercentage() {
		return getValue(Property.BAR_PERCENTAGE, Defaults.get().getGlobal().getDatasets().getBarPercentage());
	}

	/**
	 * Returns the percent (0-1) of the available width each category should be within the sample width.
	 * 
	 * @return the percent (0-1) of the available width each category should be within the sample width.
	 */
	public double getCategoryPercentage() {
		return getValue(Property.CATEGORY_PERCENTAGE, Defaults.get().getGlobal().getDatasets().getCategoryPercentage());
	}

	/**
	 * Returns the radius of dataset item in pixel.
	 *
	 * @return the radius of dataset item in pixel. Default is {@link UndefinedValues#DOUBLE}.
	 */
	public double getRadius() {
		return getValue(Property.RADIUS, Defaults.get().getGlobal().getElements().getPoint().getRadius());
	}

	/**
	 * Returns the style of the dataset item.
	 *
	 * @return the style of the dataset item.
	 */
	public List<PointStyle> getPointStyle() {
		// checks if the property is an array
		if (ObjectType.ARRAY.equals(type(Property.POINT_STYLE))) {
			// if array, maps into array
			ArrayString array = getArrayValue(Property.POINT_STYLE);
			// returns list
			return ArrayListHelper.unmodifiableList(PointStyle.values(), array);
		} else {
			// the property is a string or missing
			return Collections.unmodifiableList(Arrays.asList(getValue(Property.POINT_STYLE, PointStyle.values(), Defaults.get().getGlobal().getElements().getPoint().getPointStyle())));
		}
	}

	/**
	 * Returns the hit radius.
	 *
	 * @return the hit radius.
	 */
	public double getHitRadius() {
		return getValue(Property.HIT_RADIUS, Defaults.get().getGlobal().getElements().getPoint().getHitRadius());
	}

	/**
	 * Returns the hover radius.
	 *
	 * @return the hover radius.
	 */
	public double getHoverRadius() {
		return getValue(Property.HOVER_RADIUS, Defaults.get().getGlobal().getElements().getPoint().getHoverRadius());
	}

	/**
	 * Returns the hover radius.
	 *
	 * @return the hover radius.
	 */
	public double getHoverBorderWidth() {
		return getValue(Property.HOVER_BORDER_WIDTH, Defaults.get().getGlobal().getElements().getPoint().getHoverBorderWidth());
	}

}