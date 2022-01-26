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

import java.util.HashMap;
import java.util.Map;

import org.pepstock.charba.client.commons.Key;

/**
 * Object which stores all annotations by their ID in the {@link AnnotationPlugin#ID} plugin, using a cached to store it.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class AnnotationCachedMap extends AnnotationMap {

	// map to use as cache for the annotations added to the options
	private final Map<String, AbstractAnnotation> annotationsCache = new HashMap<>();

	/**
	 * Creates an empty object with the cache for the annotations.
	 */
	AnnotationCachedMap() {
		super();
	}

	/**
	 * Returns <code>true</code> if the annotation with the id passed as argument exists.
	 * 
	 * @param id annotation id to check
	 * @return <code>true</code> if the annotation with the id passed as argument exists
	 */
	@Override
	boolean hasAnnotation(AnnotationId id) {
		return super.hasAnnotation(id) && annotationsCache.containsKey(id.value());
	}

	/**
	 * Removes the annotation by the id passed as argument, if exists.
	 * 
	 * @param id annotation id to check
	 */
	@Override
	void removeAnnotation(AnnotationId id) {
		// invokes super
		super.removeAnnotation(id);
		// removes from cache if the annotation id exist
		annotationsCache.remove(id.value());
	}

	/**
	 * Adds an annotations for plugin.
	 * 
	 * @param annotations set of annotations.
	 */
	@Override
	void addAnnotations(AbstractAnnotation... annotations) {
		// invokes super
		super.addAnnotations(annotations);
		// checks if array argument is consistent
		if (annotations != null && annotations.length > 0) {
			// scans all arguments
			for (AbstractAnnotation annotation : annotations) {
				// adds annotation
				AnnotationId id = annotation.getId();
				// stores in the java script object
				annotationsCache.put(id.value(), annotation);
			}
		}
	}

	/**
	 * Clears all stored annotations
	 */
	@Override
	void clear() {
		// invokes super
		super.clear();
		// clears the cache
		annotationsCache.clear();
	}

	/**
	 * Gets the stored annotation from the cache.
	 * 
	 * @param id id of stored annotation
	 * @return an annotation instance or <code>null</code> if not stored.
	 */
	@Override
	AbstractAnnotation getAndCreateAnnotation(Key id) {
		return annotationsCache.get(Key.checkAndGetIfValid(id).value());
	}
}
