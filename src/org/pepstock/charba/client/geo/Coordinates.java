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
package org.pepstock.charba.client.geo;

import org.pepstock.charba.client.items.Undefined;

/**
 * Contains the latitude and longitude, calculated by {@link ChoroplethChart#projectionInvert(CoordinatesPoint)} and {@link ChoroplethChart#projectionInvert(double, double)}.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Coordinates {

	private final double latitude;

	private final double longitude;

	/**
	 * Creates the object with the latitude and longitude values.
	 * 
	 * @param latitude latitude value to store
	 * @param longitude longitude value to store
	 */
	Coordinates(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	/**
	 * Returns the latitude stored in the object.
	 * 
	 * @return the latitude value
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * Returns the longitude stored in the object.
	 * 
	 * @return the longitude value
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * Returns <code>true</code> if the latitude and longitude are consistent and not <code>NaN</code>.
	 * 
	 * @return <code>true</code> if the latitude and longitude are consistent and not <code>NaN</code>
	 */
	public boolean isConsistent() {
		return Undefined.isNot(latitude) && Undefined.isNot(longitude);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Coordinates [latitude=" + latitude + ", longitude=" + longitude + "]";
	}
}