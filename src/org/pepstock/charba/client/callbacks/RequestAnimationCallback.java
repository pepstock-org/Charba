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

import java.util.Date;

/**
 * Callback to tell the browser that you wish to perform an animation and requests that the browser calls a specified function to update an animation right before the next repaint.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public interface RequestAnimationCallback {

	/**
	 * Tells the browser that you wish to perform an animation and requests that the browser calls a specified function to update an animation right before the next repaint.
	 * 
	 * @param timestamp the callback function is passed one single argument indicating the point in time when request animation frame starts to execute callback functions.
	 */
	void invoke(Date timestamp);

}
