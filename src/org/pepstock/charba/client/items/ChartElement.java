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
 * This is the CHART.JS item with all needed info about a selected data set.<br>
 * This object has been created and passed to event handler or callbacks to apply own logic.
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

	// data set item options instance
	private final DatasetElementOptions options;

	/**
	 * Creates the item using a native java script object which contains all properties.
	 * 
	 * @param nativeObject native java script object which contains all properties.
	 */
	protected ChartElement(NativeObject nativeObject) {
		super(nativeObject);
		// sets the data set item options
		// FIXME
		this.options = new DatasetElementOptions(getValue(Property.OPTIONS));
	}

	/**
	 * FIXME Returns the data set item options.
	 *
	 * @return the data set item options.
	 */
	public final DatasetElementOptions getOptions() {
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
	 * Inner class to create data element by a native object.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	private static class UndefinedDataElementFactory implements ChartElementFactory<ChartElement> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.commons.NativeObject)
		 */
		@Override
		public ChartElement create(NativeObject nativeObject) {
			return new ChartElement(nativeObject);
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

	}
}