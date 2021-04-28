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
import org.pepstock.charba.client.callbacks.AxisBuildTicksCallback;
import org.pepstock.charba.client.callbacks.AxisCalculateTickRotationCallback;
import org.pepstock.charba.client.callbacks.AxisDataLimitsCallback;
import org.pepstock.charba.client.callbacks.AxisDimensionsCallback;
import org.pepstock.charba.client.callbacks.AxisFitCallback;
import org.pepstock.charba.client.callbacks.AxisTickToLabelConversionCallback;
import org.pepstock.charba.client.callbacks.AxisUpdateCallback;
import org.pepstock.charba.client.callbacks.CallbackFunctionContext;
import org.pepstock.charba.client.callbacks.FontCallback;
import org.pepstock.charba.client.callbacks.PaddingCallback;
import org.pepstock.charba.client.callbacks.ScaleContext;
import org.pepstock.charba.client.callbacks.ScriptableUtils;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.IsDefaultScale;
import org.pepstock.charba.client.defaults.IsDefaultScaledOptions;
import org.pepstock.charba.client.enums.AxisKind;
import org.pepstock.charba.client.enums.AxisType;
import org.pepstock.charba.client.enums.DefaultScaleId;
import org.pepstock.charba.client.enums.Display;
import org.pepstock.charba.client.items.AxisItem;
import org.pepstock.charba.client.items.FontItem;
import org.pepstock.charba.client.items.PaddingItem;
import org.pepstock.charba.client.options.ExtendedScale;
import org.pepstock.charba.client.options.IsFont;
import org.pepstock.charba.client.options.IsPadding;
import org.pepstock.charba.client.options.IsScaleId;
import org.pepstock.charba.client.options.Scale;
import org.pepstock.charba.client.options.ScaleTitle;

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

	private static final PaddingItem DEFAULT_PADDING_FOR_CALLBACK = new PaddingItem(0);

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
	// callback proxy to invoke the before build tricks function
	private final CallbackProxy<ProxyAxisCallback> beforeBuildTicksCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the after build tricks function
	private final CallbackProxy<ProxyAxisCallback> afterBuildTicksCallbackProxy = JsHelper.get().newCallbackProxy();

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
	// user callback implementation for building ticks
	private AxisBuildTicksCallback axisBuildTicksCallback = null;

	// stores axis type
	private final AxisType storeType;
	// stores default scale
	private final IsDefaultScale defaultValues;

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
	 * Builds the object storing the chart instance.
	 * 
	 * @param chart chart instance
	 * @param id axis id
	 * @param type axis type
	 * @param kind axis kind to set the right position
	 */
	Axis(IsChart chart, IsScaleId id, AxisType type, AxisKind kind) {
		super(chart);
		// checks if id is consistent
		IsScaleId.checkIfValid(id);
		// checks cartesian type
		Key.checkIfValid(kind);
		// stores internally the axis type
		this.storeType = Key.checkAndGetIfValid(type);
		// stores defaults
		this.defaultValues = getDefaultScale(id, kind);
		// sets the options (scale) to map attributes
		// getting the defaults values for scales
		setConfiguration(new ExtendedScale(new ConfigurationEnvelop<>(id), type, kind, defaultValues));
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
		beforeBuildTicksCallbackProxy.setCallback((context, item) -> onBeforeBuildTicksCallback(item));
		afterBuildTicksCallbackProxy.setCallback((context, item) -> onAfterBuildTicksCallback(item));
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
	 * Returns the type of axis.
	 * 
	 * @return the type of axis
	 */
	public final AxisType getType() {
		return getScale().getType();
	}

	/**
	 * Which kind of axis this is.<br>
	 * Possible values are: 'x', 'y' or 'r'.
	 * 
	 * @return the kind of axis.
	 */
	public final AxisKind getAxis() {
		return getScale().getAxis();
	}

	/**
	 * If set to false the axis is hidden from view.<br>
	 * Overrides {@link Grid#setDisplay(boolean)}, {@link ScaleTitle#setDisplay(boolean)}, and {@link Tick#setDisplay(boolean)}.
	 * 
	 * @param display If set to false the axis is hidden from view.<br>
	 *            Overrides {@link Grid#setDisplay(boolean)}, {@link ScaleTitle#setDisplay(boolean)}, and {@link Tick#setDisplay(boolean)}.
	 */
	public void setDisplay(boolean display) {
		getScale().setDisplay(display);
	}

	/**
	 * The display option controls the visibility of axis.<br>
	 * Controls the axis global visibility (visible when true, hidden when false).<br>
	 * When {@link Display#AUTO}, the axis is visible only if at least one associated data set is visible.
	 * 
	 * @param display display option controls the visibility of axis
	 */
	public final void setDisplay(Display display) {
		getScale().setDisplay(display);
	}

	/**
	 * The display option controls the visibility of axis.<br>
	 * Controls the axis global visibility (visible when true, hidden when false).<br>
	 * When {@link Display#AUTO}, the axis is visible only if at least one associated data set is visible.
	 * 
	 * @return display option controls the visibility of axis
	 */
	public final Display getDisplay() {
		return getScale().getDisplay();
	}

	/**
	 * Sets the default background color to use in the chart, on all objects, if not override by the specific configuration.
	 * 
	 * @param backgroundColor background color to use in the chart.
	 */
	public void setBackgroundColor(IsColor backgroundColor) {
		getScale().setBackgroundColor(backgroundColor);
	}

	/**
	 * Sets the background color of the scale area.
	 * 
	 * @param backgroundColor the background color of the scale area.
	 */
	public void setBackgroundColor(String backgroundColor) {
		getScale().setBackgroundColor(backgroundColor);
	}

	/**
	 * Returns the background color of the scale area.
	 * 
	 * @return the background color of the scale area.
	 */
	public String getBackgroundColorAsString() {
		return getScale().getBackgroundColorAsString();
	}

	/**
	 * Returns the background color of the scale area.
	 * 
	 * @return the background color of the scale area.
	 */
	public IsColor getBackgroundColor() {
		return getScale().getBackgroundColor();
	}

	/**
	 * The weight used to sort the axis.<br>
	 * Higher weights are further away from the chart area.
	 * 
	 * @param weight weight of axis
	 */
	public void setWeight(double weight) {
		getScale().setWeight(weight);
	}

	/**
	 * The weight used to sort the axis.<br>
	 * Higher weights are further away from the chart area.
	 * 
	 * @return weight of axis
	 */
	public double getWeight() {
		return getScale().getWeight();
	}

	/**
	 * Sets <code>true</code> to align pixel values to device pixels.
	 * 
	 * @param alignToPixels <code>true</code> to align pixel values to device pixels.
	 */
	public void setAlignToPixels(boolean alignToPixels) {
		getScale().setAlignToPixels(alignToPixels);
	}

	/**
	 * Returns <code>true</code> to align pixel values to device pixels.
	 * 
	 * @return <code>true</code> to align pixel values to device pixels
	 */
	public boolean isAlignToPixels() {
		return getScale().isAlignToPixels();
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
	 * Returns the scale configuration or the chart options if chart is initialized.
	 * 
	 * @return the scale configuration or the chart options if chart is initialized
	 */
	final Scale getScale() {
		// returns the scale
		return super.getConfiguration();
	}

	/**
	 * Returns the default values of the axis.
	 * 
	 * @return the default values of the axis
	 */
	final IsDefaultScale getDefaultValues() {
		return defaultValues;
	}

	/**
	 * Returns the global options for this chart.
	 * 
	 * @param scaleId the scale id to use to get the default
	 * @param kind axis kind instance
	 * @return the global options for this chart.
	 */
	private IsDefaultScale getDefaultScale(IsScaleId scaleId, AxisKind kind) {
		// gets the global option for the chart.
		IsDefaultScaledOptions options = getChart().getDefaultChartOptions();
		// if is a a chart with scales
		if (!ScaleType.NONE.equals(getChart().getType().scaleType()) && Key.isValid(kind)) {
			// creates reference for scale
			// returns default scale reference
			return options.getScales().getAxis(scaleId, kind);
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
		// stores and manages callback
		setCallback(getConfiguration(), Property.BEFORE_CALCULATE_TICK_ROTATION, axisCalculateTickRotationCallback, beforeCalculateTickRotationCallbackProxy);
		// stores and manages callback
		setCallback(getConfiguration(), Property.AFTER_CALCULATE_TICK_ROTATION, axisCalculateTickRotationCallback, afterCalculateTickRotationCallbackProxy);
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
		// stores and manages callback
		setCallback(getConfiguration(), Property.BEFORE_DATA_LIMITS, axisDataLimitsCallback, beforeDataLimitsCallbackProxy);
		// stores and manages callback
		setCallback(getConfiguration(), Property.AFTER_DATA_LIMITS, axisDataLimitsCallback, afterDataLimitsCallbackProxy);
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
		// stores and manages callback
		setCallback(getConfiguration(), Property.BEFORE_SET_DIMENSIONS, axisDimensionsCallback, beforeSetDimensionsCallbackProxy);
		// stores and manages callback
		setCallback(getConfiguration(), Property.AFTER_SET_DIMENSIONS, axisDimensionsCallback, afterSetDimensionsCallbackProxy);
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
		// stores and manages callback
		setCallback(getConfiguration(), Property.BEFORE_FIT, axisFitCallback, beforeFitCallbackProxy);
		// stores and manages callback
		setCallback(getConfiguration(), Property.AFTER_FIT, axisFitCallback, afterFitCallbackProxy);
	}

	/**
	 * Returns the user callback that runs before/after ticks are converted in the strings.
	 * 
	 * @return the user callback that runs before/after ticks are converted in the strings
	 */
	public AxisTickToLabelConversionCallback getAxisTickToLabelConversionCallback() {
		return axisTickToLabelConversionCallback;
	}

	/**
	 * Sets the user callback that runs before/after ticks are converted in the strings.
	 * 
	 * @param axisTickToLabelConversionCallback the user callback that runs before/after ticks are converted in the strings
	 */
	public void setAxisTickToLabelConversionCallback(AxisTickToLabelConversionCallback axisTickToLabelConversionCallback) {
		// sets the callback
		this.axisTickToLabelConversionCallback = axisTickToLabelConversionCallback;
		// stores and manages callback
		setCallback(getConfiguration(), Property.BEFORE_TICK_TO_LABEL_CONVERSION, axisTickToLabelConversionCallback, beforeTickToLabelConversionCallbackProxy);
		// stores and manages callback
		setCallback(getConfiguration(), Property.AFTER_TICK_TO_LABEL_CONVERSION, axisTickToLabelConversionCallback, afterTickToLabelConversionCallbackProxy);
	}

	/**
	 * Returns the user callback that runs before/after of the ticks building.
	 * 
	 * @return the user callback that runs before/after of the ticks building
	 */
	public AxisBuildTicksCallback getAxisBuildTicksCallback() {
		return axisBuildTicksCallback;
	}

	/**
	 * Sets the user callback that runs before/after of the ticks building.
	 * 
	 * @param axisBuildTicksCallback the user callback that runs before/after of the ticks building
	 */
	public void setAxisBuildTicksCallback(AxisBuildTicksCallback axisBuildTicksCallback) {
		// sets the callback
		this.axisBuildTicksCallback = axisBuildTicksCallback;
		// stores and manages callback
		setCallback(getConfiguration(), Property.BEFORE_BUILD_TICKS, axisBuildTicksCallback, beforeBuildTicksCallbackProxy);
		// stores and manages callback
		setCallback(getConfiguration(), Property.AFTER_BUILD_TICKS, axisBuildTicksCallback, afterBuildTicksCallbackProxy);
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
		// stores and manages callback
		setCallback(getConfiguration(), Property.BEFORE_UPDATE, axisUpdateCallback, beforeUpdateCallbackProxy);
		// stores and manages callback
		setCallback(getConfiguration(), Property.AFTER_UPDATE, axisUpdateCallback, afterUpdateCallbackProxy);
	}

	/**
	 * Returns a native object as font when the callback has been activated.
	 * 
	 * @param context native object as context
	 * @param callback callback to invoke
	 * @param font font instance
	 * @return a native object as font
	 */
	final NativeObject onFont(ScaleContext context, FontCallback<ScaleContext> callback, IsFont font) {
		// gets value
		FontItem result = ScriptableUtils.getOptionValue(context, callback);
		// checks if result is consistent
		if (result != null) {
			// returns result
			return result.nativeObject();
		} else if (font != null) {
			// checks if provider is consistent
			return font.create().nativeObject();
		}
		// if here, provider is not consistent
		// then returns the defaults one
		return Defaults.get().getGlobal().getFont().create().nativeObject();
	}

	/**
	 * Returns a native object as padding when the callback has been activated.
	 * 
	 * @param context native object as context
	 * @param callback callback to invoke
	 * @param padding padding instance
	 * @return a native object as font
	 */
	final NativeObject onPadding(ScaleContext context, PaddingCallback<ScaleContext> callback, IsPadding padding) {
		// gets value
		PaddingItem result = ScriptableUtils.getOptionValue(context, callback);
		// checks if result is consistent
		if (result != null) {
			// returns result
			return result.nativeObject();
		} else if (padding != null) {
			// checks if provider is consistent
			return padding.create().nativeObject();
		}
		// if here, provider is not consistent
		// then returns a zero padding
		return DEFAULT_PADDING_FOR_CALLBACK.nativeObject();
	}

	/**
	 * Sets the callbacks that every element of axis can activate.
	 * 
	 * @param node element node instance
	 * @param property property name
	 * @param callBack the callback instance
	 * @param callbackProxy the proxy instance
	 */
	final void setCallback(AbstractNode node, Key property, Object callBack, CallbackProxy<?> callbackProxy) {
		setCallback(node, property, callBack, callbackProxy.getProxy());
	}

	/**
	 * Sets the callbacks that every element of axis can activate.
	 * 
	 * @param node element node instance
	 * @param property property name
	 * @param callBack the callback instance
	 * @param proxy the proxy instance
	 */
	final void setCallback(AbstractNode node, Key property, Object callBack, CallbackProxy.Proxy proxy) {
		// checks if consistent
		if (callBack != null) {
			// adds the callback proxy function to java script object
			getConfiguration().setCallback(node, property, new ConfigurationEnvelop<>(proxy));
		} else {
			// otherwise sets null which removes the properties from java script object
			getConfiguration().setCallback(node, property, ConfigurationOptions.RESET_CALLBACK_ENVELOP);
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
			axisUpdateCallback.onBeforeUpdate(this, new AxisItem(new ConfigurationEnvelop<>(item, true)));
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
			axisDimensionsCallback.onBeforeSetDimensions(this, new AxisItem(new ConfigurationEnvelop<>(item, true)));
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
			axisDimensionsCallback.onAfterSetDimensions(this, new AxisItem(new ConfigurationEnvelop<>(item, true)));
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
			axisDataLimitsCallback.onBeforeDataLimits(this, new AxisItem(new ConfigurationEnvelop<>(item, true)));
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
			axisDataLimitsCallback.onAfterDataLimits(this, new AxisItem(new ConfigurationEnvelop<>(item, true)));
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
			axisTickToLabelConversionCallback.onBeforeTickToLabelConversion(this, new AxisItem(new ConfigurationEnvelop<>(item, true)));
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
			axisTickToLabelConversionCallback.onAfterTickToLabelConversion(this, new AxisItem(new ConfigurationEnvelop<>(item, true)));
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
			axisCalculateTickRotationCallback.onBeforeCalculateTickRotation(this, new AxisItem(new ConfigurationEnvelop<>(item, true)));
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
			axisCalculateTickRotationCallback.onAfterCalculateTickRotation(this, new AxisItem(new ConfigurationEnvelop<>(item, true)));
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
			axisFitCallback.onBeforeFit(this, new AxisItem(new ConfigurationEnvelop<>(item, true)));
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
			axisFitCallback.onAfterFit(this, new AxisItem(new ConfigurationEnvelop<>(item, true)));
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
			axisUpdateCallback.onAfterUpdate(this, new AxisItem(new ConfigurationEnvelop<>(item, true)));
		}
	}

	/**
	 * Invokes BUILD TICKS axis callback.
	 * 
	 * @param item axis item instance
	 */
	private void onBeforeBuildTicksCallback(NativeObject item) {
		// if user callback is consistent
		if (getAxisBuildTicksCallback() != null) {
			// then it is called
			getAxisBuildTicksCallback().onBeforeBuildTicks(this, new AxisItem(new ConfigurationEnvelop<>(item, true)));
		}
	}

	/**
	 * Invokes BUILD TICKS axis callback.
	 * 
	 * @param item axis item instance
	 */
	private void onAfterBuildTicksCallback(NativeObject item) {
		// if user callback is consistent
		if (getAxisBuildTicksCallback() != null) {
			// then it is called
			getAxisBuildTicksCallback().onAfterBuildTicks(this, new AxisItem(new ConfigurationEnvelop<>(item, true)));
		}
	}

}