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
package org.pepstock.charba.client.callbacks;

import org.pepstock.charba.client.configuration.Axis;
import org.pepstock.charba.client.items.AxisItem;

/**
 * Interface to be implemented which can be used to change parameters in the scale during setting dimensions.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface AxisDimensionsCallback {

	/**
	 * Callback that runs before dimensions are set.
	 * 
	 * @param axis axis instance where this callback as been defined
	 * @param item axis item instance
	 */
	void onBeforeSetDimensions(Axis axis, AxisItem item);

	/**
	 * Callback that runs after dimensions are set.
	 * 
	 * @param axis axis instance where this callback as been defined
	 * @param item axis item instance
	 */
	void onAfterSetDimensions(Axis axis, AxisItem item);

}