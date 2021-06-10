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
package org.pepstock.charba.client.configuration;

import java.util.List;

import org.pepstock.charba.client.callbacks.ChartContext;
import org.pepstock.charba.client.callbacks.ColorCallback;
import org.pepstock.charba.client.callbacks.DisplayCallback;
import org.pepstock.charba.client.callbacks.ElementAlignCallback;
import org.pepstock.charba.client.callbacks.FontCallback;
import org.pepstock.charba.client.callbacks.FullSizeCallback;
import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.callbacks.PaddingCallback;
import org.pepstock.charba.client.callbacks.PositionCallback;
import org.pepstock.charba.client.callbacks.TextCallback;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.enums.ElementAlign;
import org.pepstock.charba.client.enums.Position;
import org.pepstock.charba.client.options.IsScriptableFontProvider;
import org.pepstock.charba.client.options.IsScriptablePaddingProvider;

/**
 * Configures the chart title which defines text to draw at the top of the chart.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class Title extends ConfigurationOptionsContainer implements IsScriptableFontProvider<ChartContext>, IsScriptablePaddingProvider<ChartContext> {

	// font instance
	private final Font font;
	// instance of padding
	private final Padding padding;

	/**
	 * Builds the object storing the root options element.
	 * 
	 * @param options root options element.
	 */
	Title(ConfigurationOptions options) {
		super(options);
		// gets embedded font and padding
		this.font = new Font(this, () -> getConfiguration().getTitle().getFont());
		this.padding = new Padding(this, () -> getConfiguration().getTitle().getPadding());
	}

	/**
	 * Returns the font element.
	 * 
	 * @return the font
	 */
	public Font getFont() {
		return font;
	}

	/**
	 * Returns the padding element.
	 * 
	 * @return the padding
	 */
	public Padding getPadding() {
		return padding;
	}

	/**
	 * Sets the font color.
	 * 
	 * @param color font color.
	 */
	public void setColor(IsColor color) {
		setColor(IsColor.checkAndGetValue(color));
	}

	/**
	 * Sets the font color.
	 * 
	 * @param color font color.
	 */
	public void setColor(String color) {
		getConfiguration().getTitle().setColor(color);
	}

	/**
	 * Returns the font color as string.
	 * 
	 * @return font color as string
	 */
	public String getColorAsString() {
		return getConfiguration().getTitle().getColorAsString();
	}

	/**
	 * Returns the font color.
	 * 
	 * @return font color
	 */
	public IsColor getColor() {
		return ColorBuilder.parse(getColorAsString());
	}

	/**
	 * Sets if the title is shown.
	 * 
	 * @param display if the title is shown.
	 */
	public void setDisplay(boolean display) {
		getConfiguration().getTitle().setDisplay(display);
	}

	/**
	 * Returns if the title is shown.
	 * 
	 * @return if the title is shown.
	 */
	public boolean isDisplay() {
		return getConfiguration().getTitle().isDisplay();
	}

	/**
	 * Sets the title text to display. If specified as an array, text is rendered on multiple lines.
	 * 
	 * @param text the title text to display. If specified as an array, text is rendered on multiple lines.
	 */
	public void setText(String... text) {
		getConfiguration().getTitle().setText(text);
	}

	/**
	 * Returns the title text to display, as a list of strings.
	 * 
	 * @return a list of strings
	 */
	public List<String> getText() {
		return getConfiguration().getTitle().getText();
	}

	/**
	 * Sets the position of title.
	 * 
	 * @param position the position of title.
	 */
	public void setPosition(Position position) {
		getConfiguration().getTitle().setPosition(position);
	}

	/**
	 * Returns the position of title.
	 * 
	 * @return the position of title.
	 */
	public Position getPosition() {
		return getConfiguration().getTitle().getPosition();
	}

	/**
	 * Marks that this box should take the full width/height of the canvas (moving other boxes).
	 * 
	 * @param fullSize Marks that this box should take the full width/height of the canvas (moving other boxes)
	 */
	public void setFullSize(boolean fullSize) {
		getConfiguration().getTitle().setFullSize(fullSize);
	}

	/**
	 * Returns if marks that this box should take the full width/height of the canvas (moving other boxes).
	 * 
	 * @return Marks that this box should take the full width/height of the canvas (moving other boxes)
	 */
	public boolean isFullSize() {
		return getConfiguration().getTitle().isFullSize();
	}

	/**
	 * Sets the alignment of the title.
	 * 
	 * @param alignment alignment of the title.
	 */
	public void setAlign(ElementAlign alignment) {
		getConfiguration().getTitle().setAlign(alignment);
	}

	/**
	 * Returns the alignment of the title.
	 * 
	 * @return alignment of the title.
	 */
	public ElementAlign getAlign() {
		return getConfiguration().getTitle().getAlign();
	}

	// -------------------
	// CALLBACKS
	// -------------------

	/**
	 * Returns the callback to set if the title is shown.
	 * 
	 * @return the callback instance to use
	 */
	public DisplayCallback<ChartContext> getDisplayCallback() {
		return getConfiguration().getTitle().getDisplayCallback();
	}

	/**
	 * Sets if the title is shown by a callback.
	 * 
	 * @param displayCallback the callback instance to use
	 */
	public void setDisplay(DisplayCallback<ChartContext> displayCallback) {
		getConfiguration().getTitle().setDisplay(displayCallback);
	}

	/**
	 * Sets if the title is shown by a callback.
	 * 
	 * @param displayCallback the callback instance to use
	 */
	public void setDisplay(NativeCallback displayCallback) {
		getConfiguration().getTitle().setDisplay(displayCallback);
	}

	/**
	 * Returns the callback to set if marks that this box should take the full width/height of the canvas (moving other boxes).
	 * 
	 * @return the callback instance to use
	 */
	public FullSizeCallback<ChartContext> getFullSizeCallback() {
		return getConfiguration().getTitle().getFullSizeCallback();
	}

	/**
	 * Sets if marks that this box should take the full width/height of the canvas (moving other boxes) is shown by a callback.
	 * 
	 * @param fullSizeCallback the callback instance to use
	 */
	public void setFullSize(FullSizeCallback<ChartContext> fullSizeCallback) {
		getConfiguration().getTitle().setFullSize(fullSizeCallback);
	}

	/**
	 * Sets if marks that this box should take the full width/height of the canvas (moving other boxes) is shown by a callback.
	 * 
	 * @param fullSizeCallback the callback instance to use
	 */
	public void setFullSize(NativeCallback fullSizeCallback) {
		getConfiguration().getTitle().setFullSize(fullSizeCallback);
	}

	/**
	 * Returns the padding callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the padding callback, if set, otherwise <code>null</code>.
	 */
	@Override
	public PaddingCallback<ChartContext> getPaddingCallback() {
		return getConfiguration().getTitle().getPaddingCallback();
	}

	/**
	 * Sets the padding callback.
	 * 
	 * @param paddingCallback the padding callback to set
	 */
	@Override
	public void setPadding(PaddingCallback<ChartContext> paddingCallback) {
		getConfiguration().getTitle().setPadding(paddingCallback);
	}

	/**
	 * Sets the padding callback.
	 * 
	 * @param paddingCallback the padding callback to set
	 */
	@Override
	public void setPadding(NativeCallback paddingCallback) {
		getConfiguration().getTitle().setPadding(paddingCallback);
	}

	/**
	 * Returns the font callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the font callback, if set, otherwise <code>null</code>.
	 */
	@Override
	public FontCallback<ChartContext> getFontCallback() {
		return getConfiguration().getTitle().getFontCallback();
	}

	/**
	 * Sets the font callback.
	 * 
	 * @param fontCallback the font callback to set
	 */
	@Override
	public void setFont(FontCallback<ChartContext> fontCallback) {
		getConfiguration().getTitle().setFont(fontCallback);
	}

	/**
	 * Sets the font callback.
	 * 
	 * @param fontCallback the font callback to set
	 */
	@Override
	public void setFont(NativeCallback fontCallback) {
		getConfiguration().getTitle().setFont(fontCallback);
	}

	/**
	 * Returns the color callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the color callback, if set, otherwise <code>null</code>.
	 */
	public ColorCallback<ChartContext> getColorCallback() {
		return getConfiguration().getTitle().getColorCallback();
	}

	/**
	 * Sets the color callback.
	 * 
	 * @param colorCallback the color callback to set
	 */
	public void setColor(ColorCallback<ChartContext> colorCallback) {
		getConfiguration().getTitle().setColor(colorCallback);
	}

	/**
	 * Sets the color callback.
	 * 
	 * @param colorCallback the color callback to set
	 */
	public void setColor(NativeCallback colorCallback) {
		getConfiguration().getTitle().setColor(colorCallback);
	}

	/**
	 * Returns the text callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the text callback, if set, otherwise <code>null</code>.
	 */
	public TextCallback<ChartContext> getTextCallback() {
		return getConfiguration().getTitle().getTextCallback();
	}

	/**
	 * Sets the text callback.
	 * 
	 * @param textCallback the text callback to set
	 */
	public void setText(TextCallback<ChartContext> textCallback) {
		getConfiguration().getTitle().setText(textCallback);
	}

	/**
	 * Sets the text callback.
	 * 
	 * @param textCallback the text callback to set
	 */
	public void setText(NativeCallback textCallback) {
		getConfiguration().getTitle().setText(textCallback);
	}

	/**
	 * Returns the position callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the position callback, if set, otherwise <code>null</code>.
	 */
	public PositionCallback<ChartContext> getPositionCallback() {
		return getConfiguration().getTitle().getPositionCallback();
	}

	/**
	 * Sets the position callback.
	 * 
	 * @param positionCallback the position callback to set
	 */
	public void setPosition(PositionCallback<ChartContext> positionCallback) {
		getConfiguration().getTitle().setPosition(positionCallback);
	}

	/**
	 * Sets the position callback.
	 * 
	 * @param positionCallback the position callback to set
	 */
	public void setPosition(NativeCallback positionCallback) {
		getConfiguration().getTitle().setPosition(positionCallback);
	}

	/**
	 * Returns the align callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the align callback, if set, otherwise <code>null</code>.
	 */
	public ElementAlignCallback<ChartContext> getAlignCallback() {
		return getConfiguration().getTitle().getAlignCallback();
	}

	/**
	 * Sets the align callback.
	 * 
	 * @param alignCallback the align callback to set
	 */
	public void setAlign(ElementAlignCallback<ChartContext> alignCallback) {
		getConfiguration().getTitle().setAlign(alignCallback);
	}

	/**
	 * Sets the align callback.
	 * 
	 * @param alignCallback the align callback to set
	 */
	public void setAlign(NativeCallback alignCallback) {
		getConfiguration().getTitle().setAlign(alignCallback);
	}

}