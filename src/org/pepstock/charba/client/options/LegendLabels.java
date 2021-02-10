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
public final class LegendLabels extends AbstractModel<Legend, IsDefaultLegendLabels> implements IsDefaultLegendLabels, HasBox, HasPointStyle, HasFont {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
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

	// instance of font container
	private final FontContainer fontContainer;
	// instance of box handler
	private final BoxHandler boxHandler;
	// instance of style of points manager
	private final PointStyler pointStyler;

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param legend legend of the chart.
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	LegendLabels(Legend legend, Key childKey, IsDefaultLegendLabels defaultValues, NativeObject nativeObject) {
		super(legend, childKey, defaultValues, nativeObject);
		// creates the box handler
		this.boxHandler = new BoxHandler(this, getDefaultValues(), getNativeObject());
		// creates point styler
		this.pointStyler = new PointStyler(this, getDefaultValues(), getNativeObject());
		// creates font container
		this.fontContainer = new FontContainer(this, getDefaultValues(), getNativeObject());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.HasBox#getBoxHandler()
	 */
	@Override
	public BoxHandler getBoxHandler() {
		return boxHandler;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.HasPointStyle#getPointStyler()
	 */
	@Override
	public PointStyler getPointStyler() {
		return pointStyler;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.HasFont#getFontContainer()
	 */
	@Override
	public FontContainer getFontContainer() {
		return fontContainer;
	}

	/**
	 * Sets if label style will match corresponding point style (size is based on font size, boxWidth is not used in this case).
	 * 
	 * @param usePointStyle if label style will match corresponding point style (size is based on font size, boxWidth is not used in this case).
	 */
	public void setUsePointStyle(boolean usePointStyle) {
		setValueAndAddToParent(Property.USE_POINT_STYLE, usePointStyle);
	}

	/**
	 * Returns if label style will match corresponding point style (size is based on font size, boxWidth is not used in this case).
	 * 
	 * @return <code>true</code> if label style will match corresponding point style (size is based on font size, boxWidth is not used in this case).
	 */
	@Override
	public boolean isUsePointStyle() {
		return getValue(Property.USE_POINT_STYLE, getDefaultValues().isUsePointStyle());
	}

	/**
	 * Sets the padding to apply around labels. Only top and bottom are implemented.
	 * 
	 * @param padding padding to apply around labels. Only top and bottom are implemented.
	 */
	public void setPadding(int padding) {
		setValueAndAddToParent(Property.PADDING, padding);
	}

	/**
	 * Returns the padding to apply around labels. Only top and bottom are implemented.
	 * 
	 * @return padding to apply around labels. Only top and bottom are implemented.
	 */
	@Override
	public int getPadding() {
		return getValue(Property.PADDING, getDefaultValues().getPadding());
	}

}