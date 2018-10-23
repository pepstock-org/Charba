package org.pepstock.charba.client.jsinterop.commons;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

@JsType(isNative = true, name = "JsHelper", namespace = JsPackage.GLOBAL)
public final class JsHelper {

	public static native <T> T newArray();
	
	public static native <T extends NativeObject> T newObject();
	
	public static native <T> CallbackProxy<T> newCallbackProxy();
	
	public static native <T extends NativeObject> void remove(T object, String key);
	
	public static native int propertyAsInt(Object object, String key);
	
	public static native double propertyAsDouble(Object object, String key);

	public static native String propertyAsString(Object object, String key);

}
