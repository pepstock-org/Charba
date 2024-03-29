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
import org.pepstock.charba.client.callbacks.ColorCallback;
import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.callbacks.ScaleContext;
import org.pepstock.charba.client.callbacks.ScriptableDoubleChecker;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyArrayCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyDoubleCallback;
import org.pepstock.charba.client.callbacks.ScriptableUtil;
import org.pepstock.charba.client.callbacks.WidthCallback;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.Array;
import org.pepstock.charba.client.commons.ArrayInteger;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;

/**
 * It is used to configure angled lines that radiate from the center of the chart to the point labels.<br>
 * Note that these options only apply if display is true.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class RadialAngleLines extends AbstractScaleLines {

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the border dash function
	private final CallbackProxy<ProxyArrayCallback> borderDashCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the border dash offset function
	private final CallbackProxy<ProxyDoubleCallback> borderDashOffsetCallbackProxy = JsHelper.get().newCallbackProxy();

	// border dashoffset callback instance
	private BorderDashCallback<ScaleContext> borderDashCallback = null;
	// border dashoffset callback instance
	private BorderDashOffsetCallback<ScaleContext> borderDashOffsetCallback = null;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		BORDER_DASH("borderDash"),
		BORDER_DASH_OFFSET("borderDashOffset");

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
	 * Builds the object storing the axis which this angle lines belongs to.
	 * 
	 * @param axis axis which this angle lines belongs to.
	 */
	RadialAngleLines(Axis axis) {
		super(axis, axis.getDefaultValues().getAngleLines());
		// sets function to proxy callback in order to invoke the java interface
		this.borderDashCallbackProxy.setCallback(context -> onBorderDash(getAxis().createContext(context), getBorderDashCallback(), getAxis().getDefaultValues().getAngleLines().getBorderDash()));
		// sets function to proxy callback in order to invoke the java interface
		this.borderDashOffsetCallbackProxy.setCallback(context -> ScriptableUtil
				.getOptionValueAsNumber(getAxis().createContext(context), getBorderDashOffsetCallback(), getAxis().getDefaultValues().getAngleLines().getBorderDashOffset(), ScriptableDoubleChecker.POSITIVE_OR_DEFAULT).doubleValue());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.AbstractScaleLines#getElement()
	 */
	@Override
	AbstractNode getElement() {
		return getAxis().getScale().getAngleLines();
	}

	/**
	 * If true, angle lines are shown
	 * 
	 * @param display if true, angle lines are shown
	 */
	public void setDisplay(boolean display) {
		getAxis().getScale().getAngleLines().setDisplay(display);
	}

	/**
	 * If true, angle lines are shown
	 * 
	 * @return if true, angle lines are shown.
	 */
	public boolean isDisplay() {
		return getAxis().getScale().getAngleLines().isDisplay();
	}

	/**
	 * Sets the color of angled lines.
	 * 
	 * @param color color of angled lines.
	 */
	public void setColor(IsColor color) {
		// reset callback if there is
		setColor((ColorCallback<ScaleContext>) null);
		// stores value
		getAxis().getScale().getAngleLines().setColor(color);
	}

	/**
	 * Sets the color of angled lines.
	 * 
	 * @param color color of angled lines.
	 */
	public void setColor(String color) {
		// reset callback if there is
		setColor((ColorCallback<ScaleContext>) null);
		// stores value
		getAxis().getScale().getAngleLines().setColor(color);
	}

	/**
	 * Returns the color of angled lines.
	 * 
	 * @return color of angled lines.
	 */
	public String getColorAsString() {
		return getAxis().getScale().getAngleLines().getColorAsString();
	}

	/**
	 * Returns the color of angled lines.
	 * 
	 * @return color of angled lines.
	 */
	public IsColor getColor() {
		return getAxis().getScale().getAngleLines().getColor();
	}

	/**
	 * Sets the width of angled lines.
	 * 
	 * @param lineWidth width of angled lines.
	 */
	public void setLineWidth(int lineWidth) {
		// reset callback if there is
		setLineWidth((WidthCallback<ScaleContext>) null);
		// stores value
		getAxis().getScale().getAngleLines().setLineWidth(lineWidth);
	}

	/**
	 * Returns the width of angled lines.
	 * 
	 * @return width of angled lines.
	 */
	public int getLineWidth() {
		return getAxis().getScale().getAngleLines().getLineWidth();
	}

	/**
	 * Sets the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 * 
	 * @param borderDash the line dash pattern used when stroking lines
	 */
	public void setBorderDash(int... borderDash) {
		// reset callback if there is
		setBorderDash((BorderDashCallback<ScaleContext>) null);
		// stores value
		getAxis().getScale().getAngleLines().setBorderDash(borderDash);
	}

	/**
	 * Returns the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 * 
	 * @return the line dash pattern used when stroking lines
	 */
	public List<Integer> getBorderDash() {
		return getAxis().getScale().getAngleLines().getBorderDash();
	}

	/**
	 * Sets the line dash pattern offset.
	 * 
	 * @param borderDashOffset Offset for line dashes.
	 */
	public void setBorderDashOffset(double borderDashOffset) {
		// reset callback if there is
		setBorderDashOffset((BorderDashOffsetCallback<ScaleContext>) null);
		// stores value
		getAxis().getScale().getAngleLines().setBorderDashOffset(borderDashOffset);
	}

	/**
	 * Returns the line dash pattern offset.
	 * 
	 * @return Offset for line dashes.
	 */
	public double getBorderDashOffset() {
		return getAxis().getScale().getAngleLines().getBorderDashOffset();
	}

	// ------------------
	// CALLBACKS
	// ------------------

	/**
	 * Returns the border dash callback when element is hovered, if set, otherwise <code>null</code>.
	 * 
	 * @return the border dash callback when element is hovered, if set, otherwise <code>null</code>.
	 */
	public BorderDashCallback<ScaleContext> getBorderDashCallback() {
		return borderDashCallback;
	}

	/**
	 * Sets the border dash callback when element is hovered.
	 * 
	 * @param borderDashCallback the border dash callback when element is hovered.
	 */
	public void setBorderDash(BorderDashCallback<ScaleContext> borderDashCallback) {
		// sets the callback
		this.borderDashCallback = borderDashCallback;
		// stores and manages callback
		getAxis().setCallback(getAxis().getConfiguration().getAngleLines(), Property.BORDER_DASH, borderDashCallback, borderDashCallbackProxy);
	}

	/**
	 * Sets the border dash callback when element is hovered.
	 * 
	 * @param borderDashCallback the border dash callback when element is hovered.
	 */
	public void setBorderDash(NativeCallback borderDashCallback) {
		// resets the callback
		setBorderDash((BorderDashCallback<ScaleContext>) null);
		// stores and manages callback
		getAxis().setCallback(getAxis().getConfiguration().getAngleLines(), Property.BORDER_DASH, borderDashCallback);
	}

	/**
	 * Returns the border dash offset callback instance.
	 * 
	 * @return the border dash offset callback instance
	 */
	public BorderDashOffsetCallback<ScaleContext> getBorderDashOffsetCallback() {
		return borderDashOffsetCallback;
	}

	/**
	 * Sets the border dash offset callback instance.
	 * 
	 * @param borderDashOffsetCallback the border dash offset callback instance
	 */
	public void setBorderDashOffset(BorderDashOffsetCallback<ScaleContext> borderDashOffsetCallback) {
		// stores callback
		this.borderDashOffsetCallback = borderDashOffsetCallback;
		// stores and manages callback
		getAxis().setCallback(getAxis().getConfiguration().getAngleLines(), Property.BORDER_DASH_OFFSET, borderDashOffsetCallback, borderDashOffsetCallbackProxy);
	}

	/**
	 * Sets the border dash offset callback instance.
	 * 
	 * @param borderDashOffsetCallback the border dash offset callback instance
	 */
	public void setBorderDashOffset(NativeCallback borderDashOffsetCallback) {
		// resets callback
		setBorderDashOffset((BorderDashOffsetCallback<ScaleContext>) null);
		// stores and manages callback
		getAxis().setCallback(getAxis().getConfiguration().getAngleLines(), Property.BORDER_DASH_OFFSET, borderDashOffsetCallback);
	}

	// ------------------------
	// INTERNALS
	// ------------------------

	/**
	 * Returns an array of integer when the callback has been activated.
	 * 
	 * @param context native object as context.
	 * @param borderDashCallback border dash callback instance
	 * @param defaultValue default value of options
	 * @return an array of integer
	 */
	private Array onBorderDash(ScaleContext context, BorderDashCallback<ScaleContext> borderDashCallback, List<Integer> defaultValue) {
		// gets value
		List<Integer> result = ScriptableUtil.getOptionValue(context, borderDashCallback);
		// checks if consistent
		if (result != null) {
			// returns result of callback
			return ArrayInteger.fromOrEmpty(result);
		}
		// default result
		return ArrayInteger.fromOrEmpty(defaultValue);
	}

}