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
package org.pepstock.charba.client.configuration;

import java.util.List;

import org.pepstock.charba.client.callbacks.BorderDashOffsetCallback;
import org.pepstock.charba.client.callbacks.ColorCallback;
import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.callbacks.ScaleContext;
import org.pepstock.charba.client.callbacks.ScriptableDoubleChecker;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyDoubleCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyIntegerCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyObjectCallback;
import org.pepstock.charba.client.callbacks.ScriptableIntegerChecker;
import org.pepstock.charba.client.callbacks.ScriptableUtil;
import org.pepstock.charba.client.callbacks.WidthCallback;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;

/**
 * The grid line configuration defines options for the grid that run perpendicular to the axis.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class Grid extends AbstractScaleLines {

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the tick color function
	private final CallbackProxy<ProxyObjectCallback> tickColorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the tick width function
	private final CallbackProxy<ProxyIntegerCallback> tickWidthCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the tick border dash offset function
	private final CallbackProxy<ProxyDoubleCallback> tickBorderDashOffsetCallbackProxy = JsHelper.get().newCallbackProxy();

	// color callback instance
	private ColorCallback<ScaleContext> tickColorCallback = null;
	// tick line width callback instance
	private WidthCallback<ScaleContext> tickWidthCallback = null;
	// tick border dash offset callback instance
	private BorderDashOffsetCallback<ScaleContext> tickBorderDashOffsetCallback = null;

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
	 * Builds the object storing the axis which this grid belongs to.
	 * 
	 * @param axis axis which this grid belongs to.
	 */
	Grid(Axis axis) {
		super(axis, axis.getDefaultValues().getGrid());
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// sets function to proxy callback in order to invoke the java interface
		this.tickColorCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValueAsColor(getAxis().createContext(context), getTickColorCallback(), getAxis().getDefaultValues().getGrid().getTickColorAsString(), false));
		// sets function to proxy callback in order to invoke the java interface
		this.tickWidthCallbackProxy
				.setCallback(context -> ScriptableUtil.getOptionValueAsNumber(getAxis().createContext(context), getTickWidthCallback(), getAxis().getDefaultValues().getGrid().getTickWidth(), ScriptableIntegerChecker.POSITIVE_OR_DEFAULT).intValue());
		// sets function to proxy callback in order to invoke the java interface
		this.tickBorderDashOffsetCallbackProxy.setCallback(context -> ScriptableUtil
				.getOptionValueAsNumber(getAxis().createContext(context), getTickBorderDashOffsetCallback(), getAxis().getDefaultValues().getGrid().getTickBorderDashOffset(), ScriptableDoubleChecker.POSITIVE_OR_DEFAULT).doubleValue());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.AbstractScaleLines#getElement()
	 */
	@Override
	AbstractNode getElement() {
		return getAxis().getScale().getGrid();
	}

	/**
	 * If false, do not display grid for this axis.
	 * 
	 * @param display If false, do not display grid for this axis.
	 */
	public void setDisplay(boolean display) {
		getAxis().getScale().getGrid().setDisplay(display);
	}

	/**
	 * If false, do not display grid for this axis.
	 * 
	 * @return If false, do not display grid for this axis.
	 */
	public boolean isDisplay() {
		return getAxis().getScale().getGrid().isDisplay();
	}

	/**
	 * If true, grid are circular (on radar chart only).
	 * 
	 * @param circular If true, grid are circular (on radar chart only).
	 */
	public void setCircular(boolean circular) {
		getAxis().getScale().getGrid().setCircular(circular);
	}

	/**
	 * If true, grid are circular (on radar chart only).
	 * 
	 * @return If true, grid are circular (on radar chart only).
	 */
	public boolean isCircular() {
		return getAxis().getScale().getGrid().isCircular();
	}

	/**
	 * The color of the grid.<br>
	 * If specified as an array, the first color applies to the first grid line, the second to the second grid line and so on.
	 * 
	 * @param color The color of the grid.<br>
	 *            If specified as an array, the first color applies to the first grid line, the second to the second grid line and so on.
	 */
	public void setColor(IsColor... color) {
		// reset callback if there is
		setColor((ColorCallback<ScaleContext>) null);
		// stores value
		getAxis().getScale().getGrid().setColor(color);
	}

	/**
	 * The color of the grid.<br>
	 * If specified as an array, the first color applies to the first grid line, the second to the second grid line and so on.
	 * 
	 * @param color The color of the grid.<br>
	 *            If specified as an array, the first color applies to the first grid line, the second to the second grid line and so on.
	 */
	public void setColor(String... color) {
		// reset callback if there is
		setColor((ColorCallback<ScaleContext>) null);
		// stores value
		getAxis().getScale().getGrid().setColor(color);
	}

	/**
	 * The color of the grid.<br>
	 * If specified as an array, the first color applies to the first grid line, the second to the second grid line and so on.
	 * 
	 * @return the list of colors of the grid.
	 */
	public List<String> getColorAsString() {
		return getAxis().getScale().getGrid().getColorsAsString();
	}

	/**
	 * The color of the grid.<br>
	 * If specified as an array, the first color applies to the first grid line, the second to the second grid line and so on.
	 * 
	 * @return the list of colors of the grid.
	 */
	public List<IsColor> getColor() {
		return getAxis().getScale().getGrid().getColor();
	}

	/**
	 * Sets the stroke widths of grid.
	 * 
	 * @param lineWidth stroke widths of grid.
	 */
	public void setLineWidth(int... lineWidth) {
		// reset callback if there is
		setLineWidth((WidthCallback<ScaleContext>) null);
		// stores value
		getAxis().getScale().getGrid().setLineWidth(lineWidth);
	}

	/**
	 * Returns the stroke widths of grid.
	 * 
	 * @return lineWidth stroke widths of grid.
	 */
	public List<Integer> getLineWidth() {
		return getAxis().getScale().getGrid().getLinesWidth();
	}

	/**
	 * If true, draw lines on the chart area inside the axis lines.<br>
	 * This is useful when there are multiple axes and you need to control which grid are drawn.
	 * 
	 * @param drawOnChartArea If true, draw lines on the chart area inside the axis lines.<br>
	 *            This is useful when there are multiple axes and you need to control which grid are drawn.
	 */
	public void setDrawOnChartArea(boolean drawOnChartArea) {
		getAxis().getScale().getGrid().setDrawOnChartArea(drawOnChartArea);
	}

	/**
	 * If true, draw lines on the chart area inside the axis lines.<br>
	 * This is useful when there are multiple axes and you need to control which grid are drawn.
	 * 
	 * @return If true, draw lines on the chart area inside the axis lines.<br>
	 *         This is useful when there are multiple axes and you need to control which grid are drawn.
	 */
	public boolean isDrawOnChartArea() {
		return getAxis().getScale().getGrid().isDrawOnChartArea();
	}

	/**
	 * If true, draw lines beside the ticks in the axis area beside the chart.
	 * 
	 * @param drawTicks If true, draw lines beside the ticks in the axis area beside the chart.
	 */
	public void setDrawTicks(boolean drawTicks) {
		getAxis().getScale().getGrid().setDrawTicks(drawTicks);
	}

	/**
	 * If true, draw lines beside the ticks in the axis area beside the chart.
	 * 
	 * @return If true, draw lines beside the ticks in the axis area beside the chart.
	 */
	public boolean isDrawTicks() {
		return getAxis().getScale().getGrid().isDrawTicks();
	}

	/**
	 * Sets the length in pixels that the grid will draw in the axis area.
	 * 
	 * @param tickLength Length in pixels that the grid will draw in the axis area.
	 */
	public void setTickLength(int tickLength) {
		getAxis().getScale().getGrid().setTickLength(tickLength);
	}

	/**
	 * Returns the length in pixels that the grid will draw in the axis area.
	 * 
	 * @return Length in pixels that the grid will draw in the axis area.
	 */
	public int getTickLength() {
		return getAxis().getScale().getGrid().getTickLength();
	}

	/**
	 * If true, grid will be shifted to be between labels.<br>
	 * This is set to true in the bar chart by default.
	 * 
	 * @param offset If true, grid will be shifted to be between labels.
	 */
	public void setOffset(boolean offset) {
		getAxis().getScale().getGrid().setOffset(offset);
	}

	/**
	 * If true, grid will be shifted to be between labels.<br>
	 * This is set to true in the bar chart by default.
	 * 
	 * @return If true, grid will be shifted to be between labels.
	 */
	public boolean isOffset() {
		return getAxis().getScale().getGrid().isOffset();
	}

	/**
	 * Sets z-index of grid layer.<br>
	 * Values less than or equals to 0 are drawn under datasets, greater than 0 on top.
	 * 
	 * @param z z-index of grid layer.<br>
	 *            Values less than or equals to 0 are drawn under datasets, greater than 0 on top.
	 */
	public void setZ(int z) {
		getAxis().getScale().getGrid().setZ(z);
	}

	/**
	 * Returns z-index of grid layer.<br>
	 * Values less than or equals to 0 are drawn under datasets, greater than 0 on top.
	 * 
	 * @return z-index of grid layer.<br>
	 *         Values less than or equals to 0 are drawn under datasets, greater than 0 on top.
	 */
	public int getZ() {
		return getAxis().getScale().getGrid().getZ();
	}

	/**
	 * Sets the length and spacing of the tick mark line.
	 * 
	 * @param tickBorderDash the length and spacing of the tick mark line.
	 */
	public void setTickBorderDash(int tickBorderDash) {
		getAxis().getScale().getGrid().setTickBorderDash(tickBorderDash);
	}

	/**
	 * Returns the length and spacing of the tick mark line.
	 * 
	 * @return the length and spacing of the tick mark line.
	 */
	public List<Integer> getTickBorderDash() {
		return getAxis().getScale().getGrid().getTickBorderDash();
	}

	/**
	 * Sets the offset for the line dash of the tick mark.
	 * 
	 * @param tickBorderDashOffset the offset for the line dash of the tick mark
	 */
	public void setTickBorderDashOffset(double tickBorderDashOffset) {
		// reset callback if there is
		setTickBorderDashOffset((BorderDashOffsetCallback<ScaleContext>) null);
		// stores value
		getAxis().getScale().getGrid().setTickBorderDashOffset(tickBorderDashOffset);
	}

	/**
	 * Returns the offset for the line dash of the tick mark.
	 * 
	 * @return the offset for the line dash of the tick mark
	 */
	public double getTickBorderDashOffset() {
		return getAxis().getScale().getGrid().getTickBorderDashOffset();
	}

	/**
	 * Sets the color of the tick line.
	 * 
	 * @param color the color of the tick line.
	 */
	public void setTickColor(IsColor... color) {
		// reset callback if there is
		setTickColor((ColorCallback<ScaleContext>) null);
		// stores value
		getAxis().getScale().getGrid().setTickColor(color);
	}

	/**
	 * Sets the color of the tick line.
	 * 
	 * @param color the color of the tick line.
	 */
	public void setTickColor(String... color) {
		// reset callback if there is
		setTickColor((ColorCallback<ScaleContext>) null);
		// stores value
		getAxis().getScale().getGrid().setTickColor(color);
	}

	/**
	 * Returns the color of the tick line.
	 * 
	 * @return the color of the tick line.
	 */
	public String getTickColorAsString() {
		return getAxis().getScale().getGrid().getTickColorAsString();
	}

	/**
	 * The color of the grid.<br>
	 * If specified as an array, the first color applies to the first grid line, the second to the second grid line and so on.
	 * 
	 * @return the list of colors of the grid.
	 */
	public List<String> getTickColorsAsString() {
		return getAxis().getScale().getGrid().getTickColorsAsString();
	}

	/**
	 * The color of the grid.<br>
	 * If specified as an array, the first color applies to the first grid line, the second to the second grid line and so on.
	 * 
	 * @return the list of colors of the grid.
	 */
	public List<IsColor> getTickColor() {
		return getAxis().getScale().getGrid().getTickColor();
	}

	/**
	 * Sets the width of the tick marks in pixels.
	 * 
	 * @param tickWidth the width of the tick mark in pixels
	 */
	public void setTickWidth(int... tickWidth) {
		// reset callback if there is
		setTickWidth((WidthCallback<ScaleContext>) null);
		// stores value
		getAxis().getScale().getGrid().setTickWidth(tickWidth);
	}

	/**
	 * Returns the width of the tick mark in pixels.<br>
	 * The first element if set as array.
	 * 
	 * @return stroke width of grid.<br>
	 *         The first element if set as array.
	 */
	public int getTickWidth() {
		return getAxis().getScale().getGrid().getTickWidth();
	}

	/**
	 * Returns the width of the tick marks in pixels.
	 * 
	 * @return stroke widths of grid.
	 */
	public List<Integer> getTicksWidth() {
		return getAxis().getScale().getGrid().getTicksWidth();
	}

	// ----------------
	// CALLBACKS
	// ----------------

	/**
	 * Returns the tick color callback instance.
	 * 
	 * @return the tick color callback instance
	 */
	public ColorCallback<ScaleContext> getTickColorCallback() {
		return tickColorCallback;
	}

	/**
	 * Sets the tick color callback instance.
	 * 
	 * @param tickColorCallback the tick color callback instance
	 */
	public void setTickColor(ColorCallback<ScaleContext> tickColorCallback) {
		// stores callback
		this.tickColorCallback = tickColorCallback;
		// stores and manages callback
		getAxis().setCallback(getAxis().getConfiguration().getGrid(), Property.TICK_COLOR, tickColorCallback, tickColorCallbackProxy);
	}

	/**
	 * Sets the tick color callback instance.
	 * 
	 * @param tickColorCallback the tick color callback instance
	 */
	public void setTickColor(NativeCallback tickColorCallback) {
		// resets callback
		setTickColor((ColorCallback<ScaleContext>) null);
		// stores and manages callback
		getAxis().setCallback(getAxis().getConfiguration().getGrid(), Property.TICK_COLOR, tickColorCallback);
	}

	/**
	 * Returns the tick width callback instance.
	 * 
	 * @return the tick width callback instance
	 */
	public WidthCallback<ScaleContext> getTickWidthCallback() {
		return tickWidthCallback;
	}

	/**
	 * Sets the tick width callback instance.
	 * 
	 * @param tickWidthCallback the tick width callback instance.
	 */
	public void setTickWidth(WidthCallback<ScaleContext> tickWidthCallback) {
		// stores callback
		this.tickWidthCallback = tickWidthCallback;
		// stores and manages callback
		getAxis().setCallback(getAxis().getConfiguration().getGrid(), Property.TICK_WIDTH, tickWidthCallback, tickWidthCallbackProxy);
	}

	/**
	 * Sets the tick width callback instance.
	 * 
	 * @param tickWidthCallback the tick width callback instance.
	 */
	public void setTickWidth(NativeCallback tickWidthCallback) {
		// resets callback
		setTickWidth((WidthCallback<ScaleContext>) null);
		// stores and manages callback
		getAxis().setCallback(getAxis().getConfiguration().getGrid(), Property.TICK_WIDTH, tickWidthCallback);
	}

	/**
	 * Returns the tick border dash offset callback instance.
	 * 
	 * @return the tick border dash offset callback instance
	 */
	public BorderDashOffsetCallback<ScaleContext> getTickBorderDashOffsetCallback() {
		return tickBorderDashOffsetCallback;
	}

	/**
	 * Sets the tick border dash offset callback instance.
	 * 
	 * @param tickBorderDashOffsetCallback the tick border dash offset callback instance
	 */
	public void setTickBorderDashOffset(BorderDashOffsetCallback<ScaleContext> tickBorderDashOffsetCallback) {
		// stores callback
		this.tickBorderDashOffsetCallback = tickBorderDashOffsetCallback;
		// stores and manages callback
		getAxis().setCallback(getAxis().getConfiguration().getGrid(), Property.TICK_BORDER_DASH_OFFSET, tickBorderDashOffsetCallback, tickBorderDashOffsetCallbackProxy);
	}

	/**
	 * Sets the tick border dash offset callback instance.
	 * 
	 * @param tickBorderDashOffsetCallback the tick border dash offset callback instance
	 */
	public void setTickBorderDashOffset(NativeCallback tickBorderDashOffsetCallback) {
		// resets callback
		setTickBorderDashOffset((BorderDashOffsetCallback<ScaleContext>) null);
		// stores and manages callback
		getAxis().setCallback(getAxis().getConfiguration().getGrid(), Property.TICK_BORDER_DASH_OFFSET, tickBorderDashOffsetCallback);
	}

}