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

import org.pepstock.charba.client.enums.ElementAlign;
import org.pepstock.charba.client.enums.Position;
import org.pepstock.charba.client.options.ExtendedOptions;

/**
 * Configures the chart title which defines text to draw at the top of the chart.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class Title {

	private final ExtendedOptions options;
	// font instance
	private final Font font;

	/**
	 * Builds the object storing the root options element.
	 * 
	 * @param options root options element.
	 */
	Title(ExtendedOptions options) {
		this.options = options;
		// checks if configuration is consistent
		if (options == null) {
			// if not exception
			throw new IllegalArgumentException("Options argument is null");
		}
		this.font = new Font(options.getTitle().getFont());
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
	 * Sets if the title is shown.
	 * 
	 * @param display if the title is shown.
	 */
	public void setDisplay(boolean display) {
		options.getTitle().setDisplay(display);
	}

	/**
	 * Returns if the title is shown.
	 * 
	 * @return if the title is shown.
	 */
	public boolean isDisplay() {
		return options.getTitle().isDisplay();
	}

	/**
	 * Sets the title text to display. If specified as an array, text is rendered on multiple lines.
	 * 
	 * @param text the title text to display. If specified as an array, text is rendered on multiple lines.
	 */
	public void setText(String... text) {
		options.getTitle().setText(text);
	}

	/**
	 * Returns the title text to display, as a list of strings.
	 * 
	 * @return a list of strings
	 */
	public List<String> getText() {
		return options.getTitle().getText();
	}

	/**
	 * Sets the position of title.
	 * 
	 * @param position the position of title.
	 */
	public void setPosition(Position position) {
		options.getTitle().setPosition(position);
	}

	/**
	 * Returns the position of title.
	 * 
	 * @return the position of title.
	 */
	public Position getPosition() {
		return options.getTitle().getPosition();
	}

	/**
	 * Sets the padding to apply around title. Only top and bottom are implemented.
	 * 
	 * @param padding Padding to apply around title. Only top and bottom are implemented.
	 */
	public void setPadding(int padding) {
		options.getTitle().setPadding(padding);
	}

	/**
	 * Returns the padding to apply around title. Only top and bottom are implemented.
	 * 
	 * @return Padding to apply around title. Only top and bottom are implemented.
	 */
	public int getPadding() {
		return options.getTitle().getPadding();
	}

	/**
	 * Marks that this box should take the full width of the canvas (pushing down other boxes).
	 * 
	 * @param fullWidth Marks that this box should take the full width of the canvas (pushing down other boxes)
	 */
	public void setFullWidth(boolean fullWidth) {
		options.getTitle().setFullWidth(fullWidth);
	}

	/**
	 * Returns if marks that this box should take the full width of the canvas (pushing down other boxes)
	 * 
	 * @return Marks that this box should take the full width of the canvas (pushing down other boxes).
	 */
	public boolean isFullWidth() {
		return options.getTitle().isFullWidth();
	}
	
	/**
	 * Sets the alignment of the title.
	 * 
	 * @param alignment alignment of the title.
	 */
	public void setAlign(ElementAlign alignment) {
		options.getTitle().setAlign(alignment);
	}

	/**
	 * Returns the alignment of the title.
	 * 
	 * @return alignment of the title.
	 */
	public ElementAlign getAlign() {
		return options.getTitle().getAlign();
	}

}