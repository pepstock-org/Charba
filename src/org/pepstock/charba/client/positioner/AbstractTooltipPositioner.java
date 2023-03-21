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
package org.pepstock.charba.client.positioner;

import org.pepstock.charba.client.enums.TooltipPosition;

/**
 * Abstract class which implements a positioner. It helps to create a custom positioner, creating a custom tooltip position using the name as string passed as argument.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public abstract class AbstractTooltipPositioner implements TooltipPositioner {

	private final CustomTooltipPosition name;

	/**
	 * Creates the positioner passing the name of tooltip position as argument.<br>
	 * If the name is one of out of the box ones, see {@link TooltipPosition}, an exception is launched.
	 * 
	 * @param name of tooltip position.
	 */
	protected AbstractTooltipPositioner(String name) {
		this.name = new CustomTooltipPosition(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.positioner.TooltipPositioner#getName()
	 */
	@Override
	public final CustomTooltipPosition getName() {
		return name;
	}

}