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

import org.pepstock.charba.client.callbacks.CornerRadiusCallback;

/**
 * This is the {@link AnnotationPlugin#ID} plugin BOX annotation DEFAULTS options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
interface IsDefaultsBoxAnnotation extends IsDefaultsXYAnnotation, IsDefaultsBackgroundColorHandler {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.annotation.IsDefaultsAnnotation#getType()
	 */
	@Override
	default AnnotationType getType() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.annotation.IsDefaultsAnnotation#getBorderWidth()
	 */
	@Override
	default int getBorderWidth() {
		return BoxAnnotation.DEFAULT_BORDER_WIDTH;
	}

	/**
	 * Returns the border radius.
	 * 
	 * @return the border radius.
	 */
	default int getCornerRadius() {
		return BoxAnnotation.DEFAULT_CORNER_RADIUS;
	}

	/**
	 * Returns the callback called to set the corner radius.
	 * 
	 * @return the callback called to set the corner radius
	 */
	default CornerRadiusCallback<AnnotationContext> getCornerRadiusCallback() {
		return null;
	}

}
