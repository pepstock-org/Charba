package org.pepstock.charba.client.jsinterop;

import org.pepstock.charba.client.jsinterop.commons.NativeObject;
import org.pepstock.charba.client.jsinterop.defaults.DefaultOptions;
import org.pepstock.charba.client.jsinterop.options.NativeOptions;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

@JsType(isNative = true, namespace = JsPackage.GLOBAL)
public final class Defaults extends NativeObject {

	@JsProperty
	native NativeOptions getGlobal();
	
	@JsOverlay
	public Global global() {
		return new Global(DefaultOptions.get(), getGlobal());
	}

//	@JsProperty(name = "line")
//	public native Object line();
//	
//	@JsProperty(name = "stock")
//	native void setInternalStock(Object value);
//
//	@JsProperty(name = "stock")
//	native Object getInternalStock();
//
//	@JsOverlay
//	public final Object getObject(Type type) {
//		if (hasOwnProperty(type.name())) {
//			return BaseObject.getOwnPropertyDescriptor(this, type.name()).getValue();
//		}
//		return null;
//	}
//	
//	
//	@JsOverlay
//	public final void setStock(double... values) {
//		setInternalStock(values);
//	}
//	
//	@JsOverlay
//	public final void setStock(double value) {
//		setInternalStock(value);
//	}
//	
//	@JsOverlay
//	public final double[] getStock() {
//		Object obj = getInternalStock();
//		if (Array.isArray(obj)) {
//			return (double[]) obj;
//		} else {
//			return new double[] {(double)obj};
//		}
//	}
	
}
