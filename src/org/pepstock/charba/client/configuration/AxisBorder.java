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
import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.callbacks.ScaleContext;
import org.pepstock.charba.client.callbacks.ScriptableDoubleChecker;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyArrayCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyDoubleCallback;
import org.pepstock.charba.client.callbacks.ScriptableUtil;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.Array;
import org.pepstock.charba.client.commons.ArrayInteger;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;

/**
 * Defines options for the border that run perpendicular to the axis.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class AxisBorder extends AxisContainer {

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the border dash offset function
	private final CallbackProxy<ProxyDoubleCallback> dashOffsetCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the border dash function
	private final CallbackProxy<ProxyArrayCallback> dashCallbackProxy = JsHelper.get().newCallbackProxy();

	// border dashoffset callback instance
	private BorderDashOffsetCallback<ScaleContext> dashOffsetCallback = null;
	// border dash callback instance
	private BorderDashCallback<ScaleContext> dashCallback = null;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		DASH("dash"),
		DASH_OFFSET("dashOffset");

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
	 * Builds the object storing the axis which this scale title belongs to.
	 * 
	 * @param axis axis which this scale title belongs to.
	 */
	AxisBorder(Axis axis) {
		super(axis);
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// sets function to proxy callback in order to invoke the java interface
		this.dashOffsetCallbackProxy
				.setCallback(context -> ScriptableUtil.getOptionValueAsNumber(getAxis().createContext(context), getDashOffsetCallback(), getAxis().getDefaultValues().getBorder().getDashOffset(), ScriptableDoubleChecker.POSITIVE_OR_DEFAULT).doubleValue());
		// sets function to proxy callback in order to invoke the java interface
		this.dashCallbackProxy.setCallback(context -> onDash(getAxis().createContext(context), getDashCallback()));

	}

	/***
	 * If true, draw border at the edge between the axis and the chart area.
	 * 
	 * @param display If true, draw border at the edge between the axis and the chart area.
	 */
	public void setDisplay(boolean display) {
		getAxis().getScale().getBorder().setDisplay(display);
	}

	/**
	 * If true, draw border at the edge between the axis and the chart area.
	 * 
	 * @return If true, draw border at the edge between the axis and the chart area.
	 */
	public boolean isDisplay() {
		return getAxis().getScale().getBorder().isDisplay();
	}

	/**
	 * If set, used as the color of the border line.<br>
	 * If unset, the first color option is resolved and used.
	 * 
	 * @param color if set, used as the color of the border line.<br>
	 *            If unset, the first color option is resolved and used.
	 */
	public void setColor(IsColor color) {
		getAxis().getScale().getBorder().setColor(color);
	}

	/**
	 * If set, used as the color of the border line.<br>
	 * If unset, the first color option is resolved and used.
	 * 
	 * @param color if set, used as the color of the border line.<br>
	 *            If unset, the first color option is resolved and used.
	 */
	public void setColor(String color) {
		getAxis().getScale().getBorder().setColor(color);
	}

	/**
	 * If set, used as the color of the border line.<br>
	 * If unset, the first color option is resolved and used.
	 * 
	 * @return if set, used as the color of the border line.<br>
	 *         If unset, the first color option is resolved and used.
	 */
	public String getColorAsString() {
		return getAxis().getScale().getBorder().getColorAsString();
	}

	/**
	 * If set, used as the color of the border line.<br>
	 * If unset, the first color option is resolved and used.
	 * 
	 * @return if set, used as the color of the border line.<br>
	 *         If unset, the first color option is resolved and used.
	 */
	public IsColor getColor() {
		return getAxis().getScale().getBorder().getColor();
	}

	/**
	 * If set, used as the width of the border line.<br>
	 * If unset, the first lineWidth option is resolved and used.
	 * 
	 * @param borderWidth if set, used as the width of the border line.<br>
	 *            If unset, the first lineWidth option is resolved and used.
	 */
	public void setWidth(int borderWidth) {
		getAxis().getScale().getBorder().setWidth(borderWidth);
	}

	/**
	 * If set, used as the width of the border line.<br>
	 * If unset, the first lineWidth option is resolved and used.
	 * 
	 * @return if set, used as the width of the border line.<br>
	 *         If unset, the first lineWidth option is resolved and used.
	 */
	public int getWidth() {
		return getAxis().getScale().getBorder().getWidth();
	}

	/**
	 * Sets the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 * 
	 * @param borderDash the line dash pattern used when stroking lines
	 */
	public void setDash(int... borderDash) {
		// resets callback
		setDash((BorderDashCallback<ScaleContext>) null);
		// stores value
		getAxis().getScale().getBorder().setDash(borderDash);
	}

	/**
	 * Returns the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 * 
	 * @return the line dash pattern used when stroking lines.
	 */
	public List<Integer> getDash() {
		return getAxis().getScale().getBorder().getDash();
	}

	/**
	 * Sets the line dash pattern offset.
	 * 
	 * @param borderDashOffset Offset for line dashes.
	 */
	public void setDashOffset(double borderDashOffset) {
		// reset callback if there is
		setDashOffset((BorderDashOffsetCallback<ScaleContext>) null);
		// stores value
		getAxis().getScale().getBorder().setDashOffset(borderDashOffset);
	}

	/**
	 * Returns the line dash pattern offset.
	 * 
	 * @return Offset for line dashes.
	 */
	public double getDashOffset() {
		return getAxis().getScale().getBorder().getDashOffset();
	}

	/**
	 * Sets z-index of border layer.<br>
	 * Values less than or equals to 0 are drawn under datasets, greater than 0 on top.
	 * 
	 * @param z z-index of border layer.<br>
	 *            Values less than or equals to 0 are drawn under datasets, greater than 0 on top.
	 */
	public void setZ(int z) {
		getAxis().getScale().getBorder().setZ(z);
	}

	/**
	 * Returns z-index of border layer.<br>
	 * Values less than or equals to 0 are drawn under datasets, greater than 0 on top.
	 * 
	 * @return z-index of border layer.<br>
	 *         Values less than or equals to 0 are drawn under datasets, greater than 0 on top.
	 */
	public int getZ() {
		return getAxis().getScale().getBorder().getZ();
	}

	// ------------------------
	// CALLBACKS
	// ------------------------

	/**
	 * Returns the border dash offset callback instance.
	 * 
	 * @return the border dash offset callback instance
	 */
	public BorderDashOffsetCallback<ScaleContext> getDashOffsetCallback() {
		return dashOffsetCallback;
	}

	/**
	 * Sets the border dash offset callback instance.
	 * 
	 * @param borderDashOffsetCallback the border dash offset callback instance
	 */
	public void setDashOffset(BorderDashOffsetCallback<ScaleContext> borderDashOffsetCallback) {
		// stores callback
		this.dashOffsetCallback = borderDashOffsetCallback;
		// stores and manages callback
		getAxis().setCallback(getAxis().getScale().getBorder(), Property.DASH_OFFSET, borderDashOffsetCallback, dashOffsetCallbackProxy);
	}

	/**
	 * Sets the border dash offset callback instance.
	 * 
	 * @param borderDashOffsetCallback the border dash offset callback instance
	 */
	public void setDashOffset(NativeCallback borderDashOffsetCallback) {
		// resets callback
		setDashOffset((BorderDashOffsetCallback<ScaleContext>) null);
		// stores and manages callback
		getAxis().setCallback(getAxis().getScale().getBorder(), Property.DASH_OFFSET, borderDashOffsetCallback);
	}

	/**
	 * Returns the border dash callback instance.
	 * 
	 * @return the border dash callback instance
	 */
	public BorderDashCallback<ScaleContext> getDashCallback() {
		return dashCallback;
	}

	/**
	 * Sets the border dash callback instance.
	 * 
	 * @param borderDashCallback the border dash callback instance
	 */
	public void setDash(BorderDashCallback<ScaleContext> borderDashCallback) {
		// stores callback
		this.dashCallback = borderDashCallback;
		// stores and manages callback
		getAxis().setCallback(getAxis().getScale().getBorder(), Property.DASH, borderDashCallback, dashCallbackProxy);
	}

	/**
	 * Sets the border dash callback instance.
	 * 
	 * @param borderDashCallback the border dash callback instance
	 */
	public void setDash(NativeCallback borderDashCallback) {
		// resets callback
		setDash((BorderDashCallback<ScaleContext>) null);
		// stores and manages callback
		getAxis().setCallback(getAxis().getScale().getBorder(), Property.DASH, borderDashCallback);
	}

	// -----------------
	// INTERNALS
	// -----------------

	/**
	 * Returns an array of integer when the callback has been activated.
	 * 
	 * @param context native object as context.
	 * @param callback border dash callback instance
	 * @return an array of integer
	 */
	private Array onDash(ScaleContext context, BorderDashCallback<ScaleContext> callback) {
		// gets value
		List<Integer> result = ScriptableUtil.getOptionValue(context, callback);
		// default result
		return ArrayInteger.fromOrEmpty(result);
	}
}