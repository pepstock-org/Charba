/**
    Copyright 2017 Andrea "Stock" Stocchero

    Licensed under the Apache License, Version 2.0 (the "License";
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

	    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/
package org.pepstock.charba.client.datalabels;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.datalabels.enums.Align;
import org.pepstock.charba.client.datalabels.enums.Anchor;
import org.pepstock.charba.client.datalabels.enums.TextAlign;
import org.pepstock.charba.client.enums.Display;

/**
 * FIXME
 * This is the base for {@link DataLabelsPlugin#ID} plugin options where to set all the configuration needed to the a label.<br>
 * The options could be set by simply the value or by setting a callback.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
interface IsDefaultsDataLabelsOptions {
	
	/**
	 * Returns the font element.
	 * 
	 * @return the font element.
	 */
	IsDefaultsFont getFont();

	/**
	 * Returns the padding element.
	 * 
	 * @return the padding element.
	 */
	IsDefaultsPadding getPadding();
	
	/**
	 * Returns the listeners element.
	 * 
	 * @return the listeners element.
	 */
	IsDefaultsListeners getListeners();

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
		return Defaults.get().getGlobal().getFont().getColorAsString();
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
	default double getOffset() {
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

}
