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

import org.pepstock.charba.client.defaults.IsDefaultFiller;
import org.pepstock.charba.client.enums.DrawTime;

/**
 * CHART.JS default values for FILLER plugin element.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultFiller implements IsDefaultFiller {

	private static final boolean DEFAULT_PROPAGATE = true;

	/**
	 * To avoid any instantiation
	 */
	DefaultFiller() {
		// do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultFiller#isPropagate()
	 */
	@Override
	public boolean isPropagate() {
		return DEFAULT_PROPAGATE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultFiller#getDrawTime()
	 */
	@Override
	public DrawTime getDrawTime() {
		return DrawTime.BEFORE_DATASET_DRAW;
	}

}