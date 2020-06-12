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

import java.util.Locale;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.options.IsScaleId;

/**
 * Enumerates the default scale id.
 * 
 * @author Andrea "Stock" Stocchero
 */
public enum DefaultScaleId implements IsScaleId
{
	/**
	 * Default scale id for X axis.
	 */
	X("x"),
	/**
	 * Default scale id for Y axis.
	 */
	Y("y"),
	/**
	 * Default scale id for chart with a single axis.
	 */
	UNKNOWN("_charbaunknown");

	// name value of property
	private final String value;

	/**
	 * Creates with the property value to use into native object.
	 * 
	 * @param value value of property name
	 */
	private DefaultScaleId(String value) {
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

	/**
	 * Returns <code>true</code> if the scale id is related to this axis id.
	 * 
	 * @param scaleId scale id to be checked
	 * @return <code>true</code> if the scale id is related to this axis id
	 */
	public boolean is(String scaleId) {
		// checks id if consistent
		if (scaleId != null) {
			// put to lower case
			String id = scaleId.toLowerCase(Locale.getDefault());
			// checks if starts with
			return id.startsWith(value());
		}
		// if here the argument is not consistent
		return false;
	}

	/**
	 * Returns <code>true</code> if the scale id is related to this axis id.
	 * 
	 * @param scaleId scale id to be checked
	 * @return <code>true</code> if the scale id is related to this axis id
	 */
	public boolean is(IsScaleId scaleId) {
		return is(Key.checkAndGetIfValid(scaleId).value());
	}
	
	/**
	 * Returns the default scale id instance if the id is one of default ones, otherwise will return <code>null</code> if not found.
	 * 
	 * @param id scale id as string to scan
	 * @return the default scale id instance, otherwise will return <code>null</code> if not found
	 */
	public static DefaultScaleId getByScaleId(String id) {
		// checks if id is consistent
		if (id != null) {
			// scans all defaults to check with the argument
			for (DefaultScaleId scaleId : values()) {
				// checks if default scale id matches with argument
				if (scaleId.value().equals(id)) {
					return scaleId;
				}
			}
		}
		// if here, the id does not match
		return null;
	}

}