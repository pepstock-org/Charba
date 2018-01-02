/**
    Copyright 2017 Andrea "Stock" Stocchero

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

	    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/
package org.pepstock.charba.client.plugins;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.Plugin;
import org.pepstock.charba.client.commons.GenericJavaScriptObject;
import org.pepstock.charba.client.commons.JsObjectArrayList;
import org.pepstock.charba.client.options.InvalidPluginIdException;

import com.google.gwt.safehtml.shared.UriUtils;

/**
 * 
 */
public final class Plugins {
	
	private static final String INVALID_PLUGIN__ID_NULL = "Plugin id can not be null ";
	
	private static final String INVALID_PLUGIN__ID_FIRST_CHAR = "Plugin id can not start with a dot or an underscore ";

	private static final String INVALID_PLUGIN__ID_URL_SAFE = "Plugin id can not contain any non-URL-safe characters ";
	
	private static final String INVALID_PLUGIN__ID_UPPERCASE = "Plugin id can not contain uppercase letters ";
	
	private static final char DOT = '.';
	
	private static final char UNDERSCORE = '.';

	// chart instance
	private final AbstractChart<?, ?> chart;
	
	private final List<WrapperPlugin> plugins = new LinkedList<WrapperPlugin>();
	
	public Plugins(AbstractChart<?, ?> chart) {
		this.chart = chart;
	}

	public void add(Plugin plugin) throws InvalidPluginIdException{
		if (plugin.getId() == null){
			throw new InvalidPluginIdException(buildMessage(plugin.getId(), INVALID_PLUGIN__ID_NULL));
		} else if (plugin.getId().charAt(0) == DOT || plugin.getId().charAt(0) == UNDERSCORE){
			throw new InvalidPluginIdException(buildMessage(plugin.getId(), INVALID_PLUGIN__ID_FIRST_CHAR));
		} else if (!UriUtils.isSafeUri(plugin.getId())){
			throw new InvalidPluginIdException(buildMessage(plugin.getId(), INVALID_PLUGIN__ID_URL_SAFE));
		} else if (!plugin.getId().toLowerCase(Locale.getDefault()).equals(plugin.getId())){
			throw new InvalidPluginIdException(buildMessage(plugin.getId(), INVALID_PLUGIN__ID_UPPERCASE));
		}
		WrapperPlugin wPlugin = new WrapperPlugin(chart, plugin);	
		plugins.add(wPlugin);
	}

	private String buildMessage(String pluginId, String message){
		StringBuilder sb = new StringBuilder(message);
		sb.append("[").append(pluginId).append("]");
		return sb.toString();
	}
	
	public JsObjectArrayList<GenericJavaScriptObject> getArrayList(){
		JsObjectArrayList<GenericJavaScriptObject> list = new JsObjectArrayList<GenericJavaScriptObject>();
		for (WrapperPlugin plugin : plugins){
			list.add(plugin.getObject());
		}
		return list;
	}
	
}