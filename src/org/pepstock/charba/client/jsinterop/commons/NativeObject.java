package org.pepstock.charba.client.jsinterop.commons;

import org.pepstock.charba.client.commons.Key;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

@JsType(isNative = true, name="Object", namespace = JsPackage.GLOBAL)
public class NativeObject {
	
	/**
	 * 
	 */
	protected NativeObject() {
	}

	public static native NativeDescriptor getOwnPropertyDescriptor(Object object, String key);

	@JsOverlay
	public static final NativeDescriptor getOwnPropertyDescriptor(Object object,Key key) {
		return getOwnPropertyDescriptor(object, key.name());
	}
	
	public native boolean hasOwnProperty(String key);

	@JsOverlay
	public final boolean hasOwnProperty(Key key) {
		return hasOwnProperty(key.name());
	}
	
    /**
     * Removes the occurrence of the specified element from this object by its property name, if it is present.
     * @param key name of the property of JavaScript object.
     */
    protected final native void remove(String key);
	
	
//	public native JsArray<String> keys();

}
