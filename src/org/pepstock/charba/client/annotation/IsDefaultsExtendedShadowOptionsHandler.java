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

import org.pepstock.charba.client.callbacks.ColorCallback;

/**
 * This is the default extended options for items which provides shadow properties.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
interface IsDefaultsExtendedShadowOptionsHandler {

	/**
	 * Returns the color of the shadow of annotation.
	 * 
	 * @return the color of the shadow of annotation
	 */
	default String getBackgroundShadowColorAsString() {
		return ExtendedShadowOptionsHandler.DEFAULT_BACKGROUND_SHADOW_COLOR_AS_STRING;
	}

	// ----------------------
	// CALLBACKS
	// ----------------------

	/**
	 * Returns the callback called to set the color of the shadow of annotation.
	 * 
	 * @return the callback called to set the color of the shadow of annotation
	 */
	default ColorCallback<AnnotationContext> getBackgroundShadowColorCallback() {
		return null;
	}
}