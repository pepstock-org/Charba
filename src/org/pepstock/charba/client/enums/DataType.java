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
package org.pepstock.charba.client.enums;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.data.Dataset;

/**
 * Enumeration with all possible types of data set in the dataset.
 * 
 * @author Andrea "Stock" Stocchero
 * @see Dataset
 */
public enum DataType implements Key
{
	/**
	 * The data property is not set yet.
	 */
	UNKNOWN("unknown"),
	/**
	 * The data property is set as array of doubles.
	 */
	NUMBERS("numbers"),
	/**
	 * The data property is set as array of data points.
	 */
	POINTS("points"),
	/**
	 * The data property is set as array of floating data.<br>
	 * Only for <b>BAR</b> datasets.
	 */
	ARRAYS("arrays"),
	/**
	 * The data property is set as array of strings.
	 */
	STRINGS("strings");

	// name value of property
	private final String value;

	/**
	 * Creates with the property value to use in the native object.
	 * 
	 * @param value value of property name
	 */
	private DataType(String value) {
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