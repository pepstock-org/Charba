package org.pepstock.charba.client;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import org.pepstock.charba.client.commons.GenericJavaScriptObject;
import org.pepstock.charba.client.commons.JsArrayStringImpl;
import org.pepstock.charba.client.defaults.Options;
import org.pepstock.charba.client.defaults.Scale;

import com.google.gwt.core.client.JavaScriptObject;

public final class Defaults {
	
	Logger log = Logger.getLogger("mio");

	private static final Defaults INSTANCE = new Defaults();
	
	private final Options global;
	
	private Scale scale;
	
	/**
	 * To avoid any instantiation
	 */
	private Defaults() {
		Injector.ensureInjected();
		global = new Options((GenericJavaScriptObject)getGlobalOptions());
		scale = new Scale((GenericJavaScriptObject)getGlobalScale());
	}
	
	public static void test(){
		Options chart = new Options((GenericJavaScriptObject)INSTANCE.getChart(Type.bar.name()));
		
		for (Scale s : chart.getScales().getXAxes()){
			INSTANCE.log.info(s.toString());
		}
//		INSTANCE.log.info(chart.getScales().getXAxes()+" ");
//		INSTANCE.log.info(chart.getScales().getYAxes()+" ");
//		for (Type t : Type.values()){
//			INSTANCE.log.info("*** "+t.name()+" ***\n");
//			GenericJavaScriptObject options = (GenericJavaScriptObject)INSTANCE.getChart(t.name());
//			INSTANCE.log.info(options.toJSON());
//		}
	}
	
	public static Options getGlobal(){
		return INSTANCE.global;
	}
	
	//FIXME to remove
	public static Scale getScale(){
		return INSTANCE.scale;
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

	/**
	 */
	private native void dump()/*-{
	    console.log($wnd.Chart.defaults.line);
	}-*/;

}
