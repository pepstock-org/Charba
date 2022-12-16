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

import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.callbacks.ScriptableDoubleChecker;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyDoubleCallback;
import org.pepstock.charba.client.callbacks.ScriptableUtil;
import org.pepstock.charba.client.callbacks.StepSizeCallback;
import org.pepstock.charba.client.callbacks.TimeTickCallback;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.TickSource;

/**
 * The time scale is use to chart time data.<br>
 * It can be placed on either the x or y axis.<br>
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class CartesianTimeTick extends CartesianTick {

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the step size function
	private final CallbackProxy<ProxyDoubleCallback> stepSizeCallbackProxy = JsHelper.get().newCallbackProxy();

	// step size callback instance
	private StepSizeCallback stepSizeCallback = null;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
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

	// handler for callback for category axis
	private final TimeTickHandler tickHandler;

	/**
	 * Builds the object storing the axis instance.
	 * 
	 * @param axis axis instance
	 */
	CartesianTimeTick(Axis axis) {
		super(axis);
		// creates handler
		this.tickHandler = new TimeTickHandler(axis, this);
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		this.stepSizeCallbackProxy
				.setCallback(context -> ScriptableUtil.getOptionValueAsNumber(getAxis().createContext(context), getStepSizeCallback(), getAxis().getDefaultValues().getTicks().getStepSize(), ScriptableDoubleChecker.POSITIVE_OR_DEFAULT).doubleValue());

	}

	/**
	 * Sets the property controls the ticks generation.
	 * 
	 * @param source property controls the ticks generation.
	 */
	public void setSource(TickSource source) {
		getConfiguration().setSource(source);
	}

	/**
	 * Returns the property controls the ticks generation.
	 * 
	 * @return property controls the ticks generation.
	 */
	public TickSource getSource() {
		return getConfiguration().getSource();
	}

	/**
	 * Sets the user defined fixed step size for the scale.
	 * 
	 * @param stepSize user defined fixed step size for the scale.
	 */
	public void setStepSize(double stepSize) {
		// resets callback
		setStepSize((StepSizeCallback) null);
		// stores value
		getConfiguration().setStepSize(stepSize);
	}

	/**
	 * Returns the user defined fixed step size for the scale.
	 * 
	 * @return user defined fixed step size for the scale.
	 */
	public double getStepSize() {
		return getConfiguration().getStepSize();
	}

	/**
	 * Sets the stepSize callback instance.
	 * 
	 * @param stepSizeCallback the stepSize callback instance
	 */
	public void setStepSize(StepSizeCallback stepSizeCallback) {
		// stores callback
		this.stepSizeCallback = stepSizeCallback;
		// stores and manages callback
		getAxis().setCallback(getAxis().getConfiguration().getTicks(), Property.STEP_SIZE, stepSizeCallback, stepSizeCallbackProxy);
	}

	/**
	 * Returns the stepSize callback instance.
	 * 
	 * @return the stepSize callback instance
	 */
	public StepSizeCallback getStepSizeCallback() {
		return stepSizeCallback;
	}

	/**
	 * Sets the stepSize callback instance.
	 * 
	 * @param stepSizeCallback the stepSize callback instance
	 */
	public void setStepSize(NativeCallback stepSizeCallback) {
		// resets callback
		setStepSize((StepSizeCallback) null);
		// stores and manages callback
		getAxis().setCallback(getAxis().getConfiguration().getTicks(), Property.STEP_SIZE, stepSizeCallback);
	}

	/**
	 * Returns the user callback instance.
	 * 
	 * @return the callback
	 */
	public TimeTickCallback getCallback() {
		return tickHandler.getCallback();
	}

	/**
	 * Sets the user callback instance.
	 * 
	 * @param callback the callback to set
	 */
	public void setCallback(TimeTickCallback callback) {
		tickHandler.setCallback(callback);
	}

	/**
	 * Returns the tick handler.
	 * 
	 * @return the tick handler
	 */
	final TimeTickHandler getTickHandler() {
		return tickHandler;
	}

}