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
package org.pepstock.charba.client.annotation;

import org.pepstock.charba.client.enums.InteractionAxis;
import org.pepstock.charba.client.enums.InteractionMode;

/**
 * Interface to define interaction object defaults on annotations.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface IsDefaultsAnnotationInteraction {

	/**
	 * Returns which the mode to engaged annotations on events.
	 * 
	 * @return which the mode to engaged annotations on events.
	 */
	default InteractionMode getMode() {
		return null;
	}

	/**
	 * If true, the hover mode only applies when the mouse position intersects an annotation.
	 * 
	 * @return if true, the hover mode only applies when the mouse position intersects an annotation.
	 */
	default boolean isIntersect() {
		return false;
	}

	/**
	 * Returns which directions are used in calculating distances.
	 * 
	 * @return define which directions are used in calculating distances.
	 */
	default InteractionAxis getAxis() {
		return null;
	}

}