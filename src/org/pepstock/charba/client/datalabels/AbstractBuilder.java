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

import org.pepstock.charba.client.callbacks.ColorCallback;
import org.pepstock.charba.client.callbacks.DisplayCallback;
import org.pepstock.charba.client.callbacks.FontCallback;
import org.pepstock.charba.client.callbacks.OffsetCallback;
import org.pepstock.charba.client.callbacks.PaddingCallback;
import org.pepstock.charba.client.callbacks.RadiusCallback;
import org.pepstock.charba.client.callbacks.RotationCallback;
import org.pepstock.charba.client.callbacks.TextAlignCallback;
import org.pepstock.charba.client.callbacks.WidthCallback;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.AbstractBaseBuilder;
import org.pepstock.charba.client.commons.IsBuilder;
import org.pepstock.charba.client.datalabels.callbacks.AlignCallback;
import org.pepstock.charba.client.datalabels.callbacks.AnchorCallback;
import org.pepstock.charba.client.datalabels.callbacks.ClampCallback;
import org.pepstock.charba.client.datalabels.callbacks.ClipCallback;
import org.pepstock.charba.client.datalabels.callbacks.FormatterCallback;
import org.pepstock.charba.client.datalabels.callbacks.OpacityCallback;
import org.pepstock.charba.client.datalabels.callbacks.TextShadowBlurCallback;
import org.pepstock.charba.client.datalabels.enums.Align;
import org.pepstock.charba.client.datalabels.enums.Anchor;
import org.pepstock.charba.client.datalabels.events.AbstractEventHandler;
import org.pepstock.charba.client.datalabels.events.ClickEventHandler;
import org.pepstock.charba.client.datalabels.events.EnterEventHandler;
import org.pepstock.charba.client.datalabels.events.LeaveEventHandler;
import org.pepstock.charba.client.enums.Display;
import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.enums.TextAlign;
import org.pepstock.charba.client.enums.Weight;

/**
 * Abstract base comfortable object to create {@link DataLabelsPlugin#ID} label item by a builder.
 * 
 * @author Andrea "Stock" Stocchero
 * @param <T> type of label item
 */
public abstract class AbstractBuilder<T extends LabelItem> extends AbstractBaseBuilder {

	// label item instance
	private final T label;
	// options builder instance
	// parent of this builder
	private DataLabelsOptionsBuilder builder;

	/**
	 * Creates a label item builder with the instance to wrap.
	 * 
	 * @param label label item related to the plugin options
	 */
	AbstractBuilder(T label) {
		this.label = label;
	}

	/**
	 * Returns the label item, wrapped from this builder.
	 * 
	 * @return the label item, wrapped from this builder
	 */
	final T getLabel() {
		return label;
	}

	/**
	 * Sets the {@link DataLabelsPlugin#ID} label builder.
	 * 
	 * @param builder the {@link DataLabelsPlugin#ID} label builder
	 */
	void setOptionsBuilder(DataLabelsOptionsBuilder builder) {
		this.builder = builder;
	}

	/**
	 * Returns the {@link DataLabelsPlugin#ID} label builder.
	 * 
	 * @return the {@link DataLabelsPlugin#ID} label builder
	 */
	public DataLabelsOptionsBuilder getOptionsBuilder() {
		return builder;
	}

	/**
	 * Adds a event handler instance as listener for all events.
	 * 
	 * @param handler event handler instance as listener for all events.
	 * @return builder instance
	 */
	public final AbstractBuilder<T> setListenersHandler(AbstractEventHandler handler) {
		label.setListenersHandler(handler);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the position of the label relative to the anchor point position and orientation.
	 * 
	 * @param align the position of the label relative to the anchor point position and orientation.
	 * @return builder instance
	 */
	public final AbstractBuilder<T> setAlign(Align align) {
		label.setAlign(align);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the position of the label relative to the anchor point position and orientation, by a number representing the clockwise angle (in degree).
	 * 
	 * @param align the position of the label relative to the anchor point position and orientation, by a number representing the clockwise angle (in degree)
	 * @return builder instance
	 */
	public final AbstractBuilder<T> setAlign(double align) {
		label.setAlign(align);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the anchor point, which is defined by an orientation vector and a position on the data element.
	 * 
	 * @param anchor the anchor point, which is defined by an orientation vector and a position on the data element.
	 * @return builder instance
	 */
	public final AbstractBuilder<T> setAnchor(Anchor anchor) {
		label.setAnchor(anchor);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the background color.
	 * 
	 * @param color the background color
	 * @return builder instance
	 */
	public final AbstractBuilder<T> setBackgroundColor(IsColor color) {
		label.setBackgroundColor(color);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the background color.
	 * 
	 * @param color the background color
	 * @return builder instance
	 */
	public final AbstractBuilder<T> setBackgroundColor(String color) {
		label.setBackgroundColor(color);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the border color.
	 * 
	 * @param color the border color
	 * @return builder instance
	 */
	public final AbstractBuilder<T> setBorderColor(IsColor color) {
		label.setBorderColor(color);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the border color.
	 * 
	 * @param color the border color
	 * @return builder instance
	 */
	public final AbstractBuilder<T> setBorderColor(String color) {
		label.setBorderColor(color);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the border radius.
	 * 
	 * @param radius the border radius.
	 * @return builder instance
	 */
	public final AbstractBuilder<T> setBorderRadius(double radius) {
		label.setBorderRadius(radius);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the border width.
	 * 
	 * @param width the border width.
	 * @return builder instance
	 */
	public final AbstractBuilder<T> setBorderWidth(int width) {
		label.setBorderWidth(width);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets <code>true</code> to enforce the anchor position to be calculated based on the visible geometry of the associated element (i.e. part inside the chart area).
	 * 
	 * @param clamp <code>true</code> to enforce the anchor position to be calculated based on the visible geometry of the associated element (i.e. part inside the chart area).
	 * @return builder instance
	 */
	public final AbstractBuilder<T> setClamp(boolean clamp) {
		label.setClamp(clamp);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * When the clip option is <code>true</code>, the part of the label which is outside the chart area will be masked.
	 * 
	 * @param clip when the clip option is <code>true</code>, the part of the label which is outside the chart area will be masked.
	 * @return builder instance
	 */
	public final AbstractBuilder<T> setClip(boolean clip) {
		label.setClip(clip);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the color.
	 * 
	 * @param color the color
	 * @return builder instance
	 */
	public final AbstractBuilder<T> setColor(IsColor color) {
		label.setColor(color);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the color.
	 * 
	 * @param color the color
	 * @return builder instance
	 */
	public final AbstractBuilder<T> setColor(String color) {
		label.setColor(color);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the visibility of labels.
	 * 
	 * @param display the visibility of labels.
	 * @return builder instance
	 */
	public final AbstractBuilder<T> setDisplay(boolean display) {
		label.setDisplay(display);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the visibility of labels.
	 * 
	 * @param display the visibility of labels.
	 * @return builder instance
	 */
	public final AbstractBuilder<T> setDisplay(Display display) {
		label.setDisplay(display);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the distance (in pixels) to pull the label away from the anchor point. This option is not applicable when align is 'center'. Also note that if align is 'start', the
	 * label is moved in the opposite direction.
	 * 
	 * @param offset the distance (in pixels) to pull the label away from the anchor point. This option is not applicable when align is 'center'. Also note that if align is
	 *            'start', the label is moved in the opposite direction.
	 * @return builder instance
	 */
	public final AbstractBuilder<T> setOffset(int offset) {
		label.setOffset(offset);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the opacity.
	 * 
	 * @param opacity the opacity.
	 * @return builder instance
	 */
	public final AbstractBuilder<T> setOpacity(double opacity) {
		label.setOpacity(opacity);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the clockwise rotation angle (in degrees) of the label, the rotation center point being the label center.
	 * 
	 * @param rotation the clockwise rotation angle (in degrees) of the label, the rotation center point being the label center.
	 * @return builder instance
	 */
	public final AbstractBuilder<T> setRotation(double rotation) {
		label.setRotation(rotation);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the text alignment being used when drawing the label text.
	 * 
	 * @param textAlign the text alignment being used when drawing the label text.
	 * @return builder instance
	 */
	public final AbstractBuilder<T> setTextAlign(TextAlign textAlign) {
		label.setTextAlign(textAlign);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the text stroke color.
	 * 
	 * @param color the text stroke color
	 * @return builder instance
	 */
	public final AbstractBuilder<T> setTextStrokeColor(IsColor color) {
		label.setTextStrokeColor(color);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the text stroke color.
	 * 
	 * @param color the text stroke color.
	 * @return builder instance
	 */
	public final AbstractBuilder<T> setTextStrokeColor(String color) {
		label.setTextStrokeColor(color);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the text stroke width.
	 * 
	 * @param textStrokeWidth the text stroke width.
	 * @return builder instance
	 */
	public final AbstractBuilder<T> setTextStrokeWidth(int textStrokeWidth) {
		label.setTextStrokeWidth(textStrokeWidth);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the text shadow blur.
	 * 
	 * @param textShadowBlur the text shadow blur.
	 * @return builder instance
	 */
	public final AbstractBuilder<T> setTextShadowBlur(double textShadowBlur) {
		label.setTextShadowBlur(textShadowBlur);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the text shadow color color.
	 * 
	 * @param color the text shadow color color
	 * @return builder instance
	 */
	public final AbstractBuilder<T> setTextShadowColor(IsColor color) {
		label.setTextShadowColor(color);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the text shadow color color.
	 * 
	 * @param color the text shadow color color.
	 * @return builder instance
	 */
	public final AbstractBuilder<T> setTextShadowColor(String color) {
		label.setTextShadowColor(color);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the background color callback.
	 * 
	 * @param backgroundColorCallback the background color callback.
	 * @return builder instance
	 */
	public final AbstractBuilder<T> setBackgroundColor(ColorCallback<DataLabelsContext> backgroundColorCallback) {
		label.setBackgroundColor(backgroundColorCallback);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the border color callback.
	 * 
	 * @param borderColorCallback the border color callback.
	 * @return builder instance
	 */
	public final AbstractBuilder<T> setBorderColor(ColorCallback<DataLabelsContext> borderColorCallback) {
		label.setBorderColor(borderColorCallback);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the color callback.
	 * 
	 * @param colorCallback the color callback.
	 * @return builder instance
	 */
	public final AbstractBuilder<T> setColor(ColorCallback<DataLabelsContext> colorCallback) {
		label.setColor(colorCallback);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the formatter callback.
	 * 
	 * @param formatterCallback the formatter callback to set
	 * @return builder instance
	 */
	public final AbstractBuilder<T> setFormatter(FormatterCallback formatterCallback) {
		label.setFormatter(formatterCallback);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the align callback.
	 * 
	 * @param alignCallback the align callback to set
	 * @return builder instance
	 */
	public final AbstractBuilder<T> setAlign(AlignCallback alignCallback) {
		label.setAlign(alignCallback);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the anchor callback.
	 * 
	 * @param anchorCallback the anchor callback to set
	 * @return builder instance
	 */
	public final AbstractBuilder<T> setAnchor(AnchorCallback anchorCallback) {
		label.setAnchor(anchorCallback);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the border radius callback.
	 * 
	 * @param borderRadiusCallback the border radius callback to set
	 * @return builder instance
	 */
	public final AbstractBuilder<T> setBorderRadius(RadiusCallback<DataLabelsContext> borderRadiusCallback) {
		label.setBorderRadius(borderRadiusCallback);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the border width callback.
	 * 
	 * @param borderWidthCallback the border width callback to set
	 * @return builder instance
	 */
	public final AbstractBuilder<T> setBorderWidth(WidthCallback<DataLabelsContext> borderWidthCallback) {
		label.setBorderWidth(borderWidthCallback);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the clamp callback.
	 * 
	 * @param clampCallback the clamp callback to set
	 * @return builder instance
	 */
	public final AbstractBuilder<T> setClamp(ClampCallback clampCallback) {
		label.setClamp(clampCallback);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the clip callback.
	 * 
	 * @param clipCallback the clip callback to set
	 * @return builder instance
	 */
	public final AbstractBuilder<T> setClip(ClipCallback clipCallback) {
		label.setClip(clipCallback);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the display callback.
	 * 
	 * @param displayCallback the display callback to set
	 * @return builder instance
	 */
	public final AbstractBuilder<T> setDisplay(DisplayCallback<DataLabelsContext> displayCallback) {
		label.setDisplay(displayCallback);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the offset callback.
	 * 
	 * @param offsetCallback the offset callback to set
	 * @return builder instance
	 */
	public final AbstractBuilder<T> setOffset(OffsetCallback<DataLabelsContext> offsetCallback) {
		label.setOffset(offsetCallback);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the opacity callback.
	 * 
	 * @param opacityCallback the opacity callback to set
	 * @return builder instance
	 */
	public final AbstractBuilder<T> setOpacity(OpacityCallback opacityCallback) {
		label.setOpacity(opacityCallback);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the rotation callback.
	 * 
	 * @param rotationCallback the rotation callback to set
	 * @return builder instance
	 */
	public final AbstractBuilder<T> setRotation(RotationCallback<DataLabelsContext> rotationCallback) {
		label.setRotation(rotationCallback);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the text align callback.
	 * 
	 * @param textAlignCallback the text align callback to set
	 * @return builder instance
	 */
	public final AbstractBuilder<T> setTextAlign(TextAlignCallback<DataLabelsContext> textAlignCallback) {
		label.setTextAlign(textAlignCallback);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the text stroke color callback.
	 * 
	 * @param textStrokeColorCallback the text stroke color callback to set
	 * @return builder instance
	 */
	public final AbstractBuilder<T> setTextStrokeColor(ColorCallback<DataLabelsContext> textStrokeColorCallback) {
		label.setTextStrokeColor(textStrokeColorCallback);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the text stroke width callback.
	 * 
	 * @param textStrokeWidthCallback the text stroke width callback to set
	 * @return builder instance
	 */
	public final AbstractBuilder<T> setTextStrokeWidth(WidthCallback<DataLabelsContext> textStrokeWidthCallback) {
		label.setTextStrokeWidth(textStrokeWidthCallback);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the text shadow blur callback.
	 * 
	 * @param textShadowBlurCallback the text shadow blur callback to set
	 * @return builder instance
	 */
	public final AbstractBuilder<T> setTextShadowBlur(TextShadowBlurCallback textShadowBlurCallback) {
		label.setTextShadowBlur(textShadowBlurCallback);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the text shadow color callback.
	 * 
	 * @param textShadowColorCallback the text shadow color callback to set
	 * @return builder instance
	 */
	public final AbstractBuilder<T> setTextShadowColor(ColorCallback<DataLabelsContext> textShadowColorCallback) {
		label.setTextShadowColor(textShadowColorCallback);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the font callback.
	 * 
	 * @param fontCallback the font callback to set
	 * @return builder instance
	 */
	public final AbstractBuilder<T> setFont(FontCallback<DataLabelsContext> fontCallback) {
		label.setFont(fontCallback);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the padding callback.
	 * 
	 * @param paddingCallback the padding callback to set
	 * @return builder instance
	 */
	public final AbstractBuilder<T> setPadding(PaddingCallback<DataLabelsContext> paddingCallback) {
		label.setPadding(paddingCallback);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the font size.
	 * 
	 * @param fontSize the font size.
	 * @return builder instance
	 */
	public final AbstractBuilder<T> setFontSize(int fontSize) {
		label.getFont().setSize(fontSize);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the font style, follows CSS font-style label (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @param fontStyle Font style, follows CSS font-style label (i.e. normal, italic, oblique, initial, inherit).
	 * @return builder instance
	 */
	public final AbstractBuilder<T> setFontStyle(FontStyle fontStyle) {
		label.getFont().setStyle(fontStyle);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the font family, follows CSS font-family label.
	 * 
	 * @param fontFamily Font family, follows CSS font-family label.
	 * @return builder instance
	 */
	public final AbstractBuilder<T> setFontFamily(String fontFamily) {
		label.getFont().setFamily(fontFamily);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the font weight, follows CSS font-style-weight label.
	 * 
	 * @param weight font weight, follows CSS font-style-weight label.
	 * @return builder instance
	 */
	public final AbstractBuilder<T> setFontWeight(Weight weight) {
		label.getFont().setWeight(weight);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the line height.
	 * 
	 * @param lineHeight the line height.
	 * @return builder instance
	 */
	public final AbstractBuilder<T> setFontLineHeight(double lineHeight) {
		label.getFont().setLineHeight(lineHeight);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the line height.
	 * 
	 * @param lineHeight the line height.
	 * @return builder instance
	 */
	public final AbstractBuilder<T> setFontLineHeight(String lineHeight) {
		label.getFont().setLineHeight(lineHeight);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the padding size to all dimensions.
	 * 
	 * @param padding padding size to apply to all dimensions.
	 * @return builder instance
	 */
	public final AbstractBuilder<T> setPadding(int padding) {
		label.getPadding().set(padding);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the padding left in pixel.
	 * 
	 * @param padding the padding left in pixel.
	 * @return builder instance
	 */
	public final AbstractBuilder<T> setPaddingLeft(int padding) {
		label.getPadding().setLeft(padding);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the padding right in pixel.
	 * 
	 * @param padding the padding right in pixel.
	 * @return builder instance
	 */
	public final AbstractBuilder<T> setPaddingRight(int padding) {
		label.getPadding().setRight(padding);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the padding top in pixel.
	 * 
	 * @param padding the padding top in pixel.
	 * @return builder instance
	 */
	public final AbstractBuilder<T> setPaddingTop(int padding) {
		label.getPadding().setTop(padding);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the padding bottom in pixel.
	 * 
	 * @param padding the padding bottom in pixel.
	 * @return builder instance
	 */
	public final AbstractBuilder<T> setPaddingBottom(int padding) {
		label.getPadding().setBottom(padding);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the CLICK event (the mouse's primary button is pressed and released on a label) handler.
	 * 
	 * @param clickEventHandler the click event handler to set
	 * @return builder instance
	 */
	public final AbstractBuilder<T> setClickEventHandler(ClickEventHandler clickEventHandler) {
		label.getListeners().setClickEventHandler(clickEventHandler);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the ENTER event (the mouse is moved over a label) handler.
	 * 
	 * @param enterEventHandler the enter event handler to set
	 * @return builder instance
	 */
	public final AbstractBuilder<T> setEnterEventHandler(EnterEventHandler enterEventHandler) {
		label.getListeners().setEnterEventHandler(enterEventHandler);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the LEAVE event (the mouse is moved out of a label) handler.
	 * 
	 * @param leaveEventHandler the leave event handler to set
	 * @return builder instance
	 */
	public final AbstractBuilder<T> setLeaveEventHandler(LeaveEventHandler leaveEventHandler) {
		label.getListeners().setLeaveEventHandler(leaveEventHandler);
		return IsBuilder.checkAndGetIfValid(this);
	}

}
