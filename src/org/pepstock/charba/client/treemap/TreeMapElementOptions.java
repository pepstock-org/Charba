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
package org.pepstock.charba.client.treemap;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.data.BarBorderWidth;
import org.pepstock.charba.client.items.CommonElementOptions;

/**
 * Maps the out-of-the-box CHART.JS element options used to represents boxes for treemap on the charts.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class TreeMapElementOptions extends CommonElementOptions {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		BORDER_WIDTH("borderWidth");

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
	TreeMapElementOptions(NativeObject nativeObject) {
		super(nativeObject);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.items.ChartElementOptions#getDefaultBorderWidth()
	 */
	@Override
	protected int getDefaultBorderWidth() {
		return TreeMapDataset.DEFAULT_BORDER_WIDTH;
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
	 * Returns <code>true</code> if the border width is defined as {@link BarBorderWidth}.
	 * 
	 * @return <code>true</code> if the border width is defined as {@link BarBorderWidth}
	 */
	public boolean isBorderWidthAsObject() {
		return isType(Property.BORDER_WIDTH, ObjectType.OBJECT);
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
}