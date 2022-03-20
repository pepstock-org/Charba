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

import org.pepstock.charba.client.callbacks.MinMaxCallback;
import org.pepstock.charba.client.callbacks.NativeCallback;

/**
 * Interface which is implemented for the axes class to manage min, max, suggestedMin and suggestedMax callbacks.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
interface HasMinMaxHandler<T> {

	/**
	 * Returns a {@link MinMaxHandler} which is managing all min and max options.
	 * 
	 * @return a {@link MinMaxHandler} which is managing all min and max options
	 */
	MinMaxHandler<T> getMinMaxHandler();

	/**
	 * Returns the callback to set the user defined minimum number for the scale, overrides minimum value from data.
	 * 
	 * @return the callback to set the user defined minimum number for the scale, overrides minimum value from data.
	 */
	default MinMaxCallback<T> getMinCallback() {
		// checks if axis is consistent
		if (getMinMaxHandler() != null) {
			// returns callback
			return getMinMaxHandler().getMinCallback();
		}
		// if here, axis is not consistent
		return null;
	}

	/**
	 * Sets the callback to set the user defined minimum number for the scale, overrides minimum value from data.
	 * 
	 * @param minCallback the callback to set the user defined minimum number for the scale, overrides minimum value from data.
	 */
	default void setMin(MinMaxCallback<T> minCallback) {
		// checks if axis is consistent
		if (getMinMaxHandler() != null) {
			// returns callback
			getMinMaxHandler().setMin(minCallback);
		}
	}

	/**
	 * Sets the callback to set the user defined minimum number for the scale, overrides minimum value from data.
	 * 
	 * @param minCallback the callback to set the user defined minimum number for the scale, overrides minimum value from data.
	 */
	default void setMin(NativeCallback minCallback) {
		// checks if axis is consistent
		if (getMinMaxHandler() != null) {
			// returns callback
			getMinMaxHandler().setMin(minCallback);
		}
	}

	/**
	 * Sets the callback to set the user defined maximum number for the scale, overrides maximum value from data.
	 *
	 * @param maxCallback the callback to set the user defined maximum number for the scale, overrides maximum value from data.
	 */
	default void setMax(MinMaxCallback<T> maxCallback) {
		// checks if axis is consistent
		if (getMinMaxHandler() != null) {
			// returns callback
			getMinMaxHandler().setMax(maxCallback);
		}
	}

	/**
	 * Sets the callback to set the user defined maximum number for the scale, overrides maximum value from data.
	 *
	 * @param maxCallback the callback to set the user defined maximum number for the scale, overrides maximum value from data.
	 */
	default void setMax(NativeCallback maxCallback) {
		// checks if axis is consistent
		if (getMinMaxHandler() != null) {
			// returns callback
			getMinMaxHandler().setMax(maxCallback);
		}
	}

	/**
	 * Returns the callback to set the user defined maximum number for the scale, overrides maximum value from data.
	 *
	 * @return the callback to set the user defined maximum number for the scale, overrides maximum value from data.
	 */
	default MinMaxCallback<T> getMaxCallback() {
		// checks if axis is consistent
		if (getMinMaxHandler() != null) {
			// returns callback
			return getMinMaxHandler().getMaxCallback();
		}
		// if here, axis is not consistent
		return null;
	}

	/**
	 * Returns the callback to set the adjustment used when calculating the minimum data value.
	 * 
	 * @return the callback to set the adjustment used when calculating the minimum data value.
	 */
	default MinMaxCallback<T> getSuggestedMinCallback() {
		// checks if axis is consistent
		if (getMinMaxHandler() != null) {
			// returns callback
			return getMinMaxHandler().getSuggestedMinCallback();
		}
		// if here, axis is not consistent
		return null;
	}

	/**
	 * Sets the callback to set the adjustment used when calculating the minimum data value.
	 * 
	 * @param suggestedMinCallback the callback to set the adjustment used when calculating the minimum data value.
	 */
	default void setSuggestedMin(MinMaxCallback<T> suggestedMinCallback) {
		// checks if axis is consistent
		if (getMinMaxHandler() != null) {
			// returns callback
			getMinMaxHandler().setSuggestedMin(suggestedMinCallback);
		}
	}

	/**
	 * Sets the callback to set the adjustment used when calculating the minimum data value.
	 * 
	 * @param suggestedMinCallback the callback to set the adjustment used when calculating the minimum data value.
	 */
	default void setSuggestedMin(NativeCallback suggestedMinCallback) {
		// checks if axis is consistent
		if (getMinMaxHandler() != null) {
			// returns callback
			getMinMaxHandler().setSuggestedMin(suggestedMinCallback);
		}
	}

	/**
	 * Returns the callback to set the adjustment used when calculating the maximum data value.
	 * 
	 * @return the callback to set the adjustment used when calculating the maximum data value.
	 */
	default MinMaxCallback<T> getSuggestedMaxCallback() {
		// checks if axis is consistent
		if (getMinMaxHandler() != null) {
			// returns callback
			return getMinMaxHandler().getSuggestedMaxCallback();
		}
		// if here, axis is not consistent
		return null;
	}

	/**
	 * Sets the callback to set the adjustment used when calculating the maximum data value.
	 * 
	 * @param suggestedMaxCallback the callback to set the adjustment used when calculating the maximum data value.
	 */
	default void setSuggestedMax(MinMaxCallback<T> suggestedMaxCallback) {
		// checks if axis is consistent
		if (getMinMaxHandler() != null) {
			// returns callback
			getMinMaxHandler().setSuggestedMax(suggestedMaxCallback);
		}
	}

	/**
	 * Sets the callback to set the adjustment used when calculating the maximum data value.
	 * 
	 * @param suggestedMaxCallback the callback to set the adjustment used when calculating the maximum data value.
	 */
	default void setSuggestedMax(NativeCallback suggestedMaxCallback) {
		// checks if axis is consistent
		if (getMinMaxHandler() != null) {
			// returns callback
			getMinMaxHandler().setSuggestedMax(suggestedMaxCallback);
		}
	}

}
