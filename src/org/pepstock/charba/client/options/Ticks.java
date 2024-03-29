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
package org.pepstock.charba.client.options;

import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.IsDefaultTicks;
import org.pepstock.charba.client.enums.CrossAlign;
import org.pepstock.charba.client.enums.TickAlign;
import org.pepstock.charba.client.enums.TickSource;

/**
 * All configuration for ticks of a chart.<br>
 * It defines options for the tick marks that are generated by the axis.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Ticks extends AbstractModel<AbstractScale, IsDefaultTicks> implements IsDefaultTicks, HasFont {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		// commons
		DISPLAY("display"),
		MAJOR("major"),
		PADDING("padding"),
		TEXT_STROKE_COLOR("textStrokeColor"),
		TEXT_STROKE_WIDTH("textStrokeWidth"),
		Z("z"),
		// cartesian
		AUTO_SKIP("autoSkip"),
		AUTO_SKIP_PADDING("autoSkipPadding"),
		ALIGN("align"),
		CROSS_ALIGN("crossAlign"),
		INCLUDE_BOUNDS("includeBounds"),
		LABEL_OFFSET("labelOffset"),
		MAX_ROTATION("maxRotation"),
		MIN_ROTATION("minRotation"),
		MIRROR("mirror"),
		SAMPLE_SIZE("sampleSize"),
		// common linear
		FORMAT("format"),
		COUNT("count"),
		// linear cartesian
		MAX_TICKS_LIMIT("maxTicksLimit"),
		PRECISION("precision"),
		STEP_SIZE("stepSize"),
		// linear radial
		BACKDROP_COLOR("backdropColor"),
		BACKDROP_PADDING("backdropPadding"),
		SHOW_LABEL_BACKDROP("showLabelBackdrop"),
		// time ticks
		SOURCE("source");

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

	// instance of major
	private final Major major;
	// instance of font container
	private final FontContainer fontContainer;
	// instance of number format
	private final TicksNumberFormat numberFormat;
	// padding instance
	private final Padding backdropPadding;

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param scale scale/axis of object.
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	Ticks(AbstractScale scale, Key childKey, IsDefaultTicks defaultValues, NativeObject nativeObject) {
		super(scale, childKey, defaultValues, nativeObject);
		// gets sub elements
		this.major = new Major(this, Property.MAJOR, getDefaultValues().getMajor(), getValue(Property.MAJOR));
		this.numberFormat = new TicksNumberFormat(this, Property.FORMAT, getDefaultValues().getNumberFormat(), getValue(Property.FORMAT));
		// creates font and backdrop padding containers
		this.fontContainer = new FontContainer(this, getDefaultValues(), getNativeObject());
		this.backdropPadding = loadPadding(Property.BACKDROP_PADDING, getDefaultValues().getBackdropPadding());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.HasFont#getFontContainer()
	 */
	@Override
	public FontContainer getFontContainer() {
		return fontContainer;
	}

	/**
	 * Returns the major element.
	 * 
	 * @return the major
	 */
	@Override
	public Major getMajor() {
		return major;
	}

	/**
	 * Returns the number formatting options.
	 * 
	 * @return the number formatting options
	 */
	@Override
	public TicksNumberFormat getNumberFormat() {
		return numberFormat;
	}

	/**
	 * Returns the padding of label backdrop.
	 * 
	 * @return the padding of label backdrop
	 */
	@Override
	public Padding getBackdropPadding() {
		return backdropPadding;
	}

	/**
	 * If <code>true</code>, show tick marks.
	 * 
	 * @param display if <code>true</code>, show tick marks.
	 */
	public void setDisplay(boolean display) {
		setValueAndAddToParent(Property.DISPLAY, display);
	}

	/**
	 * If <code>true</code>, show tick marks.
	 * 
	 * @return if <code>true</code>, show tick marks.
	 */
	@Override
	public boolean isDisplay() {
		return getValue(Property.DISPLAY, getDefaultValues().isDisplay());
	}

	/**
	 * If <code>true</code>, automatically calculates how many labels that can be shown and hides labels accordingly.<br>
	 * Turn it off to show all labels no matter what
	 * 
	 * @param autoSkip if <code>true</code>, automatically calculates how many labels that can be shown and hides labels accordingly.<br>
	 *            Turn it off to show all labels no matter what
	 */
	public void setAutoSkip(boolean autoSkip) {
		setValueAndAddToParent(Property.AUTO_SKIP, autoSkip);
	}

	/**
	 * If <code>true</code>, automatically calculates how many labels that can be shown and hides labels accordingly.<br>
	 * Turn it off to show all labels no matter what
	 * 
	 * @return if <code>true</code>, automatically calculates how many labels that can be shown and hides labels accordingly.<br>
	 *         Turn it off to show all labels no matter what.
	 */
	@Override
	public boolean isAutoSkip() {
		return getValue(Property.AUTO_SKIP, getDefaultValues().isAutoSkip());
	}

	/**
	 * Sets the padding between the ticks on the horizontal axis when autoSkip is enabled.<br>
	 * Note: Only applicable to horizontal scales.
	 * 
	 * @param autoSkipPadding padding between the ticks on the horizontal axis when autoSkip is enabled.<br>
	 *            Note: Only applicable to horizontal scales.
	 */
	public void setAutoSkipPadding(int autoSkipPadding) {
		setValueAndAddToParent(Property.AUTO_SKIP_PADDING, Checker.positiveOrZero(autoSkipPadding));
	}

	/**
	 * Returns the padding between the ticks on the horizontal axis when autoSkip is enabled.<br>
	 * Note: Only applicable to horizontal scales.
	 * 
	 * @return padding between the ticks on the horizontal axis when autoSkip is enabled.<br>
	 *         Note: Only applicable to horizontal scales.
	 */
	@Override
	public int getAutoSkipPadding() {
		return getValue(Property.AUTO_SKIP_PADDING, getDefaultValues().getAutoSkipPadding());
	}

	/**
	 * If <code>true</code>, the defined min and maximum values should be presented as ticks even if they are not "nice"
	 * 
	 * @param includeBounds if <code>true</code>, the defined min and maximum values should be presented as ticks even if they are not "nice"
	 */
	public void setIncludeBounds(boolean includeBounds) {
		setValueAndAddToParent(Property.INCLUDE_BOUNDS, includeBounds);
	}

	/**
	 * If <code>true</code>, the defined minimum and maximum values should be presented as ticks even if they are not "nice".
	 * 
	 * @return if <code>true</code>, the defined min and maximum values should be presented as ticks even if they are not "nice"
	 */
	@Override
	public boolean isIncludeBounds() {
		return getValue(Property.INCLUDE_BOUNDS, getDefaultValues().isIncludeBounds());
	}

	/**
	 * Sets the distance in pixels to offset the label from the center point of the tick (in the y direction for the x axis, and the x direction for the y axis).<br>
	 * Note: this can cause labels at the edges to be cropped by the edge of the canvas.
	 * 
	 * @param labelOffset the distance in pixels to offset the label from the center point of the tick (in the y direction for the x axis, and the x direction for the y axis)
	 */
	public void setLabelOffset(int labelOffset) {
		setValueAndAddToParent(Property.LABEL_OFFSET, labelOffset);
	}

	/**
	 * Returns the distance in pixels to offset the label from the center point of the tick (in the y direction for the x axis, and the x direction for the y axis).<br>
	 * Note: this can cause labels at the edges to be cropped by the edge of the canvas.
	 * 
	 * @return the distance in pixels to offset the label from the center point of the tick (in the y direction for the x axis, and the x direction for the y axis).
	 */
	@Override
	public int getLabelOffset() {
		return getValue(Property.LABEL_OFFSET, getDefaultValues().getLabelOffset());
	}

	/**
	 * Sets the number of ticks to generate.<br>
	 * If specified, this overrides the automatic generation.
	 * 
	 * @param count the number of ticks to generate.<br>
	 *            If specified, this overrides the automatic generation
	 */
	public void setCount(int count) {
		setValueAndAddToParent(Property.COUNT, Checker.positiveOrZero(count));
	}

	/**
	 * Returns the number of ticks to generate.<br>
	 * If specified, this overrides the automatic generation.
	 * 
	 * @return the number of ticks to generate.<br>
	 *         If specified, this overrides the automatic generation
	 */
	@Override
	public int getCount() {
		return getValue(Property.COUNT, getDefaultValues().getCount());
	}

	/**
	 * Sets the maximum rotation for tick labels when rotating to condense labels.<br>
	 * Note: Rotation doesn't occur until necessary.<br>
	 * Note: Only applicable to horizontal scales.
	 * 
	 * @param maxRotation maximum rotation for tick labels when rotating to condense labels.<br>
	 *            Note: Rotation doesn't occur until necessary.<br>
	 *            Note: Only applicable to horizontal scales.
	 */
	public void setMaxRotation(int maxRotation) {
		setValueAndAddToParent(Property.MAX_ROTATION, maxRotation);
	}

	/**
	 * Returns the maximum rotation for tick labels when rotating to condense labels.<br>
	 * Note: Rotation doesn't occur until necessary. Note: Only applicable to horizontal scales.
	 * 
	 * @return maximum rotation for tick labels when rotating to condense labels.<br>
	 *         Note: Rotation doesn't occur until necessary. Note: Only applicable to horizontal scales.
	 */
	@Override
	public int getMaxRotation() {
		return getValue(Property.MAX_ROTATION, getDefaultValues().getMaxRotation());
	}

	/**
	 * Sets the minimum rotation for tick labels.<br>
	 * Note: Only applicable to horizontal scales.
	 * 
	 * @param minRotation minimum rotation for tick labels.<br>
	 *            Note: Only applicable to horizontal scales.
	 */
	public void setMinRotation(int minRotation) {
		setValueAndAddToParent(Property.MIN_ROTATION, minRotation);
	}

	/**
	 * Returns the minimum rotation for tick labels.<br>
	 * Note: Only applicable to horizontal scales.
	 * 
	 * @return minimum rotation for tick labels.<br>
	 *         Note: Only applicable to horizontal scales.
	 */
	@Override
	public int getMinRotation() {
		return getValue(Property.MIN_ROTATION, getDefaultValues().getMinRotation());
	}

	/**
	 * Sets the flips tick labels around axis, displaying the labels inside the chart instead of outside.<br>
	 * Note: Only applicable to vertical scales.
	 * 
	 * @param mirror flips tick labels around axis, displaying the labels inside the chart instead of outside.<br>
	 *            Note: Only applicable to vertical scales.
	 */
	public void setMirror(boolean mirror) {
		setValueAndAddToParent(Property.MIRROR, mirror);
	}

	/**
	 * Returns the flips tick labels around axis, displaying the labels inside the chart instead of outside.<br>
	 * Note: Only applicable to vertical scales.
	 * 
	 * @return flips tick labels around axis, displaying the labels inside the chart instead of outside.<br>
	 *         Note: Only applicable to vertical scales.
	 */
	@Override
	public boolean isMirror() {
		return getValue(Property.MIRROR, getDefaultValues().isMirror());
	}

	/**
	 * Sets the padding between the tick label and the axis.<br>
	 * When set on a vertical axis, this applies in the horizontal (X) direction.<br>
	 * When set on a horizontal axis, this applies in the vertical (Y) direction.
	 * 
	 * @param padding padding between the tick label and the axis.<br>
	 *            When set on a vertical axis, this applies in the horizontal (X) direction.<br>
	 *            When set on a horizontal axis, this applies in the vertical (Y) direction.
	 */
	public void setPadding(int padding) {
		setValueAndAddToParent(Property.PADDING, Checker.positiveOrZero(padding));
	}

	/**
	 * Returns the padding between the tick label and the axis.<br>
	 * When set on a vertical axis, this applies in the horizontal (X) direction.<br>
	 * When set on a horizontal axis, this applies in the vertical (Y) direction.
	 * 
	 * @return padding between the tick label and the axis.<br>
	 *         When set on a vertical axis, this applies in the horizontal (X) direction.<br>
	 *         When set on a horizontal axis, this applies in the vertical (Y) direction.
	 */
	@Override
	public int getPadding() {
		return getValue(Property.PADDING, getDefaultValues().getPadding());
	}

	/**
	 * Sets the maximum number of ticks and grid to show.
	 * 
	 * @param maxTicksLimit maximum number of ticks and grid to show.
	 */
	public void setMaxTicksLimit(int maxTicksLimit) {
		setValueAndAddToParent(Property.MAX_TICKS_LIMIT, Checker.positiveOrZero(maxTicksLimit));
	}

	/**
	 * Returns the maximum number of ticks and grid to show.
	 * 
	 * @return maximum number of ticks and grid to show.
	 */
	@Override
	public int getMaxTicksLimit() {
		return getValue(Property.MAX_TICKS_LIMIT, getDefaultValues().getMaxTicksLimit());
	}

	/**
	 * Sets the user defined fixed step size for the scale.
	 * 
	 * @param stepSize user defined fixed step size for the scale.
	 */
	public void setStepSize(double stepSize) {
		setValueAndAddToParent(Property.STEP_SIZE, Checker.positiveOrDefault(stepSize, getDefaultValues().getStepSize()));
	}

	/**
	 * Returns the user defined fixed step size for the scale.
	 * 
	 * @return user defined fixed step size for the scale.
	 */
	@Override
	public double getStepSize() {
		return getValue(Property.STEP_SIZE, getDefaultValues().getStepSize());
	}

	/**
	 * Sets the color of label backdrops.
	 * 
	 * @param backdropColor color of label backdrops.
	 */
	public void setBackdropColor(IsColor backdropColor) {
		setBackdropColor(IsColor.checkAndGetValue(backdropColor));
	}

	/**
	 * Sets the color of label backdrops.
	 * 
	 * @param backdropColor color of label backdrops.
	 */
	public void setBackdropColor(String backdropColor) {
		setValueAndAddToParent(Property.BACKDROP_COLOR, backdropColor);
	}

	/**
	 * Returns the color of label backdrops.
	 * 
	 * @return color of label backdrops.
	 */
	@Override
	public String getBackdropColorAsString() {
		return getValue(Property.BACKDROP_COLOR, getDefaultValues().getBackdropColorAsString());
	}

	/**
	 * Returns the color of label backdrops.
	 * 
	 * @return color of label backdrops.
	 */
	public IsColor getBackdropColor() {
		return ColorBuilder.parse(getBackdropColorAsString());
	}

	/**
	 * If <code>true</code>, draw a background behind the tick labels.
	 * 
	 * @param showLabelBackdrop if <code>true</code>, draw a background behind the tick labels.
	 */
	public void setShowLabelBackdrop(boolean showLabelBackdrop) {
		setValueAndAddToParent(Property.SHOW_LABEL_BACKDROP, showLabelBackdrop);
	}

	/**
	 * If <code>true</code>, draw a background behind the tick labels.
	 * 
	 * @return if <code>true</code>, draw a background behind the tick labels.
	 */
	@Override
	public boolean isShowLabelBackdrop() {
		return getValue(Property.SHOW_LABEL_BACKDROP, getDefaultValues().isShowLabelBackdrop());
	}

	/**
	 * Sets the property controls the ticks generation.
	 * 
	 * @param source property controls the ticks generation.
	 */
	public void setSource(TickSource source) {
		setValueAndAddToParent(Property.SOURCE, source);
	}

	/**
	 * Returns the property controls the ticks generation.
	 * 
	 * @return property controls the ticks generation.
	 */
	@Override
	public TickSource getSource() {
		return getValue(Property.SOURCE, TickSource.values(), getDefaultValues().getSource());
	}

	/**
	 * If defined and stepSize is not specified, the step size will be rounded to this many decimal places.
	 * 
	 * @param precision if defined and stepSize is not specified, the step size will be rounded to this many decimal places.
	 */
	public void setPrecision(int precision) {
		setValueAndAddToParent(Property.PRECISION, Checker.positiveOrZero(precision));
	}

	/**
	 * If defined and stepSize is not specified, the step size will be rounded to this many decimal places.
	 * 
	 * @return if defined and stepSize is not specified, the step size will be rounded to this many decimal places.
	 */
	@Override
	public int getPrecision() {
		return getValue(Property.PRECISION, getDefaultValues().getPrecision());
	}

	/**
	 * Sets z-index of tick layer. Useful when ticks are drawn on chart area.<br>
	 * Values less than or equals to 0 are drawn under datasets, greater than 0 on top.
	 * 
	 * @param z z-index of tick layer. Useful when ticks are drawn on chart area.<br>
	 *            Values less than or equals to 0 are drawn under datasets, greater than 0 on top.
	 */
	public void setZ(int z) {
		setValueAndAddToParent(Property.Z, z);
	}

	/**
	 * Returns z-index of tick layer. Useful when ticks are drawn on chart area.<br>
	 * Values less than or equals to 0 are drawn under datasets, greater than 0 on top.
	 * 
	 * @return z-index of tick layer. Useful when ticks are drawn on chart area.<br>
	 *         Values less than or equals to 0 are drawn under datasets, greater than 0 on top.
	 */
	@Override
	public int getZ() {
		return getValue(Property.Z, getDefaultValues().getZ());
	}

	/**
	 * Sets the number of ticks to examine when deciding how many labels will fit.<br>
	 * Setting a smaller value will be faster, but may be less accurate when there is large variability in label length.
	 * 
	 * @param sampleSize the number of ticks to examine when deciding how many labels will fit.
	 */
	public void setSampleSize(int sampleSize) {
		setValueAndAddToParent(Property.SAMPLE_SIZE, Checker.positiveOrZero(sampleSize));
	}

	/**
	 * Returns the number of ticks to examine when deciding how many labels will fit.<br>
	 * Setting a smaller value will be faster, but may be less accurate when there is large variability in label length.
	 * 
	 * @return the number of ticks to examine when deciding how many labels will fit.
	 */
	@Override
	public int getSampleSize() {
		return getValue(Property.SAMPLE_SIZE, getDefaultValues().getSampleSize());
	}

	/**
	 * Sets the tick alignment along the axis.
	 * 
	 * @param align the tick alignment along the axis
	 */
	public void setAlign(TickAlign align) {
		setValueAndAddToParent(Property.ALIGN, align);
	}

	/**
	 * Returns the tick alignment along the axis.
	 * 
	 * @return the tick alignment along the axis
	 */
	@Override
	public TickAlign getAlign() {
		return getValue(Property.ALIGN, TickAlign.values(), getDefaultValues().getAlign());
	}

	/**
	 * Sets the tick alignment perpendicular to the axis.
	 * 
	 * @param crossAlign the tick alignment perpendicular to the axis
	 */
	public void setCrossAlign(CrossAlign crossAlign) {
		setValueAndAddToParent(Property.CROSS_ALIGN, crossAlign);
	}

	/**
	 * Returns the tick alignment perpendicular to the axis.
	 * 
	 * @return the tick alignment perpendicular to the axis
	 */
	@Override
	public CrossAlign getCrossAlign() {
		return getValue(Property.CROSS_ALIGN, CrossAlign.values(), getDefaultValues().getCrossAlign());
	}

	/**
	 * Sets the text stroke color.
	 * 
	 * @param color the text stroke color
	 */
	public final void setTextStrokeColor(IsColor color) {
		setTextStrokeColor(IsColor.checkAndGetValue(color));
	}

	/**
	 * Sets the text stroke color.
	 * 
	 * @param color the text stroke color.
	 */
	public final void setTextStrokeColor(String color) {
		setValue(Property.TEXT_STROKE_COLOR, color);
	}

	/**
	 * Returns the text stroke color as string.
	 * 
	 * @return the text stroke color as string.
	 */
	@Override
	public final String getTextStrokeColorAsString() {
		return getValue(Property.TEXT_STROKE_COLOR, getDefaultValues().getTextStrokeColorAsString());
	}

	/**
	 * Returns the text stroke color.
	 * 
	 * @return the text stroke color.
	 */
	public final IsColor getTextStrokeColor() {
		return ColorBuilder.parse(getTextStrokeColorAsString());
	}

	/**
	 * Sets the text stroke width.
	 * 
	 * @param textStrokeWidth the text stroke width.
	 */
	public final void setTextStrokeWidth(int textStrokeWidth) {
		setValue(Property.TEXT_STROKE_WIDTH, Checker.positiveOrZero(textStrokeWidth));
	}

	/**
	 * Returns the text stroke width.
	 * 
	 * @return the text stroke width.
	 */
	@Override
	public final int getTextStrokeWidth() {
		return getValue(Property.TEXT_STROKE_WIDTH, getDefaultValues().getTextStrokeWidth());
	}

}