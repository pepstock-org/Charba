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
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.datalabels.enums.Weight;
import org.pepstock.charba.client.enums.FontStyle;

/**
 * {@link DataLabelsPlugin#ID} plugin default options for FONT element.<br>
 * It contains all default values for FONT.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class DefaultsFont extends NativeObjectContainer {

	/**
	 * Creates the object with native object instance to be wrapped.
	 * 
	 * @param nativeObject native object instance to be wrapped.
	 */
	DefaultsFont(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns the font size.
	 * 
	 * @return the font size.
	 */
	int getFontSize() {
		return getValue(Font.Property.SIZE, Defaults.get().getGlobal().getFontSize());
	}

	/**
	 * Returns the font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @return the font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 */
	FontStyle getFontStyle() {
		return getValue(Font.Property.STYLE, FontStyle.values(), Defaults.get().getGlobal().getFontStyle());
	}

	/**
	 * Returns the font family, follows CSS font-family options.
	 * 
	 * @return Font family, follows CSS font-family options.
	 */
	String getFontFamily() {
		return getValue(Font.Property.FAMILY, Defaults.get().getGlobal().getFontFamily());
	}

	/**
	 * Returns the font weight, follows CSS font-style-weight options.
	 * 
	 * @return the font weight, follows CSS font-style-weight options.
	 */
	Weight getWeight() {
		return getValue(Font.Property.WEIGHT, Weight.values(), Font.DEFAULT_WEIGHT);
	}

	/**
	 * Returns the line height.
	 * 
	 * @return the line height.
	 */
	double getLineHeight() {
		return getValue(Font.Property.LINE_HEIGHT, Font.DEFAULT_LINE_HEIGHT);
	}

}
