package org.pepstock.charba.client.jsinterop.options;

import org.pepstock.charba.client.jsinterop.commons.NativeObject;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

@JsType(isNative = true, namespace = JsPackage.GLOBAL, name="Object")
public final class NativeElements extends NativeObject {
	
	/**
	 * 
	 */
	protected NativeElements() {
	}

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
