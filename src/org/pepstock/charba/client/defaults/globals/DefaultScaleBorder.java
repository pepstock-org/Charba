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

import java.util.Collections;
import java.util.List;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.defaults.IsDefaultScaleBorder;

/**
 * CHART.JS default values for BORDER element.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultScaleBorder implements IsDefaultScaleBorder {

	private static final boolean DEFAULT_DISPLAY = true;

	private static final double DEFAULT_DASH_OFFSET = 0;

	private static final int DEFAULT_WIDTH = 1;

	private static final List<Integer> DEFAULT_DASH = Collections.emptyList();

	private static final int DEFAULT_Z = 0;

	/**
	 * To avoid any instantiation
	 */
	DefaultScaleBorder() {
		// do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScaleBorder#isDisplay()
	 */
	@Override
	public boolean isDisplay() {
		return DEFAULT_DISPLAY;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScaleBorder#getWidth()
	 */
	@Override
	public int getWidth() {
		return DEFAULT_WIDTH;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScaleBorder#getColorAsString()
	 */
	@Override
	public String getColorAsString() {
		return Defaults.get().getGlobal().getBorderColorAsString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScaleBorder#getDashOffset()
	 */
	@Override
	public double getDashOffset() {
		return DEFAULT_DASH_OFFSET;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScaleBorder#getDash()
	 */
	@Override
	public List<Integer> getDash() {
		return DEFAULT_DASH;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScaleBorder#getZ()
	 */
	@Override
	public int getZ() {
		return DEFAULT_Z;
	}

}