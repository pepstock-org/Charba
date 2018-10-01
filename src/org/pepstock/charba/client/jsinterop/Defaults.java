package org.pepstock.charba.client.jsinterop;

import java.util.HashMap;
import java.util.Map;

import org.pepstock.charba.client.Type;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;
import org.pepstock.charba.client.jsinterop.defaults.DefaultOptions;
import org.pepstock.charba.client.jsinterop.options.ChartOptionsBuilder;

public final class Defaults extends NativeObject {
	
	private static Defaults INSTANCE = null;
	
	private final NativeDefaults nativeObject;
	
	private GlobalOptions options;
	
	private GlobalScale scale;
	
	private final Map<String, ChartOptions> chartOptions = new HashMap<>();

	/**
	 * @param nativeObject
	 */
	Defaults(NativeDefaults nativeObject) {
		this.nativeObject = nativeObject;
		this.options = new GlobalOptions(DefaultOptions.get(), nativeObject.getGlobal());
		this.scale = new GlobalScale(nativeObject.getScale());
	}
	
	static Defaults get(NativeDefaults nativeObject) {
		if (INSTANCE == null) {
			INSTANCE = new Defaults(nativeObject);
		}
		return INSTANCE;
	}

	public GlobalOptions global() {
		return options;
	}

	public GlobalScale scale() {
		return scale;
	}

	public ChartOptions options(Type type) {
		return new ChartOptions(ChartOptionsBuilder.get(type));
	}

	public ChartOptions chart(AbstractChart<?, ?> chart) {
		return chart(chart.getType());
	}

	public ChartOptions chart(Type type) {
		if (!chartOptions.containsKey(type.name())) {
			chartOptions.put(type.name(), nativeObject.chart(type));
		}
		return chartOptions.get(type.name());
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
