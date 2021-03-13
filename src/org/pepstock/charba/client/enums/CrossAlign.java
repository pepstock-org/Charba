/**
    Copyright 2017 Andrea "Stock" Stocchero

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

	    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/
package org.pepstock.charba.client.enums;

import org.pepstock.charba.client.commons.Key;

/**
 * The <b>crossAlign</b> setting configures how labels align with the tick mark in the perpendicular direction.<br>
 * For instance vertical for a horizontal axis and horizontal for a vertical axis.
 * 
 * @author Andrea "Stock" Stocchero
 */
public enum CrossAlign implements Key
{
	/**
	 * the property sets the near cross alignment.
	 */
	NEAR("near"),
	/**
	 * the property sets the center cross alignment.
	 */
	CENTER("center"),
	/**
	 * the property sets the far cross alignment.
	 */
	FAR("far");

	// name value of property
	private final String value;

	/**
	 * Creates with the property value to use in the native object.
	 * 
	 * @param value value of property name
	 */
	private CrossAlign(String value) {
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