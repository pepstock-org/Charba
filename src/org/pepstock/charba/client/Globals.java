package org.pepstock.charba.client;

import java.util.HashSet;
import java.util.Set;

import org.pepstock.charba.client.commons.GenericJavaScriptObject;
import org.pepstock.charba.client.commons.JsArrayStringImpl;
import org.pepstock.charba.client.globals.Options;

import com.google.gwt.core.client.JavaScriptObject;

public final class Globals {

	private static final Globals INSTANCE = new Globals();
	
	private final Options options;
	
	/**
	 * To avoid any instantiation
	 */
	private Globals() {
		Injector.ensureInjected();
		GenericJavaScriptObject impl = (GenericJavaScriptObject)INSTANCE.getGlobalOptions();
		options = new Options(impl);
	}
	
	public static Options getOptions(){
		return INSTANCE.options;
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

}
