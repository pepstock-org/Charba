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
package org.pepstock.charba.client.intl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.pepstock.charba.client.commons.ArrayUtil;
import org.pepstock.charba.client.commons.Key;

/**
 * Interface to map the locale item in order to get the name and search the items against the name instead of the value.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public interface IsLocaleItem extends Key {

	/**
	 * Returns an unmodifiable list of items which are matching with the name passed as argument.
	 * 
	 * @param enumValues enumeration values of the locale tag-
	 * @param name name to search in the object name and value
	 * @param <T> type of locale tag
	 * @return an unmodifiable list of items which are matching with the name passed as argument
	 */
	static <T extends IsLocaleItem> List<T> getItemsByName(T[] enumValues, String name) {
		List<T> result = new ArrayList<>();
		// checks if arguments are consistent
		if (name != null && ArrayUtil.isNotEmpty(enumValues)) {
			// to lower case for perform match
			String nameLowerCase = name.toLowerCase(Locale.getDefault());
			// scans enumeration
			for (T enumValue : enumValues) {
				// checks if enumeration name contains the name
				if (enumValue.getName().toLowerCase(Locale.getDefault()).contains(nameLowerCase)) {
					// returns EnumValue
					result.add(enumValue);
				}
				// checks if enumeration value contains the name
				if (enumValue.value().toLowerCase(Locale.getDefault()).contains(nameLowerCase)) {
					// returns EnumValue
					result.add(enumValue);
				}
			}
		}
		// returns an unmodifiable list
		return Collections.unmodifiableList(result);
	}

	/**
	 * Returns the name of the locale item.
	 * 
	 * @return the name of the locale item
	 */
	String getName();

}