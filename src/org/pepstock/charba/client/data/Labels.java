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

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayMixed;
import com.google.gwt.core.client.JsArrayString;

/**
 * Contains the labels of the chart.<br>
 * Is able to manage also multi-line labels.
 * 
 * @author Andrea "Stock" Stocchero
 * @see com.google.gwt.core.client.JavaScriptObject
 * @see com.google.gwt.core.client.JsArrayMixed
 */
public final class Labels extends JavaScriptObject {
	
	/**
	 * Types of different elements of internal array.<br>
	 * String is single line label, array is multi-line label.
	 */
	public enum Type {
		string,
		array
	}

	/**
	 * Needed for GWT injection
	 */
	protected Labels() {
		// do nothing
	}
	
	/**
	 * Builds new label object
	 * @return new label object
	 */
	public static Labels build(){
		return JsArrayMixed.createArray().cast();
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
				push(value);
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
			push(value);
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
			JsArrayString multiValues = JsArrayString.createArray().cast();
			// scans values
			for(String value: values){
				// adds to JS array string
				multiValues.push(value);
			}
			// pushes array to JS array
			push(multiValues);
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
			JsArrayString multiValues = getObject(index);
			// if consistent
			if (multiValues != null){
				// creates an string array
				String[] result = new String[multiValues.length()];
				// scans all values
				for (int i=0; i<multiValues.length(); i++){
					// adds to java array
					result[i] = multiValues.get(i);
				}
				// returns array
				return result;
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
	public final Type getType(int index){
		// checks range
		if (checkRange(index)){
			// gets type of label
			String type = typeOf(index);
			// checks if is a single line or multi.
			if (Type.string.name().equalsIgnoreCase(type)){
				return Type.string;
			} else {
				return Type.array;
			}
		}
		return null;
	}
	
	/**
	 * Checks if the index is in the right range.
	 * @param index index to be checked
	 * @return <code>true</code> if the index is in the right range otherwise false
	 */
	private final boolean checkRange(int index){
		return index >= 0 && index < length();
	}

    /**
     * Returns the type of element in this object.<br>
     * <table>
     * <tr><th>Type</th><th>Result</th></tr>
     * <tr><td>Undefined</td><td>"undefined"</td></tr>
     * <tr><td>Null</td><td>"object"</td></tr>
     * <tr><td>Boolean</td><td>"boolean"</td></tr>
     * <tr><td>Number</td><td>"number"</td></tr>
     * <tr><td>String</td><td>"string"</td></tr>
     * <tr><td>Function</td><td>"function"</td></tr>
     * <tr><td>Any other object</td><td>"object"</td></tr>
     * </table>
     * <br>
     * For further information, refer to this JavaScript documentation: https://developer.mozilla.org/it/docs/Web/JavaScript/Reference/Operators/typeof
     * @param key name of the property of JavaScript object.
     * @return a string value which represents the type of property
     */
    private final native String typeOf(int index) /*-{
	    return typeof this[index];
	 }-*/;

    /**
     * Gets the {@link JavaScriptObject} at a given index.
     * 
     * @param index the index to be retrieved
     * @return the {@code JavaScriptObject} at the given index, or
     *         <code>null</code> if none exists
     */
    private final native <T extends JavaScriptObject> T getObject(int index) /*-{
       return this[index] != null ? Object(this[index]) : null;
    }-*/;
    
    /**
     * Gets the String at a given index.
     * 
     * @param index the index to be retrieved
     * @return the object at the given index, or <code>null</code> if none exists
     */
    public final native String getString(int index) /*-{
       var value = this[index];
       return value == null ? null : String(value);
    }-*/;

    /**
     * Gets the length of the array.
     * 
     * @return the array length
     */
    public final native int length() /*-{
       return this.length;
    }-*/;
    
    /**
     * Pushes the given {@link JavaScriptObject} onto the end of the array.
     */
    private final native void push(JavaScriptObject value) /*-{
       this[this.length] = value;
    }-*/;

    /**
     * Pushes the given String onto the end of the array.
     */
    private final native void push(String value) /*-{
       this[this.length] = value;
    }-*/;
	
    public final native String toJSON()/*-{
		return JSON.stringify(this, function(key, val) {
				if (typeof val === 'function') {
				return val + '';
				}
				return val;
		}, 3);
	}-*/;
}