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
package org.pepstock.charba.client.geo;

import org.pepstock.charba.client.callbacks.ScaleContext;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyIntegerCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyObjectCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyStringCallback;
import org.pepstock.charba.client.callbacks.ScriptableIntegerChecker;
import org.pepstock.charba.client.callbacks.ScriptableUtil;
import org.pepstock.charba.client.callbacks.WidthCallback;
import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.CallbackPropertyHandler;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.geo.callbacks.AlignCallback;
import org.pepstock.charba.client.geo.callbacks.LengthCallback;
import org.pepstock.charba.client.geo.callbacks.MarginCallback;
import org.pepstock.charba.client.geo.callbacks.PositionCallback;
import org.pepstock.charba.client.geo.enums.Align;
import org.pepstock.charba.client.geo.enums.Position;

/**
 * Legend configuration for GEO chart scale.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Legend extends AbstractNode {

	/**
	 * Default length options, <b>{@value DEFAULT_LENGTH}</b>.
	 */
	public static final int DEFAULT_LENGTH = 100;
	/**
	 * Default width options, <b>{@value DEFAULT_WIDTH}</b>.
	 */
	public static final int DEFAULT_WIDTH = 50;
	/**
	 * Default indicator width options, <b>{@value DEFAULT_INDICATOR_WIDTH}</b>.
	 */
	public static final int DEFAULT_INDICATOR_WIDTH = 10;
	/**
	 * Default margin options, <b>{@value DEFAULT_MARGIN}</b>.
	 */
	public static final int DEFAULT_MARGIN = 8;

	/**
	 * Name of properties of native object for projection scale.
	 */
	private enum Property implements Key
	{
		POSITION("position"),
		ALIGN("align"),
		LENGTH("length"),
		WIDTH("width"),
		INDICATOR_WIDTH("indicatorWidth"),
		MARGIN("margin");

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
	// callback proxy to invoke the position function
	private final CallbackProxy<ProxyObjectCallback> positionCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the align function
	private final CallbackProxy<ProxyStringCallback> alignCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the length function
	private final CallbackProxy<ProxyIntegerCallback> lengthCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the width function
	private final CallbackProxy<ProxyIntegerCallback> widthCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the indicator width function
	private final CallbackProxy<ProxyIntegerCallback> indicatorWidthCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the margin function
	private final CallbackProxy<ProxyObjectCallback> marginCallbackProxy = JsHelper.get().newCallbackProxy();

	// ---------------------------
	// -- USERS CALLBACKS ---
	// ---------------------------
	// user callback implementation for position
	private static final CallbackPropertyHandler<PositionCallback> POSITION_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.POSITION);
	// user callback implementation for align
	private static final CallbackPropertyHandler<AlignCallback> ALIGN_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.ALIGN);
	// user callback implementation for length
	private static final CallbackPropertyHandler<LengthCallback> LENGTH_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.LENGTH);
	// user callback implementation for width
	private static final CallbackPropertyHandler<WidthCallback<ScaleContext>> WIDTH_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.WIDTH);
	// user callback implementation for indicator width
	private static final CallbackPropertyHandler<WidthCallback<ScaleContext>> INDICATOR_WIDTH_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.INDICATOR_WIDTH);
	// user callback implementation for margin
	private static final CallbackPropertyHandler<MarginCallback> MARGIN_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.MARGIN);

	// instance of mapper
	private final LegendAxisMapper mapper;

	/**
	 * Creates the object with native object instance to be wrapped.
	 * 
	 * @param mapper mapper of legend.
	 * @param nativeObject native object instance to be wrapped.
	 */
	Legend(LegendAxisMapper mapper, NativeObject nativeObject) {
		super(nativeObject);
		// checks and gets the mapper
		this.mapper = Checker.checkAndGetIfValid(mapper, "Mapper instance ");
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// sets function to proxy callback in order to invoke the java interface
		this.positionCallbackProxy.setCallback(this::onPosition);
		// sets function to proxy callback in order to invoke the java interface
		this.alignCallbackProxy.setCallback(this::onAlign);
		// sets function to proxy callback in order to invoke the java interface
		this.lengthCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValueAsNumber(new ScaleContext(mapper.getAxis(), context), getLengthCallback(), DEFAULT_LENGTH, ScriptableIntegerChecker.POSITIVE_OR_DEFAULT).intValue());
		// sets function to proxy callback in order to invoke the java interface
		this.widthCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValueAsNumber(new ScaleContext(mapper.getAxis(), context), getWidthCallback(), DEFAULT_WIDTH, ScriptableIntegerChecker.POSITIVE_OR_DEFAULT).intValue());
		// sets function to proxy callback in order to invoke the java interface
		this.indicatorWidthCallbackProxy
				.setCallback(context -> ScriptableUtil.getOptionValueAsNumber(new ScaleContext(mapper.getAxis(), context), getIndicatorWidthCallback(), DEFAULT_INDICATOR_WIDTH, ScriptableIntegerChecker.POSITIVE_OR_DEFAULT).intValue());
		// sets function to proxy callback in order to invoke the java interface
		this.marginCallbackProxy.setCallback(this::onMargin);
	}

	/**
	 * Sets the location of the legend on the chart area.
	 * 
	 * @param position the location of the legend on the chart area
	 */
	public void setPosition(Position position) {
		// resets callback
		setPosition((PositionCallback) null);
		// stores value
		setValue(Property.POSITION, position);
	}

	/**
	 * Sets the location of the legend on the chart area.
	 * 
	 * @param position the location of the legend on the chart area
	 */
	public void setPosition(PositionPoint position) {
		// resets callback
		setPosition((PositionCallback) null);
		// stores value
		setValue(Property.POSITION, position);
	}

	/**
	 * Returns the location of the legend on the chart area.
	 * 
	 * @return the location of the legend on the chart area
	 */
	public Position getPosition() {
		return getValue(Property.POSITION, Position.values(), Position.BOTTOM_RIGHT);
	}

	/**
	 * Returns the location of the legend on the chart area.
	 * 
	 * @return the location of the legend on the chart area
	 */
	public PositionPoint getPositionAsPoint() {
		// checks if the position is a object and then a position point
		if (isType(Property.POSITION, ObjectType.OBJECT)) {
			return new PositionPoint(getValue(Property.POSITION));
		}
		// if here the position is not an object then
		// returns null
		return null;
	}

	/**
	 * Sets the alignment of the legend on the chart area.
	 * 
	 * @param align the alignment of the legend on the chart area
	 */
	public void setAlign(Align align) {
		// resets callback
		setAlign((AlignCallback) null);
		// stores value
		setValue(Property.ALIGN, align);
	}

	/**
	 * Returns the alignment of the legend on the chart area.
	 * 
	 * @return the alignment of the legend on the chart area
	 */
	public Align getAlign() {
		return getValue(Property.ALIGN, Align.values(), Align.RIGHT);
	}

	/**
	 * Sets the length of the legend.
	 * 
	 * @param length the length of the legend
	 */
	public void setLength(int length) {
		// resets callback
		setLength((LengthCallback) null);
		// stores value
		setValue(Property.LENGTH, length);
	}

	/**
	 * Returns the length of the legend.
	 * 
	 * @return the length of the legend
	 */
	public int getLength() {
		return getValue(Property.LENGTH, DEFAULT_LENGTH);
	}

	/**
	 * Sets how wide the scale is.<br>
	 * For a horizontal scale the height if a value less than 1 is given, is it assume to be a ratio of the corresponding chart area.
	 * 
	 * @param width how wide the scale is
	 */
	public void setWidth(int width) {
		// resets callback
		setWidth((WidthCallback<ScaleContext>) null);
		// stores value
		setValue(Property.WIDTH, width);
	}

	/**
	 * Returns how wide the scale is.<br>
	 * For a horizontal scale the height if a value less than 1 is given, is it assume to be a ratio of the corresponding chart area.
	 * 
	 * @return how wide the scale is
	 */
	public int getWidth() {
		return getValue(Property.WIDTH, DEFAULT_WIDTH);
	}

	/**
	 * Sets how many pixels should be used for the color bar.
	 * 
	 * @param width how many pixels should be used for the color bar
	 */
	public void setIndicatorWidth(int width) {
		// resets callback
		setIndicatorWidth((WidthCallback<ScaleContext>) null);
		// stores value
		setValue(Property.INDICATOR_WIDTH, width);
	}

	/**
	 * Returns how many pixels should be used for the color bar.
	 * 
	 * @return how many pixels should be used for the color bar
	 */
	public int getIndicatorWidth() {
		return getValue(Property.INDICATOR_WIDTH, DEFAULT_INDICATOR_WIDTH);
	}

	/**
	 * Sets the margin pixels such that it doesn't stick to the edge of the chart.
	 * 
	 * @param margin the margin pixels such that it doesn't stick to the edge of the chart
	 */
	public void setMargin(int margin) {
		// resets callback
		setMargin((MarginCallback) null);
		// stores value
		setValue(Property.MARGIN, margin);
	}

	/**
	 * Sets the margin pixels such that it doesn't stick to the edge of the chart.
	 * 
	 * @param margin the margin pixels such that it doesn't stick to the edge of the chart
	 */
	public void setMargin(Margin margin) {
		// resets callback
		setMargin((MarginCallback) null);
		// stores value
		setValue(Property.MARGIN, margin);
	}

	/**
	 * Returns the margin pixels such that it doesn't stick to the edge of the chart.
	 * 
	 * @return the margin pixels such that it doesn't stick to the edge of the chart
	 */
	public int getMargin() {
		return getValue(Property.MARGIN, DEFAULT_MARGIN);
	}

	/**
	 * Returns the margin pixels such that it doesn't stick to the edge of the chart.
	 * 
	 * @return the margin pixels such that it doesn't stick to the edge of the chart
	 */
	public Margin getMarginAsObject() {
		// checks if the margin is a object
		if (isType(Property.MARGIN, ObjectType.OBJECT)) {
			return new Margin(getValue(Property.MARGIN));
		}
		// if here the margin is not an object then
		// returns null
		return null;
	}

	// ---------------------------------
	// CALLBACK
	// ---------------------------------

	/**
	 * Sets the location of the legend on the chart area.
	 * 
	 * @param positionCallback the callback to set the location of the legend on the chart area
	 */
	public void setPosition(PositionCallback positionCallback) {
		POSITION_PROPERTY_HANDLER.setCallback(this, mapper.getAxis().getChart().getId(), positionCallback, positionCallbackProxy.getProxy());
	}

	/**
	 * Returns the callback to set the location of the legend on the chart area.
	 * 
	 * @return the callback to set the location of the legend on the chart area
	 */
	public PositionCallback getPositionCallback() {
		return POSITION_PROPERTY_HANDLER.getCallback(this, null);
	}

	/**
	 * Sets the alignment of the legend on the chart area.
	 * 
	 * @param alignCallback the alignment of the legend on the chart area.
	 */
	public void setAlign(AlignCallback alignCallback) {
		ALIGN_PROPERTY_HANDLER.setCallback(this, mapper.getAxis().getChart().getId(), alignCallback, alignCallbackProxy.getProxy());
	}

	/**
	 * Returns the callback to set the alignment of the legend on the chart area.
	 * 
	 * @return the callback to set the alignment of the legend on the chart area
	 */
	public AlignCallback getAlignCallback() {
		return ALIGN_PROPERTY_HANDLER.getCallback(this, null);
	}

	/**
	 * Sets the length of the legend.
	 * 
	 * @param lengthCallback the callback to set length of the legend.
	 */
	public void setLength(LengthCallback lengthCallback) {
		LENGTH_PROPERTY_HANDLER.setCallback(this, mapper.getAxis().getChart().getId(), lengthCallback, lengthCallbackProxy.getProxy());
	}

	/**
	 * Returns the callback to set length of the legend.
	 * 
	 * @return the callback to set length of the legend
	 */
	public LengthCallback getLengthCallback() {
		return LENGTH_PROPERTY_HANDLER.getCallback(this, null);
	}

	/**
	 * Sets how wide the scale is.
	 * 
	 * @param widthCallback the callback to set how wide the scale is
	 */
	public void setWidth(WidthCallback<ScaleContext> widthCallback) {
		WIDTH_PROPERTY_HANDLER.setCallback(this, mapper.getAxis().getChart().getId(), widthCallback, widthCallbackProxy.getProxy());
	}

	/**
	 * Returns the callback to set how wide the scale is.
	 * 
	 * @return the callback to set how wide the scale is
	 */
	public WidthCallback<ScaleContext> getWidthCallback() {
		return WIDTH_PROPERTY_HANDLER.getCallback(this, null);
	}

	/**
	 * Sets how many pixels should be used for the color bar.
	 * 
	 * @param indicatorWidthCallback the callback to set how many pixels should be used for the color bar
	 */
	public void setIndicatorWidth(WidthCallback<ScaleContext> indicatorWidthCallback) {
		INDICATOR_WIDTH_PROPERTY_HANDLER.setCallback(this, mapper.getAxis().getChart().getId(), indicatorWidthCallback, indicatorWidthCallbackProxy.getProxy());
	}

	/**
	 * Returns the callback to set how many pixels should be used for the color bar.
	 * 
	 * @return the callback to set how many pixels should be used for the color bar
	 */
	public WidthCallback<ScaleContext> getIndicatorWidthCallback() {
		return INDICATOR_WIDTH_PROPERTY_HANDLER.getCallback(this, null);
	}

	/**
	 * Sets the margin pixels such that it doesn't stick to the edge of the chart.
	 * 
	 * @param marginCallback the callback to set the margin pixels such that it doesn't stick to the edge of the chart
	 */
	public void setMargin(MarginCallback marginCallback) {
		MARGIN_PROPERTY_HANDLER.setCallback(this, mapper.getAxis().getChart().getId(), marginCallback, marginCallbackProxy.getProxy());
	}

	/**
	 * Returns the callback to set the margin pixels such that it doesn't stick to the edge of the chart.
	 * 
	 * @return the callback to set the margin pixels such that it doesn't stick to the edge of the chart
	 */
	public MarginCallback getMarginCallback() {
		return MARGIN_PROPERTY_HANDLER.getCallback(this, null);
	}

	// ---------------------
	// INTERNAL METHODS
	// ---------------------

	/**
	 * Returns a string or a {@link NativeObject} as position when the callback has been activated.
	 * 
	 * @param context scriptable context
	 * @return a string or a {@link NativeObject} as position
	 */
	private Object onPosition(NativeObject context) {
		// checks if callback is consistent
		if (getPositionCallback() != null) {
			// invokes callback
			Object result = getPositionCallback().invoke(new ScaleContext(mapper.getAxis(), context));
			// checks if result is consistent
			if (result instanceof Position) {
				// casts value
				Position item = (Position) result;
				// returns string
				return item.value();
			} else if (result instanceof PositionPoint) {
				// casts value
				PositionPoint point = (PositionPoint) result;
				// returns point
				return point.nativeObject();
			}
		}
		// default result
		return Position.BOTTOM_RIGHT.value();
	}

	/**
	 * Returns a string as align when the callback has been activated.
	 * 
	 * @param context scriptable context
	 * @return a string as align
	 */
	private String onAlign(NativeObject context) {
		// checks if callback is consistent
		if (getAlignCallback() != null) {
			// invokes callback
			Object result = getAlignCallback().invoke(new ScaleContext(mapper.getAxis(), context));
			// checks if result is consistent
			if (result instanceof Align) {
				// casts value
				Align item = (Align) result;
				// returns string
				return item.value();
			}
		}
		// default result
		return Align.RIGHT.value();
	}

	/**
	 * Returns a integer or a {@link NativeObject} as margin when the callback has been activated.
	 * 
	 * @param context scriptable context
	 * @return a integer or a {@link NativeObject} as margin
	 */
	private Object onMargin(NativeObject context) {
		// checks if callback is consistent
		if (getMarginCallback() != null) {
			// invokes callback
			Object result = getMarginCallback().invoke(new ScaleContext(mapper.getAxis(), context));
			// checks if result is consistent
			if (result instanceof Number) {
				// casts value
				Number number = (Number) result;
				// returns integer
				return number.intValue();
			} else if (result instanceof Margin) {
				// casts value
				Margin margin = (Margin) result;
				// returns margin
				return margin.nativeObject();
			}
		}
		// default result
		return DEFAULT_MARGIN;
	}
}