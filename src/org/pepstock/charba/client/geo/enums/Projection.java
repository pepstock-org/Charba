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
package org.pepstock.charba.client.geo.enums;

import org.pepstock.charba.client.commons.Key;

/**
 * Enumerates the possible map projections which are a way to flatten a globe's surface into a plane in order to make a map.
 * 
 * @author Andrea "Stock" Stocchero
 */
public enum Projection implements Key
{
	/**
	 * Projection <b>azimuthalEqualArea</b>.
	 */
	AZIMUTHAL_EQUAL_AREA("azimuthalEqualArea"),
	/**
	 * Projection <b>azimuthalEquidistant</b>.
	 */
	AZIMUTHAL_EQUIDISTANT("azimuthalEquidistant"),
	/**
	 * Projection <b>gnomonic</b>.
	 */
	GNOMONIC("gnomonic"),
	/**
	 * Projection <b>orthographic</b>.
	 */
	ORTHOGRAPHIC("orthographic"),
	/**
	 * Projection <b>stereographic</b>.
	 */
	STEREOGRAPHIC("stereographic"),
	/**
	 * Projection <b>equalEarth</b>.
	 */
	EQUAL_EARTH("equalEarth"),
	/**
	 * Projection <b>albers</b>.
	 */
	ALBERS("albers"),
	/**
	 * Projection <b>albersUsa</b>.
	 */
	ALBERS_USA("albersUsa"),
	/**
	 * Projection <b>conicConformal</b>.
	 */
	CONIC_CONFORMAL("conicConformal"),
	/**
	 * Projection <b>conicEqualArea</b>.
	 */
	CONIC_EQUAL_AREA("conicEqualArea"),
	/**
	 * Projection <b>conicEquidistant</b>.
	 */
	CONIC_EQUIDISTANT("conicEquidistant"),
	/**
	 * Projection <b>equirectangular</b>.
	 */
	EQUIRECTANGULAR("equirectangular"),
	/**
	 * Projection <b>mercator</b>.
	 */
	MERCATOR("mercator"),
	/**
	 * Projection <b>transverseMercator</b>.
	 */
	TRANSVERSE_MERCATOR("transverseMercator"),
	/**
	 * Projection <b>naturalEarth1</b>.
	 */
	NATURAL_EARTH1("naturalEarth1");

	// name value of property
	private final String value;

	/**
	 * Creates with the property value to use in the native object.
	 * 
	 * @param value value of property name
	 */
	private Projection(String value) {
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