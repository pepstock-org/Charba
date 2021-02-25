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

import java.util.Collections;
import java.util.List;

import org.pepstock.charba.client.annotation.enums.LineLabelPosition;
import org.pepstock.charba.client.colors.Color;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.HtmlColor;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.dom.elements.Img;
import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.items.UndefinedValues;

/**
 * Implements a <b>LABEL</b> to apply on a LINE annotation.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class LineLabel extends NativeObjectContainer implements IsDefaultsLineLabel {

	/**
	 * Default line label enabled, <b>{@value DEFAULT_ENABLED}</b>.
	 */
	public static final boolean DEFAULT_ENABLED = false;

	/**
	 * Default line label background color, <b>rgba(0, 0, 0, 0.8)</b>.
	 */
	public static final IsColor DEFAULT_BACKGROUND_COLOR = new Color(0, 0, 0).alpha(0.8D);

	/**
	 * Default line label background color as string, <b>rgba(0, 0, 0, 0.8)</b>.
	 */
	public static final String DEFAULT_BACKGROUND_COLOR_AS_STRING = DEFAULT_BACKGROUND_COLOR.toRGBA();

	/**
	 * Default line label font style, <b>{@link FontStyle#BOLD}</b>.
	 */
	public static final FontStyle DEFAULT_FONT_STYLE = FontStyle.BOLD;

	/**
	 * Default line label text color, <b>{@link HtmlColor#WHITE}</b>.
	 */
	public static final IsColor DEFAULT_COLOR = HtmlColor.WHITE;

	/**
	 * Default line label font color as string, <b>rgb(255, 255, 255)</b>.
	 */
	public static final String DEFAULT_FONT_COLOR_AS_STRING = DEFAULT_COLOR.toRGB();

	/**
	 * Default line label X padding, <b>{@value DEFAULT_X_PADDING}</b>.
	 */
	public static final int DEFAULT_X_PADDING = 6;

	/**
	 * Default line label Y padding, <b>{@value DEFAULT_Y_PADDING}</b>.
	 */
	public static final int DEFAULT_Y_PADDING = 6;

	/**
	 * Default line label corner radius, <b>{@value DEFAULT_CORNER_RADIUS}</b>.
	 */
	public static final double DEFAULT_CORNER_RADIUS = 6D;

	/**
	 * Default line label position, <b>{@link LineLabelPosition#CENTER}</b>.
	 */
	public static final LineLabelPosition DEFAULT_POSITION = LineLabelPosition.CENTER;

	/**
	 * Default line label X adjust, <b>{@value DEFAULT_X_ADJUST}</b>.
	 */
	public static final double DEFAULT_X_ADJUST = 0D;

	/**
	 * Default line label Y adjust, <b>{@value DEFAULT_Y_ADJUST}</b>.
	 */
	public static final double DEFAULT_Y_ADJUST = 0D;

	/**
	 * Default line label rotation, <b>{@value DEFAULT_ROTATION}</b>.
	 */
	public static final double DEFAULT_ROTATION = 0D;

	// auto rotation
	private static final String AUTO_ROTATION = "auto";

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		BACKGROUND_COLOR("backgroundColor"),
		COLOR("color"),
		CONTENT("content"),
		CORNER_RADIUS("cornerRadius"),
		ENABLED("enabled"),
		HEIGHT("height"),
		WIDTH("width"),
		FONT("font"),
		POSITION("position"),
		ROTATION("rotation"),
		X_PADDING("xPadding"),
		Y_PADDING("yPadding"),
		X_ADJUST("xAdjust"),
		Y_ADJUST("yAdjust");

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
	private final IsDefaultsLineLabel defaultValues;
	// font instance
	private final Font font;

	/**
	 * To avoid any instantiation because is added into all {@link LineAnnotation}.
	 * 
	 * @param defaultValues default options instance
	 */
	LineLabel(IsDefaultsLineLabel defaultValues) {
		this(null, defaultValues);
	}

	/**
	 * To avoid any instantiation because is added into all {@link LineAnnotation}.
	 * 
	 * @param nativeObject native object to wrap, with all properties of a label
	 * @param defaultValues default options instance
	 */
	LineLabel(NativeObject nativeObject, IsDefaultsLineLabel defaultValues) {
		super(nativeObject);
		// checks if default value is consistent
		// stores default options
		this.defaultValues = checkDefaultValuesArgument(defaultValues);
		// gets font
		this.font = new Font(defaultValues.getFont(), getValue(Property.FONT));
	}

	/**
	 * Returns the font element.
	 * 
	 * @return the font element.
	 */
	@Override
	public Font getFont() {
		return font;
	}

	/**
	 * Sets <code>true</code> whether the label is enabled and should be displayed.
	 * 
	 * @param enabled <code>true</code> whether the label is enabled and should be displayed
	 */
	public void setEnabled(boolean enabled) {
		setValue(Property.ENABLED, enabled);
	}

	/**
	 * Returns <code>true</code> whether the label is enabled and should be displayed.
	 * 
	 * @return <code>true</code> whether the label is enabled and should be displayed
	 */
	@Override
	public boolean isEnabled() {
		return getValue(Property.ENABLED, defaultValues.isEnabled());
	}

	/**
	 * Sets the background color of label.
	 * 
	 * @param backgroundColor the background color of label
	 */
	public void setBackgroundColor(IsColor backgroundColor) {
		setBackgroundColor(IsColor.checkAndGetValue(backgroundColor));
	}

	/**
	 * Sets the background color of label as string.
	 * 
	 * @param backgroundColor the background color of label
	 */
	public void setBackgroundColor(String backgroundColor) {
		setValue(Property.BACKGROUND_COLOR, backgroundColor);
	}

	/**
	 * Returns the background color of label.
	 * 
	 * @return the background color of label
	 */
	@Override
	public String getBackgroundColorAsString() {
		return getValue(Property.BACKGROUND_COLOR, defaultValues.getBackgroundColorAsString());
	}

	/**
	 * Returns the background color of label.
	 * 
	 * @return backgroundColor the background color of label
	 */
	public IsColor getBackgroundColor() {
		return ColorBuilder.parse(getBackgroundColorAsString());
	}

	/**
	 * Sets the color of text.
	 * 
	 * @param fontColor the color of text
	 */
	public void setColor(IsColor fontColor) {
		setColor(IsColor.checkAndGetValue(fontColor));
	}

	/**
	 * Sets the color of text as string.
	 * 
	 * @param fontColor the color of text
	 */
	public void setColor(String fontColor) {
		setValue(Property.COLOR, fontColor);
	}

	/**
	 * Returns the color of text as string.
	 * 
	 * @return the color of text
	 */
	@Override
	public String getColorAsString() {
		return getValue(Property.COLOR, defaultValues.getColorAsString());
	}

	/**
	 * Returns the color of text.
	 * 
	 * @return the color of text
	 */
	public IsColor getColor() {
		return ColorBuilder.parse(getColorAsString());
	}

	/**
	 * Sets the padding of label to add left and right.
	 * 
	 * @param xPadding the padding of label to add left and right
	 */
	public void setXPadding(int xPadding) {
		setValue(Property.X_PADDING, xPadding);
	}

	/**
	 * Returns the padding of label to add left and right.
	 * 
	 * @return the padding of label to add left and right
	 */
	@Override
	public int getXPadding() {
		return getValue(Property.X_PADDING, defaultValues.getXPadding());
	}

	/**
	 * Sets the padding of label to add top and bottom.
	 * 
	 * @param yPadding the padding of label to add top and bottom
	 */
	public void setYPadding(int yPadding) {
		setValue(Property.Y_PADDING, yPadding);
	}

	/**
	 * Returns the padding of label to add top and bottom.
	 * 
	 * @return the padding of label to add top and bottom
	 */
	@Override
	public int getYPadding() {
		return getValue(Property.Y_PADDING, defaultValues.getYPadding());
	}

	/**
	 * Sets the radius of label rectangle.
	 * 
	 * @param cornerRadius the radius of label rectangle
	 */
	public void setCornerRadius(double cornerRadius) {
		setValue(Property.CORNER_RADIUS, cornerRadius);
	}

	/**
	 * Returns the radius of label rectangle.
	 * 
	 * @return the radius of label rectangle
	 */
	@Override
	public double getCornerRadius() {
		return getValue(Property.CORNER_RADIUS, defaultValues.getCornerRadius());
	}

	/**
	 * Sets the anchor position of label on line.
	 * 
	 * @param position the anchor position of label on line
	 */
	public void setPosition(LineLabelPosition position) {
		setValue(Property.POSITION, position);
	}

	/**
	 * Returns the anchor position of label on line.
	 * 
	 * @return the anchor position of label on line
	 */
	@Override
	public LineLabelPosition getPosition() {
		return getValue(Property.POSITION, LineLabelPosition.values(), defaultValues.getPosition());
	}

	/**
	 * Sets the adjustment along x-axis (left-right) of label relative to above number (can be negative).<br>
	 * For horizontal lines positioned left or right, negative values move the label toward the edge, and positive values toward the center.
	 * 
	 * @param xAdjust the adjustment along x-axis (left-right) of label
	 */
	public void setXAdjust(double xAdjust) {
		setValue(Property.X_ADJUST, xAdjust);
	}

	/**
	 * Returns the adjustment along x-axis (left-right) of label relative to above number (can be negative).<br>
	 * For horizontal lines positioned left or right, negative values move the label toward the edge, and positive values toward the center.
	 * 
	 * @return the adjustment along x-axis (left-right) of label
	 */
	@Override
	public double getXAdjust() {
		return getValue(Property.X_ADJUST, defaultValues.getXAdjust());
	}

	/**
	 * Sets the adjustment along y-axis (top-bottom) of label relative to above number (can be negative).<br>
	 * For vertical lines positioned top or bottom, negative values move the label toward the edge, and positive values toward the center.
	 * 
	 * @param yAdjust the adjustment along y-axis (top-bottom) of label
	 */
	public void setYAdjust(double yAdjust) {
		setValue(Property.Y_ADJUST, yAdjust);
	}

	/**
	 * Returns the adjustment along y-axis (top-bottom) of label relative to above number (can be negative).<br>
	 * For vertical lines positioned top or bottom, negative values move the label toward the edge, and positive values toward the center.
	 * 
	 * @return the adjustment along y-axis (top-bottom) of label
	 */
	@Override
	public double getYAdjust() {
		return getValue(Property.Y_ADJUST, defaultValues.getYAdjust());
	}

	/**
	 * Sets the rotation of label in degrees.
	 * 
	 * @param rotation the rotation of label in degrees
	 */
	public void setRotation(double rotation) {
		setValue(Property.ROTATION, rotation);
	}

	/**
	 * Returns the rotation of label in degrees.
	 * 
	 * @return the rotation of label in degrees
	 */
	@Override
	public double getRotation() {
		return getValue(Property.ROTATION, defaultValues.getRotation());
	}

	/**
	 * Sets <code>true</code> whether the rotation of label must calculates automatically.
	 * 
	 * @param autoRotation <code>true</code> whether the rotation of label must calculates automatically
	 */
	public void setAutoRotation(boolean autoRotation) {
		// checks is enabling
		if (autoRotation) {
			setValue(Property.ROTATION, AUTO_ROTATION);
		} else {
			// otherwise removes the key
			remove(Property.ROTATION);
		}
	}

	/**
	 * Returns <code>true</code> whether the rotation of label must calculates automatically.
	 * 
	 * @return <code>true</code> whether the rotation of label must calculates automatically
	 */
	@Override
	public boolean isAutoRotation() {
		return isType(Property.ROTATION, ObjectType.STRING);
	}

	/**
	 * Sets the text to display in label.<br>
	 * Provide a list to display values on a new line.
	 * 
	 * @param content the text to display in label as multi-line values
	 */
	public void setContent(List<String> content) {
		// checks if argument is consistent
		if (content != null) {
			// stores it
			setContent(content.toArray(new String[0]));
		} else {
			// if here the argument is not consistent
			// then removes key
			remove(Property.CONTENT);
		}
	}

	/**
	 * Sets the text to display in label.<br>
	 * Provide an array to display values on a new line.
	 * 
	 * @param content the text to display in label
	 */
	public void setContent(String... content) {
		setValueOrArray(Property.CONTENT, content);
	}

	/**
	 * Sets the image to display in label.
	 * 
	 * @param content the image to display in label
	 */
	public void setContent(Img content) {
		setValue(Property.CONTENT, content);
	}

	/**
	 * Returns <code>true</code> if the content is set by an {@link Img}.
	 * 
	 * @return <code>true</code> if the content is set by an {@link Img}
	 */
	boolean isContentAsImage() {
		return isType(Property.CONTENT, ObjectType.OBJECT);
	}

	/**
	 * Returns the text to display in label as list.
	 * 
	 * @return the text to display in label as list
	 */
	public List<String> getContent() {
		// checks if the context is a string
		if (!isContentAsImage()) {
			// reads as array
			// and returns it
			ArrayString array = getValueOrArray(Property.CONTENT, UndefinedValues.STRING);
			return ArrayListHelper.list(array);
		}
		// if here the content is an image
		// then returns an empty list
		return Collections.emptyList();
	}

	/**
	 * Returns the text to display in label as list.
	 * 
	 * @return the text to display in label as list
	 */
	public Img getContentAsImage() {
		// checks if the context is a image
		if (isContentAsImage()) {
			return getValue(Property.CONTENT, UndefinedValues.IMAGE_ELEMENT);
		}
		// if here, the content is not an image
		// then returns the undefined image
		return UndefinedValues.IMAGE_ELEMENT;
	}

	/**
	 * Sets the height of label content, when is set as {@link Img}, in pixels in order to scale the image when drawn.
	 * 
	 * @param height the height of label content, when is set as {@link Img}, in pixels in order to scale the image when drawn
	 */
	public void setHeight(int height) {
		setValue(Property.HEIGHT, height);
	}

	/**
	 * Sets the height of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn.
	 * 
	 * @param heightPercentage the height of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn
	 */
	public void setHeight(String heightPercentage) {
		setValue(Property.HEIGHT, heightPercentage);
	}

	/**
	 * Returns the height of label content, when is set as {@link Img}, in pixels in order to scale the image when drawn.
	 * 
	 * @return the height of label content, when is set as {@link Img}, in pixels in order to scale the image when drawn
	 */
	@Override
	public int getHeight() {
		// checks if the property is set as a number
		if (isType(Property.HEIGHT, ObjectType.NUMBER)) {
			return getValue(Property.HEIGHT, defaultValues.getHeight());
		}
		// if here is not a number then
		// returns the default
		return defaultValues.getHeight();
	}

	/**
	 * Returns the height of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn.
	 * 
	 * @return the height of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn
	 */
	@Override
	public String getHeightAsString() {
		// checks if the property is set as a string
		if (isType(Property.HEIGHT, ObjectType.STRING)) {
			return getValue(Property.HEIGHT, defaultValues.getHeightAsString());
		}
		// if here is not a string then
		// returns the default
		return defaultValues.getHeightAsString();
	}

	/**
	 * Sets the width of label content, when is set as {@link Img}, in pixels in order to scale the image when drawn.
	 * 
	 * @param width the height of label content, when is set as {@link Img}, in pixels in order to scale the image when drawn
	 */
	public void setWidth(int width) {
		setValue(Property.WIDTH, width);
	}

	/**
	 * Sets the width of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn.
	 * 
	 * @param widthPercentage the height of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn
	 */
	public void setWidth(String widthPercentage) {
		setValue(Property.WIDTH, widthPercentage);
	}

	/**
	 * Returns the width of label content, when is set as {@link Img}, in pixels in order to scale the image when drawn.
	 * 
	 * @return the width of label content, when is set as {@link Img}, in pixels in order to scale the image when drawn
	 */
	@Override
	public int getWidth() {
		// checks if the property is set as a number
		if (isType(Property.WIDTH, ObjectType.NUMBER)) {
			return getValue(Property.WIDTH, defaultValues.getWidth());
		}
		// if here is not a number then
		// returns the default
		return defaultValues.getWidth();
	}

	/**
	 * Returns the width of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn.
	 * 
	 * @return the width of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn
	 */
	@Override
	public String getWidthAsString() {
		// checks if the property is set as a string
		if (isType(Property.WIDTH, ObjectType.STRING)) {
			return getValue(Property.WIDTH, defaultValues.getWidthAsString());
		}
		// if here is not a string then
		// returns the default
		return defaultValues.getWidthAsString();
	}

}
