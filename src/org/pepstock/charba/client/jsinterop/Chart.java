package org.pepstock.charba.client.jsinterop;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

@JsType(isNative = true, name = "Chart", namespace = JsPackage.GLOBAL)
public class Chart {

	@JsProperty(name = "defaults")
	public static native Defaults defaults();

//	@JsProperty(name = "defaults", namespace = JsPackage.GLOBAL)
//	public static native void setDefaults(Object obj);
//
//	public Chart(Context2d ctx, Configuration configuration) {}
	
}
