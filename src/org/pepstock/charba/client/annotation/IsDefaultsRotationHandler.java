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

import org.pepstock.charba.client.callbacks.RotationCallback;

/**
 * This is the default options for items which provides rotation property.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
interface IsDefaultsRotationHandler {

	/**
	 * Returns the rotation in degrees.
	 * 
	 * @return the rotation in degrees
	 */
	default double getRotation() {
		return AbstractAnnotation.DEFAULT_ROTATION;
	}

	/**
	 * Returns the callback called to set the rotation in degrees.
	 * 
	 * @return the callback called to set the rotation in degrees
	 */
	default RotationCallback<AnnotationContext> getRotationCallback() {
		return null;
	}

}
