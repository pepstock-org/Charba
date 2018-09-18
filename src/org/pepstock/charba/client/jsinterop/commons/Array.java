package org.pepstock.charba.client.jsinterop.commons;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

@JsType(isNative = true, name = "Array", namespace = JsPackage.GLOBAL)
public class Array {
	
	public static native boolean isArray(Object object);

}

