/**
    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.
*/
package org.pepstock.charba.client.impl.plugins;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.defaults.IsDefaultFont;
import org.pepstock.charba.client.dom.elements.Img;
import org.pepstock.charba.client.enums.Position;
import org.pepstock.charba.client.impl.plugins.enums.Align;
import org.pepstock.charba.client.impl.plugins.enums.Render;

/**
 * Maps {@link DatasetsItemsSelector#ID} plugin default options for selection cleaner element.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
interface IsDatasetsItemsSelectorDefaultSelectionCleaner {

	/**
	 * Returns <code>true</code> if clear of selection label will be applied in the chart, otherwise <code>false</code>.
	 * 
	 * @return <code>true</code> if clear of selection label will be applied in the chart, otherwise <code>false</code>
	 */
	default boolean isDisplay() {
		return SelectionCleaner.DEFAULT_DISPLAY;
	}

	/**
	 * Returns the selection cleaner label.
	 * 
	 * @return the selection cleaner label
	 */
	default String getLabel() {
		return SelectionCleaner.DEFAULT_LABEL;
	}

	/**
	 * Returns the selection cleaner label font.
	 * 
	 * @return the selection cleaner label font
	 */
	default IsDefaultFont getFont() {
		return Defaults.get().getGlobal().getFont();
	}

	/**
	 * Returns the selection cleaner label font color.
	 * 
	 * @return the selection cleaner label font color.
	 */
	default String getColorAsString() {
		return DatasetsItemsSelectorOptions.DEFAULT_BORDER_COLOR_AS_STRING;
	}

	/**
	 * Returns the selection cleaner align.
	 * 
	 * @return the selection cleaner align
	 */
	default Align getAlign() {
		return SelectionCleaner.DEFAULT_ALIGN;
	}

	/**
	 * Returns the selection cleaner render.
	 * 
	 * @return the selection cleaner render.
	 */
	default Render getRender() {
		return SelectionCleaner.DEFAULT_RENDER;
	}

	/**
	 * Returns the selection cleaner position.
	 * 
	 * @return the selection cleaner position
	 */
	default Position getPosition() {
		return SelectionCleaner.DEFAULT_POSITION;
	}

	/**
	 * Returns the selection cleaner image.
	 * 
	 * @return the selection cleaner image
	 */
	default Img getImage() {
		return null;
	}

	/**
	 * Returns the selection cleaner margin from canvas border.
	 * 
	 * @return the selection cleaner margin from canvas border
	 */
	default int getMargin() {
		return SelectionCleaner.DEFAULT_MARGIN;
	}

	/**
	 * Returns the selection cleaner padding.
	 * 
	 * @return the selection cleaner padding
	 */
	default int getPadding() {
		return SelectionCleaner.DEFAULT_PADDING;
	}

	/**
	 * Returns the spacing between label and image for selection cleaner element.
	 * 
	 * @return the spacing between label and image for selection cleaner element
	 */
	default int getSpacing() {
		return SelectionCleaner.DEFAULT_SPACING;
	}

	/**
	 * Returns <code>true</code> if selection cleaner element will apply style of selection area, otherwise <code>false</code>.
	 * 
	 * @return <code>true</code> if selection cleaner element will apply style of selection area, otherwise <code>false</code>
	 */
	default boolean isUseSelectionStyle() {
		return SelectionCleaner.DEFAULT_USE_SELECTION_STYLE;
	}

}