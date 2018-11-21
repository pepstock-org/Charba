package org.pepstock.charba.client.jsinterop.options;

import org.pepstock.charba.client.jsinterop.commons.NativeName;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

@JsType(isNative = true, namespace = JsPackage.GLOBAL, name=NativeName.OBJECT)
final class NativeElements extends NativeObject {
	
	@JsProperty
	native NativeArc getArc();

	@JsProperty
	native void setArc(NativeArc arc);
	
	@JsProperty
	native NativeLine getLine();

	@JsProperty
	native void setLine(NativeLine Line);

	@JsProperty
	native NativePoint getPoint();

	@JsProperty
	native void setPoint(NativePoint point);

	@JsProperty
	native NativeRectangle getRectangle();

	@JsProperty
	native void setRectangle(NativeRectangle rectangle);

}
