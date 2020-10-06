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
import java.util.List;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.colors.Color;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.ArrayInteger;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.options.IsScaleId;
import org.pepstock.charba.client.utils.Utilities;

/**
 * Implements a <b>LINE</b> annotation which draws a line into a chart.<br>
 * Vertical, horizontal or inclined lines are supported.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class LineAnnotation extends AbstractAnnotation implements IsDefaultsLineAnnotation {

	/**
	 * Default line annotation border color, <b>rgb(54, 162, 235)</b>.
	 */
	public static final IsColor DEFAULT_BORDER_COLOR = new Color(54, 162, 235);

	/**
	 * Default line annotation border color as string, <b>rgb(54, 162, 235)</b>.
	 */
	public static final String DEFAULT_BORDER_COLOR_AS_STRING = DEFAULT_BORDER_COLOR.toRGB();

	/**
	 * Default line annotation border width, <b>{@value DEFAULT_BORDER_WIDTH}</b>.
	 */
	public static final int DEFAULT_BORDER_WIDTH = 2;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		VALUE("value"),
		END_VALUE("endValue"),
		SCALE_ID("scaleID"),
		BORDER_DASH("borderDash"),
		BORDER_DASH_OFFSET("borderDashOffset"),
		LABEL("label");

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
	private final IsDefaultsLineAnnotation defaultValues;
	// lable for line instance
	private final LineLabel label;

	/**
	 * Creates a line annotation to be added to an {@link AnnotationOptions} instance.<br>
	 * The annotation id is calculated automatically.
	 * 
	 * @see AnnotationType#createId()
	 */
	public LineAnnotation() {
		this(AnnotationType.LINE.createId(), AnnotationType.LINE.getDefaultsValues());
	}

	/**
	 * Creates a line annotation to be added to an {@link AnnotationOptions} instance, using the ID passed as argument.
	 * 
	 * @param id annotation id to apply to the object, as string
	 */
	public LineAnnotation(String id) {
		this(IsAnnotationId.create(id));
	}

	/**
	 * Creates a line annotation to be added to an {@link AnnotationOptions} instance, using the ID passed as argument.
	 * 
	 * @param id annotation id to apply to the object
	 */
	public LineAnnotation(IsAnnotationId id) {
		this(id, Annotation.get().getDefaultsAnnotationOptionsByGlobal(AnnotationType.LINE, id));
	}

	/**
	 * Creates a line annotation to be added to an {@link AnnotationOptions} instance, using the ID passed as argument.<br>
	 * The chart instance, passed as argument, must be the chart where the annotations will be applied and is used to get the whole default options in order to get the default for
	 * this object.
	 * 
	 * @param id annotation id to apply to the object, as string
	 * @param chart chart instance related to the plugin options
	 */
	public LineAnnotation(String id, IsChart chart) {
		this(IsAnnotationId.create(id), chart);
	}

	/**
	 * Creates a line annotation to be added to an {@link AnnotationOptions} instance, using the ID passed as argument.<br>
	 * The chart instance, passed as argument, must be the chart where the annotations will be applied and is used to get the whole default options in order to get the default for
	 * this object.
	 * 
	 * @param id annotation id to apply to the object
	 * @param chart chart instance related to the plugin options
	 */
	public LineAnnotation(IsAnnotationId id, IsChart chart) {
		this(id, Annotation.get().getDefaultsAnnotationOptionsByChart(AnnotationType.LINE, id, chart));
	}

	/**
	 * Creates a line annotation to be added to an {@link AnnotationOptions} instance, using the native object and defaults passed as argument.
	 * 
	 * @param id annotation id to apply to the object
	 * @param defaultValues default options instance
	 */
	private LineAnnotation(IsAnnotationId id, IsDefaultsAnnotation defaultsOptions) {
		// if id is not consistent, new one is created
		// if defaults is not consistent, the defaults defined for this annotation type is used
		super(AnnotationType.LINE, id == null ? AnnotationType.LINE.createId() : id, defaultsOptions == null ? AnnotationType.LINE.getDefaultsValues() : defaultsOptions);
		// checks if default are of the right class
		if (getDefaultsValues() instanceof IsDefaultsLineAnnotation) {
			// casts and stores it
			this.defaultValues = (IsDefaultsLineAnnotation) getDefaultsValues();
		} else {
			// wrong class, exception!
			throw new IllegalArgumentException(Utilities.applyTemplate(INVALID_DEFAULTS_VALUES_CLASS, AnnotationType.LINE.value()));
		}
		// creates a line label
		label = new LineLabel(defaultValues.getLabel());
		// stores into annotation
		setValue(Property.LABEL, label);
	}

	/**
	 * Creates the object wrapping an existing native object.<br>
	 * <b<PAY ATTENTION</b>: this constructor is invoked from plugin before starting drawing and NOT for configuration.
	 * 
	 * @param nativeObject native object to wrap
	 * @param defaultValues default options instance
	 */
	LineAnnotation(NativeObject nativeObject, IsDefaultsAnnotation defaultValues) {
		super(nativeObject, defaultValues);
		// checks if default are of the right class
		if (getDefaultsValues() instanceof IsDefaultsLineAnnotation) {
			// casts and stores it
			this.defaultValues = (IsDefaultsLineAnnotation) getDefaultsValues();
		} else {
			// wrong class, exception!
			throw new IllegalArgumentException(Utilities.applyTemplate(INVALID_DEFAULTS_VALUES_CLASS, AnnotationType.LINE.value()));
		}
		// creates a line label
		label = new LineLabel(getValue(Property.LABEL), this.defaultValues.getLabel());
	}

	/**
	 * Returns the label on the line.
	 * 
	 * @return the label on the line
	 */
	@Override
	public LineLabel getLabel() {
		return label;
	}

	/**
	 * Returns the color of the border of annotation.
	 * 
	 * @return the color of the border of annotation
	 */
	@Override
	public String getBorderColorAsString() {
		return getValue(AbstractAnnotation.Property.BORDER_COLOR, defaultValues.getBorderColorAsString());
	}

	/**
	 * Returns the width of the border in pixels.
	 * 
	 * @return the width of the border in pixels.
	 */
	@Override
	public int getBorderWidth() {
		return getValue(AbstractAnnotation.Property.BORDER_WIDTH, defaultValues.getBorderWidth());
	}

	/**
	 * Sets the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 * 
	 * @param borderDash the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 */
	public void setBorderDash(int... borderDash) {
		setArrayValue(Property.BORDER_DASH, ArrayInteger.fromOrNull(borderDash));
	}

	/**
	 * Returns the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 * 
	 * @return the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 */
	@Override
	public List<Integer> getBorderDash() {
		// checks if there is the property
		if (has(Property.BORDER_DASH)) {
			// gets the array
			ArrayInteger array = getArrayValue(Property.BORDER_DASH);
			// and transforms to a list
			return ArrayListHelper.list(array);
		}
		// if here, the property is missing
		return defaultValues.getBorderDash();
	}

	/**
	 * Sets the line dash pattern offset or "phase".
	 * 
	 * @param borderDashOffset the line dash pattern offset or "phase".
	 */
	public void setBorderDashOffset(int borderDashOffset) {
		setValue(Property.BORDER_DASH_OFFSET, borderDashOffset);
	}

	/**
	 * Returns the line dash pattern offset or "phase".
	 * 
	 * @return the line dash pattern offset or "phase".
	 */
	@Override
	public int getBorderDashOffset() {
		return getValue(Property.BORDER_DASH_OFFSET, defaultValues.getBorderDashOffset());
	}

	/**
	 * Sets the ID of the scale to bind onto.
	 * 
	 * @param scaleId the ID of the scale to bind onto
	 */
	public void setScaleID(String scaleId) {
		// checks if scale id is valid
		IsScaleId.checkIfValid(scaleId);
		// stores it
		setValue(LineAnnotation.Property.SCALE_ID, scaleId);
	}

	/**
	 * Sets the ID of the scale to bind onto.
	 * 
	 * @param scaleId the ID of the scale to bind onto
	 */
	public void setScaleID(IsScaleId scaleId) {
		// checks if scale id is valid
		IsScaleId.checkIfValid(scaleId);
		// stores it
		setValue(LineAnnotation.Property.SCALE_ID, scaleId);
	}

	/**
	 * Returns the ID of the scale to bind onto.
	 * 
	 * @return the ID of the scale to bind onto
	 */
	@Override
	public IsScaleId getScaleID() {
		return getValue(Property.SCALE_ID, defaultValues.getScaleID());
	}

	/**
	 * Sets the data value to draw the line at.
	 * 
	 * @param value the data value to draw the line at
	 */
	public void setValue(String value) {
		setValue(Property.VALUE, value);
	}

	/**
	 * Sets the data value to draw the line at.
	 * 
	 * @param value the data value to draw the line at
	 */
	public void setValue(double value) {
		setValue(Property.VALUE, value);
	}

	/**
	 * Sets the data value to draw the line at.
	 * 
	 * @param value the data value to draw the line at
	 */
	public void setValue(Date value) {
		setValue(Property.VALUE, value);
	}

	/**
	 * Returns the data value to draw the line at.
	 * 
	 * @return the data value to draw the line at
	 */
	@Override
	public String getValueAsString() {
		return getValueForMultipleKeyTypes(Property.VALUE, defaultValues.getValueAsString());
	}

	/**
	 * Returns the data value to draw the line at.
	 * 
	 * @return the data value to draw the line at
	 */
	@Override
	public double getValueAsDouble() {
		return getValueForMultipleKeyTypes(Property.VALUE, defaultValues.getValueAsDouble());
	}

	/**
	 * Returns the data value to draw the line at.
	 * 
	 * @return the data value to draw the line at
	 */
	@Override
	public Date getValueAsDate() {
		return getValueForMultipleKeyTypes(Property.VALUE, defaultValues.getValueAsDate());
	}

	/**
	 * Sets the data value at which the line draw should end.
	 * 
	 * @param endValue the data value at which the line draw should end
	 */
	public void setEndValue(String endValue) {
		setValue(Property.END_VALUE, endValue);
	}

	/**
	 * Sets the data value at which the line draw should end.
	 * 
	 * @param endValue the data value at which the line draw should end
	 */
	public void setEndValue(double endValue) {
		setValue(Property.END_VALUE, endValue);
	}

	/**
	 * Sets the data value at which the line draw should end.
	 * 
	 * @param endValue the data value at which the line draw should end
	 */
	public void setEndValue(Date endValue) {
		setValue(Property.END_VALUE, endValue);
	}

	/**
	 * Returns the data value at which the line draw should end.
	 * 
	 * @return the data value at which the line draw should end
	 */
	@Override
	public String getEndValueAsString() {
		return getValueForMultipleKeyTypes(Property.END_VALUE, defaultValues.getEndValueAsString());
	}

	/**
	 * Returns the data value at which the line draw should end.
	 * 
	 * @return the data value at which the line draw should end
	 */
	@Override
	public double getEndValueAsDouble() {
		return getValueForMultipleKeyTypes(Property.END_VALUE, defaultValues.getEndValueAsDouble());
	}

	/**
	 * Returns the data value at which the line draw should end.
	 * 
	 * @return the data value at which the line draw should end
	 */
	@Override
	public Date getEndValueAsDate() {
		return getValueForMultipleKeyTypes(Property.END_VALUE, defaultValues.getEndValueAsDate());
	}

}
