package org.pepstock.charba.client.jsinterop.utils;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

@JsType(isNative = true, namespace = JsPackage.GLOBAL)
public class JSON {
    
	static native String stringify(Object obj, Object function, int spaces);

    public static native Object parse(String obj);
    
    @JsOverlay
    public static String stringify(Object obj) {
    	return stringify(obj, null, -1);
    }
    
    @JsOverlay
    public static String stringify(Object obj, int spaces) {
    	return stringify(obj, null, spaces);
    }

}
