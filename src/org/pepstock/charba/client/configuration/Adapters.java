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

/**
 * The following adapters element is used to configure a date adapter, injecting to support time series in the CAHRT.JS.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class Adapters extends AxisContainer {

	private final ScaleDateAdapter dateAdapterOptions;

	/**
	 * Builds the object storing the axis which this adapters belongs to.
	 * 
	 * @param axis axis which this adapters belongs to.
	 */
	Adapters(Axis axis) {
		super(axis);
		// creates the date adapter options object
		this.dateAdapterOptions = new ScaleDateAdapter(axis);
	}

	/**
	 * Returns the date adapter options.
	 * 
	 * @return date adapter options used to configure the date adapter
	 */
	public ScaleDateAdapter getDate() {
		return dateAdapterOptions;
	}

}