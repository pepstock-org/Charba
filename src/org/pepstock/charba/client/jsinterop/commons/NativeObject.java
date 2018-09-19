package org.pepstock.charba.client.jsinterop.commons;

import org.pepstock.charba.client.commons.Key;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

@JsType(isNative = true, name="Object", namespace = JsPackage.GLOBAL)
public class NativeObject {
	
//	/**
//	 * 
//	 */
//	public NativeObject() {
//	}
	
	static native <T extends NativeObject> void defineProperty(NativeObject source, String key, NativeDescriptor<T> descriptor);


	static native <T extends NativeObject, S extends NativeObject> NativeDescriptor<T> getOwnPropertyDescriptor(S source, String key);
	
	native boolean hasOwnProperty(String key);

	@JsOverlay
	final boolean hasProperty(Key key) {
		return hasOwnProperty(key.name());
	}
	
	@JsOverlay
	static final void removeProperty(NativeObject object, Key key) {
		JsFactory.remove(object, key.name());
	}

	@JsOverlay
	static final <T extends NativeObject> void defineProperty(NativeObject source, Key key, T object) {
		NativeDescriptor<T> descriptor = new NativeDescriptor<>();
		descriptor.setValue(object);
		descriptor.setConfigurable(true);
		descriptor.setEnumerable(true);
		descriptor.setWritable(true);
		defineProperty(source, key, descriptor);
	}
	
	@JsOverlay
	static final <T extends NativeObject> void defineProperty(NativeObject source, Key key, NativeDescriptor<T> descriptor) {
		defineProperty(source, key.name(), descriptor);
	}

	
	@JsOverlay
	static final <T extends NativeObject, S extends NativeObject> NativeDescriptor<T> getPropertyDescriptor(S source,Key key) {
		return getOwnPropertyDescriptor(source, key.name());
	}

}
