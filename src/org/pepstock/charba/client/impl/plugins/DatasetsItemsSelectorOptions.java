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

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.GwtMaterialColor;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.ArrayInteger;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.enums.DefaultScaleId;
import org.pepstock.charba.client.options.IsScaleId;
import org.pepstock.charba.client.plugins.AbstractPluginOptions;

/**
 * Configuration options of {@link DatasetsItemsSelector#ID} plugin.<br>
 * It is managing:<br>
 * <ul>
 * <li>the X axis ID
 * <li>the selection color
 * <li>the border color
 * <li>the border width
 * <li>the border dash
 * <li>the border dash offset
 * <li>"clear" options
 * </ul>
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DatasetsItemsSelectorOptions extends AbstractPluginOptions {

	/**
	 * Default alpha of selecting/selection colors, <b>{@value DEFAULT_ALPHA}</b>.
	 */
	public static final double DEFAULT_ALPHA = 0.3D;

	/**
	 * Default color for area, {@link GwtMaterialColor#ORANGE_LIGHTEN_3}, alpha <b>{@value DEFAULT_ALPHA}</b>.
	 */
	public static final IsColor DEFAULT_COLOR = GwtMaterialColor.ORANGE_LIGHTEN_3.alpha(DEFAULT_ALPHA);

	/**
	 * Default X axis id, {@link DefaultScaleId#X}.
	 */
	public static final DefaultScaleId DEFAULT_AXIS_ID = DefaultScaleId.X;

	/**
	 * Default border width of selection area, <b>{@value DEFAULT_BORDER_WIDTH}</b>.
	 */
	public static final int DEFAULT_BORDER_WIDTH = 0;

	/**
	 * Default border dash offset of selection area, <b>{@value DEFAULT_BORDER_DASH_OFFSET}</b>.
	 */
	public static final int DEFAULT_BORDER_DASH_OFFSET = 0;

	/**
	 * Default border color for area, {@link GwtMaterialColor#GREY_DARKEN_2}.
	 */
	public static final IsColor DEFAULT_BORDER_COLOR = GwtMaterialColor.GREY_DARKEN_2;

	// defaults global options instance
	private DatasetsItemsSelectorDefaultsOptions defaultsOptions;
	// clear selection item
	private final ClearSelection clearSelection;

	/**
	 * Name of properties of native object.
	 */
	enum Property implements Key
	{
		COLOR("color"),
		X_AXIS_ID("xAxisID"),
		BORDER_COLOR("borderColor"),
		BORDER_DASH("borderDash"),
		BORDER_DASH_OFFSET("borderDashOffset"),
		BORDER_WIDTH("borderWidth"),
		CLEAR_SELECTION("clearSelection");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use into native object.
		 * 
		 * @param value value of property name
		 */
		private Property(String value) {
			this.value = value;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.Key#value()
		 */
		@Override
		public String value() {
			return value;
		}

	}

	/**
	 * Builds the object with new java script object setting the default value of plugin.<br>
	 * The global plugin options is used, if exists, as defaults values.
	 */
	public DatasetsItemsSelectorOptions() {
		this(null, null);
	}

	/**
	 * Builds the object with a chart instance in order to get the right defaults.<br>
	 * If the plugin options have not been set by chart type, it will use the global.
	 * 
	 * @param chart chart instance related to the plugin options
	 */
	public DatasetsItemsSelectorOptions(IsChart chart) {
		this(IsChart.isConsistent(chart) ? chart.getDefaultChartOptions().getPlugins().getOptions(DatasetsItemsSelector.ID, DatasetsItemsSelector.DEFAULTS_FACTORY) : null);
	}

	/**
	 * Builds the object using the java script object of options and the defaults, set by user.<br>
	 * Used internally to call the plugin.
	 * 
	 * @param defaultsOptions default options, which must be stored into default global.
	 */
	DatasetsItemsSelectorOptions(DatasetsItemsSelectorDefaultsOptions defaultsOptions) {
		this(null, defaultsOptions);
	}

	/**
	 * Builds the object using the java script object of options and the defaults, set by user.<br>
	 * Used internally to call the plugin.
	 * 
	 * @param nativeObject configuration of plugin.
	 * @param defaultsOptions default options, which must be stored into default global.
	 */
	DatasetsItemsSelectorOptions(NativeObject nativeObject, DatasetsItemsSelectorDefaultsOptions defaultsOptions) {
		super(DatasetsItemsSelector.ID, nativeObject);
		// checks if defaults options are consistent
		if (defaultsOptions == null) {
			// reads the default default global options
			this.defaultsOptions = loadGlobalsPluginOptions(DatasetsItemsSelector.DEFAULTS_FACTORY);
		} else {
			// stores default options
			this.defaultsOptions = defaultsOptions;
		}
		// sets inner elements
		clearSelection = new ClearSelection(getValue(Property.CLEAR_SELECTION), this.defaultsOptions.getClearSelection());
		// checks if clear selection is not already stored
		if (!has(Property.CLEAR_SELECTION)) {
			// if here clear selection is not stored
			// then adds it
			setValue(Property.CLEAR_SELECTION, clearSelection);
		}
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
	 * @param xAxisID the ID of the x axis to plot this dataset on. If not specified, this defaults to the ID of the first found x axis.
	 */
	public void setXAxisID(String xAxisID) {
		// checks if is valid scale id
		IsScaleId.checkIfValid(xAxisID);
		// stores
		setValue(Property.X_AXIS_ID, xAxisID);
	}

	/**
	 * Sets the ID of the x axis to plot this dataset on. If not specified, this defaults to the ID of the first found x axis.
	 * 
	 * @param xAxisID the ID of the x axis to plot this dataset on. If not specified, this defaults to the ID of the first found x axis.
	 */
	public void setXAxisID(IsScaleId xAxisID) {
		// checks if is valid scale id
		IsScaleId.checkIfValid(xAxisID);
		// stores
		setValue(Property.X_AXIS_ID, xAxisID);
	}

	/**
	 * Returns the ID of the x axis to plot this dataset on. If not specified, this defaults to the ID of the first found x axis.
	 * 
	 * @return the ID of the x axis to plot this dataset on. If not specified, this defaults to the ID of the first found x axis.
	 */
	public IsScaleId getXAxisID() {
		return getValue(Property.X_AXIS_ID, defaultsOptions.getXAxisID());
	}

	/**
	 * Returns the color.
	 * 
	 * @return the color.
	 */
	public String getColorAsString() {
		return getValue(Property.COLOR, defaultsOptions.getColorAsString());
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
		setValue(Property.COLOR, color);
	}

	/**
	 * Sets the color.
	 * 
	 * @param color the color.
	 */
	public void setColor(IsColor color) {
		setColor(IsColor.checkAndGetValue(color));
	}

	/**
	 * Sets the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 * 
	 * @param borderDash the line dash pattern used when stroking lines
	 */
	public void setBorderDash(int... borderDash) {
		setArrayValue(Property.BORDER_DASH, ArrayInteger.fromOrNull(borderDash));
	}

	/**
	 * Returns the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 * 
	 * @return the line dash pattern used when stroking lines.
	 */
	public List<Integer> getBorderDash() {
		// gets array instance
		ArrayInteger array = getArrayValue(Property.BORDER_DASH);
		return ArrayListHelper.list(array);
	}

	/**
	 * Sets the line dash pattern offset or "phase".
	 * 
	 * @param borderDashOffset the line dash pattern offset or "phase".
	 */
	public void setBorderDashOffset(int borderDashOffset) {
		setValue(Property.BORDER_DASH_OFFSET, borderDashOffset);
	}

	/**
	 * Returns the line dash pattern offset or "phase".
	 * 
	 * @return the line dash pattern offset or "phase".
	 */
	public int getBorderDashOffset() {
		return getValue(Property.BORDER_DASH_OFFSET, defaultsOptions.getBorderDashOffset());
	}

	/**
	 * Sets the border width of the selection.
	 * 
	 * @param borderWidth the border width of the selection.
	 */
	public void setBorderWidth(int borderWidth) {
		setValue(Property.BORDER_WIDTH, borderWidth);
	}

	/**
	 * Returns the border width of the selection.
	 * 
	 * @return list of the border width of the selection.
	 */
	public int getBorderWidth() {
		return getValue(Property.BORDER_WIDTH, defaultsOptions.getBorderWidth());
	}

	/**
	 * Returns the color.
	 * 
	 * @return the color.
	 */
	public String getBorderColorAsString() {
		return getValue(Property.BORDER_COLOR, defaultsOptions.getBorderColorAsString());
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
		setValue(Property.BORDER_COLOR, color);
	}

	/**
	 * Sets the color.
	 * 
	 * @param color the color.
	 */
	public void setBorderColor(IsColor color) {
		setBorderColor(IsColor.checkAndGetValue(color));
	}

}
