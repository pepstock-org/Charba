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

import org.pepstock.charba.client.callbacks.TickCallback;

/**
 * The linear scale is use to chart numerical data.<br>
 * It can be placed on either the x or y axis.<br>
 * The scatter chart type automatically configures a line chart to use one of these scales for the x axis.<br>
 * As the name suggests, linear interpolation is used to determine where a value lies on the axis.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class CartesianLinearTick extends CartesianNumericTick implements IsLinearTick {

	// handler for callback for category axis
	private final LinearTickHandler<CartesianLinearTick> tickHandler;
	// options handler to manage the callbacks
	private final LinearTickOptionsHandler optionsHandler;

	/**
	 * Builds the object storing the axis instance.
	 * 
	 * @param axis axis instance
	 */
	CartesianLinearTick(Axis axis) {
		super(axis);
		// creates handlers
		this.tickHandler = new LinearTickHandler<>(axis, this);
		this.optionsHandler = new LinearTickOptionsHandler(axis);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.IsLinearTick#getLinearTickOptionsHandler()
	 */
	@Override
	public final LinearTickOptionsHandler getTickOptionsHandler() {
		return optionsHandler;
	}

	/**
	 * Returns the user callback instance.
	 * 
	 * @return the callback
	 */
	public TickCallback getCallback() {
		return tickHandler.getCallback();
	}

	/**
	 * Sets the user callback instance.
	 * 
	 * @param callback the callback to set
	 */
	public void setCallback(TickCallback callback) {
		tickHandler.setCallback(callback);
	}

}