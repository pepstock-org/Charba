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
import org.pepstock.charba.client.enums.FontStyle;

/**
 * 
 */
public class CartesianScaleLabel extends JavaScriptObjectContainer{
	
	private static final int DEFAULT_FONT_SIZE = 12;
	
	private static final String DEFAULT_FONT_COLOR = "#666";
	
	private static final String DEFAULT_FONT_FAMILY = "'Helvetica Neue', 'Helvetica', 'Arial', sans-serif";
	
	private static final boolean DEFAULT_DISPLAY = false;
	
	private static final String DEFAULT_LABEL_STRING = "";
	
	private static final String DEFAULT_LINE_HEIGHT = "1.2";
	
	private static final int DEFAULT_PADDING = 4;

	private enum Property implements Key {
		display,
		labelString,
		lineHeight,
		padding,
		fontSize,
		fontStyle,
		fontColor,
		fontFamily,
	}
	
//	display Boolean false If true, display the axis title.
//	labelString String '' The text for the title. (i.e. "# of People" or "Response Choices").
//	lineHeight `Number String` 1.2 Height of an individual line of text (see MDN)
//	fontColor Color '#666' Font color for scale title.
//	fontFamily String "'Helvetica Neue', 'Helvetica', 'Arial', sans-serif" Font family for the scale title, follows CSS font-family options.
//	fontSize Number 12 Font size for scale title.
//	fontStyle String 'normal' Font style for the scale title, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
//	padding Number or Object 4 Padding to apply around scale labels. Only top and bottom are implemented.

	CartesianScaleLabel() {
	}
	
	public void setDisplay(boolean display){
		  setValue(Property.display, display);
	}

	public boolean isDisplay(){
		  return getValue(Property.display, DEFAULT_DISPLAY);
	}

	public void setLabelString(String labelString){
		  setValue(Property.labelString, labelString);
	}

	public String getLabelString(){
		  return getValue(Property.labelString, DEFAULT_LABEL_STRING);
	}

	public void setLineHeight(String lineHeight){
		  setValue(Property.lineHeight, lineHeight);
	}

	public String getLineHeight(){
		  return getValue(Property.lineHeight, DEFAULT_LINE_HEIGHT);
	}

	public void setPadding(int padding){
		  setValue(Property.padding, padding);
	}

	public int getPadding(){
		  return getValue(Property.padding, DEFAULT_PADDING);
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
    	return getValue(Property.fontStyle, FontStyle.class, FontStyle.normal);
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
    
}