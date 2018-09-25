package org.pepstock.charba.client.jsinterop.commons;

import jsinterop.annotations.JsFunction;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
public final class CallbackProxy<T> {

	@JsFunction
	public interface Proxy {
	    void call();
	}

	@JsProperty
	public native Proxy getProxy();
	
	@JsProperty
	public native T getCallback();

	@JsProperty
	public native void setCallback(T callback);

	
}
