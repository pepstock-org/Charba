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
 * Contains all labels to use Microsoft Office color scheme.<br>
 * To configure COLORSCHEMES plugin, the label is composed by:<br>
 * <br>
 * <pre>
 * [category].[name]
 * </pre>
 * where category is <b>"office"</b>.<br>
 * See <a href="https://nagix.github.io/chartjs-plugin-colorschemes/colorchart.html">here</a> the list of color by scheme.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum Office implements Scheme
{
	Adjacency6,
	Advantage6,
	Angles6,
	Apex6,
	Apothecary6,
	Aspect6,
	Atlas6,
	Austin6,
	Badge6,
	Banded6,
	Basis6,
	Berlin6,
	BlackTie6,
	Blue6,
	BlueGreen6,
	BlueII6,
	BlueRed6,
	BlueWarm6,
	Breeze6,
	Capital6,
	Celestial6,
	Circuit6,
	Civic6,
	Clarity6,
	Codex6,
	Composite6,
	Concourse6,
	Couture6,
	Crop6,
	Damask6,
	Depth6,
	Dividend6,
	Droplet6,
	Elemental6,
	Equity6,
	Essential6,
	Excel16,
	Executive6,
	Exhibit6,
	Expo6,
	Facet6,
	Feathered6,
	Flow6,
	Focus6,
	Folio6,
	Formal6,
	Forte6,
	Foundry6,
	Frame6,
	Gallery6,
	Genesis6,
	Grayscale6,
	Green6,
	GreenYellow6,
	Grid6,
	Habitat6,
	Hardcover6,
	Headlines6,
	Horizon6,
	Infusion6,
	Inkwell6,
	Inspiration6,
	Integral6,
	Ion6,
	IonBoardroom6,
	Kilter6,
	Madison6,
	MainEvent6,
	Marquee6,
	Median6,
	Mesh6,
	Metail6,
	Metro6,
	Metropolitan6,
	Module6,
	NewsPrint6,
	Office6,
	Office2007_2010_6("Office2007-2010-6"),
	Opulent6,
	Orange6,
	OrangeRed6,
	Orbit6,
	Organic6,
	Oriel6,
	Origin6,
	Paper6,
	Parallax6,
	Parcel6,
	Perception6,
	Perspective6,
	Pixel6,
	Plaza6,
	Precedent6,
	Pushpin6,
	Quotable6,
	Red6,
	RedOrange6,
	RedViolet6,
	Retrospect6,
	Revolution6,
	Saddle6,
	Savon6,
	Sketchbook6,
	Sky6,
	Slate6,
	Slice6,
	Slipstream6,
	SOHO6,
	Solstice6,
	Spectrum6,
	Story6,
	Studio6,
	Summer6,
	Technic6,
	Thatch6,
	Tradition6,
	Travelogue6,
	Trek6,
	Twilight6,
	Urban6,
	UrbanPop6,
	VaporTrail6,
	Venture6,
	Verve6,
	View6,
	Violet6,
	VioletII6,
	Waveform6,
	Wisp6,
	WoodType6,
	Yellow6,
	YellowOrange6;

	/**
	 * Category name used to build the label to configure plugin.
	 */
	static final String CATEGORY = "office";
	// the value to configure the plugin
	private final String value;

	/**
	 * Builds a scheme using its enum name as value of label.
	 */
	private Office() {
		this(null);
	}

	/**
	 * Builds a scheme using argument as value of label.
	 * 
	 * @param schemeName name of scheme
	 */
	private Office(String schemeName) {
		value = createValue(CATEGORY, schemeName == null ? name() : schemeName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.colorschemes.Scheme#getValue()
	 */
	@Override
	public String getValue() {
		return value;
	}

}
