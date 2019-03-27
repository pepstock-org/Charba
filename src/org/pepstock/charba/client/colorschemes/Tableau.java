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
package org.pepstock.charba.client.colorschemes;

/**
 * @author Andrea "Stock" Stocchero
 *
 */
public enum Tableau implements Scheme
{
	OrangeBlueWhite7,
	RedGreenWhite7,
	GreenBlueWhite7,
	RedBlueWhite7,
	RedBlackWhite7,
	OrangeBlueLight7,
	Temperature7,
	BlueGreen7,
	BlueLight7,
	OrangeLight7,
	Blue20,
	Orange20,
	Green20,
	Red20,
	Purple20,
	Brown20,
	Gray20,
	GrayWarm20,
	BlueTeal20,
	OrangeGold20,
	GreenGold20,
	RedGold21,
	Classic10,
	ClassicMedium10,
	ClassicLight10,
	Classic20,
	ClassicGray5,
	ClassicColorBlind10,
	ClassicTrafficLight9,
	ClassicPurpleGray6,
	ClassicPurpleGray12,
	ClassicGreenOrange6,
	ClassicGreenOrange12,
	ClassicBlueRed6,
	ClassicBlueRed12,
	ClassicCyclic13,
	ClassicGreen7,
	ClassicGray13,
	ClassicBlue7,
	ClassicRed9,
	ClassicOrange7,
	ClassicAreaRed11,
	ClassicAreaGreen11,
	ClassicAreaBrown11,
	ClassicRedGreen11,
	ClassicRedBlue11,
	ClassicRedBlack11,
	ClassicAreaRedGreen21,
	ClassicOrangeBlue13,
	ClassicGreenBlue11,
	ClassicRedWhiteGreen11,
	ClassicRedWhiteBlack11,
	ClassicOrangeWhiteBlue11,
	ClassicRedWhiteBlackLight10,
	ClassicOrangeWhiteBlueLight11,
	ClassicRedWhiteGreenLight11,
	ClassicRedGreenLight11;
	
	static final String CATEGORY = "tableau";
	
	private final String value;

	private Tableau() {
		value = createValue(CATEGORY, name());
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.colorschemes.Scheme#getValue()
	 */
	@Override
	public String getValue() {
		return value;
	}

}
