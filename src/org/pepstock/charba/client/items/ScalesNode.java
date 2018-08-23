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
 * This item contains the new size of the chart after it has been resized.<br>
 * This object has been created ONLY when a resize event occurs.
 * 
 * @author Andrea "Stock" Stocchero
 * @see org.pepstock.charba.client.events.ChartResizeEvent
 * @see org.pepstock.charba.client.events.ChartResizeEventHandler
 */
public final class ScalesNode extends JavaScriptObjectContainer {
	
	public static final String DEFAULT_X_AXIS_ID = "x-axis-0";
	
	public static final String DEFAULT_Y_AXIS_ID = "y-axis-0";
	
	public static final String DEFAULT_SINGLE_AXIS_ID = "scale";
	
	ScalesNode(GenericJavaScriptObject javaScriptObject) {
		super(javaScriptObject);
	}

	public final Map<String, ScaleItem> getItems() {
		final Map<String, ScaleItem> result = new HashMap<>();
		List<Key> keys =  keys();
		if (!keys.isEmpty()) {
			for (Key key : keys) {
				result.put(key.name(), new ScaleItem((GenericJavaScriptObject)getValue(key)));
			}
		}
		return result;
	}

}