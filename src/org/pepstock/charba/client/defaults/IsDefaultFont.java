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
package org.pepstock.charba.client.defaults;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.enums.Weight;
import org.pepstock.charba.client.items.FontItem;
import org.pepstock.charba.client.items.Undefined;

/**
 * Interface to define fomt object defaults.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface IsDefaultFont {

	/**
	 * Returns the font size.
	 * 
	 * @return the font size.
	 */
	int getSize();

	/**
	 * Returns the font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @return the font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 */
	FontStyle getStyle();

	/**
	 * Returns the font family, follows CSS font-family options.
	 * 
	 * @return Font family, follows CSS font-family options.
	 */
	String getFamily();

	/**
	 * Returns the font weight, follows CSS font-style-weight options.
	 * 
	 * @return the font weight, follows CSS font-style-weight options.
	 */
	Weight getWeight();

	/**
	 * Returns the height of an individual line of text.
	 * 
	 * @return the height of an individual line of text.
	 */
	double getLineHeight();

	/**
	 * Returns the height of an individual line of text as string.
	 * 
	 * @return the height of an individual line of text as string.
	 */
	String getLineHeightAsString();
	
	/**
	 * Creates a font options instance using default or cloning current instance.
	 * 
	 * @return a font options instance using default or cloning current instance
	 */
	default FontItem create() {
		return create(Defaults.get().getGlobal().getFont());
	}
	
	/**
	 * Creates a font options instance using default or cloning current instance.
	 * 
	 * @param defaultValues default provider
	 * @return a font options instance using default or cloning current instance
	 */
	default FontItem create(IsDefaultFont defaultValues) {
		// creates new font item
		FontItem result = new FontItem(defaultValues);
		// gets size reference
		int size = getSize();
		// sets size checking if consistent
		if (size != Undefined.INTEGER) {
			result.setSize(size);
		}
		// stores style, family and weight
		result.setStyle(getStyle());
		result.setFamily(getFamily());
		result.setWeight(getWeight());
		// gets line height reference
		String lineHeightAsSttring = getLineHeightAsString();
		// sets line height checking if consistent
		if (lineHeightAsSttring != null) {
			result.setLineHeight(lineHeightAsSttring);
		} else {
			// if here the line height could a number
			// gets line height reference
			double lineHeight = getLineHeight();
			// sets line height checking if consistent
			if (!Double.isNaN(lineHeight)) {
				result.setLineHeight(lineHeight);
			}
		}
		// returns font item
		return result;
	}

}
