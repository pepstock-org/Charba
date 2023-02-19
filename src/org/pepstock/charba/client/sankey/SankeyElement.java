/**
    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.
*/
package org.pepstock.charba.client.sankey;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.items.ChartContextElementFactory;
import org.pepstock.charba.client.items.ChartElement;
import org.pepstock.charba.client.items.ChartElementFactory;
import org.pepstock.charba.client.items.Undefined;

/**
 * Maps the CHART.JS element used by Sankey controller.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class SankeyElement extends ChartElement {

	/**
	 * SANKEY element type.
	 */
	public static final String TYPE = "flow";
	/**
	 * Static instance for the SANKEY element factory
	 */
	public static final ChartElementFactory FACTORY = new SankeyElementFactory();

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		HEIGHT("height"),
		X2("x2"),
		Y2("y2");

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
	SankeyElement(NativeObject nativeObject) {
		super(TYPE, nativeObject);
	}

	/**
	 * Returns the element options.
	 *
	 * @return the element options.
	 */
	@Override
	public SankeyElementOptions getOptions() {
		return (SankeyElementOptions) super.getOptions();
	}

	/**
	 * Returns the X2 location of element in pixel.
	 * 
	 * @return the X2 location of element in pixel.
	 */
	public double getX2() {
		return getValue(Property.X2, Undefined.DOUBLE);
	}

	/**
	 * Returns the Y location of element in pixel.
	 * 
	 * @return the Y location of element in pixel.
	 */
	public double getY2() {
		return getValue(Property.Y2, Undefined.DOUBLE);
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
	 * Inner class to create SANKEY data element by a native object.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	private static class SankeyElementFactory implements ChartContextElementFactory {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.commons.NativeObject)
		 */
		@Override
		public SankeyElement create(NativeObject nativeObject) {
			return new SankeyElement(nativeObject);
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
		 * @see org.pepstock.charba.client.items.ChartElementFactory#createOptions(org.pepstock.charba.client.items.ChartElement, org.pepstock.charba.client.commons.NativeObject)
		 */
		@Override
		public SankeyElementOptions createOptions(ChartElement parent, NativeObject nativeObject) {
			return new SankeyElementOptions(nativeObject);
		}
	}

}