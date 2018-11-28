package org.pepstock.charba.client.jsinterop;

import java.util.HashMap;
import java.util.Map;

import org.pepstock.charba.client.Injector;
import org.pepstock.charba.client.Type;
import org.pepstock.charba.client.jsinterop.defaults.globals.DefaultOptions;
import org.pepstock.charba.client.jsinterop.plugins.GlobalPlugins;

public final class Defaults {
	
	private static final Defaults INSTANCE = new Defaults();
	
	private final NativeDefaults nativeObject;
	
	private final GlobalOptions options;
	
	private final GlobalScale scale;
	
	private final GlobalPlugins plugins;
	
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
		this.plugins = new GlobalPlugins(Chart.getPlugins());
	}
	
	public static Defaults get() {
		return INSTANCE;
	}
	
	public GlobalOptions getGlobal() {
		return options;
	}

	public GlobalScale getScale() {
		return scale;
	}
	
	public GlobalPlugins getPlugins() {
		return plugins;
	}

	public ChartOptions options(Type type) {
		//FIXME
		//return new ChartOptions(ChartOptionsBuilder.get(type));
		return new ChartOptions();
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


	
}
