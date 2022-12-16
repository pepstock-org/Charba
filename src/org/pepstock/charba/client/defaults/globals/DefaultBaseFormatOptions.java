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

import org.pepstock.charba.client.defaults.IsDefaultBaseFormatOptions;
import org.pepstock.charba.client.intl.enums.LocaleMatcher;
import org.pepstock.charba.client.intl.enums.NumberingSystem;

/**
 * INTL default values for locale matcher options for internationalization.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class DefaultBaseFormatOptions implements IsDefaultBaseFormatOptions {

	public static final IsDefaultBaseFormatOptions INSTANCE = new DefaultBaseFormatOptions();

	/**
	 * To avoid any instantiation
	 */
	DefaultBaseFormatOptions() {
		// do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultLocaleMatcher#getLocaleMatcher()
	 */
	@Override
	public LocaleMatcher getLocaleMatcher() {
		return LocaleMatcher.BEST_FIT;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultBaseFormatOptions#getNumberingSystem()
	 */
	@Override
	public NumberingSystem getNumberingSystem() {
		return null;
	}

}