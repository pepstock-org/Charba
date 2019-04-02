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

import org.pepstock.charba.client.commons.ArrayInteger;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;

/**
 * Configuration options of DEFAULT GLOBAL options of selection plugin.<br>
 * This is used to set the defaults to chart options for the plugin.<br>
 * It is managing:<br>
 * <ul>
 * <li>the X axis ID
 * <li>the selection color
 * <li>the border color
 * <li>the border width
 * <li>the border dash
 * <li>if firing event on clear selection
 * </ul>
 * 
 * @author Andrea "Stock" Stocchero
 */
final class DatasetsItemsSelectorDefaultsOptions extends NativeObjectContainer {

	// default clear selection options
	private final DatasetsItemsSelectorDefaultsClearSelection clearSelection;

	/**
	 * Builds the object using an empty java script object and uses the constants as defaults.
	 */
	DatasetsItemsSelectorDefaultsOptions() {
		super();
		// creates default clear selection options
		clearSelection = new DatasetsItemsSelectorDefaultsClearSelection();
		// sets the native object inside this object
		setValue(DatasetsItemsSelectorOptions.Property.clearSelection, clearSelection);
	}

	/**
	 * Builds the object using the java script object of options, set by user.<br>
	 * 
	 * @param nativeObject configuration of plugin.
	 */
	DatasetsItemsSelectorDefaultsOptions(NativeObject nativeObject) {
		super(nativeObject);
		// reads default clear selection options from main object
		clearSelection = new DatasetsItemsSelectorDefaultsClearSelection(getValue(DatasetsItemsSelectorOptions.Property.clearSelection));
	}

	/**
	 * Returns the clear selection item.
	 * 
	 * @return the clear selection item
	 */
	DatasetsItemsSelectorDefaultsClearSelection getClearSelection() {
		return clearSelection;
	}

	/**
	 * Returns the ID of the x axis to plot this dataset on. If not specified, this defaults to the ID of the first found x
	 * axis.
	 * 
	 * @return the ID of the x axis to plot this dataset on. If not specified, this defaults to the ID of the first found x
	 *         axis.
	 */
	String getXAxisID() {
		return getValue(DatasetsItemsSelectorOptions.Property.xAxisID, DatasetsItemsSelectorOptions.DEFAULT_AXIS_ID);
	}

	/**
	 * Returns the color.
	 * 
	 * @return the color.
	 */
	String getColorAsString() {
		return getValue(DatasetsItemsSelectorOptions.Property.color, DatasetsItemsSelectorOptions.DEFAULT_COLOR.toRGBA());
	}

	/**
	 * Returns the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of
	 * lines and gaps which describe the pattern.
	 * 
	 * @return the line dash pattern used when stroking lines.
	 */
	ArrayInteger getBorderDash() {
		return getArrayValue(DatasetsItemsSelectorOptions.Property.borderDash);
	}

	/**
	 * Returns the border width of the selection.
	 * 
	 * @return list of the border width of the selection.
	 */
	int getBorderWidth() {
		return getValue(DatasetsItemsSelectorOptions.Property.borderWidth, DatasetsItemsSelectorOptions.DEFAULT_BORDER_WIDTH);
	}

	/**
	 * Returns the color.
	 * 
	 * @return the color.
	 */
	String getBorderColorAsString() {
		return getValue(DatasetsItemsSelectorOptions.Property.borderColor, DatasetsItemsSelectorOptions.DEFAULT_BORDER_COLOR.toRGBA());
	}

	/**
	 * Returns <code>true</code> if it will fire event after clear of selection, otherwise <code>false</code>.
	 * 
	 * @return <code>true</code> if it will fire event after clear of selection, otherwise <code>false</code>
	 */
	boolean isFireEventOnClearSelection() {
		return getValue(DatasetsItemsSelectorOptions.Property.fireEventOnClearSelection, DatasetsItemsSelectorOptions.DEFAULT_FIRE_EVENT_ON_CLEAR_SELECTION);
	}

}
