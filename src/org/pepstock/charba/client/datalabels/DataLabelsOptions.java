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
package org.pepstock.charba.client.datalabels;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.datalabels.enums.Align;
import org.pepstock.charba.client.datalabels.enums.Anchor;
import org.pepstock.charba.client.datalabels.enums.TextAlign;
import org.pepstock.charba.client.enums.Display;

/**
 * This is the {@link DataLabelsPlugin#ID} plugin options where to set all the configuration needed to the plugin.<br>
 * The options could be set by simply the value or by setting a callback.<br>
 * The {@link DataLabelsPlugin#ID} plugin is highly customizable CHART.JS plugin that displays labels on data for any type of charts.<br>
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class DataLabelsOptions extends LabelItem implements IsDefaultDataLabelsOptions {

	// -------------------------------------------
	// -- DEFAULTS VALUES of DATALABELS PLUGIN ---
	// -------------------------------------------

	/**
	 * Default position of the label relative to the anchor point position and orientation, {@link Align#CENTER}.
	 */
	public static final Align DEFAULT_ALIGN = Align.CENTER;

	/**
	 * Default anchor point, which is defined by an orientation vector and a position on the data element, {@link Anchor#CENTER}.
	 */
	public static final Anchor DEFAULT_ANCHOR = Anchor.CENTER;

	/**
	 * Default background color, <code>null</code>, and uses the background color of dataset.
	 */
	public static final String DEFAULT_BACKGROUND_COLOR = null;

	/**
	 * Default border color, <code>null</code>, and uses the border color of dataset.
	 */
	public static final String DEFAULT_BORDER_COLOR = null;

	/**
	 * Default border radius of labels, <b>{@value DEFAULT_BORDER_RADIUS}</b>.
	 */
	public static final double DEFAULT_BORDER_RADIUS = 0D;

	/**
	 * Default border width of labels, <b>{@value DEFAULT_BORDER_WIDTH}</b>.
	 */
	public static final int DEFAULT_BORDER_WIDTH = 0;

	/**
	 * Default to enforce the anchor position to be calculated based on the visible geometry of the associated element, <b>{@value DEFAULT_CLAMP}</b>.
	 */
	public static final boolean DEFAULT_CLAMP = false;

	/**
	 * Default to enforce the part of the label which is outside the chart area will be masked, <b>{@value DEFAULT_CLIP}</b>.
	 */
	public static final boolean DEFAULT_CLIP = false;

	/**
	 * Default visibility of labels, {@link Display#TRUE}.
	 */
	public static final Display DEFAULT_DISPLAY = Display.TRUE;

	/**
	 * Default distance (in pixels) to pull the label away from the anchor point, <b>{@value DEFAULT_OFFSET}</b>.
	 */
	public static final double DEFAULT_OFFSET = 4D;

	/**
	 * Default opacity, <b>{@value DEFAULT_OPACITY}</b>.
	 */
	public static final double DEFAULT_OPACITY = 1D;

	/**
	 * Default clockwise rotation angle (in degrees) of the label, <b>{@value DEFAULT_ROTATION}</b>.
	 */
	public static final double DEFAULT_ROTATION = 0D;

	/**
	 * Default text alignment being used when drawing the label text, {@link TextAlign#START}.
	 */
	public static final TextAlign DEFAULT_TEXT_ALIGN = TextAlign.START;

	/**
	 * Default text stroke width, <b>{@value DEFAULT_TEXT_STROKE_WIDTH}</b>.
	 */
	public static final int DEFAULT_TEXT_STROKE_WIDTH = 0;

	/**
	 * Default text shadow blur, <b>{@value DEFAULT_TEXT_SHADOW_BLUR}</b>.
	 */
	public static final double DEFAULT_TEXT_SHADOW_BLUR = 0D;
	// labels inner element
	private final Labels labels;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		LABELS("labels");

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
	 * Creates new {@link DataLabelsPlugin#ID} plugin options.
	 */
	public DataLabelsOptions() {
		this(null, null);
	}

	/**
	 * Creates new {@link DataLabelsPlugin#ID} plugin options, relating to chart instance for default.
	 * 
	 * @param chart chart instance related to the plugin options
	 */
	public DataLabelsOptions(IsChart chart) {
		this(IsChart.isConsistent(chart) ? chart.getDefaultChartOptions().getPlugins().getOptions(DataLabelsPlugin.ID, DataLabelsPlugin.DEFAULTS_FACTORY) : null, null);
	}

	/**
	 * Creates new {@link DataLabelsPlugin#ID} plugin options.
	 * 
	 * @param defaultOptions default options stored in the defaults global
	 * @param nativeObject native object which represents the plugin options as native object
	 */
	DataLabelsOptions(IsDefaultDataLabelsOptions defaultOptions, NativeObject nativeObject) {
		// creates an empty native object
		super(defaultOptions, nativeObject);
		// gets default
		IsDefaultDataLabelsItem defaultValue = getDefaultsOptions();
		// checks instance of defaults options if has got the right type
		if (defaultValue instanceof IsDefaultDataLabelsOptions) {
			// casts to the right type
			IsDefaultDataLabelsOptions values = (IsDefaultDataLabelsOptions) defaultValue;
			// creates and stores labels
			this.labels = new Labels(this, values.getLabels(), getValue(Property.LABELS));
			// checks it has got the element
			if (!has(Property.LABELS)) {
				// stores labels
				setValue(Property.LABELS, labels);
			}
		} else {
			// if here, the default does not have the same class
			// which is needed for this object
			throw new IllegalArgumentException("The default instance is not correct type, " + defaultValue.getClass().getName());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.datalabels.LabelItem#applyingDefaults()
	 */
	@Override
	protected void applyingDefaults() {
		DataLabelsPlugin.get().mergeDefaults(this);
	}

	/**
	 * Returns the labels element.
	 * 
	 * @return the labels element.
	 */
	@Override
	public Labels getLabels() {
		return labels;
	}

}
