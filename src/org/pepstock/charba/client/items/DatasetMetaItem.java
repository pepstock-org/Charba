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

import org.pepstock.charba.client.Type;
import org.pepstock.charba.client.commons.Key;

/**
 * This object is just a proxy object, created from JavaScript side, to wrap an JavaScript array.<br>
 * Created and passed by CHART.JS.<br>
 * This object is NOT used or passed to any callbacks or event handling
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class DatasetMetaItem extends BaseItem {

	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
		hidden,
		type,
		data
	}

	/**
	 * Needed for GWt injection
	 */
	protected DatasetMetaItem() {
		// do nothing
	}
	
	/**
	 * Returns the type of axis. If not set, the default is <code>linear</code>.
	 * 
	 * @return the type of axis
	 * @see org.pepstock.charba.client.enums.AxisType
	 */
	public final Type getType() {
		String value = getString(Property.type.name());
		for (Type type : Type.values()){
			if (type.name().equalsIgnoreCase(value)){
				return type;
			}
		}
		return Type.bar;
	}

	/**
	 * Returns if the dataset is hidden.
	 * 
	 * @return <code>true</code> if the dataset is hidden, otherwise <code>false</code>.
	 */
	public final boolean isHidden() {
		return getBoolean(Property.hidden.name());
	}

	/**
	 * Sets if the dataset must be hidden.
	 * 
	 * @param hidden <code>true</code> if the dataset must be hidden, otherwise <code>false</code>.
	 */
	public final void setHidden(boolean hidden) {
		setBoolean(Property.hidden.name(), hidden);
	}

	/**
	 * Returns a list of dataset metadata items.
	 * 
	 * @return a list of dataset metadata items.
	 * @see org.pepstock.charba.client.items.DatasetItem
	 */
	public final List<DatasetItem> getDatasets() {
		return getObjectArray(Property.data.name());
	}

}