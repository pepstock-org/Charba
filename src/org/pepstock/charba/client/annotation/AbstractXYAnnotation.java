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

import java.util.Date;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.options.IsScaleId;
import org.pepstock.charba.client.utils.Utilities;

/**
 * Implements a annotation which can be configured with the X (min and max) and Y (min and max) values, like {@link BoxAnnotation} and {@link LineAnnotation}.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
abstract class AbstractXYAnnotation extends AbstractAnnotation implements IsDefaultsXYAnnotation {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		X_SCALE_ID("xScaleID"),
		X_MIN("xMin"),
		X_MAX("xMax"),
		Y_SCALE_ID("yScaleID"),
		Y_MIN("yMin"),
		Y_MAX("yMax");

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
	private final IsDefaultsXYAnnotation defaultValues;

	/**
	 * Creates a XY annotation to be added to an {@link AnnotationOptions} instance, using the native object and defaults passed as argument.
	 * 
	 * @param type annotation type of the instance
	 * @param id annotation id to apply to the object
	 * @param defaultValues default options instance
	 */
	AbstractXYAnnotation(AnnotationType type, IsAnnotationId id, IsDefaultsAnnotation defaultValues) {
		// if id is not consistent, new one is created
		// if defaults is not consistent, the defaults defined for this annotation type is used
		super(Key.checkAndGetIfValid(type), id == null ? type.createId() : id, defaultValues == null ? type.getDefaultsValues() : defaultValues);
		// checks if default are of the right class
		if (getDefaultsValues() instanceof IsDefaultsXYAnnotation) {
			// casts and stores it
			this.defaultValues = (IsDefaultsXYAnnotation) getDefaultsValues();
		} else {
			// wrong class, exception!
			throw new IllegalArgumentException(Utilities.applyTemplate(INVALID_DEFAULTS_VALUES_CLASS, type.value()));
		}
	}

	/**
	 * Creates the object wrapping an existing native object.
	 * 
	 * @param type annotation type of the instance
	 * @param nativeObject native object to wrap
	 * @param defaultValues default options instance
	 */
	AbstractXYAnnotation(AnnotationType type, NativeObject nativeObject, IsDefaultsAnnotation defaultValues) {
		super(nativeObject, defaultValues);
		// checks if default are of the right class
		if (getDefaultsValues() instanceof IsDefaultsXYAnnotation) {
			// casts and stores it
			this.defaultValues = (IsDefaultsXYAnnotation) getDefaultsValues();
		} else {
			// wrong class, exception!
			throw new IllegalArgumentException(Utilities.applyTemplate(INVALID_DEFAULTS_VALUES_CLASS, type.value()));
		}
	}

	/**
	 * Sets the ID of the X scale to bind onto.
	 * 
	 * @param scaleId the ID of the X scale to bind onto
	 */
	public final void setXScaleID(String scaleId) {
		// checks if scale id is valid
		IsScaleId.checkIfValid(scaleId);
		// stores it
		setValue(Property.X_SCALE_ID, scaleId);
	}

	/**
	 * Sets the ID of the X scale to bind onto.
	 * 
	 * @param scaleId the ID of the X scale to bind onto
	 */
	public final void setXScaleID(IsScaleId scaleId) {
		// checks if scale id is valid
		IsScaleId.checkIfValid(scaleId);
		// stores it
		setValue(Property.X_SCALE_ID, scaleId);
	}

	/**
	 * Returns the ID of the X scale to bind onto.
	 * 
	 * @return the ID of the X scale to bind onto
	 */
	@Override
	public final IsScaleId getXScaleID() {
		return getValue(Property.X_SCALE_ID, defaultValues.getXScaleID());
	}

	/**
	 * Sets the right edge of the box.
	 * 
	 * @param max the right edge of the box
	 */
	public final void setXMax(String max) {
		setValue(Property.X_MAX, max);
	}

	/**
	 * Sets the right edge of the box.
	 * 
	 * @param max the right edge of the box
	 */
	public final void setXMax(double max) {
		setValue(Property.X_MAX, max);
	}

	/**
	 * Sets the right edge of the box.
	 * 
	 * @param max the right edge of the box
	 */
	public final void setXMax(Date max) {
		setValue(Property.X_MAX, max);
	}

	/**
	 * Returns the right edge of the box.
	 * 
	 * @return the right edge of the box
	 */
	@Override
	public final String getXMaxAsString() {
		return getValueForMultipleKeyTypes(Property.X_MAX, defaultValues.getXMaxAsString());
	}

	/**
	 * Returns the right edge of the box.
	 * 
	 * @return the right edge of the box
	 */
	@Override
	public final double getXMaxAsDouble() {
		return getValueForMultipleKeyTypes(Property.X_MAX, defaultValues.getXMaxAsDouble());
	}

	/**
	 * Returns the right edge of the box.
	 * 
	 * @return the right edge of the box
	 */
	@Override
	public final Date getXMaxAsDate() {
		return getValueForMultipleKeyTypes(Property.X_MAX, defaultValues.getXMaxAsDate());
	}

	/**
	 * Sets the left edge of the box, in units along the x axis.
	 * 
	 * @param min the left edge of the box
	 */
	public final void setXMin(String min) {
		setValue(Property.X_MIN, min);
	}

	/**
	 * Sets the left edge of the box, in units along the x axis.
	 * 
	 * @param min the left edge of the box
	 */
	public final void setXMin(double min) {
		setValue(Property.X_MIN, min);
	}

	/**
	 * Sets the left edge of the box, in units along the x axis.
	 * 
	 * @param min the left edge of the box
	 */
	public final void setXMin(Date min) {
		setValue(Property.X_MIN, min);
	}

	/**
	 * Returns the left edge of the box, in units along the x axis.
	 * 
	 * @return the left edge of the box
	 */
	@Override
	public final String getXMinAsString() {
		return getValueForMultipleKeyTypes(Property.X_MIN, defaultValues.getXMinAsString());
	}

	/**
	 * Returns the left edge of the box, in units along the x axis.
	 * 
	 * @return the left edge of the box
	 */
	@Override
	public final double getXMinAsDouble() {
		return getValueForMultipleKeyTypes(Property.X_MIN, defaultValues.getXMinAsDouble());
	}

	/**
	 * Returns the left edge of the box, in units along the x axis.
	 * 
	 * @return the left edge of the box
	 */
	@Override
	public final Date getXMinAsDate() {
		return getValueForMultipleKeyTypes(Property.X_MIN, defaultValues.getXMinAsDate());
	}

	/**
	 * Sets the ID of the Y scale to bind onto.
	 * 
	 * @param scaleId the ID of the Y scale to bind onto
	 */
	public final void setYScaleID(String scaleId) {
		// checks if scale id is valid
		IsScaleId.checkIfValid(scaleId);
		// stores it
		setValue(Property.Y_SCALE_ID, scaleId);
	}

	/**
	 * Sets the ID of the Y scale to bind onto.
	 * 
	 * @param scaleId the ID of the Y scale to bind onto
	 */
	public final void setYScaleID(IsScaleId scaleId) {
		// checks if scale id is valid
		IsScaleId.checkIfValid(scaleId);
		// stores it
		setValue(Property.Y_SCALE_ID, scaleId);
	}

	/**
	 * Returns the ID of the Y scale to bind onto.
	 * 
	 * @return the ID of the Y scale to bind onto
	 */
	@Override
	public final IsScaleId getYScaleID() {
		return getValue(Property.Y_SCALE_ID, defaultValues.getYScaleID());
	}

	/**
	 * Sets the top edge of the box in units along the y axis.
	 * 
	 * @param max the top edge of the box in units along the y axis
	 */
	public final void setYMax(String max) {
		setValue(Property.Y_MAX, max);
	}

	/**
	 * Sets the top edge of the box in units along the y axis.
	 * 
	 * @param max the top edge of the box in units along the y axis
	 */
	public final void setYMax(double max) {
		setValue(Property.Y_MAX, max);
	}

	/**
	 * Sets the top edge of the box in units along the y axis.
	 * 
	 * @param max the top edge of the box in units along the y axis
	 */
	public final void setYMax(Date max) {
		setValue(Property.Y_MAX, max);
	}

	/**
	 * Returns the top edge of the box in units along the y axis.
	 * 
	 * @return the top edge of the box in units along the y axis
	 */
	@Override
	public final String getYMaxAsString() {
		return getValueForMultipleKeyTypes(Property.Y_MAX, defaultValues.getYMaxAsString());
	}

	/**
	 * Returns the top edge of the box in units along the y axis.
	 * 
	 * @return the top edge of the box in units along the y axis
	 */
	@Override
	public final double getYMaxAsDouble() {
		return getValueForMultipleKeyTypes(Property.Y_MAX, defaultValues.getYMaxAsDouble());
	}

	/**
	 * Returns the top edge of the box in units along the y axis.
	 * 
	 * @return the top edge of the box in units along the y axis
	 */
	@Override
	public final Date getYMaxAsDate() {
		return getValueForMultipleKeyTypes(Property.Y_MAX, defaultValues.getYMaxAsDate());
	}

	/**
	 * Sets the bottom edge of the box.
	 * 
	 * @param min the bottom edge of the box
	 */
	public final void setYMin(String min) {
		setValue(Property.Y_MIN, min);
	}

	/**
	 * Sets the bottom edge of the box.
	 * 
	 * @param min the bottom edge of the box
	 */
	public final void setYMin(double min) {
		setValue(Property.Y_MIN, min);
	}

	/**
	 * Sets the bottom edge of the box.
	 * 
	 * @param min the bottom edge of the box
	 */
	public final void setYMin(Date min) {
		setValue(Property.Y_MIN, min);
	}

	/**
	 * Returns the bottom edge of the box.
	 * 
	 * @return the bottom edge of the box
	 */
	@Override
	public final String getYMinAsString() {
		return getValueForMultipleKeyTypes(Property.Y_MIN, defaultValues.getYMinAsString());
	}

	/**
	 * Returns the bottom edge of the box.
	 * 
	 * @return the bottom edge of the box
	 */
	@Override
	public final double getYMinAsDouble() {
		return getValueForMultipleKeyTypes(Property.Y_MIN, defaultValues.getYMinAsDouble());
	}

	/**
	 * Returns the bottom edge of the box.
	 * 
	 * @return the bottom edge of the box
	 */
	@Override
	public final Date getYMinAsDate() {
		return getValueForMultipleKeyTypes(Property.Y_MIN, defaultValues.getYMinAsDate());
	}

}
