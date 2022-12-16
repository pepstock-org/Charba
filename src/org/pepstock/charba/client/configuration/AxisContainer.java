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
 * Container of a axis. Must be extended for all other entities which is sub element of an axis.
 * 
 * @author Andrea "Stock" Stocchero
 */
abstract class AxisContainer {

	// axis instance
	private final Axis axis;

	/**
	 * Creates the object with the axis instance
	 * 
	 * @param axis axis instance
	 */
	AxisContainer(Axis axis) {
		this.axis = axis;
	}

	/**
	 * Returns the axis instance.
	 * 
	 * @return the axis
	 */
	public final Axis getAxis() {
		return axis;
	}

}