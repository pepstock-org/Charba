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
* @return builder instance */
package org.pepstock.charba.client.datalabels;

import org.pepstock.charba.client.callbacks.BackgroundColorCallback;
import org.pepstock.charba.client.callbacks.BorderColorCallback;
import org.pepstock.charba.client.callbacks.BorderWidthCallback;
import org.pepstock.charba.client.callbacks.RadiusCallback;
import org.pepstock.charba.client.callbacks.RotationCallback;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.datalabels.callbacks.AlignCallback;
import org.pepstock.charba.client.datalabels.callbacks.AnchorCallback;
import org.pepstock.charba.client.datalabels.callbacks.ClampCallback;
import org.pepstock.charba.client.datalabels.callbacks.ClipCallback;
import org.pepstock.charba.client.datalabels.callbacks.ColorCallback;
import org.pepstock.charba.client.datalabels.callbacks.DisplayCallback;
import org.pepstock.charba.client.datalabels.callbacks.FontCallback;
import org.pepstock.charba.client.datalabels.callbacks.FormatterCallback;
import org.pepstock.charba.client.datalabels.callbacks.OffsetCallback;
import org.pepstock.charba.client.datalabels.callbacks.OpacityCallback;
import org.pepstock.charba.client.datalabels.callbacks.PaddingCallback;
import org.pepstock.charba.client.datalabels.callbacks.TextAlignCallback;
import org.pepstock.charba.client.datalabels.callbacks.TextShadowBlurCallback;
import org.pepstock.charba.client.datalabels.callbacks.TextShadowColorCallback;
import org.pepstock.charba.client.datalabels.callbacks.TextStrokeColorCallback;
import org.pepstock.charba.client.datalabels.callbacks.TextStrokeWidthCallback;
import org.pepstock.charba.client.datalabels.enums.Align;
import org.pepstock.charba.client.datalabels.enums.Anchor;
import org.pepstock.charba.client.datalabels.enums.TextAlign;
import org.pepstock.charba.client.datalabels.enums.Weight;
import org.pepstock.charba.client.datalabels.events.AbstractEventHandler;
import org.pepstock.charba.client.datalabels.events.ClickEventHandler;
import org.pepstock.charba.client.datalabels.events.EnterEventHandler;
import org.pepstock.charba.client.datalabels.events.LeaveEventHandler;
import org.pepstock.charba.client.enums.Display;
import org.pepstock.charba.client.enums.FontStyle;

/**
 * Comfortable object to create {@link DataLabelsPlugin#ID} plugin options by a builder.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class DataLabelsOptionsBuilder {
	// creates the options with deferred registration
	// because it will be register only when the build method
	// will be invoked
	private final DataLabelsOptions options = new DataLabelsOptions(true);

	/**
	 * To avoid any instantiation
	 * 
	 * @return builder instance
	 */
	private DataLabelsOptionsBuilder() {
		// do nothing;
	}

	/**
	 * Returns new builder instance.
	 * 
	 * @return new builder instance
	 */
	public static DataLabelsOptionsBuilder create() {
		return new DataLabelsOptionsBuilder();
	}

	/**
	 * Returns a configured labels options.
	 * 
	 * @return a configured labels options.
	 */
	public DataLabelsOptions build() {
		// registers the options to the factory
		options.register();
		// returns options
		return options;
	}

	/**
	 * Adds a event handler instance as listener for all events.
	 * 
	 * @param handler event handler instance as listener for all events.
	 * @return builder instance
	 */
	public DataLabelsOptionsBuilder setListenersHandler(AbstractEventHandler handler) {
		options.setListenersHandler(handler);
		return this;
	}

	/**
	 * Sets the position of the label relative to the anchor point position and orientation.
	 * 
	 * @param align the position of the label relative to the anchor point position and orientation.
	 * @return builder instance
	 */
	public DataLabelsOptionsBuilder setAlign(Align align) {
		options.setAlign(align);
		return this;
	}

	/**
	 * Sets the anchor point, which is defined by an orientation vector and a position on the data element.
	 * 
	 * @param anchor the anchor point, which is defined by an orientation vector and a position on the data element.
	 * @return builder instance
	 */
	public DataLabelsOptionsBuilder setAnchor(Anchor anchor) {
		options.setAnchor(anchor);
		return this;
	}

	/**
	 * Sets the background color.
	 * 
	 * @param color the background color
	 * @return builder instance
	 */
	public DataLabelsOptionsBuilder setBackgroundColor(IsColor color) {
		options.setBackgroundColor(color);
		return this;
	}

	/**
	 * Sets the background color.
	 * 
	 * @param color the background color
	 * @return builder instance
	 */
	public DataLabelsOptionsBuilder setBackgroundColor(String color) {
		options.setBackgroundColor(color);
		return this;
	}

	/**
	 * Sets the border color.
	 * 
	 * @param color the border color
	 * @return builder instance
	 */
	public DataLabelsOptionsBuilder setBorderColor(IsColor color) {
		options.setBorderColor(color);
		return this;
	}

	/**
	 * Sets the border color.
	 * 
	 * @param color the border color
	 * @return builder instance
	 */
	public DataLabelsOptionsBuilder setBorderColor(String color) {
		options.setBorderColor(color);
		return this;
	}

	/**
	 * Sets the border radius.
	 * 
	 * @param radius the border radius.
	 * @return builder instance
	 */
	public DataLabelsOptionsBuilder setBorderRadius(double radius) {
		options.setBorderRadius(radius);
		return this;
	}

	/**
	 * Sets the border width.
	 * 
	 * @param width the border width.
	 * @return builder instance
	 */
	public DataLabelsOptionsBuilder setBorderWidth(int width) {
		options.setBorderWidth(width);
		return this;
	}

	/**
	 * Sets <code>true</code> to enforce the anchor position to be calculated based on the visible geometry of the associated
	 * element (i.e. part inside the chart area).
	 * 
	 * @param clamp <code>true</code> to enforce the anchor position to be calculated based on the visible geometry of the
	 *            associated element (i.e. part inside the chart area).
	 * @return builder instance
	 */
	public DataLabelsOptionsBuilder setClamp(boolean clamp) {
		options.setClamp(clamp);
		return this;
	}

	/**
	 * When the clip option is <code>true</code>, the part of the label which is outside the chart area will be masked.
	 * 
	 * @param clip when the clip option is <code>true</code>, the part of the label which is outside the chart area will be
	 *            masked.
	 * @return builder instance
	 */
	public DataLabelsOptionsBuilder setClip(boolean clip) {
		options.setClip(clip);
		return this;
	}

	/**
	 * Sets the color.
	 * 
	 * @param color the color
	 * @return builder instance
	 */
	public DataLabelsOptionsBuilder setColor(IsColor color) {
		options.setColor(color);
		return this;
	}

	/**
	 * Sets the color.
	 * 
	 * @param color the color
	 * @return builder instance
	 */
	public DataLabelsOptionsBuilder setColor(String color) {
		options.setColor(color);
		return this;
	}

	/**
	 * Sets the visibility of labels.
	 * 
	 * @param display the visibility of labels.
	 * @return builder instance
	 */
	public DataLabelsOptionsBuilder setDisplay(boolean display) {
		options.setDisplay(display);
		return this;
	}

	/**
	 * Sets the visibility of labels.
	 * 
	 * @param display the visibility of labels.
	 * @return builder instance
	 */
	public DataLabelsOptionsBuilder setDisplay(Display display) {
		options.setDisplay(display);
		return this;
	}

	/**
	 * Sets the distance (in pixels) to pull the label away from the anchor point. This option is not applicable when align is
	 * 'center'. Also note that if align is 'start', the label is moved in the opposite direction.
	 * 
	 * @param offset the distance (in pixels) to pull the label away from the anchor point. This option is not applicable when
	 *            align is 'center'. Also note that if align is 'start', the label is moved in the opposite direction.
	 * @return builder instance
	 */
	public DataLabelsOptionsBuilder setOffset(double offset) {
		options.setOffset(offset);
		return this;
	}

	/**
	 * Sets the opacity.
	 * 
	 * @param opacity the opacity.
	 * @return builder instance
	 */
	public DataLabelsOptionsBuilder setOpacity(double opacity) {
		options.setOpacity(opacity);
		return this;
	}

	/**
	 * Sets the clockwise rotation angle (in degrees) of the label, the rotation center point being the label center.
	 * 
	 * @param rotation the clockwise rotation angle (in degrees) of the label, the rotation center point being the label center.
	 * @return builder instance
	 */
	public DataLabelsOptionsBuilder setRotation(double rotation) {
		options.setRotation(rotation);
		return this;
	}

	/**
	 * Sets the text alignment being used when drawing the label text.
	 * 
	 * @param textAlign the text alignment being used when drawing the label text.
	 * @return builder instance
	 */
	public DataLabelsOptionsBuilder setTextAlign(TextAlign textAlign) {
		options.setTextAlign(textAlign);
		return this;
	}

	/**
	 * Sets the text stroke color.
	 * 
	 * @param color the text stroke color
	 * @return builder instance
	 */
	public DataLabelsOptionsBuilder setTextStrokeColor(IsColor color) {
		options.setTextStrokeColor(color);
		return this;
	}

	/**
	 * Sets the text stroke color.
	 * 
	 * @param color the text stroke color.
	 * @return builder instance
	 */
	public DataLabelsOptionsBuilder setTextStrokeColor(String color) {
		options.setTextStrokeColor(color);
		return this;
	}

	/**
	 * Sets the text stroke width.
	 * 
	 * @param textStrokeWidth the text stroke width.
	 * @return builder instance
	 */
	public DataLabelsOptionsBuilder setTextStrokeWidth(int textStrokeWidth) {
		options.setTextStrokeWidth(textStrokeWidth);
		return this;
	}

	/**
	 * Sets the text shadow blur.
	 * 
	 * @param textShadowBlur the text shadow blur.
	 * @return builder instance
	 */
	public DataLabelsOptionsBuilder setTextShadowBlur(double textShadowBlur) {
		options.setTextShadowBlur(textShadowBlur);
		return this;
	}

	/**
	 * Sets the text shadow color color.
	 * 
	 * @param color the text shadow color color
	 * @return builder instance
	 */
	public DataLabelsOptionsBuilder setTextShadowColor(IsColor color) {
		options.setTextShadowColor(color);
		return this;
	}

	/**
	 * Sets the text shadow color color.
	 * 
	 * @param color the text shadow color color.
	 * @return builder instance
	 */
	public DataLabelsOptionsBuilder setTextShadowColor(String color) {
		options.setTextShadowColor(color);
		return this;
	}

	/**
	 * Sets the background color callback.
	 * 
	 * @param backgroundColorCallback the background color callback.
	 * @return builder instance
	 */
	public DataLabelsOptionsBuilder setBackgroundColor(BackgroundColorCallback backgroundColorCallback) {
		options.setBackgroundColor(backgroundColorCallback);
		return this;
	}

	/**
	 * Sets the border color callback.
	 * 
	 * @param borderColorCallback the border color callback.
	 * @return builder instance
	 */
	public DataLabelsOptionsBuilder setBorderColor(BorderColorCallback borderColorCallback) {
		options.setBorderColor(borderColorCallback);
		return this;
	}

	/**
	 * Sets the color callback.
	 * 
	 * @param colorCallback the color callback.
	 * @return builder instance
	 */
	public DataLabelsOptionsBuilder setColor(ColorCallback colorCallback) {
		options.setColor(colorCallback);
		return this;
	}

	/**
	 * Sets the formatter callback.
	 * 
	 * @param formatterCallback the formatter callback to set
	 * @return builder instance
	 */
	public DataLabelsOptionsBuilder setFormatter(FormatterCallback formatterCallback) {
		options.setFormatter(formatterCallback);
		return this;
	}

	/**
	 * Sets the align callback.
	 * 
	 * @param alignCallback the align callback to set
	 * @return builder instance
	 */
	public DataLabelsOptionsBuilder setAlign(AlignCallback alignCallback) {
		options.setAlign(alignCallback);
		return this;
	}

	/**
	 * Sets the anchor callback.
	 * 
	 * @param anchorCallback the anchor callback to set
	 * @return builder instance
	 */
	public DataLabelsOptionsBuilder setAnchor(AnchorCallback anchorCallback) {
		options.setAnchor(anchorCallback);
		return this;
	}

	/**
	 * Sets the border radius callback.
	 * 
	 * @param borderRadiusCallback the border radius callback to set
	 * @return builder instance
	 */
	public DataLabelsOptionsBuilder setBorderRadius(RadiusCallback borderRadiusCallback) {
		options.setBorderRadius(borderRadiusCallback);
		return this;
	}

	/**
	 * Sets the border width callback.
	 * 
	 * @param borderWidthCallback the border width callback to set
	 * @return builder instance
	 */
	public DataLabelsOptionsBuilder setBorderWidth(BorderWidthCallback borderWidthCallback) {
		options.setBorderWidth(borderWidthCallback);
		return this;
	}

	/**
	 * Sets the clamp callback.
	 * 
	 * @param clampCallback the clamp callback to set
	 * @return builder instance
	 */
	public DataLabelsOptionsBuilder setClamp(ClampCallback clampCallback) {
		options.setClamp(clampCallback);
		return this;
	}

	/**
	 * Sets the clip callback.
	 * 
	 * @param clipCallback the clip callback to set
	 * @return builder instance
	 */
	public DataLabelsOptionsBuilder setClip(ClipCallback clipCallback) {
		options.setClip(clipCallback);
		return this;
	}

	/**
	 * Sets the display callback.
	 * 
	 * @param displayCallback the display callback to set
	 * @return builder instance
	 */
	public DataLabelsOptionsBuilder setDisplay(DisplayCallback displayCallback) {
		options.setDisplay(displayCallback);
		return this;
	}

	/**
	 * Sets the offset callback.
	 * 
	 * @param offsetCallback the offset callback to set
	 * @return builder instance
	 */
	public DataLabelsOptionsBuilder setOffset(OffsetCallback offsetCallback) {
		options.setOffset(offsetCallback);
		return this;
	}

	/**
	 * Sets the opacity callback.
	 * 
	 * @param opacityCallback the opacity callback to set
	 * @return builder instance
	 */
	public DataLabelsOptionsBuilder setOpacity(OpacityCallback opacityCallback) {
		options.setOpacity(opacityCallback);
		return this;
	}

	/**
	 * Sets the rotation callback.
	 * 
	 * @param rotationCallback the rotation callback to set
	 * @return builder instance
	 */
	public DataLabelsOptionsBuilder setRotation(RotationCallback rotationCallback) {
		options.setRotation(rotationCallback);
		return this;
	}

	/**
	 * Sets the text align callback.
	 * 
	 * @param textAlignCallback the text align callback to set
	 * @return builder instance
	 */
	public DataLabelsOptionsBuilder setTextAlign(TextAlignCallback textAlignCallback) {
		options.setTextAlign(textAlignCallback);
		return this;
	}

	/**
	 * Sets the text stroke color callback.
	 * 
	 * @param textStrokeColorCallback the text stroke color callback to set
	 * @return builder instance
	 */
	public DataLabelsOptionsBuilder setTextStrokeColor(TextStrokeColorCallback textStrokeColorCallback) {
		options.setTextStrokeColor(textStrokeColorCallback);
		return this;
	}

	/**
	 * Sets the text stroke width callback.
	 * 
	 * @param textStrokeWidthCallback the text stroke width callback to set
	 * @return builder instance
	 */
	public DataLabelsOptionsBuilder setTextStrokeWidth(TextStrokeWidthCallback textStrokeWidthCallback) {
		options.setTextStrokeWidth(textStrokeWidthCallback);
		return this;
	}

	/**
	 * Sets the text shadow blur callback.
	 * 
	 * @param textShadowBlurCallback the text shadow blur callback to set
	 * @return builder instance
	 */
	public DataLabelsOptionsBuilder setTextShadowBlur(TextShadowBlurCallback textShadowBlurCallback) {
		options.setTextShadowBlur(textShadowBlurCallback);
		return this;
	}

	/**
	 * Sets the text shadow color callback.
	 * 
	 * @param textShadowColorCallback the text shadow color callback to set
	 * @return builder instance
	 */
	public DataLabelsOptionsBuilder setTextShadowColor(TextShadowColorCallback textShadowColorCallback) {
		options.setTextShadowColor(textShadowColorCallback);
		return this;
	}

	/**
	 * Sets the the font callback.
	 * 
	 * @param fontCallback the font callback to set
	 * @return builder instance
	 */
	public DataLabelsOptionsBuilder setFont(FontCallback fontCallback) {
		options.setFont(fontCallback);
		return this;
	}

	/**
	 * Sets the padding callback.
	 * 
	 * @param paddingCallback the padding callback to set
	 * @return builder instance
	 */
	public DataLabelsOptionsBuilder setPadding(PaddingCallback paddingCallback) {
		options.setPadding(paddingCallback);
		return this;
	}

	/**
	 * Sets the font size.
	 * 
	 * @param fontSize the font size.
	 * @return builder instance
	 */
	public DataLabelsOptionsBuilder setFontSize(int fontSize) {
		options.getFont().setSize(fontSize);
		return this;
	}

	/**
	 * Sets the font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @param fontStyle Font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * @return builder instance
	 */
	public DataLabelsOptionsBuilder setFontStyle(FontStyle fontStyle) {
		options.getFont().setStyle(fontStyle);
		return this;
	}

	/**
	 * Sets the font family, follows CSS font-family options.
	 * 
	 * @param fontFamily Font family, follows CSS font-family options.
	 * @return builder instance
	 */
	public DataLabelsOptionsBuilder setFontFamily(String fontFamily) {
		options.getFont().setFamily(fontFamily);
		return this;
	}

	/**
	 * Sets the font weight, follows CSS font-style-weight options.
	 * 
	 * @param weight font weight, follows CSS font-style-weight options.
	 * @return builder instance
	 */
	public DataLabelsOptionsBuilder setFontWeight(Weight weight) {
		options.getFont().setWeight(weight);
		return this;
	}

	/**
	 * Sets the line height.
	 * 
	 * @param lineHeight the line height.
	 * @return builder instance
	 */
	public DataLabelsOptionsBuilder setFontLineHeight(double lineHeight) {
		options.getFont().setLineHeight(lineHeight);
		return this;
	}

	/**
	 * Sets the line height.
	 * 
	 * @param lineHeight the line height.
	 * @return builder instance
	 */
	public DataLabelsOptionsBuilder setFontLineHeight(String lineHeight) {
		options.getFont().setLineHeight(lineHeight);
		return this;
	}

	/**
	 * Sets the padding size to all dimensions.
	 * 
	 * @param padding padding size to apply to all dimensions.
	 * @return builder instance
	 */
	public DataLabelsOptionsBuilder setPadding(int padding) {
		options.getPadding().set(padding);
		return this;
	}

	/**
	 * Sets the padding left in pixel.
	 * 
	 * @param padding the padding left in pixel.
	 * @return builder instance
	 */
	public DataLabelsOptionsBuilder setPaddingLeft(int padding) {
		options.getPadding().setLeft(padding);
		return this;
	}

	/**
	 * Sets the padding right in pixel.
	 * 
	 * @param padding the padding right in pixel.
	 * @return builder instance
	 */
	public DataLabelsOptionsBuilder setPaddingRight(int padding) {
		options.getPadding().setRight(padding);
		return this;
	}

	/**
	 * Sets the padding top in pixel.
	 * 
	 * @param padding the padding top in pixel.
	 * @return builder instance
	 */
	public DataLabelsOptionsBuilder setPaddingTop(int padding) {
		options.getPadding().setTop(padding);
		return this;
	}

	/**
	 * Sets the padding bottom in pixel.
	 * 
	 * @param padding the padding bottom in pixel.
	 * @return builder instance
	 */
	public DataLabelsOptionsBuilder setPaddingBottom(int padding) {
		options.getPadding().setBottom(padding);
		return this;
	}

	/**
	 * Sets the CLICK event (the mouse's primary button is pressed and released on a label) handler.
	 * 
	 * @param clickEventHandler the click event handler to set
	 * @return builder instance
	 */
	public DataLabelsOptionsBuilder setClickEventHandler(ClickEventHandler clickEventHandler) {
		options.getListeners().setClickEventHandler(clickEventHandler);
		return this;
	}

	/**
	 * Sets the ENTER event (the mouse is moved over a label) handler.
	 * 
	 * @param enterEventHandler the enter event handler to set
	 * @return builder instance
	 */
	public DataLabelsOptionsBuilder setEnterEventHandler(EnterEventHandler enterEventHandler) {
		options.getListeners().setEnterEventHandler(enterEventHandler);
		return this;
	}

	/**
	 * Sets the LEAVE event (the mouse is moved out of a label) handler.
	 * 
	 * @param leaveEventHandler the leave event handler to set
	 * @return builder instance
	 */
	public DataLabelsOptionsBuilder setLeaveEventHandler(LeaveEventHandler leaveEventHandler) {
		options.getListeners().setLeaveEventHandler(leaveEventHandler);
		return this;
	}
}
