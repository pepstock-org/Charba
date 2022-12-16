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
package org.pepstock.charba.client.datalabels;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.callbacks.ColorCallback;
import org.pepstock.charba.client.callbacks.DisplayCallback;
import org.pepstock.charba.client.callbacks.FontCallback;
import org.pepstock.charba.client.callbacks.OffsetCallback;
import org.pepstock.charba.client.callbacks.PaddingCallback;
import org.pepstock.charba.client.callbacks.RadiusCallback;
import org.pepstock.charba.client.callbacks.RotationCallback;
import org.pepstock.charba.client.callbacks.TextAlignCallback;
import org.pepstock.charba.client.callbacks.WidthCallback;
import org.pepstock.charba.client.datalabels.callbacks.AlignCallback;
import org.pepstock.charba.client.datalabels.callbacks.AnchorCallback;
import org.pepstock.charba.client.datalabels.callbacks.ClampCallback;
import org.pepstock.charba.client.datalabels.callbacks.ClipCallback;
import org.pepstock.charba.client.datalabels.callbacks.FormatterCallback;
import org.pepstock.charba.client.datalabels.callbacks.OpacityCallback;
import org.pepstock.charba.client.datalabels.callbacks.TextShadowBlurCallback;
import org.pepstock.charba.client.datalabels.enums.Align;
import org.pepstock.charba.client.datalabels.enums.Anchor;
import org.pepstock.charba.client.defaults.IsDefaultFont;
import org.pepstock.charba.client.defaults.IsDefaultPadding;
import org.pepstock.charba.client.enums.Display;
import org.pepstock.charba.client.enums.TextAlign;

/**
 * This is the base interface to map {@link DataLabelsPlugin#ID} plugin options, for a single label.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
interface IsDefaultDataLabelsItem {

	/**
	 * Returns the font element.
	 * 
	 * @return the font element.
	 */
	IsDefaultFont getFont();

	/**
	 * Returns the padding element.
	 * 
	 * @return the padding element.
	 */
	IsDefaultPadding getPadding();

	/**
	 * Returns the listeners element.
	 * 
	 * @return the listeners element.
	 */
	IsDefaultListeners getListeners();

	/**
	 * Returns the position of the label relative to the anchor point position and orientation.
	 * 
	 * @return the position of the label relative to the anchor point position and orientation.
	 */
	default Align getAlign() {
		return DataLabelsOptions.DEFAULT_ALIGN;
	}

	/**
	 * Returns the anchor point, which is defined by an orientation vector and a position on the data element
	 * 
	 * @return the anchor point, which is defined by an orientation vector and a position on the data element.
	 */
	default Anchor getAnchor() {
		return DataLabelsOptions.DEFAULT_ANCHOR;
	}

	/**
	 * Returns the background color as string.
	 * 
	 * @return the background color as string. If <code>null</code>, it uses the background color of dataset.
	 */
	default String getBackgroundColorAsString() {
		return DataLabelsOptions.DEFAULT_BACKGROUND_COLOR;
	}

	/**
	 * Returns the border color as string.
	 * 
	 * @return the border color as string. If <code>null</code>, it uses the border color of dataset.
	 */
	default String getBorderColorAsString() {
		return DataLabelsOptions.DEFAULT_BORDER_COLOR;
	}

	/**
	 * Returns the border radius.
	 * 
	 * @return the border radius.
	 */
	default double getBorderRadius() {
		return DataLabelsOptions.DEFAULT_BORDER_RADIUS;
	}

	/**
	 * Returns the border width.
	 * 
	 * @return the border width.
	 */
	default int getBorderWidth() {
		return DataLabelsOptions.DEFAULT_BORDER_WIDTH;
	}

	/**
	 * Returns <code>true</code> to enforce the anchor position to be calculated based on the visible geometry of the associated element (i.e. part inside the chart area).
	 * 
	 * @return <code>true</code> to enforce the anchor position to be calculated based on the visible geometry of the associated element (i.e. part inside the chart area).
	 */
	default boolean isClamp() {
		return DataLabelsOptions.DEFAULT_CLAMP;
	}

	/**
	 * When the clip option is <code>true</code>, the part of the label which is outside the chart area will be masked.
	 * 
	 * @return when the clip option is <code>true</code>, the part of the label which is outside the chart area will be masked.
	 */
	default boolean isClip() {
		return DataLabelsOptions.DEFAULT_CLIP;
	}

	/**
	 * Returns the color as string.
	 * 
	 * @return the color as string.
	 */
	default String getColorAsString() {
		return Defaults.get().getGlobal().getColorAsString();
	}

	/**
	 * Returns the visibility of labels.
	 * 
	 * @return the visibility of labels.
	 */
	default Display getDisplay() {
		return DataLabelsOptions.DEFAULT_DISPLAY;
	}

	/**
	 * Returns the distance (in pixels) to pull the label away from the anchor point. This option is not applicable when align is 'center'. Also note that if align is 'start', the
	 * label is moved in the opposite direction.
	 * 
	 * @return the distance (in pixels) to pull the label away from the anchor point. This option is not applicable when align is 'center'. Also note that if align is 'start', the
	 *         label is moved in the opposite direction.
	 */
	default int getOffset() {
		return DataLabelsOptions.DEFAULT_OFFSET;
	}

	/**
	 * Returns the opacity.
	 * 
	 * @return the opacity.
	 */
	default double getOpacity() {
		return DataLabelsOptions.DEFAULT_OPACITY;
	}

	/**
	 * Returns the clockwise rotation angle (in degrees) of the label, the rotation center point being the label center.
	 * 
	 * @return the clockwise rotation angle (in degrees) of the label, the rotation center point being the label center.
	 */
	default double getRotation() {
		return DataLabelsOptions.DEFAULT_ROTATION;
	}

	/**
	 * Returns the text alignment being used when drawing the label text.
	 * 
	 * @return the text alignment being used when drawing the label text.
	 */
	default TextAlign getTextAlign() {
		return DataLabelsOptions.DEFAULT_TEXT_ALIGN;
	}

	/**
	 * Returns the text stroke color as string.
	 * 
	 * @return the text stroke color as string.
	 */
	default String getTextStrokeColorAsString() {
		return getColorAsString();
	}

	/**
	 * Returns the text stroke width.
	 * 
	 * @return the text stroke width.
	 */
	default int getTextStrokeWidth() {
		return DataLabelsOptions.DEFAULT_TEXT_STROKE_WIDTH;
	}

	/**
	 * Returns the text shadow blur.
	 * 
	 * @return the text shadow blur.
	 */
	default double getTextShadowBlur() {
		return DataLabelsOptions.DEFAULT_TEXT_SHADOW_BLUR;
	}

	/**
	 * Returns the text shadow color as string.
	 * 
	 * @return the text shadow color as string.
	 */
	default String getTextShadowColorAsString() {
		return getColorAsString();
	}

	/**
	 * Returns the background color callback.
	 * 
	 * @return the background color callback.
	 */
	default ColorCallback<DataLabelsContext> getBackgroundColorCallback() {
		return null;
	}

	/**
	 * Returns the border color callback.
	 * 
	 * @return the border color callback.
	 */
	default ColorCallback<DataLabelsContext> getBorderColorCallback() {
		return null;
	}

	/**
	 * Returns the color callback.
	 * 
	 * @return the color callback.
	 */
	default ColorCallback<DataLabelsContext> getColorCallback() {
		return null;
	}

	/**
	 * Returns the formatter callback.
	 * 
	 * @return the formatter callback.
	 */
	default FormatterCallback getFormatterCallback() {
		return null;
	}

	/**
	 * Returns the align callback.
	 * 
	 * @return the align callback.
	 */
	default AlignCallback getAlignCallback() {
		return null;
	}

	/**
	 * Returns the anchor callback.
	 * 
	 * @return the anchor callback.
	 */
	default AnchorCallback getAnchorCallback() {
		return null;
	}

	/**
	 * Returns the border radius callback.
	 * 
	 * @return the border radius callback.
	 */
	default RadiusCallback<DataLabelsContext> getBorderRadiusCallback() {
		return null;
	}

	/**
	 * Returns the border width callback.
	 * 
	 * @return the border width callback.
	 */
	default WidthCallback<DataLabelsContext> getBorderWidthCallback() {
		return null;
	}

	/**
	 * Returns the clamp callback.
	 * 
	 * @return the clamp callback.
	 */
	default ClampCallback getClampCallback() {
		return null;
	}

	/**
	 * Returns the clip callback.
	 * 
	 * @return the clip callback.
	 */
	default ClipCallback getClipCallback() {
		return null;
	}

	/**
	 * Returns the display callback.
	 * 
	 * @return the display callback.
	 */
	default DisplayCallback<DataLabelsContext> getDisplayCallback() {
		return null;
	}

	/**
	 * Returns the offset callback.
	 * 
	 * @return the offset callback.
	 */
	default OffsetCallback<DataLabelsContext> getOffsetCallback() {
		return null;
	}

	/**
	 * Returns the opacity callback.
	 * 
	 * @return the opacity callback.
	 */
	default OpacityCallback getOpacityCallback() {
		return null;
	}

	/**
	 * Returns the rotation callback.
	 * 
	 * @return the rotation callback.
	 */
	default RotationCallback<DataLabelsContext> getRotationCallback() {
		return null;
	}

	/**
	 * Returns the text align callback.
	 * 
	 * @return the text align callback.
	 */
	default TextAlignCallback<DataLabelsContext> getTextAlignCallback() {
		return null;
	}

	/**
	 * Returns the text stroke color callback.
	 * 
	 * @return the text stroke color callback.
	 */
	default ColorCallback<DataLabelsContext> getTextStrokeColorCallback() {
		return null;
	}

	/**
	 * Returns the text stroke width callback.
	 * 
	 * @return the text stroke width callback.
	 */
	default WidthCallback<DataLabelsContext> getTextStrokeWidthCallback() {
		return null;
	}

	/**
	 * Returns the text shadow blur callback.
	 * 
	 * @return the text shadow blur callback.
	 */
	default TextShadowBlurCallback getTextShadowBlurCallback() {
		return null;
	}

	/**
	 * Returns the text shadow color callback.
	 * 
	 * @return the text shadow color callback.
	 */
	default ColorCallback<DataLabelsContext> getTextShadowColorCallback() {
		return null;
	}

	/**
	 * Returns the font callback.
	 * 
	 * @return the font callback.
	 */
	default FontCallback<DataLabelsContext> getFontCallback() {
		return null;
	}

	/**
	 * Returns the padding callback.
	 * 
	 * @return the padding callback.
	 */
	default PaddingCallback<DataLabelsContext> getPaddingCallback() {
		return null;
	}

}