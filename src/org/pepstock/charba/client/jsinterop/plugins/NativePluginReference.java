package org.pepstock.charba.client.jsinterop.plugins;

import org.pepstock.charba.client.jsinterop.commons.NativeName;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

@JsType(isNative = true, name = NativeName.OBJECT, namespace = JsPackage.GLOBAL)
final class NativePluginReference extends NativeObject {
	
	@JsProperty
	native String getId();
	
}
