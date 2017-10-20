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
package org.pepstock.charba.client.items;

import org.pepstock.charba.client.commons.GenericJavaScriptObject;
import org.pepstock.charba.client.commons.Key;

/**
 * It is the base object for all JavaScript objects items that CHART.JS will pass to Charba for callbacks and events.<br>
 * It maps a JavaScript object providing a set of methods to get and set easily properties from JavaScript Object.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public abstract class BaseItem  extends GenericJavaScriptObject {
    
    /** 
     * Needed for GWt injection
     */
    protected BaseItem() {
    	// do nothing
    }
   
    /**
     * Returns a value (key) into embedded JavaScript object at specific property.
     * @param key key of the property of JavaScript object.
     * @param clazz class of object to get all enumeration values
     * @param defaultValue default value if the property is missing
     * @return value of the property
     * @see org.pepstock.charba.client.commons.Key
     */
    protected final <T extends Key> T getValue(Key key, Class<T> clazz, T defaultValue){
    	// checks if the property exists
    	if (!contains(key.name())){
    		// if no, returns the default value
    		return defaultValue;
    	}
    	// gets the string value
    	String value = getString(key.name());
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
    
}