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

import java.util.concurrent.atomic.AtomicInteger;

import org.pepstock.charba.client.commons.Constants;
import org.pepstock.charba.client.commons.Key;

/**
 * Defines the type of annotation.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum AnnotationType implements Key
{
	/**
	 * Defines a <b>LINE</b> annotation which draws a line into a chart.
	 */
	LINE("line", LineAnnotationDefaultsOptions.INSTANCE),
	/**
	 * Defines a <b>BOX</b> annotation which draws a box into a chart.
	 */
	BOX("box", BoxAnnotationDefaultsOptions.INSTANCE),
	/**
	 * Defines a <b>ELLIPSE</b> annotation which draws an ellipse into a chart.
	 */
	ELLIPSE("ellipse", EllipseAnnotationDefaultsOptions.INSTANCE);

	// name value of property
	private final String value;
	// internal counter to create id
	private final AtomicInteger counter = new AtomicInteger(0);
	// default of annotation
	private final IsDefaultsAnnotation defaultValues;

	/**
	 * Creates an annotation type property value to use into native object.
	 * 
	 * @param value value of annotation type property name
	 * @param defaultValues default options for the specific annotation type
	 */
	private AnnotationType(String value, IsDefaultsAnnotation defaultValues) {
		this.value = value;
		this.defaultValues = defaultValues;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.commons.Key#value()
	 */
	@Override
	public String value() {
		return value;
	}

	/**
	 * Returns the default options for the specific annotation type.
	 * 
	 * @return the default options for the specific annotation type
	 */
	IsDefaultsAnnotation getDefaultsValues() {
		return defaultValues;
	}

	/**
	 * Creates a unique id for the annotation.<br>
	 * The format is:<br>
	 * <br>
	 * 
	 * <pre>
	 * <code>
	 * 	[annotationType]-[number]
	 * </code>
	 * </pre>
	 * 
	 * @return a unique id for the annotation
	 */
	public IsAnnotationId createId() {
		// gets a builder with annotation type as initial value
		StringBuilder builder = new StringBuilder(value());
		// adds minus and counter and creates an annotation id
		return IsAnnotationId.create(builder.append(Constants.MINUS).append(counter.incrementAndGet()).toString());
	}

}
