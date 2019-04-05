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
package org.pepstock.charba.client.colors;

/**
 * All modern browsers support the following 140 color names.
 * 
 * @author Andrea "Stock" Stocchero
 * 
 * @see <a href="https://www.w3schools.com/colors/colors_names.asp">HTML Color names</a>
 *
 */
public enum HtmlColor implements IsColor
{

	/**
	 * HTML color name "ALICE_BLUE" - <span style="background-color:#F0F8FF; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	ALICE_BLUE("#F0F8FF"),
	/**
	 * HTML color name "ANTIQUE_WHITE" - <span style="background-color:#FAEBD7; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	ANTIQUE_WHITE("#FAEBD7"),
	/**
	 * HTML color name "AQUA" - <span style="background-color:#00FFFF; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	AQUA("#00FFFF"),
	/**
	 * HTML color name "AQUAMARINE" - <span style="background-color:#7FFFD4; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	AQUAMARINE("#7FFFD4"),
	/**
	 * HTML color name "AZURE" - <span style="background-color:#F0FFFF; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	AZURE("#F0FFFF"),
	/**
	 * HTML color name "BEIGE" - <span style="background-color:#F5F5DC; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	BEIGE("#F5F5DC"),
	/**
	 * HTML color name "BISQUE" - <span style="background-color:#FFE4C4; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	BISQUE("#FFE4C4"),
	/**
	 * HTML color name "BLACK" - <span style="background-color:#000000; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	BLACK("#000000"),
	/**
	 * HTML color name "BLANCHED_ALMOND" - <span style="background-color:#FFEBCD; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	BLANCHED_ALMOND("#FFEBCD"),
	/**
	 * HTML color name "BLUE" - <span style="background-color:#0000FF; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	BLUE("#0000FF"),
	/**
	 * HTML color name "BLUE_VIOLET" - <span style="background-color:#8A2BE2; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	BLUE_VIOLET("#8A2BE2"),
	/**
	 * HTML color name "BROWN" - <span style="background-color:#A52A2A; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	BROWN("#A52A2A"),
	/**
	 * HTML color name "BURLY_WOOD" - <span style="background-color:#DEB887; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	BURLY_WOOD("#DEB887"),
	/**
	 * HTML color name "CADET_BLUE" - <span style="background-color:#5F9EA0; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	CADET_BLUE("#5F9EA0"),
	/**
	 * HTML color name "CHARTREUSE" - <span style="background-color:#7FFF00; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	CHARTREUSE("#7FFF00"),
	/**
	 * HTML color name "CHOCOLATE" - <span style="background-color:#D2691E; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	CHOCOLATE("#D2691E"),
	/**
	 * HTML color name "CORAL" - <span style="background-color:#FF7F50; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	CORAL("#FF7F50"),
	/**
	 * HTML color name "CORNFLOWER_BLUE" - <span style="background-color:#6495ED; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	CORNFLOWER_BLUE("#6495ED"),
	/**
	 * HTML color name "CORNSILK" - <span style="background-color:#FFF8DC; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	CORNSILK("#FFF8DC"),
	/**
	 * HTML color name "CRIMSON" - <span style="background-color:#DC143C; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	CRIMSON("#DC143C"),
	/**
	 * HTML color name "CYAN" - <span style="background-color:#00FFFF; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	CYAN("#00FFFF"),
	/**
	 * HTML color name "DARK_BLUE" - <span style="background-color:#00008B; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	DARK_BLUE("#00008B"),
	/**
	 * HTML color name "DARK_CYAN" - <span style="background-color:#008B8B; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	DARK_CYAN("#008B8B"),
	/**
	 * HTML color name "DARK_GOLDEN_ROD" - <span style="background-color:#B8860B; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	DARK_GOLDEN_ROD("#B8860B"),
	/**
	 * HTML color name "DARK_GRAY" - <span style="background-color:#A9A9A9; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	DARK_GRAY("#A9A9A9"),
	/**
	 * HTML color name "DARK_GREY" - <span style="background-color:#A9A9A9; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	DARK_GREY("#A9A9A9"),
	/**
	 * HTML color name "DARK_GREEN" - <span style="background-color:#006400; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	DARK_GREEN("#006400"),
	/**
	 * HTML color name "DARK_KHAKI" - <span style="background-color:#BDB76B; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	DARK_KHAKI("#BDB76B"),
	/**
	 * HTML color name "DARK_MAGENTA" - <span style="background-color:#8B008B; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	DARK_MAGENTA("#8B008B"),
	/**
	 * HTML color name "DARK_OLIVE_GREEN" - <span style="background-color:#556B2F; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	DARK_OLIVE_GREEN("#556B2F"),
	/**
	 * HTML color name "DARK_ORANGE" - <span style="background-color:#FF8C00; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	DARK_ORANGE("#FF8C00"),
	/**
	 * HTML color name "DARK_ORCHID" - <span style="background-color:#9932CC; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	DARK_ORCHID("#9932CC"),
	/**
	 * HTML color name "DARK_RED" - <span style="background-color:#8B0000; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	DARK_RED("#8B0000"),
	/**
	 * HTML color name "DARK_SALMON" - <span style="background-color:#E9967A; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	DARK_SALMON("#E9967A"),
	/**
	 * HTML color name "DARK_SEA_GREEN" - <span style="background-color:#8FBC8F; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	DARK_SEA_GREEN("#8FBC8F"),
	/**
	 * HTML color name "DARK_SLATE_BLUE" - <span style="background-color:#483D8B; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	DARK_SLATE_BLUE("#483D8B"),
	/**
	 * HTML color name "DARK_SLATE_GRAY" - <span style="background-color:#2F4F4F; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	DARK_SLATE_GRAY("#2F4F4F"),
	/**
	 * HTML color name "DARK_SLATE_GREY" - <span style="background-color:#2F4F4F; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	DARK_SLATE_GREY("#2F4F4F"),
	/**
	 * HTML color name "DARK_TURQUOISE" - <span style="background-color:#00CED1; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	DARK_TURQUOISE("#00CED1"),
	/**
	 * HTML color name "DARK_VIOLET" - <span style="background-color:#9400D3; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	DARK_VIOLET("#9400D3"),
	/**
	 * HTML color name "DEEP_PINK" - <span style="background-color:#FF1493; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	DEEP_PINK("#FF1493"),
	/**
	 * HTML color name "DEEP_SKY_BLUE" - <span style="background-color:#00BFFF; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	DEEP_SKY_BLUE("#00BFFF"),
	/**
	 * HTML color name "DIM_GRAY" - <span style="background-color:#696969; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	DIM_GRAY("#696969"),
	/**
	 * HTML color name "DIM_GREY" - <span style="background-color:#696969; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	DIM_GREY("#696969"),
	/**
	 * HTML color name "DODGER_BLUE" - <span style="background-color:#1E90FF; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	DODGER_BLUE("#1E90FF"),
	/**
	 * HTML color name "FIRE_BRICK" - <span style="background-color:#B22222; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	FIRE_BRICK("#B22222"),
	/**
	 * HTML color name "FLORAL_WHITE" - <span style="background-color:#FFFAF0; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	FLORAL_WHITE("#FFFAF0"),
	/**
	 * HTML color name "FOREST_GREEN" - <span style="background-color:#228B22; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	FOREST_GREEN("#228B22"),
	/**
	 * HTML color name "FUCHSIA" - <span style="background-color:#FF00FF; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	FUCHSIA("#FF00FF"),
	/**
	 * HTML color name "GAINSBORO" - <span style="background-color:#DCDCDC; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	GAINSBORO("#DCDCDC"),
	/**
	 * HTML color name "GHOST_WHITE" - <span style="background-color:#F8F8FF; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	GHOST_WHITE("#F8F8FF"),
	/**
	 * HTML color name "GOLD" - <span style="background-color:#FFD700; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	GOLD("#FFD700"),
	/**
	 * HTML color name "GOLDEN_ROD" - <span style="background-color:#DAA520; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	GOLDEN_ROD("#DAA520"),
	/**
	 * HTML color name "GRAY" - <span style="background-color:#808080; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	GRAY("#808080"),
	/**
	 * HTML color name "GREY" - <span style="background-color:#808080; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	GREY("#808080"),
	/**
	 * HTML color name "GREEN" - <span style="background-color:#008000; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	GREEN("#008000"),
	/**
	 * HTML color name "GREEN_YELLOW" - <span style="background-color:#ADFF2F; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	GREEN_YELLOW("#ADFF2F"),
	/**
	 * HTML color name "HONEY_DEW" - <span style="background-color:#F0FFF0; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	HONEY_DEW("#F0FFF0"),
	/**
	 * HTML color name "HOT_PINK" - <span style="background-color:#FF69B4; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	HOT_PINK("#FF69B4"),
	/**
	 * HTML color name "INDIAN_RED" - <span style="background-color:#CD5C5C; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	INDIAN_RED("#CD5C5C"),
	/**
	 * HTML color name "INDIGO" - <span style="background-color:#4B0082; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	INDIGO("#4B0082"),
	/**
	 * HTML color name "IVORY" - <span style="background-color:#FFFFF0; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	IVORY("#FFFFF0"),
	/**
	 * HTML color name "KHAKI" - <span style="background-color:#F0E68C; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	KHAKI("#F0E68C"),
	/**
	 * HTML color name "LAVENDER" - <span style="background-color:#E6E6FA; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	LAVENDER("#E6E6FA"),
	/**
	 * HTML color name "LAVENDER_BLUSH" - <span style="background-color:#FFF0F5; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	LAVENDER_BLUSH("#FFF0F5"),
	/**
	 * HTML color name "LAWN_GREEN" - <span style="background-color:#7CFC00; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	LAWN_GREEN("#7CFC00"),
	/**
	 * HTML color name "LEMON_CHIFFON" - <span style="background-color:#FFFACD; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	LEMON_CHIFFON("#FFFACD"),
	/**
	 * HTML color name "LIGHT_BLUE" - <span style="background-color:#ADD8E6; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	LIGHT_BLUE("#ADD8E6"),
	/**
	 * HTML color name "LIGHT_CORAL" - <span style="background-color:#F08080; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	LIGHT_CORAL("#F08080"),
	/**
	 * HTML color name "LIGHT_CYAN" - <span style="background-color:#E0FFFF; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	LIGHT_CYAN("#E0FFFF"),
	/**
	 * HTML color name "LIGHT_GOLDEN_ROD_YELLOW" - <span style="background-color:#FAFAD2; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	LIGHT_GOLDEN_ROD_YELLOW("#FAFAD2"),
	/**
	 * HTML color name "LIGHT_GRAY" - <span style="background-color:#D3D3D3; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	LIGHT_GRAY("#D3D3D3"),
	/**
	 * HTML color name "LIGHT_GREY" - <span style="background-color:#D3D3D3; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	LIGHT_GREY("#D3D3D3"),
	/**
	 * HTML color name "LIGHT_GREEN" - <span style="background-color:#90EE90; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	LIGHT_GREEN("#90EE90"),
	/**
	 * HTML color name "LIGHT_PINK" - <span style="background-color:#FFB6C1; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	LIGHT_PINK("#FFB6C1"),
	/**
	 * HTML color name "LIGHT_SALMON" - <span style="background-color:#FFA07A; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	LIGHT_SALMON("#FFA07A"),
	/**
	 * HTML color name "LIGHT_SEA_GREEN" - <span style="background-color:#20B2AA; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	LIGHT_SEA_GREEN("#20B2AA"),
	/**
	 * HTML color name "LIGHT_SKY_BLUE" - <span style="background-color:#87CEFA; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	LIGHT_SKY_BLUE("#87CEFA"),
	/**
	 * HTML color name "LIGHT_SLATE_GRAY" - <span style="background-color:#778899; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	LIGHT_SLATE_GRAY("#778899"),
	/**
	 * HTML color name "LIGHT_SLATE_GREY" - <span style="background-color:#778899; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	LIGHT_SLATE_GREY("#778899"),
	/**
	 * HTML color name "LIGHT_STEEL_BLUE" - <span style="background-color:#B0C4DE; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	LIGHT_STEEL_BLUE("#B0C4DE"),
	/**
	 * HTML color name "LIGHT_YELLOW" - <span style="background-color:#FFFFE0; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	LIGHT_YELLOW("#FFFFE0"),
	/**
	 * HTML color name "LIME" - <span style="background-color:#00FF00; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	LIME("#00FF00"),
	/**
	 * HTML color name "LIME_GREEN" - <span style="background-color:#32CD32; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	LIME_GREEN("#32CD32"),
	/**
	 * HTML color name "LINEN" - <span style="background-color:#FAF0E6; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	LINEN("#FAF0E6"),
	/**
	 * HTML color name "MAGENTA" - <span style="background-color:#FF00FF; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	MAGENTA("#FF00FF"),
	/**
	 * HTML color name "MAROON" - <span style="background-color:#800000; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	MAROON("#800000"),
	/**
	 * HTML color name "MEDIUM_AQUA_MARINE" - <span style="background-color:#66CDAA; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	MEDIUM_AQUA_MARINE("#66CDAA"),
	/**
	 * HTML color name "MEDIUM_BLUE" - <span style="background-color:#0000CD; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	MEDIUM_BLUE("#0000CD"),
	/**
	 * HTML color name "MEDIUM_ORCHID" - <span style="background-color:#BA55D3; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	MEDIUM_ORCHID("#BA55D3"),
	/**
	 * HTML color name "MEDIUM_PURPLE" - <span style="background-color:#9370DB; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	MEDIUM_PURPLE("#9370DB"),
	/**
	 * HTML color name "MEDIUM_SEA_GREEN" - <span style="background-color:#3CB371; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	MEDIUM_SEA_GREEN("#3CB371"),
	/**
	 * HTML color name "MEDIUM_SLATE_BLUE" - <span style="background-color:#7B68EE; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	MEDIUM_SLATE_BLUE("#7B68EE"),
	/**
	 * HTML color name "MEDIUM_SPRING_GREEN" - <span style="background-color:#00FA9A; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	MEDIUM_SPRING_GREEN("#00FA9A"),
	/**
	 * HTML color name "MEDIUM_TURQUOISE" - <span style="background-color:#48D1CC; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	MEDIUM_TURQUOISE("#48D1CC"),
	/**
	 * HTML color name "MEDIUM_VIOLET_RED" - <span style="background-color:#C71585; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	MEDIUM_VIOLET_RED("#C71585"),
	/**
	 * HTML color name "MIDNIGHT_BLUE" - <span style="background-color:#191970; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	MIDNIGHT_BLUE("#191970"),
	/**
	 * HTML color name "MINT_CREAM" - <span style="background-color:#F5FFFA; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	MINT_CREAM("#F5FFFA"),
	/**
	 * HTML color name "MISTY_ROSE" - <span style="background-color:#FFE4E1; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	MISTY_ROSE("#FFE4E1"),
	/**
	 * HTML color name "MOCCASIN" - <span style="background-color:#FFE4B5; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	MOCCASIN("#FFE4B5"),
	/**
	 * HTML color name "NAVAJO_WHITE" - <span style="background-color:#FFDEAD; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	NAVAJO_WHITE("#FFDEAD"),
	/**
	 * HTML color name "NAVY" - <span style="background-color:#000080; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	NAVY("#000080"),
	/**
	 * HTML color name "OLD_LACE" - <span style="background-color:#FDF5E6; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	OLD_LACE("#FDF5E6"),
	/**
	 * HTML color name "OLIVE" - <span style="background-color:#808000; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	OLIVE("#808000"),
	/**
	 * HTML color name "OLIVE_DRAB" - <span style="background-color:#6B8E23; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	OLIVE_DRAB("#6B8E23"),
	/**
	 * HTML color name "ORANGE" - <span style="background-color:#FFA500; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	ORANGE("#FFA500"),
	/**
	 * HTML color name "ORANGE_RED" - <span style="background-color:#FF4500; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	ORANGE_RED("#FF4500"),
	/**
	 * HTML color name "ORCHID" - <span style="background-color:#DA70D6; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	ORCHID("#DA70D6"),
	/**
	 * HTML color name "PALE_GOLDEN_ROD" - <span style="background-color:#EEE8AA; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	PALE_GOLDEN_ROD("#EEE8AA"),
	/**
	 * HTML color name "PALE_GREEN" - <span style="background-color:#98FB98; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	PALE_GREEN("#98FB98"),
	/**
	 * HTML color name "PALE_TURQUOISE" - <span style="background-color:#AFEEEE; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	PALE_TURQUOISE("#AFEEEE"),
	/**
	 * HTML color name "PALE_VIOLET_RED" - <span style="background-color:#DB7093; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	PALE_VIOLET_RED("#DB7093"),
	/**
	 * HTML color name "PAPAYA_WHIP" - <span style="background-color:#FFEFD5; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	PAPAYA_WHIP("#FFEFD5"),
	/**
	 * HTML color name "PEACH_PUFF" - <span style="background-color:#FFDAB9; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	PEACH_PUFF("#FFDAB9"),
	/**
	 * HTML color name "PERU" - <span style="background-color:#CD853F; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	PERU("#CD853F"),
	/**
	 * HTML color name "PINK" - <span style="background-color:#FFC0CB; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	PINK("#FFC0CB"),
	/**
	 * HTML color name "PLUM" - <span style="background-color:#DDA0DD; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	PLUM("#DDA0DD"),
	/**
	 * HTML color name "POWDER_BLUE" - <span style="background-color:#B0E0E6; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	POWDER_BLUE("#B0E0E6"),
	/**
	 * HTML color name "PURPLE" - <span style="background-color:#800080; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	PURPLE("#800080"),
	/**
	 * HTML color name "REBECCA_PURPLE" - <span style="background-color:#663399; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	REBECCA_PURPLE("#663399"),
	/**
	 * HTML color name "RED" - <span style="background-color:#FF0000; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	RED("#FF0000"),
	/**
	 * HTML color name "ROSY_BROWN" - <span style="background-color:#BC8F8F; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	ROSY_BROWN("#BC8F8F"),
	/**
	 * HTML color name "ROYAL_BLUE" - <span style="background-color:#4169E1; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	ROYAL_BLUE("#4169E1"),
	/**
	 * HTML color name "SADDLE_BROWN" - <span style="background-color:#8B4513; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	SADDLE_BROWN("#8B4513"),
	/**
	 * HTML color name "SALMON" - <span style="background-color:#FA8072; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	SALMON("#FA8072"),
	/**
	 * HTML color name "SANDY_BROWN" - <span style="background-color:#F4A460; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	SANDY_BROWN("#F4A460"),
	/**
	 * HTML color name "SEA_GREEN" - <span style="background-color:#2E8B57; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	SEA_GREEN("#2E8B57"),
	/**
	 * HTML color name "SEA_SHELL" - <span style="background-color:#FFF5EE; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	SEA_SHELL("#FFF5EE"),
	/**
	 * HTML color name "SIENNA" - <span style="background-color:#A0522D; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	SIENNA("#A0522D"),
	/**
	 * HTML color name "SILVER" - <span style="background-color:#C0C0C0; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	SILVER("#C0C0C0"),
	/**
	 * HTML color name "SKY_BLUE" - <span style="background-color:#87CEEB; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	SKY_BLUE("#87CEEB"),
	/**
	 * HTML color name "SLATE_BLUE" - <span style="background-color:#6A5ACD; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	SLATE_BLUE("#6A5ACD"),
	/**
	 * HTML color name "SLATE_GRAY" - <span style="background-color:#708090; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	SLATE_GRAY("#708090"),
	/**
	 * HTML color name "SLATE_GREY" - <span style="background-color:#708090; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	SLATE_GREY("#708090"),
	/**
	 * HTML color name "SNOW" - <span style="background-color:#FFFAFA; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	SNOW("#FFFAFA"),
	/**
	 * HTML color name "SPRING_GREEN" - <span style="background-color:#00FF7F; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	SPRING_GREEN("#00FF7F"),
	/**
	 * HTML color name "STEEL_BLUE" - <span style="background-color:#4682B4; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	STEEL_BLUE("#4682B4"),
	/**
	 * HTML color name "TAN" - <span style="background-color:#D2B48C; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	TAN("#D2B48C"),
	/**
	 * HTML color name "TEAL" - <span style="background-color:#008080; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	TEAL("#008080"),
	/**
	 * HTML color name "THISTLE" - <span style="background-color:#D8BFD8; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	THISTLE("#D8BFD8"),
	/**
	 * HTML color name "TOMATO" - <span style="background-color:#FF6347; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	TOMATO("#FF6347"),
	/**
	 * HTML color name "TURQUOISE" - <span style="background-color:#40E0D0; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	TURQUOISE("#40E0D0"),
	/**
	 * HTML color name "VIOLET" - <span style="background-color:#EE82EE; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	VIOLET("#EE82EE"),
	/**
	 * HTML color name "WHEAT" - <span style="background-color:#F5DEB3; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	WHEAT("#F5DEB3"),
	/**
	 * HTML color name "WHITE" - <span style="background-color:#FFFFFF; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	WHITE("#FFFFFF"),
	/**
	 * HTML color name "WHITE_SMOKE" - <span style="background-color:#F5F5F5; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	WHITE_SMOKE("#F5F5F5"),
	/**
	 * HTML color name "YELLOW" - <span style="background-color:#FFFF00; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	YELLOW("#FFFF00"),
	/**
	 * HTML color name "YELLOW_GREEN" - <span style="background-color:#9ACD32; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	YELLOW_GREEN("#9ACD32");

	// color string representation in hex mode
	private final String hexValue;
	// color instance
	private final IsColor color;

	/**
	 * Creates a color with HEX value.
	 * 
	 * @param hexValue color string representation in hex mode
	 */
	private HtmlColor(String hexValue) {
		this.hexValue = hexValue;
		// checks if the HEX value
		String newHexvalue = hexValue.substring(1);
		// reads colors for RED, GREEN and BLUE
		String redValue = newHexvalue.substring(0, 2);
		int red = Integer.parseInt(redValue, 16);
		String greenValue = newHexvalue.substring(2, 4);
		int green = Integer.parseInt(greenValue, 16);
		String blueValue = newHexvalue.substring(4);
		int blue = Integer.parseInt(blueValue, 16);
		// by default, using HEX format, transparency is 1
		color = new Color(red, green, blue, Color.DEFAULT_ALPHA);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.utils.IsColor#getRed()
	 */
	@Override
	public int getRed() {
		return color.getRed();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.utils.IsColor#getGreen()
	 */
	@Override
	public int getGreen() {
		return color.getGreen();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.utils.IsColor#getBlue()
	 */
	@Override
	public int getBlue() {
		return color.getBlue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.utils.IsColor#getAlpha()
	 */
	@Override
	public double getAlpha() {
		return color.getAlpha();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.utils.IsColor#alpha(double)
	 */
	@Override
	public IsColor alpha(double alpha) {
		return color.alpha(alpha);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.utils.IsColor#toRGBA()
	 */
	@Override
	public String toRGBA() {
		return color.toRGBA();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.utils.IsColor#toRGB()
	 */
	@Override
	public String toRGB() {
		return color.toRGB();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.colors.IsColor#toHSLA()
	 */
	@Override
	public String toHSLA() {
		return color.toHSLA();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.colors.IsColor#toHSL()
	 */
	@Override
	public String toHSL() {
		return color.toHSL();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.utils.IsColor#toHex()
	 */
	@Override
	public String toHex() {
		return hexValue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.colors.IsColor#toRGBs()
	 */
	@Override
	public int toRGBs() {
		return color.toRGBs();
	}

}
