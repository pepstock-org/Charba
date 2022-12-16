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

import org.pepstock.charba.client.callbacks.BeginAtZeroCallback;
import org.pepstock.charba.client.callbacks.NativeCallback;

/**
 * Interface which is implemented for the axes class to manage beginAtZero callback.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
interface HasBeginAtZeroCallbackHandler {

	/**
	 * Returns a {@link BegiAtZeroCallbackHandler} which is managing beginAtZero callback options.
	 * 
	 * @return a {@link BegiAtZeroCallbackHandler} which is managing beginAtZero callback options
	 */
	BegiAtZeroCallbackHandler getBegiAtZeroCallbackHandler();

	/**
	 * Returns the callback to set if true, scale will include 0 if it is not already included.
	 * 
	 * @return the callback to set if true, scale will include 0 if it is not already included.
	 */
	default BeginAtZeroCallback getBeginAtZeroCallback() {
		// checks if begitAtZero handler is consistent
		if (getBegiAtZeroCallbackHandler() != null) {
			// returns callback
			return getBegiAtZeroCallbackHandler().getBeginAtZeroCallback();
		}
		// if here, begitAtZero handler is not consistent
		return null;
	}

	/**
	 * Sets the callback to set if true, scale will include 0 if it is not already included.
	 * 
	 * @param beginAtZeroCallback the callback to set if true, scale will include 0 if it is not already included.
	 */
	default void setBeginAtZero(BeginAtZeroCallback beginAtZeroCallback) {
		// checks if begitAtZero handler is consistent
		if (getBegiAtZeroCallbackHandler() != null) {
			// returns callback
			getBegiAtZeroCallbackHandler().setBeginAtZero(beginAtZeroCallback);
		}
	}

	/**
	 * Sets the callback to set if true, scale will include 0 if it is not already included.
	 * 
	 * @param beginAtZeroCallback the callback to set if true, scale will include 0 if it is not already included.
	 */
	default void setBeginAtZero(NativeCallback beginAtZeroCallback) {
		// checks if begitAtZero handler is consistent
		if (getBegiAtZeroCallbackHandler() != null) {
			// returns callback
			getBegiAtZeroCallbackHandler().setBeginAtZero(beginAtZeroCallback);
		}
	}

}