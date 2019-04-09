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
package org.pepstock.charba.client.items;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.commons.ObjectType;

/**
 * Legend internal object to get data about ticks and their length in pixels.<br>
 * This is a wrapper of scale text item of Chart (of CHART.JS).
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ScaleLongestTextCacheItem extends BaseBoxNodeItem {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		DATA("data"),
		GARBAGE_COLLECT("garbageCollect"),
		FONT("font");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use into native object.
		 * 
		 * @param value value of property name
		 */
		private Property(String value) {
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

	/**
	 * Creates the item using a native java script object which contains all properties.
	 * 
	 * @param nativeObject native java script object which contains all properties.
	 */
	ScaleLongestTextCacheItem(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns the font of scale.
	 * 
	 * @return the font of scale. Default is {@link UndefinedValues#STRING}
	 */
	public String getFont() {
		return getValue(Property.FONT, UndefinedValues.STRING);
	}

	/**
	 * Returns the list of ticks in garbage collect item
	 * 
	 * @return the list of ticks in garbage collect item
	 */
	public List<String> getGarbageCollect() {
		// gets array from native object
		ArrayString array = getArrayValue(Property.GARBAGE_COLLECT);
		// returns list
		return ArrayListHelper.unmodifiableList(array);
	}

	/**
	 * Returns a map with all ticks and max lengths in pixel of ticks.<br>
	 * Key is the value of tick in string format, value is the max length in pixels.
	 * 
	 * @return a map with all ticks and max lengths in pixel of ticks.
	 */
	public Map<String, Integer> getData() {
		// creates result
		Map<String, Integer> result = new HashMap<>();
		// checks if data object exists
		if (ObjectType.OBJECT.equals(type(Property.DATA))) {
			// creates data
			Data data = new Data(getValue(Property.DATA));
			// gets all keys
			List<Key> keys = data.dataKeys();
			// if keys are consistent
			if (!keys.isEmpty()) {
				// scans all keys
				for (Key key : keys) {
					// loads data item
					result.put(key.value(), data.dataValue(key));
				}
			}
		}
		// returns a unmodifiable map
		return Collections.unmodifiableMap(result);
	}

	/**
	 * Class which maps the DATA value
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class Data extends NativeObjectContainer {

		/**
		 * Creates the object for data.
		 * 
		 * @param native object which must be mapped
		 */
		Data(NativeObject nativeObject) {
			super(nativeObject);
		}

		/**
		 * Returns the list of properties names of the object.
		 * 
		 * @return the list of properties names of the object.
		 */
		List<Key> dataKeys() {
			return keys();
		}

		/**
		 * Returns a value (int) into embedded JavaScript object at specific property.
		 * 
		 * @param key key of the property of JavaScript object.
		 * @return value of the property
		 */
		int dataValue(Key key) {
			return getValue(key, UndefinedValues.INTEGER);
		}

	}
}