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

import com.google.gwt.core.client.JavaScriptObject;

/**
 * A wrapper to a native GWT JavaScript object.<br>
 * A JavaScriptObject cannot be created directly.<br>
 * JavaScriptObject should be declared as the return type of a JSNI method that returns native (non-Java) objects.<br>
 * A JavaScriptObject passed back into JSNI from Java becomes the original object, and can be accessed in JavaScript as expected.
 * 
 * @author Andrea "Stock" Stocchero
 * @see com.google.gwt.core.client.JavaScriptObject
 */
public class GenericJavaScriptObject extends JavaScriptObject{

	/**
	 * Needed for GWT injection
	 */
    protected GenericJavaScriptObject(){
        // do nothing
    }

    /**
     * Creates an instance of a JavaScript object (internally used).
     * @return a JavaScript object
     */
    static GenericJavaScriptObject build(){
        return JavaScriptObject.createObject().cast();
    }

    /**
     * Stores an array list (integers) into the JavaScript object.
     * @param key name of the property of JavaScript object where to store the array list.
     * @param list array list instance to be stored
     * @see org.pepstock.charba.client.commons.JsIntegerArrayList
     */
    protected final void setIntegerArray(String key, JsIntegerArrayList list) {
		setJavaScriptObject(key, list != null ? list.getJsArray() : null);
	}

    /**
     * Returns an array list (integers) from the JavaScript object.
     * @param key key name of the property of JavaScript object.
     * @return  array list instance stored in this object
     * @see org.pepstock.charba.client.commons.JsIntegerArrayList
     */
	protected final JsIntegerArrayList getIntegerArray(String key) {
		JsArrayIntegerImpl impl = (JsArrayIntegerImpl)getJavaScriptObject(key);
		return new JsIntegerArrayList(impl);
	}

    /**
     * Stores an array list (doubles) into the JavaScript object.
     * @param key name of the property of JavaScript object where to store the array list.
     * @param list array list instance to be stored
     * @see org.pepstock.charba.client.commons.JsDoubleArrayList
     */
	protected final void setDoubleArray(String key, JsDoubleArrayList list) {
		setJavaScriptObject(key, list != null ? list.getJsArray() : null);
	}

    /**
     * Returns an array list (doubles) from the JavaScript object.
     * @param key key name of the property of JavaScript object.
     * @return  array list instance stored in this object
     * @see org.pepstock.charba.client.commons.JsDoubleArrayList
     */
	protected final JsDoubleArrayList getDoubleArray(String key) {
		JsArrayDoubleImpl impl = (JsArrayDoubleImpl)getJavaScriptObject(key);
		return new JsDoubleArrayList(impl);
	}

    /**
     * Stores an array list (strings) into the JavaScript object.
     * @param key name of the property of JavaScript object where to store the array list.
     * @param list array list instance to be stored
     * @see org.pepstock.charba.client.commons.JsStringArrayList
     */
	protected final void setStringArray(String key, JsStringArrayList list) {
		setJavaScriptObject(key, list != null ? list.getJsArray() : null);
	}
	
	/**
     * Returns an array list (strings) from the JavaScript object.
     * @param key key name of the property of JavaScript object.
     * @return  array list instance stored in this object
     * @see org.pepstock.charba.client.commons.JsStringArrayList
     */
	protected final JsStringArrayList getStringArray(String key) {
		JsArrayStringImpl impl = (JsArrayStringImpl)getJavaScriptObject(key);
		return new JsStringArrayList(impl);
	}

    /**
     * Stores an array list (generic JavaScript objects) into the JavaScript object.
     * @param key name of the property of JavaScript object where to store the array list.
     * @param list array list instance to be stored
     * @param <T> type of java script object
     * @see org.pepstock.charba.client.commons.JsObjectArrayList
     */
	protected final <T extends GenericJavaScriptObject> void setObjectArray(String key, JsObjectArrayList<T> list) {
		setJavaScriptObject(key, list != null ? list.getJsArray() : null);
	}

	/**
     * Returns an array list (generic JavaScript objects) from the JavaScript object.
     * @param key key name of the property of JavaScript object.
     * @param <T> type of java script object
     * @return  array list instance stored in this object
     * @see org.pepstock.charba.client.commons.JsObjectArrayList
     */
	protected final <T extends GenericJavaScriptObject> JsObjectArrayList<T> getObjectArray(String key) {
		JsArrayObjectImpl impl = (JsArrayObjectImpl)getJavaScriptObject(key);
		return new JsObjectArrayList<T>(impl);
	}

	/**
	 * Returns true if this object contains no elements.
	 * @return <code>true</code> if this object contains no elements.
	 */
    protected final native boolean isEmpty()/*-{
	    var l = 0;
	    for (p in this) l++;
	    return l==0;
	}-*/;

    /**
     * Returns the number of elements in this object.
     * @return the number of elements in this list.
     */
    protected final native int size()/*-{
	    var l = 0;
	    for (p in this) l++;
	    return l;
	}-*/;

    /**
     * Returns the type of element in this object.<br>
     * <table summary="">
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
    protected final native String typeOf(String key)/*-{
		return typeof this[key];
	}-*/;

    /**
     * Returns true if this object contains the specified element.
     * @param key name of the property of JavaScript object.
     * @return <code>true</code> if this object contains the specified element.
     */
    protected final native boolean contains(String key)/*-{
		return this.hasOwnProperty(key);
	}-*/;
    
    /**
     * Removes the occurrence of the specified element from this object by its property name, if it is present.
     * @param key name of the property of JavaScript object.
     */
    protected final native void remove(String key)/*-{
		delete this[key];
	}-*/;
    
    /**
     * Sets an element (boolean) in this object by key.
     * @param key name of the property of JavaScript object.
     * @param value value of element.
     */
    protected final native void setBoolean(String key, boolean value)/*-{
	    this[key] = value;
	}-*/;
	
    /**
     * Returns the element (boolean) stored in this object at specific key.  
     * @param key name of the property of JavaScript object.
     * @return the element stored in this object at specific key.
     */
	protected final native boolean getBoolean(String key)/*-{
	    return this[key];
	}-*/;
	
    /**
     * Sets an element (int) in this object by key.
     * @param key name of the property of JavaScript object.
     * @param value value of element.
     */
	protected final native void setInt(String key, int value)/*-{
	    this[key] = value;
	}-*/;

    /**
     * Returns the element (int) stored in this object at specific key.  
     * @param key name of the property of JavaScript object.
     * @return the element stored in this object at specific key.
     */
	protected final native int getInt(String key)/*-{
	    return this[key];
	}-*/;

    /**
     * Sets an element (double) in this object by key.
     * @param key name of the property of JavaScript object.
     * @param value value of element.
     */
	protected final native void setDouble(String key, double value)/*-{
	    this[key] = value;
	}-*/;
	
    /**
     * Returns the element (double) stored in this object at specific key.  
     * @param key name of the property of JavaScript object.
     * @return the element stored in this object at specific key.
     */
	protected final native double getDouble(String key)/*-{
	    return this[key];
	}-*/;
	
    /**
     * Sets an element (string) in this object by key.
     * @param key name of the property of JavaScript object.
     * @param value value of element.
     */
	protected final native void setString(String key, String value)/*-{
	    this[key] = value;
	}-*/;
	
    /**
     * Returns the element (string) stored in this object at specific key.  
     * @param key name of the property of JavaScript object.
     * @return the element stored in this object at specific key.
     */
	protected final native String getString(String key)/*-{
	    return this[key];
	}-*/;
	
    /**
     * Sets an element (javaScript object instance) in this object by key.
     * @param key name of the property of JavaScript object.
     * @param value value of element.
     * @see com.google.gwt.core.client.JavaScriptObject
     */
	protected final native void setJavaScriptObject(String key, JavaScriptObject value)/*-{
	    this[key] = value;
	}-*/;
	
    /**
     * Returns the element (javaScript object instance) stored in this object at specific key.  
     * @param key name of the property of JavaScript object.
     * @return the element stored in this object at specific key.
     * @see com.google.gwt.core.client.JavaScriptObject
     */
	protected final native JavaScriptObject getJavaScriptObject(String key)/*-{
	    return this[key];
	}-*/;
    
	/**
	 * Returns a JSON representation of this object.
	 * @return a JSON representation of this object
	 */
    public final native String toJSON()/*-{
    	return JSON.stringify(this, function(key, val) {
  			if (typeof val === 'function') {
    			return val + '';
  			}
  			return val;
		}, 3);
    }-*/;

}