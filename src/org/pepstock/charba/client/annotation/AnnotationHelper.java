/**
    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.
*/
package org.pepstock.charba.client.annotation;

import java.util.HashMap;
import java.util.Map;

import org.pepstock.charba.client.items.Undefined;

/**
 * Helpers to provides utilities in the {@link AnnotationPlugin#ID} plugin.
 * 
 * @author Andrea "Stock" Stocchero
 */
final class AnnotationHelper {

	// singleton instance
	private static final AnnotationHelper INSTANCE = new AnnotationHelper();
	// map to maintain all annotation instances, acts as a cache
	private final Map<Integer, AbstractAnnotation> annotationInstancesCache = new HashMap<>();

	/**
	 * To avoid any instantiation
	 */
	private AnnotationHelper() {
		// do nothing
	}

	/**
	 * Returns the singleton instance of helper.
	 * 
	 * @return the singleton instance of plugin
	 */
	static AnnotationHelper get() {
		return INSTANCE;
	}

	/**
	 * Adds an annotation configuration in the cache.
	 * 
	 * @param annotation annotation configuration instance to store in the cache
	 */
	void addAnnotation(AbstractAnnotation annotation) {
		// checks if annotation argument is consistent
		if (annotation != null && Undefined.isNot(annotation.getAnnotationId())) {
			// stores the annotation configuration
			annotationInstancesCache.put(annotation.getAnnotationId(), annotation);
		}
	}

	/**
	 * Retrieves a cached annotation configuration item, previously stored.
	 * 
	 * @param annotationId annotation id to use to get the annotation configuration item
	 * @return a cached annotation configuration item or <code>null</code> if not exist
	 */
	AbstractAnnotation getAnnotation(int annotationId) {
		return annotationInstancesCache.get(annotationId);
	}

}