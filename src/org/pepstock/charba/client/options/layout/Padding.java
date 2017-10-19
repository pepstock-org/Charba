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
package org.pepstock.charba.client.options.layout;

import org.pepstock.charba.client.commons.JavaScriptObjectContainer;
import org.pepstock.charba.client.enums.Position;

/**
 * 
 */
public final class Padding extends JavaScriptObjectContainer{

	private static final int DEFAULT_PADDING = 0;

	public void setLeft(int padding){
    	setValue(Position.left, padding);
    }

    public int getLeft(){
    	return getValue(Position.left, DEFAULT_PADDING);
    }

    public void setRight(int padding){
    	setValue(Position.right, padding);
    }

    public int getRight(){
    	return getValue(Position.right, DEFAULT_PADDING);
    }

    public void setTop(int padding){
    	setValue(Position.top, padding);
    }

    public int getTop(){
    	return getValue(Position.top, DEFAULT_PADDING);
    }

    public void setBottom(int padding){
    	setValue(Position.bottom, padding);
    }

    public int getBottom(){
    	return getValue(Position.bottom, DEFAULT_PADDING);
    }
    
}