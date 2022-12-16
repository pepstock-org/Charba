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
import org.pepstock.charba.client.enums.CapStyle;
import org.pepstock.charba.client.enums.JoinStyle;
import org.pepstock.charba.client.options.ElementFactory;
import org.pepstock.charba.client.utils.Utilities;

/**
 * Implements a BOX annotation which draws a box in the a chart.<br>
 * If one of the axes is not specified, the box will take the entire chart dimension.<br>
 * The 4 coordinates, xMin, xMax, yMin, yMax are optional. If not specified, the box is expanded out to the edges.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class BoxAnnotation extends AbstractAnnotation implements IsDefaultsBoxAnnotation, IsLabelContainer<BoxLabel>, HasBackgroundColor, HasBorderRadius, HasExtendedBorderOptions, HasExtendedShadowOptions, HasRotation {

	/**
	 * Default box annotation border width, <b>{@value DEFAULT_BORDER_WIDTH}</b>.
	 */
	public static final int DEFAULT_BORDER_WIDTH = 1;

	/**
	 * Default box annotation border radius, <b>{@value DEFAULT_BORDER_RADIUS}</b>.
	 */
	public static final int DEFAULT_BORDER_RADIUS = 0;

	/**
	 * Default box annotation border cap style, <b>{@link CapStyle#BUTT}</b>.
	 */
	public static final CapStyle DEFAULT_BORDER_CAP_STYLE = CapStyle.BUTT;

	/**
	 * Default box annotation border join style, <b>{@link JoinStyle#MITER}</b>.
	 */
	public static final JoinStyle DEFAULT_BORDER_JOIN_STYLE = JoinStyle.MITER;

	// Annotation element key
	private static final String ELEMENT_KEY_AS_STRING = "boxAnnotation";

	/**
	 * Element factory to get "{@value BoxAnnotation#ELEMENT_KEY_AS_STRING}" element.
	 */
	public static final ElementFactory<BoxAnnotation> FACTORY = new BoxElementFactory(ELEMENT_KEY_AS_STRING);

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
	private final IsDefaultsBoxAnnotation defaultValues;
	// background color handler
	private final BackgroundColorHandler backgroundColorHandler;
	// border radius handler
	private final BorderRadiusHandler borderRadiusHandler;
	// extended border options handler
	private final ExtendedBorderOptionsHandler extendedBorderOptionsHandler;
	// extended shadow options handler
	private final ExtendedShadowOptionsHandler extendedShadowOptionsHandler;
	// rotation handler
	private final RotationHandler rotationHandler;
	// label for box instance
	private final BoxLabel label;

	/**
	 * Creates a box annotation to be added to an {@link AnnotationOptions} instance.<br>
	 * The annotation id is calculated automatically.
	 * 
	 * @see AnnotationType#createId()
	 */
	public BoxAnnotation() {
		this(AnnotationType.BOX.createId(), Defaults.get().getGlobal().getElements().getElement(FACTORY));
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
		this(id, Defaults.get().getGlobal().getElements().getElement(FACTORY));
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
		this(id, IsChart.checkAndGetIfConsistent(chart).getOptions().getElements().getElement(FACTORY));
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
		// creates a box label
		this.label = new BoxLabel(this, this.defaultValues.getLabel());
		// stores in the annotation
		setValue(Property.LABEL, label);
		// creates background color handler
		this.backgroundColorHandler = new BackgroundColorHandler(this, this.defaultValues, getNativeObject());
		// creates border radius handler
		this.borderRadiusHandler = new BorderRadiusHandler(this, this.defaultValues, getNativeObject());
		// creates border options handler
		this.extendedBorderOptionsHandler = new ExtendedBorderOptionsHandler(this, this.defaultValues, getNativeObject());
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
	BoxAnnotation(NativeObject nativeObject, IsDefaultsAnnotation defaultValues) {
		super(nativeObject, defaultValues);
		// checks if default are of the right class
		Checker.assertCheck(getDefaultsValues() instanceof IsDefaultsBoxAnnotation, Utilities.applyTemplate(INVALID_DEFAULTS_VALUES_CLASS, AnnotationType.BOX.value()));
		// casts and stores it
		this.defaultValues = (IsDefaultsBoxAnnotation) getDefaultsValues();
		// creates a box label
		this.label = new BoxLabel(this, getValue(Property.LABEL), this.defaultValues.getLabel());
		// creates background color handler
		this.backgroundColorHandler = new BackgroundColorHandler(this, this.defaultValues, getNativeObject());
		// creates border radius handler
		this.borderRadiusHandler = new BorderRadiusHandler(this, this.defaultValues, getNativeObject());
		// creates border options handler
		this.extendedBorderOptionsHandler = new ExtendedBorderOptionsHandler(this, this.defaultValues, getNativeObject());
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
	 * @see org.pepstock.charba.client.annotation.HasBorderRadius#getBorderRadiusHandler()
	 */
	@Override
	public BorderRadiusHandler getBorderRadiusHandler() {
		return borderRadiusHandler;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.annotation.HasExtendedBorderOptions#getExtendedBorderOptionsHandler()
	 */
	@Override
	public ExtendedBorderOptionsHandler getExtendedBorderOptionsHandler() {
		return extendedBorderOptionsHandler;
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
	 * Returns the label on the box.
	 * 
	 * @return the label on the box
	 */
	@Override
	public BoxLabel getLabel() {
		return label;
	}

	/**
	 * Specific element factory for box annotation elements.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class BoxElementFactory extends AnnotationElementFactory<BoxAnnotation> {

		/**
		 * Creates the factory by the key of object, as string.
		 * 
		 * @param elementKeyAsString the key of object, as string.
		 */
		private BoxElementFactory(String elementKeyAsString) {
			super(elementKeyAsString);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.annotation.AnnotationElementFactory#createOptions(org.pepstock.charba.client.commons.NativeObject)
		 */
		@Override
		BoxAnnotation createOptions(NativeObject nativeObject) {
			return new BoxAnnotation(nativeObject, AnnotationType.BOX.getDefaultsValues());
		}
	}

}