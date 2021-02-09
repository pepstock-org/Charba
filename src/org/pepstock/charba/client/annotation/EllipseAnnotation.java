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

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.commons.NativeObject;

/**
 * Implements a ELLIPSE annotation which draws a ellipse into a chart.<br>
 * If one of the axes is not specified, the ellipse will take the entire chart dimension.<br>
 * The 4 coordinates, xMin, xMax, yMin, yMax are optional. If not specified, the ellipse is expanded out to the edges.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class EllipseAnnotation extends AbstractBoxAnnotation implements IsDefaultsEllipseAnnotation {

	/**
	 * Creates a ellipse annotation to be added to an {@link AnnotationOptions} instance.<br>
	 * The annotation id is calculated automatically.
	 * 
	 * @see AnnotationType#createId()
	 */
	public EllipseAnnotation() {
		this(AnnotationType.ELLIPSE.createId(), AnnotationType.ELLIPSE.getDefaultsValues());
	}

	/**
	 * Creates a ellipse annotation to be added to an {@link AnnotationOptions} instance, using the ID passed as argument.
	 * 
	 * @param id annotation id to apply to the object, as string
	 */
	public EllipseAnnotation(String id) {
		this(IsAnnotationId.create(id));
	}

	/**
	 * Creates a ellipse annotation to be added to an {@link AnnotationOptions} instance, using the ID passed as argument.
	 * 
	 * @param id annotation id to apply to the object
	 */
	public EllipseAnnotation(IsAnnotationId id) {
		this(id, AnnotationHelper.get().getDefaultsAnnotationOptionsByGlobal(AnnotationType.ELLIPSE, id));
	}

	/**
	 * Creates a ellipse annotation to be added to an {@link AnnotationOptions} instance, using the ID passed as argument.<br>
	 * The chart instance, passed as argument, must be the chart where the annotations will be applied and is used to get the whole default options in order to get the default for
	 * this object.
	 * 
	 * @param id annotation id to apply to the object, as string
	 * @param chart chart instance related to the plugin options
	 */
	public EllipseAnnotation(String id, IsChart chart) {
		this(IsAnnotationId.create(id), chart);
	}

	/**
	 * Creates a ellipse annotation to be added to an {@link AnnotationOptions} instance, using the ID passed as argument.<br>
	 * The chart instance, passed as argument, must be the chart where the annotations will be applied and is used to get the whole default options in order to get the default for
	 * this object.
	 * 
	 * @param id annotation id to apply to the object
	 * @param chart chart instance related to the plugin options
	 */
	public EllipseAnnotation(IsAnnotationId id, IsChart chart) {
		this(id, AnnotationHelper.get().getDefaultsAnnotationOptionsByChart(AnnotationType.ELLIPSE, id, chart));
	}

	/**
	 * Creates a ellipse annotation to be added to an {@link AnnotationOptions} instance, using the native object and defaults passed as argument.
	 * 
	 * @param id annotation id to apply to the object
	 * @param defaultValues default options instance
	 */
	private EllipseAnnotation(IsAnnotationId id, IsDefaultsAnnotation defaultValues) {
		super(AnnotationType.ELLIPSE, id, defaultValues);
	}

	/**
	 * Creates the object wrapping an existing native object.
	 * 
	 * @param nativeObject native object to wrap
	 * @param defaultValues default options instance
	 */
	EllipseAnnotation(NativeObject nativeObject, IsDefaultsAnnotation defaultValues) {
		super(AnnotationType.ELLIPSE, nativeObject, defaultValues);
	}
}
