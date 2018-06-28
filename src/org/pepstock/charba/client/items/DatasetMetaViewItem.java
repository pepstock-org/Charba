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
import org.pepstock.charba.client.commons.Key;

/**
 * This item provides all information about the view where a dataset has been displayed.<br>
 * This object has been created and passed to event handler or callbacks to apply own logic.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class DatasetMetaViewItem extends BaseItem {

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
	 * Needed for GWt injection
	 */
	protected DatasetMetaViewItem() {
		// do nothing
	}

	/**
	 * Returns the dataset label
	 * 
	 * @return the dataset label
	 * @see org.pepstock.charba.client.data.Dataset#setLabel(String)
	 */
	public final String getDatasetLabel() {
		return getString(Property.datasetLabel.name());
	}

	/**
	 * Returns the label
	 * 
	 * @return the label
	 */
	public final String getLabel() {
		return getString(Property.label.name());
	}

	/**
	 * Returns the edge to skip drawing the border for.
	 * 
	 * @return the edge to skip drawing the border for.
	 */
	public final String getBorderSkipped() {
		return getString(Property.borderSkipped.name());
	}

	/**
	 * Returns the fill color of the dataset item
	 * 
	 * @return list of the fill color of the dataset item
	 */
	public final String getBackgroundColorAsString() {
		return getString(Property.backgroundColor.name());
	}

	/**
	 * Returns the fill color of the dataset item
	 * 
	 * @return list of the fill color of the dataset item
	 */
	public final IsColor getBackgroundColor() {
		return ColorBuilder.parse(getBackgroundColorAsString());
	}

	/**
	 * Returns the color of the dataset item border
	 * 
	 * @return list of the color of the dataset item border
	 */
	public final String getBorderColorAsString() {
		return getString(Property.borderColor.name());
	}

	/**
	 * Returns the color of the dataset item border
	 * 
	 * @return list of the color of the dataset item border
	 */
	public final IsColor getBorderColor() {
		return ColorBuilder.parse(getBorderColorAsString());
	}
	
	/**
	 * Returns the stroke width of the dataset item in pixels.
	 * 
	 * @return list of the stroke width of the dataset item in pixels.
	 */
	public final int getBorderWidth() {
		return getInt(Property.borderWidth.name());
	}

	/**
	 * Returns if is an horizontal view
	 * 
	 * @return <code>true</code> if is an horizontal view
	 */
	public final boolean isHorizontal() {
		return getBoolean(Property.horizontal.name());
	}

	/**
	 * Returns the base value of dataset
	 * 
	 * @return the base value of dataset
	 */
	public final int getBase() {
		return getInt(Property.base.name());
	}

	/**
	 * Returns the X location of dataset item in pixel.
	 * 
	 * @return the X location of dataset item in pixel.
	 */
	public final double getX() {
		return getDouble(Property.x.name());
	}

	/**
	 * Returns the Y location of dataset item in pixel.
	 * 
	 * @return the Y location of dataset item in pixel.
	 */
	public final double getY() {
		return getDouble(Property.y.name());
	}

	/**
	 * Returns the width of dataset item in pixel.
	 * 
	 * @return the width of dataset item in pixel.
	 */
	public final double getWidth() {
		return getDouble(Property.width.name());
	}

	/**
	 * Returns the height of dataset item in pixel.
	 * 
	 * @return the height of dataset item in pixel.
	 */
	public final double getHeight() {
		return getDouble(Property.height.name());
	}

}