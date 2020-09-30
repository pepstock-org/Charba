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

import org.pepstock.charba.client.enums.DefaultPluginId;
import org.pepstock.charba.client.enums.ElementAlign;
import org.pepstock.charba.client.enums.Position;

/**
 * Interface to define the common properties of chart element related to default plugins, {@link DefaultPluginId#TITLE} and {@link DefaultPluginId#LEGEND}.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface IsDefaultPluginElement {

	/**
	 * Returns if the element is shown.
	 * 
	 * @return if the element is shown.
	 */
	boolean isDisplay();

	/**
	 * Returns the position of element.
	 * 
	 * @return the position of element.
	 */
	Position getPosition();

	/**
	 * Returns the alignment of the element.
	 * 
	 * @return alignment of the element.
	 */
	ElementAlign getAlign();

}