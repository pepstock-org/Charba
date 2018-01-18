package org.pepstock.charba.client.plugins.impl;

import org.pepstock.charba.client.commons.GenericJavaScriptObject;
import org.pepstock.charba.client.commons.JavaScriptObjectContainer;
import org.pepstock.charba.client.commons.Key;

import com.google.gwt.core.client.JavaScriptObject;

public final class ChartBackgroundColorOptions extends JavaScriptObjectContainer{
	
	private final String color;
	
	private enum Property implements Key{
		backGroundColor
	}
	
	public ChartBackgroundColorOptions() {
		super();
		this.color = ChartBackgroundColor.DEFAULT_BACKGROUND_COLOR;
	}

	ChartBackgroundColorOptions(JavaScriptObject javaScriptObject, String color) {
		super((GenericJavaScriptObject)javaScriptObject);
		this.color = color;
	}

	public String getBackgroundColor(){
		return getValue(Property.backGroundColor, color);
	}
	
	public void setBackgroundColor(String color){
		setValue(Property.backGroundColor, color);
	}
	
	public JavaScriptObject getObject(){
		return super.getJavaScriptObject();
	}

}
