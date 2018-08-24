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
 * Wrapper of scales node of CHART.JS.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class ScalesNode extends JavaScriptObjectContainer {

	/**
	 * Default name of X axis
	 */
	public static final String DEFAULT_X_AXIS_ID = "x-axis-0";

	/**
	 * Default name of Y axis
	 */
	public static final String DEFAULT_Y_AXIS_ID = "y-axis-0";

	/**
	 * Default name of axis when the chart has got only 1 scale (polar, radar)
	 */
	public static final String DEFAULT_SINGLE_AXIS_ID = "scale";

	/**
	 * Wraps the CHART.JS java script object.
	 * 
	 * @param javaScriptObject CHART.JS java script object
	 */
	ScalesNode(GenericJavaScriptObject javaScriptObject) {
		super(javaScriptObject);
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
				result.put(key.name(), new ScaleItem((GenericJavaScriptObject) getValue(key)));
			}
		}
		return result;
	}

}