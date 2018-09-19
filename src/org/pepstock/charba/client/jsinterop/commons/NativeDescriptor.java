package org.pepstock.charba.client.jsinterop.commons;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

@JsType(isNative = true, name = "Object", namespace = JsPackage.GLOBAL)
public class NativeDescriptor<T extends NativeObject> {
	
//	/**
//	 * 
//	 */
//	protected NativeDescriptor() {
//	}

	@JsProperty
	public native void setValue(T value);

	@JsProperty
	public native T getValue();

	@JsProperty
	public native void setConfigurable(boolean configurable);
	
	@JsProperty
	public native boolean isConfigurable();

	@JsProperty
	public native void setEnumerable(boolean enumerable);
	
	@JsProperty
	public native boolean isEnumerable();

	@JsProperty
	public native void setWritable(boolean writable);
	
	@JsProperty
	public native boolean isWritable();
	
}
