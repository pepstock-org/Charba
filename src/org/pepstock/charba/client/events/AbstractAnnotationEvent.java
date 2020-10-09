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
package org.pepstock.charba.client.events;

import org.pepstock.charba.client.annotation.AbstractAnnotation;
import org.pepstock.charba.client.annotation.Annotation;
import org.pepstock.charba.client.dom.BaseNativeEvent;

/**
 * Base event for all events related to an annotation, by {@link Annotation#ID} plugin.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
abstract class AbstractAnnotationEvent extends AbstractEvent {

	// annotation instance
	private final AbstractAnnotation annotation;

	/**
	 * Creates the event with an annotation instance, origin of event.
	 * 
	 * @param nativeEvent native event of this custom event
	 * @param type type of event
	 * @param annotation annotation instance, origin of event
	 */
	AbstractAnnotationEvent(BaseNativeEvent nativeEvent, EventType type, AbstractAnnotation annotation) {
		super(nativeEvent, type);
		// checks if arguments are consistent
		if (annotation == null) {
			throw new IllegalArgumentException("Annotation argument is null");
		}
		// stores annotation
		this.annotation = annotation;
	}

	/**
	 * Returns the annotation instance, origin of event.
	 * 
	 * @return the starting value of selected scale
	 */
	public final AbstractAnnotation getAnnotation() {
		return annotation;
	}

}