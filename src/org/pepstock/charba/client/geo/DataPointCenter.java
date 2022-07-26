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
package org.pepstock.charba.client.geo;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.items.Undefined;

/**
 * This class enables the capability to specify the coordinates where the tooltip should appear. e.g. at the capital city.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class DataPointCenter extends NativeObjectContainer {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		LATITUDE("latitude"),
		LONGITUDE("longitude");

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
	 * Creates a data point center.
	 */
	public DataPointCenter() {
		super();
	}

	/**
	 * Creates a data point center with {@link Coordinates}.
	 * 
	 * @param coordinates the coordinates value
	 */
	public DataPointCenter(Coordinates coordinates) {
		this();
		// stores value
		// passed value is consistent
		if (coordinates != null && coordinates.isConsistent()) {
			setLatitude(coordinates.getLatitude());
			setLongitude(coordinates.getLongitude());
		}
	}

	/**
	 * Creates a data point center with latitude and longitude.
	 * 
	 * @param latitude the latitude value
	 * @param longitude the longitude value
	 */
	public DataPointCenter(double latitude, double longitude) {
		this();
		// stores value
		setLatitude(latitude);
		setLongitude(longitude);
	}

	/**
	 * Creates the object with native object instance to be wrapped.
	 * 
	 * @param nativeObject native object instance to be wrapped.
	 */
	DataPointCenter(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Sets the latitude stored in the object.
	 * 
	 * @param latitude the latitude value
	 */
	public void setLatitude(double latitude) {
		setValue(Property.LATITUDE, latitude);
	}

	/**
	 * Returns the latitude stored in the object.
	 * 
	 * @return the latitude value
	 */
	public double getLatitude() {
		return getValue(Property.LATITUDE, Undefined.DOUBLE);
	}

	/**
	 * Sets the longitude stored in the object.
	 * 
	 * @param longitude the longitude value
	 */
	public void setLongitude(double longitude) {
		setValue(Property.LONGITUDE, longitude);
	}

	/**
	 * Returns the longitude stored in the object.
	 * 
	 * @return the longitude value
	 */
	public double getLongitude() {
		return getValue(Property.LONGITUDE, Undefined.DOUBLE);
	}

	/**
	 * Returns the native object instance.
	 * 
	 * @return the native object instance.
	 */
	NativeObject nativeObject() {
		return getNativeObject();
	}

}
