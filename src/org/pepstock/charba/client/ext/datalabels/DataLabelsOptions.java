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
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.ext.datalabels.DataLabelsOptionsFactory.DataLabelsDefaultsOptionsFactory;

/**
 * FIXME
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class DataLabelsOptions extends NativeObjectContainer {

	// defaults global options instance
	private DataLabelsDefaultsOptions defaultsOptions;
	// defaults global options factory
	private final DataLabelsDefaultsOptionsFactory defaultsFactory = new DataLabelsDefaultsOptionsFactory();

	private final Padding padding;

	private final Font font;

	enum Property implements Key
	{
		align,
		anchor,
		backgroundColor,
		borderColor,
		borderRadius,
		borderWidth,
		clamp,
		clip,
		color,
		display,
		font,
		formatter,
		listeners,
		offset,
		opacity,
		padding,
		rotation,
		textAlign,
		textStrokeColor,
		textStrokeWidth,
		textShadowBlur,
		textShadowColor
	}

	public DataLabelsOptions() {
		super();
		// checks if the default global options has been added for the plugin
		if (Defaults.get().getGlobal().getPlugins().hasOptions(DataLabelsPlugin.ID)) {
			// reads the default default global options
			defaultsOptions = Defaults.get().getGlobal().getPlugins().getOptions(DataLabelsPlugin.ID, defaultsFactory);
		} else {
			// if here, no default global option
			// then the plugin will use the static defaults
			defaultsOptions = new DataLabelsDefaultsOptions();
		}
		padding = new Padding(defaultsOptions.getPadding());
		font = new Font(defaultsOptions.getFont());
		setValue(Property.padding, padding);
		setValue(Property.font, font);
	}

	DataLabelsOptions(NativeObject nativeObject, DataLabelsDefaultsOptions defaultsOptions) {
		super(nativeObject);
		// stores default options
		this.defaultsOptions = defaultsOptions;
		padding = new Padding(getValue(Property.padding), defaultsOptions.getPadding());
		font = new Font(getValue(Property.font), defaultsOptions.getFont());
	}

	/**
	 * @return the padding
	 */
	public final Padding getPadding() {
		return padding;
	}

	/**
	 * @return the font
	 */
	public final Font getFont() {
		return font;
	}

	/**
	 * Sets the position of the label relative to the anchor point position and orientation.
	 * 
	 * @param align the position of the label relative to the anchor point position and orientation.
	 */
	public final void setAlign(Align align) {
		setValue(Property.align, align);
	}

	/**
	 * Returns the position of the label relative to the anchor point position and orientation.
	 * 
	 * @return the position of the label relative to the anchor point position and orientation.
	 */
	public final Align getAlign() {
		return getValue(Property.align, Align.class, defaultsOptions.getAlign());
	}

	/**
	 * Sets the anchor point, which is defined by an orientation vector and a position on the data element.
	 * 
	 * @param anchor the anchor point, which is defined by an orientation vector and a position on the data element.
	 */
	public final void setAnchor(Anchor anchor) {
		setValue(Property.anchor, anchor);
	}

	/**
	 * Returns the anchor point, which is defined by an orientation vector and a position on the data element.
	 * 
	 * @return the anchor point, which is defined by an orientation vector and a position on the data element.
	 */
	public final Anchor getAnchor() {
		return getValue(Property.anchor, Anchor.class, defaultsOptions.getAnchor());
	}

	/**
	 * Sets the background color.
	 * 
	 * @param color the background color
	 */
	public final void setBackgroundColor(IsColor color) {
		setBackgroundColor(color.toRGBA());
	}

	/**
	 * Sets the background color.
	 * 
	 * @param color the background color
	 */
	public final void setBackgroundColor(String color) {
		setValue(Property.backgroundColor, color);
	}

	/**
	 * Returns the background color as string.
	 * 
	 * @return the background color as string.
	 */
	public final String getBackgroundColorAsString() {
		return getValue(Property.backgroundColor, defaultsOptions.getBackgroundColorAsString());
	}

	/**
	 * Returns the background color.
	 * 
	 * @return the background color.
	 */
	public final IsColor getBackgroundColor() {
		String color = getBackgroundColorAsString();
		return color != null ? ColorBuilder.parse(color) : null;
	}

	/**
	 * Sets the border color.
	 * 
	 * @param color the border color
	 */
	public final void setBorderColor(IsColor color) {
		setBorderColor(color.toRGBA());
	}

	/**
	 * Sets the border color.
	 * 
	 * @param color the border color
	 */
	public final void setBorderColor(String color) {
		setValue(Property.borderColor, color);
	}

	/**
	 * Returns the border color as string.
	 * 
	 * @return the border color as string.
	 */
	public final String getBorderColorAsString() {
		return getValue(Property.borderColor, defaultsOptions.getBorderColorAsString());
	}

	/**
	 * Returns the border color.
	 * 
	 * @return the border color.
	 */
	public final IsColor getBorderColor() {
		String color = getBorderColorAsString();
		return color != null ? ColorBuilder.parse(color) : null;
	}

	/**
	 * Sets the border radius.
	 * 
	 * @param radius the border radius.
	 */
	public final void setBorderRadius(double radius) {
		setValue(Property.borderRadius, radius);
	}

	/**
	 * Returns the border radius.
	 * 
	 * @return the border radius.
	 */
	public final double getBorderRadius() {
		return getValue(Property.borderRadius, defaultsOptions.getBorderRadius());
	}

	/**
	 * Sets the border width.
	 * 
	 * @param width the border width.
	 */
	public final void setBorderWidth(int width) {
		setValue(Property.borderWidth, width);
	}

	/**
	 * Returns the border width.
	 * 
	 * @return the border width.
	 */
	public final int getBorderWidth() {
		return getValue(Property.borderWidth, defaultsOptions.getBorderWidth());
	}

	/**
	 * Sets <code>true</code> to enforce the anchor position to be calculated based on the visible geometry of the associated
	 * element (i.e. part inside the chart area).
	 * 
	 * @param clamp <code>true</code> to enforce the anchor position to be calculated based on the visible geometry of the
	 *            associated element (i.e. part inside the chart area).
	 */
	public final void setClamp(boolean clamp) {
		setValue(Property.clamp, clamp);
	}

	/**
	 * Returns <code>true</code> to enforce the anchor position to be calculated based on the visible geometry of the associated
	 * element (i.e. part inside the chart area).
	 * 
	 * @return <code>true</code> to enforce the anchor position to be calculated based on the visible geometry of the associated
	 *         element (i.e. part inside the chart area).
	 */
	public final boolean isClamp() {
		return getValue(Property.clamp, defaultsOptions.isClamp());
	}

	/**
	 * When the clip option is <code>true</code>, the part of the label which is outside the chart area will be masked.
	 * 
	 * @param clip when the clip option is <code>true</code>, the part of the label which is outside the chart area will be
	 *            masked.
	 */
	public final void setClip(boolean clip) {
		setValue(Property.clip, clip);
	}

	/**
	 * When the clip option is <code>true</code>, the part of the label which is outside the chart area will be masked.
	 * 
	 * @return when the clip option is <code>true</code>, the part of the label which is outside the chart area will be masked.
	 */
	public final boolean isClip() {
		return getValue(Property.clip, defaultsOptions.isClip());
	}

	/**
	 * Sets the color.
	 * 
	 * @param color the color
	 */
	public final void setColor(IsColor color) {
		setColor(color.toRGBA());
	}

	/**
	 * Sets the color.
	 * 
	 * @param color the color
	 */
	public final void setColor(String color) {
		setValue(Property.color, color);
	}

	/**
	 * Returns the color as string.
	 * 
	 * @return the color as string.
	 */
	public final String getColorAsString() {
		return getValue(Property.color, defaultsOptions.getColorAsString());
	}

	/**
	 * Returns the color.
	 * 
	 * @return the color.
	 */
	public final IsColor getColor() {
		return ColorBuilder.parse(getColorAsString());
	}

	/**
	 * Sets the visibility of labels.
	 * 
	 * @param display the visibility of labels.
	 */
	public final void setDisplay(boolean display) {
		setValue(Property.display, display);
	}

	/**
	 * Sets the visibility of labels.
	 * 
	 * @param display the visibility of labels.
	 */
	public final void setDisplay(Display display) {
		if (Display.auto.equals(display)) {
			setValue(Property.display, display);
		} else {
			setValue(Property.display, Display.isTrue.equals(display) ? true : false);
		}
	}

	/**
	 * Returns the visibility of labels.
	 * 
	 * @return the visibility of labels.
	 */
	public final Display getDisplay() {
		ObjectType type = type(Property.display);
		if (ObjectType.Boolean.equals(type)) {
			boolean value = getValue(Property.display, true);
			return value ? Display.isTrue : Display.isFalse;
		} else if (ObjectType.String.equals(type)) {
			return getValue(Property.display, Display.class, defaultsOptions.getDisplay());
		}
		return defaultsOptions.getDisplay();
	}

	/**
	 * Sets the distance (in pixels) to pull the label away from the anchor point. This option is not applicable when align is
	 * 'center'. Also note that if align is 'start', the label is moved in the opposite direction.
	 * 
	 * @param offset the distance (in pixels) to pull the label away from the anchor point. This option is not applicable when
	 *            align is 'center'. Also note that if align is 'start', the label is moved in the opposite direction.
	 */
	public final void setOffset(double offset) {
		setValue(Property.offset, offset);
	}

	/**
	 * Returns the distance (in pixels) to pull the label away from the anchor point. This option is not applicable when align
	 * is 'center'. Also note that if align is 'start', the label is moved in the opposite direction.
	 * 
	 * @return the distance (in pixels) to pull the label away from the anchor point. This option is not applicable when align
	 *         is 'center'. Also note that if align is 'start', the label is moved in the opposite direction.
	 */
	public final double getOffset() {
		return getValue(Property.offset, defaultsOptions.getOffset());
	}

	/**
	 * Sets the opacity.
	 * 
	 * @param opacity the opacity.
	 */
	public final void setOpacity(double opacity) {
		setValue(Property.opacity, opacity);
	}

	/**
	 * Returns the opacity.
	 * 
	 * @return the opacity.
	 */
	public final double getOpacity() {
		return getValue(Property.opacity, defaultsOptions.getOpacity());
	}

	/**
	 * Sets the clockwise rotation angle (in degrees) of the label, the rotation center point being the label center.
	 * 
	 * @param rotation the clockwise rotation angle (in degrees) of the label, the rotation center point being the label center.
	 */
	public final void setRotation(double rotation) {
		setValue(Property.rotation, rotation);
	}

	/**
	 * Returns the clockwise rotation angle (in degrees) of the label, the rotation center point being the label center.
	 * 
	 * @return the clockwise rotation angle (in degrees) of the label, the rotation center point being the label center.
	 */
	public final double getRotation() {
		return getValue(Property.rotation, defaultsOptions.getRotation());
	}

	/**
	 * Sets the text alignment being used when drawing the label text.
	 * 
	 * @param textAlign the text alignment being used when drawing the label text.
	 */
	public final void setTextAlign(TextAlign textAlign) {
		setValue(Property.textAlign, textAlign);
	}

	/**
	 * Returns the text alignment being used when drawing the label text.
	 * 
	 * @return the text alignment being used when drawing the label text.
	 */
	public final TextAlign getTextAlign() {
		return getValue(Property.textAlign, TextAlign.class, defaultsOptions.getTextAlign());
	}

	/**
	 * Sets the text stroke color.
	 * 
	 * @param color the text stroke color
	 */
	public final void setTextStrokeColor(IsColor color) {
		setColor(color.toRGBA());
	}

	/**
	 * Sets the text stroke color.
	 * 
	 * @param color the text stroke color.
	 */
	public final void setTextStrokeColor(String color) {
		setValue(Property.textStrokeColor, color);
	}

	/**
	 * Returns the text stroke color as string.
	 * 
	 * @return the text stroke color as string.
	 */
	public final String getTextStrokeColorAsString() {
		return getValue(Property.textStrokeColor, defaultsOptions.getTextStrokeColorAsString());
	}

	/**
	 * Returns the text stroke color.
	 * 
	 * @return the text stroke color.
	 */
	public final IsColor getTextStrokeColor() {
		return ColorBuilder.parse(getTextStrokeColorAsString());
	}

	/**
	 * Sets the text stroke width.
	 * 
	 * @param textStrokeWidth the text stroke width.
	 */
	public final void setTextStrokeWidth(int textStrokeWidth) {
		setValue(Property.textStrokeWidth, textStrokeWidth);
	}

	/**
	 * Returns the text stroke width.
	 * 
	 * @return the text stroke width.
	 */
	public final int getTextStrokeWidth() {
		return getValue(Property.textStrokeWidth, defaultsOptions.getTextStrokeWidth());
	}

	/**
	 * Sets the text shadow blur.
	 * 
	 * @param textShadowBlur the text shadow blur.
	 */
	public final void setTextShadowBlur(double textShadowBlur) {
		setValue(Property.textShadowBlur, textShadowBlur);
	}

	/**
	 * Returns the text shadow blur.
	 * 
	 * @return the text shadow blur.
	 */
	public final double getTextShadowBlur() {
		return getValue(Property.textShadowBlur, defaultsOptions.getTextShadowBlur());
	}

	/**
	 * Sets the text shadow color color.
	 * 
	 * @param color the text shadow color color
	 */
	public final void setTextShadowColor(IsColor color) {
		setColor(color.toRGBA());
	}

	/**
	 * Sets the text shadow color color.
	 * 
	 * @param color the text shadow color color.
	 */
	public final void setTextShadowColor(String color) {
		setValue(Property.textShadowColor, color);
	}

	/**
	 * Returns the text shadow color as string.
	 * 
	 * @return the text shadow color as string.
	 */
	public final String getTextShadowColorAsString() {
		return getValue(Property.textShadowColor, defaultsOptions.getTextShadowColorAsString());
	}

	/**
	 * Returns the text shadow color.
	 * 
	 * @return the text shadow color.
	 */
	public final IsColor getTextShadowColor() {
		return ColorBuilder.parse(getTextShadowColorAsString());
	}

}
