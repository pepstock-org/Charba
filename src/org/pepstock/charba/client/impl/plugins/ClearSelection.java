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
import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.enums.Position;
import org.pepstock.charba.client.impl.plugins.enums.Align;
import org.pepstock.charba.client.impl.plugins.enums.Render;

import com.google.gwt.dom.client.ImageElement;

/**
 * Datasets items selector plugin configuration element in order to have into the chart a clickable element to clear the
 * selection.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class ClearSelection extends NativeObjectContainer {

	// defaults values for dimensions and locations
	static final double DEFAULT_VALUE = 0D;
	// defaults global options instance
	private DatasetsItemsSelectorDefaultsClearSelection defaultsOptions;

	/**
	 * Name of properties of native object.
	 */
	enum Property implements Key
	{
		display,
		label,
		fontSize,
		fontFamily,
		fontColor,
		fontStyle,
		align,
		position,
		image,
		render,
		margin,
		padding,
		spacing,
		// internal properties to store the points and dimensions info
		_x,
		_y,
		_width,
		_height,
		_imageX,
		_imageY,
		_imageWidth,
		_imageHeight,
		_labelX,
		_labelY,
		_labelWidth,
		_labelHeight,
		_layoutPadding
	}

	/**
	 * Creates new font element, using the default values options.
	 * 
	 * @param defaultsOptions default clear selection element options to returns the default when required.
	 */
	ClearSelection(DatasetsItemsSelectorDefaultsClearSelection defaultsOptions) {
		this(null, defaultsOptions);
	}

	/**
	 * Creates new font element, using stored native object instance and the default values options.
	 * 
	 * @param nativeObject stored font values into native object to read.
	 * @param defaultsOptions default clear selection element options to returns the default when required.
	 */
	ClearSelection(NativeObject nativeObject, DatasetsItemsSelectorDefaultsClearSelection defaultsOptions) {
		super(nativeObject);
		this.defaultsOptions = defaultsOptions;
	}

	/**
	 * Sets <code>true</code> if clear of selection label will e applied into chart, otherwise <code>false</code>.
	 * 
	 * @param display <code>true</code> if clear of selection label will e applied into chart, otherwise <code>false</code>
	 */
	public void setDisplay(boolean display) {
		setValue(Property.display, display);
	}

	/**
	 * Returns <code>true</code> if clear of selection label will be applied into chart, otherwise <code>false</code>.
	 * 
	 * @return <code>true</code> if clear of selection label will be applied into chart, otherwise <code>false</code>
	 */
	public boolean isDisplay() {
		return getValue(Property.display, defaultsOptions.isDisplay());
	}

	/**
	 * Sets the clear selection label.
	 * 
	 * @param label the clear selection label
	 */
	public void setLabel(String label) {
		setValue(Property.label, label);
	}

	/**
	 * Returns the clear selection label.
	 * 
	 * @return the clear selection label
	 */
	public String getLabel() {
		return getValue(Property.label, defaultsOptions.getLabel());
	}

	/**
	 * Sets the font size.
	 * 
	 * @param fontSize the font size.
	 */
	public void setFontSize(int fontSize) {
		setValue(Property.fontSize, fontSize);
	}

	/**
	 * Returns the font size.
	 * 
	 * @return the font size.
	 */
	public int getFontSize() {
		return getValue(Property.fontSize, defaultsOptions.getFontSize());
	}

	/**
	 * Sets the font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @param fontStyle Font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 */
	public void setFontStyle(FontStyle fontStyle) {
		setValue(Property.fontStyle, fontStyle);
	}

	/**
	 * Returns the font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @return the font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 */
	public FontStyle getFontStyle() {
		return getValue(Property.fontStyle, FontStyle.class, defaultsOptions.getFontStyle());
	}

	/**
	 * Sets the font family, follows CSS font-family options.
	 * 
	 * @param fontFamily Font family, follows CSS font-family options.
	 */
	public void setFontFamily(String fontFamily) {
		setValue(Property.fontFamily, fontFamily);
	}

	/**
	 * Returns the font family, follows CSS font-family options.
	 * 
	 * @return Font family, follows CSS font-family options.
	 */
	public String getFontFamily() {
		return getValue(Property.fontFamily, defaultsOptions.getFontFamily());
	}

	/**
	 * Returns the the clear selection label font color.
	 * 
	 * @return the the clear selection label font color.
	 */
	public String getFontColorAsString() {
		return getValue(Property.fontColor, defaultsOptions.getFontColorAsString());
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
		setValue(Property.fontColor, color);
	}

	/**
	 * Set the clear selection label font color.
	 * 
	 * @param color the clear selection label font color.
	 */
	public void setFontColor(IsColor color) {
		setFontColor(color.toRGBA());
	}

	/**
	 * Sets the clear selection label text align.
	 * 
	 * @param align the clear selection label text align
	 */
	public void setAlign(Align align) {
		setValue(Property.align, align);
	}

	/**
	 * Returns the clear selection label text align.
	 * 
	 * @return the clear selection label text align
	 */
	public Align getAlign() {
		return getValue(Property.align, Align.class, defaultsOptions.getAlign());
	}

	/**
	 * Sets the clear selection render.
	 * 
	 * @param render the clear selection render
	 */
	public void setRender(Render render) {
		setValue(Property.render, render);
	}

	/**
	 * Returns the clear selection render.
	 * 
	 * @return the clear selection render
	 */
	public Render getRender() {
		return getValue(Property.render, Render.class, defaultsOptions.getRender());
	}

	/**
	 * Sets the clear selection label position.
	 * 
	 * @param position the clear selection label position
	 */
	public void setPosition(Position position) {
		setValue(Property.position, position);
	}

	/**
	 * Returns the clear selection label position.
	 * 
	 * @return the clear selection label position
	 */
	public Position getPosition() {
		return getValue(Property.position, Position.class, defaultsOptions.getPosition());
	}

	/**
	 * Sets the clear selection image.
	 * 
	 * @param image the clear selection image
	 */
	public void setImage(ImageElement image) {
		setValue(Property.image, image);
	}

	/**
	 * Returns the clear selection image.
	 * 
	 * @return the clear selection image or <code>null</code> if not set
	 */
	public ImageElement getImage() {
		return getValue(Property.image, defaultsOptions.getImage());
	}

	/**
	 * Sets the margin from canvas border.
	 * 
	 * @param margin margin from canvas border
	 */
	public void setMargin(int margin) {
		setValue(Property.margin, margin);
	}

	/**
	 * Returns the margin from canvas border.
	 * 
	 * @return the margin from canvas border
	 */
	public int getMargin() {
		return getValue(Property.margin, defaultsOptions.getMargin());
	}

	/**
	 * Sets the padding of clear selection element.
	 * 
	 * @param padding padding of clear selection element
	 */
	public void setPadding(int padding) {
		setValue(Property.padding, padding);
	}

	/**
	 * Returns the padding of clear selection element.
	 * 
	 * @return the padding of clear selection element
	 */
	public int getPadding() {
		return getValue(Property.padding, defaultsOptions.getPadding());
	}

	/**
	 * Sets the spacing between label and image for clear selection element.
	 * 
	 * @param spacing spacing between label and image for clear selection element
	 */
	public void setSpacing(int spacing) {
		setValue(Property.spacing, spacing);
	}

	/**
	 * Returns the spacing between label and image for clear selection element.
	 * 
	 * @return the spacing between label and image for clear selection element
	 */
	public int getSpacing() {
		return getValue(Property.spacing, defaultsOptions.getSpacing());
	}

	/**
	 * Sets the X point of clear selection element.
	 * 
	 * @param x X point of clear selection element
	 */
	void setX(double x) {
		setValue(Property._x, x);
	}

	/**
	 * Returns the X point of clear selection element.
	 * 
	 * @return the X point of clear selection element or <code>0</code> if not set
	 */
	double getX() {
		return getValue(Property._x, DEFAULT_VALUE);
	}

	/**
	 * Sets the Y point of clear selection element.
	 * 
	 * @param y Y point of clear selection element
	 */
	void setY(double y) {
		setValue(Property._y, y);
	}

	/**
	 * Returns the Y point of clear selection element.
	 * 
	 * @return the Y point of clear selection element or <code>0</code> if not set
	 */
	double getY() {
		return getValue(Property._y, DEFAULT_VALUE);
	}

	/**
	 * Sets the width of clear selection element.
	 * 
	 * @param width width of clear selection element
	 */
	void setWidth(double width) {
		setValue(Property._width, width);
	}

	/**
	 * Returns the width of clear selection element.
	 * 
	 * @return the width of clear selection element or <code>0</code> if not set
	 */
	double getWidth() {
		return getValue(Property._width, DEFAULT_VALUE);
	}

	/**
	 * Sets the height of clear selection element.
	 * 
	 * @param height height of clear selection element
	 */
	void setHeight(double height) {
		setValue(Property._height, height);
	}

	/**
	 * Returns the height of clear selection element.
	 * 
	 * @return the height of clear selection element or <code>0</code> if not set
	 */
	double getHeight() {
		return getValue(Property._height, DEFAULT_VALUE);
	}

	/**
	 * Sets the X point of image for clear selection element.
	 * 
	 * @param x X point of image for clear selection element
	 */
	void setImageX(double x) {
		setValue(Property._imageX, x);
	}

	/**
	 * Returns the X point of image for clear selection element.
	 * 
	 * @return the X point of image for clear selection element or <code>0</code> if not set
	 */
	double getImageX() {
		return getValue(Property._imageX, DEFAULT_VALUE);
	}

	/**
	 * Sets the Y point of image for clear selection element.
	 * 
	 * @param y Y point of image for clear selection element
	 */
	void setImageY(double y) {
		setValue(Property._imageY, y);
	}

	/**
	 * Returns the Y point of image for clear selection element.
	 * 
	 * @return the Y point of image for clear selection element or <code>0</code> if not set
	 */
	double getImageY() {
		return getValue(Property._imageY, DEFAULT_VALUE);
	}

	/**
	 * Sets the width of image for clear selection element.
	 * 
	 * @param width width of image for clear selection element
	 */
	void setImageWidth(double width) {
		setValue(Property._imageWidth, width);
	}

	/**
	 * Returns the width of image for clear selection element.
	 * 
	 * @return the width of image for clear selection element or <code>0</code> if not set
	 */
	double getImageWidth() {
		return getValue(Property._imageWidth, DEFAULT_VALUE);
	}

	/**
	 * Sets the height of image for clear selection element.
	 * 
	 * @param height height of image for clear selection element
	 */
	void setImageHeight(double height) {
		setValue(Property._imageHeight, height);
	}

	/**
	 * Returns the height of image for clear selection element.
	 * 
	 * @return the height of image for clear selection element or <code>0</code> if not set
	 */
	double getImageHeight() {
		return getValue(Property._imageHeight, DEFAULT_VALUE);
	}

	/**
	 * Sets the X point of label for clear selection element.
	 * 
	 * @param x X point of label for clear selection element
	 */
	void setLabelX(double x) {
		setValue(Property._labelX, x);
	}

	/**
	 * Returns the X point of label for clear selection element.
	 * 
	 * @return the X point of label for clear selection element or <code>0</code> if not set
	 */
	double getLabelX() {
		return getValue(Property._labelX, DEFAULT_VALUE);
	}

	/**
	 * Sets the Y point of label for clear selection element.
	 * 
	 * @param y Y point of label for clear selection element
	 */
	void setLabelY(double y) {
		setValue(Property._labelY, y);
	}

	/**
	 * Returns the Y point of label for clear selection element.
	 * 
	 * @return the Y point of label for clear selection element or <code>0</code> if not set
	 */

	double getLabelY() {
		return getValue(Property._labelY, DEFAULT_VALUE);
	}

	/**
	 * Sets the width of label for clear selection element.
	 * 
	 * @param width width of label for clear selection element
	 */

	void setLabelWidth(double width) {
		setValue(Property._labelWidth, width);
	}

	/**
	 * Returns the width of label for clear selection element.
	 * 
	 * @return the width of label for clear selection element or <code>0</code> if not set
	 */
	double getLabelWidth() {
		return getValue(Property._labelWidth, DEFAULT_VALUE);
	}

	/**
	 * Sets the height of label for clear selection element.
	 * 
	 * @param height height of label for clear selection element
	 */
	void setLabelHeight(double height) {
		setValue(Property._labelHeight, height);
	}

	/**
	 * Returns the height of label for clear selection element.
	 * 
	 * @return the height of label for clear selection element or <code>0</code> if not set
	 */
	double getLabelHeight() {
		return getValue(Property._labelHeight, DEFAULT_VALUE);
	}

	/**
	 * Sets the chart layout padding reserve for clear selection element.
	 * 
	 * @param layoutPadding the chart layout padding reserve for clear selection element
	 */
	void setLayoutPadding(double layoutPadding) {
		setValue(Property._layoutPadding, layoutPadding);
	}

	/**
	 * Returns the chart layout padding reserve for clear selection element.
	 * 
	 * @return the chart layout padding reserve for clear selection element or <code>0</code> if not set
	 */
	double getLayoutPadding() {
		return getValue(Property._layoutPadding, DEFAULT_VALUE);
	}
}
