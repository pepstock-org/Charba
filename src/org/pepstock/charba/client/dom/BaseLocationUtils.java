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
package org.pepstock.charba.client.dom;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.pepstock.charba.client.commons.Constants;
import org.pepstock.charba.client.utils.RegExp;
import org.pepstock.charba.client.utils.RegExpResult;

/**
 * Parses the query string of location in order to provide the parameters as a map.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class BaseLocationUtils {
	// REGEXP pattern for query string
	private static final String REGEXP_QUERY_STRING_PATTERN = "([^?=&]+)(=([^&]*))?";
	// REGEXP for query string
	private static final RegExp REGEXP_QUERY_STRING = new RegExp(REGEXP_QUERY_STRING_PATTERN, "gi");
	// static instance of list parameters
	private static final Map<String, List<String>> QUERY_STRING_PARAMETERS_MAP = new HashMap<>();
	// static instance of cached query string
	private static String queryString = Constants.EMPTY_STRING;

	/**
	 * To avoid any instantiation
	 */
	BaseLocationUtils() {
		// do nothing
	}

	/**
	 * Gets the URL parameter of the specified name.<br>
	 * Note that if multiple parameters have been specified with the same name, the last one will be returned.
	 *
	 * @param location location DOM item with URL page definition
	 * @param name the name of the URL parameter
	 * @return the value of the URL parameter, or <code>null</code> if missing
	 */
	static String getParameter(BaseLocation location, String name) {
		// checks if argument is consistent
		if (name != null) {
			// checks the consistency of parameters map
			// and loads it if not yet done
			checkAndLoadParametersMap(location);
			// retrieves the parameter by name
			List<String> parametersList = QUERY_STRING_PARAMETERS_MAP.get(name);
			// checks if list is consistent
			if (parametersList != null && !parametersList.isEmpty()) {
				// returns the last parameter set if more then one
				return parametersList.get(parametersList.size() - 1);
			}
		}
		// if here, the argument is not consistent
		// or the name is not present
		// returns null
		return null;
	}

	/**
	 * Returns an unmodifiable map of the URL query parameters for the host page at the time this method was called.<br>
	 * Any changes to the window's location will be reflected in the result of subsequent calls.
	 *
	 * @param location location DOM item with URL page definition
	 * @return a map from URL query parameter names to a list of values
	 */
	static Map<String, List<String>> getParameterMap(BaseLocation location) {
		// checks the consistency of parameters map
		// and loads it if not yet done
		checkAndLoadParametersMap(location);
		// returns the parameters as immutable
		return Collections.unmodifiableMap(QUERY_STRING_PARAMETERS_MAP);
	}

	/**
	 * Checks the consistency of parameters map and loads it if not yet done or if URL query string is change.
	 * 
	 * @param location location DOM item with URL page definition
	 */
	private static void checkAndLoadParametersMap(BaseLocation location) {
		// gets the current string
		final String currentQueryString = location.getSearch();
		// checks the query string is changed from
		// previous invocation
		if (!queryString.equals(currentQueryString)) {
			// reads and parses the query string
			loadParametersMap(currentQueryString);
			// stores the current query string to the cached reference
			queryString = currentQueryString;
		}
	}

	/**
	 * Builds an unmodifiable map that contains all parameters, parsed from query string.
	 * 
	 * @param queryString the query string of URL of document
	 */
	private static void loadParametersMap(String queryString) {
		// clears the map of parameters
		QUERY_STRING_PARAMETERS_MAP.clear();
		// creates the reference of regular expression result
		RegExpResult regExpResult;
		// starts cycle applying the regexp till no result
		while ((regExpResult = REGEXP_QUERY_STRING.exec(queryString)) != null) {
			// checks if value is correctly parsed
			// 0 = whole token, 1 = key, 2 = equals plus value, 3 = value
			if (regExpResult.length() == 4 && regExpResult.get(1) != null) {
				// gets key and value
				String key = regExpResult.get(1);
				String value = regExpResult.get(3);
				// gets reference for list of values
				List<String> values;
				// checks if the key has been already stored
				if (!QUERY_STRING_PARAMETERS_MAP.containsKey(key)) {
					// NEW KEY
					// creates new list
					values = new LinkedList<>();
					// stores list
					QUERY_STRING_PARAMETERS_MAP.put(key, values);
				} else {
					// OLD KEY
					// gets the stored list of value for the key
					values = QUERY_STRING_PARAMETERS_MAP.get(key);
				}
				// stores the value
				values.add(value == null ? Constants.EMPTY_STRING : value);
			}
		}
		// scans all entries to replace the list with unmodifiable list
		// so the user can not change it.
		for (Map.Entry<String, List<String>> entry : QUERY_STRING_PARAMETERS_MAP.entrySet()) {
			entry.setValue(Collections.unmodifiableList(entry.getValue()));
		}
	}

}
