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

import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.GenericJavaScriptObject;
import org.pepstock.charba.client.commons.JavaScriptObjectContainer;
import org.pepstock.charba.client.commons.Key;

/**
 * This item provides all information about the view where a dataset has been displayed.<br>
 * This object has been created and passed to event handler or callbacks to apply own logic.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class DatasetMetaViewItem extends JavaScriptObjectContainer {

	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
		datasetLabel,
		label,
		borderSkipped,
		backgroundColor,
		borderColor,
		borderWidth,
		horizontal,
		base,
		x,
		y,
		width,
		height
	}

	/**
	 * Wraps the CHART.JS java script object.
	 * 
	 * @param javaScriptObject CHART.JS java script object
	 */
	DatasetMetaViewItem(GenericJavaScriptObject javaScriptObject) {
		super(javaScriptObject);
	}

	/**
	 * Returns the dataset label
	 * 
	 * @return the dataset label
	 * @see org.pepstock.charba.client.data.Dataset#setLabel(String)
	 */
	public String getDatasetLabel() {
		return getValue(Property.datasetLabel, UndefinedValues.STRING);
	}

	/**
	 * Returns the label
	 * 
	 * @return the label
	 */
	public String getLabel() {
		return getValue(Property.label, UndefinedValues.STRING);
	}

	/**
	 * Returns the edge to skip drawing the border for.
	 * 
	 * @return the edge to skip drawing the border for.
	 */
	public String getBorderSkipped() {
		return getValue(Property.borderSkipped, UndefinedValues.STRING);
	}

	/**
	 * Returns the fill color of the dataset item
	 * 
	 * @return list of the fill color of the dataset item
	 */
	public String getBackgroundColorAsString() {
		return getValue(Property.backgroundColor, UndefinedValues.STRING);
	}

	/**
	 * Returns the fill color of the dataset item
	 * 
	 * @return list of the fill color of the dataset item
	 */
	public IsColor getBackgroundColor() {
		return ColorBuilder.parse(getBackgroundColorAsString());
	}

	/**
	 * Returns the color of the dataset item border
	 * 
	 * @return list of the color of the dataset item border
	 */
	public String getBorderColorAsString() {
		return getValue(Property.borderColor, UndefinedValues.STRING);
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
	 * @return list of the stroke width of the dataset item in pixels.
	 */
	public int getBorderWidth() {
		return getValue(Property.borderWidth, UndefinedValues.INTEGER);
	}

	/**
	 * Returns if is an horizontal view
	 * 
	 * @return <code>true</code> if is an horizontal view
	 */
	public boolean isHorizontal() {
		return getValue(Property.horizontal, UndefinedValues.BOOLEAN);
	}

	/**
	 * Returns the base value of dataset
	 * 
	 * @return the base value of dataset
	 */
	public int getBase() {
		return getValue(Property.base, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the X location of dataset item in pixel.
	 * 
	 * @return the X location of dataset item in pixel.
	 */
	public double getX() {
		return getValue(Property.x, UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the Y location of dataset item in pixel.
	 * 
	 * @return the Y location of dataset item in pixel.
	 */
	public double getY() {
		return getValue(Property.y, UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the width of dataset item in pixel.
	 * 
	 * @return the width of dataset item in pixel.
	 */
	public double getWidth() {
		return getValue(Property.width, UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the height of dataset item in pixel.
	 * 
	 * @return the height of dataset item in pixel.
	 */
	public double getHeight() {
		return getValue(Property.height, UndefinedValues.DOUBLE);
	}

}