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
package org.pepstock.charba.client.items;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;

/**
 * Maps the out-of-the-box CHART.JS element used to represents bars on the charts.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class BarElement extends ChartElement {

	/**
	 * BAR element type.
	 */
	public static final String TYPE = "bar";
	/**
	 * Static instance for the BAR element factory
	 */
	public static final ChartElementFactory<BarElement> FACTORY = new BarElementFactory();

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		BASE("base"),
		HEIGHT("height"),
		HORIZONTAL("horizontal"),
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
	BarElement(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns if is an horizontal view.
	 * 
	 * @return <code>true</code> if is an horizontal view.
	 */
	public final boolean isHorizontal() {
		return getValue(Property.HORIZONTAL, Undefined.BOOLEAN);
	}

	/**
	 * Returns the base value of data set.
	 * 
	 * @return the base value of data set.
	 */
	public final double getBase() {
		return getValue(Property.BASE, Undefined.DOUBLE);
	}

	/**
	 * Returns the width of data set item in pixel.
	 * 
	 * @return the width of data set item in pixel.
	 */
	public final double getWidth() {
		return getValue(Property.WIDTH, Undefined.DOUBLE);
	}

	/**
	 * Returns the height of data set item in pixel.
	 * 
	 * @return the height of data set item in pixel.
	 */
	public final double getHeight() {
		return getValue(Property.HEIGHT, Undefined.DOUBLE);
	}

	/**
	 * Inner class to create BAR data element by a native object.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	private static class BarElementFactory implements ChartElementFactory<BarElement> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.commons.NativeObject)
		 */
		@Override
		public BarElement create(NativeObject nativeObject) {
			return new BarElement(nativeObject);
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
	}

}