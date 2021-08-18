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

import java.util.Collections;
import java.util.List;

import org.pepstock.charba.client.enums.ModifierKey;
import org.pepstock.charba.client.options.ScaleId;

/**
 * Maps all configuration options of DEFAULT GLOBAL options of {@link DatasetsItemsSelector#ID} plugin.
 * 
 * @author Andrea "Stock" Stocchero
 */
interface IsDatasetsItemsSelectorDefaultOptions {

	/**
	 * Returns the selection cleaner item.
	 * 
	 * @return the selection cleaner item
	 */
	IsDatasetsItemsSelectorDefaultSelectionCleaner getSelectionCleaner();

	/**
	 * Returns <code>true</code> if plugin is enabled.
	 * 
	 * @return <code>true</code> if plugin is enabled.
	 */
	default boolean isEnabled() {
		return DatasetsItemsSelectorOptions.DEFAULT_ENABLED;
	}

	/**
	 * Returns the ID of the x axis to plot this dataset on.<br>
	 * If not specified, this defaults to the ID of the first found x axis.
	 * 
	 * @return the ID of the x axis to plot this dataset on.<br>
	 *         If not specified, this defaults to the ID of the first found x axis.
	 */
	default ScaleId getXAxisID() {
		return DatasetsItemsSelectorOptions.DEFAULT_AXIS_ID;
	}

	/**
	 * Returns the color.
	 * 
	 * @return the color.
	 */
	default String getColorAsString() {
		return DatasetsItemsSelectorOptions.DEFAULT_COLOR.toRGBA();
	}

	/**
	 * Returns the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 * 
	 * @return the line dash pattern used when stroking lines.
	 */
	default List<Integer> getBorderDash() {
		return Collections.emptyList();
	}

	/**
	 * Returns the line dash pattern offset.
	 * 
	 * @return the line dash pattern offset.
	 */
	default double getBorderDashOffset() {
		return DatasetsItemsSelectorOptions.DEFAULT_BORDER_DASH_OFFSET;
	}

	/**
	 * Returns the border width of the selection.
	 * 
	 * @return list of the border width of the selection.
	 */
	default int getBorderWidth() {
		return DatasetsItemsSelectorOptions.DEFAULT_BORDER_WIDTH;
	}

	/**
	 * Returns the color.
	 * 
	 * @return the color.
	 */
	default String getBorderColorAsString() {
		return DatasetsItemsSelectorOptions.DEFAULT_BORDER_COLOR.toRGBA();
	}
	
	/**
	 * Returns the modifier key to activate selection.
	 * 
	 * @return the modifier key to activate selection
	 */
	default ModifierKey getModifierKey() {
		return null;
	}

}
