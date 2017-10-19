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
import org.pepstock.charba.client.callbacks.RadialPointLabelCallback;
import org.pepstock.charba.client.commons.GenericJavaScriptObject;
import org.pepstock.charba.client.commons.JavaScriptObjectContainer;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.FontStyle;

/**
 * 
 */
public final class RadialPointLabel extends JavaScriptObjectContainer{
	
	private static final int DEFAULT_FONT_SIZE = 10;
	
	private static final String DEFAULT_FONT_COLOR = "#666";
	
	private static final String DEFAULT_FONT_FAMILY = "'Helvetica Neue', 'Helvetica', 'Arial', sans-serif";
	
	private final Axis axis;
	
	private RadialPointLabelCallback callback = null;

	private enum Property implements Key {
		callback, 
		fontSize,
		fontStyle,
		fontColor,
		fontFamily,
	}
	
//	callback 	Function 		Returns the string representation of the tick value as it should be displayed on the chart. See callback.
//	fontColor 	Color 	'#666' 	Font color for tick labels.
//	fontFamily 	String 	"'Helvetica Neue', 'Helvetica', 'Arial', sans-serif" 	Font family for the tick labels, follows CSS font-family options.
//	fontSize 	Number 	10 	Font size for the tick labels.
//	fontStyle 	String 	'normal' 	Font style for the tick labels, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
//	
	
    RadialPointLabel(Axis axis) {
    	this.axis = axis;
    	registerNativePointLabelCallbacktHandler(getJavaScriptObject());
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
	public RadialPointLabelCallback getCallback() {
		return callback;
	}

	/**
	 * @param callback the callback to set
	 */
	public void setCallback(RadialPointLabelCallback callback) {
		if (hasToBeRegistered(callback, Property.callback)){
			registerNativePointLabelCallbacktHandler(getJavaScriptObject());
		}
		this.callback = callback;
	}

	protected String onCallback(String item){
		AbstractChart<?, ?> chart = axis.getChart();
		if (callback != null && chart != null){
			return callback.onCallback(chart, item);
		}
    	return item;
    }
    
	/**
	 * 
	 * @param options
	 */
    private native void registerNativePointLabelCallbacktHandler(GenericJavaScriptObject options)/*-{
		var self = this;
	    options.callback = function(item){
	    	return self.@org.pepstock.charba.client.options.scales.RadialPointLabel::onCallback(Ljava/lang/String;)(item);
	    }
	}-*/;
}