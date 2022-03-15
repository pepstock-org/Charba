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

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.callbacks.CrosshairFormatterCallback;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.AbstractBaseBuilder;
import org.pepstock.charba.client.commons.IsBuilder;
import org.pepstock.charba.client.enums.InteractionAxis;
import org.pepstock.charba.client.enums.ModifierKey;
import org.pepstock.charba.client.options.ScaleId;

/**
 * Comfortable object to create {@link Crosshair#ID} plugin options by a builder.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class CrosshairOptionsBuilder extends AbstractBaseBuilder {

	// plugin options instance
	private CrosshairOptions options;

	/**
	 * To avoid any instantiation
	 * 
	 * @param chart chart instance related to the plugin options
	 */
	private CrosshairOptionsBuilder(IsChart chart) {
		this.options = new CrosshairOptions(chart);
	}

	/**
	 * Returns new builder instance.
	 * 
	 * @return new builder instance
	 */
	public static CrosshairOptionsBuilder create() {
		return create(null);
	}

	/**
	 * Returns new builder instance using the chart global options.
	 * 
	 * @param chart chart instance related to the plugin options
	 * @return new builder instance
	 */
	public static CrosshairOptionsBuilder create(IsChart chart) {
		return new CrosshairOptionsBuilder(chart);
	}

	/**
	 * Returns a configured plugin options.
	 * 
	 * @return a configured plugin options.
	 */
	public CrosshairOptions build() {
		// sets built status
		setBuilt(true);
		// returns options
		return options;
	}

	/**
	 * Sets <code>true</code> if plugin is enabled.
	 * 
	 * @param enabled <code>true</code> if plugin is enabled.
	 * @return new builder instance
	 */
	public CrosshairOptionsBuilder setEnabled(boolean enabled) {
		options.setEnabled(enabled);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the interaction axis mode.
	 * 
	 * @param mode the interaction axis mode
	 * @return new builder instance
	 */
	public CrosshairOptionsBuilder setMode(InteractionAxis mode) {
		options.setMode(mode);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the color of the line.
	 * 
	 * @param color the color of the line.
	 * @return new builder instance
	 */
	public CrosshairOptionsBuilder setLineColor(IsColor color) {
		options.setLineColor(color);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the color of the line.
	 * 
	 * @param color the color of the line.
	 * @return new builder instance
	 */
	public CrosshairOptionsBuilder setLineColor(String color) {
		options.setLineColor(color);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the width of the line.
	 * 
	 * @param lineWidth the width of the line
	 * @return new builder instance
	 */
	public CrosshairOptionsBuilder setLineWidth(int lineWidth) {
		options.setLineWidth(lineWidth);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 * 
	 * @param lineDash the line dash pattern used when stroking lines
	 * @return new builder instance
	 */
	public CrosshairOptionsBuilder setLineDash(int... lineDash) {
		options.setLineDash(lineDash);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the line dash pattern offset.
	 * 
	 * @param lineDashOffset Offset for line dashes.
	 * @return new builder instance
	 */
	public CrosshairOptionsBuilder setLineDashOffset(double lineDashOffset) {
		options.setLineDashOffset(lineDashOffset);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the ID of the X scale to bind onto.
	 * 
	 * @param scaleId the ID of the X scale to bind onto
	 * @return new builder instance
	 */
	public CrosshairOptionsBuilder setXScaleID(String scaleId) {
		options.setXScaleID(scaleId);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the ID of the X scale to bind onto.
	 * 
	 * @param scaleId the ID of the X scale to bind onto
	 * @return new builder instance
	 */
	public CrosshairOptionsBuilder setXScaleID(ScaleId scaleId) {
		options.setXScaleID(scaleId);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the ID of the Y scale to bind onto.
	 * 
	 * @param scaleId the ID of the Y scale to bind onto
	 * @return new builder instance
	 */
	public CrosshairOptionsBuilder setYScaleID(String scaleId) {
		options.setYScaleID(scaleId);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the ID of the Y scale to bind onto.
	 * 
	 * @param scaleId the ID of the Y scale to bind onto
	 * @return new builder instance
	 */
	public CrosshairOptionsBuilder setYScaleID(ScaleId scaleId) {
		options.setYScaleID(scaleId);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets the modifier key to activate the crosshair.
	 * 
	 * @param modifierKey the modifier key to activate the crosshair
	 * @return new builder instance
	 */
	public CrosshairOptionsBuilder setModifierKey(ModifierKey modifierKey) {
		options.setModifierKey(modifierKey);
		return IsBuilder.checkAndGetIfValid(this);
	}

	// ------------------------
	// X LABEL
	// ------------------------

	/**
	 * Sets, for X label, <code>true</code> if label will be applied in the chart, otherwise <code>false</code>.
	 * 
	 * @param display <code>true</code> if label will be applied in the chart, otherwise <code>false</code>
	 * @return new builder instance
	 */
	public CrosshairOptionsBuilder setXLabelDisplay(boolean display) {
		options.getXLabel().setDisplay(display);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets, for X label, the crosshair label font color.
	 * 
	 * @param color the crosshair label font color.
	 * @return new builder instance
	 */
	public CrosshairOptionsBuilder setXLabelColor(String color) {
		options.getXLabel().setColor(color);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Set the crosshair label font color.
	 * 
	 * @param color the crosshair label font color.
	 * @return new builder instance
	 */
	public CrosshairOptionsBuilder setXLabelColor(IsColor color) {
		options.getXLabel().setColor(color);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets, for X label, the padding of crosshair label.
	 * 
	 * @param padding padding of crosshair label
	 * @return new builder instance
	 */
	public CrosshairOptionsBuilder setXLabelPadding(int padding) {
		options.getXLabel().setPadding(padding);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets, for X label, the background color.
	 * 
	 * @param color the background color.
	 * @return new builder instance
	 */
	public CrosshairOptionsBuilder setXLabelBackgroundColor(String color) {
		options.getXLabel().setBackgroundColor(color);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets, for X label, the background color.
	 * 
	 * @param color the background color.
	 * @return new builder instance
	 */
	public CrosshairOptionsBuilder setXLabelBackgroundColor(IsColor color) {
		options.getXLabel().setBackgroundColor(color);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets, for X label, the label border radius (in pixels).
	 * 
	 * @param borderRadius the label border radius (in pixels).
	 * @return new builder instance
	 */
	public CrosshairOptionsBuilder setXLabelBorderRadius(int borderRadius) {
		options.getXLabel().setBorderRadius(borderRadius);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets, for X label, the callback which can be implemented to change the text of label.
	 * 
	 * @param formatterCallback the callback which can be implemented to change the text of label
	 * @return new builder instance
	 */
	public CrosshairOptionsBuilder setXLabelFormatter(CrosshairFormatterCallback formatterCallback) {
		options.getXLabel().setFormatter(formatterCallback);
		return IsBuilder.checkAndGetIfValid(this);
	}

	// ------------------------
	// Y LABEL
	// ------------------------

	/**
	 * Sets, for Y label, <code>true</code> if label will be applied in the chart, otherwise <code>false</code>.
	 * 
	 * @param display <code>true</code> if label will be applied in the chart, otherwise <code>false</code>
	 * @return new builder instance
	 */
	public CrosshairOptionsBuilder setYLabelDisplay(boolean display) {
		options.getYLabel().setDisplay(display);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets, for Y label, the crosshair label font color.
	 * 
	 * @param color the crosshair label font color.
	 * @return new builder instance
	 */
	public CrosshairOptionsBuilder setYLabelColor(String color) {
		options.getYLabel().setColor(color);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Set the crosshair label font color.
	 * 
	 * @param color the crosshair label font color.
	 * @return new builder instance
	 */
	public CrosshairOptionsBuilder setYLabelColor(IsColor color) {
		options.getYLabel().setColor(color);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets, for Y label, the padding of crosshair label.
	 * 
	 * @param padding padding of crosshair label
	 * @return new builder instance
	 */
	public CrosshairOptionsBuilder setYLabelPadding(int padding) {
		options.getYLabel().setPadding(padding);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets, for Y label, the background color.
	 * 
	 * @param color the background color.
	 * @return new builder instance
	 */
	public CrosshairOptionsBuilder setYLabelBackgroundColor(String color) {
		options.getYLabel().setBackgroundColor(color);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets, for Y label, the background color.
	 * 
	 * @param color the background color.
	 * @return new builder instance
	 */
	public CrosshairOptionsBuilder setYLabelBackgroundColor(IsColor color) {
		options.getYLabel().setBackgroundColor(color);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets, for Y label, the label border radius (in pixels).
	 * 
	 * @param borderRadius the label border radius (in pixels).
	 * @return new builder instance
	 */
	public CrosshairOptionsBuilder setYLabelBorderRadius(int borderRadius) {
		options.getYLabel().setBorderRadius(borderRadius);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets, for Y label, the callback which can be implemented to change the text of label.
	 * 
	 * @param formatterCallback the callback which can be implemented to change the text of label
	 * @return new builder instance
	 */
	public CrosshairOptionsBuilder setYLabelFormatter(CrosshairFormatterCallback formatterCallback) {
		options.getYLabel().setFormatter(formatterCallback);
		return IsBuilder.checkAndGetIfValid(this);
	}

	// ------------------------
	// BOTH LABELS
	// ------------------------

	/**
	 * Sets, for both labels, <code>true</code> if label will be applied in the chart, otherwise <code>false</code>.
	 * 
	 * @param display <code>true</code> if label will be applied in the chart, otherwise <code>false</code>
	 * @return new builder instance
	 */
	public CrosshairOptionsBuilder setLabelsDisplay(boolean display) {
		options.getLabels().setDisplay(display);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets, for both labels, the crosshair label font color.
	 * 
	 * @param color the crosshair label font color.
	 * @return new builder instance
	 */
	public CrosshairOptionsBuilder setLabelsColor(String color) {
		options.getLabels().setColor(color);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Set the crosshair label font color.
	 * 
	 * @param color the crosshair label font color.
	 * @return new builder instance
	 */
	public CrosshairOptionsBuilder setLabelsColor(IsColor color) {
		options.getLabels().setColor(color);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets, for both labels, the padding of crosshair label.
	 * 
	 * @param padding padding of crosshair label
	 * @return new builder instance
	 */
	public CrosshairOptionsBuilder setLabelsPadding(int padding) {
		options.getLabels().setPadding(padding);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets, for both labels, the background color.
	 * 
	 * @param color the background color.
	 * @return new builder instance
	 */
	public CrosshairOptionsBuilder setLabelsBackgroundColor(String color) {
		options.getLabels().setBackgroundColor(color);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets, for both labels, the background color.
	 * 
	 * @param color the background color.
	 * @return new builder instance
	 */
	public CrosshairOptionsBuilder setLabelsBackgroundColor(IsColor color) {
		options.getLabels().setBackgroundColor(color);
		return IsBuilder.checkAndGetIfValid(this);
	}

	/**
	 * Sets, for both labels, the label border radius (in pixels).
	 * 
	 * @param borderRadius the label border radius (in pixels).
	 * @return new builder instance
	 */
	public CrosshairOptionsBuilder setLabelsBorderRadius(int borderRadius) {
		options.getLabels().setBorderRadius(borderRadius);
		return IsBuilder.checkAndGetIfValid(this);
	}

}
