package org.pepstock.charba.client.jsinterop.options;

import org.pepstock.charba.client.jsinterop.commons.CallbackProxy;
import org.pepstock.charba.client.jsinterop.commons.NativeName;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

@JsType(isNative = true, namespace = JsPackage.GLOBAL, name=NativeName.OBJECT)
class NativeTickItem extends NativeFontItem {
	
	@JsProperty
	native void setCallback(CallbackProxy.Proxy proxy);

}
