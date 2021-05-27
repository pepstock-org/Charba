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

import java.util.Collections;
import java.util.List;

import org.pepstock.charba.client.annotation.enums.DrawTime;

/**
 * This is the {@link AnnotationPlugin#ID} plugin DEFAULTS options interface, in order to define the attributes of main annotations options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
interface IsDefaultsAnnotationOptions {

	/**
	 * Returns the double-click speed in milliseconds used to distinguish single-clicks from double-clicks whenever you need to capture both.
	 * 
	 * @return the double-click speed in milliseconds
	 */
	default int getDoubleClickSpeed() {
		return AnnotationOptions.DEFAULT_DOUBLE_CLICK_SPEED;
	}

	/**
	 * Returns the draw time which defines when the annotations are drawn.
	 * 
	 * @return the draw time which defines when the annotations are drawn
	 */
	default DrawTime getDrawTime() {
		return AnnotationOptions.DEFAULT_DRAW_TIME;
	}

	/**
	 * Returns <code>true</code> if the annotation with the id passed as argument exists.
	 * 
	 * @param id annotation id to check
	 * @return <code>true</code> if the annotation with the id passed as argument exists
	 */
	default boolean hasAnnotation(AnnotationId id) {
		return false;
	}

	/**
	 * Returns the list of annotations.
	 * 
	 * @return the list of annotations
	 */
	default List<AbstractAnnotation> getAnnotations() {
		return Collections.emptyList();
	}

	/**
	 * Returns the annotation with the id passed as argument or <code>null</code> if not exist.
	 * 
	 * @param id annotation id to use to retrieve the annotation
	 * @return the annotation or <code>null</code> if not exist
	 */
	default AbstractAnnotation getAnnotation(AnnotationId id) {
		return null;
	}

}
