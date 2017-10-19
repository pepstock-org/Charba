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

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.callbacks.TickCallback;
import org.pepstock.charba.client.commons.GenericJavaScriptObject;
import org.pepstock.charba.client.commons.JavaScriptObjectContainer;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.items.TickItem;

/**
 * 
 */
public class BaseTick extends JavaScriptObjectContainer{
	
	private static final int DEFAULT_FONT_SIZE = 12;
	
	private static final String DEFAULT_FONT_COLOR = "#666";
	
	private static final String DEFAULT_FONT_FAMILY = "'Helvetica Neue', 'Helvetica', 'Arial', sans-serif";
	
	private Axis axis = null;
	
	private TickCallback callback = null;

	private enum Property implements Key {
		callback, 
		fontSize,
		fontStyle,
		fontColor,
		fontFamily,
	}
	
	
	
	// callback // Return an empty string to draw the tick line but hide the tick label
    // Return `null` or `undefined` to hide the tick line entirely
//	callback 	Function 		Returns the string representation of the tick value as it should be displayed on the chart. See callback.
//	fontColor 	Color 	'#666' 	Font color for tick labels.
//	fontFamily 	String 	"'Helvetica Neue', 'Helvetica', 'Arial', sans-serif" 	Font family for the tick labels, follows CSS font-family options.
//	fontSize 	Number 	12 	Font size for the tick labels.
//	fontStyle 	String 	'normal' 	Font style for the tick labels, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
//	
	
	BaseTick() {
		registerNativeTickCallbacktHandler(getJavaScriptObject());
	}

	/**
	 * @param axis the axis to set
	 */
	void setAxis(Axis axis) {
		this.axis = axis;
	}

	/**
	 * @return the axis
	 */
	Axis getAxis() {
		return axis;
	}

	public void setFontSize(int fontSize) {
		setValue(Property.fontSize, fontSize);
	}

    public int getFontSize(){
    	return getValue(Property.fontSize, DEFAULT_FONT_SIZE);
    }

     public void setFontStyle(FontStyle fontStyle){
    	 setValue(Property.fontStyle, fontStyle);
    }

    public FontStyle getFontStyle(){
    	return getValue(Property.fontStyle, FontStyle.values(), FontStyle.normal);
    }    
    
	public void setFontColor(String fontColor) {
		setValue(Property.fontColor, fontColor);
	}

    public String getFontColor(){
    	return getValue(Property.fontColor, DEFAULT_FONT_COLOR);
    }

	public void setFontFamily(String fontFamily) {
		setValue(Property.fontFamily, fontFamily);
	}

    public String getFontFamily(){
    	return getValue(Property.fontFamily, DEFAULT_FONT_FAMILY);
    }
    
    /**
	 * @return the callback
	 */
	public TickCallback getCallback() {
		return callback;
	}

	/**
	 * @param callback the callback to set
	 */
	public void setCallback(TickCallback callback) {
		if (hasToBeRegistered(callback, Property.callback)){
			registerNativeTickCallbacktHandler(getJavaScriptObject());
		}
		this.callback = callback;
	}

	protected String onCallback(TickItem item){
		AbstractChart<?, ?> chart = axis.getChart();
		if (callback != null && chart != null){
			return callback.onCallback(chart, item);
		}
    	return String.valueOf(item.getValue());
    }
    
	/**
	 * 
	 * @param options
	 */
    private native void registerNativeTickCallbacktHandler(GenericJavaScriptObject options)/*-{
		var self = this;
	    options.callback = function(value, index, values){
	    	var myItem = new Object();
	    	myItem.value = value;
	    	myItem.index = index;
	    	myItem.values = values;
	    	return self.@org.pepstock.charba.client.options.scales.BaseTick::onCallback(Lorg/pepstock/charba/client/items/TickItem;)(myItem);
	    }
	}-*/;
    
}