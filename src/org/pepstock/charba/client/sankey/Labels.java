/**
    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.
*/
package org.pepstock.charba.client.sankey;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.Constants;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.items.Undefined;

/**
 * Is a map to apply a different label to sankey node, node defined by {@link SankeyDataPoint#setFrom(Key)} or {@link SankeyDataPoint#setTo(Key)}.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class Labels extends NativeObjectContainer {

	/**
	 * Creates the object with an empty native object instance.
	 */
	public Labels() {
		this(null);
	}

	/**
	 * Creates the object with native object instance to be wrapped.
	 * 
	 * @param nativeObject native object instance to be wrapped.
	 */
	Labels(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns <code>true</code> if there is at least a key, stored in the map.
	 * 
	 * @return <code>true</code> if there is at least a key, stored in the map
	 */
	public boolean isConsistent() {
		return !empty();
	}

	/**
	 * Sets a value in the map, by its key of the node.
	 * 
	 * @param nodeKey the key of sankey node
	 * @param value the value to assign to the node.
	 */
	public void set(String nodeKey, String value) {
		set(Key.create(nodeKey), value);
	}

	/**
	 * Sets a value in the map, by its key of the node.
	 * 
	 * @param nodeKey the key of sankey node
	 * @param value the value to assign to the node.
	 */
	public void set(Key nodeKey, String value) {
		setValue(nodeKey, value);
	}

	/**
	 * Returns the stored value in the map, by its key of the node.
	 * 
	 * @param nodeKey the key of sankey node
	 * @return the value to assign to the node.
	 */
	public String get(String nodeKey) {
		return get(Key.create(nodeKey));
	}

	/**
	 * Returns the stored value in the map, by its key of the node.
	 * 
	 * @param nodeKey the key of sankey node
	 * @return the value to assign to the node.
	 */
	public String get(Key nodeKey) {
		return getValue(nodeKey, Undefined.STRING);
	}

	/**
	 * Sets a list of labels in the map, by its key of the node.
	 * 
	 * @param nodeKey the key of sankey node
	 * @param value list of labels to assign to the node.
	 */
	public void set(String nodeKey, List<String> value) {
		set(Key.create(nodeKey), value);
	}

	/**
	 * Sets a list of labels in the map, by its key of the node.
	 * 
	 * @param nodeKey the key of sankey node
	 * @param value list of labels to assign to the node.
	 */
	public void set(Key nodeKey, List<String> value) {
		// creates an array
		ArrayString array = ArrayString.fromOrEmpty(value);
		// joins it with line separator for multiple lines
		setValue(nodeKey, array.join(Constants.LINE_SEPARATOR));
	}

	/**
	 * Returns the labels as multiple lines, by its key of the node.
	 * 
	 * @param nodeKey the key of sankey node
	 * @return list of labels
	 */
	public List<String> getAsMultipleLines(String nodeKey) {
		return getAsMultipleLines(Key.create(nodeKey));
	}

	/**
	 * Returns the stored value in the map, by its key of the node.
	 * 
	 * @param nodeKey the key of sankey node
	 * @return list of labels
	 */
	public List<String> getAsMultipleLines(Key nodeKey) {
		// gets the stored value
		String value = get(nodeKey);
		// checks if value is consistent
		if (value != null) {
			// splits the string by line separator
			String[] splittedValue = value.split(Constants.LINE_SEPARATOR);
			// creates and returns the list
			return new LinkedList<>(Arrays.asList(splittedValue));
		}
		// if here, no value
		// then returns an empty list
		return Collections.emptyList();
	}

}