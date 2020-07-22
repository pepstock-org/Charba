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
package org.pepstock.charba.client.options;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.DefaultAnimationMode;

/**
 * Represents the mode to set to configure animation.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface IsAnimationMode extends Key {

	/**
	 * Returns a animation mode instance by its string value.
	 * 
	 * @param mode string value to use
	 * @return new mode instance
	 */
	static IsAnimationMode create(String mode) {
		// checks if mode as argument is a default one
		for (DefaultAnimationMode defMode : DefaultAnimationMode.values()) {
			// checks if mode is equals to default
			if (defMode.value().equals(mode)) {
				// if equals, returns the default mode
				return defMode;
			}
		}
		// if here, is not a default one
		// then creates new animation mode
		return new StandardAnimationMode(mode);
	}

}