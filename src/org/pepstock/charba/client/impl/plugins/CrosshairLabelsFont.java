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
package org.pepstock.charba.client.impl.plugins;

import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.enums.Weight;

/**
 * Utility provided to the user in order to set the same values to all font labels, X and Y.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class CrosshairLabelsFont {

	// options parent
	private final CrosshairOptions parent;

	/**
	 * Creates new crosshair labelsfont .
	 * 
	 * @param parent plugin options.
	 */
	CrosshairLabelsFont(CrosshairOptions parent) {
		this.parent = parent;
	}

	/**
	 * Sets the font size.
	 * 
	 * @param size the font size.
	 */
	public void setSize(int size) {
		// stores value on x and y labels
		parent.getXLabel().getFont().setSize(size);
		parent.getYLabel().getFont().setSize(size);
	}

	/**
	 * Sets the font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @param style Font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 */
	public void setStyle(FontStyle style) {
		// stores value on x and y labels
		parent.getXLabel().getFont().setStyle(style);
		parent.getYLabel().getFont().setStyle(style);
	}

	/**
	 * Sets the font family, follows CSS font-family options.
	 * 
	 * @param family Font family, follows CSS font-family options.
	 */
	public void setFamily(String family) {
		// stores value on x and y labels
		parent.getXLabel().getFont().setFamily(family);
		parent.getYLabel().getFont().setFamily(family);
	}

	/**
	 * Sets the font weight, follows CSS font-style-weight options.
	 * 
	 * @param weight font weight, follows CSS font-style-weight options.
	 */
	public void setWeight(Weight weight) {
		// stores value on x and y labels
		parent.getXLabel().getFont().setWeight(weight);
		parent.getYLabel().getFont().setWeight(weight);
	}

	/**
	 * Sets the line height.
	 * 
	 * @param lineHeight the line height.
	 */
	public void setLineHeight(double lineHeight) {
		// stores value on x and y labels
		parent.getXLabel().getFont().setLineHeight(lineHeight);
		parent.getYLabel().getFont().setLineHeight(lineHeight);
	}

	/**
	 * Sets the line height.
	 * 
	 * @param lineHeight the line height.
	 */
	public void setLineHeight(String lineHeight) {
		// stores value on x and y labels
		parent.getXLabel().getFont().setLineHeight(lineHeight);
		parent.getYLabel().getFont().setLineHeight(lineHeight);
	}

}
