package org.pepstock.charba.client.jsinterop.options.elements;

import org.pepstock.charba.client.jsinterop.commons.NativeObject;
import org.pepstock.charba.client.jsinterop.options.elements.arc.NativeArc;
import org.pepstock.charba.client.jsinterop.options.elements.line.NativeLine;
import org.pepstock.charba.client.jsinterop.options.elements.point.NativePoint;
import org.pepstock.charba.client.jsinterop.options.elements.rectangle.NativeRectangle;

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
	public native NativeArc getArc();

	@JsProperty
	public native void setArc(NativeArc arc);
	
	@JsProperty
	public native NativeLine getLine();

	@JsProperty
	public native void setLine(NativeLine Line);

	@JsProperty
	public native NativePoint getPoint();

	@JsProperty
	public native void setPoint(NativePoint point);

	@JsProperty
	public native NativeRectangle getRectangle();

	@JsProperty
	public native void setRectangle(NativeRectangle rectangle);

}
