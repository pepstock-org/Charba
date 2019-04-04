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
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.datalabels.enums.Align;
import org.pepstock.charba.client.datalabels.enums.Anchor;
import org.pepstock.charba.client.datalabels.enums.TextAlign;
import org.pepstock.charba.client.enums.Display;

/**
 * {@link DataLabelsPlugin#ID} plugin default options.<br>
 * It contains all default values.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class DataLabelsDefaultsOptions extends NativeObjectContainer {

	// default padding options
	private final DataLabelsDefaultsPadding padding;
	// default font options
	private final DataLabelsDefaultsFont font;

	/**
	 * Creates an empty options without any default global options. It will use the constants as default of plugin properties.
	 */
	DataLabelsDefaultsOptions() {
		super();
		// creates default padding options
		padding = new DataLabelsDefaultsPadding();
		// creates default font options
		font = new DataLabelsDefaultsFont();
		// sets the native object inside this object
		setValue(DataLabelsOptions.Property.PADDING, padding);
		setValue(DataLabelsOptions.Property.FONT, font);
	}

	/**
	 * Creates the object wrapping the default global options if there are. It will use the constants as default of plugin
	 * properties.
	 * 
	 * @param nativeObject native object which maps default global options.
	 */
	DataLabelsDefaultsOptions(NativeObject nativeObject) {
		super(nativeObject);
		// reads default padding options from main object
		padding = new DataLabelsDefaultsPadding(getValue(DataLabelsOptions.Property.PADDING));
		// reads default font options from main object
		font = new DataLabelsDefaultsFont(getValue(DataLabelsOptions.Property.FONT));
	}

	/**
	 * Returns the font element.
	 * 
	 * @return the font element.
	 */
	DataLabelsDefaultsFont getFont() {
		return font;
	}

	/**
	 * Returns the padding element.
	 * 
	 * @return the padding element.
	 */
	DataLabelsDefaultsPadding getPadding() {
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
		return getValue(DataLabelsOptions.Property.BACKGROUND_COLOR, DataLabelsOptions.DEFAULT_BACKGROUNDCOLOR);
	}

	/**
	 * Returns the border color as string.
	 * 
	 * @return the border color as string. If <code>null</code>, it uses the border color of dataset.
	 */
	String getBorderColorAsString() {
		return getValue(DataLabelsOptions.Property.BORDER_COLOR, DataLabelsOptions.DEFAULT_BORDERCOLOR);
	}

	/**
	 * Returns the border radius.
	 * 
	 * @return the border radius.
	 */
	double getBorderRadius() {
		return getValue(DataLabelsOptions.Property.BORDER_RADIUS, DataLabelsOptions.DEFAULT_BORDERRADIUS);
	}

	/**
	 * Returns the border width.
	 * 
	 * @return the border width.
	 */
	int getBorderWidth() {
		return getValue(DataLabelsOptions.Property.BORDER_WIDTH, DataLabelsOptions.DEFAULT_BORDERWIDTH);
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
		return getValue(DataLabelsOptions.Property.COLOR, Defaults.get().getGlobal().getDefaultFontColorAsString());
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
		return getValue(DataLabelsOptions.Property.TEXT_ALIGN, TextAlign.class, DataLabelsOptions.DEFAULT_TEXTALIGN);
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
		return getValue(DataLabelsOptions.Property.TEXT_STROKE_WIDTH, DataLabelsOptions.DEFAULT_TEXTSTROKEWIDTH);
	}

	/**
	 * Returns the text shadow blur.
	 * 
	 * @return the text shadow blur.
	 */
	double getTextShadowBlur() {
		return getValue(DataLabelsOptions.Property.TEXT_SHADOW_BLUR, DataLabelsOptions.DEFAULT_TEXTSHADOWBLUR);
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
