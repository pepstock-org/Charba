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
package org.pepstock.charba.client.impl.plugins.enums;

import java.util.LinkedList;
import java.util.List;

import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.impl.plugins.ColorScheme;
import org.pepstock.charba.client.impl.plugins.ColorSchemes;

/**
 * Contains all scheme definitions to map the Microsoft Office schemes.<br>
 * To configure {@link ColorSchemes#ID} plugin, where category is <b>"office"</b>.<br>
 * Every color scheme has a number at the end of its name, which indicates the number of that colors included in the scheme. If
 * the number of the datasets is larger than it, the same colors will appear repeatedly. A color is not modified if it is
 * specified by dataset options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum OfficeScheme implements ColorScheme
{
	/**
	 * Color scheme name "Adjacency6" - <span style="background-color:#a9a57c; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#9cbebd; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d2cb6c; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#95a39d;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#c89f5d; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b1a089; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	ADJACENCY6("Adjacency6", "#a9a57c", "#9cbebd", "#d2cb6c", "#95a39d", "#c89f5d", "#b1a089"),
	/**
	 * Color scheme name "Advantage6" - <span style="background-color:#663366; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#330f42; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#666699; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#999966;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#f7901e; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a3a101; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	ADVANTAGE6("Advantage6", "#663366", "#330f42", "#666699", "#999966", "#f7901e", "#a3a101"),
	/**
	 * Color scheme name "Angles6" - <span style="background-color:#797b7e; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f96a1b; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#08a1d9; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#7c984a;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#c2ad8d; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#506e94; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	ANGLES6("Angles6", "#797b7e", "#f96a1b", "#08a1d9", "#7c984a", "#c2ad8d", "#506e94"),
	/**
	 * Color scheme name "Apex6" - <span style="background-color:#ceb966; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#9cb084; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#6bb1c9; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#6585cf;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#7e6bc9; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a379bb; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	APEX6("Apex6", "#ceb966", "#9cb084", "#6bb1c9", "#6585cf", "#7e6bc9", "#a379bb"),
	/**
	 * Color scheme name "Apothecary6" - <span style="background-color:#93a299; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#cf543f; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b5ae53; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#848058;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#e8b54d; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#786c71; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	APOTHECARY6("Apothecary6", "#93a299", "#cf543f", "#b5ae53", "#848058", "#e8b54d", "#786c71"),
	/**
	 * Color scheme name "Aspect6" - <span style="background-color:#f07f09; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#9f2936; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#1b587c; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#4e8542;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#604878; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c19859; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	ASPECT6("Aspect6", "#f07f09", "#9f2936", "#1b587c", "#4e8542", "#604878", "#c19859"),
	/**
	 * Color scheme name "Atlas6" - <span style="background-color:#f81b02; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fc7715; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#afbf41; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#50c49f;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#3b95c4; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b560d4; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	ATLAS6("Atlas6", "#f81b02", "#fc7715", "#afbf41", "#50c49f", "#3b95c4", "#b560d4"),
	/**
	 * Color scheme name "Austin6" - <span style="background-color:#94c600; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#71685a; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ff6700; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#909465;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#956b43; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fea022; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	AUSTIN6("Austin6", "#94c600", "#71685a", "#ff6700", "#909465", "#956b43", "#fea022"),
	/**
	 * Color scheme name "Badge6" - <span style="background-color:#f8b323; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#656a59; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#46b2b5; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#8caa7e;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#d36f68; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#826276; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	BADGE6("Badge6", "#f8b323", "#656a59", "#46b2b5", "#8caa7e", "#d36f68", "#826276"),
	/**
	 * Color scheme name "Banded6" - <span style="background-color:#ffc000; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a5d028; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#08cc78; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f24099;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#828288; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f56617; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	BANDED6("Banded6", "#ffc000", "#a5d028", "#08cc78", "#f24099", "#828288", "#f56617"),
	/**
	 * Color scheme name "Basis6" - <span style="background-color:#f09415; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c1b56b; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#4baf73; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#5aa6c0;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#d17df9; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fa7e5c; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	BASIS6("Basis6", "#f09415", "#c1b56b", "#4baf73", "#5aa6c0", "#d17df9", "#fa7e5c"),
	/**
	 * Color scheme name "Berlin6" - <span style="background-color:#a6b727; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#df5327; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fe9e00; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#418ab3;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#d7d447; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#818183; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	BERLIN6("Berlin6", "#a6b727", "#df5327", "#fe9e00", "#418ab3", "#d7d447", "#818183"),
	/**
	 * Color scheme name "BlackTie6" - <span style="background-color:#6f6f74; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a7b789; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#beae98; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#92a9b9;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#9c8265; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#8d6974; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	BLACK_TIE6("BlackTie6", "#6f6f74", "#a7b789", "#beae98", "#92a9b9", "#9c8265", "#8d6974"),
	/**
	 * Color scheme name "Blue6" - <span style="background-color:#0f6fc6; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#009dd9; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#0bd0d9; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#10cf9b;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#7cca62; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a5c249; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	BLUE6("Blue6", "#0f6fc6", "#009dd9", "#0bd0d9", "#10cf9b", "#7cca62", "#a5c249"),
	/**
	 * Color scheme name "BlueGreen6" - <span style="background-color:#3494ba; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#58b6c0; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#75bda7; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#7a8c8e;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#84acb6; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#2683c6; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	BLUE_GREEN6("BlueGreen6", "#3494ba", "#58b6c0", "#75bda7", "#7a8c8e", "#84acb6", "#2683c6"),
	/**
	 * Color scheme name "BlueII6" - <span style="background-color:#1cade4; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#2683c6; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#27ced7; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#42ba97;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#3e8853; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#62a39f; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	BLUE_II6("BlueII6", "#1cade4", "#2683c6", "#27ced7", "#42ba97", "#3e8853", "#62a39f"),
	/**
	 * Color scheme name "BlueRed6" - <span style="background-color:#4a66ac; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#629dd1; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#297fd5; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#7f8fa9;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#5aa2ae; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#9d90a0; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	BLUE_RED6("BlueRed6", "#4a66ac", "#629dd1", "#297fd5", "#7f8fa9", "#5aa2ae", "#9d90a0"),
	/**
	 * Color scheme name "BlueWarm6" - <span style="background-color:#4a66ac; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#629dd1; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#297fd5; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#7f8fa9;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#5aa2ae; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#9d90a0; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	BLUE_WARM6("BlueWarm6", "#4a66ac", "#629dd1", "#297fd5", "#7f8fa9", "#5aa2ae", "#9d90a0"),
	/**
	 * Color scheme name "Breeze6" - <span style="background-color:#2c7c9f; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#244a58; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e2751d; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffb400;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#7eb606; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c00000; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	BREEZE6("Breeze6", "#2c7c9f", "#244a58", "#e2751d", "#ffb400", "#7eb606", "#c00000"),
	/**
	 * Color scheme name "Capital6" - <span style="background-color:#4b5a60; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#9c5238; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#504539; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c1ad79;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#667559; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#bad6ad; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	CAPITAL6("Capital6", "#4b5a60", "#9c5238", "#504539", "#c1ad79", "#667559", "#bad6ad"),
	/**
	 * Color scheme name "Celestial6" - <span style="background-color:#ac3ec1; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#477bd1; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#46b298; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#90ba4c;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#dd9d31; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e25247; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	CELESTIAL6("Celestial6", "#ac3ec1", "#477bd1", "#46b298", "#90ba4c", "#dd9d31", "#e25247"),
	/**
	 * Color scheme name "Circuit6" - <span style="background-color:#9acd4c; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#faa93a; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d35940; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b258d3;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#63a0cc; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#8ac4a7; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	CIRCUIT6("Circuit6", "#9acd4c", "#faa93a", "#d35940", "#b258d3", "#63a0cc", "#8ac4a7"),
	/**
	 * Color scheme name "Civic6" - <span style="background-color:#d16349; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ccb400; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#8cadae; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#8c7b70;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#8fb08c; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d19049; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	CIVIC6("Civic6", "#d16349", "#ccb400", "#8cadae", "#8c7b70", "#8fb08c", "#d19049"),
	/**
	 * Color scheme name "Clarity6" - <span style="background-color:#93a299; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ad8f67; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#726056; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#4c5a6a;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#808da0; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#79463d; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	CLARITY6("Clarity6", "#93a299", "#ad8f67", "#726056", "#4c5a6a", "#808da0", "#79463d"),
	/**
	 * Color scheme name "Codex6" - <span style="background-color:#990000; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#efab16; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#78ac35; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#35aca2;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#4083cf; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#0d335e; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	CODEX6("Codex6", "#990000", "#efab16", "#78ac35", "#35aca2", "#4083cf", "#0d335e"),
	/**
	 * Color scheme name "Composite6" - <span style="background-color:#98c723; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#59b0b9; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#deae00; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b77bb4;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#e0773c; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a98d63; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	COMPOSITE6("Composite6", "#98c723", "#59b0b9", "#deae00", "#b77bb4", "#e0773c", "#a98d63"),
	/**
	 * Color scheme name "Concourse6" - <span style="background-color:#2da2bf; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#da1f28; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#eb641b; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#39639d;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#474b78; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#7d3c4a; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	CONCOURSE6("Concourse6", "#2da2bf", "#da1f28", "#eb641b", "#39639d", "#474b78", "#7d3c4a"),
	/**
	 * Color scheme name "Couture6" - <span style="background-color:#9e8e5c; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a09781; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#85776d; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#aeafa9;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#8d878b; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#6b6149; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	COUTURE6("Couture6", "#9e8e5c", "#a09781", "#85776d", "#aeafa9", "#8d878b", "#6b6149"),
	/**
	 * Color scheme name "Crop6" - <span style="background-color:#8c8d86; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e6c069; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#897b61; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#8dab8e;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#77a2bb; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e28394; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	CROP6("Crop6", "#8c8d86", "#e6c069", "#897b61", "#8dab8e", "#77a2bb", "#e28394"),
	/**
	 * Color scheme name "Damask6" - <span style="background-color:#9ec544; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#50bea3; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#4a9ccc; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#9a66ca;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#c54f71; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#de9c3c; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	DAMASK6("Damask6", "#9ec544", "#50bea3", "#4a9ccc", "#9a66ca", "#c54f71", "#de9c3c"),
	/**
	 * Color scheme name "Depth6" - <span style="background-color:#41aebd; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#97e9d5; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a2cf49; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#608f3d;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#f4de3a; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fcb11c; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	DEPTH6("Depth6", "#41aebd", "#97e9d5", "#a2cf49", "#608f3d", "#f4de3a", "#fcb11c"),
	/**
	 * Color scheme name "Dividend6" - <span style="background-color:#4d1434; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#903163; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b2324b; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#969fa7;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#66b1ce; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#40619d; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	DIVIDEND6("Dividend6", "#4d1434", "#903163", "#b2324b", "#969fa7", "#66b1ce", "#40619d"),
	/**
	 * Color scheme name "Droplet6" - <span style="background-color:#2fa3ee; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#4bcaad; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#86c157; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d99c3f;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#ce6633; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a35dd1; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	DROPLET6("Droplet6", "#2fa3ee", "#4bcaad", "#86c157", "#d99c3f", "#ce6633", "#a35dd1"),
	/**
	 * Color scheme name "Elemental6" - <span style="background-color:#629dd1; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#297fd5; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#7f8fa9; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#4a66ac;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#5aa2ae; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#9d90a0; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	ELEMENTAL6("Elemental6", "#629dd1", "#297fd5", "#7f8fa9", "#4a66ac", "#5aa2ae", "#9d90a0"),
	/**
	 * Color scheme name "Equity6" - <span style="background-color:#d34817; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#9b2d1f; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a28e6a; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#956251;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#918485; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#855d5d; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	EQUITY6("Equity6", "#d34817", "#9b2d1f", "#a28e6a", "#956251", "#918485", "#855d5d"),
	/**
	 * Color scheme name "Essential6" - <span style="background-color:#7a7a7a; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f5c201; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#526db0; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#989aac;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#dc5924; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b4b392; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	ESSENTIAL6("Essential6", "#7a7a7a", "#f5c201", "#526db0", "#989aac", "#dc5924", "#b4b392"),
	/**
	 * Color scheme name "Excel16" - <span style="background-color:#9999ff; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#993366; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffffcc; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ccffff;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#660066; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ff8080; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#0066cc; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ccccff;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#000080; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#FF00FF; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#FFFF00; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#0000FF;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#800080; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#800000; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#008080; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#0000FF;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	EXCEL16("Excel16", "#9999ff", "#993366", "#ffffcc", "#ccffff", "#660066", "#ff8080", "#0066cc", "#ccccff", "#000080", "#FF00FF", "#FFFF00", "#0000FF", "#800080", "#800000", "#008080", "#0000FF"),
	/**
	 * Color scheme name "Executive6" - <span style="background-color:#6076b4; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#9c5252; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e68422; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#846648;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#63891f; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#758085; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	EXECUTIVE6("Executive6", "#6076b4", "#9c5252", "#e68422", "#846648", "#63891f", "#758085"),
	/**
	 * Color scheme name "Exhibit6" - <span style="background-color:#3399ff; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#69ffff; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ccff33; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#3333ff;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#9933ff; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ff33ff; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	EXHIBIT6("Exhibit6", "#3399ff", "#69ffff", "#ccff33", "#3333ff", "#9933ff", "#ff33ff"),
	/**
	 * Color scheme name "Expo6" - <span style="background-color:#fbc01e; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#efe1a2; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fa8716; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#be0204;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#640f10; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#7e13e3; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	EXPO6("Expo6", "#fbc01e", "#efe1a2", "#fa8716", "#be0204", "#640f10", "#7e13e3"),
	/**
	 * Color scheme name "Facet6" - <span style="background-color:#90c226; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#54a021; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e6b91e; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e76618;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#c42f1a; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#918655; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	FACET6("Facet6", "#90c226", "#54a021", "#e6b91e", "#e76618", "#c42f1a", "#918655"),
	/**
	 * Color scheme name "Feathered6" - <span style="background-color:#606372; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#79a8a4; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b2ad8f; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ad8082;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#dec18c; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#92a185; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	FEATHERED6("Feathered6", "#606372", "#79a8a4", "#b2ad8f", "#ad8082", "#dec18c", "#92a185"),
	/**
	 * Color scheme name "Flow6" - <span style="background-color:#0f6fc6; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#009dd9; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#0bd0d9; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#10cf9b;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#7cca62; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a5c249; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	FLOW6("Flow6", "#0f6fc6", "#009dd9", "#0bd0d9", "#10cf9b", "#7cca62", "#a5c249"),
	/**
	 * Color scheme name "Focus6" - <span style="background-color:#ffb91d; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f97817; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#6de304; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#FF0000;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#732bea; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c913ad; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	FOCUS6("Focus6", "#ffb91d", "#f97817", "#6de304", "#FF0000", "#732bea", "#c913ad"),
	/**
	 * Color scheme name "Folio6" - <span style="background-color:#294171; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#748cbc; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#8e887c; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#834736;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#5a1705; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a0a16a; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	FOLIO6("Folio6", "#294171", "#748cbc", "#8e887c", "#834736", "#5a1705", "#a0a16a"),
	/**
	 * Color scheme name "Formal6" - <span style="background-color:#907f76; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a46645; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#cd9c47; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#9a92cd;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#7d639b; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#733678; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	FORMAL6("Formal6", "#907f76", "#a46645", "#cd9c47", "#9a92cd", "#7d639b", "#733678"),
	/**
	 * Color scheme name "Forte6" - <span style="background-color:#c70f0c; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#dd6b0d; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#faa700; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#93e50d;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#17c7ba; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#0a96e4; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	FORTE6("Forte6", "#c70f0c", "#dd6b0d", "#faa700", "#93e50d", "#17c7ba", "#0a96e4"),
	/**
	 * Color scheme name "Foundry6" - <span style="background-color:#72a376; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b0ccb0; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a8cdd7; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c0beaf;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#cec597; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e8b7b7; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	FOUNDRY6("Foundry6", "#72a376", "#b0ccb0", "#a8cdd7", "#c0beaf", "#cec597", "#e8b7b7"),
	/**
	 * Color scheme name "Frame6" - <span style="background-color:#40bad2; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fab900; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#90bb23; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ee7008;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#1ab39f; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d5393d; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	FRAME6("Frame6", "#40bad2", "#fab900", "#90bb23", "#ee7008", "#1ab39f", "#d5393d"),
	/**
	 * Color scheme name "Gallery6" - <span style="background-color:#b71e42; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#de478e; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#bc72f0; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#795faf;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#586ea6; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#6892a0; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	GALLERY6("Gallery6", "#b71e42", "#de478e", "#bc72f0", "#795faf", "#586ea6", "#6892a0"),
	/**
	 * Color scheme name "Genesis6" - <span style="background-color:#80b606; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e29f1d; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#2397e2; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#35aca2;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#5430bb; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#8d34e0; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	GENESIS6("Genesis6", "#80b606", "#e29f1d", "#2397e2", "#35aca2", "#5430bb", "#8d34e0"),
	/**
	 * Color scheme name "Grayscale6" - <span style="background-color:#dddddd; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b2b2b2; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#969696; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#808080;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#5f5f5f; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#4d4d4d; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	GRAYSCALE6("Grayscale6", "#dddddd", "#b2b2b2", "#969696", "#808080", "#5f5f5f", "#4d4d4d"),
	/**
	 * Color scheme name "Green6" - <span style="background-color:#549e39; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#8ab833; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c0cf3a; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#029676;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#4ab5c4; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#0989b1; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	GREEN6("Green6", "#549e39", "#8ab833", "#c0cf3a", "#029676", "#4ab5c4", "#0989b1"),
	/**
	 * Color scheme name "GreenYellow6" - <span style="background-color:#99cb38; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#63a537; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#37a76f; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#44c1a3;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#4eb3cf; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#51c3f9; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	GREEN_YELLOW6("GreenYellow6", "#99cb38", "#63a537", "#37a76f", "#44c1a3", "#4eb3cf", "#51c3f9"),
	/**
	 * Color scheme name "Grid6" - <span style="background-color:#c66951; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#bf974d; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#928b70; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#87706b;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#94734e; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#6f777d; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	GRID6("Grid6", "#c66951", "#bf974d", "#928b70", "#87706b", "#94734e", "#6f777d"),
	/**
	 * Color scheme name "Habitat6" - <span style="background-color:#f8c000; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f88600; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f83500; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#8b723d;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#818b3d; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#586215; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	HABITAT6("Habitat6", "#f8c000", "#f88600", "#f83500", "#8b723d", "#818b3d", "#586215"),
	/**
	 * Color scheme name "Hardcover6" - <span style="background-color:#873624; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d6862d; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d0be40; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#877f6c;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#972109; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#aeb795; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	HARDCOVER6("Hardcover6", "#873624", "#d6862d", "#d0be40", "#877f6c", "#972109", "#aeb795"),
	/**
	 * Color scheme name "Headlines6" - <span style="background-color:#439eb7; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e28b55; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#dcb64d; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#4ca198;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#835b82; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#645135; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	HEADLINES6("Headlines6", "#439eb7", "#e28b55", "#dcb64d", "#4ca198", "#835b82", "#645135"),
	/**
	 * Color scheme name "Horizon6" - <span style="background-color:#7e97ad; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#cc8e60; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#7a6a60; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b4936d;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#67787b; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#9d936f; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	HORIZON6("Horizon6", "#7e97ad", "#cc8e60", "#7a6a60", "#b4936d", "#67787b", "#9d936f"),
	/**
	 * Color scheme name "Infusion6" - <span style="background-color:#8c73d0; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c2e8c4; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c5a6e8; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b45ec7;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#9fdafb; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#95c5b0; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	INFUSION6("Infusion6", "#8c73d0", "#c2e8c4", "#c5a6e8", "#b45ec7", "#9fdafb", "#95c5b0"),
	/**
	 * Color scheme name "Inkwell6" - <span style="background-color:#860908; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#4a0505; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#7a500a; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c47810;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#827752; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b5bb83; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	INKWELL6("Inkwell6", "#860908", "#4a0505", "#7a500a", "#c47810", "#827752", "#b5bb83"),
	/**
	 * Color scheme name "Inspiration6" - <span style="background-color:#749805; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#bacc82; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#6e9ec2; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#2046a5;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#5039c6; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#7411d0; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	INSPIRATION6("Inspiration6", "#749805", "#bacc82", "#6e9ec2", "#2046a5", "#5039c6", "#7411d0"),
	/**
	 * Color scheme name "Integral6" - <span style="background-color:#1cade4; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#2683c6; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#27ced7; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#42ba97;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#3e8853; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#62a39f; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	INTEGRAL6("Integral6", "#1cade4", "#2683c6", "#27ced7", "#42ba97", "#3e8853", "#62a39f"),
	/**
	 * Color scheme name "Ion6" - <span style="background-color:#b01513; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ea6312; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e6b729; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#6aac90;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#5f9c9d; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#9e5e9b; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	ION6("Ion6", "#b01513", "#ea6312", "#e6b729", "#6aac90", "#5f9c9d", "#9e5e9b"),
	/**
	 * Color scheme name "IonBoardroom6" - <span style="background-color:#b31166; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e33d6f; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e45f3c; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e9943a;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#9b6bf2; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d53dd0; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	ION_BOARDROOM6("IonBoardroom6", "#b31166", "#e33d6f", "#e45f3c", "#e9943a", "#9b6bf2", "#d53dd0"),
	/**
	 * Color scheme name "Kilter6" - <span style="background-color:#76c5ef; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fea022; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ff6700; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#70a525;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#a5d848; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#20768c; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	KILTER6("Kilter6", "#76c5ef", "#fea022", "#ff6700", "#70a525", "#a5d848", "#20768c"),
	/**
	 * Color scheme name "Madison6" - <span style="background-color:#a1d68b; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#5ec795; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#4dadcf; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#cdb756;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#e29c36; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#8ec0c1; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	MADISON6("Madison6", "#a1d68b", "#5ec795", "#4dadcf", "#cdb756", "#e29c36", "#8ec0c1"),
	/**
	 * Color scheme name "MainEvent6" - <span style="background-color:#b80e0f; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a6987d; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#7f9a71; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#64969f;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#9b75b2; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#80737a; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	MAIN_EVENT6("MainEvent6", "#b80e0f", "#a6987d", "#7f9a71", "#64969f", "#9b75b2", "#80737a"),
	/**
	 * Color scheme name "Marquee6" - <span style="background-color:#418ab3; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a6b727; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f69200; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#838383;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#fec306; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#df5327; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	MARQUEE6("Marquee6", "#418ab3", "#a6b727", "#f69200", "#838383", "#fec306", "#df5327"),
	/**
	 * Color scheme name "Median6" - <span style="background-color:#94b6d2; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#dd8047; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a5ab81; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d8b25c;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#7ba79d; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#968c8c; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	MEDIAN6("Median6", "#94b6d2", "#dd8047", "#a5ab81", "#d8b25c", "#7ba79d", "#968c8c"),
	/**
	 * Color scheme name "Mesh6" - <span style="background-color:#6f6f6f; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#bfbfa5; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#dcd084; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e7bf5f;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#e9a039; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#cf7133; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	MESH6("Mesh6", "#6f6f6f", "#bfbfa5", "#dcd084", "#e7bf5f", "#e9a039", "#cf7133"),
	/**
	 * Color scheme name "Metail6" - <span style="background-color:#6283ad; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#324966; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#5b9ea4; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#1d5b57;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#1b4430; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#2f3c35; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	METAIL6("Metail6", "#6283ad", "#324966", "#5b9ea4", "#1d5b57", "#1b4430", "#2f3c35"),
	/**
	 * Color scheme name "Metro6" - <span style="background-color:#7fd13b; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ea157a; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#feb80a; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#00addc;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#738ac8; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#1ab39f; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	METRO6("Metro6", "#7fd13b", "#ea157a", "#feb80a", "#00addc", "#738ac8", "#1ab39f"),
	/**
	 * Color scheme name "Metropolitan6" - <span style="background-color:#50b4c8; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a8b97f; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#9b9256; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#657689;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#7a855d; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#84ac9d; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	METROPOLITAN6("Metropolitan6", "#50b4c8", "#a8b97f", "#9b9256", "#657689", "#7a855d", "#84ac9d"),
	/**
	 * Color scheme name "Module6" - <span style="background-color:#f0ad00; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#60b5cc; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e66c7d; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#6bb76d;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#e88651; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c64847; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	MODULE6("Module6", "#f0ad00", "#60b5cc", "#e66c7d", "#6bb76d", "#e88651", "#c64847"),
	/**
	 * Color scheme name "NewsPrint6" - <span style="background-color:#ad0101; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#726056; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ac956e; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#808da9;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#424e5b; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#730e00; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	NEWS_PRINT6("NewsPrint6", "#ad0101", "#726056", "#ac956e", "#808da9", "#424e5b", "#730e00"),
	/**
	 * Color scheme name "Office6" - <span style="background-color:#5b9bd5; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ed7d31; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a5a5a5; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffc000;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#4472c4; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#70ad47; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	OFFICE6("Office6", "#5b9bd5", "#ed7d31", "#a5a5a5", "#ffc000", "#4472c4", "#70ad47"),
	/**
	 * Color scheme name "Office2007_2010_6" - <span style="background-color:#4f81bd; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c0504d; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#9bbb59; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#8064a2;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#4bacc6; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f79646; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	OFFICE2007_2010_6("Office2007_2010_6", "#4f81bd", "#c0504d", "#9bbb59", "#8064a2", "#4bacc6", "#f79646"),
	/**
	 * Color scheme name "Opulent6" - <span style="background-color:#b83d68; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ac66bb; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#de6c36; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f9b639;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#cf6da4; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fa8d3d; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	OPULENT6("Opulent6", "#b83d68", "#ac66bb", "#de6c36", "#f9b639", "#cf6da4", "#fa8d3d"),
	/**
	 * Color scheme name "Orange6" - <span style="background-color:#e48312; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#bd582c; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#865640; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#9b8357;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#c2bc80; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#94a088; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	ORANGE6("Orange6", "#e48312", "#bd582c", "#865640", "#9b8357", "#c2bc80", "#94a088"),
	/**
	 * Color scheme name "OrangeRed6" - <span style="background-color:#d34817; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#9b2d1f; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a28e6a; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#956251;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#918485; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#855d5d; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	ORANGE_RED6("OrangeRed6", "#d34817", "#9b2d1f", "#a28e6a", "#956251", "#918485", "#855d5d"),
	/**
	 * Color scheme name "Orbit6" - <span style="background-color:#f2d908; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#9de61e; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#0d8be6; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c61b1b;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#e26f08; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#8d35d1; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	ORBIT6("Orbit6", "#f2d908", "#9de61e", "#0d8be6", "#c61b1b", "#e26f08", "#8d35d1"),
	/**
	 * Color scheme name "Organic6" - <span style="background-color:#83992a; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#3c9770; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#44709d; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a23c33;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#d97828; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#deb340; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	ORGANIC6("Organic6", "#83992a", "#3c9770", "#44709d", "#a23c33", "#d97828", "#deb340"),
	/**
	 * Color scheme name "Oriel6" - <span style="background-color:#fe8637; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#7598d9; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b32c16; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f5cd2d;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#aebad5; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#777c84; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	ORIEL6("Oriel6", "#fe8637", "#7598d9", "#b32c16", "#f5cd2d", "#aebad5", "#777c84"),
	/**
	 * Color scheme name "Origin6" - <span style="background-color:#727ca3; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#9fb8cd; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d2da7a; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fada7a;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#b88472; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#8e736a; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	ORIGIN6("Origin6", "#727ca3", "#9fb8cd", "#d2da7a", "#fada7a", "#b88472", "#8e736a"),
	/**
	 * Color scheme name "Paper6" - <span style="background-color:#a5b592; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f3a447; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e7bc29; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d092a7;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#9c85c0; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#809ec2; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	PAPER6("Paper6", "#a5b592", "#f3a447", "#e7bc29", "#d092a7", "#9c85c0", "#809ec2"),
	/**
	 * Color scheme name "Parallax6" - <span style="background-color:#30acec; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#80c34f; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e29d3e; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d64a3b;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#d64787; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a666e1; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	PARALLAX6("Parallax6", "#30acec", "#80c34f", "#e29d3e", "#d64a3b", "#d64787", "#a666e1"),
	/**
	 * Color scheme name "Parcel6" - <span style="background-color:#f6a21d; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#9bafb5; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c96731; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#9ca383;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#87795d; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a0988c; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	PARCEL6("Parcel6", "#f6a21d", "#9bafb5", "#c96731", "#9ca383", "#87795d", "#a0988c"),
	/**
	 * Color scheme name "Perception6" - <span style="background-color:#a2c816; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e07602; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e4c402; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#7dc1ef;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#21449b; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a2b170; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	PERCEPTION6("Perception6", "#a2c816", "#e07602", "#e4c402", "#7dc1ef", "#21449b", "#a2b170"),
	/**
	 * Color scheme name "Perspective6" - <span style="background-color:#838d9b; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d2610c; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#80716a; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#94147c;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#5d5ad2; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#6f6c7d; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	PERSPECTIVE6("Perspective6", "#838d9b", "#d2610c", "#80716a", "#94147c", "#5d5ad2", "#6f6c7d"),
	/**
	 * Color scheme name "Pixel6" - <span style="background-color:#ff7f01; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f1b015; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fbec85; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d2c2f1;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#da5af4; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#9d09d1; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	PIXEL6("Pixel6", "#ff7f01", "#f1b015", "#fbec85", "#d2c2f1", "#da5af4", "#9d09d1"),
	/**
	 * Color scheme name "Plaza6" - <span style="background-color:#990000; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#580101; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e94a00; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#eb8f00;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#a4a4a4; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#666666; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	PLAZA6("Plaza6", "#990000", "#580101", "#e94a00", "#eb8f00", "#a4a4a4", "#666666"),
	/**
	 * Color scheme name "Precedent6" - <span style="background-color:#993232; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#9b6c34; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#736c5d; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c9972b;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#c95f2b; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#8f7a05; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	PRECEDENT6("Precedent6", "#993232", "#9b6c34", "#736c5d", "#c9972b", "#c95f2b", "#8f7a05"),
	/**
	 * Color scheme name "Pushpin6" - <span style="background-color:#fda023; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#aa2b1e; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#71685c; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#64a73b;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#eb5605; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b9ca1a; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	PUSHPIN6("Pushpin6", "#fda023", "#aa2b1e", "#71685c", "#64a73b", "#eb5605", "#b9ca1a"),
	/**
	 * Color scheme name "Quotable6" - <span style="background-color:#00c6bb; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#6feba0; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b6df5e; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#efb251;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#ef755f; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ed515c; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	QUOTABLE6("Quotable6", "#00c6bb", "#6feba0", "#b6df5e", "#efb251", "#ef755f", "#ed515c"),
	/**
	 * Color scheme name "Red6" - <span style="background-color:#a5300f; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d55816; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e19825; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b19c7d;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#7f5f52; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b27d49; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	RED6("Red6", "#a5300f", "#d55816", "#e19825", "#b19c7d", "#7f5f52", "#b27d49"),
	/**
	 * Color scheme name "RedOrange6" - <span style="background-color:#e84c22; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffbd47; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b64926; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ff8427;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#cc9900; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b22600; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	RED_ORANGE6("RedOrange6", "#e84c22", "#ffbd47", "#b64926", "#ff8427", "#cc9900", "#b22600"),
	/**
	 * Color scheme name "RedViolet6" - <span style="background-color:#e32d91; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c830cc; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#4ea6dc; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#4775e7;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#8971e1; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d54773; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	RED_VIOLET6("RedViolet6", "#e32d91", "#c830cc", "#4ea6dc", "#4775e7", "#8971e1", "#d54773"),
	/**
	 * Color scheme name "Retrospect6" - <span style="background-color:#e48312; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#bd582c; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#865640; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#9b8357;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#c2bc80; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#94a088; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	RETROSPECT6("Retrospect6", "#e48312", "#bd582c", "#865640", "#9b8357", "#c2bc80", "#94a088"),
	/**
	 * Color scheme name "Revolution6" - <span style="background-color:#0c5986; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ddf53d; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#508709; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#bf5e00;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#9c0001; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#660075; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	REVOLUTION6("Revolution6", "#0c5986", "#ddf53d", "#508709", "#bf5e00", "#9c0001", "#660075"),
	/**
	 * Color scheme name "Saddle6" - <span style="background-color:#c6b178; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#9c5b14; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#71b2bc; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#78aa5d;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#867099; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#4c6f75; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	SADDLE6("Saddle6", "#c6b178", "#9c5b14", "#71b2bc", "#78aa5d", "#867099", "#4c6f75"),
	/**
	 * Color scheme name "Savon6" - <span style="background-color:#1cade4; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#2683c6; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#27ced7; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#42ba97;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#3e8853; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#62a39f; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	SAVON6("Savon6", "#1cade4", "#2683c6", "#27ced7", "#42ba97", "#3e8853", "#62a39f"),
	/**
	 * Color scheme name "Sketchbook6" - <span style="background-color:#a63212; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e68230; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#9bb05e; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#6b9bc7;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#4e66b2; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#8976ac; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	SKETCHBOOK6("Sketchbook6", "#a63212", "#e68230", "#9bb05e", "#6b9bc7", "#4e66b2", "#8976ac"),
	/**
	 * Color scheme name "Sky6" - <span style="background-color:#073779; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#8fd9fb; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffcc00; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#eb6615;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#c76402; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b523b4; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	SKY6("Sky6", "#073779", "#8fd9fb", "#ffcc00", "#eb6615", "#c76402", "#b523b4"),
	/**
	 * Color scheme name "Slate6" - <span style="background-color:#bc451b; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d3ba68; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#bb8640; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ad9277;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#a55a43; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ad9d7b; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	SLATE6("Slate6", "#bc451b", "#d3ba68", "#bb8640", "#ad9277", "#a55a43", "#ad9d7b"),
	/**
	 * Color scheme name "Slice6" - <span style="background-color:#052f61; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a50e82; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#14967c; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#6a9e1f;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#e87d37; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c62324; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	SLICE6("Slice6", "#052f61", "#a50e82", "#14967c", "#6a9e1f", "#e87d37", "#c62324"),
	/**
	 * Color scheme name "Slipstream6" - <span style="background-color:#4e67c8; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#5eccf3; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a7ea52; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#5dceaf;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#ff8021; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f14124; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	SLIPSTREAM6("Slipstream6", "#4e67c8", "#5eccf3", "#a7ea52", "#5dceaf", "#ff8021", "#f14124"),
	/**
	 * Color scheme name "SOHO6" - <span style="background-color:#61625e; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#964d2c; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#66553e; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#848058;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#afa14b; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ad7d4d; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	SOHO6("SOHO6", "#61625e", "#964d2c", "#66553e", "#848058", "#afa14b", "#ad7d4d"),
	/**
	 * Color scheme name "Solstice6" - <span style="background-color:#3891a7; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#feb80a; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c32d2e; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#84aa33;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#964305; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#475a8d; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	SOLSTICE6("Solstice6", "#3891a7", "#feb80a", "#c32d2e", "#84aa33", "#964305", "#475a8d"),
	/**
	 * Color scheme name "Spectrum6" - <span style="background-color:#990000; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ff6600; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffba00; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#99cc00;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#528a02; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#333333; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	SPECTRUM6("Spectrum6", "#990000", "#ff6600", "#ffba00", "#99cc00", "#528a02", "#333333"),
	/**
	 * Color scheme name "Story6" - <span style="background-color:#1d86cd; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#732e9a; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b50b1b; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e8950e;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#55992b; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#2c9c89; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	STORY6("Story6", "#1d86cd", "#732e9a", "#b50b1b", "#e8950e", "#55992b", "#2c9c89"),
	/**
	 * Color scheme name "Studio6" - <span style="background-color:#f7901e; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fec60b; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#9fe62f; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#4ea5d1;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#1c4596; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#542d90; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	STUDIO6("Studio6", "#f7901e", "#fec60b", "#9fe62f", "#4ea5d1", "#1c4596", "#542d90"),
	/**
	 * Color scheme name "Summer6" - <span style="background-color:#51a6c2; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#51c2a9; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#7ec251; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e1dc53;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#b54721; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a16bb1; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	SUMMER6("Summer6", "#51a6c2", "#51c2a9", "#7ec251", "#e1dc53", "#b54721", "#a16bb1"),
	/**
	 * Color scheme name "Technic6" - <span style="background-color:#6ea0b0; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ccaf0a; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#8d89a4; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#748560;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#9e9273; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#7e848d; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	TECHNIC6("Technic6", "#6ea0b0", "#ccaf0a", "#8d89a4", "#748560", "#9e9273", "#7e848d"),
	/**
	 * Color scheme name "Thatch6" - <span style="background-color:#759aa5; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#cfc60d; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#99987f; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#90ac97;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#ffad1c; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b9ab6f; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	THATCH6("Thatch6", "#759aa5", "#cfc60d", "#99987f", "#90ac97", "#ffad1c", "#b9ab6f"),
	/**
	 * Color scheme name "Tradition6" - <span style="background-color:#6b4a0b; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#790a14; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#908342; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#423e5c;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#641345; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#748a2f; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	TRADITION6("Tradition6", "#6b4a0b", "#790a14", "#908342", "#423e5c", "#641345", "#748a2f"),
	/**
	 * Color scheme name "Travelogue6" - <span style="background-color:#b74d21; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a32323; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#4576a3; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#615d9a;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#67924b; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#bf7b1b; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	TRAVELOGUE6("Travelogue6", "#b74d21", "#a32323", "#4576a3", "#615d9a", "#67924b", "#bf7b1b"),
	/**
	 * Color scheme name "Trek6" - <span style="background-color:#f0a22e; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a5644e; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b58b80; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c3986d;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#a19574; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c17529; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	TREK6("Trek6", "#f0a22e", "#a5644e", "#b58b80", "#c3986d", "#a19574", "#c17529"),
	/**
	 * Color scheme name "Twilight6" - <span style="background-color:#e8bc4a; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#83c1c6; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e78d35; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#909ce1;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#839c41; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#cc5439; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	TWILIGHT6("Twilight6", "#e8bc4a", "#83c1c6", "#e78d35", "#909ce1", "#839c41", "#cc5439"),
	/**
	 * Color scheme name "Urban6" - <span style="background-color:#53548a; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#438086; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a04da3; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c4652d;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#8b5d3d; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#5c92b5; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	URBAN6("Urban6", "#53548a", "#438086", "#a04da3", "#c4652d", "#8b5d3d", "#5c92b5"),
	/**
	 * Color scheme name "UrbanPop6" - <span style="background-color:#86ce24; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#00a2e6; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fac810; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#7d8f8c;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#d06b20; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#958b8b; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	URBAN_POP6("UrbanPop6", "#86ce24", "#00a2e6", "#fac810", "#7d8f8c", "#d06b20", "#958b8b"),
	/**
	 * Color scheme name "VaporTrail6" - <span style="background-color:#df2e28; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fe801a; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e9bf35; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#81bb42;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#32c7a9; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#4a9bdc; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	VAPOR_TRAIL6("VaporTrail6", "#df2e28", "#fe801a", "#e9bf35", "#81bb42", "#32c7a9", "#4a9bdc"),
	/**
	 * Color scheme name "Venture6" - <span style="background-color:#9eb060; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d09a08; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f2ec86; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#824f1c;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#511818; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#553876; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	VENTURE6("Venture6", "#9eb060", "#d09a08", "#f2ec86", "#824f1c", "#511818", "#553876"),
	/**
	 * Color scheme name "Verve6" - <span style="background-color:#ff388c; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e40059; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#9c007f; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#68007f;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#005bd3; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#00349e; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	VERVE6("Verve6", "#ff388c", "#e40059", "#9c007f", "#68007f", "#005bd3", "#00349e"),
	/**
	 * Color scheme name "View6" - <span style="background-color:#6f6f74; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#92a9b9; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a7b789; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b9a489;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#8d6374; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#9b7362; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	VIEW6("View6", "#6f6f74", "#92a9b9", "#a7b789", "#b9a489", "#8d6374", "#9b7362"),
	/**
	 * Color scheme name "Violet6" - <span style="background-color:#ad84c6; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#8784c7; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#5d739a; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#6997af;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#84acb6; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#6f8183; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	VIOLET6("Violet6", "#ad84c6", "#8784c7", "#5d739a", "#6997af", "#84acb6", "#6f8183"),
	/**
	 * Color scheme name "VioletII6" - <span style="background-color:#92278f; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#9b57d3; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#755dd9; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#665eb8;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#45a5ed; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#5982db; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	VIOLET_II6("VioletII6", "#92278f", "#9b57d3", "#755dd9", "#665eb8", "#45a5ed", "#5982db"),
	/**
	 * Color scheme name "Waveform6" - <span style="background-color:#31b6fd; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#4584d3; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#5bd078; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a5d028;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#f5c040; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#05e0db; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	WAVEFORM6("Waveform6", "#31b6fd", "#4584d3", "#5bd078", "#a5d028", "#f5c040", "#05e0db"),
	/**
	 * Color scheme name "Wisp6" - <span style="background-color:#a53010; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#de7e18; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#9f8351; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#728653;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#92aa4c; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#6aac91; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	WISP6("Wisp6", "#a53010", "#de7e18", "#9f8351", "#728653", "#92aa4c", "#6aac91"),
	/**
	 * Color scheme name "WoodType6" - <span style="background-color:#d34817; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#9b2d1f; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a28e6a; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#956251;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#918485; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#855d5d; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	WOOD_TYPE6("WoodType6", "#d34817", "#9b2d1f", "#a28e6a", "#956251", "#918485", "#855d5d"),
	/**
	 * Color scheme name "Yellow6" - <span style="background-color:#ffca08; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f8931d; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ce8d3e; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ec7016;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#e64823; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#9c6a6a; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	YELLOW6("Yellow6", "#ffca08", "#f8931d", "#ce8d3e", "#ec7016", "#e64823", "#9c6a6a"),
	/**
	 * Color scheme name "YellowOrange6" - <span style="background-color:#f0a22e; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a5644e; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b58b80; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c3986d;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#a19574; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c17529; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	YELLOW_ORANGE6("YellowOrange6", "#f0a22e", "#a5644e", "#b58b80", "#c3986d", "#a19574", "#c17529");

	// Category name used to build the label to configure plugin.
	private static final String CATEGORY = "office";
	// name value of property
	private final String value;
	// list of colors for each scheme
	private final List<IsColor> colors = new LinkedList<>();

	/**
	 * Builds a scheme using argument as list of colors in HEX format.
	 * 
	 * @param value value of property name
	 * @param hexColors list of colors in HEX format
	 */
	private OfficeScheme(String value, String... hexColors) {
		this.value = value;
		// scans all hex colors and creates ISCOLOR
		for (String color : hexColors) {
			// and adds them into the list
			colors.add(ColorBuilder.parse(color));
		}
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.impl.plugins.Scheme#category()
	 */
	@Override
	public String category() {
		return CATEGORY;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.impl.plugins.Scheme#getColors()
	 */
	@Override
	public List<IsColor> getColors() {
		return colors;
	}

}
