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

import org.pepstock.charba.client.ScaleType;
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
 * @version 2.0
 *
 */
public abstract class Axis extends ConfigurationContainer<ExtendedScale> {
	
	// ---------------------------
	// -- JAVASCRIPT FUNCTIONS ---
	// ---------------------------
	
	/**
	 * Java script FUNCTION callback called before the update process starts.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 * @version 2.0
	 */
	@JsFunction
	interface ProxyBeforeUpdateCallback {
		
		/**
		 * Method of function to be called before the update process starts.
		 * @param context Value of <code>this</code> to the execution context of function.
		 * @param item native object of axis
		 */
		void call(Object context, NativeObject item);
	}

	/**
	 * Java script FUNCTION callback that runs before dimensions are set.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 * @version 2.0
	 */
	@JsFunction
	interface ProxyBeforeSetDimensionsCallback {
		
		/**
		 * Method of function to be called that runs before dimensions are set.
		 * @param context Value of <code>this</code> to the execution context of function.
		 * @param item native object of axis
		 */
		void call(Object context, NativeObject item);
	}

	/**
	 * Java script FUNCTION callback that runs after dimensions are set.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 * @version 2.0
	 */
	@JsFunction
	interface ProxyAfterSetDimensionsCallback {
		
		/**
		 * Method of function to be called that runs after dimensions are set.
		 * @param context Value of <code>this</code> to the execution context of function.
		 * @param item native object of axis
		 */
		void call(Object context, NativeObject item);
	}

	/**
	 * Java script FUNCTION callback that runs before data limits are determined.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 * @version 2.0
	 */
	@JsFunction
	interface ProxyBeforeDataLimitsCallback {
		
		/**
		 * Method of function to be called that runs before data limits are determined.
		 * @param context Value of <code>this</code> to the execution context of function.
		 * @param item native object of axis
		 */
		void call(Object context, NativeObject item);
	}

	/**
	 * Java script FUNCTION callback that runs after data limits are determined.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 * @version 2.0
	 */
	@JsFunction
	interface ProxyAfterDataLimitsCallback {
		
		/**
		 * Method of function to be called that runs after data limits are determined.
		 * @param context Value of <code>this</code> to the execution context of function.
		 * @param item native object of axis
		 */
		void call(Object context, NativeObject item);
	}

	/**
	 * Java script FUNCTION callback that runs before ticks are created.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 * @version 2.0
	 */
	@JsFunction
	interface ProxyBeforeBuildTicksCallback {
		
		/**
		 * Method of function to be called that runs before ticks are created.
		 * @param context Value of <code>this</code> to the execution context of function.
		 * @param item native object of axis
		 */
		void call(Object context, NativeObject item);
	}

	/**
	 * Java script FUNCTION callback that runs after ticks are created. Useful for filtering ticks.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 * @version 2.0
	 */
	@JsFunction
	interface ProxyAfterBuildTicksCallback {
		
		/**
		 * Method of function to be called that runs after ticks are created. Useful for filtering ticks.
		 * @param context Value of <code>this</code> to the execution context of function.
		 * @param item native object of axis
		 */
		void call(Object context, NativeObject item);
	}

	/**
	 * Java script FUNCTION callback that runs before ticks are converted into strings.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 * @version 2.0
	 */
	@JsFunction
	interface ProxyBeforeTickToLabelConversionCallback {
		
		/**
		 * Method of function to be called that runs before ticks are converted into strings.
		 * @param context Value of <code>this</code> to the execution context of function.
		 * @param item native object of axis
		 */
		void call(Object context, NativeObject item);
	}
	 
	/**
	 * Java script FUNCTION callback that runs after ticks are converted into strings.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 * @version 2.0
	 */ 
	@JsFunction
	interface ProxyAfterTickToLabelConversionCallback {
		
		/**
		 * Method of function to be called that runs after ticks are converted into strings.
		 * @param context Value of <code>this</code> to the execution context of function.
		 * @param item native object of axis
		 */
		void call(Object context, NativeObject item);
	}

	/**
	 * Java script FUNCTION callback that runs before tick rotation is determined.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 * @version 2.0
	 */ 
	@JsFunction
	interface ProxyBeforeCalculateTickRotationCallback {
		
		/**
		 * Method of function to be called that runs before tick rotation is determined.
		 * @param context Value of <code>this</code> to the execution context of function.
		 * @param item native object of axis
		 */
		void call(Object context, NativeObject item);
	}

	/**
	 * Java script FUNCTION callback that runs after tick rotation is determined.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 * @version 2.0
	 */ 
	@JsFunction
	interface ProxyAfterCalculateTickRotationCallback {
		
		/**
		 * Method of function to be called that runs before tick rotation is determined.
		 * @param context Value of <code>this</code> to the execution context of function.
		 * @param item native object of axis
		 */
		void call(Object context, NativeObject item);
	}

	/**
	 * Java script FUNCTION callback that runs before the scale fits to the canvas.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 * @version 2.0
	 */
	@JsFunction
	interface ProxyBeforeFitCallback {
		
		/**
		 * Method of function to be called that runs before the scale fits to the canvas.
		 * @param context Value of <code>this</code> to the execution context of function.
		 * @param item native object of axis
		 */
		void call(Object context, NativeObject item);
	}

	/**
	 * Java script FUNCTION callback that runs after the scale fits to the canvas.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 * @version 2.0
	 */
	@JsFunction
	interface ProxyAfterFitCallback {
		
		/**
		 * Method of function to be called that runs after the scale fits to the canvas.
		 * @param context Value of <code>this</code> to the execution context of function.
		 * @param item native object of axis
		 */
		void call(Object context, NativeObject item);
	}
	
	/**
	 * Java script FUNCTION callback that runs at the end of the update process.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 * @version 2.0
	 */
	@JsFunction
	interface ProxyAfterUpdateCallback {
		
		/**
		 * Method of function to be called that runs at the end of the update process.
		 * @param context Value of <code>this</code> to the execution context of function.
		 * @param item native object of axis
		 */		
		void call(Object context, NativeObject item);
	}
	
	// ---------------------------
	// -- CALLBACKS PROXIES    ---
	// ---------------------------
	
	// callback proxy to invoke the before update function
	private final CallbackProxy<ProxyBeforeUpdateCallback> beforeUpdateCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the before set dimension function
	private final CallbackProxy<ProxyBeforeSetDimensionsCallback> beforeSetDimensionsCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the after set dimension function
	private final CallbackProxy<ProxyAfterSetDimensionsCallback> afterSetDimensionsCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the before data limit function
	private final CallbackProxy<ProxyBeforeDataLimitsCallback> beforeDataLimitsCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the after data limit function
	private final CallbackProxy<ProxyAfterDataLimitsCallback> afterDataLimitsCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the before build tricks function
	private final CallbackProxy<ProxyBeforeBuildTicksCallback> beforeBuildTicksCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the after build tricks function
	private final CallbackProxy<ProxyAfterBuildTicksCallback> afterBuildTicksCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the before tick label conversion function
	private final CallbackProxy<ProxyBeforeTickToLabelConversionCallback> beforeTickToLabelConversionCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the after tick label conversion function
	private final CallbackProxy<ProxyAfterTickToLabelConversionCallback> afterTickToLabelConversionCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the before calculate tick rotation function
	private final CallbackProxy<ProxyBeforeCalculateTickRotationCallback> beforeCalculateTickRotationCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the after calculate tick rotation function
	private final CallbackProxy<ProxyAfterCalculateTickRotationCallback> afterCalculateTickRotationCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the before fit function
	private final CallbackProxy<ProxyBeforeFitCallback> beforeFitCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the after fit function
	private final CallbackProxy<ProxyAfterFitCallback> afterFitCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the after update function
	private final CallbackProxy<ProxyAfterUpdateCallback> afterUpdateCallbackProxy = JsHelper.get().newCallbackProxy();
	
	// ---------------------------
	// -- USERS CALLBACKS      ---
	// ---------------------------

	// user callbacks implementation for build ticks
	private AxisBuildTicksCallback axisBuildTicksCallback = null;
	// user callbacks implementation for tick rotation calculation
	private AxisCalculateTickRotationCallback axisCalculateTickRotationCallback = null;
	// user callbacks implementation for data limits
	private AxisDataLimitsCallback axisDataLimitsCallback = null;
	// user callbacks implementation for dimension set
	private AxisDimensionsCallback axisDimensionsCallback = null;
	// user callbacks implementation for fit
	private AxisFitCallback axisFitCallback = null;
	// user callbacks implementation for tick label conversion
	private AxisTickToLabelConversionCallback axisTickToLabelConversionCallback = null;
	// user callbacks implementation for update
	private AxisUpdateCallback axisUpdateCallback = null;
	
	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
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
		// sets the options (scale) to map attributes
		// getting the defaults values for scales 
		setConfiguration(new ExtendedScale(new DefaultChartScale(getDefaultScale())));
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		beforeUpdateCallbackProxy.setCallback(new ProxyBeforeUpdateCallback() {

			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.jsinterop.configuration.Axis.ProxyBeforeUpdateCallback#call(java.lang.Object, org.pepstock.charba.client.jsinterop.commons.NativeObject)
			 */
			@Override
			public void call(Object context, NativeObject item) {
				// if user callback is consistent
				if (axisUpdateCallback != null) {
					// then it is called
					axisUpdateCallback.onBeforeUpdate(Axis.this, new AxisItem(item));
				}
			}
		});
		beforeSetDimensionsCallbackProxy.setCallback(new ProxyBeforeSetDimensionsCallback() {
			
			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.jsinterop.configuration.Axis.ProxyBeforeSetDimensionsCallback#call(java.lang.Object, org.pepstock.charba.client.jsinterop.commons.NativeObject)
			 */
			@Override
			public void call(Object context, NativeObject item) {
				// if user callback is consistent
				if (axisDimensionsCallback != null) {
					// then it is called
					axisDimensionsCallback.onBeforeSetDimensions(Axis.this, new AxisItem(item));
				}
			}
		});
		afterSetDimensionsCallbackProxy.setCallback(new ProxyAfterSetDimensionsCallback() {

			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.jsinterop.configuration.Axis.ProxyAfterSetDimensionsCallback#call(java.lang.Object, org.pepstock.charba.client.jsinterop.commons.NativeObject)
			 */
			@Override
			public void call(Object context, NativeObject item) {
				// if user callback is consistent
				if (axisDimensionsCallback != null) {
					// then it is called
					axisDimensionsCallback.onAfterSetDimensions(Axis.this, new AxisItem(item));
				}
			}
		});
		beforeDataLimitsCallbackProxy.setCallback(new ProxyBeforeDataLimitsCallback() {

			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.jsinterop.configuration.Axis.ProxyBeforeDataLimitsCallback#call(java.lang.Object, org.pepstock.charba.client.jsinterop.commons.NativeObject)
			 */
			@Override
			public void call(Object context, NativeObject item) {
				// if user callback is consistent
				if (axisDataLimitsCallback != null) {
					// then it is called
					axisDataLimitsCallback.onBeforeDataLimits(Axis.this, new AxisItem(item));
				}
			}
		});
		afterDataLimitsCallbackProxy.setCallback(new ProxyAfterDataLimitsCallback() {

			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.jsinterop.configuration.Axis.ProxyAfterDataLimitsCallback#call(java.lang.Object, org.pepstock.charba.client.jsinterop.commons.NativeObject)
			 */
			@Override
			public void call(Object context, NativeObject item) {
				// if user callback is consistent
				if (axisDataLimitsCallback != null) {
					// then it is called
					axisDataLimitsCallback.onAfterDataLimits(Axis.this, new AxisItem(item));
				}
			}
		});
		beforeBuildTicksCallbackProxy.setCallback(new ProxyBeforeBuildTicksCallback() {

			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.jsinterop.configuration.Axis.ProxyBeforeBuildTicksCallback#call(java.lang.Object, org.pepstock.charba.client.jsinterop.commons.NativeObject)
			 */
			@Override
			public void call(Object context, NativeObject item) {
				// if user callback is consistent
				if (axisBuildTicksCallback != null) {
					// then it is called
					axisBuildTicksCallback.onBeforeBuildTicks(Axis.this, new AxisItem(item));
				}
			}
		});
		afterBuildTicksCallbackProxy.setCallback(new ProxyAfterBuildTicksCallback() {

			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.jsinterop.configuration.Axis.ProxyAfterBuildTicksCallback#call(java.lang.Object, org.pepstock.charba.client.jsinterop.commons.NativeObject)
			 */
			@Override
			public void call(Object context, NativeObject item) {
				// if user callback is consistent
				if (axisBuildTicksCallback != null) {
					// then it is called
					axisBuildTicksCallback.onAfterBuildTicks(Axis.this, new AxisItem(item));
				}
			}
		});
		beforeTickToLabelConversionCallbackProxy.setCallback(new ProxyBeforeTickToLabelConversionCallback() {

			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.jsinterop.configuration.Axis.ProxyBeforeTickToLabelConversionCallback#call(java.lang.Object, org.pepstock.charba.client.jsinterop.commons.NativeObject)
			 */
			@Override
			public void call(Object context, NativeObject item) {
				// if user callback is consistent
				if (axisTickToLabelConversionCallback != null) {
					// then it is called
					axisTickToLabelConversionCallback.onBeforeTickToLabelConversion(Axis.this, new AxisItem(item));
				}
			}
		});
		afterTickToLabelConversionCallbackProxy.setCallback(new ProxyAfterTickToLabelConversionCallback() {

			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.jsinterop.configuration.Axis.ProxyAfterTickToLabelConversionCallback#call(java.lang.Object, org.pepstock.charba.client.jsinterop.commons.NativeObject)
			 */
			@Override
			public void call(Object context, NativeObject item) {
				// if user callback is consistent
				if (axisTickToLabelConversionCallback != null) {
					// then it is called
					axisTickToLabelConversionCallback.onAfterTickToLabelConversion(Axis.this, new AxisItem(item));
				}
			}
		});
		beforeCalculateTickRotationCallbackProxy.setCallback(new ProxyBeforeCalculateTickRotationCallback() {

			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.jsinterop.configuration.Axis.ProxyBeforeCalculateTickRotationCallback#call(java.lang.Object, org.pepstock.charba.client.jsinterop.commons.NativeObject)
			 */
			@Override
			public void call(Object context, NativeObject item) {
				// if user callback is consistent
				if (axisCalculateTickRotationCallback != null) {
					// then it is called
					axisCalculateTickRotationCallback.onBeforeCalculateTickRotation(Axis.this, new AxisItem(item));
				}
			}
		});
		afterCalculateTickRotationCallbackProxy.setCallback(new ProxyAfterCalculateTickRotationCallback() {

			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.jsinterop.configuration.Axis.ProxyAfterCalculateTickRotationCallback#call(java.lang.Object, org.pepstock.charba.client.jsinterop.commons.NativeObject)
			 */
			@Override
			public void call(Object context, NativeObject item) {
				// if user callback is consistent
				if (axisCalculateTickRotationCallback != null) {
					// then it is called
					axisCalculateTickRotationCallback.onAfterCalculateTickRotation(Axis.this, new AxisItem(item));
				}
			}
		});
		beforeFitCallbackProxy.setCallback(new ProxyBeforeFitCallback() {

			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.jsinterop.configuration.Axis.ProxyBeforeFitCallback#call(java.lang.Object, org.pepstock.charba.client.jsinterop.commons.NativeObject)
			 */
			@Override
			public void call(Object context, NativeObject item) {
				// if user callback is consistent
				if (axisFitCallback != null) {
					// then it is called
					axisFitCallback.onBeforeFit(Axis.this, new AxisItem(item));
				}
			}
		});
		afterFitCallbackProxy.setCallback(new ProxyAfterFitCallback() {

			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.jsinterop.configuration.Axis.ProxyAfterFitCallback#call(java.lang.Object, org.pepstock.charba.client.jsinterop.commons.NativeObject)
			 */
			@Override
			public void call(Object context, NativeObject item) {
				// if user callback is consistent
				if (axisFitCallback != null) {
					// then it is called
					axisFitCallback.onAfterFit(Axis.this, new AxisItem(item));
				}
			}
		});
		afterUpdateCallbackProxy.setCallback(new ProxyAfterUpdateCallback() {

			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.jsinterop.configuration.Axis.ProxyAfterUpdateCallback#call(java.lang.Object, org.pepstock.charba.client.jsinterop.commons.NativeObject)
			 */
			@Override
			public void call(Object context, NativeObject item) {
				// if user callback is consistent
				if (axisUpdateCallback != null) {
					// then it is called
					axisUpdateCallback.onAfterUpdate(Axis.this, new AxisItem(item));
				}
			}
		});
	}
	
	/**
	 * Type of scale being employed.
	 * 
	 * @param type type of axis
	 */
	public void setType(AxisType type) {
		getScale().setType(type);
	}

	/**
	 * Returns the type of axis.
	 * 
	 * @return the type of axis
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
		if (ScaleType.multi.equals(getChart().getType().scaleType())) {
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
		} else if (ScaleType.single.equals(getChart().getType().scaleType())) {
			// being a single scale
			// returns scale option
			return options.getScale();
		}
		// returns default scale
		return Defaults.get().getScale();
	}

	/**
	 * Returns the scale option for multi-scale chart using the axis type.
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
	 * Returns the user callback that runs before/after ticks are created.
	 * 
	 * @return the axisBuildTicksCallback 
	 */
	public AxisBuildTicksCallback getAxisBuildTicksCallback() {
		return axisBuildTicksCallback;
	}
	
	/**
	 * Sets the user callback that runs before/after ticks are created.
	 * 
	 * @param axisBuildTicksCallback the axisBuildTicksCallback to set
	 */
	public void setAxisBuildTicksCallback(AxisBuildTicksCallback axisBuildTicksCallback) {
		// sets the callback
		this.axisBuildTicksCallback = axisBuildTicksCallback;
		// checks if callback is consistent
		if (axisBuildTicksCallback != null) {
			// adds the callback proxy function to java script object
			getConfiguration().setCallback(Property.beforeBuildTicks, beforeBuildTicksCallbackProxy.getProxy());
			getConfiguration().setCallback(Property.afterBuildTicks, afterBuildTicksCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			getConfiguration().setCallback(Property.beforeBuildTicks, null);
			getConfiguration().setCallback(Property.afterBuildTicks, null);
		}
	}

	/**
	 * Returns the user callback that runs before/after tick rotation is determined.
	 * 
	 * @return the axisCalculateTickRotationCallback
	 */
	public AxisCalculateTickRotationCallback getAxisCalculateTickRotationCallback() {
		return axisCalculateTickRotationCallback;
	}

	/**
	 * Sets the user callback that runs before/after tick rotation is determined.
	 * 
	 * @param axisCalculateTickRotationCallback the axisCalculateTickRotationCallback to set
	 */
	public void setAxisCalculateTickRotationCallback(AxisCalculateTickRotationCallback axisCalculateTickRotationCallback) {
		// sets the callback
		this.axisCalculateTickRotationCallback = axisCalculateTickRotationCallback;
		// checks if callback is consistent
		if (axisCalculateTickRotationCallback != null) {
			// adds the callback proxy function to java script object
			getConfiguration().setCallback(Property.beforeCalculateTickRotation, beforeCalculateTickRotationCallbackProxy.getProxy());
			getConfiguration().setCallback(Property.afterCalculateTickRotation, afterCalculateTickRotationCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			getConfiguration().setCallback(Property.beforeCalculateTickRotation, null);
			getConfiguration().setCallback(Property.afterCalculateTickRotation, null);
		}
	}

	/**
	 * Returns the user callback that runs before/after data limits are determined.
	 * 
	 * @return the axisDataLimitsCallback
	 */
	public AxisDataLimitsCallback getAxisDataLimitsCallback() {
		return axisDataLimitsCallback;
	}

	/**
	 * Sets the user callback that runs before/after data limits are determined.
	 * 
	 * @param axisDataLimitsCallback the axisDataLimitsCallback to set
	 */
	public void setAxisDataLimitsCallback(AxisDataLimitsCallback axisDataLimitsCallback) {
		// sets the callback
		this.axisDataLimitsCallback = axisDataLimitsCallback;
		// checks if callback is consistent
		if (axisDataLimitsCallback != null) {
			// adds the callback proxy function to java script object
			getConfiguration().setCallback(Property.beforeDataLimits, beforeDataLimitsCallbackProxy.getProxy());
			getConfiguration().setCallback(Property.afterDataLimits, afterDataLimitsCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			getConfiguration().setCallback(Property.beforeDataLimits, null);
			getConfiguration().setCallback(Property.afterDataLimits, null);
		}
	}

	/**
	 * Returns the user callback that runs before/after dimensions are set.
	 * 
	 * @return the axisDimensionsCallback
	 */
	public AxisDimensionsCallback getAxisDimensionsCallback() {
		return axisDimensionsCallback;
	}

	/**
	 * Sets the user callback that runs before/after dimensions are set.
	 * 
	 * @param axisDimensionsCallback the axisDimensionsCallback to set
	 */
	public void setAxisDimensionsCallback(AxisDimensionsCallback axisDimensionsCallback) {
		// sets the callback
		this.axisDimensionsCallback = axisDimensionsCallback;
		// checks if callback is consistent
		if (axisDimensionsCallback != null) {
			// adds the callback proxy function to java script object
			getConfiguration().setCallback(Property.beforeSetDimensions, beforeSetDimensionsCallbackProxy.getProxy());
			getConfiguration().setCallback(Property.afterSetDimensions, afterSetDimensionsCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			getConfiguration().setCallback(Property.beforeSetDimensions, null);
			getConfiguration().setCallback(Property.afterSetDimensions, null);
		}
	}

	/**
	 * Returns the user callback that runs before/after the scale fits to the canvas.
	 * 
	 * @return the axisFitCallback
	 */
	public AxisFitCallback getAxisFitCallback() {
		return axisFitCallback;
	}

	/**
	 * Sets the user callback that runs before/after the scale fits to the canvas.
	 * 
	 * @param axisFitCallback the axisFitCallback to set
	 */
	public void setAxisFitCallback(AxisFitCallback axisFitCallback) {
		// sets the callback
		this.axisFitCallback = axisFitCallback;
		// checks if callback is consistent
		if (axisFitCallback != null) {
			// adds the callback proxy function to java script object
			getConfiguration().setCallback(Property.beforeFit, beforeFitCallbackProxy.getProxy());
			getConfiguration().setCallback(Property.afterFit, afterFitCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			getConfiguration().setCallback(Property.beforeFit, null);
			getConfiguration().setCallback(Property.afterFit, null);
		}
	}

	/**
	 * Returns the user callback that runs before/after ticks are converted into strings.
	 * 
	 * @return the axisTickToLabelConversionCallback
	 */
	public AxisTickToLabelConversionCallback getAxisTickToLabelConversionCallback() {
		return axisTickToLabelConversionCallback;
	}

	/**
	 * Sets the user callback that runs before/after ticks are converted into strings.
	 * 
	 * @param axisTickToLabelConversionCallback the axisTickToLabelConversionCallback to set
	 */
	public void setAxisTickToLabelConversionCallback(AxisTickToLabelConversionCallback axisTickToLabelConversionCallback) {
		// sets the callback
		this.axisTickToLabelConversionCallback = axisTickToLabelConversionCallback;
		// checks if callback is consistent
		if (axisTickToLabelConversionCallback != null) {
			// adds the callback proxy function to java script object
			getConfiguration().setCallback(Property.beforeTickToLabelConversion, beforeTickToLabelConversionCallbackProxy.getProxy());
			getConfiguration().setCallback(Property.afterTickToLabelConversion, afterTickToLabelConversionCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			getConfiguration().setCallback(Property.beforeTickToLabelConversion, null);
			getConfiguration().setCallback(Property.afterTickToLabelConversion, null);
		}
	}

	/**
	 * Returns the user callback that runs before/after of the update process.
	 * 
	 * @return the axisUpdateCallback
	 */
	public AxisUpdateCallback getAxisUpdateCallback() {
		return axisUpdateCallback;
	}

	/**
	 * Sets the user callback that runs before/after of the update process.
	 * 
	 * @param axisUpdateCallback the axisUpdateCallback to set
	 */
	public void setAxisUpdateCallback(AxisUpdateCallback axisUpdateCallback) {
		// sets the callback
		this.axisUpdateCallback = axisUpdateCallback;
		// checks if callback is consistent
		if (axisUpdateCallback != null) {
			// adds the callback proxy function to java script object
			getConfiguration().setCallback(Property.beforeUpdate, beforeUpdateCallbackProxy.getProxy());
			getConfiguration().setCallback(Property.afterUpdate, afterUpdateCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			getConfiguration().setCallback(Property.beforeUpdate, null);
			getConfiguration().setCallback(Property.afterUpdate, null);
		}
	}
	
}