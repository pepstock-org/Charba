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

/**
 * Interface to define arc object defaults.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface IsDefaultOptionsElement {

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
	 * Returns the background color when hovered.
	 * 
	 * @return the background color when hovered.
	 */
	String getHoverBackgroundColorAsString();
	
	/**
	 * Returns the border width when hovered.
	 * 
	 * @return the border width when hovered.
	 */
	int getHoverBorderWidth();
	
	/**
	 * Returns the border color when hovered.
	 * 
	 * @return the border color when hovered.
	 */
	String getHoverBorderColorAsString();
}
