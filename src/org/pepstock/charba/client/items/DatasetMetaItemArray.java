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

import java.util.List;

import org.pepstock.charba.client.commons.GenericJavaScriptObject;
import org.pepstock.charba.client.commons.Key;

/**
 * This object is just a proxy object, created from JavaScript side, to wrap an JavaScript array.<br>
 * Created and passed by CHART.JS.<br>
 * This object is NOT used or passed to any callbacks or event handling
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class DatasetMetaItemArray extends GenericJavaScriptObject {

	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
		items
	}

	/**
	 * Needed for GWt injection
	 */
	protected DatasetMetaItemArray() {
		// do nothing
	}

	/**
	 * Returns a list of dataset metadata items.
	 * 
	 * @return a list of dataset metadata items.
	 * @see org.pepstock.charba.client.items.DatasetMetaItem
	 */
	public final List<DatasetMetaItem> getItems() {
		return getObjectArray(Property.items.name());
	}

}