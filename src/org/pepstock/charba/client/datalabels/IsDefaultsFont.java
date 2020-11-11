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
package org.pepstock.charba.client.datalabels;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.enums.Weight;

/**
 * FIXME
 * {@link DataLabelsPlugin#ID} plugin default options for FONT element.<br>
 * It contains all default values for FONT.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
interface IsDefaultsFont {

	/**
	 * Returns the font size.
	 * 
	 * @return the font size.
	 */
	default int getSize() {
		return Defaults.get().getGlobal().getFont().getSize();
	}

	/**
	 * Returns the font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @return the font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 */
	default FontStyle getStyle() {
		return Defaults.get().getGlobal().getFont().getStyle();
	}

	/**
	 * Returns the font family, follows CSS font-family options.
	 * 
	 * @return Font family, follows CSS font-family options.
	 */
	default String getFamily() {
		return Defaults.get().getGlobal().getFont().getFamily();
	}

	/**
	 * Returns the font weight, follows CSS font-style-weight options.
	 * 
	 * @return the font weight, follows CSS font-style-weight options.
	 */
	default Weight getWeight() {
		// FIXME checks the real default
		return Font.DEFAULT_WEIGHT;
	}

	/**
	 * Returns the line height.
	 * 
	 * @return the line height.
	 */
	default double getLineHeight() {
		// FIXME checks the real default
		return Font.DEFAULT_LINE_HEIGHT;
	}

}
