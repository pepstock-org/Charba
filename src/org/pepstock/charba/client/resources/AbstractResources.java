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
package org.pepstock.charba.client.resources;

/**
 * Base class to extend in order to have java script injection, needed to CHARBA, where CHART.JS and date library.<br>
 * Every instance must have a module related to date adapter and library.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public abstract class AbstractResources {

	// flag to know if the LUXON date library must be injected ot not
	private final boolean injectDateLibrary;

	/**
	 * Creates a resource object by a flag.<br>
	 * Of passed argument is <code>true</code>, it must inject the LUXON date library.
	 * 
	 * @param injectDateLibrary <code>true</code> if it must inject the LUXON date library
	 */
	AbstractResources(boolean injectDateLibrary) {
		this.injectDateLibrary = injectDateLibrary;
	}

	/**
	 * Returns <code>true</code> if it must inject the LUXON date library.
	 * 
	 * @return <code>true</code> if it must inject the LUXON date library
	 */
	final boolean mustInjectDateLibrary() {
		return injectDateLibrary;
	}

	/**
	 * Injects CHART.JS, date adapter and library if not already injected.
	 */
	public abstract void inject();

}