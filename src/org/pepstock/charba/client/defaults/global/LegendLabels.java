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
package org.pepstock.charba.client.defaults.global;

import org.pepstock.charba.client.commons.GenericJavaScriptObject;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.defaults.FontItem;

/**
 * This is the labels configuration of the legend.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class LegendLabels extends FontItem {

	private static final int DEFAULT_PADDING = 10;
	
	private static final int DEFAULT_BOX_WIDTH = 40;

	private static final boolean DEFAULT_USE_POINT_STYLE = false;

	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
		boxWidth,
		padding,
		usePointStyle
	}
	
	/**
	 * Builds the object storing the chart instance.
	 * 
	 * @param chart chart instance
	 */
	public LegendLabels(GenericJavaScriptObject javaScriptObject) {
		super(javaScriptObject);
	}

	/**
	 * Sets if label style will match corresponding point style (size is based on fontSize, boxWidth is not used in this case).
	 * 
	 * @param usePointStyle if label style will match corresponding point style (size is based on fontSize, boxWidth is not used in this case).
	 */
	public void setUsePointStyle(boolean usePointStyle) {
		setValue(Property.usePointStyle, usePointStyle);
	}

	/**
	 * Returns if label style will match corresponding point style (size is based on fontSize, boxWidth is not used in this case).
	 * 
	 * @return if label style will match corresponding point style (size is based on fontSize, boxWidth is not used in this case). Default is false.
	 */
	public boolean isUsePointStyle() {
		return getValue(Property.usePointStyle, DEFAULT_USE_POINT_STYLE);
	}

	/**
	 * Sets the width of coloured box.
	 * 
	 * @param boxWidth width of coloured box.
	 */
	public void setBoxWidth(int boxWidth) {
		setValue(Property.boxWidth, boxWidth);
	}

	/**
	 * Returns the width of coloured box.
	 * 
	 * @return width of coloured box. Default is 40.
	 */
	public int getBoxWidth() {
		return getValue(Property.boxWidth, DEFAULT_BOX_WIDTH);
	}

	/**
	 * Sets the padding to apply around labels. Only top and bottom are implemented.
	 * 
	 * @param padding Padding to apply around labels. Only top and bottom are implemented.
	 */
	public void setPadding(int padding) {
		setValue(Property.padding, padding);
	}

	/**
	 * Returns the padding to apply around labels. Only top and bottom are implemented.
	 * 
	 * @return Padding to apply around labels. Only top and bottom are implemented. Default is 10.
	 */
	public int getPadding() {
		return getValue(Property.padding, DEFAULT_PADDING);
	}

}