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

/**
 * Property to set alignment of the legend and the title.
 * 
 * @author Andrea "Stock" Stocchero
 */
public enum ElementAlign implements Key
{
	/**
	 * The property sets the start alignment.
	 */
	START("start", "left"),
	/**
	 * The property sets the center alignment.
	 */
	CENTER("center", "center"),
	/**
	 * The property sets the end alignment.
	 */
	END("end", "right");

	// name value of property
	private final String value;
	// CSS value
	private final String horizontalAlignmentValue;

	/**
	 * Creates with the property value to use in the native object.
	 * 
	 * @param value value of property name
	 * @param horizontalAlignmentValue CSS value to apply to horizontal alignment
	 */
	private ElementAlign(String value, String horizontalAlignmentValue) {
		this.value = value;
		this.horizontalAlignmentValue = horizontalAlignmentValue;
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

	/**
	 * Returns the CSS value for horizontal alignment in the HTML element style.
	 * 
	 * @return the CSS value for horizontal alignment in the HTML element style
	 */
	public String getHorizontalAlignmentValue() {
		return horizontalAlignmentValue;
	}

}