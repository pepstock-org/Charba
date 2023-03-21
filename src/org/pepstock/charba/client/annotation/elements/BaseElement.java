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
package org.pepstock.charba.client.annotation.elements;

import java.util.Collections;
import java.util.List;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.annotation.AbstractAnnotation;
import org.pepstock.charba.client.annotation.AnnotationOptions;
import org.pepstock.charba.client.annotation.LabelAnnotation;
import org.pepstock.charba.client.annotation.enums.ContentType;
import org.pepstock.charba.client.annotation.enums.DrawTime;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.ArrayInteger;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.ArrayUtil;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.data.BarBorderRadius;
import org.pepstock.charba.client.defaults.IsDefaultPadding;
import org.pepstock.charba.client.defaults.globals.DefaultPadding;
import org.pepstock.charba.client.dom.elements.Canvas;
import org.pepstock.charba.client.dom.elements.Img;
import org.pepstock.charba.client.enums.CapStyle;
import org.pepstock.charba.client.enums.JoinStyle;
import org.pepstock.charba.client.enums.TextAlign;
import org.pepstock.charba.client.items.ChartElementOptions;
import org.pepstock.charba.client.items.Undefined;
import org.pepstock.charba.client.options.AbstractFont;
import org.pepstock.charba.client.options.AbstractPadding;
import org.pepstock.charba.client.options.IsFont;
import org.pepstock.charba.client.options.IsPadding;

/**
 * Base object to map all options which belongs to all annotations and labels container.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
abstract class BaseElement extends ChartElementOptions {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		BACKGROUND_COLOR("backgroundColor"),
		BACKGROUND_SHADOW_COLOR("backgroundShadowColor"),
		BORDER_CAP_STYLE("borderCapStyle"),
		BORDER_COLOR("borderColor"),
		BORDER_DASH("borderDash"),
		BORDER_DASH_OFFSET("borderDashOffset"),
		BORDER_JOIN_STYLE("borderJoinStyle"),
		BORDER_RADIUS("borderRadius"),
		BORDER_SHADOW_COLOR("borderShadowColor"),
		BORDER_WIDTH("borderWidth"),
		COLOR("color"),
		CONTENT("content"),
		DRAW_TIME("drawTime"),
		FONT("font"),
		HEIGHT("height"),
		OPACITY("opacity"),
		PADDING("padding"),
		POSITION("position"),
		ROTATION("rotation"),
		SHADOW_BLUR("shadowBlur"),
		SHADOW_OFFSET_X("shadowOffsetX"),
		SHADOW_OFFSET_Y("shadowOffsetY"),
		TEXT_ALIGN("textAlign"),
		X_ADJUST("xAdjust"),
		Y_ADJUST("yAdjust"),
		WIDTH("width"),
		Z("z");

		// name value of property
		private String value;

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

	// font instance
	private final InnerFontItem font;
	// padding instance
	private final InnerPaddingItem padding;

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param parent parent node to use to add this element where changed
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param nativeObject native object to map java script properties
	 */
	BaseElement(AbstractNode parent, Key childKey, NativeObject nativeObject) {
		super(parent, childKey, nativeObject);
		// loads inner element
		this.font = new InnerFontItem(this, getValue(Property.FONT));
		this.padding = new InnerPaddingItem(this, getValue(Property.PADDING));
	}

	/**
	 * Returns the font element.
	 * 
	 * @return the font element.
	 */
	public final IsFont getFont() {
		return font;
	}

	/**
	 * Returns the padding element.
	 * 
	 * @return the padding element.
	 */
	public final IsPadding getPadding() {
		return padding;
	}

	/**
	 * Returns the anchor position of label on line.
	 * 
	 * @return the anchor position of label on line
	 */
	public final AlignPositionElement getAlignPosition() {
		return new AlignPositionElement(this, Property.POSITION, getValue(Property.POSITION));
	}

	/**
	 * Sets the color of the border of annotation.
	 * 
	 * @param borderColor the color of the border of annotation
	 */
	public final void setBorderColor(IsColor borderColor) {
		setBorderColor(IsColor.checkAndGetValue(borderColor));
	}

	/**
	 * Sets the color of the border of annotation.
	 * 
	 * @param borderColor the color of the border of annotation
	 */
	public final void setBorderColor(String borderColor) {
		setValueAndAddToParent(Property.BORDER_COLOR, borderColor);
	}

	/**
	 * Returns the color of the border of annotation.
	 * 
	 * @return the color of the border of annotation
	 */
	public final String getBorderColorAsString() {
		return getValue(Property.BORDER_COLOR, Defaults.get().getGlobal().getBorderColorAsString());
	}

	/**
	 * Returns the color of the border of annotation.
	 * 
	 * @return the color of the border of annotation
	 */
	public final IsColor getBorderColor() {
		return ColorBuilder.parse(getBorderColorAsString());
	}

	/**
	 * Sets the width of the border in pixels.
	 * 
	 * @param borderWidth the width of the border in pixels.
	 */
	public final void setBorderWidth(int borderWidth) {
		setValueAndAddToParent(Property.BORDER_WIDTH, Checker.positiveOrZero(borderWidth));
	}

	/**
	 * Returns the width of the border in pixels.
	 * 
	 * @return the width of the border in pixels.
	 */
	public final int getBorderWidth() {
		return getValue(Property.BORDER_WIDTH, Undefined.INTEGER);
	}

	/**
	 * Sets the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 * 
	 * @param borderDash the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 */
	public final void setBorderDash(int... borderDash) {
		setArrayValueAndAddToParent(Property.BORDER_DASH, ArrayInteger.fromOrNull(borderDash));
	}

	/**
	 * Returns the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 * 
	 * @return the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 */
	public final List<Integer> getBorderDash() {
		ArrayInteger array = getArrayValue(Property.BORDER_DASH);
		// and transforms to a list
		return ArrayListHelper.list(array);
	}

	/**
	 * Sets the line dash pattern offset.
	 * 
	 * @param borderDashOffset the line dash pattern offset.
	 */
	public final void setBorderDashOffset(double borderDashOffset) {
		setValueAndAddToParent(Property.BORDER_DASH_OFFSET, borderDashOffset);
	}

	/**
	 * Returns the line dash pattern offset.
	 * 
	 * @return the line dash pattern offset.
	 */
	public final double getBorderDashOffset() {
		return getValue(Property.BORDER_DASH_OFFSET, Undefined.DOUBLE);
	}

	/**
	 * Sets the color of the border shadow of annotation.
	 * 
	 * @param borderColor the color of the border shadow of annotation
	 */
	public final void setBorderShadowColor(IsColor borderColor) {
		setBorderShadowColor(IsColor.checkAndGetValue(borderColor));
	}

	/**
	 * Sets the color of the border shadow of annotation.
	 * 
	 * @param borderShadowColor the border shadow of the border of annotation
	 */
	public final void setBorderShadowColor(String borderShadowColor) {
		setValueAndAddToParent(Property.BORDER_SHADOW_COLOR, borderShadowColor);
	}

	/**
	 * Returns the color of the border shadow of annotation.
	 * 
	 * @return the color of the border shadow of annotation
	 */
	public final String getBorderShadowColorAsString() {
		return getValue(Property.BORDER_SHADOW_COLOR, Defaults.get().getGlobal().getBorderColorAsString());
	}

	/**
	 * Sets the amount of blur applied to shadows.
	 * 
	 * @param shadowBlur the amount of blur applied to shadows
	 */
	public final void setShadowBlur(double shadowBlur) {
		setValueAndAddToParent(Property.SHADOW_BLUR, Checker.positiveOrZero(shadowBlur));
	}

	/**
	 * Returns the amount of blur applied to shadows.
	 * 
	 * @return the amount of blur applied to shadows
	 */
	public final double getShadowBlur() {
		return getValue(Property.SHADOW_BLUR, Undefined.DOUBLE);
	}

	/**
	 * Sets the distance that shadows will be offset horizontally.
	 * 
	 * @param shadowOffset the distance that shadows will be offset horizontally.
	 */
	public final void setShadowOffsetX(int shadowOffset) {
		setValueAndAddToParent(Property.SHADOW_OFFSET_X, shadowOffset);
	}

	/**
	 * Returns the distance that shadows will be offset horizontally.
	 * 
	 * @return the distance that shadows will be offset horizontally.
	 */
	public final int getShadowOffsetX() {
		return getValue(Property.SHADOW_OFFSET_X, Undefined.INTEGER);
	}

	/**
	 * Sets the distance that shadows will be offset vertically.
	 * 
	 * @param shadowOffset the distance that shadows will be offset vertically.
	 */
	public final void setShadowOffsetY(int shadowOffset) {
		setValueAndAddToParent(Property.SHADOW_OFFSET_Y, shadowOffset);
	}

	/**
	 * Returns the distance that shadows will be offset vertically.
	 * 
	 * @return the distance that shadows will be offset vertically.
	 */
	public final int getShadowOffsetY() {
		return getValue(Property.SHADOW_OFFSET_Y, Undefined.INTEGER);
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
		setValueAndAddToParent(Property.BACKGROUND_COLOR, backgroundColor);
	}

	/**
	 * Returns the color of the background of annotation.
	 * 
	 * @return the color of the background of annotation
	 */
	public final String getBackgroundColorAsString() {
		return getValue(Property.BACKGROUND_COLOR, Defaults.get().getGlobal().getBackgroundColorAsString());
	}

	/**
	 * Returns the color of the background of annotation.
	 * 
	 * @return the color of the background of annotation
	 */
	public final IsColor getBackgroundColor() {
		return ColorBuilder.parse(getBackgroundColorAsString());
	}

	/**
	 * Sets the border radius.
	 * 
	 * @param radius the border radius.
	 */
	public final void setBorderRadius(int radius) {
		setValueAndAddToParent(Property.BORDER_RADIUS, Checker.positiveOrZero(radius));
	}

	/**
	 * Sets the border radius (in pixels).
	 * 
	 * @param borderRadius the border radius (in pixels).
	 */
	public final void setBorderRadius(BarBorderRadius borderRadius) {
		setValueAndAddToParent(Property.BORDER_RADIUS, borderRadius);
	}

	/**
	 * Returns the border radius (in pixels).
	 * 
	 * @return the border radius (in pixels).
	 */
	public final int getBorderRadius() {
		// checks if was stored as number
		if (isType(Property.BORDER_RADIUS, ObjectType.NUMBER)) {
			return getValue(Property.BORDER_RADIUS, Undefined.INTEGER);
		} else if (isType(Property.BORDER_RADIUS, ObjectType.OBJECT)) {
			// if here, the property is a object
			BarBorderRadius object = getBorderRadiusAsObject();
			// checks if there is the same value
			if (object != null && object.areValuesEquals()) {
				// the returns the same value
				// in whatever property
				return object.getTopLeft();
			}
		}
		// if here, the property is missing
		// then returns default
		return Undefined.INTEGER;
	}

	/**
	 * Returns the border radius (in pixels).
	 * 
	 * @return the border radius (in pixels).
	 */
	public final BarBorderRadius getBorderRadiusAsObject() {
		// checks if was stored as object
		if (isType(Property.BORDER_RADIUS, ObjectType.OBJECT)) {
			return BarBorderRadius.FACTORY.create(getValue(Property.BORDER_RADIUS));
		} else if (isType(Property.BORDER_RADIUS, ObjectType.NUMBER)) {
			// if here, the property is a number
			// then returns new border radius object
			return new BarBorderRadius(getBorderRadius());
		}
		// if here, the property is missing
		// then returns null
		return null;
	}

	/**
	 * Sets how the end points of every line are drawn.
	 * 
	 * @param borderCapStyle how the end points of every line are drawn.
	 */
	public final void setBorderCapStyle(CapStyle borderCapStyle) {
		setValueAndAddToParent(Property.BORDER_CAP_STYLE, borderCapStyle);
	}

	/**
	 * Returns how the end points of every line are drawn.
	 * 
	 * @return how the end points of every line are drawn.
	 */
	public final CapStyle getBorderCapStyle() {
		return getValue(Property.BORDER_CAP_STYLE, CapStyle.values(), CapStyle.BUTT);
	}

	/**
	 * Sets how two connecting segments (of lines, arcs or curves) with non-zero lengths in a shape are joined together (degenerate segments with zero lengths, whose specified end
	 * points and control points are exactly at the same position, are skipped).
	 * 
	 * @param borderJoinStyle how two connecting segments (of lines, arcs or curves) with non-zero lengths in a shape are joined together
	 */
	public final void setBorderJoinStyle(JoinStyle borderJoinStyle) {
		setValueAndAddToParent(Property.BORDER_JOIN_STYLE, borderJoinStyle);
	}

	/**
	 * Returns how two connecting segments (of lines, arcs or curves) with non-zero lengths in a shape are joined together (degenerate segments with zero lengths, whose specified
	 * end points and control points are exactly at the same position, are skipped).
	 * 
	 * @return how two connecting segments (of lines, arcs or curves) with non-zero lengths in a shape are joined together
	 */
	public final JoinStyle getBorderJoinStyle() {
		return getValue(Property.BORDER_JOIN_STYLE, JoinStyle.values(), JoinStyle.MITER);
	}

	/**
	 * Sets the color of the shadow of annotation.
	 * 
	 * @param backgroundColor the color of the shadow of annotation
	 */
	public final void setBackgroundShadowColor(IsColor backgroundColor) {
		setBackgroundShadowColor(IsColor.checkAndGetValue(backgroundColor));
	}

	/**
	 * Sets the color of the shadow of annotation.
	 * 
	 * @param borderShadowColor the shadow of the border of annotation
	 */
	public final void setBackgroundShadowColor(String borderShadowColor) {
		setValueAndAddToParent(Property.BACKGROUND_SHADOW_COLOR, borderShadowColor);
	}

	/**
	 * Returns the color of the shadow of annotation.
	 * 
	 * @return the color of the shadow of annotation
	 */
	public final String getBackgroundShadowColorAsString() {
		return getValue(Property.BACKGROUND_SHADOW_COLOR, Defaults.get().getGlobal().getBackgroundColorAsString());
	}

	/**
	 * Returns the color of the shadow of annotation.
	 * 
	 * @return the color of the shadow of annotation
	 */
	public final IsColor getBackgroundShadowColor() {
		return ColorBuilder.parse(getBackgroundShadowColorAsString());
	}

	/**
	 * Sets the color of text as string.
	 * 
	 * @param fontColor the color of text
	 */
	public final void setColor(String... fontColor) {
		setValueOrArrayAndAddToParent(Property.COLOR, fontColor);
	}

	/**
	 * Sets the draw time which defines when the annotations are drawn.
	 * 
	 * @param drawTime the draw time which defines when the annotations are drawn
	 */
	public final void setDrawTime(DrawTime drawTime) {
		setValueAndAddToParent(Property.DRAW_TIME, drawTime);
	}

	/**
	 * Returns the draw time which defines when the annotations are drawn.
	 * 
	 * @return the draw time which defines when the annotations are drawn
	 */
	public final DrawTime getDrawTime() {
		return getValue(Property.DRAW_TIME, DrawTime.values(), AnnotationOptions.DEFAULT_DRAW_TIME);
	}

	/**
	 * Sets the color of text.
	 * 
	 * @param fontColor the color of text
	 */
	public final void setColor(IsColor... fontColor) {
		setValueOrArrayAndAddToParent(Property.COLOR, fontColor);
	}

	/**
	 * Returns the color of text as string.
	 * 
	 * @return the color of text
	 */
	public final List<String> getColorAsString() {
		ArrayString array = getValueOrArray(Property.COLOR, Defaults.get().getGlobal().getColorAsString());
		return ArrayListHelper.list(array);
	}

	/**
	 * Returns the color of text.
	 * 
	 * @return the color of text
	 */
	public final List<IsColor> getColor() {
		return ColorBuilder.parse(getColorAsString());
	}

	/**
	 * Sets the text to display in label.<br>
	 * Provide a list to display values on a new line.
	 * 
	 * @param content the text to display in label as multi-line values
	 */
	public final void setContent(List<String> content) {
		if (content != null) {
			// stores it
			setContent(ArrayUtil.toStrings(content));
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
	public final void setContent(String... content) {
		setValueOrArrayAndAddToParent(Property.CONTENT, content);
	}

	/**
	 * Sets the image to display in label.
	 * 
	 * @param content the image to display in label
	 */
	public final void setContent(Img content) {
		setValueAndAddToParent(Property.CONTENT, content);
	}

	/**
	 * Sets the canvas to display in label.
	 * 
	 * @param content the canvas to display in label
	 */
	public final void setContent(Canvas content) {
		setValueAndAddToParent(Property.CONTENT, content);
	}

	/**
	 * Returns the text to display in label as list.
	 * 
	 * @return the text to display in label as list
	 */
	public final List<String> getContent() {
		// gets type
		ContentType type = ContentType.get(this);
		// checks if the context is a string or array
		if (ContentType.ARRAY.equals(type) || ContentType.STRING.equals(type)) {
			// reads as array
			// and returns it
			ArrayString array = getValueOrArray(Property.CONTENT, Undefined.STRING);
			return ArrayListHelper.list(array);
		}
		// if here the content is not a text
		// then returns an empty list
		return Collections.emptyList();
	}

	/**
	 * Returns the text to display in label as {@link Img}.
	 * 
	 * @return the text to display in label as {@link Img}
	 */
	public final Img getContentAsImage() {
		// gets type
		ContentType type = ContentType.get(this);
		// checks if the context is a string or array
		if (ContentType.IMAGE.equals(type)) {
			// reads as image
			// and returns it
			return getValue(Property.CONTENT, Undefined.IMAGE_ELEMENT);
		}
		// if here, the content is not an image
		// then returns the undefined image
		return Undefined.IMAGE_ELEMENT;
	}

	/**
	 * Returns the text to display in label as {@link Canvas}.
	 * 
	 * @return the text to display in label as {@link Canvas}
	 */
	public final Canvas getContentAsCanvas() {
		// gets type
		ContentType type = ContentType.get(this);
		// checks if the context is a string or array
		if (ContentType.CANVAS.equals(type)) {
			// reads as image
			// and returns it
			return getValue(Property.CONTENT, Undefined.CANVAS_ELEMENT);
		}
		// if here, the content is not an canvas
		// then returns the undefined canvas
		return Undefined.CANVAS_ELEMENT;
	}

	/**
	 * Sets the height of label content, when is set as {@link Img}, in pixels in order to scale the image when drawn.
	 * 
	 * @param height the height of label content, when is set as {@link Img}, in pixels in order to scale the image when drawn
	 */
	public final void setImageHeight(int height) {
		setValueAndAddToParent(Property.HEIGHT, Checker.positiveOrZero(height));
	}

	/**
	 * Sets the height of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn.
	 * 
	 * @param heightPercentage the height of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn
	 */
	public final void setImageHeightAsPercentage(String heightPercentage) {
		setValueAndAddToParent(Property.HEIGHT, heightPercentage);
	}

	/**
	 * Returns the height of label content, when is set as {@link Img}, in pixels in order to scale the image when drawn.
	 * 
	 * @return the height of label content, when is set as {@link Img}, in pixels in order to scale the image when drawn
	 */
	public final int getImageHeight() {
		// checks if the property is set as a number
		if (isType(Property.HEIGHT, ObjectType.NUMBER)) {
			return getValue(Property.HEIGHT, Undefined.INTEGER);
		}
		// if here is not a number then
		// returns undefined
		return Undefined.INTEGER;
	}

	/**
	 * Returns the height of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn.
	 * 
	 * @return the height of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn
	 */
	public final String getImageHeightAsPercentage() {
		// checks if the property is set as a string
		if (isType(Property.HEIGHT, ObjectType.STRING)) {
			return getValue(Property.HEIGHT, Undefined.STRING);
		}
		// if here is not a string then
		// returns undefined
		return Undefined.STRING;
	}

	/**
	 * Sets the width of label content, when is set as {@link Img}, in pixels in order to scale the image when drawn.
	 * 
	 * @param width the height of label content, when is set as {@link Img}, in pixels in order to scale the image when drawn
	 */
	public final void setImageWidth(int width) {
		setValueAndAddToParent(Property.WIDTH, Checker.positiveOrZero(width));
	}

	/**
	 * Sets the width of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn.
	 * 
	 * @param widthPercentage the height of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn
	 */
	public final void setImageWidthAsPercentage(String widthPercentage) {
		setValueAndAddToParent(Property.WIDTH, widthPercentage);
	}

	/**
	 * Returns the width of label content, when is set as {@link Img}, in pixels in order to scale the image when drawn.
	 * 
	 * @return the width of label content, when is set as {@link Img}, in pixels in order to scale the image when drawn
	 */
	public final int getImageWidth() {
		// checks if the property is set as a number
		if (isType(Property.WIDTH, ObjectType.NUMBER)) {
			return getValue(Property.WIDTH, Undefined.INTEGER);
		}
		// if here is not a number then
		// returns undefined
		return Undefined.INTEGER;
	}

	/**
	 * Returns the width of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn.
	 * 
	 * @return the width of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn
	 */
	public final String getImageWidthAsPercentage() {
		// checks if the property is set as a string
		if (isType(Property.WIDTH, ObjectType.STRING)) {
			return getValue(Property.WIDTH, Undefined.STRING);
		}
		// if here is not a string then
		// returns undefined
		return Undefined.STRING;
	}

	/**
	 * Overrides the opacity of the image or canvas element. Could be set a number in the range 0.0 to 1.0, inclusive.<br>
	 * If undefined, uses the opacity of the image or canvas element.<br>
	 * It is used only when the content is an image or canvas element.
	 * 
	 * @param opacity the opacity of the image or canvas element. Could be set a number in the range 0.0 to 1.0, inclusive
	 */
	public final void setImageOpacity(double opacity) {
		// stores the value
		setValueAndAddToParent(Property.OPACITY, Checker.betweenOrDefault(opacity, 0, 1, LabelAnnotation.DEFAULT_IMAGE_OPACITY));
	}

	/**
	 * Overrides the opacity of the image or canvas element. Could be set a number in the range 0.0 to 1.0, inclusive.<br>
	 * If undefined, uses the opacity of the image or canvas element.<br>
	 * It is used only when the content is an image or canvas element.
	 * 
	 * @return the opacity of the image or canvas element. Could be set a number in the range 0.0 to 1.0, inclusive
	 */
	public final double getImageOpacity() {
		return getValue(Property.OPACITY, LabelAnnotation.DEFAULT_IMAGE_OPACITY);
	}

	/**
	 * Sets the rotation of annotation in degrees.
	 * 
	 * @param rotation the rotation of annotation in degrees
	 */
	public final void setRotation(double rotation) {
		setValueAndAddToParent(Property.ROTATION, rotation);
	}

	/**
	 * Returns the rotation of annotation in degrees.
	 * 
	 * @return the rotation of annotation in degrees
	 */
	public final double getRotation() {
		return getValue(Property.ROTATION, Undefined.DOUBLE);
	}

	/**
	 * Sets the horizontal alignment of the label text when multiple lines.
	 * 
	 * @param align the horizontal alignment of the label text when multiple lines
	 */
	public final void setTextAlign(TextAlign align) {
		setValueAndAddToParent(Property.TEXT_ALIGN, Key.isValid(align) ? align.getStartEndValue() : null);
	}

	/**
	 * Returns the horizontal alignment of the label text when multiple lines.
	 * 
	 * @return the horizontal alignment of the label text when multiple lines
	 */
	public final TextAlign getTextAlign() {
		return getValue(Property.TEXT_ALIGN, TextAlign.values(), TextAlign.CENTER);
	}

	/**
	 * Sets the adjustment along y-axis (top-bottom) of annotation relative to above number (can be negative).
	 * 
	 * @param yAdjust the adjustment along y-axis (top-bottom) of annotation
	 */
	public final void setYAdjust(double yAdjust) {
		setValueAndAddToParent(Property.Y_ADJUST, yAdjust);
	}

	/**
	 * Returns the adjustment along y-axis (top-bottom) of annotation relative to above number (can be negative).
	 * 
	 * @return the adjustment along y-axis (top-bottom) of annotation
	 */
	public final double getYAdjust() {
		return getValue(Property.Y_ADJUST, Undefined.INTEGER);
	}

	/**
	 * Sets the adjustment along x-axis (left-right) of annotation relative to above number (can be negative).
	 * 
	 * @param xAdjust the adjustment along x-axis (left-right) of annotation
	 */
	public final void setXAdjust(double xAdjust) {
		setValueAndAddToParent(Property.X_ADJUST, xAdjust);
	}

	/**
	 * Returns the adjustment along x-axis (left-right) of annotation relative to above number (can be negative).
	 * 
	 * @return the adjustment along x-axis (left-right) of annotation
	 */
	public final double getXAdjust() {
		return getValue(Property.X_ADJUST, Undefined.INTEGER);
	}

	/**
	 * Sets the property determines the drawing stack level of the box annotation element.<br>
	 * All visible elements will be drawn in ascending order of `z` option, with the same "drawTime" option.
	 * 
	 * @param z the property determines the drawing stack level of the box annotation element.<br>
	 *            All visible elements will be drawn in ascending order of `z` option, with the same "drawTime" option.
	 */
	public final void setZ(int z) {
		setValueAndAddToParent(Property.Z, z);
	}

	/**
	 * Returns the property determines the drawing stack level of the box annotation element.<br>
	 * All visible elements will be drawn in ascending order of `z` option, with the same "drawTime" option.
	 * 
	 * @return the property determines the drawing stack level of the box annotation element.<br>
	 *         All visible elements will be drawn in ascending order of `z` option, with the same "drawTime" option.
	 */
	public final int getZ() {
		return getValue(Property.Z, AbstractAnnotation.DEFAULT_Z);
	}

	/**
	 * Maps a font options as inner element.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class InnerFontItem extends AbstractFont {

		/**
		 * Creates the object with native object instance to be wrapped.
		 * 
		 * @param parent the native object container which font belongs to.
		 * @param nativeObject native object instance to be wrapped.
		 */
		private InnerFontItem(AbstractNode parent, NativeObject nativeObject) {
			super(parent, Defaults.get().getGlobal().getFont(), nativeObject);
		}
	}

	/**
	 * Maps a padding options as inner element.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class InnerPaddingItem extends AbstractPadding {

		// default padding with 0 to all dimensions
		private static final IsDefaultPadding DEFAULT_PADDING = new DefaultPadding(0);

		/**
		 * Creates the object with native object instance to be wrapped.
		 * 
		 * @param parent the native object container which font belongs to.
		 * @param nativeObject native object instance to be wrapped
		 */
		private InnerPaddingItem(AbstractNode parent, NativeObject nativeObject) {
			super(parent, DEFAULT_PADDING, nativeObject);
		}

	}
}