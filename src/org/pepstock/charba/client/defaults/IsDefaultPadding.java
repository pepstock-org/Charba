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

import org.pepstock.charba.client.items.PaddingItem;

/**
 * Interface to define padding object defaults.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface IsDefaultPadding {

	/**
	 * Returns the padding left in pixel.
	 * 
	 * @return the padding left in pixel.
	 */
	int getLeft();

	/**
	 * Returns the padding right in pixel.
	 * 
	 * @return the padding right in pixel.
	 */
	int getRight();

	/**
	 * Returns the padding top in pixel.
	 * 
	 * @return the padding top in pixel.
	 */
	int getTop();

	/**
	 * Returns the padding bottom in pixel.
	 * 
	 * @return the padding bottom in pixel.
	 */
	int getBottom();
	
	/**
	 * Creates a padding options instance using default or cloning current instance.
	 * 
	 * @return a padding options instance using default or cloning current instance
	 */
	default PaddingItem create() {
		return create(null);
	}
	
	/**
	 * Creates a padding options instance using default or cloning current instance.
	 * 
	 * @param defaultValues default provider
	 * @return a padding options instance using default or cloning current instance
	 */
	default PaddingItem create(IsDefaultPadding defaultValues) {
		// creates new padding item
		PaddingItem result = new PaddingItem(defaultValues);
		// sets left
		result.setLeft(Math.max(getLeft(), 0));
		// sets right
		result.setRight(Math.max(getRight(), 0));
		// sets top
		result.setTop(Math.max(getTop(), 0));
		// sets bottom
		result.setBottom(Math.max(getBottom(), 0));
		// returns padding item
		return result;
	}

}