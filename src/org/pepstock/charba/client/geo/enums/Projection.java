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
 * Enumerates the possible values to clip the rendering to the chart area of the GEO graph.
 * 
 * @author Andrea "Stock" Stocchero
 */
public enum Projection implements Key
{
	/**
	 * Projection 'azimuthalEqualArea'.
	 */
	AZIMUTHAL_EQUAL_AREA("azimuthalEqualArea"),
	/**
	 * Projection 'azimuthalEquidistant'.
	 */
	AZIMUTHAL_EQUIDISTANT("azimuthalEquidistant"),
	/**
	 * Projection 'gnomonic'.
	 */
	GNOMONIC("gnomonic"),
	/**
	 * Projection 'orthographic'.
	 */
	ORTHOGRAPHIC("orthographic"),
	/**
	 * Projection 'stereographic'.
	 */
	STEREOGRAPHIC("stereographic"),
	/**
	 * Projection 'equalEarth'.
	 */
	EQUAL_EARTH("equalEarth"),
	/**
	 * Projection 'albers'.
	 */
	ALBERS("albers"),
	/**
	 * Projection 'albersUsa'.
	 */
	ALBERS_USA("albersUsa"),
	/**
	 * Projection 'conicConformal'.
	 */
	CONIC_CONFORMAL("conicConformal"),
	/**
	 * Projection 'conicEqualArea'.
	 */
	CONIC_EQUAL_AREA("conicEqualArea"),
	/**
	 * Projection 'conicEquidistant'.
	 */
	CONIC_EQUIDISTANT("conicEquidistant"),
	/**
	 * Projection 'equirectangular'.
	 */
	EQUIRECTANGULAR("equirectangular"),
	/**
	 * Projection 'mercator'.
	 */
	MERCATOR("mercator"),
	/**
	 * Projection 'transverseMercator'.
	 */
	TRANSVERSE_MERCATOR("transverseMercator"),
	/**
	 * Projection 'naturalEarth1'.
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