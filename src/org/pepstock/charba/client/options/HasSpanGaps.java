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
package org.pepstock.charba.client.options;

import org.pepstock.charba.client.defaults.globals.DefaultsBuilder;
import org.pepstock.charba.client.items.UndefinedValues;

/**
 * Defines a configuration element which is managing the SPANGAPS property.<br>
 * It has being used into options and datasets instances where SPANGAPS is required.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public interface HasSpanGaps {

	/**
	 * Returns a span gap handler instance to use into default methods of this interface.
	 * 
	 * @return a span gap handler instance
	 */
	SpanGapHandler getSpanGapHandler();

	/**
	 * Sets if lines will be drawn between points with no or null data.<br>
	 * If <code>false</code>, points with {@link Double#NaN} data will create a break in the line.
	 * 
	 * @param spanGaps <code>true</code> if lines will be drawn between points with no or null data.<br>
	 *            If <code>false</code>, points with {@link Double#NaN} data will create a break in the line
	 */
	default void setSpanGaps(boolean spanGaps) {
		// checks if gap handler is consistent
		if (getSpanGapHandler() != null) {
			getSpanGapHandler().setSpanGaps(spanGaps);
		}
	}

	/**
	 * Sets the value of the data if lines will be drawn between points with no or null data.
	 * 
	 * @param spanGaps the value of the data if lines will be drawn between points with no or null data
	 */
	default void setSpanGaps(double spanGaps) {
		// checks if gap handler is consistent
		if (getSpanGapHandler() != null) {
			getSpanGapHandler().setSpanGaps(spanGaps);
		}
	}

	/**
	 * Returns if lines will be drawn between points with no or null data.<br>
	 * If <code>false</code>, points with {@link Double#NaN} data will create a break in the line.
	 * 
	 * @return <code>true</code> if lines will be drawn between points with no or null data.<br>
	 *         If <code>false</code>, points with {@link Double#NaN} data will create a break in the line
	 */
	default boolean isSpanGaps() {
		// checks if gap handler is consistent
		if (getSpanGapHandler() != null) {
			return getSpanGapHandler().isSpanGaps();
		}
		return DefaultsBuilder.get().getOptions().isSpanGaps();
	}

	/**
	 * Returns the value of the data if lines will be drawn between points with no or null data.
	 * 
	 * @return the value of the data if lines will be drawn between points with no or null data
	 */
	default double getSpanGaps() {
		// checks if gap handler is consistent
		if (getSpanGapHandler() != null) {
			return getSpanGapHandler().getSpanGaps();
		}
		return UndefinedValues.DOUBLE;
	}

}
