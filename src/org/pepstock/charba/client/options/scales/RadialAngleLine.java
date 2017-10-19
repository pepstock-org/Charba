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
package org.pepstock.charba.client.options.scales;

import org.pepstock.charba.client.commons.JavaScriptObjectContainer;
import org.pepstock.charba.client.commons.Key;

/**
 * 
 */
public final class RadialAngleLine extends JavaScriptObjectContainer{
	
	private static final boolean DEFAULT_DISPLAY = true;
	
	private static final String DEFAULT_COLOR = "rgba(0,0,0,0.1)";
	
	private static final int DEFAULT_LINE_WIDTH = 1;
	
	private enum Property implements Key {
		display,
		color,
		lineWidth
	}
	
//	display Boolean true if true, angle lines are shown
//	color Color rgba(0, 0, 0, 0.1) Color of angled lines
//	lineWidth Number 1 Width of angled lines
	
	RadialAngleLine() {
	}
	
	public void setDisplay(boolean display){
		  setValue(Property.display, display);
	}

	public boolean isDisplay(){
		  return getValue(Property.display, DEFAULT_DISPLAY);
	}

	public void setColor(String color){
		  setValue(Property.color, color);
	}

	public String getColor(){
		  return getValue(Property.color, DEFAULT_COLOR);
	}

	public void setLineWidth(int lineWidth){
		  setValue(Property.lineWidth, lineWidth);
	}

	public int getLineWidth(){
		  return getValue(Property.lineWidth, DEFAULT_LINE_WIDTH);
	}

}