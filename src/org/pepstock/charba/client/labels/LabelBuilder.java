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
package org.pepstock.charba.client.labels;

import org.pepstock.charba.client.callbacks.FontCallback;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.IsBuilder;
import org.pepstock.charba.client.dom.elements.Img;
import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.labels.callbacks.RenderCallback;
import org.pepstock.charba.client.labels.enums.Position;
import org.pepstock.charba.client.labels.enums.Render;

/**
 * Comfortable object to create {@link LabelsPlugin#ID} plugin LABEL options by a builder.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class LabelBuilder {

	// options builder instance
	// parent of this builder
	private LabelsOptionsBuilder builder;
	// plugin label options instance
	private final Label label;

	/**
	 * Creates a label builder with the parent and the label to wrap.
	 * 
	 * @param builder label plugin options builder instance
	 * @param label label instance to wrap.
	 */
	LabelBuilder(LabelsOptionsBuilder builder, Label label) {
		this.builder = builder;
		this.label = label;
	}

	/**
	 * Sets the {@link LabelsPlugin#ID} label builder.
	 * 
	 * @param builder the {@link LabelsPlugin#ID} label builder
	 */
	void setOptionsBuilder(LabelsOptionsBuilder builder) {
		this.builder = builder;
	}

	/**
	 * Returns the {@link LabelsPlugin#ID} label builder.
	 * 
	 * @return the {@link LabelsPlugin#ID} label builder
	 */
	public LabelsOptionsBuilder getOptionsBuilder() {
		return builder;
	}

	/**
	 * Sets what data must be showed.
	 * 
	 * @param render what data must be showed.
	 * @return builder instance
	 */
	public LabelBuilder setRender(Render render) {
		IsBuilder.checkIfValid(getOptionsBuilder());
		label.setRender(render);
		return this;
	}

	/**
	 * Sets the precision for percentage.
	 * 
	 * @param precision the precision for percentage
	 * @return builder instance
	 */
	public LabelBuilder setPrecision(int precision) {
		IsBuilder.checkIfValid(getOptionsBuilder());
		label.setPrecision(precision);
		return this;
	}

	/**
	 * Sets whether or not labels of value 0 are displayed.
	 * 
	 * @param showZero whether or not labels of value 0 are displayed.
	 * @return builder instance
	 */
	public LabelBuilder setShowZero(boolean showZero) {
		IsBuilder.checkIfValid(getOptionsBuilder());
		label.setShowZero(showZero);
		return this;
	}

	/**
	 * Sets the font size.
	 * 
	 * @param size the font size.
	 * @return builder instance
	 */
	public LabelBuilder setFontSize(int size) {
		IsBuilder.checkIfValid(getOptionsBuilder());
		label.getFont().setSize(size);
		return this;

	}

	/**
	 * Sets the font color as color.
	 * 
	 * @param color the font color as color.
	 * @return builder instance
	 */
	public LabelBuilder setColor(IsColor color) {
		IsBuilder.checkIfValid(getOptionsBuilder());
		label.setColor(color);
		return this;
	}

	/**
	 * Sets the font color as string.
	 * 
	 * @param color the font color as string.
	 * @return builder instance
	 */
	public LabelBuilder setColor(String color) {
		IsBuilder.checkIfValid(getOptionsBuilder());
		label.setColor(color);
		return this;
	}

	/**
	 * Sets the font style.
	 * 
	 * @param fontStyle the font style.
	 * @return builder instance
	 */
	public LabelBuilder setFontStyle(FontStyle fontStyle) {
		IsBuilder.checkIfValid(getOptionsBuilder());
		label.getFont().setStyle(fontStyle);
		return this;
	}

	/**
	 * Sets the font family
	 * 
	 * @param fontFamily the font family
	 * @return builder instance
	 */
	public LabelBuilder setFontFamily(String fontFamily) {
		IsBuilder.checkIfValid(getOptionsBuilder());
		label.getFont().setFamily(fontFamily);
		return this;
	}

	/**
	 * Sets if draws text shadows under labels.
	 * 
	 * @param textShadow <code>true</code> if draws text shadows under labels.
	 * @return builder instance
	 */
	public LabelBuilder setTextShadow(boolean textShadow) {
		IsBuilder.checkIfValid(getOptionsBuilder());
		label.setTextShadow(textShadow);
		return this;
	}

	/**
	 * Sets the text shadow intensity.
	 * 
	 * @param shadowBlur the text shadow intensity.
	 * @return builder instance
	 */
	public LabelBuilder setShadowBlur(int shadowBlur) {
		IsBuilder.checkIfValid(getOptionsBuilder());
		label.setShadowBlur(shadowBlur);
		return this;
	}

	/**
	 * Sets the text shadow X offset.
	 * 
	 * @param shadowOffsetX the text shadow X offset.
	 * @return builder instance
	 */
	public LabelBuilder setShadowOffsetX(int shadowOffsetX) {
		IsBuilder.checkIfValid(getOptionsBuilder());
		label.setShadowOffsetX(shadowOffsetX);
		return this;
	}

	/**
	 * Sets the text shadow Y offset.
	 * 
	 * @param shadowOffsetY the text shadow Y offset.
	 * @return builder instance
	 */
	public LabelBuilder setShadowOffsetY(int shadowOffsetY) {
		IsBuilder.checkIfValid(getOptionsBuilder());
		label.setShadowOffsetY(shadowOffsetY);
		return this;
	}

	/**
	 * Sets the text shadow color as color.
	 * 
	 * @param shadowColor the text shadow color as color.
	 * @return builder instance
	 */
	public LabelBuilder setShadowColor(IsColor shadowColor) {
		IsBuilder.checkIfValid(getOptionsBuilder());
		label.setShadowColor(shadowColor);
		return this;
	}

	/**
	 * Sets the text shadow color as string.
	 * 
	 * @param shadowColor the text shadow color as string.
	 * @return builder instance
	 */
	public LabelBuilder setShadowColor(String shadowColor) {
		IsBuilder.checkIfValid(getOptionsBuilder());
		label.setShadowColor(shadowColor);
		return this;
	}

	/**
	 * Sets if draws label in arc. For bar chart this is ignored.
	 * 
	 * @param arc if draws label in arc.
	 * @return builder instance
	 */
	public LabelBuilder setArc(boolean arc) {
		IsBuilder.checkIfValid(getOptionsBuilder());
		label.setArc(arc);
		return this;
	}

	/**
	 * + Sets the position to draw label. For bar chart this is ignored.
	 * 
	 * @param position the position to draw label.
	 * @return builder instance
	 */
	public LabelBuilder setPosition(Position position) {
		IsBuilder.checkIfValid(getOptionsBuilder());
		label.setPosition(position);
		return this;
	}

	/**
	 * Sets if draws label even it's overlap. For bar chart this is ignored.
	 * 
	 * @param overlap if draws label even it's overlap.
	 * @return builder instance
	 */
	public LabelBuilder setOverlap(boolean overlap) {
		IsBuilder.checkIfValid(getOptionsBuilder());
		label.setOverlap(overlap);
		return this;
	}

	/**
	 * Sets if shows the real calculated percentages from the values and don't apply the additional logic to fit the percentages to 100 in total.
	 * 
	 * @param showActualPercentages if shows the real calculated percentages from the values and don't apply the additional logic to fit the percentages to 100 in total.
	 * @return builder instance
	 */
	public LabelBuilder setShowActualPercentages(boolean showActualPercentages) {
		IsBuilder.checkIfValid(getOptionsBuilder());
		label.setShowActualPercentages(showActualPercentages);
		return this;
	}

	/**
	 * Sets the padding when position is {@link Position#OUTSIDE}.
	 * 
	 * @param outsidePadding the padding when position is {@link Position#OUTSIDE}.
	 * @return builder instance
	 */
	public LabelBuilder setOutsidePadding(int outsidePadding) {
		IsBuilder.checkIfValid(getOptionsBuilder());
		label.setOutsidePadding(outsidePadding);
		return this;
	}

	/**
	 * Sets the margin of text when position is {@link Position#OUTSIDE} or {@link Position#BORDER}.
	 * 
	 * @param textMargin the margin of text when position is {@link Position#OUTSIDE} or {@link Position#BORDER}.
	 * @return builder instance
	 */
	public LabelBuilder setTextMargin(int textMargin) {
		IsBuilder.checkIfValid(getOptionsBuilder());
		label.setTextMargin(textMargin);
		return this;
	}

	/**
	 * Sets the images when {@link Render} is {@link Render#IMAGE}.
	 * 
	 * @param images images when {@link Render} is {@link Render#IMAGE}.
	 * @return builder instance
	 */
	public LabelBuilder setImages(Img... images) {
		IsBuilder.checkIfValid(getOptionsBuilder());
		label.setImages(images);
		return this;
	}

	/**
	 * Sets the render callback.
	 * 
	 * @param renderCallback the render callback to set
	 * @return builder instance
	 */
	public LabelBuilder setRender(RenderCallback renderCallback) {
		IsBuilder.checkIfValid(getOptionsBuilder());
		label.setRender(renderCallback);
		return this;
	}

	/**
	 * Sets the font color callback.
	 * 
	 * @param fontColorCallback the font color callback.
	 * @return builder instance
	 */
	public LabelBuilder setFont(FontCallback<LabelsContext> fontColorCallback) {
		IsBuilder.checkIfValid(getOptionsBuilder());
		label.setFont(fontColorCallback);
		return this;
	}

}
