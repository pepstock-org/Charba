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
package org.pepstock.charba.client.utils.toast;

import org.pepstock.charba.client.dom.enums.BorderStyle;

/**
 * Interface to define toast action object defaults.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface IsDefaultAction extends IsDefaultContentElement {

	/**
	 * Returns the background color.
	 * 
	 * @return the background color.
	 */
	String getBackgroundColorAsString();

	/**
	 * Returns the border width.
	 * 
	 * @return the border width.
	 */
	int getBorderWidth();

	/**
	 * Returns the border color.
	 * 
	 * @return the border color.
	 */
	String getBorderColorAsString();

	/**
	 * Returns the border radius (in pixels).
	 * 
	 * @return the border radius (in pixels).
	 */
	int getBorderRadius();

	/**
	 * Returns the border styles set for the action element.
	 * 
	 * @return the border styles set for the action element
	 */
	BorderStyle getBorderStyle();

}