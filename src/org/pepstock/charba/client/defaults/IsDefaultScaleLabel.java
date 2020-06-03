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

import org.pepstock.charba.client.enums.ScaleLabelAlign;

/**
 * Interface to define scale label object defaults.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface IsDefaultScaleLabel extends IsDefaultFontItem {

	/**
	 * Returns the padding defaults.
	 * 
	 * @return the padding defaults.
	 */
	IsDefaultPadding getPadding();

	/**
	 * Returns if the title is shown.
	 * 
	 * @return if the title is shown.
	 */
	boolean isDisplay();

	/**
	 * Returns the text for the scale string.
	 * 
	 * @return The text for the scale string.
	 */
	String getLabelString();
	
	/**
	 * Returns the alignment of the axis title.
	 * 
	 * @return the alignment of the axis title.
	 */
	ScaleLabelAlign getAlign();

	/**
	 * Returns the height of an individual line of text.
	 * 
	 * @return the height of an individual line of text.
	 */
	double getLineHeight();

}