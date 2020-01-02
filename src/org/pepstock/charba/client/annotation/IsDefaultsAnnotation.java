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

import org.pepstock.charba.client.annotation.enums.DrawTime;

/**
 * This is the {@link AnnotationPlugin#ID} plugin annotation DEFAULTS options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
interface IsDefaultsAnnotation {

	/**
	 * Returns the id of annotation.
	 * 
	 * @return the id of annotation
	 */
	String getId();

	/**
	 * Returns the draw time which defines when the annotations are drawn.
	 * 
	 * @return the draw time which defines when the annotations are drawn
	 */
	default DrawTime getDrawTime() {
		return AnnotationOptions.DEFAULT_DRAW_TIME;
	}

	/**
	 * Returns the color of the border of annotation.
	 * 
	 * @return the color of the border of annotation
	 */
	String getBorderColorAsString();

	/**
	 * Returns the width of the border in pixels.
	 * 
	 * @return the width of the border in pixels.
	 */
	int getBorderWidth();

}
