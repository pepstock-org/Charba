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

import org.pepstock.charba.client.annotation.callbacks.LabelAlignPositionCallback;

/**
 * This is the {@link AnnotationPlugin#ID} plugin LABEL annotation DEFAULTS options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
interface IsDefaultsLabelAnnotation extends IsDefaultsAbstractPointedAnnotation, IsDefaultsLabelHandler, IsDefaultsBorderRadiusHandler, IsDefaultsExtendedBorderOptionsHandler, IsDefaultsRotationHandler, IsDefaultsTextStrokeOptionsHandler {

	/**
	 * Returns the callout node.
	 * 
	 * @return the callout node
	 */
	IsDefaultsCallout getCallout();

	/**
	 * Returns the anchor position of label in the box.
	 * 
	 * @return the anchor position of label in the box
	 */
	IsDefaultsAlignPosition getPosition();

	// ----------------
	// CALLBACKS
	// ----------------

	/**
	 * Returns the callback called to set the anchor position of label in the box.
	 * 
	 * @return the callback called to set the anchor position of label in the box
	 */
	default LabelAlignPositionCallback getPositionCallback() {
		return null;
	}
}
