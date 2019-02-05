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
package org.pepstock.charba.client.ext.datalabels;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.commons.ObjectType;

/**
 * It wraps default global options if there are and provides all default values for LABELS plugin.
 * 
 * @author Andrea "Stock" Stocchero
 */
final class DataLabelsDefaultsOptions extends NativeObjectContainer {

	private static final Align DEFAULT_ALIGN = Align.center;

	private static final Anchor DEFAULT_ANCHOR = Anchor.center;

	private static final String DEFAULT_BACKGROUNDCOLOR = null;

	private static final String DEFAULT_BORDERCOLOR = null;

	private static final double DEFAULT_BORDERRADIUS = 0D;

	private static final int DEFAULT_BORDERWIDTH = 0;

	private static final boolean DEFAULT_CLAMP = false;

	private static final boolean DEFAULT_CLIP = false;

	private static final Display DEFAULT_DISPLAY = Display.isTrue;

	private static final double DEFAULT_OFFSET = 4D;

	private static final double DEFAULT_OPACITY = 1D;

	private static final double DEFAULT_ROTATION = 0D;

	private static final TextAlign DEFAULT_TEXTALIGN = TextAlign.start;

	private static final int DEFAULT_TEXTSTROKEWIDTH = 0;

	private static final double DEFAULT_TEXTSHADOWBLUR = 0D;

	private final DataLabelsDefaultsPadding padding;

	private final DataLabelsDefaultsFont font;

	/**
	 * Creates an empty options without any default global options. it will use the constants as default of plugin properties.
	 */
	DataLabelsDefaultsOptions() {
		super();
		padding = new DataLabelsDefaultsPadding();
		font = new DataLabelsDefaultsFont();
		setValue(DataLabelsOptions.Property.padding, padding);
		setValue(DataLabelsOptions.Property.font, font);
	}

	/**
	 * Creates the object wrapping the default global options if there are.
	 * 
	 * @param nativeObject native object which maps default global options.
	 */
	DataLabelsDefaultsOptions(NativeObject nativeObject) {
		super(nativeObject);
		padding = new DataLabelsDefaultsPadding(getValue(DataLabelsOptions.Property.padding));
		font = new DataLabelsDefaultsFont(getValue(DataLabelsOptions.Property.font));
	}

	/**
	 * @return the font
	 */
	DataLabelsDefaultsFont getFont() {
		return font;
	}

	/**
	 * @return the padding
	 */
	DataLabelsDefaultsPadding getPadding() {
		return padding;
	}

	/**
	 * Returns the position of the label relative to the anchor point position and orientation.
	 * 
	 * @return the position of the label relative to the anchor point position and orientation. Default is {@link Align#center}.
	 */
	Align getAlign() {
		return getValue(DataLabelsOptions.Property.align, Align.class, DEFAULT_ALIGN);
	}

	/**
	 * Returns the anchor point, which is defined by an orientation vector and a position on the data element
	 * 
	 * @return the anchor point, which is defined by an orientation vector and a position on the data element. Default is
	 *         {@link Anchor#center}.
	 */
	Anchor getAnchor() {
		return getValue(DataLabelsOptions.Property.anchor, Anchor.class, DEFAULT_ANCHOR);
	}

	/**
	 * Returns the background color as string.
	 * 
	 * @return the background color as string. Default is <code>null</code> and uses the background color of dataset.
	 */
	String getBackgroundColorAsString() {
		return getValue(DataLabelsOptions.Property.backgroundColor, DEFAULT_BACKGROUNDCOLOR);
	}

	/**
	 * Returns the border color as string.
	 * 
	 * @return the border color as string. Default is <code>null</code> and uses the border color of dataset.
	 */
	String getBorderColorAsString() {
		return getValue(DataLabelsOptions.Property.borderColor, DEFAULT_BORDERCOLOR);
	}

	/**
	 * Returns the border radius.
	 * 
	 * @return the border radius. Default is 0.
	 */
	double getBorderRadius() {
		return getValue(DataLabelsOptions.Property.borderRadius, DEFAULT_BORDERRADIUS);
	}

	/**
	 * Returns the border width.
	 * 
	 * @return the border width. Default is 0.
	 */
	int getBorderWidth() {
		return getValue(DataLabelsOptions.Property.borderWidth, DEFAULT_BORDERWIDTH);
	}

	/**
	 * Returns <code>true</code> to enforce the anchor position to be calculated based on the visible geometry of the associated
	 * element (i.e. part inside the chart area).
	 * 
	 * @return <code>true</code> to enforce the anchor position to be calculated based on the visible geometry of the associated
	 *         element (i.e. part inside the chart area). Default is <code>false</code>.
	 */
	boolean isClamp() {
		return getValue(DataLabelsOptions.Property.clamp, DEFAULT_CLAMP);
	}

	/**
	 * When the clip option is <code>true</code>, the part of the label which is outside the chart area will be masked.
	 * 
	 * @return when the clip option is <code>true</code>, the part of the label which is outside the chart area will be masked.
	 *         Default is <code>false</code>.
	 */
	boolean isClip() {
		return getValue(DataLabelsOptions.Property.clip, DEFAULT_CLIP);
	}

	/**
	 * Returns the color as string.
	 * 
	 * @return the color as string.
	 */
	String getColorAsString() {
		return getValue(DataLabelsOptions.Property.color, Defaults.get().getGlobal().getDefaultFontColorAsString());
	}

	/**
	 * Returns the visibility of labels.
	 * 
	 * @return the visibility of labels. Default is {@link Display#isTrue}.
	 */
	Display getDisplay() {
		ObjectType type = type(DataLabelsOptions.Property.display);
		if (ObjectType.Boolean.equals(type)) {
			boolean value = getValue(DataLabelsOptions.Property.display, true);
			return value ? Display.isTrue : Display.isFalse;
		} else if (ObjectType.String.equals(type)) {
			return getValue(DataLabelsOptions.Property.display, Display.class, DEFAULT_DISPLAY);
		}
		return DEFAULT_DISPLAY;
	}

	/**
	 * Returns the distance (in pixels) to pull the label away from the anchor point. This option is not applicable when align
	 * is 'center'. Also note that if align is 'start', the label is moved in the opposite direction.
	 * 
	 * @return the distance (in pixels) to pull the label away from the anchor point. This option is not applicable when align
	 *         is 'center'. Also note that if align is 'start', the label is moved in the opposite direction. Default is 4.
	 */
	double getOffset() {
		return getValue(DataLabelsOptions.Property.offset, DEFAULT_OFFSET);
	}

	/**
	 * Returns the opacity.
	 * 
	 * @return the opacity. Default is 1.
	 */
	double getOpacity() {
		return getValue(DataLabelsOptions.Property.opacity, DEFAULT_OPACITY);
	}

	/**
	 * Returns the clockwise rotation angle (in degrees) of the label, the rotation center point being the label center.
	 * 
	 * @return the clockwise rotation angle (in degrees) of the label, the rotation center point being the label center. Default
	 *         is 0.
	 */
	double getRotation() {
		return getValue(DataLabelsOptions.Property.rotation, DEFAULT_ROTATION);
	}

	/**
	 * Returns the text alignment being used when drawing the label text.
	 * 
	 * @return the text alignment being used when drawing the label text. Default is {@link TextAlign#start}.
	 */
	TextAlign getTextAlign() {
		return getValue(DataLabelsOptions.Property.textAlign, TextAlign.class, DEFAULT_TEXTALIGN);
	}

	/**
	 * Returns the text stroke color as string.
	 * 
	 * @return the text stroke color as string. Default is {@link DataLabelsDefaultsOptions#getColorAsString()}.
	 */
	String getTextStrokeColorAsString() {
		return getValue(DataLabelsOptions.Property.textStrokeColor, getColorAsString());
	}

	/**
	 * Returns the text stroke width.
	 * 
	 * @return the text stroke width. Default is 0.
	 */
	int getTextStrokeWidth() {
		return getValue(DataLabelsOptions.Property.textStrokeWidth, DEFAULT_TEXTSTROKEWIDTH);
	}

	/**
	 * Returns the text shadow blur.
	 * 
	 * @return the text shadow blur. Default is 0.
	 */
	double getTextShadowBlur() {
		return getValue(DataLabelsOptions.Property.textShadowBlur, DEFAULT_TEXTSHADOWBLUR);
	}

	/**
	 * Returns the text shadow color as string.
	 * 
	 * @return the text shadow color as string. Default is {@link DataLabelsDefaultsOptions#getColorAsString()}.
	 */
	String getTextShadowColorAsString() {
		return getValue(DataLabelsOptions.Property.textShadowColor, getColorAsString());
	}

}
