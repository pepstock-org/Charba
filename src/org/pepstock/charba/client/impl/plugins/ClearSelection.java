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
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.dom.DOMBuilder;
import org.pepstock.charba.client.dom.elements.Img;
import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.enums.Position;
import org.pepstock.charba.client.impl.plugins.enums.Align;
import org.pepstock.charba.client.impl.plugins.enums.Render;

/**
 * {@link DatasetsItemsSelector#ID} plugin configuration element in order to have in the the chart a clickable element to clear the selection.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class ClearSelection extends NativeObjectContainer implements IsDatasetsItemsSelectorDefaultClearSelection {

	// clear selection image in the URL base 64
	private static final String CLEAR_SELECTION_18 = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABIAAAASCAYAAABWzo5XAAAAW0lEQVR4AWMYWUCAGmr4gfgeEPfjUdMPVcNPyLB2IP6Pw7B+qFw7bu2ENbSji5FqWAMQN8JdSSaYBjKAIkMQrkIYRKkhjQS9RnlgUx79jdRMkLzUyCKCDCMHAABoYiU8YS3zcAAAAABJRU5ErkJggg==";
	// clear selection image in the URL base 64
	private static final String CLEAR_SELECTION_24 = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAYCAQAAABKfvVzAAAAd0lEQVR4AeWSAQbAMAxF3yFKUbBh97/AsM1uMBSqepKMgloa1AB9QPGanyTMzsaO44vjZKXLgRDxtHgiwkGXQEFIjeJJCIUAlpKqEvRLof7U9TRN6kzRHVm4qggRByPCYwhGpKwj/W96dKzDi7tGT2PjNo5vYW5esdREthFCWOcAAAAASUVORK5CYII=";
	// clear selection image in the URL base 64
	private static final String CLEAR_SELECTION_36 = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACQAAAAkCAYAAADhAJiYAAAAr0lEQVR4Ae2WgQbFMAxFmx+r9/ZF+4QNFFBggIJ957Yg4NrQ3TAqhwsscaZtmxQEQXDPTyNEvVgPF2bNqWkvpcRqD+tFkU1GY1L9NOiRE0mBhltH7Qa1JTlRUeqFTE2At1Qlvu2A/Gtehpcq/H7jwZOzaFZahkBQir8efKT2Bxn5SqjxQoMuGcqs/KYe5NjzF+OoT4f/4zrK+JFRxn9A40ZYhBthCf4OQ/6kCYIgQC66xpQeyjgZrwAAAABJRU5ErkJggg==";

	/**
	 * Default clear selection image, <img alt="Default image 18x" src=
	 * "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABIAAAASCAYAAABWzo5XAAAAW0lEQVR4AWMYWUCAGmr4gfgeEPfjUdMPVcNPyLB2IP6Pw7B+qFw7bu2ENbSji5FqWAMQN8JdSSaYBjKAIkMQrkIYRKkhjQS9RnlgUx79jdRMkLzUyCKCDCMHAABoYiU8YS3zcAAAAABJRU5ErkJggg==">.
	 */
	public static final Img DEFAULT_CLEAR_SELECTION_18 = DOMBuilder.get().createImageElement(CLEAR_SELECTION_18);
	/**
	 * Default clear selection image, <img alt="Default image 24x" src=
	 * "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAYCAQAAABKfvVzAAAAd0lEQVR4AeWSAQbAMAxF3yFKUbBh97/AsM1uMBSqepKMgloa1AB9QPGanyTMzsaO44vjZKXLgRDxtHgiwkGXQEFIjeJJCIUAlpKqEvRLof7U9TRN6kzRHVm4qggRByPCYwhGpKwj/W96dKzDi7tGT2PjNo5vYW5esdREthFCWOcAAAAASUVORK5CYII=">.
	 */
	public static final Img DEFAULT_CLEAR_SELECTION_24 = DOMBuilder.get().createImageElement(CLEAR_SELECTION_24);
	/**
	 * Default clear selection image, <img alt="Default image 18x" src=
	 * "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACQAAAAkCAYAAADhAJiYAAAAr0lEQVR4Ae2WgQbFMAxFmx+r9/ZF+4QNFFBggIJ957Yg4NrQ3TAqhwsscaZtmxQEQXDPTyNEvVgPF2bNqWkvpcRqD+tFkU1GY1L9NOiRE0mBhltH7Qa1JTlRUeqFTE2At1Qlvu2A/Gtehpcq/H7jwZOzaFZahkBQir8efKT2Bxn5SqjxQoMuGcqs/KYe5NjzF+OoT4f/4zrK+JFRxn9A40ZYhBthCf4OQ/6kCYIgQC66xpQeyjgZrwAAAABJRU5ErkJggg==">.
	 */
	public static final Img DEFAULT_CLEAR_SELECTION_36 = DOMBuilder.get().createImageElement(CLEAR_SELECTION_36);

	/**
	 * Default flag if clear selection must be showed in the chart, value is <b>{@value DEFAULT_DISPLAY}</b>.
	 */
	public static final boolean DEFAULT_DISPLAY = false;

	/**
	 * Default label for clear selection element, value is <b>{@value DEFAULT_LABEL}</b>.
	 */
	public static final String DEFAULT_LABEL = "Reset selection";

	/**
	 * Default margin from canvas for clear selection element, value is <b>{@value DEFAULT_MARGIN}</b>.
	 */
	public static final int DEFAULT_MARGIN = 2;

	/**
	 * Default padding for clear selection element, value is <b>{@value DEFAULT_PADDING}</b>.
	 */
	public static final int DEFAULT_PADDING = 4;

	/**
	 * Default spacing between label and image for clear selection element, value is <b>{@value DEFAULT_SPACING}</b>.
	 */
	public static final int DEFAULT_SPACING = 3;

	/**
	 * Default image for clear selection element.
	 */
	public static final Img DEFAULT_IMAGE = DEFAULT_CLEAR_SELECTION_18;

	/**
	 * Default flag if clear selection will use selection area style, value is <b>{@value DEFAULT_USE_SELECTION_STYLE}</b>.
	 */
	public static final boolean DEFAULT_USE_SELECTION_STYLE = false;

	/**
	 * Default clear selection box alignment, {@link Align#RIGHT}.
	 */
	public static final Align DEFAULT_ALIGN = Align.RIGHT;

	/**
	 * Default clear selection rendering, {@link Render#IMAGE_LABEL}.
	 */
	public static final Render DEFAULT_RENDER = Render.IMAGE_LABEL;

	/**
	 * Default clear selection box position, {@link Position#BOTTOM}.
	 */
	public static final Position DEFAULT_POSITION = Position.BOTTOM;

	// defaults values for dimensions and locations
	static final double DEFAULT_VALUE = 0D;
	/**
	 * Default border width of clear selection element<br>
	 * Used only when use selection style is set to <code>true</code>.
	 */
	static final int BORDER_WIDTH = 1;

	/**
	 * Name of properties of native object.
	 */
	enum Property implements Key
	{
		DISPLAY("display"),
		LABEL("label"),
		FONT_SIZE("fontSize"),
		FONT_FAMILY("fontFamily"),
		FONT_COLOR("fontColor"),
		FONT_STYLE("fontStyle"),
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
	private IsDatasetsItemsSelectorDefaultClearSelection defaultOptions;

	/**
	 * Creates new font element, using stored native object instance and the default values options.
	 * 
	 * @param nativeObject stored font values in the native object to read.
	 * @param defaultOptions default clear selection element options to returns the default when required.
	 */
	ClearSelection(NativeObject nativeObject, IsDatasetsItemsSelectorDefaultClearSelection defaultOptions) {
		super(nativeObject);
		this.defaultOptions = defaultOptions;
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
	 * Sets the clear selection label.
	 * 
	 * @param label the clear selection label
	 */
	public void setLabel(String label) {
		setValue(Property.LABEL, label);
	}

	/**
	 * Returns the clear selection label.
	 * 
	 * @return the clear selection label
	 */
	@Override
	public String getLabel() {
		return getValue(Property.LABEL, defaultOptions.getLabel());
	}

	/**
	 * Sets the font size.
	 * 
	 * @param fontSize the font size.
	 */
	public void setFontSize(int fontSize) {
		setValue(Property.FONT_SIZE, fontSize);
	}

	/**
	 * Returns the font size.
	 * 
	 * @return the font size.
	 */
	@Override
	public int getFontSize() {
		return getValue(Property.FONT_SIZE, defaultOptions.getFontSize());
	}

	/**
	 * Sets the font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @param fontStyle Font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 */
	public void setFontStyle(FontStyle fontStyle) {
		setValue(Property.FONT_STYLE, fontStyle);
	}

	/**
	 * Returns the font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @return the font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 */
	@Override
	public FontStyle getFontStyle() {
		return getValue(Property.FONT_STYLE, FontStyle.values(), defaultOptions.getFontStyle());
	}

	/**
	 * Sets the font family, follows CSS font-family options.
	 * 
	 * @param fontFamily Font family, follows CSS font-family options.
	 */
	public void setFontFamily(String fontFamily) {
		setValue(Property.FONT_FAMILY, fontFamily);
	}

	/**
	 * Returns the font family, follows CSS font-family options.
	 * 
	 * @return Font family, follows CSS font-family options.
	 */
	@Override
	public String getFontFamily() {
		return getValue(Property.FONT_FAMILY, defaultOptions.getFontFamily());
	}

	/**
	 * Returns the the clear selection label font color.
	 * 
	 * @return the the clear selection label font color.
	 */
	@Override
	public String getFontColorAsString() {
		return getValue(Property.FONT_COLOR, defaultOptions.getFontColorAsString());
	}

	/**
	 * Returns the the clear selection label font color.
	 * 
	 * @return the the clear selection label font color.
	 */
	public IsColor getFontColor() {
		return ColorBuilder.parse(getFontColorAsString());
	}

	/**
	 * Sets the clear selection label font color.
	 * 
	 * @param color the clear selection label font color.
	 */
	public void setFontColor(String color) {
		setValue(Property.FONT_COLOR, color);
	}

	/**
	 * Set the clear selection label font color.
	 * 
	 * @param color the clear selection label font color.
	 */
	public void setFontColor(IsColor color) {
		setFontColor(IsColor.checkAndGetValue(color));
	}

	/**
	 * Sets the clear selection align.
	 * 
	 * @param align the clear selection align
	 */
	public void setAlign(Align align) {
		setValue(Property.ALIGN, align);
	}

	/**
	 * Returns the clear selection align.
	 * 
	 * @return the clear selection align
	 */
	@Override
	public Align getAlign() {
		return getValue(Property.ALIGN, Align.values(), defaultOptions.getAlign());
	}

	/**
	 * Sets the clear selection render.
	 * 
	 * @param render the clear selection render
	 */
	public void setRender(Render render) {
		setValue(Property.RENDER, render);
	}

	/**
	 * Returns the clear selection render.
	 * 
	 * @return the clear selection render
	 */
	@Override
	public Render getRender() {
		return getValue(Property.RENDER, Render.values(), defaultOptions.getRender());
	}

	/**
	 * Sets the clear selection label position.
	 * 
	 * @param position the clear selection label position
	 */
	public void setPosition(Position position) {
		setValue(Property.POSITION, position);
	}

	/**
	 * Returns the clear selection label position.
	 * 
	 * @return the clear selection label position
	 */
	@Override
	public Position getPosition() {
		return getValue(Property.POSITION, Position.values(), defaultOptions.getPosition());
	}

	/**
	 * Sets the clear selection image.
	 * 
	 * @param image the clear selection image
	 */
	public void setImage(Img image) {
		setValue(Property.IMAGE, image);
	}

	/**
	 * Returns the clear selection image.
	 * 
	 * @return the clear selection image or <code>null</code> if not set
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
		setValue(Property.MARGIN, margin);
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
	 * Sets the padding of clear selection element.
	 * 
	 * @param padding padding of clear selection element
	 */
	public void setPadding(int padding) {
		setValue(Property.PADDING, padding);
	}

	/**
	 * Returns the padding of clear selection element.
	 * 
	 * @return the padding of clear selection element
	 */
	@Override
	public int getPadding() {
		return getValue(Property.PADDING, defaultOptions.getPadding());
	}

	/**
	 * Sets the spacing between label and image for clear selection element.
	 * 
	 * @param spacing spacing between label and image for clear selection element
	 */
	public void setSpacing(int spacing) {
		setValue(Property.SPACING, spacing);
	}

	/**
	 * Returns the spacing between label and image for clear selection element.
	 * 
	 * @return the spacing between label and image for clear selection element
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
	 * Returns <code>true</code> if clear selection element will apply style of selection area, otherwise <code>false</code>.
	 * 
	 * @return <code>true</code> if clear selection element will apply style of selection area, otherwise <code>false</code>
	 */
	@Override
	public boolean isUseSelectionStyle() {
		return getValue(Property.USE_SELECTION_STYLE, defaultOptions.isUseSelectionStyle());
	}

	/**
	 * Sets the X point of clear selection element.
	 * 
	 * @param x X point of clear selection element
	 */
	void setX(double x) {
		setValue(Property.X, x);
	}

	/**
	 * Returns the X point of clear selection element.
	 * 
	 * @return the X point of clear selection element or <code>0</code> if not set
	 */
	double getX() {
		return getValue(Property.X, DEFAULT_VALUE);
	}

	/**
	 * Sets the Y point of clear selection element.
	 * 
	 * @param y Y point of clear selection element
	 */
	void setY(double y) {
		setValue(Property.Y, y);
	}

	/**
	 * Returns the Y point of clear selection element.
	 * 
	 * @return the Y point of clear selection element or <code>0</code> if not set
	 */
	double getY() {
		return getValue(Property.Y, DEFAULT_VALUE);
	}

	/**
	 * Sets the width of clear selection element.
	 * 
	 * @param width width of clear selection element
	 */
	void setWidth(double width) {
		setValue(Property.WIDTH, width);
	}

	/**
	 * Returns the width of clear selection element.
	 * 
	 * @return the width of clear selection element or <code>0</code> if not set
	 */
	double getWidth() {
		return getValue(Property.WIDTH, DEFAULT_VALUE);
	}

	/**
	 * Sets the height of clear selection element.
	 * 
	 * @param height height of clear selection element
	 */
	void setHeight(double height) {
		setValue(Property.HEIGHT, height);
	}

	/**
	 * Returns the height of clear selection element.
	 * 
	 * @return the height of clear selection element or <code>0</code> if not set
	 */
	double getHeight() {
		return getValue(Property.HEIGHT, DEFAULT_VALUE);
	}

	/**
	 * Sets the X point of image for clear selection element.
	 * 
	 * @param x X point of image for clear selection element
	 */
	void setImageX(double x) {
		setValue(Property.IMAGE_X, x);
	}

	/**
	 * Returns the X point of image for clear selection element.
	 * 
	 * @return the X point of image for clear selection element or <code>0</code> if not set
	 */
	double getImageX() {
		return getValue(Property.IMAGE_X, DEFAULT_VALUE);
	}

	/**
	 * Sets the Y point of image for clear selection element.
	 * 
	 * @param y Y point of image for clear selection element
	 */
	void setImageY(double y) {
		setValue(Property.IMAGE_Y, y);
	}

	/**
	 * Returns the Y point of image for clear selection element.
	 * 
	 * @return the Y point of image for clear selection element or <code>0</code> if not set
	 */
	double getImageY() {
		return getValue(Property.IMAGE_Y, DEFAULT_VALUE);
	}

	/**
	 * Sets the width of image for clear selection element.
	 * 
	 * @param width width of image for clear selection element
	 */
	void setImageWidth(double width) {
		setValue(Property.IMAGE_WIDTH, width);
	}

	/**
	 * Returns the width of image for clear selection element.
	 * 
	 * @return the width of image for clear selection element or <code>0</code> if not set
	 */
	double getImageWidth() {
		return getValue(Property.IMAGE_WIDTH, DEFAULT_VALUE);
	}

	/**
	 * Sets the height of image for clear selection element.
	 * 
	 * @param height height of image for clear selection element
	 */
	void setImageHeight(double height) {
		setValue(Property.IMAGE_HEIGHT, height);
	}

	/**
	 * Returns the height of image for clear selection element.
	 * 
	 * @return the height of image for clear selection element or <code>0</code> if not set
	 */
	double getImageHeight() {
		return getValue(Property.IMAGE_HEIGHT, DEFAULT_VALUE);
	}

	/**
	 * Sets the X point of label for clear selection element.
	 * 
	 * @param x X point of label for clear selection element
	 */
	void setLabelX(double x) {
		setValue(Property.LABEL_X, x);
	}

	/**
	 * Returns the X point of label for clear selection element.
	 * 
	 * @return the X point of label for clear selection element or <code>0</code> if not set
	 */
	double getLabelX() {
		return getValue(Property.LABEL_X, DEFAULT_VALUE);
	}

	/**
	 * Sets the Y point of label for clear selection element.
	 * 
	 * @param y Y point of label for clear selection element
	 */
	void setLabelY(double y) {
		setValue(Property.LABEL_Y, y);
	}

	/**
	 * Returns the Y point of label for clear selection element.
	 * 
	 * @return the Y point of label for clear selection element or <code>0</code> if not set
	 */

	double getLabelY() {
		return getValue(Property.LABEL_Y, DEFAULT_VALUE);
	}

	/**
	 * Sets the width of label for clear selection element.
	 * 
	 * @param width width of label for clear selection element
	 */

	void setLabelWidth(double width) {
		setValue(Property.LABEL_WIDTH, width);
	}

	/**
	 * Returns the width of label for clear selection element.
	 * 
	 * @return the width of label for clear selection element or <code>0</code> if not set
	 */
	double getLabelWidth() {
		return getValue(Property.LABEL_WIDTH, DEFAULT_VALUE);
	}

	/**
	 * Sets the height of label for clear selection element.
	 * 
	 * @param height height of label for clear selection element
	 */
	void setLabelHeight(double height) {
		setValue(Property.LABEL_HEIGHT, height);
	}

	/**
	 * Returns the height of label for clear selection element.
	 * 
	 * @return the height of label for clear selection element or <code>0</code> if not set
	 */
	double getLabelHeight() {
		return getValue(Property.LABEL_HEIGHT, DEFAULT_VALUE);
	}

	/**
	 * Sets the chart layout padding reserve for clear selection element.
	 * 
	 * @param layoutPadding the chart layout padding reserve for clear selection element
	 */
	void setLayoutPadding(double layoutPadding) {
		setValue(Property.LAYOUT_PADDING, layoutPadding);
	}

	/**
	 * Returns the chart layout padding reserve for clear selection element.
	 * 
	 * @return the chart layout padding reserve for clear selection element or <code>0</code> if not set
	 */
	double getLayoutPadding() {
		return getValue(Property.LAYOUT_PADDING, DEFAULT_VALUE);
	}
}
