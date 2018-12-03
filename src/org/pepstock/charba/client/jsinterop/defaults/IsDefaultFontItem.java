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
package org.pepstock.charba.client.jsinterop.defaults;

import org.pepstock.charba.client.enums.FontStyle;

/**
 * Interface for elements object defaults.
 * 
 * @author Andrea "Stock" Stocchero
 * @version 2.0
 */
public interface IsDefaultFontItem {
	
	/**
	 * Returns the font color
	 * 
	 * @return Font color
	 */
	String getFontColorAsString();

	/**
	 * Returns the font size.
	 * 
	 * @return the font size.
	 */
	int getFontSize();

	/**
	 * Returns the font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @return the font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit). 
	 */
	FontStyle getFontStyle();

	/**
	 * Returns the font family, follows CSS font-family options.
	 * 
	 * @return Font family, follows CSS font-family options.
	 */
	String getFontFamily();
}
