package org.pepstock.charba.client.jsinterop.commons;

import org.pepstock.charba.client.jsinterop.utils.JSON;

public abstract class NativeObjectContainer<O extends NativeObject> {
	
	private final O nativeObject;
	
	/**
	 * @param nativeObject
	 * @param defaultValues
	 */
	protected NativeObjectContainer(O nativeObject) {
		this.nativeObject = nativeObject;
	}
	
	protected final O getNativeObject() {
		return nativeObject;
	}
	
	public final String toJSON() {
		return JSON.stringify(nativeObject, 3);
	}
}
