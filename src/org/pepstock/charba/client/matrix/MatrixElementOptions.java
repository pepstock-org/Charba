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

import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.data.BarBorderRadius;
import org.pepstock.charba.client.data.BarBorderWidth;
import org.pepstock.charba.client.items.CommonElementOptions;
import org.pepstock.charba.client.matrix.enums.Anchor;
import org.pepstock.charba.client.options.AbstractElementFactory;
import org.pepstock.charba.client.options.ElementFactory;

/**
 * Maps the out-of-the-box CHART.JS element options used to represents boxes for matrix on the charts.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class MatrixElementOptions extends CommonElementOptions {

	/**
	 * Element factory to get "{@value MatrixElement#TYPE}" element.
	 */
	public static final ElementFactory<MatrixElementOptions> FACTORY = new MatrixElementOptionsFactory(MatrixElement.TYPE);

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		BORDER_RADIUS("borderRadius"),
		BORDER_WIDTH("borderWidth"),
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
	 * Sets the height of matrix element in pixels.
	 * 
	 * @param height the height of matrix element in pixels
	 */
	public void setHeight(double height) {
		setValue(Property.HEIGHT, Checker.positiveOrZero(height));
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
	 * Sets the width of matrix element in pixels.
	 * 
	 * @param width the width of matrix element in pixels
	 */
	public void setWidth(double width) {
		setValue(Property.WIDTH, Checker.positiveOrZero(width));
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
	 * Sets the anchor point on X orientation of matrix element.
	 * 
	 * @param anchor the anchor point on X orientation of matrix element
	 */
	public void setXAnchor(Anchor anchor) {
		// checks if the values of anchor are correct for x orientation
		if (!Anchor.BOTTOM.equals(anchor) && !Anchor.TOP.equals(anchor)) {
			setValue(Property.ANCHOR_X, anchor);
		}
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
	 * Sets the anchor point on Y orientation of matrix element.
	 * 
	 * @param anchor the anchor point on Y orientation of matrix element
	 */
	public void setYAnchor(Anchor anchor) {
		// checks if the values of anchor are correct for x orientation
		if (!Anchor.LEFT.equals(anchor) && !Anchor.RIGHT.equals(anchor)) {
			setValue(Property.ANCHOR_Y, anchor);
		}
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
	 * Returns <code>true</code> if the border width is defined as {@link BarBorderRadius}.
	 * 
	 * @return <code>true</code> if the border width is defined as {@link BarBorderRadius}
	 */
	public boolean isBorderRadiusAsObject() {
		return isType(Property.BORDER_RADIUS, ObjectType.OBJECT);
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

	/**
	 * Sets the border radius (in pixels).
	 * 
	 * @param borderRadius the border radius (in pixels).
	 */
	public void setBorderRadius(int borderRadius) {
		setValue(Property.BORDER_RADIUS, Checker.positiveOrZero(borderRadius));
	}

	/**
	 * Sets the border radius (in pixels).
	 * 
	 * @param borderRadius the border radius (in pixels).
	 */
	public void setBorderRadius(BarBorderRadius borderRadius) {
		setValue(Property.BORDER_RADIUS, borderRadius);
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

	/**
	 * Sets the border width (in pixels).
	 * 
	 * @param borderWidth the border width (in pixels).
	 */
	public void setBorderWidth(BarBorderWidth borderWidth) {
		setValue(Property.BORDER_WIDTH, borderWidth);
	}

	/**
	 * Specific element factory for matrix element options.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class MatrixElementOptionsFactory extends AbstractElementFactory<MatrixElementOptions> {

		/**
		 * Creates the factory by the key of object, as string.
		 * 
		 * @param elementKeyAsString the key of object, as string.
		 */
		private MatrixElementOptionsFactory(String elementKeyAsString) {
			super(elementKeyAsString);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.commons.NativeObject)
		 */
		@Override
		public MatrixElementOptions create(NativeObject nativeObject) {
			return new MatrixElementOptions(nativeObject);
		}

	}
}