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
import org.pepstock.charba.client.items.Undefined;

/**
 * Used for BubbleMap data sets to set the data to render.<br>
 * A data has to have a <code>latitude</code> and <code>longitude</code> properties and a <code>value</code> property containing the value for the coloring.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class BubbleMapDataPoint extends GeoDataPoint {

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
	 * Creates the object with an empty native object.
	 */
	public BubbleMapDataPoint() {
		this((NativeObject) null);
	}

	/**
	 * Creates the object with the assigned latitude and longitude.
	 * 
	 * @param latitude the latitude of the data
	 * @param longitude the longitude of the data
	 */
	public BubbleMapDataPoint(double latitude, double longitude) {
		this();
		// stores values
		setLatitude(latitude);
		setLongitude(longitude);
	}

	/**
	 * Creates the object with the assigned latitude, longitude and value.
	 * 
	 * @param latitude the latitude of the data
	 * @param longitude the longitude of the data
	 * @param value the value for the coloring
	 */
	public BubbleMapDataPoint(double latitude, double longitude, double value) {
		this(latitude, longitude);
		// stores value
		setValue(value);
	}

	/**
	 * Creates the object with a native object passed as argument.
	 * 
	 * @param nativeObject native object which maps a data point
	 */
	BubbleMapDataPoint(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Sets the latitude of the data.
	 * 
	 * @param latitude the latitude of the data
	 */
	public void setLatitude(double latitude) {
		setValue(Property.LATITUDE, latitude);
	}

	/**
	 * Returns the latitude of the data.
	 * 
	 * @return the latitude of the data
	 */
	public double getLatitude() {
		return getValue(Property.LATITUDE, Undefined.DOUBLE);
	}

	/**
	 * Sets the longitude of the data.
	 * 
	 * @param longitude the longitude of the data
	 */
	public void setLongitude(double longitude) {
		setValue(Property.LONGITUDE, longitude);
	}

	/**
	 * Returns the longitude of the data.
	 * 
	 * @return the longitude of the data
	 */
	public double getLongitude() {
		return getValue(Property.LONGITUDE, Undefined.DOUBLE);
	}

}