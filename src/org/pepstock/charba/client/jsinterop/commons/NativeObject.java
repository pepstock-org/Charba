package org.pepstock.charba.client.jsinterop.commons;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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
	
	static native <T> void defineProperty(NativeObject source, String key, NativeDescriptor<T> descriptor);
	
	static native <T, S extends NativeObject> NativeDescriptor<T> getOwnPropertyDescriptor(S source, String key);
	
	static native ArrayString keys(NativeObject source);
	
	native boolean hasOwnProperty(String key);

	@JsOverlay
	protected final boolean hasProperty(Key key) {
		return hasOwnProperty(key.name());
	}
	
	@JsOverlay
	protected static final void remove(NativeObject object, Key key) {
		JsFactory.remove(object, key.name());
	}

//	@JsOverlay
//	protected static final void defineProperty(NativeObject source, Key key, boolean object) {
//		NativeDescriptor<Boolean> descriptor = new NativeDescriptor<>();
//		descriptor.setValue(object);
//		descriptor.setConfigurable(true);
//		descriptor.setEnumerable(true);
//		descriptor.setWritable(true);
//		defineProperty(source, key, descriptor);
//	}

	@JsOverlay
	protected static final <T> void defineProperty(NativeObject source, Key key, T object) {
		NativeDescriptor<T> descriptor = new NativeDescriptor<>();
		descriptor.setValue(object);
		descriptor.setConfigurable(true);
		descriptor.setEnumerable(true);
		descriptor.setWritable(true);
		defineProperty(source, key, descriptor);
	}
	
	@JsOverlay
	protected static final <T> void defineProperty(NativeObject source, Key key, NativeDescriptor<T> descriptor) {
		defineProperty(source, key.name(), descriptor);
	}
	
	@JsOverlay
	protected static final <T, S extends NativeObject> NativeDescriptor<T> getProperty(S source, Key key) {
		if (source.hasProperty(key)) {
			return getOwnPropertyDescriptor(source, key.name());
		}
		return null;
	}
	
	@JsOverlay
	protected static final <T, S extends NativeObject> Map<String, T> getObjectAsMap(S source) {
		Map<String, T> result = new HashMap<String, T>();
		ArrayString keys = keys(source);
		if (keys != null && keys.length() > 0) {
			for (int i=0; i<keys.length(); i++) {
				String keyToAdd = keys.get(i);
				NativeDescriptor<T> descriptor = getOwnPropertyDescriptor(source, keyToAdd);
				result.put(keyToAdd, descriptor.getValue());
			}
		}
		return Collections.unmodifiableMap(result);
	}
	
	@JsOverlay
	protected static final <T extends NativeObject> void defineObject(NativeObject source, Key key, T object) {
		NativeDescriptor<T> descriptor = new NativeDescriptor<>();
		descriptor.setValue(object);
		descriptor.setConfigurable(true);
		descriptor.setEnumerable(true);
		descriptor.setWritable(true);
		defineProperty(source, key, descriptor);
	}
	
	@JsOverlay
	protected static final <T extends NativeObject> void defineObject(NativeObject source, Key key, NativeDescriptor<T> descriptor) {
		defineProperty(source, key.name(), descriptor);
	}
	
	@JsOverlay
	protected static final <T extends NativeObject, S extends NativeObject> NativeDescriptor<T> getObject(S source,Key key) {
		if (source.hasProperty(key)) {
			return getOwnPropertyDescriptor(source, key.name());
		}
		return null;
	}

}
