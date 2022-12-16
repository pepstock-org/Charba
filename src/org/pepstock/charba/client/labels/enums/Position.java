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
package org.pepstock.charba.client.labels.enums;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.labels.LabelsPlugin;

/**
 * Enumeration of available positions to use to configure {@link LabelsPlugin#ID} plugin.
 * 
 * @author Andrea "Stock" Stocchero
 */
public enum Position implements Key
{
	/**
	 * In labels plugin is named 'default', inside the chart.
	 */
	DEFAULT("default"),
	/**
	 * The labels are located on border.
	 */
	BORDER("border"),
	/**
	 * The labels are located outisde of the chart.
	 */
	OUTSIDE("outside");

	// value of enum
	// this is the real value to set to configure
	// correctly the LABELS plugin
	private final String value;

	/**
	 * Creates the enumeration item by its value.
	 * 
	 * @param value this is the real value to set to configure LABELS plugin
	 */
	private Position(String value) {
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