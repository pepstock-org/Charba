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

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.plugins.AbstractPluginCachedOptions;

/**
 * Base object to map multi labels options for {@link DataLabelsPlugin#ID} plugin configuration.<br>
 * It's possible to define multiple labels for each data element using the this object.<br>
 * It is an object where each property represents a new label, the key being the label key and the value being the options specific to each label.<br>
 * These options are merged on top of the options defined at the chart and dataset levels.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class Labels extends AbstractElement {

	// maps all data labels options set as label options
	private final Map<String, DataLabelsOptions> storedOptions = new HashMap<>();

	/**
	 * To avoid any instantiation
	 */
	Labels() {
		super(null);
		// redefines hashcode in order do not have
		// the property $H for hashcode
		super.redefineHashcode();
	}

	/**
	 * Returns a collection with all stored data labels options.
	 * 
	 * @return a collection with all stored data labels options
	 */
	Collection<AbstractPluginCachedOptions> getAllOptions() {
		return Collections.unmodifiableCollection(storedOptions.values());
	}

	/**
	 * Sets new data labels options with a specific key.
	 * 
	 * @param key key of the options
	 * @param options options instance
	 */
	public void setLabel(String key, DataLabelsOptions options) {
		setLabel(Key.create(key), options);
	}

	/**
	 * Sets new data labels options with a specific key.
	 * 
	 * @param key key of the options
	 * @param options options instance
	 */
	public void setLabel(Key key, DataLabelsOptions options) {
		// checks consistency of key
		Key.checkIfValid(key);
		// stores options
		setValue(key, options);
		// checks if options are consistent
		if (options != null) {
			// adds to map
			storedOptions.put(key.value(), options);
		} else if (storedOptions.containsKey(key.value())) {
			// if here, options are not consistent
			// then removes from map
			storedOptions.remove(key.value());
		}
	}

	/**
	 * Returns the stored options for specific key.
	 * 
	 * @param key key of the options
	 * @return the stored option or <code>null</code> if no options are stored for that key
	 */
	public DataLabelsOptions getLabel(String key) {
		return getLabel(Key.create(key));
	}

	/**
	 * Returns the stored options for specific key.
	 * 
	 * @param key key of the options
	 * @return the stored option or <code>null</code> if no options are stored for that key
	 */
	public DataLabelsOptions getLabel(Key key) {
		// checks consistency of key
		Key.checkIfValid(key);
		// returns the stored options if there is
		return storedOptions.get(key.value());
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
		// checks consistency of key
		Key.checkIfValid(key);
		// checks if there is into object and into map
		return has(key) && storedOptions.containsKey(key.value());
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