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
 * Contains all scheme definitions to map the <a href="http://colorbrewer2.org/">Color Brewer</a> schemes.<br>
 * To configure {@link ColorSchemes#ID} plugin, where category is <b>"brewer"</b>.<br>
 * Every color scheme has a number at the end of its name, which indicates the number of that colors included in the scheme. If
 * the number of the datasets is larger than it, the same colors will appear repeatedly. A color is not modified if it is
 * specified by dataset options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum BrewerScheme implements ColorScheme
{
	/**
	 * Color scheme name "YlGn3" - <span style="background-color:#f7fcb9; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#addd8e; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#31a354; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	YlGn3("#f7fcb9", "#addd8e", "#31a354"),
	/**
	 * Color scheme name "YlGn4" - <span style="background-color:#ffffcc; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c2e699; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#78c679; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#238443;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	YlGn4("#ffffcc", "#c2e699", "#78c679", "#238443"),
	/**
	 * Color scheme name "YlGn5" - <span style="background-color:#ffffcc; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c2e699; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#78c679; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#31a354;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#006837; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	YlGn5("#ffffcc", "#c2e699", "#78c679", "#31a354", "#006837"),
	/**
	 * Color scheme name "YlGn6" - <span style="background-color:#ffffcc; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d9f0a3; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#addd8e; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#78c679;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#31a354; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#006837; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	YlGn6("#ffffcc", "#d9f0a3", "#addd8e", "#78c679", "#31a354", "#006837"),
	/**
	 * Color scheme name "YlGn7" - <span style="background-color:#ffffcc; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d9f0a3; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#addd8e; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#78c679;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#41ab5d; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#238443; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#005a32; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	YlGn7("#ffffcc", "#d9f0a3", "#addd8e", "#78c679", "#41ab5d", "#238443", "#005a32"),
	/**
	 * Color scheme name "YlGn8" - <span style="background-color:#ffffe5; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f7fcb9; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d9f0a3; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#addd8e;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#78c679; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#41ab5d; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#238443; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#005a32;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	YlGn8("#ffffe5", "#f7fcb9", "#d9f0a3", "#addd8e", "#78c679", "#41ab5d", "#238443", "#005a32"),
	/**
	 * Color scheme name "YlGn9" - <span style="background-color:#ffffe5; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f7fcb9; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d9f0a3; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#addd8e;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#78c679; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#41ab5d; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#238443; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#006837;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#004529; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	YlGn9("#ffffe5", "#f7fcb9", "#d9f0a3", "#addd8e", "#78c679", "#41ab5d", "#238443", "#006837", "#004529"),
	/**
	 * Color scheme name "YlGnBu3" - <span style="background-color:#edf8b1; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#7fcdbb; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#2c7fb8; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	YlGnBu3("#edf8b1", "#7fcdbb", "#2c7fb8"),
	/**
	 * Color scheme name "YlGnBu4" - <span style="background-color:#ffffcc; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a1dab4; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#41b6c4; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#225ea8;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	YlGnBu4("#ffffcc", "#a1dab4", "#41b6c4", "#225ea8"),
	/**
	 * Color scheme name "YlGnBu5" - <span style="background-color:#ffffcc; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a1dab4; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#41b6c4; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#2c7fb8;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#253494; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	YlGnBu5("#ffffcc", "#a1dab4", "#41b6c4", "#2c7fb8", "#253494"),
	/**
	 * Color scheme name "YlGnBu6" - <span style="background-color:#ffffcc; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c7e9b4; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#7fcdbb; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#41b6c4;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#2c7fb8; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#253494; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	YlGnBu6("#ffffcc", "#c7e9b4", "#7fcdbb", "#41b6c4", "#2c7fb8", "#253494"),
	/**
	 * Color scheme name "YlGnBu7" - <span style="background-color:#ffffcc; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c7e9b4; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#7fcdbb; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#41b6c4;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#1d91c0; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#225ea8; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#0c2c84; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	YlGnBu7("#ffffcc", "#c7e9b4", "#7fcdbb", "#41b6c4", "#1d91c0", "#225ea8", "#0c2c84"),
	/**
	 * Color scheme name "YlGnBu8" - <span style="background-color:#ffffd9; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#edf8b1; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c7e9b4; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#7fcdbb;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#41b6c4; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#1d91c0; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#225ea8; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#0c2c84;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	YlGnBu8("#ffffd9", "#edf8b1", "#c7e9b4", "#7fcdbb", "#41b6c4", "#1d91c0", "#225ea8", "#0c2c84"),
	/**
	 * Color scheme name "YlGnBu9" - <span style="background-color:#ffffd9; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#edf8b1; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c7e9b4; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#7fcdbb;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#41b6c4; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#1d91c0; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#225ea8; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#253494;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#081d58; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	YlGnBu9("#ffffd9", "#edf8b1", "#c7e9b4", "#7fcdbb", "#41b6c4", "#1d91c0", "#225ea8", "#253494", "#081d58"),
	/**
	 * Color scheme name "GnBu3" - <span style="background-color:#e0f3db; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a8ddb5; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#43a2ca; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	GnBu3("#e0f3db", "#a8ddb5", "#43a2ca"),
	/**
	 * Color scheme name "GnBu4" - <span style="background-color:#f0f9e8; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#bae4bc; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#7bccc4; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#2b8cbe;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	GnBu4("#f0f9e8", "#bae4bc", "#7bccc4", "#2b8cbe"),
	/**
	 * Color scheme name "GnBu5" - <span style="background-color:#f0f9e8; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#bae4bc; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#7bccc4; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#43a2ca;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#0868ac; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	GnBu5("#f0f9e8", "#bae4bc", "#7bccc4", "#43a2ca", "#0868ac"),
	/**
	 * Color scheme name "GnBu6" - <span style="background-color:#f0f9e8; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ccebc5; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a8ddb5; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#7bccc4;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#43a2ca; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#0868ac; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	GnBu6("#f0f9e8", "#ccebc5", "#a8ddb5", "#7bccc4", "#43a2ca", "#0868ac"),
	/**
	 * Color scheme name "GnBu7" - <span style="background-color:#f0f9e8; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ccebc5; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a8ddb5; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#7bccc4;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#4eb3d3; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#2b8cbe; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#08589e; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	GnBu7("#f0f9e8", "#ccebc5", "#a8ddb5", "#7bccc4", "#4eb3d3", "#2b8cbe", "#08589e"),
	/**
	 * Color scheme name "GnBu8" - <span style="background-color:#f7fcf0; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e0f3db; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ccebc5; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a8ddb5;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#7bccc4; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#4eb3d3; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#2b8cbe; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#08589e;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	GnBu8("#f7fcf0", "#e0f3db", "#ccebc5", "#a8ddb5", "#7bccc4", "#4eb3d3", "#2b8cbe", "#08589e"),
	/**
	 * Color scheme name "GnBu9" - <span style="background-color:#f7fcf0; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e0f3db; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ccebc5; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a8ddb5;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#7bccc4; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#4eb3d3; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#2b8cbe; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#0868ac;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#084081; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	GnBu9("#f7fcf0", "#e0f3db", "#ccebc5", "#a8ddb5", "#7bccc4", "#4eb3d3", "#2b8cbe", "#0868ac", "#084081"),
	/**
	 * Color scheme name "BuGn3" - <span style="background-color:#e5f5f9; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#99d8c9; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#2ca25f; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	BuGn3("#e5f5f9", "#99d8c9", "#2ca25f"),
	/**
	 * Color scheme name "BuGn4" - <span style="background-color:#edf8fb; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b2e2e2; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#66c2a4; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#238b45;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	BuGn4("#edf8fb", "#b2e2e2", "#66c2a4", "#238b45"),
	/**
	 * Color scheme name "BuGn5" - <span style="background-color:#edf8fb; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b2e2e2; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#66c2a4; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#2ca25f;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#006d2c; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	BuGn5("#edf8fb", "#b2e2e2", "#66c2a4", "#2ca25f", "#006d2c"),
	/**
	 * Color scheme name "BuGn6" - <span style="background-color:#edf8fb; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ccece6; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#99d8c9; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#66c2a4;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#2ca25f; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#006d2c; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	BuGn6("#edf8fb", "#ccece6", "#99d8c9", "#66c2a4", "#2ca25f", "#006d2c"),
	/**
	 * Color scheme name "BuGn7" - <span style="background-color:#edf8fb; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ccece6; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#99d8c9; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#66c2a4;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#41ae76; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#238b45; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#005824; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	BuGn7("#edf8fb", "#ccece6", "#99d8c9", "#66c2a4", "#41ae76", "#238b45", "#005824"),
	/**
	 * Color scheme name "BuGn8" - <span style="background-color:#f7fcfd; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e5f5f9; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ccece6; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#99d8c9;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#66c2a4; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#41ae76; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#238b45; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#005824;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	BuGn8("#f7fcfd", "#e5f5f9", "#ccece6", "#99d8c9", "#66c2a4", "#41ae76", "#238b45", "#005824"),
	/**
	 * Color scheme name "BuGn9" - <span style="background-color:#f7fcfd; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e5f5f9; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ccece6; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#99d8c9;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#66c2a4; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#41ae76; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#238b45; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#006d2c;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#00441b; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	BuGn9("#f7fcfd", "#e5f5f9", "#ccece6", "#99d8c9", "#66c2a4", "#41ae76", "#238b45", "#006d2c", "#00441b"),
	/**
	 * Color scheme name "PuBuGn3" - <span style="background-color:#ece2f0; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a6bddb; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#1c9099; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	PuBuGn3("#ece2f0", "#a6bddb", "#1c9099"),
	/**
	 * Color scheme name "PuBuGn4" - <span style="background-color:#f6eff7; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#bdc9e1; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#67a9cf; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#02818a;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	PuBuGn4("#f6eff7", "#bdc9e1", "#67a9cf", "#02818a"),
	/**
	 * Color scheme name "PuBuGn5" - <span style="background-color:#f6eff7; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#bdc9e1; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#67a9cf; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#1c9099;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#016c59; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	PuBuGn5("#f6eff7", "#bdc9e1", "#67a9cf", "#1c9099", "#016c59"),
	/**
	 * Color scheme name "PuBuGn6" - <span style="background-color:#f6eff7; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d0d1e6; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a6bddb; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#67a9cf;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#1c9099; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#016c59; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	PuBuGn6("#f6eff7", "#d0d1e6", "#a6bddb", "#67a9cf", "#1c9099", "#016c59"),
	/**
	 * Color scheme name "PuBuGn7" - <span style="background-color:#f6eff7; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d0d1e6; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a6bddb; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#67a9cf;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#3690c0; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#02818a; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#016450; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	PuBuGn7("#f6eff7", "#d0d1e6", "#a6bddb", "#67a9cf", "#3690c0", "#02818a", "#016450"),
	/**
	 * Color scheme name "PuBuGn8" - <span style="background-color:#fff7fb; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ece2f0; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d0d1e6; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a6bddb;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#67a9cf; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#3690c0; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#02818a; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#016450;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	PuBuGn8("#fff7fb", "#ece2f0", "#d0d1e6", "#a6bddb", "#67a9cf", "#3690c0", "#02818a", "#016450"),
	/**
	 * Color scheme name "PuBuGn9" - <span style="background-color:#fff7fb; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ece2f0; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d0d1e6; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a6bddb;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#67a9cf; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#3690c0; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#02818a; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#016c59;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#014636; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	PuBuGn9("#fff7fb", "#ece2f0", "#d0d1e6", "#a6bddb", "#67a9cf", "#3690c0", "#02818a", "#016c59", "#014636"),
	/**
	 * Color scheme name "PuBu3" - <span style="background-color:#ece7f2; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a6bddb; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#2b8cbe; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	PuBu3("#ece7f2", "#a6bddb", "#2b8cbe"),
	/**
	 * Color scheme name "PuBu4" - <span style="background-color:#f1eef6; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#bdc9e1; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#74a9cf; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#0570b0;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	PuBu4("#f1eef6", "#bdc9e1", "#74a9cf", "#0570b0"),
	/**
	 * Color scheme name "PuBu5" - <span style="background-color:#f1eef6; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#bdc9e1; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#74a9cf; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#2b8cbe;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#045a8d; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	PuBu5("#f1eef6", "#bdc9e1", "#74a9cf", "#2b8cbe", "#045a8d"),
	/**
	 * Color scheme name "PuBu6" - <span style="background-color:#f1eef6; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d0d1e6; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a6bddb; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#74a9cf;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#2b8cbe; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#045a8d; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	PuBu6("#f1eef6", "#d0d1e6", "#a6bddb", "#74a9cf", "#2b8cbe", "#045a8d"),
	/**
	 * Color scheme name "PuBu7" - <span style="background-color:#f1eef6; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d0d1e6; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a6bddb; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#74a9cf;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#3690c0; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#0570b0; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#034e7b; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	PuBu7("#f1eef6", "#d0d1e6", "#a6bddb", "#74a9cf", "#3690c0", "#0570b0", "#034e7b"),
	/**
	 * Color scheme name "PuBu8" - <span style="background-color:#fff7fb; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ece7f2; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d0d1e6; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a6bddb;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#74a9cf; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#3690c0; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#0570b0; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#034e7b;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	PuBu8("#fff7fb", "#ece7f2", "#d0d1e6", "#a6bddb", "#74a9cf", "#3690c0", "#0570b0", "#034e7b"),
	/**
	 * Color scheme name "PuBu9" - <span style="background-color:#fff7fb; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ece7f2; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d0d1e6; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a6bddb;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#74a9cf; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#3690c0; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#0570b0; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#045a8d;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#023858; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	PuBu9("#fff7fb", "#ece7f2", "#d0d1e6", "#a6bddb", "#74a9cf", "#3690c0", "#0570b0", "#045a8d", "#023858"),
	/**
	 * Color scheme name "BuPu3" - <span style="background-color:#e0ecf4; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#9ebcda; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#8856a7; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	BuPu3("#e0ecf4", "#9ebcda", "#8856a7"),
	/**
	 * Color scheme name "BuPu4" - <span style="background-color:#edf8fb; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b3cde3; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#8c96c6; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#88419d;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	BuPu4("#edf8fb", "#b3cde3", "#8c96c6", "#88419d"),
	/**
	 * Color scheme name "BuPu5" - <span style="background-color:#edf8fb; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b3cde3; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#8c96c6; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#8856a7;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#810f7c; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	BuPu5("#edf8fb", "#b3cde3", "#8c96c6", "#8856a7", "#810f7c"),
	/**
	 * Color scheme name "BuPu6" - <span style="background-color:#edf8fb; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#bfd3e6; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#9ebcda; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#8c96c6;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#8856a7; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#810f7c; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	BuPu6("#edf8fb", "#bfd3e6", "#9ebcda", "#8c96c6", "#8856a7", "#810f7c"),
	/**
	 * Color scheme name "BuPu7" - <span style="background-color:#edf8fb; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#bfd3e6; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#9ebcda; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#8c96c6;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#8c6bb1; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#88419d; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#6e016b; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	BuPu7("#edf8fb", "#bfd3e6", "#9ebcda", "#8c96c6", "#8c6bb1", "#88419d", "#6e016b"),
	/**
	 * Color scheme name "BuPu8" - <span style="background-color:#f7fcfd; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e0ecf4; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#bfd3e6; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#9ebcda;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#8c96c6; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#8c6bb1; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#88419d; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#6e016b;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	BuPu8("#f7fcfd", "#e0ecf4", "#bfd3e6", "#9ebcda", "#8c96c6", "#8c6bb1", "#88419d", "#6e016b"),
	/**
	 * Color scheme name "BuPu9" - <span style="background-color:#f7fcfd; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e0ecf4; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#bfd3e6; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#9ebcda;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#8c96c6; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#8c6bb1; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#88419d; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#810f7c;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#4d004b; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	BuPu9("#f7fcfd", "#e0ecf4", "#bfd3e6", "#9ebcda", "#8c96c6", "#8c6bb1", "#88419d", "#810f7c", "#4d004b"),
	/**
	 * Color scheme name "RdPu3" - <span style="background-color:#fde0dd; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fa9fb5; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c51b8a; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	RdPu3("#fde0dd", "#fa9fb5", "#c51b8a"),
	/**
	 * Color scheme name "RdPu4" - <span style="background-color:#feebe2; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fbb4b9; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f768a1; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ae017e;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	RdPu4("#feebe2", "#fbb4b9", "#f768a1", "#ae017e"),
	/**
	 * Color scheme name "RdPu5" - <span style="background-color:#feebe2; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fbb4b9; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f768a1; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c51b8a;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#7a0177; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	RdPu5("#feebe2", "#fbb4b9", "#f768a1", "#c51b8a", "#7a0177"),
	/**
	 * Color scheme name "RdPu6" - <span style="background-color:#feebe2; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fcc5c0; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fa9fb5; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f768a1;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#c51b8a; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#7a0177; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	RdPu6("#feebe2", "#fcc5c0", "#fa9fb5", "#f768a1", "#c51b8a", "#7a0177"),
	/**
	 * Color scheme name "RdPu7" - <span style="background-color:#feebe2; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fcc5c0; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fa9fb5; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f768a1;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#dd3497; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ae017e; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#7a0177; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	RdPu7("#feebe2", "#fcc5c0", "#fa9fb5", "#f768a1", "#dd3497", "#ae017e", "#7a0177"),
	/**
	 * Color scheme name "RdPu8" - <span style="background-color:#fff7f3; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fde0dd; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fcc5c0; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fa9fb5;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#f768a1; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#dd3497; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ae017e; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#7a0177;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	RdPu8("#fff7f3", "#fde0dd", "#fcc5c0", "#fa9fb5", "#f768a1", "#dd3497", "#ae017e", "#7a0177"),
	/**
	 * Color scheme name "RdPu9" - <span style="background-color:#fff7f3; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fde0dd; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fcc5c0; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fa9fb5;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#f768a1; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#dd3497; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ae017e; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#7a0177;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#49006a; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	RdPu9("#fff7f3", "#fde0dd", "#fcc5c0", "#fa9fb5", "#f768a1", "#dd3497", "#ae017e", "#7a0177", "#49006a"),
	/**
	 * Color scheme name "PuRd3" - <span style="background-color:#e7e1ef; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c994c7; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#dd1c77; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	PuRd3("#e7e1ef", "#c994c7", "#dd1c77"),
	/**
	 * Color scheme name "PuRd4" - <span style="background-color:#f1eef6; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d7b5d8; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#df65b0; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ce1256;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	PuRd4("#f1eef6", "#d7b5d8", "#df65b0", "#ce1256"),
	/**
	 * Color scheme name "PuRd5" - <span style="background-color:#f1eef6; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d7b5d8; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#df65b0; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#dd1c77;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#980043; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	PuRd5("#f1eef6", "#d7b5d8", "#df65b0", "#dd1c77", "#980043"),
	/**
	 * Color scheme name "PuRd6" - <span style="background-color:#f1eef6; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d4b9da; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c994c7; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#df65b0;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#dd1c77; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#980043; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	PuRd6("#f1eef6", "#d4b9da", "#c994c7", "#df65b0", "#dd1c77", "#980043"),
	/**
	 * Color scheme name "PuRd7" - <span style="background-color:#f1eef6; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d4b9da; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c994c7; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#df65b0;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#e7298a; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ce1256; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#91003f; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	PuRd7("#f1eef6", "#d4b9da", "#c994c7", "#df65b0", "#e7298a", "#ce1256", "#91003f"),
	/**
	 * Color scheme name "PuRd8" - <span style="background-color:#f7f4f9; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e7e1ef; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d4b9da; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c994c7;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#df65b0; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e7298a; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ce1256; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#91003f;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	PuRd8("#f7f4f9", "#e7e1ef", "#d4b9da", "#c994c7", "#df65b0", "#e7298a", "#ce1256", "#91003f"),
	/**
	 * Color scheme name "PuRd9" - <span style="background-color:#f7f4f9; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e7e1ef; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d4b9da; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c994c7;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#df65b0; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e7298a; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ce1256; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#980043;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#67001f; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	PuRd9("#f7f4f9", "#e7e1ef", "#d4b9da", "#c994c7", "#df65b0", "#e7298a", "#ce1256", "#980043", "#67001f"),
	/**
	 * Color scheme name "OrRd3" - <span style="background-color:#fee8c8; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fdbb84; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e34a33; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	OrRd3("#fee8c8", "#fdbb84", "#e34a33"),
	/**
	 * Color scheme name "OrRd4" - <span style="background-color:#fef0d9; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fdcc8a; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fc8d59; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d7301f;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	OrRd4("#fef0d9", "#fdcc8a", "#fc8d59", "#d7301f"),
	/**
	 * Color scheme name "OrRd5" - <span style="background-color:#fef0d9; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fdcc8a; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fc8d59; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e34a33;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#b30000; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	OrRd5("#fef0d9", "#fdcc8a", "#fc8d59", "#e34a33", "#b30000"),
	/**
	 * Color scheme name "OrRd6" - <span style="background-color:#fef0d9; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fdd49e; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fdbb84; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fc8d59;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#e34a33; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b30000; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	OrRd6("#fef0d9", "#fdd49e", "#fdbb84", "#fc8d59", "#e34a33", "#b30000"),
	/**
	 * Color scheme name "OrRd7" - <span style="background-color:#fef0d9; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fdd49e; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fdbb84; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fc8d59;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#ef6548; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d7301f; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#990000; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	OrRd7("#fef0d9", "#fdd49e", "#fdbb84", "#fc8d59", "#ef6548", "#d7301f", "#990000"),
	/**
	 * Color scheme name "OrRd8" - <span style="background-color:#fff7ec; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fee8c8; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fdd49e; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fdbb84;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#fc8d59; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ef6548; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d7301f; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#990000;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	OrRd8("#fff7ec", "#fee8c8", "#fdd49e", "#fdbb84", "#fc8d59", "#ef6548", "#d7301f", "#990000"),
	/**
	 * Color scheme name "OrRd9" - <span style="background-color:#fff7ec; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fee8c8; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fdd49e; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fdbb84;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#fc8d59; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ef6548; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d7301f; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b30000;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#7f0000; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	OrRd9("#fff7ec", "#fee8c8", "#fdd49e", "#fdbb84", "#fc8d59", "#ef6548", "#d7301f", "#b30000", "#7f0000"),
	/**
	 * Color scheme name "YlOrRd3" - <span style="background-color:#ffeda0; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#feb24c; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f03b20; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	YlOrRd3("#ffeda0", "#feb24c", "#f03b20"),
	/**
	 * Color scheme name "YlOrRd4" - <span style="background-color:#ffffb2; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fecc5c; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fd8d3c; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e31a1c;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	YlOrRd4("#ffffb2", "#fecc5c", "#fd8d3c", "#e31a1c"),
	/**
	 * Color scheme name "YlOrRd5" - <span style="background-color:#ffffb2; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fecc5c; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fd8d3c; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f03b20;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#bd0026; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	YlOrRd5("#ffffb2", "#fecc5c", "#fd8d3c", "#f03b20", "#bd0026"),
	/**
	 * Color scheme name "YlOrRd6" - <span style="background-color:#ffffb2; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fed976; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#feb24c; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fd8d3c;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#f03b20; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#bd0026; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	YlOrRd6("#ffffb2", "#fed976", "#feb24c", "#fd8d3c", "#f03b20", "#bd0026"),
	/**
	 * Color scheme name "YlOrRd7" - <span style="background-color:#ffffb2; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fed976; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#feb24c; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fd8d3c;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#fc4e2a; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e31a1c; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b10026; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	YlOrRd7("#ffffb2", "#fed976", "#feb24c", "#fd8d3c", "#fc4e2a", "#e31a1c", "#b10026"),
	/**
	 * Color scheme name "YlOrRd8" - <span style="background-color:#ffffcc; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffeda0; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fed976; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#feb24c;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#fd8d3c; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fc4e2a; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e31a1c; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b10026;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	YlOrRd8("#ffffcc", "#ffeda0", "#fed976", "#feb24c", "#fd8d3c", "#fc4e2a", "#e31a1c", "#b10026"),
	/**
	 * Color scheme name "YlOrRd9" - <span style="background-color:#ffffcc; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffeda0; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fed976; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#feb24c;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#fd8d3c; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fc4e2a; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e31a1c; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#bd0026;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#800026; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	YlOrRd9("#ffffcc", "#ffeda0", "#fed976", "#feb24c", "#fd8d3c", "#fc4e2a", "#e31a1c", "#bd0026", "#800026"),
	/**
	 * Color scheme name "YlOrBr3" - <span style="background-color:#fff7bc; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fec44f; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d95f0e; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	YlOrBr3("#fff7bc", "#fec44f", "#d95f0e"),
	/**
	 * Color scheme name "YlOrBr4" - <span style="background-color:#ffffd4; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fed98e; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fe9929; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#cc4c02;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	YlOrBr4("#ffffd4", "#fed98e", "#fe9929", "#cc4c02"),
	/**
	 * Color scheme name "YlOrBr5" - <span style="background-color:#ffffd4; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fed98e; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fe9929; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d95f0e;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#993404; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	YlOrBr5("#ffffd4", "#fed98e", "#fe9929", "#d95f0e", "#993404"),
	/**
	 * Color scheme name "YlOrBr6" - <span style="background-color:#ffffd4; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fee391; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fec44f; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fe9929;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#d95f0e; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#993404; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	YlOrBr6("#ffffd4", "#fee391", "#fec44f", "#fe9929", "#d95f0e", "#993404"),
	/**
	 * Color scheme name "YlOrBr7" - <span style="background-color:#ffffd4; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fee391; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fec44f; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fe9929;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#ec7014; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#cc4c02; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#8c2d04; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	YlOrBr7("#ffffd4", "#fee391", "#fec44f", "#fe9929", "#ec7014", "#cc4c02", "#8c2d04"),
	/**
	 * Color scheme name "YlOrBr8" - <span style="background-color:#ffffe5; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fff7bc; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fee391; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fec44f;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#fe9929; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ec7014; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#cc4c02; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#8c2d04;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	YlOrBr8("#ffffe5", "#fff7bc", "#fee391", "#fec44f", "#fe9929", "#ec7014", "#cc4c02", "#8c2d04"),
	/**
	 * Color scheme name "YlOrBr9" - <span style="background-color:#ffffe5; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fff7bc; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fee391; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fec44f;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#fe9929; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ec7014; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#cc4c02; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#993404;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#662506; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	YlOrBr9("#ffffe5", "#fff7bc", "#fee391", "#fec44f", "#fe9929", "#ec7014", "#cc4c02", "#993404", "#662506"),
	/**
	 * Color scheme name "Purples3" - <span style="background-color:#efedf5; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#bcbddc; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#756bb1; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Purples3("#efedf5", "#bcbddc", "#756bb1"),
	/**
	 * Color scheme name "Purples4" - <span style="background-color:#f2f0f7; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#cbc9e2; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#9e9ac8; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#6a51a3;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Purples4("#f2f0f7", "#cbc9e2", "#9e9ac8", "#6a51a3"),
	/**
	 * Color scheme name "Purples5" - <span style="background-color:#f2f0f7; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#cbc9e2; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#9e9ac8; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#756bb1;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#54278f; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Purples5("#f2f0f7", "#cbc9e2", "#9e9ac8", "#756bb1", "#54278f"),
	/**
	 * Color scheme name "Purples6" - <span style="background-color:#f2f0f7; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#dadaeb; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#bcbddc; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#9e9ac8;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#756bb1; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#54278f; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Purples6("#f2f0f7", "#dadaeb", "#bcbddc", "#9e9ac8", "#756bb1", "#54278f"),
	/**
	 * Color scheme name "Purples7" - <span style="background-color:#f2f0f7; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#dadaeb; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#bcbddc; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#9e9ac8;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#807dba; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#6a51a3; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#4a1486; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Purples7("#f2f0f7", "#dadaeb", "#bcbddc", "#9e9ac8", "#807dba", "#6a51a3", "#4a1486"),
	/**
	 * Color scheme name "Purples8" - <span style="background-color:#fcfbfd; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#efedf5; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#dadaeb; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#bcbddc;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#9e9ac8; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#807dba; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#6a51a3; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#4a1486;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Purples8("#fcfbfd", "#efedf5", "#dadaeb", "#bcbddc", "#9e9ac8", "#807dba", "#6a51a3", "#4a1486"),
	/**
	 * Color scheme name "Purples9" - <span style="background-color:#fcfbfd; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#efedf5; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#dadaeb; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#bcbddc;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#9e9ac8; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#807dba; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#6a51a3; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#54278f;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#3f007d; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Purples9("#fcfbfd", "#efedf5", "#dadaeb", "#bcbddc", "#9e9ac8", "#807dba", "#6a51a3", "#54278f", "#3f007d"),
	/**
	 * Color scheme name "Blues3" - <span style="background-color:#deebf7; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#9ecae1; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#3182bd; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Blues3("#deebf7", "#9ecae1", "#3182bd"),
	/**
	 * Color scheme name "Blues4" - <span style="background-color:#eff3ff; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#bdd7e7; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#6baed6; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#2171b5;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Blues4("#eff3ff", "#bdd7e7", "#6baed6", "#2171b5"),
	/**
	 * Color scheme name "Blues5" - <span style="background-color:#eff3ff; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#bdd7e7; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#6baed6; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#3182bd;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#08519c; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Blues5("#eff3ff", "#bdd7e7", "#6baed6", "#3182bd", "#08519c"),
	/**
	 * Color scheme name "Blues6" - <span style="background-color:#eff3ff; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c6dbef; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#9ecae1; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#6baed6;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#3182bd; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#08519c; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Blues6("#eff3ff", "#c6dbef", "#9ecae1", "#6baed6", "#3182bd", "#08519c"),
	/**
	 * Color scheme name "Blues7" - <span style="background-color:#eff3ff; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c6dbef; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#9ecae1; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#6baed6;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#4292c6; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#2171b5; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#084594; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Blues7("#eff3ff", "#c6dbef", "#9ecae1", "#6baed6", "#4292c6", "#2171b5", "#084594"),
	/**
	 * Color scheme name "Blues8" - <span style="background-color:#f7fbff; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#deebf7; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c6dbef; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#9ecae1;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#6baed6; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#4292c6; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#2171b5; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#084594;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Blues8("#f7fbff", "#deebf7", "#c6dbef", "#9ecae1", "#6baed6", "#4292c6", "#2171b5", "#084594"),
	/**
	 * Color scheme name "Blues9" - <span style="background-color:#f7fbff; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#deebf7; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c6dbef; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#9ecae1;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#6baed6; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#4292c6; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#2171b5; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#08519c;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#08306b; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Blues9("#f7fbff", "#deebf7", "#c6dbef", "#9ecae1", "#6baed6", "#4292c6", "#2171b5", "#08519c", "#08306b"),
	/**
	 * Color scheme name "Greens3" - <span style="background-color:#e5f5e0; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a1d99b; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#31a354; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Greens3("#e5f5e0", "#a1d99b", "#31a354"),
	/**
	 * Color scheme name "Greens4" - <span style="background-color:#edf8e9; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#bae4b3; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#74c476; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#238b45;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Greens4("#edf8e9", "#bae4b3", "#74c476", "#238b45"),
	/**
	 * Color scheme name "Greens5" - <span style="background-color:#edf8e9; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#bae4b3; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#74c476; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#31a354;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#006d2c; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Greens5("#edf8e9", "#bae4b3", "#74c476", "#31a354", "#006d2c"),
	/**
	 * Color scheme name "Greens6" - <span style="background-color:#edf8e9; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c7e9c0; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a1d99b; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#74c476;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#31a354; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#006d2c; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Greens6("#edf8e9", "#c7e9c0", "#a1d99b", "#74c476", "#31a354", "#006d2c"),
	/**
	 * Color scheme name "Greens7" - <span style="background-color:#edf8e9; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c7e9c0; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a1d99b; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#74c476;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#41ab5d; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#238b45; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#005a32; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Greens7("#edf8e9", "#c7e9c0", "#a1d99b", "#74c476", "#41ab5d", "#238b45", "#005a32"),
	/**
	 * Color scheme name "Greens8" - <span style="background-color:#f7fcf5; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e5f5e0; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c7e9c0; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a1d99b;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#74c476; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#41ab5d; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#238b45; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#005a32;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Greens8("#f7fcf5", "#e5f5e0", "#c7e9c0", "#a1d99b", "#74c476", "#41ab5d", "#238b45", "#005a32"),
	/**
	 * Color scheme name "Greens9" - <span style="background-color:#f7fcf5; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e5f5e0; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c7e9c0; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a1d99b;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#74c476; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#41ab5d; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#238b45; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#006d2c;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#00441b; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Greens9("#f7fcf5", "#e5f5e0", "#c7e9c0", "#a1d99b", "#74c476", "#41ab5d", "#238b45", "#006d2c", "#00441b"),
	/**
	 * Color scheme name "Oranges3" - <span style="background-color:#fee6ce; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fdae6b; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e6550d; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Oranges3("#fee6ce", "#fdae6b", "#e6550d"),
	/**
	 * Color scheme name "Oranges4" - <span style="background-color:#feedde; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fdbe85; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fd8d3c; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d94701;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Oranges4("#feedde", "#fdbe85", "#fd8d3c", "#d94701"),
	/**
	 * Color scheme name "Oranges5" - <span style="background-color:#feedde; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fdbe85; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fd8d3c; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e6550d;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#a63603; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Oranges5("#feedde", "#fdbe85", "#fd8d3c", "#e6550d", "#a63603"),
	/**
	 * Color scheme name "Oranges6" - <span style="background-color:#feedde; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fdd0a2; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fdae6b; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fd8d3c;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#e6550d; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a63603; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Oranges6("#feedde", "#fdd0a2", "#fdae6b", "#fd8d3c", "#e6550d", "#a63603"),
	/**
	 * Color scheme name "Oranges7" - <span style="background-color:#feedde; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fdd0a2; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fdae6b; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fd8d3c;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#f16913; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d94801; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#8c2d04; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Oranges7("#feedde", "#fdd0a2", "#fdae6b", "#fd8d3c", "#f16913", "#d94801", "#8c2d04"),
	/**
	 * Color scheme name "Oranges8" - <span style="background-color:#fff5eb; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fee6ce; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fdd0a2; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fdae6b;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#fd8d3c; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f16913; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d94801; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#8c2d04;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Oranges8("#fff5eb", "#fee6ce", "#fdd0a2", "#fdae6b", "#fd8d3c", "#f16913", "#d94801", "#8c2d04"),
	/**
	 * Color scheme name "Oranges9" - <span style="background-color:#fff5eb; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fee6ce; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fdd0a2; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fdae6b;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#fd8d3c; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f16913; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d94801; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a63603;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#7f2704; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Oranges9("#fff5eb", "#fee6ce", "#fdd0a2", "#fdae6b", "#fd8d3c", "#f16913", "#d94801", "#a63603", "#7f2704"),
	/**
	 * Color scheme name "Reds3" - <span style="background-color:#fee0d2; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fc9272; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#de2d26; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Reds3("#fee0d2", "#fc9272", "#de2d26"),
	/**
	 * Color scheme name "Reds4" - <span style="background-color:#fee5d9; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fcae91; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fb6a4a; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#cb181d;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Reds4("#fee5d9", "#fcae91", "#fb6a4a", "#cb181d"),
	/**
	 * Color scheme name "Reds5" - <span style="background-color:#fee5d9; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fcae91; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fb6a4a; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#de2d26;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#a50f15; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Reds5("#fee5d9", "#fcae91", "#fb6a4a", "#de2d26", "#a50f15"),
	/**
	 * Color scheme name "Reds6" - <span style="background-color:#fee5d9; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fcbba1; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fc9272; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fb6a4a;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#de2d26; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a50f15; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Reds6("#fee5d9", "#fcbba1", "#fc9272", "#fb6a4a", "#de2d26", "#a50f15"),
	/**
	 * Color scheme name "Reds7" - <span style="background-color:#fee5d9; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fcbba1; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fc9272; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fb6a4a;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#ef3b2c; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#cb181d; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#99000d; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Reds7("#fee5d9", "#fcbba1", "#fc9272", "#fb6a4a", "#ef3b2c", "#cb181d", "#99000d"),
	/**
	 * Color scheme name "Reds8" - <span style="background-color:#fff5f0; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fee0d2; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fcbba1; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fc9272;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#fb6a4a; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ef3b2c; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#cb181d; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#99000d;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Reds8("#fff5f0", "#fee0d2", "#fcbba1", "#fc9272", "#fb6a4a", "#ef3b2c", "#cb181d", "#99000d"),
	/**
	 * Color scheme name "Reds9" - <span style="background-color:#fff5f0; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fee0d2; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fcbba1; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fc9272;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#fb6a4a; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ef3b2c; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#cb181d; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a50f15;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#67000d; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Reds9("#fff5f0", "#fee0d2", "#fcbba1", "#fc9272", "#fb6a4a", "#ef3b2c", "#cb181d", "#a50f15", "#67000d"),
	/**
	 * Color scheme name "Greys3" - <span style="background-color:#f0f0f0; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#bdbdbd; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#636363; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Greys3("#f0f0f0", "#bdbdbd", "#636363"),
	/**
	 * Color scheme name "Greys4" - <span style="background-color:#f7f7f7; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#cccccc; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#969696; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#525252;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Greys4("#f7f7f7", "#cccccc", "#969696", "#525252"),
	/**
	 * Color scheme name "Greys5" - <span style="background-color:#f7f7f7; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#cccccc; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#969696; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#636363;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#252525; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Greys5("#f7f7f7", "#cccccc", "#969696", "#636363", "#252525"),
	/**
	 * Color scheme name "Greys6" - <span style="background-color:#f7f7f7; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d9d9d9; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#bdbdbd; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#969696;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#636363; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#252525; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Greys6("#f7f7f7", "#d9d9d9", "#bdbdbd", "#969696", "#636363", "#252525"),
	/**
	 * Color scheme name "Greys7" - <span style="background-color:#f7f7f7; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d9d9d9; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#bdbdbd; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#969696;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#737373; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#525252; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#252525; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Greys7("#f7f7f7", "#d9d9d9", "#bdbdbd", "#969696", "#737373", "#525252", "#252525"),
	/**
	 * Color scheme name "Greys8" - <span style="background-color:#FFFFFF; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f0f0f0; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d9d9d9; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#bdbdbd;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#969696; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#737373; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#525252; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#252525;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Greys8("#FFFFFF", "#f0f0f0", "#d9d9d9", "#bdbdbd", "#969696", "#737373", "#525252", "#252525"),
	/**
	 * Color scheme name "Greys9" - <span style="background-color:#FFFFFF; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f0f0f0; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d9d9d9; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#bdbdbd;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#969696; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#737373; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#525252; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#252525;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#000000; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Greys9("#FFFFFF", "#f0f0f0", "#d9d9d9", "#bdbdbd", "#969696", "#737373", "#525252", "#252525", "#000000"),
	/**
	 * Color scheme name "PuOr3" - <span style="background-color:#f1a340; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f7f7f7; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#998ec3; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	PuOr3("#f1a340", "#f7f7f7", "#998ec3"),
	/**
	 * Color scheme name "PuOr4" - <span style="background-color:#e66101; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fdb863; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b2abd2; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#5e3c99;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	PuOr4("#e66101", "#fdb863", "#b2abd2", "#5e3c99"),
	/**
	 * Color scheme name "PuOr5" - <span style="background-color:#e66101; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fdb863; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f7f7f7; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b2abd2;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#5e3c99; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	PuOr5("#e66101", "#fdb863", "#f7f7f7", "#b2abd2", "#5e3c99"),
	/**
	 * Color scheme name "PuOr6" - <span style="background-color:#b35806; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f1a340; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fee0b6; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d8daeb;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#998ec3; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#542788; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	PuOr6("#b35806", "#f1a340", "#fee0b6", "#d8daeb", "#998ec3", "#542788"),
	/**
	 * Color scheme name "PuOr7" - <span style="background-color:#b35806; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f1a340; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fee0b6; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f7f7f7;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#d8daeb; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#998ec3; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#542788; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	PuOr7("#b35806", "#f1a340", "#fee0b6", "#f7f7f7", "#d8daeb", "#998ec3", "#542788"),
	/**
	 * Color scheme name "PuOr8" - <span style="background-color:#b35806; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e08214; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fdb863; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fee0b6;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#d8daeb; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b2abd2; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#8073ac; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#542788;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	PuOr8("#b35806", "#e08214", "#fdb863", "#fee0b6", "#d8daeb", "#b2abd2", "#8073ac", "#542788"),
	/**
	 * Color scheme name "PuOr9" - <span style="background-color:#b35806; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e08214; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fdb863; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fee0b6;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#f7f7f7; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d8daeb; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b2abd2; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#8073ac;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#542788; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	PuOr9("#b35806", "#e08214", "#fdb863", "#fee0b6", "#f7f7f7", "#d8daeb", "#b2abd2", "#8073ac", "#542788"),
	/**
	 * Color scheme name "PuOr10" - <span style="background-color:#7f3b08; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b35806; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e08214; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fdb863;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#fee0b6; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d8daeb; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b2abd2; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#8073ac;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#542788; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#2d004b; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	PuOr10("#7f3b08", "#b35806", "#e08214", "#fdb863", "#fee0b6", "#d8daeb", "#b2abd2", "#8073ac", "#542788", "#2d004b"),
	/**
	 * Color scheme name "PuOr11" - <span style="background-color:#7f3b08; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b35806; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e08214; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fdb863;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#fee0b6; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f7f7f7; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d8daeb; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b2abd2;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#8073ac; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#542788; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#2d004b; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	PuOr11("#7f3b08", "#b35806", "#e08214", "#fdb863", "#fee0b6", "#f7f7f7", "#d8daeb", "#b2abd2", "#8073ac", "#542788", "#2d004b"),
	/**
	 * Color scheme name "BrBG3" - <span style="background-color:#d8b365; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#F5F5F5; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#5ab4ac; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	BrBG3("#d8b365", "#F5F5F5", "#5ab4ac"),
	/**
	 * Color scheme name "BrBG4" - <span style="background-color:#a6611a; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#dfc27d; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#80cdc1; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#018571;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	BrBG4("#a6611a", "#dfc27d", "#80cdc1", "#018571"),
	/**
	 * Color scheme name "BrBG5" - <span style="background-color:#a6611a; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#dfc27d; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#F5F5F5; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#80cdc1;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#018571; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	BrBG5("#a6611a", "#dfc27d", "#F5F5F5", "#80cdc1", "#018571"),
	/**
	 * Color scheme name "BrBG6" - <span style="background-color:#8c510a; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d8b365; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f6e8c3; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c7eae5;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#5ab4ac; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#01665e; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	BrBG6("#8c510a", "#d8b365", "#f6e8c3", "#c7eae5", "#5ab4ac", "#01665e"),
	/**
	 * Color scheme name "BrBG7" - <span style="background-color:#8c510a; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d8b365; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f6e8c3; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#F5F5F5;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#c7eae5; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#5ab4ac; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#01665e; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	BrBG7("#8c510a", "#d8b365", "#f6e8c3", "#F5F5F5", "#c7eae5", "#5ab4ac", "#01665e"),
	/**
	 * Color scheme name "BrBG8" - <span style="background-color:#8c510a; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#bf812d; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#dfc27d; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f6e8c3;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#c7eae5; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#80cdc1; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#35978f; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#01665e;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	BrBG8("#8c510a", "#bf812d", "#dfc27d", "#f6e8c3", "#c7eae5", "#80cdc1", "#35978f", "#01665e"),
	/**
	 * Color scheme name "BrBG9" - <span style="background-color:#8c510a; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#bf812d; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#dfc27d; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f6e8c3;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#F5F5F5; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c7eae5; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#80cdc1; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#35978f;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#01665e; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	BrBG9("#8c510a", "#bf812d", "#dfc27d", "#f6e8c3", "#F5F5F5", "#c7eae5", "#80cdc1", "#35978f", "#01665e"),
	/**
	 * Color scheme name "BrBG10" - <span style="background-color:#543005; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#8c510a; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#bf812d; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#dfc27d;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#f6e8c3; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c7eae5; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#80cdc1; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#35978f;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#01665e; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#003c30; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	BrBG10("#543005", "#8c510a", "#bf812d", "#dfc27d", "#f6e8c3", "#c7eae5", "#80cdc1", "#35978f", "#01665e", "#003c30"),
	/**
	 * Color scheme name "BrBG11" - <span style="background-color:#543005; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#8c510a; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#bf812d; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#dfc27d;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#f6e8c3; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#F5F5F5; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c7eae5; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#80cdc1;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#35978f; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#01665e; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#003c30; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	BrBG11("#543005", "#8c510a", "#bf812d", "#dfc27d", "#f6e8c3", "#F5F5F5", "#c7eae5", "#80cdc1", "#35978f", "#01665e", "#003c30"),
	/**
	 * Color scheme name "PRGn3" - <span style="background-color:#af8dc3; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f7f7f7; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#7fbf7b; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	PRGn3("#af8dc3", "#f7f7f7", "#7fbf7b"),
	/**
	 * Color scheme name "PRGn4" - <span style="background-color:#7b3294; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c2a5cf; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a6dba0; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#008837;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	PRGn4("#7b3294", "#c2a5cf", "#a6dba0", "#008837"),
	/**
	 * Color scheme name "PRGn5" - <span style="background-color:#7b3294; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c2a5cf; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f7f7f7; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a6dba0;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#008837; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	PRGn5("#7b3294", "#c2a5cf", "#f7f7f7", "#a6dba0", "#008837"),
	/**
	 * Color scheme name "PRGn6" - <span style="background-color:#762a83; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#af8dc3; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e7d4e8; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d9f0d3;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#7fbf7b; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#1b7837; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	PRGn6("#762a83", "#af8dc3", "#e7d4e8", "#d9f0d3", "#7fbf7b", "#1b7837"),
	/**
	 * Color scheme name "PRGn7" - <span style="background-color:#762a83; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#af8dc3; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e7d4e8; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f7f7f7;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#d9f0d3; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#7fbf7b; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#1b7837; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	PRGn7("#762a83", "#af8dc3", "#e7d4e8", "#f7f7f7", "#d9f0d3", "#7fbf7b", "#1b7837"),
	/**
	 * Color scheme name "PRGn8" - <span style="background-color:#762a83; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#9970ab; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c2a5cf; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e7d4e8;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#d9f0d3; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a6dba0; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#5aae61; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#1b7837;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	PRGn8("#762a83", "#9970ab", "#c2a5cf", "#e7d4e8", "#d9f0d3", "#a6dba0", "#5aae61", "#1b7837"),
	/**
	 * Color scheme name "PRGn9" - <span style="background-color:#762a83; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#9970ab; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c2a5cf; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e7d4e8;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#f7f7f7; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d9f0d3; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a6dba0; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#5aae61;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#1b7837; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	PRGn9("#762a83", "#9970ab", "#c2a5cf", "#e7d4e8", "#f7f7f7", "#d9f0d3", "#a6dba0", "#5aae61", "#1b7837"),
	/**
	 * Color scheme name "PRGn10" - <span style="background-color:#40004b; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#762a83; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#9970ab; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c2a5cf;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#e7d4e8; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d9f0d3; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a6dba0; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#5aae61;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#1b7837; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#00441b; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	PRGn10("#40004b", "#762a83", "#9970ab", "#c2a5cf", "#e7d4e8", "#d9f0d3", "#a6dba0", "#5aae61", "#1b7837", "#00441b"),
	/**
	 * Color scheme name "PRGn11" - <span style="background-color:#40004b; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#762a83; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#9970ab; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c2a5cf;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#e7d4e8; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f7f7f7; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d9f0d3; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a6dba0;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#5aae61; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#1b7837; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#00441b; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	PRGn11("#40004b", "#762a83", "#9970ab", "#c2a5cf", "#e7d4e8", "#f7f7f7", "#d9f0d3", "#a6dba0", "#5aae61", "#1b7837", "#00441b"),
	/**
	 * Color scheme name "PiYG3" - <span style="background-color:#e9a3c9; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f7f7f7; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a1d76a; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	PiYG3("#e9a3c9", "#f7f7f7", "#a1d76a"),
	/**
	 * Color scheme name "PiYG4" - <span style="background-color:#d01c8b; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f1b6da; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b8e186; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#4dac26;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	PiYG4("#d01c8b", "#f1b6da", "#b8e186", "#4dac26"),
	/**
	 * Color scheme name "PiYG5" - <span style="background-color:#d01c8b; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f1b6da; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f7f7f7; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b8e186;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#4dac26; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	PiYG5("#d01c8b", "#f1b6da", "#f7f7f7", "#b8e186", "#4dac26"),
	/**
	 * Color scheme name "PiYG6" - <span style="background-color:#c51b7d; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e9a3c9; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fde0ef; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e6f5d0;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#a1d76a; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#4d9221; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	PiYG6("#c51b7d", "#e9a3c9", "#fde0ef", "#e6f5d0", "#a1d76a", "#4d9221"),
	/**
	 * Color scheme name "PiYG7" - <span style="background-color:#c51b7d; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e9a3c9; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fde0ef; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f7f7f7;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#e6f5d0; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a1d76a; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#4d9221; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	PiYG7("#c51b7d", "#e9a3c9", "#fde0ef", "#f7f7f7", "#e6f5d0", "#a1d76a", "#4d9221"),
	/**
	 * Color scheme name "PiYG8" - <span style="background-color:#c51b7d; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#de77ae; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f1b6da; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fde0ef;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#e6f5d0; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b8e186; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#7fbc41; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#4d9221;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	PiYG8("#c51b7d", "#de77ae", "#f1b6da", "#fde0ef", "#e6f5d0", "#b8e186", "#7fbc41", "#4d9221"),
	/**
	 * Color scheme name "PiYG9" - <span style="background-color:#c51b7d; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#de77ae; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f1b6da; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fde0ef;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#f7f7f7; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e6f5d0; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b8e186; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#7fbc41;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#4d9221; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	PiYG9("#c51b7d", "#de77ae", "#f1b6da", "#fde0ef", "#f7f7f7", "#e6f5d0", "#b8e186", "#7fbc41", "#4d9221"),
	/**
	 * Color scheme name "PiYG10" - <span style="background-color:#8e0152; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c51b7d; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#de77ae; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f1b6da;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#fde0ef; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e6f5d0; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b8e186; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#7fbc41;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#4d9221; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#276419; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	PiYG10("#8e0152", "#c51b7d", "#de77ae", "#f1b6da", "#fde0ef", "#e6f5d0", "#b8e186", "#7fbc41", "#4d9221", "#276419"),
	/**
	 * Color scheme name "PiYG11" - <span style="background-color:#8e0152; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#c51b7d; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#de77ae; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f1b6da;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#fde0ef; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f7f7f7; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e6f5d0; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b8e186;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#7fbc41; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#4d9221; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#276419; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	PiYG11("#8e0152", "#c51b7d", "#de77ae", "#f1b6da", "#fde0ef", "#f7f7f7", "#e6f5d0", "#b8e186", "#7fbc41", "#4d9221", "#276419"),
	/**
	 * Color scheme name "RdBu3" - <span style="background-color:#ef8a62; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f7f7f7; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#67a9cf; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	RdBu3("#ef8a62", "#f7f7f7", "#67a9cf"),
	/**
	 * Color scheme name "RdBu4" - <span style="background-color:#ca0020; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f4a582; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#92c5de; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#0571b0;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	RdBu4("#ca0020", "#f4a582", "#92c5de", "#0571b0"),
	/**
	 * Color scheme name "RdBu5" - <span style="background-color:#ca0020; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f4a582; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f7f7f7; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#92c5de;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#0571b0; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	RdBu5("#ca0020", "#f4a582", "#f7f7f7", "#92c5de", "#0571b0"),
	/**
	 * Color scheme name "RdBu6" - <span style="background-color:#b2182b; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ef8a62; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fddbc7; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d1e5f0;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#67a9cf; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#2166ac; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	RdBu6("#b2182b", "#ef8a62", "#fddbc7", "#d1e5f0", "#67a9cf", "#2166ac"),
	/**
	 * Color scheme name "RdBu7" - <span style="background-color:#b2182b; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ef8a62; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fddbc7; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f7f7f7;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#d1e5f0; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#67a9cf; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#2166ac; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	RdBu7("#b2182b", "#ef8a62", "#fddbc7", "#f7f7f7", "#d1e5f0", "#67a9cf", "#2166ac"),
	/**
	 * Color scheme name "RdBu8" - <span style="background-color:#b2182b; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d6604d; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f4a582; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fddbc7;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#d1e5f0; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#92c5de; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#4393c3; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#2166ac;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	RdBu8("#b2182b", "#d6604d", "#f4a582", "#fddbc7", "#d1e5f0", "#92c5de", "#4393c3", "#2166ac"),
	/**
	 * Color scheme name "RdBu9" - <span style="background-color:#b2182b; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d6604d; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f4a582; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fddbc7;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#f7f7f7; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d1e5f0; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#92c5de; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#4393c3;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#2166ac; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	RdBu9("#b2182b", "#d6604d", "#f4a582", "#fddbc7", "#f7f7f7", "#d1e5f0", "#92c5de", "#4393c3", "#2166ac"),
	/**
	 * Color scheme name "RdBu10" - <span style="background-color:#67001f; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b2182b; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d6604d; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f4a582;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#fddbc7; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d1e5f0; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#92c5de; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#4393c3;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#2166ac; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#053061; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	RdBu10("#67001f", "#b2182b", "#d6604d", "#f4a582", "#fddbc7", "#d1e5f0", "#92c5de", "#4393c3", "#2166ac", "#053061"),
	/**
	 * Color scheme name "RdBu11" - <span style="background-color:#67001f; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b2182b; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d6604d; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f4a582;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#fddbc7; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f7f7f7; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d1e5f0; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#92c5de;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#4393c3; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#2166ac; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#053061; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	RdBu11("#67001f", "#b2182b", "#d6604d", "#f4a582", "#fddbc7", "#f7f7f7", "#d1e5f0", "#92c5de", "#4393c3", "#2166ac", "#053061"),
	/**
	 * Color scheme name "RdGy3" - <span style="background-color:#ef8a62; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#FFFFFF; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#999999; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	RdGy3("#ef8a62", "#FFFFFF", "#999999"),
	/**
	 * Color scheme name "RdGy4" - <span style="background-color:#ca0020; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f4a582; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#bababa; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#404040;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	RdGy4("#ca0020", "#f4a582", "#bababa", "#404040"),
	/**
	 * Color scheme name "RdGy5" - <span style="background-color:#ca0020; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f4a582; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#FFFFFF; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#bababa;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#404040; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	RdGy5("#ca0020", "#f4a582", "#FFFFFF", "#bababa", "#404040"),
	/**
	 * Color scheme name "RdGy6" - <span style="background-color:#b2182b; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ef8a62; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fddbc7; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e0e0e0;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#999999; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#4d4d4d; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	RdGy6("#b2182b", "#ef8a62", "#fddbc7", "#e0e0e0", "#999999", "#4d4d4d"),
	/**
	 * Color scheme name "RdGy7" - <span style="background-color:#b2182b; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ef8a62; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fddbc7; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#FFFFFF;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#e0e0e0; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#999999; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#4d4d4d; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	RdGy7("#b2182b", "#ef8a62", "#fddbc7", "#FFFFFF", "#e0e0e0", "#999999", "#4d4d4d"),
	/**
	 * Color scheme name "RdGy8" - <span style="background-color:#b2182b; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d6604d; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f4a582; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fddbc7;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#e0e0e0; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#bababa; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#878787; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#4d4d4d;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	RdGy8("#b2182b", "#d6604d", "#f4a582", "#fddbc7", "#e0e0e0", "#bababa", "#878787", "#4d4d4d"),
	/**
	 * Color scheme name "RdGy9" - <span style="background-color:#b2182b; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d6604d; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f4a582; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fddbc7;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#FFFFFF; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e0e0e0; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#bababa; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#878787;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#4d4d4d; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	RdGy9("#b2182b", "#d6604d", "#f4a582", "#fddbc7", "#FFFFFF", "#e0e0e0", "#bababa", "#878787", "#4d4d4d"),
	/**
	 * Color scheme name "RdGy10" - <span style="background-color:#67001f; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b2182b; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d6604d; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f4a582;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#fddbc7; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e0e0e0; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#bababa; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#878787;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#4d4d4d; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#1a1a1a; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	RdGy10("#67001f", "#b2182b", "#d6604d", "#f4a582", "#fddbc7", "#e0e0e0", "#bababa", "#878787", "#4d4d4d", "#1a1a1a"),
	/**
	 * Color scheme name "RdGy11" - <span style="background-color:#67001f; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b2182b; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d6604d; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f4a582;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#fddbc7; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#FFFFFF; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e0e0e0; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#bababa;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#878787; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#4d4d4d; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#1a1a1a; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	RdGy11("#67001f", "#b2182b", "#d6604d", "#f4a582", "#fddbc7", "#FFFFFF", "#e0e0e0", "#bababa", "#878787", "#4d4d4d", "#1a1a1a"),
	/**
	 * Color scheme name "RdYlBu3" - <span style="background-color:#fc8d59; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffffbf; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#91bfdb; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	RdYlBu3("#fc8d59", "#ffffbf", "#91bfdb"),
	/**
	 * Color scheme name "RdYlBu4" - <span style="background-color:#d7191c; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fdae61; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#abd9e9; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#2c7bb6;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	RdYlBu4("#d7191c", "#fdae61", "#abd9e9", "#2c7bb6"),
	/**
	 * Color scheme name "RdYlBu5" - <span style="background-color:#d7191c; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fdae61; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffffbf; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#abd9e9;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#2c7bb6; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	RdYlBu5("#d7191c", "#fdae61", "#ffffbf", "#abd9e9", "#2c7bb6"),
	/**
	 * Color scheme name "RdYlBu6" - <span style="background-color:#d73027; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fc8d59; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fee090; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e0f3f8;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#91bfdb; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#4575b4; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	RdYlBu6("#d73027", "#fc8d59", "#fee090", "#e0f3f8", "#91bfdb", "#4575b4"),
	/**
	 * Color scheme name "RdYlBu7" - <span style="background-color:#d73027; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fc8d59; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fee090; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffffbf;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#e0f3f8; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#91bfdb; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#4575b4; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	RdYlBu7("#d73027", "#fc8d59", "#fee090", "#ffffbf", "#e0f3f8", "#91bfdb", "#4575b4"),
	/**
	 * Color scheme name "RdYlBu8" - <span style="background-color:#d73027; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f46d43; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fdae61; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fee090;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#e0f3f8; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#abd9e9; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#74add1; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#4575b4;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	RdYlBu8("#d73027", "#f46d43", "#fdae61", "#fee090", "#e0f3f8", "#abd9e9", "#74add1", "#4575b4"),
	/**
	 * Color scheme name "RdYlBu9" - <span style="background-color:#d73027; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f46d43; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fdae61; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fee090;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#ffffbf; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e0f3f8; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#abd9e9; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#74add1;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#4575b4; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	RdYlBu9("#d73027", "#f46d43", "#fdae61", "#fee090", "#ffffbf", "#e0f3f8", "#abd9e9", "#74add1", "#4575b4"),
	/**
	 * Color scheme name "RdYlBu10" - <span style="background-color:#a50026; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d73027; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f46d43; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fdae61;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#fee090; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e0f3f8; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#abd9e9; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#74add1;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#4575b4; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#313695; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	RdYlBu10("#a50026", "#d73027", "#f46d43", "#fdae61", "#fee090", "#e0f3f8", "#abd9e9", "#74add1", "#4575b4", "#313695"),
	/**
	 * Color scheme name "RdYlBu11" - <span style="background-color:#a50026; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d73027; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f46d43; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fdae61;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#fee090; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffffbf; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e0f3f8; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#abd9e9;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#74add1; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#4575b4; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#313695; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	RdYlBu11("#a50026", "#d73027", "#f46d43", "#fdae61", "#fee090", "#ffffbf", "#e0f3f8", "#abd9e9", "#74add1", "#4575b4", "#313695"),
	/**
	 * Color scheme name "Spectral3" - <span style="background-color:#fc8d59; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffffbf; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#99d594; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Spectral3("#fc8d59", "#ffffbf", "#99d594"),
	/**
	 * Color scheme name "Spectral4" - <span style="background-color:#d7191c; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fdae61; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#abdda4; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#2b83ba;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Spectral4("#d7191c", "#fdae61", "#abdda4", "#2b83ba"),
	/**
	 * Color scheme name "Spectral5" - <span style="background-color:#d7191c; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fdae61; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffffbf; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#abdda4;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#2b83ba; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Spectral5("#d7191c", "#fdae61", "#ffffbf", "#abdda4", "#2b83ba"),
	/**
	 * Color scheme name "Spectral6" - <span style="background-color:#d53e4f; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fc8d59; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fee08b; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e6f598;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#99d594; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#3288bd; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Spectral6("#d53e4f", "#fc8d59", "#fee08b", "#e6f598", "#99d594", "#3288bd"),
	/**
	 * Color scheme name "Spectral7" - <span style="background-color:#d53e4f; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fc8d59; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fee08b; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffffbf;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#e6f598; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#99d594; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#3288bd; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Spectral7("#d53e4f", "#fc8d59", "#fee08b", "#ffffbf", "#e6f598", "#99d594", "#3288bd"),
	/**
	 * Color scheme name "Spectral8" - <span style="background-color:#d53e4f; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f46d43; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fdae61; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fee08b;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#e6f598; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#abdda4; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#66c2a5; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#3288bd;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Spectral8("#d53e4f", "#f46d43", "#fdae61", "#fee08b", "#e6f598", "#abdda4", "#66c2a5", "#3288bd"),
	/**
	 * Color scheme name "Spectral9" - <span style="background-color:#d53e4f; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f46d43; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fdae61; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fee08b;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#ffffbf; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e6f598; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#abdda4; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#66c2a5;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#3288bd; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Spectral9("#d53e4f", "#f46d43", "#fdae61", "#fee08b", "#ffffbf", "#e6f598", "#abdda4", "#66c2a5", "#3288bd"),
	/**
	 * Color scheme name "Spectral10" - <span style="background-color:#9e0142; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d53e4f; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f46d43; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fdae61;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#fee08b; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e6f598; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#abdda4; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#66c2a5;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#3288bd; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#5e4fa2; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Spectral10("#9e0142", "#d53e4f", "#f46d43", "#fdae61", "#fee08b", "#e6f598", "#abdda4", "#66c2a5", "#3288bd", "#5e4fa2"),
	/**
	 * Color scheme name "Spectral11" - <span style="background-color:#9e0142; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d53e4f; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f46d43; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fdae61;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#fee08b; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffffbf; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e6f598; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#abdda4;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#66c2a5; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#3288bd; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#5e4fa2; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Spectral11("#9e0142", "#d53e4f", "#f46d43", "#fdae61", "#fee08b", "#ffffbf", "#e6f598", "#abdda4", "#66c2a5", "#3288bd", "#5e4fa2"),
	/**
	 * Color scheme name "RdYlGn3" - <span style="background-color:#fc8d59; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffffbf; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#91cf60; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	RdYlGn3("#fc8d59", "#ffffbf", "#91cf60"),
	/**
	 * Color scheme name "RdYlGn4" - <span style="background-color:#d7191c; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fdae61; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a6d96a; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#1a9641;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	RdYlGn4("#d7191c", "#fdae61", "#a6d96a", "#1a9641"),
	/**
	 * Color scheme name "RdYlGn5" - <span style="background-color:#d7191c; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fdae61; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffffbf; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a6d96a;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#1a9641; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	RdYlGn5("#d7191c", "#fdae61", "#ffffbf", "#a6d96a", "#1a9641"),
	/**
	 * Color scheme name "RdYlGn6" - <span style="background-color:#d73027; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fc8d59; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fee08b; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d9ef8b;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#91cf60; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#1a9850; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	RdYlGn6("#d73027", "#fc8d59", "#fee08b", "#d9ef8b", "#91cf60", "#1a9850"),
	/**
	 * Color scheme name "RdYlGn7" - <span style="background-color:#d73027; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fc8d59; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fee08b; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffffbf;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#d9ef8b; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#91cf60; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#1a9850; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	RdYlGn7("#d73027", "#fc8d59", "#fee08b", "#ffffbf", "#d9ef8b", "#91cf60", "#1a9850"),
	/**
	 * Color scheme name "RdYlGn8" - <span style="background-color:#d73027; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f46d43; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fdae61; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fee08b;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#d9ef8b; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a6d96a; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#66bd63; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#1a9850;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	RdYlGn8("#d73027", "#f46d43", "#fdae61", "#fee08b", "#d9ef8b", "#a6d96a", "#66bd63", "#1a9850"),
	/**
	 * Color scheme name "RdYlGn9" - <span style="background-color:#d73027; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f46d43; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fdae61; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fee08b;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#ffffbf; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d9ef8b; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a6d96a; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#66bd63;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#1a9850; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	RdYlGn9("#d73027", "#f46d43", "#fdae61", "#fee08b", "#ffffbf", "#d9ef8b", "#a6d96a", "#66bd63", "#1a9850"),
	/**
	 * Color scheme name "RdYlGn10" - <span style="background-color:#a50026; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d73027; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f46d43; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fdae61;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#fee08b; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d9ef8b; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a6d96a; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#66bd63;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#1a9850; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#006837; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	RdYlGn10("#a50026", "#d73027", "#f46d43", "#fdae61", "#fee08b", "#d9ef8b", "#a6d96a", "#66bd63", "#1a9850", "#006837"),
	/**
	 * Color scheme name "RdYlGn11" - <span style="background-color:#a50026; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d73027; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f46d43; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fdae61;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#fee08b; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffffbf; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d9ef8b; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a6d96a;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#66bd63; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#1a9850; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#006837; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	RdYlGn11("#a50026", "#d73027", "#f46d43", "#fdae61", "#fee08b", "#ffffbf", "#d9ef8b", "#a6d96a", "#66bd63", "#1a9850", "#006837"),
	/**
	 * Color scheme name "Accent3" - <span style="background-color:#7fc97f; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#beaed4; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fdc086; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Accent3("#7fc97f", "#beaed4", "#fdc086"),
	/**
	 * Color scheme name "Accent4" - <span style="background-color:#7fc97f; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#beaed4; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fdc086; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffff99;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Accent4("#7fc97f", "#beaed4", "#fdc086", "#ffff99"),
	/**
	 * Color scheme name "Accent5" - <span style="background-color:#7fc97f; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#beaed4; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fdc086; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffff99;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#386cb0; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Accent5("#7fc97f", "#beaed4", "#fdc086", "#ffff99", "#386cb0"),
	/**
	 * Color scheme name "Accent6" - <span style="background-color:#7fc97f; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#beaed4; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fdc086; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffff99;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#386cb0; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f0027f; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Accent6("#7fc97f", "#beaed4", "#fdc086", "#ffff99", "#386cb0", "#f0027f"),
	/**
	 * Color scheme name "Accent7" - <span style="background-color:#7fc97f; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#beaed4; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fdc086; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffff99;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#386cb0; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f0027f; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#bf5b17; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Accent7("#7fc97f", "#beaed4", "#fdc086", "#ffff99", "#386cb0", "#f0027f", "#bf5b17"),
	/**
	 * Color scheme name "Accent8" - <span style="background-color:#7fc97f; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#beaed4; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fdc086; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffff99;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#386cb0; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f0027f; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#bf5b17; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#666666;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Accent8("#7fc97f", "#beaed4", "#fdc086", "#ffff99", "#386cb0", "#f0027f", "#bf5b17", "#666666"),
	/**
	 * Color scheme name "Dark2_3" - <span style="background-color:#1b9e77; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d95f02; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#7570b3; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Dark2_3("#1b9e77", "#d95f02", "#7570b3"),
	/**
	 * Color scheme name "Dark2_4" - <span style="background-color:#1b9e77; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d95f02; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#7570b3; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e7298a;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Dark2_4("#1b9e77", "#d95f02", "#7570b3", "#e7298a"),
	/**
	 * Color scheme name "Dark2_5" - <span style="background-color:#1b9e77; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d95f02; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#7570b3; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e7298a;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#66a61e; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Dark2_5("#1b9e77", "#d95f02", "#7570b3", "#e7298a", "#66a61e"),
	/**
	 * Color scheme name "Dark2_6" - <span style="background-color:#1b9e77; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d95f02; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#7570b3; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e7298a;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#66a61e; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e6ab02; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Dark2_6("#1b9e77", "#d95f02", "#7570b3", "#e7298a", "#66a61e", "#e6ab02"),
	/**
	 * Color scheme name "Dark2_7" - <span style="background-color:#1b9e77; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d95f02; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#7570b3; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e7298a;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#66a61e; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e6ab02; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a6761d; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Dark2_7("#1b9e77", "#d95f02", "#7570b3", "#e7298a", "#66a61e", "#e6ab02", "#a6761d"),
	/**
	 * Color scheme name "Dark2_8" - <span style="background-color:#1b9e77; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#d95f02; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#7570b3; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e7298a;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#66a61e; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e6ab02; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a6761d; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#666666;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Dark2_8("#1b9e77", "#d95f02", "#7570b3", "#e7298a", "#66a61e", "#e6ab02", "#a6761d", "#666666"),
	/**
	 * Color scheme name "Paired3" - <span style="background-color:#a6cee3; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#1f78b4; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b2df8a; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Paired3("#a6cee3", "#1f78b4", "#b2df8a"),
	/**
	 * Color scheme name "Paired4" - <span style="background-color:#a6cee3; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#1f78b4; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b2df8a; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#33a02c;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Paired4("#a6cee3", "#1f78b4", "#b2df8a", "#33a02c"),
	/**
	 * Color scheme name "Paired5" - <span style="background-color:#a6cee3; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#1f78b4; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b2df8a; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#33a02c;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#fb9a99; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Paired5("#a6cee3", "#1f78b4", "#b2df8a", "#33a02c", "#fb9a99"),
	/**
	 * Color scheme name "Paired6" - <span style="background-color:#a6cee3; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#1f78b4; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b2df8a; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#33a02c;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#fb9a99; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e31a1c; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Paired6("#a6cee3", "#1f78b4", "#b2df8a", "#33a02c", "#fb9a99", "#e31a1c"),
	/**
	 * Color scheme name "Paired7" - <span style="background-color:#a6cee3; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#1f78b4; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b2df8a; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#33a02c;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#fb9a99; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e31a1c; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fdbf6f; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Paired7("#a6cee3", "#1f78b4", "#b2df8a", "#33a02c", "#fb9a99", "#e31a1c", "#fdbf6f"),
	/**
	 * Color scheme name "Paired8" - <span style="background-color:#a6cee3; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#1f78b4; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b2df8a; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#33a02c;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#fb9a99; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e31a1c; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fdbf6f; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ff7f00;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Paired8("#a6cee3", "#1f78b4", "#b2df8a", "#33a02c", "#fb9a99", "#e31a1c", "#fdbf6f", "#ff7f00"),
	/**
	 * Color scheme name "Paired9" - <span style="background-color:#a6cee3; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#1f78b4; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b2df8a; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#33a02c;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#fb9a99; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e31a1c; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fdbf6f; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ff7f00;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#cab2d6; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Paired9("#a6cee3", "#1f78b4", "#b2df8a", "#33a02c", "#fb9a99", "#e31a1c", "#fdbf6f", "#ff7f00", "#cab2d6"),
	/**
	 * Color scheme name "Paired10" - <span style="background-color:#a6cee3; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#1f78b4; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b2df8a; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#33a02c;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#fb9a99; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e31a1c; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fdbf6f; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ff7f00;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#cab2d6; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#6a3d9a; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Paired10("#a6cee3", "#1f78b4", "#b2df8a", "#33a02c", "#fb9a99", "#e31a1c", "#fdbf6f", "#ff7f00", "#cab2d6", "#6a3d9a"),
	/**
	 * Color scheme name "Paired11" - <span style="background-color:#a6cee3; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#1f78b4; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b2df8a; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#33a02c;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#fb9a99; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e31a1c; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fdbf6f; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ff7f00;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#cab2d6; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#6a3d9a; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffff99; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Paired11("#a6cee3", "#1f78b4", "#b2df8a", "#33a02c", "#fb9a99", "#e31a1c", "#fdbf6f", "#ff7f00", "#cab2d6", "#6a3d9a", "#ffff99"),
	/**
	 * Color scheme name "Paired12" - <span style="background-color:#a6cee3; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#1f78b4; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b2df8a; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#33a02c;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#fb9a99; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e31a1c; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fdbf6f; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ff7f00;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#cab2d6; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#6a3d9a; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffff99; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b15928;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Paired12("#a6cee3", "#1f78b4", "#b2df8a", "#33a02c", "#fb9a99", "#e31a1c", "#fdbf6f", "#ff7f00", "#cab2d6", "#6a3d9a", "#ffff99", "#b15928"),
	/**
	 * Color scheme name "Pastel1_3" - <span style="background-color:#fbb4ae; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b3cde3; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ccebc5; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Pastel1_3("#fbb4ae", "#b3cde3", "#ccebc5"),
	/**
	 * Color scheme name "Pastel1_4" - <span style="background-color:#fbb4ae; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b3cde3; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ccebc5; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#decbe4;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Pastel1_4("#fbb4ae", "#b3cde3", "#ccebc5", "#decbe4"),
	/**
	 * Color scheme name "Pastel1_5" - <span style="background-color:#fbb4ae; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b3cde3; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ccebc5; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#decbe4;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#fed9a6; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Pastel1_5("#fbb4ae", "#b3cde3", "#ccebc5", "#decbe4", "#fed9a6"),
	/**
	 * Color scheme name "Pastel1_6" - <span style="background-color:#fbb4ae; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b3cde3; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ccebc5; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#decbe4;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#fed9a6; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffffcc; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Pastel1_6("#fbb4ae", "#b3cde3", "#ccebc5", "#decbe4", "#fed9a6", "#ffffcc"),
	/**
	 * Color scheme name "Pastel1_7" - <span style="background-color:#fbb4ae; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b3cde3; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ccebc5; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#decbe4;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#fed9a6; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffffcc; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e5d8bd; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Pastel1_7("#fbb4ae", "#b3cde3", "#ccebc5", "#decbe4", "#fed9a6", "#ffffcc", "#e5d8bd"),
	/**
	 * Color scheme name "Pastel1_8" - <span style="background-color:#fbb4ae; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b3cde3; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ccebc5; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#decbe4;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#fed9a6; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffffcc; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e5d8bd; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fddaec;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Pastel1_8("#fbb4ae", "#b3cde3", "#ccebc5", "#decbe4", "#fed9a6", "#ffffcc", "#e5d8bd", "#fddaec"),
	/**
	 * Color scheme name "Pastel1_9" - <span style="background-color:#fbb4ae; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b3cde3; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ccebc5; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#decbe4;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#fed9a6; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffffcc; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e5d8bd; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fddaec;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#f2f2f2; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Pastel1_9("#fbb4ae", "#b3cde3", "#ccebc5", "#decbe4", "#fed9a6", "#ffffcc", "#e5d8bd", "#fddaec", "#f2f2f2"),
	/**
	 * Color scheme name "Pastel2_3" - <span style="background-color:#b3e2cd; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fdcdac; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#cbd5e8; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Pastel2_3("#b3e2cd", "#fdcdac", "#cbd5e8"),
	/**
	 * Color scheme name "Pastel2_4" - <span style="background-color:#b3e2cd; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fdcdac; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#cbd5e8; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f4cae4;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Pastel2_4("#b3e2cd", "#fdcdac", "#cbd5e8", "#f4cae4"),
	/**
	 * Color scheme name "Pastel2_5" - <span style="background-color:#b3e2cd; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fdcdac; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#cbd5e8; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f4cae4;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#e6f5c9; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Pastel2_5("#b3e2cd", "#fdcdac", "#cbd5e8", "#f4cae4", "#e6f5c9"),
	/**
	 * Color scheme name "Pastel2_6" - <span style="background-color:#b3e2cd; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fdcdac; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#cbd5e8; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f4cae4;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#e6f5c9; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fff2ae; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Pastel2_6("#b3e2cd", "#fdcdac", "#cbd5e8", "#f4cae4", "#e6f5c9", "#fff2ae"),
	/**
	 * Color scheme name "Pastel2_7" - <span style="background-color:#b3e2cd; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fdcdac; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#cbd5e8; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f4cae4;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#e6f5c9; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fff2ae; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f1e2cc; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Pastel2_7("#b3e2cd", "#fdcdac", "#cbd5e8", "#f4cae4", "#e6f5c9", "#fff2ae", "#f1e2cc"),
	/**
	 * Color scheme name "Pastel2_8" - <span style="background-color:#b3e2cd; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fdcdac; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#cbd5e8; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f4cae4;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#e6f5c9; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fff2ae; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f1e2cc; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#cccccc;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Pastel2_8("#b3e2cd", "#fdcdac", "#cbd5e8", "#f4cae4", "#e6f5c9", "#fff2ae", "#f1e2cc", "#cccccc"),
	/**
	 * Color scheme name "Set1_3" - <span style="background-color:#e41a1c; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#377eb8; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#4daf4a; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Set1_3("#e41a1c", "#377eb8", "#4daf4a"),
	/**
	 * Color scheme name "Set1_4" - <span style="background-color:#e41a1c; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#377eb8; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#4daf4a; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#984ea3;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Set1_4("#e41a1c", "#377eb8", "#4daf4a", "#984ea3"),
	/**
	 * Color scheme name "Set1_5" - <span style="background-color:#e41a1c; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#377eb8; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#4daf4a; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#984ea3;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#ff7f00; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Set1_5("#e41a1c", "#377eb8", "#4daf4a", "#984ea3", "#ff7f00"),
	/**
	 * Color scheme name "Set1_6" - <span style="background-color:#e41a1c; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#377eb8; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#4daf4a; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#984ea3;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#ff7f00; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffff33; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Set1_6("#e41a1c", "#377eb8", "#4daf4a", "#984ea3", "#ff7f00", "#ffff33"),
	/**
	 * Color scheme name "Set1_7" - <span style="background-color:#e41a1c; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#377eb8; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#4daf4a; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#984ea3;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#ff7f00; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffff33; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a65628; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Set1_7("#e41a1c", "#377eb8", "#4daf4a", "#984ea3", "#ff7f00", "#ffff33", "#a65628"),
	/**
	 * Color scheme name "Set1_8" - <span style="background-color:#e41a1c; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#377eb8; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#4daf4a; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#984ea3;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#ff7f00; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffff33; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a65628; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f781bf;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Set1_8("#e41a1c", "#377eb8", "#4daf4a", "#984ea3", "#ff7f00", "#ffff33", "#a65628", "#f781bf"),
	/**
	 * Color scheme name "Set1_9" - <span style="background-color:#e41a1c; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#377eb8; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#4daf4a; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#984ea3;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#ff7f00; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffff33; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#a65628; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#f781bf;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#999999; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Set1_9("#e41a1c", "#377eb8", "#4daf4a", "#984ea3", "#ff7f00", "#ffff33", "#a65628", "#f781bf", "#999999"),
	/**
	 * Color scheme name "Set2_3" - <span style="background-color:#66c2a5; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fc8d62; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#8da0cb; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Set2_3("#66c2a5", "#fc8d62", "#8da0cb"),
	/**
	 * Color scheme name "Set2_4" - <span style="background-color:#66c2a5; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fc8d62; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#8da0cb; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e78ac3;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Set2_4("#66c2a5", "#fc8d62", "#8da0cb", "#e78ac3"),
	/**
	 * Color scheme name "Set2_5" - <span style="background-color:#66c2a5; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fc8d62; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#8da0cb; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e78ac3;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#a6d854; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Set2_5("#66c2a5", "#fc8d62", "#8da0cb", "#e78ac3", "#a6d854"),
	/**
	 * Color scheme name "Set2_6" - <span style="background-color:#66c2a5; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fc8d62; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#8da0cb; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e78ac3;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#a6d854; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffd92f; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Set2_6("#66c2a5", "#fc8d62", "#8da0cb", "#e78ac3", "#a6d854", "#ffd92f"),
	/**
	 * Color scheme name "Set2_7" - <span style="background-color:#66c2a5; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fc8d62; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#8da0cb; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e78ac3;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#a6d854; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffd92f; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e5c494; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Set2_7("#66c2a5", "#fc8d62", "#8da0cb", "#e78ac3", "#a6d854", "#ffd92f", "#e5c494"),
	/**
	 * Color scheme name "Set2_8" - <span style="background-color:#66c2a5; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fc8d62; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#8da0cb; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e78ac3;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#a6d854; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffd92f; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#e5c494; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b3b3b3;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Set2_8("#66c2a5", "#fc8d62", "#8da0cb", "#e78ac3", "#a6d854", "#ffd92f", "#e5c494", "#b3b3b3"),
	/**
	 * Color scheme name "Set3_3" - <span style="background-color:#8dd3c7; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffffb3; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#bebada; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Set3_3("#8dd3c7", "#ffffb3", "#bebada"),
	/**
	 * Color scheme name "Set3_4" - <span style="background-color:#8dd3c7; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffffb3; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#bebada; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fb8072;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Set3_4("#8dd3c7", "#ffffb3", "#bebada", "#fb8072"),
	/**
	 * Color scheme name "Set3_5" - <span style="background-color:#8dd3c7; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffffb3; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#bebada; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fb8072;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#80b1d3; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Set3_5("#8dd3c7", "#ffffb3", "#bebada", "#fb8072", "#80b1d3"),
	/**
	 * Color scheme name "Set3_6" - <span style="background-color:#8dd3c7; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffffb3; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#bebada; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fb8072;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#80b1d3; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fdb462; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Set3_6("#8dd3c7", "#ffffb3", "#bebada", "#fb8072", "#80b1d3", "#fdb462"),
	/**
	 * Color scheme name "Set3_7" - <span style="background-color:#8dd3c7; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffffb3; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#bebada; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fb8072;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#80b1d3; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fdb462; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b3de69; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Set3_7("#8dd3c7", "#ffffb3", "#bebada", "#fb8072", "#80b1d3", "#fdb462", "#b3de69"),
	/**
	 * Color scheme name "Set3_8" - <span style="background-color:#8dd3c7; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffffb3; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#bebada; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fb8072;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#80b1d3; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fdb462; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b3de69; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fccde5;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Set3_8("#8dd3c7", "#ffffb3", "#bebada", "#fb8072", "#80b1d3", "#fdb462", "#b3de69", "#fccde5"),
	/**
	 * Color scheme name "Set3_9" - <span style="background-color:#8dd3c7; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffffb3; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#bebada; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fb8072;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#80b1d3; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fdb462; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b3de69; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fccde5;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#d9d9d9; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Set3_9("#8dd3c7", "#ffffb3", "#bebada", "#fb8072", "#80b1d3", "#fdb462", "#b3de69", "#fccde5", "#d9d9d9"),
	/**
	 * Color scheme name "Set3_10" - <span style="background-color:#8dd3c7; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffffb3; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#bebada; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fb8072;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#80b1d3; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fdb462; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b3de69; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fccde5;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#d9d9d9; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#bc80bd; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Set3_10("#8dd3c7", "#ffffb3", "#bebada", "#fb8072", "#80b1d3", "#fdb462", "#b3de69", "#fccde5", "#d9d9d9", "#bc80bd"),
	/**
	 * Color scheme name "Set3_11" - <span style="background-color:#8dd3c7; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffffb3; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#bebada; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fb8072;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#80b1d3; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fdb462; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b3de69; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fccde5;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#d9d9d9; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#bc80bd; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ccebc5; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Set3_11("#8dd3c7", "#ffffb3", "#bebada", "#fb8072", "#80b1d3", "#fdb462", "#b3de69", "#fccde5", "#d9d9d9", "#bc80bd", "#ccebc5"),
	/**
	 * Color scheme name "Set3_12" - <span style="background-color:#8dd3c7; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffffb3; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#bebada; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fb8072;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#80b1d3; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fdb462; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#b3de69; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#fccde5;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=
	 * "background-color:#d9d9d9; border-style: solid; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#bc80bd; border-style: solid;
	 * border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ccebc5; border-style:
	 * solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="background-color:#ffed6f;
	 * border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	Set3_12("#8dd3c7", "#ffffb3", "#bebada", "#fb8072", "#80b1d3", "#fdb462", "#b3de69", "#fccde5", "#d9d9d9", "#bc80bd", "#ccebc5", "#ffed6f");

	// Category name used to build the label to configure plugin.
	private static final String CATEGORY = "brewer";
	// list of colors for each scheme
	private final List<IsColor> colors = new LinkedList<>();

	/**
	 * Builds a scheme using argument as list of colors in HEX format.
	 * 
	 * @param hexColors list of colors in HEX format
	 */
	private BrewerScheme(String... hexColors) {
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
