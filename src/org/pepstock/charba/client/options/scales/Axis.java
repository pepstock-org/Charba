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
 * 
 */
public abstract class Axis extends JavaScriptObjectContainer {
	
	private static final int DEFAULT_WEIGHT = 0;

	private static final boolean DEFAULT_DISPLAY = true;
	
	private AbstractChart<?, ?> chart = null;
	
	private AxisUpdateCallback updateCallback = null;
	
	private AxisDimensionsCallback dimensionsCallback = null;
	
	private AxisDataLimitsCallback dataLimitsCallback = null;
	
	private AxisBuildTicksCallback buildTicksCallback = null;
	
	private AxisTickToLabelConversionCallback tickToLabelConversionCallback = null;
	
	private AxisCalculateTickRotationCallback calculateTickRotationCallback = null;
	
	private AxisFitCallback fitCallback = null;

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

	public void setType(AxisType type) {
		setValue(Property.type, type);
	}

	public AxisType getType() {
		return getValue(Property.type, AxisType.values(), AxisType.linear);
	}

	public void setDisplay(boolean display) {
		setValue(Property.display, display);
	}

	public boolean isDisplay() {
		return getValue(Property.display, DEFAULT_DISPLAY);
	}

	public void setWeight(int weight) {
		setValue(Property.weight, weight);
	}

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
		if (hasToBeRegistered(axisUpdateCallback, Property.beforeUpdate, Property.afterUpdate)){
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
		if (hasToBeRegistered(dimensionsCallback, Property.beforeSetDimensions, Property.afterSetDimensions)){
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
		if (hasToBeRegistered(dataLimitsCallback, Property.beforeDataLimits, Property.afterDataLimits)){
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
		if (hasToBeRegistered(buildTicksCallback, Property.beforeBuildTicks, Property.afterBuildTicks)){
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
		if (hasToBeRegistered(tickToLabelConversionCallback, Property.beforeTickToLabelConversion, Property.afterTickToLabelConversion)){
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
		if (hasToBeRegistered(calculateTickRotationCallback, Property.beforeCalculateTickRotation, Property.afterCalculateTickRotation)){
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
		if (hasToBeRegistered(fitCallback, Property.beforeFit, Property.afterFit)){
			registerNativeAxisFitHandler(getJavaScriptObject());
		}
		this.fitCallback = fitCallback;
	}

	
	/**
	 * INTERVAL CALLBACKS
	 */
	/**
	 * I
	 * @param item
	 */
	protected void onBeforeUpdate(AxisItem item){
		if (updateCallback != null && chart != null){
			updateCallback.onBeforeUpdate(chart, item);
		}
	}

	protected void onAfterUpdate(AxisItem item){
		if (updateCallback != null && chart != null){
			updateCallback.onAfterUpdate(chart, item);
		}
	}

	protected void onBeforeSetDimensions(AxisItem item){
		if (dimensionsCallback != null && chart != null){
			dimensionsCallback.onBeforeSetDimensions(chart, item);
		}
	}

	protected void onAfterSetDimensions(AxisItem item){
		if (dimensionsCallback != null && chart != null){
			dimensionsCallback.onAfterSetDimensions(chart, item);
		}
	}

	protected void onBeforeDataLimits(AxisItem item){
		if (dataLimitsCallback != null && chart != null){
			dataLimitsCallback.onBeforeDataLimits(chart, item);;
		}
	}

	protected void onAfterDataLimits(AxisItem item){
		if (dataLimitsCallback != null && chart != null){
			dataLimitsCallback.onAfterDataLimits(chart, item);;
		}
	}

	protected void onBeforeBuildTicks(AxisItem item){
		if (buildTicksCallback != null && chart != null){
			buildTicksCallback.onBeforeBuildTicks(chart, item);
		}
	}

	protected void onAfterBuildTicks(AxisItem item){
		if (buildTicksCallback != null && chart != null){
			buildTicksCallback.onAfterBuildTicks(chart, item);
		}
	}
	
	protected void onBeforeTickToLabelConversion(AxisItem item){
		if (tickToLabelConversionCallback != null && chart != null){
			tickToLabelConversionCallback.onBeforeTickToLabelConversion(chart, item);
		}
	}

	protected void onAfterTickToLabelConversion(AxisItem item){
		if (tickToLabelConversionCallback != null && chart != null){
			tickToLabelConversionCallback.onAfterTickToLabelConversion(chart, item);
		}
	}
	
	protected void onBeforeCalculateTickRotation(AxisItem item){
		if (calculateTickRotationCallback != null && chart != null){
			calculateTickRotationCallback.onBeforeCalculateTickRotation(chart, item);
		}
	}

	protected void onAfterCalculateTickRotation(AxisItem item){
		if (calculateTickRotationCallback != null && chart != null){
			calculateTickRotationCallback.onAfterCalculateTickRotation(chart, item);
		}
	}

	protected void onBeforeFit(AxisItem item){
		if (fitCallback != null && chart != null){
			fitCallback.onBeforeFit(chart, item);
		}
	}

	protected void onAfterFit(AxisItem item){
		if (fitCallback != null && chart != null){
			fitCallback.onAfterFit(chart, item);
		}
	}


	/**
	 * 
	 * @param options
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
	 * 
	 * @param options
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
	 * 
	 * @param options
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
	 * 
	 * @param options
	 */
    private native void registerNativeAxisBuildTicksHandler(GenericJavaScriptObject options)/*-{
		var self = this;
	    options.beforeBuildTicks = function(axis){
	    	console.log(axis.minSize);
        	self.@org.pepstock.charba.client.options.scales.Axis::onBeforeBuildTicks(Lorg/pepstock/charba/client/items/AxisItem;)(axis);
	    }
	    options.afterBuildTicks = function(axis){
        	self.@org.pepstock.charba.client.options.scales.Axis::onAfterBuildTicks(Lorg/pepstock/charba/client/items/AxisItem;)(axis);
	    }
	}-*/;
    
	/**
	 * 
	 * @param options
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
	 * 
	 * @param options
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
	 * 
	 * @param options
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