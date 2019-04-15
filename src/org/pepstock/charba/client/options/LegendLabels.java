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

import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.IsDefaultLegendLabels;
import org.pepstock.charba.client.enums.FontStyle;

/**
 * This is the labels configuration of the legend.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class LegendLabels extends AbstractModel<Legend, IsDefaultLegendLabels> implements IsDefaultLegendLabels, HasFont {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		BOX_WIDTH("boxWidth"),
		PADDING("padding"),
		USE_POINT_STYLE("usePointStyle");

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
	
	// instance of font manager
	private final Fonter fonter;

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
		this.fonter = new Fonter(getNativeObject(), getDefaultValues());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.HasFont#getFonter()
	 */
	@Override
	public final Fonter getFonter() {
		return fonter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.HasFont#setFontSize(int)
	 */
	@Override
	public void setFontSize(int fontSize) {
		HasFont.super.setFontSize(fontSize);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.HasFont#setFontStyle(org.pepstock.charba.client.enums.FontStyle)
	 */
	@Override
	public void setFontStyle(FontStyle fontStyle) {
		HasFont.super.setFontStyle(fontStyle);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.HasFont#setFontColor(org.pepstock.charba.client.colors.IsColor)
	 */
	@Override
	public void setFontColor(IsColor fontColor) {
		HasFont.super.setFontColor(fontColor);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.HasFont#setFontColor(java.lang.String)
	 */
	@Override
	public void setFontColor(String fontColor) {
		HasFont.super.setFontColor(fontColor);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.HasFont#setFontFamily(java.lang.String)
	 */
	@Override
	public void setFontFamily(String fontFamily) {
		HasFont.super.setFontFamily(fontFamily);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Sets if label style will match corresponding point style (size is based on fontSize, boxWidth is not used in this case).
	 * 
	 * @param usePointStyle if label style will match corresponding point style (size is based on fontSize, boxWidth is not used
	 *            in this case).
	 */
	public void setUsePointStyle(boolean usePointStyle) {
		setValue(Property.USE_POINT_STYLE, usePointStyle);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns if label style will match corresponding point style (size is based on fontSize, boxWidth is not used in this
	 * case).
	 * 
	 * @return <code>true</code> if label style will match corresponding point style (size is based on fontSize, boxWidth is not
	 *         used in this case).
	 */
	public boolean isUsePointStyle() {
		return getValue(Property.USE_POINT_STYLE, getDefaultValues().isUsePointStyle());
	}

	/**
	 * Sets the width of colored box.
	 * 
	 * @param boxWidth width of colored box.
	 */
	public void setBoxWidth(int boxWidth) {
		setValue(Property.BOX_WIDTH, boxWidth);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the width of colored box.
	 * 
	 * @return width of colored box.
	 */
	public int getBoxWidth() {
		return getValue(Property.BOX_WIDTH, getDefaultValues().getBoxWidth());
	}

	/**
	 * Sets the padding to apply around labels. Only top and bottom are implemented.
	 * 
	 * @param padding padding to apply around labels. Only top and bottom are implemented.
	 */
	public void setPadding(int padding) {
		setValue(Property.PADDING, padding);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the padding to apply around labels. Only top and bottom are implemented.
	 * 
	 * @return padding to apply around labels. Only top and bottom are implemented.
	 */
	public int getPadding() {
		return getValue(Property.PADDING, getDefaultValues().getPadding());
	}

}