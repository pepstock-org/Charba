package org.pepstock.charba.client.plugins;

import java.util.HashSet;
import java.util.Set;

import org.pepstock.charba.client.Plugin;
import org.pepstock.charba.client.commons.GenericJavaScriptObject;
import org.pepstock.charba.client.commons.JsArrayStringImpl;

import com.google.gwt.core.client.JavaScriptObject;

public final class GlobalPlugins {

	private final Set<String> pluginIds = new HashSet<String>();

	public boolean register(Plugin plugin) throws InvalidPluginIdException{
		// checks the plugin id
		PluginIdChecker.check(plugin.getId());
		if (getIds().contains(plugin.getId())){
			return false;
		}
		// creates a java script object, wrapper of the plugin
		GlobalPlugin wPlugin = new GlobalPlugin(plugin);
		registerPlugin(wPlugin.getObject());
		// stores the id into a set
		pluginIds.add(plugin.getId());
		return true;
	}

	public boolean unregister(String pluginId) throws InvalidPluginIdException{
		// checks the plugin id
		PluginIdChecker.check(pluginId);
		if (!pluginIds.contains(pluginId)){
			return false;
		}
		// creates a java script object, wrapper of the plugin
		unregisterPlugin(pluginId);
		// stores the id into a set
		pluginIds.remove(pluginId);
		return true;
	}

	public Set<String> getIds(){
		JsArrayStringImpl impl = (JsArrayStringImpl)getPluginsIds();
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
	    return $wnd.Chart.plugins.getAll().map(function(element) { 
			return element.id;
		});
	}-*/;


	/**
	 */
	private native void registerPlugin(GenericJavaScriptObject instance)/*-{
		$wnd.Chart.plugins.register(instance);
	}-*/;

	/**
	 */
	private native void unregisterPlugin(String instanceId)/*-{
		var position = $wnd.Chart.plugins.getAll().map(function(element) { 
			return element.id;
		}).indexOf(instanceId);
		if (position >= 0){
			$wnd.Chart.plugins.unregister($wnd.Chart.plugins.getAll()[position]);
		}
	}-*/;

}
