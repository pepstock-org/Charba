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

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.commons.Key;
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
	 * Adds an annotation configuration in the the cache.
	 * 
	 * @param annotation annotation configuration instance to store in the the cache
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

	/**
	 * Retrieves the annotation configuration instance from chart, by an annotation id, in order to get it as default of another annotation object.
	 * 
	 * @param type annotation type of the default annotation object
	 * @param id annotation id to use to get the annotation object
	 * @param chart chart instance to search the annotation object
	 * @return the annotation configuration related to the id passed as argument
	 */
	IsDefaultsAnnotation getDefaultsAnnotationOptionsByChart(AnnotationType type, AnnotationId id, IsChart chart) {
		// checks annotation type
		// if not exception
		Key.checkIfValid(type);
		// checks if annotation type is consistent
		if (IsChart.isConsistent(chart) && AnnotationId.isValid(id)) {
			// gets result, inspecting the chart options
			return inspectChartToGetAnnotation(type, id, chart.getDefaultChartOptions().getPlugins().getOptions(AnnotationPlugin.ID, AnnotationPlugin.FACTORY));
		}
		// ig here, the chart is not consistent
		// then returns the default for the annotation type
		return type.getDefaultsValues();
	}

	/**
	 * Retrieves the annotation configuration instance from globals, by an annotation id, in order to get it as default of another annotation object.
	 * 
	 * @param type annotation type of the default annotation object
	 * @param id annotation id to use to get the annotation object
	 * @return the annotation configuration related to the id passed as argument
	 */
	IsDefaultsAnnotation getDefaultsAnnotationOptionsByGlobal(AnnotationType type, AnnotationId id) {
		// checks annotation type
		// if not exception
		Key.checkIfValid(type);
		// gets result, inspecting the global options
		return inspectChartToGetAnnotation(type, id, Defaults.get().getGlobal().getPlugins().getOptions(AnnotationPlugin.ID, AnnotationPlugin.FACTORY));
	}

	/**
	 * Retrieves the annotation configuration instance, inspecting an annotation options, in order to get it as default of another annotation object.
	 * 
	 * @param type annotation type of the default annotation object
	 * @param id annotation id to use to get the annotation object
	 * @param options annotation options instance to inspect in order to retrieve the annotation object
	 * @return the annotation configuration related to the id passed as argument
	 */
	private IsDefaultsAnnotation inspectChartToGetAnnotation(AnnotationType type, AnnotationId id, AnnotationOptions options) {
		// checks if annotation id and options are consistent
		if (AnnotationId.isValid(id) && options != null && options.hasAnnotation(id)) {
			// stores the result
			IsDefaultsAnnotation result = options.getAnnotation(id);
			// checks if the result type and type passed as argument
			// must be the same otherwise the default will be return
			if (type.equals(result.getType())) {
				return result;
			}
		}
		// if here, something is not consistent
		// then returns the defaults
		return type.getDefaultsValues();
	}
}