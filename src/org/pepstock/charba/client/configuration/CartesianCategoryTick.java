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

import org.pepstock.charba.client.callbacks.CategoryTickCallback;

/**
 * The category scale provides the following options for configuring tick marks.<br>
 * The labels are drawn from one of the label arrays included in the chart data.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class CartesianCategoryTick extends CartesianTick {

	// handler for callback for category axis
	private final CategoryTickHandler tickHandler;

	/**
	 * Builds the object storing the axis instance.
	 * 
	 * @param axis axis instance
	 */
	CartesianCategoryTick(Axis axis) {
		super(axis);
		// creates handler
		this.tickHandler = new CategoryTickHandler(axis, this);
	}

	/**
	 * Returns the user callback instance.
	 * 
	 * @return the callback
	 */
	public CategoryTickCallback getCallback() {
		return tickHandler.getCallback();
	}

	/**
	 * Sets the user callback instance.
	 * 
	 * @param callback the callback to set
	 */
	public void setCallback(CategoryTickCallback callback) {
		tickHandler.setCallback(callback);
	}

}