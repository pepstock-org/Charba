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

import java.util.List;
import java.util.Set;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.GwtMaterialColor;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.ArrayInteger;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.dom.enums.KeyboardUiKey;
import org.pepstock.charba.client.dom.enums.MouseEventType;
import org.pepstock.charba.client.dom.enums.TouchEventType;
import org.pepstock.charba.client.enums.DefaultScaleId;
import org.pepstock.charba.client.enums.ModifierKey;
import org.pepstock.charba.client.items.Undefined;
import org.pepstock.charba.client.options.IsEvent;
import org.pepstock.charba.client.options.ScaleId;
import org.pepstock.charba.client.plugins.AbstractPluginOptions;

/**
 * Configuration options of {@link DatasetsItemsSelector#ID} plugin.<br>
 * It is managing:<br>
 * <ul>
 * <li>the X axis ID
 * <li>the Y axis ID
 * <li>the selection color
 * <li>the border color
 * <li>the border width
 * <li>the border dash
 * <li>the border dash offset
 * <li>flag to fire event on update
 * <li>flag to activate clean selection by {@link KeyboardUiKey#ESCAPE}
 * <li>"clear" options
 * </ul>
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DatasetsItemsSelectorOptions extends AbstractPluginOptions implements IsDatasetsItemsSelectorDefaultOptions {

	/**
	 * Default enabled options, <b>{@value DEFAULT_ENABLED}</b>.
	 */
	public static final boolean DEFAULT_ENABLED = true;

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
	public static final double DEFAULT_BORDER_DASH_OFFSET = 0D;

	/**
	 * Default border color for area, {@link GwtMaterialColor#GREY_DARKEN_2}.
	 */
	public static final IsColor DEFAULT_BORDER_COLOR = GwtMaterialColor.GREY_DARKEN_2;

	/**
	 * Default flag option to activate clear selection by {@link KeyboardUiKey#ESCAPE}, <b>{@value DEFAULT_CLEAR_BY_ESCAPE_ENABLED}</b>.
	 */
	public static final boolean DEFAULT_CLEAR_BY_ESCAPE_ENABLED = true;

	// default border color for area as string.
	static final String DEFAULT_BORDER_COLOR_AS_STRING = DEFAULT_BORDER_COLOR.toRGBA();

	// defaults global options instance
	private IsDatasetsItemsSelectorDefaultOptions defaultOptions;
	// selection cleaner item
	private final SelectionCleaner selectionCleaner;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		ENABLED("enabled"),
		COLOR("color"),
		CLEAR_BY_ESC_ENABLED("enabledClearByEscape"),
		X_AXIS_ID("xAxisID"),
		Y_AXIS_ID("yAxisID"),
		BORDER_COLOR("borderColor"),
		BORDER_DASH("borderDash"),
		BORDER_DASH_OFFSET("borderDashOffset"),
		BORDER_WIDTH("borderWidth"),
		MODIFIER_KEY("modifierKey"),
		SELECTION_CLEANER("selectionCleaner");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use in the native object.
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
	 * @param defaultOptions default options, which must be stored in the default global.
	 */
	DatasetsItemsSelectorOptions(IsDatasetsItemsSelectorDefaultOptions defaultOptions) {
		this(defaultOptions, null);
	}

	/**
	 * Builds the object using the java script object of options and the defaults, set by user.<br>
	 * Used internally to call the plugin.
	 * 
	 * @param defaultOptions default options, which must be stored in the default global.
	 * @param nativeObject configuration of plugin.
	 */
	DatasetsItemsSelectorOptions(IsDatasetsItemsSelectorDefaultOptions defaultOptions, NativeObject nativeObject) {
		super(DatasetsItemsSelector.ID, nativeObject);
		// checks if defaults options are consistent
		if (defaultOptions == null) {
			// reads the default default global options
			this.defaultOptions = loadGlobalsPluginOptions(DatasetsItemsSelector.DEFAULTS_FACTORY);
		} else {
			// stores default options
			this.defaultOptions = defaultOptions;
		}
		// sets inner elements
		this.selectionCleaner = new SelectionCleaner(getValue(Property.SELECTION_CLEANER), this.defaultOptions.getSelectionCleaner());
		// checks if selection cleaner is not already stored
		if (!has(Property.SELECTION_CLEANER)) {
			// if here selection cleaner is not stored
			// then adds it
			setValue(Property.SELECTION_CLEANER, this.selectionCleaner);
		}
		// sets default events
		setEvents((Set<IsEvent>) null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.plugins.AbstractPluginOptions#setEvents(org.pepstock.charba.client.enums.Event[])
	 */
	@Override
	public void setEvents(IsEvent... events) {
		setEvents((Set<IsEvent>) null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.plugins.AbstractPluginOptions#setEvents(java.util.Set)
	 */
	@Override
	public void setEvents(Set<IsEvent> events) {
		// these are the only events that the plugin should receive from CHART.JS
		// MOUSE MOVE is added even if not managed because this enables the usage
		// of other plugins with this one
		super.setEvents(MouseEventType.CLICK, TouchEventType.TOUCH_START, MouseEventType.MOUSE_MOVE);
	}

	/**
	 * Returns the selection cleaner element.
	 * 
	 * @return the selection cleaner element
	 */
	@Override
	public SelectionCleaner getSelectionCleaner() {
		return selectionCleaner;
	}

	/**
	 * Sets <code>true</code> if plugin is enabled.
	 * 
	 * @param enabled <code>true</code> if plugin is enabled.
	 */
	public void setEnabled(boolean enabled) {
		setValue(Property.ENABLED, enabled);
	}

	/**
	 * Returns <code>true</code> if plugin is enabled.
	 * 
	 * @return <code>true</code> if plugin is enabled.
	 */
	@Override
	public boolean isEnabled() {
		return getValue(Property.ENABLED, defaultOptions.isEnabled());
	}

	/**
	 * Sets <code>true</code> if you want to clear selection by {@link KeyboardUiKey#ESCAPE}.
	 * 
	 * @param enabled <code>true</code> if you want to clear selection by {@link KeyboardUiKey#ESCAPE}.
	 */
	public void setEnabledClearByEscape(boolean enabled) {
		setValue(Property.CLEAR_BY_ESC_ENABLED, enabled);
	}

	/**
	 * Returns <code>true</code> if you want to clear selection by {@link KeyboardUiKey#ESCAPE}.
	 * 
	 * @return <code>true</code> if you want to clear selection by {@link KeyboardUiKey#ESCAPE}.
	 */
	@Override
	public boolean isEnabledClearByEscape() {
		return getValue(Property.CLEAR_BY_ESC_ENABLED, defaultOptions.isEnabledClearByEscape());
	}

	/**
	 * Sets the ID of the x axis to plot this dataset on. If not specified, this defaults to the ID of the first found x axis.
	 * 
	 * @param xAxisID the ID of the x axis to plot this dataset on. If not specified, this defaults to the ID of the first found x axis.
	 */
	public void setXAxisID(String xAxisID) {
		// checks if is valid scale id
		ScaleId.checkIfValid(xAxisID);
		// stores
		setValue(Property.X_AXIS_ID, xAxisID);
	}

	/**
	 * Sets the ID of the x axis to plot this dataset on. If not specified, this defaults to the ID of the first found x axis.
	 * 
	 * @param xAxisID the ID of the x axis to plot this dataset on. If not specified, this defaults to the ID of the first found x axis.
	 */
	public void setXAxisID(ScaleId xAxisID) {
		// checks if is valid scale id
		ScaleId.checkIfValid(xAxisID);
		// stores
		setValue(Property.X_AXIS_ID, xAxisID);
	}

	/**
	 * Returns the ID of the x axis to plot this dataset on. If not specified, this defaults to the ID of the first found x axis.
	 * 
	 * @return the ID of the x axis to plot this dataset on. If not specified, this defaults to the ID of the first found x axis.
	 */
	@Override
	public ScaleId getXAxisID() {
		return getValue(Property.X_AXIS_ID, defaultOptions.getXAxisID());
	}

	/**
	 * Sets the ID of the y axis to plot this dataset on. If not specified, the chart area is used.
	 * 
	 * @param yAxisID the ID of the y axis to plot this dataset on. If not specified, the chart area is used.
	 */
	public void setYAxisID(String yAxisID) {
		// checks if is valid scale id
		ScaleId.checkIfValid(yAxisID);
		// stores
		setValue(Property.Y_AXIS_ID, yAxisID);
	}

	/**
	 * Sets the ID of the y axis to plot this dataset on. If not specified, the chart area is used.
	 * 
	 * @param yAxisID the ID of the y axis to plot this dataset on. If not specified, the chart area is used.
	 */
	public void setYAxisID(ScaleId yAxisID) {
		// checks if is valid scale id
		ScaleId.checkIfValid(yAxisID);
		// stores
		setValue(Property.Y_AXIS_ID, yAxisID);
	}

	/**
	 * Returns the ID of the y axis to plot this dataset on. If not specified, the chart area is used.
	 * 
	 * @return the ID of the y axis to plot this dataset on. If not specified, the chart area is used.
	 */
	@Override
	public ScaleId getYAxisID() {
		// gets value as string
		String value = getValue(Property.Y_AXIS_ID, Undefined.STRING);
		// checks if consistent
		if (value != null) {
			return ScaleId.checkAndGetScaleID(value, defaultOptions.getYAxisID());
		}
		// if here, the value is not consistent
		// then returns null
		return null;
	}

	/**
	 * Returns the color.
	 * 
	 * @return the color.
	 */
	@Override
	public String getColorAsString() {
		return getValue(Property.COLOR, defaultOptions.getColorAsString());
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
	@Override
	public List<Integer> getBorderDash() {
		// gets array instance
		ArrayInteger array = getArrayValue(Property.BORDER_DASH);
		return ArrayListHelper.list(array);
	}

	/**
	 * Sets the line dash pattern offset.
	 * 
	 * @param borderDashOffset the line dash pattern offset.
	 */
	public void setBorderDashOffset(double borderDashOffset) {
		setValue(Property.BORDER_DASH_OFFSET, borderDashOffset);
	}

	/**
	 * Returns the line dash pattern offset.
	 * 
	 * @return the line dash pattern offset.
	 */
	@Override
	public double getBorderDashOffset() {
		return getValue(Property.BORDER_DASH_OFFSET, defaultOptions.getBorderDashOffset());
	}

	/**
	 * Sets the border width of the selection.
	 * 
	 * @param borderWidth the border width of the selection.
	 */
	public void setBorderWidth(int borderWidth) {
		setValue(Property.BORDER_WIDTH, Checker.positiveOrZero(borderWidth));
	}

	/**
	 * Returns the border width of the selection.
	 * 
	 * @return list of the border width of the selection.
	 */
	@Override
	public int getBorderWidth() {
		return getValue(Property.BORDER_WIDTH, defaultOptions.getBorderWidth());
	}

	/**
	 * Returns the color.
	 * 
	 * @return the color.
	 */
	@Override
	public String getBorderColorAsString() {
		return getValue(Property.BORDER_COLOR, defaultOptions.getBorderColorAsString());
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

	/**
	 * Sets the modifier key to activate selection.
	 * 
	 * @param modifierKey the modifier key to activate selection
	 */
	public void setModifierKey(ModifierKey modifierKey) {
		setValue(Property.MODIFIER_KEY, modifierKey);
	}

	/**
	 * Returns the modifier key to activate selection.
	 * 
	 * @return the modifier key to activate selection
	 */
	@Override
	public ModifierKey getModifierKey() {
		return getValue(Property.MODIFIER_KEY, ModifierKey.values(), defaultOptions.getModifierKey());
	}

}