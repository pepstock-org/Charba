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
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.utils.Utilities;

/**
 * Implements a BOX annotation which draws a box in the a chart.<br>
 * If one of the axes is not specified, the box will take the entire chart dimension.<br>
 * The 4 coordinates, xMin, xMax, yMin, yMax are optional. If not specified, the box is expanded out to the edges.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class BoxAnnotation extends AbstractXYAnnotation implements IsDefaultsBoxAnnotation, HasBackgroundColor, HasBorderRadius {

	/**
	 * Default box annotation border width, <b>{@value DEFAULT_BORDER_WIDTH}</b>.
	 */
	public static final int DEFAULT_BORDER_WIDTH = 1;

	/**
	 * Default box annotation border radius, <b>{@value DEFAULT_BORDER_RADIUS}</b>.
	 */
	public static final int DEFAULT_BORDER_RADIUS = 0;

	// defaults options
	private final IsDefaultsBoxAnnotation defaultValues;
	// background color handler
	private final BackgroundColorHandler backgroundColorHandler;
	// border radius handler
	private final BorderRadiusHandler borderRadiusHandler;

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
		this(AnnotationId.create(id));
	}

	/**
	 * Creates a box annotation to be added to an {@link AnnotationOptions} instance, using the ID passed as argument.
	 * 
	 * @param id annotation id to apply to the object
	 */
	public BoxAnnotation(AnnotationId id) {
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
		this(AnnotationId.create(id), chart);
	}

	/**
	 * Creates a box annotation to be added to an {@link AnnotationOptions} instance, using the ID passed as argument.<br>
	 * The chart instance, passed as argument, must be the chart where the annotations will be applied and is used to get the whole default options in order to get the default for
	 * this object.
	 * 
	 * @param id annotation id to apply to the object
	 * @param chart chart instance related to the plugin options
	 */
	public BoxAnnotation(AnnotationId id, IsChart chart) {
		this(id, AnnotationHelper.get().getDefaultsAnnotationOptionsByChart(AnnotationType.BOX, id, chart));
	}

	/**
	 * Creates a box annotation to be added to an {@link AnnotationOptions} instance, using the native object and defaults passed as argument.
	 * 
	 * @param id annotation id to apply to the object
	 * @param defaultValues default options instance
	 */
	private BoxAnnotation(AnnotationId id, IsDefaultsAnnotation defaultValues) {
		super(AnnotationType.BOX, id, defaultValues);
		// checks if default are of the right class
		Checker.assertCheck(getDefaultsValues() instanceof IsDefaultsBoxAnnotation, Utilities.applyTemplate(INVALID_DEFAULTS_VALUES_CLASS, AnnotationType.BOX.value()));
		// casts and stores it
		this.defaultValues = (IsDefaultsBoxAnnotation) getDefaultsValues();
		// creates background color handler
		this.backgroundColorHandler = new BackgroundColorHandler(this, this.defaultValues, getNativeObject());
		// creates border radius handler
		this.borderRadiusHandler = new BorderRadiusHandler(this, this.defaultValues, getNativeObject());
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
		Checker.assertCheck(getDefaultsValues() instanceof IsDefaultsBoxAnnotation, Utilities.applyTemplate(INVALID_DEFAULTS_VALUES_CLASS, AnnotationType.BOX.value()));
		// casts and stores it
		this.defaultValues = (IsDefaultsBoxAnnotation) getDefaultsValues();
		// creates background color handler
		this.backgroundColorHandler = new BackgroundColorHandler(this, this.defaultValues, getNativeObject());
		// creates border radius handler
		this.borderRadiusHandler = new BorderRadiusHandler(this, this.defaultValues, getNativeObject());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.annotation.HasBackgroundColor#getBackgroundColorHandler()
	 */
	@Override
	public BackgroundColorHandler getBackgroundColorHandler() {
		return backgroundColorHandler;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.annotation.HasBorderRadius#getBorderRadiusHandler()
	 */
	@Override
	public BorderRadiusHandler getBorderRadiusHandler() {
		return borderRadiusHandler;
	}

	/**
	 * Returns the color of the border of annotation.
	 * 
	 * @return the color of the border of annotation
	 */
	@Override
	public final String getBorderColorAsString() {
		return getValue(AbstractAnnotation.Property.BORDER_COLOR, defaultValues.getBorderColorAsString());
	}

	/**
	 * Returns the width of the border in pixels.
	 * 
	 * @return the width of the border in pixels.
	 */
	@Override
	public final int getBorderWidth() {
		return getValue(AbstractAnnotation.Property.BORDER_WIDTH, defaultValues.getBorderWidth());
	}
}
