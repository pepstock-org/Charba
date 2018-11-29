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
package org.pepstock.charba.client.jsinterop.items;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;
import org.pepstock.charba.client.jsinterop.commons.NativeObjectContainer;

/**
 * Wrapper of scales node of CHART.JS.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class ScalesNode extends NativeObjectContainer {

	/**
	 * @param nativeObject
	 */
	public ScalesNode(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns a map with all defined axis. Key is the scale ID and value is scale item.
	 * 
	 * @return a map with all defined axis. Key is the scale ID and value is scale item.
	 */
	public Map<String, ScaleItem> getItems() {
		// creates result
		Map<String, ScaleItem> result = new HashMap<>();
		// gets all keys
		List<Key> keys = keys();
		// if keys are consistent
		if (!keys.isEmpty()) {
			// scans all keys
			for (Key key : keys) {
				// loads scale item
				result.put(key.name(), new ScaleItem(getValue(key)));
			}
		}
		return Collections.unmodifiableMap(result);
	}

}