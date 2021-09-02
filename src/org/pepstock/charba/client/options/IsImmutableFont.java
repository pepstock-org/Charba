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
package org.pepstock.charba.client.options;

import org.pepstock.charba.client.Helpers;
import org.pepstock.charba.client.defaults.IsDefaultFont;

/**
 * Interface to map a font element in read-only mode, normalized by CHART.JS by {@link Helpers#toFont(org.pepstock.charba.client.items.FontItem)}, providing also the CSS string of font itself.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface IsImmutableFont extends IsDefaultFont {

	/**
	 * Builds the font string (shorthand property of CSS font) to use in the canvas object.<br>
	 * See <a href="https://www.w3schools.com/tags/canvas_font.asp">here</a> CSS specification.
	 * 
	 * @return the font string to use in the canvas object.
	 */
	String toCSSString();

}
