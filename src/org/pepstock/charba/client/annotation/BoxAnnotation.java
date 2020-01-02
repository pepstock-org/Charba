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

import org.pepstock.charba.client.annotation.enums.AnnotationType;
import org.pepstock.charba.client.colors.Color;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.Key;

/**
 * Implements a BOX annotation which draws a box into a chart.<br>
 * If one of the axes is not specified, the box will take the entire chart dimension.<br>
 * The 4 coordinates, xMin, xMax, yMin, yMax are optional. If not specified, the box is expanded out to the edges.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class BoxAnnotation extends AbstractAnnotation implements IsDefaultsBoxAnnotation {

	/**
	 * Default box annotation ID, <b>{@value DEFAULT_ID}</b>.
	 */
	public static final String DEFAULT_ID = "a-box-1";

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
		BACKGROUND_COLOR("backgroundColor");

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

	/**
	 * Creates a box annotation to be added to an {@link AnnotationOptions} instance.
	 */
	public BoxAnnotation() {
		super(AnnotationType.BOX);
	}

	/**
	 * Returns the color of the border of annotation.
	 * 
	 * @return the color of the border of annotation
	 */
	@Override
	public String getBorderColorAsString() {
		return getValue(AbstractAnnotation.Property.BORDER_COLOR, IsDefaultsBoxAnnotation.super.getBorderColorAsString());
	}

	/**
	 * Returns the width of the border in pixels.
	 * 
	 * @return the width of the border in pixels.
	 */
	@Override
	public int getBorderWidth() {
		return getValue(AbstractAnnotation.Property.BORDER_WIDTH, IsDefaultsBoxAnnotation.super.getBorderWidth());
	}

	/**
	 * Sets the color of the background of annotation.
	 * 
	 * @param backgroundColor the color of the background of annotation
	 */
	public void setBackgroundColor(IsColor backgroundColor) {
		setBackgroundColor(checkValue(backgroundColor));
	}

	/**
	 * Sets the color of the background of annotation.
	 * 
	 * @param backgroundColor the color of the background of annotation
	 */
	public void setBackgroundColor(String backgroundColor) {
		// stores value
		setValue(Property.BACKGROUND_COLOR, backgroundColor);
	}

	/**
	 * Returns the color of the background of annotation.
	 * 
	 * @return the color of the background of annotation
	 */
	@Override
	public String getBackgroundColorAsString() {
		return getValue(Property.BACKGROUND_COLOR, IsDefaultsBoxAnnotation.super.getBackgroundColorAsString());
	}

	/**
	 * Returns the color of the background of annotation.
	 * 
	 * @return the color of the background of annotation
	 */
	public IsColor getBackgroundColor() {
		return ColorBuilder.parse(getBackgroundColorAsString());
	}

	/**
	 * Sets the ID of the X scale to bind onto.
	 * 
	 * @param scaleId the ID of the X scale to bind onto
	 */
	public void setXScaleID(String scaleId) {
		setValue(Property.X_SCALE_ID, scaleId);
	}

	/**
	 * Returns the ID of the X scale to bind onto.
	 * 
	 * @return the ID of the X scale to bind onto
	 */
	@Override
	public String getXScaleID() {
		return getValue(Property.X_SCALE_ID, IsDefaultsBoxAnnotation.super.getXScaleID());
	}

	/**
	 * Sets the right edge of the box.
	 * 
	 * @param max the right edge of the box
	 */
	public void setXMax(String max) {
		setValue(Property.X_MAX, max);
	}

	/**
	 * Sets the right edge of the box.
	 * 
	 * @param max the right edge of the box
	 */
	public void setXMax(double max) {
		setValue(Property.X_MAX, max);
	}

	/**
	 * Sets the right edge of the box.
	 * 
	 * @param max the right edge of the box
	 */
	public void setXMax(Date max) {
		setValue(Property.X_MAX, max);
	}

	/**
	 * Returns the right edge of the box.
	 * 
	 * @return the right edge of the box
	 */
	@Override
	public String getXMaxAsString() {
		return getValueForMultipleKeyTypes(Property.X_MAX, IsDefaultsBoxAnnotation.super.getXMaxAsString());
	}

	/**
	 * Returns the right edge of the box.
	 * 
	 * @return the right edge of the box
	 */
	@Override
	public double getXMaxAsDouble() {
		return getValueForMultipleKeyTypes(Property.X_MAX, IsDefaultsBoxAnnotation.super.getXMaxAsDouble());
	}

	/**
	 * Returns the right edge of the box.
	 * 
	 * @return the right edge of the box
	 */
	@Override
	public Date getXMaxAsDate() {
		return getValueForMultipleKeyTypes(Property.X_MAX, IsDefaultsBoxAnnotation.super.getXMaxAsDate());
	}

	/**
	 * Sets the left edge of the box, in units along the x axis.
	 * 
	 * @param min the left edge of the box
	 */
	public void setXMin(String min) {
		setValue(Property.X_MIN, min);
	}

	/**
	 * Sets the left edge of the box, in units along the x axis.
	 * 
	 * @param min the left edge of the box
	 */
	public void setXMin(double min) {
		setValue(Property.X_MIN, min);
	}

	/**
	 * Sets the left edge of the box, in units along the x axis.
	 * 
	 * @param min the left edge of the box
	 */
	public void setXMin(Date min) {
		setValue(Property.X_MIN, min);
	}

	/**
	 * Returns the left edge of the box, in units along the x axis.
	 * 
	 * @return the left edge of the box
	 */
	@Override
	public String getXMinAsString() {
		return getValueForMultipleKeyTypes(Property.X_MIN, IsDefaultsBoxAnnotation.super.getXMinAsString());
	}

	/**
	 * Returns the left edge of the box, in units along the x axis.
	 * 
	 * @return the left edge of the box
	 */
	@Override
	public double getXMinAsDouble() {
		return getValueForMultipleKeyTypes(Property.X_MIN, IsDefaultsBoxAnnotation.super.getXMinAsDouble());
	}

	/**
	 * Returns the left edge of the box, in units along the x axis.
	 * 
	 * @return the left edge of the box
	 */
	@Override
	public Date getXMinAsDate() {
		return getValueForMultipleKeyTypes(Property.X_MIN, IsDefaultsBoxAnnotation.super.getXMinAsDate());
	}

	/**
	 * Sets the ID of the Y scale to bind onto.
	 * 
	 * @param scaleId the ID of the Y scale to bind onto
	 */
	public void setYScaleID(String scaleId) {
		setValue(Property.Y_SCALE_ID, scaleId);
	}

	/**
	 * Returns the ID of the Y scale to bind onto.
	 * 
	 * @return the ID of the Y scale to bind onto
	 */
	@Override
	public String getYScaleID() {
		return getValue(Property.Y_SCALE_ID, IsDefaultsBoxAnnotation.super.getYScaleID());
	}

	/**
	 * Sets the top edge of the box in units along the y axis.
	 * 
	 * @param max the top edge of the box in units along the y axis
	 */
	public void setYMax(String max) {
		setValue(Property.Y_MAX, max);
	}

	/**
	 * Sets the top edge of the box in units along the y axis.
	 * 
	 * @param max the top edge of the box in units along the y axis
	 */
	public void setYMax(double max) {
		setValue(Property.Y_MAX, max);
	}

	/**
	 * Sets the top edge of the box in units along the y axis.
	 * 
	 * @param max the top edge of the box in units along the y axis
	 */
	public void setYMax(Date max) {
		setValue(Property.Y_MAX, max);
	}

	/**
	 * Returns the top edge of the box in units along the y axis.
	 * 
	 * @return the top edge of the box in units along the y axis
	 */
	@Override
	public String getYMaxAsString() {
		return getValueForMultipleKeyTypes(Property.Y_MAX, IsDefaultsBoxAnnotation.super.getYMaxAsString());
	}

	/**
	 * Returns the top edge of the box in units along the y axis.
	 * 
	 * @return the top edge of the box in units along the y axis
	 */
	@Override
	public double getYMaxAsDouble() {
		return getValueForMultipleKeyTypes(Property.Y_MAX, IsDefaultsBoxAnnotation.super.getYMaxAsDouble());
	}

	/**
	 * Returns the top edge of the box in units along the y axis.
	 * 
	 * @return the top edge of the box in units along the y axis
	 */
	@Override
	public Date getYMaxAsDate() {
		return getValueForMultipleKeyTypes(Property.Y_MAX, IsDefaultsBoxAnnotation.super.getYMaxAsDate());
	}

	/**
	 * Sets the bottom edge of the box.
	 * 
	 * @param min the bottom edge of the box
	 */
	public void setYMin(String min) {
		setValue(Property.Y_MIN, min);
	}

	/**
	 * Sets the bottom edge of the box.
	 * 
	 * @param min the bottom edge of the box
	 */
	public void setYMin(double min) {
		setValue(Property.Y_MIN, min);
	}

	/**
	 * Sets the bottom edge of the box.
	 * 
	 * @param min the bottom edge of the box
	 */
	public void setYMin(Date min) {
		setValue(Property.Y_MIN, min);
	}

	/**
	 * Returns the bottom edge of the box.
	 * 
	 * @return the bottom edge of the box
	 */
	@Override
	public String getYMinAsString() {
		return getValueForMultipleKeyTypes(Property.Y_MIN, IsDefaultsBoxAnnotation.super.getYMinAsString());
	}

	/**
	 * Returns the bottom edge of the box.
	 * 
	 * @return the bottom edge of the box
	 */
	@Override
	public double getYMinAsDouble() {
		return getValueForMultipleKeyTypes(Property.Y_MIN, IsDefaultsBoxAnnotation.super.getYMinAsDouble());
	}

	/**
	 * Returns the bottom edge of the box.
	 * 
	 * @return the bottom edge of the box
	 */
	@Override
	public Date getYMinAsDate() {
		return getValueForMultipleKeyTypes(Property.Y_MIN, IsDefaultsBoxAnnotation.super.getYMinAsDate());
	}

}
