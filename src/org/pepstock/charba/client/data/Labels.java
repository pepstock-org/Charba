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
package org.pepstock.charba.client.data;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.pepstock.charba.client.commons.Array;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayMixedObject;
import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.Constants;
import org.pepstock.charba.client.commons.ObjectType;

/**
 * Contains the labels of the chart.<br>
 * Is able to manage also multi-line labels.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class Labels {

	private final ArrayMixedObject array;

	/**
	 * To avoid any instantiations
	 * 
	 * @param array original array of objects.<br>
	 *            If <code>null</code>, an empty array is created
	 */
	private Labels(ArrayMixedObject array) {
		this.array = array != null ? array : new ArrayMixedObject();
	}

	/**
	 * Returns the java script array.
	 * 
	 * @return the java script array.
	 */
	ArrayMixedObject getArray() {
		return array;
	}

	/**
	 * Builds new label object
	 * 
	 * @return new label object
	 */
	public static Labels build() {
		return new Labels(new ArrayMixedObject());
	}

	/**
	 * Loads the labels form a native array.
	 * 
	 * @param array native array.
	 * @return a labels instance
	 */
	static Labels load(ArrayMixedObject array) {
		return new Labels(array);
	}

	/**
	 * Loads single line labels.
	 * 
	 * @param values array of labels
	 */
	public void load(String... values) {
		// checks if is a valid array
		if (values != null && values.length > 0) {
			// scans values
			for (String value : values) {
				// checks is not null
				if (value != null) {
					// pushes to JS array
					add(value);
				}
			}
		}
	}

	/**
	 * Loads single line labels.
	 * 
	 * @param values array of labels
	 */
	public void load(List<String> values) {
		// checks if is a valid array
		if (values != null && !values.isEmpty()) {
			// scans values
			for (String value : values) {
				// checks is not null
				if (value != null) {
					// pushes to JS array
					add(value);
				}
			}
		}
	}

	/**
	 * Adds a single line label
	 * 
	 * @param value a single label
	 */
	public void add(String value) {
		// if consistent
		if (value != null) {
			// checks if contains separator
			if (value.contains(Constants.LINE_SEPARATOR)) {
				// splits the string by separator
				// and adds to object as array
				array.push(ArrayString.fromOrEmpty(value.split(Constants.LINE_SEPARATOR)));
			} else {
				// pushes to JS array
				array.push(value);
			}
		}
	}

	/**
	 * Adds a multi-line label
	 * 
	 * @param values array of string which represents a multi line label
	 */
	public void add(String... values) {
		// checks if is a valid array
		if (values != null && values.length > 0) {
			// creates new JS array and push it
			array.push(ArrayString.fromOrEmpty(values));
		}
	}

	/**
	 * Removes an item of labels by index
	 * 
	 * @param index index of label
	 */
	public void remove(int index) {
		// checks range
		checkRange(index);
		// creates new JS array
		array.remove(index);
	}

	/**
	 * Returns the amount of loaded labels.
	 * 
	 * @return the amount of loaded labels
	 */
	public int size() {
		return array.length();
	}

	/**
	 * Returns <code>tree</code> if no label is loaded.
	 * 
	 * @return <code>tree</code> if no label is loaded
	 */
	public boolean isEmpty() {
		return array.isEmpty();
	}

	/**
	 * Returns the index of the first occurrence of the specified element in the labels, or -1 if this labels instance does not contain the element.
	 * 
	 * @param values element to search for, if an array, the elements will be joined with {@link Constants#LINE_SEPARATOR}
	 * @return the index of the first occurrence of the specified element in the labels, or -1 if this labels instance does not contain the element
	 */
	public int indexOf(String... values) {
		// checks arguments
		if (values != null && values.length > 0 && !isEmpty()) {
			// creates an array
			ArrayString internalArray = ArrayString.fromOrEmpty(values);
			// gets instance to search
			String value = internalArray.join(Constants.LINE_SEPARATOR);
			// scans array
			for (int i = 0; i < size(); i++) {
				// gets stored value
				String storedvalue = getString(i);
				// checks if equals
				if (value.equals(storedvalue)) {
					// found and then returns index
					return i;
				}
			}
		}
		// if here, not found
		return -1;
	}

	/**
	 * Returns a label at a specific index.<br>
	 * If at index there is multi-line label, returns labels with {@link Constants#LINE_SEPARATOR} as separator.<br>
	 * If the index is out of bounds, throws an exception.
	 * 
	 * @param index index of label
	 * @return a label at a specific index
	 */
	public String getString(int index) {
		ObjectType type = getType(index);
		if (ObjectType.ARRAY.equals(type)) {
			ArrayString internalArray = (ArrayString) array.get(index);
			// returns string
			return internalArray.join(Constants.LINE_SEPARATOR);
		}
		// returns string
		// string can not be null, because checked during loading
		return (String) array.get(index);
	}

	/**
	 * Returns a multi line label at a specific index. An unmodifiable list of strings is returned.<br>
	 * If the index is out of bounds, throws an exception.
	 * 
	 * @param index index of label
	 * @return a unmodifiable list of strings
	 */
	public List<String> getStrings(int index) {
		// checks index and type of index
		ObjectType type = getType(index);
		// checks if at index there is an array
		if (ObjectType.ARRAY.equals(type)) {
			// gets array
			ArrayString internalArray = (ArrayString) array.get(index);
			// creates an unmodifiable string list
			return ArrayListHelper.unmodifiableList(internalArray);
		}
		// returns a list with single item
		// string can not be null, because checked during loading
		return Collections.unmodifiableList(Arrays.asList((String) array.get(index)));
	}

	/**
	 * Returns the type of a label at specific index.
	 * 
	 * @param index index of label
	 * @return the type of label or null if out of range
	 */
	public ObjectType getType(int index) {
		// checks range
		checkRange(index);
		// gets type of label
		return Array.isArray(array.get(index)) ? ObjectType.ARRAY : ObjectType.STRING;
	}

	/**
	 * Checks if the index is in the right range.<br>
	 * If the index is out of bounds, throws an exception.
	 * 
	 * @param index index to be checked
	 */
	private void checkRange(int index) {
		if (index < 0 || index >= array.length()) {
			throw new IndexOutOfBoundsException("Index " + index + " is out of bouds [0, " + array.length() + "]");
		}
	}

}