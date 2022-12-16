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
package org.pepstock.charba.client.datalabels.enums;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.datalabels.DataLabelsPlugin;
import org.pepstock.charba.client.dom.enums.MouseEventType;

/**
 * This {@link DataLabelsPlugin#ID} plugin currently supports the below label events.<br>
 * Charba events that need to be enabled in order to get the associated label event working. Note that by default Charba enables "mousemove", "mouseout", "click", meaning that
 * label events work out-of-the-box.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum Event implements Key
{
	/**
	 * The mouse is moved over a label. Charba {@link MouseEventType#MOUSE_MOVE} must be enabled.
	 */
	ENTER("enter"),
	/**
	 * The mouse is moved out of a label. Charba {@link MouseEventType#MOUSE_MOVE} must be enabled.
	 */
	LEAVE("leave"),
	/**
	 * The mouse's primary button is pressed and released on a label. Charba {@link MouseEventType#CLICK} must be enabled.
	 */
	CLICK("click");

	// name value of property
	private final String value;

	/**
	 * Creates with the property value to use in the native object.
	 * 
	 * @param value value of property name
	 */
	private Event(String value) {
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