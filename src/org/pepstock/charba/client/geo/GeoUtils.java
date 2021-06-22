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
package org.pepstock.charba.client.geo;

import java.util.Collections;
import java.util.List;

import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.resources.AbstractInjectableResource;

/**
 * FIXME
 * @author Andrea "Stock" Stocchero
 *
 */
public final class GeoUtils {

	/**
	 * To avoid any instantiation
	 */
	private GeoUtils() {
		// do nothing
	}

	/**
	 * 
	 * @param topojson
	 * @param featureProperty
	 * @return
	 */
	public static List<Feature> feature(AbstractInjectableResource topojson, String featureProperty) {
		// checks if topojson is consistent
		if (topojson != null) {
			return feature(topojson.getContent(), featureProperty);
		}
		// if here, the arguments or the feature parsing are not consistent
		// then returns an empty list
		return Collections.emptyList();
	}
	
	/**
	 * 
	 * @param topojson
	 * @param featureProperty
	 * @return
	 */
	public static List<Feature> feature(AbstractInjectableResource topojson, Key featureProperty) {
		// checks if arguments are consistent
		if (topojson != null && Key.isValid(featureProperty)) {
			return feature(topojson.getContent(), featureProperty.value());
		}
		// if here, the arguments or the feature parsing are not consistent
		// then returns an empty list
		return Collections.emptyList();
	}

	/**
	 * 
	 * @param topojson
	 * @param featureProperty
	 * @return
	 */
	public static List<Feature> feature(String topojson, Key featureProperty) {
		// checks if property is consistent
		if (Key.isValid(featureProperty)) {
			return feature(topojson, featureProperty.value());
		}
		// if here, the arguments or the feature parsing are not consistent
		// then returns an empty list
		return Collections.emptyList();
	}

	/**
	 * 
	 * @param topojson
	 * @param featureProperty
	 * @return
	 */
	public static List<Feature> feature(String topojson, String featureProperty) {
		// checks if arguments are consistent
		if (isConsistent(topojson) && isConsistent(featureProperty)) {
			// gets array of features
			ArrayObject array = JsGeoHelper.get().feature(topojson, featureProperty);
			// checks if result is consistent
			if (array != null) {
				return ArrayListHelper.unmodifiableList(array, Feature.FACTORY);
			}
		}
		// if here, the arguments or the feature parsing are not consistent
		// then returns an empty list
		return Collections.emptyList();
	}
	
	/**
	 * Returns <code>true</code> if the string argument is not null and length greater than 0.
	 * 
	 * @param value
	 * @return
	 */
	private static boolean isConsistent(String value) {
		return value != null && value.trim().length() > 0;
	}
	
}
