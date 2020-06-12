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

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.ScaleType;
import org.pepstock.charba.client.callbacks.AxisCalculateTickRotationCallback;
import org.pepstock.charba.client.callbacks.AxisDataLimitsCallback;
import org.pepstock.charba.client.callbacks.AxisDimensionsCallback;
import org.pepstock.charba.client.callbacks.AxisFitCallback;
import org.pepstock.charba.client.callbacks.AxisTickToLabelConversionCallback;
import org.pepstock.charba.client.callbacks.AxisUpdateCallback;
import org.pepstock.charba.client.callbacks.CallbackFunctionContext;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.IsDefaultScale;
import org.pepstock.charba.client.defaults.IsDefaultScaledOptions;
import org.pepstock.charba.client.enums.AxisType;
import org.pepstock.charba.client.enums.CartesianAxisType;
import org.pepstock.charba.client.enums.DefaultScaleId;
import org.pepstock.charba.client.enums.Display;
import org.pepstock.charba.client.items.AxisItem;
import org.pepstock.charba.client.options.ExtendedScale;
import org.pepstock.charba.client.options.IsScaleId;
import org.pepstock.charba.client.options.Scale;

import jsinterop.annotations.JsFunction;

/**
 * Axes are an integral part of a chart.<br>
 * They are used to determine how data maps to a pixel value on the chart.<br>
 * It contains a number of config callbacks that can be used to change parameters in the scale at different points in the update process.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public abstract class Axis extends ConfigurationContainer<ExtendedScale> {

	// ---------------------------
	// -- JAVASCRIPT FUNCTIONS ---
	// ---------------------------
	/**
	 * Java script FUNCTION callback called to invoke a custom callback for axis.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyAxisCallback {

		/**
		 * Method of function to be called to invoke a custom callback for axis.
		 * 
		 * @param context value of <code>this</code> to the execution context of function.
		 * @param item native object of axis
		 */
		void call(CallbackFunctionContext context, NativeObject item);
	}

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------

	// callback proxy to invoke the before update function
	private final CallbackProxy<ProxyAxisCallback> beforeUpdateCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the before set dimension function
	private final CallbackProxy<ProxyAxisCallback> beforeSetDimensionsCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the after set dimension function
	private final CallbackProxy<ProxyAxisCallback> afterSetDimensionsCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the before data limit function
	private final CallbackProxy<ProxyAxisCallback> beforeDataLimitsCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the after data limit function
	private final CallbackProxy<ProxyAxisCallback> afterDataLimitsCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the before tick label conversion function
	private final CallbackProxy<ProxyAxisCallback> beforeTickToLabelConversionCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the after tick label conversion function
	private final CallbackProxy<ProxyAxisCallback> afterTickToLabelConversionCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the before calculate tick rotation function
	private final CallbackProxy<ProxyAxisCallback> beforeCalculateTickRotationCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the after calculate tick rotation function
	private final CallbackProxy<ProxyAxisCallback> afterCalculateTickRotationCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the before fit function
	private final CallbackProxy<ProxyAxisCallback> beforeFitCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the after fit function
	private final CallbackProxy<ProxyAxisCallback> afterFitCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the after update function
	private final CallbackProxy<ProxyAxisCallback> afterUpdateCallbackProxy = JsHelper.get().newCallbackProxy();

	// ---------------------------
	// -- USERS CALLBACKS ---
	// ---------------------------
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
	// stores axis type
	private final AxisType storeType;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		BEFORE_UPDATE("beforeUpdate"),
		AFTER_UPDATE("afterUpdate"),
		BEFORE_SET_DIMENSIONS("beforeSetDimensions"),
		AFTER_SET_DIMENSIONS("afterSetDimensions"),
		BEFORE_DATA_LIMITS("beforeDataLimits"),
		AFTER_DATA_LIMITS("afterDataLimits"),
		BEFORE_BUILD_TICKS("beforeBuildTicks"),
		AFTER_BUILD_TICKS("afterBuildTicks"),
		BEFORE_TICK_TO_LABEL_CONVERSION("beforeTickToLabelConversion"),
		AFTER_TICK_TO_LABEL_CONVERSION("afterTickToLabelConversion"),
		BEFORE_CALCULATE_TICK_ROTATION("beforeCalculateTickRotation"),
		AFTER_CALCULATE_TICK_ROTATION("afterCalculateTickRotation"),
		BEFORE_FIT("beforeFit"),
		AFTER_FIT("afterFit");

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
	 * Builds the object storing the chart instance.
	 * 
	 * @param chart chart instance
	 * @param id axis id
	 * @param type axis type
	 * @param cartesiantype cartesian type
	 */
	Axis(IsChart chart, IsScaleId id, AxisType type, CartesianAxisType cartesiantype) {
		super(chart);
		// checks if id is consistent
		IsScaleId.checkIfValid(id);
		// checks cartesian type
		Key.checkIfValid(cartesiantype);
		// stores internally the axis type
		this.storeType = Key.checkAndGetIfValid(type);
		// sets the options (scale) to map attributes
		// getting the defaults values for scales
		setConfiguration(new ExtendedScale(id, getDefaultScale(cartesiantype)));
		// stores axis type
		setType(type);
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		beforeUpdateCallbackProxy.setCallback((context, item) -> onBeforeUpdateCallback(item));
		beforeSetDimensionsCallbackProxy.setCallback((context, item) -> onBeforeSetDimensionsCallback(item));
		afterSetDimensionsCallbackProxy.setCallback((context, item) -> onAfterSetDimensionsCallback(item));
		beforeDataLimitsCallbackProxy.setCallback((context, item) -> onBeforeDataLimitsCallback(item));
		afterDataLimitsCallbackProxy.setCallback((context, item) -> onAfterDataLimitsCallback(item));
		beforeTickToLabelConversionCallbackProxy.setCallback((context, item) -> onBeforeTickToLabelConversionCallback(item));
		afterTickToLabelConversionCallbackProxy.setCallback((context, item) -> onAfterTickToLabelConversionCallback(item));
		beforeCalculateTickRotationCallbackProxy.setCallback((context, item) -> onBeforeCalculateTickRotationCallback(item));
		afterCalculateTickRotationCallbackProxy.setCallback((context, item) -> onAfterCalculateTickRotationCallback(item));
		beforeFitCallbackProxy.setCallback((context, item) -> onBeforeFitCallback(item));
		afterFitCallbackProxy.setCallback((context, item) -> onAfterFitCallback(item));
		afterUpdateCallbackProxy.setCallback((context, item) -> onAfterUpdateCallback(item));
	}

	/**
	 * Returns the unique id of axis.
	 * 
	 * @return the unique id of axis
	 */
	public final int getCharbaId() {
		return getConfiguration().getCharbaId();
	}

	/**
	 * The ID is used to link datasets and scale axes together.<br>
	 * This is especially needed if multi-axes charts are used.
	 * 
	 * @return The ID is used to link datasets and scale axes together or {@link DefaultScaleId#UNKNOWN} if not set
	 */
	public final IsScaleId getId() {
		return getConfiguration().getId();
	}

	/**
	 * Type of scale being employed.
	 * 
	 * @param type type of axis
	 */
	private void setType(AxisType type) {
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
	 * @param display If set to false the axis is hidden from view. Overrides gridLines.display, scaleLabel.display, and ticks.display.
	 */
	public void setDisplay(boolean display) {
		getScale().setDisplay(display);
	}

	/**
	 * The display option controls the visibility of axis.<br>
	 * Controls the axis global visibility (visible when true, hidden when false). When display: 'auto', the axis is visible only if at least one associated dataset is visible.
	 * 
	 * @param display display option controls the visibility of axis
	 */
	public final void setDisplay(Display display) {
		getScale().setDisplay(display);
	}

	/**
	 * The display option controls the visibility of axis.<br>
	 * Controls the axis global visibility (visible when true, hidden when false). When display: 'auto', the axis is visible only if at least one associated dataset is visible.
	 * 
	 * @return display option controls the visibility of axis
	 */
	public final Display getDisplay() {
		return getScale().getDisplay();
	}

	/**
	 * The weight used to sort the axis. Higher weights are further away from the chart area.
	 * 
	 * @param weight weight of axis
	 */
	public void setWeight(double weight) {
		getScale().setWeight(weight);
	}

	/**
	 * The weight used to sort the axis. Higher weights are further away from the chart area.
	 * 
	 * @return weight of axis
	 */
	public double getWeight() {
		return getScale().getWeight();
	}
	
	/**
	 * Sets the reverses order of tick labels.
	 * 
	 * @param reverse reverses order of tick labels.
	 */
	public void setReverse(boolean reverse) {
		getScale().setReverse(reverse);
	}

	/**
	 * Returns the reverses order of tick labels.
	 * 
	 * @return reverses order of tick labels.
	 */
	public boolean isReverse() {
		return getScale().isReverse();
	}


	/**
	 * Returns the scale configuration
	 * 
	 * @return the scale configuration
	 */
	final Scale getScale() {
		return super.getConfiguration();
	}

	/**
	 * Returns the global options for this chart.
	 * 
	 * @param cartesiantype cartesian type
	 * @return the global options for this chart.
	 */
	private IsDefaultScale getDefaultScale(CartesianAxisType cartesiantype) {
		// gets the global option for the chart.
		IsDefaultScaledOptions options = getChart().getDefaultChartOptions();
		// if is a multi scale chart
		if (ScaleType.MULTI.equals(getChart().getType().scaleType())) {
			// checks if is cartesian axis
			// only cartesian axis has got the multi scale
			if (Key.isValid(cartesiantype)) {
				// returns the option for x or y scale.
				return getCartesianScale(CartesianAxisType.X.equals(cartesiantype) ? options.getScales().getXAxis() : options.getScales().getYAxis());
			}
		} else if (ScaleType.SINGLE.equals(getChart().getType().scaleType())) {
			// being a single scale
			// returns scale option
			return options.getScale();
		}
		// returns default scale
		return Defaults.get().getScale(this.storeType);
	}

	/**
	 * Returns the scale option for multi-scale chart using the axis type.
	 * 
	 * @param axis X or Y scale option.
	 * @return a scale object with axis configuration
	 */
	private IsDefaultScale getCartesianScale(IsDefaultScale axis) {
		// if configuration type equals to this axis
		if (storeType.equals(axis.getType())) {
			// returns scale config
			return axis;
		}
		// returns default scale
		return Defaults.get().getScale(this.storeType);
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
			getConfiguration().setCallback(Property.BEFORE_CALCULATE_TICK_ROTATION, beforeCalculateTickRotationCallbackProxy.getProxy());
			getConfiguration().setCallback(Property.AFTER_CALCULATE_TICK_ROTATION, afterCalculateTickRotationCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			getConfiguration().setCallback(Property.BEFORE_CALCULATE_TICK_ROTATION, null);
			getConfiguration().setCallback(Property.AFTER_CALCULATE_TICK_ROTATION, null);
		}
	}

	/**
	 * Returns the user callback that runs before/after data limits are determined.
	 * 
	 * @return the user callback that runs before/after data limits are determined
	 */
	public AxisDataLimitsCallback getAxisDataLimitsCallback() {
		return axisDataLimitsCallback;
	}

	/**
	 * Sets the user callback that runs before/after data limits are determined.
	 * 
	 * @param axisDataLimitsCallback the user callback that runs before/after data limits are determined
	 */
	public void setAxisDataLimitsCallback(AxisDataLimitsCallback axisDataLimitsCallback) {
		// sets the callback
		this.axisDataLimitsCallback = axisDataLimitsCallback;
		// checks if callback is consistent
		if (axisDataLimitsCallback != null) {
			// adds the callback proxy function to java script object
			getConfiguration().setCallback(Property.BEFORE_DATA_LIMITS, beforeDataLimitsCallbackProxy.getProxy());
			getConfiguration().setCallback(Property.AFTER_DATA_LIMITS, afterDataLimitsCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			getConfiguration().setCallback(Property.BEFORE_DATA_LIMITS, null);
			getConfiguration().setCallback(Property.AFTER_DATA_LIMITS, null);
		}
	}

	/**
	 * Returns the user callback that runs before/after dimensions are set.
	 * 
	 * @return the user callback that runs before/after dimensions are set
	 */
	public AxisDimensionsCallback getAxisDimensionsCallback() {
		return axisDimensionsCallback;
	}

	/**
	 * Sets the user callback that runs before/after dimensions are set.
	 * 
	 * @param axisDimensionsCallback the user callback that runs before/after dimensions are set
	 */
	public void setAxisDimensionsCallback(AxisDimensionsCallback axisDimensionsCallback) {
		// sets the callback
		this.axisDimensionsCallback = axisDimensionsCallback;
		// checks if callback is consistent
		if (axisDimensionsCallback != null) {
			// adds the callback proxy function to java script object
			getConfiguration().setCallback(Property.BEFORE_SET_DIMENSIONS, beforeSetDimensionsCallbackProxy.getProxy());
			getConfiguration().setCallback(Property.AFTER_SET_DIMENSIONS, afterSetDimensionsCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			getConfiguration().setCallback(Property.BEFORE_SET_DIMENSIONS, null);
			getConfiguration().setCallback(Property.AFTER_SET_DIMENSIONS, null);
		}
	}

	/**
	 * Returns the user callback that runs before/after the scale fits to the canvas.
	 * 
	 * @return the user callback that runs before/after the scale fits to the canvas
	 */
	public AxisFitCallback getAxisFitCallback() {
		return axisFitCallback;
	}

	/**
	 * Sets the user callback that runs before/after the scale fits to the canvas.
	 * 
	 * @param axisFitCallback the user callback that runs before/after the scale fits to the canvas
	 */
	public void setAxisFitCallback(AxisFitCallback axisFitCallback) {
		// sets the callback
		this.axisFitCallback = axisFitCallback;
		// checks if callback is consistent
		if (axisFitCallback != null) {
			// adds the callback proxy function to java script object
			getConfiguration().setCallback(Property.BEFORE_FIT, beforeFitCallbackProxy.getProxy());
			getConfiguration().setCallback(Property.AFTER_FIT, afterFitCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			getConfiguration().setCallback(Property.BEFORE_FIT, null);
			getConfiguration().setCallback(Property.AFTER_FIT, null);
		}
	}

	/**
	 * Returns the user callback that runs before/after ticks are converted into strings.
	 * 
	 * @return the user callback that runs before/after ticks are converted into strings
	 */
	public AxisTickToLabelConversionCallback getAxisTickToLabelConversionCallback() {
		return axisTickToLabelConversionCallback;
	}

	/**
	 * Sets the user callback that runs before/after ticks are converted into strings.
	 * 
	 * @param axisTickToLabelConversionCallback the user callback that runs before/after ticks are converted into strings
	 */
	public void setAxisTickToLabelConversionCallback(AxisTickToLabelConversionCallback axisTickToLabelConversionCallback) {
		// sets the callback
		this.axisTickToLabelConversionCallback = axisTickToLabelConversionCallback;
		// checks if callback is consistent
		if (axisTickToLabelConversionCallback != null) {
			// adds the callback proxy function to java script object
			getConfiguration().setCallback(Property.BEFORE_TICK_TO_LABEL_CONVERSION, beforeTickToLabelConversionCallbackProxy.getProxy());
			getConfiguration().setCallback(Property.AFTER_TICK_TO_LABEL_CONVERSION, afterTickToLabelConversionCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			getConfiguration().setCallback(Property.BEFORE_TICK_TO_LABEL_CONVERSION, null);
			getConfiguration().setCallback(Property.AFTER_TICK_TO_LABEL_CONVERSION, null);
		}
	}

	/**
	 * Returns the user callback that runs before/after of the update process.
	 * 
	 * @return the user callback that runs before/after of the update process
	 */
	public AxisUpdateCallback getAxisUpdateCallback() {
		return axisUpdateCallback;
	}

	/**
	 * Sets the user callback that runs before/after of the update process.
	 * 
	 * @param axisUpdateCallback the user callback that runs before/after of the update process
	 */
	public void setAxisUpdateCallback(AxisUpdateCallback axisUpdateCallback) {
		// sets the callback
		this.axisUpdateCallback = axisUpdateCallback;
		// checks if callback is consistent
		if (axisUpdateCallback != null) {
			// adds the callback proxy function to java script object
			getConfiguration().setCallback(Property.BEFORE_UPDATE, beforeUpdateCallbackProxy.getProxy());
			getConfiguration().setCallback(Property.AFTER_UPDATE, afterUpdateCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			getConfiguration().setCallback(Property.BEFORE_UPDATE, null);
			getConfiguration().setCallback(Property.AFTER_UPDATE, null);
		}
	}

	/**
	 * Invokes UPDATE axis callback.
	 * 
	 * @param item axis item instance
	 */
	private void onBeforeUpdateCallback(NativeObject item) {
		// if user callback is consistent
		if (axisUpdateCallback != null) {
			// then it is called
			axisUpdateCallback.onBeforeUpdate(this, new AxisItem(item));
		}
	}

	/**
	 * Invokes SET DIMENSION axis callback.
	 * 
	 * @param item axis item instance
	 */
	private void onBeforeSetDimensionsCallback(NativeObject item) {
		// if user callback is consistent
		if (axisDimensionsCallback != null) {
			// then it is called
			axisDimensionsCallback.onBeforeSetDimensions(this, new AxisItem(item));
		}
	}

	/**
	 * Invokes SET DIMENSION axis callback.
	 * 
	 * @param item axis item instance
	 */
	private void onAfterSetDimensionsCallback(NativeObject item) {
		// if user callback is consistent
		if (axisDimensionsCallback != null) {
			// then it is called
			axisDimensionsCallback.onAfterSetDimensions(this, new AxisItem(item));
		}
	}

	/**
	 * Invokes DATA LIMITS axis callback.
	 * 
	 * @param item axis item instance
	 */
	private void onBeforeDataLimitsCallback(NativeObject item) {
		// if user callback is consistent
		if (axisDataLimitsCallback != null) {
			// then it is called
			axisDataLimitsCallback.onBeforeDataLimits(this, new AxisItem(item));
		}
	}

	/**
	 * Invokes DATA LIMITS axis callback.
	 * 
	 * @param item axis item instance
	 */
	private void onAfterDataLimitsCallback(NativeObject item) {
		// if user callback is consistent
		if (axisDataLimitsCallback != null) {
			// then it is called
			axisDataLimitsCallback.onAfterDataLimits(this, new AxisItem(item));
		}
	}

	/**
	 * Invokes TICK TO LABEL conversion axis callback.
	 * 
	 * @param item axis item instance
	 */
	private void onBeforeTickToLabelConversionCallback(NativeObject item) {
		// if user callback is consistent
		if (axisTickToLabelConversionCallback != null) {
			// then it is called
			axisTickToLabelConversionCallback.onBeforeTickToLabelConversion(this, new AxisItem(item));
		}
	}

	/**
	 * Invokes TICK TO LABEL conversion axis callback.
	 * 
	 * @param item axis item instance
	 */
	private void onAfterTickToLabelConversionCallback(NativeObject item) {
		// if user callback is consistent
		if (axisTickToLabelConversionCallback != null) {
			// then it is called
			axisTickToLabelConversionCallback.onAfterTickToLabelConversion(this, new AxisItem(item));
		}
	}

	/**
	 * Invokes CALCULATE TICK rotation conversion axis callback.
	 * 
	 * @param item axis item instance
	 */
	private void onBeforeCalculateTickRotationCallback(NativeObject item) {
		// if user callback is consistent
		if (axisCalculateTickRotationCallback != null) {
			// then it is called
			axisCalculateTickRotationCallback.onBeforeCalculateTickRotation(this, new AxisItem(item));
		}
	}

	/**
	 * Invokes CALCULATE TICK rotation conversion axis callback.
	 * 
	 * @param item axis item instance
	 */
	private void onAfterCalculateTickRotationCallback(NativeObject item) {
		// if user callback is consistent
		if (axisCalculateTickRotationCallback != null) {
			// then it is called
			axisCalculateTickRotationCallback.onAfterCalculateTickRotation(this, new AxisItem(item));
		}
	}

	/**
	 * Invokes FIT conversion axis callback.
	 * 
	 * @param item axis item instance
	 */
	private void onBeforeFitCallback(NativeObject item) {
		// if user callback is consistent
		if (axisFitCallback != null) {
			// then it is called
			axisFitCallback.onBeforeFit(this, new AxisItem(item));
		}
	}

	/**
	 * Invokes FIT conversion axis callback.
	 * 
	 * @param item axis item instance
	 */
	private void onAfterFitCallback(NativeObject item) {
		// if user callback is consistent
		if (axisFitCallback != null) {
			// then it is called
			axisFitCallback.onAfterFit(this, new AxisItem(item));
		}
	}

	/**
	 * Invokes UPDATE conversion axis callback.
	 * 
	 * @param item axis item instance
	 */
	private void onAfterUpdateCallback(NativeObject item) {
		// if user callback is consistent
		if (axisUpdateCallback != null) {
			// then it is called
			axisUpdateCallback.onAfterUpdate(this, new AxisItem(item));
		}
	}

}