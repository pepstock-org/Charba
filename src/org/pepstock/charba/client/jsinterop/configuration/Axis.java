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
package org.pepstock.charba.client.jsinterop.configuration;

import java.util.List;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.AxisType;
import org.pepstock.charba.client.enums.CartesianAxisType;
import org.pepstock.charba.client.jsinterop.AbstractChart;
import org.pepstock.charba.client.jsinterop.ChartOptions;
import org.pepstock.charba.client.jsinterop.Defaults;
import org.pepstock.charba.client.jsinterop.callbacks.AxisBuildTicksCallback;
import org.pepstock.charba.client.jsinterop.callbacks.AxisCalculateTickRotationCallback;
import org.pepstock.charba.client.jsinterop.callbacks.AxisDataLimitsCallback;
import org.pepstock.charba.client.jsinterop.callbacks.AxisDimensionsCallback;
import org.pepstock.charba.client.jsinterop.callbacks.AxisFitCallback;
import org.pepstock.charba.client.jsinterop.callbacks.AxisTickToLabelConversionCallback;
import org.pepstock.charba.client.jsinterop.callbacks.AxisUpdateCallback;
import org.pepstock.charba.client.jsinterop.commons.CallbackProxy;
import org.pepstock.charba.client.jsinterop.commons.JsHelper;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;
import org.pepstock.charba.client.jsinterop.defaults.chart.DefaultChartScale;
import org.pepstock.charba.client.jsinterop.items.AxisItem;
import org.pepstock.charba.client.jsinterop.options.ExtendedScale;
import org.pepstock.charba.client.jsinterop.options.Scale;

import jsinterop.annotations.JsFunction;

/**
 * Axes are an integral part of a chart.<br>
 * They are used to determine how data maps to a pixel value on the chart.<br>
 * It contains a number of config callbacks that can be used to change parameters in the scale at different points in the update
 * process.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public abstract class Axis extends ConfigurationContainer<ExtendedScale> {
	
	@JsFunction
	interface ProxyBeforeUpdateCallback {
		void call(Object context, NativeObject item);
	}

	@JsFunction
	interface ProxyBeforeSetDimensionsCallback {
		void call(Object context, NativeObject item);
	}

	@JsFunction
	interface ProxyAfterSetDimensionsCallback {
		void call(Object context, NativeObject item);
	}

	@JsFunction
	interface ProxyBeforeDataLimitsCallback {
		void call(Object context, NativeObject item);
	}

	@JsFunction
	interface ProxyAfterDataLimitsCallback {
		void call(Object context, NativeObject item);
	}

	@JsFunction
	interface ProxyBeforeBuildTicksCallback {
		void call(Object context, NativeObject item);
	}

	@JsFunction
	interface ProxyAfterBuildTicksCallback {
		void call(Object context, NativeObject item);
	}

	@JsFunction
	interface ProxyBeforeTickToLabelConversionCallback {
		void call(Object context, NativeObject item);
	}

	@JsFunction
	interface ProxyAfterTickToLabelConversionCallback {
		void call(Object context, NativeObject item);
	}

	@JsFunction
	interface ProxyBeforeCalculateTickRotationCallback {
		void call(Object context, NativeObject item);
	}

	@JsFunction
	interface ProxyAfterCalculateTickRotationCallback {
		void call(Object context, NativeObject item);
	}

	@JsFunction
	interface ProxyBeforeFitCallback {
		void call(Object context, NativeObject item);
	}

	@JsFunction
	interface ProxyAfterFitCallback {
		void call(Object context, NativeObject item);
	}

	@JsFunction
	interface ProxyAfterUpdateCallback {
		void call(Object context, NativeObject item);
	}
	
	private final CallbackProxy<ProxyBeforeUpdateCallback> beforeUpdateCallbackProxy = JsHelper.get().newCallbackProxy();

	private final CallbackProxy<ProxyBeforeSetDimensionsCallback> beforeSetDimensionsCallbackProxy = JsHelper.get().newCallbackProxy();

	private final CallbackProxy<ProxyAfterSetDimensionsCallback> afterSetDimensionsCallbackProxy = JsHelper.get().newCallbackProxy();

	private final CallbackProxy<ProxyBeforeDataLimitsCallback> beforeDataLimitsCallbackProxy = JsHelper.get().newCallbackProxy();

	private final CallbackProxy<ProxyAfterDataLimitsCallback> afterDataLimitsCallbackProxy = JsHelper.get().newCallbackProxy();

	private final CallbackProxy<ProxyBeforeBuildTicksCallback> beforeBuildTicksCallbackProxy = JsHelper.get().newCallbackProxy();

	private final CallbackProxy<ProxyAfterBuildTicksCallback> afterBuildTicksCallbackProxy = JsHelper.get().newCallbackProxy();

	private final CallbackProxy<ProxyBeforeTickToLabelConversionCallback> beforeTickToLabelConversionCallbackProxy = JsHelper.get().newCallbackProxy();

	private final CallbackProxy<ProxyAfterTickToLabelConversionCallback> afterTickToLabelConversionCallbackProxy = JsHelper.get().newCallbackProxy();

	private final CallbackProxy<ProxyBeforeCalculateTickRotationCallback> beforeCalculateTickRotationCallbackProxy = JsHelper.get().newCallbackProxy();

	private final CallbackProxy<ProxyAfterCalculateTickRotationCallback> afterCalculateTickRotationCallbackProxy = JsHelper.get().newCallbackProxy();

	private final CallbackProxy<ProxyBeforeFitCallback> beforeFitCallbackProxy = JsHelper.get().newCallbackProxy();

	private final CallbackProxy<ProxyAfterFitCallback> afterFitCallbackProxy = JsHelper.get().newCallbackProxy();

	private final CallbackProxy<ProxyAfterUpdateCallback> afterUpdateCallbackProxy = JsHelper.get().newCallbackProxy();
	
	private AxisBuildTicksCallback axisBuildTicksCallback = null;
	
	private AxisCalculateTickRotationCallback axisCalculateTickRotationCallback = null;
	
	private AxisDataLimitsCallback axisDataLimitsCallback = null;
	
	private AxisDimensionsCallback axisDimensionsCallback = null;
	
	private AxisFitCallback axisFitCallback = null;
	
	private AxisTickToLabelConversionCallback axisTickToLabelConversionCallback = null;
	
	private AxisUpdateCallback axisUpdateCallback = null;

	
	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
		//--- CALL BACKS
		beforeUpdate,
		afterUpdate,
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
		afterFit
	}

	/**
	 * Builds the object storing the chart instance.
	 * 
	 * @param chart chart instance
	 */
	Axis(AbstractChart<?, ?> chart) {
		super(chart);
		setConfiguration(new ExtendedScale(new DefaultChartScale(getDefaultScale())));
		// callbacks
		beforeUpdateCallbackProxy.setCallback(new ProxyBeforeUpdateCallback() {

			@Override
			public void call(Object context, NativeObject item) {
				if (axisUpdateCallback != null) {
					axisUpdateCallback.onBeforeUpdate(Axis.this, new AxisItem(item));
				}
			}
		});

		beforeSetDimensionsCallbackProxy.setCallback(new ProxyBeforeSetDimensionsCallback() {

			@Override
			public void call(Object context, NativeObject item) {
				if (axisDimensionsCallback != null) {
					axisDimensionsCallback.onBeforeSetDimensions(Axis.this, new AxisItem(item));
				}
			}
		});

		afterSetDimensionsCallbackProxy.setCallback(new ProxyAfterSetDimensionsCallback() {

			@Override
			public void call(Object context, NativeObject item) {
				if (axisDimensionsCallback != null) {
					axisDimensionsCallback.onAfterSetDimensions(Axis.this, new AxisItem(item));
				}
			}
		});

		beforeDataLimitsCallbackProxy.setCallback(new ProxyBeforeDataLimitsCallback() {

			@Override
			public void call(Object context, NativeObject item) {
				if (axisDataLimitsCallback != null) {
					axisDataLimitsCallback.onBeforeDataLimits(Axis.this, new AxisItem(item));
				}
			}
		});

		afterDataLimitsCallbackProxy.setCallback(new ProxyAfterDataLimitsCallback() {

			@Override
			public void call(Object context, NativeObject item) {
				if (axisDataLimitsCallback != null) {
					axisDataLimitsCallback.onAfterDataLimits(Axis.this, new AxisItem(item));
				}
			}
		});

		beforeBuildTicksCallbackProxy.setCallback(new ProxyBeforeBuildTicksCallback() {

			@Override
			public void call(Object context, NativeObject item) {
				if (axisBuildTicksCallback != null) {
					axisBuildTicksCallback.onBeforeBuildTicks(Axis.this, new AxisItem(item));
				}
			}
		});

		afterBuildTicksCallbackProxy.setCallback(new ProxyAfterBuildTicksCallback() {

			@Override
			public void call(Object context, NativeObject item) {
				if (axisBuildTicksCallback != null) {
					axisBuildTicksCallback.onAfterBuildTicks(Axis.this, new AxisItem(item));
				}
			}
		});

		beforeTickToLabelConversionCallbackProxy.setCallback(new ProxyBeforeTickToLabelConversionCallback() {

			@Override
			public void call(Object context, NativeObject item) {
				if (axisTickToLabelConversionCallback != null) {
					axisTickToLabelConversionCallback.onBeforeTickToLabelConversion(Axis.this, new AxisItem(item));
				}
			}
		});

		afterTickToLabelConversionCallbackProxy.setCallback(new ProxyAfterTickToLabelConversionCallback() {

			@Override
			public void call(Object context, NativeObject item) {
				if (axisTickToLabelConversionCallback != null) {
					axisTickToLabelConversionCallback.onAfterTickToLabelConversion(Axis.this, new AxisItem(item));
				}
			}
		});

		beforeCalculateTickRotationCallbackProxy.setCallback(new ProxyBeforeCalculateTickRotationCallback() {

			@Override
			public void call(Object context, NativeObject item) {
				if (axisCalculateTickRotationCallback != null) {
					axisCalculateTickRotationCallback.onBeforeCalculateTickRotation(Axis.this, new AxisItem(item));
				}
			}
		});

		afterCalculateTickRotationCallbackProxy.setCallback(new ProxyAfterCalculateTickRotationCallback() {

			@Override
			public void call(Object context, NativeObject item) {
				if (axisCalculateTickRotationCallback != null) {
					axisCalculateTickRotationCallback.onAfterCalculateTickRotation(Axis.this, new AxisItem(item));
				}
			}
		});

		beforeFitCallbackProxy.setCallback(new ProxyBeforeFitCallback() {

			@Override
			public void call(Object context, NativeObject item) {
				if (axisFitCallback != null) {
					axisFitCallback.onBeforeFit(Axis.this, new AxisItem(item));
				}
			}
		});

		afterFitCallbackProxy.setCallback(new ProxyAfterFitCallback() {

			@Override
			public void call(Object context, NativeObject item) {
				if (axisFitCallback != null) {
					axisFitCallback.onAfterFit(Axis.this, new AxisItem(item));
				}
			}
		});

		afterUpdateCallbackProxy.setCallback(new ProxyAfterUpdateCallback() {

			@Override
			public void call(Object context, NativeObject item) {
				if (axisUpdateCallback != null) {
					axisUpdateCallback.onAfterUpdate(Axis.this, new AxisItem(item));
				}
			}
		});
	}
	
	/**
	 * Type of scale being employed.
	 * 
	 * @param type type of axis
	 * @see org.pepstock.charba.client.enums.AxisType
	 */
	public void setType(AxisType type) {
		getScale().setType(type);
	}

	/**
	 * Returns the type of axis.
	 * 
	 * @return the type of axis
	 * @see org.pepstock.charba.client.enums.AxisType
	 */
	public AxisType getType() {
		return getScale().getType();
	}

	/**
	 * If set to false the axis is hidden from view. Overrides gridLines.display, scaleLabel.display, and ticks.display.
	 * 
	 * @param display If set to false the axis is hidden from view. Overrides gridLines.display, scaleLabel.display, and
	 *            ticks.display.
	 */
	public void setDisplay(boolean display) {
		getScale().setDisplay(display);
	}

	/**
	 * Returns if axis is hidden.
	 * 
	 * @return <code>false</code> if axis is hidden, otherwise <code>true</code>.
	 */
	public boolean isDisplay() {
		return getScale().isDisplay();
	}

	/**
	 * The weight used to sort the axis. Higher weights are further away from the chart area.
	 * 
	 * @param weight weight of axis
	 */
	public void setWeight(int weight) {
		getScale().setWeight(weight);
	}

	/**
	 * The weight used to sort the axis. Higher weights are further away from the chart area.
	 * 
	 * @return weight of axis
	 */
	public int getWeight() {
		return getScale().getWeight();
	}

	/**
	 * @return the options
	 */
	final Scale getScale() {
		return super.getConfiguration();
	}

	/**
	 * Returns the global options for this chart.
	 * 
	 * @return the global options for this chart.
	 */
	Scale getDefaultScale() {
		// gets the global option for the chart.
		ChartOptions options = Defaults.get().options(getChart().getType());
		// if is a multi scale chart
		if (getChart().getOptions() instanceof MultiScalesOptions) {
			CartesianAxisType type = null;
			// checks if is cartesian axis
			// only cartesian axis has got the mutli scale
			if (this instanceof CartesianAxis<?>) {
				CartesianAxis<?> cAxis = (CartesianAxis<?>) this;
				// gets cartesian type
				type = cAxis.getCartesianType();
			}
			// if type is consistent
			if (type != null) {
				// returns the option for x or y scale.
				return getCartesianScale(CartesianAxisType.x.equals(type) ? options.getScales().getXAxes() : options.getScales().getYAxes());
			}
		} else if (getChart().getOptions() instanceof SingleScaleOptions) {
			// being a single scale
			// returns scale option
			return options.getScale();
		}
		// returns default scale
		return Defaults.get().getScale();
	}

	/**
	 * Returns the scale option for multi scale chart using the axis type.
	 * 
	 * @param scales X or Y scale option.
	 * @return a scale object with axis configuration
	 */
	private Scale getCartesianScale(List<Scale> scales) {
		// scan all configuration axes
		for (Scale axis : scales) {
			// if configuration type equals to this axis
			if (getType().equals(axis.getType())) {
				// returns scale config
				return axis;
			}
		}
		// returns default scale
		return Defaults.get().getScale();
	}

	/**
	 * @return the axisBuildTicksCallback
	 */
	public AxisBuildTicksCallback getAxisBuildTicksCallback() {
		return axisBuildTicksCallback;
	}
	
	/**
	 * @param axisBuildTicksCallback the axisBuildTicksCallback to set
	 */
	public void setAxisBuildTicksCallback(AxisBuildTicksCallback axisBuildTicksCallback) {
		this.axisBuildTicksCallback = axisBuildTicksCallback;
		if (axisBuildTicksCallback != null) {
			getConfiguration().setCallback(Property.beforeBuildTicks, beforeBuildTicksCallbackProxy.getProxy());
			getConfiguration().setCallback(Property.afterBuildTicks, afterBuildTicksCallbackProxy.getProxy());
		} else {
			getConfiguration().setCallback(Property.beforeBuildTicks, null);
			getConfiguration().setCallback(Property.afterBuildTicks, null);
		}
	}

	/**
	 * @return the axisCalculateTickRotationCallback
	 */
	public AxisCalculateTickRotationCallback getAxisCalculateTickRotationCallback() {
		return axisCalculateTickRotationCallback;
	}

	/**
	 * @param axisCalculateTickRotationCallback the axisCalculateTickRotationCallback to set
	 */
	public void setAxisCalculateTickRotationCallback(AxisCalculateTickRotationCallback axisCalculateTickRotationCallback) {
		this.axisCalculateTickRotationCallback = axisCalculateTickRotationCallback;
		if (axisCalculateTickRotationCallback != null) {
			getConfiguration().setCallback(Property.beforeCalculateTickRotation, beforeCalculateTickRotationCallbackProxy.getProxy());
			getConfiguration().setCallback(Property.afterCalculateTickRotation, afterCalculateTickRotationCallbackProxy.getProxy());
		} else {
			getConfiguration().setCallback(Property.beforeCalculateTickRotation, null);
			getConfiguration().setCallback(Property.afterCalculateTickRotation, null);
		}
	}

	/**
	 * @return the axisDataLimitsCallback
	 */
	public AxisDataLimitsCallback getAxisDataLimitsCallback() {
		return axisDataLimitsCallback;
	}

	/**
	 * @param axisDataLimitsCallback the axisDataLimitsCallback to set
	 */
	public void setAxisDataLimitsCallback(AxisDataLimitsCallback axisDataLimitsCallback) {
		this.axisDataLimitsCallback = axisDataLimitsCallback;
		if (axisDataLimitsCallback != null) {
			getConfiguration().setCallback(Property.beforeDataLimits, beforeDataLimitsCallbackProxy.getProxy());
			getConfiguration().setCallback(Property.afterDataLimits, afterDataLimitsCallbackProxy.getProxy());
		} else {
			getConfiguration().setCallback(Property.beforeDataLimits, null);
			getConfiguration().setCallback(Property.afterDataLimits, null);
		}
	}

	/**
	 * @return the axisDimensionsCallback
	 */
	public AxisDimensionsCallback getAxisDimensionsCallback() {
		return axisDimensionsCallback;
	}

	/**
	 * @param axisDimensionsCallback the axisDimensionsCallback to set
	 */
	public void setAxisDimensionsCallback(AxisDimensionsCallback axisDimensionsCallback) {
		this.axisDimensionsCallback = axisDimensionsCallback;
		if (axisDimensionsCallback != null) {
			getConfiguration().setCallback(Property.beforeSetDimensions, beforeSetDimensionsCallbackProxy.getProxy());
			getConfiguration().setCallback(Property.afterSetDimensions, afterSetDimensionsCallbackProxy.getProxy());
		} else {
			getConfiguration().setCallback(Property.beforeSetDimensions, null);
			getConfiguration().setCallback(Property.afterSetDimensions, null);
		}
	}

	/**
	 * @return the axisFitCallback
	 */
	public AxisFitCallback getAxisFitCallback() {
		return axisFitCallback;
	}

	/**
	 * @param axisFitCallback the axisFitCallback to set
	 */
	public void setAxisFitCallback(AxisFitCallback axisFitCallback) {
		this.axisFitCallback = axisFitCallback;
		if (axisFitCallback != null) {
			getConfiguration().setCallback(Property.beforeFit, beforeFitCallbackProxy.getProxy());
			getConfiguration().setCallback(Property.afterFit, afterFitCallbackProxy.getProxy());
		} else {
			getConfiguration().setCallback(Property.beforeFit, null);
			getConfiguration().setCallback(Property.afterFit, null);
		}
	}

	/**
	 * @return the axisTickToLabelConversionCallback
	 */
	public AxisTickToLabelConversionCallback getAxisTickToLabelConversionCallback() {
		return axisTickToLabelConversionCallback;
	}

	/**
	 * @param axisTickToLabelConversionCallback the axisTickToLabelConversionCallback to set
	 */
	public void setAxisTickToLabelConversionCallback(AxisTickToLabelConversionCallback axisTickToLabelConversionCallback) {
		this.axisTickToLabelConversionCallback = axisTickToLabelConversionCallback;
		if (axisTickToLabelConversionCallback != null) {
			getConfiguration().setCallback(Property.beforeTickToLabelConversion, beforeTickToLabelConversionCallbackProxy.getProxy());
			getConfiguration().setCallback(Property.afterTickToLabelConversion, afterTickToLabelConversionCallbackProxy.getProxy());
		} else {
			getConfiguration().setCallback(Property.beforeTickToLabelConversion, null);
			getConfiguration().setCallback(Property.afterTickToLabelConversion, null);
		}
	}

	/**
	 * @return the axisUpdateCallback
	 */
	public AxisUpdateCallback getAxisUpdateCallback() {
		return axisUpdateCallback;
	}

	/**
	 * @param axisUpdateCallback the axisUpdateCallback to set
	 */
	public void setAxisUpdateCallback(AxisUpdateCallback axisUpdateCallback) {
		this.axisUpdateCallback = axisUpdateCallback;
		if (axisUpdateCallback != null) {
			getConfiguration().setCallback(Property.beforeUpdate, beforeUpdateCallbackProxy.getProxy());
			getConfiguration().setCallback(Property.afterUpdate, afterUpdateCallbackProxy.getProxy());
		} else {
			getConfiguration().setCallback(Property.beforeUpdate, null);
			getConfiguration().setCallback(Property.afterUpdate, null);
		}
	}
	
}