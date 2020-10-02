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

import org.pepstock.charba.client.annotation.callbacks.ClickCallback;
import org.pepstock.charba.client.annotation.callbacks.EnterCallback;
import org.pepstock.charba.client.annotation.callbacks.LeaveCallback;
import org.pepstock.charba.client.annotation.enums.DrawTime;

/**
 * This is the {@link Annotation#ID} plugin annotation DEFAULTS options interface in order to map the common attributes for all annotations.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
interface IsDefaultsAnnotation {

	/**
	 * Returns the type of annotation.
	 * 
	 * @return the type of annotation
	 */
	AnnotationType getType();

	/**
	 * Returns <code>true</code> whether the annotation is enabled and should be displayed.
	 * 
	 * @return <code>true</code> whether the annotation is enabled and should be displayed
	 */
	default boolean isEnabled() {
		return AbstractAnnotation.DEFAULT_ENABLED;
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

	/**
	 * Returns the callback called when a mouse event is occurring, entering on annotation element.
	 * 
	 * @return the callback called when a mouse event is occurring, entering on annotation element
	 */
	default EnterCallback getEnterCallback() {
		return null;
	}

	/**
	 * Returns the callback called when a mouse event is occurring, leaving the annotation element.
	 * 
	 * @return the callback called when a mouse event is occurring, leaving the annotation element
	 */
	default LeaveCallback getLeaveCallback() {
		return null;
	}

	/**
	 * Returns the callback called when a mouse event is occurring, clicking on the annotation element.
	 * 
	 * @return the callback called when a mouse event is occurring, clicking on the annotation element
	 */
	default ClickCallback getClickCallback() {
		return null;
	}
}
