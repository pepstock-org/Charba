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

import org.pepstock.charba.client.callbacks.AngleCallback;
import org.pepstock.charba.client.callbacks.BorderAlignCallback;
import org.pepstock.charba.client.callbacks.BorderRadiusCallback;
import org.pepstock.charba.client.callbacks.DatasetContext;
import org.pepstock.charba.client.callbacks.JoinStyleCallback;
import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.callbacks.OffsetCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyDoubleCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyIntegerCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyStringCallback;
import org.pepstock.charba.client.callbacks.ScriptableUtil;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.defaults.IsDefaultArc;
import org.pepstock.charba.client.enums.BorderAlign;
import org.pepstock.charba.client.enums.JoinStyle;
import org.pepstock.charba.client.options.AbstractElement;

/**
 * Arcs are used in the polar area, doughnut and pie charts.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class Arc extends AbstractConfigurationElement<IsDefaultArc> {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		ANGLE("angle"),
		BORDER_ALIGN("borderAlign"),
		BORDER_JOIN_STYLE("borderJoinStyle"),
		BORDER_RADIUS("borderRadius"),
		HOVER_BORDER_JOIN_STYLE("hoverBorderJoinStyle"),
		HOVER_OFFSET("hoverOffset"),
		OFFSET("offset");

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
	// callback proxy to invoke the border align function
	private final CallbackProxy<ProxyStringCallback> borderAlignCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the angle function
	private final CallbackProxy<ProxyDoubleCallback> angleCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the offset function
	private final CallbackProxy<ProxyIntegerCallback> offsetCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the hover offset function
	private final CallbackProxy<ProxyIntegerCallback> hoverOffsetCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the border radius function
	private final CallbackProxy<ProxyIntegerCallback> borderRadiusCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the border join style function
	private final CallbackProxy<ProxyStringCallback> borderJoinStyleCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the hover border join style function
	private final CallbackProxy<ProxyStringCallback> hoverBorderJoinStyleCallbackProxy = JsHelper.get().newCallbackProxy();

	// border align callback instance
	private BorderAlignCallback borderAlignCallback = null;
	// angle callback instance
	private AngleCallback angleCallback = null;
	// offset callback instance
	private OffsetCallback<DatasetContext> offsetCallback = null;
	// hover offset callback instance
	private OffsetCallback<DatasetContext> hoverOffsetCallback = null;
	// border radius callback instance
	private BorderRadiusCallback<DatasetContext> borderRadiusCallback = null;
	// border join style callback instance
	private JoinStyleCallback<DatasetContext> borderJoinStyleCallback = null;
	// hover border join style callback instance
	private JoinStyleCallback<DatasetContext> hoverBorderJoinStyleCallback = null;

	/**
	 * Builds the object setting the java script options object and defaults options for arc.
	 * 
	 * @param options root options of chart
	 */
	Arc(ConfigurationOptions options) {
		super(options);
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// sets function to proxy callback in order to invoke the java interface
		this.offsetCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValueAsNumber(createContext(context), getOffsetCallback(), getDefaultElement().getOffset()).intValue());
		// sets function to proxy callback in order to invoke the java interface
		this.hoverOffsetCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValueAsNumber(createContext(context), getHoverOffsetCallback(), getDefaultElement().getOffset()).intValue());
		// sets function to proxy callback in order to invoke the java interface
		this.borderRadiusCallbackProxy.setCallback(context -> onBorderRadius(createContext(context), getBorderRadiusCallback(), getDefaultElement().getBorderRadius()));
		// gets value and calls the callback
		this.borderAlignCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValueAsString(createContext(context), getBorderAlignCallback(), getDefaultElement().getBorderAlign()).value());
		// sets function to proxy callback in order to invoke the java interface
		this.angleCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValueAsNumber(createContext(context), getAngleCallback(), getDefaultElement().getAngle()).doubleValue());
		// sets function to proxy callback in order to invoke the java interface
		this.borderJoinStyleCallbackProxy.setCallback(context -> onBorderJoinStyle(createContext(context), getBorderJoinStyleCallback(), getDefaultElement().getBorderJoinStyle()));
		// sets function to proxy callback in order to invoke the java interface
		this.hoverBorderJoinStyleCallbackProxy.setCallback(context -> onBorderJoinStyle(createContext(context), getHoverBorderJoinStyleCallback(), getDefaultElement().getHoverBorderJoinStyle()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.AbstractConfigurationElement#getElement()
	 */
	@Override
	protected AbstractElement<IsDefaultArc> getElement() {
		return getConfiguration().getElements().getArc();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.AbstractConfigurationElement#getDefaultElement()
	 */
	@Override
	protected IsDefaultArc getDefaultElement() {
		return getOptions().getDefaultValues().getElements().getArc();
	}

	/**
	 * Sets the property to set the border alignment on chart datasets.
	 * 
	 * @param align the property to set the border alignment on chart datasets
	 */
	public void setBorderAlign(BorderAlign align) {
		// resets callback
		setBorderAlign((BorderAlignCallback) null);
		// stores value
		getConfiguration().getElements().getArc().setBorderAlign(align);
	}

	/**
	 * Returns the property to set the border alignment on chart datasets.
	 * 
	 * @return the property to set the border alignment on chart datasets.
	 */
	public BorderAlign getBorderAlign() {
		return getConfiguration().getElements().getArc().getBorderAlign();
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
		getConfiguration().getElements().getArc().setBorderJoinStyle(borderJoinStyle);
	}

	/**
	 * Returns how two connecting segments (of lines, arcs or curves) with non-zero lengths in a shape are joined together (degenerate segments with zero lengths, whose specified
	 * end points and control points are exactly at the same position, are skipped).
	 * 
	 * @return how two connecting segments (of lines, arcs or curves) with non-zero lengths in a shape are joined together
	 */
	public JoinStyle getBorderJoinStyle() {
		return getConfiguration().getElements().getArc().getBorderJoinStyle();
	}

	/**
	 * Sets how two connecting segments (of lines, arcs or curves) with non-zero lengths in a shape are joined together (degenerate segments with zero lengths, whose specified end
	 * points and control points are exactly at the same position, are skipped).
	 * 
	 * @param borderJoinStyle how two connecting segments (of lines, arcs or curves) with non-zero lengths in a shape are joined together
	 */
	public void setHoverBorderJoinStyle(JoinStyle borderJoinStyle) {
		// resets callback
		setHoverBorderJoinStyle((JoinStyleCallback<DatasetContext>) null);
		// stores value
		getConfiguration().getElements().getArc().setHoverBorderJoinStyle(borderJoinStyle);
	}

	/**
	 * Returns how two connecting segments (of lines, arcs or curves) with non-zero lengths in a shape are joined together (degenerate segments with zero lengths, whose specified
	 * end points and control points are exactly at the same position, are skipped).
	 * 
	 * @return how two connecting segments (of lines, arcs or curves) with non-zero lengths in a shape are joined together
	 */
	public JoinStyle getHoverBorderJoinStyle() {
		return getConfiguration().getElements().getArc().getHoverBorderJoinStyle();
	}

	/**
	 * Sets the relative thickness of the data set.<br>
	 * Providing a value for weight will cause the pie or doughnut data set to be drawn with a thickness relative to the sum of all the data set weight values.
	 * 
	 * @param weight the relative thickness of the data set
	 */
	public void setWeight(double weight) {
		getConfiguration().getElements().getArc().setWeight(weight);
	}

	/**
	 * Returns the relative thickness of the data set.<br>
	 * Providing a value for weight will cause the pie or doughnut data set to be drawn with a thickness relative to the sum of all the data set weight values.
	 * 
	 * @return the relative thickness of the data set
	 */
	public double getWeight() {
		return getConfiguration().getElements().getArc().getWeight();
	}

	/**
	 * Sets the arc angle to cover.
	 * 
	 * @param angle the arc angle to cover
	 */
	public void setAngle(double angle) {
		// resets callback
		setAngle((AngleCallback) null);
		// stores value
		getConfiguration().getElements().getArc().setAngle(angle);
	}

	/**
	 * Returns the arc angle to cover.
	 * 
	 * @return the arc angle to cover
	 */
	public double getAngle() {
		return getConfiguration().getElements().getArc().getAngle();
	}

	/**
	 * Sets the arc offset (in pixels).
	 * 
	 * @param offset the arc offset
	 */
	public void setOffset(int offset) {
		// resets callback
		setOffset((OffsetCallback<DatasetContext>) null);
		// stores value
		getConfiguration().getElements().getArc().setOffset(offset);
	}

	/**
	 * Returns the arc offset (in pixels).
	 * 
	 * @return the arc offset
	 */
	public int getOffset() {
		return getConfiguration().getElements().getArc().getOffset();
	}

	/**
	 * Sets the fixed arc offset (in pixels).<br>
	 * Similar to <code>offset</code> but applies to all arcs.
	 * 
	 * @param spacing the fixed arc offset (in pixels)
	 */
	public void setSpacing(int spacing) {
		getConfiguration().getElements().getArc().setSpacing(spacing);
	}

	/**
	 * Returns the fixed arc offset (in pixels).<br>
	 * Similar to <code>offset</code> but applies to all arcs.
	 * 
	 * @return the fixed arc offset (in pixels)
	 */
	public int getSpacing() {
		return getConfiguration().getElements().getArc().getSpacing();
	}

	/**
	 * Sets the arc border radius (in pixels).
	 * 
	 * @param borderRadius the arc border radius (in pixels).
	 */
	public void setBorderRadius(int borderRadius) {
		// resets callback
		setBorderRadius((BorderRadiusCallback<DatasetContext>) null);
		// stores value
		getConfiguration().getElements().getArc().setBorderRadius(borderRadius);
	}

	/**
	 * Returns the arc border radius (in pixels).
	 * 
	 * @return the arc border radius (in pixels).
	 */
	public int getBorderRadius() {
		return getConfiguration().getElements().getArc().getBorderRadius();
	}

	/**
	 * Sets the arc offset (in pixels) when hovered.
	 * 
	 * @param offset the arc offset when hovered
	 */
	public void setHoverOffset(int offset) {
		// resets callback
		setHoverOffset((OffsetCallback<DatasetContext>) null);
		// stores value
		getConfiguration().getElements().getArc().setHoverOffset(offset);
	}

	/**
	 * Returns the arc offset (in pixels) when hovered.
	 * 
	 * @return the arc offset when hovered
	 */
	public int getHoverOffset() {
		return getConfiguration().getElements().getArc().getHoverOffset();
	}

	/**
	 * Sets <code>true</code> if the arc is curved.
	 * 
	 * @param circular <code>true</code> if the arc is curved
	 */
	public void setCircular(int circular) {
		getConfiguration().getElements().getArc().setCircular(circular);
	}

	/**
	 * Returns <code>true</code> if the arc is curved.
	 * 
	 * @return <code>true</code> if the arc is curved
	 */
	public boolean isCircular() {
		return getConfiguration().getElements().getArc().isCircular();
	}

	// -----------------
	// CALLBACK
	// -----------------

	/**
	 * Returns the border align callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the border align callback, if set, otherwise <code>null</code>.
	 */
	public BorderAlignCallback getBorderAlignCallback() {
		return borderAlignCallback;
	}

	/**
	 * Sets the border align callback.
	 * 
	 * @param borderAlignCallback the border align callback.
	 */
	public void setBorderAlign(BorderAlignCallback borderAlignCallback) {
		// sets the callback
		this.borderAlignCallback = borderAlignCallback;
		// stores and manages callback
		getChart().getOptions().setCallback(getElement(), Property.BORDER_ALIGN, borderAlignCallback, borderAlignCallbackProxy);
	}

	/**
	 * Sets the border align callback.
	 * 
	 * @param borderAlignCallback the border align callback.
	 */
	public void setBorderAlign(NativeCallback borderAlignCallback) {
		// resets callback
		setBorderAlign((BorderAlignCallback) null);
		// stores and manages callback
		getChart().getOptions().setCallback(getElement(), Property.BORDER_ALIGN, borderAlignCallback);
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
	 * Returns the hover border join style callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the hover border join style callback, if set, otherwise <code>null</code>.
	 */
	public JoinStyleCallback<DatasetContext> getHoverBorderJoinStyleCallback() {
		return hoverBorderJoinStyleCallback;
	}

	/**
	 * Sets the hover border join style callback.
	 * 
	 * @param hoverBorderJoinStyleCallback the hover border join style callback.
	 */
	public void setHoverBorderJoinStyle(JoinStyleCallback<DatasetContext> hoverBorderJoinStyleCallback) {
		// sets the callback
		this.hoverBorderJoinStyleCallback = hoverBorderJoinStyleCallback;
		// stores and manages callback
		getChart().getOptions().setCallback(getElement(), Property.HOVER_BORDER_JOIN_STYLE, hoverBorderJoinStyleCallback, hoverBorderJoinStyleCallbackProxy);
	}

	/**
	 * Sets the hover border join style callback.
	 * 
	 * @param hoverBorderJoinStyleCallback the hover border join style callback.
	 */
	public void setHoverBorderJoinStyle(NativeCallback hoverBorderJoinStyleCallback) {
		// resets callback
		setHoverBorderJoinStyle((JoinStyleCallback<DatasetContext>) null);
		// stores and manages callback
		getChart().getOptions().setCallback(getElement(), Property.HOVER_BORDER_JOIN_STYLE, hoverBorderJoinStyleCallback);
	}

	/**
	 * Returns the border radius callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the border radius callback, if set, otherwise <code>null</code>.
	 */
	public BorderRadiusCallback<DatasetContext> getBorderRadiusCallback() {
		return borderRadiusCallback;
	}

	/**
	 * Sets the border radius callback.
	 * 
	 * @param borderRadiusCallback the border radius callback.
	 */
	public void setBorderRadius(BorderRadiusCallback<DatasetContext> borderRadiusCallback) {
		// sets the callback
		this.borderRadiusCallback = borderRadiusCallback;
		// stores and manages callback
		getChart().getOptions().setCallback(getElement(), Property.BORDER_RADIUS, borderRadiusCallback, borderRadiusCallbackProxy);
	}

	/**
	 * Sets the border radius callback.
	 * 
	 * @param borderRadiusCallback the border radius callback.
	 */
	public void setBorderRadius(NativeCallback borderRadiusCallback) {
		// resets callback
		setBorderRadius((BorderRadiusCallback<DatasetContext>) null);
		// stores and manages callback
		getChart().getOptions().setCallback(getElement(), Property.BORDER_RADIUS, borderRadiusCallback);
	}

	/**
	 * Returns the offset callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the offset callback, if set, otherwise <code>null</code>.
	 */
	public OffsetCallback<DatasetContext> getOffsetCallback() {
		return offsetCallback;
	}

	/**
	 * Sets the offset callback.
	 * 
	 * @param offsetCallback the offset callback.
	 */
	public void setOffset(OffsetCallback<DatasetContext> offsetCallback) {
		// sets the callback
		this.offsetCallback = offsetCallback;
		// stores and manages callback
		getChart().getOptions().setCallback(getElement(), Property.OFFSET, offsetCallback, offsetCallbackProxy);
	}

	/**
	 * Sets the offset callback.
	 * 
	 * @param offsetCallback the offset callback.
	 */
	public void setOffset(NativeCallback offsetCallback) {
		// resets callback
		setOffset((OffsetCallback<DatasetContext>) null);
		// stores and manages callback
		getChart().getOptions().setCallback(getElement(), Property.OFFSET, offsetCallback);
	}

	/**
	 * Returns the offset callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the offset callback, if set, otherwise <code>null</code>.
	 */
	public OffsetCallback<DatasetContext> getHoverOffsetCallback() {
		return hoverOffsetCallback;
	}

	/**
	 * Sets the offset callback, when hovered.
	 * 
	 * @param hoverOffsetCallback the offset callback, when hovered.
	 */
	public void setHoverOffset(OffsetCallback<DatasetContext> hoverOffsetCallback) {
		// sets the callback
		this.hoverOffsetCallback = hoverOffsetCallback;
		// stores and manages callback
		getChart().getOptions().setCallback(getElement(), Property.HOVER_OFFSET, hoverOffsetCallback, hoverOffsetCallbackProxy);
	}

	/**
	 * Sets the offset callback, when hovered.
	 * 
	 * @param hoverOffsetCallback the offset callback, when hovered.
	 */
	public void setHoverOffset(NativeCallback hoverOffsetCallback) {
		// resets callback
		setHoverOffset((OffsetCallback<DatasetContext>) null);
		// stores and manages callback
		getChart().getOptions().setCallback(getElement(), Property.HOVER_OFFSET, hoverOffsetCallback);
	}

	/**
	 * Returns the angle callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the angle callback, if set, otherwise <code>null</code>.
	 */
	public AngleCallback getAngleCallback() {
		return angleCallback;
	}

	/**
	 * Sets the angle callback.
	 * 
	 * @param angleCallback the angle callback.
	 */
	public void setAngle(AngleCallback angleCallback) {
		// sets the callback
		this.angleCallback = angleCallback;
		// stores and manages callback
		getChart().getOptions().setCallback(getElement(), Property.ANGLE, angleCallback, angleCallbackProxy);
	}

	/**
	 * Sets the angle callback.
	 * 
	 * @param angleCallback the angle callback.
	 */
	public void setAngle(NativeCallback angleCallback) {
		// resets callback
		setAngle((AngleCallback) null);
		// stores and manages callback
		getChart().getOptions().setCallback(getElement(), Property.ANGLE, angleCallback);
	}
}