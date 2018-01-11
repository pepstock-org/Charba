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
package org.pepstock.charba.client.commons;

import com.google.gwt.core.client.JsArray;

/**
 * A simple wrapper around a homogeneous native array of object (generic JavaScript objects) values.<br>
 * Extends GWT implementation adding additional methods, helpful to manage the array as a list.
 *  
 * @author Andrea "Stock" Stocchero
 * @see com.google.gwt.core.client.JsArray
 */
public class JsArrayObjectImpl extends JsArray<GenericJavaScriptObject> {

	/**
	 * Needed for GWT injection
	 */
	protected JsArrayObjectImpl() {
		// do nothing
	}

	/**
	 * Returns the index of the last occurrence of the specified element in this object, or -1 if this object does not contain the element.
	 * @param value element to search for
	 * @return the index of the last occurrence of the specified element in this object, or -1 if this object does not contain the element
	 */
    public final native int lastIndexOf(Object value) /*-{
	    return this.lastIndexOf(value);
	}-*/;

    /**
     * Returns the index of the first occurrence of the specified element in this object, or -1 if this object does not contain the element.
     * @param value element to search for
     * @return the index of the first occurrence of the specified element in this object, or -1 if this object does not contain the element
     */
    public final native int indexOf(Object value) /*-{
	    return this.indexOf(value);
	}-*/;

    /**
     * Removes all of the elements from this object. The object will be empty after this call returns.
     */
    public final native void clear() /*-{
    	var len = this.length;
	    this.splice(0, len);
	}-*/;

    /**
     * Removes the element at the specified position in this object. Shifts any subsequent elements to the left (subtracts one from their indices). Returns the element that was removed from the object.
     * @param index  the index of the element to be removed
     * @return the element previously at the specified position
     */
    public final native GenericJavaScriptObject remove(int index) /*-{
	    return this.splice(index, 1)[0];
	}-*/;

    /**
     * Inserts the specified element at the specified position in this object. Shifts the element currently at that position (if any) and any subsequent elements to the right (adds one to their indices).
     * @param index index at which the specified element is to be inserted
     * @param value element to be inserted
     */
    public final native void insertAt(int index, GenericJavaScriptObject value) /*-{
	    return this.splice(index, 0, value);
	}-*/;
    
	/**
	 * Returns a JSON representation of this object.
	 * @return a JSON representation of this object FIXME errore val_0_g$.toJSON is not a function
	 */
    public final native String toJSON()/*-{
		return JSON.stringify(this, function(key, val) {
				if (typeof val === 'function') {
				return val + '';
				}
				return val.toJSON();
		}, 3);
	}-*/;

	
}