package org.pepstock.charba.client.jsinterop.commons;

import jsinterop.annotations.JsFunction;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
public class CallbackProxy {
	
	@JsFunction
	public interface VoidCallback {
	    void call(Object context, int val1);
	}
	
	@JsFunction
	public interface ResultCallback {
	    Object call(Object context, int val1, int val2);
	}

	@JsFunction
	public interface Proxy {
	    void call();
	}
	
	@JsProperty
	public native Proxy getProxy();
	
	@JsProperty
	public native Object getCallback();

	@JsProperty
	public native void setCallback(Object callback);

	
}
