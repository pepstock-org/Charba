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
package org.pepstock.charba.client.datalabels;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.datalabels.enums.Align;
import org.pepstock.charba.client.datalabels.enums.Anchor;
import org.pepstock.charba.client.datalabels.enums.TextAlign;
import org.pepstock.charba.client.enums.Display;
import org.pepstock.charba.client.plugins.AbstractPluginOptions;

/**
 * {@link DataLabelsPlugin#ID} plugin default options.<br>
 * It contains all default values.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class DefaultsOptions extends AbstractPluginOptions {

	// defaults options instance
	static final DefaultsOptions DEFAULTS_INSTANCE = new DefaultsOptions();
	// default padding options
	private final DefaultsPadding padding;
	// default font options
	private final DefaultsFont font;

	/**
	 * Creates an empty options without any default global options. It will use the constants as default of plugin properties.
	 */
	private DefaultsOptions() {
		this(null);
	}

	/**
	 * Creates the object wrapping the default global options if there are. It will use the constants as default of plugin
	 * properties.
	 * 
	 * @param nativeObject native object which maps default global options.
	 */
	DefaultsOptions(NativeObject nativeObject) {
		super(DataLabelsPlugin.ID, nativeObject);
		// reads default padding options from main object
		padding = new DefaultsPadding(getValue(DataLabelsOptions.Property.PADDING));
		// reads default font options from main object
		font = new DefaultsFont(getValue(DataLabelsOptions.Property.FONT));
		// checks if padding is stored
		if (!has(DataLabelsOptions.Property.PADDING)) {
			// sets the native object inside this object
			setValue(DataLabelsOptions.Property.PADDING, padding);
		}
		// checks if padding is stored
		if (!has(DataLabelsOptions.Property.FONT)) {
			// sets the native object inside this object
			setValue(DataLabelsOptions.Property.FONT, font);
		}
	}

	/**
	 * Returns the font element.
	 * 
	 * @return the font element.
	 */
	DefaultsFont getFont() {
		return font;
	}

	/**
	 * Returns the padding element.
	 * 
	 * @return the padding element.
	 */
	DefaultsPadding getPadding() {
		return padding;
	}

	/**
	 * Returns the position of the label relative to the anchor point position and orientation.
	 * 
	 * @return the position of the label relative to the anchor point position and orientation.
	 */
	Align getAlign() {
		return getValue(DataLabelsOptions.Property.ALIGN, Align.class, DataLabelsOptions.DEFAULT_ALIGN);
	}

	/**
	 * Returns the anchor point, which is defined by an orientation vector and a position on the data element
	 * 
	 * @return the anchor point, which is defined by an orientation vector and a position on the data element.
	 */
	Anchor getAnchor() {
		return getValue(DataLabelsOptions.Property.ANCHOR, Anchor.class, DataLabelsOptions.DEFAULT_ANCHOR);
	}

	/**
	 * Returns the background color as string.
	 * 
	 * @return the background color as string. If <code>null</code>, it uses the background color of dataset.
	 */
	String getBackgroundColorAsString() {
		return getValue(DataLabelsOptions.Property.BACKGROUND_COLOR, DataLabelsOptions.DEFAULT_BACKGROUND_COLOR);
	}

	/**
	 * Returns the border color as string.
	 * 
	 * @return the border color as string. If <code>null</code>, it uses the border color of dataset.
	 */
	String getBorderColorAsString() {
		return getValue(DataLabelsOptions.Property.BORDER_COLOR, DataLabelsOptions.DEFAULT_BORDER_COLOR);
	}

	/**
	 * Returns the border radius.
	 * 
	 * @return the border radius.
	 */
	double getBorderRadius() {
		return getValue(DataLabelsOptions.Property.BORDER_RADIUS, DataLabelsOptions.DEFAULT_BORDER_RADIUS);
	}

	/**
	 * Returns the border width.
	 * 
	 * @return the border width.
	 */
	int getBorderWidth() {
		return getValue(DataLabelsOptions.Property.BORDER_WIDTH, DataLabelsOptions.DEFAULT_BORDER_WIDTH);
	}

	/**
	 * Returns <code>true</code> to enforce the anchor position to be calculated based on the visible geometry of the associated
	 * element (i.e. part inside the chart area).
	 * 
	 * @return <code>true</code> to enforce the anchor position to be calculated based on the visible geometry of the associated
	 *         element (i.e. part inside the chart area).
	 */
	boolean isClamp() {
		return getValue(DataLabelsOptions.Property.CLAMP, DataLabelsOptions.DEFAULT_CLAMP);
	}

	/**
	 * When the clip option is <code>true</code>, the part of the label which is outside the chart area will be masked.
	 * 
	 * @return when the clip option is <code>true</code>, the part of the label which is outside the chart area will be masked.
	 */
	boolean isClip() {
		return getValue(DataLabelsOptions.Property.CLIP, DataLabelsOptions.DEFAULT_CLIP);
	}

	/**
	 * Returns the color as string.
	 * 
	 * @return the color as string.
	 */
	String getColorAsString() {
		return getValue(DataLabelsOptions.Property.COLOR, Defaults.get().getGlobal().getFontColorAsString());
	}

	/**
	 * Returns the visibility of labels.
	 * 
	 * @return the visibility of labels.
	 */
	Display getDisplay() {
		// gets object type
		ObjectType type = type(DataLabelsOptions.Property.DISPLAY);
		// if boolean
		if (ObjectType.BOOLEAN.equals(type)) {
			// gets value and compare with enum value
			boolean value = getValue(DataLabelsOptions.Property.DISPLAY, true);
			return value ? Display.TRUE : Display.FALSE;
		} else if (ObjectType.STRING.equals(type)) {
			// if string
			return getValue(DataLabelsOptions.Property.DISPLAY, Display.class, DataLabelsOptions.DEFAULT_DISPLAY);
		}
		return DataLabelsOptions.DEFAULT_DISPLAY;
	}

	/**
	 * Returns the distance (in pixels) to pull the label away from the anchor point. This option is not applicable when align
	 * is 'center'. Also note that if align is 'start', the label is moved in the opposite direction.
	 * 
	 * @return the distance (in pixels) to pull the label away from the anchor point. This option is not applicable when align
	 *         is 'center'. Also note that if align is 'start', the label is moved in the opposite direction.
	 */
	double getOffset() {
		return getValue(DataLabelsOptions.Property.OFFSET, DataLabelsOptions.DEFAULT_OFFSET);
	}

	/**
	 * Returns the opacity.
	 * 
	 * @return the opacity.
	 */
	double getOpacity() {
		return getValue(DataLabelsOptions.Property.OPACITY, DataLabelsOptions.DEFAULT_OPACITY);
	}

	/**
	 * Returns the clockwise rotation angle (in degrees) of the label, the rotation center point being the label center.
	 * 
	 * @return the clockwise rotation angle (in degrees) of the label, the rotation center point being the label center.
	 */
	double getRotation() {
		return getValue(DataLabelsOptions.Property.ROTATION, DataLabelsOptions.DEFAULT_ROTATION);
	}

	/**
	 * Returns the text alignment being used when drawing the label text.
	 * 
	 * @return the text alignment being used when drawing the label text.
	 */
	TextAlign getTextAlign() {
		return getValue(DataLabelsOptions.Property.TEXT_ALIGN, TextAlign.class, DataLabelsOptions.DEFAULT_TEXT_ALIGN);
	}

	/**
	 * Returns the text stroke color as string.
	 * 
	 * @return the text stroke color as string.
	 */
	String getTextStrokeColorAsString() {
		return getValue(DataLabelsOptions.Property.TEXT_STROKE_COLOR, getColorAsString());
	}

	/**
	 * Returns the text stroke width.
	 * 
	 * @return the text stroke width.
	 */
	int getTextStrokeWidth() {
		return getValue(DataLabelsOptions.Property.TEXT_STROKE_WIDTH, DataLabelsOptions.DEFAULT_TEXT_STROKE_WIDTH);
	}

	/**
	 * Returns the text shadow blur.
	 * 
	 * @return the text shadow blur.
	 */
	double getTextShadowBlur() {
		return getValue(DataLabelsOptions.Property.TEXT_SHADOW_BLUR, DataLabelsOptions.DEFAULT_TEXT_SHADOW_BLUR);
	}

	/**
	 * Returns the text shadow color as string.
	 * 
	 * @return the text shadow color as string.
	 */
	String getTextShadowColorAsString() {
		return getValue(DataLabelsOptions.Property.TEXT_SHADOW_COLOR, getColorAsString());
	}

}
