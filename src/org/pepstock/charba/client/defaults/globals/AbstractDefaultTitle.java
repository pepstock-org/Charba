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

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.defaults.IsDefaultAbstractTitle;
import org.pepstock.charba.client.enums.ElementAlign;
import org.pepstock.charba.client.enums.Position;

/**
 * CHART.JS default values for TITLE and SUBTITLE elements.
 * 
 * @author Andrea "Stock" Stocchero
 */
abstract class AbstractDefaultTitle implements IsDefaultAbstractTitle {

	private static final boolean DEFAULT_FULL_SIZE = true;

	private static final boolean DEFAULT_DISPLAY = false;

	/**
	 * To avoid any instantiation
	 */
	AbstractDefaultTitle() {
		// do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultPluginElement#isDisplay()
	 */
	@Override
	public boolean isDisplay() {
		return DEFAULT_DISPLAY;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultPluginElement#getPosition()
	 */
	@Override
	public Position getPosition() {
		return Position.TOP;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAbstractTitle#isFullSize()
	 */
	@Override
	public boolean isFullSize() {
		return DEFAULT_FULL_SIZE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultPluginElement#getAlign()
	 */
	@Override
	public ElementAlign getAlign() {
		return ElementAlign.CENTER;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultFontContainer#getColorAsString()
	 */
	@Override
	public String getColorAsString() {
		return Defaults.get().getGlobal().getColorAsString();
	}

}