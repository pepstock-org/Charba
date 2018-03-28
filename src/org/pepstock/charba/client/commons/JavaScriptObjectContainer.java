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

import java.util.Date;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsDate;

/**
 * Contains a GWT JavaScript object. This is used for all configuration items for CHART.JS.
 * 
 * @author Andrea "Stock" Stocchero
 * @see org.pepstock.charba.client.commons.GenericJavaScriptObject
 */
public class JavaScriptObjectContainer {

	// every object must have a generic JavaScript object 
	private final GenericJavaScriptObject javaScriptObject;

	protected JavaScriptObjectContainer() {
		this(GenericJavaScriptObject.build());
	}

	protected JavaScriptObjectContainer(GenericJavaScriptObject javaScriptObject) {
		if (javaScriptObject == null){
			this.javaScriptObject = GenericJavaScriptObject.build();	
		} else {
			this.javaScriptObject = javaScriptObject;
		}
	}

	/**
	 * Returns the JavaScript object instance 
	 * @return the JavaScript object instance 
	 */
	protected final GenericJavaScriptObject getJavaScriptObject() {
		return javaScriptObject;
	}
	
	/**
	 * Returns true if the embedded JavaScript object contains no elements.
	 * @return <code>true</code> if the embedded JavaScript object contains no elements.
	 */
	protected boolean isEmpty(){
		return javaScriptObject.isEmpty();
	}
	
	/**
	 * Returns true if the embedded JavaScript object contains an element at specific property. 
	 * @param key key of the property of JavaScript object.
	 * @return <code>true</code> if the embedded JavaScript object contains an element at specific property
	 * @see org.pepstock.charba.client.commons.Key
	 */
	protected boolean has(Key key){
		return javaScriptObject.contains(key.name());
	}

	/**
	 * Returns true if the embedded JavaScript object contains an element at all properties. 
	 * @param keys set of keys of the properties of JavaScript object.
	 * @return <code>true</code> if the embedded JavaScript object contains an element at all properties. 
	 * @see org.pepstock.charba.client.commons.Key
	 */
	protected boolean has(Key... keys){
		// scans keys
		for (Key key : keys){
			// if one is not present
			// returns false
			if (!has(key)){
				return false;
			}
		}
		return true;
	}

	/**
	 * Removes an element (by key) from the embedded JavaScript object if exists.
	 * @param key key of the property of JavaScript object.
	 * @see org.pepstock.charba.client.commons.Key
	 */
	protected void removeIfExists(Key key){
		// checks if there is
		if (has(key)){
			// and then remove
			remove(key);
		}
	}

	/**
	 * Removes an element (by key) from the embedded JavaScript object. 
	 * @param key key of the property of JavaScript object.
	 * @see org.pepstock.charba.client.commons.Key
	 */
	protected void remove(Key key){
		javaScriptObject.remove(key.name());
	}

	/**
	 * Removes a set of elements (by keys) from the embedded JavaScript object. 
	 * @param keys set of keys of the properties of JavaScript object.
	 * @see org.pepstock.charba.client.commons.Key
	 */
	protected void remove(Key... keys){
		// scans all keys
		for (Key key : keys){
			// removes if exists
			removeIfExists(key);
		}
	}

	/**
	 * Returns true if the object is present into embedded JavaScript object.
	 * @param object object to be set
	 * @param keys set of keys of the properties of JavaScript object.
	 * @return <code>true</code> if the object is NOT present.
	 * @see org.pepstock.charba.client.commons.Key
	 */
	protected boolean hasToBeRegistered(Object object, Key... keys){
		// if object is null
		if (object == null){
			// removes all references by keys
			remove(keys);
			// it should not be registered.
			return false;
		}
		// checks if presents
		return !has(keys);
	}
	
	/**
	 * Sets a value (int) into embedded JavaScript object at specific property.
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 * @see org.pepstock.charba.client.commons.Key
	 */
    protected void setValue(Key key, int value){
       	javaScriptObject.setInt(key.name(), value);
    }
    
    /**
     * Returns a value (int) into embedded JavaScript object at specific property.
     * @param key key of the property of JavaScript object.
     * @param defaultValue default value if the property is missing
     * @return value of the property
     * @see org.pepstock.charba.client.commons.Key
     */
    protected int getValue(Key key, int defaultValue){
    	// checks if the property exists
    	if (!has(key)){
    		// if no, returns the default value
    		return defaultValue;
    	}
    	// returns value
    	return javaScriptObject.getInt(key.name());
    }

	/**
	 * Sets a value (double) into embedded JavaScript object at specific property.
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 * @see org.pepstock.charba.client.commons.Key
	 */
    protected void setValue(Key key, double value){
       	javaScriptObject.setDouble(key.name(), value);
    }
    
    /**
     * Returns a value (double) into embedded JavaScript object at specific property.
     * @param key key of the property of JavaScript object.
     * @param defaultValue default value if the property is missing
     * @return value of the property
     * @see org.pepstock.charba.client.commons.Key
     */
    protected double getValue(Key key, double defaultValue){
    	// checks if the property exists
    	if (!has(key)){
    		// if no, returns the default value
    		return defaultValue;
    	}
    	// returns value
    	return javaScriptObject.getDouble(key.name());
    }

	/**
	 * Sets a value (boolean) into embedded JavaScript object at specific property.
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 * @see org.pepstock.charba.client.commons.Key
	 */
    protected void setValue(Key key, boolean value){
       	javaScriptObject.setBoolean(key.name(), value);
    }
    
    /**
     * Returns a value (boolean) into embedded JavaScript object at specific property.
     * @param key key of the property of JavaScript object.
     * @param defaultValue default value if the property is missing
     * @return value of the property
     * @see org.pepstock.charba.client.commons.Key
     */
    protected boolean getValue(Key key, boolean defaultValue){
    	// checks if the property exists
    	if (!has(key)){
    		// if no, returns the default value
    		return defaultValue;
    	}
    	// returns value
    	return javaScriptObject.getBoolean(key.name());
    }

    /**
     * Returns a value (string) into embedded JavaScript object at specific property.
     * @param key key of the property of JavaScript object.
     * @param defaultValue default value if the property is missing
     * @return value of the property
     * @see org.pepstock.charba.client.commons.Key
     */
    protected String getValue(Key key, String defaultValue){
    	// checks if the property exists
    	if (!has(key)){
    		// if no, returns the default value
    		return defaultValue;
    	}
    	// returns value
    	return javaScriptObject.getString(key.name());
    }

	/**
	 * Sets a value (string) into embedded JavaScript object at specific property.
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 * @see org.pepstock.charba.client.commons.Key
	 */
    protected void setValue(Key key, String value){
    	// if value is null
    	// try to remove the reference if exists
    	if (value == null){
    		// checks if the property exists
        	if (has(key)){
        		// removes property
        		remove(key);
        	}
        } else {
        	// sets value
        	javaScriptObject.setString(key.name(), value);	
        }
    }

    /**
     * Returns a value (date) into embedded JavaScript object at specific property.
     * @param key key of the property of JavaScript object.
     * @param defaultValue default value if the property is missing
     * @return value of the property
     * @see org.pepstock.charba.client.commons.Key
     */
    protected Date getValue(Key key, Date defaultValue){
    	// checks if the property exists
    	if (!has(key)){
    		// if no, returns the default value
    		return defaultValue;
    	}
    	JsDate date = (JsDate)javaScriptObject.getJavaScriptObject(key.name());
    	// returns value
    	return new Date((long)date.getTime());
    }

	/**
	 * Sets a value (date) into embedded JavaScript object at specific property.
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 * @see org.pepstock.charba.client.commons.Key
	 */
    protected void setValue(Key key, Date value){
    	// if value is null
    	// try to remove the reference if exists
    	if (value == null){
    		// checks if the property exists
        	if (has(key)){
        		// removes property
        		remove(key);
        	}
        } else {
        	// sets value
        	javaScriptObject.setJavaScriptObject(key.name(), JsDate.create((double) value.getTime()));	
        }
    }

    /**
     * Returns a value (JavaScript Object) into embedded JavaScript object at specific property.
     * @param key key of the property of JavaScript object.
     * @param <T> type of java script object
     * @return value of the property or <code>null</code> if not there
     * @see org.pepstock.charba.client.commons.Key
     * @see com.google.gwt.core.client.JavaScriptObject
     */
    @SuppressWarnings("unchecked")
	protected <T extends JavaScriptObject> T getValue(Key key){
    	return (T)javaScriptObject.getJavaScriptObject(key.name());
    }
    
	/**
	 * Sets a value (JavaScript Object) into embedded JavaScript object at specific property.
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 * @see com.google.gwt.core.client.JavaScriptObject
	 * @see org.pepstock.charba.client.commons.Key
	 */
    protected void setValue(Key key, JavaScriptObject value){
    	// if value is null
    	// try to remove the reference if exists
    	if (value == null){
    		// checks if the property exists
    		if (has(key)){
    			// removes property
    			remove(key);
    		}
    	} else {
    		// sets value
    		javaScriptObject.setJavaScriptObject(key.name(), value);	
    	}
    }

    /**
     * Returns a value (key) into embedded JavaScript object at specific property.
     * @param key key of the property of JavaScript object.
     * @param clazz class of object to get all enumeration values
     * @param defaultValue default value if the property is missing
     * @param <T> type of key
     * @return value of the property
     * @see org.pepstock.charba.client.commons.Key
     */
    protected <T extends Key> T getValue(Key key, Class<T> clazz, T defaultValue){
    	// checks if the property exists
    	if (!has(key)){
    		// if no, returns the default value
    		return defaultValue;
    	}
    	// gets the string value
    	String value = javaScriptObject.getString(key.name());
    	// if not null
		if (value != null){
			// scans all EnumValue array
			for (T enumValue : clazz.getEnumConstants()){
				// checks if Enum value name is equals to value
				if (enumValue.name().equalsIgnoreCase(value)){
					// returns EnumValue
					return enumValue;
				}
			}
		}
		// if here, means the value is wrong into java script object
		// returns the default value
		return defaultValue;
    }
    
	/**
	 * Sets a value (EnumValue) into embedded JavaScript object at specific property.
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 * @param <T> type of key
	 * @see org.pepstock.charba.client.commons.Key
	 */
    protected <T extends Key> void setValue(Key key, T value){
    	// if value is null
    	// try to remove the reference if exists
    	if (value == null){
    		// checks if the property exists
        	if (has(key)){
        		// removes property
        		remove(key);
        	}
        } else {
        	// sets value
        	javaScriptObject.setString(key.name(), value.name());	
        }
    }
    
	/**
	 * Sets a value (double array list) into embedded JavaScript object at specific property.
	 * @param key key of the property of JavaScript object.
	 * @param list value to be set
	 * @see org.pepstock.charba.client.commons.Key
	 * @see org.pepstock.charba.client.commons.JsDoubleArrayList
	 */
    protected final void setDoubleArray(Key key, JsDoubleArrayList list){
    	// if list is null
    	// try to remove the reference if exists
    	if (list == null){
    		// checks if the property exists
        	if (has(key)){
        		// removes property
        		remove(key);
        	}
    	} else {
    		// sets value
    		setValue(key, list.getJsArray());
    	}
    }
    
    /**
     * Returns a value (double array list) into embedded JavaScript object at specific property.
     * @param key key of the property of JavaScript object.
     * @return value of the property or <code>null</code> if not there 
     * @see org.pepstock.charba.client.commons.Key
     * @see org.pepstock.charba.client.commons.JsDoubleArrayList
     */
	protected final JsDoubleArrayList getDoubleArray(Key key) {
		return javaScriptObject.getDoubleArray(key.name());
	}

	/**
	 * Checks if it should set an array list (doubles) or a single value for a specific property.
	 * @param key key of the property of JavaScript object.
	 * @param values array list to set 
	 * @return <code>true</code> if the complete list has been set otherwise false
	 * @see org.pepstock.charba.client.commons.JsDoubleArrayList
	 */
	protected boolean checkAndSetDoubleValues(Key key, JsDoubleArrayList values) {
		// if values is consistent and has got ONLY 1 value
		if (values != null && values.size() == 1){
			// sets at that property only the value and not the array
			setValue(key, values.get(0));
			return false;
		} else {
			// sets array
			setDoubleArray(key, values);
			return true;
		}
	}

	/**
	 * Checks if the value is stored as array (doubles) into the object and accordingly the element.
	 * @param key key of the property of JavaScript object.
	 * @param isArray <code>true</code> if the value has been stored as an array 
	 * @return an array list of elements
	 * @see org.pepstock.charba.client.commons.JsDoubleArrayList
	 */
	protected JsDoubleArrayList checkAndGetDoubleValues(Key key, boolean isArray) {
		// if is array
		if (isArray){
			// returns an array list
			return getDoubleArray(key);
		}
		// gets value
    	double value = getValue(key, Double.MIN_VALUE);
    	// returns an array list with 1 element or empty
    	return (value > Double.MIN_VALUE) ? ArrayListHelper.build(value) : new JsDoubleArrayList();	
    }

	/**
	 * Sets a value (string array list) into embedded JavaScript object at specific property.
	 * @param key key of the property of JavaScript object.
	 * @param list value to be set
	 * @see org.pepstock.charba.client.commons.Key
	 * @see org.pepstock.charba.client.commons.JsStringArrayList
	 */
    protected final void setStringArray(Key key, JsStringArrayList list){
    	// if list is null
    	// try to remove the reference if exists
    	if (list == null){
    		// checks if the property exists
        	if (has(key)){
        		// removes property
        		remove(key);
        	}
    	} else {
    		// sets value
    		setValue(key, list.getJsArray());
    	}
    }

    /**
     * Returns a value (string array list) into embedded JavaScript object at specific property.
     * @param key key of the property of JavaScript object.
     * @return value of the property or <code>null</code> if not there 
     * @see org.pepstock.charba.client.commons.Key
     * @see org.pepstock.charba.client.commons.JsStringArrayList
     */
	protected final JsStringArrayList getStringArray(Key key) {
		return javaScriptObject.getStringArray(key.name());
	}
	
	/**
	 * Checks if it should set an array list (strings) or a single value for a specific property.
	 * @param key key of the property of JavaScript object.
	 * @param values array list to set 
	 * @return <code>true</code> if the complete list has been set otherwise false
	 * @see org.pepstock.charba.client.commons.JsStringArrayList
	 */
	protected boolean checkAndSetStringValues(Key key, JsStringArrayList values) {
		// if values is consistent and has got ONLY 1 value
		if (values != null && values.size() == 1){
			// sets at that property only the value and not the array
			setValue(key, values.get(0));
			return false;
		} else {
			// sets array
			setStringArray(key, values);
			return true;
		}
	}

	/**
	 * Checks if the value is stored as array (strings) into the object and accordingly the element.
	 * @param key key of the property of JavaScript object.
	 * @param isArray <code>true</code> if the value has been stored as an array 
	 * @return an array list of elements
	 * @see org.pepstock.charba.client.commons.JsStringArrayList
	 */
	protected JsStringArrayList checkAndGetStringValues(Key key, boolean isArray) {
		// if is array
    	if (isArray){
    		// returns an array list
    		return getStringArray(key);
    	}
    	// gets value
    	String value = getValue(key, (String)null);
    	// returns an array list with 1 element or empty
    	return value != null ? ArrayListHelper.build(value) : new JsStringArrayList();
	}

	/**
	 * Sets a value (EnumValue array list) into embedded JavaScript object at specific property.
	 * @param key key of the property of JavaScript object.
	 * @param list value to be set
	 * @param <T> type of key
	 * @see org.pepstock.charba.client.commons.Key
	 * @see org.pepstock.charba.client.commons.JsEnumValueArrayList
	 */
    protected final <T extends Key> void setEnumValueArray(Key key, JsEnumValueArrayList<T> list){
    	// if list is null
    	// try to remove the reference if exists
    	if (list == null){
    		// checks if the property exists
        	if (has(key)){
        		// removes property
        		remove(key);
        	}
    	} else {
    		// sets value
    		setValue(key, list.getJsArray());
    	}
    }

	/**
	 * Checks if it should set an array list (EnumValues) or a single value for a specific property.
	 * @param key key of the property of JavaScript object.
	 * @param values array list to set 
	 * @param <T> type of key
	 * @return <code>true</code> if the complete list has been set otherwise false
	 * @see org.pepstock.charba.client.commons.JsEnumValueArrayList
	 */
	protected <T extends Key> boolean checkAndSetEnumValues(Key key, JsEnumValueArrayList<T> values) {
		// if values is consistent and has got ONLY 1 value
		if (values != null && values.size() == 1){
			// sets at that property only the value and not the array
			setValue(key, values.get(0));
			return false;
		} else {
			// sets array
			setEnumValueArray(key, values);
			return true;
		}
	}

	/**
	 * Checks if the value is stored as array (EnumValue) into the object and accordingly the element.
	 * @param key key of the property of JavaScript object.
	 * @param isArray <code>true</code> if the value has been stored as an array 
	 * @return an array list of elements
	 * @see org.pepstock.charba.client.commons.JsStringArrayList
	 */
	protected JsStringArrayList checkAndGetEnumValues(Key key, boolean isArray) {
		return checkAndGetStringValues(key, isArray);
	}
	
	/**
	 * Sets a value (int array list) into embedded JavaScript object at specific property.
	 * @param key key of the property of JavaScript object.
	 * @param list value to be set
	 * @see org.pepstock.charba.client.commons.Key
	 * @see org.pepstock.charba.client.commons.JsIntegerArrayList
	 */
	protected final void setIntegerArray(Key key, JsIntegerArrayList list){
    	// if list is null
    	// try to remove the reference if exists
		if (list == null){
			// checks if the property exists
			if (has(key)){
				// removes property
				remove(key);
			}
		} else {
			// sets value
			setValue(key, list.getJsArray());
		}
	}

    /**
     * Returns a value (integer array list) into embedded JavaScript object at specific property.
     * @param key key of the property of JavaScript object.
     * @return value of the property or <code>null</code> if not there 
     * @see org.pepstock.charba.client.commons.Key
     * @see org.pepstock.charba.client.commons.JsIntegerArrayList
     */
	protected final JsIntegerArrayList getIntegerArray(Key key) {
		return javaScriptObject.getIntegerArray(key.name());
	}

	/**
	 * Checks if it should set an array list (integers) or a single value for a specific property.
	 * @param key key of the property of JavaScript object.
	 * @param values array list to set 
	 * @return <code>true</code> if the complete list has been set otherwise false
	 * @see org.pepstock.charba.client.commons.JsIntegerArrayList
	 */
	protected boolean checkAndSetIntegerValues(Key key, JsIntegerArrayList values) {
		// if values is consistent and has got ONLY 1 value
		if (values != null && values.size() == 1){
			// sets at that property only the value and not the array
			setValue(key, values.get(0));
			return false;
		} else {
			// sets array
			setIntegerArray(key, values);
			return true;
		}
	}

	/**
	 * Checks if the value is stored as array (integers) into the object and accordingly the element.
	 * @param key key of the property of JavaScript object.
	 * @param isArray <code>true</code> if the value has been stored as an array 
	 * @return an array list of elements
	 * @see org.pepstock.charba.client.commons.JsIntegerArrayList
	 */
	protected JsIntegerArrayList checkAndGetIntegerValues(Key key, boolean isArray) {
		// if is array
		if (isArray){
			// returns an array list
			return getIntegerArray(key);
		}
		// gets value
		int value = getValue(key, Integer.MIN_VALUE);
		// returns an array list with 1 element or empty
		return (value != Integer.MIN_VALUE) ? ArrayListHelper.build(value) : new JsIntegerArrayList();	
	}
	
	/**
	 * Sets a value (JavaScript array list) into embedded JavaScript object at specific property.
	 * @param key key of the property of JavaScript object.
	 * @param list value to be set
	 * @param <T> type of java script object
	 * @see org.pepstock.charba.client.commons.Key
	 * @see org.pepstock.charba.client.commons.JsObjectArrayList
	 */
    protected final <T extends GenericJavaScriptObject> void setObjectArray(Key key, JsObjectArrayList<T> list){
    	// if list is null
    	// try to remove the reference if exists
    	if (list == null){
    		// checks if the property exists
        	if (has(key)){
        		// removes property
        		remove(key);
        	}
    	} else {
    		// sets value
    		setValue(key, list.getJsArray());
    	}
    }

	/**
	 * Sets a value (JavaScript object container) into embedded JavaScript object at specific property.
	 * @param key key of the property of JavaScript object.
	 * @param container value to be set
	 * @see org.pepstock.charba.client.commons.Key
	 * @see org.pepstock.charba.client.commons.JavaScriptObjectContainer
	 */
    protected void setValue(Key key, JavaScriptObjectContainer container){
    	// if container is null
    	// try to remove the reference if exists
    	if (container == null){
    		// checks if the property exists
        	if (has(key)){
        		// removes property
        		remove(key);
        	}
        } else {
        	// sets value
            setValue(key, container.getJavaScriptObject());
        }
    }
    
	/**
	 * Sets a value (JavaScript object container array list) into embedded JavaScript object at specific property.
	 * @param key key of the property of JavaScript object.
	 * @param container value to be set
	 * @param <E> type of java script object
	 * @see org.pepstock.charba.client.commons.Key
	 * @see org.pepstock.charba.client.commons.AbstractList
	 */
    protected <E> void setValue(Key key, AbstractList<E> container){
    	// if container is null
    	// try to remove the reference if exists
    	if (container == null){
    		// checks if the property exists
        	if (has(key)){
        		// removes property
        		remove(key);
        	}
        } else {
        	// sets value
            setValue(key, container.getJsArray());
        }
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return javaScriptObject.toJSON();
	}

}