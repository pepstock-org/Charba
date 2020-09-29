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
import org.pepstock.charba.client.defaults.IsDefaultLegendTitle;
import org.pepstock.charba.client.items.HasLegendText;
import org.pepstock.charba.client.items.LegendTexter;

/**
 * This is the title configuration of the legend.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class LegendTitle extends AbstractModel<Legend, IsDefaultLegendTitle> implements IsDefaultLegendTitle, HasLegendText {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		DISPLAY("display"),
		FONT("font"),
		PADDING("padding");

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

	// instance of font
	private final Font font;
	// legend texter instance
	private final LegendTexter legendtexter;

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param legend legend of the chart.
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	LegendTitle(Legend legend, Key childKey, IsDefaultLegendTitle defaultValues, NativeObject nativeObject) {
		super(legend, childKey, defaultValues, nativeObject);
		this.font = new Font(this, Property.FONT, getDefaultValues().getFont(), getValue(Property.FONT));
		// creates the legend texter
		this.legendtexter = new LegendTexter(new OptionsEnvelop<>(getNativeObject()), legend);
	}

	/**
	 * Returns the font element.
	 * 
	 * @return the font
	 */
	@Override
	public Font getFont() {
		return font;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.items.HasLegendText#getLegendTexter()
	 */
	@Override
	public LegendTexter getLegendTexter() {
		return legendtexter;
	}

	/**
	 * Sets <code>true</code> if the title is shown.
	 * 
	 * @param display if <code>true</code> the title is shown.
	 */
	public void setDisplay(boolean display) {
		setValue(Property.DISPLAY, display);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns <code>true</code> if the title is shown.
	 * 
	 * @return if <code>true</code> the title is shown.
	 */
	@Override
	public boolean isDisplay() {
		return getValue(Property.DISPLAY, getDefaultValues().isDisplay());
	}

	/**
	 * Sets the padding to apply around title.
	 * 
	 * @param padding padding to apply around title.
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
	@Override
	public int getPadding() {
		return getValue(Property.PADDING, getDefaultValues().getPadding());
	}

}