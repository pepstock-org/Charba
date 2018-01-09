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
		GenericJavaScriptObject options = (GenericJavaScriptObject)getGlobalOptions();
		global = new Options(options);
		GenericJavaScriptObject scaleImpl = (GenericJavaScriptObject)getGlobalScale();
		scale = new Scale(scaleImpl);
	}
	
	public static void test(){
		INSTANCE.dump();
	}
	
	public static Options getGlobal(){
		return INSTANCE.global;
	}
	
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

	/**
	 */
	private native void dump()/*-{
	    console.log($wnd.Chart.defaults);
	}-*/;

}
