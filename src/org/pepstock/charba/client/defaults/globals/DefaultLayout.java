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

import org.pepstock.charba.client.defaults.IsDefaultLayout;
import org.pepstock.charba.client.defaults.IsDefaultPadding;

/**
 * CHART.JS default values for LAYOUT element.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultLayout implements IsDefaultLayout {

	private static final boolean DEFAULT_AUTO_PADDING = true;

	private static final int DEFAULT_PADDING = 0;

	private final DefaultPadding padding = new DefaultPadding(DEFAULT_PADDING);

	/**
	 * To avoid any instantiation
	 */
	DefaultLayout() {
		// do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.IsDefaultOptions#getPadding()
	 */
	@Override
	public IsDefaultPadding getPadding() {
		return padding;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultLayout#isAutoPadding()
	 */
	@Override
	public boolean isAutoPadding() {
		return DEFAULT_AUTO_PADDING;
	}

}