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

import org.pepstock.charba.client.callbacks.ColorCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyObjectCallback;
import org.pepstock.charba.client.callbacks.ScriptableUtils;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.CallbackPropertyHandler;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
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
abstract class AbstractBoxAnnotation extends AbstractXYAnnotation implements IsDefaultsAbstractBoxAnnotation {

	/**
	 * Default box annotation border width, <b>{@value DEFAULT_BORDER_WIDTH}</b>.
	 */
	public static final int DEFAULT_BORDER_WIDTH = 1;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		BACKGROUND_COLOR("backgroundColor");

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
	
	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the background color function
	private final CallbackProxy<ProxyObjectCallback> backgroundColorCallbackProxy = JsHelper.get().newCallbackProxy();

	// callback instance to handle background color options
	private static final CallbackPropertyHandler<ColorCallback<AnnotationContext>> BACKGROUND_COLOR_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.BACKGROUND_COLOR);


	// defaults options
	private final IsDefaultsAbstractBoxAnnotation defaultValues;

	/**
	 * Creates a box annotation to be added to an {@link AnnotationOptions} instance, using the native object and defaults passed as argument.
	 * 
	 * @param type annotation type of the instance
	 * @param id annotation id to apply to the object
	 * @param defaultValues default options instance
	 */
	AbstractBoxAnnotation(AnnotationType type, IsAnnotationId id, IsDefaultsAnnotation defaultValues) {
		// if id is not consistent, new one is created
		// if defaults is not consistent, the defaults defined for this annotation type is used
		super(Key.checkAndGetIfValid(type), id == null ? type.createId() : id, defaultValues == null ? type.getDefaultsValues() : defaultValues);
		// checks if default are of the right class
		if (getDefaultsValues() instanceof IsDefaultsAbstractBoxAnnotation) {
			// casts and stores it
			this.defaultValues = (IsDefaultsAbstractBoxAnnotation) getDefaultsValues();
		} else {
			// wrong class, exception!
			throw new IllegalArgumentException(Utilities.applyTemplate(INVALID_DEFAULTS_VALUES_CLASS, type.value()));
		}
		// sets callbacks proxies
		initAbstractBoxCallbacks();
	}

	/**
	 * Creates the object wrapping an existing native object.
	 * 
	 * @param type annotation type of the instance
	 * @param nativeObject native object to wrap
	 * @param defaultValues default options instance
	 */
	AbstractBoxAnnotation(AnnotationType type, NativeObject nativeObject, IsDefaultsAnnotation defaultValues) {
		super(type, nativeObject, defaultValues);
		// checks if default are of the right class
		if (getDefaultsValues() instanceof IsDefaultsAbstractBoxAnnotation) {
			// casts and stores it
			this.defaultValues = (IsDefaultsAbstractBoxAnnotation) getDefaultsValues();
		} else {
			// wrong class, exception!
			throw new IllegalArgumentException(Utilities.applyTemplate(INVALID_DEFAULTS_VALUES_CLASS, type.value()));
		}
		// sets callbacks proxies
		initAbstractBoxCallbacks();
	}
	
	/**
	 * Initializes the callbacks proxies for the options which can be scriptable.
	 */
	private void initAbstractBoxCallbacks() {
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// sets function to proxy callback in order to invoke the java interface
		this.backgroundColorCallbackProxy.setCallback((contextFunction, context) -> ScriptableUtils.getOptionValueAsColor(new AnnotationContext(this, context), getBackgroundColorCallback(), defaultValues.getBackgroundColorAsString()));
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

	/**
	 * Sets the color of the background of annotation.
	 * 
	 * @param backgroundColor the color of the background of annotation
	 */
	public final void setBackgroundColor(IsColor backgroundColor) {
		setBackgroundColor(IsColor.checkAndGetValue(backgroundColor));
	}

	/**
	 * Sets the color of the background of annotation.
	 * 
	 * @param backgroundColor the color of the background of annotation
	 */
	public final void setBackgroundColor(String backgroundColor) {
		// resets callback
		setBackgroundColor((ColorCallback<AnnotationContext>)null);
		// stores value
		setValue(Property.BACKGROUND_COLOR, backgroundColor);
	}

	/**
	 * Returns the color of the background of annotation.
	 * 
	 * @return the color of the background of annotation
	 */
	@Override
	public final String getBackgroundColorAsString() {
		return getValue(Property.BACKGROUND_COLOR, defaultValues.getBackgroundColorAsString());
	}

	/**
	 * Returns the color of the background of annotation.
	 * 
	 * @return the color of the background of annotation
	 */
	public final IsColor getBackgroundColor() {
		return ColorBuilder.parse(getBackgroundColorAsString());
	}
	
	// ---------------------
	// CALLBACKS
	// ---------------------

	/**
	 * Returns the callback called to set the color of the background of annotation.
	 * 
	 * @return the callback called to set the color of the background of annotation
	 */
	@Override
	public final ColorCallback<AnnotationContext> getBackgroundColorCallback() {
		return BACKGROUND_COLOR_PROPERTY_HANDLER.getCallback(this, defaultValues.getBackgroundColorCallback());
	}

	/**
	 * Sets the callback to set the color of the background of annotation.
	 * 
	 * @param backgroundColorCallback to set the color of the background of annotation
	 */
	public final void setBackgroundColor(ColorCallback<AnnotationContext> backgroundColorCallback) {
		BACKGROUND_COLOR_PROPERTY_HANDLER.setCallback(this, PLUGIN_SCOPE, backgroundColorCallback, backgroundColorCallbackProxy.getProxy());
	}

}
