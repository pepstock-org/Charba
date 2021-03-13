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

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.dom.elements.Img;
import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.enums.Position;
import org.pepstock.charba.client.impl.plugins.enums.Align;
import org.pepstock.charba.client.impl.plugins.enums.Render;

/**
 * Maps {@link DatasetsItemsSelector#ID} plugin default options for CLEAR SELECTION element.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
interface IsDatasetsItemsSelectorDefaultClearSelection {

	/**
	 * Returns <code>true</code> if clear of selection label will be applied in the chart, otherwise <code>false</code>.
	 * 
	 * @return <code>true</code> if clear of selection label will be applied in the chart, otherwise <code>false</code>
	 */
	default boolean isDisplay() {
		return ClearSelection.DEFAULT_DISPLAY;
	}

	/**
	 * Returns the clear selection label.
	 * 
	 * @return the clear selection label
	 */
	default String getLabel() {
		return ClearSelection.DEFAULT_LABEL;
	}

	/**
	 * Returns the clear selection label font size.
	 * 
	 * @return the clear selection label font size
	 */
	default int getFontSize() {
		return Defaults.get().getGlobal().getFont().getSize();
	}

	/**
	 * Returns the clear selection label font family.
	 * 
	 * @return the clear selection label font family
	 */
	default String getFontFamily() {
		return Defaults.get().getGlobal().getFont().getFamily();
	}

	/**
	 * Returns the clear selection label font style.
	 * 
	 * @return the clear selection label font style
	 */
	default FontStyle getFontStyle() {
		return Defaults.get().getGlobal().getFont().getStyle();
	}

	/**
	 * Returns the clear selection label font color.
	 * 
	 * @return the clear selection label font color.
	 */
	default String getFontColorAsString() {
		return DatasetsItemsSelectorOptions.DEFAULT_BORDER_COLOR.toRGBA();
	}

	/**
	 * Returns the clear selection align.
	 * 
	 * @return the clear selection align
	 */
	default Align getAlign() {
		return ClearSelection.DEFAULT_ALIGN;
	}

	/**
	 * Returns the clear selection render.
	 * 
	 * @return the clear selection render.
	 */
	default Render getRender() {
		return ClearSelection.DEFAULT_RENDER;
	}

	/**
	 * Returns the clear selection position.
	 * 
	 * @return the clear selection position
	 */
	default Position getPosition() {
		return ClearSelection.DEFAULT_POSITION;
	}

	/**
	 * Returns the clear selection image.
	 * 
	 * @return the clear selection image
	 */
	default Img getImage() {
		return ClearSelection.DEFAULT_IMAGE;
	}

	/**
	 * Returns the clear selection margin from canvas border.
	 * 
	 * @return the clear selection margin from canvas border
	 */
	default int getMargin() {
		return ClearSelection.DEFAULT_MARGIN;
	}

	/**
	 * Returns the clear selection padding.
	 * 
	 * @return the clear selection padding
	 */
	default int getPadding() {
		return ClearSelection.DEFAULT_PADDING;
	}

	/**
	 * Returns the spacing between label and image for clear selection element.
	 * 
	 * @return the spacing between label and image for clear selection element
	 */
	default int getSpacing() {
		return ClearSelection.DEFAULT_SPACING;
	}

	/**
	 * Returns <code>true</code> if clear selection element will apply style of selection area, otherwise <code>false</code>.
	 * 
	 * @return <code>true</code> if clear selection element will apply style of selection area, otherwise <code>false</code>
	 */
	default boolean isUseSelectionStyle() {
		return ClearSelection.DEFAULT_USE_SELECTION_STYLE;
	}

}
