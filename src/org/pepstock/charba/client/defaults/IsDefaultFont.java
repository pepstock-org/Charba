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

import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.enums.Weight;

/**
 * Interface to define fomt object defaults.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface IsDefaultFont {

	/**
	 * Returns the font color
	 * 
	 * @return Font color
	 */
	String getColorAsString();

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
	 * Returns the stroke width around the text.<br>
	 * Currently only supported by ticks.
	 * 
	 * @return the stroke width around the text
	 */
	int getLineWidth();
	
	/**
	 * Returns the color of the stroke around the text.<br>
	 * Currently only supported by ticks.
	 * 
	 * @return the color of the stroke around the text
	 */
	String getStrokeStyleAsString();
}
