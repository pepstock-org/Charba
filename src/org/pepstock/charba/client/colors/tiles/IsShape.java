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
package org.pepstock.charba.client.colors.tiles;

import org.pepstock.charba.client.commons.Key;

/**
 * Defines a shape to draw on the tile.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public interface IsShape extends Key {
	
	/**
	 * Returns the instance of shape drawer.
	 * 
	 * @return the instance of shape drawer.
	 */
	ShapeDrawer getDrawer();
	
	/**
	 * Returns a unique key prefix for shape to use into cache.
	 * 
	 * @return a unique key prefix for shape to use into cache
	 */
	String getKeyPrefix();

}
