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
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.utils.Utilities;

/**
 * Implements a BOX annotation which draws a box into a chart.<br>
 * If one of the axes is not specified, the box will take the entire chart dimension.<br>
 * The 4 coordinates, xMin, xMax, yMin, yMax are optional. If not specified, the box is expanded out to the edges.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class BoxAnnotation extends AbstractBoxAnnotation implements IsDefaultsBoxAnnotation {

	/**
	 * Default box annotation corner radius, <b>{@value DEFAULT_CORNER_RADIUS}</b>.
	 */
	public static final int DEFAULT_CORNER_RADIUS = 0;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		CORNER_RADIUS("cornerRadius");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use into native object.
		 * 
		 * @param value value of property name
		 */
		private Property(String value) {
			this.value = value;
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

	}

	// defaults options
	private final IsDefaultsBoxAnnotation defaultValues;

	/**
	 * Creates a box annotation to be added to an {@link AnnotationOptions} instance.<br>
	 * The annotation id is calculated automatically.
	 * 
	 * @see AnnotationType#createId()
	 */
	public BoxAnnotation() {
		this(AnnotationType.BOX.createId(), AnnotationType.BOX.getDefaultsValues());
	}

	/**
	 * Creates a box annotation to be added to an {@link AnnotationOptions} instance, using the ID passed as argument.
	 * 
	 * @param id annotation id to apply to the object, as string
	 */
	public BoxAnnotation(String id) {
		this(IsAnnotationId.create(id));
	}

	/**
	 * Creates a box annotation to be added to an {@link AnnotationOptions} instance, using the ID passed as argument.
	 * 
	 * @param id annotation id to apply to the object
	 */
	public BoxAnnotation(IsAnnotationId id) {
		this(id, AnnotationHelper.get().getDefaultsAnnotationOptionsByGlobal(AnnotationType.BOX, id));
	}

	/**
	 * Creates a box annotation to be added to an {@link AnnotationOptions} instance, using the ID passed as argument.<br>
	 * The chart instance, passed as argument, must be the chart where the annotations will be applied and is used to get the whole default options in order to get the default for
	 * this object.
	 * 
	 * @param id annotation id to apply to the object, as string
	 * @param chart chart instance related to the plugin options
	 */
	public BoxAnnotation(String id, IsChart chart) {
		this(IsAnnotationId.create(id), chart);
	}

	/**
	 * Creates a box annotation to be added to an {@link AnnotationOptions} instance, using the ID passed as argument.<br>
	 * The chart instance, passed as argument, must be the chart where the annotations will be applied and is used to get the whole default options in order to get the default for
	 * this object.
	 * 
	 * @param id annotation id to apply to the object
	 * @param chart chart instance related to the plugin options
	 */
	public BoxAnnotation(IsAnnotationId id, IsChart chart) {
		this(id, AnnotationHelper.get().getDefaultsAnnotationOptionsByChart(AnnotationType.BOX, id, chart));
	}

	/**
	 * Creates a box annotation to be added to an {@link AnnotationOptions} instance, using the native object and defaults passed as argument.
	 * 
	 * @param id annotation id to apply to the object
	 * @param defaultValues default options instance
	 */
	private BoxAnnotation(IsAnnotationId id, IsDefaultsAnnotation defaultValues) {
		super(AnnotationType.BOX, id, defaultValues);
		// checks if default are of the right class
		if (getDefaultsValues() instanceof IsDefaultsAbstractBoxAnnotation) {
			// casts and stores it
			this.defaultValues = (IsDefaultsBoxAnnotation) getDefaultsValues();
		} else {
			// wrong class, exception!
			throw new IllegalArgumentException(Utilities.applyTemplate(INVALID_DEFAULTS_VALUES_CLASS, AnnotationType.BOX.value()));
		}
	}

	/**
	 * Creates the object wrapping an existing native object.
	 * 
	 * @param nativeObject native object to wrap
	 * @param defaultValues default options instance
	 */
	BoxAnnotation(NativeObject nativeObject, IsDefaultsAnnotation defaultValues) {
		super(AnnotationType.BOX, nativeObject, defaultValues);
		// checks if default are of the right class
		if (getDefaultsValues() instanceof IsDefaultsAbstractBoxAnnotation) {
			// casts and stores it
			this.defaultValues = (IsDefaultsBoxAnnotation) getDefaultsValues();
		} else {
			// wrong class, exception!
			throw new IllegalArgumentException(Utilities.applyTemplate(INVALID_DEFAULTS_VALUES_CLASS, AnnotationType.BOX.value()));
		}
	}

	/**
	 * Sets the corner radius.
	 * 
	 * @param corner the border radius.
	 */
	public void setCornerRadius(double corner) {
		setValue(Property.CORNER_RADIUS, corner);
	}

	/**
	 * Returns the border radius.
	 * 
	 * @return the border radius.
	 */
	@Override
	public double getCornerRadius() {
		return getValue(Property.CORNER_RADIUS, defaultValues.getCornerRadius());
	}
}
