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
import org.pepstock.charba.client.defaults.IsDefaultLegendLabels;

/**
 * This is the labels configuration of the legend.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class LegendLabels extends FontItem<Legend, IsDefaultLegendLabels> implements IsDefaultLegendLabels {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		boxWidth,
		padding,
		usePointStyle
	}

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script
	 * properties.
	 * 
	 * @param legend legend of the chart.
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	LegendLabels(Legend legend, Key childKey, IsDefaultLegendLabels defaultValues, NativeObject nativeObject) {
		super(legend, childKey, defaultValues, nativeObject);
	}

	/**
	 * Sets if label style will match corresponding point style (size is based on fontSize, boxWidth is not used in this case).
	 * 
	 * @param usePointStyle if label style will match corresponding point style (size is based on fontSize, boxWidth is not used
	 *            in this case).
	 */
	public void setUsePointStyle(boolean usePointStyle) {
		setValue(Property.usePointStyle, usePointStyle);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns if label style will match corresponding point style (size is based on fontSize, boxWidth is not used in this
	 * case).
	 * 
	 * @return <code>true</code> if label style will match corresponding point style (size is based on fontSize, boxWidth is not used in this
	 *         case).
	 */
	public boolean isUsePointStyle() {
		return getValue(Property.usePointStyle, getDefaultValues().isUsePointStyle());
	}

	/**
	 * Sets the width of colored box.
	 * 
	 * @param boxWidth width of colored box.
	 */
	public void setBoxWidth(int boxWidth) {
		setValue(Property.boxWidth, boxWidth);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the width of colored box.
	 * 
	 * @return width of colored box.
	 */
	public int getBoxWidth() {
		return getValue(Property.boxWidth, getDefaultValues().getBoxWidth());
	}

	/**
	 * Sets the padding to apply around labels. Only top and bottom are implemented.
	 * 
	 * @param padding padding to apply around labels. Only top and bottom are implemented.
	 */
	public void setPadding(int padding) {
		setValue(Property.padding, padding);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the padding to apply around labels. Only top and bottom are implemented.
	 * 
	 * @return padding to apply around labels. Only top and bottom are implemented.
	 */
	public int getPadding() {
		return getValue(Property.padding, getDefaultValues().getPadding());
	}

}