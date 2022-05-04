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

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.callbacks.BoundsCallback;
import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.callbacks.ScaleOffsetCallback;
import org.pepstock.charba.client.callbacks.ScalePositionCallback;
import org.pepstock.charba.client.callbacks.ScaleWeightCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyBooleanCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyDoubleCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyStringCallback;
import org.pepstock.charba.client.callbacks.ScriptableUtil;
import org.pepstock.charba.client.callbacks.StackCallback;
import org.pepstock.charba.client.callbacks.StackedCallback;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.AxisKind;
import org.pepstock.charba.client.enums.AxisPosition;
import org.pepstock.charba.client.enums.Bounds;
import org.pepstock.charba.client.enums.DefaultScaleId;
import org.pepstock.charba.client.options.ScaleId;

/**
 * Axes are an integral part of a chart. They are used to determine how data maps to a pixel value on the chart. <br>
 * In a cartesian chart, there is 1 or more X axis and 1 or more Y axis to map points onto the 2 dimensional canvas.<br>
 * These axes are know as 'cartesian axes'.<br>
 * Axes that follow a cartesian grid are known as 'Cartesian Axes'.<br>
 * Cartesian axes are used for line, bar, and bubble charts.<br>
 * Four cartesian axes are included by default.<br>
 * <ul>
 * <li>linear
 * <li>logarithmic
 * <li>category
 * <li>time
 * </ul>
 * 
 * @author Andrea "Stock" Stocchero
 *
 * @param <T> type of tick to apply to axis
 */
public abstract class CartesianAxis<T extends CartesianTick> extends Axis {

	// --------------------------------------------
	// -- CALLBACKS PROXIES FOR AXIS PROPERTIES ---
	// --------------------------------------------
	// callback proxy to invoke the position function
	private final CallbackProxy<ProxyStringCallback> positionCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the offset function
	private final CallbackProxy<ProxyBooleanCallback> offsetCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the bounds function
	private final CallbackProxy<ProxyStringCallback> boundsCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the stacked function
	private final CallbackProxy<ProxyBooleanCallback> stackedCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the stackWeight function
	private final CallbackProxy<ProxyDoubleCallback> stackWeightCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the stack function
	private final CallbackProxy<ProxyStringCallback> stackCallbackProxy = JsHelper.get().newCallbackProxy();

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		// cartesian
		BOUNDS("bounds"),
		POSITION("position"),
		OFFSET("offset"),
		STACK("stack"),
		STACK_WEIGHT("stackWeight"),
		STACKED("stacked");

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

	// ----------------------------------------
	// -- USERS AXIS CALLBACKS x PROPERTIES ---
	// ----------------------------------------
	// user callback implementation for position
	private ScalePositionCallback positionCallback = null;
	// user callback implementation for offset
	private ScaleOffsetCallback offsetCallback = null;
	// user callback implementation for offset
	private BoundsCallback boundsCallback = null;
	// user callback implementation for stacked
	private StackedCallback stackedCallback = null;
	// user callback implementation for stackWeight
	private ScaleWeightCallback stackWeightCallback = null;
	// user callback implementation for stackWeight
	private StackCallback stackCallback = null;

	private final Grid grid;

	private final CartesianScaleTitle title;

	/**
	 * Builds the object storing the chart instance and cartesian axis type.
	 * 
	 * @param chart chart instance
	 * @param id axis id
	 * @param type axis type
	 * @param kind axis kind
	 */
	protected CartesianAxis(IsChart chart, ScaleId id, AxisType type, AxisKind kind) {
		super(chart, id, type, kind);
		// checks if scale id of scale is consistent
		// used for cartesian, it must not be set to unknown
		Checker.assertCheck(!DefaultScaleId.UNKNOWN.is(id.value()), "The scale id is invalid. It must not be " + id.value());
		// checks if axis kind of scale is consistent
		// used for cartesian, it must not be set to R
		Checker.assertCheck(!AxisKind.R.equals(kind), "The axis kind is invalid. It must not be " + AxisKind.R.value());
		// sets to the objects
		grid = new Grid(this);
		title = new CartesianScaleTitle(this);
		// -------------------------------------------------
		// -- SET CALLBACKS to PROXIES x AXIS PROPERTIES ---
		// -------------------------------------------------
		// sets function to proxy callback in order to invoke the java interface
		this.positionCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValue(createContext(context), getPositionCallback(), getDefaultValues().getPosition()).value());
		// sets function to proxy callback in order to invoke the java interface
		this.offsetCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValue(createContext(context), getOffsetCallback(), getDefaultValues().isOffset()).booleanValue());
		// sets function to proxy callback in order to invoke the java interface
		this.boundsCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValue(createContext(context), getBoundsCallback(), getDefaultValues().getBounds()).value());
		// sets function to proxy callback in order to invoke the java interface
		this.stackedCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValue(createContext(context), getStackedCallback(), getDefaultValues().isStacked()).booleanValue());
		// sets function to proxy callback in order to invoke the java interface
		this.stackWeightCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValueAsNumber(createContext(context), getStackWeightCallback(), getDefaultValues().getStackWeight()).doubleValue());
		// sets function to proxy callback in order to invoke the java interface
		this.stackCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValue(createContext(context), getStackCallback(), getDefaultValues().getStack()));
	}

	/**
	 * Returns the scale title element
	 * 
	 * @return the scale title element
	 */
	public CartesianScaleTitle getTitle() {
		return title;
	}

	/**
	 * Returns the ticks element
	 * 
	 * @return the ticks
	 */
	public abstract T getTicks();

	/**
	 * Returns the grid element
	 * 
	 * @return the grid
	 */
	public Grid getGrid() {
		return grid;
	}

	/**
	 * Sets if the axis are stacked or not.
	 * 
	 * @param stacked if the axis are stacked or not.
	 */
	public void setStacked(boolean stacked) {
		// resets callback
		setStacked((StackedCallback) null);
		// stores values
		getScale().setStacked(stacked);
	}

	/**
	 * Gets if the axis are stacked or not.
	 * 
	 * @return if the axis are stacked or not.
	 */
	public boolean isStacked() {
		return getScale().isStacked();
	}

	/**
	 * Sets if you might want to stack positive and negative values together.
	 * 
	 * @param stacked if you might want to stack positive and negative values together
	 */
	public void setSingleStacked(boolean stacked) {
		getScale().setSingleStacked(stacked);
	}

	/**
	 * Returns if you might want to stack positive and negative values together.
	 * 
	 * @return if you might want to stack positive and negative values together
	 */
	public boolean isSingleStacked() {
		return getScale().isSingleStacked();
	}

	/**
	 * If true, extra space is added to the both edges and the axis is scaled to fit in the chart area.<br>
	 * This is set to <code>true</code> in the bar chart by default.
	 * 
	 * @param offset extra space of axis
	 */
	public void setOffset(boolean offset) {
		// resets callback
		setOffset((ScaleOffsetCallback) null);
		// stores values
		getScale().setOffset(offset);
	}

	/**
	 * If true, extra space is added to the both edges and the axis is scaled to fit in the chart area.<br>
	 * This is set to <code>true</code> in the bar chart by default.
	 * 
	 * @return extra space of axis.
	 */
	public boolean isOffset() {
		return getScale().isOffset();
	}

	/**
	 * An axis can either be positioned at the edge of the chart, at the center of the chart area, or dynamically with respect to a data value.<br>
	 * To position the axis at the edge of the chart, set the position option to one of: 'top', 'left', 'bottom', 'right'.<br>
	 * To position the axis at the center of the chart area, set the position option to 'center'.
	 * 
	 * @param position position of axis
	 */
	public void setPosition(AxisPosition position) {
		// resets callback
		setPosition((ScalePositionCallback) null);
		// stores value
		getScale().setPosition(position);
	}

	/**
	 * An axis can either be positioned at the edge of the chart, at the center of the chart area, or dynamically with respect to a data value.<br>
	 * To position the axis at the edge of the chart, set the position option to one of: 'top', 'left', 'bottom', 'right'.<br>
	 * To position the axis at the center of the chart area, set the position option to 'center'.
	 * 
	 * @return position of axis.
	 */
	public AxisPosition getPosition() {
		return getScale().getPosition();
	}

	/**
	 * Sets the property controls the scale boundary strategy (bypassed by min/max time options).
	 * 
	 * @param bounds property controls the scale boundary strategy (bypassed by min/max time options).
	 */
	public void setBounds(Bounds bounds) {
		// resets callback
		setBounds((BoundsCallback) null);
		// stores values
		getScale().setBounds(bounds);
	}

	/**
	 * Returns the property controls the scale boundary strategy (bypassed by min/max time options).
	 * 
	 * @return property controls the scale boundary strategy (bypassed by min/max time options).
	 */
	public Bounds getBounds() {
		return getScale().getBounds();
	}

	/**
	 * Sets the stack group.<br>
	 * Axes at the same position with same stack are stacked.
	 * 
	 * @param stack the stack group.<br>
	 *            Axes at the same position with same stack are stacked
	 */
	public void setStack(String stack) {
		// resets callback
		setStack((StackCallback) null);
		// stores and manages callback
		getScale().setStack(stack);
	}

	/**
	 * Returns the stack group.<br>
	 * Axes at the same position with same stack are stacked.
	 * 
	 * @return the stack group.<br>
	 *         Axes at the same position with same stack are stacked
	 */
	public String getStack() {
		return getScale().getStack();
	}

	/**
	 * Sets the weight of the scale in stack group.<br>
	 * Used to determine the amount of allocated space for the scale within the group.
	 * 
	 * @param stackWeight the weight of the scale in stack group.<br>
	 *            Used to determine the amount of allocated space for the scale within the group.
	 */
	public void setStackWeight(double stackWeight) {
		// resets callback
		setStackWeight((ScaleWeightCallback) null);
		// stores value
		getScale().setStackWeight(stackWeight);
	}

	/**
	 * Returns the weight of the scale in stack group.<br>
	 * Used to determine the amount of allocated space for the scale within the group.
	 * 
	 * @return the weight of the scale in stack group.<br>
	 *         Used to determine the amount of allocated space for the scale within the group
	 */
	public double getStackWeight() {
		return getScale().getStackWeight();
	}

	// -----------------------------
	// AXIS PROPERTIES CALLBACKS
	// -----------------------------

	/**
	 * Returns the user callback that sets the axis position on the chart.
	 * 
	 * @return the user callback that sets the axis position on the chart
	 */
	public ScalePositionCallback getPositionCallback() {
		return positionCallback;
	}

	/**
	 * Sets the user callback that sets the axis position on the chart.
	 * 
	 * @param positionCallback the user callback that sets the axis position on the chart
	 */
	public void setPosition(ScalePositionCallback positionCallback) {
		// sets the callback
		this.positionCallback = positionCallback;
		// stores and manages callback
		setCallback(getConfiguration(), Property.POSITION, positionCallback, positionCallbackProxy);
	}

	/**
	 * Sets the user callback that sets the axis position on the chart.
	 * 
	 * @param positionCallback that sets the axis position on the chart
	 */
	public void setPosition(NativeCallback positionCallback) {
		// resets callback
		setPosition((ScalePositionCallback) null);
		// stores and manages callback
		setCallback(getConfiguration(), Property.POSITION, positionCallback);
	}

	/**
	 * Returns the user callback that sets the added extra space to the both edges and the axis is scaled to fit in the chart area.
	 * 
	 * @return the user callback that sets the added extra space to the both edges and the axis is scaled to fit in the chart area.
	 */
	public ScaleOffsetCallback getOffsetCallback() {
		return offsetCallback;
	}

	/**
	 * Sets the user callback that sets the added extra space to the both edges and the axis is scaled to fit in the chart area.
	 * 
	 * @param offsetCallback the user callback that sets the added extra space to the both edges and the axis is scaled to fit in the chart area.
	 */
	public void setOffset(ScaleOffsetCallback offsetCallback) {
		// sets the callback
		this.offsetCallback = offsetCallback;
		// stores and manages callback
		setCallback(getConfiguration(), Property.OFFSET, offsetCallback, offsetCallbackProxy);
	}

	/**
	 * Sets the user callback that sets the added extra space to the both edges and the axis is scaled to fit in the chart area.
	 * 
	 * @param offsetCallback that sets the added extra space to the both edges and the axis is scaled to fit in the chart area.
	 */
	public void setOffset(NativeCallback offsetCallback) {
		// resets callback
		setOffset((ScaleOffsetCallback) null);
		// stores and manages callback
		setCallback(getConfiguration(), Property.OFFSET, offsetCallback);
	}

	/**
	 * Returns the user callback that sets the property controls the scale boundary strategy (bypassed by min/max time options).
	 * 
	 * @return the user callback that sets the property controls the scale boundary strategy (bypassed by min/max time options).
	 */
	public BoundsCallback getBoundsCallback() {
		return boundsCallback;
	}

	/**
	 * Sets the user callback that sets the property controls the scale boundary strategy (bypassed by min/max time options).
	 * 
	 * @param boundsCallback the user callback that sets the property controls the scale boundary strategy (bypassed by min/max time options).
	 */
	public void setBounds(BoundsCallback boundsCallback) {
		// sets the callback
		this.boundsCallback = boundsCallback;
		// stores and manages callback
		setCallback(getConfiguration(), Property.BOUNDS, boundsCallback, boundsCallbackProxy);
	}

	/**
	 * Sets the user callback that sets the property controls the scale boundary strategy (bypassed by min/max time options).
	 * 
	 * @param boundsCallback that sets the property controls the scale boundary strategy (bypassed by min/max time options).
	 */
	public void setBounds(NativeCallback boundsCallback) {
		// resets callback
		setBounds((BoundsCallback) null);
		// stores and manages callback
		setCallback(getConfiguration(), Property.BOUNDS, boundsCallback);
	}

	/**
	 * Returns the user callback that sets if the axis are stacked or not.
	 * 
	 * @return the user callback that sets if the axis are stacked or not.
	 */
	public StackedCallback getStackedCallback() {
		return stackedCallback;
	}

	/**
	 * Sets the user callback that sets if the axis are stacked or not.
	 * 
	 * @param stackedCallback the user callback that sets if the axis are stacked or not.
	 */
	public void setStacked(StackedCallback stackedCallback) {
		// sets the callback
		this.stackedCallback = stackedCallback;
		// stores and manages callback
		setCallback(getConfiguration(), Property.STACKED, stackedCallback, stackedCallbackProxy);
	}

	/**
	 * Sets the user callback that sets if the axis are stacked or not.
	 * 
	 * @param stackedCallback that sets if the axis are stacked or not.
	 */
	public void setStacked(NativeCallback stackedCallback) {
		// resets callback
		setStacked((StackedCallback) null);
		// stores and manages callback
		setCallback(getConfiguration(), Property.STACKED, stackedCallback);
	}

	/**
	 * Returns the user callback that sets the weight of the scale in stack group.
	 * 
	 * @return the user callback that sets the weight of the scale in stack group.
	 */
	public ScaleWeightCallback getStackWeightCallback() {
		return stackWeightCallback;
	}

	/**
	 * Sets the user callback that sets the weight of the scale in stack group.
	 * 
	 * @param stackWeightCallback the user callback that sets the weight of the scale in stack group.
	 */
	public void setStackWeight(ScaleWeightCallback stackWeightCallback) {
		// sets the callback
		this.stackWeightCallback = stackWeightCallback;
		// stores and manages callback
		setCallback(getConfiguration(), Property.STACK_WEIGHT, stackWeightCallback, stackWeightCallbackProxy);
	}

	/**
	 * Sets the user callback that sets the weight of the scale in stack group.
	 * 
	 * @param stackWeightCallback that sets the weight of the scale in stack group.
	 */
	public void setStackWeight(NativeCallback stackWeightCallback) {
		// resets callback
		setStackWeight((ScaleWeightCallback) null);
		// stores and manages callback
		setCallback(getConfiguration(), Property.STACK_WEIGHT, stackWeightCallback);
	}

	/**
	 * Returns the user callback that sets the stack group.
	 * 
	 * @return the user callback that sets the stack group
	 */
	public StackCallback getStackCallback() {
		return stackCallback;
	}

	/**
	 * Sets the user callback that sets the stack group.
	 * 
	 * @param stackCallback the user callback that sets the stack group
	 */
	public void setStack(StackCallback stackCallback) {
		// sets the callback
		this.stackCallback = stackCallback;
		// stores and manages callback
		setCallback(getConfiguration(), Property.STACK, stackCallback, stackCallbackProxy);
	}

	/**
	 * Sets the user callback that sets the stack group.
	 * 
	 * @param stackCallback that sets the stack group
	 */
	public void setStack(NativeCallback stackCallback) {
		// resets callback
		setStack((StackCallback) null);
		// stores and manages callback
		setCallback(getConfiguration(), Property.STACK, stackCallback);
	}
}