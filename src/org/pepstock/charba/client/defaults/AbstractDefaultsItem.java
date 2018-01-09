package org.pepstock.charba.client.defaults;

import org.pepstock.charba.client.commons.GenericJavaScriptObject;
import org.pepstock.charba.client.commons.JavaScriptObjectContainer;
import org.pepstock.charba.client.commons.Key;

public abstract class AbstractDefaultsItem extends JavaScriptObjectContainer {

	protected AbstractDefaultsItem(GenericJavaScriptObject javaScriptObject) {
		super(javaScriptObject);
	}
	
	protected GenericJavaScriptObject load(Key key){
		if (has(key)){
			return (GenericJavaScriptObject) getValue(key);
		} else {
			return null;
		}
	}

}
