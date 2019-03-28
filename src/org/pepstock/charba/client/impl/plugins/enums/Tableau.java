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
 * Contains all scheme definitions to map the Tableau schemes.<br>
 * To configure "{@value ColorSchemes#ID}" plugin, where category is <b>"tableau"</b>.<br>
 * Every color scheme has a number at the end of its name, which indicates the number of that colors included in the scheme. If
 * the number of the datasets is larger than it, the same colors will appear repeatedly. A color is not modified if it is
 * specified by dataset options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum Tableau implements ColorScheme
{
	/**
	 * Color scheme name "Tableau10" - <span style="background-color:#4e79a7; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f28e2b; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e15759; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#76b7b2;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#59a14f; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#edc948; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b07aa1; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ff9da7;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#9c755f; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#bab0ac; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Tableau10("#4e79a7", "#f28e2b", "#e15759", "#76b7b2", "#59a14f", "#edc948", "#b07aa1", "#ff9da7", "#9c755f", "#bab0ac"),
	/**
	 * Color scheme name "Tableau20" - <span style="background-color:#4e79a7; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a0cbe8; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f28e2b; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffbe7d;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#59a14f; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#8cd17d; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b6992d; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f1ce63;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#499894; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#86bcb6; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e15759; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ff9d9a;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#79706e; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#bab0ac; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d37295; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fabfd2;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#b07aa1; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d4a6c8; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#9d7660; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d7b5a6;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Tableau20("#4e79a7", "#a0cbe8", "#f28e2b", "#ffbe7d", "#59a14f", "#8cd17d", "#b6992d", "#f1ce63", "#499894", "#86bcb6", "#e15759", "#ff9d9a", "#79706e", "#bab0ac", "#d37295", "#fabfd2", "#b07aa1", "#d4a6c8", "#9d7660", "#d7b5a6"),
	/**
	 * Color scheme name "ColorBlind10" - <span style="background-color:#1170aa; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fc7d0b; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a3acb9; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#57606c;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#5fa2ce; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c85200; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#7b848f; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a3cce9;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#ffbc79; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c8d0d9; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	ColorBlind10("#1170aa", "#fc7d0b", "#a3acb9", "#57606c", "#5fa2ce", "#c85200", "#7b848f", "#a3cce9", "#ffbc79", "#c8d0d9"),
	/**
	 * Color scheme name "SeattleGrays5" - <span style="background-color:#767f8b; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b3b7b8; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#5c6068; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#D3D3D3;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#989ca3; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	SeattleGrays5("#767f8b", "#b3b7b8", "#5c6068", "#D3D3D3", "#989ca3"),
	/**
	 * Color scheme name "Traffic9" - <span style="background-color:#b60a1c; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e39802; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#309143; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e03531;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#f0bd27; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#51b364; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ff684c; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffda66;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#8ace7e; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Traffic9("#b60a1c", "#e39802", "#309143", "#e03531", "#f0bd27", "#51b364", "#ff684c", "#ffda66", "#8ace7e"),
	/**
	 * Color scheme name "MillerStone11" - <span style="background-color:#4f6980; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#849db1; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a2ceaa; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#638b66;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#bfbb60; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f47942; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fbb04e; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b66353;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#d7ce9f; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b9aa97; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#7e756d; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	MillerStone11("#4f6980", "#849db1", "#a2ceaa", "#638b66", "#bfbb60", "#f47942", "#fbb04e", "#b66353", "#d7ce9f", "#b9aa97", "#7e756d"),
	/**
	 * Color scheme name "SuperfishelStone10" - <span style="background-color:#6388b4; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffae34; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ef6f6a; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#8cc2ca;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#55ad89; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c3bc3f; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#bb7693; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#baa094;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#a9b5ae; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#767676; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	SuperfishelStone10("#6388b4", "#ffae34", "#ef6f6a", "#8cc2ca", "#55ad89", "#c3bc3f", "#bb7693", "#baa094", "#a9b5ae", "#767676"),
	/**
	 * Color scheme name "NurielStone9" - <span style="background-color:#8175aa; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#6fb899; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#31a1b3; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ccb22b;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#a39fc9; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#94d0c0; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#959c9e; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#027b8e;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#9f8f12; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	NurielStone9("#8175aa", "#6fb899", "#31a1b3", "#ccb22b", "#a39fc9", "#94d0c0", "#959c9e", "#027b8e", "#9f8f12"),
	/**
	 * Color scheme name "JewelBright9" - <span style="background-color:#eb1e2c; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fd6f30; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f9a729; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f9d23c;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#5fbb68; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#64cdcc; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#91dcea; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a4a4d5;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#bbc9e5; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	JewelBright9("#eb1e2c", "#fd6f30", "#f9a729", "#f9d23c", "#5fbb68", "#64cdcc", "#91dcea", "#a4a4d5", "#bbc9e5"),
	/**
	 * Color scheme name "Summer8" - <span style="background-color:#bfb202; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b9ca5d; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#cf3e53; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f1788d;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#00a2b3; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#97cfd0; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f3a546; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f7c480;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Summer8("#bfb202", "#b9ca5d", "#cf3e53", "#f1788d", "#00a2b3", "#97cfd0", "#f3a546", "#f7c480"),
	/**
	 * Color scheme name "Winter10" - <span style="background-color:#90728f; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b9a0b4; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#9d983d; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#cecb76;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#e15759; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ff9888; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#6b6b6b; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#bab2ae;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#aa8780; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#dab6af; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Winter10("#90728f", "#b9a0b4", "#9d983d", "#cecb76", "#e15759", "#ff9888", "#6b6b6b", "#bab2ae", "#aa8780", "#dab6af"),
	/**
	 * Color scheme name "GreenOrangeTeal12" - <span style="background-color:#4e9f50; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#87d180; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ef8a0c; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fcc66d;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#3ca8bc; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#98d9e4; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#94a323; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c3ce3d;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#a08400; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f7d42a; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#26897e; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#8dbfa8;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	GreenOrangeTeal12("#4e9f50", "#87d180", "#ef8a0c", "#fcc66d", "#3ca8bc", "#98d9e4", "#94a323", "#c3ce3d", "#a08400", "#f7d42a", "#26897e", "#8dbfa8"),
	/**
	 * Color scheme name "RedBlueBrown12" - <span style="background-color:#466f9d; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#91b3d7; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ed444a; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#feb5a2;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#9d7660; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d7b5a6; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#3896c4; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a0d4ee;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#ba7e45; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#39b87f; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c8133b; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ea8783;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	RedBlueBrown12("#466f9d", "#91b3d7", "#ed444a", "#feb5a2", "#9d7660", "#d7b5a6", "#3896c4", "#a0d4ee", "#ba7e45", "#39b87f", "#c8133b", "#ea8783"),
	/**
	 * Color scheme name "PurplePinkGray12" - <span style="background-color:#8074a8; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c6c1f0; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c46487; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffbed1;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#9c9290; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c5bfbe; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#9b93c9; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ddb5d5;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#7c7270; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f498b6; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b173a0; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c799bc;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	PurplePinkGray12("#8074a8", "#c6c1f0", "#c46487", "#ffbed1", "#9c9290", "#c5bfbe", "#9b93c9", "#ddb5d5", "#7c7270", "#f498b6", "#b173a0", "#c799bc"),
	/**
	 * Color scheme name "HueCircle19" - <span style="background-color:#1ba3c6; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#2cb5c0; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#30bcad; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#21b087;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#33a65c; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#57a337; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a2b627; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d5bb21;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#f8b620; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f89217; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f06719; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e03426;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#f64971; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fc719e; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#eb73b3; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ce69be;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#a26dc2; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#7873c0; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#4f7cba; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	HueCircle19("#1ba3c6", "#2cb5c0", "#30bcad", "#21b087", "#33a65c", "#57a337", "#a2b627", "#d5bb21", "#f8b620", "#f89217", "#f06719", "#e03426", "#f64971", "#fc719e", "#eb73b3", "#ce69be", "#a26dc2", "#7873c0", "#4f7cba"),
	/**
	 * Color scheme name "OrangeBlue7" - <span style="background-color:#9e3d22; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d45b21; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f69035; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d9d5c9;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#77acd3; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#4f81af; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#2b5c8a; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	OrangeBlue7("#9e3d22", "#d45b21", "#f69035", "#d9d5c9", "#77acd3", "#4f81af", "#2b5c8a"),
	/**
	 * Color scheme name "RedGreen7" - <span style="background-color:#a3123a; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e33f43; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f8816b; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ced7c3;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#73ba67; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#44914e; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#24693d; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	RedGreen7("#a3123a", "#e33f43", "#f8816b", "#ced7c3", "#73ba67", "#44914e", "#24693d"),
	/**
	 * Color scheme name "GreenBlue7" - <span style="background-color:#24693d; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#45934d; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#75bc69; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c9dad2;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#77a9cf; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#4e7fab; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#2a5783; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	GreenBlue7("#24693d", "#45934d", "#75bc69", "#c9dad2", "#77a9cf", "#4e7fab", "#2a5783"),
	/**
	 * Color scheme name "RedBlue7" - <span style="background-color:#a90c38; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e03b42; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f87f69; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#dfd4d1;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#7eaed3; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#5383af; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#2e5a87; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	RedBlue7("#a90c38", "#e03b42", "#f87f69", "#dfd4d1", "#7eaed3", "#5383af", "#2e5a87"),
	/**
	 * Color scheme name "RedBlack7" - <span style="background-color:#ae123a; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e33e43; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f8816b; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d9d9d9;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#a0a7a8; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#707c83; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#49525e; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	RedBlack7("#ae123a", "#e33e43", "#f8816b", "#d9d9d9", "#a0a7a8", "#707c83", "#49525e"),
	/**
	 * Color scheme name "GoldPurple7" - <span style="background-color:#ad9024; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c1a33b; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d4b95e; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e3d8cf;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#d4a3c3; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c189b0; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ac7299; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	GoldPurple7("#ad9024", "#c1a33b", "#d4b95e", "#e3d8cf", "#d4a3c3", "#c189b0", "#ac7299"),
	/**
	 * Color scheme name "RedGreenGold7" - <span style="background-color:#be2a3e; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e25f48; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f88f4d; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f4d166;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#90b960; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#4b9b5f; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#22763f; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	RedGreenGold7("#be2a3e", "#e25f48", "#f88f4d", "#f4d166", "#90b960", "#4b9b5f", "#22763f"),
	/**
	 * Color scheme name "SunsetSunrise7" - <span style="background-color:#33608c; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#9768a5; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e7718a; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f6ba57;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#ed7846; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d54c45; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b81840; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	SunsetSunrise7("#33608c", "#9768a5", "#e7718a", "#f6ba57", "#ed7846", "#d54c45", "#b81840"),
	/**
	 * Color scheme name "OrangeBlueWhite7" - <span style="background-color:#9e3d22; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e36621; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fcad52; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#FFFFFF;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#95c5e1; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#5b8fbc; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#2b5c8a; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	OrangeBlueWhite7("#9e3d22", "#e36621", "#fcad52", "#FFFFFF", "#95c5e1", "#5b8fbc", "#2b5c8a"),
	/**
	 * Color scheme name "RedGreenWhite7" - <span style="background-color:#ae123a; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ee574d; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fdac9e; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#FFFFFF;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#91d183; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#539e52; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#24693d; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	RedGreenWhite7("#ae123a", "#ee574d", "#fdac9e", "#FFFFFF", "#91d183", "#539e52", "#24693d"),
	/**
	 * Color scheme name "GreenBlueWhite7" - <span style="background-color:#24693d; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#529c51; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#8fd180; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#FFFFFF;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#95c1dd; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#598ab5; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#2a5783; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	GreenBlueWhite7("#24693d", "#529c51", "#8fd180", "#FFFFFF", "#95c1dd", "#598ab5", "#2a5783"),
	/**
	 * Color scheme name "RedBlueWhite7" - <span style="background-color:#a90c38; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ec534b; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#feaa9a; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#FFFFFF;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#9ac4e1; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#5c8db8; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#2e5a87; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	RedBlueWhite7("#a90c38", "#ec534b", "#feaa9a", "#FFFFFF", "#9ac4e1", "#5c8db8", "#2e5a87"),
	/**
	 * Color scheme name "RedBlackWhite7" - <span style="background-color:#ae123a; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ee574d; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fdac9d; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#FFFFFF;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#bdc0bf; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#7d888d; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#49525e; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	RedBlackWhite7("#ae123a", "#ee574d", "#fdac9d", "#FFFFFF", "#bdc0bf", "#7d888d", "#49525e"),
	/**
	 * Color scheme name "OrangeBlueLight7" - <span style="background-color:#ffcc9e; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f9d4b6; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f0dccd; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e5e5e5;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#dae1ea; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#cfdcef; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c4d8f3; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	OrangeBlueLight7("#ffcc9e", "#f9d4b6", "#f0dccd", "#e5e5e5", "#dae1ea", "#cfdcef", "#c4d8f3"),
	/**
	 * Color scheme name "Temperature7" - <span style="background-color:#529985; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#6c9e6e; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#99b059; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#dbcf47;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#ebc24b; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e3a14f; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c26b51; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Temperature7("#529985", "#6c9e6e", "#99b059", "#dbcf47", "#ebc24b", "#e3a14f", "#c26b51"),
	/**
	 * Color scheme name "BlueGreen7" - <span style="background-color:#feffd9; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f2fabf; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#dff3b2; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c4eab1;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#94d6b7; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#69c5be; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#41b7c4; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	BlueGreen7("#feffd9", "#f2fabf", "#dff3b2", "#c4eab1", "#94d6b7", "#69c5be", "#41b7c4"),
	/**
	 * Color scheme name "BlueLight7" - <span style="background-color:#e5e5e5; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e0e3e8; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#dbe1ea; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d5dfec;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#d0dcef; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#cadaf1; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c4d8f3; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	BlueLight7("#e5e5e5", "#e0e3e8", "#dbe1ea", "#d5dfec", "#d0dcef", "#cadaf1", "#c4d8f3"),
	/**
	 * Color scheme name "OrangeLight7" - <span style="background-color:#e5e5e5; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ebe1d9; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f0ddcd; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f5d9c2;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#f9d4b6; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fdd0aa; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffcc9e; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	OrangeLight7("#e5e5e5", "#ebe1d9", "#f0ddcd", "#f5d9c2", "#f9d4b6", "#fdd0aa", "#ffcc9e"),
	/**
	 * Color scheme name "Blue20" - <span style="background-color:#b9ddf1; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#afd6ed; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a5cfe9; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#9bc7e4;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#92c0df; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#89b8da; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#80b0d5; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#79aacf;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#72a3c9; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#6a9bc3; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#6394be; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#5b8cb8;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#5485b2; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#4e7fac; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#4878a6; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#437a9f;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#3d6a98; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#376491; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#305d8a; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#2a5783;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Blue20("#b9ddf1", "#afd6ed", "#a5cfe9", "#9bc7e4", "#92c0df", "#89b8da", "#80b0d5", "#79aacf", "#72a3c9", "#6a9bc3", "#6394be", "#5b8cb8", "#5485b2", "#4e7fac", "#4878a6", "#437a9f", "#3d6a98", "#376491", "#305d8a", "#2a5783"),
	/**
	 * Color scheme name "Orange20" - <span style="background-color:#ffc685; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fcbe75; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f9b665; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f7ae54;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#f5a645; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f59c3c; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f49234; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f2882d;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#f07e27; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ee7422; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e96b20; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e36420;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#db5e20; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d25921; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ca5422; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c14f22;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#b84b23; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#af4623; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a64122; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#9e3d22;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Orange20("#ffc685", "#fcbe75", "#f9b665", "#f7ae54", "#f5a645", "#f59c3c", "#f49234", "#f2882d", "#f07e27", "#ee7422", "#e96b20", "#e36420", "#db5e20", "#d25921", "#ca5422", "#c14f22", "#b84b23", "#af4623", "#a64122", "#9e3d22"),
	/**
	 * Color scheme name "Green20" - <span style="background-color:#b3e0a6; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a5db96; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#98d687; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#8ed07f;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#85ca77; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#7dc370; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#75bc69; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#6eb663;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#67af5c; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#61a956; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#59a253; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#519c51;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#49964f; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#428f4d; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#398949; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#308344;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#2b7c40; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#27763d; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#256f3d; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#24693d;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Green20("#b3e0a6", "#a5db96", "#98d687", "#8ed07f", "#85ca77", "#7dc370", "#75bc69", "#6eb663", "#67af5c", "#61a956", "#59a253", "#519c51", "#49964f", "#428f4d", "#398949", "#308344", "#2b7c40", "#27763d", "#256f3d", "#24693d"),
	/**
	 * Color scheme name "Red20" - <span style="background-color:#ffbeb2; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#feb4a6; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fdab9b; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fca290;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#fb9984; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fa8f79; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f9856e; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f77b66;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#f5715d; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f36754; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f05c4d; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ec5049;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#e74545; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e13b42; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#da323f; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d3293d;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#ca223c; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c11a3b; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b8163a; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ae123a;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Red20("#ffbeb2", "#feb4a6", "#fdab9b", "#fca290", "#fb9984", "#fa8f79", "#f9856e", "#f77b66", "#f5715d", "#f36754", "#f05c4d", "#ec5049", "#e74545", "#e13b42", "#da323f", "#d3293d", "#ca223c", "#c11a3b", "#b8163a", "#ae123a"),
	/**
	 * Color scheme name "Purple20" - <span style="background-color:#eec9e5; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#eac1df; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e6b9d9; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e0b2d2;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#daabcb; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d5a4c4; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#cf9dbe; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ca96b8;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#c48fb2; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#be89ac; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b882a6; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b27ba1;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#aa759d; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a27099; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#9a6a96; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#926591;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#8c5f86; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#865986; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#81537f; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#7c4d79;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Purple20("#eec9e5", "#eac1df", "#e6b9d9", "#e0b2d2", "#daabcb", "#d5a4c4", "#cf9dbe", "#ca96b8", "#c48fb2", "#be89ac", "#b882a6", "#b27ba1", "#aa759d", "#a27099", "#9a6a96", "#926591", "#8c5f86", "#865986", "#81537f", "#7c4d79"),
	/**
	 * Color scheme name "Brown20" - <span style="background-color:#eedbbd; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ecd2ad; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ebc994; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#eac085;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#e8b777; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e5ae6c; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e2a562; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#de9d5a;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#d99455; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d38c54; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ce8451; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c9784d;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#c47247; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c16941; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#bd6036; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b85636;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#b34d34; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ad4433; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a63d32; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#9f3632;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Brown20("#eedbbd", "#ecd2ad", "#ebc994", "#eac085", "#e8b777", "#e5ae6c", "#e2a562", "#de9d5a", "#d99455", "#d38c54", "#ce8451", "#c9784d", "#c47247", "#c16941", "#bd6036", "#b85636", "#b34d34", "#ad4433", "#a63d32", "#9f3632"),
	/**
	 * Color scheme name "Gray20" - <span style="background-color:#d5d5d5; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#cdcecd; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c5c7c6; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#bcbfbe;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#b4b7b7; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#acb0b1; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a4a9ab; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#9ca3a4;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#939c9e; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#8b9598; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#848e93; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#7c878d;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#758087; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#6e7a81; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#67737c; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#616c77;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#5b6570; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#555f6a; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#4f5864; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#49525e;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Gray20("#d5d5d5", "#cdcecd", "#c5c7c6", "#bcbfbe", "#b4b7b7", "#acb0b1", "#a4a9ab", "#9ca3a4", "#939c9e", "#8b9598", "#848e93", "#7c878d", "#758087", "#6e7a81", "#67737c", "#616c77", "#5b6570", "#555f6a", "#4f5864", "#49525e"),
	/**
	 * Color scheme name "GrayWarm20" - <span style="background-color:#dcd4d0; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d4ccc8; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#cdc4c0; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c5bdb9;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#beb6b2; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b7afab; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b0a7a4; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a9a09d;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#a29996; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#9b938f; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#948c88; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#8d8481;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#867e7b; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#807774; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#79706e; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#736967;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#6c6260; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#665c51; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#5f5654; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#59504e;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	GrayWarm20("#dcd4d0", "#d4ccc8", "#cdc4c0", "#c5bdb9", "#beb6b2", "#b7afab", "#b0a7a4", "#a9a09d", "#a29996", "#9b938f", "#948c88", "#8d8481", "#867e7b", "#807774", "#79706e", "#736967", "#6c6260", "#665c51", "#5f5654", "#59504e"),
	/**
	 * Color scheme name "BlueTeal20" - <span style="background-color:#bce4d8; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#aedcd5; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a1d5d2; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#95cecf;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#89c8cc; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#7ec1ca; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#72bac6; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#66b2c2;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#59acbe; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#4ba5ba; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#419eb6; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#3b96b2;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#358ead; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#3586a7; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#347ea1; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#32779b;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#316f96; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#2f6790; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#2d608a; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#2c5985;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	BlueTeal20("#bce4d8", "#aedcd5", "#a1d5d2", "#95cecf", "#89c8cc", "#7ec1ca", "#72bac6", "#66b2c2", "#59acbe", "#4ba5ba", "#419eb6", "#3b96b2", "#358ead", "#3586a7", "#347ea1", "#32779b", "#316f96", "#2f6790", "#2d608a", "#2c5985"),
	/**
	 * Color scheme name "OrangeGold20" - <span style="background-color:#f4d166; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f6c760; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f8bc58; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f8b252;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#f7a84a; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f69e41; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f49538; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f38b2f;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#f28026; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f0751e; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#eb6c1c; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e4641e;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#de5d1f; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d75521; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#cf4f22; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c64a22;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#bc4623; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b24223; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a83e24; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#9e3a26;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	OrangeGold20("#f4d166", "#f6c760", "#f8bc58", "#f8b252", "#f7a84a", "#f69e41", "#f49538", "#f38b2f", "#f28026", "#f0751e", "#eb6c1c", "#e4641e", "#de5d1f", "#d75521", "#cf4f22", "#c64a22", "#bc4623", "#b24223", "#a83e24", "#9e3a26"),
	/**
	 * Color scheme name "GreenGold20" - <span style="background-color:#f4d166; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e3cd62; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d3c95f; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c3c55d;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#b2c25b; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a3bd5a; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#93b958; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#84b457;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#76af56; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#67a956; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#5aa355; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#4f9e53;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#479751; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#40914f; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#3a8a4d; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#34844a;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#2d7d45; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#257740; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#1c713b; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#146c36;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	GreenGold20("#f4d166", "#e3cd62", "#d3c95f", "#c3c55d", "#b2c25b", "#a3bd5a", "#93b958", "#84b457", "#76af56", "#67a956", "#5aa355", "#4f9e53", "#479751", "#40914f", "#3a8a4d", "#34844a", "#2d7d45", "#257740", "#1c713b", "#146c36"),
	/**
	 * Color scheme name "RedGold21" - <span style="background-color:#f4d166; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f5c75f; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f6bc58; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f7b254;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#f9a750; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fa9d4f; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fa9d4f; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fb934d;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#f7894b; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f47f4a; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f0774a; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#eb6349;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#e66549; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e15c48; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#dc5447; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d64c45;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#d04344; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ca3a42; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c43141; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#bd273f;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#b71d3e; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	RedGold21("#f4d166", "#f5c75f", "#f6bc58", "#f7b254", "#f9a750", "#fa9d4f", "#fa9d4f", "#fb934d", "#f7894b", "#f47f4a", "#f0774a", "#eb6349", "#e66549", "#e15c48", "#dc5447", "#d64c45", "#d04344", "#ca3a42", "#c43141", "#bd273f", "#b71d3e"),
	/**
	 * Color scheme name "Classic10" - <span style="background-color:#1f77b4; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ff7f0e; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#2ca02c; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d62728;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#9467bd; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#8c564b; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e377c2; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#7f7f7f;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#bcbd22; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#17becf; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Classic10("#1f77b4", "#ff7f0e", "#2ca02c", "#d62728", "#9467bd", "#8c564b", "#e377c2", "#7f7f7f", "#bcbd22", "#17becf"),
	/**
	 * Color scheme name "ClassicMedium10" - <span style="background-color:#729ece; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ff9e4a; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#67bf5c; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ed665d;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#ad8bc9; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a8786e; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ed97ca; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a2a2a2;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#cdcc5d; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#6dccda; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	ClassicMedium10("#729ece", "#ff9e4a", "#67bf5c", "#ed665d", "#ad8bc9", "#a8786e", "#ed97ca", "#a2a2a2", "#cdcc5d", "#6dccda"),
	/**
	 * Color scheme name "ClassicLight10" - <span style="background-color:#aec7e8; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffbb78; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#98df8a; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ff9896;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#c5b0d5; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c49c94; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f7b6d2; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c7c7c7;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#dbdb8d; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#9edae5; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	ClassicLight10("#aec7e8", "#ffbb78", "#98df8a", "#ff9896", "#c5b0d5", "#c49c94", "#f7b6d2", "#c7c7c7", "#dbdb8d", "#9edae5"),
	/**
	 * Color scheme name "Classic20" - <span style="background-color:#1f77b4; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#aec7e8; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ff7f0e; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffbb78;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#2ca02c; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#98df8a; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d62728; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ff9896;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#9467bd; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c5b0d5; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#8c564b; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c49c94;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#e377c2; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f7b6d2; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#7f7f7f; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c7c7c7;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#bcbd22; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#dbdb8d; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#17becf; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#9edae5;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Classic20("#1f77b4", "#aec7e8", "#ff7f0e", "#ffbb78", "#2ca02c", "#98df8a", "#d62728", "#ff9896", "#9467bd", "#c5b0d5", "#8c564b", "#c49c94", "#e377c2", "#f7b6d2", "#7f7f7f", "#c7c7c7", "#bcbd22", "#dbdb8d", "#17becf", "#9edae5"),
	/**
	 * Color scheme name "ClassicGray5" - <span style="background-color:#60636a; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a5acaf; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#414451; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#8f8782;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#cfcfcf; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	ClassicGray5("#60636a", "#a5acaf", "#414451", "#8f8782", "#cfcfcf"),
	/**
	 * Color scheme name "ClassicColorBlind10" - <span style="background-color:#006ba4; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ff800e; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ababab; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#595959;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#5f9ed1; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c85200; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#898989; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a2c8ec;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#ffbc79; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#cfcfcf; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	ClassicColorBlind10("#006ba4", "#ff800e", "#ababab", "#595959", "#5f9ed1", "#c85200", "#898989", "#a2c8ec", "#ffbc79", "#cfcfcf"),
	/**
	 * Color scheme name "ClassicTrafficLight9" - <span style="background-color:#b10318; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#dba13a; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#309343; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d82526;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#ffc156; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#69b764; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f26c64; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffdd71;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#9fcd99; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	ClassicTrafficLight9("#b10318", "#dba13a", "#309343", "#d82526", "#ffc156", "#69b764", "#f26c64", "#ffdd71", "#9fcd99"),
	/**
	 * Color scheme name "ClassicPurpleGray6" - <span style="background-color:#7b66d2; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#dc5fbd; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#94917b; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#995688;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#d098ee; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d7d5c5; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	ClassicPurpleGray6("#7b66d2", "#dc5fbd", "#94917b", "#995688", "#d098ee", "#d7d5c5"),
	/**
	 * Color scheme name "ClassicPurpleGray12" - <span style="background-color:#7b66d2; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a699e8; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#dc5fbd; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffc0da;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#5f5a41; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b4b19b; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#995688; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d898ba;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#ab6ad5; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d098ee; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#8b7c6e; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#dbd4c5;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	ClassicPurpleGray12("#7b66d2", "#a699e8", "#dc5fbd", "#ffc0da", "#5f5a41", "#b4b19b", "#995688", "#d898ba", "#ab6ad5", "#d098ee", "#8b7c6e", "#dbd4c5"),
	/**
	 * Color scheme name "ClassicGreenOrange6" - <span style="background-color:#32a251; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ff7f0f; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#3cb7cc; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffd94a;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#39737c; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b85a0d; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	ClassicGreenOrange6("#32a251", "#ff7f0f", "#3cb7cc", "#ffd94a", "#39737c", "#b85a0d"),
	/**
	 * Color scheme name "ClassicGreenOrange12" - <span style="background-color:#32a251; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#acd98d; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ff7f0f; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffb977;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#3cb7cc; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#98d9e4; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b85a0d; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffd94a;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#39737c; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#86b4a9; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#82853b; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ccc94d;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	ClassicGreenOrange12("#32a251", "#acd98d", "#ff7f0f", "#ffb977", "#3cb7cc", "#98d9e4", "#b85a0d", "#ffd94a", "#39737c", "#86b4a9", "#82853b", "#ccc94d"),
	/**
	 * Color scheme name "ClassicBlueRed6" - <span style="background-color:#2c69b0; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f02720; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ac613c; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#6ba3d6;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#ea6b73; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e9c39b; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	ClassicBlueRed6("#2c69b0", "#f02720", "#ac613c", "#6ba3d6", "#ea6b73", "#e9c39b"),
	/**
	 * Color scheme name "ClassicBlueRed12" - <span style="background-color:#2c69b0; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b5c8e2; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f02720; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffb6b0;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#ac613c; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e9c39b; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#6ba3d6; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b5dffd;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#ac8763; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ddc9b4; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#bd0a36; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f4737a;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	ClassicBlueRed12("#2c69b0", "#b5c8e2", "#f02720", "#ffb6b0", "#ac613c", "#e9c39b", "#6ba3d6", "#b5dffd", "#ac8763", "#ddc9b4", "#bd0a36", "#f4737a"),
	/**
	 * Color scheme name "ClassicCyclic13" - <span style="background-color:#1f83b4; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#12a2a8; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#2ca030; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#78a641;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#bcbd22; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffbf50; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffaa0e; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ff7f0e;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#d63a3a; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c7519c; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ba43b4; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#8a60b0;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#6f63bb; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	ClassicCyclic13("#1f83b4", "#12a2a8", "#2ca030", "#78a641", "#bcbd22", "#ffbf50", "#ffaa0e", "#ff7f0e", "#d63a3a", "#c7519c", "#ba43b4", "#8a60b0", "#6f63bb"),
	/**
	 * Color scheme name "ClassicGreen7" - <span style="background-color:#bccfb4; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#94bb83; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#69a761; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#339444;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#27823b; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#1a7232; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#09622a; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	ClassicGreen7("#bccfb4", "#94bb83", "#69a761", "#339444", "#27823b", "#1a7232", "#09622a"),
	/**
	 * Color scheme name "ClassicGray13" - <span style="background-color:#c3c3c3; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b2b2b2; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a2a2a2; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#929292;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#838383; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#747474; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#666666; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#585858;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#4b4b4b; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#3f3f3f; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#333333; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#282828;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#1e1e1e; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	ClassicGray13("#c3c3c3", "#b2b2b2", "#a2a2a2", "#929292", "#838383", "#747474", "#666666", "#585858", "#4b4b4b", "#3f3f3f", "#333333", "#282828", "#1e1e1e"),
	/**
	 * Color scheme name "ClassicBlue7" - <span style="background-color:#b4d4da; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#7bc8e2; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#67add4; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#3a87b7;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#1c73b1; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#1c5998; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#26456e; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	ClassicBlue7("#b4d4da", "#7bc8e2", "#67add4", "#3a87b7", "#1c73b1", "#1c5998", "#26456e"),
	/**
	 * Color scheme name "ClassicRed9" - <span style="background-color:#eac0bd; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f89a90; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f57667; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e35745;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#d8392c; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#cf1719; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c21417; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b10c1d;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#9c0824; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	ClassicRed9("#eac0bd", "#f89a90", "#f57667", "#e35745", "#d8392c", "#cf1719", "#c21417", "#b10c1d", "#9c0824"),
	/**
	 * Color scheme name "ClassicOrange7" - <span style="background-color:#f0c294; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fdab67; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fd8938; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f06511;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#d74401; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a33202; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#7b3014; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	ClassicOrange7("#f0c294", "#fdab67", "#fd8938", "#f06511", "#d74401", "#a33202", "#7b3014"),
	/**
	 * Color scheme name "ClassicAreaRed11" - <span style="background-color:#f5cac7; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fbb3ab; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fd9c8f; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fe8b7a;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#fd7864; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f46b55; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ea5e45; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e04e35;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#d43e25; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c92b14; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#bd1100; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	ClassicAreaRed11("#f5cac7", "#fbb3ab", "#fd9c8f", "#fe8b7a", "#fd7864", "#f46b55", "#ea5e45", "#e04e35", "#d43e25", "#c92b14", "#bd1100"),
	/**
	 * Color scheme name "ClassicAreaGreen11" - <span style="background-color:#dbe8b4; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c3e394; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#acdc7a; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#9ad26d;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#8ac765; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#7abc5f; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#6cae59; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#60a24d;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#569735; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#4a8c1c; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#3c8200; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	ClassicAreaGreen11("#dbe8b4", "#c3e394", "#acdc7a", "#9ad26d", "#8ac765", "#7abc5f", "#6cae59", "#60a24d", "#569735", "#4a8c1c", "#3c8200"),
	/**
	 * Color scheme name "ClassicAreaBrown11" - <span style="background-color:#f3e0c2; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f6d29c; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f7c577; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f0b763;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#e4aa63; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d89c63; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#cc8f63; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c08262;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#bb7359; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#bb6348; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#bb5137; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	ClassicAreaBrown11("#f3e0c2", "#f6d29c", "#f7c577", "#f0b763", "#e4aa63", "#d89c63", "#cc8f63", "#c08262", "#bb7359", "#bb6348", "#bb5137"),
	/**
	 * Color scheme name "ClassicRedGreen11" - <span style="background-color:#9c0824; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#bd1316; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d11719; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#df513f;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#fc8375; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#cacaca; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a2c18f; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#69a761;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#2f8e41; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#1e7735; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#09622a; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	ClassicRedGreen11("#9c0824", "#bd1316", "#d11719", "#df513f", "#fc8375", "#cacaca", "#a2c18f", "#69a761", "#2f8e41", "#1e7735", "#09622a"),
	/**
	 * Color scheme name "ClassicRedBlue11" - <span style="background-color:#9c0824; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#bd1316; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d11719; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#df513f;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#fc8375; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#cacaca; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#67add4; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#3a87b7;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#1c73b1; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#1c5998; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#26456e; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	ClassicRedBlue11("#9c0824", "#bd1316", "#d11719", "#df513f", "#fc8375", "#cacaca", "#67add4", "#3a87b7", "#1c73b1", "#1c5998", "#26456e"),
	/**
	 * Color scheme name "ClassicRedBlack11" - <span style="background-color:#9c0824; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#bd1316; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d11719; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#df513f;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#fc8375; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#cacaca; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#9b9b9b; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#777777;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#565656; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#383838; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#1e1e1e; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	ClassicRedBlack11("#9c0824", "#bd1316", "#d11719", "#df513f", "#fc8375", "#cacaca", "#9b9b9b", "#777777", "#565656", "#383838", "#1e1e1e"),
	/**
	 * Color scheme name "ClassicAreaRedGreen21" - <span style="background-color:#bd1100; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c82912; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d23a21; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#dc4930;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#e6583e; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ef654d; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f7705b; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fd7e6b;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#fe8e7e; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fca294; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e9dabe; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c7e298;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#b1de7f; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a0d571; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#90cb68; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#82c162;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#75b65d; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#69aa56; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#5ea049; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#559633;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#4a8c1c; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	ClassicAreaRedGreen21("#bd1100", "#c82912", "#d23a21", "#dc4930", "#e6583e", "#ef654d", "#f7705b", "#fd7e6b", "#fe8e7e", "#fca294", "#e9dabe", "#c7e298", "#b1de7f", "#a0d571", "#90cb68", "#82c162", "#75b65d", "#69aa56", "#5ea049", "#559633",
			"#4a8c1c"),
	/**
	 * Color scheme name "ClassicOrangeBlue13" - <span style="background-color:#7b3014; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a33202; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d74401; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f06511;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#fd8938; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fdab67; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#cacaca; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#7bc8e2;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#67add4; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#3a87b7; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#1c73b1; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#1c5998;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#26456e; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	ClassicOrangeBlue13("#7b3014", "#a33202", "#d74401", "#f06511", "#fd8938", "#fdab67", "#cacaca", "#7bc8e2", "#67add4", "#3a87b7", "#1c73b1", "#1c5998", "#26456e"),
	/**
	 * Color scheme name "ClassicGreenBlue11" - <span style="background-color:#09622a; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#1e7735; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#2f8e41; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#69a761;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#a2c18f; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#cacaca; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#67add4; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#3a87b7;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#1c73b1; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#1c5998; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#26456e; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	ClassicGreenBlue11("#09622a", "#1e7735", "#2f8e41", "#69a761", "#a2c18f", "#cacaca", "#67add4", "#3a87b7", "#1c73b1", "#1c5998", "#26456e"),
	/**
	 * Color scheme name "ClassicRedWhiteGreen11" - <span style="background-color:#9c0824; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b41f27; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#cc312b; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e86753;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#fcb4a5; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#FFFFFF; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b9d7b7; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#74af72;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#428f49; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#297839; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#09622a; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	ClassicRedWhiteGreen11("#9c0824", "#b41f27", "#cc312b", "#e86753", "#fcb4a5", "#FFFFFF", "#b9d7b7", "#74af72", "#428f49", "#297839", "#09622a"),
	/**
	 * Color scheme name "ClassicRedWhiteBlack11" - <span style="background-color:#9c0824; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b41f27; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#cc312b; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e86753;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#fcb4a5; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#FFFFFF; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#bfbfbf; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#838383;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#575757; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#393939; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#1e1e1e; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	ClassicRedWhiteBlack11("#9c0824", "#b41f27", "#cc312b", "#e86753", "#fcb4a5", "#FFFFFF", "#bfbfbf", "#838383", "#575757", "#393939", "#1e1e1e"),
	/**
	 * Color scheme name "ClassicOrangeWhiteBlue11" - <span style="background-color:#7b3014; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a84415; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d85a13; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fb8547;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#ffc2a1; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#FFFFFF; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b7cde2; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#6a9ec5;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#3679a8; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#2e5f8a; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#26456e; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	ClassicOrangeWhiteBlue11("#7b3014", "#a84415", "#d85a13", "#fb8547", "#ffc2a1", "#FFFFFF", "#b7cde2", "#6a9ec5", "#3679a8", "#2e5f8a", "#26456e"),
	/**
	 * Color scheme name "ClassicRedWhiteBlackLight10" -
	 * <span style="background-color:#ffc2c5; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffd1d3; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffe0e1; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fff0f0;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#FFFFFF; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f3f3f3; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e8e8e8; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#dddddd;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#d1d1d1; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c6c6c6; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	ClassicRedWhiteBlackLight10("#ffc2c5", "#ffd1d3", "#ffe0e1", "#fff0f0", "#FFFFFF", "#f3f3f3", "#e8e8e8", "#dddddd", "#d1d1d1", "#c6c6c6"),
	/**
	 * Color scheme name "ClassicOrangeWhiteBlueLight11" -
	 * <span style="background-color:#ffcc9e; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffd6b1; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffe0c5; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffead8;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#fff5eb; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#FFFFFF; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f3f7fd; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e8effa;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#dce8f8; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d0e0f6; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c4d8f3; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	ClassicOrangeWhiteBlueLight11("#ffcc9e", "#ffd6b1", "#ffe0c5", "#ffead8", "#fff5eb", "#FFFFFF", "#f3f7fd", "#e8effa", "#dce8f8", "#d0e0f6", "#c4d8f3"),
	/**
	 * Color scheme name "ClassicRedWhiteGreenLight11" -
	 * <span style="background-color:#ffb2b6; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffc2c5; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffd1d3; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffe0e1;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#fff0f0; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#FFFFFF; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f1faed; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e3f5db;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#d5f0ca; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c6ebb8; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b7e6a7; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	ClassicRedWhiteGreenLight11("#ffb2b6", "#ffc2c5", "#ffd1d3", "#ffe0e1", "#fff0f0", "#FFFFFF", "#f1faed", "#e3f5db", "#d5f0ca", "#c6ebb8", "#b7e6a7"),
	/**
	 * Color scheme name "ClassicRedGreenLight11" - <span style="background-color:#ffb2b6; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fcbdc0; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f8c7c9; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f2d1d2;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#ecdbdc; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e5e5e5; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#dde6d9; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d4e6cc;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#cae6c0; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c1e6b4; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b7e6a7; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	ClassicRedGreenLight11("#ffb2b6", "#fcbdc0", "#f8c7c9", "#f2d1d2", "#ecdbdc", "#e5e5e5", "#dde6d9", "#d4e6cc", "#cae6c0", "#c1e6b4", "#b7e6a7");

	// Category name used to build the label to configure plugin.
	private static final String CATEGORY = "tableau";
	// list of colors for each scheme
	private final List<IsColor> colors = new LinkedList<>();

	/**
	 * Builds a scheme using argument as list of colors in HEX format.
	 * 
	 * @param hexColors list of colors in HEX format
	 */
	private Tableau(String... hexColors) {
		// scans all hex colors and creates ISCOLOR
		for (String color : hexColors) {
			// and adds them into the list
			colors.add(ColorBuilder.parse(color));
		}
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
