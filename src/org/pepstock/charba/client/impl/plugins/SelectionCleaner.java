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
package org.pepstock.charba.client.impl.plugins;

import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.dom.elements.Img;
import org.pepstock.charba.client.enums.Position;
import org.pepstock.charba.client.impl.plugins.enums.Align;
import org.pepstock.charba.client.impl.plugins.enums.Render;
import org.pepstock.charba.client.options.IsFont;

/**
 * {@link DatasetsItemsSelector#ID} plugin configuration element in order to have in the the chart a clickable element to clear the selection.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class SelectionCleaner extends NativeObjectContainer implements IsDatasetsItemsSelectorDefaultSelectionCleaner {

	/**
	 * Default flag if selection cleaner must be showed in the chart, value is <b>{@value DEFAULT_DISPLAY}</b>.
	 */
	public static final boolean DEFAULT_DISPLAY = false;

	/**
	 * Default label for selection cleaner element, value is <b>{@value DEFAULT_LABEL}</b>.
	 */
	public static final String DEFAULT_LABEL = "Reset selection";

	/**
	 * Default margin from canvas for selection cleaner element, value is <b>{@value DEFAULT_MARGIN}</b>.
	 */
	public static final int DEFAULT_MARGIN = 2;

	/**
	 * Default padding for selection cleaner element, value is <b>{@value DEFAULT_PADDING}</b>.
	 */
	public static final int DEFAULT_PADDING = 4;

	/**
	 * Default spacing between label and image for selection cleaner element, value is <b>{@value DEFAULT_SPACING}</b>.
	 */
	public static final int DEFAULT_SPACING = 3;

	/**
	 * Default flag if selection cleaner will use selection area style, value is <b>{@value DEFAULT_USE_SELECTION_STYLE}</b>.
	 */
	public static final boolean DEFAULT_USE_SELECTION_STYLE = false;

	/**
	 * Default selection cleaner box alignment, {@link Align#RIGHT}.
	 */
	public static final Align DEFAULT_ALIGN = Align.RIGHT;

	/**
	 * Default selection cleaner rendering, {@link Render#IMAGE_LABEL}.
	 */
	public static final Render DEFAULT_RENDER = Render.LABEL;

	/**
	 * Default selection cleaner box position, {@link Position#BOTTOM}.
	 */
	public static final Position DEFAULT_POSITION = Position.BOTTOM;

	// defaults values for dimensions and locations
	static final double DEFAULT_VALUE = 0D;
	/**
	 * Default border width of selection cleaner element<br>
	 * Used only when use selection style is set to <code>true</code>.
	 */
	static final int BORDER_WIDTH = 1;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		DISPLAY("display"),
		LABEL("label"),
		COLOR("color"),
		FONT("font"),
		ALIGN("align"),
		POSITION("position"),
		IMAGE("image"),
		RENDER("render"),
		MARGIN("margin"),
		PADDING("padding"),
		SPACING("spacing"),
		USE_SELECTION_STYLE("useSelectionStyle"),
		// internal properties to store the points and dimensions info
		X("x"),
		Y("y"),
		WIDTH("width"),
		HEIGHT("height"),
		IMAGE_X("imageX"),
		IMAGE_Y("imageY"),
		IMAGE_WIDTH("imageWidth"),
		IMAGE_HEIGHT("imageHeight"),
		LABEL_X("labelX"),
		LABEL_Y("labelY"),
		LABEL_WIDTH("labelWidth"),
		LABEL_HEIGHT("labelHeight"),
		LAYOUT_PADDING("layoutPadding");

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

	// defaults global options instance
	private IsDatasetsItemsSelectorDefaultSelectionCleaner defaultOptions;
	// instance of font
	private final SelectionCleanerFont font;


	/**
	 * Creates new font element, using stored native object instance and the default values options.
	 * 
	 * @param nativeObject stored font values in the native object to read.
	 * @param defaultOptions default selection cleaner element options to returns the default when required.
	 */
	SelectionCleaner(NativeObject nativeObject, IsDatasetsItemsSelectorDefaultSelectionCleaner defaultOptions) {
		super(nativeObject);
		// checks the default values
		this.defaultOptions = checkDefaultValuesArgument(defaultOptions);
		// gets font
		this.font = new SelectionCleanerFont(this.defaultOptions.getFont(), getNativeObject());
	}
	
	/**
	 * Returns the the font object.
	 * 
	 * @return the font object.
	 */
	@Override
	public IsFont getFont() {
		return font;
	}

	/**
	 * Sets <code>true</code> if clear of selection label will e applied in the chart, otherwise <code>false</code>.
	 * 
	 * @param display <code>true</code> if clear of selection label will e applied in the chart, otherwise <code>false</code>
	 */
	public void setDisplay(boolean display) {
		setValue(Property.DISPLAY, display);
	}

	/**
	 * Returns <code>true</code> if clear of selection label will be applied in the chart, otherwise <code>false</code>.
	 * 
	 * @return <code>true</code> if clear of selection label will be applied in the chart, otherwise <code>false</code>
	 */
	@Override
	public boolean isDisplay() {
		return getValue(Property.DISPLAY, defaultOptions.isDisplay());
	}

	/**
	 * Sets the selection cleaner label.
	 * 
	 * @param label the selection cleaner label
	 */
	public void setLabel(String label) {
		setValue(Property.LABEL, label);
	}

	/**
	 * Returns the selection cleaner label.
	 * 
	 * @return the selection cleaner label
	 */
	@Override
	public String getLabel() {
		return getValue(Property.LABEL, defaultOptions.getLabel());
	}

	/**
	 * Returns the the selection cleaner label font color.
	 * 
	 * @return the the selection cleaner label font color.
	 */
	@Override
	public String getColorAsString() {
		return getValue(Property.COLOR, defaultOptions.getColorAsString());
	}

	/**
	 * Returns the the selection cleaner label font color.
	 * 
	 * @return the the selection cleaner label font color.
	 */
	public IsColor getColor() {
		return ColorBuilder.parse(getColorAsString());
	}

	/**
	 * Sets the selection cleaner label font color.
	 * 
	 * @param color the selection cleaner label font color.
	 */
	public void setColor(String color) {
		setValue(Property.COLOR, color);
	}

	/**
	 * Set the selection cleaner label font color.
	 * 
	 * @param color the selection cleaner label font color.
	 */
	public void setColor(IsColor color) {
		setColor(IsColor.checkAndGetValue(color));
	}

	/**
	 * Sets the selection cleaner align.
	 * 
	 * @param align the selection cleaner align
	 */
	public void setAlign(Align align) {
		setValue(Property.ALIGN, align);
	}

	/**
	 * Returns the selection cleaner align.
	 * 
	 * @return the selection cleaner align
	 */
	@Override
	public Align getAlign() {
		return getValue(Property.ALIGN, Align.values(), defaultOptions.getAlign());
	}

	/**
	 * Sets the selection cleaner render.
	 * 
	 * @param render the selection cleaner render
	 */
	public void setRender(Render render) {
		setValue(Property.RENDER, render);
	}

	/**
	 * Returns the selection cleaner render.
	 * 
	 * @return the selection cleaner render
	 */
	@Override
	public Render getRender() {
		return getValue(Property.RENDER, Render.values(), defaultOptions.getRender());
	}

	/**
	 * Sets the selection cleaner label position.
	 * 
	 * @param position the selection cleaner label position
	 */
	public void setPosition(Position position) {
		setValue(Property.POSITION, position);
	}

	/**
	 * Returns the selection cleaner label position.
	 * 
	 * @return the selection cleaner label position
	 */
	@Override
	public Position getPosition() {
		return getValue(Property.POSITION, Position.values(), defaultOptions.getPosition());
	}

	/**
	 * Sets the selection cleaner image.
	 * 
	 * @param image the selection cleaner image
	 */
	public void setImage(Img image) {
		setValue(Property.IMAGE, image);
	}

	/**
	 * Returns the selection cleaner image.
	 * 
	 * @return the selection cleaner image or <code>null</code> if not set
	 */
	@Override
	public Img getImage() {
		return getValue(Property.IMAGE, defaultOptions.getImage());
	}

	/**
	 * Sets the margin from canvas border.
	 * 
	 * @param margin margin from canvas border
	 */
	public void setMargin(int margin) {
		setValue(Property.MARGIN, Checker.positiveOrZero(margin));
	}

	/**
	 * Returns the margin from canvas border.
	 * 
	 * @return the margin from canvas border
	 */
	@Override
	public int getMargin() {
		return getValue(Property.MARGIN, defaultOptions.getMargin());
	}

	/**
	 * Sets the padding of selection cleaner element.
	 * 
	 * @param padding padding of selection cleaner element
	 */
	public void setPadding(int padding) {
		setValue(Property.PADDING, Checker.positiveOrZero(padding));
	}

	/**
	 * Returns the padding of selection cleaner element.
	 * 
	 * @return the padding of selection cleaner element
	 */
	@Override
	public int getPadding() {
		return getValue(Property.PADDING, defaultOptions.getPadding());
	}

	/**
	 * Sets the spacing between label and image for selection cleaner element.
	 * 
	 * @param spacing spacing between label and image for selection cleaner element
	 */
	public void setSpacing(int spacing) {
		setValue(Property.SPACING, Checker.positiveOrZero(spacing));
	}

	/**
	 * Returns the spacing between label and image for selection cleaner element.
	 * 
	 * @return the spacing between label and image for selection cleaner element
	 */
	@Override
	public int getSpacing() {
		return getValue(Property.SPACING, defaultOptions.getSpacing());
	}

	/**
	 * Sets <code>true</code> if clear of selection label will e applied in the chart, otherwise <code>false</code>.
	 * 
	 * @param useSelectionStyle <code>true</code> if clear of selection label will e applied in the chart, otherwise <code>false</code>
	 */
	public void setUseSelectionStyle(boolean useSelectionStyle) {
		setValue(Property.USE_SELECTION_STYLE, useSelectionStyle);
	}

	/**
	 * Returns <code>true</code> if selection cleaner element will apply style of selection area, otherwise <code>false</code>.
	 * 
	 * @return <code>true</code> if selection cleaner element will apply style of selection area, otherwise <code>false</code>
	 */
	@Override
	public boolean isUseSelectionStyle() {
		return getValue(Property.USE_SELECTION_STYLE, defaultOptions.isUseSelectionStyle());
	}

	/**
	 * Sets the X point of selection cleaner element.
	 * 
	 * @param x X point of selection cleaner element
	 */
	void setX(double x) {
		setValue(Property.X, x);
	}

	/**
	 * Returns the X point of selection cleaner element.
	 * 
	 * @return the X point of selection cleaner element or <code>0</code> if not set
	 */
	double getX() {
		return getValue(Property.X, DEFAULT_VALUE);
	}

	/**
	 * Sets the Y point of selection cleaner element.
	 * 
	 * @param y Y point of selection cleaner element
	 */
	void setY(double y) {
		setValue(Property.Y, y);
	}

	/**
	 * Returns the Y point of selection cleaner element.
	 * 
	 * @return the Y point of selection cleaner element or <code>0</code> if not set
	 */
	double getY() {
		return getValue(Property.Y, DEFAULT_VALUE);
	}

	/**
	 * Sets the width of selection cleaner element.
	 * 
	 * @param width width of selection cleaner element
	 */
	void setWidth(double width) {
		setValue(Property.WIDTH, width);
	}

	/**
	 * Returns the width of selection cleaner element.
	 * 
	 * @return the width of selection cleaner element or <code>0</code> if not set
	 */
	double getWidth() {
		return getValue(Property.WIDTH, DEFAULT_VALUE);
	}

	/**
	 * Sets the height of selection cleaner element.
	 * 
	 * @param height height of selection cleaner element
	 */
	void setHeight(double height) {
		setValue(Property.HEIGHT, height);
	}

	/**
	 * Returns the height of selection cleaner element.
	 * 
	 * @return the height of selection cleaner element or <code>0</code> if not set
	 */
	double getHeight() {
		return getValue(Property.HEIGHT, DEFAULT_VALUE);
	}

	/**
	 * Sets the X point of image for selection cleaner element.
	 * 
	 * @param x X point of image for selection cleaner element
	 */
	void setImageX(double x) {
		setValue(Property.IMAGE_X, x);
	}

	/**
	 * Returns the X point of image for selection cleaner element.
	 * 
	 * @return the X point of image for selection cleaner element or <code>0</code> if not set
	 */
	double getImageX() {
		return getValue(Property.IMAGE_X, DEFAULT_VALUE);
	}

	/**
	 * Sets the Y point of image for selection cleaner element.
	 * 
	 * @param y Y point of image for selection cleaner element
	 */
	void setImageY(double y) {
		setValue(Property.IMAGE_Y, y);
	}

	/**
	 * Returns the Y point of image for selection cleaner element.
	 * 
	 * @return the Y point of image for selection cleaner element or <code>0</code> if not set
	 */
	double getImageY() {
		return getValue(Property.IMAGE_Y, DEFAULT_VALUE);
	}

	/**
	 * Sets the width of image for selection cleaner element.
	 * 
	 * @param width width of image for selection cleaner element
	 */
	void setImageWidth(double width) {
		setValue(Property.IMAGE_WIDTH, width);
	}

	/**
	 * Returns the width of image for selection cleaner element.
	 * 
	 * @return the width of image for selection cleaner element or <code>0</code> if not set
	 */
	double getImageWidth() {
		return getValue(Property.IMAGE_WIDTH, DEFAULT_VALUE);
	}

	/**
	 * Sets the height of image for selection cleaner element.
	 * 
	 * @param height height of image for selection cleaner element
	 */
	void setImageHeight(double height) {
		setValue(Property.IMAGE_HEIGHT, height);
	}

	/**
	 * Returns the height of image for selection cleaner element.
	 * 
	 * @return the height of image for selection cleaner element or <code>0</code> if not set
	 */
	double getImageHeight() {
		return getValue(Property.IMAGE_HEIGHT, DEFAULT_VALUE);
	}

	/**
	 * Sets the X point of label for selection cleaner element.
	 * 
	 * @param x X point of label for selection cleaner element
	 */
	void setLabelX(double x) {
		setValue(Property.LABEL_X, x);
	}

	/**
	 * Returns the X point of label for selection cleaner element.
	 * 
	 * @return the X point of label for selection cleaner element or <code>0</code> if not set
	 */
	double getLabelX() {
		return getValue(Property.LABEL_X, DEFAULT_VALUE);
	}

	/**
	 * Sets the Y point of label for selection cleaner element.
	 * 
	 * @param y Y point of label for selection cleaner element
	 */
	void setLabelY(double y) {
		setValue(Property.LABEL_Y, y);
	}

	/**
	 * Returns the Y point of label for selection cleaner element.
	 * 
	 * @return the Y point of label for selection cleaner element or <code>0</code> if not set
	 */

	double getLabelY() {
		return getValue(Property.LABEL_Y, DEFAULT_VALUE);
	}

	/**
	 * Sets the width of label for selection cleaner element.
	 * 
	 * @param width width of label for selection cleaner element
	 */

	void setLabelWidth(double width) {
		setValue(Property.LABEL_WIDTH, width);
	}

	/**
	 * Returns the width of label for selection cleaner element.
	 * 
	 * @return the width of label for selection cleaner element or <code>0</code> if not set
	 */
	double getLabelWidth() {
		return getValue(Property.LABEL_WIDTH, DEFAULT_VALUE);
	}

	/**
	 * Sets the height of label for selection cleaner element.
	 * 
	 * @param height height of label for selection cleaner element
	 */
	void setLabelHeight(double height) {
		setValue(Property.LABEL_HEIGHT, height);
	}

	/**
	 * Returns the height of label for selection cleaner element.
	 * 
	 * @return the height of label for selection cleaner element or <code>0</code> if not set
	 */
	double getLabelHeight() {
		return getValue(Property.LABEL_HEIGHT, DEFAULT_VALUE);
	}

	/**
	 * Sets the chart layout padding reserve for selection cleaner element.
	 * 
	 * @param layoutPadding the chart layout padding reserve for selection cleaner element
	 */
	void setLayoutPadding(double layoutPadding) {
		setValue(Property.LAYOUT_PADDING, layoutPadding);
	}

	/**
	 * Returns the chart layout padding reserve for selection cleaner element.
	 * 
	 * @return the chart layout padding reserve for selection cleaner element or <code>0</code> if not set
	 */
	double getLayoutPadding() {
		return getValue(Property.LAYOUT_PADDING, DEFAULT_VALUE);
	}
}
