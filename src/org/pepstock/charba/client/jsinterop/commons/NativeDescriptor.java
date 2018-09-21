package org.pepstock.charba.client.jsinterop.commons;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

@JsType(isNative = true, name = "Object", namespace = JsPackage.GLOBAL)
public final class NativeDescriptor<T> {

	@JsProperty
	native void setValue(T value);

	@JsProperty
	public native T getValue();

	@JsProperty
	native void setConfigurable(boolean configurable);
	
	@JsProperty
	native boolean isConfigurable();

	@JsProperty
	native void setEnumerable(boolean enumerable);
	
	@JsProperty
	native boolean isEnumerable();

	@JsProperty
	native void setWritable(boolean writable);
	
	@JsProperty
	native boolean isWritable();
	
}
