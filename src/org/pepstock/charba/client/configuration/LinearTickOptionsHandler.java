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

import org.pepstock.charba.client.callbacks.CountCallback;
import org.pepstock.charba.client.callbacks.MaxTicksLimitCallback;
import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.callbacks.PrecisionCallback;
import org.pepstock.charba.client.callbacks.ScriptableDoubleChecker;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyDoubleCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyIntegerCallback;
import org.pepstock.charba.client.callbacks.ScriptableIntegerChecker;
import org.pepstock.charba.client.callbacks.ScriptableUtil;
import org.pepstock.charba.client.callbacks.StepSizeCallback;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;

/**
 * This object is used to provide the scriptable options of {@link IsLinearTick} ticks.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class LinearTickOptionsHandler extends NumericTickOptionsHandler {

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the count function
	private final CallbackProxy<ProxyIntegerCallback> countCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the max ticks limit function
	private final CallbackProxy<ProxyIntegerCallback> maxTicksLimitCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the precision function
	private final CallbackProxy<ProxyIntegerCallback> precisionCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the step size function
	private final CallbackProxy<ProxyDoubleCallback> stepSizeCallbackProxy = JsHelper.get().newCallbackProxy();

	// count callback instance
	private CountCallback countCallback = null;
	// max ticks limit callback instance
	private MaxTicksLimitCallback maxTicksLimitCallback = null;
	// precision callback instance
	private PrecisionCallback precisionCallback = null;
	// step size callback instance
	private StepSizeCallback stepSizeCallback = null;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		// common linear
		COUNT("count"),
		// linear cartesian
		MAX_TICKS_LIMIT("maxTicksLimit"),
		PRECISION("precision"),
		STEP_SIZE("stepSize");

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
	 * Builds the object storing the axis which this tick belongs to.
	 * 
	 * @param axis axis which this tick belongs to.
	 */
	LinearTickOptionsHandler(Axis axis) {
		super(axis);
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// sets function to proxy callback in order to invoke the java interface
		this.countCallbackProxy
				.setCallback(context -> ScriptableUtil.getOptionValueAsNumber(getAxis().createContext(context), getCountCallback(), getAxis().getDefaultValues().getTicks().getCount(), ScriptableIntegerChecker.POSITIVE_OR_DEFAULT).intValue());
		this.maxTicksLimitCallbackProxy.setCallback(
				context -> ScriptableUtil.getOptionValueAsNumber(getAxis().createContext(context), getMaxTicksLimitCallback(), getAxis().getDefaultValues().getTicks().getMaxTicksLimit(), ScriptableIntegerChecker.POSITIVE_OR_DEFAULT).intValue());
		this.precisionCallbackProxy
				.setCallback(context -> ScriptableUtil.getOptionValueAsNumber(getAxis().createContext(context), getPrecisionCallback(), getAxis().getDefaultValues().getTicks().getPrecision(), ScriptableIntegerChecker.POSITIVE_OR_DEFAULT).intValue());
		this.stepSizeCallbackProxy
				.setCallback(context -> ScriptableUtil.getOptionValueAsNumber(getAxis().createContext(context), getStepSizeCallback(), getAxis().getDefaultValues().getTicks().getStepSize(), ScriptableDoubleChecker.POSITIVE_OR_DEFAULT).doubleValue());
	}

	/**
	 * Returns the count callback instance.
	 * 
	 * @return the count callback instance
	 */
	CountCallback getCountCallback() {
		return countCallback;
	}

	/**
	 * Sets the count callback instance.
	 * 
	 * @param countCallback the count callback instance
	 */
	void setCount(CountCallback countCallback) {
		// stores callback
		this.countCallback = countCallback;
		// stores and manages callback
		getAxis().setCallback(getAxis().getConfiguration().getTicks(), Property.COUNT, countCallback, countCallbackProxy);
	}

	/**
	 * Sets the count callback instance.
	 * 
	 * @param countCallback the count callback instance
	 */
	void setCount(NativeCallback countCallback) {
		// resets callback
		setCount((CountCallback) null);
		// stores and manages callback
		getAxis().setCallback(getAxis().getConfiguration().getTicks(), Property.COUNT, countCallback);
	}

	/**
	 * Returns the maxTicksLimit callback instance.
	 * 
	 * @return the maxTicksLimit callback instance
	 */
	MaxTicksLimitCallback getMaxTicksLimitCallback() {
		return maxTicksLimitCallback;
	}

	/**
	 * Sets the maxTicksLimit callback instance.
	 * 
	 * @param maxTicksLimitCallback the maxTicksLimit callback instance
	 */
	void setMaxTicksLimit(MaxTicksLimitCallback maxTicksLimitCallback) {
		// stores callback
		this.maxTicksLimitCallback = maxTicksLimitCallback;
		// stores and manages callback
		getAxis().setCallback(getAxis().getConfiguration().getTicks(), Property.MAX_TICKS_LIMIT, maxTicksLimitCallback, maxTicksLimitCallbackProxy);
	}

	/**
	 * Sets the maxTicksLimit callback instance.
	 * 
	 * @param maxTicksLimitCallback the maxTicksLimit callback instance
	 */
	void setMaxTicksLimit(NativeCallback maxTicksLimitCallback) {
		// resets callback
		setMaxTicksLimit((MaxTicksLimitCallback) null);
		// stores and manages callback
		getAxis().setCallback(getAxis().getConfiguration().getTicks(), Property.MAX_TICKS_LIMIT, maxTicksLimitCallback);
	}

	/**
	 * Returns the precision callback instance.
	 * 
	 * @return the precision callback instance
	 */
	PrecisionCallback getPrecisionCallback() {
		return precisionCallback;
	}

	/**
	 * Sets the precision callback instance.
	 * 
	 * @param precisionCallback the precision callback instance
	 */
	void setPrecision(PrecisionCallback precisionCallback) {
		// stores callback
		this.precisionCallback = precisionCallback;
		// stores and manages callback
		getAxis().setCallback(getAxis().getConfiguration().getTicks(), Property.PRECISION, precisionCallback, precisionCallbackProxy);
	}

	/**
	 * Sets the precision callback instance.
	 * 
	 * @param precisionCallback the precision callback instance
	 */
	void setPrecision(NativeCallback precisionCallback) {
		// resets callback
		setPrecision((PrecisionCallback) null);
		// stores and manages callback
		getAxis().setCallback(getAxis().getConfiguration().getTicks(), Property.PRECISION, precisionCallback);
	}

	/**
	 * Returns the stepSize callback instance.
	 * 
	 * @return the stepSize callback instance
	 */
	StepSizeCallback getStepSizeCallback() {
		return stepSizeCallback;
	}

	/**
	 * Sets the stepSize callback instance.
	 * 
	 * @param stepSizeCallback the stepSize callback instance
	 */
	void setStepSize(StepSizeCallback stepSizeCallback) {
		// stores callback
		this.stepSizeCallback = stepSizeCallback;
		// stores and manages callback
		getAxis().setCallback(getAxis().getConfiguration().getTicks(), Property.STEP_SIZE, stepSizeCallback, stepSizeCallbackProxy);
	}

	/**
	 * Sets the stepSize callback instance.
	 * 
	 * @param stepSizeCallback the stepSize callback instance
	 */
	void setStepSize(NativeCallback stepSizeCallback) {
		// resets callback
		setStepSize((StepSizeCallback) null);
		// stores and manages callback
		getAxis().setCallback(getAxis().getConfiguration().getTicks(), Property.STEP_SIZE, stepSizeCallback);
	}

}