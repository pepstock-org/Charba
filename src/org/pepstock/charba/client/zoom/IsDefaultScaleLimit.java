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
package org.pepstock.charba.client.zoom;

import org.pepstock.charba.client.items.Undefined;

/**
 * {@link ZoomPlugin#ID} plugin default options interface for scale limit (min and max) elements.<br>
 * It contains all default values for scale limit.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
interface IsDefaultScaleLimit extends IsDefaultScaleRange {

	/**
	 * Returns <code>true</code> to use whatever minimum limit the scale had when the chart was first displayed.
	 * 
	 * @return <code>true</code> to use whatever minimum limit the scale had when the chart was first displayed
	 */
	default boolean isOriginalMin() {
		return false;
	}

	/**
	 * Returns <code>true</code> to use whatever maximum limit the scale had when the chart was first displayed.
	 * 
	 * @return <code>true</code> to use whatever maximum limit the scale had when the chart was first displayed
	 */
	default boolean isOriginalMax() {
		return false;
	}

	/**
	 * Returns the minimum allowed range.<br>
	 * This defines the max zoom level.
	 * 
	 * @return the minimum allowed range.<br>
	 *         This defines the max zoom level.
	 */
	default double getMinRange() {
		return Undefined.DOUBLE;
	}

}