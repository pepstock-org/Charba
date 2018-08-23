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
import org.pepstock.charba.client.commons.Key;

public class LongestTextCacheItem extends BaseBoxNodeItem {

	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
		data,
		garbageCollect,
		font
	}

	LongestTextCacheItem(GenericJavaScriptObject javaScriptObject) {
		super(javaScriptObject);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Returns the font of scale
	 * 
	 * @return the font of scale
	 */
	public final String getFont() {
		return getValue(Property.font, UndefinedValues.STRING);
	}
	
	public final List<String> getGarbageCollect() {
		return getStringArray(Property.garbageCollect);
	}
	
	public final Map<String, Integer> getData() {
		final Map<String, Integer> result = new HashMap<>();
		List<Key> keys =  keys();
		if (keys != null && !keys.isEmpty()) {
			for (Key key : keys) {
				result.put(key.name(), getValue(key, UndefinedValues.INTEGER));
			}
		}
		return result;
	}

}