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

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.options.ElementFactory;
import org.pepstock.charba.client.utils.Utilities;

/**
 * Implements a ELLIPSE annotation which draws a ellipse in the a chart.<br>
 * If one of the axes is not specified, the ellipse will take the entire chart dimension.<br>
 * The 4 coordinates, xMin, xMax, yMin, yMax are optional. If not specified, the ellipse is expanded out to the edges.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class EllipseAnnotation extends AbstractAnnotation implements IsDefaultsEllipseAnnotation, IsLabelContainer<EllipseLabel>, HasBackgroundColor, HasExtendedShadowOptions, HasRotation {

	/**
	 * Default ellipse annotation border width, <b>{@value DEFAULT_BORDER_WIDTH}</b>.
	 */
	public static final int DEFAULT_BORDER_WIDTH = 1;

	// Annotation element key
	private static final String ELEMENT_KEY_AS_STRING = "ellipseAnnotation";

	/**
	 * Element factory to get "{@value EllipseAnnotation#ELEMENT_KEY_AS_STRING}" element.
	 */
	public static final ElementFactory<EllipseAnnotation> FACTORY = new EllipseElementFactory(ELEMENT_KEY_AS_STRING);

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		LABEL("label");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use in the native object.
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
	private final IsDefaultsEllipseAnnotation defaultValues;
	// background color handler
	private final BackgroundColorHandler backgroundColorHandler;
	// extended shadow options handler
	private final ExtendedShadowOptionsHandler extendedShadowOptionsHandler;
	// rotation handler
	private final RotationHandler rotationHandler;
	// label for ellipse instance
	private final EllipseLabel label;

	/**
	 * Creates a ellipse annotation to be added to an {@link AnnotationOptions} instance.<br>
	 * The annotation id is calculated automatically.
	 * 
	 * @see AnnotationType#createId()
	 */
	public EllipseAnnotation() {
		this(AnnotationType.ELLIPSE.createId(), Defaults.get().getGlobal().getElements().getElement(FACTORY));
	}

	/**
	 * Creates a ellipse annotation to be added to an {@link AnnotationOptions} instance, using the ID passed as argument.
	 * 
	 * @param id annotation id to apply to the object, as string
	 */
	public EllipseAnnotation(String id) {
		this(AnnotationId.create(id));
	}

	/**
	 * Creates a ellipse annotation to be added to an {@link AnnotationOptions} instance, using the ID passed as argument.
	 * 
	 * @param id annotation id to apply to the object
	 */
	public EllipseAnnotation(AnnotationId id) {
		this(id, Defaults.get().getGlobal().getElements().getElement(FACTORY));
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
		this(AnnotationId.create(id), chart);
	}

	/**
	 * Creates a ellipse annotation to be added to an {@link AnnotationOptions} instance, using the ID passed as argument.<br>
	 * The chart instance, passed as argument, must be the chart where the annotations will be applied and is used to get the whole default options in order to get the default for
	 * this object.
	 * 
	 * @param id annotation id to apply to the object
	 * @param chart chart instance related to the plugin options
	 */
	public EllipseAnnotation(AnnotationId id, IsChart chart) {
		this(id, IsChart.checkAndGetIfConsistent(chart).getOptions().getElements().getElement(FACTORY));
	}

	/**
	 * Creates a ellipse annotation to be added to an {@link AnnotationOptions} instance, using the native object and defaults passed as argument.
	 * 
	 * @param id annotation id to apply to the object
	 * @param defaultValues default options instance
	 */
	private EllipseAnnotation(AnnotationId id, IsDefaultsAnnotation defaultValues) {
		super(AnnotationType.ELLIPSE, id, defaultValues);
		// checks if default are of the right class
		Checker.assertCheck(getDefaultsValues() instanceof IsDefaultsEllipseAnnotation, Utilities.applyTemplate(INVALID_DEFAULTS_VALUES_CLASS, AnnotationType.ELLIPSE.value()));
		// casts and stores it
		this.defaultValues = (IsDefaultsEllipseAnnotation) getDefaultsValues();
		// creates a ellipse label
		this.label = new EllipseLabel(this, this.defaultValues.getLabel());
		// stores in the annotation
		setValue(Property.LABEL, label);
		// creates background color handler
		this.backgroundColorHandler = new BackgroundColorHandler(this, this.defaultValues, getNativeObject());
		// creates shadow options handler
		this.extendedShadowOptionsHandler = new ExtendedShadowOptionsHandler(this, this.defaultValues, getNativeObject());
		// creates rotation handler
		this.rotationHandler = new RotationHandler(this, this.defaultValues, getNativeObject());

	}

	/**
	 * Creates the object wrapping an existing native object.
	 * 
	 * @param nativeObject native object to wrap
	 * @param defaultValues default options instance
	 */
	EllipseAnnotation(NativeObject nativeObject, IsDefaultsAnnotation defaultValues) {
		super(nativeObject, defaultValues);
		// checks if default are of the right class
		Checker.assertCheck(getDefaultsValues() instanceof IsDefaultsEllipseAnnotation, Utilities.applyTemplate(INVALID_DEFAULTS_VALUES_CLASS, AnnotationType.ELLIPSE.value()));
		// casts and stores it
		this.defaultValues = (IsDefaultsEllipseAnnotation) getDefaultsValues();
		// creates a ellipse label
		this.label = new EllipseLabel(this, getValue(Property.LABEL), this.defaultValues.getLabel());
		// creates background color handler
		this.backgroundColorHandler = new BackgroundColorHandler(this, this.defaultValues, getNativeObject());
		// creates shadow options handler
		this.extendedShadowOptionsHandler = new ExtendedShadowOptionsHandler(this, this.defaultValues, getNativeObject());
		// creates rotation handler
		this.rotationHandler = new RotationHandler(this, this.defaultValues, getNativeObject());
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
	 * @see org.pepstock.charba.client.annotation.HasExtendedShadowOptions#getExtendedShadowOptionsHandler()
	 */
	@Override
	public ExtendedShadowOptionsHandler getExtendedShadowOptionsHandler() {
		return extendedShadowOptionsHandler;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.annotation.HasRotation#getRotationHandler()
	 */
	@Override
	public RotationHandler getRotationHandler() {
		return rotationHandler;
	}

	/**
	 * Returns the label on the ellipse.
	 * 
	 * @return the label on the ellipse
	 */
	@Override
	public EllipseLabel getLabel() {
		return label;
	}

	/**
	 * Specific element factory for ellipse annotation elements.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class EllipseElementFactory extends AnnotationElementFactory<EllipseAnnotation> {

		/**
		 * Creates the factory by the key of object, as string.
		 * 
		 * @param elementKeyAsString the key of object, as string.
		 */
		private EllipseElementFactory(String elementKeyAsString) {
			super(elementKeyAsString);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.annotation.AnnotationElementFactory#createOptions(org.pepstock.charba.client.commons.NativeObject)
		 */
		@Override
		EllipseAnnotation createOptions(NativeObject nativeObject) {
			return new EllipseAnnotation(nativeObject, AnnotationType.ELLIPSE.getDefaultsValues());
		}

	}
}