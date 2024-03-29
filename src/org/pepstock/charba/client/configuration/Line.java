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

import org.pepstock.charba.client.callbacks.BorderDashCallback;
import org.pepstock.charba.client.callbacks.BorderDashOffsetCallback;
import org.pepstock.charba.client.callbacks.CapStyleCallback;
import org.pepstock.charba.client.callbacks.CubicInterpolationModeCallback;
import org.pepstock.charba.client.callbacks.DatasetContext;
import org.pepstock.charba.client.callbacks.FillCallback;
import org.pepstock.charba.client.callbacks.JoinStyleCallback;
import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.callbacks.ScriptableDoubleChecker;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyArrayCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyDoubleCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyObjectCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyStringCallback;
import org.pepstock.charba.client.callbacks.ScriptableUtil;
import org.pepstock.charba.client.callbacks.SteppedCallback;
import org.pepstock.charba.client.commons.Array;
import org.pepstock.charba.client.commons.ArrayInteger;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.defaults.IsDefaultLine;
import org.pepstock.charba.client.enums.CapStyle;
import org.pepstock.charba.client.enums.CubicInterpolationMode;
import org.pepstock.charba.client.enums.IsFill;
import org.pepstock.charba.client.enums.JoinStyle;
import org.pepstock.charba.client.enums.Stepped;
import org.pepstock.charba.client.items.FillBaseline;
import org.pepstock.charba.client.items.FillColors;
import org.pepstock.charba.client.options.AbstractElement;

/**
 * Line elements are used to represent the line in a line chart.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class Line extends AbstractConfigurationElement<IsDefaultLine> {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		BORDER_CAP_STYLE("borderCapStyle"),
		BORDER_DASH("borderDash"),
		BORDER_DASH_OFFSET("borderDashOffset"),
		BORDER_JOIN_STYLE("borderJoinStyle"),
		CUBIC_INTERPOLATION_MODE("cubicInterpolationMode"),
		STEPPED("stepped"),
		FILL("fill");

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
	// callback proxy to invoke the border cap style function
	private final CallbackProxy<ProxyStringCallback> borderCapStyleCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the border dash function
	private final CallbackProxy<ProxyArrayCallback> borderDashCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the border dash offset function
	private final CallbackProxy<ProxyDoubleCallback> borderDashOffsetCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the border join style function
	private final CallbackProxy<ProxyStringCallback> borderJoinStyleCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the cubic interpolation mode function
	private final CallbackProxy<ProxyStringCallback> cubicInterpolationModeCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the fill function
	private final CallbackProxy<ProxyObjectCallback> fillCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the stepped function
	private final CallbackProxy<ProxyObjectCallback> steppedCallbackProxy = JsHelper.get().newCallbackProxy();

	// border cap style callback instance
	private CapStyleCallback<DatasetContext> borderCapStyleCallback = null;
	// border dash callback instance
	private BorderDashCallback<DatasetContext> borderDashCallback = null;
	// border dash offset callback instance
	private BorderDashOffsetCallback<DatasetContext> borderDashOffsetCallback = null;
	// border join style callback instance
	private JoinStyleCallback<DatasetContext> borderJoinStyleCallback = null;
	// cubic interpolation mode callback instance
	private CubicInterpolationModeCallback cubicInterpolationModeCallback = null;
	// fill callback instance
	private FillCallback fillCallback = null;
	// stepped callback instance
	private SteppedCallback steppedCallback = null;

	/**
	 * Builds the object with options, root and setting the line element.
	 * 
	 * @param options options instance
	 */
	Line(ConfigurationOptions options) {
		super(options);
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// sets function to proxy callback in order to invoke the java interface
		this.borderCapStyleCallbackProxy.setCallback(context -> onBorderCapStyle(createContext(context), getBorderCapStyleCallback(), getDefaultElement().getBorderCapStyle()));
		// sets function to proxy callback in order to invoke the java interface
		this.borderDashCallbackProxy.setCallback(context -> onBorderDash(createContext(context), getBorderDashCallback()));
		// sets function to proxy callback in order to invoke the java interface
		this.borderDashOffsetCallbackProxy
				.setCallback(context -> ScriptableUtil.getOptionValueAsNumber(createContext(context), getBorderDashOffsetCallback(), getDefaultElement().getBorderDashOffset(), ScriptableDoubleChecker.POSITIVE_OR_DEFAULT).doubleValue());
		// sets function to proxy callback in order to invoke the java interface
		this.borderJoinStyleCallbackProxy.setCallback(context -> onBorderJoinStyle(createContext(context), getBorderJoinStyleCallback(), getDefaultElement().getBorderJoinStyle()));
		// sets function to proxy callback in order to invoke the java interface
		this.cubicInterpolationModeCallbackProxy.setCallback(context -> onCubicInterpolationMode(createContext(context), getCubicInterpolationModeCallback(), getDefaultElement().getCubicInterpolationMode()));
		// sets function to proxy callback in order to invoke the java interface
		this.fillCallbackProxy.setCallback(context -> onFill(new DatasetContext(context), getFillCallback(), getDefaultElement().getFill()));
		// sets function to proxy callback in order to invoke the java interface
		this.steppedCallbackProxy.setCallback(context -> onStepped(new DatasetContext(context), getSteppedCallback(), getDefaultElement().getStepped()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.AbstractConfigurationElement#getElement()
	 */
	@Override
	protected AbstractElement<IsDefaultLine> getElement() {
		return getConfiguration().getElements().getLine();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.AbstractConfigurationElement#getDefaultElement()
	 */
	@Override
	protected IsDefaultLine getDefaultElement() {
		return getOptions().getDefaultValues().getElements().getLine();
	}

	/**
	 * Returns the B\u00e9zier curve tension (0 for no B\u00e9zier curves).
	 * 
	 * @param tension the B\u00e9zier curve tension (0 for no B\u00e9zier curves).
	 */
	public void setTension(double tension) {
		getConfiguration().getElements().getLine().setTension(tension);
	}

	/**
	 * Returns the B\u00e9zier curve tension (0 for no B\u00e9zier curves).
	 * 
	 * @return the B\u00e9zier curve tension (0 for no B\u00e9zier curves).
	 */
	public double getTension() {
		return getConfiguration().getElements().getLine().getTension();
	}

	/**
	 * Sets how the end points of every line are drawn.
	 * 
	 * @param borderCapStyle how the end points of every line are drawn.
	 */
	public void setBorderCapStyle(CapStyle borderCapStyle) {
		// resets callback
		setBorderCapStyle((CapStyleCallback<DatasetContext>) null);
		// stores value
		getConfiguration().getElements().getLine().setBorderCapStyle(borderCapStyle);
	}

	/**
	 * Returns how the end points of every line are drawn.
	 * 
	 * @return how the end points of every line are drawn.
	 */
	public CapStyle getBorderCapStyle() {
		return getConfiguration().getElements().getLine().getBorderCapStyle();
	}

	/**
	 * Sets the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 * 
	 * @param borderDash the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 */
	public void setBorderDash(int... borderDash) {
		// resets callback
		setBorderDash((BorderDashCallback<DatasetContext>) null);
		// stores value
		getConfiguration().getElements().getLine().setBorderDash(borderDash);
	}

	/**
	 * Returns the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 * 
	 * @return the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 */
	public List<Integer> getBorderDash() {
		return getConfiguration().getElements().getLine().getBorderDash();
	}

	/**
	 * Sets the line dash pattern offset.
	 * 
	 * @param borderDashOffset the line dash pattern offset.
	 */
	public void setBorderDashOffset(double borderDashOffset) {
		// resets callback
		setBorderDashOffset((BorderDashOffsetCallback<DatasetContext>) null);
		// stores value
		getConfiguration().getElements().getLine().setBorderDashOffset(borderDashOffset);
	}

	/**
	 * Returns the line dash pattern offset.
	 * 
	 * @return the line dash pattern offset
	 */
	public double getBorderDashOffset() {
		return getConfiguration().getElements().getLine().getBorderDashOffset();
	}

	/**
	 * Sets how two connecting segments (of lines, arcs or curves) with non-zero lengths in a shape are joined together (degenerate segments with zero lengths, whose specified end
	 * points and control points are exactly at the same position, are skipped).
	 * 
	 * @param borderJoinStyle how two connecting segments (of lines, arcs or curves) with non-zero lengths in a shape are joined together
	 */
	public void setBorderJoinStyle(JoinStyle borderJoinStyle) {
		// resets callback
		setBorderJoinStyle((JoinStyleCallback<DatasetContext>) null);
		// stores value
		getConfiguration().getElements().getLine().setBorderJoinStyle(borderJoinStyle);
	}

	/**
	 * Returns how two connecting segments (of lines, arcs or curves) with non-zero lengths in a shape are joined together (degenerate segments with zero lengths, whose specified
	 * end points and control points are exactly at the same position, are skipped).
	 * 
	 * @return how two connecting segments (of lines, arcs or curves) with non-zero lengths in a shape are joined together
	 */
	public JoinStyle getBorderJoinStyle() {
		return getConfiguration().getElements().getLine().getBorderJoinStyle();
	}

	/**
	 * Sets <code>true</code> to keep B\u00e9zier control inside the chart, <code>false</code> for no restriction.
	 * 
	 * @param capBezierPoints <code>true</code> to keep B\u00e9zier control inside the chart, <code>false</code> for no restriction.
	 */
	public void setCapBezierPoints(boolean capBezierPoints) {
		getConfiguration().getElements().getLine().setCapBezierPoints(capBezierPoints);
	}

	/**
	 * Returns <code>true</code> to keep B\u00e9zier control inside the chart, <code>false</code> for no restriction.
	 * 
	 * @return <code>true</code> to keep B\u00e9zier control inside the chart, <code>false</code> for no restriction.
	 */
	public boolean isCapBezierPoints() {
		return getConfiguration().getElements().getLine().isCapBezierPoints();
	}

	/**
	 * Sets algorithm used to interpolate a smooth curve from the discrete data points.<br>
	 * The following interpolation modes are supported:<br>
	 * <br>
	 * 
	 * <pre>
	 * 'default'
	 * 'monotone'
	 * </pre>
	 * 
	 * <br>
	 * The 'default' algorithm uses a custom weighted cubic interpolation, which produces pleasant curves for all types of data sets.<br>
	 * The 'monotone' algorithm is more suited to y = f(x) data sets : it preserves monotonicity (or piecewise monotonicity) of the data set being interpolated, and ensures local
	 * extremums (if any) stay at input data points.
	 * 
	 * @param mode algorithm used to interpolate a smooth curve from the discrete data points
	 */
	public void setCubicInterpolationMode(CubicInterpolationMode mode) {
		// resets callback
		setCubicInterpolationMode((CubicInterpolationModeCallback) null);
		// stores value
		getConfiguration().getElements().getLine().setCubicInterpolationMode(mode);
	}

	/**
	 * Returns algorithm used to interpolate a smooth curve from the discrete data points.
	 * 
	 * @return algorithm used to interpolate a smooth curve from the discrete data points.
	 */
	public CubicInterpolationMode getCubicInterpolationMode() {
		return getConfiguration().getElements().getLine().getCubicInterpolationMode();
	}

	/**
	 * Sets how to fill the area under the line.
	 * 
	 * @param fill <code>true</code> to fill, otherwise <code>false</code>.
	 */
	public void setFill(boolean fill) {
		// resets callback
		setFill((FillCallback) null);
		// stores value
		getConfiguration().getElements().getLine().setFill(fill);
	}

	/**
	 * Sets how to fill the area under the line, by absolute data set index.
	 * 
	 * @param index absolute data set index of the chart.
	 */
	public void setFill(int index) {
		// resets callback
		setFill((FillCallback) null);
		// stores value
		getConfiguration().getElements().getLine().setFill(index);
	}

	/**
	 * Sets how to fill the area under the line, by relative data set index.
	 * 
	 * @param index relative data set index of the chart.
	 */
	public void setFill(String index) {
		// resets callback
		setFill((FillCallback) null);
		// stores value
		getConfiguration().getElements().getLine().setFill(index);
	}

	/**
	 * Sets how to fill the area under the line.
	 * 
	 * @param fill how to fill the area under the line.
	 */
	public void setFill(IsFill fill) {
		// resets callback
		setFill((FillCallback) null);
		// stores value
		getConfiguration().getElements().getLine().setFill(fill);
	}

	/**
	 * Returns how to fill the area under the line.
	 * 
	 * @return how to fill the area under the line.
	 */
	public IsFill getFill() {
		return getConfiguration().getElements().getLine().getFill();
	}

	/**
	 * Sets the baseline value to use for filling.
	 * 
	 * @param baseline the baseline value to use for filling
	 */
	public void setFillBaseline(double baseline) {
		// resets callback
		setFill((FillCallback) null);
		// stores value
		getConfiguration().getElements().getLine().setFillBaseline(baseline);
	}

	/**
	 * Sets the baseline value to use for filling.
	 * 
	 * @param baseline the baseline value to use for filling
	 */
	public void setFillBaseline(FillBaseline baseline) {
		// resets callback
		setFill((FillCallback) null);
		// stores value
		getConfiguration().getElements().getLine().setFillBaseline(baseline);
	}

	/**
	 * Returns the baseline value to use for filling.
	 * 
	 * @return the baseline value to use for filling
	 */
	public FillBaseline getFillBaseline() {
		return getConfiguration().getElements().getLine().getFillBaseline();
	}

	/**
	 * Sets the above and below color of baseline to use for filling.
	 * 
	 * @param colors the above and below color of baseline to use for filling.
	 */
	public void setFillColors(FillColors colors) {
		// resets callback
		setFill((FillCallback) null);
		// stores value
		getConfiguration().getElements().getLine().setFillColors(colors);
	}

	/**
	 * Returns the above and below color of baseline to use for filling.
	 * 
	 * @return the above and below color of baseline to use for filling.
	 */
	public FillColors getFillColors() {
		return getConfiguration().getElements().getLine().getFillColors();
	}

	/**
	 * Sets if the line is shown as a stepped line.<br>
	 * If the stepped value is set to anything other than false, tension will be ignored.
	 * 
	 * @param stepped if the line is shown as a stepped line. <code>false</code> is no step interpolation
	 */
	public void setStepped(boolean stepped) {
		// resets callback
		setStepped((SteppedCallback) null);
		// stores value
		getConfiguration().getElements().getLine().setStepped(stepped);
	}

	/**
	 * Sets if the line is shown as a stepped line.<br>
	 * If the stepped value is set to anything other than false, tension will be ignored.
	 * 
	 * @param stepped if the line is shown as a stepped line.
	 */
	public void setStepped(Stepped stepped) {
		// resets callback
		setStepped((SteppedCallback) null);
		// stores value
		getConfiguration().getElements().getLine().setStepped(stepped);
	}

	/**
	 * Returns if the line is shown as a stepped line.
	 * 
	 * @return If the line is shown as a stepped line.
	 */
	public Stepped getStepped() {
		return getConfiguration().getElements().getLine().getStepped();
	}

	// ---------------
	// CALLBACKS
	// ---------------

	/**
	 * Returns the border capstyle callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the border capstyle callback, if set, otherwise <code>null</code>.
	 */
	public CapStyleCallback<DatasetContext> getBorderCapStyleCallback() {
		return borderCapStyleCallback;
	}

	/**
	 * Sets the border capstyle callback.
	 * 
	 * @param borderCapStyleCallback the border capstyle callback.
	 */
	public void setBorderCapStyle(CapStyleCallback<DatasetContext> borderCapStyleCallback) {
		// sets the callback
		this.borderCapStyleCallback = borderCapStyleCallback;
		// stores and manages callback
		getChart().getOptions().setCallback(getElement(), Property.BORDER_CAP_STYLE, borderCapStyleCallback, borderCapStyleCallbackProxy);
	}

	/**
	 * Sets the border capstyle callback.
	 * 
	 * @param borderCapStyleCallback the border capstyle callback.
	 */
	public void setBorderCapStyle(NativeCallback borderCapStyleCallback) {
		// resets callback
		setBorderCapStyle((CapStyleCallback<DatasetContext>) null);
		// stores and manages callback
		getChart().getOptions().setCallback(getElement(), Property.BORDER_CAP_STYLE, borderCapStyleCallback);
	}

	/**
	 * Returns the border dash callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the border dash callback, if set, otherwise <code>null</code>.
	 */
	public BorderDashCallback<DatasetContext> getBorderDashCallback() {
		return borderDashCallback;
	}

	/**
	 * Sets the border dash callback.
	 * 
	 * @param borderDashCallback the border dash callback.
	 */
	public void setBorderDash(BorderDashCallback<DatasetContext> borderDashCallback) {
		// sets the callback
		this.borderDashCallback = borderDashCallback;
		// stores and manages callback
		getChart().getOptions().setCallback(getElement(), Property.BORDER_DASH, borderDashCallback, borderDashCallbackProxy);
	}

	/**
	 * Sets the border dash callback.
	 * 
	 * @param borderDashCallback the border dash callback.
	 */
	public void setBorderDash(NativeCallback borderDashCallback) {
		// resets callback
		setBorderDash((BorderDashCallback<DatasetContext>) null);
		// stores and manages callback
		getChart().getOptions().setCallback(getElement(), Property.BORDER_DASH, borderDashCallback);
	}

	/**
	 * Returns the border dash offset callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the border dash offset callback, if set, otherwise <code>null</code>.
	 */
	public BorderDashOffsetCallback<DatasetContext> getBorderDashOffsetCallback() {
		return borderDashOffsetCallback;
	}

	/**
	 * Sets the border dash offset callback.
	 * 
	 * @param borderDashOffsetCallback the border dash offset callback.
	 */
	public void setBorderDashOffset(BorderDashOffsetCallback<DatasetContext> borderDashOffsetCallback) {
		// sets the callback
		this.borderDashOffsetCallback = borderDashOffsetCallback;
		// stores and manages callback
		getChart().getOptions().setCallback(getElement(), Property.BORDER_DASH_OFFSET, borderDashOffsetCallback, borderDashOffsetCallbackProxy);
	}

	/**
	 * Sets the border dash offset callback.
	 * 
	 * @param borderDashOffsetCallback the border dash offset callback.
	 */
	public void setBorderDashOffset(NativeCallback borderDashOffsetCallback) {
		// resets callback
		setBorderDashOffset((BorderDashOffsetCallback<DatasetContext>) null);
		// stores and manages callback
		getChart().getOptions().setCallback(getElement(), Property.BORDER_DASH_OFFSET, borderDashOffsetCallback);
	}

	/**
	 * Returns the border join style callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the border join style callback, if set, otherwise <code>null</code>.
	 */
	public JoinStyleCallback<DatasetContext> getBorderJoinStyleCallback() {
		return borderJoinStyleCallback;
	}

	/**
	 * Sets the border join style callback.
	 * 
	 * @param borderJoinStyleCallback the border join style callback.
	 */
	public void setBorderJoinStyle(JoinStyleCallback<DatasetContext> borderJoinStyleCallback) {
		// sets the callback
		this.borderJoinStyleCallback = borderJoinStyleCallback;
		// stores and manages callback
		getChart().getOptions().setCallback(getElement(), Property.BORDER_JOIN_STYLE, borderJoinStyleCallback, borderJoinStyleCallbackProxy);
	}

	/**
	 * Sets the border join style callback.
	 * 
	 * @param borderJoinStyleCallback the border join style callback.
	 */
	public void setBorderJoinStyle(NativeCallback borderJoinStyleCallback) {
		// resets callback
		setBorderJoinStyle((JoinStyleCallback<DatasetContext>) null);
		// stores and manages callback
		getChart().getOptions().setCallback(getElement(), Property.BORDER_JOIN_STYLE, borderJoinStyleCallback);
	}

	/**
	 * Returns the cubic interpolation mode callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the cubic interpolation mode callback, if set, otherwise <code>null</code>.
	 */
	public CubicInterpolationModeCallback getCubicInterpolationModeCallback() {
		return cubicInterpolationModeCallback;
	}

	/**
	 * Sets the cubic interpolation mode callback.
	 * 
	 * @param cubicInterpolationModeCallback the cubic interpolation mode callback.
	 */
	public void setCubicInterpolationMode(CubicInterpolationModeCallback cubicInterpolationModeCallback) {
		// sets the callback
		this.cubicInterpolationModeCallback = cubicInterpolationModeCallback;
		// stores and manages callback
		getChart().getOptions().setCallback(getElement(), Property.CUBIC_INTERPOLATION_MODE, cubicInterpolationModeCallback, cubicInterpolationModeCallbackProxy);
	}

	/**
	 * Sets the cubic interpolation mode callback.
	 * 
	 * @param cubicInterpolationModeCallback the cubic interpolation mode callback.
	 */
	public void setCubicInterpolationMode(NativeCallback cubicInterpolationModeCallback) {
		// resets callback
		setCubicInterpolationMode((CubicInterpolationModeCallback) null);
		// stores and manages callback
		getChart().getOptions().setCallback(getElement(), Property.CUBIC_INTERPOLATION_MODE, cubicInterpolationModeCallback);
	}

	/**
	 * Returns the fill callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the fill callback, if set, otherwise <code>null</code>.
	 */
	public FillCallback getFillCallback() {
		return fillCallback;
	}

	/**
	 * Sets the fill callback.
	 * 
	 * @param fillCallback the fill callback.
	 */
	public void setFill(FillCallback fillCallback) {
		// sets the callback
		this.fillCallback = fillCallback;
		// stores and manages callback
		getChart().getOptions().setCallback(getElement(), Property.FILL, fillCallback, fillCallbackProxy);
	}

	/**
	 * Sets the fill callback.
	 * 
	 * @param fillCallback the fill callback.
	 */
	public void setFill(NativeCallback fillCallback) {
		// resets callback
		setFill((FillCallback) null);
		// stores and manages callback
		getChart().getOptions().setCallback(getElement(), Property.FILL, fillCallback);
	}

	/**
	 * Returns the stepped callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the stepped callback, if set, otherwise <code>null</code>.
	 */
	public SteppedCallback getSteppedCallback() {
		return steppedCallback;
	}

	/**
	 * Sets the stepped callback.
	 * 
	 * @param steppedCallback the stepped callback.
	 */
	public void setStepped(SteppedCallback steppedCallback) {
		// sets the callback
		this.steppedCallback = steppedCallback;
		// stores and manages callback
		getChart().getOptions().setCallback(getElement(), Property.STEPPED, steppedCallback, steppedCallbackProxy);
	}

	/**
	 * Sets the stepped callback.
	 * 
	 * @param steppedCallback the stepped callback.
	 */
	public void setStepped(NativeCallback steppedCallback) {
		// resets callback
		setStepped((SteppedCallback) null);
		// stores and manages callback
		getChart().getOptions().setCallback(getElement(), Property.STEPPED, steppedCallback);
	}

	// ------------------------
	// INTERNALS for CALLBACKS
	// ------------------------

	/**
	 * Returns a {@link CapStyle} when the callback has been activated.
	 * 
	 * @param context native object as context.
	 * @param callback border cap style callback instance
	 * @param defaultValue default value of cap style
	 * @return a object property value, as {@link CapStyle}
	 */
	private String onBorderCapStyle(DatasetContext context, CapStyleCallback<DatasetContext> callback, CapStyle defaultValue) {
		return checkCallbackResult(ScriptableUtil.getOptionValue(context, callback), defaultValue);
	}

	/**
	 * Returns an array of integer when the callback has been activated.
	 * 
	 * @param context native object as context.
	 * @param callback border dash callback instance
	 * @return an array of integer
	 */
	private Array onBorderDash(DatasetContext context, BorderDashCallback<DatasetContext> callback) {
		// gets value
		List<Integer> result = ScriptableUtil.getOptionValue(context, callback);
		// default result
		return ArrayInteger.fromOrEmpty(result);
	}

	/**
	 * Returns a {@link CubicInterpolationMode} when the callback has been activated.
	 * 
	 * @param context native object as context.
	 * @param callback cubic interpolation mode callback instance
	 * @param defaultValue default value of cubic interpolation mode
	 * @return a object property value, as {@link CubicInterpolationMode}
	 */
	private String onCubicInterpolationMode(DatasetContext context, CubicInterpolationModeCallback callback, CubicInterpolationMode defaultValue) {
		return checkCallbackResult(ScriptableUtil.getOptionValue(context, callback), defaultValue);
	}

	/**
	 * Returns a object which can be a boolean, integer, string or {@link IsFill} when the callback has been activated.
	 * 
	 * @param context native object as context.
	 * @param callback fill callback instance
	 * @param defaultValue default value of fill mode
	 * @return a object property value
	 */
	private Object onFill(DatasetContext context, FillCallback callback, IsFill defaultValue) {
		// gets value
		Object result = ScriptableUtil.getOptionValue(context, callback);
		// checks and transforms result
		Object transformed = IsFill.transform(result);
		// checks if consistent
		if (transformed != null) {
			// returns the result
			return transformed;
		}
		// if here, result is null
		// then checks and returns default
		return IsFill.toObject(Checker.checkAndGetIfValid(defaultValue, "Default fill argument"));
	}

	/**
	 * Returns a {@link Stepped} when the callback has been activated.
	 * 
	 * @param context native object as context.
	 * @param callback stepped callback instance
	 * @param defaultValue default value of stepped
	 * @return a object property value, as {@link Stepped}
	 */
	private Object onStepped(DatasetContext context, SteppedCallback callback, Stepped defaultValue) {
		// gets value
		Stepped result = ScriptableUtil.getOptionValue(context, callback);
		// checks if consistent
		if (Stepped.FALSE.equals(result)) {
			// returns the result as boolean
			return false;
		} else if (result != null) {
			// returns the result as string
			return result.value();
		}
		// if here, result is null
		// then checks and returns default
		return Key.checkAndGetIfValid(defaultValue).value();
	}
}