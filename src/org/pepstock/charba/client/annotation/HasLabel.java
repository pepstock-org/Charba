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
package org.pepstock.charba.client.annotation;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.annotation.callbacks.ContentCallback;
import org.pepstock.charba.client.annotation.callbacks.ImageOpacityCallback;
import org.pepstock.charba.client.annotation.callbacks.ImageSizeCallback;
import org.pepstock.charba.client.callbacks.ColorCallback;
import org.pepstock.charba.client.callbacks.FontsCallback;
import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.callbacks.PaddingCallback;
import org.pepstock.charba.client.callbacks.TextAlignCallback;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.dom.elements.Canvas;
import org.pepstock.charba.client.dom.elements.Img;
import org.pepstock.charba.client.enums.TextAlign;
import org.pepstock.charba.client.items.FontItem;
import org.pepstock.charba.client.items.Undefined;
import org.pepstock.charba.client.options.IsFont;
import org.pepstock.charba.client.options.IsPadding;
import org.pepstock.charba.client.options.IsScriptablePaddingProvider;

/**
 * Interface to map the label options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
interface HasLabel extends IsDefaultsLabelHandler, IsScriptablePaddingProvider<AnnotationContext> {

	/**
	 * Returns a label handler instance to use in the default methods of this interface.
	 * 
	 * @return a label handler instance
	 */
	LabelHandler getLabelHandler();

	/**
	 * Returns the font element.
	 * 
	 * @return the font element.
	 */
	@Override
	default IsFont getFont() {
		// checks if handler is consistent
		if (getLabelHandler() != null) {
			return getLabelHandler().getFont();
		}
		// if here, handler is not consistent
		// then returns the default
		return Defaults.get().getGlobal().getFont().create();
	}

	/**
	 * Sets the font of the text.
	 * 
	 * @param fonts the font of the text
	 */
	default void setFonts(FontItem... fonts) {
		// checks if handler is consistent
		if (getLabelHandler() != null) {
			// stores value
			getLabelHandler().setFonts(fonts);
		}

	}

	/**
	 * Sets the font of the text.
	 * 
	 * @param fonts the font of the text
	 */
	default void setFonts(List<FontItem> fonts) {
		// checks if handler is consistent
		if (getLabelHandler() != null) {
			// stores value
			getLabelHandler().setFonts(fonts);
		}

	}

	/**
	 * Returns the font of the text.
	 * 
	 * @return the font of the text
	 */
	default List<IsFont> getFonts() {
		// checks if handler is consistent
		if (getLabelHandler() != null) {
			return getLabelHandler().getFonts();
		}
		// if here, handler is not consistent
		// then returns the default
		return Arrays.asList(Defaults.get().getGlobal().getFont().create());
	}

	/**
	 * Returns the padding element.
	 * 
	 * @return the padding element.
	 */
	@Override
	default IsPadding getPadding() {
		// checks if handler is consistent
		if (getLabelHandler() != null) {
			return getLabelHandler().getPadding();
		}
		// if here, handler is not consistent
		// then returns the default
		return LabelHandler.INTERNAL_DEFAULT_PADDING;
	}

	/**
	 * Sets the color of text.
	 * 
	 * @param fontColor the color of text
	 */
	default void setColor(IsColor fontColor) {
		setColor(IsColor.checkAndGetValue(fontColor));
	}

	/**
	 * Sets the color of text as string.
	 * 
	 * @param fontColor the color of text
	 */
	default void setColor(String fontColor) {
		// checks if handler is consistent
		if (getLabelHandler() != null) {
			// stores value
			getLabelHandler().setColor(fontColor);
		}
	}

	/**
	 * Returns the color of text as string.
	 * 
	 * @return the color of text
	 */
	@Override
	default String getColorAsString() {
		// checks if handler is consistent
		if (getLabelHandler() != null) {
			return getLabelHandler().getColorAsString();
		}
		// if here, handler is not consistent
		// then returns the default
		return Defaults.get().getGlobal().getColorAsString();
	}

	/**
	 * Returns the color of text.
	 * 
	 * @return the color of text
	 */
	default IsColor getColor() {
		return ColorBuilder.parse(getColorAsString());
	}

	/**
	 * Sets the text to display in label.<br>
	 * Provide a list to display values on a new line.
	 * 
	 * @param content the text to display in label as multi-line values
	 */
	default void setContent(List<String> content) {
		// checks if handler is consistent
		if (getLabelHandler() != null) {
			// stores value
			getLabelHandler().setContent(content);
		}
	}

	/**
	 * Sets the text to display in label.<br>
	 * Provide an array to display values on a new line.
	 * 
	 * @param content the text to display in label
	 */
	default void setContent(String... content) {
		// checks if handler is consistent
		if (getLabelHandler() != null) {
			// stores value
			getLabelHandler().setContent(content);
		}
	}

	/**
	 * Sets the image to display in label.
	 * 
	 * @param content the image to display in label
	 */
	default void setContent(Img content) {
		// checks if handler is consistent
		if (getLabelHandler() != null) {
			// stores value
			getLabelHandler().setContent(content);
		}
	}

	/**
	 * Sets the canvas to display in label.
	 * 
	 * @param content the canvas to display in label
	 */
	default void setContent(Canvas content) {
		// checks if handler is consistent
		if (getLabelHandler() != null) {
			// stores value
			getLabelHandler().setContent(content);
		}
	}

	/**
	 * Returns the text to display in label as list.
	 * 
	 * @return the text to display in label as list
	 */
	@Override
	default List<String> getContent() {
		// checks if handler is consistent
		if (getLabelHandler() != null) {
			return getLabelHandler().getContent();
		}
		// if here, handler is not consistent
		// then returns the default
		return Collections.emptyList();
	}

	/**
	 * Returns the text to display in label as list.
	 * 
	 * @return the text to display in label as list
	 */
	@Override
	default Img getContentAsImage() {
		// checks if handler is consistent
		if (getLabelHandler() != null) {
			return getLabelHandler().getContentAsImage();
		}
		// if here, handler is not consistent
		// then returns the default
		return Undefined.IMAGE_ELEMENT;
	}

	/**
	 * Returns the text to display in label as {@link Canvas}.
	 * 
	 * @return the text to display in label as {@link Canvas}
	 */
	@Override
	default Canvas getContentAsCanvas() {
		// checks if handler is consistent
		if (getLabelHandler() != null) {
			return getLabelHandler().getContentAsCanvas();
		}
		// if here, handler is not consistent
		// then returns the default
		return Undefined.CANVAS_ELEMENT;
	}

	/**
	 * Sets the height of label content, when is set as {@link Img}, in pixels in order to scale the image when drawn.
	 * 
	 * @param height the height of label content, when is set as {@link Img}, in pixels in order to scale the image when drawn
	 */
	default void setImageHeight(int height) {
		// checks if handler is consistent
		if (getLabelHandler() != null) {
			// stores value
			getLabelHandler().setImageHeight(height);
		}
	}

	/**
	 * Sets the height of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn.
	 * 
	 * @param heightPercentage the height of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn
	 */
	default void setImageHeightAsPercentage(String heightPercentage) {
		// checks if handler is consistent
		if (getLabelHandler() != null) {
			// stores value
			getLabelHandler().setImageHeightAsPercentage(heightPercentage);
		}
	}

	/**
	 * Returns the height of label content, when is set as {@link Img}, in pixels in order to scale the image when drawn.
	 * 
	 * @return the height of label content, when is set as {@link Img}, in pixels in order to scale the image when drawn
	 */
	@Override
	default int getImageHeight() {
		// checks if handler is consistent
		if (getLabelHandler() != null) {
			return getLabelHandler().getImageHeight();
		}
		// if here, handler is not consistent
		// then returns the default
		return Undefined.INTEGER;
	}

	/**
	 * Returns the height of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn.
	 * 
	 * @return the height of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn
	 */
	@Override
	default String getImageHeightAsPercentage() {
		// checks if handler is consistent
		if (getLabelHandler() != null) {
			return getLabelHandler().getImageHeightAsPercentage();
		}
		// if here, handler is not consistent
		// then returns the default
		return Undefined.STRING;
	}

	/**
	 * Sets the width of label content, when is set as {@link Img}, in pixels in order to scale the image when drawn.
	 * 
	 * @param width the height of label content, when is set as {@link Img}, in pixels in order to scale the image when drawn
	 */
	default void setImageWidth(int width) {
		// checks if handler is consistent
		if (getLabelHandler() != null) {
			// stores value
			getLabelHandler().setImageWidth(width);
		}
	}

	/**
	 * Sets the width of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn.
	 * 
	 * @param widthPercentage the height of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn
	 */
	default void setImageWidthAsPercentage(String widthPercentage) {
		// checks if handler is consistent
		if (getLabelHandler() != null) {
			// stores value
			getLabelHandler().setImageWidthAsPercentage(widthPercentage);
		}
	}

	/**
	 * Returns the width of label content, when is set as {@link Img}, in pixels in order to scale the image when drawn.
	 * 
	 * @return the width of label content, when is set as {@link Img}, in pixels in order to scale the image when drawn
	 */
	@Override
	default int getImageWidth() {
		// checks if handler is consistent
		if (getLabelHandler() != null) {
			return getLabelHandler().getImageWidth();
		}
		// if here, handler is not consistent
		// then returns the default
		return Undefined.INTEGER;
	}

	/**
	 * Returns the width of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn.
	 * 
	 * @return the width of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn
	 */
	@Override
	default String getImageWidthAsPercentage() {
		// checks if handler is consistent
		if (getLabelHandler() != null) {
			return getLabelHandler().getImageWidthAsPercentage();
		}
		// if here, handler is not consistent
		// then returns the default
		return Undefined.STRING;
	}

	/**
	 * Overrides the opacity of the image or canvas element. Could be set a number in the range 0.0 to 1.0, inclusive.<br>
	 * If undefined, uses the opacity of the image or canvas element.<br>
	 * It is used only when the content is an image or canvas element.
	 * 
	 * @param opacity the opacity of the image or canvas element. Could be set a number in the range 0.0 to 1.0, inclusive
	 */
	default void setImageOpacity(double opacity) {
		// checks if handler is consistent
		if (getLabelHandler() != null) {
			// stores value
			getLabelHandler().setImageOpacity(opacity);
		}
	}

	/**
	 * Overrides the opacity of the image or canvas element. Could be set a number in the range 0.0 to 1.0, inclusive.<br>
	 * If undefined, uses the opacity of the image or canvas element.<br>
	 * It is used only when the content is an image or canvas element.
	 * 
	 * @return the opacity of the image or canvas element. Could be set a number in the range 0.0 to 1.0, inclusive
	 */
	@Override
	default double getImageOpacity() {
		// checks if handler is consistent
		if (getLabelHandler() != null) {
			return getLabelHandler().getImageOpacity();
		}
		// if here, handler is not consistent
		// then returns the default
		return LabelAnnotation.DEFAULT_IMAGE_OPACITY;
	}

	/**
	 * Sets the horizontal alignment of the label text when multiple lines.
	 * 
	 * @param align the horizontal alignment of the label text when multiple lines
	 */
	default void setTextAlign(TextAlign align) {
		// checks if handler is consistent
		if (getLabelHandler() != null) {
			// stores value
			getLabelHandler().setTextAlign(align);
		}

	}

	/**
	 * Returns the horizontal alignment of the label text when multiple lines.
	 * 
	 * @return the horizontal alignment of the label text when multiple lines
	 */
	@Override
	default TextAlign getTextAlign() {
		// checks if handler is consistent
		if (getLabelHandler() != null) {
			return getLabelHandler().getTextAlign();
		}
		// if here, handler is not consistent
		// then returns the default
		return TextAlign.CENTER;
	}

	// ---------------------
	// CALLBACKS
	// ---------------------

	/**
	 * Returns the callback called to set the color of the text of label.
	 * 
	 * @return the callback called to set the color of the text of label
	 */
	@Override
	default ColorCallback<AnnotationContext> getColorCallback() {
		// checks if handler is consistent
		if (getLabelHandler() != null) {
			return getLabelHandler().getColorCallback();
		}
		// if here, handler is not consistent
		// then returns the default
		return null;
	}

	/**
	 * Sets the callback to set the color of the text of label.
	 * 
	 * @param colorCallback to set the color of the text of label
	 */
	default void setColor(ColorCallback<AnnotationContext> colorCallback) {
		// checks if handler is consistent
		if (getLabelHandler() != null) {
			// stores value
			getLabelHandler().setColor(colorCallback);
		}
	}

	/**
	 * Sets the callback to set the color of the text of label.
	 * 
	 * @param colorCallback to set the color of the text of label
	 */
	default void setColor(NativeCallback colorCallback) {
		// checks if handler is consistent
		if (getLabelHandler() != null) {
			// stores value
			getLabelHandler().setColor(colorCallback);
		}
	}

	/**
	 * Returns the callback called to set the text to display in label as list.
	 * 
	 * @return the callback called to set the text to display in label as list
	 */
	@Override
	default ContentCallback getContentCallback() {
		// checks if handler is consistent
		if (getLabelHandler() != null) {
			return getLabelHandler().getContentCallback();
		}
		// if here, handler is not consistent
		// then returns the default
		return null;
	}

	/**
	 * Sets the callback to set the text to display in label as list.
	 * 
	 * @param contentCallback to set the text to display in label as list
	 */
	default void setContent(ContentCallback contentCallback) {
		// checks if handler is consistent
		if (getLabelHandler() != null) {
			// stores value
			getLabelHandler().setContent(contentCallback);
		}
	}

	/**
	 * Sets the callback to set the text to display in label as list.
	 * 
	 * @param contentCallback to set the text to display in label as list
	 */
	default void setContent(NativeCallback contentCallback) {
		// checks if handler is consistent
		if (getLabelHandler() != null) {
			// stores value
			getLabelHandler().setContent(contentCallback);
		}
	}

	/**
	 * Returns the callback called to set the height of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn.
	 * 
	 * @return the callback called to set the height of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn
	 */
	@Override
	default ImageSizeCallback getImageHeightCallback() {
		// checks if handler is consistent
		if (getLabelHandler() != null) {
			return getLabelHandler().getImageHeightCallback();
		}
		// if here, handler is not consistent
		// then returns the default
		return null;
	}

	/**
	 * Sets the callback to set the height of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn.
	 * 
	 * @param imageSizeCallback to set the height of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn
	 */
	default void setImageHeight(ImageSizeCallback imageSizeCallback) {
		// checks if handler is consistent
		if (getLabelHandler() != null) {
			// stores value
			getLabelHandler().setImageHeight(imageSizeCallback);
		}
	}

	/**
	 * Sets the callback to set the height of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn.
	 * 
	 * @param imageSizeCallback to set the height of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn
	 */
	default void setImageHeight(NativeCallback imageSizeCallback) {
		// checks if handler is consistent
		if (getLabelHandler() != null) {
			// stores value
			getLabelHandler().setImageHeight(imageSizeCallback);
		}
	}

	/**
	 * Returns the callback called to set the width of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn.
	 * 
	 * @return the callback called to set the width of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn
	 */
	@Override
	default ImageSizeCallback getImageWidthCallback() {
		// checks if handler is consistent
		if (getLabelHandler() != null) {
			return getLabelHandler().getImageWidthCallback();
		}
		// if here, handler is not consistent
		// then returns the default
		return null;
	}

	/**
	 * Sets the callback to set the width of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn.
	 * 
	 * @param imageSizeCallback to set the width of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn
	 */
	default void setImageWidth(ImageSizeCallback imageSizeCallback) {
		// checks if handler is consistent
		if (getLabelHandler() != null) {
			// stores value
			getLabelHandler().setImageWidth(imageSizeCallback);
		}
	}

	/**
	 * Sets the callback to set the width of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn.
	 * 
	 * @param imageSizeCallback to set the width of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn
	 */
	default void setImageWidth(NativeCallback imageSizeCallback) {
		// checks if handler is consistent
		if (getLabelHandler() != null) {
			// stores value
			getLabelHandler().setImageWidth(imageSizeCallback);
		}
	}

	/**
	 * Returns the callback called to set the horizontal alignment of the label text when multiple lines.
	 * 
	 * @return the callback called to set the horizontal alignment of the label text when multiple lines
	 */
	@Override
	default TextAlignCallback<AnnotationContext> getTextAlignCallback() {
		// checks if handler is consistent
		if (getLabelHandler() != null) {
			return getLabelHandler().getTextAlignCallback();
		}
		// if here, handler is not consistent
		// then returns the default
		return null;
	}

	/**
	 * Sets the callback to set the horizontal alignment of the label text when multiple lines.
	 * 
	 * @param alignCallback to the horizontal alignment of the label text when multiple lines
	 */
	default void setTextAlign(TextAlignCallback<AnnotationContext> alignCallback) {
		// checks if handler is consistent
		if (getLabelHandler() != null) {
			// stores value
			getLabelHandler().setTextAlign(alignCallback);
		}
	}

	/**
	 * Sets the callback to set the horizontal alignment of the label text when multiple lines.
	 * 
	 * @param alignCallback to the horizontal alignment of the label text when multiple lines
	 */
	default void setTextAlign(NativeCallback alignCallback) {
		// checks if handler is consistent
		if (getLabelHandler() != null) {
			// stores value
			getLabelHandler().setTextAlign(alignCallback);
		}
	}

	/**
	 * Returns the callback called to set the padding of label.
	 * 
	 * @return the callback called to set the padding of label
	 */
	@Override
	default PaddingCallback<AnnotationContext> getPaddingCallback() {
		// checks if handler is consistent
		if (getLabelHandler() != null) {
			return getLabelHandler().getPaddingCallback();
		}
		// if here, handler is not consistent
		// then returns the default
		return null;
	}

	/**
	 * Sets the callback to set the padding of label.
	 * 
	 * @param paddingCallback to set the padding of label
	 */
	@Override
	default void setPadding(PaddingCallback<AnnotationContext> paddingCallback) {
		// checks if handler is consistent
		if (getLabelHandler() != null) {
			// stores value
			getLabelHandler().setPadding(paddingCallback);
		}
	}

	/**
	 * Sets the callback to set the padding of label to add left and right.
	 * 
	 * @param paddingCallback to set the padding of label to add left and right
	 */
	@Override
	default void setPadding(NativeCallback paddingCallback) {
		// checks if handler is consistent
		if (getLabelHandler() != null) {
			// stores value
			getLabelHandler().setPadding(paddingCallback);
		}
	}

	/**
	 * Returns the font callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the font callback, if set, otherwise <code>null</code>.
	 */
	@Override
	default FontsCallback<AnnotationContext> getFontCallback() {
		// checks if handler is consistent
		if (getLabelHandler() != null) {
			return getLabelHandler().getFontCallback();
		}
		// if here, handler is not consistent
		// then returns the default
		return null;
	}

	/**
	 * Sets the font callback.
	 * 
	 * @param fontCallback the font callback to set
	 */
	default void setFont(FontsCallback<AnnotationContext> fontCallback) {
		// checks if handler is consistent
		if (getLabelHandler() != null) {
			// stores value
			getLabelHandler().setFont(fontCallback);
		}
	}

	/**
	 * Sets the font callback.
	 * 
	 * @param fontCallback the font callback to set
	 */
	default void setFont(NativeCallback fontCallback) {
		// checks if handler is consistent
		if (getLabelHandler() != null) {
			// stores value
			getLabelHandler().setFont(fontCallback);
		}
	}

	/**
	 * Returns the opacity callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the opacity callback, if set, otherwise <code>null</code>.
	 */
	@Override
	default ImageOpacityCallback getImageOpacityCallback() {
		// checks if handler is consistent
		if (getLabelHandler() != null) {
			return getLabelHandler().getImageOpacityCallback();
		}
		// if here, handler is not consistent
		// then returns the default
		return null;
	}

	/**
	 * Sets the opacity callback.
	 * 
	 * @param opacityCallback the opacity callback to set
	 */
	default void setImageOpacity(ImageOpacityCallback opacityCallback) {
		// checks if handler is consistent
		if (getLabelHandler() != null) {
			// stores value
			getLabelHandler().setImageOpacity(opacityCallback);
		}
	}

	/**
	 * Sets the opacity callback.
	 * 
	 * @param opacityCallback the opacity callback to set
	 */
	default void setImageOpacity(NativeCallback opacityCallback) {
		// checks if handler is consistent
		if (getLabelHandler() != null) {
			// stores value
			getLabelHandler().setImageOpacity(opacityCallback);
		}
	}
}