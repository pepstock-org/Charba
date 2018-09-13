package org.pepstock.charba.client.jsinterop.commons;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

// FIXME mancano attributi
@JsType(isNative = true, name = "Object", namespace = JsPackage.GLOBAL)
public class NativeDescriptor {
	
	/**
	 * 
	 */
	protected NativeDescriptor() {
	}

	@JsProperty
	public native Object getValue();
	
	public native boolean hasOwnProperty(String key);
	
//	public native JsArray<String> keys();

}
