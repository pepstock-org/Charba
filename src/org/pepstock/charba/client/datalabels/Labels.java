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
 * It is an object where each property represents a new label, the key being the label key and the value being the options specific to each label.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class Labels extends AbstractElement implements IsDefaultLabels {
	
	private final IsDefaultDataLabelsOptions parent;

	private final IsDefaultLabels defaultOptions;

	/**
	 * Creates the object with native object instance to be wrapped.
	 * 
	 * @param parent parent node where this object is belonging to
	 * @param defaultOptions default options stored into defaults global
	 * @param nativeObject native object instance to be wrapped.
	 */
	Labels(IsDefaultDataLabelsOptions parent, IsDefaultLabels defaultOptions, NativeObject nativeObject) {
		super(nativeObject);
		// redefines hashcode in order do not have
		// the property $H for hashcode
		super.redefineHashcode();
		// checks if default parent is consistent
		// stores parent
		this.parent = checkDefaultValuesArgument(parent);
		// checks if default value is consistent
		// stores default
		this.defaultOptions = checkDefaultValuesArgument(defaultOptions);
	}
	
	/**
	 * Returns new options for specific id.
	 * 
	 * @param id id of the options
	 * @return the new option for that id
	 */
	public LabelItem createLabel(String id) {
		return createLabel(IsDataLabelId.create(id));
	}
	
	/**
	 * Returns new options for specific id.
	 * 
	 * @param id id of the options
	 * @return the new option for that id
	 */
	public LabelItem createLabel(IsDataLabelId id) {
		// checks consistency of key
		IsDataLabelId.checkIfValid(id);
		// checks if exists
		if (has(id)) {
			// gets and returns the label item
			return new LabelItem(parent, getValue(id));
		} else {
			// creates new item
			LabelItem item = new LabelItem(parent, null);
			// stores the item
			setValue(id, item);
			// checks 
			return item;
		}
	}

	/**
	 * Returns the stored options for specific id.
	 * 
	 * @param id id of the options
	 * @return the stored option or <code>null</code> if no options are stored for that id
	 */
	public LabelItem getLabel(String id) {
		return getLabel(IsDataLabelId.create(id));
	}

	/**
	 * Returns the stored options for specific id.
	 * 
	 * @param id id of the options
	 * @return the stored option or <code>null</code> if no options are stored for that id
	 */
	@Override
	public LabelItem getLabel(IsDataLabelId id) {
		// checks consistency of key
		IsDataLabelId.checkIfValid(id);
		// checks if exists
		if (has(id)) {
			// gets and returns the label item
			return new LabelItem(parent, getValue(id));
		}
		// if here, the label for the passed key
		// does not exist and check to default
		return defaultOptions.getLabel(id);
	}

	/**
	 * Returns <code>true</code> if there is a stored options for specific id.
	 * 
	 * @param id id of the options
	 * @return <code>true</code> if there is a stored options for specific id
	 */
	public boolean hasLabel(String id) {
		return hasLabel(IsDataLabelId.create(id));
	}

	/**
	 * Returns <code>true</code> if there is a stored options for specific key.
	 * 
	 * @param id key of the options
	 * @return <code>true</code> if there is a stored options for specific key
	 */
	@Override
	public boolean hasLabel(IsDataLabelId id) {
		// checks if the label id is consistent
		IsDataLabelId.checkIfValid(id);
		// checks if the label id exist
		return has(id) || defaultOptions.hasLabel(id);
	}
	
	/**
	 * Removes an existing label for specific id.
	 * 
	 * @param id id of the options
	 */
	public void removeLabel(String id) {
		removeLabel(IsDataLabelId.create(id));
	}

	/**
	 * Removes an existing label for specific id.
	 * 
	 * @param id key of the options
	 */
	public void removeLabel(IsDataLabelId id) {
		// checks if the label id is consistent
		IsDataLabelId.checkIfValid(id);
		// and then removes the label
		removeIfExists(id);
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