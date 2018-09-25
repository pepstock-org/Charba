package org.pepstock.charba.client.jsinterop;

import org.pepstock.charba.client.Type;
import org.pepstock.charba.client.jsinterop.commons.NativeDescriptor;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;
import org.pepstock.charba.client.jsinterop.defaults.DefaultOptions;
import org.pepstock.charba.client.jsinterop.options.ChartOptionsBuilder;
import org.pepstock.charba.client.jsinterop.options.NativeOptions;
import org.pepstock.charba.client.jsinterop.options.NativeScale;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

@JsType(isNative = true, namespace = JsPackage.GLOBAL)
public final class Defaults extends NativeObject {

	@JsProperty
	native NativeOptions getGlobal();

	@JsProperty
	native NativeScale getScale();

	
	@JsOverlay
	public GlobalOptions global() {
		return new GlobalOptions(DefaultOptions.get(), getGlobal());
	}

	@JsOverlay
	public GlobalScale scale() {
		return new GlobalScale(getScale());
	}

	@JsOverlay
	public ChartOptions chart(Type type) {
		// returns the configuration creating a key by plugin id.
		NativeDescriptor<NativeOptions> descriptor = getProperty(this, type);
		if (descriptor != null) {
			return new ChartOptions(descriptor.getValue());
		} else {
			return new ChartOptions();
		}
	}

	@JsOverlay
	public ChartOptions options(Type type) {
		return new ChartOptions(ChartOptionsBuilder.get(type));
	}


	
	
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
