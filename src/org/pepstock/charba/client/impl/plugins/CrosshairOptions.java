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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.HtmlColor;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.ArrayInteger;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.enums.DefaultEvent;
import org.pepstock.charba.client.enums.InteractionAxis;
import org.pepstock.charba.client.enums.ModifierKey;
import org.pepstock.charba.client.options.IsEvent;
import org.pepstock.charba.client.options.ScaleId;
import org.pepstock.charba.client.plugins.AbstractPluginOptions;

/**
 * Configuration options of {@link Crosshair#ID} plugin.<br>
 * It manages the drawing the horizontal and vertical crosshair on the chart.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class CrosshairOptions extends AbstractPluginOptions implements IsCrosshairDefaultOptions {

	/**
	 * Default enabled options, <b>{@value DEFAULT_ENABLED}</b>.
	 */
	public static final boolean DEFAULT_ENABLED = true;

	/**
	 * Default interaction axis mode, <b>{@link InteractionAxis#XY}</b>.
	 */
	public static final InteractionAxis DEFAULT_MODE = InteractionAxis.XY;

	/**
	 * Default line color, <b>{@link HtmlColor#LIGHT_GRAY}</b>.
	 */
	public static final IsColor DEFAULT_LINE_COLOR = HtmlColor.GRAY;

	/**
	 * Default line color as string, <b>{@link HtmlColor#LIGHT_GRAY}</b>.
	 */
	public static final String DEFAULT_LINE_COLOR_AS_STRING = DEFAULT_LINE_COLOR.toRGBA();

	/**
	 * Default line width, <b>{@value DEFAULT_LINE_WIDTH}</b>.
	 */
	public static final int DEFAULT_LINE_WIDTH = 1;

	/**
	 * Default line dash, <b>[2, 2]</b>.
	 */
	public static final List<Integer> DEFAULT_LINE_DASH = Collections.unmodifiableList(Arrays.asList(2, 2));

	/**
	 * Default line dash offset, <b>{@value DEFAULT_LINE_DASH_OFFSET}</b>.
	 */
	public static final double DEFAULT_LINE_DASH_OFFSET = 0D;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		ENABLED("enabled"),
		MODIFIER_KEY("modifierKey"),
		// inner elements
		X_LABEL("xLabel"),
		Y_LABEL("yLabel"),
		// options
		LINE_COLOR("lineColor"),
		LINE_DASH("lineDash"),
		LINE_DASH_OFFSET("lineDashOffset"),
		LINE_WIDTH("lineWidth"),
		MODE("mode"),
		X_SCALE_ID("xScaleID"),
		Y_SCALE_ID("yScaleID");

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

	// defaults global options instance
	private IsCrosshairDefaultOptions defaultOptions;
	// x label item
	private final CrosshairLabel xLabel;
	// y label item
	private final CrosshairLabel yLabel;
	// labels utility
	private final CrosshairLabels labels;

	/**
	 * Builds the object with new java script object setting the default value of plugin.<br>
	 * The global plugin options is used, if exists, as defaults values.
	 */
	public CrosshairOptions() {
		this(null, null);
	}

	/**
	 * Builds the object with a chart instance in order to get the right defaults.<br>
	 * If the plugin options have not been set by chart type, it will use the global.
	 * 
	 * @param chart chart instance related to the plugin options
	 */
	public CrosshairOptions(IsChart chart) {
		this(IsChart.isConsistent(chart) ? chart.getDefaultChartOptions().getPlugins().getOptions(Crosshair.ID, Crosshair.DEFAULTS_FACTORY) : null);
	}

	/**
	 * Builds the object with the default global ones
	 * 
	 * @param defaultOptions default options stored in the defaults global
	 */
	CrosshairOptions(IsCrosshairDefaultOptions defaultOptions) {
		this(defaultOptions, null);
	}

	/**
	 * Builds the object with a java script object stored in the options and the default global ones
	 * 
	 * @param nativeObject native object in the options
	 * @param defaultOptions default options stored in the defaults global
	 */
	CrosshairOptions(IsCrosshairDefaultOptions defaultOptions, NativeObject nativeObject) {
		super(Crosshair.ID, nativeObject);
		// checks if defaults options are consistent
		if (defaultOptions == null) {
			// reads the default default global options
			this.defaultOptions = loadGlobalsPluginOptions(Crosshair.DEFAULTS_FACTORY);
		} else {
			// stores default options
			this.defaultOptions = defaultOptions;
		}
		// sets inner elements
		// X LABEL
		this.xLabel = new CrosshairLabel(this, getValue(Property.X_LABEL), this.defaultOptions.getXLabel());
		// checks if x label is not already stored
		if (!has(Property.X_LABEL)) {
			// if here x label is not stored
			// then adds it
			setValue(Property.X_LABEL, this.xLabel);
		}
		// Y LABEL
		this.yLabel = new CrosshairLabel(this, getValue(Property.Y_LABEL), this.defaultOptions.getYLabel());
		// checks if y label is not already stored
		if (!has(Property.Y_LABEL)) {
			// if here y label is not stored
			// then adds it
			setValue(Property.Y_LABEL, this.yLabel);
		}
		// creates labels
		this.labels = new CrosshairLabels(this);
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
		// this is the only event that the plugin should receive from CHART.JS
		super.setEvents(DefaultEvent.MOUSE_MOVE);
	}

	/**
	 * Returns the labels utility to set the same value to all labels.
	 * 
	 * @return the labels utility to set the same value to all labels
	 */
	public CrosshairLabels getLabels() {
		return labels;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.impl.plugins.IsCrosshairDefaultOptions#getXLabel()
	 */
	@Override
	public CrosshairLabel getXLabel() {
		return xLabel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.impl.plugins.IsCrosshairDefaultOptions#getYLabel()
	 */
	@Override
	public CrosshairLabel getYLabel() {
		return yLabel;
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
	 * Sets the interaction axis mode.
	 * 
	 * @param mode the interaction axis mode
	 */
	public void setMode(InteractionAxis mode) {
		// interaction axis R is not supported
		if (!InteractionAxis.R.equals(mode)) {
			setValue(Property.MODE, mode);
		} else {
			// if here is null or R
			// then removes the property
			// using the default
			remove(Property.MODE);
		}
	}

	/**
	 * Returns the interaction axis mode.
	 * 
	 * @return the interaction axis mode
	 */
	@Override
	public InteractionAxis getMode() {
		return getValue(Property.MODE, InteractionAxis.values(), defaultOptions.getMode());
	}

	/**
	 * Sets the color of the line.
	 * 
	 * @param color the color of the line.
	 */
	public void setLineColor(IsColor color) {
		setLineColor(IsColor.checkAndGetValue(color));
	}

	/**
	 * Sets the color of the line.
	 * 
	 * @param color the color of the line.
	 */
	public void setLineColor(String color) {
		setValue(Property.LINE_COLOR, color);
	}

	/**
	 * Returns the color of the line.
	 * 
	 * @return the color of the line.
	 */
	@Override
	public String getLineColorAsString() {
		return getValue(Property.LINE_COLOR, defaultOptions.getLineColorAsString());
	}

	/**
	 * Returns the color of the line.
	 * 
	 * @return the color of the line.
	 */
	public IsColor getLineColor() {
		return ColorBuilder.parse(getLineColorAsString());
	}

	/**
	 * Sets the width of the line.
	 * 
	 * @param lineWidth the width of the line
	 */
	public void setLineWidth(int lineWidth) {
		setValue(Property.LINE_WIDTH, Checker.positiveOrZero(lineWidth));
	}

	/**
	 * Sets the width of the line.
	 * 
	 * @return the width of the line
	 */
	@Override
	public int getLineWidth() {
		return getValue(Property.LINE_WIDTH, defaultOptions.getLineWidth());
	}

	/**
	 * Sets the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 * 
	 * @param lineDash the line dash pattern used when stroking lines
	 */
	public void setLineDash(int... lineDash) {
		setArrayValue(Property.LINE_DASH, ArrayInteger.fromOrNull(lineDash));
	}

	/**
	 * Returns the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 * 
	 * @return the line dash pattern used when stroking lines
	 */
	@Override
	public List<Integer> getLineDash() {
		// checks if the value is stored
		if (has(Property.LINE_DASH)) {
			// gets array
			ArrayInteger array = getArrayValue(Property.LINE_DASH);
			// exists then returns the value
			return ArrayListHelper.list(array);
		}
		// if here, the options is missing
		// the returns the defaults.
		return defaultOptions.getLineDash();
	}

	/**
	 * Sets the line dash pattern offset.
	 * 
	 * @param lineDashOffset Offset for line dashes.
	 */
	public void setLineDashOffset(double lineDashOffset) {
		setValue(Property.LINE_DASH_OFFSET, lineDashOffset);
	}

	/**
	 * Returns the line dash pattern offset.
	 * 
	 * @return Offset for line dashes.
	 */
	@Override
	public double getLineDashOffset() {
		return getValue(Property.LINE_DASH_OFFSET, defaultOptions.getLineDashOffset());
	}

	/**
	 * Sets the ID of the X scale to bind onto.
	 * 
	 * @param scaleId the ID of the X scale to bind onto
	 */
	public final void setXScaleID(String scaleId) {
		// checks if scale id is valid
		ScaleId.checkIfValid(scaleId);
		// stores it
		setValue(Property.X_SCALE_ID, scaleId);
	}

	/**
	 * Sets the ID of the X scale to bind onto.
	 * 
	 * @param scaleId the ID of the X scale to bind onto
	 */
	public final void setXScaleID(ScaleId scaleId) {
		// checks if scale id is valid
		ScaleId.checkIfValid(scaleId);
		// stores it
		setValue(Property.X_SCALE_ID, scaleId);
	}

	/**
	 * Returns the ID of the X scale to bind onto.
	 * 
	 * @return the ID of the X scale to bind onto
	 */
	@Override
	public final ScaleId getXScaleID() {
		return getValue(Property.X_SCALE_ID, defaultOptions.getXScaleID());
	}

	/**
	 * Sets the ID of the Y scale to bind onto.
	 * 
	 * @param scaleId the ID of the Y scale to bind onto
	 */
	public final void setYScaleID(String scaleId) {
		// checks if scale id is valid
		ScaleId.checkIfValid(scaleId);
		// stores it
		setValue(Property.Y_SCALE_ID, scaleId);
	}

	/**
	 * Sets the ID of the Y scale to bind onto.
	 * 
	 * @param scaleId the ID of the Y scale to bind onto
	 */
	public final void setYScaleID(ScaleId scaleId) {
		// checks if scale id is valid
		ScaleId.checkIfValid(scaleId);
		// stores it
		setValue(Property.Y_SCALE_ID, scaleId);
	}

	/**
	 * Returns the ID of the Y scale to bind onto.
	 * 
	 * @return the ID of the Y scale to bind onto
	 */
	@Override
	public final ScaleId getYScaleID() {
		return getValue(Property.Y_SCALE_ID, defaultOptions.getYScaleID());
	}

	/**
	 * Sets the modifier key to activate the crosshair.
	 * 
	 * @param modifierKey the modifier key to activate the crosshair
	 */
	public void setModifierKey(ModifierKey modifierKey) {
		setValue(Property.MODIFIER_KEY, modifierKey);
	}

	/**
	 * Returns the modifier key to activate the crosshair.
	 * 
	 * @return the modifier key to activate the crosshair
	 */
	@Override
	public ModifierKey getModifierKey() {
		return getValue(Property.MODIFIER_KEY, ModifierKey.values(), defaultOptions.getModifierKey());
	}
}
