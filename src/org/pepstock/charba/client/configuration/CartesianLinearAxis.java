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
import org.pepstock.charba.client.callbacks.GraceCallback;
import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.callbacks.ScaleContext;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyObjectCallback;
import org.pepstock.charba.client.callbacks.ScriptableUtils;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.AxisKind;
import org.pepstock.charba.client.enums.ChartAxisType;
import org.pepstock.charba.client.enums.DefaultScaleId;
import org.pepstock.charba.client.options.ScaleId;
import org.pepstock.charba.client.utils.Utilities;

/**
 * This object is used to map defined axis as linear.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class CartesianLinearAxis extends CartesianAxis<CartesianLinearTick> implements IsLinearAxis {

	// --------------------------------------------
	// -- CALLBACKS PROXIES FOR AXIS PROPERTIES ---
	// --------------------------------------------
	// callback proxy to invoke the position function
	private final CallbackProxy<ProxyObjectCallback> graceCallbackProxy = JsHelper.get().newCallbackProxy();

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		GRACE("grace");

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
	private GraceCallback graceCallback = null;

	private final CartesianLinearTick ticks;
	// min max handler
	private final MinMaxCallbacksHandler<Double> minMaxHandler;
	// begin at zero handler
	private final BegiAtZeroCallbackHandler beginAtZeroHandler;

	/**
	 * Builds the object storing the chart instance. Axis type is Y by default.
	 * 
	 * @param chart chart instance
	 */
	public CartesianLinearAxis(IsChart chart) {
		// uses Y as axis id
		this(chart, ChartAxisType.LINEAR.getDefaultScaleId());
	}

	/**
	 * Builds the object storing the chart instance. Axis type is Y by default.
	 * 
	 * @param chart chart instance
	 * @param id axis id
	 */
	public CartesianLinearAxis(IsChart chart, String id) {
		this(chart, ScaleId.create(id));
	}

	/**
	 * Builds the object storing the chart instance. Axis type is Y by default.
	 * 
	 * @param chart chart instance
	 * @param id axis id
	 */
	public CartesianLinearAxis(IsChart chart, ScaleId id) {
		this(chart, id, null);
	}

	/**
	 * Builds the object storing the chart instance and axis type.
	 * 
	 * @param chart chart instance
	 * @param kind axis kind.
	 */
	public CartesianLinearAxis(IsChart chart, AxisKind kind) {
		this(chart, DefaultScaleId.getByAxisKind(kind, ChartAxisType.LINEAR.getDefaultScaleId()), kind);
	}

	/**
	 * Builds the object storing the chart instance and axis type.
	 * 
	 * @param chart chart instance
	 * @param id axis id
	 * @param kind axis kind.
	 */
	public CartesianLinearAxis(IsChart chart, String id, AxisKind kind) {
		this(chart, ScaleId.create(id), kind);
	}

	/**
	 * Builds the object storing the chart instance and axis type.
	 * 
	 * @param chart chart instance
	 * @param id axis id
	 * @param kind axis kind.
	 */
	public CartesianLinearAxis(IsChart chart, ScaleId id, AxisKind kind) {
		this(chart, id, ChartAxisType.LINEAR, Key.isValid(kind) ? kind : DefaultScaleId.getAxisKindByScaleId(id, AxisKind.Y));
	}

	/**
	 * Builds the object storing the chart instance and cartesian axis type, to use to extend the axis.
	 * 
	 * @param chart chart instance
	 * @param id axis id
	 * @param type axis type
	 * @param kind axis kind
	 */
	protected CartesianLinearAxis(IsChart chart, ScaleId id, AxisType type, AxisKind kind) {
		super(chart, id, type, kind);
		// creates the ticks instance
		this.ticks = new CartesianLinearTick(this);
		// creates internal handlers
		this.minMaxHandler = new MinMaxCallbacksHandler<>(this);
		this.beginAtZeroHandler = new BegiAtZeroCallbackHandler(this);
		// -------------------------------------------------
		// -- SET CALLBACKS to PROXIES x AXIS PROPERTIES ---
		// -------------------------------------------------
		// sets function to proxy callback in order to invoke the java interface
		this.graceCallbackProxy.setCallback(context -> onGrace(createContext(context), getDefaultValues().getGrace()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.IsLinearAxis#getAxisElement()
	 */
	@Override
	public final Axis getAxisElement() {
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.HasMinMaxHandler#getMinMaxHandler()
	 */
	@Override
	public MinMaxCallbacksHandler<Double> getMinMaxCallbacksHandler() {
		return minMaxHandler;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.HasBeginAtZeroCallbackHandler#getBegiAtZeroCallbackHandler()
	 */
	@Override
	public BegiAtZeroCallbackHandler getBegiAtZeroCallbackHandler() {
		return beginAtZeroHandler;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.scales.CartesianAxis#getTicks()
	 */
	@Override
	public CartesianLinearTick getTicks() {
		return ticks;
	}

	/**
	 * Sets the value in pixels is added to the maximum data value and subtracted from the minimum data.<br>
	 * This extends the scale range as if the data values were that much greater.
	 * 
	 * @param grace the value in pixels is added to the maximum data value and subtracted from the minimum data
	 */
	public void setGrace(int grace) {
		getScale().setGrace(grace);
	}

	/**
	 * Returns the value in pixels is added to the maximum data value and subtracted from the minimum data.<br>
	 * This extends the scale range as if the data values were that much greater.
	 * 
	 * @return the value in pixels is added to the maximum data value and subtracted from the minimum data
	 */
	public int getGrace() {
		return getScale().getGrace();
	}

	/**
	 * Sets the value in percentage, value between 0 and 1, is added to the maximum data value and subtracted from the minimum data.<br>
	 * This extends the scale range as if the data values were that much greater.
	 * 
	 * @param grace the value in percentage is added to the maximum data value and subtracted from the minimum data
	 */
	public void setGraceAsPercentage(double grace) {
		getScale().setGraceAsPercentage(grace);
	}

	/**
	 * Returns the value in percentage, value between 0 and 1, is added to the maximum data value and subtracted from the minimum data.<br>
	 * This extends the scale range as if the data values were that much greater.
	 * 
	 * @return the value in percentage is added to the maximum data value and subtracted from the minimum data
	 */
	public double getGraceAsPercentage() {
		return getScale().getGraceAsPercentage();
	}

	// -----------------------------
	// AXIS PROPERTIES CALLBACKS
	// -----------------------------

	/**
	 * Returns the user callback that sets the value in pixels is added to the maximum data value and subtracted from the minimum data.
	 * 
	 * @return the user callback that sets the value in pixels is added to the maximum data value and subtracted from the minimum data.
	 */
	public GraceCallback getGraceCallback() {
		return graceCallback;
	}

	/**
	 * Sets the user callback that sets the value in pixels is added to the maximum data value and subtracted from the minimum data.
	 * 
	 * @param graceCallback the user callback that sets the value in pixels is added to the maximum data value and subtracted from the minimum data.
	 */
	public void setGrace(GraceCallback graceCallback) {
		// sets the callback
		this.graceCallback = graceCallback;
		// stores and manages callback
		setCallback(getConfiguration(), Property.GRACE, graceCallback, graceCallbackProxy);
	}

	/**
	 * Sets the user callback that sets the value in pixels is added to the maximum data value and subtracted from the minimum data.
	 * 
	 * @param graceCallback that sets the value in pixels is added to the maximum data value and subtracted from the minimum data.
	 */
	public void setGrace(NativeCallback graceCallback) {
		// resets callback
		setGrace((GraceCallback) null);
		// stores and manages callback
		setCallback(getConfiguration(), Property.GRACE, graceCallback);
	}

	// -----------------------------
	// INTERNALS
	// -----------------------------

	/**
	 * Returns an object as string when the callback has been activated.
	 * 
	 * @param context scale context instance.
	 * @param defaultValue default value to apply if callback returns an inconsistent value
	 * @return an object as string
	 */
	private Object onGrace(ScaleContext context, int defaultValue) {
		// gets value
		Number result = ScriptableUtils.getOptionValue(context, getGraceCallback(), defaultValue);
		// checks if consistent
		if (result instanceof Integer) {
			// returns as integer
			return result.intValue();
		} else if (result instanceof Double) {
			// cats to double
			double value = result.doubleValue();
			// to be a percentage, must be between 0 and 1
			if (Checker.isBetween(value, 0, 1)) {
				// is a percentage
				// returns the double value
				return Utilities.getAsPercentage(result.doubleValue(), 0);
			}
			// if here, returns the returned value
			return value;
		}
		// if here the result is null
		// then returns the default
		return defaultValue;
	}
}