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
import org.pepstock.charba.client.options.IsNumberFormat;

/**
 * This object is used to map defined radial axis as linear.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class RadialLinearTick extends Tick implements IsLinearTick {

	// handler for callback for category axis
	private final LinearTickHandler<RadialLinearTick> tickHandler;
	// number formatting manager
	private final NumberFormatter numberFormatter;
	// options handler to manage the callbacks
	private final LinearTickOptionsHandler optionsHandler;

	/**
	 * Builds the object storing the axis which this tick belongs to.
	 * 
	 * @param axis axis which this tick belongs to.
	 */
	RadialLinearTick(Axis axis) {
		super(axis);
		// creates handler and number format
		this.tickHandler = new LinearTickHandler<>(axis, this);
		this.numberFormatter = new NumberFormatter(() -> getConfiguration().getNumberFormat());
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.IsNumericTick#getNumberFormat()
	 */
	@Override
	public IsNumberFormat getNumberFormat() {
		return numberFormatter;
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