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
package org.pepstock.charba.client.datalabels;

import java.util.Collections;
import java.util.List;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;

/**
 * Base object to map multi labels options for {@link DataLabelsPlugin#ID} plugin configuration.<br>
 * It's possible to define multiple labels for each data element using the this object.<br>
 * It is an object where each property represents a new label, the key being the label key and the value being the options specific to each label.<br>
 * These options are merged on top of the options defined at the chart and dataset levels.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class Labels extends AbstractElement {
	
	private final IsDefaultsDataLabelsOptions defaultsOptions;

	/**
	 * Creates the object with native object instance to be wrapped.
	 * 
	 * @param defaultsOptions default options stored into defaults global
	 * @param nativeObject native object instance to be wrapped.
	 */
	Labels(IsDefaultsDataLabelsOptions defaultsOptions, NativeObject nativeObject) {
		super(nativeObject);
		// redefines hashcode in order do not have
		// the property $H for hashcode
		super.redefineHashcode();
		// checks if default value is consistent
		// stores default
		this.defaultsOptions = checkDefaultValuesArgument(defaultsOptions);
	}
	
	/**
	 * Returns new options for specific key.
	 * 
	 * @param key key of the options
	 * @return the new option for that key
	 */
	public LabelItem createLabel(String key) {
		return createLabel(Key.create(key));
	}
	
	/**
	 * Returns new options for specific key.
	 * 
	 * @param key key of the options
	 * @return the new option for that key
	 */
	public LabelItem createLabel(Key key) {
		// checks consistency of key
		Key labelKey = Key.checkAndGetIfValid(key);
		// checks if exists
		if (has(labelKey)) {
			// gets and returns the label item
			return new LabelItem(defaultsOptions, getValue(labelKey));
		} else {
			// creates new item
			LabelItem item = new LabelItem(defaultsOptions, null);
			// stores the item
			setValue(labelKey, item);
			// checks 
			return item;
		}
	}

	/**
	 * Returns the stored options for specific key.
	 * 
	 * @param key key of the options
	 * @return the stored option or <code>null</code> if no options are stored for that key
	 */
	public LabelItem getLabel(String key) {
		return getLabel(Key.create(key));
	}

	/**
	 * Returns the stored options for specific key.
	 * 
	 * @param key key of the options
	 * @return the stored option or <code>null</code> if no options are stored for that key
	 */
	public LabelItem getLabel(Key key) {
		// checks consistency of key
		Key labelKey = Key.checkAndGetIfValid(key);
		// checks if exists
		if (has(labelKey)) {
			// gets and returns the label item
			return new LabelItem(defaultsOptions, getValue(labelKey));
		}
		// if here, the label for the passed key
		// does not exist
		return null;
	}

	/**
	 * Returns <code>true</code> if there is a stored options for specific key.
	 * 
	 * @param key key of the options
	 * @return <code>true</code> if there is a stored options for specific key
	 */
	public boolean hasLabel(String key) {
		return hasLabel(Key.create(key));
	}

	/**
	 * Returns <code>true</code> if there is a stored options for specific key.
	 * 
	 * @param key key of the options
	 * @return <code>true</code> if there is a stored options for specific key
	 */
	public boolean hasLabel(Key key) {
		return has(Key.checkAndGetIfValid(key));
	}
	
	/**
	 * Removes an existing label for specific key.
	 * 
	 * @param key key of the options
	 */
	public void removeLabel(String key) {
		removeLabel(Key.create(key));
	}

	/**
	 * Removes an existing label for specific key.
	 * 
	 * @param key key of the options
	 */
	public void removeLabel(Key key) {
		// checks consistency of key
		// and then removes the label
		removeIfExists(Key.checkAndGetIfValid(key));
	}


	/**
	 * Returns the list of all keys related to stored options.
	 * 
	 * @return the list of all keys related to stored options
	 */
	public List<Key> getLabelKeys() {
		return Collections.unmodifiableList(keys());
	}
}