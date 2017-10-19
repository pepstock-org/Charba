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
package org.pepstock.charba.client;

import org.pepstock.charba.client.commons.GenericJavaScriptObject;
import org.pepstock.charba.client.commons.JavaScriptObjectContainer;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.Easing;

public final class UpdateConfiguration  extends JavaScriptObjectContainer {
	
	private static final boolean DEFAULT_LAZY = false;
	
	private static final int DEFAULT_DURATION = 1000;
	
    private enum Property implements Key{
    	duration,
    	lazy,
    	easing
    }
   
    public void setEasing(Easing easing){
    	setValue(Property.easing, easing);
    }

    public Easing getEasing(){
    	return getValue(Property.easing, Easing.values(), Easing.easeOutQuart);
    }
    
    public void setDuration(int milliseconds){
    	setValue(Property.duration, milliseconds);
    }

    public int getDuration(){
    	return getValue(Property.duration, DEFAULT_DURATION);
    }


	public void setLazy(boolean intersect) {
		setValue(Property.lazy, intersect);
	}

    public boolean isLazy(){
    	return getValue(Property.lazy, DEFAULT_LAZY);
    }
	
	GenericJavaScriptObject getObject(){
		return getJavaScriptObject();
	}

}