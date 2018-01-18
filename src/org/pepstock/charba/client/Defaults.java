package org.pepstock.charba.client;

import java.util.HashMap;
import java.util.Map;

import org.pepstock.charba.client.commons.GenericJavaScriptObject;
import org.pepstock.charba.client.defaults.global.Options;
import org.pepstock.charba.client.defaults.scale.Scale;
import org.pepstock.charba.client.plugins.GlobalPlugins;

import com.google.gwt.core.client.JavaScriptObject;

public final class Defaults {

	private static final Defaults INSTANCE = new Defaults();
	
	private final Options global;
	
	private final Scale scale;
	
	private final Map<Type, Options> chartOptions = new HashMap<Type, Options>();
	
	private final GlobalPlugins plugins = new GlobalPlugins();
	/**
	 * To avoid any instantiation
	 */
	private Defaults() {
		Injector.ensureInjected();
		global = new Options((GenericJavaScriptObject)getGlobalOptions());
		scale = new Scale((GenericJavaScriptObject)getGlobalScale());
		for (Type type : Type.values()){
			chartOptions.put(type, new Options((GenericJavaScriptObject)getChart(type.name())));
		}
	}
	
	public static Options getGlobal(){
		return INSTANCE.global;
	}
	
	public static Scale getScale(){
		return INSTANCE.scale;
	}
	
	public static GlobalPlugins getPlugins(){
		return INSTANCE.plugins;
	}
	
	
	public static Options getChartGlobal(Type type){
		if (INSTANCE.chartOptions.containsKey(type)){
			return INSTANCE.chartOptions.get(type);
		}
		return INSTANCE.chartOptions.get(Type.bar);
	}
	
	/**
	 */
	private native JavaScriptObject getGlobalOptions()/*-{
	    return $wnd.Chart.defaults.global;
	}-*/;

	private native JavaScriptObject getGlobalScale()/*-{
    	return $wnd.Chart.defaults.scale;
	}-*/;

	private native JavaScriptObject getChart(String type)/*-{
		return $wnd.Chart.defaults[type];
	}-*/;
	
}
