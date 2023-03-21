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
package org.pepstock.charba.client.defaults;

import org.pepstock.charba.client.intl.enums.LocaleMatcher;
import org.pepstock.charba.client.intl.enums.NumberingSystem;

/**
 * Interface to define the locale matching options for internationalization.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface IsDefaultBaseFormatOptions {

	/**
	 * Returns the locale matching algorithm to use.
	 * 
	 * @return the locale matching algorithm to use
	 */
	LocaleMatcher getLocaleMatcher();

	/**
	 * Returns the numbering system to use.
	 * 
	 * @return the numbering system to use
	 */
	NumberingSystem getNumberingSystem();

}