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
import org.pepstock.charba.client.callbacks.NumberFormatCallback;
import org.pepstock.charba.client.options.IsNumberFormat;

/**
 * Common methods for numeric ticks (linear/log cartesian and radial).<br>
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public interface IsNumericTick {

	/**
	 * Returns the linear tick options handler instance, for callbacks.
	 * 
	 * @return the linear tick options handler instance, for callbacks
	 */
	NumericTickOptionsHandler getTickOptionsHandler();

	/**
	 * Returns the number format instance.
	 * 
	 * @return the number format instance
	 */
	IsNumberFormat getNumberFormat();

	/**
	 * Returns the callback to set the number formatting options.
	 * 
	 * @return the callback instance to use
	 */
	default NumberFormatCallback getNumberFormatCallback() {
		// checks if options handler is consistent
		if (getTickOptionsHandler() != null) {
			return getTickOptionsHandler().getNumberFormatCallback();
		}
		// if here, handler is not consistent
		// then returns null
		return null;
	}

	/**
	 * Sets the number formatting options.
	 * 
	 * @param numberFormatCallback the callback instance to use
	 */
	default void setNumberFormat(NumberFormatCallback numberFormatCallback) {
		// checks if options handler is consistent
		if (getTickOptionsHandler() != null) {
			getTickOptionsHandler().setNumberFormat(numberFormatCallback);
		}
	}

	/**
	 * Sets the number formatting options.
	 * 
	 * @param numberFormatCallback the callback instance to use
	 */
	default void setNumberFormat(NativeCallback numberFormatCallback) {
		// checks if options handler is consistent
		if (getTickOptionsHandler() != null) {
			getTickOptionsHandler().setNumberFormat(numberFormatCallback);
		}
	}

}