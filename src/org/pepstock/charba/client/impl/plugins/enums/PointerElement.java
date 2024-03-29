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
package org.pepstock.charba.client.impl.plugins.enums;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.impl.plugins.ChartPointer;

/**
 * The elements of the chart in scope of {@link ChartPointer#ID} plugin.
 * 
 * @author Andrea "Stock" Stocchero
 */
public enum PointerElement implements Key
{
	/**
	 * Changes the cursor when over to a dataset.
	 */
	DATASET("dataset"),
	/**
	 * Changes the cursor when over to the title.
	 */
	TITLE("title"),
	/**
	 * Changes the cursor when over to the subtitle.
	 */
	SUBTITLE("subtitle"),
	/**
	 * Changes the cursor when over to an axes.
	 */
	AXES("axes"),
	/**
	 * Changes the cursor when over to the legend.
	 */
	LEGEND("legend");

	// name value of property
	private final String value;

	/**
	 * Creates with the property value to use in the native object.
	 * 
	 * @param value value of property name
	 */
	private PointerElement(String value) {
		this.value = value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.commons.Key#value()
	 */
	@Override
	public String value() {
		return value;
	}

}