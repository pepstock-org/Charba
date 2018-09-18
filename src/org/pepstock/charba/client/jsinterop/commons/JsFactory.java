package org.pepstock.charba.client.jsinterop.commons;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

@JsType(isNative = true, name = "JsFactory", namespace = JsPackage.GLOBAL)
public final class JsFactory {

	public static native <T> T newArray();
	
	public static native <T extends NativeObject> T newObject();
	
	public static native <T> CallbackProxy<T> newCallbackProxy();
	
	public static native <T extends NativeObject> void remove(T object, String key);

}
