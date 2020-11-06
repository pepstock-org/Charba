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

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.callbacks.AnnotationValueCallback;
import org.pepstock.charba.client.colors.Color;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.Gradient;
import org.pepstock.charba.client.colors.GradientBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.colors.Pattern;
import org.pepstock.charba.client.colors.PatternBuilder;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.enums.ColorType;
import org.pepstock.charba.client.options.IsScaleId;
import org.pepstock.charba.client.utils.Utilities;

/**
 * Implements a <b>BOX</b> annotation which draws a box into a chart.<br>
 * If one of the axes is not specified, the box will take the entire chart dimension.<br>
 * The 4 coordinates, xMin, xMax, yMin, yMax are optional. If not specified, the box is expanded out to the edges.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class BoxAnnotation extends AbstractAnnotation implements IsDefaultsBoxAnnotation {

	/**
	 * Default box annotation background color, <b>rgb(102, 102, 102)</b>.
	 */
	public static final IsColor DEFAULT_BACKGROUND_COLOR = new Color(102, 102, 102);

	/**
	 * Default box annotation background color as string, <b>rgb(102, 102, 102)</b>.
	 */
	public static final String DEFAULT_BACKGROUND_COLOR_AS_STRING = DEFAULT_BACKGROUND_COLOR.toRGB();

	/**
	 * Default box annotation border color, <b>rgb(92, 92, 92)</b>.
	 */
	public static final IsColor DEFAULT_BORDER_COLOR = new Color(92, 92, 92);

	/**
	 * Default box annotation border color as string, <b>rgb(92, 92, 92)</b>.
	 */
	public static final String DEFAULT_BORDER_COLOR_AS_STRING = DEFAULT_BORDER_COLOR.toRGB();

	/**
	 * Default box annotation border width, <b>{@value DEFAULT_BORDER_WIDTH}</b>.
	 */
	public static final int DEFAULT_BORDER_WIDTH = 1;

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
		Y_MAX("yMax"),
		BACKGROUND_COLOR("backgroundColor"),
		BACKGROUND_COLOR_TYPE("backgroundColorType");

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
	// X minimum callback instance
	private AnnotationValueCallback xMinCallback;
	// X maximum value callback instance
	private AnnotationValueCallback xMaxCallback;
	// Y minimum callback instance
	private AnnotationValueCallback yMinCallback;
	// Y maximum value callback instance
	private AnnotationValueCallback yMaxCallback;

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
		this(id, Annotation.get().getDefaultsAnnotationOptionsByGlobal(AnnotationType.BOX, id));
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
		this(id, Annotation.get().getDefaultsAnnotationOptionsByChart(AnnotationType.BOX, id, chart));
	}

	/**
	 * Creates a box annotation to be added to an {@link AnnotationOptions} instance, using the native object and defaults passed as argument.
	 * 
	 * @param id annotation id to apply to the object
	 * @param defaultValues default options instance
	 */
	private BoxAnnotation(IsAnnotationId id, IsDefaultsAnnotation defaultValues) {
		// if id is not consistent, new one is created
		// if defaults is not consistent, the defaults defined for this annotation type is used
		super(AnnotationType.BOX, id == null ? AnnotationType.BOX.createId() : id, defaultValues == null ? AnnotationType.BOX.getDefaultsValues() : defaultValues);
		// checks if default are of the right class
		if (getDefaultsValues() instanceof IsDefaultsBoxAnnotation) {
			// casts and stores it
			this.defaultValues = (IsDefaultsBoxAnnotation) getDefaultsValues();
		} else {
			// wrong class, exception!
			throw new IllegalArgumentException(Utilities.applyTemplate(INVALID_DEFAULTS_VALUES_CLASS, AnnotationType.BOX.value()));
		}
	}

	/**
	 * Creates the object wrapping an existing native object.<br>
	 * <b>PAY ATTENTION</b>: this constructor is invoked from plugin before starting drawing and NOT for configuration.
	 * 
	 * @param nativeObject native object to wrap
	 * @param defaultValues default options instance
	 */
	BoxAnnotation(NativeObject nativeObject, IsDefaultsAnnotation defaultValues) {
		super(nativeObject, defaultValues);
		// checks if default are of the right class
		if (getDefaultsValues() instanceof IsDefaultsBoxAnnotation) {
			// casts and stores it
			this.defaultValues = (IsDefaultsBoxAnnotation) getDefaultsValues();
		} else {
			// wrong class, exception!
			throw new IllegalArgumentException(Utilities.applyTemplate(INVALID_DEFAULTS_VALUES_CLASS, AnnotationType.BOX.value()));
		}
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
	 * Returns the type of background color has been set.
	 * 
	 * @return the type of background color has been set.
	 */
	ColorType getBackgroundColorType() {
		return getValue(Property.BACKGROUND_COLOR_TYPE, ColorType.values(), ColorType.COLOR);
	}

	/**
	 * Returns the color of the background of annotation.
	 * 
	 * @return the color of the background of annotation.
	 */
	@Override
	public String getBackgroundColorAsString() {
		// checks if color has been set
		if (ColorType.COLOR.equals(getBackgroundColorType())) {
			return getValue(Property.BACKGROUND_COLOR, defaultValues.getBackgroundColorAsString());
		}
		// otherwise returns defaults
		return defaultValues.getBackgroundColorAsString();
	}

	/**
	 * Returns the color of the background of annotation.
	 * 
	 * @return the color of the background of annotation.
	 */
	public IsColor getBackgroundColor() {
		// checks if color has been set
		if (ColorType.COLOR.equals(getBackgroundColorType())) {
			return ColorBuilder.parse(getBackgroundColorAsString());
		}
		// otherwise returns defaults
		return ColorBuilder.parse(defaultValues.getBackgroundColorAsString());
	}

	/**
	 * Returns the canvas gradient of the background of annotation.<br>
	 * If it has been set a color or pattern, returns <code>null</code>.
	 * 
	 * @return the canvas gradient of the background of annotation.<br>
	 *         If it has been set a color or pattern, returns <code>null</code>
	 */
	@Override
	public Gradient getBackgroundColorAsGradient() {
		// checks if gradient has been set
		if (ColorType.GRADIENT.equals(getBackgroundColorType())) {
			// checks if the gradient has been set into this object or into defaults
			if (has(Property.BACKGROUND_COLOR)) {
				// if here, the gradient has been set into this options
				return GradientBuilder.build(getValue(Property.BACKGROUND_COLOR));
			} else {
				// if here, the gradient has been set into defaults options
				return defaultValues.getBackgroundColorAsGradient();
			}
		}
		// otherwise returns null
		return null;
	}

	/**
	 * Returns the canvas pattern of the background of annotation.<br>
	 * If it has been set a color or gradient, returns <code>null</code>.
	 * 
	 * @return the canvas pattern of the background of annotation.<br>
	 *         If it has been set a color or pattern, returns <code>null</code>
	 */
	@Override
	public Pattern getBackgroundColorAsPattern() {
		// checks if pattern has been set
		if (ColorType.PATTERN.equals(getBackgroundColorType())) {
			// checks if the pattern has been set into this object or into defaults
			if (has(Property.BACKGROUND_COLOR)) {
				// if here, the pattern has been set into this options
				return PatternBuilder.build(getValue(Property.BACKGROUND_COLOR));
			} else {
				// if here, the pattern has been set into defaults options
				return defaultValues.getBackgroundColorAsPattern();
			}
		}
		// otherwise returns null
		return null;
	}

	/**
	 * Sets the background color.
	 * 
	 * @param color the background color.
	 */
	public void setBackgroundColor(String color) {
		setValue(Property.BACKGROUND_COLOR, color);
		// checks if color is consistent
		if (color != null) {
			// sets the color type
			setValue(Property.BACKGROUND_COLOR_TYPE, ColorType.COLOR);
		} else {
			// if here, the color is not consistent
			// removes type
			remove(Property.BACKGROUND_COLOR_TYPE);
		}
	}

	/**
	 * Sets the background color.
	 * 
	 * @param color the background color.
	 */
	public void setBackgroundColor(IsColor color) {
		setBackgroundColor(IsColor.checkAndGetValue(color));
	}

	/**
	 * Sets the background gradient.
	 * 
	 * @param gradient the background gradient.
	 */
	public void setBackgroundColor(Gradient gradient) {
		setValue(Property.BACKGROUND_COLOR, gradient);
		// checks if argument is consistent
		if (gradient != null) {
			// sets the color type
			setValue(Property.BACKGROUND_COLOR_TYPE, ColorType.GRADIENT);
		} else {
			// if here, the gradient is not consistent
			// removes type
			remove(Property.BACKGROUND_COLOR_TYPE);
		}
	}

	/**
	 * Sets the background pattern.
	 * 
	 * @param pattern the background pattern.
	 */
	public void setBackgroundColor(Pattern pattern) {
		setValue(Property.BACKGROUND_COLOR, pattern);
		// checks if argument is consistent
		if (pattern != null) {
			// sets the color type
			setValue(Property.BACKGROUND_COLOR_TYPE, ColorType.PATTERN);
		} else {
			// if here, the pattern is not consistent
			// removes type
			remove(Property.BACKGROUND_COLOR_TYPE);
		}
	}

	/**
	 * Sets the ID of the X scale to bind onto.
	 * 
	 * @param scaleId the ID of the X scale to bind onto
	 */
	public void setXScaleID(String scaleId) {
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
	public void setXScaleID(IsScaleId scaleId) {
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
	public IsScaleId getXScaleID() {
		return getValue(Property.X_SCALE_ID, defaultValues.getXScaleID());
	}

	/**
	 * Sets the right edge of the box.
	 * 
	 * @param max the right edge of the box
	 */
	public void setXMax(String max) {
		setValue(Property.X_MAX, max);
		// resets callback
		setXMax((AnnotationValueCallback) null);
	}

	/**
	 * Sets the right edge of the box.
	 * 
	 * @param max the right edge of the box
	 */
	public void setXMax(double max) {
		setValue(Property.X_MAX, max);
		// resets callback
		setXMax((AnnotationValueCallback) null);
	}

	/**
	 * Sets the right edge of the box.
	 * 
	 * @param max the right edge of the box
	 */
	public void setXMax(Date max) {
		setValue(Property.X_MAX, max);
		// resets callback
		setXMax((AnnotationValueCallback) null);
	}

	/**
	 * Returns the right edge of the box.
	 * 
	 * @return the right edge of the box
	 */
	@Override
	public String getXMaxAsString() {
		return getValueForMultipleKeyTypes(Property.X_MAX, defaultValues.getXMaxAsString());
	}

	/**
	 * Returns the right edge of the box.
	 * 
	 * @return the right edge of the box
	 */
	@Override
	public double getXMaxAsDouble() {
		return getValueForMultipleKeyTypes(Property.X_MAX, defaultValues.getXMaxAsDouble());
	}

	/**
	 * Returns the right edge of the box.
	 * 
	 * @return the right edge of the box
	 */
	@Override
	public Date getXMaxAsDate() {
		return getValueForMultipleKeyTypes(Property.X_MAX, defaultValues.getXMaxAsDate());
	}

	/**
	 * Sets the left edge of the box, in units along the x axis.
	 * 
	 * @param min the left edge of the box
	 */
	public void setXMin(String min) {
		setValue(Property.X_MIN, min);
		// resets callback
		setXMin((AnnotationValueCallback) null);
	}

	/**
	 * Sets the left edge of the box, in units along the x axis.
	 * 
	 * @param min the left edge of the box
	 */
	public void setXMin(double min) {
		setValue(Property.X_MIN, min);
		// resets callback
		setXMin((AnnotationValueCallback) null);
	}

	/**
	 * Sets the left edge of the box, in units along the x axis.
	 * 
	 * @param min the left edge of the box
	 */
	public void setXMin(Date min) {
		setValue(Property.X_MIN, min);
		// resets callback
		setXMin((AnnotationValueCallback) null);
	}

	/**
	 * Returns the left edge of the box, in units along the x axis.
	 * 
	 * @return the left edge of the box
	 */
	@Override
	public String getXMinAsString() {
		return getValueForMultipleKeyTypes(Property.X_MIN, defaultValues.getXMinAsString());
	}

	/**
	 * Returns the left edge of the box, in units along the x axis.
	 * 
	 * @return the left edge of the box
	 */
	@Override
	public double getXMinAsDouble() {
		return getValueForMultipleKeyTypes(Property.X_MIN, defaultValues.getXMinAsDouble());
	}

	/**
	 * Returns the left edge of the box, in units along the x axis.
	 * 
	 * @return the left edge of the box
	 */
	@Override
	public Date getXMinAsDate() {
		return getValueForMultipleKeyTypes(Property.X_MIN, defaultValues.getXMinAsDate());
	}

	/**
	 * Sets the ID of the Y scale to bind onto.
	 * 
	 * @param scaleId the ID of the Y scale to bind onto
	 */
	public void setYScaleID(String scaleId) {
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
	public void setYScaleID(IsScaleId scaleId) {
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
	public IsScaleId getYScaleID() {
		return getValue(Property.Y_SCALE_ID, defaultValues.getYScaleID());
	}

	/**
	 * Sets the top edge of the box in units along the y axis.
	 * 
	 * @param max the top edge of the box in units along the y axis
	 */
	public void setYMax(String max) {
		setValue(Property.Y_MAX, max);
		// resets callback
		setYMax((AnnotationValueCallback) null);
	}

	/**
	 * Sets the top edge of the box in units along the y axis.
	 * 
	 * @param max the top edge of the box in units along the y axis
	 */
	public void setYMax(double max) {
		setValue(Property.Y_MAX, max);
		// resets callback
		setYMax((AnnotationValueCallback) null);
	}

	/**
	 * Sets the top edge of the box in units along the y axis.
	 * 
	 * @param max the top edge of the box in units along the y axis
	 */
	public void setYMax(Date max) {
		setValue(Property.Y_MAX, max);
		// resets callback
		setYMax((AnnotationValueCallback) null);
	}

	/**
	 * Returns the top edge of the box in units along the y axis.
	 * 
	 * @return the top edge of the box in units along the y axis
	 */
	@Override
	public String getYMaxAsString() {
		return getValueForMultipleKeyTypes(Property.Y_MAX, defaultValues.getYMaxAsString());
	}

	/**
	 * Returns the top edge of the box in units along the y axis.
	 * 
	 * @return the top edge of the box in units along the y axis
	 */
	@Override
	public double getYMaxAsDouble() {
		return getValueForMultipleKeyTypes(Property.Y_MAX, defaultValues.getYMaxAsDouble());
	}

	/**
	 * Returns the top edge of the box in units along the y axis.
	 * 
	 * @return the top edge of the box in units along the y axis
	 */
	@Override
	public Date getYMaxAsDate() {
		return getValueForMultipleKeyTypes(Property.Y_MAX, defaultValues.getYMaxAsDate());
	}

	/**
	 * Sets the bottom edge of the box.
	 * 
	 * @param min the bottom edge of the box
	 */
	public void setYMin(String min) {
		setValue(Property.Y_MIN, min);
		// resets callback
		setYMin((AnnotationValueCallback) null);
	}

	/**
	 * Sets the bottom edge of the box.
	 * 
	 * @param min the bottom edge of the box
	 */
	public void setYMin(double min) {
		setValue(Property.Y_MIN, min);
		// resets callback
		setYMin((AnnotationValueCallback) null);
	}

	/**
	 * Sets the bottom edge of the box.
	 * 
	 * @param min the bottom edge of the box
	 */
	public void setYMin(Date min) {
		setValue(Property.Y_MIN, min);
		// resets callback
		setYMin((AnnotationValueCallback) null);
	}

	/**
	 * Returns the bottom edge of the box.
	 * 
	 * @return the bottom edge of the box
	 */
	@Override
	public String getYMinAsString() {
		return getValueForMultipleKeyTypes(Property.Y_MIN, defaultValues.getYMinAsString());
	}

	/**
	 * Returns the bottom edge of the box.
	 * 
	 * @return the bottom edge of the box
	 */
	@Override
	public double getYMinAsDouble() {
		return getValueForMultipleKeyTypes(Property.Y_MIN, defaultValues.getYMinAsDouble());
	}

	/**
	 * Returns the bottom edge of the box.
	 * 
	 * @return the bottom edge of the box
	 */
	@Override
	public Date getYMinAsDate() {
		return getValueForMultipleKeyTypes(Property.Y_MIN, defaultValues.getYMinAsDate());
	}

	/**
	 * Returns the data value callback to calculate minimum value of box on X scale.
	 * 
	 * @return the data value callback to calculate minimum value of box on X scale
	 */
	@Override
	public AnnotationValueCallback getXMinCallback() {
		// checks if not consistent
		if (xMinCallback == null) {
			// then checks from default
			return defaultValues.getXMinCallback();
		}
		return xMinCallback;
	}

	/**
	 * @param xMinCallback the xMinCallback to set
	 */
	public void setXMin(AnnotationValueCallback xMinCallback) {
		// stores callback
		this.xMinCallback = xMinCallback;
		// checks if callback is consistent
		if (xMinCallback != null) {
			// resets the value configuration
			remove(Property.X_MIN);
		}
	}

	/**
	 * Returns the data value callback to calculate maximum value of box on X scale.
	 * 
	 * @return the data value callback to calculate maximum value of box on X scale
	 */
	@Override
	public AnnotationValueCallback getXMaxCallback() {
		// checks if not consistent
		if (xMaxCallback == null) {
			// then checks from default
			return defaultValues.getXMaxCallback();
		}
		return xMaxCallback;
	}

	/**
	 * Sets the data value callback to calculate maximum value of box on X scale.
	 * 
	 * @param xMaxCallback the data value callback to calculate maximum value of box on X scale
	 */
	public void setXMax(AnnotationValueCallback xMaxCallback) {
		// stores callback
		this.xMaxCallback = xMaxCallback;
		// checks if callback is consistent
		if (xMaxCallback != null) {
			// resets the value configuration
			remove(Property.X_MAX);
		}
	}

	/**
	 * Returns the data value callback to calculate minimum value of box on Y scale.
	 * 
	 * @return the data value callback to calculate minimum value of box on Y scale
	 */
	@Override
	public AnnotationValueCallback getYMinCallback() {
		// checks if not consistent
		if (yMinCallback == null) {
			// then checks from default
			return defaultValues.getYMinCallback();
		}
		return yMinCallback;
	}

	/**
	 * Sets the data value callback to calculate minimum value of box on Y scale.
	 * 
	 * @param yMinCallback the data value callback to calculate minimum value of box on Y scale
	 */
	public void setYMin(AnnotationValueCallback yMinCallback) {
		// stores callback
		this.yMinCallback = yMinCallback;
		// checks if callback is consistent
		if (yMinCallback != null) {
			// resets the value configuration
			remove(Property.Y_MIN);
		}	
	}

	/**
	 * Returns the data value callback to calculate maximum value of box on Y scale.
	 * 
	 * @return the data value callback to calculate maximum value of box on Y scale
	 */
	@Override
	public AnnotationValueCallback getYMaxCallback() {
		// checks if not consistent
		if (yMaxCallback == null) {
			// then checks from default
			return defaultValues.getYMaxCallback();
		}
		return yMaxCallback;
	}

	/**
	 * Sets the data value callback to calculate maximum value of box on Y scale.
	 * 
	 * @param yMaxCallback the data value callback to calculate maximum value of box on Y scale
	 */
	public void setYMax(AnnotationValueCallback yMaxCallback) {
		// stores callback
		this.yMaxCallback = yMaxCallback;
		// checks if callback is consistent
		if (yMaxCallback != null) {
			// resets the value configuration
			remove(Property.Y_MAX);
		}		
	}

}
