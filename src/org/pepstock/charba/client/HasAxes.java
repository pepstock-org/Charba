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
package org.pepstock.charba.client;

import org.pepstock.charba.client.configuration.Axis;

/**
 * Defines the acceptable axes for a chart.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public interface HasAxes {

	/**
	 * Returns <code>true</code> if the axis can be managed by a this chart type.
	 * 
	 * @param axis axis to check
	 * @return <code>true</code> if the axis can be managed by a this chart type
	 */
	default boolean checkAxis(Axis axis) {
		return true;
	}

}