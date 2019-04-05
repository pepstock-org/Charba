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

import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.labels.callbacks.FontColorCallback;
import org.pepstock.charba.client.labels.callbacks.RenderCallback;
import org.pepstock.charba.client.labels.enums.Position;
import org.pepstock.charba.client.labels.enums.Render;

import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Image;

/**
 * Comfortable object to create {@link LabelsPlugin#ID} plugin options by a builder.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class LabelsOptionsBuilder {
	// creates the options with deferred registration
	// because it will be register only when the build method
	// will be invoked
	private final LabelsOptions options = new LabelsOptions(true);

	/**
	 * To avoid any instantiation
	 * 
	 * @return builder instance
	 */
	private LabelsOptionsBuilder() {
		// do nothing;
	}

	/**
	 * Returns new builder instance.
	 * 
	 * @return new builder instance
	 */
	public static LabelsOptionsBuilder create() {
		return new LabelsOptionsBuilder();
	}

	/**
	 * Returns a configured labels options.
	 * 
	 * @return a configured labels options.
	 */
	public LabelsOptions build() {
		// registers the options to the factory
		options.register();
		// returns options
		return options;
	}

	/**
	 * Sets what data must be showed.
	 * 
	 * @param render what data must be showed.
	 * @return builder instance
	 */
	public LabelsOptionsBuilder setRender(Render render) {
		options.setRender(render);
		return this;
	}

	/**
	 * Sets the precision for percentage.
	 * 
	 * @param precision the precision for percentage
	 * @return builder instance
	 */
	public LabelsOptionsBuilder setPrecision(int precision) {
		options.setPrecision(precision);
		return this;
	}

	/**
	 * Sets whether or not labels of value 0 are displayed.
	 * 
	 * @param showZero whether or not labels of value 0 are displayed.
	 * @return builder instance
	 */
	public LabelsOptionsBuilder setShowZero(boolean showZero) {
		options.setShowZero(showZero);
		return this;
	}

	/**
	 * Sets the font size.
	 * 
	 * @param size the font size.
	 * @return builder instance
	 */
	public LabelsOptionsBuilder setFontSize(int size) {
		options.setFontSize(size);
		return this;

	}

	/**
	 * Sets the font color as color.
	 * 
	 * @param color the font color as color.
	 * @return builder instance
	 */
	public LabelsOptionsBuilder setFontColor(IsColor color) {
		options.setFontColor(color);
		return this;
	}

	/**
	 * Sets the font color as string.
	 * 
	 * @param color the font color as string.
	 * @return builder instance
	 */
	public LabelsOptionsBuilder setFontColor(String color) {
		options.setFontColor(color);
		return this;
	}

	/**
	 * Sets the font style.
	 * 
	 * @param fontStyle the font style.
	 * @return builder instance
	 */
	public LabelsOptionsBuilder setFontStyle(FontStyle fontStyle) {
		options.setFontStyle(fontStyle);
		return this;
	}

	/**
	 * Sets the font family
	 * 
	 * @param fontFamily the font family
	 * @return builder instance
	 */
	public LabelsOptionsBuilder setFontFamily(String fontFamily) {
		options.setFontFamily(fontFamily);
		return this;
	}

	/**
	 * Sets if draws text shadows under labels.
	 * 
	 * @param textShadow <code>true</code> if draws text shadows under labels.
	 * @return builder instance
	 */
	public LabelsOptionsBuilder setTextShadow(boolean textShadow) {
		options.setTextShadow(textShadow);
		return this;
	}

	/**
	 * Sets the text shadow intensity.
	 * 
	 * @param shadowBlur the text shadow intensity.
	 * @return builder instance
	 */
	public LabelsOptionsBuilder setShadowBlur(int shadowBlur) {
		options.setShadowBlur(shadowBlur);
		return this;
	}

	/**
	 * Sets the text shadow X offset.
	 * 
	 * @param shadowOffsetX the text shadow X offset.
	 * @return builder instance
	 */
	public LabelsOptionsBuilder setShadowOffsetX(int shadowOffsetX) {
		options.setShadowOffsetX(shadowOffsetX);
		return this;
	}

	/**
	 * Sets the text shadow Y offset.
	 * 
	 * @param shadowOffsetY the text shadow Y offset.
	 * @return builder instance
	 */
	public LabelsOptionsBuilder setShadowOffsetY(int shadowOffsetY) {
		options.setShadowOffsetY(shadowOffsetY);
		return this;
	}

	/**
	 * Sets the text shadow color as color.
	 * 
	 * @param shadowColor the text shadow color as color.
	 * @return builder instance
	 */
	public LabelsOptionsBuilder setShadowColor(IsColor shadowColor) {
		options.setShadowColor(shadowColor);
		return this;
	}

	/**
	 * Sets the text shadow color as string.
	 * 
	 * @param shadowColor the text shadow color as string.
	 * @return builder instance
	 */
	public LabelsOptionsBuilder setShadowColor(String shadowColor) {
		options.setShadowColor(shadowColor);
		return this;
	}

	/**
	 * Sets if draws label in arc. For bar chart this is ignored.
	 * 
	 * @param arc if draws label in arc.
	 * @return builder instance
	 */
	public LabelsOptionsBuilder setArc(boolean arc) {
		options.setArc(arc);
		return this;
	}

	/**
	 * + Sets the position to draw label. For bar chart this is ignored.
	 * 
	 * @param position the position to draw label.
	 * @return builder instance
	 */
	public LabelsOptionsBuilder setPosition(Position position) {
		options.setPosition(position);
		return this;
	}

	/**
	 * Sets if draws label even it's overlap. For bar chart this is ignored.
	 * 
	 * @param overlap if draws label even it's overlap.
	 * @return builder instance
	 */
	public LabelsOptionsBuilder setOverlap(boolean overlap) {
		options.setOverlap(overlap);
		return this;
	}

	/**
	 * Sets if shows the real calculated percentages from the values and don't apply the additional logic to fit the percentages
	 * to 100 in total.
	 * 
	 * @param showActualPercentages if shows the real calculated percentages from the values and don't apply the additional
	 *            logic to fit the percentages to 100 in total.
	 * @return builder instance
	 */
	public LabelsOptionsBuilder setShowActualPercentages(boolean showActualPercentages) {
		options.setShowActualPercentages(showActualPercentages);
		return this;
	}

	/**
	 * Sets the padding when position is {@link Position#OUTSIDE}.
	 * 
	 * @param outsidePadding the padding when position is {@link Position#OUTSIDE}.
	 * @return builder instance
	 */
	public LabelsOptionsBuilder setOutsidePadding(int outsidePadding) {
		options.setOutsidePadding(outsidePadding);
		return this;
	}

	/**
	 * Sets the margin of text when position is {@link Position#OUTSIDE} or {@link Position#BORDER}.
	 * 
	 * @param textMargin the margin of text when position is {@link Position#OUTSIDE} or {@link Position#BORDER}.
	 * @return builder instance
	 */
	public LabelsOptionsBuilder setTextMargin(int textMargin) {
		options.setTextMargin(textMargin);
		return this;
	}

	/**
	 * Sets the images when {@link Render} is {@link Render#IMAGE}.
	 * 
	 * @param images images when {@link Render} is {@link Render#IMAGE}.
	 * @return builder instance
	 */
	public LabelsOptionsBuilder setImages(ImageResource... images) {
		options.setImages(images);
		return this;
	}

	/**
	 * Sets the images when {@link Render} is {@link Render#IMAGE}.
	 * 
	 * @param images images when {@link Render} is {@link Render#IMAGE}.
	 * @return builder instance
	 */
	public LabelsOptionsBuilder setImages(Image... images) {
		options.setImages(images);
		return this;
	}

	/**
	 * Sets the images when {@link Render} is {@link Render#IMAGE}.
	 * 
	 * @param images images when {@link Render} is {@link Render#IMAGE}.
	 * @return builder instance
	 */
	public LabelsOptionsBuilder setImages(ImageElement... images) {
		options.setImages(images);
		return this;
	}

	/**
	 * Sets the render callback.
	 * 
	 * @param renderCallback the render callback to set
	 * @return builder instance
	 */
	public LabelsOptionsBuilder setRender(RenderCallback<?> renderCallback) {
		options.setRender(renderCallback);
		return this;
	}

	/**
	 * Sets the font color callback.
	 * 
	 * @param fontColorCallback the font color callback.
	 * @return builder instance
	 */
	public LabelsOptionsBuilder setFontColor(FontColorCallback<?> fontColorCallback) {
		options.setFontColor(fontColorCallback);
		return this;
	}

}
