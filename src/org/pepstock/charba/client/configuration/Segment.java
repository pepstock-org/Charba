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

import org.pepstock.charba.client.Charts;
import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.callbacks.BorderDashCallback;
import org.pepstock.charba.client.callbacks.BorderDashOffsetCallback;
import org.pepstock.charba.client.callbacks.CapStyleCallback;
import org.pepstock.charba.client.callbacks.ChartContext;
import org.pepstock.charba.client.callbacks.ColorCallback;
import org.pepstock.charba.client.callbacks.JoinStyleCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyArrayCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyDoubleCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyIntegerCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyObjectCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyStringCallback;
import org.pepstock.charba.client.callbacks.ScriptableUtils;
import org.pepstock.charba.client.callbacks.SegmentContext;
import org.pepstock.charba.client.callbacks.WidthCallback;
import org.pepstock.charba.client.colors.Pattern;
import org.pepstock.charba.client.commons.Array;
import org.pepstock.charba.client.commons.ArrayInteger;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.enums.CapStyle;
import org.pepstock.charba.client.enums.JoinStyle;

/**
 * The segment configuration for {@link LineOptions}.<br>
 * Line segment styles can be overridden by scriptable options in the segment object.<br>
 * Currently all of the border* and background color options are supported.<br>
 * The segment styles are resolved for each section of the line between each point.<br>
 * <code>null</code> fallbacks to main line styles.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class Segment extends ConfigurationOptionsContainer {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		BACKGROUND_COLOR("backgroundColor"),
		BORDER_WIDTH("borderWidth"),
		BORDER_COLOR("borderColor"),
		BORDER_DASH("borderDash"),
		BORDER_DASH_OFFSET("borderDashOffset"),
		BORDER_CAP_STYLE("borderCapStyle"),
		BORDER_JOIN_STYLE("borderJoinStyle"),
		// for context
		CHART("chart");

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

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the background color function
	private final CallbackProxy<ProxyObjectCallback> backgroundColorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the border color function
	private final CallbackProxy<ProxyObjectCallback> borderColorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the border width function
	private final CallbackProxy<ProxyIntegerCallback> borderWidthCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the border cap style function
	private final CallbackProxy<ProxyStringCallback> borderCapStyleCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the border dash function
	private final CallbackProxy<ProxyArrayCallback> borderDashCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the border dash offset function
	private final CallbackProxy<ProxyDoubleCallback> borderDashOffsetCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the border join style function
	private final CallbackProxy<ProxyStringCallback> borderJoinStyleCallbackProxy = JsHelper.get().newCallbackProxy();

	// background color callback instance
	private ColorCallback<SegmentContext> backgroundColorCallback = null;
	// border color callback instance
	private ColorCallback<SegmentContext> borderColorCallback = null;
	// border width callback instance
	private WidthCallback<SegmentContext> borderWidthCallback = null;
	// border cap style callback instance
	private CapStyleCallback<SegmentContext> borderCapStyleCallback = null;
	// border join style callback instance
	private JoinStyleCallback<SegmentContext> borderJoinStyleCallback = null;
	// border dash callback instance
	private BorderDashCallback<SegmentContext> borderDashCallback = null;
	// border dash offset callback instance
	private BorderDashOffsetCallback<SegmentContext> borderDashOffsetCallback = null;

	/**
	 * Builds the object storing the root options element.
	 * 
	 * @param options root options element.
	 */
	Segment(ConfigurationOptions options) {
		super(options);
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// sets function to proxy callback in order to invoke the java interface
		this.backgroundColorCallbackProxy.setCallback((contextFunction, context) -> onColor(new BaseContext(getChart(), context), backgroundColorCallback, getOptions().getElements().getLine().getBackgroundColorAsString(), true));
		// sets function to proxy callback in order to invoke the java interface
		this.borderColorCallbackProxy.setCallback((contextFunction, context) -> onColor(new BaseContext(getChart(), context), borderColorCallback, getOptions().getElements().getLine().getBorderColorAsString(), false));
		// sets function to proxy callback in order to invoke the java interface
		this.borderWidthCallbackProxy.setCallback((contextFunction, context) -> onBorderWidth(new BaseContext(getChart(), context)));
		// sets function to proxy callback in order to invoke the java interface
		this.borderCapStyleCallbackProxy.setCallback((contextFunction, context) -> onBorderCapStyle(new BaseContext(getChart(), context)));
		// sets function to proxy callback in order to invoke the java interface
		this.borderDashCallbackProxy.setCallback((contextFunction, context) -> onBorderDash(new BaseContext(getChart(), context)));
		// sets function to proxy callback in order to invoke the java interface
		this.borderDashOffsetCallbackProxy.setCallback((contextFunction, context) -> onBorderDashOffset(new BaseContext(getChart(), context)));
		// sets function to proxy callback in order to invoke the java interface
		this.borderJoinStyleCallbackProxy.setCallback((contextFunction, context) -> onBorderJoinStyle(new BaseContext(getChart(), context)));
	}

	/**
	 * Returns the background width callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the background width callback, if set, otherwise <code>null</code>.
	 */
	public ColorCallback<SegmentContext> getBackgroundColorCallback() {
		return backgroundColorCallback;
	}

	/**
	 * Sets the background color callback.
	 * 
	 * @param backgroundColorCallback the background color callback to set
	 */
	public void setBackgroundColor(ColorCallback<SegmentContext> backgroundColorCallback) {
		// sets the callback
		this.backgroundColorCallback = backgroundColorCallback;
		// stores and manages callback
		getOptions().setCallback(getConfiguration().getSegment(), Property.BACKGROUND_COLOR, backgroundColorCallback, backgroundColorCallbackProxy);
	}

	/**
	 * Returns the border width callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the border width callback, if set, otherwise <code>null</code>.
	 */
	public WidthCallback<SegmentContext> getBorderWidthCallback() {
		return borderWidthCallback;
	}

	/**
	 * Sets the border width callback.
	 * 
	 * @param borderWidthCallback the border width callback to set
	 */
	public void setBorderWidth(WidthCallback<SegmentContext> borderWidthCallback) {
		// sets the callback
		this.borderWidthCallback = borderWidthCallback;
		// stores and manages callback
		getOptions().setCallback(getConfiguration().getSegment(), Property.BORDER_WIDTH, borderWidthCallback, borderWidthCallbackProxy);
	}

	/**
	 * Returns the border width callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the border width callback, if set, otherwise <code>null</code>.
	 */
	public ColorCallback<SegmentContext> getBorderColorCallback() {
		return borderColorCallback;
	}

	/**
	 * Sets the border color callback.
	 * 
	 * @param borderColorCallback the border color callback to set
	 */
	public void setBorderColor(ColorCallback<SegmentContext> borderColorCallback) {
		// sets the callback
		this.borderColorCallback = borderColorCallback;
		// stores and manages callback
		getOptions().setCallback(getConfiguration().getSegment(), Property.BORDER_COLOR, borderColorCallback, borderColorCallbackProxy);
	}

	/**
	 * Returns the border cap-style callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the border cap-style callback, if set, otherwise <code>null</code>.
	 */
	public CapStyleCallback<SegmentContext> getBorderCapStyleCallback() {
		return borderCapStyleCallback;
	}

	/**
	 * Sets the border cap-style callback.
	 * 
	 * @param borderCapStyleCallback the border cap-style callback to set
	 */
	public void setBorderCapStyle(CapStyleCallback<SegmentContext> borderCapStyleCallback) {
		// sets the callback
		this.borderCapStyleCallback = borderCapStyleCallback;
		// stores and manages callback
		getOptions().setCallback(getConfiguration().getSegment(), Property.BORDER_CAP_STYLE, borderCapStyleCallback, borderCapStyleCallbackProxy);
	}

	/**
	 * Returns the border join-style callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the border join-style callback, if set, otherwise <code>null</code>.
	 */
	public JoinStyleCallback<SegmentContext> getBorderJoinStyleCallback() {
		return borderJoinStyleCallback;
	}

	/**
	 * Sets the border join-style callback.
	 * 
	 * @param borderJoinStyleCallback the border join-style callback to set
	 */
	public void setBorderJoinStyle(JoinStyleCallback<SegmentContext> borderJoinStyleCallback) {
		// sets the callback
		this.borderJoinStyleCallback = borderJoinStyleCallback;
		// stores and manages callback
		getOptions().setCallback(getConfiguration().getSegment(), Property.BORDER_JOIN_STYLE, borderJoinStyleCallback, borderJoinStyleCallbackProxy);
	}

	/**
	 * Returns the border dash callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the border dash callback, if set, otherwise <code>null</code>.
	 */
	public BorderDashCallback<SegmentContext> getBorderDashCallback() {
		return borderDashCallback;
	}

	/**
	 * Sets the border dash callback.
	 * 
	 * @param borderDashCallback the border dash callback to set
	 */
	public void setBorderDash(BorderDashCallback<SegmentContext> borderDashCallback) {
		// sets the callback
		this.borderDashCallback = borderDashCallback;
		// stores and manages callback
		getOptions().setCallback(getConfiguration().getSegment(), Property.BORDER_DASH, borderDashCallback, borderDashCallbackProxy);
	}

	/**
	 * Returns the border dash callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the border dash callback, if set, otherwise <code>null</code>.
	 */
	public BorderDashOffsetCallback<SegmentContext> getBorderDashOffsetCallback() {
		return borderDashOffsetCallback;
	}

	/**
	 * Sets the border dash callback.
	 * 
	 * @param borderDashOffsetCallback the border dash callback to set
	 */
	public void setBorderDashOffset(BorderDashOffsetCallback<SegmentContext> borderDashOffsetCallback) {
		// sets the callback
		this.borderDashOffsetCallback = borderDashOffsetCallback;
		// stores and manages callback
		getOptions().setCallback(getConfiguration().getSegment(), Property.BORDER_DASH, borderDashOffsetCallback, borderDashOffsetCallbackProxy);
	}

	// -------------------------------
	// internal methods to manage the
	// callback invocation
	// -------------------------------

	/**
	 * Returns a native object as padding when the callback has been activated.
	 * 
	 * @param context native object as context
	 * @return a native object as padding
	 */
	private int onBorderWidth(BaseContext context) {
		return ScriptableUtils.getOptionValue(new SegmentContext(new ConfigurationEnvelop<>(context.nativeObject())), borderWidthCallback, getOptions().getElements().getLine().getBorderWidth()).intValue();
	}

	/**
	 * Returns a color value of property by a callback, checking all different types of object which can be used as value of the property in color ones.
	 * 
	 * @param context scriptable context
	 * @param callback color callback instance to invoke
	 * @param defaultValue default color value to apply if callback not consistent
	 * @param hasPattern if <code>true</code> the result could be also a {@link Pattern}
	 * @return a value of property as color
	 */
	protected final Object onColor(BaseContext context, ColorCallback<SegmentContext> callback, String defaultValue, boolean hasPattern) {
		// PAY ATTENTION: Gradients are not managed due to Segment context where dataIndex and datasetIndex are missing
		return ScriptableUtils.getOptionValueAsColor(new SegmentContext(new ConfigurationEnvelop<>(context.nativeObject())), callback, defaultValue, hasPattern);
	}

	/**
	 * Returns a {@link CapStyle} when the callback has been activated.
	 * 
	 * @param context native object as context.
	 * @return a object property value, as {@link CapStyle}
	 */
	private String onBorderCapStyle(BaseContext context) {
		// gets value
		CapStyle result = ScriptableUtils.getOptionValue(new SegmentContext(new ConfigurationEnvelop<>(context.nativeObject())), borderCapStyleCallback);
		// checks result
		if (result != null) {
			return result.value();
		}
		// default result
		return getOptions().getElements().getLine().getBorderCapStyle().value();
	}

	/**
	 * Returns a {@link JoinStyle} when the callback has been activated.
	 * 
	 * @param context native object as context.
	 * @return a object property value, as {@link JoinStyle}
	 */
	private String onBorderJoinStyle(BaseContext context) {
		// gets value
		JoinStyle result = ScriptableUtils.getOptionValue(new SegmentContext(new ConfigurationEnvelop<>(context.nativeObject())), borderJoinStyleCallback);
		// checks result
		if (result != null) {
			return result.value();
		}
		// default result
		return getOptions().getElements().getLine().getBorderJoinStyle().value();
	}

	/**
	 * Returns an array of integer when the callback has been activated.
	 * 
	 * @param context native object as context.
	 * @return an array of integer
	 */
	private Array onBorderDash(BaseContext context) {
		// gets value
		List<Integer> result = ScriptableUtils.getOptionValue(new SegmentContext(new ConfigurationEnvelop<>(context.nativeObject())), borderDashCallback);
		// default result
		return ArrayInteger.fromOrEmpty(result);
	}

	/**
	 * Returns a native object as padding when the callback has been activated.
	 * 
	 * @param context native object as context
	 * @return a native object as padding
	 */
	private double onBorderDashOffset(BaseContext context) {
		return ScriptableUtils.getOptionValue(new SegmentContext(new ConfigurationEnvelop<>(context.nativeObject())), borderDashOffsetCallback, getOptions().getElements().getLine().getBorderDashOffset()).doubleValue();
	}

	/**
	 * Utility object which is creating a native object, setting chart and type, in order to be able to create a {@link ChartContext} without CHART.JS, for callbacks of segment.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	private static final class BaseContext extends NativeObjectContainer {

		/**
		 * Creates the object with native object instance to be wrapped.
		 * 
		 * @param chart chart instance
		 * @param nativeContext native context coming from CHART.JS for segment.
		 * 
		 */
		BaseContext(IsChart chart, NativeObject nativeContext) {
			super(nativeContext);
			// checks if chart instance is loaded
			Checker.assertCheck(Charts.hasNative(chart), "Chart argument does not have any related native chart instance");
			// checks if chart is stored
			if (!has(Property.CHART)) {
				// stores the chart and type
				setValue(Property.CHART, Charts.getNative(chart));
			}
		}

		/**
		 * Returns the native object instance.
		 * 
		 * @return the native object instance.
		 */
		NativeObject nativeObject() {
			return getNativeObject();
		}
	}

}