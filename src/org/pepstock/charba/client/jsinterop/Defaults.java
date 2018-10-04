package org.pepstock.charba.client.jsinterop;

import java.util.HashMap;
import java.util.Map;

import org.pepstock.charba.client.Injector;
import org.pepstock.charba.client.Type;
import org.pepstock.charba.client.jsinterop.defaults.DefaultOptions;
import org.pepstock.charba.client.jsinterop.options.ChartOptionsBuilder;

public final class Defaults {
	
	private static final Defaults INSTANCE = new Defaults();
	
	private final NativeDefaults nativeObject;
	
	private final GlobalOptions options;
	
	private final GlobalScale scale;
	
	private final Map<String, ChartOptions> chartOptions = new HashMap<>();

	/**
	 * @param nativeObject
	 */
	Defaults() {
		// to be sure that chart.js has been injected
		Injector.ensureInjected();
		this.nativeObject = Chart.getDefaults();
		this.options = new GlobalOptions(DefaultOptions.get(), nativeObject.getGlobal());
		this.scale = new GlobalScale(nativeObject.getScale());
	}
	
	public static GlobalOptions getGlobal() {
		return INSTANCE.options;
	}

	public static GlobalScale getScale() {
		return INSTANCE.scale;
	}

	public static ChartOptions options(Type type) {
		return new ChartOptions(ChartOptionsBuilder.get(type));
	}

	public static ChartOptions chart(AbstractChart<?, ?> chart) {
		return chart(chart.getType());
	}

	public static ChartOptions chart(Type type) {
		if (!INSTANCE.chartOptions.containsKey(type.name())) {
			INSTANCE.chartOptions.put(type.name(), INSTANCE.nativeObject.chart(type));
		}
		return INSTANCE.chartOptions.get(type.name());
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
