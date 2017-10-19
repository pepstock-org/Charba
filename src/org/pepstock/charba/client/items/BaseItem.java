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
import org.pepstock.charba.client.enums.EnumValue;

public abstract class BaseItem  extends GenericJavaScriptObject {
    
     /** 
     * Needed for GWt injection
     */
    protected BaseItem() {
	}
   
    /**
     * ENUM
     * @param key
     * @param values
     * @param defaultValue
     * @return
     */
    protected final <T> T getValue(Key key, EnumValue<T>[] values, T defaultValue){
    	if (!contains(key.name())){
    		return defaultValue;
    	}
    	Object object = getString(key.name());
		if (object != null){
			String value = object.toString();
			for (EnumValue<T> enumValue : values){
				if (enumValue.name().equalsIgnoreCase(value)){
					return enumValue.getValue();
				}
			}
		}
		return defaultValue;
    }
    
}