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

import java.util.Arrays;
import java.util.List;

import org.pepstock.charba.client.colors.Gradient;
import org.pepstock.charba.client.colors.GradientColor;
import org.pepstock.charba.client.colors.GwtMaterialColor;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.impl.plugins.ColorScheme;
import org.pepstock.charba.client.impl.plugins.ColorSchemes;

/**
 * Contains all scheme definitions to map the GWT material schemes.<br>
 * To configure {@link ColorSchemes#ID} plugin, where category is <b>"gwtmaterial"</b>.<br>
 * Every color scheme has a number at the end of its name, which indicates the number of that colors included in the scheme. If
 * the number of the datasets is larger than it, the same colors will appear repeatedly. A color is not modified if it is
 * specified by dataset options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum GwtMaterialScheme implements IsEnumeratedScheme
{
	/**
	 * Color scheme name "BLUE" - <span style="background-color:#e3f2fd; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#bbdefb; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#90caf9; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#64b5f6;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#42a5f5; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#2196f3; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#1e88e5; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#1976d2;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#1565c0; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#0d47a1; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	BLUE(GwtMaterialColor.BLUE_LIGHTEN_5, GwtMaterialColor.BLUE_LIGHTEN_4, GwtMaterialColor.BLUE_LIGHTEN_3, GwtMaterialColor.BLUE_LIGHTEN_2, GwtMaterialColor.BLUE_LIGHTEN_1, GwtMaterialColor.BLUE, GwtMaterialColor.BLUE_DARKEN_1,
			GwtMaterialColor.BLUE_DARKEN_2, GwtMaterialColor.BLUE_DARKEN_3, GwtMaterialColor.BLUE_DARKEN_4),
	/**
	 * Color scheme name "PURPLE" - <span style="background-color:#f3e5f5; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e1bee7; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ce93d8; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ba68c8;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#ab47bc; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#9c27b0; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#8e24aa; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#7b1fa2;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#6a1b9a; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#4a148c; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	PURPLE(GwtMaterialColor.PURPLE_LIGHTEN_5, GwtMaterialColor.PURPLE_LIGHTEN_4, GwtMaterialColor.PURPLE_LIGHTEN_3, GwtMaterialColor.PURPLE_LIGHTEN_2, GwtMaterialColor.PURPLE_LIGHTEN_1, GwtMaterialColor.PURPLE, GwtMaterialColor.PURPLE_DARKEN_1,
			GwtMaterialColor.PURPLE_DARKEN_2, GwtMaterialColor.PURPLE_DARKEN_3, GwtMaterialColor.PURPLE_DARKEN_4),
	/**
	 * Color scheme name "GREEN" - <span style="background-color:#e8f5e9; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c8e6c9; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a5d6a7; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#81c784;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#66bb6a; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#4caf50; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#43a047; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#388e3c;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#2e7d32; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#1b5e20; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	GREEN(GwtMaterialColor.GREEN_LIGHTEN_5, GwtMaterialColor.GREEN_LIGHTEN_4, GwtMaterialColor.GREEN_LIGHTEN_3, GwtMaterialColor.GREEN_LIGHTEN_2, GwtMaterialColor.GREEN_LIGHTEN_1, GwtMaterialColor.GREEN, GwtMaterialColor.GREEN_DARKEN_1,
			GwtMaterialColor.GREEN_DARKEN_2, GwtMaterialColor.GREEN_DARKEN_3, GwtMaterialColor.GREEN_DARKEN_4),
	/**
	 * Color scheme name "INDIGO" - <span style="background-color:#e8eaf6; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c5cae9; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#9fa8da; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#7986cb;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#5c6bc0; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#3f51b5; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#3949ab; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#303f9f;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#283593; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#1a237e; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	INDIGO(GwtMaterialColor.INDIGO_LIGHTEN_5, GwtMaterialColor.INDIGO_LIGHTEN_4, GwtMaterialColor.INDIGO_LIGHTEN_3, GwtMaterialColor.INDIGO_LIGHTEN_2, GwtMaterialColor.INDIGO_LIGHTEN_1, GwtMaterialColor.INDIGO, GwtMaterialColor.INDIGO_DARKEN_1,
			GwtMaterialColor.INDIGO_DARKEN_2, GwtMaterialColor.INDIGO_DARKEN_3, GwtMaterialColor.INDIGO_DARKEN_4),
	/**
	 * Color scheme name "RED" - <span style="background-color:#ffebee; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffcdd2; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ef9a9a; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e57373;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#ef5350; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f44336; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e53935; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d32f2f;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#c62828; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b71c1c; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	RED(GwtMaterialColor.RED_LIGHTEN_5, GwtMaterialColor.RED_LIGHTEN_4, GwtMaterialColor.RED_LIGHTEN_3, GwtMaterialColor.RED_LIGHTEN_2, GwtMaterialColor.RED_LIGHTEN_1, GwtMaterialColor.RED, GwtMaterialColor.RED_DARKEN_1, GwtMaterialColor.RED_DARKEN_2,
			GwtMaterialColor.RED_DARKEN_3, GwtMaterialColor.RED_DARKEN_4),
	/**
	 * Color scheme name "PINK" - <span style="background-color:#fce4ec; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f8bbd0; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f48fb1; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f06292;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#ec407a; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e91e63; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d81b60; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c2185b;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#ad1457; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#880e4f; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	PINK(GwtMaterialColor.PINK_LIGHTEN_5, GwtMaterialColor.PINK_LIGHTEN_4, GwtMaterialColor.PINK_LIGHTEN_3, GwtMaterialColor.PINK_LIGHTEN_2, GwtMaterialColor.PINK_LIGHTEN_1, GwtMaterialColor.PINK, GwtMaterialColor.PINK_DARKEN_1,
			GwtMaterialColor.PINK_DARKEN_2, GwtMaterialColor.PINK_DARKEN_3, GwtMaterialColor.PINK_DARKEN_4),
	/**
	 * Color scheme name "AMBER" - <span style="background-color:#fff8e1; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffecb3; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffe082; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffd54f;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#ffca28; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffc107; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffb300; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffa000;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#ff8f00; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ff6f00; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	AMBER(GwtMaterialColor.AMBER_LIGHTEN_5, GwtMaterialColor.AMBER_LIGHTEN_4, GwtMaterialColor.AMBER_LIGHTEN_3, GwtMaterialColor.AMBER_LIGHTEN_2, GwtMaterialColor.AMBER_LIGHTEN_1, GwtMaterialColor.AMBER, GwtMaterialColor.AMBER_DARKEN_1,
			GwtMaterialColor.AMBER_DARKEN_2, GwtMaterialColor.AMBER_DARKEN_3, GwtMaterialColor.AMBER_DARKEN_4),
	/**
	 * Color scheme name "LIME" - <span style="background-color:#f9fbe7; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f0f4c3; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e6ee9c; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#dce775;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#d4e157; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#cddc39; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c0ca33; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#afb42b;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#9e9d24; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#827717; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	LIME(GwtMaterialColor.LIME_LIGHTEN_5, GwtMaterialColor.LIME_LIGHTEN_4, GwtMaterialColor.LIME_LIGHTEN_3, GwtMaterialColor.LIME_LIGHTEN_2, GwtMaterialColor.LIME_LIGHTEN_1, GwtMaterialColor.LIME, GwtMaterialColor.LIME_DARKEN_1,
			GwtMaterialColor.LIME_DARKEN_2, GwtMaterialColor.LIME_DARKEN_3, GwtMaterialColor.LIME_DARKEN_4),
	/**
	 * Color scheme name "TEAL" - <span style="background-color:#e0f2f1; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b2dfdb; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#80cbc4; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#4db6ac;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#26a69a; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#009688; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#00897b; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#00796b;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#00695c; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#004d40; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	TEAL(GwtMaterialColor.TEAL_LIGHTEN_5, GwtMaterialColor.TEAL_LIGHTEN_4, GwtMaterialColor.TEAL_LIGHTEN_3, GwtMaterialColor.TEAL_LIGHTEN_2, GwtMaterialColor.TEAL_LIGHTEN_1, GwtMaterialColor.TEAL, GwtMaterialColor.TEAL_DARKEN_1,
			GwtMaterialColor.TEAL_DARKEN_2, GwtMaterialColor.TEAL_DARKEN_3, GwtMaterialColor.TEAL_DARKEN_4),
	/**
	 * Color scheme name "YELLOW" - <span style="background-color:#fffde7; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fff9c4; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fff59d; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fff176;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#ffee58; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffeb3b; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fdd835; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fbc02d;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#f9a825; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f57f17; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	YELLOW(GwtMaterialColor.YELLOW_LIGHTEN_5, GwtMaterialColor.YELLOW_LIGHTEN_4, GwtMaterialColor.YELLOW_LIGHTEN_3, GwtMaterialColor.YELLOW_LIGHTEN_2, GwtMaterialColor.YELLOW_LIGHTEN_1, GwtMaterialColor.YELLOW, GwtMaterialColor.YELLOW_DARKEN_1,
			GwtMaterialColor.YELLOW_DARKEN_2, GwtMaterialColor.YELLOW_DARKEN_3, GwtMaterialColor.YELLOW_DARKEN_4),
	/**
	 * Color scheme name "CYAN" - <span style="background-color:#e0f7fa; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b2ebf2; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#80deea; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#4dd0e1;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#26c6da; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#00bcd4; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#00acc1; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#0097a7;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#00838f; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#006064; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	CYAN(GwtMaterialColor.CYAN_LIGHTEN_5, GwtMaterialColor.CYAN_LIGHTEN_4, GwtMaterialColor.CYAN_LIGHTEN_3, GwtMaterialColor.CYAN_LIGHTEN_2, GwtMaterialColor.CYAN_LIGHTEN_1, GwtMaterialColor.CYAN, GwtMaterialColor.CYAN_DARKEN_1,
			GwtMaterialColor.CYAN_DARKEN_2, GwtMaterialColor.CYAN_DARKEN_3, GwtMaterialColor.CYAN_DARKEN_4),
	/**
	 * Color scheme name "BROWN" - <span style="background-color:#efebe9; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d7ccc8; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#bcaaa4; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a1887f;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#8d6e63; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#795548; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#6d4c41; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#5d4037;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#4e342e; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#3e2723; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	BROWN(GwtMaterialColor.BROWN_LIGHTEN_5, GwtMaterialColor.BROWN_LIGHTEN_4, GwtMaterialColor.BROWN_LIGHTEN_3, GwtMaterialColor.BROWN_LIGHTEN_2, GwtMaterialColor.BROWN_LIGHTEN_1, GwtMaterialColor.BROWN, GwtMaterialColor.BROWN_DARKEN_1,
			GwtMaterialColor.BROWN_DARKEN_2, GwtMaterialColor.BROWN_DARKEN_3, GwtMaterialColor.BROWN_DARKEN_4),
	/**
	 * Color scheme name "GREY" - <span style="background-color:#fafafa; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f5f5f5; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#eeeeee; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e0e0e0;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#bdbdbd; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#9e9e9e; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#757575; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#616161;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#424242; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#212121; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	GREY(GwtMaterialColor.GREY_LIGHTEN_5, GwtMaterialColor.GREY_LIGHTEN_4, GwtMaterialColor.GREY_LIGHTEN_3, GwtMaterialColor.GREY_LIGHTEN_2, GwtMaterialColor.GREY_LIGHTEN_1, GwtMaterialColor.GREY, GwtMaterialColor.GREY_DARKEN_1,
			GwtMaterialColor.GREY_DARKEN_2, GwtMaterialColor.GREY_DARKEN_3, GwtMaterialColor.GREY_DARKEN_4),
	/**
	 * Color scheme name "ORANGE" - <span style="background-color:#fff3e0; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffe0b2; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffcc80; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffb74d;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#ffa726; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ff9800; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fb8c00; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f57c00;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#ef6c00; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e65100; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	ORANGE(GwtMaterialColor.ORANGE_LIGHTEN_5, GwtMaterialColor.ORANGE_LIGHTEN_4, GwtMaterialColor.ORANGE_LIGHTEN_3, GwtMaterialColor.ORANGE_LIGHTEN_2, GwtMaterialColor.ORANGE_LIGHTEN_1, GwtMaterialColor.ORANGE, GwtMaterialColor.ORANGE_DARKEN_1,
			GwtMaterialColor.ORANGE_DARKEN_2, GwtMaterialColor.ORANGE_DARKEN_3, GwtMaterialColor.ORANGE_DARKEN_4);

	// Category name used to build the label to configure plugin.
	private static final String CATEGORY = "gwtmaterial";
	// enumerated scheme instance
	private final EnumeratedScheme scheme;

	/**
	 * Builds a scheme using argument as list of colors in HEX format.
	 * 
	 * @param hexColors list of colors in HEX format
	 */
	private GwtMaterialScheme(IsColor... gwtColors) {
		scheme = new EnumeratedScheme(CATEGORY, name());
		// adds them into the list
		scheme.addAll(Arrays.asList(gwtColors));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.impl.plugins.enums.IsEnumeratedScheme#getScheme()
	 */
	@Override
	public ColorScheme getScheme() {
		return scheme;
	}

	/**
	 * Loads a gradient with the scheme of colors, setting the offset proportionally.
	 * 
	 * @param gradient gradient instance to be loaded with colors
	 */
	public void loadGradient(Gradient gradient) {
		// gets the list of colors
		List<IsColor> colors = scheme.getColors();
		// checks if gradient is consistent
		// and if colors list is consistent, minimum 2 colors
		if (gradient != null && colors.size() > 1) {
			// gets index of last calculated color
			int lastCalculatedColorIndex = colors.size() - 1;
			// calculates the increment of offset, and then due to 0 and 1 are already assigned
			// the increment is 1 divided by size of list - 1
			double offsetIncrement = GradientColor.DEFAULT_OFFSET_STOP / lastCalculatedColorIndex;
			// amount of offset, starting from 0
			double offset = 0D;
			// scans colors from 1 to last calculated index
			for (int i = 0; i < lastCalculatedColorIndex; i++) {
				// color at index 0 is color at offset 0
				gradient.addColorStop(offset, colors.get(i));
				// increments offset
				offset += offsetIncrement;
			}
			// adds last color with offset 1
			gradient.addColorStop(GradientColor.DEFAULT_OFFSET_STOP, colors.get(lastCalculatedColorIndex));
		}
	}

}
