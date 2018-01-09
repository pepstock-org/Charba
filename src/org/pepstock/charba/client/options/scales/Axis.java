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
package org.pepstock.charba.client.options.scales;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.callbacks.AxisBuildTicksCallback;
import org.pepstock.charba.client.callbacks.AxisCalculateTickRotationCallback;
import org.pepstock.charba.client.callbacks.AxisDataLimitsCallback;
import org.pepstock.charba.client.callbacks.AxisDimensionsCallback;
import org.pepstock.charba.client.callbacks.AxisFitCallback;
import org.pepstock.charba.client.callbacks.AxisTickToLabelConversionCallback;
import org.pepstock.charba.client.callbacks.AxisUpdateCallback;
import org.pepstock.charba.client.commons.GenericJavaScriptObject;
import org.pepstock.charba.client.commons.JavaScriptObjectContainer;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.AxisType;
import org.pepstock.charba.client.items.AxisItem;

/**
 * Axes are an integral part of a chart.<br>
 * They are used to determine how data maps to a pixel value on the chart.<br>
 * It contains a number of config callbacks that can be used to change parameters in the scale at different points in the update process.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public abstract class Axis extends JavaScriptObjectContainer {

	// default weight
	private static final int DEFAULT_WEIGHT = 0;

	private AbstractChart<?, ?> chart = null;

	private AxisUpdateCallback updateCallback = null;

	private AxisDimensionsCallback dimensionsCallback = null;

	private AxisDataLimitsCallback dataLimitsCallback = null;

	private AxisBuildTicksCallback buildTicksCallback = null;

	private AxisTickToLabelConversionCallback tickToLabelConversionCallback = null;

	private AxisCalculateTickRotationCallback calculateTickRotationCallback = null;

	private AxisFitCallback fitCallback = null;

	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
		type,
		display,
		weight,
		beforeUpdate,
		beforeSetDimensions,
		afterSetDimensions,
		beforeDataLimits,
		afterDataLimits,
		beforeBuildTicks,
		afterBuildTicks,
		beforeTickToLabelConversion,
		afterTickToLabelConversion,
		beforeCalculateTickRotation,
		afterCalculateTickRotation,
		beforeFit,
		afterFit,
		afterUpdate
	}

	/**
	 * @param chart the chart to set
	 */
	public void setChart(AbstractChart<?, ?> chart) {
		this.chart = chart;
	}

	/**
	 * @return the chart
	 */
	AbstractChart<?, ?> getChart() {
		return chart;
	}

	/**
	 * Type of scale being employed.
	 * 
	 * @param type type of axis
	 * @see org.pepstock.charba.client.enums.AxisType
	 */
	public void setType(AxisType type) {
		setValue(Property.type, type);
	}

	/**
	 * Returns the type of axis. If not set, the default is <code>linear</code>.
	 * 
	 * @return the type of axis
	 * @see org.pepstock.charba.client.enums.AxisType
	 */
	public AxisType getType() {
		return getValue(Property.type, AxisType.class, AxisType.linear);
	}

	/**
	 * If set to false the axis is hidden from view. Overrides gridLines.display, scaleLabel.display, and ticks.display.
	 * 
	 * @param display If set to false the axis is hidden from view. Overrides gridLines.display, scaleLabel.display, and ticks.display.
	 */
	public void setDisplay(boolean display) {
		setValue(Property.display, display);
	}

	/**
	 * Returns if axis is hidden.
	 * 
	 * @return <code>false</code> if axis is hidden, otherwise <code>true</code>. Default is {@link org.pepstock.charba.client.defaults.Scale#isDisplay()}.
	 */
	public boolean isDisplay() {
		return getValue(Property.display, Defaults.getScale().isDisplay());
	}

	/**
	 * The weight used to sort the axis. Higher weights are further away from the chart area.
	 * 
	 * @param weight weight of axis
	 */
	public void setWeight(int weight) {
		setValue(Property.weight, weight);
	}

	/**
	 * The weight used to sort the axis. Higher weights are further away from the chart area.
	 * 
	 * @return weight of axis
	 */
	public int getWeight() {
		return getValue(Property.weight, DEFAULT_WEIGHT);
	}

	/**
	 * @return the axisUpdateCallback
	 */
	public AxisUpdateCallback getUpdateCallback() {
		return updateCallback;
	}

	/**
	 * @param axisUpdateCallback the axisUpdateCallback to set
	 */
	public void setUpdateCallback(AxisUpdateCallback axisUpdateCallback) {
		// checks if callback has been already set
		if (hasToBeRegistered(axisUpdateCallback, Property.beforeUpdate, Property.afterUpdate)) {
			// sets callback
			registerNativeAxisUpdateHandler(getJavaScriptObject());
		}
		this.updateCallback = axisUpdateCallback;
	}

	/**
	 * @return the dimensionsCallback
	 */
	public AxisDimensionsCallback getDimensionsCallback() {
		return dimensionsCallback;
	}

	/**
	 * @param dimensionsCallback the dimensionsCallback to set
	 */
	public void setDimensionsCallback(AxisDimensionsCallback dimensionsCallback) {
		// checks if callback has been already set
		if (hasToBeRegistered(dimensionsCallback, Property.beforeSetDimensions, Property.afterSetDimensions)) {
			// sets callback
			registerNativeAxisDimensionsHandler(getJavaScriptObject());
		}
		this.dimensionsCallback = dimensionsCallback;
	}

	/**
	 * @return the dataLimitsCallback
	 */
	public AxisDataLimitsCallback getDataLimitsCallback() {
		return dataLimitsCallback;
	}

	/**
	 * @param dataLimitsCallback the dataLimitsCallback to set
	 */
	public void setDataLimitsCallback(AxisDataLimitsCallback dataLimitsCallback) {
		// checks if callback has been already set
		if (hasToBeRegistered(dataLimitsCallback, Property.beforeDataLimits, Property.afterDataLimits)) {
			// sets callback
			registerNativeAxisDataLimitsHandler(getJavaScriptObject());
		}
		this.dataLimitsCallback = dataLimitsCallback;
	}

	/**
	 * @return the buildTicksCallback
	 */
	public AxisBuildTicksCallback getBuildTicksCallback() {
		return buildTicksCallback;
	}

	/**
	 * @param buildTicksCallback the buildTicksCallback to set
	 */
	public void setBuildTicksCallback(AxisBuildTicksCallback buildTicksCallback) {
		// checks if callback has been already set
		if (hasToBeRegistered(buildTicksCallback, Property.beforeBuildTicks, Property.afterBuildTicks)) {
			// sets callback
			registerNativeAxisBuildTicksHandler(getJavaScriptObject());
		}
		this.buildTicksCallback = buildTicksCallback;
	}

	/**
	 * @return the tickToLabelConversionCallback
	 */
	public AxisTickToLabelConversionCallback getTickToLabelConversionCallback() {
		return tickToLabelConversionCallback;
	}

	/**
	 * @param tickToLabelConversionCallback the tickToLabelConversionCallback to set
	 */
	public void setTickToLabelConversionCallback(AxisTickToLabelConversionCallback tickToLabelConversionCallback) {
		// checks if callback has been already set
		if (hasToBeRegistered(tickToLabelConversionCallback, Property.beforeTickToLabelConversion, Property.afterTickToLabelConversion)) {
			// sets callback
			registerNativeAxisTickToLabelConversionHandler(getJavaScriptObject());
		}
		this.tickToLabelConversionCallback = tickToLabelConversionCallback;
	}

	/**
	 * @return the calculateTickRotationCallback
	 */
	public AxisCalculateTickRotationCallback getCalculateTickRotationCallback() {
		return calculateTickRotationCallback;
	}

	/**
	 * @param calculateTickRotationCallback the calculateTickRotationCallback to set
	 */
	public void setCalculateTickRotationCallback(AxisCalculateTickRotationCallback calculateTickRotationCallback) {
		// checks if callback has been already set
		if (hasToBeRegistered(calculateTickRotationCallback, Property.beforeCalculateTickRotation, Property.afterCalculateTickRotation)) {
			// sets callback
			registerNativeAxisCalculateTickRotationHandler(getJavaScriptObject());
		}
		this.calculateTickRotationCallback = calculateTickRotationCallback;
	}

	/**
	 * @return the fitCallback
	 */
	public AxisFitCallback getFitCallback() {
		return fitCallback;
	}

	/**
	 * @param fitCallback the fitCallback to set
	 */
	public void setFitCallback(AxisFitCallback fitCallback) {
		// checks if callback has been already set
		if (hasToBeRegistered(fitCallback, Property.beforeFit, Property.afterFit)) {
			// sets callback
			registerNativeAxisFitHandler(getJavaScriptObject());
		}
		this.fitCallback = fitCallback;
	}

	/**
	 * Callback called before the update process starts.
	 * 
	 * @param item axis item got from java script
	 * @see org.pepstock.charba.client.items.AxisItem
	 */
	protected void onBeforeUpdate(AxisItem item) {
		// checks if callback and chart are consistent
		if (updateCallback != null && chart != null) {
			updateCallback.onBeforeUpdate(chart, item);
		}
	}

	/**
	 * Callback that runs at the end of the update process.
	 * 
	 * @param item axis item got from java script
	 * @see org.pepstock.charba.client.items.AxisItem
	 */
	protected void onAfterUpdate(AxisItem item) {
		// checks if callback and chart are consistent
		if (updateCallback != null && chart != null) {
			updateCallback.onAfterUpdate(chart, item);
		}
	}

	/**
	 * Callback that runs before dimensions are set.
	 * 
	 * @param item axis item got from java script
	 * @see org.pepstock.charba.client.items.AxisItem
	 */
	protected void onBeforeSetDimensions(AxisItem item) {
		// checks if callback and chart are consistent
		if (dimensionsCallback != null && chart != null) {
			dimensionsCallback.onBeforeSetDimensions(chart, item);
		}
	}

	/**
	 * Callback that runs after dimensions are set.
	 * 
	 * @param item axis item got from java script
	 * @see org.pepstock.charba.client.items.AxisItem
	 */
	protected void onAfterSetDimensions(AxisItem item) {
		// checks if callback and chart are consistent
		if (dimensionsCallback != null && chart != null) {
			dimensionsCallback.onAfterSetDimensions(chart, item);
		}
	}

	/**
	 * Callback that runs before data limits are determined.
	 * 
	 * @param item axis item got from java script
	 * @see org.pepstock.charba.client.items.AxisItem
	 */
	protected void onBeforeDataLimits(AxisItem item) {
		// checks if callback and chart are consistent
		if (dataLimitsCallback != null && chart != null) {
			dataLimitsCallback.onBeforeDataLimits(chart, item);
			;
		}
	}

	/**
	 * Callback that runs after data limits are determined.
	 * 
	 * @param item axis item got from java script
	 * @see org.pepstock.charba.client.items.AxisItem
	 */
	protected void onAfterDataLimits(AxisItem item) {
		// checks if callback and chart are consistent
		if (dataLimitsCallback != null && chart != null) {
			dataLimitsCallback.onAfterDataLimits(chart, item);
			;
		}
	}

	/**
	 * Callback that runs before ticks are created.
	 * 
	 * @param item axis item got from java script
	 * @see org.pepstock.charba.client.items.AxisItem
	 */
	protected void onBeforeBuildTicks(AxisItem item) {
		// checks if callback and chart are consistent
		if (buildTicksCallback != null && chart != null) {
			buildTicksCallback.onBeforeBuildTicks(chart, item);
		}
	}

	/**
	 * Callback that runs after ticks are created. Useful for filtering ticks.
	 * 
	 * @param item axis item got from java script
	 * @see org.pepstock.charba.client.items.AxisItem
	 */
	protected void onAfterBuildTicks(AxisItem item) {
		// checks if callback and chart are consistent
		if (buildTicksCallback != null && chart != null) {
			buildTicksCallback.onAfterBuildTicks(chart, item);
		}
	}

	/**
	 * Callback that runs before ticks are converted into strings.
	 * 
	 * @param item axis item got from java script
	 * @see org.pepstock.charba.client.items.AxisItem
	 */
	protected void onBeforeTickToLabelConversion(AxisItem item) {
		// checks if callback and chart are consistent
		if (tickToLabelConversionCallback != null && chart != null) {
			tickToLabelConversionCallback.onBeforeTickToLabelConversion(chart, item);
		}
	}

	/**
	 * Callback that runs after ticks are converted into strings.
	 * 
	 * @param item axis item got from java script
	 * @see org.pepstock.charba.client.items.AxisItem
	 */
	protected void onAfterTickToLabelConversion(AxisItem item) {
		// checks if callback and chart are consistent
		if (tickToLabelConversionCallback != null && chart != null) {
			tickToLabelConversionCallback.onAfterTickToLabelConversion(chart, item);
		}
	}

	/**
	 * Callback that runs before tick rotation is determined.
	 * 
	 * @param item axis item got from java script
	 * @see org.pepstock.charba.client.items.AxisItem
	 */
	protected void onBeforeCalculateTickRotation(AxisItem item) {
		// checks if callback and chart are consistent
		if (calculateTickRotationCallback != null && chart != null) {
			calculateTickRotationCallback.onBeforeCalculateTickRotation(chart, item);
		}
	}

	/**
	 * Callback that runs after tick rotation is determined.
	 * 
	 * @param item axis item got from java script
	 * @see org.pepstock.charba.client.items.AxisItem
	 */
	protected void onAfterCalculateTickRotation(AxisItem item) {
		// checks if callback and chart are consistent
		if (calculateTickRotationCallback != null && chart != null) {
			calculateTickRotationCallback.onAfterCalculateTickRotation(chart, item);
		}
	}

	/**
	 * Callback that runs before the scale fits to the canvas.
	 * 
	 * @param item axis item got from java script
	 * @see org.pepstock.charba.client.items.AxisItem
	 */
	protected void onBeforeFit(AxisItem item) {
		// checks if callback and chart are consistent
		if (fitCallback != null && chart != null) {
			fitCallback.onBeforeFit(chart, item);
		}
	}

	/**
	 * Callback that runs after the scale fits to the canvas.
	 * 
	 * @param item axis item got from java script
	 * @see org.pepstock.charba.client.items.AxisItem
	 */
	protected void onAfterFit(AxisItem item) {
		// checks if callback and chart are consistent
		if (fitCallback != null && chart != null) {
			fitCallback.onAfterFit(chart, item);
		}
	}

	/**
	 * Sets the java script code to activate the call back, adding functions.
	 * 
	 * @param options
	 *            java script object where adding new functions definition.
	 */
	private native void registerNativeAxisUpdateHandler(GenericJavaScriptObject options)/*-{
		var self = this;
		options.beforeUpdate = function(axis){
			self.@org.pepstock.charba.client.options.scales.Axis::onBeforeUpdate(Lorg/pepstock/charba/client/items/AxisItem;)(axis);
		}
		options.afterUpdate = function(axis){
			self.@org.pepstock.charba.client.options.scales.Axis::onAfterUpdate(Lorg/pepstock/charba/client/items/AxisItem;)(axis);
		}
	}-*/;

	/**
	 * Sets the java script code to activate the call back, adding functions.
	 * 
	 * @param options
	 *            java script object where adding new functions definition.
	 */
	private native void registerNativeAxisDimensionsHandler(GenericJavaScriptObject options)/*-{
		var self = this;
		options.beforeSetDimensions = function(axis){
			self.@org.pepstock.charba.client.options.scales.Axis::onBeforeSetDimensions(Lorg/pepstock/charba/client/items/AxisItem;)(axis);
		}
		options.afterSetDimensions = function(axis){
			self.@org.pepstock.charba.client.options.scales.Axis::onAfterSetDimensions(Lorg/pepstock/charba/client/items/AxisItem;)(axis);
		}
	}-*/;

	/**
	 * Sets the java script code to activate the call back, adding functions.
	 * 
	 * @param options
	 *            java script object where adding new functions definition.
	 */
	private native void registerNativeAxisDataLimitsHandler(GenericJavaScriptObject options)/*-{
		var self = this;
		options.beforeDataLimits = function(axis){
			self.@org.pepstock.charba.client.options.scales.Axis::onBeforeDataLimits(Lorg/pepstock/charba/client/items/AxisItem;)(axis);
		}
		options.afterDataLimits = function(axis){
			self.@org.pepstock.charba.client.options.scales.Axis::onAfterDataLimits(Lorg/pepstock/charba/client/items/AxisItem;)(axis);
		}
	}-*/;

	/**
	 * Sets the java script code to activate the call back, adding functions.
	 * 
	 * @param options
	 *            java script object where adding new functions definition.
	 */
	private native void registerNativeAxisBuildTicksHandler(GenericJavaScriptObject options)/*-{
		var self = this;
		options.beforeBuildTicks = function(axis){
			self.@org.pepstock.charba.client.options.scales.Axis::onBeforeBuildTicks(Lorg/pepstock/charba/client/items/AxisItem;)(axis);
		}
		options.afterBuildTicks = function(axis){
			self.@org.pepstock.charba.client.options.scales.Axis::onAfterBuildTicks(Lorg/pepstock/charba/client/items/AxisItem;)(axis);
		}
	}-*/;

	/**
	 * Sets the java script code to activate the call back, adding functions.
	 * 
	 * @param options
	 *            java script object where adding new functions definition.
	 */
	private native void registerNativeAxisTickToLabelConversionHandler(GenericJavaScriptObject options)/*-{
		var self = this;
		options.beforeTickToLabelConversion = function(axis){
			self.@org.pepstock.charba.client.options.scales.Axis::onBeforeTickToLabelConversion(Lorg/pepstock/charba/client/items/AxisItem;)(axis);
		}
		options.afterTickToLabelConversion = function(axis){
			self.@org.pepstock.charba.client.options.scales.Axis::onAfterTickToLabelConversion(Lorg/pepstock/charba/client/items/AxisItem;)(axis);
		}
	}-*/;

	/**
	 * Sets the java script code to activate the call back, adding functions.
	 * 
	 * @param options
	 *            java script object where adding new functions definition.
	 */
	private native void registerNativeAxisCalculateTickRotationHandler(GenericJavaScriptObject options)/*-{
		var self = this;
		options.beforeCalculateTickRotation = function(axis){
			self.@org.pepstock.charba.client.options.scales.Axis::onBeforeCalculateTickRotation(Lorg/pepstock/charba/client/items/AxisItem;)(axis);
		}
		options.afterCalculateTickRotation = function(axis){
			self.@org.pepstock.charba.client.options.scales.Axis::onAfterCalculateTickRotation(Lorg/pepstock/charba/client/items/AxisItem;)(axis);
		}
	}-*/;

	/**
	 * Sets the java script code to activate the call back, adding functions.
	 * 
	 * @param options
	 *            java script object where adding new functions definition.
	 */
	private native void registerNativeAxisFitHandler(GenericJavaScriptObject options)/*-{
		var self = this;
		options.beforeFit = function(axis){
			self.@org.pepstock.charba.client.options.scales.Axis::onBeforeFit(Lorg/pepstock/charba/client/items/AxisItem;)(axis);
		}
		options.afterFit = function(axis){
			self.@org.pepstock.charba.client.options.scales.Axis::onBeforeFit(Lorg/pepstock/charba/client/items/AxisItem;)(axis);
		}
	}-*/;

}