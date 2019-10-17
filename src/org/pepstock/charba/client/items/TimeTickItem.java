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

import java.util.Date;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;
import org.pepstock.charba.client.configuration.CartesianTimeTick;

import com.google.gwt.core.client.JsDate;

/**
 * The time tick item passed when a tick callback has been set for cartesian time axis, {@link CartesianTimeTick}.<br>
 * This is a wrapper of the CHART.JS item with all needed info.<br>
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class TimeTickItem extends ScaleTickItem {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		VALUE("value");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use into native object.
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
	TimeTickItem(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns the date of the tick.
	 * 
	 * @return the date of the tick or <code>null</code> if missing.
	 */
	public Date getValue() {
		// gets the value
		double timeValue = getValue(Property.VALUE, UndefinedValues.DOUBLE);
		// checks if value is consistent
		if (Double.isNaN(timeValue)) {
			// if not, returns null
			return null;
		}
		// gets the value, creating a js date and returning a date
		return new Date((long) JsDate.create(timeValue).getTime());
	}

	/**
	 * Inner class to create time tick item by a native object.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	public static final class TimeTickItemFactory implements NativeObjectContainerFactory<TimeTickItem> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.commons.
		 * NativeObject)
		 */
		@Override
		public TimeTickItem create(NativeObject nativeObject) {
			return new TimeTickItem(nativeObject);
		}
	}

}