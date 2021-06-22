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
 * Enumerates the color interpolation for data set and scale.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum Interpolate implements Key
{
	/**
	 * Interpolate color 'blues'.
	 */
	BLUES("blues"),
	/**
	 * Interpolate color 'brBG'.
	 */
	BR_B_G("brBG"),
	/**
	 * Interpolate color 'buGn'.
	 */
	BU_GN("buGn"),
	/**
	 * Interpolate color 'buPu'.
	 */
	BU_PU("buPu"),
	/**
	 * Interpolate color 'cividis'.
	 */
	CIVIDIS("cividis"),
	/**
	 * Interpolate color 'cool'.
	 */
	COOL("cool"),
	/**
	 * Interpolate color 'cubehelixDefault'.
	 */
	CUBEHELIX_DEFAULT("cubehelixDefault"),
	/**
	 * Interpolate color 'gnBu'.
	 */
	GN_BU("gnBu"),
	/**
	 * Interpolate color 'greens'.
	 */
	GREENS("greens"),
	/**
	 * Interpolate color 'greys'.
	 */
	GREYS("greys"),
	/**
	 * Interpolate color 'inferno'.
	 */
	INFERNO("inferno"),
	/**
	 * Interpolate color 'magma'.
	 */
	MAGMA("magma"),
	/**
	 * Interpolate color 'orRd'.
	 */
	OR_RD("orRd"),
	/**
	 * Interpolate color 'oranges'.
	 */
	ORANGES("oranges"),
	/**
	 * Interpolate color 'pRGn'.
	 */
	P_R_GN("pRGn"),
	/**
	 * Interpolate color 'piYG'.
	 */
	PI_Y_G("piYG"),
	/**
	 * Interpolate color 'plasma'.
	 */
	PLASMA("plasma"),
	/**
	 * Interpolate color 'puBu'.
	 */
	PU_BU("puBu"),
	/**
	 * Interpolate color 'puBuGn'.
	 */
	PU_BU_GN("puBuGn"),
	/**
	 * Interpolate color 'puOr'.
	 */
	PU_OR("puOr"),
	/**
	 * Interpolate color 'puRd'.
	 */
	PU_RD("puRd"),
	/**
	 * Interpolate color 'purples'.
	 */
	PURPLES("purples"),
	/**
	 * Interpolate color 'rainbow'.
	 */
	RAINBOW("rainbow"),
	/**
	 * Interpolate color 'rdBu'.
	 */
	RD_BU("rdBu"),
	/**
	 * Interpolate color 'rdGy'.
	 */
	RD_GY("rdGy"),
	/**
	 * Interpolate color 'rdPu'.
	 */
	RD_PU("rdPu"),
	/**
	 * Interpolate color 'rdYlBu'.
	 */
	RD_YL_BU("rdYlBu"),
	/**
	 * Interpolate color 'rdYlGn'.
	 */
	RD_YL_GN("rdYlGn"),
	/**
	 * Interpolate color 'reds'.
	 */
	REDS("reds"),
	/**
	 * Interpolate color 'sinebow'.
	 */
	SINEBOW("sinebow"),
	/**
	 * Interpolate color 'spectral'.
	 */
	SPECTRAL("spectral"),
	/**
	 * Interpolate color 'turbo'.
	 */
	TURBO("turbo"),
	/**
	 * Interpolate color 'viridis'.
	 */
	VIRIDIS("viridis"),
	/**
	 * Interpolate color 'warm'.
	 */
	WARM("warm"),
	/**
	 * Interpolate color 'ylGn'.
	 */
	YL_GN("ylGn"),
	/**
	 * Interpolate color 'ylGnBu'.
	 */
	YL_GN_BU("ylGnBu"),
	/**
	 * Interpolate color 'ylOrBr'.
	 */
	YL_OR_BR("ylOrBr"),
	/**
	 * Interpolate color 'ylOrRd'.
	 */
	YL_OR_RD("ylOrRd");

	// name value of property
	private final String value;

	/**
	 * Creates with the property value to use in the native object.
	 * 
	 * @param value value of property name
	 */
	private Interpolate(String value) {
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
