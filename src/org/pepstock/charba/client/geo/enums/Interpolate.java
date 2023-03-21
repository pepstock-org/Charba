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
	 * Interpolate color <b>blues</b>.
	 */
	BLUES("blues"),
	/**
	 * Interpolate color <b>brBG</b>.
	 */
	BR_B_G("brBG"),
	/**
	 * Interpolate color <b>buGn</b>.
	 */
	BU_GN("buGn"),
	/**
	 * Interpolate color <b>buPu</b>.
	 */
	BU_PU("buPu"),
	/**
	 * Interpolate color <b>cividis</b>.
	 */
	CIVIDIS("cividis"),
	/**
	 * Interpolate color <b>cool</b>.
	 */
	COOL("cool"),
	/**
	 * Interpolate color <b>cubehelixDefault</b>.
	 */
	CUBEHELIX_DEFAULT("cubehelixDefault"),
	/**
	 * Interpolate color <b>gnBu</b>.
	 */
	GN_BU("gnBu"),
	/**
	 * Interpolate color <b>greens</b>.
	 */
	GREENS("greens"),
	/**
	 * Interpolate color <b>greys</b>.
	 */
	GREYS("greys"),
	/**
	 * Interpolate color <b>inferno</b>.
	 */
	INFERNO("inferno"),
	/**
	 * Interpolate color <b>magma</b>.
	 */
	MAGMA("magma"),
	/**
	 * Interpolate color <b>orRd</b>.
	 */
	OR_RD("orRd"),
	/**
	 * Interpolate color <b>oranges</b>.
	 */
	ORANGES("oranges"),
	/**
	 * Interpolate color <b>pRGn</b>.
	 */
	P_R_GN("pRGn"),
	/**
	 * Interpolate color <b>piYG</b>.
	 */
	PI_Y_G("piYG"),
	/**
	 * Interpolate color <b>plasma</b>.
	 */
	PLASMA("plasma"),
	/**
	 * Interpolate color <b>puBu</b>.
	 */
	PU_BU("puBu"),
	/**
	 * Interpolate color <b>puBuGn</b>.
	 */
	PU_BU_GN("puBuGn"),
	/**
	 * Interpolate color <b>puOr</b>.
	 */
	PU_OR("puOr"),
	/**
	 * Interpolate color <b>puRd</b>.
	 */
	PU_RD("puRd"),
	/**
	 * Interpolate color <b>purples</b>.
	 */
	PURPLES("purples"),
	/**
	 * Interpolate color <b>rainbow</b>.
	 */
	RAINBOW("rainbow"),
	/**
	 * Interpolate color <b>rdBu</b>.
	 */
	RD_BU("rdBu"),
	/**
	 * Interpolate color <b>rdGy</b>.
	 */
	RD_GY("rdGy"),
	/**
	 * Interpolate color <b>rdPu</b>.
	 */
	RD_PU("rdPu"),
	/**
	 * Interpolate color <b>rdYlBu</b>.
	 */
	RD_YL_BU("rdYlBu"),
	/**
	 * Interpolate color <b>rdYlGn</b>.
	 */
	RD_YL_GN("rdYlGn"),
	/**
	 * Interpolate color <b>reds</b>.
	 */
	REDS("reds"),
	/**
	 * Interpolate color <b>sinebow</b>.
	 */
	SINEBOW("sinebow"),
	/**
	 * Interpolate color <b>spectral</b>.
	 */
	SPECTRAL("spectral"),
	/**
	 * Interpolate color <b>turbo</b>.
	 */
	TURBO("turbo"),
	/**
	 * Interpolate color <b>viridis</b>.
	 */
	VIRIDIS("viridis"),
	/**
	 * Interpolate color <b>warm</b>.
	 */
	WARM("warm"),
	/**
	 * Interpolate color <b>ylGn</b>.
	 */
	YL_GN("ylGn"),
	/**
	 * Interpolate color <b>ylGnBu</b>.
	 */
	YL_GN_BU("ylGnBu"),
	/**
	 * Interpolate color <b>ylOrBr</b>.
	 */
	YL_OR_BR("ylOrBr"),
	/**
	 * Interpolate color <b>ylOrRd</b>.
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