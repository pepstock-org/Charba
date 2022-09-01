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
import org.pepstock.charba.client.items.ChartElement;
import org.pepstock.charba.client.items.ChartElementFactory;
import org.pepstock.charba.client.items.Undefined;

/**
 * FIXME Calling some methods on your chart instance passing an argument of an event, will return the elements at the event position.<br>
 * The elements are mapped by this object.<br>
 * This is the CHART.JS item with all needed info about a selected data set.<br>
 * This object has been created and passed to event handler or callbacks to apply own logic.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class TreeMapElement extends ChartElement {

	/**
	 * TREEMAP element type.
	 */
	public static final String TYPE = "treemap";
	/**
	 * Static instance for the TREEMAP element factory
	 */
	public static final ChartElementFactory<TreeMapElement> FACTORY = new TreeMapElementFactory();

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
	TreeMapElement(NativeObject nativeObject) {
		super(nativeObject);
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
	 * Inner class to create data set item by a native object.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	private static class TreeMapElementFactory implements ChartElementFactory<TreeMapElement> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.commons.NativeObject)
		 */
		@Override
		public TreeMapElement create(NativeObject nativeObject) {
			return new TreeMapElement(nativeObject);
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