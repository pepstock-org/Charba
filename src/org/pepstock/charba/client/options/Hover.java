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
package org.pepstock.charba.client.options;

import org.pepstock.charba.client.commons.JavaScriptObjectContainer;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.InteractionMode;

/**
 * 
 */
public final class Hover extends JavaScriptObjectContainer{
	
	private static final boolean DEFAULT_INTERSECT = true;
	
	private static final int DEFAULT_ANIMATION_DURATION = 400;
	
	private enum Property implements Key {
		mode,
		intersect,
		animationDuration
	}
	
    Hover() {
		super();
	}

    public void setMode(InteractionMode mode){
    	setValue(Property.mode, mode);
    }

    public InteractionMode getMode(){
    	return getValue(Property.mode, InteractionMode.class, InteractionMode.nearest);
    }    
    
	public void setIntersect(boolean intersect) {
		setValue(Property.intersect, intersect);
	}

    public boolean isIntersect(){
    	return getValue(Property.intersect, DEFAULT_INTERSECT);
    }
    
    public void setAnimationDuration(int milliseconds){
    	setValue(Property.animationDuration, milliseconds);
    }

    public int getAnimationDuration(){
    	return getValue(Property.animationDuration, DEFAULT_ANIMATION_DURATION);
    }
}