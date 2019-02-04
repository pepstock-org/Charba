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
package org.pepstock.charba.client.ext.datalabels;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.enums.FontStyle;

/**
 * Base object to map font options for configuration.
 * 
 * @author Andrea "Stock" Stocchero
 *
 * @param <P> parent node class
 * @param <D> defaults provider class
 */
final class DataLabelsDefaultsFont extends NativeObjectContainer {
	
	private static final Weight DEFAULT_WEIGHT = Weight.normal;
	
	private static final double DEFAULT_LINEHEIGHT = 1.2D;
	
	DataLabelsDefaultsFont() {
		super();
	}

	DataLabelsDefaultsFont(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns the font size.
	 * 
	 * @return the font size.
	 */
	int getFontSize() {
		return getValue(Font.Property.size, Defaults.get().getGlobal().getDefaultFontSize());
	}

	/**
	 * Returns the font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @return the font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 */
	FontStyle getFontStyle() {
		return getValue(Font.Property.style, FontStyle.class, Defaults.get().getGlobal().getDefaultFontStyle());
	}

	/**
	 * Returns the font family, follows CSS font-family options.
	 * 
	 * @return Font family, follows CSS font-family options.
	 */
	String getFontFamily() {
		return getValue(Font.Property.family, Defaults.get().getGlobal().getDefaultFontFamily());
	}

	/**
	 * Returns the font weight, follows CSS font-style-weight options.
	 * 
	 * @return the font weight, follows CSS font-style-weight options.
	 */
	Weight getWeight() {
		return getValue(Font.Property.weight, Weight.class, DEFAULT_WEIGHT);
	}

	/**
	 * Returns the line height.
	 * 
	 * @return the line height.
	 */
	double getLineHeight() {
		return getValue(Font.Property.lineHeight, DEFAULT_LINEHEIGHT);
	}

}
