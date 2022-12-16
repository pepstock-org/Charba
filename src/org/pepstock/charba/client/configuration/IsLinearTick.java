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

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.callbacks.CountCallback;
import org.pepstock.charba.client.callbacks.MaxTicksLimitCallback;
import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.callbacks.PrecisionCallback;
import org.pepstock.charba.client.callbacks.StepSizeCallback;

/**
 * Common methods for linear scale tick is use to chart numerical data.<br>
 * Can be used for linear cartesian and radial ticks.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface IsLinearTick extends IsNumericTick {

	/**
	 * Returns the axis instance.
	 * 
	 * @return the axis instance
	 */
	Axis getAxis();

	/**
	 * Returns the linear tick options handler instance, for callbacks.
	 * 
	 * @return the linear tick options handler instance, for callbacks
	 */
	@Override
	LinearTickOptionsHandler getTickOptionsHandler();

	/**
	 * Sets the number of ticks to generate.<br>
	 * If specified, this overrides the automatic generation.
	 * 
	 * @param count the number of ticks to generate.<br>
	 *            If specified, this overrides the automatic generation
	 */
	default void setCount(int count) {
		// checks if axis is consistent
		if (getAxis() != null) {
			// resets callback
			setCount((CountCallback) null);
			// stores value
			getAxis().getScale().getTicks().setCount(count);
		}
	}

	/**
	 * Returns the number of ticks to generate.<br>
	 * If specified, this overrides the automatic generation.
	 * 
	 * @return the number of ticks to generate.<br>
	 *         If specified, this overrides the automatic generation
	 */
	default int getCount() {
		// checks if axis is consistent
		if (getAxis() != null) {
			return getAxis().getScale().getTicks().getCount();
		}
		// if here, axis is not consistent
		return Defaults.get().getScale().getTicks().getCount();
	}

	/**
	 * Sets the maximum number of ticks and grid to show.
	 * 
	 * @param maxTicksLimit maximum number of ticks and grid to show.
	 */
	default void setMaxTicksLimit(int maxTicksLimit) {
		// checks if axis is consistent
		if (getAxis() != null) {
			// resets callback
			setMaxTicksLimit((MaxTicksLimitCallback) null);
			// stores value
			getAxis().getScale().getTicks().setMaxTicksLimit(maxTicksLimit);
		}
	}

	/**
	 * Returns the maximum number of ticks and grid to show.
	 * 
	 * @return maximum number of ticks and grid to show.
	 */
	default int getMaxTicksLimit() {
		// checks if axis is consistent
		if (getAxis() != null) {
			return getAxis().getScale().getTicks().getMaxTicksLimit();
		}
		// if here, axis is not consistent
		return Defaults.get().getScale().getTicks().getMaxTicksLimit();
	}

	/**
	 * Sets the user defined fixed step size for the scale.
	 * 
	 * @param stepSize user defined fixed step size for the scale.
	 */
	default void setStepSize(double stepSize) {
		// checks if axis is consistent
		if (getAxis() != null) {
			// resets callback
			setStepSize((StepSizeCallback) null);
			// stores value
			getAxis().getScale().getTicks().setStepSize(stepSize);
		}
	}

	/**
	 * Returns the user defined fixed step size for the scale.
	 * 
	 * @return user defined fixed step size for the scale.
	 */
	default double getStepSize() {
		// checks if axis is consistent
		if (getAxis() != null) {
			return getAxis().getScale().getTicks().getStepSize();
		}
		// if here, axis is not consistent
		return Defaults.get().getScale().getTicks().getStepSize();
	}

	/**
	 * If defined and stepSize is not specified, the step size will be rounded to this many decimal places.
	 * 
	 * @param precision if defined and stepSize is not specified, the step size will be rounded to this many decimal places.
	 */
	default void setPrecision(int precision) {
		// checks if axis is consistent
		if (getAxis() != null) {
			// resets callback
			setPrecision((PrecisionCallback) null);
			// stores value
			getAxis().getScale().getTicks().setPrecision(precision);
		}
	}

	/**
	 * If defined and stepSize is not specified, the step size will be rounded to this many decimal places.
	 * 
	 * @return if defined and stepSize is not specified, the step size will be rounded to this many decimal places.
	 */
	default int getPrecision() {
		// checks if axis is consistent
		if (getAxis() != null) {
			return getAxis().getScale().getTicks().getPrecision();
		}
		// if here, axis is not consistent
		return Defaults.get().getScale().getTicks().getPrecision();
	}

	/**
	 * Returns the count callback instance.
	 * 
	 * @return the count callback instance
	 */
	default CountCallback getCountCallback() {
		// checks if options handler is consistent
		if (getTickOptionsHandler() != null) {
			return getTickOptionsHandler().getCountCallback();
		}
		// if here, handler is not consistent
		// then returns null
		return null;
	}

	/**
	 * Sets the count callback instance.
	 * 
	 * @param countCallback the count callback instance
	 */
	default void setCount(CountCallback countCallback) {
		// checks if options handler is consistent
		if (getTickOptionsHandler() != null) {
			getTickOptionsHandler().setCount(countCallback);
		}
	}

	/**
	 * Sets the count callback instance.
	 * 
	 * @param countCallback the count callback instance
	 */
	default void setCount(NativeCallback countCallback) {
		// checks if options handler is consistent
		if (getTickOptionsHandler() != null) {
			getTickOptionsHandler().setCount(countCallback);
		}
	}

	/**
	 * Returns the maxTicksLimit callback instance.
	 * 
	 * @return the maxTicksLimit callback instance
	 */
	default MaxTicksLimitCallback getMaxTicksLimitCallback() {
		// checks if options handler is consistent
		if (getTickOptionsHandler() != null) {
			return getTickOptionsHandler().getMaxTicksLimitCallback();
		}
		// if here, handler is not consistent
		// then returns null
		return null;
	}

	/**
	 * Sets the maxTicksLimit callback instance.
	 * 
	 * @param maxTicksLimitCallback the maxTicksLimit callback instance
	 */
	default void setMaxTicksLimit(MaxTicksLimitCallback maxTicksLimitCallback) {
		// checks if options handler is consistent
		if (getTickOptionsHandler() != null) {
			getTickOptionsHandler().setMaxTicksLimit(maxTicksLimitCallback);
		}
	}

	/**
	 * Sets the maxTicksLimit callback instance.
	 * 
	 * @param maxTicksLimitCallback the maxTicksLimit callback instance
	 */
	default void setMaxTicksLimit(NativeCallback maxTicksLimitCallback) {
		// checks if options handler is consistent
		if (getTickOptionsHandler() != null) {
			getTickOptionsHandler().setMaxTicksLimit(maxTicksLimitCallback);
		}
	}

	/**
	 * Returns the precision callback instance.
	 * 
	 * @return the precision callback instance
	 */
	default PrecisionCallback getPrecisionCallback() {
		// checks if options handler is consistent
		if (getTickOptionsHandler() != null) {
			return getTickOptionsHandler().getPrecisionCallback();
		}
		// if here, handler is not consistent
		// then returns null
		return null;
	}

	/**
	 * Sets the precision callback instance.
	 * 
	 * @param precisionCallback the precision callback instance
	 */
	default void setPrecision(PrecisionCallback precisionCallback) {
		// checks if options handler is consistent
		if (getTickOptionsHandler() != null) {
			getTickOptionsHandler().setPrecision(precisionCallback);
		}
	}

	/**
	 * Sets the precision callback instance.
	 * 
	 * @param precisionCallback the precision callback instance
	 */
	default void setPrecision(NativeCallback precisionCallback) {
		// checks if options handler is consistent
		if (getTickOptionsHandler() != null) {
			getTickOptionsHandler().setPrecision(precisionCallback);
		}
	}

	/**
	 * Returns the stepSize callback instance.
	 * 
	 * @return the stepSize callback instance
	 */
	default StepSizeCallback getStepSizeCallback() {
		// checks if options handler is consistent
		if (getTickOptionsHandler() != null) {
			return getTickOptionsHandler().getStepSizeCallback();
		}
		// if here, handler is not consistent
		// then returns null
		return null;
	}

	/**
	 * Sets the stepSize callback instance.
	 * 
	 * @param stepSizeCallback the stepSize callback instance
	 */
	default void setStepSize(StepSizeCallback stepSizeCallback) {
		// checks if options handler is consistent
		if (getTickOptionsHandler() != null) {
			getTickOptionsHandler().setStepSize(stepSizeCallback);
		}
	}

	/**
	 * Sets the stepSize callback instance.
	 * 
	 * @param stepSizeCallback the stepSize callback instance
	 */
	default void setStepSize(NativeCallback stepSizeCallback) {
		// checks if options handler is consistent
		if (getTickOptionsHandler() != null) {
			getTickOptionsHandler().setStepSize(stepSizeCallback);
		}
	}

}