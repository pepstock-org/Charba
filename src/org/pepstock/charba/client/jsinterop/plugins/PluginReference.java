package org.pepstock.charba.client.jsinterop.plugins;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;
import org.pepstock.charba.client.jsinterop.commons.NativeObjectContainer;
import org.pepstock.charba.client.jsinterop.items.UndefinedValues;

final class PluginReference extends NativeObjectContainer{
	
	/**
	 * Name of fields of JavaScript object. 
	 */
	private enum Property implements Key {
		id
	}

	/**
	 * @param nativeObject
	 */
	PluginReference(NativeObject nativeObject) {
		super(nativeObject);
	}

	String getId() {
		return getValue(Property.id, UndefinedValues.STRING);
	}
	
}
