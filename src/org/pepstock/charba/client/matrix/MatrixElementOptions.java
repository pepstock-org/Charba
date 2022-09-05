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
package org.pepstock.charba.client.matrix;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.data.BarBorderRadius;
import org.pepstock.charba.client.items.CommonElementOptions;
import org.pepstock.charba.client.matrix.enums.Anchor;

/**
 * Maps the out-of-the-box CHART.JS element options used to represents boxes for matrix on the charts.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class MatrixElementOptions extends CommonElementOptions {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		BORDER_RADIUS("borderRadius"),
		ANCHOR_X("anchorX"),
		ANCHOR_Y("anchorY"),
		HEIGHT("height"),
		WIDTH("width");

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
	MatrixElementOptions(NativeObject nativeObject) {
		super(nativeObject);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.items.ChartElementOptions#getDefaultBorderWidth()
	 */
	@Override
	protected int getDefaultBorderWidth() {
		return MatrixDataset.DEFAULT_BORDER_WIDTH;
	}

	/**
	 * Returns the height of matrix element in pixels.
	 * 
	 * @return the height of matrix element in pixels
	 */
	public double getHeight() {
		return getValue(Property.HEIGHT, MatrixDataset.DEFAULT_HEIGHT);
	}

	/**
	 * Returns the width of matrix element in pixels.
	 * 
	 * @return the width of matrix element in pixels.
	 */
	public double getWidth() {
		return getValue(Property.WIDTH, MatrixDataset.DEFAULT_WIDTH);
	}

	/**
	 * Returns the anchor point on X orientation of matrix element.
	 * 
	 * @return the anchor point on X orientation of matrix element
	 */
	public Anchor getXAnchor() {
		return getValue(Property.ANCHOR_X, Anchor.values(), Anchor.CENTER);
	}

	/**
	 * Returns the anchor point on Y orientation of matrix element.
	 * 
	 * @return the anchor point on Y orientation of matrix element
	 */
	public Anchor getYAnchor() {
		return getValue(Property.ANCHOR_Y, Anchor.values(), Anchor.CENTER);
	}

	/**
	 * Returns <code>true</code> if the border width is defined as {@link BarBorderRadius}.
	 * 
	 * @return <code>true</code> if the border width is defined as {@link BarBorderRadius}
	 */
	public boolean isBorderRadiusAsObject() {
		return isType(Property.BORDER_RADIUS, ObjectType.OBJECT);
	}

	/**
	 * Returns the bar border radius (in pixels).
	 * 
	 * @return the bar border radius (in pixels).
	 */
	public int getBorderRadius() {
		// checks if border radius is an object
		if (isBorderRadiusAsObject()) {
			// gets the border radius object
			// then returns the average
			return BarBorderRadius.FACTORY.create(getValue(Property.BORDER_RADIUS)).average();
		}
		// if here, the border radius is a number or missing
		return getValue(Property.BORDER_RADIUS, MatrixDataset.DEFAULT_BORDER_RADIUS);
	}

	/**
	 * Returns the border radius of the dataset item in pixels as {@link BarBorderRadius}.
	 *
	 * @return the border radius of the dataset item in pixels as {@link BarBorderRadius}.
	 */
	public BarBorderRadius getBarBorderRadius() {
		// checks if border radius is an object
		if (isBorderRadiusAsObject()) {
			// gets the border radius object
			return BarBorderRadius.FACTORY.create(getValue(Property.BORDER_RADIUS));
		}
		// if here, the border radius is a number or missing
		// then returns a new object with same value
		return new BarBorderRadius(getValue(Property.BORDER_RADIUS, MatrixDataset.DEFAULT_BORDER_RADIUS));
	}
}