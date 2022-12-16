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
package org.pepstock.charba.client.defaults.globals;

import org.pepstock.charba.client.defaults.IsDefaultInteraction;
import org.pepstock.charba.client.enums.InteractionAxis;
import org.pepstock.charba.client.enums.InteractionMode;

/**
 * CHART.JS default values for INTERACTION element.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class DefaultInteraction implements IsDefaultInteraction {

	private static final boolean DEFAULT_INTERSECT = true;

	private static final boolean DEFAULT_INCLUDE_INVISIBLE = false;

	/**
	 * To avoid any instantiation
	 */
	DefaultInteraction() {
		// do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultInteraction#getMode()
	 */
	@Override
	public InteractionMode getMode() {
		return InteractionMode.NEAREST;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultInteraction#isIntersect()
	 */
	@Override
	public boolean isIntersect() {
		return DEFAULT_INTERSECT;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultInteraction#getAxis()
	 */
	@Override
	public InteractionAxis getAxis() {
		return InteractionAxis.X;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultInteraction#isIncludeInvisible()
	 */
	@Override
	public boolean isIncludeInvisible() {
		return DEFAULT_INCLUDE_INVISIBLE;
	}

}