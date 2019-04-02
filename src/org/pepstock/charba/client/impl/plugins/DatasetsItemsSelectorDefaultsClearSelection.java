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
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.enums.Position;
import org.pepstock.charba.client.impl.plugins.enums.Align;
import org.pepstock.charba.client.impl.plugins.enums.Render;

import com.google.gwt.dom.client.ImageElement;

/**
 * {@link DatasetsItemsSelector#ID} plugin default options for CLEAR SELECTION element.<br>
 * It contains all default values for CLEAR Selection.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class DatasetsItemsSelectorDefaultsClearSelection extends NativeObjectContainer {

	/**
	 * Creates the object with an empty native object instance.
	 */
	DatasetsItemsSelectorDefaultsClearSelection() {
		super();
	}

	/**
	 * Creates the object with native object instance to be wrapped.
	 * 
	 * @param nativeObject native object instance to be wrapped.
	 */
	DatasetsItemsSelectorDefaultsClearSelection(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns <code>true</code> if clear of selection label will be applied into chart, otherwise <code>false</code>.
	 * 
	 * @return <code>true</code> if clear of selection label will be applied into chart, otherwise <code>false</code>
	 */
	boolean isDisplay() {
		return getValue(ClearSelection.Property.display, ClearSelection.DEFAULT_DISPLAY);
	}

	/**
	 * Returns the clear selection label.
	 * 
	 * @return the clear selection label
	 */
	String getLabel() {
		return getValue(ClearSelection.Property.label, ClearSelection.DEFAULT_LABEL);
	}

	/**
	 * Returns the clear selection label font size.
	 * 
	 * @return the clear selection label font size
	 */
	int getFontSize() {
		return getValue(ClearSelection.Property.fontSize, Defaults.get().getGlobal().getDefaultFontSize());
	}

	/**
	 * Returns the clear selection label font family.
	 * 
	 * @return the clear selection label font family
	 */
	String getFontFamily() {
		return getValue(ClearSelection.Property.fontFamily, Defaults.get().getGlobal().getDefaultFontFamily());
	}

	/**
	 * Returns the clear selection label font style.
	 * 
	 * @return the clear selection label font style
	 */
	FontStyle getFontStyle() {
		return getValue(ClearSelection.Property.fontStyle, FontStyle.class, Defaults.get().getGlobal().getDefaultFontStyle());
	}

	/**
	 * Returns the clear selection label font color.
	 * 
	 * @return the clear selection label font color.
	 */
	String getFontColorAsString() {
		return getValue(ClearSelection.Property.fontColor, DatasetsItemsSelectorOptions.DEFAULT_BORDER_COLOR.toRGBA());
	}

	/**
	 * Returns the clear selection align.
	 * 
	 * @return the clear selection align
	 */
	Align getAlign() {
		return getValue(ClearSelection.Property.align, Align.class, ClearSelection.DEFAULT_ALIGN);
	}

	/**
	 * Returns the clear selection render.
	 * 
	 * @return the clear selection render.
	 */
	Render getRender() {
		return getValue(ClearSelection.Property.render, Render.class, ClearSelection.DEFAULT_RENDER);
	}

	/**
	 * Returns the clear selection position.
	 * 
	 * @return the clear selection position
	 */
	Position getPosition() {
		return getValue(ClearSelection.Property.position, Position.class, ClearSelection.DEFAULT_POSITION);
	}

	/**
	 * Returns the clear selection image.
	 * 
	 * @return the clear selection image
	 */
	ImageElement getImage() {
		return getValue(ClearSelection.Property.image, ClearSelection.DEFAULT_IMAGE);
	}

	/**
	 * Returns the clear selection margin from canvas border.
	 * 
	 * @return the clear selection margin from canvas border
	 */
	int getMargin() {
		return getValue(ClearSelection.Property.margin, ClearSelection.DEFAULT_MARGIN);
	}

	/**
	 * Returns the clear selection padding.
	 * 
	 * @return the clear selection padding
	 */
	int getPadding() {
		return getValue(ClearSelection.Property.padding, ClearSelection.DEFAULT_PADDING);
	}

	/**
	 * Returns the spacing between label and image for clear selection element.
	 * 
	 * @return the spacing between label and image for clear selection element
	 */
	int getSpacing() {
		return getValue(ClearSelection.Property.spacing, ClearSelection.DEFAULT_SPACING);
	}

	/**
	 * Returns <code>true</code> if clear selection element will apply style of selection area, otherwise <code>false</code>.
	 * 
	 * @return <code>true</code> if clear selection element will apply style of selection area, otherwise <code>false</code>
	 */
	boolean isUseSelectionStyle() {
		return getValue(ClearSelection.Property.useSelectionStyle, ClearSelection.DEFAULT_USE_SELECTION_STYLE);
	}

}
