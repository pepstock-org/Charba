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

import java.util.List;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.ArrayInteger;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.impl.plugins.DatasetsItemsSelectorOptionsFactory.DatasetsItemsSelectorDefaultsOptionsFactory;

/**
 * Configuration options of selection plugin.<br>
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
public final class DatasetsItemsSelectorOptions extends NativeObjectContainer {

	// defaults global options instance
	private DatasetsItemsSelectorDefaultsOptions defaultsOptions;
	// defaults global options factory
	private final DatasetsItemsSelectorDefaultsOptionsFactory defaultsFactory = new DatasetsItemsSelectorDefaultsOptionsFactory();
	// clear selection item
	private final ClearSelection clearSelection;

	/**
	 * Name of properties of native object.
	 */
	enum Property implements Key
	{
		color,
		xAxisID,
		borderColor,
		borderDash,
		borderWidth,
		fireEventOnClearSelection,
		clearSelection
	}

	/**
	 * Builds the object with a new java script object setting the default value of plugin.
	 */
	public DatasetsItemsSelectorOptions() {
		super(null);
		// this constructor is used by user to set options for plugin
		// both default global or chart one.
		// checks if the default global options has been added for the plugin
		if (Defaults.get().getGlobal().getPlugins().hasOptions(DatasetsItemsSelector.ID)) {
			// reads the default default global options
			defaultsOptions = Defaults.get().getGlobal().getPlugins().getOptions(DatasetsItemsSelector.ID, defaultsFactory);
		} else {
			// if here, no default global option
			// then the plugin will use the static defaults
			defaultsOptions = new DatasetsItemsSelectorDefaultsOptions();
		}
		// sets inner elements
		clearSelection = new ClearSelection(defaultsOptions.getClearSelection());
		// stores inner elements
		setValue(Property.clearSelection, clearSelection);
	}

	/**
	 * Builds the object using the java script object of options and the defaults, set by user.<br>
	 * Used internally to call the plugin.
	 * 
	 * @param nativeObject configuration of plugin.
	 * @param defaultsOptions default options, which must be stored into default global.
	 */
	DatasetsItemsSelectorOptions(NativeObject nativeObject, DatasetsItemsSelectorDefaultsOptions defaultsOptions) {
		super(nativeObject);
		this.defaultsOptions = defaultsOptions;
		// sets inner elements
		clearSelection = new ClearSelection(getValue(Property.clearSelection), defaultsOptions.getClearSelection());
	}

	/**
	 * Returns the clear selection element.
	 * 
	 * @return the clear selection element
	 */
	public ClearSelection getClearSelection() {
		return clearSelection;
	}

	/**
	 * Sets the ID of the x axis to plot this dataset on. If not specified, this defaults to the ID of the first found x axis.
	 * 
	 * @param xAxisID the ID of the x axis to plot this dataset on. If not specified, this defaults to the ID of the first found
	 *            x axis.
	 */
	public void setXAxisID(String xAxisID) {
		setValue(Property.xAxisID, xAxisID);
	}

	/**
	 * Returns the ID of the x axis to plot this dataset on. If not specified, this defaults to the ID of the first found x
	 * axis.
	 * 
	 * @return the ID of the x axis to plot this dataset on. If not specified, this defaults to the ID of the first found x
	 *         axis.
	 */
	public String getXAxisID() {
		return getValue(Property.xAxisID, defaultsOptions.getXAxisID());
	}

	/**
	 * Returns the color.
	 * 
	 * @return the color.
	 */
	public String getColorAsString() {
		return getValue(Property.color, defaultsOptions.getColorAsString());
	}

	/**
	 * Returns the color.
	 * 
	 * @return the color.
	 */
	public IsColor getColor() {
		return ColorBuilder.parse(getColorAsString());
	}

	/**
	 * Sets the color.
	 * 
	 * @param color the color.
	 */
	public void setColor(String color) {
		setValue(Property.color, color);
	}

	/**
	 * Sets the color.
	 * 
	 * @param color the color.
	 */
	public void setColor(IsColor color) {
		setColor(color.toRGBA());
	}

	/**
	 * Sets the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines
	 * and gaps which describe the pattern.
	 * 
	 * @param borderDash the line dash pattern used when stroking lines
	 */
	public void setBorderDash(int... borderDash) {
		setArrayValue(Property.borderDash, ArrayInteger.fromOrNull(borderDash));
	}

	/**
	 * Returns the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of
	 * lines and gaps which describe the pattern.
	 * 
	 * @return the line dash pattern used when stroking lines.
	 */
	public List<Integer> getBorderDash() {
		return ArrayListHelper.list(getBorderDashAsJavaScriptObject());
	}

	/**
	 * Returns the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of
	 * lines and gaps which describe the pattern.
	 * 
	 * @return the line dash pattern used when stroking lines.
	 */
	ArrayInteger getBorderDashAsJavaScriptObject() {
		// creates array instance
		ArrayInteger array = null;
		// checks if there is the property set
		if (has(Property.borderDash)) {
			// returns array
			array = getArrayValue(Property.borderDash);
		} else {
			// returns default
			array = defaultsOptions.getBorderDash();
		}
		return array;
	}

	/**
	 * Sets the border width of the selection.
	 * 
	 * @param borderWidth the border width of the selection.
	 */
	public void setBorderWidth(int borderWidth) {
		setValue(Property.borderWidth, borderWidth);
	}

	/**
	 * Returns the border width of the selection.
	 * 
	 * @return list of the border width of the selection.
	 */
	public int getBorderWidth() {
		return getValue(Property.borderWidth, defaultsOptions.getBorderWidth());
	}

	/**
	 * Returns the color.
	 * 
	 * @return the color.
	 */
	public String getBorderColorAsString() {
		return getValue(Property.borderColor, defaultsOptions.getBorderColorAsString());
	}

	/**
	 * Returns the color.
	 * 
	 * @return the color.
	 */
	public IsColor getBorderColor() {
		return ColorBuilder.parse(getColorAsString());
	}

	/**
	 * Sets the color.
	 * 
	 * @param color the color.
	 */
	public void setBorderColor(String color) {
		setValue(Property.borderColor, color);
	}

	/**
	 * Sets the color.
	 * 
	 * @param color the color.
	 */
	public void setBorderColor(IsColor color) {
		setBorderColor(color.toRGBA());
	}

	/**
	 * Sets <code>true</code> if it will fire event after clear of selection, otherwise <code>false</code>.
	 * 
	 * @param fireEvent <code>true</code> if it will fire event after clear of selection, otherwise <code>false</code>
	 */
	public void setFireEventOnClearSelection(boolean fireEvent) {
		setValue(Property.fireEventOnClearSelection, fireEvent);
	}

	/**
	 * Returns <code>true</code> if it will fire event after clear of selection, otherwise <code>false</code>.
	 * 
	 * @return <code>true</code> if it will fire event after clear of selection, otherwise <code>false</code>
	 */
	public boolean isFireEventOnClearSelection() {
		return getValue(Property.fireEventOnClearSelection, defaultsOptions.isFireEventOnClearSelection());
	}

	/**
	 * Returns the java script object of this options.
	 * 
	 * @return the java script object of this options.
	 */
	public NativeObject getObject() {
		return super.getNativeObject();
	}

}
