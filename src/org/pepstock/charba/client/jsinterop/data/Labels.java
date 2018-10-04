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
package org.pepstock.charba.client.jsinterop.data;

import org.pepstock.charba.client.jsinterop.commons.Array;
import org.pepstock.charba.client.jsinterop.commons.ArrayMixedObject;
import org.pepstock.charba.client.jsinterop.commons.ArrayString;
import org.pepstock.charba.client.jsinterop.commons.ObjectType;

/**
 * Contains the labels of the chart.<br>
 * Is able to manage also multi-line labels.
 * 
 * @author Andrea "Stock" Stocchero
 * @see com.google.gwt.core.client.JavaScriptObject
 * @see com.google.gwt.core.client.JsArrayMixed
 */
public final class Labels {
	
	private final ArrayMixedObject array;
	
	/**
	 * Needed for GWT injection
	 */
	private Labels(ArrayMixedObject array) {
		this.array = array != null ? array : new ArrayMixedObject();
	}
	
	/**
	 * @return the array
	 */
	ArrayMixedObject getArray() {
		return array;
	}

	/**
	 * Builds new label object
	 * @return new label object
	 */
	public static Labels build(){
		return new Labels(new ArrayMixedObject());
	}

	static Labels load(ArrayMixedObject array){
		return new Labels(array);
	}

	/**
	 * Loads single line labels.
	 * @param values array of labels
	 */
	public final void load(String... values){
		// checks if is a valid array
		if (values != null && values.length > 0){
			// scans values
			for(String value: values){
				// pushes to JS array 
				array.push(value);
			}
		}
	}
	
	/**
	 * Adds a single line label
	 * @param value a single label
	 */
	public final void add(String value){
		// if consistent
		if (value != null){
			// pushes to JS array
			array.push(value);
		}
	}
	
	/**
	 * Adds a multi line label
	 * @param values array ofstring which represents a multi line label
	 */
	public final void add(String... values){
		// checks if is a valid array
		if (values != null && values.length > 0){
			// creates new JS array
			array.push(ArrayString.of(values));
		}
	}
	
	/**
	 * Returns a multi line label at a specific index. An array of strings is returned. 
	 * @param index index of label
	 * @return an array of strings
	 */
	public final String[] getStrings(int index){
		// checks range
		if (checkRange(index)){
			// gets multi line array
			Object multiValues = array.get(index);
			// if consistent
			if (multiValues != null){
				if (Array.isArray(multiValues)) {
					ArrayString internalArray = (ArrayString) multiValues;
					// creates an string array
					String[] result = new String[internalArray.length()];
					// scans all values
					for (int i=0; i<internalArray.length(); i++){
						// adds to java array
						result[i] = internalArray.get(i);
					}
					// returns array
					return result;
				} else {
					return new String[] {(String)multiValues};
				}
			} else {
				// returns an empty array
				return new String[0];
			}
		}
		// returns an empty array
		return new String[0];
	}
	
	/**
	 * Returns the type of a label at specific index.
	 * @param index index of label
	 * @return the type of label or null if out of range
	 */
	public final ObjectType getType(int index){
		// checks range
		if (checkRange(index)){
			// gets type of label
			return Array.isArray(array.get(index)) ? ObjectType.Array : ObjectType.String ;
		}
		return ObjectType.Undefined;
	}
	
	/**
	 * Checks if the index is in the right range.
	 * @param index index to be checked
	 * @return <code>true</code> if the index is in the right range otherwise false
	 */
	private final boolean checkRange(int index){
		return index >= 0 && index < array.length();
	}

}