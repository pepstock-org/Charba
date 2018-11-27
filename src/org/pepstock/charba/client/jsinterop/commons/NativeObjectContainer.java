/**
    Copyright 2017 Andrea "Stock" Stocchero

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

	    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUString WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/
package org.pepstock.charba.client.jsinterop.commons;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.StandardKey;
import org.pepstock.charba.client.jsinterop.utils.JSON;

import com.google.gwt.core.client.JsDate;

/**
 * Base class for all classes which are wrapping an native java script object.
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 *
 * @param <O> type of native object wraps by this class
 */
public abstract class NativeObjectContainer<O extends NativeObject> {
	
	// native object instance
	private final O nativeObject;

	/**
	 * Creates the object with native object instance to be wrapped.
	 * @param nativeObject native object instance to be wrapped.
	 */
	protected NativeObjectContainer(O nativeObject) {
		this.nativeObject = nativeObject;
	}
	
	/**
	 * Returns true if the embedded JavaScript object contains an element at specific property. 
	 * @param key key of the property of JavaScript object.
	 * @return <code>true</code> if the embedded JavaScript object contains an element at specific property
	 * @see org.pepstock.charba.client.commons.Key
	 */
	protected boolean has(Key key){
		return nativeObject.hasProperty(key.name());
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
	 * Returns the list of properties names of the object.  
	 * @return the list of properties names of the object.
	 */
	protected List<Key> keys() {
		// creates the result
		List<Key> keys = new ArrayList<>();
		// scans all properties names of object
		for (String key : nativeObject.propertyKeys()) {
			// adds a key object by name of the property
			keys.add(new StandardKey(key));
		}
		return keys;
	}
	
	/**
	 * Returns the java script type of the property.
	 * @param key name of the java script property.
	 * @return the java script type of the property.
	 */
	protected ObjectType type(Key key) {
		return JsHelper.typeOf(nativeObject, key.name());
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
		nativeObject.removeProperty(key.name());
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
	 * Sets a value (int) into embedded JavaScript object at specific property.
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 * @see org.pepstock.charba.client.commons.Key
	 */
    protected void setValue(Key key, int value){
       	nativeObject.defineIntProperty(key.name(), value);
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
    	// gets descriptor
    	NativeIntegerDescriptor descriptor = nativeObject.getIntProperty(key.name());
    	// returns value
    	return descriptor == null ? defaultValue : descriptor.getValue();
    }

	/**
	 * Sets a value (double) into embedded JavaScript object at specific property.
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 * @see org.pepstock.charba.client.commons.Key
	 */
    protected void setValue(Key key, double value){
       	nativeObject.defineDoubleProperty(key.name(), value);
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
    	// gets descriptor
    	NativeDoubleDescriptor descriptor = nativeObject.getDoubleProperty(key.name());
    	// returns value
    	return descriptor == null ? defaultValue : descriptor.getValue();
    }

	/**
	 * Sets a value (boolean) into embedded JavaScript object at specific property.
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 * @see org.pepstock.charba.client.commons.Key
	 */
    protected void setValue(Key key, boolean value){
       	nativeObject.defineBooleanProperty(key.name(), value);
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
    	// gets descriptor
    	NativeBooleanDescriptor descriptor = nativeObject.getBooleanProperty(key.name());
    	// returns value
    	return descriptor == null ? defaultValue : descriptor.getValue();
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
    	// gets descriptor
    	NativeStringDescriptor descriptor = nativeObject.getStringProperty(key.name());
    	// returns value
    	return descriptor == null ? defaultValue : descriptor.getValue();
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
        	nativeObject.defineStringProperty(key.name(), value);	
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
    	// gets descriptor
    	NativeDateDescriptor descriptor = nativeObject.getDateProperty(key.name());
    	// returns value
    	return descriptor == null ? defaultValue : new Date((long)descriptor.getValue().getTime());
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
        	nativeObject.defineDateProperty(key.name(), JsDate.create((double) value.getTime()));	
        }
    }

    /**
     * Returns a value (JavaScript Object) into embedded JavaScript object at specific property.
     * @param key key of the property of JavaScript object.
     * @param <T> type of java script object
     * @return value of the property or <code>null</code> if not there
     * @see org.pepstock.charba.client.commons.Key
     */
    protected <T extends NativeObject> T getValue(Key key){
    	// checks if the property exists
    	if (!has(key)){
    		// if no, returns the default value
    		return null;
    	}
    	// gets descriptor
    	NativeObjectDescriptor<T> descriptor = nativeObject.getObjectProperty(key.name());
    	// returns value
    	return descriptor == null ? null : descriptor.getValue();
    }
    
	/**
	 * Sets a value (JavaScript Object) into embedded JavaScript object at specific property.
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 * @see com.google.gwt.core.client.JavaScriptObject
	 * @see org.pepstock.charba.client.commons.Key
	 */
    protected <T extends NativeObject> void setValue(Key key, T value){
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
    		nativeObject.defineObjectProperty(key.name(), value);	
    	}
    }
    
	/**
	 * Sets a value (JavaScript Object) into embedded JavaScript object at specific property.
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 * @see com.google.gwt.core.client.JavaScriptObject
	 * @see org.pepstock.charba.client.commons.Key
	 */
    protected void setValue(Key key, CallbackProxy.Proxy value){
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
    		nativeObject.defineCallbackProperty(key.name(), value);	
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
    	String value = getValue(key, defaultValue.name());
    	// scans all EnumValue array
    	for (T enumValue : clazz.getEnumConstants()){
    		// checks if Enum value name is equals to value
    		if (enumValue.name().equalsIgnoreCase(value)){
    			// returns EnumValue
    			return enumValue;
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
        	nativeObject.defineStringProperty(key.name(), value.name());	
        }
    }
    
    protected <T extends Array> void setArrayValue(Key key, T value){
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
        	nativeObject.defineArrayProperty(key.name(), value);	
        }
    }
    
    protected <T extends Array> T getArrayValue(Key key){
    	// checks if the property exists
    	if (!has(key)){
    		// if no, returns null
    		return null;
    	}
    	// gets descriptor
    	NativeArrayDescriptor<T> descriptor = nativeObject.getArrayProperty(key.name());
    	// returns value
    	return descriptor == null ? null : descriptor.getValue();
    }
	
	/**
	 * Returns the native object instance.
	 * @return the native object instance.
	 */
	protected final O getNativeObject() {
		return nativeObject;
	}
	
	/**
	 * Returns the string JSON representation of the object.
	 * @return the string JSON representation of the object.
	 */
	public final String toJSON() {
		return JSON.stringify(nativeObject, 3);
	}
}
