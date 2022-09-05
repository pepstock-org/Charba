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
import org.pepstock.charba.client.items.ChartElement;
import org.pepstock.charba.client.items.ChartElementFactory;
import org.pepstock.charba.client.items.Undefined;

/**
 * Maps the CHART.JS element used by Matrix controller.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class MatrixElement extends ChartElement {

	/**
	 * MATRIX element type.
	 */
	public static final String TYPE = "matrix";
	/**
	 * Static instance for the MATRIX element factory
	 */
	public static final ChartElementFactory FACTORY = new MatrixElementFactory();

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
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
	MatrixElement(NativeObject nativeObject) {
		super(TYPE, nativeObject);
	}

	/**
	 * Returns the element options.
	 *
	 * @return the element options.
	 */
	@Override
	public MatrixElementOptions getOptions() {
		return (MatrixElementOptions) super.getOptions();
	}

	/**
	 * Returns the width of data set item in pixel.
	 * 
	 * @return the width of data set item in pixel.
	 */
	public double getWidth() {
		return getValue(Property.WIDTH, Undefined.DOUBLE);
	}

	/**
	 * Returns the height of data set item in pixel.
	 * 
	 * @return the height of data set item in pixel.
	 */
	public double getHeight() {
		return getValue(Property.HEIGHT, Undefined.DOUBLE);
	}

	/**
	 * Inner class to create matrix data element by a native object.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	private static class MatrixElementFactory implements ChartElementFactory {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.commons.NativeObject)
		 */
		@Override
		public MatrixElement create(NativeObject nativeObject) {
			return new MatrixElement(nativeObject);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.items.ElementFactory#getType()
		 */
		@Override
		public String getType() {
			return TYPE;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.items.ChartElementFactory#createOptions(org.pepstock.charba.client.commons.NativeObject)
		 */
		@Override
		public MatrixElementOptions createOptions(NativeObject nativeObject) {
			return new MatrixElementOptions(nativeObject);
		}

	}

}