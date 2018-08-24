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

import java.util.LinkedList;
import java.util.List;

import org.pepstock.charba.client.commons.GenericJavaScriptObject;
import org.pepstock.charba.client.commons.JavaScriptObjectContainer;
import org.pepstock.charba.client.commons.Key;

/**
 * This object is just a proxy object, created from JavaScript side, to wrap an JavaScript array.<br>
 * Created and passed by CHART.JS.<br>
 * This object is NOT used or passed to any callbacks or event handling
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class TooltipItemArray extends JavaScriptObjectContainer {

	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
		items
	}

	/**
	 * Wraps the CHART.JS java script object.
	 * 
	 * @param javaScriptObject CHART.JS java script object
	 */
	public TooltipItemArray(GenericJavaScriptObject javaScriptObject) {
		super(javaScriptObject);
	}

	/**
	 * Returns a list of tooltip items.
	 * 
	 * @return a list of tooltip items.
	 * @see org.pepstock.charba.client.items.TooltipItem
	 */
	public List<TooltipItem> getItems() {
		// creates result
		List<TooltipItem> result = new LinkedList<>();
		// checks if items exists
		if (has(Property.items)) {
			// gets and scans all java script objects
			for (GenericJavaScriptObject object : getObjectArray(Property.items)) {
				// creates tooltip otem
				TooltipItem item = new TooltipItem(object);
				result.add(item);
			}
		}
		return result;
	}
}