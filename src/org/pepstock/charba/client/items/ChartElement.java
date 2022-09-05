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

import org.pepstock.charba.client.commons.AbstractReadOnlyPoint;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;

/**
 * Calling some methods on your chart instance passing an argument of an event, will return the elements at the event position.<br>
 * The elements are mapped by this object.<br>
 * This is the CHART.JS item with all needed info about a data element, used to draw the chart.<br>
 * This is the base element created by CHART.JS which is extended by the controllers to manage own elements.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class ChartElement extends AbstractReadOnlyPoint {

	// static instance for the data set item factory
	static final UndefinedDataElementFactory FACTORY = new UndefinedDataElementFactory();
	// data element type when undefined
	static final String UNDEFINED_TYPE = "_undefined";

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		ACTIVE("active"),
		OPTIONS("options"),
		SKIP("skip"),
		STOP("stop");

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

	// element type instance
	private final String type;
	// element options instance
	private final ChartElementOptions options;

	/**
	 * Creates the element using its type and a native java script object which contains all properties.
	 * 
	 * @param type chart element type
	 * @param nativeObject native java script object which contains all properties.
	 */
	protected ChartElement(String type, NativeObject nativeObject) {
		super(nativeObject);
		// gets factory
		ChartElementFactory factory = ChartElementFactories.get().getFactory(type);
		// stores chart element type
		this.type = factory.getType();
		// sets the element options
		this.options = factory.createOptions(getValue(Property.OPTIONS));
	}

	/**
	 * Returns the data set item options.
	 *
	 * @return the data set item options.
	 */
	public final String getType() {
		return type;
	}

	/**
	 * Returns the element options.
	 *
	 * @return the element options.
	 */
	public ChartElementOptions getOptions() {
		return options;
	}

	/**
	 * Returns if element is active.
	 * 
	 * @return <code>true</code> if the element is active.
	 */
	public final boolean isActive() {
		return getValue(Property.ACTIVE, Undefined.BOOLEAN);
	}

	/**
	 * Returns <code>true</code> if skipped.
	 * 
	 * @return <code>true</code> if skipped.
	 */
	public final boolean isSkipped() {
		return getValue(Property.SKIP, Undefined.BOOLEAN);
	}

	/**
	 * Returns <code>true</code> if stopped.
	 * 
	 * @return <code>true</code> if stopped.
	 */
	public final boolean isStop() {
		return getValue(Property.STOP, Undefined.BOOLEAN);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public final String toString() {
		return "Element [x=" + getX() + ", y=" + getY() + "]";
	}

	/**
	 * Inner class to create a chart element by a native object.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	private static class UndefinedDataElementFactory implements ChartElementFactory {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.commons.NativeObject)
		 */
		@Override
		public ChartElement create(NativeObject nativeObject) {
			return new ChartElement(UNDEFINED_TYPE, nativeObject);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.items.DataElementFactory#getType()
		 */
		@Override
		public String getType() {
			return UNDEFINED_TYPE;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.items.ChartElementFactory#createOptions(org.pepstock.charba.client.commons.NativeObject)
		 */
		@Override
		public ChartElementOptions createOptions(NativeObject nativeObject) {
			return new ChartElementOptions(nativeObject);
		}

	}
}