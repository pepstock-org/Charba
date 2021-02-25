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
package org.pepstock.charba.client.configuration;

import java.util.List;

import org.pepstock.charba.client.callbacks.ScaleBorderDashOffsetCallback;
import org.pepstock.charba.client.callbacks.ScaleColorCallback;
import org.pepstock.charba.client.callbacks.ScaleLineWidthCallback;
import org.pepstock.charba.client.callbacks.ScaleScriptableContext;
import org.pepstock.charba.client.callbacks.ScriptableFunctions;
import org.pepstock.charba.client.callbacks.ScriptableUtils;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;

/**
 * The grid line configuration defines options for the grid lines that run perpendicular to the axis.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class GridLines extends AbstractScaleLines {

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the tick color function
	private final CallbackProxy<ScriptableFunctions.ProxyObjectCallback> tickColorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the tick width function
	private final CallbackProxy<ScriptableFunctions.ProxyIntegerCallback> tickWidthCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the tick border dash offset function
	private final CallbackProxy<ScriptableFunctions.ProxyDoubleCallback> tickBorderDashOffsetCallbackProxy = JsHelper.get().newCallbackProxy();
	// color callback instance
	private ScaleColorCallback tickColorCallback = null;
	// tick line width callback instance
	private ScaleLineWidthCallback tickWidthCallback = null;
	// tick border dashoffset callback instance
	private ScaleBorderDashOffsetCallback tickBorderDashOffsetCallback = null;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		TICK_BORDER_DASH_OFFSET("tickBorderDashOffset"),
		TICK_COLOR("tickColor"),
		TICK_WIDTH("tickWidth");

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
	 * Builds the object storing the axis which this grid lines belongs to.
	 * 
	 * @param axis axis which this grid lines belongs to.
	 */
	GridLines(Axis axis) {
		super(axis, axis.getDefaultValues().getGridLines());
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// gets value calling callback
		tickColorCallbackProxy.setCallback(
				(contextFunction, context) -> ScriptableUtils.getOptionValueAsColor(getAxis(), new ScaleScriptableContext(new ConfigurationEnvelop<>(context)), tickColorCallback, getAxis().getDefaultValues().getGridLines().getTickColorAsString(), false));
		// gets value calling callback
		tickWidthCallbackProxy
				.setCallback((contextFunction, context) -> ScriptableUtils.getOptionValue(getAxis(), new ScaleScriptableContext(new ConfigurationEnvelop<>(context)), tickWidthCallback, getAxis().getDefaultValues().getGridLines().getTickWidth()).intValue());
		// gets value calling callback
		tickBorderDashOffsetCallbackProxy.setCallback((contextFunction, context) -> ScriptableUtils
				.getOptionValue(getAxis(), new ScaleScriptableContext(new ConfigurationEnvelop<>(context)), tickBorderDashOffsetCallback, getAxis().getDefaultValues().getGridLines().getTickBorderDashOffset()).doubleValue());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.AbstractScaleLines#getElement()
	 */
	@Override
	AbstractNode getElement() {
		return getAxis().getScale().getGridLines();
	}

	/**
	 * If false, do not display grid lines for this axis.
	 * 
	 * @param display If false, do not display grid lines for this axis.
	 */
	public void setDisplay(boolean display) {
		getAxis().getScale().getGridLines().setDisplay(display);
	}

	/**
	 * If false, do not display grid lines for this axis.
	 * 
	 * @return If false, do not display grid lines for this axis.
	 */
	public boolean isDisplay() {
		return getAxis().getScale().getGridLines().isDisplay();
	}

	/**
	 * If true, gridlines are circular (on radar chart only).
	 * 
	 * @param circular If true, gridlines are circular (on radar chart only).
	 */
	public void setCircular(boolean circular) {
		getAxis().getScale().getGridLines().setCircular(circular);
	}

	/**
	 * If true, grid lines are circular (on radar chart only).
	 * 
	 * @return If true, grid lines are circular (on radar chart only).
	 */
	public boolean isCircular() {
		return getAxis().getScale().getGridLines().isCircular();
	}

	/**
	 * The color of the grid lines.<br>
	 * If specified as an array, the first color applies to the first grid line, the second to the second grid line and so on.
	 * 
	 * @param color The color of the grid lines.<br>
	 *            If specified as an array, the first color applies to the first grid line, the second to the second grid line and so on.
	 */
	public void setColor(IsColor... color) {
		// reset callback if there is
		setColor((ScaleColorCallback) null);
		// stores value
		getAxis().getScale().getGridLines().setColor(color);
	}

	/**
	 * The color of the grid lines.<br>
	 * If specified as an array, the first color applies to the first grid line, the second to the second grid line and so on.
	 * 
	 * @param color The color of the grid lines.<br>
	 *            If specified as an array, the first color applies to the first grid line, the second to the second grid line and so on.
	 */
	public void setColor(String... color) {
		// reset callback if there is
		setColor((ScaleColorCallback) null);
		// stores value
		getAxis().getScale().getGridLines().setColor(color);
	}

	/**
	 * The color of the grid lines.<br>
	 * If specified as an array, the first color applies to the first grid line, the second to the second grid line and so on.
	 * 
	 * @return the list of colors of the grid lines.
	 */
	public List<String> getColorAsString() {
		return getAxis().getScale().getGridLines().getColorsAsString();
	}

	/**
	 * The color of the grid lines.<br>
	 * If specified as an array, the first color applies to the first grid line, the second to the second grid line and so on.
	 * 
	 * @return the list of colors of the grid lines.
	 */
	public List<IsColor> getColor() {
		return getAxis().getScale().getGridLines().getColor();
	}

	/**
	 * If set, used as the color of the border line.<br>
	 * If unset, the first color option is resolved and used.
	 * 
	 * @param color if set, used as the color of the border line.<br>
	 *            If unset, the first color option is resolved and used.
	 */
	public void setBorderColor(IsColor color) {
		getAxis().getScale().getGridLines().setBorderColor(color);
	}

	/**
	 * If set, used as the color of the border line.<br>
	 * If unset, the first color option is resolved and used.
	 * 
	 * @param color if set, used as the color of the border line.<br>
	 *            If unset, the first color option is resolved and used.
	 */
	public void setBorderColor(String color) {
		getAxis().getScale().getGridLines().setBorderColor(color);
	}

	/**
	 * If set, used as the color of the border line.<br>
	 * If unset, the first color option is resolved and used.
	 * 
	 * @return if set, used as the color of the border line.<br>
	 *         If unset, the first color option is resolved and used.
	 */
	public String getBorderColorAsString() {
		return getAxis().getScale().getGridLines().getBorderColorAsString();
	}

	/**
	 * If set, used as the color of the border line.<br>
	 * If unset, the first color option is resolved and used.
	 * 
	 * @return if set, used as the color of the border line.<br>
	 *         If unset, the first color option is resolved and used.
	 */
	public IsColor getBorderColor() {
		return getAxis().getScale().getGridLines().getBorderColor();
	}

	/**
	 * If set, used as the width of the border line.<br>
	 * If unset, the first lineWidth option is resolved and used.
	 * 
	 * @param borderWidth if set, used as the width of the border line.<br>
	 *            If unset, the first lineWidth option is resolved and used.
	 */
	public void setBorderWidth(int borderWidth) {
		getAxis().getScale().getGridLines().setBorderWidth(borderWidth);
	}

	/**
	 * If set, used as the width of the border line.<br>
	 * If unset, the first lineWidth option is resolved and used.
	 * 
	 * @return if set, used as the width of the border line.<br>
	 *         If unset, the first lineWidth option is resolved and used.
	 */
	public int getBorderWidth() {
		return getAxis().getScale().getGridLines().getBorderWidth();
	}

	/**
	 * Sets the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 * 
	 * @param borderDash the line dash pattern used when stroking lines
	 */
	public void setBorderDash(int... borderDash) {
		getAxis().getScale().getGridLines().setBorderDash(borderDash);
	}

	/**
	 * Returns the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 * 
	 * @return the line dash pattern used when stroking lines.
	 */
	public List<Integer> getBorderDash() {
		return getAxis().getScale().getGridLines().getBorderDash();
	}

	/**
	 * Sets the line dash pattern offset.
	 * 
	 * @param borderDashOffset Offset for line dashes.
	 */
	public void setBorderDashOffset(double borderDashOffset) {
		// reset callback if there is
		setBorderDashOffset((ScaleBorderDashOffsetCallback) null);
		// stores value
		getAxis().getScale().getGridLines().setBorderDashOffset(borderDashOffset);
	}

	/**
	 * Returns the line dash pattern offset.
	 * 
	 * @return Offset for line dashes.
	 */
	public double getBorderDashOffset() {
		return getAxis().getScale().getGridLines().getBorderDashOffset();
	}

	/**
	 * Sets the stroke widths of grid lines.
	 * 
	 * @param lineWidth stroke widths of grid lines.
	 */
	public void setLineWidth(int... lineWidth) {
		// reset callback if there is
		setLineWidth((ScaleLineWidthCallback) null);
		// stores value
		getAxis().getScale().getGridLines().setLineWidth(lineWidth);
	}

	/**
	 * Returns the stroke widths of grid lines.
	 * 
	 * @return lineWidth stroke widths of grid lines.
	 */
	public List<Integer> getLineWidth() {
		return getAxis().getScale().getGridLines().getLinesWidth();
	}

	/***
	 * If true, draw border at the edge between the axis and the chart area.
	 * 
	 * @param drawBorder If true, draw border at the edge between the axis and the chart area.
	 */
	public void setDrawBorder(boolean drawBorder) {
		getAxis().getScale().getGridLines().setDrawBorder(drawBorder);
	}

	/**
	 * If true, draw border at the edge between the axis and the chart area.
	 * 
	 * @return If true, draw border at the edge between the axis and the chart area.
	 */
	public boolean isDrawBorder() {
		return getAxis().getScale().getGridLines().isDrawBorder();
	}

	/**
	 * If true, draw lines on the chart area inside the axis lines.<br>
	 * This is useful when there are multiple axes and you need to control which grid lines are drawn.
	 * 
	 * @param drawOnChartArea If true, draw lines on the chart area inside the axis lines.<br>
	 *            This is useful when there are multiple axes and you need to control which grid lines are drawn.
	 */
	public void setDrawOnChartArea(boolean drawOnChartArea) {
		getAxis().getScale().getGridLines().setDrawOnChartArea(drawOnChartArea);
	}

	/**
	 * If true, draw lines on the chart area inside the axis lines.<br>
	 * This is useful when there are multiple axes and you need to control which grid lines are drawn.
	 * 
	 * @return If true, draw lines on the chart area inside the axis lines.<br>
	 *         This is useful when there are multiple axes and you need to control which grid lines are drawn.
	 */
	public boolean isDrawOnChartArea() {
		return getAxis().getScale().getGridLines().isDrawOnChartArea();
	}

	/**
	 * If true, draw lines beside the ticks in the axis area beside the chart.
	 * 
	 * @param drawTicks If true, draw lines beside the ticks in the axis area beside the chart.
	 */
	public void setDrawTicks(boolean drawTicks) {
		getAxis().getScale().getGridLines().setDrawTicks(drawTicks);
	}

	/**
	 * If true, draw lines beside the ticks in the axis area beside the chart.
	 * 
	 * @return If true, draw lines beside the ticks in the axis area beside the chart.
	 */
	public boolean isDrawTicks() {
		return getAxis().getScale().getGridLines().isDrawTicks();
	}

	/**
	 * Sets the length in pixels that the grid lines will draw into the axis area.
	 * 
	 * @param tickLength Length in pixels that the grid lines will draw into the axis area.
	 */
	public void setTickLength(int tickLength) {
		getAxis().getScale().getGridLines().setTickLength(tickLength);
	}

	/**
	 * Returns the length in pixels that the grid lines will draw into the axis area.
	 * 
	 * @return Length in pixels that the grid lines will draw into the axis area.
	 */
	public int getTickLength() {
		return getAxis().getScale().getGridLines().getTickLength();
	}

	/**
	 * If true, grid lines will be shifted to be between labels.<br>
	 * This is set to true in the bar chart by default.
	 * 
	 * @param offsetGridLines If true, grid lines will be shifted to be between labels.
	 */
	public void setOffsetGridLines(boolean offsetGridLines) {
		getAxis().getScale().getGridLines().setOffsetGridLines(offsetGridLines);
	}

	/**
	 * If true, grid lines will be shifted to be between labels.<br>
	 * This is set to true in the bar chart by default.
	 * 
	 * @return If true, grid lines will be shifted to be between labels.
	 */
	public boolean isOffsetGridLines() {
		return getAxis().getScale().getGridLines().isOffsetGridLines();
	}

	/**
	 * Sets z-index of grid line layer.<br>
	 * Values less than or equals to 0 are drawn under datasets, greater than 0 on top.
	 * 
	 * @param z z-index of grid line layer.<br>
	 *            Values less than or equals to 0 are drawn under datasets, greater than 0 on top.
	 */
	public void setZ(int z) {
		getAxis().getScale().getGridLines().setZ(z);
	}

	/**
	 * Returns z-index of grid line layer.<br>
	 * Values less than or equals to 0 are drawn under datasets, greater than 0 on top.
	 * 
	 * @return z-index of grid line layer.<br>
	 *         Values less than or equals to 0 are drawn under datasets, greater than 0 on top.
	 */
	public int getZ() {
		return getAxis().getScale().getGridLines().getZ();
	}

	/**
	 * Sets the length and spacing of the tick mark line.
	 * 
	 * @param tickBorderDash the length and spacing of the tick mark line.
	 */
	public void setTickBorderDash(int tickBorderDash) {
		getAxis().getScale().getGridLines().setTickBorderDash(tickBorderDash);
	}

	/**
	 * Returns the length and spacing of the tick mark line.
	 * 
	 * @return the length and spacing of the tick mark line.
	 */
	public List<Integer> getTickBorderDash() {
		return getAxis().getScale().getGridLines().getTickBorderDash();
	}

	/**
	 * Sets the offset for the line dash of the tick mark.
	 * 
	 * @param tickBorderDashOffset the offset for the line dash of the tick mark
	 */
	public void setTickBorderDashOffset(double tickBorderDashOffset) {
		// reset callback if there is
		setTickBorderDashOffset((ScaleBorderDashOffsetCallback) null);
		// stores value
		getAxis().getScale().getGridLines().setTickBorderDashOffset(tickBorderDashOffset);
	}

	/**
	 * Returns the offset for the line dash of the tick mark.
	 * 
	 * @return the offset for the line dash of the tick mark
	 */
	public double getTickBorderDashOffset() {
		return getAxis().getScale().getGridLines().getTickBorderDashOffset();
	}

	/**
	 * Sets the color of the tick line.
	 * 
	 * @param color the color of the tick line.
	 */
	public void setTickColor(IsColor... color) {
		// reset callback if there is
		setTickColor((ScaleColorCallback) null);
		// stores value
		getAxis().getScale().getGridLines().setTickColor(color);
	}

	/**
	 * Sets the color of the tick line.
	 * 
	 * @param color the color of the tick line.
	 */
	public void setTickColor(String... color) {
		// reset callback if there is
		setTickColor((ScaleColorCallback) null);
		// stores value
		getAxis().getScale().getGridLines().setTickColor(color);
	}

	/**
	 * Returns the color of the tick line.
	 * 
	 * @return the color of the tick line.
	 */
	public String getTickColorAsString() {
		return getAxis().getScale().getGridLines().getTickColorAsString();
	}

	/**
	 * The color of the grid lines.<br>
	 * If specified as an array, the first color applies to the first grid line, the second to the second grid line and so on.
	 * 
	 * @return the list of colors of the grid lines.
	 */
	public List<String> getTickColorsAsString() {
		return getAxis().getScale().getGridLines().getTickColorsAsString();
	}

	/**
	 * The color of the grid lines.<br>
	 * If specified as an array, the first color applies to the first grid line, the second to the second grid line and so on.
	 * 
	 * @return the list of colors of the grid lines.
	 */
	public List<IsColor> getTickColor() {
		return getAxis().getScale().getGridLines().getTickColor();
	}

	/**
	 * Sets the width of the tick marks in pixels.
	 * 
	 * @param tickWidth the width of the tick mark in pixels
	 */
	public void setTickWidth(int... tickWidth) {
		// reset callback if there is
		setTickWidth((ScaleLineWidthCallback) null);
		// stores value
		getAxis().getScale().getGridLines().setTickWidth(tickWidth);
	}

	/**
	 * Returns the width of the tick mark in pixels.<br>
	 * The first element if set as array.
	 * 
	 * @return stroke width of grid lines.<br>
	 *         The first element if set as array.
	 */
	public int getTickWidth() {
		return getAxis().getScale().getGridLines().getTickWidth();
	}

	/**
	 * Returns the width of the tick marks in pixels.
	 * 
	 * @return stroke widths of grid lines.
	 */
	public List<Integer> getTicksWidth() {
		return getAxis().getScale().getGridLines().getTicksWidth();
	}

	/**
	 * Returns the tick color callback instance.
	 * 
	 * @return the tick color callback instance
	 */
	public ScaleColorCallback getTickColorCallback() {
		return tickColorCallback;
	}

	/**
	 * Sets the tick color callback instance.
	 * 
	 * @param tickColorCallback the tick color callback instance
	 */
	public void setTickColor(ScaleColorCallback tickColorCallback) {
		// stores callback
		this.tickColorCallback = tickColorCallback;
		// checks if consistent
		if (tickColorCallback != null) {
			// adds the callback proxy function to java script object
			getAxis().getConfiguration().setCallback(getAxis().getScale().getGridLines(), Property.TICK_COLOR, tickColorCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			getAxis().getConfiguration().setCallback(getAxis().getScale().getGridLines(), Property.TICK_COLOR, null);
		}
	}

	/**
	 * Returns the tick width callback instance.
	 * 
	 * @return the tick width callback instance
	 */
	public ScaleLineWidthCallback getTickWidthCallback() {
		return tickWidthCallback;
	}

	/**
	 * Sets the tick width callback instance.
	 * 
	 * @param tickWidthCallback the tick width callback instance.
	 */
	public void setTickWidth(ScaleLineWidthCallback tickWidthCallback) {
		// stores callback
		this.tickWidthCallback = tickWidthCallback;
		// checks if consistent
		if (tickWidthCallback != null) {
			// adds the callback proxy function to java script object
			getAxis().getConfiguration().setCallback(getAxis().getScale().getGridLines(), Property.TICK_WIDTH, tickWidthCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			getAxis().getConfiguration().setCallback(getAxis().getScale().getGridLines(), Property.TICK_WIDTH, null);
		}
	}

	/**
	 * Returns the tick border dash offset callback instance.
	 * 
	 * @return the tick border dash offset callback instance
	 */
	public ScaleBorderDashOffsetCallback getTickBorderDashOffsetCallback() {
		return tickBorderDashOffsetCallback;
	}

	/**
	 * Sets the tick border dash offset callback instance.
	 * 
	 * @param tickBorderDashOffsetCallback the tick border dash offset callback instance
	 */
	public void setTickBorderDashOffset(ScaleBorderDashOffsetCallback tickBorderDashOffsetCallback) {
		// stores callback
		this.tickBorderDashOffsetCallback = tickBorderDashOffsetCallback;
		// checks if consistent
		if (tickBorderDashOffsetCallback != null) {
			// adds the callback proxy function to java script object
			getAxis().getConfiguration().setCallback(getAxis().getScale().getGridLines(), Property.TICK_BORDER_DASH_OFFSET, tickBorderDashOffsetCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			getAxis().getConfiguration().setCallback(getAxis().getScale().getGridLines(), Property.TICK_BORDER_DASH_OFFSET, null);
		}
	}
}