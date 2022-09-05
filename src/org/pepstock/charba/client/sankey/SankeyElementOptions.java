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
package org.pepstock.charba.client.sankey;

import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.items.ChartElementOptions;
import org.pepstock.charba.client.sankey.enums.ColorMode;

/**
 * Maps the out-of-the-box CHART.JS element options used to represents flows on the charts.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class SankeyElementOptions extends ChartElementOptions {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		COLOR_FROM("colorFrom"),
		COLOR_MODE("colorMode"),
		COLOR_TO("colorTo");

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
	SankeyElementOptions(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns the color mode between sankey elements.
	 * 
	 * @return the color mode between sankey elements
	 */
	public ColorMode getColorMode() {
		return getValue(Property.COLOR_MODE, ColorMode.values(), ColorMode.GRADIENT);
	}

	/**
	 * Returns the starting color of the flow between sankey elements.
	 * 
	 * @return the starting color of the flow between sankey elements
	 */
	public String getColorFromAsString() {
		return getValue(Property.COLOR_FROM, SankeyDataset.DEFAULT_COLOR_FROM);
	}

	/**
	 * Returns the starting color of the flow between sankey elements.
	 * 
	 * @return the starting color of the flow between sankey elements
	 */
	public IsColor getColorFrom() {
		return ColorBuilder.parse(getColorFromAsString());
	}

	/**
	 * Returns the ending color of the flow between sankey elements.
	 * 
	 * @return the ending color of the flow between sankey elements
	 */
	public String getColorToAsString() {
		return getValue(Property.COLOR_TO, SankeyDataset.DEFAULT_COLOR_TO);
	}

	/**
	 * Returns the ending color of the flow between sankey elements.
	 * 
	 * @return the ending color of the flow between sankey elements
	 */
	public IsColor getColorTo() {
		return ColorBuilder.parse(getColorToAsString());
	}
}