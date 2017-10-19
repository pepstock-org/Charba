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

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.commons.ChartContainer;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.FontStyle;

abstract class AbstractLabel extends ChartContainer{
	
	private static final int DEFAULT_FONT_SIZE = 12;
	
	private static final String DEFAULT_FONT_COLOR = "#666";
	
	private static final String DEFAULT_FONT_FAMILY = "'Helvetica Neue', 'Helvetica', 'Arial', sans-serif";
	
	private static final int DEFAULT_PADDING = 10;
	
	private enum Property implements Key{
		fontSize,
		fontStyle,
		fontColor,
		fontFamily,
		padding
	}
	
	protected AbstractLabel(AbstractChart<?, ?> chart) {
		super(chart);
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
    
	public void setPadding(int padding) {
		setValue(Property.padding, padding);
	}

    public int getPadding(){
    	return getValue(Property.padding, DEFAULT_PADDING);
    }

}