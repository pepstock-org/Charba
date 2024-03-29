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

import org.pepstock.charba.client.callbacks.MinMaxCallback;
import org.pepstock.charba.client.callbacks.NativeCallback;

/**
 * Interface which is implemented for the axes class to manage min, max, suggestedMin and suggestedMax callbacks.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
interface HasMinMaxCallbacksHandler<T> {

	/**
	 * Returns a {@link MinMaxCallbacksHandler} which is managing all min and max callbacks options.
	 * 
	 * @return a {@link MinMaxCallbacksHandler} which is managing all min and max callbacks options
	 */
	MinMaxCallbacksHandler<T> getMinMaxCallbacksHandler();

	/**
	 * Returns the callback to set the user defined minimum number for the scale, overrides minimum value from data.
	 * 
	 * @return the callback to set the user defined minimum number for the scale, overrides minimum value from data.
	 */
	default MinMaxCallback<T> getMinCallback() {
		// checks if min/max handler is consistent
		if (getMinMaxCallbacksHandler() != null) {
			// returns callback
			return getMinMaxCallbacksHandler().getMinCallback();
		}
		// if here, min/max handler is not consistent
		return null;
	}

	/**
	 * Sets the callback to set the user defined minimum number for the scale, overrides minimum value from data.
	 * 
	 * @param minCallback the callback to set the user defined minimum number for the scale, overrides minimum value from data.
	 */
	default void setMin(MinMaxCallback<T> minCallback) {
		// checks if min/max handler is consistent
		if (getMinMaxCallbacksHandler() != null) {
			// returns callback
			getMinMaxCallbacksHandler().setMin(minCallback);
		}
	}

	/**
	 * Sets the callback to set the user defined minimum number for the scale, overrides minimum value from data.
	 * 
	 * @param minCallback the callback to set the user defined minimum number for the scale, overrides minimum value from data.
	 */
	default void setMin(NativeCallback minCallback) {
		// checks if min/max handler is consistent
		if (getMinMaxCallbacksHandler() != null) {
			// returns callback
			getMinMaxCallbacksHandler().setMin(minCallback);
		}
	}

	/**
	 * Sets the callback to set the user defined maximum number for the scale, overrides maximum value from data.
	 *
	 * @param maxCallback the callback to set the user defined maximum number for the scale, overrides maximum value from data.
	 */
	default void setMax(MinMaxCallback<T> maxCallback) {
		// checks if min/max handler is consistent
		if (getMinMaxCallbacksHandler() != null) {
			// returns callback
			getMinMaxCallbacksHandler().setMax(maxCallback);
		}
	}

	/**
	 * Sets the callback to set the user defined maximum number for the scale, overrides maximum value from data.
	 *
	 * @param maxCallback the callback to set the user defined maximum number for the scale, overrides maximum value from data.
	 */
	default void setMax(NativeCallback maxCallback) {
		// checks if min/max handler is consistent
		if (getMinMaxCallbacksHandler() != null) {
			// returns callback
			getMinMaxCallbacksHandler().setMax(maxCallback);
		}
	}

	/**
	 * Returns the callback to set the user defined maximum number for the scale, overrides maximum value from data.
	 *
	 * @return the callback to set the user defined maximum number for the scale, overrides maximum value from data.
	 */
	default MinMaxCallback<T> getMaxCallback() {
		// checks if min/max handler is consistent
		if (getMinMaxCallbacksHandler() != null) {
			// returns callback
			return getMinMaxCallbacksHandler().getMaxCallback();
		}
		// if here, min/max handler is not consistent
		return null;
	}

	/**
	 * Returns the callback to set the adjustment used when calculating the minimum data value.
	 * 
	 * @return the callback to set the adjustment used when calculating the minimum data value.
	 */
	default MinMaxCallback<T> getSuggestedMinCallback() {
		// checks if min/max handler is consistent
		if (getMinMaxCallbacksHandler() != null) {
			// returns callback
			return getMinMaxCallbacksHandler().getSuggestedMinCallback();
		}
		// if here, min/max handler is not consistent
		return null;
	}

	/**
	 * Sets the callback to set the adjustment used when calculating the minimum data value.
	 * 
	 * @param suggestedMinCallback the callback to set the adjustment used when calculating the minimum data value.
	 */
	default void setSuggestedMin(MinMaxCallback<T> suggestedMinCallback) {
		// checks if min/max handler is consistent
		if (getMinMaxCallbacksHandler() != null) {
			// returns callback
			getMinMaxCallbacksHandler().setSuggestedMin(suggestedMinCallback);
		}
	}

	/**
	 * Sets the callback to set the adjustment used when calculating the minimum data value.
	 * 
	 * @param suggestedMinCallback the callback to set the adjustment used when calculating the minimum data value.
	 */
	default void setSuggestedMin(NativeCallback suggestedMinCallback) {
		// checks if min/max handler is consistent
		if (getMinMaxCallbacksHandler() != null) {
			// returns callback
			getMinMaxCallbacksHandler().setSuggestedMin(suggestedMinCallback);
		}
	}

	/**
	 * Returns the callback to set the adjustment used when calculating the maximum data value.
	 * 
	 * @return the callback to set the adjustment used when calculating the maximum data value.
	 */
	default MinMaxCallback<T> getSuggestedMaxCallback() {
		// checks if min/max handler is consistent
		if (getMinMaxCallbacksHandler() != null) {
			// returns callback
			return getMinMaxCallbacksHandler().getSuggestedMaxCallback();
		}
		// if here, min/max handler is not consistent
		return null;
	}

	/**
	 * Sets the callback to set the adjustment used when calculating the maximum data value.
	 * 
	 * @param suggestedMaxCallback the callback to set the adjustment used when calculating the maximum data value.
	 */
	default void setSuggestedMax(MinMaxCallback<T> suggestedMaxCallback) {
		// checks if min/max handler is consistent
		if (getMinMaxCallbacksHandler() != null) {
			// returns callback
			getMinMaxCallbacksHandler().setSuggestedMax(suggestedMaxCallback);
		}
	}

	/**
	 * Sets the callback to set the adjustment used when calculating the maximum data value.
	 * 
	 * @param suggestedMaxCallback the callback to set the adjustment used when calculating the maximum data value.
	 */
	default void setSuggestedMax(NativeCallback suggestedMaxCallback) {
		// checks if min/max handler is consistent
		if (getMinMaxCallbacksHandler() != null) {
			// returns callback
			getMinMaxCallbacksHandler().setSuggestedMax(suggestedMaxCallback);
		}
	}

}