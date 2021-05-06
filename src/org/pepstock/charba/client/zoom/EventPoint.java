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
package org.pepstock.charba.client.zoom;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.items.Undefined;

/**
 * This object is wrapping the native java script object provided by {@link ZoomContext} to know the position of the event when pan or zoom are about to start.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class EventPoint extends NativeObjectContainer {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		X("x"),
		Y("y");

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
	 * Creates the object with native object instance to be wrapped.
	 * 
	 * @param nativeObject native object instance to be wrapped.
	 */
	EventPoint(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns the X coordinate of the point.
	 * 
	 * @return the X coordinate of the point.
	 */
	public final double getX() {
		return getValue(Property.X, Undefined.DOUBLE);
	}

	/**
	 * Returns the Y coordinate of the point.
	 * 
	 * @return the Y coordinate of the point.
	 */
	public final double getY() {
		return getValue(Property.Y, Undefined.DOUBLE);
	}

	/**
	 * Returns <code>true</code> if the coordinates are consistent and not <code>NaN</code>.
	 * 
	 * @return <code>true</code> if the coordinates are consistent and not <code>NaN</code>
	 */
	public final boolean isConsistent() {
		return Undefined.isNot(getX()) && Undefined.isNot(getY());
	}

}