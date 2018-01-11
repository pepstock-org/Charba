package org.pepstock.charba.client;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.pepstock.charba.client.commons.GenericJavaScriptObject;
import org.pepstock.charba.client.commons.JsArrayStringImpl;
import org.pepstock.charba.client.defaults.global.Options;
import org.pepstock.charba.client.defaults.scale.Scale;

import com.google.gwt.core.client.JavaScriptObject;

public final class Defaults {

	private static final Defaults INSTANCE = new Defaults();
	
	private final Options global;
	
	private final Scale scale;
	
	private final Map<Type, Options> chartOptions = new HashMap<Type, Options>();
	
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
	
	public static Options getChartGlobal(Type type){
		if (INSTANCE.chartOptions.containsKey(type)){
			return INSTANCE.chartOptions.get(type);
		}
		return INSTANCE.chartOptions.get(Type.bar);
	}

	public static Set<String> getPlugins(){
		JsArrayStringImpl impl = (JsArrayStringImpl)INSTANCE.getPluginsIds();
		final Set<String> pluginsIds = new HashSet<String>();
		if (impl != null && impl.length() > 0){
			for (int i=0; i<impl.length(); i++){
				pluginsIds.add(impl.get(i));
			}
		}
		return pluginsIds;
	}
	
	/**
	 */
	private native JavaScriptObject getPluginsIds()/*-{
	    //console.log($wnd.Chart.defaults.global);
	    var ids = $wnd.Chart.plugins._plugins;
	    if (ids != null && ids.length > 0){
	    	var returnValue = new Array();
	    	for(var i = 0; i < ids.length; i++)
			{
     			returnValue[i] = ids[i].id;
			}
			return returnValue;
	    }
	    return new Array[0];
	}-*/;

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
