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

import java.util.Collections;
import java.util.List;

import org.pepstock.charba.client.annotation.callbacks.ContentCallback;
import org.pepstock.charba.client.annotation.callbacks.ImageOpacityCallback;
import org.pepstock.charba.client.annotation.callbacks.ImageSizeCallback;
import org.pepstock.charba.client.callbacks.ColorsCallback;
import org.pepstock.charba.client.callbacks.FontsCallback;
import org.pepstock.charba.client.callbacks.PaddingCallback;
import org.pepstock.charba.client.callbacks.TextAlignCallback;
import org.pepstock.charba.client.defaults.IsDefaultFont;
import org.pepstock.charba.client.defaults.IsDefaultPadding;
import org.pepstock.charba.client.dom.elements.Canvas;
import org.pepstock.charba.client.dom.elements.Img;
import org.pepstock.charba.client.enums.TextAlign;
import org.pepstock.charba.client.items.Undefined;

/**
 * This is the {@link AnnotationPlugin#ID} plugin LABEL handler DEFAULTS options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
interface IsDefaultsLabelHandler {

	/**
	 * Returns the font element.
	 * 
	 * @return the font element.
	 */
	IsDefaultFont getFont();

	/**
	 * Returns the padding.
	 * 
	 * @return the padding instance
	 */
	IsDefaultPadding getPadding();

	/**
	 * Returns the font color of text as string.
	 * 
	 * @return the font color of text
	 */
	List<String> getColorAsString();

	/**
	 * Returns the text to display in label as list.
	 * 
	 * @return the text to display in label as list
	 */
	default List<String> getContent() {
		return Collections.emptyList();
	}

	/**
	 * Returns the text to display in label as {@link Img}.
	 * 
	 * @return the text to display in label as {@link Img}
	 */
	default Img getContentAsImage() {
		return Undefined.IMAGE_ELEMENT;
	}

	/**
	 * Returns the text to display in label as {@link Canvas}.
	 * 
	 * @return the text to display in label as {@link Canvas}
	 */
	default Canvas getContentAsCanvas() {
		return Undefined.CANVAS_ELEMENT;
	}

	/**
	 * Returns the height of label content, when is set as {@link Img}, in pixels in order to scale the image when drawn.
	 * 
	 * @return the height of label content, when is set as {@link Img}, in pixels in order to scale the image when drawn
	 */
	default int getImageHeight() {
		return Undefined.INTEGER;
	}

	/**
	 * Returns the height of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn.
	 * 
	 * @return the height of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn
	 */
	default String getImageHeightAsPercentage() {
		return null;
	}

	/**
	 * Returns the width of label content, when is set as {@link Img}, in pixels in order to scale the image when drawn.
	 * 
	 * @return the width of label content, when is set as {@link Img}, in pixels in order to scale the image when drawn
	 */
	default int getImageWidth() {
		return Undefined.INTEGER;
	}

	/**
	 * Returns the width of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn.
	 * 
	 * @return the width of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn
	 */
	default String getImageWidthAsPercentage() {
		return null;
	}

	/**
	 * Overrides the opacity of the image or canvas element. Could be set a number in the range 0.0 to 1.0, inclusive.<br>
	 * If undefined, uses the opacity of the image or canvas element.<br>
	 * It is used only when the content is an image or canvas element.
	 * 
	 * @return the opacity of the image or canvas element. Could be set a number in the range 0.0 to 1.0, inclusive
	 */
	default double getImageOpacity() {
		return LabelAnnotation.DEFAULT_IMAGE_OPACITY;
	}

	/**
	 * Returns the horizontal alignment of the label text when multiple lines.
	 * 
	 * @return the horizontal alignment of the label text when multiple lines
	 */
	TextAlign getTextAlign();

	// ----------------
	// CALLBACKS
	// ----------------

	/**
	 * Returns the callback called to set the color of the text of label.
	 * 
	 * @return the callback called to set the color of the text of label
	 */
	default ColorsCallback<AnnotationContext> getColorCallback() {
		return null;
	}

	/**
	 * Returns the callback called to set the text to display in label as list.
	 * 
	 * @return the callback called to set the text to display in label as list
	 */
	default ContentCallback getContentCallback() {
		return null;
	}

	/**
	 * Returns the callback called to set the height of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn.
	 * 
	 * @return the callback called to set the height of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn
	 */
	default ImageSizeCallback getImageHeightCallback() {
		return null;
	}

	/**
	 * Returns the callback called to set the width of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn.
	 * 
	 * @return the callback called to set the width of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn
	 */
	default ImageSizeCallback getImageWidthCallback() {
		return null;
	}

	/**
	 * Returns the callback called to set the horizontal alignment of the label text when multiple lines.
	 * 
	 * @return the callback called to set the horizontal alignment of the label text when multiple lines
	 */
	default TextAlignCallback<AnnotationContext> getTextAlignCallback() {
		return null;
	}

	/**
	 * Returns the callback called to set the padding of label.
	 * 
	 * @return the callback called to set the padding of label
	 */
	default PaddingCallback<AnnotationContext> getPaddingCallback() {
		return null;
	}

	/**
	 * Returns the font callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the font callback, if set, otherwise <code>null</code>.
	 */
	default FontsCallback<AnnotationContext> getFontCallback() {
		return null;
	}

	/**
	 * Returns the opacity callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the opacity callback, if set, otherwise <code>null</code>.
	 */
	default ImageOpacityCallback getImageOpacityCallback() {
		return null;
	}
}