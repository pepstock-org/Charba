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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.pepstock.charba.client.commons.GenericJavaScriptObject;
import org.pepstock.charba.client.commons.JavaScriptObjectContainer;
import org.pepstock.charba.client.commons.Key;

/**
 * Legend internal object to get data about ticks and their length in pixels.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ScaleLongestTextCacheItem extends BaseBoxNodeItem {
	
	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
		data,
		garbageCollect,
		font
	}

	/**
	 * Wraps the CHART.JS java script object.
	 * 
	 * @param javaScriptObject CHART.JS java script object
	 */
	ScaleLongestTextCacheItem(GenericJavaScriptObject javaScriptObject) {
		super(javaScriptObject);
	}

	/**
	 * Returns the font of scale.
	 * 
	 * @return the font of scale. Default is {@link org.pepstock.charba.client.items.UndefinedValues#STRING}
	 */
	public String getFont() {
		return getValue(Property.font, UndefinedValues.STRING);
	}

	/**
	 * Returns the list of ticks in garbage collect item
	 * 
	 * @return the list of ticks in garbage collect item
	 */
	public List<String> getGarbageCollect() {
		return getStringArray(Property.garbageCollect);
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
		// checks if data is present
		if (has(Property.data)) {
			// creates data object
			Data data = new Data((GenericJavaScriptObject) getValue(Property.data));
			// gets all keys
			List<Key> keys = data.keys();
			// checks if has got items
			if (!keys.isEmpty()) {
				// scans all properties
				for (Key key : keys) {
					// loads map
					result.put(key.name(), data.getValue(key, UndefinedValues.INTEGER));
				}
			}
		}
		return result;
	}

	/**
	 * Internal class to expose keys and getValue form generic java script object.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class Data extends JavaScriptObjectContainer {

		/**
		 * Wraps the CHART.JS java script object.
		 * 
		 * @param javaScriptObject CHART.JS java script object
		 */
		Data(GenericJavaScriptObject javaScriptObject) {
			super(javaScriptObject);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.JavaScriptObjectContainer#keys()
		 */
		@Override
		protected List<Key> keys() {
			return super.keys();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.JavaScriptObjectContainer#getValue(org.pepstock.charba.client.commons.Key,
		 * int)
		 */
		@Override
		protected int getValue(Key key, int defaultValue) {
			return super.getValue(key, defaultValue);
		}
	}
}