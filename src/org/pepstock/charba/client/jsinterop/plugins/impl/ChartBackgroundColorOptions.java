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
package org.pepstock.charba.client.jsinterop.plugins.impl;

import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.jsinterop.commons.Checker;

import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsProperty;

/**
 * Configuration options of background color plugin.
 *  
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ChartBackgroundColorOptions{
	
	@JsProperty(name = "backgroundColor")
	native String getNativeBackgroundColor();
	
	@JsProperty(name = "backgroundColor")
	native void setNativeBackgroundColor(String color);

	
	/**
	 * Builds the object with a new java script object setting the default value of plugin.
	 */
	@JsConstructor
	public ChartBackgroundColorOptions() {
		setBackgroundColor(ChartBackgroundColor.DEFAULT_BACKGROUND_COLOR);
	}

	/**
	 * Builds the object using the java script object of options, set by user.<br>
	 * Used internally to call the plugin.
	 * 
	 * @param javaScriptObject configuration of plugin.
	 * @param color default color to use.
	 */
	ChartBackgroundColorOptions(String color) {
		this();
		setBackgroundColor(color);
	}

	/**
	 * Returns the background color.
	 * 
	 * @return the background color.
	 */
	public String getBackgroundColorAsString() {
		return Checker.check(getNativeBackgroundColor(), ChartBackgroundColor.DEFAULT_BACKGROUND_COLOR); 
	}
	
	/**
	 * Returns the background color.
	 * 
	 * @return the background color.
	 */
	public IsColor getBackgroundColor(){
		return ColorBuilder.parse(getBackgroundColorAsString());
	}
	
	/**
	 * Sets the background color.
	 * @param color the background color.
	 */
	public void setBackgroundColor(String color) {
		setNativeBackgroundColor(color);
	}

	/**
	 * Sets the background color.
	 * @param color the background color.
	 */
	public void setBackgroundColor(IsColor color){
		setNativeBackgroundColor(color.toRGBA());
	}
}
