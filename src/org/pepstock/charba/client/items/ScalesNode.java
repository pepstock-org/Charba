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

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.dom.BaseNativeEvent;
import org.pepstock.charba.client.options.IsScaleId;

/**
 * Wrapper of scales node of CHART.JS.<br>
 * This is a wrapper of scale node of Chart (of CHART.JS).
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class ScalesNode extends NativeObjectContainer {

	/**
	 * Creates the item using a native java script object which contains all properties.
	 * 
	 * @param nativeObject native java script object which contains all properties.
	 */
	public ScalesNode(NativeObject nativeObject) {
		super(nativeObject);
		// redefines hashcode in order do not have
		// the property $H for hashcode
		super.redefineHashcode();
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
				result.put(key.value(), new ScaleItem(IsScaleId.create(key.value()), getValue(key)));
			}
		}
		// returns a unmodifiable map
		return Collections.unmodifiableMap(result);
	}

	/**
	 * Returns the scale item if the chart event is inside of one of scales, otherwise <code>null</code>.
	 * 
	 * @param event event to check if inside of one of scales.
	 * @return the scale item if the chart event is inside of one of scales, otherwise <code>null</code>
	 */
	public ScaleItem getScaleIsInside(BaseNativeEvent event) {
		// gets all keys
		List<Key> keys = keys();
		// if keys are consistent
		if (!keys.isEmpty()) {
			// scans all keys
			for (Key key : keys) {
				// loads scale item
				ScaleItem scaleItem = new ScaleItem(IsScaleId.create(key.value()), getValue(key));
				// checks if event is inside
				if (scaleItem.isInside(event)) {
					// returns scale item
					return scaleItem;
				}

			}
		}
		// if here not scales or event not inside of scale box
		return null;
	}

	/**
	 * Returns <code>true</code> if the chart event is inside of one of scales, otherwise <code>false</code>.
	 * 
	 * @param event event to check if inside of one of scales.
	 * @return <code>true</code> if the chart event is inside of one of scales, otherwise <code>false</code>
	 */
	public boolean isInside(BaseNativeEvent event) {
		return getScaleIsInside(event) != null;
	}
}