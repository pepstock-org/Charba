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
package org.pepstock.charba.client.colors;

import java.util.concurrent.atomic.AtomicInteger;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.items.UndefinedValues;

/**
 * Base object for pattern and gradient instances, based on canvas element.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public abstract class CanvasObject extends NativeObjectContainer {

	// internal counter
	private static final AtomicInteger counter = new AtomicInteger(0);

	/**
	 * Name of properties of native object. ALL INTERNAL USE ONLY
	 */
	private enum Property implements Key
	{
		CHARBA_OBJECT_ID("_charbaObjectID");

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
	 * Creates an empty canvas object.
	 */
	CanvasObject() {
		super();
		// increments the id
		// unique for every canvas object
		// stores the ID
		setValue(Property.CHARBA_OBJECT_ID, counter.getAndIncrement());
	}

	/**
	 * Creates a canvas object by a native object.
	 * 
	 * @param nativeObject native object which will be wrapped.
	 */
	CanvasObject(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns the unique canvas id.
	 * 
	 * @return the unique canvas id.
	 */
	public final int getId() {
		return getValue(Property.CHARBA_OBJECT_ID, UndefinedValues.INTEGER);
	}

}
